<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 		

String d_id =(String)request.getAttribute("d_id");
String tdte =(String)request.getAttribute("tdte");
String id_fac_estado =(String)request.getAttribute("id_fac_estado");

String EMISORA=(String)request.getAttribute("EMISORA");
String fecha=(String)request.getAttribute("fecha");
String condiciones=(String)request.getAttribute("condiciones");
String CLPR_NOMBRE_FANTASIA=(String)request.getAttribute("CLPR_NOMBRE_FANTASIA");

String CONT_NOMBRE=(String)request.getAttribute("CONT_NOMBRE");
String CONT_TELEFONO=(String)request.getAttribute("CONT_TELEFONO");

String obs=(String)request.getAttribute("obs");
String estado=(String)request.getAttribute("estado");
String fecha_emision=(String)request.getAttribute("fecha_emision");
String USU_INICIAL=(String)request.getAttribute("USU_INICIAL");
String responsable=(String)request.getAttribute("responsable");

String empresas_rutcliente=(String)request.getAttribute("CLPR_RUT");
String empresas_rut_ar[]=empresas_rutcliente.split("-");
java.text.NumberFormat nff = java.text.NumberFormat.getInstance();
String valRut = nff.format(Integer.parseInt(empresas_rut_ar[0]));
empresas_rutcliente=valRut+"-"+empresas_rut_ar[1]; 

String CLPR_RAZON_SOCIAL=(String)request.getAttribute("CLPR_RAZON_SOCIAL");
String DIRE_DIRECCION=(String)request.getAttribute("DIRE_DIRECCION");
String DIRE_CIUDAD=(String)request.getAttribute("DIRE_CIUDAD");
String REGI_NOMBRE=(String)request.getAttribute("REGI_NOMBRE");
String PERS_NOMBRE=(String)request.getAttribute("PERS_NOMBRE");
String trans=(String)request.getAttribute("GV_TRANSPORTE");
String COMU_NOMBRE=(String)request.getAttribute("COMU_NOMBRE");
String folio_birt=(String)request.getAttribute("folio_birt");


java.text.NumberFormat nf = java.text.NumberFormat.getInstance();



String tipodte=(String)request.getAttribute("tipodte");
String ref=(String)request.getAttribute("ref");
String obs1=(String)request.getAttribute("obs1");
String obs2=(String)request.getAttribute("obs2");

String[] ar_productos =(String[])request.getAttribute("ar_productos");

String Usu_nom=(String)request.getAttribute("usuname");
String estadoname=(String)request.getAttribute("estadoname");


String subtotal=(String)request.getAttribute("subtotal");
subtotal = nf.format(Integer.parseInt(subtotal)).replace(",",".");

String neto=(String)request.getAttribute("neto");
neto = nf.format(Integer.parseInt(neto)).replace(",",".");

String total=(String)request.getAttribute("total");
total = nf.format(Integer.parseInt(total)).replace(",",".");

String desc=(String)request.getAttribute("descuento");
desc = nf.format(Integer.parseInt(desc)).replace(",",".");

String gr_glosa_desc=(String)request.getAttribute("glosadescuento");
String iva=(String)request.getAttribute("iva");
iva = nf.format(Integer.parseInt(iva)).replace(",",".");
String tipo_dteref=(String)request.getAttribute("tipo_dteref");	
String folioref=(String)request.getAttribute("folioref");	
String fec_ref=(String)request.getAttribute("fec_ref");

if(tipo_dteref!=null && !tipo_dteref.equals("null")){
	
	if(tipo_dteref.equals("801")) tipo_dteref="ORDEN DE COMPRA";
	if(tipo_dteref.equals("802")) tipo_dteref="NOTA DE PEDIDO";
	if(tipo_dteref.equals("803")) tipo_dteref="CONTRATO";
	if(tipo_dteref.equals("HES")) tipo_dteref="HOJA DE ENTRADA";
}

String nrobirt=(String)request.getAttribute("nrobirt");
String g_afecta=(String)request.getAttribute("g_afecta");

