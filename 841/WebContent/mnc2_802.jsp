<%@page import="java.util.ArrayList"%>
<% 	
String fec_nc=(String)request.getAttribute("fec_nc");
String razon_nc=(String)request.getAttribute("razon_nc");
String[] ar_estados =(String[]) request.getAttribute("ar_estados");
String estadovignovig=(String)request.getAttribute("estadovignovig");
String total_ref=(String)request.getAttribute("total_ref");
String[] arAlertas =(String[]) request.getAttribute("ar_alertas");

//String[] ar_clientes =(String[]) request.getAttribute("ar_clientes");
String guiaresumen_id=(String)request.getAttribute("guiaresumen_id");
String emisor_nof=(String)request.getAttribute("emisor_nof");
	
String empresas_idemisor=(String)request.getAttribute("empresas_idemisor");	


String USU_INICIAL_EMISOR=(String)request.getAttribute("USU_INICIAL_EMISOR");
String gr_folio=(String)request.getAttribute("gr_folio");
String id_dte=(String)request.getAttribute("id_dte");
String gr_fecha=(String)request.getAttribute("gr_fecha");
String empresas_id=(String)request.getAttribute("empresas_id");
String empresas_nombrenof=(String)request.getAttribute("empresas_nombrenof");
String empresas_rut=(String)request.getAttribute("empresas_rut");
String empresas_razonsocial=(String)request.getAttribute("empresas_razonsocial");
String estados_vig_novig_nombre=(String)request.getAttribute("estados_vig_novig_nombre");
String empresas_rut_ar[]=empresas_rut.split("-");

java.text.NumberFormat nff = java.text.NumberFormat.getInstance();
String valRut = nff.format(Integer.parseInt(empresas_rut_ar[0]));
empresas_rut=valRut+"-"+empresas_rut_ar[1];


String fac_servim_tipodte=(String)request.getAttribute("fac_servim_tipodte");	


String empresas_giro=(String)request.getAttribute("empresas_giro");

String desc=(String)request.getAttribute("DESCUENTO");	
String neto=(String)request.getAttribute("NETO");
String subtotal=(String)request.getAttribute("SUBTOTAL");
String iva=(String)request.getAttribute("IVA");
String total=(String)request.getAttribute("TOTAL");


String direccion_nombre=(String)request.getAttribute("direccion_nombre");
String regi_nombre=(String)request.getAttribute("regi_nombre");
String gr_ciudad=(String)request.getAttribute("gr_ciudad");
String comu_nombre=(String)request.getAttribute("comu_nombre");
String cont_nombre=(String)request.getAttribute("cont_nombre");
String cont_telefono=(String)request.getAttribute("cont_telefono");
String gr_responsable=(String)request.getAttribute("gr_responsable");
String gr_obs=(String)request.getAttribute("gr_obs");
String CONT_EMAIL=(String)request.getAttribute("CONT_EMAIL");


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
String tref=(String)request.getAttribute("tref");
String fac_id=(String)request.getAttribute("fac_id");
String mcod=(String)request.getAttribute("mcod");

if(tipo_dteref==null || tipo_dteref.equals("NULL") || tipo_dteref.equals("")){tipo_dteref="NINGUNA";}
if(folioref==null || folioref.equals("NULL")){folioref="";}
if(fec_ref==null || fec_ref.equals("NULL")){fec_ref="";}


String nc_dondedice=(String)request.getAttribute("nc_dondedice");
String nc_debedecir=(String)request.getAttribute("nc_debedecir");
    %>
<!DOCTYPE html>
<html lang="en"><head>
    <meta charset="utf-8">
    <title>New Office</title>
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
    <link rel="stylesheet" href="lib/jquery-ui/css/ui-lightness/jquery-ui-1.10.4.custom.css">
    <script src="lib/jquery-ui/js/jquery-ui-1.10.4.custom.js"></script>
