<%@page import="java.util.ArrayList"%>
<% 	

String d_id =(String)request.getAttribute("d_id");
String tdte =(String)request.getAttribute("tdte");
String id_fac_estado =(String)request.getAttribute("id_fac_estado");

String[] arAlertas =(String[]) request.getAttribute("ar_alertas");

//String[] ar_clientes =(String[]) request.getAttribute("ar_clientes");
String guiaresumen_id=(String)request.getAttribute("guiaresumen_id");
String gr_folio=(String)request.getAttribute("gr_folio");
String id_dte=(String)request.getAttribute("id_dte");
String cliente_id=(String)request.getAttribute("cliente_id");
String gr_fecha=(String)request.getAttribute("gr_fecha");
String estado=(String)request.getAttribute("gr_estado");
String empresas_nombrenof=(String)request.getAttribute("empresas_nombrenof");
String cliente_nombre=(String)request.getAttribute("cliente_nombre");
String cliente_rut=(String)request.getAttribute("cliente_rut");
String cliente_razonsocial=(String)request.getAttribute("cliente_razonsocial");
String estados_vig_novig_nombre=(String)request.getAttribute("estados_vig_novig_nombre");
String empresas_rut_ar[]=cliente_rut.split("-");

java.text.NumberFormat nff = java.text.NumberFormat.getInstance();
String valRut = nff.format(Integer.parseInt(empresas_rut_ar[0]));
cliente_rut=valRut+"-"+empresas_rut_ar[1];

String fac_servim_tipodte=(String)request.getAttribute("fac_servim_tipodte");	




String DESC=(String)request.getAttribute("DESC");	

String desc=nff.format(Integer.parseInt(DESC)).replace(",",".");

String NETO=(String)request.getAttribute("NETO");
String neto = nff.format(Integer.parseInt(NETO)).replace(",",".");

String SUBTOTAL=(String)request.getAttribute("subtotal");
String subtotal = nff.format(Integer.parseInt(SUBTOTAL)).replace(",",".");

String IVA=(String)request.getAttribute("iva");
String iva = nff.format(Integer.parseInt(IVA)).replace(",",".");

String TOTAL=(String)request.getAttribute("total");
String total = nff.format(Integer.parseInt(TOTAL)).replace(",",".");

String direccion_nombre=(String)request.getAttribute("direccion_nombre");
String regi_nombre=(String)request.getAttribute("regi_nombre");
String gr_ciudad=(String)request.getAttribute("gr_ciudad");
String comu_nombre=(String)request.getAttribute("comu_nombre");
String cont_nombre=(String)request.getAttribute("cont_nombre");
String cont_telefono=(String)request.getAttribute("cont_telefono");
String CONT_EMAIL=(String)request.getAttribute("CONT_EMAIL");
String gr_responsable=(String)request.getAttribute("gr_responsable");

String gr_obs=(String)request.getAttribute("gr_obs");
String PERS_NOMBRE=(String)request.getAttribute("PERS_NOMBRE");

String Usu_nom=(String)request.getAttribute("usuname");	
String fecha=(String)request.getAttribute("fecha");
String fac_servim_fecvencimiento=(String)request.getAttribute("fac_servim_fecvencimiento");	

String gr_glosa_desc=(String)request.getAttribute("gr_glosa_desc");	

String tipo_dteref=(String)request.getAttribute("tipo_dteref");	
String folioref=(String)request.getAttribute("folioref");	
String fec_ref=(String)request.getAttribute("fec_ref");	


if(tipo_dteref!=null && !tipo_dteref.equals("null")){
	
	if(tipo_dteref.equals("801")) tipo_dteref="ORDEN DE COMPRA";
	if(tipo_dteref.equals("802")) tipo_dteref="NOTA DE PEDIDO";
	if(tipo_dteref.equals("803")) tipo_dteref="CONTRATO";
	if(tipo_dteref.equals("HES")) tipo_dteref="HOJA DE ENTRADA";
}
if(tipo_dteref==null || tipo_dteref.equals("NULL") || tipo_dteref.equals("")){tipo_dteref="NINGUNA";}
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
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/800/iguias_search?tipo_dte=802'">VOLVER</button> 
	<div align="center" class="title">
		<p >INGRESAR GENERADOR DOCUMENTO DTE</p>
	</div>
