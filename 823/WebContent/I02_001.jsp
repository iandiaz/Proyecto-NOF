<%@page import="VO.ContactoVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 	
String minyear=(String)request.getAttribute("minyear");
String minmes=(String)request.getAttribute("minmes");
String maxyear=(String)request.getAttribute("maxyear");
String maxmes=(String)request.getAttribute("maxmes");

String empresas_id=(String)request.getAttribute("empresas_id");
if(empresas_id==null )empresas_id="";
String contactos_id="";

String did =(String)request.getAttribute("did");


String nom1 =(String)request.getAttribute("nom1");
String rut1 =(String)request.getAttribute("rut1");
String id1 =(String)request.getAttribute("id1");
String dir1 =(String)request.getAttribute("dir1");
String reg1 =(String)request.getAttribute("reg1");
String com1 =(String)request.getAttribute("com1");
String cui1 =(String)request.getAttribute("cui1");

String nom2 =(String)request.getAttribute("nom2");
String rut2 =(String)request.getAttribute("rut2");
String id2 =(String)request.getAttribute("id2");
String dir2 =(String)request.getAttribute("dir2");
String reg2 =(String)request.getAttribute("reg2");
String com2 =(String)request.getAttribute("com2");
String cui2 =(String)request.getAttribute("cui2");
String RESPONSABLE =(String)request.getAttribute("RESPONSABLE");

String CONT_NOMBRE =(String)request.getAttribute("CONT_NOMBRE");
String CONT_TELEFONO =(String)request.getAttribute("CONT_TELEFONO");
String CONT_TELEFONOC =(String)request.getAttribute("CONT_TELEFONOC");
String fecha =(String)request.getAttribute("fecha");

String Usu_nom=(String)request.getAttribute("usuname");

String[] ar_empresas =(String[]) request.getAttribute("ar_empresas");
ArrayList<ContactoVO> contactos =(ArrayList<ContactoVO>) request.getAttribute("contactos");
String[] ar_guias =(String[]) request.getAttribute("ar_guias");


int numguias =0;

if(ar_guias!=null && ar_guias.length>0) numguias= ar_guias.length;

String condiciones=(String)request.getAttribute("condiciones");
String obs=(String)request.getAttribute("obs");
String ref=(String)request.getAttribute("ref");


if(condiciones==null) condiciones="";
if(obs==null) obs="";
if(ref==null) ref="";

String o_id=(String)request.getAttribute("o_id");
String d_id=(String)request.getAttribute("d_id");

String clientes_id =(String)request.getAttribute("clientes_id");
////////////////DATOS DE ORIGEN /////////////////////


String ORIGEN_DIRE_DIRECCION =(String)request.getAttribute("ORIGEN_DIRE_DIRECCION");
String ORIGEN_empresas_razonsocial =(String)request.getAttribute("ORIGEN_empresas_razonsocial");
String ORIGEN_empresas_id =(String)request.getAttribute("ORIGEN_empresas_id");
String ORIGEN_empresas_rut =(String)request.getAttribute("ORIGEN_empresas_rut");
String ORIGEN_DIRE_CIUDAD =(String)request.getAttribute("ORIGEN_DIRE_CIUDAD");
String ORIGEN_COMU_NOMBRE =(String)request.getAttribute("ORIGEN_COMU_NOMBRE");
String ORIGEN_REGI_NOMBRE =(String)request.getAttribute("ORIGEN_REGI_NOMBRE");

////////////////DATOS DE DESTINO /////////////////////


String DESTINO_DIRE_DIRECCION =(String)request.getAttribute("DESTINO_DIRE_DIRECCION");
String DESTINO_empresas_razonsocial =(String)request.getAttribute("DESTINO_empresas_razonsocial");
String DESTINO_empresas_id =(String)request.getAttribute("DESTINO_empresas_id");
String DESTINO_empresas_rut =(String)request.getAttribute("DESTINO_empresas_rut");
String DESTINO_DIRE_CIUDAD =(String)request.getAttribute("DESTINO_DIRE_CIUDAD");
String DESTINO_COMU_NOMBRE =(String)request.getAttribute("DESTINO_COMU_NOMBRE");
String DESTINO_REGI_NOMBRE =(String)request.getAttribute("DESTINO_REGI_NOMBRE");

String empresas_giro =(String)request.getAttribute("empresas_giro");

    %>