<script>
  	$( document ).ready(function() {
  		
  		$( "#fec_nc" ).datepicker({ dateFormat: "dd-mm-yy", monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
  	        monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
  	        dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
  	        dayNamesShort: ['Dom','Lun','Mar','Mie','Juv','Vie','Sab'],
  	        dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa']});
  			var tip_ref= <%=tref%>;
  			
  			if(tip_ref==1){
  				
  				
  				
  				$('.tf2').hide();
  				$('#tipref').val('ANULACION DOCUMENTO DE REFERENCIA');
  				
  				
  			}
  			if(tip_ref==2){
  				
  				
  				$('.tf2').show();
  				$('#tipref').val('CORRECCION TEXTO EN DOCUMENTO DE REFERENCIA');
  				
  			}
  			if(tip_ref==3){
  				
  				
  				$('.tf2').hide();
  				$('#tipref').val('CORRECCION MONTO EN DOCUMENTO DE REFERENCIA');
  				
  			}
  			
            
  		
  	});
  	
  	
  	function calcularTotal(){
  		var subtotal=0;
  		
       <% for(int i =0; i<arAlertas.length; i++){
          	 String[] ProdDato = arAlertas[i].split("/"); %>
          	 
          	subtotal+=Number($('#detalle_nc_<%=ProdDato[5]%>').val());
          	
   			
          	<% } %>
          	
          	var tipdt=$('input[name=tipodte]:checked').val();
          	$('#subtotal').val(subtotal);
          	$('#neto').val(subtotal-$('#desc').val());
          	if(tipdt=='33' || tipdt=='30') $('#iva').val(Math.round(Number($('#neto').val())*0.19));
        	if(tipdt=='34' || tipdt=='32') $('#iva').val("0");
        	$('#total').val(Number($('#iva').val())+Number($('#neto').val()));
          	
          	
          	console.log('Nuevo subtotal: '+subtotal);
  		
  	}
          
    function validateSubmit(){
    	$("#form1").attr("action", "");
    	
    	
    	
		
    	var validate= true;
    	var msg="ERRORES: \n";
    	if(Number(document.getElementById('total').value)><%=total_ref%>){
    		msg+="EL MONTO INGRESADO NO PUEDE SUPERAR EL VALOR DE LA FACTURA\n";
			document.getElementById('razon_nc').focus();
			validate= false;
		}
    	
    	if(Number(document.getElementById('total').value)==0){
    		msg+="EL MONTO INGRESADO NO PUEDE SER 0\n";
			document.getElementById('razon_nc').focus();
			validate= false;
		}
    	
    	        	
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
		
		
		 <%   
			
		   for(int i =0; i<arAlertas.length; i++){
		       	 String[] Alertas = arAlertas[i].split("/");
	       	
	       	
	       	 %>
	       		var r="<%=Alertas[4]%>;<%=Alertas[2]%>;1;"+$('#detalle_nc_<%=Alertas[5]%>').val()+";0;"+$('#detalle_nc_<%=Alertas[5]%>').val();
			$('#detalle_prev_id_'+<%=Alertas[5]%>).val(r);
				
	       	<% } %>
			
	    	$("#total_prev").val($("#total").val());
	    	$("#iva_prev").val($("#iva").val());
	    	$("#neto_prev").val($("#neto").val());
	    	
	    	$("#obs_prev").val('<%=USU_INICIAL_EMISOR%>');
	    	
	    	$("#obs_prev2").val('<%=cont_nombre%>-<%=CONT_EMAIL%>');
	    	
	    	
	    	$("#obs_prev3").val('<%=gr_obs%>');
	    	
	    	$("#dsco_prev").val($("#desc").val());
	    	
	    	$("#fec_prev").val($("#datepickerfechafactura").val());
	    	
	    	if(Number($("#desc").val()>0)){
	    		$("#detalle_descuento_prev").val($("#glosa_desc").val()+" $"+$("#desc").val());
	    			
	    	}
	    	
	    	var tipdt=$('input[name=tipodte]:checked').val();
	    	var tiprefname="";
	    	if(tipdt=='33') tiprefname="FACTURA AFECTA ELECTRONICA";
        	if(tipdt=='34') tiprefname="FACTURA NO AFECTA O EXENTA ELECTRONICA";
	    	
	    	
	    		$("#ref_prev").val(tiprefname+": Nro. "+$("#folio").val()+" del "+$("#datepickerfechafactura").val());
	    
	}
  	
  	</script>
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
 textarea { text-transform: uppercase; }
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
	  <p>N841.M.02.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/841/mnc1'">VOLVER</button> 
	<div align="center" class="title">
		<p>MODIFICAR NOTA DE CREDITO</p>
	</div>
</div>
<div class="content" >
  <form action="" name="form1" id="form1" method="post"> 
    
    <input type="hidden" name="tref" value="<%=tref%>" >
    <input type="hidden" name="fac_id" value="<%=fac_id%>" >
    <input type="hidden" name="mcod" value="<%=mcod%>" >
    <input type="hidden" name="tipo_dteref" value="<%=fac_servim_tipodte%>" >
    
    <input type="hidden" name="empresas_girocliente" value="<%=empresas_giro%>" >
    <input type="hidden" name="empresas_idemisor" value="<%=empresas_idemisor%>" >
    
      <input type="hidden" name="obs0" id="obs0" value="<%=USU_INICIAL_EMISOR%>" >
      <input type="hidden" name="obs1" id="obs1" value="<%=cont_nombre%>-<%=CONT_EMAIL%>" >
       <input type="hidden" name="obs2" id="obs2" value="<%=gr_obs%>" >
    
	<div class=" cuadroblanco" style="height:160px;padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>NRO.FACTURA:</strong><input type="text" name="folio" id="folio" style="width:80px;height:30px ;background:#FFF;" value="<%=gr_folio %>" readonly="readonly"></div>
		<div class="divhead"><strong>FECHA:</strong><input type="text" name="fec_ref" id="datepickerfechafactura" style="width:130px;height:30px ;background:#FFF;" value="<%=gr_fecha %>" readonly="readonly" ></div>
		<div class="divhead"><strong>FECHA NC:</strong><input name="fec_nc" id="fec_nc" type="text" class="amarillo" style="width:125px;height:30px ;background:#FF3;" readonly="readonly"  value="<%=fec_nc%>"></div>
		
		<div class="divhead"><strong>EMISOR:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;" value="<%=emisor_nof %>" readonly="readonly" name="empresa_emisora_nombre"></div>
		<div class="divhead"><strong>ESTADO:</strong><select name="estados_vig_novig_id" id="estados_vig_novig_id"  style="margin:0px 0px 0px 0px;width: 167px; margin-bottom: 9px">
    <% for(int i =0; i<ar_estados.length; i++){
       		String[] estados_vig_novig = ar_estados[i].split("/");
    
    %>
     
      <option value="<%=estados_vig_novig[0]%>" <% if(estadovignovig.equals(estados_vig_novig[0]))%><%="selected"%>><%=estados_vig_novig[1] %></option>
    <% } %>
    </select><span class="cabecera" style="color:#F00"> *</span></div>
	
		<div class="divhead"><strong>TIPO DOCUMENTO:</strong><input type="radio" name="tipodte"   value="33" disabled="disabled" <% if(fac_servim_tipodte.equals("33")){%> checked="checked" <%} %> >(33)AFECTA <input type="radio" name="tipodte"  disabled="disabled" <% if(fac_servim_tipodte.equals("34")){%> checked="checked" <%} %> value="34">(34)EXENTA</div>
		<div class="divhead"><strong>FEC. VENCIMIENTO:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" value="<%=fac_servim_fecvencimiento %>" disabled="disabled" ></div>
		<div class="divhead"><strong>TIPO REF:</strong><input type="text"  maxlength="64" style="width:720px;height:30px ;background:#FFF;" readonly="readonly" id="tipref" name="tipref"></div>
	</div>
	

		<div class=" cuadroblanco" style="height:275px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CLIENTE</p></h3>
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>CLIENTE:</strong><input type="text" style="width:450px;height:30px ;background:#FFF;" value="<%=empresas_nombrenof %>" disabled></div>
		<div class="divhead"><strong>RUT:</strong><input type="text" style="width:145px;height:30px ;background:#FFF;" value="<%=empresas_rut %>" name="841_rut" readonly="readonly"></div>
		<div class="divhead"><strong>COD CLIENTE:</strong><input type="text" style="width:70px;height:30px ;background:#FFF;" value="<%=empresas_id %>" id="clpr_id" name="clpr_id" readonly="readonly"></div>
		<div class="divhead"><strong>RAZON SOCIAL:</strong><input type="text" style="width:600px;height:30px ;background:#FFF;" value="<%=empresas_razonsocial %>" readonly="readonly" name="841_rz"></div>
		<div class="divhead"><strong>DIRECCION:</strong><input type="text" style="width:700px;height:30px ;background:#FFF;" value="<%=direccion_nombre %>" readonly="readonly" name="841_direccion"></div>
		<div class="divhead"><strong>REGION:</strong><input type="text" style="width:510px;height:30px ;background:#FFF;" value="<%=regi_nombre %>" disabled></div>
		<div class="divhead"><strong>CIUDAD:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" value="<%=gr_ciudad %>" readonly="readonly" name="ciudad"></div>
		<div class="divhead"><strong>COMUNA:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" value="<%=comu_nombre %>" readonly="readonly" name="841_comuna"></div>
		<div class="divhead"><strong>RESPONSABLE CUENTA:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" value="<%=gr_responsable %>" disabled></div>
	</div>
	
	</div>
	<div class=" cuadroblanco" style="height:120px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CONTACTO</p></h3>
		
		<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>CONTACTO:</strong><input type="text"  style="width:350px;height:30px ;background:#FFF;" disabled="disabled" value="<%=cont_nombre%>"></div>
		<div class="divhead"><strong>E-MAIL:</strong><input type="text"  style="width:350px;height:30px ;background:#FFF;" readonly="readonly" value="<%=CONT_EMAIL%>" name="cont_email"></div>
		
		<div class="divhead"><strong>TELEFONO:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=cont_telefono%>"></div>
		
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
			        String valString = Alertas[1];
			 %>
					<tr class="hov">
						<td width="300px" ><a href="<%if(Alertas[3].indexOf("S")!=-1){%>/822/cserv?gd_id=<%=Alertas[3].replace("S", "")%><%}else{%> /821/cfac?gv_id=<%=Alertas[3].replace("A", "")%> <%} %>" target="_blank"><%=Alertas[4]%></a></td>
						<td width="350px" >
						<input type="hidden" name="detalle_nc_cod_<%=Alertas[5]%>"  value="<%=Alertas[4]%>">
						<input type="hidden" name="detalle_nc_desc_<%=Alertas[5]%>"  value="<%=Alertas[2]%>">
				
						<input type="hidden" name="detalle_nc[]"  value="<%=Alertas[5]%>;;<%=valString%>">
						
						<% if(tref.equals("3")){ %><input onblur="calcularTotal()"  class="amarillo" style="width:100px;height:30px ;background:#FF3;" type="text" id="detalle_nc_<%=Alertas[5]%>" name="detalle_nc_<%=Alertas[5]%>" value="<%=valString%>"> <% }
				else{%><input style="width:100px;height:30px ;background:#FFF;" type="text" id="detalle_nc_<%=Alertas[5]%>" name="detalle_nc_<%=Alertas[5]%>" value="<%=valString%>" readonly="readonly"><% } %> 
						
						
						
						
						
						</td>
						
						
						
						
						<td width="350px" ><%=Alertas[2]%></td>
					</tr>
				<% } %>
			</table>
		</div>
	</div>
	
		<div class=" cuadroblanco" style="height:120px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>SUBTOTAL:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;text-align: right;" name="subtotal" id="subtotal" readonly="readonly" value="<%=subtotal%>"></div>
		<div class="divhead"><strong>MONTO DESCUENTO:</strong><input type="text" name="desc"  id="desc" <% if(tref.equals("3")){ %> class="amarillo" style="width:150px;height:30px ;background:#FF3;text-align: right;"  value="<%=desc %>"  onblur="calcularTotal()"  <%}else{%> style="width:150px;height:30px ;background:#FFF;text-align: right;"  value="<%=desc %>" readonly="readonly" <%} %>   ></div>
		<div class="divhead"><strong>GLOSA DESCUENTO:</strong><input type="text"  style="width:600px;height:30px ;background:#FFF;"  value="<%=gr_glosa_desc %>" readonly="readonly" id="glosa_desc" name="glosa_desc" ></div>
		<div class="divhead"><strong><% if(fac_servim_tipodte.equals("33") || fac_servim_tipodte.equals("30")){%>AFECTO<%}%><% if(fac_servim_tipodte.equals("34") || fac_servim_tipodte.equals("32")){%>EXENTO<%}%>:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;text-align: right;" id="neto" name="neto" value="<%=neto %>" readonly="readonly"></div>
		
		<div class="divhead"><strong>IVA 19%:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;text-align: right;" id="iva" name="iva" value="<%=iva%>"></div>
		<div class="divhead"><strong>TOTAL:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;text-align: right;" id="total" name="total" value="<%=total%>"></div>
		
	</div>
	</div>
	<div class=" cuadroblanco" style="height:80px;margin-top: 5px">
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>TIPO REF:</strong><input type="text" maxlength="36" style="width:200px;height:30px ;background:#FFF;" value="<%=tipo_dteref%>" readonly="readonly" id="tipo_dteref"></div>
		<div class="divhead"><strong>FOLIO REF:</strong><input type="text" name="folioref" id="folioref" maxlength="36" style="width:410px;height:30px ;background:#FFF;" value="<%=folioref%>" readonly="readonly"></div>
		<div class="divhead"><strong>FECHA REF:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;"  id="datepickerref"  readonly="readonly" value="<%=fec_ref%>" ></div>
	</div>
		
	</div>
	<div class=" cuadroblanco" style="height:40px;margin-top: 5px;">
		<div style="padding: 5px 5px 5px 5px;">
			<div class="divhead">
			<strong>RAZON NC:</strong><input class="amarillo" style="width:850px;height:30px ;background:#FF3;" maxlength="75" type="text" id="razon_nc" name="razon_nc" value="<%=razon_nc%>">
			</div>
		</div>
	</div>
	<div class=" cuadroblanco" style="height:80px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>OBSERVACION:</strong><input type="text" style="width:720px;height:30px ;background:#FFF;" value="<%=gr_obs %>" disabled></div>
		<div class="bgrabar">
		 <%   
			
        for(int i =0; i<arAlertas.length; i++){
       	 String[] Alertas = arAlertas[i].split("/");
       	 
       	 %>
       		
			<input type="hidden" name="detalle_prev[]" id="detalle_prev_id_<%=Alertas[5]%>" >
			
       	<% } %>
		
		<input type="hidden" name="total_prev" id="total_prev" value="0">
			<input type="hidden" name="iva_prev" id="iva_prev" value="0">
			<input type="hidden" name="neto_prev" id="neto_prev" value="0">
			<input type="hidden" name="dir_prev" id="dir_prev" value="<%=direccion_nombre%>">
			<input type="hidden" name="obs_prev" id="obs_prev" value="">
			<input type="hidden" name="obs_prev2" id="obs_prev2" value="">
			<input type="hidden" name="obs_prev3" id="obs_prev3" value="">
			<input type="hidden" name="obs_prev4" id="obs_prev4" value="">
			<input type="hidden" name="rut_prev" id="rut_prev" value="<%=empresas_rut%>">
			<input type="hidden" name="giro_prev" id="giro_prev" value="<%=empresas_giro%>">
			<input type="hidden" name="dsco_prev" id="dsco_prev" value="0">
			<input type="hidden" name="tipodte_prev" id="tipodte_prev">
				<input type="hidden" value="<%=gr_fecha%>" name="fec_prev" id="fec_prev">
			<input type="hidden" value="<%=empresas_razonsocial%>" name="rz_prev">
			<input type="hidden" value="<%=comu_nombre%>" name="comuna_prev">
			<input type="hidden" value="<%=gr_ciudad%>" name="ciudad_prev">
			<input type="hidden" value="<%=cont_telefono%>" name="telefono_prev">
			<input type="hidden" name="ref_prev" id="ref_prev" value="">
		<input type="hidden" name="tipo_doc_titulo_prev" id="tipo_doc_titulo_prev" value="NOTA DE CREDITO ELECTRONICA">
			
			<!-- <button type="submit" name="previ" id="previ" class="btn btn-success" onclick="previsualizar()"  >PREVISUALIZAR</button> -->
          <button type="submit" value="graba" name="grabar" id="grabar" class="btn btn-success" onclick="return validateSubmit();" >GRABAR</button>
       </div> 
	</div>
	</div>
	
	
	
	<div class=" cuadroblanco tf2" style="height:80px;margin-top: 5px;display: none;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead">
		<strong>DONDE DICE:</strong><input class="amarillo" style="width:850px;height:30px ;background:#FF3;" maxlength="75" type="text" id="nc_dondedice" name="nc_dondedice" value="<%=nc_dondedice %>">
		<strong>DEBE DECIR:</strong><input class="amarillo" style="width:850px;height:30px ;background:#FF3;" maxlength="75" type="text" id="nc_debedecir" name="nc_debedecir" value="<%=nc_debedecir %>">
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


