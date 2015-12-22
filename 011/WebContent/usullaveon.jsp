<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%
String[] arusuarios =(String[]) request.getAttribute("arusuarios");
String block =(String) request.getAttribute("block");
if(block==null) block="0";
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
        
        //verificamos si ya habia uno seleccionado, si es asi lo seleccionamos
        if($('#ubi_usullave', $(parent.document)).val()!=""){
        	//alert($('#'+$('#ubi_usullave', $(parent.document)).val()).val());
        	
        	$('#'+$('#ubi_usullave', $(parent.document)).val()).prop('checked', true);
        }
       
       
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
<td ><strong>ID</strong></td>
<td  ><strong>LOGIN</strong></td>
<td  ><strong>NOMBRE</strong></td>
<td ><strong>ESTADO</strong></td>
</tr>  
<tbody  >

 <%
         for(int i =0; i<arusuarios.length; i++){
        	 String[] usuarios = arusuarios[i].split("/");
             
                     
        	 %>
        		
			<tr  >
			<td><input type='checkbox' value='<%=usuarios[0]%>' id="<%=usuarios[0]%>" class='unique' <% if(block.equals("1")){%> disabled="disabled" <% } %>></td>
				<td ><%=usuarios[0]%></td>
				<td ><%=usuarios[1]%></td>
				<td ><%=usuarios[4]%></td>
				<td ><%=usuarios[3]%></td>
			</tr>
			
        	<% }
        	 %>

</table>

</div>
       
</body>
</html>