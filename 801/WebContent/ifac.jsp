<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 		

String fact_id =(String)request.getAttribute("FACT_ID");
String id_fac_estado =(String)request.getAttribute("id_fac_estado");
String FACT_FECHA=(String)request.getAttribute("FACT_FECHA");
String FACT_CONDICIONES=(String)request.getAttribute("FACT_CONDICIONES");
String CLPR_NOMBRE_FANTASIA=(String)request.getAttribute("CLPR_NOMBRE_FANTASIA");
String CONT_EMAIL=(String)request.getAttribute("CONT_EMAIL");

String CLPR_GIRO=(String)request.getAttribute("CLPR_GIRO");
String FACT_TIPO_CAMBIO=(String)request.getAttribute("FACT_TIPO_CAMBIO");
String CONT_NOMBRE=(String)request.getAttribute("CONT_NOMBRE");
String CONT_TELEFONO=(String)request.getAttribute("CONT_TELEFONO");
String FACT_OBS=(String)request.getAttribute("FACT_OBS");
String FACT_ESTADO=(String)request.getAttribute("FACT_ESTADO");
String FACT_FECHA_EMISION=(String)request.getAttribute("FACT_FECHA_EMISION");
String USU_INICIAL=(String)request.getAttribute("USU_INICIAL");
String VTA_OC=(String)request.getAttribute("VTA_OC");

String CLPR_RUT=(String)request.getAttribute("CLPR_RUT");
String CLPR_DV=(String)request.getAttribute("CLPR_DV");
String empresas_rut = CLPR_RUT;//+CLPR_DV;
String CLPR_RAZON_SOCIAL=(String)request.getAttribute("CLPR_RAZON_SOCIAL");
String DIRE_DIRECCION=(String)request.getAttribute("DIRE_DIRECCION");
String DIRE_CIUDAD=(String)request.getAttribute("DIRE_CIUDAD");
String REGI_NOMBRE=(String)request.getAttribute("REGI_NOMBRE");
String PERS_NOMBRE=(String)request.getAttribute("USU_NOMBRE");
String COMU_NOMBRE=(String)request.getAttribute("COMU_NOMBRE");

java.text.NumberFormat nf = java.text.NumberFormat.getInstance();

String neto = (String)request.getAttribute("NETO");

String iva = (String)request.getAttribute("IVA");

String total = (String)request.getAttribute("TOTAL");

String[] ar_productos =(String[])request.getAttribute("ar_productos");
String[] ar_dire =(String[])request.getAttribute("ar_dire");

String Usu_nom=(String)request.getAttribute("usuname");