<!DOCTYPE html>
<html lang="en"><head>
<% 
  if(request.getParameter("sg")!=null && !request.getParameter("sg").equals("")){
  		if(request.getParameter("sg").equals("OK")){
			out.println("<script>alert('- DEBE INGRESAR GUIAS.')</script>");
			//out.println("<script>window.location = '/001/'</script>");
		} 
		
	}
   
	%>
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
    <script type="text/javascript"> 
	//arreglo de contactos
	
    $( document ).ready(function() {
    	
    	
    	$( "#datepicker3" ).datepicker({ 
    		dateFormat: "dd-mm-yy", monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
            monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
            dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
            dayNamesShort: ['Dom','Lun','Mar','Mie','Juv','Vie','Sab'],
            dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa'],
            minDate: new Date(<%=minyear%>,<%=minmes%>-1,1),
            maxDate: new Date(<%=maxyear%>,<%=maxmes%>,0)
            
    	});
    	
     	$( "#datepickerref" ).datepicker({ dateFormat: "dd-mm-yy", monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
    	    monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
    	    dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
    	    dayNamesShort: ['Dom','Lun','Mar','Mie','Juv','Vie','Sab'],
    	    dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa']
    	});
        
   
    	
    	
    	
		$('#cont_id').on('change', function() {
			
			var cont_seleccionada= $(this).val();
			
			
			
			if(cont_seleccionada==""){
				$('#cont_phone').val("");
				$('#cont_mail').val("");
				$('#cont_phonec').val("");
			}
			else{
				$.post( "getContactoAsync", {id_contacto:cont_seleccionada })
		        .done(function( data ) {
		        		var contacto = $.parseJSON(data);
		          		
		        		$('#cont_phone').val(contacto.telefono);
						$('#cont_mail').val(contacto.email);
						$('#cont_phonec').val(contacto.celular);
		          		 
		          		
		        });
			}
			
			
			
			
		});
		$('#cont_id').change();
		
		
		 $('#subtotal').on('blur', function() {
         	
         	//$('#subtotal').val(Number($('#val1').val())+Number($('#val2').val())+Number($('#val3').val())+Number($('#val4').val())+Number($('#val5').val()));
         	
         	$('#neto').val(Math.round( (Number($('#subtotal').val())-Number($('#desc').val()))));
         	$('#iva').val(Math.round((Number($('#neto').val()))*0.19));
         	if(!$("#g_afecta").is(':checked')) $('#iva').val("0");
         	$('#total').val(Math.round( (Number($('#neto').val()))+Number($('#iva').val())));
         	
         	
         	
     		 });
         
         $('#desc').on('blur', function() {
         	$('#subtotal').blur();
                 
     		 });
        
         $('#g_afecta').on('change', function() {
         	$('#subtotal').blur();
                 
     		 });
        
         $('#subtotal').blur();
         
         $('#tipo_dteref').on('change', function() {
             var t_ref= $(this).val();
             
         		if(t_ref==""){
         			$("#folioref").prop('disabled', true);
            		$("#folioref").css('background','#FFF');
            		
            		$("#datepickerref").prop('disabled', true);
            		$("#datepickerref").css('background','#FFF');
            		
            		
         		}else{
         			$("#folioref").prop('disabled', false);
            		$("#folioref").css('background','#FF3');
            		
            		$("#datepickerref").prop('disabled', false);
            		$("#datepickerref").css('background','#FF3');
         		}
                      
         	 });


          $('#tipo_dteref').change();
		
		
		
		
    });
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
        	
        	$("#empresa_emisora_nombre").val($("#empresas_id").find('option:selected').text());
        	$("#cont_nombre").val($("#cont_id").find('option:selected').text());
    		
        	
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
        	
        	if(document.getElementById('obs1').value==""){
        		msg+="DEBE INGRESAR EL NUMERO DE TICKET\n";
        		validate=false;
        	} 
        	if(document.getElementById('obs').value==""){
        		msg+="DEBE INGRESAR LA FORMA DE TRASLADO\n";
        		validate=false;
        	}  
        	if(<%=numguias%> == 0){
        		msg+="DEBE INGRESAR DETALLE.";
        		validate=false;	
        	}
        	if(document.getElementById('total').value<0){
        		todobien=false;
        		msg+="NO SE PUEDE EMITIR CON VALORES NEGATIVOS\n";
        	}
        	
        	if($("#tipo_dteref").find('option:selected').text()!="NINGUNA" && document.getElementById('datepicker').value==''){
        		todobien=false;
        		msg+="DEBE INGRESAR UNA FECHA PARA LA REFERENCIA \n";
        			
        	}
        	if($("#cont_id").find('option:selected').val()==""){
        		todobien=false;
        		msg+="DEBE INGRESAR UN CONTACTO \n";
        			
        	}
        	
        	
        	<% if(ar_guias!=null){%>
        	
        	if(<%=ar_guias.length%>>14){
        		todobien=false;
        		msg+="NO SE PUEDE AGREGAR MAS DE 14 ACTIVOS, DEBE REDUCIR LA CANTIDAD DE ACTVOS EN EL DETALLE DE LA GUIA\n";
        	}
        	
        	<% } %>
        	
        	
        	
        	
        	
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
    </script>
      <script type="text/javascript">
    
    function VerificaGuias(){
    	if(<%=numguias%> == 0){
    		alert('- DEBE INGRESAR GUIAS.');
    		return false;	
    	}else{
    		return true;
    	}
    }
    
    
    function previsualizar(){
    	disableSubmitB();
    	$("#form1").attr("action", "http://186.67.10.115:81/NOF/factura_formato_venta.php");
		
		$("#dir_prev").val($("#dire_id").val());
		$("#dsco_prev").val($("#desc").val());
		
		$("#fec_prev").val($("#datepicker3").val());
		
		$("#comuna_prev").val($("#gv_comuna").val());
		$("#ciudad_prev").val($("#gv_ciudad").val());
		$("#telefono_prev").val($("#cont_phone").val());
		$("#rut_prev").val($("#empresas_rut").val());
		$("#rz_prev").val($("#rz_destino").val());
		
		
		
		$("#obs_prev").val($("#obs1").val()+'-'+$("#obs").val());
		$("#obs_prev2").val($("#cont_id :selected").text()+'-'+$("#cont_mail").val());
		
		//$("#obs_prev2").val($("#ref").val());
		$("#obs_prev3").val($("#obs2").val());
		
		if($("#dsco_prev").val()>0){
			//generamos linea de descuento para previsualizacion 
			
			$("#detalle_descuento_prev").val($("#glosa_desc").val()+" $"+$("#desc").val());
			
		}
		
		
		
		if($("#tipo_dteref").find('option:selected').text()!="NINGUNA"){
			$("#ref_prev").val($("#tipo_dteref").find('option:selected').text()+": Nro. "+$("#folioref").val()+" del "+$("#datepickerref").val());
				
		}
		if(!$("#g_afecta").is(':checked')) {
			$("#afecta_prev").val("0");
		}
		else{
			$("#afecta_prev").val("1");
		}
		
		$("#neto_prev").val($("#subtotal").val());
		$("#iva_prev").val($("#iva").val());
		$("#total_prev").val($("#total").val());
		
		
		
		enableSubmitB();
		
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
	  <p>N823.I.02.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/823/I01_001'">VOLVER</button> 
	<div align="center" class="title">
		<p >INGRESAR GUIA DE DESPACHO TRASLADO ACTIVOS NORMAL</p>
	</div>
</div>
<div class="content" >
  <form action="I02_001" name="form1" id="form1" method="post" >
 	<input type="hidden" name="o_id" value="<%=request.getAttribute("o_id")  %>">
 	<input type="hidden" name="d_id" value="<%=request.getAttribute("d_id")  %>">
  <input type="hidden" style="width:100px;height:30px ;background:#FFF;" name="clientes_id" value="<%=clientes_id%>" >
  <input type="hidden" style="width:100px;height:30px ;background:#FFF;" name="did" value="<%=did%>" >
	<div class=" cuadroblanco" style="height:80px;">
	<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>NRO.GUIA:</strong><input  type="text" style="width:100px;height:30px ;background:#FFF;" disabled="disabled" value="ND"></div>
		<div class="divhead"><strong>FECHA:</strong><input  type="text" style="width:150px;height:30px ;background:#FF3;"  class="amarillo"  name="gv_fecha" id="datepicker3" readonly="readonly" value="<%=fecha%>"></div>
		<div class="divhead"><strong>CONTROL GUIA:</strong><input type="text" maxlength="30" style="width:350px;height:30px ;background:#FFF" disabled="disabled" value="POR EMITIR" ></div>
		<div class="divhead"><strong>EMISOR:</strong><select name="empresas_id" id="empresas_id" style="margin:0px 0px 0px 0px;width: 135px;" >
				<% for(int i =0; i<ar_empresas.length; i++){
					String[] empresas = ar_empresas[i].split("/");%>
				<option value="<%=empresas[0]%>"<% if(empresas_id.equals(empresas[0]))%><%="selected"%>><%=empresas[1] %></option>
			<% } %>
    		</select><span class="cabecera" style="color:#F00">*</span></div>
		<div class="divhead"><strong>ESTADO SII:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="NO ENVIADA"></div>
		<div class="divhead" style="vertical-align:middle;padding: 8px 0 8px 0;" ><strong>AFECTA:</strong><input type="checkbox" value="1" name="g_afecta" id="g_afecta"  class="searchInput" checked="checked" ></div>
	</div>
	</div>

	<div class=" cuadroblanco" style="height:160px;margin-top: 5px;">

		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS ORIGEN</p></h3>
			<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>RAZON SOCIAL:</strong><input type="text" style="width:600px;height:30px ;background:#FFF;" disabled="disabled" value="<%=ORIGEN_empresas_razonsocial%>" ></div>
		<div class="divhead"><strong>RUT:</strong><input type="text" style="width:145px;height:30px ;background:#FFF;" disabled="disabled" value="<%=ORIGEN_empresas_rut%>" ></div>
		<div class="divhead"><strong>COD CLIENTE:</strong><input type="text" style="width:70px;height:30px ;background:#FFF;" disabled="disabled" value="<%=ORIGEN_empresas_id%>" ></div>
		<div class="divhead"><strong>DIRECCION:</strong><input type="text" style="width:500px;height:30px ;background:#FFF;" disabled="disabled" value="<%=ORIGEN_DIRE_DIRECCION%>"></div>
		<div class="divhead"><strong>REGION:</strong><input type="text" style="width:450px;height:30px ;background:#FFF;" disabled="disabled" value="<%=ORIGEN_REGI_NOMBRE%>"></div>
		<div class="divhead"><strong>COMUNA:</strong><input type="text" style="width:250px;height:30px ;background:#FFF;" disabled="disabled" value="<%=ORIGEN_COMU_NOMBRE%>" ></div>		
		<div class="divhead"><strong>CIUDAD:</strong><input type="text" style="width:250px;height:30px ;background:#FFF;" disabled="disabled" value="<%=ORIGEN_DIRE_CIUDAD%>" ></div>
		</div>
	</div> 
	
	<div class=" cuadroblanco" style="height:200px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS DESTINO</p></h3>
			<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>RAZON SOCIAL:</strong><input type="text" style="width:600px;height:30px ;background:#FFF;" disabled="disabled" value="<%=DESTINO_empresas_razonsocial%>" id="rz_destino"></div>
		<div class="divhead"><strong>RUT:</strong><input type="text" name="empresas_rut" id="empresas_rut" style="width:145px;height:30px ;background:#FFF;" disabled="disabled" value="<%=DESTINO_empresas_rut%>" ></div>
		<div class="divhead"><strong>COD CLIENTE:</strong><input type="text" style="width:70px;height:30px ;background:#FFF;" disabled="disabled" value="<%=DESTINO_empresas_id%>" ></div>
		<div class="divhead"><strong>DIRECCION:</strong><input type="text" style="width:500px;height:30px ;background:#FFF;" disabled="disabled" value="<%=DESTINO_DIRE_DIRECCION%>" id="dire_id"></div>
		<div class="divhead"><strong>REGION:</strong><input type="text" style="width:450px;height:30px ;background:#FFF;" disabled="disabled" value="<%=DESTINO_REGI_NOMBRE%>"></div>
		<div class="divhead"><strong>COMUNA:</strong><input type="text" id="gv_comuna" style="width:250px;height:30px ;background:#FFF;" disabled="disabled" value="<%=DESTINO_COMU_NOMBRE%>" ></div>		
		<div class="divhead"><strong>CIUDAD:</strong><input type="text" id="gv_ciudad" name="gv_ciudad" style="width:250px;height:30px ;background:#FFF;" readonly="readonly" value="<%=DESTINO_DIRE_CIUDAD%>" ></div>
		<div class="divhead"><strong>RESPONSABLE CUENTA:</strong><input type="text" id="resp" name="resp" style="width:350px;height:30px ;background:#FFF;" readonly="readonly" value="<%=RESPONSABLE  %>"></div>
</div>
	</div> 
	
	<div class=" cuadroblanco" style="height:120px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CONTACTO</p></h3>
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>CONTACTO:</strong><select name="cont_id" id="cont_id" style="margin:0px 0px 0px 0px; width: 366px">
				<% for(ContactoVO contacto: contactos){
					%>
				<option value="<%=contacto.getId()%>"<% if(contactos_id.equals(contacto.getId()))%><%="selected"%>><%=contacto.getNombre() %> <%=contacto.getApe_p() %> <%=contacto.getApe_m() %></option>
			<% } %>
    		</select><span class="cabecera" style="color:#F00">*</span></div>
		<div class="divhead"><strong>E-MAIL:</strong><input type="text" id="cont_mail" name="cont_email" style="width:350px;height:30px ;background:#FFF;" readonly="readonly" value=""></div>
		<div class="divhead"><strong>TELEFONO:</strong><input type="text" id="cont_phone" name="cont_phone" style="width:150px;height:30px ;background:#FFF;" readonly="readonly" value=""></div>
		<div class="divhead"><strong>CELULAR:</strong><input type="text" id="cont_phonec" name="cont_telefonoc" style="width:150px;height:30px ;background:#FFF;" readonly="readonly" value=""></div>		
		</div>
	</div>

	<div class=" cuadroblanco" style="height:250px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS ACTIVOS TRASLADOS</p></h3>
		
		<table class="table">
			<thead style="border: 1px solid black;display: inline-block;">
			   <tr style="background-color:#0101DF;color:#FFFFFF">
				<td width="80" style="font-size:20px"><strong>ID</strong></td>
				<td width="850" style="font-size:20px"><strong>DESCRIPCION</strong></td>
				<td width="200" style="font-size:20px"><strong>UBICACION</strong></td>
				<td width="200" style="font-size:20px"><strong>SERIE</strong></td>
				<td width="200" style="font-size:20px"><strong>FECHA</strong></td>
				<td width="200" style="font-size:20px"><strong>VALOR</strong></td>
			  </tr>  
			<tbody id="fbody" class="scroll" style="border: 1px solid black;display: inline-block;">
			<%    
			 int valor=0;
				if(ar_guias!=null)
					for(int i =0; i<ar_guias.length; i++){
				    	String[] Guias = ar_guias[i].split(";;");
				    	valor+=Integer.parseInt(Guias[12]);
			%>
			<tr class="hov">
				<td width="80"><%=Guias[0]%>
					<input type="hidden" value="<%=Guias[1]%>" name="detguias[]">
					<input type="hidden" style="width:700px;height:30px" value="<%=Guias[0]%>;;<%=Guias[2]%>;;<%=Guias[3]%>;;<%=Guias[4]%>;;<%=Guias[5]%>;;<%=Guias[7]%>;;<%=Guias[8]%>;;<%=Guias[9]%>;;<%=Guias[10]%>;;<%=Guias[11]%>;;<%=Guias[12]%>" name="tras_id[]">
					<input type="hidden" name="detalle_prev[]" value="<%=Guias[1]%>-<%=Guias[10]%>-<%=Guias[11]%>;<%=Guias[2]%> - <%=Guias[3]%> - <%=Guias[6]%>;1;<%=Guias[12]%>;0;<%=Guias[12]%>"></td>
				<td width="850"><%=Guias[2]%> - <%=Guias[3]%></td>
				<td width="200"><%=Guias[7]%></td>
				<td width="200"><%=Guias[5]%></td>
				<td width="200"><%=Guias[6]%></td>
				<td width="200"><%=Guias[12]%></td>
			</tr>
			<% } %>
			</tbody>
			
		  </table>
	<div class="bgrabar">
		<button type="submit" name="agregarguias" id="agregarguias" class="btn btn-success" onclick="$('#form1').attr('action', 'iguia_tras'); return true;" >AGREGAR</button>
	</div>
	</div>
	
	<div class=" cuadroblanco" style="height:80px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>SUBTOTAL:</strong><input name="subtotal" id="subtotal" type="text" style="width:150px;height:30px ;background:#FFF;text-align: right;"  value="<%=valor %>" readonly="readonly"  ></div>
		<div class="divhead"><strong>MONTO DESCUENTO:</strong><input type="text" name="desc"  id="desc" onkeydown="return validateNum(event)" class="amarillo" style="width:150px;height:30px ;background:#FF3;text-align: right;"  value="0"  maxlength="12" ></div>
		<div class="divhead"><strong>GLOSA DESCUENTO:</strong><input type="text" name="glosa_desc"  id="glosa_desc" class="amarillo" style="width:350px;height:30px ;background:#FF3;" maxlength="30"  value=""  ></div>
		<div class="divhead"><strong id="netolabel">NETO:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;text-align: right;" readonly="readonly" value="" id="neto" name="neto"></div>
		
		<div class="divhead"><strong>IVA 19%:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;text-align: right;" readonly="readonly" value="" id="iva" name="iva"></div>
		<div class="divhead"><strong>TOTAL:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;text-align: right;" readonly="readonly" value="" id="total" name="total"></div>
		
	</div>
	</div>
	
		<div class=" cuadroblanco" style="height:40px;margin-top: 5px">
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>TIPO REF:</strong><select name="tipo_dteref" id="tipo_dteref">
			<option value="">NINGUNA</option>
			<option value="801">ORDEN DE COMPRA</option>
			<option value="802">NOTA DE PEDIDO</option>
			<option value="803">CONTRATO</option>
			<option value="HES">HOJA DE ENTRADA</option>
			
		</select></div>
		<div class="divhead"><strong>FOLIO REF:</strong><input type="text" name="folioref" id="folioref" class="amarillo" maxlength="36" style="width:410px;height:30px ;background:#FF3;" value=""></div>
		<div class="divhead"><strong>FECHA REF:</strong><input type="text" style="width:130px;height:30px ;background:#FF3;" name="fec_ref" id="datepickerref"  readonly="readonly" class="amarillo" ></div>
	</div>
		
	</div>
	
	<div class=" cuadroblanco" style="height:150px;margin-top: 5px;">
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>NUMERO DE TICKET:</strong><input type="text" id="obs1" name="ref" maxlength="255" id="ref" value="<%=ref %>" class="amarillo"style="width:750px;height:30px ;background:#FF3;" ><span class="cabecera" style="color:#F00">*</span></div>
		<div class="divhead"><strong>FORMA TRASLADO:</strong><input type="text" name="obs" maxlength="255" id="obs" value="<%=obs %>" class="amarillo" style="width:720px;height:30px ;background:#FF3;"><span class="cabecera" style="color:#F00">*</span></div>
		<div class="divhead"><strong>MOTIVO:</strong><input type="text" name="obs2" id="obs2" style="width:720px;height:30px ;background:#FFF;" value="SOLO TRASLADO NO CONSTITUYE VENTA" readonly="readonly"></div>
		</div>
		<div class="bgrabar">
		
		<input type="hidden" name="empresa_emisora_nombre" id="empresa_emisora_nombre" >
			<input type="hidden" name="cont_nombre" id="cont_nombre" >
		
		
			<input type="hidden" name="total_prev" id="total_prev" value="0">
			<input type="hidden" name="iva_prev" id="iva_prev" value="0">
			<input type="hidden" name="neto_prev" id="neto_prev" value="0">
			<input type="hidden" name="dsco_prev" id="dsco_prev" value="0">
			
			<input type="hidden" name="dir_prev" id="dir_prev" value="">
			
			<input type="hidden" name="obs_prev" id="obs_prev" value="">
			<input type="hidden" name="obs_prev2" id="obs_prev2" value="">
			<input type="hidden" name="obs_prev3" id="obs_prev3" value="">
			
			<input type="hidden" name="giro_prev" id="giro_prev" value="<%=empresas_giro%>">
			<input type="hidden" name="rz_prev" id="rz_prev">
			<input type="hidden" name="ref_prev" id="ref_prev">
			<input type="hidden" name="comuna_prev" id="comuna_prev">
			<input type="hidden" name="ciudad_prev" id="ciudad_prev">
			<input type="hidden" name="rut_prev"  id="rut_prev">
			<input type="hidden" value="<%=fecha%>" name="fec_prev" id="fec_prev">
			<input type="hidden" name="telefono_prev" id="telefono_prev">
			<input type="hidden" name="folio_prev" id="folio_prev" value="ND">
			<input type="hidden" name="tipo_doc_titulo_prev" id="tipo_doc_titulo_prev" value="GUIA ELECTRONICA">
			<input type="hidden" name="detalle_descuento_prev" id="detalle_descuento_prev">
			<input type="hidden" name="afecta_prev" id="afecta_prev">
		
			<button type="submit" name="previ" id="previ" class="btn btn-success" onclick="previsualizar()"  >PREVISUALIZAR</button>
          <button type="submit" value="graba" name="grabar" id="grabar" class="btn btn-success" onclick="return validateSubmit();" >GRABAR</button>
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

    <script src="lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>
    
  </body>
</html>


