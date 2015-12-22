<%@page import="VO.ActivoVO"%>
<%@page import="VO.UbicacionVO"%>
<%@page import="VO.EstructuraCobroVO"%>
<%@page import="VO.AnexoContratoVO"%>
<%@page import="VO.PeriodoTcVO"%>
<%@page import="VO.DireccionVO"%>
<%@page import="VO.EmpresaVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="VO.MaquinaContadorVO"%>
<% 	
String modname=(String)request.getAttribute("modname");
String Usu_nom=(String)request.getAttribute("usuname");	

String minyear=(String)request.getAttribute("minyear");
String minmes=(String)request.getAttribute("minmes");
String maxyear=(String)request.getAttribute("maxyear");
String maxmes=(String)request.getAttribute("maxmes");
String condiciones=(String)request.getAttribute("condiciones");

String desc=(String)request.getAttribute("desc");
String glosa_desc=(String)request.getAttribute("glosa_desc");
if(condiciones==null) condiciones="";

String obs=(String)request.getAttribute("obs");
if(obs==null) obs="";


String[] ubicaciones=(String[])request.getAttribute("ubicaciones");


String tipo_cambio_id=(String)request.getAttribute("tipo_cambio_id");
String tcambio=(String)request.getAttribute("tcambio");

if(tipo_cambio_id==null) tipo_cambio_id="";
if(tcambio==null) tcambio="";

ArrayList<EmpresaVO> emisores =(ArrayList<EmpresaVO>) request.getAttribute("emisores");
ArrayList<DireccionVO> direcciones =(ArrayList<DireccionVO>) request.getAttribute("direcciones");
ArrayList<PeriodoTcVO> periodostc =(ArrayList<PeriodoTcVO>) request.getAttribute("periodostc");

ArrayList<String> tipocambios = (ArrayList<String>)request.getAttribute("tipocambios");

String fecha=(String)request.getAttribute("fecha");

String empresas_giro=(String)request.getAttribute("empresas_giro");

EmpresaVO emprempSeleccionadaesas_giro=(EmpresaVO)request.getAttribute("empSeleccionada");


String[] ar_periodos =(String[]) request.getAttribute("ar_periodos");
String[] periodos_para_tc=(String[])request.getAttribute("periodos_para_tc");
String[] periodosfechas_para_tc=(String[])request.getAttribute("periodosfechas_para_tc");


String[] arActivos =(String[]) request.getAttribute("ar_activos");


ArrayList<String> cont0_activo =(ArrayList<String>) request.getAttribute("cont0_activo");
ArrayList<String> cont0_values =(ArrayList<String>) request.getAttribute("cont0_values");
ArrayList<String> cont0_difs =(ArrayList<String>) request.getAttribute("cont0_difs");

ArrayList<String> cont1_activo =(ArrayList<String>) request.getAttribute("cont1_activo");
ArrayList<String> cont1_values =(ArrayList<String>) request.getAttribute("cont1_values");
ArrayList<String> cont1_difs =(ArrayList<String>) request.getAttribute("cont1_difs");

ArrayList<String> cont2_activo =(ArrayList<String>) request.getAttribute("cont2_activo");
ArrayList<String> cont2_values =(ArrayList<String>) request.getAttribute("cont2_values");
ArrayList<String> cont2_difs =(ArrayList<String>) request.getAttribute("cont2_difs");

ArrayList<String> cont3_activo =(ArrayList<String>) request.getAttribute("cont3_activo");
ArrayList<String> cont3_values =(ArrayList<String>) request.getAttribute("cont3_values");
ArrayList<String> cont3_difs =(ArrayList<String>) request.getAttribute("cont3_difs");

ArrayList<String> cont4_activo =(ArrayList<String>) request.getAttribute("cont4_activo");
ArrayList<String> cont4_values =(ArrayList<String>) request.getAttribute("cont4_values");
ArrayList<String> cont4_difs =(ArrayList<String>) request.getAttribute("cont4_difs");

