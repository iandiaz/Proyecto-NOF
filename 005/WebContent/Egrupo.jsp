<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String[] arGrupos =(String[]) request.getAttribute("arGrupos");
String[] ar_grupos_emp =(String[]) request.getAttribute("ar_grupos_emp");
	String Usu_nom=(String)request.getAttribute("usuname");
	
%>
<!DOCTYPE html>
<html lang="en">
  <head>
  <%
	if(request.getParameter("EExito")!=null && !request.getParameter("EExito").equals("")){
	 		if(request.getParameter("EExito").equals("OK")){
				out.println("<script>alert('OPERACI\u00d3N REALIZADA CON \u00c9XITO')</script>");
				//out.println("<script>window.location = '/001/'</script>");
			} 
			
	} 
	%>
    <meta charset="utf-8">
    <title>New Office - Grupo</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/butons.css">
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" type="text/css" href="stylesheets/inputs.css">

    <script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>

    <!-- Demo page code -->

     <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
		
  	tbody {
	  
	    height: 225px;
	    overflow-y: scroll;
	}
	thead, tbody {
		 min-width:100%;
    	display: inline-block;
	}
	tr{
		 width:100%;
	}
	tr.hov:hover{
		opacity:0.5;
		cursor: pointer;
	}
  
    </style>

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
     <script>
    $(document).ready(function() {

         $("#searchInput").keyup(function(){
   	//hide all the rows
             $("#fbody").find("tr").hide();

   	//split the current value of searchInput
             var data = this.value;//.split(" ");
   	//create a jquery object of the rows
             var jo = $("#fbody").find("tr");
             
   	//Recusively filter the jquery object to get results.
            // $.each(data, function(i, v){
                 jo = jo.filter("*:contains('"+data.toUpperCase()+"')");
             //});
           //show the rows that match.
             jo.show();
        //Removes the placeholder text  
      
         }).focus(function(){
         //    this.value="";
             $(this).css({"color":"black"});
             $(this).unbind('focus');
         }).css({"color":"#000"});

     });

   </script>
  </head>

  <!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
  <!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
  <!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
  <!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
  <!--[if (gt IE 9)|!(IE)]><!--> 
  
  <body class=""> 
  <!--<![endif]-->
   <script type="text/javascript">
  	function detelePerfil(id_g,nombre_g){
  		if(confirm("¿ESTA SEGURO QUE DESEA ELIMINAR EL GRUPO '"+nombre_g+"' ?")){
  			document.getElementById('delete_id').value=id_g;
  			document.getElementById('deletegrupo').submit();
  		}
  		
  	}
  </script>
  
  <form id="deletegrupo" action="" method="post" style="margin: 0px 0px 0px 0px;">
  	<input type="hidden" name="delete_id"  id="delete_id"> 
  </form>
  
    <div class="navbar">
                        
                     
                     <div class="brand"><img src="lib/bootstrap/img/logonew_big.png"></div>
                     <div class="version" align="right">
                     <p>N005.E.01.001</p>
                     </div>
                    <form method="get" action="Mgrupo" style="margin: 0px 0px 0px 0px">                
                    <button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
                    </form>
                    <button type="button" onclick="location='/005/'" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">VOLVER</button>
                   
                    <div align="center" class="title">
                    <p >ELIMINAR GRUPO</p>
                    </div>
                    
               
                             
    </div>
         <div align="center" class="form-group has-warning">
         <label class="control-label" for="inputWarning" style="font-size:20px;color:#000"><strong>TEXTO PARA FILTRAR:</strong></label>
         <input maxlength="25" type="text" id="searchInput" class="form-control span12" style="width:290px;height:30px" autofocus="autofocus">
                    </div>
</div>
  <div class="content">
        
         <div class="containermenu">
         <div class="menu">
         <div class="titulomenu" style="border: 1px solid black">
         <h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px">
         
         <p  style="margin:0px 0px;font-size:20px;background-color: #39F">ELEGIR GRUPO A ELIMINAR</p> </h3>
  
        </div>
        
        
       <div style=" max-height:300px; ">
  <table class="table">
<thead style="border: 1px solid black">
<tr style="background-color:#0101DF;color:#FFFFFF">
<td width="47px" style="font-size:20px"><strong>ID</strong></td>
<td width="290px" style="font-size:20px"  ><strong>NOMBRE</strong></td>
<td width="150px" style="font-size:20px"><strong>ESTADO</strong></td>
</tr>  
<tbody id="fbody" class="scroll" style="border: 1px solid black">

 <%
        
         
         for(int i =0; i<arGrupos.length; i++){
        	 String[] Grupos = arGrupos[i].split("/");
        	 String cadenaEmpresas="";
        	 //buscamos las empresas de este grupo
    	 	 for(int x =0; x<ar_grupos_emp.length; x++){
    	 		String[] arGrEmp = ar_grupos_emp[x].split("/");
    	 		String id_gru=arGrEmp[0];
    	 		String nom_emp=arGrEmp[1];
    	 		
    	 		if(id_gru.equals(Grupos[0])){ cadenaEmpresas+=nom_emp+"\n";}
    	 	 }	 
        	 %>
        		
			<tr class="hov" title="<%=cadenaEmpresas.equals("") ? "SIN EMPRESA ASIGNADO" : cadenaEmpresas %>" style="color:<%=Grupos[2].equals("1") ? "#000" :"#FFF"%>;font-size:20px;background-color:<%=Grupos[2].equals("1") ? "#00FF00" :"#F00"%>;font-size:20px;" onclick="location='Egrupo2?id_g=<%=Grupos[0]%>';">
				<td width="47px"><%=Grupos[0]%></td>
				<td width="290px"><%=Grupos[1]%></td>
				<td width="150px"><%=Grupos[3]%></td>
			</tr>
			
        	<% }
        	 %>

</table>
       </div> 
  <!-- Table -->

         
         
         </div>
         </div>
        
    </div>
    
				<div class="footerapp">
               
                  <p style="float: left;"><i class="icon-user"></i>
                  <strong><%=Usu_nom %></strong></p>
                  <button type="button" onclick="window.open('/003/','_blank')" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:2px;">MENU</button>
                </div>

    <script src="lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
		
    </script>
    
  </body>
</html>


