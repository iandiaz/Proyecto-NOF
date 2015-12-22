<%@page import="VO.UbicacionVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
        <%
    
    String filtro = "";
    if(request.getAttribute("filtro")!=null){
    filtro = (String)request.getAttribute("filtro");
    }
    String Usu_nom=(String)request.getAttribute("usuname");
    String empresas_id=(String)request.getAttribute("empresas_id");
    
    
    %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>New Office - UBICACION</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
     <link href='http://fonts.googleapis.com/css?family=Inconsolata:400,700' rel='stylesheet' type='text/css'>
   
    <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/butons.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/inputs.css">
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">

    <script src="lib/jquery-2.1.4.min.js" type="text/javascript"></script>

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
		 font-size:20px;
	}
	tr.hov:hover{
		opacity:0.5;
		cursor: pointer;
	}
	.vig{
		color:#000;
        background-color:#00FF00;
	}
  	.novig{
  		color:#FFF;
        background-color:#F00;
	}
    </style>
    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
    
   <script>
    $(document).ready(function() {

         $("#searchInput").keyup(function(){
        	 
             var data = this.value;
    
             setTimeout( function() { 
            	 var jo = $("#fbody").find("tr");
            	 jo.hide();
            	 jo = jo.filter("*:contains('"+data.toUpperCase()+"')");
               	 jo.show();
            	 
				 }, 1000 );
   	
         }).focus(function(){
         //    this.value="";
             $(this).css({"color":"black"});
             $(this).unbind('focus');
         }).css({"color":"#000"});
         
         getUbicaciones();

     });
    
    
    
    function getUbicaciones(){
      	
        $.post( "getUbicacionesAsync", {  })
        .done(function( data ) {
        	$('#fbody').empty();
          		var ubicaciones_arr = $.parseJSON(data);
          		
          		$.each( ubicaciones_arr, function( key, ubicacion ) {
          			
          			var classvnv ="";
          			
          			
          			if(ubicacion.estadoVigNoVig.id==1){
          				classvnv="vig";
          			}
          				
          			if(ubicacion.estadoVigNoVig.id==2){
          				classvnv="novig";
          			}
          			
          			var direccion=ubicacion.direccion.direccion;
          			if(direccion.legth>40) direccion=direccion.substring(0, 40);
          			if(direccion.legth>20) direccion=direccion.substring(0, 20);
          			
          			var line="<tr class='hov "+classvnv+"' onclick=\"location='cubi3?dir_id="+ubicacion.direccion.id+"&ubi_id="+ubicacion.id+"';\" style='font-size:20px'  >";
          			
          			line+="<td width=\"60px\" >"+ubicacion.id+"</td>";
          			
          			line+="<td width='300px' >"+ubicacion.empresa.nombre_nof+"</td>";
          			line+="<td width='250px' >"+direccion+"</td>";
          			
          			line+="<td width='430px' >"+ubicacion.nom_fisica+"</td>";
          			line+="<td width='146px' >"+ubicacion.estadoVigNoVig.nombre+"</td>";
          			
          			line+="</tr>";
          			
          			$('#fbody').append(line);
          		 
          		});
          		
          		
        });
    }

   </script>
  </head>
<body>
<div class="navbar">
	<div class="brand">
	  <img src="lib/bootstrap/img/logonew_big.png">
	</div>
	<div class="version" align="right">
	  <p>N011.C.01.001</p>
	</div>	
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESI&Oacute;N</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/011/'">VOLVER</button> 
	<div align="center" class="title">
		<p >CONSULTAR UBICACION</p>
	</div>
</div>
    
         <div align="center" class="form-group has-warning">
         <label class="control-label" for="inputWarning" style="font-size:20px;color:#000"><strong>TEXTO PARA FILTRAR:</strong></label>
         <input maxlength="25" type="text" id="searchInput" class="form-control span12" style="width:290px;height:30px" autofocus="autofocus">
         </div>


         <div class="menu"  style="min-width: 1200px;max-width: 1200px;">
         	<div class="titulomenu" style="border: 1px solid black;min-width: 1200px;max-width: 1200px;">
         		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px">
         		<p style="margin:0px 0px;font-size:20px;background-color:#39F;">ELEGIR UBICACION A CONSULTAR</p> </h3>
			</div>
        	<div style=" max-height:300px; ">
  <table class="table" style="margin-left:0px; width:100%;">
	<thead style="border: 1px solid black; background-color: #0101DF">
	<tr style="background-color:#0101DF;color:#FFFFFF">
		<td width="60px" style="font-size:20px"><strong>ID</strong></td>
		<td width="300px" style="font-size:20px"><strong>EMPRESA</strong></td>
	
		<td width="250px" style="font-size:20px"><strong>DIRECCION</strong></td>
		<td width="430px" style="font-size:20px"><strong>UBICACION</strong></td>
	<td  width="146px" style="font-size:20px"><strong>ESTADO</strong></td>
</tr>  
<tbody id="fbody" class="scroll" style="border: 1px solid black">

 
			<tr class="hov" style="font-size: 20px">
				<td colspan="5" width="1200px" align="center">CARGANDO... <img alt="" src="/011/images/loading.GIF"></td>
				
			</tr>

</table>
	</div>
</div><br><br><br>
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