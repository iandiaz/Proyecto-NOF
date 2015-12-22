<%@page import="java.util.ArrayList"%>
<% 	
String gv_id="";
if(request.getAttribute("gv_id")!=null && !request.getAttribute("gv_id").equals("")) gv_id =(String) request.getAttribute("gv_id");

String[] arProd =(String[]) request.getAttribute("ar_prod");
String Usu_nom=(String)request.getAttribute("usuname");

String user_crea=(String)request.getAttribute("user_crea"); 
String user_mod=(String)request.getAttribute("user_mod"); 
String fec_crea=(String)request.getAttribute("fec_crea"); 
String fec_mod=(String)request.getAttribute("fec_mod");

String g_afecta=(String)request.getAttribute("g_afecta");
String gd_id=(String)request.getAttribute("gd_id");
String gd_fecha=(String)request.getAttribute("gd_fecha");
String emi=(String)request.getAttribute("emi");
String empresas_nombre=(String)request.getAttribute("empresas_nombre");
String estados_vig_novig_nombre=(String)request.getAttribute("estados_vig_novig_nombre");
String empresas_rut=(String)request.getAttribute("empresas_rut");
String empresas_rut_ar[]=empresas_rut.split("-");

java.text.NumberFormat nff = java.text.NumberFormat.getInstance();
	String valRut = nff.format(Integer.parseInt(empresas_rut_ar[0]));

	empresas_rut=valRut+"-"+empresas_rut_ar[1];

String empresas_id=(String)request.getAttribute("empresas_id");
String empresas_razonsocial=(String)request.getAttribute("empresas_razonsocial");
String DIRE_DIRECCION=(String)request.getAttribute("DIRE_DIRECCION");
String REGI_NOMBRE=(String)request.getAttribute("REGI_NOMBRE");
String GD_CIUDAD=(String)request.getAttribute("GD_CIUDAD");
String CONT_NOMBRE=(String)request.getAttribute("CONT_NOMBRE");
String CONT_TELEFONO=(String)request.getAttribute("CONT_TELEFONO");
String CONT_EMAIL=(String)request.getAttribute("CONT_EMAIL");
String CONT_TELEFONOC=(String)request.getAttribute("CONT_TELEFONOC");
String GD_RESPONSBALE=(String)request.getAttribute("GD_RESPONSBALE");
String GD_TRANSPORTE=(String)request.getAttribute("GD_TRANSPORTE");

String guia_obs1=(String)request.getAttribute("guia_obs1");
String guia_obs2=(String)request.getAttribute("guia_obs2");
String estado_sii=(String)request.getAttribute("estado_sii");
String GD_FOLIO=(String)request.getAttribute("GD_FOLIO");
String PERS_NOMBRE=(String)request.getAttribute("PERS_NOMBRE");
String GD_COMUNA=(String)request.getAttribute("GD_COMUNA");

if(GD_FOLIO==null || GD_FOLIO.equals("") || GD_FOLIO.equals("null")) GD_FOLIO ="ND";
String tipo_guia_serv=(String)request.getAttribute("tipo_guia_serv");

String tip_guia_serv ="";

if(tipo_guia_serv.equals("1")) tip_guia_serv="SERVICIO IMPRESION";
if(tipo_guia_serv.equals("2"))tip_guia_serv="SERVICIO TÉCNICO";

String subtotal=(String)request.getAttribute("subtotal");
String neto=(String)request.getAttribute("neto");
String total=(String)request.getAttribute("total");
String desc=(String)request.getAttribute("descuento");
String gr_glosa_desc=(String)request.getAttribute("glosadescuento");
String iva=(String)request.getAttribute("iva");


String tipo_dteref=(String)request.getAttribute("tipo_dteref");	
String folioref=(String)request.getAttribute("folioref");	
String fec_ref=(String)request.getAttribute("fec_ref");


if(tipo_dteref!=null && !tipo_dteref.equals("null")){
	
	if(tipo_dteref.equals("801")) tipo_dteref="ORDEN DE COMPRA";
	if(tipo_dteref.equals("802")) tipo_dteref="NOTA DE PEDIDO";
	if(tipo_dteref.equals("803")) tipo_dteref="CONTRATO";
	if(tipo_dteref.equals("HES")) tipo_dteref="HOJA DE ENTRADA";
}


subtotal=nff.format(Integer.parseInt(subtotal));

neto=nff.format(Integer.parseInt(neto));
total=nff.format(Integer.parseInt(total));
desc=nff.format(Integer.parseInt(desc));
iva=nff.format(Integer.parseInt(iva));


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
    <script type="text/javascript" src="/822/lib/jquery.Rut.js"></script>

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
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/800/iguias_search?tipo_dte=822'">VOLVER</button> 
	<div align="center" class="title">
		<p>INGRESAR GENERADOR DOCUMENTO DTE</p>
	</div>
