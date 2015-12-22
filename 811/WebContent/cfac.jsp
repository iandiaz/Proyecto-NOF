<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 		
 
String folio=(String)request.getAttribute("folio");	
if(folio==null) folio="ND";

String oc_id =(String)request.getAttribute("FACT_ID");
String estado_sii =(String)request.getAttribute("estado_sii");
String FACT_FECHA=(String)request.getAttribute("FAC_FECHA");
String fac_servim_condiciones=(String)request.getAttribute("fac_servim_condiciones");	
String fac_servim_fecvencimiento=(String)request.getAttribute("fac_servim_fecvencimiento");	
String empresas_nombrenofemisor=(String)request.getAttribute("empresas_nombrenofemisor");	
String fac_servim_tipodte=(String)request.getAttribute("fac_servim_tipodte");	
String empresas_razonsocialcliente=(String)request.getAttribute("empresas_razonsocialcliente");	
String empresas_idcliente=(String)request.getAttribute("empresas_idcliente");	
String empresas_rutcliente=(String)request.getAttribute("empresas_rutcliente");	
String empresas_rut_ar[]=empresas_rutcliente.split("-");
java.text.NumberFormat nff = java.text.NumberFormat.getInstance();
String valRut = nff.format(Integer.parseInt(empresas_rut_ar[0]));
empresas_rutcliente=valRut+"-"+empresas_rut_ar[1];

String empresas_nombrenofcliente=(String)request.getAttribute("empresas_nombrenofcliente");	
String fac_servim_obs=(String)request.getAttribute("fac_servim_obs");
String fac_servim_obs1=(String)request.getAttribute("fac_servim_obs1");	
String fac_servim_tcambio=(String)request.getAttribute("fac_servim_tcambio");
String CONT_EMAIL=(String)request.getAttribute("CONT_EMAIL");	
String CONT_NOMBRE=(String)request.getAttribute("CONT_NOMBRE");	
String CONT_TELEFONO=(String)request.getAttribute("CONT_TELEFONO");	
String CONT_TELEFONOC=(String)request.getAttribute("CONT_TELEFONOC");	

String PERS_NOMBRE=(String)request.getAttribute("PERS_NOMBRE");
String DIRE_DIRECCION=(String)request.getAttribute("DIRE_DIRECCION");
String DIRE_CIUDAD=(String)request.getAttribute("DIRE_CIUDAD");
String COMU_NOMBRE=(String)request.getAttribute("COMU_NOMBRE");
String REGI_NOMBRE=(String)request.getAttribute("REGI_NOMBRE");

String FACT_ESTADO=(String)request.getAttribute("FACT_ESTADO");
String FACT_FECHA_EMISION=(String)request.getAttribute("FACT_FECHA_EMISION");
String USU_INICIAL=(String)request.getAttribute("USU_INICIAL");

String fac_servim_total=(String)request.getAttribute("fac_servim_total");
String neto= nff.format(Integer.parseInt(fac_servim_total)).replace(",",".");
 
String DESC=(String)request.getAttribute("DESC");	
String desc=nff.format(Integer.parseInt(DESC)).replace(",",".");



String fac_comactivo_neto=(String)request.getAttribute("fac_comactivo_neto");	
String netodesc=nff.format(Integer.parseInt(fac_comactivo_neto)).replace(",",".");

String fac_comactivo_totalfinal=(String)request.getAttribute("fac_comactivo_totalfinal");	
String totalfinal=nff.format(Integer.parseInt(fac_comactivo_totalfinal)).replace(",",".");

String[] arProd =(String[]) request.getAttribute("ar_prod");

String Usu_nom=(String)request.getAttribute("usuname");
String fac_comserv_ivap=(String)request.getAttribute("fac_comactivo_porcentaje_ivaret");	
String ivap=nff.format(Integer.parseInt(fac_comserv_ivap)).replace(",",".");

String fac_comserv_iva=(String)request.getAttribute("fac_comactivo_valor_ivaret");	
String iva=nff.format(Integer.parseInt(fac_comserv_iva)).replace(",",".");
String fac_comserv_valor_ivanoret=(String)request.getAttribute("fac_comactivo_valor_ivanoret");	

    %>
<!DOCTYPE html>
<html lang="en"><head>
    <meta charset="utf-8">
    <title>New Office - Consultar Factura Afecta Excenta Activos</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/butons.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/inputs.css">
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">

    <script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="/801/lib/jquery.Rut.min.js"></script>
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
	  <p>N811.C.02.001</p>
	</div>
	<form method="get" action="iperusu" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/811/cfac_search'">VOLVER</button> 
	<div align="center" class="title">
		<p >CONSULTAR FACTURA AFECTA - EXENTA ACTIVOS</p>
	</div>
