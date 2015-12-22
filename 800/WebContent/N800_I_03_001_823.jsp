<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 	
String d_id =(String)request.getAttribute("d_id");
String tdte =(String)request.getAttribute("tdte");

String empresas_id="";
String contactos_id="";

String clientes_id =(String)request.getAttribute("clientes_id");
String emisor =(String)request.getAttribute("emisor");
String dte =(String)request.getAttribute("dte");
String condicion =(String)request.getAttribute("condicion");
String empresas_rut =(String)request.getAttribute("empresas_rut");
String empresas_nombrenof =(String)request.getAttribute("empresas_nombrenof");
String dire_id =(String)request.getAttribute("dire_id");
String direccion =(String)request.getAttribute("direccion");
String estado=(String)request.getAttribute("estado");
String COMU_NOMBRE =(String)request.getAttribute("COMU_NOMBRE");
String DIRE_CIUDAD =(String)request.getAttribute("DIRE_CIUDAD");
String REGI_NOMBRE =(String)request.getAttribute("REGI_NOMBRE");
String empresas_giro =(String)request.getAttribute("empresas_giro");
String CONT_NOMBRE =(String)request.getAttribute("CONT_NOMBRE");
String CONT_TELEFONO =(String)request.getAttribute("CONT_TELEFONO");
String CONT_TELEFONO2 =(String)request.getAttribute("CONT_TELEFONOC");
String CONT_EMAIL =(String)request.getAttribute("CONT_EMAIL");
String RESPONSABLE =(String)request.getAttribute("RESPONSABLE");
String ref =(String)request.getAttribute("ref");
String obs =(String)request.getAttribute("obs");
String obs2 =(String)request.getAttribute("obs2");
String fecha =(String)request.getAttribute("fecha");

String Usu_nom=(String)request.getAttribute("usuname");

String[] ar_guias =(String[]) request.getAttribute("ar_guias");
String subtotal=(String)request.getAttribute("subtotal");
String neto=(String)request.getAttribute("neto");
String total=(String)request.getAttribute("total");
String desc=(String)request.getAttribute("descuento");
String gr_glosa_desc=(String)request.getAttribute("glosadescuento");
String iva=(String)request.getAttribute("iva");



String nom1 =(String)request.getAttribute("nom1");
String rut1 =(String)request.getAttribute("rut1");
String id1 =(String)request.getAttribute("id1");
String dir1 =(String)request.getAttribute("dir1");
String reg1 =(String)request.getAttribute("reg1");
String com1 =(String)request.getAttribute("com1");
String ciu1 =(String)request.getAttribute("cui1");

String nom2 =(String)request.getAttribute("nom2");
String rut2 =(String)request.getAttribute("rut2");
String id2 =(String)request.getAttribute("id2");
String dir2 =(String)request.getAttribute("dir2");
String reg2 =(String)request.getAttribute("reg2");
String com2 =(String)request.getAttribute("com2");
String cui2 =(String)request.getAttribute("cui2");
String numtick =(String)request.getAttribute("numtick");


String tipo_dteref=(String)request.getAttribute("tipo_dteref");	
String folioref=(String)request.getAttribute("folioref");	
String fec_ref=(String)request.getAttribute("fec_ref");


if(tipo_dteref!=null && !tipo_dteref.equals("null")){
	
	if(tipo_dteref.equals("801")) tipo_dteref="ORDEN DE COMPRA";
	if(tipo_dteref.equals("802")) tipo_dteref="NOTA DE PEDIDO";
	if(tipo_dteref.equals("803")) tipo_dteref="CONTRATO";
	if(tipo_dteref.equals("HES")) tipo_dteref="HOJA DE ENTRADA";
}

String g_afecta=(String)request.getAttribute("g_afecta");

java.text.NumberFormat nff = java.text.NumberFormat.getInstance();
subtotal=nff.format(Integer.parseInt(subtotal));

neto=nff.format(Integer.parseInt(neto));
total=nff.format(Integer.parseInt(total));
desc=nff.format(Integer.parseInt(desc));
iva=nff.format(Integer.parseInt(iva));
    %>
<!DOCTYPE html>
<html lang="en"><head>
    <meta charset="utf-8">
    <title>New Office - Ingresar Generador Documento DTE</title>
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
	  <p>N800.I.03.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/800/iguias_search?tipo_dte=823'">VOLVER</button> 
	<div align="center" class="title">
		<p >INGRESAR GENERADOR DOCUMENTO DTE</p>
	</div>
