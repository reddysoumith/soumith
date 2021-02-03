package org.soumith.excel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadExcelAction extends HttpServlet{
	/**
  * 
  */
 private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		boolean res=true;
		List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
		try{
		 List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
				for(FileItem item : multiparts){
					String ext = null;
					String fileext = null;
					if(!item.isFormField()){
				 String name = new File(item.getName()).getName();
				 int ext2 = name.lastIndexOf(".");
				 if (ext2 > 0)
				  ext = name.substring(ext2 + 1).toLowerCase();
				  fileext = "." + ext;
					if (fileext.equalsIgnoreCase(".xlsx")) {
						InputStream inputstream = item.getInputStream();
			   	result = ExcelRead.readExcelx(inputstream);
		   }
					if (fileext.equalsIgnoreCase(".xls")) {
						 InputStream inputstream = item.getInputStream();
			   	result = ExcelRead.readExcel(inputstream);
		   }
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Data Stored in DB
	 Connection conn = null;
	 PreparedStatement pstmt;
  try {
   Class.forName("org.postgresql.Driver");  
   conn=DriverManager.getConnection("jdbc:postgresql://10.2.9.40:5432/suh","suh","suh");  
   String sql="insert into student_details(student_id,student_name,student_class) values (?,?,?)";
   pstmt = conn.prepareStatement(sql);
  for(HashMap<String, Object> map : result){
   pstmt.setString(1, map.get("roll_no")+"");
   pstmt.setString(2, map.get("name")+"");
   pstmt.setInt(3, Integer.parseInt(map.get("class")+""));
   pstmt.addBatch();
  }
   int status[] = pstmt.executeBatch();
		 for (int i = 0; i < status.length; i++) {
			if(status[i]<=0){
				res=false;
				break;
			}
		}
   if(res){
   				req.setAttribute("msg", "Inserted Successfully....");
    	  System.out.println("Inserted Successfully....");
    	  req.setAttribute("result",result);
   } else{
   		req.setAttribute("msg", "Insertion faild....");
     System.out.println("Insertion faild....");
   }
    
  } catch (SQLException e) {
      throw new Error("Problem", e);
  } catch (ClassNotFoundException e) {
	     e.printStackTrace();
  } finally {
    try {
      if (conn != null) {
          conn.close();
      }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
  }
  RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
  requestDispatcher.forward(req, resp);
  
  
	}
}