</div>
<div class="content" >
  <form action="ifac" name="form1" method="post">
  <input type="hidden" name="fact_id" value="<%=oc_id%>" >
	<div class=" cuadroblanco" style="height:100px;">
		<div class="divhead"><strong>NRO.FACT:</strong><input  type="text" style="width:100px;height:30px ;background:#FFF;" disabled="disabled" value="<%=oc_id%>"></div>
		<div class="divhead"><strong>FOLIO:</strong><input  type="text" style="width:100px;height:30px ;background:#FFF;" disabled="disabled" value="<%=folio%>"></div>
		<div class="divhead"><strong>FECHA:</strong><input  type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=FACT_FECHA%>"></div>
		<div class="divhead"><strong>CONDICIONES:</strong><input type="text" style="width:380px;height:30px ;background:#FFF;" disabled="disabled" value="<%=fac_servim_condiciones%>"></div>
		<div class="divhead"><strong>EMISOR:</strong><input type="text" style="width:90px;height:30px ;background:#FFF;" disabled="disabled" value="BIRT"></div>
		<div class="divhead"><strong>ESTADO SII:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=estado_sii%>"></div>
		<div class="divhead"><strong>TIPO DOCUMENTO:</strong>
		<input type="radio" name="tipodte" value="46" checked="checked" disabled="disabled">(46) FACT COMPRA ELECT</div>
	</div>
	<br>
	<div class=" cuadroblanco" style="height:270px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CLIENTE</p></h3>
		<div class="divhead"><strong>RAZON SOCIAL:</strong><input type="text" style="width:600px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_nombrenofcliente%>"></div>
		<div class="divhead"><strong>RUT:</strong><input type="text" name="empresas_rut" id="empresas_rut" style="width:145px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_rutcliente%>" ></div>
		<div class="divhead"><strong>DIRECCION:</strong><input type="text" style="width:500px;height:30px ;background:#FFF;" disabled="disabled" value="<%=DIRE_DIRECCION%>"></div>
		<div class="divhead"><strong>COMUNA:</strong><input type="text" style="width:250px;height:30px ;background:#FFF;" disabled="disabled" value="<%=COMU_NOMBRE%>" ></div>		
		<div class="divhead"><strong>CIUDAD:</strong><input type="text" style="width:250px;height:30px ;background:#FFF;" disabled="disabled" value="<%=DIRE_CIUDAD%>" ></div>
		<div class="divhead"><strong>REGION:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" disabled="disabled" value="<%=REGI_NOMBRE%>"></div>
		<div class="divhead"><strong>GIRO:</strong><input type="text" style="width:600px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_razonsocialcliente%>"></div>
		<div class="divhead"><strong>RESPONSABLE:</strong><input type="text" style="width:400px;height:30px ;background:#FFF;" disabled="disabled" value="<%=PERS_NOMBRE%>"></div>
		<div class="divhead"><strong>TIPO CAMBIO:</strong><input  type="text" style="width:120px;height:30px ;background:#FFF;" disabled="disabled" value="<%=fac_servim_tcambio%>"></div>
		<div class="divhead"><strong>CONTACTO:</strong><input type="text" style="width:400px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CONT_NOMBRE%>"></div>
		<div class="divhead"><strong>TELEFONO:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CONT_TELEFONO%>"></div>
	</div> 
	<br>
	<div class=" cuadroblanco" style="height:200px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS PRODUCTOS A FACTURAR</p></h3>
		<table class="table">
			<thead style="border: 1px solid black;">
			   <tr style="background-color:#0101DF;color:#FFFFFF">
				<td width="80" style="font-size:20px"><strong>ID</strong></td>
				<td width="750" style="font-size:20px"><strong>PN/TLI - DESCRIPCION</strong></td>
				<td width="250" style="font-size:20px"><strong>VALOR</strong></td>
			  </tr>  
			<tbody id="fbody" class="scroll" style="border: 1px solid black">
			 <%   
			 String des;
        for(int i =0; i<arProd.length; i++){
       	 String[] ProdDato = arProd[i].split("/");
       	 String monto = nff.format(Integer.parseInt(ProdDato[2])).replace(",",".");
       	 if(ProdDato[1].length()>35){
       		 des = ProdDato[1].substring(0,36);
       	 }else{
       		 des = ProdDato[1];
       	 }
       	 %>
       		
			<tr class="hov">
				<td width="80"><%=ProdDato[0]%></td>
				<td width="750"><%=des%></td>
				<td width="250">$<%=monto%></td>
			</tr>
			
       	<% } %>
			</tbody>
			  </tr>
		  </table>
	</div>
	<br>
	<div class=" cuadroblanco" style="height:80px;">
		<div class="divhead"><strong>SUBTOTAL:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=neto%>"></div>
		<div class="divhead"><strong>NETO:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;" disabled="disabled" value="<%=netodesc %>" id="netoneto"></div>
		<div class="divhead"><strong>%IVA RETENIDO:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;" disabled="disabled" value="<%=ivap%>"> $<input type="text" readonly="readonly" style="width:120px;height:30px ;background:#FFF;" value="<%=iva%>" id="iva" name="iva" ></div>
		<div class="divhead"><strong>%IVA NO RETENIDO:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;" disabled="disabled" value="<%=((19-Integer.parseInt(ivap))+"") %>"> $<input type="text" readonly="readonly" style="width:120px;height:30px ;background:#FFF;" value="<%=fac_comserv_valor_ivanoret%>" id="iva" name="iva" ></div>
		<div class="divhead"><strong>TOTAL:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=totalfinal%>"></div>
		
	</div>
	<br>
	<div class=" cuadroblanco" style="height:40px;">
		<div class="divhead"><strong>ESTADO EN BIRT:</strong><input type="text" value="<%=FACT_ESTADO%>" style="width:200px;height:30px ;background:#FFF;" disabled="disabled" ></div>
		<div class="divhead"><strong>FECHA EMISION:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=FACT_FECHA_EMISION%>"></div>
		<div class="divhead"><strong>EMISOR:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;" disabled="disabled" value="<%=USU_INICIAL%>"></div>
	</div>
	<br>
	<div class=" cuadroblanco" style="height:80px;">
		<div class="divhead"><strong>FORMA DE DESPACHO:</strong><input type="text" name="obs1" disabled="disabled" style="width:720px;height:30px ;background:#FFF;" value="<%=fac_servim_obs%>"></div>
		<div class="divhead"><strong>OBSERVACION:</strong><input type="text" name="obs2" disabled="disabled" style="width:720px;height:30px ;background:#FFF;" value="<%=fac_servim_obs1%>"></div>
	</div>
</form>
</div><br><br><br>
<div class="footerapp">               
	<p style="float: left;"><i class="icon-user"></i><strong><%=Usu_nom %></strong></p>
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


