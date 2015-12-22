<%@page import="java.util.ArrayList"%>
<% 	

String gd_id=(String)request.getAttribute("gd_id");	

//data factura 
String[] arProd =(String[]) request.getAttribute("ar_prod");

String folio=(String)request.getAttribute("folio");	
if(folio==null) folio="ND";

String estado_sii=(String)request.getAttribute("estado_sii");	
String FAC_FECHA=(String)request.getAttribute("FAC_FECHA");	
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
String sub_fac_servim_total=(String)request.getAttribute("fac_servotros_total");

String tipo_dteref=(String)request.getAttribute("tipo_dteref");	
String folioref=(String)request.getAttribute("folioref");	
String fec_ref=(String)request.getAttribute("fec_ref");	


String id_dte=(String)request.getAttribute("id_dte");

String fac_servim_total=(String)request.getAttribute("fac_servim_total");
String neto= nff.format(Integer.parseInt(fac_servim_total)).replace(",",".");
 
String DESC=(String)request.getAttribute("DESC");	
String desc=nff.format(Integer.parseInt(DESC)).replace(",",".");

String fac_servotros_iva=(String)request.getAttribute("fac_servotros_iva");	
String iva=nff.format(Integer.parseInt(fac_servotros_iva)).replace(",",".");
if(fac_servim_tipodte.equals("34") || fac_servim_tipodte.equals("32")){iva="0";}

String fac_servotros_neto=(String)request.getAttribute("fac_servotros_neto");	
String netodesc=nff.format(Integer.parseInt(fac_servotros_neto)).replace(",",".");

String fac_servotros_totalfinal=(String)request.getAttribute("fac_servotros_totalfinal");	
String totalfinal=nff.format(Integer.parseInt(fac_servotros_totalfinal)).replace(",",".");



String Usu_nom=(String)request.getAttribute("usuname");	


String gr_glosa_desc=(String)request.getAttribute("fac_servim_glosa_desc");	

