<%@page import="VO.PedidoVO"%>
<%@page import="VO.PedidoDetalleVO"%>
<%@page import="VO.EmpresaVO"%>
<%@page import="java.util.ArrayList"%>
<% 	
String modname=(String)request.getAttribute("modname");
String Usu_nom=(String)request.getAttribute("usuname");	

PedidoVO pedido=(PedidoVO)request.getAttribute("pedido");

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
<script src="lib/util.js"></script>
  	<script src="lib/jquery-ui/js/jquery-1.10.2.js"></script>
    <link rel="stylesheet" href="lib/jquery-ui/css/ui-lightness/jquery-ui-1.10.4.custom.css">
    <script src="lib/jquery-ui/js/jquery-ui-1.10.4.custom.js"></script>

	<link href="//cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/css/select2.min.css" rel="stylesheet" />
	<script src="//cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/js/select2.min.js"></script>
  
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
		 display: inline-block;
    	
    	
	}
	select{
			
				}
	.hov>td{
		padding-left: 7px;
		padding-right: 2px;
		padding-top: 1px;
		padding-bottom: 1px;
		width: 116px;
		
	}
	tr.hov:hover{
		opacity:0.6;
		/*cursor: pointer;*/
	}
	.inputDetail{
		margin-bottom: 0px !important;
		text-align: right;
		width: 112px;
	}
	td.detailID{
		width: 60px;
	}
	td.detailDir{
		width: 400px;
	}
	td.detailUbi{
		width: 300px;
	}
	td.detailFec{
		width: 100px;
	}
	
	td.checkUbi{
		width: 30px;
	}
	td.detailProd{
	width: 150px;
	}
	td.detailPn{
	width: 150px;
	}	
	td.detailCantidad{
	width: 120px;
	}	
		
	@media screen and (min-width: 700px) {
	.cuadroblanco{
			box-shadow:0px 2px 5px 0px rgba(50, 50, 50, 0.75);
			border: 1px solid black;
			min-height:100%; height:330px;  
			min-width: 350px;
			max-width:1200px;
position: relative;
background:#CCC;
margin: 0 auto;
		}
		.inputMovil{
			width:100%;
		}

	}
	@media screen and (max-width: 699px) {
		.cuadroblanco{
			min-width:100%; 
			 height:100px; min-height:250px;width:350px;
			background:#FFF;
			position: relative;

		}
		.inputMovil{
			width:100%;
		}
		.content{
			min-width: 1px;
			}

	}
    </style>
    
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
	  <p>N070.E.02.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/070/E01'">VOLVER</button> 
	<div align="center" class="title">
		<p>ELIMINAR <%=modname %></p>
	</div>
</div>
<div class="content" >
  <form action="" name="form1" method="post" id="form1" > 
  
  	<div class=" cuadroblanco" style="height:170px;" >
  	<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS DEL PEDIDO</p></h3>		
		<div style="padding: 5px 5px 5px 5px;">
			<div class="divhead"><strong>ID PEDIDO:</strong><input type="text" name="id_pedido" id="id_pedido" style="width:145px;height:30px ;background:#FFF;" readonly="readonly" value="${pedido.getId()}" > </div>
			<div class="divhead"><strong>CLIENTE:</strong><input type="text" style="width:345px;height:30px ;background:#FFF;" readonly="readonly" value="${pedido.getCliente().getNombre_nof()}" > </div>
			<div class="divhead"><strong>PROVEEDOR:</strong><input type="text" style="width:345px;height:30px ;background:#FFF;" readonly="readonly" value="${pedido.getProveedor().getNombre_nof()}" > </div>
			<div class="divhead"><strong>ESTADO PEDIDO:</strong><input type="text" style="width:145px;height:30px ;background:#FFF;" readonly="readonly" value="${pedido.getEstado_p()}" > </div>
			<div class="divhead"><strong>FECHA PEDIDO:</strong><input type="text" style="width:145px;height:30px ;background:#FFF;" readonly="readonly" value="${pedido.getFec_crea()}" > </div>
			<div class="divhead"><strong>OBS:</strong><input type="text" style="width:515px;height:30px ;background:#FFF;" value="${pedido.getObs()}" readonly="readonly" ></div>
		</div>
	</div>

	<div class=" cuadroblanco" style="height:45px;margin-top: 5px;">
	
	<div class="divhead"><strong>DIRECCION:</strong><input type="text" style="width:745px;height:30px ;background:#FFF;" readonly="readonly" value="${pedido.getDireccion().getDireccion()}" > </div>
			
    
    
		
	</div>
	
	
	
	<div class=" cuadroblanco" style="height:300px;margin-top: 5px;">
	<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">PRODUCTOS SELECCIONADOS</p></h3>		
		<div style="padding: 5px 5px 5px 5px;" class="divscroll scroll">
		
		
		<table class="table " style="margin-left:0px; ">
		
		<thead style="border: 1px solid black;background-color:#0101DF">
		<tr style="background-color:#0101DF;color:#FFFFFF">
			<td class="detailID" style="font-size:20px"><strong>ID</strong></td>
			<td class="detailPn" style="font-size:20px"><strong>PNUMBER </strong></td>
			<td class="detailDir" style="font-size:20px"><strong>DESCRIPCI&Oacute;N </strong></td>
			<td class="detailUbi" style="font-size:20px"><strong>UBICACI&Oacute;N </strong></td>
			<td class="detailCantidad" style="font-size:20px"><strong>CANTIDAD </strong></td>
			<td class="detailID" style="font-size:20px">&nbsp;</td>
			
		</tr>
		</thead>
		<tbody id="fbody" class="scroll " style="border: 1px solid black">
		<%for(PedidoDetalleVO detallepedido: pedido.getDetallePedido()){%>
		<tr class='hov' >
			<td class="detailID" style="font-size:20px"><strong><%=detallepedido.getProducto().getId() %></strong></td>
			<td class="detailPn" style="font-size:20px"><strong><%=detallepedido.getProducto().getPn() %> </strong></td>
			<td class="detailDir" style="font-size:20px"><strong><%=detallepedido.getProducto().getDesc_corto() %> </strong></td>
			<td class="detailUbi" style="font-size:20px"><strong><%=detallepedido.getUbicacion().getNom_fisica() %> </strong></td>
			<td class="detailCantidad" style="font-size:20px"><strong><%=detallepedido.getCantidad() %> </strong></td>
			<td class="detailID" style="font-size:20px"><button type="submit" name="deteleProducto" id="deteleProducto" class="btn btn-danger" value="<%=detallepedido.getId()%> "><i class="icon-remove"></i></button></td>
			
		  
		</tr>
		<% } %>
		</tbody>
		</table>
		</div>
		
	</div>
	
	
	
</form>
</div><br><br><br>
<div class="footerapp">               
	<p style="float: left;"><i class="icon-user"></i>	
	<strong><%=Usu_nom %></strong></p>
	<button type="button" onclick="window.open('/003/','_blank')" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:2px;">MENU</button>
</div>
    
  </body>
</html>


