<%@page import="VO.ProductoVO"%>
<%@page import="VO.SpecialBidVO"%>
<%@page import="Constantes.Constantes"%>

<%@page import="java.util.ArrayList"%>
<% 	
String modname=(String)request.getAttribute("modname");
String Usu_nom=(String)request.getAttribute("usuname");	
SpecialBidVO specialbid=(SpecialBidVO)request.getAttribute("specialbid");	


%>
<!DOCTYPE html>
<html lang="en"><head>
    <meta charset="utf-8">
    <title>New Office</title>
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
	
  	<script src="lib/jquery-2.1.4.min.js"></script>
    <link rel="stylesheet" href="lib/jquery-ui/css/ui-lightness/jquery-ui-1.10.4.custom.css">
    
    <script src="lib/jquery-ui/js/jquery-ui-1.10.4.custom.js"></script>

	<link href="lib/select2/css/select2.min.css" rel="stylesheet" />
	<script src="lib/select2/js/select2.min.js"></script>
	
	<script src="lib/util.js"></script>
  
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
       
        .amarillo{
			border-color: #FFFF00;
			background-color: #FFFF00;
		}
		.amarillo:focus{
		border-color: #F4FA58;
		box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 6px #F0EC33;
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
		table{
			font-size:20px;
			}
	tbody {
	  
	    height: 205px;
	    overflow-y: scroll;
	}
	thead, tbody {
		 min-width:100%;
		
    	
    	
	}
	select{
			
				}
	.hov>td{
		padding-left: 5px;
		padding-right: 2px;
		padding-top: 1px;
		padding-bottom: 1px;
		width: 116px;
		
	}
	.inputDetail{
		margin-bottom: 0px !important;
		text-align: right;
		width: 112px;
	}
	td.detailID{
		width: 90px;
	}
	td.detailDir{
		width: 370px;
	}
	td.detailUbi{
		width: 450px;
	}
	td.detailFec{
		width: 100px;
	}
	
	td.checkUbi{
		width: 30px;
	}
		
		
	.cuadroblanco{
			box-shadow:0px 2px 5px 0px rgba(50, 50, 50, 0.75);
			border: 1px solid black;
			min-height:100%; height:330px;  
		min-width: 1250px;
			max-width:1250px;
position: relative;
background:#CCC;
margin: 0 auto;
		}
		.inputMovil{
			width:100%;
		}

	
	@media screen and (max-width: 699px) {
		
		.content{
			min-width: 1px;
			}

	}
    </style>
    
    
    <script type="text/javascript">
    

    
    $( document ).ready(function() {
    	
    

	    
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
    
<div class="navbar">
	<div class="brand">
	  <img src="lib/bootstrap/img/logonew_big.png">
	</div>
	<div class="version" align="right">
	  <p>N<%=Constantes.MODULO %>.C.02.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/<%=Constantes.MODULO%>/N<%=Constantes.MODULO%>_C_01_001'">VOLVER</button> 
	<div align="center" class="title">
		<p>CONSULTAR <%=modname %></p>
	</div>
</div>
<div class="content" >
  <form action="" name="form1" method="post" id="form1" > 
  	<div class=" cuadroblanco" style="height:auto;margin-top: 5px;overflow: auto; min-height: 50px">
  	<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><span style="margin:0px 0px;font-size:20px;">DATOS SPECIAL BID </span> </h3>
	
		<div style="padding: 5px 5px 5px 5px;">
			<div class="divhead vertical-middle"><strong>ID BID:</strong><input type="text" style="width:80px;height:30px ;" readonly="readonly" value="${specialbid.id}"  ></div>
			<div class="divhead vertical-middle"><strong>COD BID:</strong><input type="text"  maxlength="20" style="width:220px;height:30px ;" value="${specialbid.cod}" readonly="readonly"></div>
			<div class="divhead vertical-middle"><strong>TIPO CAMBIO:</strong><input type="text" style="width:100px;height:30px ;" value="${specialbid.tipo_moneda.nombre}" readonly="readonly"></div>
			<div class="divhead vertical-middle"><strong>VALOR T/C:</strong><input type="text" style="width:100px;height:30px ;" value="${specialbid.valor_tc}" readonly="readonly"></div>
			<div class="divhead vertical-middle"><strong>EMPRESA:</strong><input type="text" style="width:370px;height:30px ;" value="${specialbid.empresa}" readonly="readonly"></div>
			<div class="divhead vertical-middle"><strong>FECHA SOLICITUD:</strong><input type="text" style="width:120px;height:30px ;" value="${specialbid.fecSolicitud}" readonly="readonly"></div>
			<div class="divhead vertical-middle"><strong>FECHA APROBACION:</strong><input type="text" style="width:120px;height:30px ;" value="${specialbid.fecAprobacion}" readonly="readonly"></div>
			<div class="divhead vertical-middle"><strong>FECHA INICIO:</strong><input type="text" style="width:120px;height:30px ;" value="${specialbid.fecInicio}" readonly="readonly"></div>
			<div class="divhead vertical-middle"><strong>FECHA TERMINO:</strong><input type="text" style="width:120px;height:30px ;" value="${specialbid.fecTermino}" readonly="readonly"></div>
		</div>
	</div>
	<div class=" cuadroblanco" style="height:auto;margin-top: 5px;overflow: auto; min-height: 50px">
  	<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><span style="margin:0px 0px;font-size:20px;">DATOS PRODUCTOS SOLICITADOS </span> </h3>
	
		<div style="padding: 5px 5px 5px 5px;">
		
		
		
		<table class="table noloading" style="margin-left:0px; ">
		
		<thead style="border: 1px solid black;background-color:#0101DF;display: inline-block;">
		<tr style="background-color:#0101DF;color:#FFFFFF">
			<td class="detailID" style="font-size:20px"><strong>ID</strong></td>
			<td class="detailDir" style="font-size:20px"><strong>PN/TLI</strong></td>
			<td class="detailUbi" style="font-size:20px"><strong>PRODUCTO</strong></td> 
			<td class="detailFec" style="font-size:20px"><strong>CANT</strong></td>
			<td class="detailFec" style="font-size:20px"><strong>TOTAL US$</strong></td>
			<td class="detailFec" style="font-size:20px"><strong>TOTAL $CL</strong></td>
			
		</tr>
		</thead>
		<tbody id="fbody" class="scroll" style="border: 1px solid black;display: inline-block;">
		<%
			for(ProductoVO prod:
				specialbid.getProductos()){
				if(prod.getEstadoVigNoVig().getId().equals("2")) continue;%>
		<tr>
			<td class="detailID" style="font-size:20px"><strong><%=prod.getId() %></strong></td>
			<td class="detailDir" style="font-size:20px"><strong><%=prod.getPn() %></strong></td>
			<td class="detailUbi" style="font-size:20px"><strong><%=prod.getDesc_corto() %></strong></td> 
			<td class="detailFec" style="font-size:20px"><strong><input type="text" style="width:80px;height:30px ;" value="<%=prod.getCantidad()%>" readonly="readonly"></strong></td>
			<td class="detailFec" style="font-size:20px"><strong><input type="text" style="width:80px;height:30px ;" value="<%=prod.getPrecio_us()%>" readonly="readonly"></strong></td>
			<td class="detailFec" style="font-size:20px"><strong><input type="text" style="width:80px;height:30px ;" value="<%=prod.getPrecio_cl()%>" readonly="readonly"></strong></td>
			
			
		</tr>
		<% } %>
		
		</tbody>
		</table>
		</div>
	</div>
	
	
	
	
	
<!-- Modal PARA LANZAR MENSAJE-->
<div id="mensaje" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">ATENCI&Oacute;N</h4>
      </div>
      <div class="modal-body">
       <span id="msg"></span>
      </div>
      <div class="modal-footer">
      	
        
      </div>
    </div>

  </div>
</div>
	
	
</form>
</div><br><br><br>
<div class="footerapp">               
	<p style="float: left;"><i class="icon-user"></i>	
	<strong><%=Usu_nom %></strong></p>
	<button type="button" onclick="window.open('/003/','_blank')" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:2px;">MENU</button>
</div>



  

<script src="lib/bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>


