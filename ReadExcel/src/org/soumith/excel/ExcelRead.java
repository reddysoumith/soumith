package org.soumith.excel;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {
	public static List<HashMap<String, Object>> readExcel(InputStream is) throws Exception {
		Vector cellVectorHolder = new Vector();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		try {
			HSSFWorkbook wb = new HSSFWorkbook(is);
			HSSFSheet sheet = wb.getSheetAt(0);
			ArrayList<HashMap<String, String>> data = null;
			HashMap<String, String> map = null;
			data = new ArrayList<HashMap<String, String>>();
			Iterator rows = sheet.rowIterator();
			StringBuffer headerValue = new StringBuffer();
			String header = null;
			int j = 1;
			int count1 = 0;
			while (j == 1) {
				HSSFRow row = (HSSFRow) rows.next();
				Iterator cells = row.cellIterator();
				while (cells.hasNext()) {

					HSSFCell cell = (HSSFCell) cells.next();
					count1 = count1 + 1;
					headerValue.append(cell);
					headerValue.append(",");
				}
				j = j + 1;

			}
			header = headerValue.toString();
			String headerArray[] = header.split(",");
			if (rows.hasNext()) {
				list = rowRead(rows, cellVectorHolder,count1, headerArray,1);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
	public static List<HashMap<String, Object>> readExcelx (InputStream is) throws Exception {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		try {
			XSSFWorkbook myWorkBook = new XSSFWorkbook(is);
			XSSFSheet mySheet = myWorkBook.getSheetAt(0);
			int totalRows = mySheet.getLastRowNum();
			Iterator rowIter = mySheet.rowIterator();
			Vector cellVectorHolder = new Vector();
			StringBuffer headerValue = new StringBuffer();
			String header = null;
			int j = 1;
			int count1 = 0;

			while (j == 1) {
				XSSFRow myRow = (XSSFRow) rowIter.next();
				Iterator cellIter = myRow.cellIterator();
				while (cellIter.hasNext()) {
					XSSFCell myCell = (XSSFCell) cellIter.next();
					count1 = count1 + 1;
					headerValue.append(myCell);
					headerValue.append(",");
				}
				j = j + 1;
			}
			header = headerValue.toString();
			String headerArray[] = header.split(",");
			if (rowIter.hasNext()) {
				  list=rowRead(rowIter, cellVectorHolder,count1, headerArray, 2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
	public static 	List<HashMap<String, Object>> rowRead(Iterator rowIter,Vector cellVectorHolder, int noOfCol,String[] headerArray, int y) throws Exception {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
	String cellData = "";
	String cellData2 = "";
	int cellData3 = 0;
	Vector cellStoreVector = new Vector();
	ArrayList listOfCellValues = new ArrayList();
	HashMap<String, Object> map = new HashMap<String, Object>();
	if (rowIter.hasNext()) {
		while (rowIter.hasNext()) {
			map = new HashMap<String, Object>();
			XSSFRow myRow = null;
			HSSFRow myRowh =null;
			XSSFCell myCell1 =  null;
			XSSFCell myCell2 =  null;
			XSSFCell myCell3 =  null;
			HSSFCell myCellh1 =  null;
			HSSFCell myCellh2 =  null;
			HSSFCell myCellh3 =  null;
			
				if(y == 1){ // XLS
					  myRowh = (HSSFRow) rowIter.next();
				}
				if(y==2){ // XLSX
					  myRow = (XSSFRow) rowIter.next();
				}
			int emptyCheck = 0;
			int colNo = 0;
			int colNo2 = 1;
			int colNo3 = 2;
			while (colNo < noOfCol) {
				  
				if(y==1){
					  myCellh1 = myRowh.getCell(colNo);
					  myCellh2 = myRowh.getCell(colNo2);  
					  myCellh3 = myRowh.getCell(colNo3); 
					  
				}
				if(y==2){
					  myCell1 = myRow.getCell(colNo);
					  myCell2 = myRow.getCell(colNo2);
					  myCell3 = myRow.getCell(colNo3);
				}
				if( (myCell1 == null || myCell1.getCellType() == 2 || myCell2 == null || myCell2.getCellType() == 2 || myCell3 == null || myCell3.getCellType() == 2)  && 
					   (myCellh1 == null || myCellh1.getCellType() == 2 || myCellh2 == null || myCellh2.getCellType() == 2 || myCellh3 == null || myCellh3.getCellType() == 2)) {
					   cellStoreVector.addElement("");
				} else {
					emptyCheck++;
					
					if (colNo == 0 || colNo2 == 1 || colNo3 == 2) {
						
						if(y==1){
							  cellData = myCellh1.toString();
							  cellData2 = myCellh2.toString();	
							  cellData3 = (int)Math.round(myCell3.getNumericCellValue());	
						}
						if(y==2){
							  cellData = myCell1.toString();
							  cellData2 = myCell2.toString();
							  cellData3 = (int)Math.round(myCell3.getNumericCellValue());	
						}
						if(!cellData.equals("")){
							if (!listOfCellValues.contains(cellData)) {
								map.put("name", cellData);
								map.put("roll_no", cellData2);
								map.put("class", cellData3);
						} 
						}
					}
					}
				colNo++;
				colNo2++;
				colNo3++;
			}
			list.add(map);
		}
	}
	return list; 
}
	public static void main(String[] args) {
		try{
			List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
		String ext = null;
		String fileext = null;
		File file =  new File("D:/sample.xls");
		String fileName = file.getName();
		int ext2 = fileName.lastIndexOf(".");
		if (ext2 > 0)
			ext = fileName.substring(ext2 + 1).toLowerCase();
		 fileext = "." + ext;
		 System.out.println(fileext);
			if (fileext.equalsIgnoreCase(".xlsx")) {
				InputStream inputstream = new FileInputStream("D:/sample.xls");
	   	result = readExcelx(inputstream);
   }
	  /*if (fileext.equalsIgnoreCase(".xls")) {
	  	InputStream inputstream = new FileInputStream("D:/sample.xls");
		   result = readExcel(inputstream);
	  }*/
 } catch (Exception e) {
 		e.printStackTrace();
 }
	}
		
		// TODO: handle exception
	}