</div>
<div class="content" > 
<form action="" name="form1" method="post" >
  <input type="hidden" name="fact_id" value="<%=gd_id%>" >

	<div class=" cuadroblanco" style="height:80px;padding: 5px 5px 5px 5px;margin-top: 5px;">
		<div class="divhead"><strong>NRO.GUIA:</strong><input type="text" style="width:80px;height:30px ;background:#FFF;" value="<%=GD_FOLIO %>" disabled></div>
		<div class="divhead"><strong>FECHA:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;" value="<%=gd_fecha %>" disabled="disabled" ></div>
		<div class="divhead"><strong>EMISOR:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;" value="<%=emi %>" disabled></div>
		<div class="divhead"><strong>ESTADO SII:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" value="<%=estado_sii%>" disabled="disabled"></div>
		<div class="divhead"><strong>ESTADO:</strong><input type="text" style="width:144px;height:30px ;background:#FFF;" value="<%=estados_vig_novig_nombre %>" disabled></div>
		<div class="divhead"><strong>TIPO GUIA SERV:</strong><input type="text" style="width:224px;height:30px ;background:#FFF;" value="<%=tip_guia_serv %>" disabled="disabled"></div>
		<div class="divhead" style="vertical-align:middle;padding: 8px 0 8px 0;" ><strong>AFECTA:</strong><input type="checkbox" value="1" name="g_afecta" id="g_afecta"  class="searchInput" <% if(g_afecta.equals("1")){%> checked="checked" <%} %> disabled="disabled" ></div>
	</div>

	<div class=" cuadroblanco" style="height:275px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CLIENTE</p></h3>
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>CLIENTE:</strong><input type="text" style="width:450px;height:30px ;background:#FFF;" value="<%=empresas_razonsocial %>" disabled></div>
		<div class="divhead"><strong>RUT:</strong><input type="text"  id="rut_demo_1" name="rut_demo_1" style="width:145px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_rut %>" ></div>
		<div class="divhead"><strong>COD CLIENTE:</strong><input type="text" style="width:70px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_id %>" ></div>
		<div class="divhead"><strong>RAZON SOCIAL:</strong><input type="text" style="width:600px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_razonsocial%>"></div>
		<div class="divhead"><strong>DIRECCION:</strong><input type="text" style="width:680px;height:30px ;background:#FFF;" disabled value="<%=DIRE_DIRECCION %>"></div>
		<div class="divhead"><strong>REGION:</strong><input type="text" style="width:510px;height:30px ;background:#FFF;" disabled="disabled" value="<%=REGI_NOMBRE%>"></div>
		<div class="divhead"><strong>CIUDAD:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" disabled value="<%=GD_CIUDAD %>" ></div>
		<div class="divhead"><strong>COMUNA:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" disabled value="<%=GD_COMUNA %>" ></div>
		<div class="divhead"><strong>RESPONSABLE CUENTA:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" disabled="disabled" value="<%=PERS_NOMBRE%>"></div>
	</div>
	</div>

	<div class=" cuadroblanco" style="height:120px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CONTACTO Y GUIA</p></h3>
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>CONTACTO:</strong><input type="text" style="width:366px;height:30px ;background:#FFF;" value="<%=CONT_NOMBRE %>" disabled></div>
		<div class="divhead"><strong>E-MAIL:</strong><input type="text" id="cont_mail" style="width:350px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CONT_EMAIL%>"></div>
	
		<div class="divhead"><strong>TELEFONO:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CONT_TELEFONO%>"></div>
	
		<div class="divhead"><strong>CELULAR:</strong><input type="text" id="cont_phonec" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CONT_TELEFONOC%>"></div>
	
		
		</div>
	</div>

	<div class=" cuadroblanco" style="height:235px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS SERVICIOS</p></h3>
			<div style="padding: 5px 5px 5px 5px">
		<%        
		for(int i =0; i<5; i++){
			String servicio="";
			String valor="";
			try{
			if(arProd[i] !=null){
				String[] Prod = arProd[i].split("/");
				java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
       	 		String valString = nf.format(Integer.parseInt(Prod[2])).replace(",",".");
       	 		servicio=Prod[1];
       	 		valor=valString;
			}
			}catch(Exception e){}
		%>
		<div class="divhead"><strong>SERVICIO <%=i+1%>:</strong><input type="text" style="width:600px;height:30px ;background:#FFF;" value="<%=servicio%>" disabled></div>
		<div class="divhead"><strong>VALOR <%=i+1%>:</strong><input type="text" style="width:140px;height:30px ;background:#FFF;" value="<%=valor%>" disabled></div>
		<% } %>
		</div>
	</div>
		<div class=" cuadroblanco" style="height:120px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>SUBTOTAL:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;text-align: right;" disabled="disabled" value="<%=subtotal%>"></div>
		<div class="divhead"><strong>MONTO DESCUENTO:</strong><input type="text" name="desc"  id="desc"  style="width:150px;height:30px ;background:#FFF;text-align: right;"  value="<%=desc %>" readonly="readonly" disabled="disabled" ></div>
		<div class="divhead" style="width: 800px"><strong>GLOSA DESCUENTO:</strong><input type="text"  style="width:350px;height:30px ;background:#FFF;"  value="<%=gr_glosa_desc %>" disabled="disabled" ></div>
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
	
	<div class=" cuadroblanco" style="height:120px;margin-top: 5px;padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>FORMA DE DESPACHO:</strong><input type="text" style="width:720px;height:30px ;background:#FFF;" value="<%=guia_obs1%>" disabled="disabled"></div>
		<div class="divhead"><strong>OBSERVACION:</strong><input type="text" style="width:720px;height:30px ;background:#FFF;" value="<%=guia_obs2%>" disabled="disabled"></div>
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


