<%@page import="VO.OcDetalleVO"%>
<%@page import="DAO.OCsDAO"%>
<%@page import="VO.OcVO"%>
<%@page import="VO.ProductoVO"%>
<%@page import="VO.SpecialBidVO"%>
<%@page import="Constantes.Constantes"%>

<%@page import="java.util.ArrayList"%>
<% 	
String modname=(String)request.getAttribute("modname");
String Usu_nom=(String)request.getAttribute("usuname");	
OcVO oc=(OcVO)request.getAttribute("oc");	


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
	<script src="N031_I_01_001.js"></script>
  
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
    function grabar(){
		$('.loaded').hide();
		$('.loading').show();
		$.post( "eliminarOCAsync", $( "#form1" ).serialize())
		    .done(function( data ) {
		    	
		    	if(data.trim()=="OK"){
		    		alert("OPERACION REALIZADA CON EXITO");
	        		  location="N<%=Constantes.MODULO%>_1_01_001";  
		    	}
		    	else if(data.trim()=="ERROR"){
		    		alert("HA OCURRIDO UN ERROR FAVOR CONTACTARSE CON EL ADMINISTRADOR DEL SISTEMA COD ERROR: "+data);
		    		$('.loaded').show();
		    		$('.loading').hide();
	 	    	}

	        });
			
		}
	
	
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
	  <p>N<%=Constantes.MODULO %>.E.02.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESI�N</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/<%=Constantes.MODULO%>/N<%=Constantes.MODULO%>_E_01_001'">VOLVER</button> 
	<div align="center" class="title">
		<p>ELIMINAR <%=modname %></p>
	</div>
