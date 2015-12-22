<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%
ArrayList<String> alltickets30 =(ArrayList<String>) request.getAttribute("alltickets30");


%>    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
<script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>

 <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/butons.css">
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" type="text/css" href="stylesheets/inputs.css">
    
 <link href="lib/FixedHeaderTable/css/defaultTheme.css" rel="stylesheet" media="screen" />
 <script src="lib/FixedHeaderTable/jquery.fixedheadertable.js"></script>
 
  
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    

    <script >
    
    
    $(document).ready(function() {
        $('#myTable01').fixedHeaderTable({ 
        	footer: false, 
        	cloneHeadToFoot: false, 
        	
        	height: 600,
        	autoShow: true 
        	});
        
      
        
        var $unique = $('input.unique');
        $unique.click(function() {
            $unique.filter(':checked').not(this).removeAttr('checked');
          //  alert($unique.filter(':checked').val());
            if ($unique.filter(':checked').val() === undefined || $unique.filter(':checked').val() === null) {
            	 $('#ubi_usullave', $(parent.document)).val("");
            }else{
            	 $('#ubi_usullave', $(parent.document)).val($unique.filter(':checked').val());
            }
           
           
        });
        
      
       
       
    });

    </script>
   
   <style type="text/css">
   	td{
   		padding-left: 5px;
   		padding-right: 5px;
   		font-size: 20px !important;
   	}
   
 
   </style>
   
</head>
<body >

<div style="margin-left: auto;
margin-right: auto;
width: 800px;">
 <table id="myTable01" class="table" >
<thead  >
<tr style="background-color:#0101DF;color:#FFFFFF">
<td ></td>
<td ><strong>TICKET</strong></td>
<td ><strong>SERIE</strong></td>
<td ><strong>ASIGNADO A</strong></td>
<td ><strong>EST TICKET</strong></td>
</tr>  
<tbody  >

 <%
         for(String tick:alltickets30){
        	 String[] tick_ar = tick.split(";");
             
                     
        	 %>
        		
			<tr  >
				<td></td>
				<td ><%=tick_ar[0]%></td>
				<td ><%=tick_ar[2]%></td>
				<td ><%=tick_ar[3]%></td>
				<td ><%=tick_ar[4]%></td>
			</tr>
			
        	<% }
        	 %>

</table>

</div>
       
</body>
</html>