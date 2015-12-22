<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%



String fechaaprobacion=(String)request.getAttribute("fechaaprobacion");
String usuaprobacion=(String)request.getAttribute("usuaprobacion");

String[] ar_estados =(String[]) request.getAttribute("ar_estados");

String est_aprobacion=(String)request.getAttribute("est_aprobacion");
String fec_nc=(String)request.getAttribute("fec_nc");
String razon_nc=(String)request.getAttribute("razon_nc");	
String d_id =(String)request.getAttribute("d_id");
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

String VTA_OC=(String)request.getAttribute("VTA_OC");

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
String CLPR_ID=(String)request.getAttribute("CLPR_ID");




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
String neto = NETO;

String IVA=(String)request.getAttribute("IVA");
String iva = IVA;

String TOTAL=(String)request.getAttribute("TOTAL");
String total = TOTAL;

String SUBTOTAL_=(String)request.getAttribute("SUBTOTAL");

String DESCUENTO_=(String)request.getAttribute("DESCUENTO");

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
	
String FACT_FOLIO=(String)request.getAttribute("FACT_FOLIO");
if(FACT_FOLIO==null) FACT_FOLIO="ND";



String tref=(String)request.getAttribute("tref");
String fac_id=(String)request.getAttribute("fac_id");
String mcod=(String)request.getAttribute("mcod");

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
    <link href='http://fonts.googleapis.com/css?family=Inconsolata:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/butons.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/inputs.css">
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">

    <script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="/801/lib/jquery.Rut.min.js"></script>
    <!-- Demo page code -->

    <style type="text/css">
    textarea { text-transform: uppercase; }
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
  	$( document ).ready(function() {
  		
  			
  			var tip_ref= <%=tref%>;
  			
  			if(tip_ref==1){
  				
  				$('.v1').prop('disabled', true);
  				$('.v2').prop('disabled', true);
  				$('.v3').prop('disabled', true);
  				$('.v4').prop('disabled', true);
  				$('.v5').prop('disabled', true);
  				
  				$('.v1').css("background","#FFF");
  				$('.v2').css("background","#FFF");
  				$('.v3').css("background","#FFF");
  				$('.v4').css("background","#FFF");
  				$('.v5').css("background","#FFF");
  				
  				$('.tf2').hide();
  				$('#tipref').val('ANULACION DOCUMENTO DE REFERENCIA');
  				
  				
  			}
  			if(tip_ref==2){
  				$('.v1').prop('disabled', true);
  				$('.v2').prop('disabled', true);
  				$('.v3').prop('disabled', true);
  				$('.v4').prop('disabled', true);
  				$('.v5').prop('disabled', true);
  				
  				$('.v1').css("background","#FFF");
  				$('.v2').css("background","#FFF");
  				$('.v3').css("background","#FFF");
  				$('.v4').css("background","#FFF");
  				$('.v5').css("background","#FFF");
  				
  				$('.tf2').show();
  				$('#tipref').val('CORRECCION TEXTO EN DOCUMENTO DE REFERENCIA');
  				
  			}
  			if(tip_ref==3){
  				$('.v1').prop('disabled', false);
  				$('.v2').prop('disabled', false);
  				$('.v3').prop('disabled', false);
  				$('.v4').prop('disabled', false);
  				$('.v5').prop('disabled', false);
  				
  				$('.v1').css("background","#FF3");
  				$('.v2').css("background","#FF3");
  				$('.v3').css("background","#FF3");
  				$('.v4').css("background","#FF3");
  				$('.v5').css("background","#FF3");
  				
  				$('.tf2').hide();
  				$('#tipref').val('CORRECCION MONTO EN DOCUMENTO DE REFERENCIA');
  				
  			}
  			
            
  		
  	});
  	
  	function calcularTotal(){
  		var subtotal=0;
  		
       <% for(int i =0; i<ar_productos.length; i++){
          	 String[] ProdDato = ar_productos[i].split("/"); %>
          	 
          	subtotal+=Number($('#detalle_nc_<%=ProdDato[7]%>').val());
          	
   			
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
			if(!tref.equals("2")){
				
				for(int i =0; i<ar_productos.length; i++){
			       	 String[] ProdDato = ar_productos[i].split("/");
			       	 String monto = nf.format(Integer.parseInt(ProdDato[4])).replace(",",".");
			       	 String dess="";
			       	 if(ProdDato[2].length()>35){
			       		dess = ProdDato[2].substring(0,36);
			       	 }else{
			       		dess = ProdDato[2];
			       	 }
			       	 %>
			       		var r="<%=ProdDato[0]%>-<%=ProdDato[5]%>-<%=ProdDato[6]%>;<%=dess%> - <%=ProdDato[3]%>-<%=ProdDato[1]%>;1;"+$('#detalle_nc_<%=ProdDato[7]%>').val()+";0;"+$('#detalle_nc_<%=ProdDato[7]%>').val();
					$('#detalle_prev_id_'+<%=ProdDato[7]%>).val(r);
						
			       	<% } %>
				
				

			    	$("#total_prev").val($("#total").val());
			    	$("#iva_prev").val($("#iva").val());
			    	$("#neto_prev").val($("#neto").val());
			    	$("#dsco_prev").val($("#desc").val());
			    	if(Number($("#desc").val()>0)){
			    		$("#detalle_descuento_prev").val($("#glosa_desc").val()+" $"+$("#desc").val());
			    	}
			    	
				
				<%
				}else{ %>
				
				<%
				String primerid="";
				for(int i =0; i<ar_productos.length; i++){
					 String[] ProdDato = ar_productos[i].split("/");
					 primerid=ProdDato[7];
					break;
				}%>
					var r="0;DONDE DICE "+$('#nc_dondedice').val()+" DEBE DECIR "+$('#nc_debedecir').val()+";0;0;0;0";
					
					$('#detalle_prev_id_'+<%=primerid%>).val(r);
				
					
				<% } %>
			
	        
			
	    	$("#obs_prev").val('<%=USU_INICIAL%>-'+$("#obs1").val()+'-'+$("#condiciones").val()+'-<%=VTA_OC%>');
	    	
	    	$("#obs_prev2").val($("#CONT_NOMBRE").val()+'-'+$("#CONT_EMAIL").val());
	    	
	    	
	    	$("#obs_prev3").val($("#obs2").val()+'-'+$("#ref").val()+'-TC:'+$("#tipo_cambio").val());
	    	
	    	$("#rut_prev").val($("#empresas_rut").val());
	    	
	    	
	    	$("#fec_prev").val($("#datepickerfechafactura").val());
	    	
	    	
	    	
	    	var tipdt=$('input[name=tipodte]:checked').val();
	    	var tiprefname="";
	    	if(tipdt=='33') tiprefname="FACTURA AFECTA ELECTRONICA";
        	if(tipdt=='34') tiprefname="FACTURA NO AFECTA O EXENTA ELECTRONICA";
	    	
	    	$("#ref_prev").val(tiprefname+": Nro. "+$("#folio").val()+" del "+$("#datepickerfechafactura").val()+" "+$("#razon_nc").val());
	    
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
	   <p>N870.C.02.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/870/c1'">VOLVER</button> 
	<div align="center" class="title">
		<p >CONSULTAR APROBACION NOTA DE CREDITO</p>
	</div>
</div>
<div class="content" >
  <form action="" name="form1" id="form1" method="post">
    
<input type="hidden" name="d_id" value="<%=d_id%>" >
  <input type="hidden" name="fact_id" value="<%=fact_id%>" >
   <input type="hidden" name="tref" value="<%=tref%>" >
    <input type="hidden" name="fac_id" value="<%=fac_id%>" >
    <input type="hidden" name="mcod" value="<%=mcod%>" >

    <input type="hidden" name="tipo_dteref" value="<%=factura_tipodte%>" >
    <input type="hidden" name="clpr_id" value="<%=CLPR_ID%>" >
    
    
    
	<div class=" cuadroblanco" style="height:200px;">
	<div style="padding: 5px 5px 0px 5px">
		<div class="divhead"><strong>NRO.FOLIO:</strong><input  type="text" name="folio" id="folio" style="width:100px;height:30px ;background:#FFF;" readonly="readonly" value="<%=FACT_FOLIO%>"></div>
		<div class="divhead"><strong>NRO.BIRT:</strong><input  type="text" style="width:100px;height:30px ;background:#FFF;" readonly="readonly" value="<%=numbirt%>" name="Codigo_relacionado"></div>
		<div class="divhead"><strong>FECHA:</strong><input name="fec_ref" type="text" style="width:125px;height:30px ;background:#FFF;" readonly="readonly" id="datepickerfechafactura" value="<%=FACT_FECHA%>"></div>
		<div class="divhead"><strong>FECHA NC:</strong><input name="fec_nc" id="fec_nc" type="text"  style="width:125px;height:30px ;background:#FFF;" readonly="readonly"  value="<%=fec_nc%>"></div>
		
		<div class="divhead"><strong>CONDICIONES:</strong><input type="text" style="width:380px;height:30px ;background:#FFF;" readonly="readonly" value="<%=FACT_CONDICIONES%>" id="condiciones" ></div>
		<div class="divhead"><strong>EMISOR:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CLPR_NOMBRE_FANTASIA%>"></div>
		<div class="divhead"><strong>ESTADO SII:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="PARA ENVIAR"></div>
		<div class="divhead"><strong>TIPO DOCUMENTO:</strong>
		<input style="height:30px" type="radio" name="tipodte" value="33" disabled="disabled" <% if(factura_tipodte != null && factura_tipodte.equals("33")){ %> checked="checked" <% }%>>(33)AFECTA 
		<input  style="height:30px" type="radio" name="tipodte" value="34" disabled="disabled" <% if(factura_tipodte != null && factura_tipodte.equals("34")){ %> checked="checked" <% }%>>(34)EXENTA</div>
		<div class="divhead"><strong>FEC. VENCIMIENTO:</strong><input maxlength="10" type="text" style="width:130px;height:30px ;background:#FFF;" value="<%=fec_vencimiento %>"   readonly="readonly"></div>
		<div class="divhead"><strong>ESTADO:</strong><input maxlength="10" type="text" style="width:130px;height:30px ;background:#FFF;" value="<%=estadoname %>"   readonly="readonly"></div>
		<div class="divhead"><strong>ESTADO:</strong><div class="divhead"><strong>ESTADO:</strong><input maxlength="10" type="text" style="width:130px;height:30px ;background:#FFF;" value="<%=est_aprobacion %>"   readonly="readonly"></div></div>
		<div class="divhead"><strong>TIPO REF:</strong><input type="text"  maxlength="64" style="width:720px;height:30px ;background:#FFF;" readonly="readonly" id="tipref" name="tipref"></div>
	</div>
	</div>
	
	<div class=" cuadroblanco" style="height:310px;margin-top: 5px">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CLIENTE</p></h3>
		<div style="padding: 5px 5px 0px 5px">
	
		<div class="divhead"><strong>RAZON SOCIAL:</strong><input type="text" style="width:600px;height:30px ;background:#FFF;" readonly="readonly" value="<%=CLPR_RAZON_SOCIAL%>"></div>
		<div class="divhead"><strong>RUT:</strong><input type="text" name="empresas_rut" id="empresas_rut" style="width:145px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_rut%>" ></div>
		<div class="divhead"><strong>GIRO:</strong><input type="text" style="width:600px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CLPR_GIRO%>"></div>
		
		<div class="divhead"><strong>DIRECCION:</strong><input type="text" style="width:500px;height:30px ;background:#FFF;" disabled="disabled" value="<%=DIRE_DIRECCION%>"></div>
		<div class="divhead"><strong>COMUNA:</strong><input type="text" style="width:250px;height:30px ;background:#FFF;" disabled="disabled" value="<%=COMU_NOMBRE%>" ></div>		
		<div class="divhead"><strong>CIUDAD:</strong><input type="text" style="width:250px;height:30px ;background:#FFF;" disabled="disabled" value="<%=DIRE_CIUDAD%>" ></div>
		<div class="divhead"><strong>REGION:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" disabled="disabled" value="<%=REGI_NOMBRE%>"></div>
		<div class="divhead"><strong>RESPONSABLE:</strong><input type="text" style="width:400px;height:30px ;background:#FFF;" disabled="disabled" value="<%=FAC_RESPONSABLE%>"></div>
		<div class="divhead"><strong>TIPO CAMBIO:</strong><input id="tipo_cambio"  type="text" style="width:120px;height:30px ;background:#FFF;" readonly="readonly" value="<%=FACT_TIPO_CAMBIO%>"></div>
		<div class="divhead"><strong>CONTACTO:</strong><input type="text" style="width:400px;height:30px ;background:#FFF;" readonly="readonly" value="<%=CONT_NOMBRE%>" id="CONT_NOMBRE" ></div>
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
				<td width="80"><%=ProdDato[0]%>
				<input type="hidden" name="detalle_nc_cod_<%=ProdDato[7]%>"  value="<%=ProdDato[0]%>-<%=ProdDato[5]%>-<%=ProdDato[6]%>">
				<input type="hidden" name="detalle_nc_desc_<%=ProdDato[7]%>"  value="<%=ProdDato[1]%> - <%=ProdDato[3]%> - <%=des%>">
		
				<input type="hidden" name="detalle_nc[]"  value="<%=ProdDato[7]%>;;<%=ProdDato[4]%>"></td>
				<td width="700"><%=ProdDato[1]%> - <%=des%></td>
				<td width="120"><%=ProdDato[3]%></td>
				<td width="100">$<%=monto%><input style="width:100px;height:30px ;background:#FFF;" type="hidden" id="detalle_nc_<%=ProdDato[7]%>" name="detalle_nc_<%=ProdDato[7]%>" value="<%=ProdDato[4]%>" readonly="readonly"></td>
			</tr>
			
       	<% } %>
			</tbody>
		  </table>
	</div>
	<div class=" cuadroblanco" style="height:120px;margin-top: 5px">
	<div style="padding: 5px 5px 0px 5px">
		<div class="divhead"><strong>SUBTOTAL:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;text-align: right;" readonly="readonly" id="subtotal" name="subtotal" value="<%=SUBTOTAL_%>"></div>
		
		<div class="divhead"><strong>MONTO DESCUENTO:</strong><input type="text"  style="width:150px;height:30px ;background:#FFF;text-align: right;"  value="<%=DESCUENTO_ %>"  maxlength="12" readonly="readonly" id="desc" name="desc"></div>
		<div class="divhead" style="width:800px;"><strong>GLOSA DESCUENTO:</strong><input type="text"  style="width:350px;height:30px ;background:#FFF;" maxlength="30"  value="<%=GLOSADESC %>" readonly="readonly" id="glosa_desc" name="glosa_desc"  ></div>
		<div class="divhead"><strong>NETO:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;text-align: right;" readonly="readonly" value="<%=neto %>" id="neto" name="neto" ></div>
		
		<div class="divhead"><strong>IVA 19%:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;text-align: right;" readonly="readonly" id="iva" name="iva" value="<%=iva%>"></div>
		<div class="divhead"><strong>TOTAL:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;text-align: right;" readonly="readonly" id="total" name="total" value="<%=total%>"></div>
		
		</div>
	</div>
	
	
	<div class=" cuadroblanco" style="height:80px;margin-top: 5px">
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>TIPO REF:</strong><input type="text" maxlength="36" style="width:200px;height:30px ;background:#FFF;" value="<%=tipo_dteref%>" readonly="readonly" id="tipo_dteref"></div>
		<div class="divhead"><strong>FOLIO REF:</strong><input type="text" name="folioref" id="folioref" maxlength="36" style="width:410px;height:30px ;background:#FFF;" value="<%=folioref%>" readonly="readonly"></div>
		<div class="divhead"><strong>FECHA REF:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;"  id="datepicker"  readonly="readonly" value="<%=fec_ref%>" ></div>
	</div>
		
	</div>
	<div class=" cuadroblanco" style="height:40px;margin-top: 5px">
	<div style="padding: 5px 5px 0px 5px">
		<div class="divhead"><strong>ESTADO EN BIRT:</strong><input type="text" value="EMITIDA" style="width:200px;height:30px ;background:#FFF;" disabled="disabled" ></div>
		<div class="divhead"><strong>FECHA EMISION:</strong><input type="text" style="width:125px;height:30px ;background:#FFF;" disabled="disabled" value="<%=FACT_FECHA_EMISION%>"></div>
		<div class="divhead"><strong>EMISOR:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;" disabled="disabled" value="<%=USU_INICIAL%>"></div>
	</div>
	</div>
	<div class=" cuadroblanco" style="height:40px;margin-top: 5px;">
		<div style="padding: 5px 5px 5px 5px;">
			<div class="divhead">
			<strong>RAZON NC:</strong><input style="width:850px;height:30px ;background:#FFF;" readonly="readonly" maxlength="75" type="text" id="razon_nc" name="razon_nc" value="<%=razon_nc%>">
			</div>
		</div>
	</div>
	<div class=" cuadroblanco" style="height:40px;margin-top: 5px;">
		<div style="padding: 5px 5px 5px 5px;">
			<div class="divhead">
			<strong>USU APROBACION:</strong><input style="width:300px;height:30px ;background:#FFF;" readonly="readonly" maxlength="75" type="text" value="<%=usuaprobacion%>">
			<strong>FECHA APROBACION:</strong><input style="width:130px;height:30px ;background:#FFF;" readonly="readonly" maxlength="75" type="text" value="<%=fechaaprobacion%>">
			</div>
		</div>
	</div>
	
	<div class=" cuadroblanco" style="height:150px;margin-top: 5px">
	<div style="padding: 5px 5px 0px 5px">
	
		<div class="divhead"><strong>FORMA DE DESPACHO:</strong><input type="text" name="obs1" id="obs1" readonly="readonly" style="width:410px;height:30px ;background:#FFF;" value="<%=factura_obs1%>"></div>
		<div class="divhead"><strong>OBSERVACION:</strong><input type="text" name="obs2" id="obs2" readonly="readonly" style="width:460px;height:30px ;background:#FFF;" value="<%=factura_obs2%>"></div>
		<div class="divhead"><strong>OTRAS OBS:</strong><input type="text" style="width:450px;height:30px ;background:#FFF;" id="ref" readonly="readonly" value="<%=FACT_OBS%>"></div>
		<div class="bgrabar">
		
		
		 <%   
			
        for(int i =0; i<ar_productos.length; i++){
       	 String[] ProdDato = ar_productos[i].split("/");
       	 String monto = nf.format(Integer.parseInt(ProdDato[4])).replace(",",".");
       	 if(ProdDato[2].length()>35){
       		 des = ProdDato[2].substring(0,36);
       	 }else{
       		 des = ProdDato[2];
       	 }
       	 %>
       		
			<input type="hidden" name="detalle_prev[]" id="detalle_prev_id_<%=ProdDato[7]%>" >
			
       	<% } %>
		
		<input type="hidden" name="total_prev" id="total_prev" value="0">
			<input type="hidden" name="iva_prev" id="iva_prev" value="0">
			<input type="hidden" name="neto_prev" id="neto_prev" value="0">
			<input type="hidden" name="dir_prev" id="dir_prev" value="<%=DIRE_DIRECCION%>">
			<input type="hidden" name="obs_prev" id="obs_prev" value="">
			<input type="hidden" name="obs_prev2" id="obs_prev2" value="">
			<input type="hidden" name="obs_prev3" id="obs_prev3" value="">
			<input type="hidden" name="obs_prev4" id="obs_prev4" value="">
			<input type="hidden" name="rut_prev" id="rut_prev">
			<input type="hidden" name="giro_prev" id="giro_prev" value="<%=CLPR_GIRO%>">
			<input type="hidden" name="dsco_prev" id="dsco_prev" value="0">
			<input type="hidden" name="tipodte_prev" id="tipodte_prev">
		<input type="hidden" value="<%=FACT_FECHA%>" name="fec_prev" id="fec_prev">
			<input type="hidden" value="<%=CLPR_RAZON_SOCIAL%>" name="rz_prev">
			<input type="hidden" value="<%=COMU_NOMBRE%>" name="comuna_prev">
			<input type="hidden" value="<%=DIRE_CIUDAD%>" name="ciudad_prev">
			<input type="hidden" value="<%=CONT_TELEFONO%>" name="telefono_prev">
			<input type="hidden" name="ref_prev" id="ref_prev" value="">
		<input type="hidden" name="tipo_doc_titulo_prev" id="tipo_doc_titulo_prev" value="NOTA DE CREDITO ELECTRONICA">
		
		<button type="submit" name="previ" id="previ" class="btn btn-success" onclick="previsualizar()"  >PREVISUALIZAR</button> 
          
          
       </div> 
       </div>
	</div>
	<div class=" cuadroblanco tf2" style="height:80px;margin-top: 5px;display: none;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead">
		<strong>DONDE DICE:</strong><input readonly="readonly" style="width:850px;height:30px ;background:#FFF;" maxlength="75" type="text" id="nc_dondedice" name="nc_dondedice" value="<%=nc_dondedice %>">
		<strong>DEBE DECIR:</strong><input readonly="readonly" style="width:850px;height:30px ;background:#FFF;" maxlength="75" type="text" id="nc_debedecir" name="nc_debedecir" value="<%=nc_debedecir %>">
		</div>
	</div>
		
	</div>
</form>
</div><br><br>
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