String fec_ar[]=FACT_FECHA_EMISION.split("-");

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

    <script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>
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
	.tbodyy {
	  
	    height: 120px;
	    overflow-y: scroll;
	    min-width:100%;
    	display: inline-block;
	}
	.theadd {
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

function previsualizar(){
	
	$("#formdata").attr("action", "http://186.67.10.115:81/NOF/factura_formato_venta.php");
	$("#total_prev").val($("#total").val());
	$("#iva_prev").val($("#iva").val());
	$("#neto_prev").val($("#netoneto").val());
	
	$("#dir_prev").val($("#dire_id").find('option:selected').text());
	$("#obs_prev").val('<%=USU_INICIAL%>-'+$("#obs1").val()+'-'+$("#condiciones").val()+'-<%=VTA_OC%>');
	
	$("#obs_prev2").val($("#CONT_NOMBRE").val()+'-'+$("#CONT_EMAIL").val());
	
	
	$("#obs_prev3").val($("#obs2").val()+'-'+$("#ref").val()+'-TC:'+$("#tipo_cambio").val());
	
	$("#rut_prev").val($("#empresas_rut").val());
	$("#dsco_prev").val($("#desc").val());
	
	$("#fec_prev").val($("#datepickerfechafactura").val());
	
	if(Number($("#desc").val()>0)){
		$("#detalle_descuento_prev").val($("#glosa_desc").val()+" $"+$("#desc").val());
			
	}
	
	if($("#tipo_dteref").find('option:selected').text()!="NINGUNA"){
		$("#ref_prev").val($("#tipo_dteref").find('option:selected').text()+": Nro. "+$("#folioref").val()+" del "+$("#datepicker").val());
			
	}
	
	
	
}

function graba(){
	
	$("#formdata").attr("action", "");
	
	var todobien=true;
	var msg="";
	
	if(document.getElementById('obs1').value==''){
		todobien=false;
		msg+="DEBE INGRESAR LA FORMA DE DESPACHO\n";
	}
	if(document.getElementById('datepickervencimiento').value==''){
		todobien=false;
		msg+="DEBE INGRESAR LA FECHA DE VENCIMIENTO\n";
	}
	if(<%=ar_productos.length%>>14){
		todobien=false;
		msg+="NO SE PUEDE FACTURAR MAS DE 14 PRODUCTOS, DEBE REDUCIR LA CANTIDAD DE PRODUCTOS EN EL DETALLE EN BIRT\n";
	}
	if(document.getElementById('total').value<0){
		todobien=false;
		msg+="NO SE PUEDE FACTURAR CON VALORES NEGATIVOS\n";
	}
	if($("#tipo_dteref").find('option:selected').text()!="NINGUNA" && document.getElementById('datepicker').value==''){
		todobien=false;
		msg+="DEBE INGRESAR UNA FECHA PARA LA REFERENCIA \n";
			
	}
	
	
	
	if(todobien){return true;}
	else{
		alert(msg);
		msg="";
		return false;
	}
}

$( document ).ready(function() {
	//alert('validando rut');
	
	
	//alert($.Rut.formatear($('#empresas_rut').val()));
	var rut_formateado= $.Rut.formatear($('#empresas_rut').val())+"-"+"<%=CLPR_DV%>";
	//alert(rut_formateado);
	$('#empresas_rut').val(rut_formateado);
	
	
	
	
	$( "#datepicker" ).datepicker({ dateFormat: "dd-mm-yy", monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
        monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
        dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
        dayNamesShort: ['Dom','Lun','Mar','Mie','Juv','Vie','Sab'],
        dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa']});
	
	$( "#datepickervencimiento" ).datepicker({ 
		dateFormat: "dd-mm-yy", monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
        monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
        dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
        dayNamesShort: ['Dom','Lun','Mar','Mie','Juv','Vie','Sab'],
        dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa'],
        minDate: new Date(<%=fec_ar[2]%>, <%=fec_ar[1]%> - 1, <%=fec_ar[0]%>),
	
	});
	
	$( "#datepickerfechafactura" ).datepicker({
		dateFormat: "dd-mm-yy", monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
        monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
        dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
        dayNamesShort: ['Dom','Lun','Mar','Mie','Juv','Vie','Sab'],
        dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa'],
        onClose: function( selectedDate ) {
            $( "#datepickervencimiento" ).datepicker( "option", "minDate", selectedDate );
          }
        
	
	});
 
	
	 $('#tipo_dteref').on('change', function() {
         var t_ref= $(this).val();
         
			if(t_ref==""){
				$("#folioref").prop('disabled', true);
        		$("#folioref").css('background','#FFF');
        		
        		$("#datepicker").prop('disabled', true);
        		$("#datepicker").css('background','#FFF');
        		
        		
			}else{
				$("#folioref").prop('disabled', false);
        		$("#folioref").css('background','#FF3');
        		
        		$("#datepicker").prop('disabled', false);
        		$("#datepicker").css('background','#FF3');
			}
	             
		 });
	
	
	 $('#tipo_dteref').change();
	 
	 
	  
     $('#neto').on('blur', function() {
     	var tipdt=$('input[name=tipodte]:checked').val();
     	
    	$('#tipodte_prev').val(tipdt);
    	
     	if(tipdt=='33') $('#iva').val(Math.round((Number($('#neto').val())-Number($('#desc').val()))*0.19));
     	if(tipdt=='34') $('#iva').val("0");
     	$('#total').val(Math.round( (Number($('#neto').val())-Number($('#desc').val()))+Number($('#iva').val())));
     	$('#netoneto').val(Math.round( (Number($('#neto').val())-Number($('#desc').val()))));
		 });
     
     $('#desc').on('blur', function() {
     	$('#neto').blur();
             
		 });
    
     $('#tipodte1').on('change', function() {
     	$('#neto').blur();
             
		 });
     $('#tipodte2').on('change', function() {
     	$('#neto').blur();
             
		 });
     
     $('#neto').blur();
     
     
     $('#dire_id').on('change', function() {
         var dire_seleccionada= $(this).val();
         
   	   for(var ii=0; ii< dirs_ar.length;ii++){
            
            var d_ar =dirs_ar[ii].split("/");
            if(d_ar[0]==dire_seleccionada){
          	  //alert('esta');
          	  
          	  $('#regi').val(d_ar[3]);
          	 $('#gv_ciudad').val(d_ar[4]);
          	$('#gv_comuna').val(d_ar[5]);
          	 //gv_ciudad
             
              break;
            }
          
          }
             
		 });
	
	
	
});



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
	  <p>N801.I.02.001</p>
	</div>
	<form method="get" action="iperusu" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/801/ifac_search'">VOLVER</button> 
	<div align="center" class="title">
		<p >INGRESAR FACTURA AFECTA - EXENTA ACTIVOS</p>
	</div>
</div>
<div class="content" >
  <form action="ifac" name="form1" method="post" id="formdata">
  <input type="hidden" name="fact_id" value="<%=fact_id%>" >
   
	<div class=" cuadroblanco" style="height:160px;">
	<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>NRO.FOLIO:</strong><input  type="text" style="width:100px;height:30px ;background:#FFF;" disabled="disabled" value="ND"></div>
		<div class="divhead"><strong>NRO.BIRT:</strong><input  type="text" style="width:100px;height:30px ;background:#FFF;" disabled="disabled" value="<%=fact_id%>"></div>
		<div class="divhead"><strong>FECHA:</strong><input  type="text" style="width:125px;height:30px ;background:#FF3;" readonly="readonly" class="amarillo" id="datepickerfechafactura" name="datepickerfechafactura" value="<%=FACT_FECHA%>"></div>
		<div class="divhead"><strong>CONDICIONES:</strong><input type="text" id="condiciones" style="width:350px;height:30px ;background:#FFF;" readonly="readonly" value="<%=FACT_CONDICIONES%>"></div>
		<div class="divhead"><strong>EMISOR:</strong><input name="FACT_EMPRESA_EMISORA_NOMBRE" type="text" style="width:120px;height:30px ;background:#FFF;" readonly="readonly" value="<%=CLPR_NOMBRE_FANTASIA%>"></div>
		<div class="divhead"><strong>ESTADO SII:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=id_fac_estado%>"></div>
		<div class="divhead"><strong>TIPO DOCUMENTO:</strong><input type="radio" name="tipodte" id="tipodte1" value="33" checked="checked" style="height:30px">(33)AFECTA <input type="radio" name="tipodte" id="tipodte2" value="34" style="height:30px">(34)EXENTA</div>
		<div class="divhead"><strong>FEC. VENCIMIENTO:</strong><input maxlength="10" type="text" style="width:130px;height:30px ;background:#FF3;" value="" class="amarillo" id="datepickervencimiento" name="fecvencimiento" readonly="readonly"><span class="cabecera" style="color:#F00">*</span></div>
	
	</div> 
	</div>

	<div class=" cuadroblanco" style="height:310px;margin-top: 5px">
	
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CLIENTE</p></h3>
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>RAZON SOCIAL:</strong><input type="text" style="width:600px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CLPR_RAZON_SOCIAL%>"><input type="hidden" value="<%=CLPR_RAZON_SOCIAL%>" name="rz_prev"></div>
		<div class="divhead"><strong>RUT:</strong><input type="text" name="empresas_rut" id="empresas_rut" style="width:145px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_rut%>" ></div>
		<div class="divhead"><strong>GIRO:</strong><input type="text" style="width:600px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CLPR_GIRO%>"></div>
		
		<div class="divhead"><strong>DIRECCION:</strong><select name="dire_id" id="dire_id" style="width:550px;height:30px;">
		<%        
			for(int i =0; i<ar_dire.length; i++){
       	 	String[] DireDato = ar_dire[i].split("/");
       	 %>
       	 	<option value="<%=DireDato[0]%>"><%=DireDato[1]%></option>
       	 <% } %>
       	 </select>
       	 </div>
		<div class="divhead"><strong>COMUNA:</strong><input type="text" style="width:200px;height:30px ;background:#FFF;" disabled="disabled" value="<%=COMU_NOMBRE%>" ><input type="hidden" value="<%=COMU_NOMBRE%>" name="comuna_prev"></div>		
		<div class="divhead"><strong>CIUDAD:</strong><input type="text" style="width:250px;height:30px ;background:#FFF;" disabled="disabled" value="<%=DIRE_CIUDAD%>" ><input type="hidden" value="<%=DIRE_CIUDAD%>" name="ciudad_prev"></div>
		<div class="divhead"><strong>REGION:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" disabled="disabled" value="<%=REGI_NOMBRE%>"></div>
		
		
		<div class="divhead"><strong>RESPONSABLE:</strong><input id="responsable" name="responsable_nombre" type="text" style="width:400px;height:30px ;background:#FFF;" readonly="readonly" value="<%=PERS_NOMBRE%>"></div>
		<div class="divhead"><strong>TIPO CAMBIO:</strong><input id="tipo_cambio"  type="text" style="width:120px;height:30px ;background:#FFF;" readonly="readonly" value="<%=FACT_TIPO_CAMBIO%>"></div>
		<div class="divhead"><strong>CONTACTO:</strong><input type="text" id="CONT_NOMBRE" name="CONT_NOMBRE" style="width:400px;height:30px ;background:#FFF;" readonly="readonly" value="<%=CONT_NOMBRE%>"></div>
		<div class="divhead"><strong>TELEFONO:</strong><input type="text" name="CONT_TELEFONO" style="width:240px;height:30px ;background:#FFF;" readonly="readonly" value="<%=CONT_TELEFONO%>"><input type="hidden" value="<%=CONT_TELEFONO%>" name="telefono_prev"></div>
		<div class="divhead"><strong>CONTACTO EMAIL:</strong><input id="CONT_EMAIL" name="CONT_EMAIL" type="text" maxlength="40" style="width:450px;height:30px ;background:#FF3;" class="amarillo"  value="<%=CONT_EMAIL%>"></div>
		
		
	</div>
	</div> 
	
	<div class=" cuadroblanco" style="height:200px;margin-top: 5px">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS PRODUCTOS A FACTURAR</p></h3>
		<table class="table">
			<thead style="border: 1px solid black;" class="theadd">
			   <tr style="background-color:#0101DF;color:#FFFFFF">
				<td width="80" style="font-size:20px"><strong>ID</strong></td>
				<td width="700" style="font-size:20px"><strong>PN/TLI - DESCRIPCION</strong></td>
				<td width="120" style="font-size:20px"><strong>SERIE</strong></td>
				<td width="100" style="font-size:20px"><strong>VALOR</strong></td>
			  </tr>  
			<tbody id="fbody" class="scroll tbodyy" style="border: 1px solid black">
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
				<td width="80"><%=ProdDato[0]%><input type="hidden" name="detalle_prev[]" value="<%=ProdDato[0]%>-<%=ProdDato[5]%>-<%=ProdDato[6]%>;<%=des%> - <%=ProdDato[3]%>-<%=ProdDato[1]%>;1;<%=monto%>;0;<%=monto%>"></td>
				<td width="700"><%=ProdDato[1]%> - <%=des%></td>
				<td width="120"><%=ProdDato[3]%></td>
				<td width="100">$<%=monto%></td>
			</tr>
			
       	<% } %>
			</tbody>
			  </tr>
		  </table>
	</div>

	<div class=" cuadroblanco" style="height:120px;margin-top: 5px">
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>SUBTOTAL:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" readonly="readonly" value="<%=neto%>" id="neto" name="FACT_SUBTOTAL"></div>
		<div class="divhead"><strong>MONTO DESCUENTO:</strong><input type="text" name="desc"  id="desc" class="amarillo" style="width:150px;height:30px ;background:#FF3;"  value="0"  maxlength="12" onkeydown="return validateNum(event)" ></div>
		<div class="divhead" style="width:800px;"><strong>GLOSA DESCUENTO:</strong><input type="text" name="glosa_desc"  id="glosa_desc" class="amarillo" style="width:350px;height:30px ;background:#FF3;" maxlength="30"  value=""  ></div>
		<div class="divhead"><strong>NETO:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;" readonly="readonly" value="" id="netoneto" name="FACT_NETO"></div>
		
		<div class="divhead"><strong>IVA 19%:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;" readonly="readonly" value="<%=iva%>" id="iva" name="FACT_IVA"></div>
		<div class="divhead"><strong>TOTAL:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" readonly="readonly" value="<%=total%>" id="total" name="FACT_TOTAL"></div>
		
		</div>
	</div>
	
	<div class=" cuadroblanco" style="height:80px;margin-top: 5px">
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>TIPO REF:</strong><select name="tipo_dteref" id="tipo_dteref">
			<option value="">NINGUNA</option>
			<option value="801">ORDEN DE COMPRA</option>
			<option value="802">NOTA DE PEDIDO</option>
			<option value="803">CONTRATO</option>
			<option value="HES">HOJA DE ENTRADA</option>
			
		</select></div>
		<div class="divhead"><strong>FOLIO REF:</strong><input type="text" name="folioref" id="folioref" class="amarillo" maxlength="36" style="width:410px;height:30px ;background:#FF3;" value=""></div>
		<div class="divhead"><strong>FECHA REF:</strong><input type="text" style="width:130px;height:30px ;background:#FF3;" name="fec_ref" id="datepicker"  readonly="readonly" class="amarillo" ></div>
	</div>
		
	</div>

	<div class=" cuadroblanco" style="height:40px;margin-top: 5px">
	<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>ESTADO EN BIRT:</strong><input type="text" value="<%=FACT_ESTADO%>" style="width:200px;height:30px ;background:#FFF;" disabled="disabled" ></div>
		<div class="divhead"><strong>FECHA EMISION:</strong><input type="text" style="width:125px;height:30px ;background:#FFF;" disabled="disabled" value="<%=FACT_FECHA_EMISION%>"></div>
		<div class="divhead"><strong>EMISOR:</strong><input type="text" name="USU_INICIAL" style="width:120px;height:30px ;background:#FFF;" readonly="readonly" value="<%=USU_INICIAL%>"></div>
		</div>
	</div>

	<div class=" cuadroblanco" style="height:160px;margin-top: 5px">
	<div style="padding: 5px 5px 0px 5px">
		<div class="divhead"><strong>FORMA DE DESPACHO:</strong><input type="text" name="obs1" id="obs1" class="amarillo" maxlength="36" style="width:410px;height:30px ;background:#FF3;" value=""><span class="cabecera" style="color:#F00">*</span></div>
		<div class="divhead"><strong>OBSERVACION:</strong><input type="text" name="obs2" id="obs2" class="amarillo" maxlength="40" style="width:460px;height:30px ;background:#FF3;" value=""></div>
		<div class="divhead"><strong>OTRAS OBS:</strong><input type="text" name="ref" id="ref" style="width:720px;height:30px ;background:#FFF;" readonly="readonly" value="<%=FACT_OBS%>"></div>
		<div class="bgrabar">
			<input type="hidden" name="total_prev" id="total_prev" value="0">
			<input type="hidden" name="iva_prev" id="iva_prev" value="0">
			<input type="hidden" name="neto_prev" id="neto_prev" value="0">
			<input type="hidden" name="dir_prev" id="dir_prev" value="">
			<input type="hidden" name="obs_prev" id="obs_prev" value="">
			<input type="hidden" name="obs_prev2" id="obs_prev2" value="">
			<input type="hidden" name="obs_prev3" id="obs_prev3" value="">
			<input type="hidden" name="obs_prev4" id="obs_prev4" value="">
			<input type="hidden" name="rut_prev" id="rut_prev">
			<input type="hidden" name="giro_prev" id="giro_prev" value="<%=CLPR_GIRO%>">
			<input type="hidden" name="dsco_prev" id="dsco_prev" value="0">
			<input type="hidden" name="tipodte_prev" id="tipodte_prev">
			<input type="hidden" name="detalle_descuento_prev" id="detalle_descuento_prev" >
			<input type="hidden" name="ref_prev" id="ref_prev" value="">
			<input type="hidden" value="<%=FACT_FECHA%>" name="fec_prev" id="fec_prev">
			
			
			<button type="submit" name="previ" id="previ" class="btn btn-success" onclick="previsualizar()"  >PREVISUALIZAR</button>
          	<button type="submit" name="grabar" id="grabar" class="btn btn-success" <% if(id_fac_estado.equals("ENVIADA")){ %>disabled<% } %>  onclick="return graba()" >GRABAR</button>
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