</div>
<div class="content" >
  <form action="iguias_desn" name="form1" method="post">
  <input type="hidden" name="d_id" value="<%=d_id%>" >
  <input type="hidden" name="tdte" value="<%=tdte%>" > 
	<div class=" cuadroblanco" style="height:80px;">
		<div class="divhead"><strong>NRO.GUIA:</strong><input  type="text" style="width:100px;height:30px ;background:#FFF;" disabled="disabled" value="ND"></div>
		<div class="divhead"><strong>FECHA:</strong><input  type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=fecha%>"></div>
		<div class="divhead"><strong>CONTROL GUIA:</strong><input type="text" maxlength="30" style="width:350px;height:30px ;background:#FFF" disabled="disabled" value="POR EMITIR" ></div>
		<div class="divhead"><strong>EMISOR:</strong><input type="text" maxlength="30" style="width:135px;height:30px ;background:#FFF;" disabled value="<%=emisor%>"></div>
		<div class="divhead"><strong>ESTADO SII:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=dte%>"></div>
		<div class="divhead" style="vertical-align:middle;padding: 8px 0 8px 0;" ><strong>AFECTA:</strong><input type="checkbox" value="1" name="g_afecta" id="g_afecta"  class="searchInput" <% if(g_afecta.equals("1")){%> checked="checked" <%} %> disabled="disabled" ></div>
	
	</div>
	<div class=" cuadroblanco" style="height:160px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS ORIGEN</p></h3>
		<div class="divhead"><strong>RAZON SOCIAL:</strong><input type="text" style="width:600px;height:30px ;background:#FFF;" disabled="disabled" value="<%=nom1%>"></div>
		<div class="divhead"><strong>RUT:</strong><input type="text" style="width:145px;height:30px ;background:#FFF;" disabled="disabled" value="<%=rut1%>" ></div>
		<div class="divhead"><strong>COD CLIENTE:</strong><input type="text" style="width:70px;height:30px ;background:#FFF;" disabled="disabled" value="<%=id1%>" ></div>
		<div class="divhead"><strong>DIRECCION:</strong><input type="text" style="width:500px;height:30px ;background:#FFF;" disabled="disabled" value="<%=dir1%>"></div>
		<div class="divhead"><strong>REGION:</strong><input type="text" style="width:450px;height:30px ;background:#FFF;" disabled="disabled" value="<%=reg1%>"></div>
		<div class="divhead"><strong>COMUNA:</strong><input type="text" style="width:250px;height:30px ;background:#FFF;" disabled="disabled" value="<%=com1%>" ></div>		
		<div class="divhead"><strong>CIUDAD:</strong><input type="text" style="width:250px;height:30px ;background:#FFF;" disabled="disabled" value="<%=ciu1%>" ></div>
	</div> 	
	<div class=" cuadroblanco" style="height:190px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS DESTINO</p></h3>
		<div class="divhead"><strong>RAZON SOCIAL:</strong><input type="text" style="width:600px;height:30px ;background:#FFF;" disabled="disabled" value="<%=nom2%>"></div>
		<div class="divhead"><strong>RUT:</strong><input type="text" style="width:145px;height:30px ;background:#FFF;" disabled="disabled" value="<%=rut2%>" ></div>
		<div class="divhead"><strong>COD CLIENTE:</strong><input type="text" style="width:70px;height:30px ;background:#FFF;" disabled="disabled" value="<%=id2%>" ></div>
		<div class="divhead"><strong>DIRECCION:</strong><input type="text" style="width:500px;height:30px ;background:#FFF;" disabled="disabled" value="<%=dir2%>"></div>
		<div class="divhead"><strong>REGION:</strong><input type="text" style="width:450px;height:30px ;background:#FFF;" disabled="disabled" value="<%=reg2%>"></div>
		<div class="divhead"><strong>COMUNA:</strong><input type="text" style="width:250px;height:30px ;background:#FFF;" disabled="disabled" value="<%=com2%>" ></div>		
		<div class="divhead"><strong>CIUDAD:</strong><input type="text" style="width:250px;height:30px ;background:#FFF;" disabled="disabled" value="<%=cui2%>" ></div>
		<div class="divhead"><strong>RESPONSABLE CUENTA:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" disabled="disabled" value="<%=RESPONSABLE  %>"></div>
	</div> 
	<div class=" cuadroblanco" style="height:120px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CONTACTO</p></h3>
		<div class="divhead"><strong>CONTACTO:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" disabled value="<%=CONT_NOMBRE%>"></div>
		<div class="divhead"><strong>E-MAIL:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CONT_EMAIL%>"></div>
		<div class="divhead"><strong>TELEFONO:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CONT_TELEFONO%>"></div>
		<div class="divhead"><strong>CELULAR:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CONT_TELEFONO2%>"></div>
	</div>
	
	<div class=" cuadroblanco" style="height:220px;margin-top: 5px">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS ACTIVOS TRASLADOS</p></h3>
		<table class="table">
			<thead style="border: 1px solid black;">
			   <tr style="background-color:#0101DF;color:#FFFFFF">
				<td width="80" style="font-size:20px"><strong>ID</strong></td>
				<td width="850" style="font-size:20px"><strong>DESCRIPCION</strong></td>
				<td width="200" style="font-size:20px"><strong>SERIE</strong></td>
				<td width="200" style="font-size:20px"><strong>UBICACON</strong></td>
				<td width="200" style="font-size:20px"><strong>FECHA</strong></td>
				<td width="200" style="font-size:20px"><strong>VALOR</strong></td>
			  </tr>  
			<tbody id="fbody" class="scroll" style="border: 1px solid black">
			<%        
				 String c="0";
					for(int i =0; i<ar_guias.length; i++){
						c="1";
				    	String[] Guias = ar_guias[i].split("/");
			%>
			<tr class="hov">
				<td width="80"><%=Guias[0]%></td>
				<td width="850"><%=Guias[2]%> - <%=Guias[3]%></td>
				<td width="200"><%=Guias[4]%></td>
				<td width="200"><%=Guias[5]%></td>
				<td width="200"><%=Guias[6]%></td>
				<td width="200"><%=Guias[7]%></td>
			</tr>
			<% } %>
			</tbody>
		
		  </table>
	</div>
	<div class=" cuadroblanco" style="height:120px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>SUBTOTAL:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;text-align: right;" disabled="disabled" value="<%=subtotal%>"></div>
		<div class="divhead"><strong>MONTO DESCUENTO:</strong><input type="text" name="desc"  id="desc"  style="width:150px;height:30px ;background:#FFF;"  value="<%=desc %>" readonly="readonly" disabled="disabled" ></div>
		<div class="divhead" ><strong>GLOSA DESCUENTO:</strong><input type="text"  style="width:350px;height:30px ;background:#FFF;text-align: right;"  value="<%=gr_glosa_desc %>" disabled="disabled" ></div>
		<div class="divhead"><strong>NETO:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;text-align: right;" disabled="disabled" value="<%=neto %>" id="netoneto"></div>
		
		<div class="divhead"><strong>IVA 19%:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;text-align: right;" disabled="disabled" value="<%=iva%>"></div>
		<div class="divhead"><strong>TOTAL:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;text-align: right;" disabled="disabled" value="<%=total%>"></div>
		
	</div>
	</div>
	<div class=" cuadroblanco" style="height:80px;margin-top: 5px">
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>TIPO REF:</strong><input type="text" maxlength="36" style="width:200px;height:30px ;background:#FFF;" value="<%=tipo_dteref%>" readonly="readonly" id="tipo_dteref"></div>
		<div class="divhead"><strong>FOLIO REF:</strong><input type="text" name="folioref" id="folioref" maxlength="36" style="width:410px;height:30px ;background:#FFF;" value="<%=folioref%>" readonly="readonly"></div>
		<div class="divhead"><strong>FECHA REF:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;" name="fec_ref" id="datepickerref"  readonly="readonly" value="<%=fec_ref%>" ></div>
	</div>
		
	</div>
	<div class=" cuadroblanco" style="height:160px;margin-top: 5px">
		<div class="divhead"><strong>NUMERO DE TICKET:</strong><input type="text" name="ref" value="<%=numtick %>" style="width:750px;height:30px ;background:#FFF;" disabled></div>
		<div class="divhead"><strong>FORMA TRASLADO:</strong><input type="text" name="obs" value="<%=obs %>" style="width:720px;height:30px ;background:#FFF;" disabled></div>
		<div class="divhead"><strong>MOTIVO:</strong><input type="text" name="obs" value="SOLO TRASLADO NO CONSTITUYE VENTA" style="width:720px;height:30px ;background:#FFF;" disabled></div>
		<div class="bgrabar">
          <button type="submit" name="grabar" id="grabar" class="btn btn-success"  >ENVIAR AL SII</button>
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


