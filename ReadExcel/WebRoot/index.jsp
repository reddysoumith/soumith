<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style>
		 #table {
			 border-collapse: collapse;
		    margin-bottom: 3em;
		    background: #fff;
			font-family: Segoe UI;
			border:1px solid #fafafa;
		    box-shadow:0px 0px 1px grey;
		    font-size: 14px;
			}
		
		#table caption {
			font-size: 18px;
			font-weight: bold;
			color: #000000;
		}
		
		#table caption span {
			font-size: inherit;
			font-family: inherit;
			color: green;
		}
		#table th {
		   background-color: #489dde; 
		    font-weight: 800;
		    color: #fff;
		    white-space: nowrap;
		}
		#table tbody tr:nth-child(2n-1) {
		    background-color: #fafafa;
		    transition: all .125s ease-in-out;
		}
		#table tbody tr:nth-child(2n) {
		    background-color: #ffffff;
		    transition: all .125s ease-in-out;
		}
		#table tbody tr:hover {
		    background-color: #e5e6ea;
		} 
		#table th, #myTable td {
	  text-align: left;
	  padding: 12px; 
	}
	
	#table tr {
	  border-bottom: 1px solid #ddd;
	}
	
	#table tr.header, #table tr:hover {
	  background-color: #f1f1f1;
	}
	#table {
	  border-collapse: collapse; 
	  width: 100%; 
	  border: 1px solid #ddd; 
	  font-size: 18px; 
	}
		
	#myInput {
	  background-position: 10px 12px; 
	  background-repeat: no-repeat; 
	  width: 100%;
	  font-size: 16px;
	  padding: 12px 20px 12px 40px; 
	  border: 1px solid #ddd; 
	  margin-bottom: 12px; 
	}
	
	#myTable {
	  border-collapse: collapse; 
	  width: 100%; 
	  border: 1px solid #ddd; 
	  font-size: 18px; 
	}
	
	#myTable th, #myTable td {
	  text-align: left;
	  padding: 12px; 
	}
	
	#myTable tr {
	  border-bottom: 1px solid #ddd;
	}
	
	#myTable tr.header, #myTable tr:hover {
	  background-color: #f1f1f1;
	}
	.button {
    background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #ededed), color-stop(1, #dfdfdf) );
    background:-moz-linear-gradient( center top, #ededed 5%, #dfdfdf 100% );
    filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#ededed', endColorstr='#dfdfdf');
    background-color:#ededed;
    -moz-border-radius:8px;
    -webkit-border-radius:8px;
    border-radius:8px;
    border:1px solid #8f8f8f;
    display:inline-block;
    color:#0568B4;
    font-family:arial;
    font-size:12px;
    font-weight:bold;
    padding:3px 8px;
    text-decoration:none;
    text-shadow:1px 1px 0px #ffffff;
}
.button:hover {
    background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #dfdfdf), color-stop(1, #ededed) );
    background:-moz-linear-gradient( center top, #dfdfdf 5%, #ededed 100% );
    filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#dfdfdf', endColorstr='#ededed');
    background-color:#dfdfdf;
}
.button:active {
    position:relative;
    top:1px;
}
.custom-file-upload {
    border: 1px solid #ccc;
    display: inline-block;
    padding: 6px 12px;
    cursor: pointer;
    width: 100%;
}
	</style>
  <script type="text/javascript">
    function validateExcelFile(Obj){
        var validExts = new Array(".xls", ".xlsx");
    	var fileExt = Obj.value;
        fileExt = fileExt.substring(fileExt.lastIndexOf('.'));
	    if (validExts.indexOf(fileExt) < 0) {
	      alert("Invalid file selected, valid files are of " + validExts.toString() + " types.");
	      Obj.value = "";    
	      return false;
	    } else {
          return true;
        }
    }
    
   function uploadExcelFile(){
    var filename = document.getElementById("file-id").files[0].name; 
    if(filename == ''){
     alert("Please Select Excel File");
     return false;
    } else{
      document.forms[0].submit();
     }
    }
  </script>	
  </head>
  <body><!--  -->
  <form action="ExcelUpload" method="post" enctype="multipart/form-data" autocomplete="off">
    <div style="text-align: center;">
    <table style="width: 50%;margin-left:25%;" id="table">
	      <thead>
	       <tr> <th colspan="2" style="text-align: center;">File Upload <span style="color: green;font-size: 14px;">Upload Excel files only.</span></th></tr>
	      </thead>
	      <tr>
	        <td>Select File For Upload</td>
	        <td><input type="file" name="excelfile" id="file-id" onchange="validateExcelFile(this)" class="custom-file-upload"/></td>
	      </tr>
	      <tr>
	        <td colspan="2" style="text-align: center;"><input type="button" name="Upload" value="Upload Excel File" onclick="uploadExcelFile()" class="button"></td>
	      </tr>
    </table>
	</div>
	<div style="text-align: center;"><%=request.getAttribute("msg")%></div>
	<table style="width:100%" id="myTable">
	<caption><input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for names.."></caption>
	<thead>
	  <tr>
	    <th>Sl.No</th>
	    <th>Student Id</th>
	    <th>Student Name</th>
		<th>Class</th>
	  </tr>
	 </thead>
	 <tbody>
	    <% List eList =(ArrayList)request.getAttribute("result");%>
		    <%
		    if(eList !=null){
		    for(int i=0; i<eList.size();i++){
		      HashMap<String, Object> map=(HashMap<String, Object>)eList.get(i);%>
		        <tr>
		        	<td><%=(i+1)%></td>
		            <td><%=map.get("roll_no")%></td>
		            <td><%=map.get("name")%></td>
		            <td><%=map.get("class")%></td>  
		        </tr>
		      <%}}%>
	 </tbody> 
	</table>
	<script type="text/javascript">
function myFunction() {
  // Declare variables
  var input, filter, table, tr, td, i, txtValue,td1,txtValue1;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("myTable");
  tr = table.getElementsByTagName("tr");

  // Loop through all table rows, and hide those who don't match the search query
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[1];
    td1 = tr[i].getElementsByTagName("td")[2];
    if (td) {
      txtValue = td.textContent || td.innerText;
      txtValue1 = td1.textContent || td1.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1 || txtValue1.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
  
}
</script>
 </form>	
  </body>
</html>