ArrayList<String> cont5_activo =(ArrayList<String>) request.getAttribute("cont5_activo");
ArrayList<String> cont5_values =(ArrayList<String>) request.getAttribute("cont5_values");
ArrayList<String> cont5_difs =(ArrayList<String>) request.getAttribute("cont5_difs");

ArrayList<String> cont6_activo =(ArrayList<String>) request.getAttribute("cont6_activo");
ArrayList<String> cont6_values =(ArrayList<String>) request.getAttribute("cont6_values");
ArrayList<String> cont6_difs =(ArrayList<String>) request.getAttribute("cont6_difs");



ArrayList<AnexoContratoVO> anexosContratos =(ArrayList<AnexoContratoVO>) request.getAttribute("anexosContratos");


String peri_tc_correlativo_actual=null;
String peri_tc_fechad_actual=null;
String peri_tc_fechah_actual=null;

String id_per=(String)request.getAttribute("id_per");


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

  	<script src="lib/jquery-ui/js/jquery-1.10.2.js"></script>
    <link rel="stylesheet" href="lib/jquery-ui/css/ui-lightness/jquery-ui-1.10.4.custom.css">
    <script src="lib/jquery-ui/js/jquery-ui-1.10.4.custom.js"></script>

	<link href="//cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/css/select2.min.css" rel="stylesheet" />
	<script src="//cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/js/select2.min.js"></script>
  
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
	  
	    height: 205px;
	    overflow-y: scroll;
	}
	thead, tbody {
		 min-width:100%;
		 display: inline-block;
    	
    	
	}
	select{
			
				}
	.hov>td{
		padding-left: 2px;
		padding-right: 2px;
		padding-top: 1px;
		padding-bottom: 1px;
		width: 116px;
		
	}
	.inputDetail{
		margin-bottom: 0px !important;
		text-align: right;
		width: 112px;
	}
	td.detailID{
		width: 70px;
	}
	td.detailActivo{
		width: 300px;
	}
	td.checkUbi{
		width: 30px;
	}
		
		
	@media screen and (min-width: 700px) {
	.cuadroblanco{
			box-shadow:0px 2px 5px 0px rgba(50, 50, 50, 0.75);
			border: 1px solid black;
			min-height:100%; height:330px;  
		min-width: 350px;
			max-width:1100px;
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
    
    
    <script type="text/javascript">
    

    ////---------definimos array de direcciones ------------/////  
    
    var direcciones =[
                      <% for(DireccionVO dire: direcciones){ %>
                      	{id:'<%=dire.getId() %>',ciudad:'<%=dire.getCiudad() %>',comuna:'<%=dire.getComu_nombre() %>', region:'<%=dire.getRegi_nombre() %>' },
                      <% } %>
                      
                      ];
    
    $( document ).ready(function() {
    	
    	$('#dire_id').select2();
    	$('#id_per').select2();
    	
        // Handler for .ready() called.
		$( "#datepicker" ).datepicker({ dateFormat: "dd-mm-yy", monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
                                      monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
                                      dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
                                      dayNamesShort: ['Dom','Lun','Mar','Mie','Juv','Vie','Sab'],
                                      dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa'],
                                      
                                      });
	
	
	
	
		$('#dire_id').on('change', function() {
	        var dire_seleccionada= $(this).val();
	        
	  	   for(var ii=0; ii< direcciones.length;ii++){
	           
	           var d_ar =direcciones[ii];
	           if(d_ar.id==dire_seleccionada){
	         	  
	         	 	$('#regi').val(d_ar.region);
	         	 	$('#ciudad').val(d_ar.ciudad);
	         		$('#comuna').val(d_ar.comuna);
	         	
	             break;
	           }
	         
	         }
	            
			 });
    
		$('#dire_id').change();
	
		$('#tcambio').on('blur', function() {
			if($('#tcambio').val()==""){limpiarValores();}
			else{
				var subtotal = Number($('#subtotal_').val())*Number($('#tcambio').val());
				$('#subtotal').val(Math.round(subtotal));
				calcTotal();
		        	
			}
			    
			 });
		$('#desc').on('blur', function() {
			if($('#desc').val()==""){$('#desc').val("0");}
				calcTotal();
		});
		
		
		
		$('#tcambio').blur();
		
		$('.ubicaciones_checks').on('change', function() {
			
			$('#grabar').hide();
			 });
		
		if($( ".ubicaciones_checks:checked" ).length==0){
			$('#grabar').hide();
		}
	    
	    });
    	
    	function limpiarValores(){
    		$('#neto').val("");
    		$('#iva').val("");
    		$('#total').val("");
    	}
    
    	function calcTotal(){
    		var neto = Number($('#subtotal').val())-Number($('#desc').val());
    		$('#neto').val(Math.round(neto));
    		
    		var iva=neto*0.19;
    		$('#iva').val(Math.round(iva));
    		
    		var total_=neto+iva;
    		$('#total').val(Math.round(total_));
    		
    		
    		
    	}
    	      
        function validateNum(event) {
            var key = window.event ? event.keyCode : event.which;
			//alert(event.keyCode);
            if (event.keyCode == 8 || event.keyCode == 46
             || event.keyCode == 37 || event.keyCode == 39 || event.keyCode == 9 || event.keyCode == 190 || (key >= 96 && key <= 105)) {
                return true;
            }
            else if ( key < 48 || key > 57 ) {
                return false;
            }
            else return true;
        };
        
        var submitting;
        function disableSubmitB(){
        	submitting=true;
        	$("#load").show();
        	$("#grabar").hide();
        	//document.getElementById('grabar').disabled = true;
        	document.getElementById('grabar').value = "ESPERA";
        	$("#previ").hide();
        	
        	
        }
		function enableSubmitB(){
			submitting=false;
        	$("#load").hide();
        	$("#grabar").show();
        	//document.getElementById('grabar').disabled = false;
        	document.getElementById('grabar').value = "GRABAR";
        	
        	$("#previ").show();
        }
        function validateSubmit(){
        	
        	
        	$("#form1").attr("action", "");
        	
        	
        	$('#dir_direccion').val($("#dire_id option:selected").text());
        	var validate= true;
        	var msg="ERRORES: \n";
        	

        	if(document.getElementById('desc').value=="" || document.getElementById('desc').value=="0"){
        		$('#desc').val("0");
        		if(document.getElementById('glosa_desc').value!=""){
        			msg+="DEBE INGRESAR UN DESCUENTO PARA LA GLOSA DE DESCUENTO\n";
            		validate=false;
        		}
        	}
        	else if(document.getElementById('glosa_desc').value==""){
        		msg+="DEBE INGRESAR LA GLOSA DE DESCUENTO\n";
        		validate=false;
        	}
        	if(document.getElementById('obs').value==""){
        		msg+="DEBE INGRESAR LA OBSERVACION\n";
        		validate=false;
        	}
        	if(document.getElementById('n_impresiones').value==""){
        		msg+="DEBE INGRESAR EL NUMERO DE IMPRESIONES\n";
        		validate=false;
        	}
        	if(document.getElementById('subtotal').value==""){
        		msg+="DEBE INGRESAR EL SUBTOTAL DE FACTURA\n";
        		validate=false;
        	}
        	if(Number(document.getElementById('subtotal').value)<0){
        		msg+="DEBE INGRESAR EL SUBTOTAL MAYOR A 0\n";
        		validate=false;
        	}
        	
        	if(Number(document.getElementById('total').value)<0){
        		msg+="DEBE INGRESAR EL TOTAL MAYOR A 0\n";
        		validate=false;
        	}
        	
        	if(document.getElementById('datepicker').value==""){
        		msg+="DEBE INGRESAR FECHA\n";
        		validate=false;
        	}
        	
        	if(document.getElementById('condiciones').value==""){
        		msg+="DEBE INGRESAR CONDICIONES DE FACTURA\n";
        		validate=false;
        	}
        	if(document.getElementById('id_per').value==""){
        		msg+="DEBE INGRESAR UN PERIODO DE FACTURACION\n";
        		validate=false;
        	}
        	if(document.getElementById('tcambio').value==""){
        		msg+="DEBE INGRESAR UN TIPO CAMBIO\n";
        		validate=false;
        	}
        	        	
        	if(validate){
        		if(submitting){
        			alert("INFORMACION YA ENVIADA");
        		}else{
        			disableSubmitB();
                	
            		return true;	
        		}
        		
        	}
        	else{
        		enableSubmitB();
        		alert(msg);
        		return false;
        	}
        	
        	
        }
        
        function validateCalclTotal(){
        	if($('#id_per').val()==""){
        		alert('DEBE SELECCIONAR UN PERIODO');
        		return false; 
        	}
        	if($('#tipo_cambio_id').val()==""){
        		alert('DEBE SELECCIONAR UN TIPO CAMBIO');
        		return false; 
        	}
        	
        	if($('#tcambio').val()==""){
        		alert('DEBE INGRESAR UN TIPO CAMBIO');
        		return false; 
        	}
        	return true;
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
	  <p>N703.M.02.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESI�N</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/703/I01'">VOLVER</button> 
	<div align="center" class="title">
		<p>MODIFICAR <%=modname %></p>
	</div>
</div>
<div class="content" >
  <form action="" name="form1" method="post" id="form1" > 
  <input type="hidden" name="empresas_id" value="${empresas_id}">
  <input type="hidden" name="dir_direccion" id="dir_direccion" value="">
  
	<div class=" cuadroblanco" style="height:50px;padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>FECHA:</strong><input type="text" style="width:130px;height:30px ;background:#FF3;" class="amarillo"  name="fecha" id="datepicker" value="<%=fecha %>" readonly="readonly"></div>
		<div class="divhead"><strong>COND PAGO:</strong><input name="condiciones" id="condiciones" type="text" maxlength="30" style="width:350px;height:30px ;background:#FF3;" class="amarillo" value="<%=condiciones%>"><span class="cabecera" style="color:#F00">*</span></div>
		
		<div class="divhead"><strong>EMISOR:</strong><select name="id_emisor" id="id_emisor" style="margin:0px 0px 0px 0px;" >
				<% for(EmpresaVO emisor:emisores){ %>
				<option value="<%=emisor.getEmpresas_id()%>"><%=emisor.getEmpresas_nombrenof() %></option>
			<% } %>
    		</select><span class="cabecera" style="color:#F00">*</span></div>
		
		
		
	</div>

	<div class=" cuadroblanco" style="height:275px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CLIENTE</p></h3>
		<div style="padding: 5px 5px 5px 5px">
		
		<div class="divhead"><strong>RUT:</strong><input type="text" name="empresas_rut" id="empresas_rut" style="width:145px;height:30px ;background:#FFF;" readonly="readonly" value="${empSeleccionada.getEmpresas_rut()}" ><input type="hidden"  name="rut_prev"  id="rut_prev"></div>
		<div class="divhead"><strong>COD CLIENTE:</strong><input type="text" name="cod_cliente" id="cod_cliente" style="width:70px;height:30px ;background:#FFF;" readonly="readonly" value="${empSeleccionada.getEmpresas_id()}" ></div>
		<div class="divhead"><strong>RAZON SOCIAL:</strong><input type="text" name="rz_cliente" id="rz_cliente" style="width:500px;height:30px ;background:#FFF;" readonly="readonly" value="${empSeleccionada.getEmpresas_razonsocial()}"></div>
		<div class="divhead"><strong>DIRECCION:</strong><select name="dire_id" id="dire_id" style="margin:0px 0px 0px 0px;width: 700px">
				<% for(DireccionVO dire:direcciones){
				%>
				<option value="<%=dire.getId()%>"><%=dire.getDireccion() %></option>
			<% } %>
    		</select><span class="cabecera" style="color:#F00">*</span></div>
		<div class="divhead"><strong>REGION:</strong><input type="text" id="regi" name="regi" style="width:510px;height:30px ;background:#FFF;" readonly="readonly" value=""></div>
		<div class="divhead"><strong>CIUDAD:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" id="ciudad" name="ciudad" readonly="readonly" value="" ><input type="hidden"  name="ciudad_prev" id="ciudad_prev"></div>
		<div class="divhead"><strong>COMUNA:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" id="comuna" name="comuna" readonly="readonly" value="" ><input type="hidden"  name="comuna_prev" id="comuna_prev"></div>
		<div class="divhead"><strong>RESPONSABLE CUENTA:</strong><input type="text" id="resp" name="resp" style="width:350px;height:30px ;background:#FFF;" readonly="readonly" value=""></div>
		<div class="divhead"><strong>PER FACT.:</strong><select name="id_per" id="id_per" style="margin:0px 0px 0px 0px;width: 325px" >
		<option value="" >SELECCIONAR</option>
					<% for(PeriodoTcVO periodotc:periodostc){
						%>
					<option value="<%=periodotc.getId()%>" <% if(id_per!=null && id_per.equals(periodotc.getId())){%> selected="selected" <%} %>><%=periodotc.getCorrelativo()+" "+periodotc.getFdesde()+" AL "+periodotc.getFhasta() %></option>
				<% 
				if(id_per!=null && id_per.equals(periodotc.getId())){
					peri_tc_correlativo_actual=periodotc.getCorrelativo();
					peri_tc_fechad_actual=periodotc.getFdesde();
					peri_tc_fechah_actual=periodotc.getFhasta();
				}
				} %>
				</select><span class="cabecera" style="color:#F00">*</span></div>
	<div class="divhead"><strong>TIPO CAMBIO:</strong><select name="tipo_cambio_id" id="tipo_cambio_id">
										<option value="" >SELECCIONAR</option>
    									<% for(String tipoCam:tipocambios){
    											String tipc_ar[]=tipoCam.split(";;");
    										%>
    										<option value="<%=tipc_ar[0] %>" <%if(tipo_cambio_id.equals(tipc_ar[0])){%> selected="selected" <% } %> ><%=tipc_ar[1] %></option>	
    									<% } %>
    									
    								
    								</select></div>
	<div class="divhead"><strong>TIPO CAMBIO:</strong><input type="text" name="tcambio" id="tcambio" class="amarillo" maxlength="9" style="width:120px;height:30px ;background:#FF3;text-align: right;" value="<%=tcambio %>" onkeydown="return validateNum(event)"><span class="cabecera" style="color:#F00">*</span></div>				
	</div>
	
	</div>
	
	<div class=" cuadroblanco" style="height:335px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS SERVICIOS IMPRESION</p></h3>		
		
		<div style="padding: 5px 5px 5px 5px;" class="divscroll scroll">
		
		<%
		 int totalImp=0; double total=0;
		if(id_per!=null){%>
			<table class="table" style="margin-left:0px; ">
		
		<thead style="border: 1px solid black;background-color:#0101DF">
		<tr style="background-color:#0101DF;color:#FFFFFF">
			<td class="checkUbi" style="font-size:20px">&nbsp;</td>
			<td class="detailID" style="font-size:20px"><strong>ID</strong></td>
			<td class="detailActivo" style="font-size:20px"><strong>ACTIVO</strong></td>
			<td width="116px" style="font-size:20px"><% if(periodos_para_tc[4]!=null){%> <strong>P<%=periodos_para_tc[4] %></strong><br><%=periodosfechas_para_tc[4] %> <% }else{%> ND <% } %></td> 
			<td width="116px" style="font-size:20px"><% if(periodos_para_tc[5]!=null){%> <strong>P<%=periodos_para_tc[5] %></strong><br><%=periodosfechas_para_tc[5] %> <% } else{%> ND <% } %></td>
			<td width="116px" style="font-size:20px"><strong>P<%=peri_tc_correlativo_actual %></strong><br><%=peri_tc_fechad_actual %> <%=peri_tc_fechah_actual %></td>
			<td width="116px" style="font-size:20px"><strong>C.FIJO</strong></td>
			<td width="116px" style="font-size:20px"><strong>C.VARIABLE</strong></td>
		</tr>
		</thead>
		<tbody id="fbody" class="scroll" style="border: 1px solid black">
		
		 <%       
		 		String ubi_ant="";
		
		         for(int i =0; i<arActivos.length; i++){
		        	 
		        	
		        	 
		        	 String[] Activos = arActivos[i].split(";;");
		        	
		        	 boolean selected=false;
		        	 if(ubicaciones!=null)for(int ii =0; ii<ubicaciones.length;ii++){
			        		if(ubicaciones[ii].equals(Activos[7])){
			        			selected=true;
			        			break;
			        		}
			        	}
		        	 //buscamos cual es el costo fijo y variable para este activo 
		        	 String CF="0";
		        	 String CV="0";
		        	 
		        	 for(AnexoContratoVO anexo : anexosContratos){
		        		 for(EstructuraCobroVO estructura : anexo.getEstructurasCobro()){
		        			 for(MaquinaContadorVO maq: estructura.getMaquinasContador()){
		        				 if(maq.getId_activo().equals(Activos[0]) && estructura.getEstrcobro_id().equals(Activos[9]) && maq.getUbi_id().equals(Activos[7])){
		        						if(maq.getCosto_fijo()!=null)CF=maq.getCosto_fijo();
		        						if(maq.getCosto_variable()!=null)CV=maq.getCosto_variable();
		        					 }
		        				 
		        			 }
		        		 }
		        	 }
		        	
		        	 if(selected){
		        		 total+=(Double.parseDouble(CF)+Double.parseDouble(CV)) ;
		        		 
		        	 }
		        
		        	if(!ubi_ant.equals(Activos[3]+" - "+Activos[2])){
		        		
		        	%>
		        	<tr class="hov" >
						<td class="checkUbi"><input type="checkbox" name="ubicaciones[]" class="ubicaciones_checks" value="<%=Activos[7]%>" <%if(selected){%> checked="checked" <%} %>></td>
						<td colspan="8"><strong><%=Activos[3]%> - <%=Activos[2]%></strong></td>
						
					</tr>
		        	<% 
		        	}ubi_ant=Activos[3]+" - "+Activos[2];
		        	%>
		        	
					<tr class="hov" >
						<td class="checkUbi">&nbsp;</td>
						<td class="detailID" ><%=Activos[0]%> <input type="hidden" name="activosdetail[]" value="<%=Activos[0]%>" />
																<input type="hidden" name="ubisdetail[]" value="<%=Activos[7]%>" />
																<input type="hidden" name="estrucdetail[]" value="<%=Activos[9]%>" />
																<input type="hidden" name="anexo_detail[]" value="<%=Activos[8]%>" /></td>
						<td class="detailActivo"><%=Activos[1]%> - <%=Activos[5]%></td>
						<td><input type="text" class="inputDetail" name="p5" <% if(periodos_para_tc[4]!=null){if(cont4_activo.indexOf(Activos[0]+";;"+Activos[6])!=-1){%> value="<%=cont4_values.get(cont4_activo.indexOf(Activos[0]+";;"+Activos[6])) %>" <%}else{%> value="0" <% } %> <% } else{%> value="ND" <% } %> style="height:30px ;" disabled></td>
						<td><input type="text" class="inputDetail" id="p6_<%=Activos[0] %>;<%=Activos[6] %>" name="p6" <% if(periodos_para_tc[5]!=null){if(cont5_activo.indexOf(Activos[0]+";;"+Activos[6])!=-1){%> value="<%=cont5_values.get(cont5_activo.indexOf(Activos[0]+";;"+Activos[6])) %>" <%}else{%> value="0" <% } %> <% } else{%> value="ND" <% } %> style="height:30px ;" disabled></td>
						<td><input type="text" class="inputDetail" id="p7" name="p7_<%=Activos[0] %>;<%=Activos[6] %>" maxlength="8" <% if(cont6_activo.indexOf(Activos[0]+";;"+Activos[6])!=-1){%> value="<%=cont6_values.get(cont6_activo.indexOf(Activos[0]+";;"+Activos[6])) %>" <%}else{%> value="0" <% } %>   style="height:30px ;background:#FFF;" disabled="disabled"    ></td>
						<td><input type="text" class="inputDetail" id="p7" name="detallePreciosCF[]" maxlength="8" style="height:30px ;background:#FFF;" readonly="readonly" value="<%=CF %>"  ></td>
						<td><input type="text" class="inputDetail" id="p7" name="detallePreciosCV[]" maxlength="8" style="height:30px ;background:#FFF;" readonly="readonly" value="<%=CV %>"  ></td>
						
					</tr>
					<tr class="hov" style="border-bottom: 3px solid black; ">
						<td class="checkUbi">&nbsp;</td>
						<td class="detailID" ></td>
						<td class="detailActivo" >ESTRUCTURA: <a target="_blank" href="/013/C03?estr=<%=Activos[9]%>"><%=Activos[9]%></a></td>
						<td><input type="text" class="inputDetail" <% if(periodos_para_tc[4]!=null){if(cont4_activo.indexOf(Activos[0]+";;"+Activos[6])!=-1){%> value="<%=cont4_difs.get(cont4_activo.indexOf(Activos[0]+";;"+Activos[6])) %>" <%}else{%> value="0" <% } %> <% } else{%> value="ND" <% } %> style="height:30px ;" disabled></td>
						<td><input type="text" class="inputDetail" <% if(periodos_para_tc[5]!=null){if(cont5_activo.indexOf(Activos[0]+";;"+Activos[6])!=-1){%> value="<%=cont5_difs.get(cont5_activo.indexOf(Activos[0]+";;"+Activos[6])) %>" <%}else{%> value="0" <% } %> <% } else{%> value="ND" <% } %> style="height:30px ;" disabled></td>
						<td><input type="text" class="inputDetail" name="detalleImps[]" <% if(cont6_activo.indexOf(Activos[0]+";;"+Activos[6])!=-1 && selected){ totalImp+=Integer.parseInt(cont6_difs.get(cont6_activo.indexOf(Activos[0]+";;"+Activos[6])));%> value="<%=cont6_difs.get(cont6_activo.indexOf(Activos[0]+";;"+Activos[6])) %>" <%}else{%> value="0" <% } %> style="height:30px ;" readonly="readonly"></td>
						<td >&nbsp;</td>
						<td >&nbsp;</td>
						
					</tr>
		        	<% } 
		        	
		         for(AnexoContratoVO anexo : anexosContratos){
		        	 
		        	 if(anexo.getCargoFijoUnico()!=null){
		        		 //dibujamos linea 
		        		 total+=(Double.parseDouble(anexo.getCargoFijoUnico()) );
		        		 %>
        				 <tr class="hov" style="border-bottom: 1px solid black; ">
        				 	<td class="checkUbi">&nbsp;<input type="hidden" name="anexosdetail[]" value="<%=anexo.getAnc_id() %>"/></td>
		        			<td class="detailID" ><%=anexo.getAnc_id() %></td>
							<td class="detailActivo">ANEXO CONTRATO</td>
							<td colspan="3"><%=anexo.getObservacion()%><input type="hidden" name="anexos_obs_detail[]" value="<%=anexo.getObservacion() %>"/></td>
							<td><input type="text" class="inputDetail" id="p7" name="detallePreciosAnexoCF[]" maxlength="8" style="height:30px ;background:#FFF;" readonly="readonly" value="<%=anexo.getCargoFijoUnico() %>"  ></td>
							<td><input type="text" class="inputDetail" id="p7" maxlength="8" style="height:30px ;background:#FFF;" readonly="readonly" value="0"  ></td>
						</tr>
        				 <%
		        	 }
		        	 
	        		 for(EstructuraCobroVO estructura : anexo.getEstructurasCobro()){
	        			 if(estructura.getCostoFijo()!=null){
	        				 //dibujamos linea 
	        				  total+=(Double.parseDouble(estructura.getCostoFijo()) );
	        				 %>
	        				 <tr class="hov" style="border-bottom: 1px solid black; ">
	        					<td class="checkUbi">&nbsp;<input type="hidden" name="estrdetail[]" value="<%=estructura.getEstrcobro_id() %>"/>
	        												<input type="hidden" name="anexos_estrdetail[]" value="<%=estructura.getAnc_id() %>"/></td>
			        			<td class="detailID" ><%=estructura.getEstrcobro_id() %></td>
								<td class="detailActivo">ESTRUCTURA DE COBRO</td>
								<td colspan="3"><%=estructura.getEstrcobro_nombre()%><input type="hidden" name="estr_obs_detail[]" value="<%=estructura.getEstrcobro_nombre() %>"/></td>
								<td><input type="text" class="inputDetail" name="detallePreciosEstrCF[]" maxlength="8" style="height:30px ;background:#FFF;" readonly="readonly" value="<%=estructura.getCostoFijo() %>"  ></td>
								<td><input type="text" class="inputDetail" name="detallePreciosEstrCV[]" maxlength="8" style="height:30px ;background:#FFF;" readonly="readonly" value="<%=estructura.getCostoVariable() %>"  ></td>
							</tr>
	        				 <%
	        			 }
	        		 }
	        	 }
		        	
		        	%>
		        	</tbody>
		</table>
		<%} %>
		
	
	
	
	
		
		</div>
	</div>
	
	<%if(ubicaciones!=null && ubicaciones.length>0){%>
		<div class=" cuadroblanco" style="height:120px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>SUBTOTAL:</strong><input name="subtotal" id="subtotal" type="text" style="width:150px;height:30px ;background:#FFF;" value="0"  readonly="readonly"><input id="subtotal_" type="hidden" value="<%=total %>"  readonly="readonly"></div>
		<div class="divhead"><strong>MONTO DESCUENTO:</strong><input type="text" name="desc"  id="desc" onkeydown="return validateNum(event)" class="amarillo" style="width:150px;height:30px ;background:#FF3;"  value="<%=desc %>"  maxlength="12" ></div>
		<div class="divhead"><strong>GLOSA DESCUENTO:</strong><input type="text" name="glosa_desc"  id="glosa_desc" class="amarillo" style="width:350px;height:30px ;background:#FF3;" maxlength="30"  value="<%=glosa_desc %>"  ></div>
		<div class="divhead"><strong id="netolabel">AFECTO:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;" readonly="readonly" value="" id="neto" name="neto"></div>
		
		<div class="divhead"><strong>IVA 19%:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;" readonly="readonly" value="" id="iva" name="iva"></div>
		<div class="divhead"><strong>TOTAL:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" readonly="readonly" value="" id="total" name="total"></div>
		
	</div>
	</div>
	<% } %>
	
	
	<div class=" cuadroblanco" style="height:90px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>N DE IMPRESIONES:</strong><input type="text" name="n_impresiones" id="n_impresiones" onkeydown="return validateNum(event)" class="amarillo" maxlength="10" style="width:130px;height:30px ;background:#FFF;" value="<%=totalImp%>" readonly="readonly"></div>
		
		<div class="divhead"><strong>OBSERVACIONES:</strong><input type="text" name="obs" id="obs" class="amarillo" maxlength="64" style="width:720px;height:30px ;background:#FF3;" value="<%=obs%>"><span class="cabecera" style="color:#F00">*</span></div>
	</div>
		
	</div>
	
	<div class=" cuadroblanco" style="height:45px;margin-top: 5px;">
		<div class="bgrabar">
		<button type="submit" name="calcTotal" id="calcTotal" class="btn btn-success" onclick="return validateCalclTotal()" >CALCULAR TOTAL</button>
		  <button type="submit" name="grabar" id="grabar" class="btn btn-success" onclick="return validateSubmit()" >GRABAR</button>
          <img alt="" src="images/loading.GIF" style="height: 30px;display: none" id="load">
       </div> 
	</div>
</form>
</div><br><br><br>
<div class="footerapp">               
	<p style="float: left;"><i class="icon-user"></i>	
	<strong><%=Usu_nom %></strong></p>
	<button type="button" onclick="window.open('/003/','_blank')" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:2px;">MENU</button>
</div>
    
  </body>
</html>