if(tipo_dteref!=null && !tipo_dteref.equals("null")){
	
	if(tipo_dteref.equals("801")) tipo_dteref="ORDEN DE COMPRA";
	if(tipo_dteref.equals("802")) tipo_dteref="NOTA DE PEDIDO";
	if(tipo_dteref.equals("803")) tipo_dteref="CONTRATO";
	if(tipo_dteref.equals("HES")) tipo_dteref="HOJA DE ENTRADA";
}
String empresas_giro=(String)request.getAttribute("empresas_giro");
%>
<!DOCTYPE html>
<html lang="en"><head>
    <meta charset="utf-8">
    <title>New Office - FACTURA AFECTA SERVICIOS OTROS</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/butons.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/inputs.css">
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">

  <script src="lib/jquery-ui/js/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="/801/lib/jquery.Rut.min.js"></script>

    <link rel="stylesheet" href="lib/jquery-ui/css/ui-lightness/jquery-ui-1.10.4.custom.css">
    <script src="lib/jquery-ui/js/jquery-ui-1.10.4.custom.js"></script>
  

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
    
 <script>
 
 function validateSubmit(){
	 	$("#form1").attr("action", "");
	 		
	 	var validate= true;
	 	var msg="ERRORES: \n";
	 	
	 	        	
	 	if(validate){
	 		
	 		return true;
	 	}
	 	else{
	 		alert(msg);
	 		return false;
	 	}
	 	
	 	
	 }
	 
	 
	 function previsualizar(){
			
			$("#form1").attr("action", "http://186.67.10.115:81/NOF/factura_formato_venta.php");
			$("#total_prev").val($("#totaltotal").val());
			$("#iva_prev").val($("#iva").val());
			$("#neto_prev").val($("#total").val());
			$("#dsco_prev").val($("#desc").val());
			
			
			$("#obs_prev").val('<%=fac_servim_condiciones%>');
			
			$("#obs_prev2").val('<%=CONT_NOMBRE%>'+'-'+'<%=CONT_EMAIL%>');
			
			
			$("#obs_prev3").val('<%=fac_servim_obs%>'+'-TC:'+'<%=fac_servim_tcambio%>');
			//asignamos el detalle prev 
			//primero hacemos el array
			var indice =0;
			var detalle = new Array();
			if($("#serv1").val()!=""){
				var servicio1=(indice+1)+";"+$("#serv1").val()+";1"+";"+$("#val1").val()+";0;"+$("#val1").val();
				indice++;
				$("#detalle_prev1").val(servicio1);
			}
			 
			if($("#serv2").val()!=""){
				var servicio2=(indice+1)+";"+$("#serv2").val()+";1"+";"+$("#val2").val()+";0;"+$("#val2").val();
				indice++;
				$("#detalle_prev2").val(servicio2);
			}
			if($("#serv3").val()!=""){
				var servicio3 = (indice+1)+";"+$("#serv3").val()+";1"+";"+$("#val3").val()+";0;"+$("#val3").val();
				indice++;
				$("#detalle_prev3").val(servicio3);
			}
			if($("#serv4").val()!=""){
				var servicio4= (indice+1)+";"+$("#serv4").val()+";1"+";"+$("#val4").val()+";0;"+$("#val4").val();
				indice++;
				$("#detalle_prev4").val(servicio4);
			}
			if($("#serv5").val()!=""){
				var servicio5= (indice+1)+";"+$("#serv5").val()+";1"+";"+$("#val5").val()+";0;"+$("#val5").val();
				indice++;
				$("#detalle_prev5").val(servicio5);
			}
			
			if($("#dsco_prev").val()>0){
				//generamos linea de descuento para previsualizacion 
				$("#detalle_descuento_prev").val($("#glosa_desc").val()+" $"+$("#desc").val());
				
			}
			
			if($("#tipo_dteref").val()!="NINGUNA"){
				$("#ref_prev").val($("#tipo_dteref").val()+": Nro. "+$("#folioref").val()+" del "+$("#datepickerref").val());
					
			}
			
			
			
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
	  <p>N804.E.02.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/804/efac1'">VOLVER</button> 
	<div align="center" class="title">
		<p>ELIMINAR FACTURA AFECTA - EXENTA SERVICIOS OTROS</p>
	</div>
</div>
<div class="content" >
    <form action="" name="form1" id="form1"  method="post" > 
    <input type="hidden" name="gd_id" value="<%=gd_id %>" >
	<div class=" cuadroblanco" style="height:120px;padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>NRO.FACT:</strong><input type="text" style="width:80px;height:30px ;background:#FFF;"  value="<%=folio%>" disabled="disabled"></div>
		<div class="divhead"><strong>FECHA:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;"  value="<%=FAC_FECHA %>" disabled="disabled" ></div>
		<div class="divhead"><strong>COND PAGO:</strong><input name="fac_servim_condiciones" type="text" style="width:380px;height:30px ;background:#FFF;" disabled="disabled" value="<%=fac_servim_condiciones%>"></div>
		
		<div class="divhead"><strong>EMISOR:</strong><input type="text" style="width:80px;height:30px ;background:#FFF;"  value="<%=empresas_nombrenofemisor%>" disabled="disabled"></div>
		<div class="divhead"><strong>ESTADO SII:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" value="<%=estado_sii%>" disabled="disabled"></div>
		<div class="divhead"><strong>TIPO DTE:</strong><input type="radio"  value="33" disabled="disabled" <% if(fac_servim_tipodte.equals("33")){%> checked="checked" <%} %> >(33)AFECTA <input type="radio" disabled="disabled" <% if(fac_servim_tipodte.equals("34")){%> checked="checked" <%} %> value="34">(34)EXENTA</div>
		
		<div class="divhead"><strong>FEC. VENCIMIENTO:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" value="<%=fac_servim_fecvencimiento %>" disabled="disabled" ></div>
		<div class="divhead"><strong>TIPO MANUAL:</strong><input type="radio"  value="30" disabled="disabled" <% if(fac_servim_tipodte.equals("30")){%> checked="checked" <%} %> >(30)AFECTA <input type="radio" disabled="disabled" <% if(fac_servim_tipodte.equals("32")){%> checked="checked" <%} %> value="32">(32)EXENTA</div>
		
		
	</div>

	<div class=" cuadroblanco" style="height:275px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CLIENTE</p></h3>
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>CLIENTE:</strong><input  type="text" style="width:380px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_nombrenofcliente%>"></div>
		<div class="divhead"><strong>RUT:</strong><input type="text" style="width:145px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_rutcliente%>" ></div>
		<div class="divhead"><strong>COD CLIENTE:</strong><input type="text"  style="width:70px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_idcliente%>"></div>
		<div class="divhead"><strong>RAZON SOCIAL:</strong><input type="text" style="width:500px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_razonsocialcliente%>"></div>
		<div class="divhead"><strong>DIRECCION:</strong><input type="text"  style="width:700px;height:30px ;background:#FFF;" disabled="disabled" value="<%=DIRE_DIRECCION%>"></div>
		<div class="divhead"><strong>REGION:</strong><input type="text" id="regi" style="width:510px;height:30px ;background:#FFF;" disabled="disabled" value="<%=REGI_NOMBRE%>"></div>
		<div class="divhead"><strong>CIUDAD:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" disabled="disabled" value="<%=DIRE_CIUDAD %>" ></div>
		<div class="divhead"><strong>COMUNA:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;"  disabled="disabled" value="<%=COMU_NOMBRE %>" ></div>
		<div class="divhead"><strong>RESPONSABLE CUENTA:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" disabled="disabled" value="<%=PERS_NOMBRE%>"></div>
	</div>
	
	</div>
	
	<div class=" cuadroblanco" style="height:120px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CONTACTO</p></h3>
		
		<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>CONTACTO:</strong><input type="text"  style="width:350px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CONT_NOMBRE%>"></div>
		<div class="divhead"><strong>E-MAIL:</strong><input type="text"  style="width:350px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CONT_EMAIL%>"></div>
		
		<div class="divhead"><strong>TELEFONO:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CONT_TELEFONO%>"></div>
		<div class="divhead"><strong>CELULAR:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CONT_TELEFONOC%>"></div>
				
		
	</div>
	
	</div>
	
	<div class=" cuadroblanco" style="height:235px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS SERVICIOS</p></h3>
		
		<div style="padding: 5px 5px 5px 5px;">
		<%        
		for(int i =0; i<5; i++){
			String servicio="";
			String valor="";
			try{
			if(arProd[i] !=null){
				String[] Prod = arProd[i].split("/");
				//java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
       	 		String valString = nff.format(Integer.parseInt(Prod[2])).replace(",",".");
       	 		servicio=Prod[1];
       	 		valor=valString;
			}
			}catch(Exception e){}
		%>
		<div class="divhead"><strong>SERV OTROS <%=i+1%>:</strong><input type="text" style="width:600px;height:30px ;background:#FFF;" value="<%=servicio%>" readonly="readonly" id="serv<%=i+1%>"></div>
		<div class="divhead"><strong>VALOR <%=i+1%>:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;text-align: right;" value="<%=valor%>" readonly="readonly" id="val<%=i+1%>"></div>
		<% } %>
		</div>
	</div>
	<div class=" cuadroblanco" style="height:120px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>SUBTOTAL:</strong><input type="text" id="total" style="width:150px;height:30px ;background:#FFF;text-align: right;" readonly="readonly" value="<%=neto%>"></div>
		<div class="divhead"><strong>MONTO DESCUENTO:</strong><input type="text" name="desc"  id="desc"  style="width:150px;height:30px ;background:#FFF;text-align: right;"  value="<%=desc %>" readonly="readonly"  ></div>
		<div class="divhead"><strong>GLOSA DESCUENTO:</strong><input type="text" id="glosa_desc"  style="width:350px;height:30px ;background:#FFF;"  value="<%=gr_glosa_desc %>" readonly="readonly"></div>
		<div class="divhead"><strong><% if(fac_servim_tipodte.equals("33") || fac_servim_tipodte.equals("30")){%>AFECTO<%}%><% if(fac_servim_tipodte.equals("34") || fac_servim_tipodte.equals("32")){%>EXENTO<%}%>:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;" readonly="readonly" value="<%=netodesc %>" id="netoneto"></div>
		
		<div class="divhead"><strong>IVA 19%:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;text-align: right;" readonly="readonly" id="iva" value="<%=iva%>"></div>
		<div class="divhead"><strong>TOTAL:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;text-align: right;" readonly="readonly" id="totaltotal" value="<%=totalfinal%>"></div>
		
	</div>
	</div>

<div class=" cuadroblanco" style="height:80px;margin-top: 5px">
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>TIPO REF:</strong><input type="text" maxlength="36" style="width:200px;height:30px ;background:#FFF;" value="<%=tipo_dteref%>" readonly="readonly" id="tipo_dteref"></div>
		<div class="divhead"><strong>FOLIO REF:</strong><input type="text" name="folioref" id="folioref" maxlength="36" style="width:410px;height:30px ;background:#FFF;" value="<%=folioref%>" readonly="readonly"></div>
		<div class="divhead"><strong>FECHA REF:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;" name="fec_ref" id="datepickerref"  readonly="readonly" value="<%=fec_ref%>" ></div>
	</div>
		
	</div>


	<div class=" cuadroblanco" style="height:120px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>TIPO CAMBIO:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;" value="<%=fac_servim_tcambio%>" disabled="disabled"></div>
		<div class="divhead"><strong>OBSERVACIONES:</strong><input type="text"  maxlength="64" style="width:720px;height:30px ;background:#FFF;" value="<%=fac_servim_obs%>" disabled="disabled"></div>
	</div>
		<div class="bgrabar">
		<input type="hidden" name="total_prev" id="total_prev" value="0">
		<input type="hidden" name="iva_prev" id="iva_prev" value="0">
			<input type="hidden" name="neto_prev" id="neto_prev" value="0">
			<input type="hidden" name="dsco_prev" id="dsco_prev" value="0">
			
			<input type="hidden" name="dir_prev" id="dir_prev" value="<%=DIRE_DIRECCION%>">
			<input type="hidden" name="obs_prev" id="obs_prev" value="">
			<input type="hidden" name="obs_prev2" id="obs_prev2" value="">
			<input type="hidden" name="obs_prev3" id="obs_prev3" value="">
			<input type="hidden" name="giro_prev" id="giro_prev" value="<%=empresas_giro%>" >
			<input type="hidden" name="rz_prev" id="rz_prev" value="<%=empresas_razonsocialcliente%>">
			<input type="hidden" name="tipodte_prev" id="tipodte_prev" value="<%=fac_servim_tipodte%>">
				<input type="hidden" name="detalle_descuento_prev" id="detalle_descuento_prev">
			
			<input type="hidden" name="detalle_prev[]" id="detalle_prev1">
			<input type="hidden" name="detalle_prev[]" id="detalle_prev2">
			<input type="hidden" name="detalle_prev[]" id="detalle_prev3">
			<input type="hidden" name="detalle_prev[]" id="detalle_prev4">
			<input type="hidden" name="detalle_prev[]" id="detalle_prev5">
			
			<input type="hidden"  name="comuna_prev" id="comuna_prev" value="<%=COMU_NOMBRE%>" >
			<input type="hidden"  name="ciudad_prev" id="ciudad_prev" value="<%=DIRE_CIUDAD%>" >
			<input type="hidden" name="telefono_prev" id="telefono_prev" value="<%=CONT_TELEFONO%>">
			
			<input type="hidden"  name="rut_prev"  id="rut_prev" value="<%=empresas_rutcliente%>">
			<input type="hidden" name="ref_prev" id="ref_prev" value="">
			<input type="hidden" value="<%=FAC_FECHA%>" name="fec_prev" id="fec_prev">
			<button type="submit" name="previ" id="previ" class="btn btn-success" onclick="previsualizar()"  >PREVISUALIZAR</button>
          <button type="submit" name="grabar" id="grabar" class="btn btn-success" <% if(id_dte.equals("1")){%> disabled="disabled" <% } %> onclick="return validateSubmit()" >GRABAR</button>
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


