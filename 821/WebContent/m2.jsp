<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 	
		
String gv_id =(String)request.getAttribute("GV_ID");
String id_guia_estado =(String)request.getAttribute("id_guia_estado");
String GV_FECHA=(String)request.getAttribute("GV_FECHA");
String EMISORA=(String)request.getAttribute("EMISORA");
String CLPR_RAZON_SOCIAL=(String)request.getAttribute("CLPR_RAZON_SOCIAL");
String CLPR_RUT=(String)request.getAttribute("CLPR_RUT");
String empresas_rutcliente=(String)request.getAttribute("CLPR_RUT")+"-"+(String)request.getAttribute("CLPR_DV");
String empresas_rut_ar[]=empresas_rutcliente.split("-");
java.text.NumberFormat nff = java.text.NumberFormat.getInstance();
String valRut = nff.format(Integer.parseInt(empresas_rut_ar[0]));
empresas_rutcliente=valRut+"-"+empresas_rut_ar[1];
String DIRE_DIRECCION=(String)request.getAttribute("DIRE_DIRECCION");
String CONT_TELEFONO=(String)request.getAttribute("CONT_TELEFONO");
String REGI_NOMBRE=(String)request.getAttribute("REGI_NOMBRE");
String COMU_NOMBRE=(String)request.getAttribute("COMU_NOMBRE");
String GV_CIUDAD=(String)request.getAttribute("GV_CIUDAD");
String CONT_NOMBRE=(String)request.getAttribute("CONT_NOMBRE");
String PERS_NOMBRE=(String)request.getAttribute("PERS_NOMBRE");
String GV_TRANSPORTE=(String)request.getAttribute("GV_TRANSPORTE");
String GV_OBS=(String)request.getAttribute("GV_OBS");
String GV_ESTADO=(String)request.getAttribute("GV_ESTADO");
String GV_FECHA_EMISION=(String)request.getAttribute("GV_FECHA_EMISION");
String USU_INICIAL=(String)request.getAttribute("USU_INICIAL");
String CLPR_GIRO=(String)request.getAttribute("CLPR_GIRO");
String guia_obs1=(String)request.getAttribute("guia_obs1");
String guia_obs2=(String)request.getAttribute("guia_obs2");
String neto=(String)request.getAttribute("neto");
String subtotal=(String)request.getAttribute("subtotal");
String[] ar_estados =(String[]) request.getAttribute("ar_estados");

String desc=(String)request.getAttribute("descuento");
String gr_glosa_desc=(String)request.getAttribute("glosadescuento");
String nrobirt=(String)request.getAttribute("nrobirt");

String[] ar_productos =(String[])request.getAttribute("ar_productos");

String Usu_nom=(String)request.getAttribute("usuname");

java.text.NumberFormat nf = java.text.NumberFormat.getInstance();


String iva=(String)request.getAttribute("iva");
String total=(String)request.getAttribute("total");

String tipo_dteref=(String)request.getAttribute("tipo_dteref");	
String folioref=(String)request.getAttribute("folioref");	
String fec_ref=(String)request.getAttribute("fec_ref");
String estados_vig_novig_id=(String) request.getAttribute("GV_ESTADO");

String g_afecta=(String)request.getAttribute("g_afecta");
    %>
