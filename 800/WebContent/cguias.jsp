<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 		

String fact_id =(String)request.getAttribute("FACT_ID");
String tdte =(String)request.getAttribute("tdte");
String id_fac_estado =(String)request.getAttribute("id_fac_estado");
String CLPR_GIRO=(String)request.getAttribute("CLPR_GIRO");

String FACT_FECHA=(String)request.getAttribute("FACT_FECHA");
String FACT_CONDICIONES=(String)request.getAttribute("FACT_CONDICIONES");
String CLPR_NOMBRE_FANTASIA=(String)request.getAttribute("CLPR_NOMBRE_FANTASIA");
String FACT_TIPO_CAMBIO=(String)request.getAttribute("FACT_TIPO_CAMBIO");
String CONT_NOMBRE=(String)request.getAttribute("CONT_NOMBRE");
String CONT_TELEFONO=(String)request.getAttribute("CONT_TELEFONO");
String FACT_TOTAL_TEXTO=(String)request.getAttribute("FACT_TOTAL_TEXTO");
String FACT_OBS=(String)request.getAttribute("FACT_OBS");
String FACT_ESTADO=(String)request.getAttribute("FACT_ESTADO");
String FACT_FECHA_EMISION=(String)request.getAttribute("FACT_FECHA_EMISION");
String USU_INICIAL=(String)request.getAttribute("USU_INICIAL");
String FAC_RESPONSABLE=(String)request.getAttribute("FAC_RESPONSABLE");
String CONT_EMAIL=(String)request.getAttribute("CONT_EMAIL");
String fec_vencimiento=(String)request.getAttribute("fec_vencimiento");

String CLPR_RUT=(String)request.getAttribute("CLPR_RUT");
String empresas_rut = CLPR_RUT;
String empresas_rut_ar[]=empresas_rut.split("-");
java.text.NumberFormat nff = java.text.NumberFormat.getInstance();
	String valRut = nff.format(Integer.parseInt(empresas_rut_ar[0]));
	empresas_rut=valRut+"-"+empresas_rut_ar[1];
	
String CLPR_RAZON_SOCIAL=(String)request.getAttribute("CLPR_RAZON_SOCIAL");
String DIRE_DIRECCION=(String)request.getAttribute("DIRE_DIRECCION");
String DIRE_CIUDAD=(String)request.getAttribute("DIRE_CIUDAD");
String REGI_NOMBRE=(String)request.getAttribute("REGI_NOMBRE");
String PERS_NOMBRE=(String)request.getAttribute("PERS_NOMBRE");
String COMU_NOMBRE=(String)request.getAttribute("COMU_NOMBRE");
String foliofact=(String)request.getAttribute("foliofact");


String tipo_dteref=(String)request.getAttribute("tipo_dteref");	
String folioref=(String)request.getAttribute("folioref");	
String fec_ref=(String)request.getAttribute("fec_ref");	


if(tipo_dteref!=null && !tipo_dteref.equals("null")){
	
	if(tipo_dteref.equals("801")) tipo_dteref="ORDEN DE COMPRA";
	if(tipo_dteref.equals("802")) tipo_dteref="NOTA DE PEDIDO";
	if(tipo_dteref.equals("803")) tipo_dteref="CONTRATO";
	if(tipo_dteref.equals("HES")) tipo_dteref="HOJA DE ENTRADA";
}

java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
String NETO=(String)request.getAttribute("NETO");
String neto = nf.format(Integer.parseInt(NETO)).replace(",",".");

String IVA=(String)request.getAttribute("IVA");
String iva = nf.format(Integer.parseInt(IVA)).replace(",",".");

String TOTAL=(String)request.getAttribute("TOTAL");
String total = nf.format(Integer.parseInt(TOTAL)).replace(",",".");

String SUBTOTAL_=(String)request.getAttribute("SUBTOTAL");
String SUBTOTAL = nf.format(Integer.parseInt(SUBTOTAL_)).replace(",",".");

String DESCUENTO_=(String)request.getAttribute("DESCUENTO");
String DESCUENTO = nf.format(Integer.parseInt(DESCUENTO_)).replace(",",".");

String factura_tipodte=(String)request.getAttribute("factura_tipodte");
String factura_referencia=(String)request.getAttribute("factura_referencia");
String factura_obs1=(String)request.getAttribute("factura_obs1");
String factura_obs2=(String)request.getAttribute("factura_obs2");

String[] ar_productos =(String[])request.getAttribute("ar_productos");

String Usu_nom=(String)request.getAttribute("usuname");
String GLOSADESC=(String)request.getAttribute("GLOSADESC");



