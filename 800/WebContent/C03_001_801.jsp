<%@page import="VO.DteReferenciaVO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="VO.DteTotalVO"%>
<%@page import="VO.DteDetalleVO"%>
<%@page import="VO.DteVO"%>
<%@page import="java.util.ArrayList"%>
<% 		
String modname=(String)request.getAttribute("modname");

String Usu_nom=(String)request.getAttribute("usuname");
DteVO dte_encabezado=(DteVO)request.getAttribute("dte_encabezado");
ArrayList<DteDetalleVO> dte_detalles=(ArrayList<DteDetalleVO>)request.getAttribute("dte_detalles");

DteTotalVO dte_totales=(DteTotalVO)request.getAttribute("dte_totales");
DteReferenciaVO dte_referencia=(DteReferenciaVO)request.getAttribute("dte_referencia");

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

    <script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="/800/lib/jquery.Rut.min.js"></script>
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
	  
	    height: 120px;
	    overflow-y: scroll;
	}
	thead, tbody {
		 min-width:100%;
    	display: inline-block;
	}
		select{
			width:225px;
				}
		
	@media screen and (min-width: 700px) {
	.cuadroblanco{
			box-shadow:0px 2px 5px 0px rgba(50, 50, 50, 0.75);
			border: 1px solid black;
			min-height:100%; height:330px;  
		min-width: 350px;
			max-width:1000px;
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
    <script>



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
	  <p>N800.C.03.001</p>
	</div>
	<form method="get" action="iperusu" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/800/cguias_stipo'">VOLVER</button> 
	<div align="center" class="title">
		<p>CONSULTAR <%=modname %></p>
	</div>
</div>
<div class="content" >
  <form action="iguias" name="form1" method="post">
  
	<div class=" cuadroblanco" style="height:160px;">
	<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS ENCABEZADO FACTURA</p></h3>
		
	<div style="padding: 5px 5px 0px 5px">
		<div class="divhead"><strong>NRO.FOLIO:</strong><input  type="text" style="width:100px;height:30px ;background:#FFF;" readonly="readonly" value="<%=dte_encabezado.getFolio() %>"></div>
		<div class="divhead"><strong>NRO.BIRT:</strong><input  type="text" style="width:100px;height:30px ;background:#FFF;" readonly="readonly" value="" ></div>
		<div class="divhead"><strong>FECHA:</strong><input  type="text" style="width:125px;height:30px ;background:#FFF;" readonly="readonly" value="<%=dte_encabezado.getFechaEmision() %>"></div>
		<div class="divhead"><strong>CONDICIONES:</strong><input type="text" style="width:380px;height:30px ;background:#FFF;" readonly="readonly" value="<%=dte_encabezado.getCondiciones()==null?"":dte_encabezado.getCondiciones()%>"></div>
		<div class="divhead"><strong>EMISOR:</strong><input type="text" style="width:110px;height:30px ;background:#FFF;" value="<%=dte_encabezado.getEmisor().getNombre_nof() %>"  readonly="readonly"></div>		
		<div class="divhead"><strong>ESTADO SII:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="ENVIADA"></div>
		<div class="divhead"><strong>TIPO DOCUMENTO:</strong>
		<input style="height:30px" type="radio" name="tipodte" value="33" disabled="disabled" <% if(dte_encabezado.getTipoDTE().equals("33")){%> checked="checked" <%}%> >(33)AFECTA 
		<input style="height:30px" type="radio" name="tipodte" value="34" disabled="disabled" <% if(dte_encabezado.getTipoDTE().equals("34")){%> checked="checked" <%}%> >(34)EXENTA</div>
		<div class="divhead"><strong>FEC. VENCIMIENTO:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;" value="<%=dte_encabezado.getFechaVencimiento()==null?"":dte_encabezado.getFechaVencimiento()%>" readonly="readonly"></div>
		
	</div>
	</div>

	<div class=" cuadroblanco" style="height:240px;margin-top: 5px">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CLIENTE</p></h3>
		<div style="padding: 5px 5px 0px 5px">
	
		<div class="divhead"><strong>RUT:</strong><input type="text" name="empresas_rut" id="empresas_rut" style="width:145px;height:30px ;background:#FFF;"  value="<%=dte_encabezado.getCliente().getRut() %>" readonly="readonly"></div>
		<div class="divhead"><strong>RAZON SOCIAL:</strong><input type="text" name="rz_cliente" id="rz_cliente" style="width:500px;height:30px ;background:#FFF;"  value="<%=dte_encabezado.getCliente().getRazonsocial() %>" readonly="readonly"></div>
		<div class="divhead"><strong>GIRO:</strong><input type="text" name="rz_cliente" id="rz_cliente" style="width:500px;height:30px ;background:#FFF;"  value="<%=dte_encabezado.getCliente().getGiro() %>" readonly="readonly"></div>
		<div class="divhead"><strong>DIRECCION:</strong><input type="text" style="width:500px;height:30px ;background:#FFF;" readonly="readonly"  value="<%=dte_encabezado.getDireccionCliente().getDireccion() %>"></div>
		<div class="divhead"><strong>COMUNA:</strong><input type="text" id="comuna" style="width:350px;height:30px ;background:#FFF;" readonly="readonly" value="<%=dte_encabezado.getDireccionCliente().getComu_nombre() %>" ></div>
		<div class="divhead"><strong>CIUDAD:</strong><input type="text" id="ciudad" style="width:350px;height:30px ;background:#FFF;" readonly="readonly" value="<%=dte_encabezado.getDireccionCliente().getCiudad() %>" ></div>
		<div class="divhead"><strong>REGION:</strong><input type="text" id="region" style="width:510px;height:30px ;background:#FFF;" readonly="readonly" value="<%=dte_encabezado.getDireccionCliente().getRegi_nombre()==null?"":dte_encabezado.getDireccionCliente().getRegi_nombre() %>"></div>
		
		
		
		
	</div>
	</div> 
<div class=" cuadroblanco" style="height:130px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CONTACTO</p></h3>
		
		<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>CONTACTO:</strong><input type="text" style="width:400px;height:30px ;background:#FFF;" readonly="readonly" value="<%=dte_encabezado.getContactoCliente().getNombre()==null?"":dte_encabezado.getContactoCliente().getNombre() %>" ></div>
		<div class="divhead"><strong>E-MAIL:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" readonly="readonly" value="<%=dte_encabezado.getContactoCliente().getEmail() %>"></div>
		
		<div class="divhead"><strong>TELEFONO:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" readonly="readonly" value="<%=dte_encabezado.getContactoCliente().getTelefono()==null?"":dte_encabezado.getContactoCliente().getTelefono() %>"></div>
		<div class="divhead"><strong>CELULAR:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" readonly="readonly" value="<%=dte_encabezado.getContactoCliente().getCelular()==null?"":dte_encabezado.getContactoCliente().getCelular() %>"></div>
		
	</div>
	
	</div>
	<div class=" cuadroblanco" style="height:200px;margin-top: 5px">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS PRODUCTOS FACTURADOS</p></h3>
		<table class="table">
			<thead style="border: 1px solid black;">
			   <tr style="background-color:#0101DF;color:#FFFFFF">
				<td width="80" style="font-size:20px"><strong>ID</strong></td>
				<td width="700" style="font-size:20px"><strong>PN/TLI - DESCRIPCION</strong></td>
				<td width="120" style="font-size:20px"><strong>SERIE</strong></td>
				<td width="100" style="font-size:20px"><strong>VALOR</strong></td>
			  </tr>  
			<tbody id="fbody" class="scroll" style="border: 1px solid black">
			
			<%for(DteDetalleVO dte_detalle:dte_detalles){%>
			<tr class="hov">
				<td width="80"><%=dte_detalle.getId_prod() %></td>
				<td width="700"><%=dte_detalle.getDescripcion()%></td>
				<td width="120"><%=dte_detalle.getSerie()%></td>
				<td width="100">$<%=dte_detalle.getPrecio()%></td>
			</tr>
		<%} %>
			 
			</tbody>
		  </table>
	</div>

	<div class=" cuadroblanco" style="height:120px;margin-top: 5px">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>SUBTOTAL:</strong><input name="total" id="total" type="text" style="width:150px;height:30px ;background:#FFF;" value="<%=dte_totales.getSubtotal() %>"  readonly="readonly"></div>
		<div class="divhead"><strong>MONTO DESCUENTO:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;"  value="<%=dte_totales.getDesctoGral() %>" readonly="readonly" ></div>
		<div class="divhead"><strong>GLOSA DESCUENTO:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" readonly="readonly"  value="<%=dte_totales.getGlosaDescuento()==null?"": dte_totales.getGlosaDescuento()%>"  ></div>
		<div class="divhead"><strong id="netolabel">AFECTO:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;" readonly="readonly" value="<%=dte_totales.getNeto() %>"></div>
		
		<div class="divhead"><strong>IVA 19%:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;" readonly="readonly" value="<%=dte_totales.getIVA() %>" ></div>
		<div class="divhead"><strong>TOTAL:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" readonly="readonly" value="<%=dte_totales.getTotal() %>" ></div>
		
	</div>
	</div>
	
	
	<div class=" cuadroblanco" style="height:80px;margin-top: 5px">
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>TIPO REF:</strong><input type="text" maxlength="36" style="width:200px;height:30px ;background:#FFF;" value="<%=dte_referencia.getTipoDTERef_nombre()%>" readonly="readonly"></div>
		<div class="divhead"><strong>FOLIO REF:</strong><input type="text" name="folioref" id="folioref" maxlength="36" style="width:410px;height:30px ;background:#FFF;" value="<%=dte_referencia.getFolioRef()%>" readonly="readonly"></div>
		<div class="divhead"><strong>FECHA REF:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;" name="fec_ref" id="datepicker"  readonly="readonly" value="<%=dte_referencia.getFechaRef()%>" ></div>
	</div>
		
	</div>

	<div class=" cuadroblanco" style="height:40px;margin-top: 5px">
	<div style="padding: 5px 5px 0px 5px">
		<div class="divhead"><strong>ESTADO EN BIRT:</strong><input type="text" value="EMITIDA" style="width:200px;height:30px ;background:#FFF;" disabled="disabled" ></div>
		<div class="divhead"><strong>FECHA EMISION:</strong><input type="text" style="width:125px;height:30px ;background:#FFF;" disabled="disabled" ></div>
		<div class="divhead"><strong>EMISOR:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;" disabled="disabled" ></div>
	</div>
	</div>

	<div class=" cuadroblanco" style="height:150px;margin-top: 5px">
	<div style="padding: 5px 5px 0px 5px">
		<div class="divhead"><strong>FORMA DE DESPACHO:</strong><input type="text" name="obs1" disabled="disabled" style="width:410px;height:30px ;background:#FFF;" ></div>
		<div class="divhead"><strong>OBSERVACION:</strong><input type="text" name="obs2" disabled="disabled" style="width:460px;height:30px ;background:#FFF;" ></div>
		<div class="divhead"><strong>OTRAS OBS:</strong><input type="text" style="width:450px;height:30px ;background:#FFF;" disabled="disabled" ></div>
		
       </div>
	</div>
</form>
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