<!DOCTYPE html>
<html lang="en"><head>
    <meta charset="utf-8">
    <title>New Office - Ingresar Factura Afecta Excenta Activos</title>
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
    
    <script type="text/javascript">
    $( document ).ready(function() {
    	
    	
    	$( "#datepicker3" ).datepicker({
    		dateFormat: "dd-mm-yy", monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
            monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
            dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
            dayNamesShort: ['Dom','Lun','Mar','Mie','Juv','Vie','Sab'],
            dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa']
         
    	});
    	
    	$( "#datepickerref" ).datepicker({ dateFormat: "dd-mm-yy", monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
    	    monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
    	    dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
    	    dayNamesShort: ['Dom','Lun','Mar','Mie','Juv','Vie','Sab'],
    	    dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa']
    	});
        
    
    
    $('#subtotal').on('blur', function() {
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
     $('#subtotal').blur();
    
    
    
    });
    
    
    function validateSubmit(){
    	$("#form1").attr("action", "");
    	
    	
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
    		msg+="DEBE INGRESAR LA FORMA DE DESPACHO\n";
    		validate=false;
    	}
    	if(document.getElementById('obs2').value==""){
    		msg+="DEBE INGRESAR UNA OBSERVACION\n";
    		validate=false;
    	}
    	if($("#tipo_dteref").find('option:selected').text()!="NINGUNA"){
    		
    		if(document.getElementById('datepickerref').value==""){
        		msg+="DEBE INGRESAR UNA FECHA DE REFERENCIA\n";
        		validate=false;
        	}
    		if(document.getElementById('folioref').value==""){
        		msg+="DEBE INGRESAR UN FOLIO DE REFERENCIA\n";
        		validate=false;
        	}
			
				
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
		$("#rut_prev").val($("#empresas_rut").val());
		$("#rz_prev").val($("#rz").val());
		$("#fec_prev").val($("#datepicker3").val());
		
		
		$("#total_prev").val($("#total").val());
		$("#iva_prev").val($("#iva").val());
		$("#neto_prev").val($("#subtotal").val());
		
		$("#dsco_prev").val($("#desc").val());
		$("#giro_prev").val('<%=CLPR_GIRO%>');
		
		
		$("#dir_prev").val($("#dire_id").val());
		
		$("#comuna_prev").val($("#gv_comuna").val());
		$("#ciudad_prev").val($("#gv_ciudad").val());
		$("#telefono_prev").val($("#cont_phone").val());
		
		
		$("#obs_prev").val('<%=USU_INICIAL%>-'+$("#obs1").val());
		
		$("#obs_prev2").val('<%=PERS_NOMBRE%>');
		
		//$("#obs_prev2").val($("#ref").val());
		$("#obs_prev3").val('<%=GV_OBS%>-'+$("#obs2").val());
		
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
	  <p>N821.M.02.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/821/m1'">VOLVER</button> 
	<div align="center" class="title">
		<p >MODIFICAR GUIA DE DESPACHO VENTA ACTIVO</p>
	</div>
</div>
<div class="content" >
  <form action="" name="form1" method="post" id="form1">
  <input type="hidden" name="gv_id" value="<%=gv_id%>" >
	<div class=" cuadroblanco" style="height:80px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>NRO.BIRT:</strong><input  type="text" style="width:100px;height:30px ;background:#FFF;" disabled="disabled" value="<%=nrobirt%>"></div>
		<div class="divhead"><strong>FOLIO:</strong><input  type="text" style="width:100px;height:30px ;background:#FFF;" disabled="disabled" value="ND"></div>
		
		<div class="divhead"><strong>FECHA:</strong><input  type="text" style="width:150px;height:30px ;background:#FF3;" readonly="readonly" class="amarillo"  name="gv_fecha" id="datepicker3" value="<%=GV_FECHA%>"></div>
		<div class="divhead"><strong>EMISOR:</strong><input type="text" name="empresa_emisora_nombre" style="width:120px;height:30px ;background:#FFF;" readonly="readonly" value="<%=EMISORA%>"></div>
		<div class="divhead"><strong>ESTADO SII:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="NO ENVIADA"></div>
		<div class="divhead" style="vertical-align:middle;padding: 8px 0 8px 0;" ><strong>AFECTA:</strong><input type="checkbox" value="1" name="g_afecta" id="g_afecta"  class="searchInput" <% if(g_afecta.equals("1")){%> checked="checked" <%} %>  ></div>
		<div class="divhead"><strong>ESTADO:</strong><select name="estados_vig_novig_id" id="estados_vig_novig_id"  style="margin:0px 0px 0px 0px;width: 167px; margin-bottom: 9px">
    <% for(int i =0; i<ar_estados.length; i++){
       		String[] estados_vig_novig = ar_estados[i].split("/");
    
    %>
    
      <option value="<%=estados_vig_novig[0]%>" <% if(estados_vig_novig_id.equals(estados_vig_novig[0]))%><%="selected"%>><%=estados_vig_novig[1] %></option>
    <% } %>
    </select><span class="cabecera" style="color:#F00"> *</span></div>
	</div>
	</div>
	
	<div class=" cuadroblanco" style="height:270px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS DESTINO</p></h3>
		
		<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>RAZON SOCIAL:</strong><input type="text" style="width:600px;height:30px ;background:#FFF;" readonly="readonly" id="rz" value="<%=CLPR_RAZON_SOCIAL%>"></div>
		<div class="divhead"><strong>RUT:</strong><input type="text" name="empresas_rut" id="empresas_rut" style="width:145px;height:30px ;background:#FFF;" readonly="readonly" value="<%=empresas_rutcliente%>" ></div>
		<div class="divhead"><strong>DIRECCION:</strong><input type="text" style="width:500px;height:30px ;background:#FFF;" readonly="readonly" value="<%=DIRE_DIRECCION%>" id="dire_id"></div>
		<div class="divhead"><strong>TELEFONO:</strong><input type="text" style="width:160px;height:30px ;background:#FFF;" readonly="readonly" value="<%=CONT_TELEFONO%>" id="cont_phone"></div>
		<div class="divhead"><strong>REGION:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" disabled="disabled" value="<%=REGI_NOMBRE%>"></div>
		<div class="divhead"><strong>COMUNA:</strong><input type="text" style="width:250px;height:30px ;background:#FFF;" readonly="readonly" value="<%=COMU_NOMBRE%>" id="gv_comuna" ></div>		
		<div class="divhead"><strong>CIUDAD:</strong><input type="text" style="width:250px;height:30px ;background:#FFF;" readonly="readonly" value="<%=GV_CIUDAD%>" id="gv_ciudad" ></div>
		<div class="divhead"><strong>CONTACTO:</strong><input type="text" style="width:400px;height:30px ;background:#FFF;"  value="<%=CONT_NOMBRE%>" name="contacto_nombre" readonly="readonly"></div>
		<div class="divhead"><strong>RESPONSABLE:</strong><input type="text" style="width:400px;height:30px ;background:#FFF;" readonly="readonly" value="<%=PERS_NOMBRE%>" name="GV_RESPONSBALE_NAME"></div>
		<div class="divhead"><strong>TRANSPORTE:</strong><input  type="text" style="width:300px;height:30px ;background:#FFF;" disabled="disabled" value="<%=GV_TRANSPORTE%>"></div>
		</div>
	</div> 

	<div class=" cuadroblanco" style="height:200px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS PRODUCTOS A FACTURAR</p></h3>
		<table class="table">
			<thead style="border: 1px solid black;display: inline-block;">
			  <tr style="background-color:#0101DF;color:#FFFFFF">
				<td width="100" style="font-size:20px"><strong>ID</strong></td>
				<td width="700" style="font-size:20px"><strong>PN/TLI - DESCRIPCION</strong></td>
				<td width="200" style="font-size:20px"><strong>SERIE</strong></td>
				<td width="100" style="font-size:20px"><strong>VALOR</strong></td>
			  </tr>  
			<tbody id="fbody" class="scroll" style="border: 1px solid black;display: inline-block;">
			 <%        
        for(int i =0; i<ar_productos.length; i++){
       	 String[] ProdDato = ar_productos[i].split(";;");
       	 String monto = nf.format(Integer.parseInt(ProdDato[4])).replace(",",".");
       	 %>
       		
			<tr class="hov">
				<td width="100"><%=ProdDato[0]%><input type="hidden" name="detalle_prev[]" value="<%=ProdDato[0]%>-<%=ProdDato[5]%>-<%=ProdDato[6]%>;<%=ProdDato[1]%>-<%=ProdDato[3]%>-<%=ProdDato[2]%>;1;<%=monto%>;0;<%=monto%>" >
			</td>
				<td width="700"><%=ProdDato[1]%> - <%=ProdDato[2]%></td>
				<td width="200"><%=ProdDato[3]%></td>
				<td width="100">$<%=monto%></td>
			</tr>
			
       	<% } %>
			</tbody>
			  </tr>
		  </table>
	</div>
	
		<div class=" cuadroblanco" style="height:120px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>SUBTOTAL:</strong><input name="subtotal" id="subtotal" type="text" style="width:150px;height:30px ;background:#FFF;" value="<%=subtotal %>"  readonly="readonly"></div>
		<div class="divhead"><strong>MONTO DESCUENTO:</strong><input type="text" name="desc"  id="desc" onkeydown="return validateNum(event)" class="amarillo" style="width:150px;height:30px ;background:#FF3;"  value="<%=desc %>"  maxlength="12" ></div>
		<div class="divhead" style="width: 800px;"><strong>GLOSA DESCUENTO:</strong><input type="text" name="glosa_desc"  id="glosa_desc" class="amarillo" style="width:350px;height:30px ;background:#FF3;" maxlength="30"  value="<%=gr_glosa_desc %>"  ></div>
		<div class="divhead"><strong id="netolabel">NETO:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;" readonly="readonly" value="<%=neto %>" id="neto" name="neto"></div>
		
		<div class="divhead"><strong>IVA 19%:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;" readonly="readonly" value="<%=iva %>" id="iva" name="iva"></div>
		<div class="divhead"><strong>TOTAL:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" readonly="readonly" value="<%=total %>" id="total" name="total"></div>
		
	</div>
	</div>
	<div class=" cuadroblanco" style="height:80px;margin-top: 5px">
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>TIPO REF:</strong><select name="tipo_dteref" id="tipo_dteref">
			<option value="">NINGUNA</option>
			<option value="801" <% if (tipo_dteref.equals("801")){%> selected="selected" <% } %>>ORDEN DE COMPRA</option>
			<option value="802" <% if (tipo_dteref.equals("802")){%> selected="selected" <% } %>>NOTA DE PEDIDO</option>
			<option value="803" <% if (tipo_dteref.equals("803")){%> selected="selected" <% } %>>CONTRATO</option>
			<option value="HES" <% if (tipo_dteref.equals("HES")){%> selected="selected" <% } %>>HOJA DE ENTRADA</option>
			
		</select></div>
		<div class="divhead"><strong>FOLIO REF:</strong><input type="text" name="folioref" id="folioref" class="amarillo" maxlength="36" style="width:410px;height:30px ;background:#FF3;" value="<%=folioref%>" ></div>
		<div class="divhead"><strong>FECHA REF:</strong><input type="text" style="width:130px;height:30px ;background:#FF3;" name="fec_ref" id="datepickerref"  readonly="readonly" class="amarillo" value="<%=fec_ref%>" ></div>
	</div>
		
	</div>
	<div class=" cuadroblanco" style="height:40px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
	
		<div class="divhead"><strong>ESTADO:</strong><input type="text" value="EMITIDA" style="width:200px;height:30px ;background:#FFF;" disabled="disabled" ></div>
		<div class="divhead"><strong>FECHA EMISION:</strong><input type="text" style="width:240px;height:30px ;background:#FFF;" disabled="disabled" value="<%=GV_FECHA_EMISION%>"></div>
		<div class="divhead"><strong>EMISOR:</strong><input type="text" style="width:50px;height:30px ;background:#FFF;" readonly="readonly" value="<%=USU_INICIAL%>" name="EMISOR_INICIAL"></div>
	</div>
	</div>
	<div class=" cuadroblanco" style="height:160px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>FORMA DE DESPACHO:</strong><input type="text" name="obs1" id="obs1" class="amarillo" maxlength="255" style="width:720px;height:30px ;background:#FF3;" value="<%=guia_obs1%>"><span class="cabecera" style="color:#F00">*</span></div>
		<div class="divhead"><strong>OBSERVACION:</strong><input type="text" name="obs2" id="obs2" class="amarillo" maxlength="255" style="width:720px;height:30px ;background:#FF3;" value="<%=guia_obs2%>"><span class="cabecera" style="color:#F00">*</span></div>
		<div class="divhead"><strong>OTRAS OBS:</strong><input  type="text" name="ref" style="width:550px;height:30px ;background:#FFF;" readonly="readonly" value="<%=GV_OBS%>" ></div>
		</div>
		
		
		<div class="bgrabar">
		
			<input type="hidden"  name="rut_prev"  id="rut_prev">
			<input type="hidden" value="<%=GV_FECHA%>" name="fec_prev" id="fec_prev">
			<input type="hidden" name="total_prev" id="total_prev" value="0">
			<input type="hidden" name="iva_prev" id="iva_prev" value="0">
			<input type="hidden" name="neto_prev" id="neto_prev" value="0">
			<input type="hidden" name="dsco_prev" id="dsco_prev" value="0">
			
			<input type="hidden"  name="ref_prev" id="ref_prev">
			<input type="hidden" name="afecta_prev" id="afecta_prev">
			<input type="hidden" name="dir_prev" id="dir_prev" value="">
			<input type="hidden"  name="ciudad_prev" id="ciudad_prev">
			<input type="hidden"  name="comuna_prev" id="comuna_prev">
			<input type="hidden" name="obs_prev" id="obs_prev" value="">
			<input type="hidden" name="obs_prev2" id="obs_prev2" value="">
			<input type="hidden" name="obs_prev3" id="obs_prev3" value="">
			<input type="hidden" name="giro_prev" id="giro_prev">
			<input type="hidden" name="rz_prev" id="rz_prev">
			<input type="hidden" name="tipodte_prev" id="tipodte_prev">
			<input type="hidden" name="telefono_prev" id="telefono_prev">
			<input type="hidden" name="tipo_doc_titulo_prev" id="tipo_doc_titulo_prev" value="GUIA ELECTRONICA">
			
				<input type="hidden" name="detalle_descuento_prev" id="detalle_descuento_prev">
			
			<button type="submit" name="previ" id="previ" class="btn btn-success" onclick="previsualizar()"  >PREVISUALIZAR</button>
          	<button type="submit" name="grabar" id="grabar" class="btn btn-success"  onclick="return validateSubmit()" >GRABAR</button>
          
          
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