String folio=(String)request.getAttribute("folio");


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
	<form method="get" action="iperusu" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/800/iguias_search?tipo_dte=821'">VOLVER</button> 
	<div align="center" class="title">
		<p >INGRESAR GENERADOR DOCUMENTO DTE</p>
	</div>
</div>
<div class="content" >
  <form action="iguias_venta" name="form1" method="post">
  <input type="hidden" name="d_id" value="<%=d_id%>" >
  <input type="hidden" name="tdte" value="<%=tdte%>" >
	<div class=" cuadroblanco" style="height:80px;">
	<div style="padding: 5px 5px 5px 5px;">
	
		<div class="divhead"><strong>NRO.BIRT:</strong><input  type="text" style="width:100px;height:30px ;background:#FFF;" disabled="disabled" value="<%=nrobirt%>"></div>
		<div class="divhead"><strong>FOLIO:</strong><input  type="text" style="width:100px;height:30px ;background:#FFF;" disabled="disabled" value="<%=folio%>"></div>
		<div class="divhead"><strong>FECHA:</strong><input  type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=fecha%>"></div>
		<div class="divhead"><strong>EMISOR:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CLPR_NOMBRE_FANTASIA%>"></div>
	<div class="divhead"><strong>ESTADO SII:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="NO ENVIADA"></div>
	<div class="divhead" style="vertical-align:middle;padding: 8px 0 8px 0;" ><strong>AFECTA:</strong><input type="checkbox" value="1" name="g_afecta" id="g_afecta"  class="searchInput" <% if(g_afecta.equals("1")){%> checked="checked" <%} %> disabled="disabled" ></div>
	<div class="divhead"><strong>ESTADO:</strong><input maxlength="10" type="text" style="width:130px;height:30px ;background:#FFF;" value="<%=estadoname %>"   readonly="readonly"></div>
	</div>
	</div>
	
	<div class=" cuadroblanco" style="height:270px;margin-top: 5px">
	
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CLIENTE</p></h3>
		<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>RAZON SOCIAL:</strong><input type="text" style="width:600px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CLPR_RAZON_SOCIAL%>"></div>
		<div class="divhead"><strong>RUT:</strong><input type="text" name="empresas_rut" id="empresas_rut" style="width:145px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_rutcliente%>" ></div>
		<div class="divhead"><strong>DIRECCION:</strong><input type="text" style="width:500px;height:30px ;background:#FFF;" disabled="disabled" value="<%=DIRE_DIRECCION%>"></div>
		<div class="divhead"><strong>TELEFONO:</strong><input type="text" style="width:160px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CONT_TELEFONO%>"></div>
		<div class="divhead"><strong>REGION:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" disabled="disabled" value="<%=REGI_NOMBRE%>"></div>
		<div class="divhead"><strong>COMUNA:</strong><input type="text" style="width:250px;height:30px ;background:#FFF;" disabled="disabled" value="<%=COMU_NOMBRE%>" ></div>		
		<div class="divhead"><strong>CIUDAD:</strong><input type="text" style="width:250px;height:30px ;background:#FFF;" disabled="disabled" value="<%=DIRE_CIUDAD%>" ></div>
		<div class="divhead"><strong>CONTACTO:</strong><input type="text" style="width:400px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CONT_NOMBRE%>"></div>
		<div class="divhead"><strong>RESPONSABLE:</strong><input type="text" style="width:400px;height:30px ;background:#FFF;" disabled="disabled" value="<%=responsable%>"></div>
		<div class="divhead"><strong>TRANSPORTE:</strong><input  type="text" style="width:300px;height:30px ;background:#FFF;" disabled="disabled" value="<%=trans%>"></div>
		</div>
	</div> 
	
	<div class=" cuadroblanco" style="height:200px;margin-top: 5px">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS PRODUCTOS A FACTURAR</p></h3>
		<table class="table">
			<thead style="border: 1px solid black;">
			   <tr style="background-color:#0101DF;color:#FFFFFF">
				<td width="80" style="font-size:20px"><strong>ID</strong></td>
				<td width="700" style="font-size:20px"><strong>PN/TLI - DESCRIPCION</strong></td>
				<td width="120" style="font-size:20px"><strong>SERIE</strong></td>
				<td width="100" style="font-size:20px"><strong>VALOR</strong></td>
			  </tr>  
			<tbody id="fbody" class="scroll" style="border: 1px solid black">
			 <%   
			 String des;
        for(int i =0; i<ar_productos.length; i++){
       	 String[] ProdDato = ar_productos[i].split("//");
       	 String monto = nf.format(Integer.parseInt(ProdDato[4])).replace(",",".");
       	
       	 %>
       		
			<tr class="hov">
				<td width="80"><%=ProdDato[0]%></td>
				<td width="700"><%=ProdDato[1]%> - <%=ProdDato[2]%></td>
				<td width="120"><%=ProdDato[3]%></td>
				<td width="100">$<%=monto%></td>
			</tr>
			
       	<% } %>
			</tbody>
		  </table>
	</div>
	<div class=" cuadroblanco" style="height:120px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>SUBTOTAL:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=subtotal%>"></div>
		<div class="divhead"><strong>MONTO DESCUENTO:</strong><input type="text" name="desc"  id="desc"  style="width:150px;height:30px ;background:#FFF;"  value="<%=desc %>" readonly="readonly" disabled="disabled" ></div>
		<div class="divhead" style="width: 800px"><strong>GLOSA DESCUENTO:</strong><input type="text"  style="width:350px;height:30px ;background:#FFF;"  value="<%=gr_glosa_desc %>" disabled="disabled" ></div>
		<div class="divhead"><strong>NETO:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;" disabled="disabled" value="<%=neto %>" id="netoneto"></div>
		
		<div class="divhead"><strong>IVA 19%:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;" disabled="disabled" value="<%=iva%>"></div>
		<div class="divhead"><strong>TOTAL:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=total%>"></div>
		
	</div>
	</div>
	
	<div class=" cuadroblanco" style="height:80px;margin-top: 5px">
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>TIPO REF:</strong><input type="text" maxlength="36" style="width:200px;height:30px ;background:#FFF;" value="<%=tipo_dteref%>" readonly="readonly" id="tipo_dteref"></div>
		<div class="divhead"><strong>FOLIO REF:</strong><input type="text" name="folioref" id="folioref" maxlength="36" style="width:410px;height:30px ;background:#FFF;" value="<%=folioref%>" readonly="readonly"></div>
		<div class="divhead"><strong>FECHA REF:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;" name="fec_ref" id="datepickerref"  readonly="readonly" value="<%=fec_ref%>" ></div>
	</div>
		
	</div>
	
	
	<div class=" cuadroblanco" style="height:40px;margin-top: 5px">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>ESTADO EN BIRT:</strong><input type="text" value="EMITIDA" style="width:200px;height:30px ;background:#FFF;" disabled="disabled" ></div>
		<div class="divhead"><strong>FECHA EMISION:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=fecha_emision%>"></div>
		<div class="divhead"><strong>EMISOR:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;" disabled="disabled" value="<%=USU_INICIAL%>"></div>
	</div>
	</div>
	
	<div class=" cuadroblanco" style="height:150px;margin-top: 5px">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>FORMA DE DESPACHO:</strong><input type="text" name="obs1" disabled="disabled" style="width:720px;height:30px ;background:#FFF;" value="<%=obs1%>"></div>
		<div class="divhead"><strong>OBSERVACION:</strong><input type="text" name="obs2" disabled="disabled" style="width:720px;height:30px ;background:#FFF;" value="<%=obs2%>"></div>
		<div class="divhead"><strong>OTRAS OBS:</strong><input  type="text" name="ref" style="width:550px;height:30px ;background:#FFF;" disabled="disabled" value="<%=ref%>"></div>
		</div>
		<div class="bgrabar">
          <button type="submit" name="grabar" id="grabar" class="btn btn-success" <% if(estado.equals("EMITIDA")){ %>disabled<% } %> >ENVIAR AL SII</button>
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