</div>
<div class="content" >
  <form action="" name="form1" method="post" id="form1" > 
  	<div class=" cuadroblanco" style="height:auto;margin-top: 5px;overflow: auto; min-height: 50px">
  	<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><span style="margin:0px 0px;font-size:20px;">DATOS OC </span> </h3>
	
		<div style="padding: 5px 5px 5px 5px;">
			<div class="divhead vertical-middle"><strong>ID:</strong><input type="text" style="width:80px;height:30px ;" readonly="readonly" value="${oc.id}" name="id"  ></div>
			<div class="divhead vertical-middle"><strong>NRO. OC:</strong><input type="text" style="width:220px;height:30px ;" id="oc_nro" name="oc_nro" value="${oc.nro}" readonly="readonly"></div>
			<div class="divhead vertical-middle"><strong>NOTA PEDIDO:</strong><input type="text" style="width:220px;height:30px ;" value="${oc.notapedido}" ></div>
			<div class="divhead vertical-middle"><strong>TIPO CAMBIO:</strong><input type="text" style="width:100px;height:30px ;" value="${oc.tipomoneda.nombre}" readonly="readonly"></div>
			<div class="divhead vertical-middle"><strong>VALOR T/C:</strong><input type="text" style="width:100px;height:30px ;" id="oc_tcambio_ref" name="oc_tcambio_ref" value="${oc.tcambio_ref}" readonly="readonly"></div>
			<div class="divhead vertical-middle"><strong>FORMA DE PAGO:</strong><input type="text" style="width:370px;height:30px ;" value="${oc.formadepago}" readonly="readonly"></div>
			<div class="divhead vertical-middle"><strong>PLAZO DE PAGO:</strong><input type="text" style="width:50px;height:30px ;" id="oc_plazo_pago" name="oc_plazo_pago"  value="${oc.plazodepago}" readonly="readonly"> DIAS</div>
			<div class="divhead vertical-middle"><strong>FECHA EMISION:</strong><input type="text" style="width:120px;height:30px ;" value="${oc.fec_emision}" readonly="readonly"></div>
			<div class="divhead vertical-middle"><strong>FECHA RECEPCION:</strong><input type="text" style="width:120px;height:30px ;" value="${oc.fec_recepcion}" readonly="readonly"></div>
			
		</div>
	</div>
	<div class=" cuadroblanco" style="height:auto;margin-top: 5px;overflow: auto; min-height: 50px">
  	<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><span style="margin:0px 0px;font-size:20px;">DATOS PROVEEDOR</span> </h3>
	
		<div style="padding: 5px 5px 5px 5px;">
			<div class="divhead vertical-middle"><strong>PROVEEDOR:</strong><input type="text" style="width:300px;height:30px ;" value="${oc.proveedor.nombre_nof}" readonly="readonly"></div>
			<div class="divhead vertical-middle"><strong>RUT:</strong><input type="text" id="rut_proveedor" style="width:120px;height:30px ;" readonly="readonly" value="${oc.proveedor.rut}"></div>
			<div class="divhead vertical-middle"><strong>RAZON SOCIAL:</strong><input type="text" id="razonsocial_proveedor" style="width:620px;height:30px ;" readonly="readonly" value="${oc.proveedor.razonsocial}" ></div>
			<div class="divhead vertical-middle"><strong>DIRECCION:</strong><input type="text" style="width:680px;height:30px ;" readonly="readonly" value="${oc.direccion_proveedor.direccion}"></div>
			<div class="divhead vertical-middle"><strong>CONTACTO:</strong><input type="text" style="width:300px;height:30px ;" readonly="readonly" value="${oc.contacto_proveedor.nombre} ${oc.contacto_proveedor.ape_p} ${oc.contacto_proveedor.ape_m}"></div>
		</div>
	</div>
	<div class=" cuadroblanco" style="height:auto;margin-top: 5px;overflow: auto; min-height: 50px">
  	<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><span style="margin:0px 0px;font-size:20px;">DATOS COMPRADOR</span> </h3>
	
		<div style="padding: 5px 5px 5px 5px;">
			<div class="divhead vertical-middle"><strong>COMPRADOR:</strong><input type="text" style="width:300px;height:30px ;" value="${oc.comprador.nombre_nof}" readonly="readonly"></div>
			<div class="divhead vertical-middle"><strong>RUT:</strong><input type="text" id="rut_comprador" style="width:120px;height:30px ;" readonly="readonly" value="${oc.comprador.rut}"></div>
			<div class="divhead vertical-middle"><strong>RAZON SOCIAL:</strong><input type="text" id="razonsocial_comprador" style="width:620px;height:30px ;" readonly="readonly" value="${oc.comprador.razonsocial}"></div>
			<div class="divhead vertical-middle"><strong>DIRECCION:</strong><input type="text" style="width:680px;height:30px ;" readonly="readonly" value="${oc.direccion_comprador.direccion}"></div>
			<div class="divhead vertical-middle"><strong>CONTACTO:</strong><input type="text" style="width:300px;height:30px ;" readonly="readonly" value="${oc.contacto_comprador.nombre} ${oc.contacto_comprador.ape_p} ${oc.contacto_comprador.ape_m}"></div>
		</div>
	</div>
	<div class=" cuadroblanco" style="height:auto;margin-top: 5px;overflow: auto; min-height: 50px">
  	<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><span style="margin:0px 0px;font-size:20px;">DATOS PRODUCTOS SOLICITADOS </span> </h3>
	
		<div style="padding: 5px 5px 5px 5px;">
		
		<table class="table noloading" style="margin-left:0px; ">
		
		<thead style="border: 1px solid black;background-color:#0101DF;display: inline-block;">
		<tr style="background-color:#0101DF;color:#FFFFFF">
			<td class="detailID" style="font-size:20px"><strong>ID</strong></td>
			<td class="detailProdPn" style="font-size:20px"><strong>PN/TLI</strong></td>
			<td class="detailProdDesc" style="font-size:20px"><strong>PRODUCTO</strong></td> 
			<td class="detailProdCant" style="font-size:20px"><strong>CANT</strong></td>
			<td class="detailProdPrecio" style="font-size:20px"><strong>TOTAL TC</strong></td>
			<td class="detailProdPrecio" style="font-size:20px"><strong>TOTAL $CL</strong></td>
			<td class="detailSBEmpresa" style="font-size:20px"><strong>SPECIALBID</strong></td>
			<td style="font-size:20px">&nbsp;</td>
		</tr>
		</thead>
		<tbody id="fbody" class="scroll" style="border: 1px solid black;display: inline-block;">
		<%
			for(OcDetalleVO detalle: oc.getDetalle_oc()){ %>
		<tr>
			<td class="detailID" style="font-size:20px"><strong><%=detalle.getProducto().getId() %></strong></td>
			<td class="detailProdPn" style="font-size:20px"><strong><%=detalle.getProducto().getPn() %></strong></td>
			<td class="detailProdDesc" style="font-size:20px"><strong><%=detalle.getProducto().getDesc_corto() %></strong></td> 
			<td class="detailProdCant" style="font-size:20px"><strong><input type="text" style="width:50px;height:30px ;" id="cant_<%=detalle.getProducto().getId() %>" name="cant_<%=detalle.getProducto().getId() %>" value="<%=detalle.getCantidad()==null?"":detalle.getCantidad()%>" readonly="readonly"></strong></td>
			<td class="detailProdPrecio" style="font-size:20px"><strong><input type="text" style="width:100px;height:30px ;" id="total_us_<%=detalle.getProducto().getId() %>" name="total_us_<%=detalle.getProducto().getId() %>" value="<%=detalle.getPrecio()==null?"":detalle.getPrecio()%>" readonly="readonly"></strong></td>
			<td class="detailProdPrecio" style="font-size:20px"><strong><input type="text" maxlength="20" style="width:100px;height:30px ;" id="total_cl_<%=detalle.getProducto().getId() %>" name="total_cl_<%=detalle.getProducto().getId() %>" value="<%=detalle.getPrecio_pesos()==null?"":detalle.getPrecio_pesos()%>" readonly="readonly"></strong></td>
			<td class="detailSBEmpresa" style="font-size:20px"><strong><input type="text" maxlength="20" style="width:220px;height:30px ;" value="<%=detalle.getSpecialbid()==null?"":detalle.getSpecialbid().getEmpresa()%>" readonly="readonly"></strong></td>
			<td style="font-size:20px"></td>
			
		</tr>
		<% } %>
		
		</tbody>
		</table>
		</div>
	</div>
	
	<div class=" cuadroblanco" style="height:auto;margin-top: 5px;overflow: auto; min-height: 50px">
  	<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><span style="margin:0px 0px;font-size:20px;">TOTALES</span> </h3>
	
		<div style="padding: 5px 5px 5px 5px;">
			
			<div class="divhead vertical-middle"><strong>NETO:</strong><input type="text" maxlength="20" style="width:220px;height:30px ;" id="oc_neto" name="oc_neto" readonly="readonly" value="${oc.neto}"></div>
			<div class="divhead vertical-middle"><strong>IVA:</strong><input type="text" maxlength="20" style="width:220px;height:30px ;" id="oc_iva" name="oc_iva" readonly="readonly" value="${oc.iva}"></div>
			<div class="divhead vertical-middle"><strong>TOTAL:</strong><input type="text" maxlength="20" style="width:220px;height:30px ;" id="oc_total" name="oc_total" readonly="readonly" value="${oc.total}" ></div>
						
		</div>
	</div>
	<div class=" cuadroblanco loaded" style="height:auto;margin-top: 5px;overflow: auto; min-height: 40px">
		<button type="button" onclick="grabar()" class="btn btn-success" style="position: absolute;bottom: 0; right: 0;margin: 2px 2px 2px 2px;" >GRABAR</button>	
	</div>
	<div class=" cuadroblanco loading" style="height:auto;margin-top: 5px;overflow: auto; min-height: 40px;display: none;">
		<button type="button" class="btn btn-success" style="position: absolute;bottom: 0; right: 0;margin: 2px 2px 2px 2px;" >GRABANDO..</button>	
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


