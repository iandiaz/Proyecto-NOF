<%@page import="VO.EstructuraCobroVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%

ArrayList<EstructuraCobroVO> estructurasCobro =(ArrayList<EstructuraCobroVO>) request.getAttribute("estructurasCobro");

ArrayList<EstructuraCobroVO> estructurasCobroSeleccionadas =(ArrayList<EstructuraCobroVO>) request.getAttribute("estructurasCobroSeleccionadas");
String close =(String) request.getAttribute("close");
String estrselect =(String) request.getAttribute("estrselect");

String ubi_id =(String) request.getAttribute("ubi_id");
String activo_tptc_id =(String) request.getAttribute("activo_tptc_id");

String block =(String) request.getAttribute("block");
if(block==null) block="0";
%>    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
 <link href='http://fonts.googleapis.com/css?family=Inconsolata:400,700' rel='stylesheet' type='text/css'>
   
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
  

 <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/butons.css">
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" type="text/css" href="stylesheets/inputs.css">
    
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    

    <script >
    
    
	$(document).ready(function() {
 
        
        <% if(close!=null && close.equals("1")){
        	%>
        	
        $('#<%=ubi_id%>_nestructuras', $(parent.document)).html('<%=estrselect%>');
        
        parent.$.fancybox.close();
        <% } %>
      
        
       
    });
    
    function disableSubmitB(){
		document.getElementById('grabar').value = "GRABANDO..";
    	
    	
    }
    
    function validateSubmit(){
    	disableSubmitB();
    	return true;
    }

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
<form action="ubiestrM" method="get" onsubmit="return validateSubmit()" style="height:500px;">
<input type="hidden" name="ubi_id" value="<%=ubi_id%>" />
<input type="hidden" name="activo_tptc_id" value="<%=activo_tptc_id%>" /> 
 
<div>
<table id="myTable01" class="table" >

<thead  >
<tr style="background-color:#0101DF;color:#FFFFFF">
<td colspan="2" >AGREGAR ESTRUCTURAS DE COBRO A LA UBICACI&OacuteN: <%=ubi_id %></td>

</tr> 
<tr style="background-color:#0101DF;color:#FFFFFF">

<td ><strong>ID</strong></td>
<td  ><strong>NOMBRE</strong></td>
</tr>  
<tbody  >

 <%
         if(estructurasCobro!=null)for(int i =0; i<estructurasCobro.size(); i++){
        	 EstructuraCobroVO estructura =estructurasCobro.get(i);
        	 
        	 boolean check=false; 
        	 //evaluamos si esta dentro de las seleccionadas 
        	 
        	 for(EstructuraCobroVO estrSelec : estructurasCobroSeleccionadas){
        		 if(estrSelec.getEstrcobro_id().equals(estructura.getEstrcobro_id())){
        			 check=true;break; 
        		 }
        	 }
                     
        	 %>
        		
			<tr  >
				<td>
				<input type='checkbox' name="estructurasSelect[]" value='<%=estructura.getEstrcobro_id()%>' id="<%=estructura.getEstrcobro_id()%>" class='unique' <% if(block.equals("1")){%> disabled="disabled" <% } %> <%if(check){%> checked="checked" <%} %>  ></td>
				<td ><%=estructura.getEstrcobro_nombre()%></td>
				
			</tr>
			
        	<% }
        	 %>

</table>
</div>
<div style="position:absolute; bottom:0;width: 100%;">
<button type="submit" name="grabar" id="grabar" class="btn btn-success pull-right" style="margin-right: 5px; margin-bottom:2px;">GRABAR</button>
</div>
</form>


</div>
       
</body>
</html>