if(tipo_dteref==null || tipo_dteref.equals("NULL") || tipo_dteref.equals("")){tipo_dteref="NINGUNA";}
if(folioref==null || folioref.equals("NULL")){folioref="";}
if(fec_ref==null || fec_ref.equals("NULL")){fec_ref="";}

String numbirt=(String)request.getAttribute("numbirt");

String estadovignovig=(String)request.getAttribute("estadovignovig");

String estadoname="";

if(estadovignovig.equals("1")) estadoname="VIGENTE";
if(estadovignovig.equals("2")) estadoname="NO VIGENTE";


    %>
<!DOCTYPE html>
<html lang="en"><head>
    <meta charset="utf-8">
    <title>New Office - Generador Documento DTE</title>
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
		<p >CONSULTAR GENERADOR DOCUMENTO DTE</p>
	</div>
</div>
<div class="content" >
  <form action="iguias" name="form1" method="post">
  <input type="hidden" name="fact_id" value="<%=fact_id%>" >
  <input type="hidden" name="tdte" value="<%=tdte%>" >
	<div class=" cuadroblanco" style="height:160px;">
	<div style="padding: 5px 5px 0px 5px">
		<div class="divhead"><strong>NRO.FOLIO:</strong><input  type="text" style="width:100px;height:30px ;background:#FFF;" disabled="disabled" value="<%=foliofact%>"></div>
		<div class="divhead"><strong>NRO.BIRT:</strong><input  type="text" style="width:100px;height:30px ;background:#FFF;" readonly="readonly" value="<%=numbirt%>" name="Codigo_relacionado"></div>
		<div class="divhead"><strong>FECHA:</strong><input  type="text" style="width:125px;height:30px ;background:#FFF;" disabled="disabled" value="<%=FACT_FECHA%>"></div>
		<div class="divhead"><strong>CONDICIONES:</strong><input type="text" style="width:380px;height:30px ;background:#FFF;" disabled="disabled" value="<%=FACT_CONDICIONES%>"></div>
		<div class="divhead"><strong>EMISOR:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CLPR_NOMBRE_FANTASIA%>"></div>
		<div class="divhead"><strong>ESTADO SII:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="PARA ENVIAR"></div>
		<div class="divhead"><strong>TIPO DOCUMENTO:</strong>
		<input style="height:30px" type="radio" name="tipodte" value="33" disabled="disabled" <% if(factura_tipodte != null && factura_tipodte.equals("33")){ %> checked="checked" <% }%>>(33)AFECTA 
		<input  style="height:30px" type="radio" name="tipodte" value="34" disabled="disabled" <% if(factura_tipodte != null && factura_tipodte.equals("34")){ %> checked="checked" <% }%>>(34)EXENTA</div>
		<div class="divhead"><strong>FEC. VENCIMIENTO:</strong><input maxlength="10" type="text" style="width:130px;height:30px ;background:#FFF;" value="<%=fec_vencimiento %>"   readonly="readonly"></div>
		<div class="divhead"><strong>ESTADO:</strong><input maxlength="10" type="text" style="width:130px;height:30px ;background:#FFF;" value="<%=estadoname %>"   readonly="readonly"></div>
	</div>
	</div>

	<div class=" cuadroblanco" style="height:310px;margin-top: 5px">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CLIENTE</p></h3>
		<div style="padding: 5px 5px 0px 5px">
	
		<div class="divhead"><strong>RAZON SOCIAL:</strong><input type="text" style="width:600px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CLPR_RAZON_SOCIAL%>"></div>
		<div class="divhead"><strong>RUT:</strong><input type="text" name="empresas_rut" id="empresas_rut" style="width:145px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_rut%>" ></div>
		<div class="divhead"><strong>GIRO:</strong><input type="text" style="width:600px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CLPR_GIRO%>"></div>
		
		<div class="divhead"><strong>DIRECCION:</strong><input type="text" style="width:500px;height:30px ;background:#FFF;" disabled="disabled" value="<%=DIRE_DIRECCION%>"></div>
		<div class="divhead"><strong>COMUNA:</strong><input type="text" style="width:250px;height:30px ;background:#FFF;" disabled="disabled" value="<%=COMU_NOMBRE%>" ></div>		
		<div class="divhead"><strong>CIUDAD:</strong><input type="text" style="width:250px;height:30px ;background:#FFF;" disabled="disabled" value="<%=DIRE_CIUDAD%>" ></div>
		<div class="divhead"><strong>REGION:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" disabled="disabled" value="<%=REGI_NOMBRE%>"></div>
		<div class="divhead"><strong>RESPONSABLE:</strong><input type="text" style="width:400px;height:30px ;background:#FFF;" disabled="disabled" value="<%=FAC_RESPONSABLE%>"></div>
		<div class="divhead"><strong>TIPO CAMBIO:</strong><input  type="text" style="width:120px;height:30px ;background:#FFF;" disabled="disabled" value="<%=FACT_TIPO_CAMBIO%>"></div>
		<div class="divhead"><strong>CONTACTO:</strong><input type="text" style="width:400px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CONT_NOMBRE%>"></div>
		<div class="divhead"><strong>TELEFONO:</strong><input type="text" style="width:240px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CONT_TELEFONO%>"></div>
		<div class="divhead"><strong>CONTACTO EMAIL:</strong><input id="CONT_EMAIL" name="CONT_EMAIL" type="text" maxlength="40" style="width:450px;height:30px ;background:#FFF;" readonly="readonly" value="<%=CONT_EMAIL%>"></div>
		
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
       	 String[] ProdDato = ar_productos[i].split("/");
       	 String monto = nf.format(Integer.parseInt(ProdDato[4])).replace(",",".");
       	 if(ProdDato[2].length()>35){
       		 des = ProdDato[2].substring(0,36);
       	 }else{
       		 des = ProdDato[2];
       	 }
       	 %>
       		
			<tr class="hov">
				<td width="80"><%=ProdDato[0]%></td>
				<td width="700"><%=ProdDato[1]%> - <%=des%></td>
				<td width="120"><%=ProdDato[3]%></td>
				<td width="100">$<%=monto%></td>
			</tr>
			
       	<% } %>
			</tbody>
		  </table>
	</div>

	<div class=" cuadroblanco" style="height:120px;margin-top: 5px">
	<div style="padding: 5px 5px 0px 5px">
		<div class="divhead"><strong>SUBTOTAL:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;text-align: right;" disabled="disabled" value="<%=SUBTOTAL%>"></div>
		
		<div class="divhead"><strong>MONTO DESCUENTO:</strong><input type="text"  style="width:150px;height:30px ;background:#FFF;text-align: right;"  value="<%=DESCUENTO %>"  maxlength="12" readonly="readonly" ></div>
		<div class="divhead" style="width:800px;"><strong>GLOSA DESCUENTO:</strong><input type="text"  style="width:350px;height:30px ;background:#FFF;" maxlength="30"  value="<%=GLOSADESC %>" readonly="readonly"  ></div>
		<div class="divhead"><strong>NETO:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;text-align: right;" readonly="readonly" value="<%=neto %>" ></div>
		
		<div class="divhead"><strong>IVA 19%:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;text-align: right;" disabled="disabled" value="<%=iva%>"></div>
		<div class="divhead"><strong>TOTAL:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;text-align: right;" disabled="disabled" value="<%=total%>"></div>
		
		</div>
	</div>
	
	
	<div class=" cuadroblanco" style="height:80px;margin-top: 5px">
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>TIPO REF:</strong><input type="text" maxlength="36" style="width:200px;height:30px ;background:#FFF;" value="<%=tipo_dteref%>" readonly="readonly"></div>
		<div class="divhead"><strong>FOLIO REF:</strong><input type="text" name="folioref" id="folioref" maxlength="36" style="width:410px;height:30px ;background:#FFF;" value="<%=folioref%>" readonly="readonly"></div>
		<div class="divhead"><strong>FECHA REF:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;" name="fec_ref" id="datepicker"  readonly="readonly" value="<%=fec_ref%>" ></div>
	</div>
		
	</div>

	<div class=" cuadroblanco" style="height:40px;margin-top: 5px">
	<div style="padding: 5px 5px 0px 5px">
		<div class="divhead"><strong>ESTADO EN BIRT:</strong><input type="text" value="EMITIDA" style="width:200px;height:30px ;background:#FFF;" disabled="disabled" ></div>
		<div class="divhead"><strong>FECHA EMISION:</strong><input type="text" style="width:125px;height:30px ;background:#FFF;" disabled="disabled" value="<%=FACT_FECHA_EMISION%>"></div>
		<div class="divhead"><strong>EMISOR:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;" disabled="disabled" value="<%=USU_INICIAL%>"></div>
	</div>
	</div>

	<div class=" cuadroblanco" style="height:150px;margin-top: 5px">
	<div style="padding: 5px 5px 0px 5px">
		<div class="divhead"><strong>FORMA DE DESPACHO:</strong><input type="text" name="obs1" disabled="disabled" style="width:410px;height:30px ;background:#FFF;" value="<%=factura_obs1%>"></div>
		<div class="divhead"><strong>OBSERVACION:</strong><input type="text" name="obs2" disabled="disabled" style="width:460px;height:30px ;background:#FFF;" value="<%=factura_obs2%>"></div>
		<div class="divhead"><strong>OTRAS OBS:</strong><input type="text" style="width:450px;height:30px ;background:#FFF;" disabled="disabled" value="<%=FACT_OBS%>"></div>
		
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