</div>
<div class="content" >
  <form action="iguias_res" name="form1" method="post">
  <input type="hidden" style="width:80px;height:30px" name="d_id" value="<%=d_id%>" >
  <input type="hidden" name="tdte" value="<%=tdte%>" > 
	<div class=" cuadroblanco" style="height:80px;padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>NRO.FACTURA:</strong><input type="text" style="width:80px;height:30px ;background:#FFF;" value="<%=gr_folio %>" disabled></div>
		<div class="divhead"><strong>FECHA:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;" value="<%=gr_fecha %>" disabled ></div>		
		<div class="divhead"><strong>EMISOR:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;" value="<%=empresas_nombrenof %>" disabled></div>
		<div class="divhead"><strong>ESTADO:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;" value="<%=estados_vig_novig_nombre %>" disabled></div>
		<div class="divhead"><strong>TIPO DOCUMENTO:</strong><input type="radio"  value="33" disabled="disabled" <% if(fac_servim_tipodte.equals("33")){%> checked="checked" <%} %> >(33)AFECTA <input type="radio" disabled="disabled" <% if(fac_servim_tipodte.equals("34")){%> checked="checked" <%} %> value="34">(34)EXENTA</div>
		<div class="divhead"><strong>FEC. VENCIMIENTO:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" value="<%=fac_servim_fecvencimiento %>" disabled="disabled" ></div>
	</div>
	

	<div class=" cuadroblanco" style="height:275px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CLIENTE</p></h3>
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>CLIENTE:</strong><input type="text" style="width:450px;height:30px ;background:#FFF;" value="<%=cliente_nombre %>" disabled></div>
		<div class="divhead"><strong>RUT:</strong><input type="text" style="width:145px;height:30px ;background:#FFF;" value="<%=cliente_rut %>" disabled></div>
		<div class="divhead"><strong>COD CLIENTE:</strong><input type="text" style="width:70px;height:30px ;background:#FFF;" value="<%=cliente_id %>" disabled></div>
		<div class="divhead"><strong>RAZON SOCIAL:</strong><input type="text" style="width:500px;height:30px ;background:#FFF;" value="<%=cliente_razonsocial %>" disabled></div>
		<div class="divhead"><strong>DIRECCION:</strong><input type="text" style="width:700px;height:30px ;background:#FFF;" value="<%=direccion_nombre %>" disabled></div>
		<div class="divhead"><strong>REGION:</strong><input type="text" style="width:510px;height:30px ;background:#FFF;" value="<%=regi_nombre %>" disabled></div>
		<div class="divhead"><strong>CIUDAD:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" value="<%=gr_ciudad %>" disabled></div>
		<div class="divhead"><strong>COMUNA:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" value="<%=comu_nombre %>" disabled></div>
		<div class="divhead"><strong>RESPONSABLE CUENTA:</strong><input type="text" style="width:450px;height:30px ;background:#FFF;" value="<%=PERS_NOMBRE %>" disabled></div>
	</div>
	
	</div>
	
	<div class=" cuadroblanco" style="height:120px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CONTACTO Y GUIA</p></h3>
		<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>CONTACTO:</strong><input type="text" style="width:366px;height:30px ;background:#FFF;" value="<%=cont_nombre %>" disabled></div>
		<div class="divhead"><strong>E-MAIL:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" value="<%=CONT_EMAIL %>" disabled></div>
		<div class="divhead"><strong>TELEFONO:</strong><input type="text" style="width:240px;height:30px ;background:#FFF;" value="<%=cont_telefono %>" disabled></div>
				
	</div>
	
	</div>
	
	<div class=" cuadroblanco" style="height:250px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">GUIAS DE DESPACHO VENTA ACTIVO O SERVICIO</p></h3>
		<div style=" max-height:300px; width:1000px; ">
			<table class="table" style="margin-left:0px; width:100%;">
			<thead style="border: 1px solid black">
				<tr style="background-color:#0101DF;color:#FFFFFF">
					<td width="300px" style="font-size:20px"><strong>ID</strong></td>
					<td width="350px" style="font-size:20px"><strong>MONTO</strong></td>
					<td width="350px" style="font-size:20px"><strong>FECHA</strong></td>
				</tr>  
			<tbody id="fbody" class="scroll" style="border: 1px solid black">
			 <%        
				for(int i =0; i<arAlertas.length; i++){
					String[] Alertas = arAlertas[i].split("/");
			        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
			        String valString = nf.format(Integer.parseInt(Alertas[1])).replace(",",".");
			 %>
					<tr class="hov">
						<td width="300px" ><a href="<%if(Alertas[3].indexOf("S")!=-1){%>/822/cserv?gd_id=<%=Alertas[3].replace("S", "")%><%}else{%> /821/cfac?gv_id=<%=Alertas[3].replace("A", "")%> <%} %>" target="_blank"><%=Alertas[4]%></a></td>
						<td width="350px" >$<%=valString%></td>
						<td width="350px" ><%=Alertas[2]%></td>
					</tr>
				<% } %>
			</table>
		</div>
	</div>
	<div class=" cuadroblanco" style="height:120px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>SUBTOTAL:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;text-align: right;" disabled="disabled" value="<%=subtotal%>"></div>
		<div class="divhead"><strong>MONTO DESCUENTO:</strong><input type="text" name="desc"  id="desc"  style="width:150px;height:30px ;background:#FFF;text-align: right;"  value="<%=desc %>" readonly="readonly"  ></div>
		<div class="divhead"><strong>GLOSA DESCUENTO:</strong><input type="text"  style="width:350px;height:30px ;background:#FFF;"  value="<%=gr_glosa_desc %>"  ></div>
		<div class="divhead"><strong>NETO:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;text-align: right;" disabled="disabled" value="<%=neto %>" id="netoneto"></div>
		
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
	<div class=" cuadroblanco" style="height:80px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>OBSERVACION:</strong><input type="text" style="width:720px;height:30px ;background:#FFF;" value="<%=gr_obs %>" disabled></div>
		<div class="bgrabar">
          <button type="submit" name="grabar" id="grabar" class="btn btn-success"  >ENVIAR AL SII</button>
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

    <script src="lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>
    
  </body>
</html>


