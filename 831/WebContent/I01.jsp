<%@page import="java.util.ArrayList"%>
<% 	
String modname=(String)request.getAttribute("modname");

String Usu_nom=(String)request.getAttribute("usuname");


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
	  <p>N831.I.01.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/831/'">VOLVER</button> 
	<div align="center" class="title">
		<p>INGRESAR <%=modname %></p>
	</div>
</div>
<div class="content" >
  <form action="" name="form1" method="post" id="form1" > 
	<div class=" cuadroblanco" style="height:90px;padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>NRO.BOL:</strong><input type="text" style="width:80px;height:30px ;background:#FFF;" name="gv_id" id="gv_id" value="ND" disabled="disabled"></div>
		<div class="divhead"><strong>FECHA:</strong><input type="text" style="width:130px;height:30px ;background:#FF3;" class="amarillo"  name="gv_fecha" id="datepicker3" value="" readonly="readonly"></div>
		<div class="divhead"><strong>COND PAGO:</strong><input name="fac_servim_condiciones" id="fac_servim_condiciones" type="text" maxlength="30" style="width:350px;height:30px ;background:#FF3;" class="amarillo" value=""><span class="cabecera" style="color:#F00">*</span></div>
		
		<div class="divhead"><strong>EMISOR:</strong><select name="fac_servim_emisor" id="fac_servim_emisor" style="margin:0px 0px 0px 0px;" >
				
    		</select><span class="cabecera" style="color:#F00">*</span></div>
		<div class="divhead"><strong>ESTADO SII:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" value="NO ENVIADA" readonly="readonly"></div>
		<div class="divhead"><strong>TIPO DTE:</strong><input type="radio" name="tipodte" id="tipodte1" value="39" checked="checked" style="height: 30px;">(39)AFECTA <input type="radio" name="tipodte" id="tipodte2" value="41" style="height: 30px;">(41)EXENTA</div>
		
		<div class="divhead"><strong>FEC. VENCIMIENTO:</strong><input maxlength="10" type="text" style="width:130px;height:30px ;background:#FF3;" value="" class="amarillo" id="datepicker" name="fac_servim_fecvencimiento" readonly="readonly"><span class="cabecera" style="color:#F00">*</span></div>
		
	</div>

	<div class=" cuadroblanco" style="height:275px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CLIENTE</p></h3>
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>CLIENTE:</strong><select name="tipo_cli" id="tipo_cli" style="margin:0px 0px 0px 0px;">
				<option value="0"> GENERICO </option>
				<option value="1"> MANUAL </option>
    		</select><span class="cabecera" style="color:#F00">*</span></div>
		<div class="divhead"><strong>RUT:</strong><input type="text" name="empresas_rut" id="empresas_rut" style="width:145px;height:30px ;background:#FF3;"  value="" ></div>
		<div class="divhead"><strong>RAZON SOCIAL:</strong><input type="text" name="rz_cliente" id="rz_cliente" style="width:500px;height:30px ;background:#FF3;"  value=""></div>
		<div class="divhead"><strong>DIRECCION:</strong><input type="text" name="dir_cliente" id="dir_cliente" style="width:500px;height:30px ;background:#FF3;"  value=""><span class="cabecera" style="color:#F00">*</span></div>
		<div class="divhead"><strong>REGION:</strong><input type="text" id="region" style="width:510px;height:30px ;background:#FF3;"  value=""></div>
		<div class="divhead"><strong>CIUDAD:</strong><input type="text" id="ciudad" style="width:350px;height:30px ;background:#FF3;"  name="gv_ciudad" value="" ></div>
		<div class="divhead"><strong>COMUNA:</strong><input type="text" id="comuna" style="width:350px;height:30px ;background:#FF3;"   value="" ></div>
		<div class="divhead"><strong>RESPONSABLE CUENTA:</strong><input type="text" id="resp" name="resp" style="width:350px;height:30px ;background:#FFF;" readonly="readonly" value=""></div>
		<div class="divhead"><strong>PER FACT.:</strong><select name="peri_tc_id" id="peri_tc_id" style="margin:0px 0px 0px 0px;width: 325px" >
					
				</select><span class="cabecera" style="color:#F00">*</span></div>
	</div>
	
	</div>
	
	<div class=" cuadroblanco" style="height:130px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CONTACTO</p></h3>
		
		<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>CONTACTO:</strong><select name="cont_id" id="cont_id" style="margin:0px 0px 0px 0px; width: 366px">
				
    		</select><span class="cabecera" style="color:#F00">*</span></div>
		<div class="divhead"><strong>E-MAIL:</strong><input type="text" id="cont_mail" name="cont_mail" style="width:350px;height:30px ;background:#FFF;" readonly="readonly" value=""></div>
		
		<div class="divhead"><strong>TELEFONO:</strong><input type="text" id="cont_phone" name="cont_phone" style="width:150px;height:30px ;background:#FFF;" readonly="readonly" value=""><input type="hidden" name="telefono_prev" id="telefono_prev"></div>
		<div class="divhead"><strong>CELULAR:</strong><input type="text" id="cont_phonec" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value=""></div>
		
	</div>
	
	</div>
	
	<div class=" cuadroblanco" style="height:235px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS SERVICIOS IMPRESION</p></h3>
		
		<div style="padding: 5px 5px 5px 5px;" class="divscroll scroll">
		<div class="divhead"><strong>SERV  1:</strong><input type="text" maxlength="53"  class="amarillo" style="width:600px;height:30px ;background:#FF3;" name="serv1" id="serv1" value="" autofocus="autofocus" ></div>
		<div class="divhead"><strong>VALOR 1:</strong><input type="text" maxlength="9" onkeydown="return validateNum(event)" onblur="cambiaDet()" class="amarillo" style="width:120px;height:30px ;background:#FF3;" name="val1" id="val1" value=""></div>
		<div class="divhead"><strong>SERV  2:</strong><input type="text" maxlength="53" class="amarillo" style="width:600px;height:30px ;background:#FF3;" name="serv2" id="serv2" value=""></div>
		<div class="divhead"><strong>VALOR 2:</strong><input type="text" maxlength="9" onkeydown="return validateNum(event)" onblur="cambiaDet()" class="amarillo" style="width:120px;height:30px ;background:#FF3;" name="val2" id="val2" value=""></div>
		<div class="divhead"><strong>SERV  3:</strong><input type="text" maxlength="53" class="amarillo" style="width:600px;height:30px ;background:#FF3;" name="serv3" id="serv3" value=""></div>
		<div class="divhead"><strong>VALOR 3:</strong><input type="text" maxlength="9" onkeydown="return validateNum(event)" onblur="cambiaDet()" class="amarillo" style="width:120px;height:30px ;background:#FF3;" name="val3" id="val3" value=""></div>
		<div class="divhead"><strong>SERV  4:</strong><input type="text" maxlength="53" class="amarillo" style="width:600px;height:30px ;background:#FF3;" name="serv4" id="serv4" value=""></div>
		<div class="divhead"><strong>VALOR 4:</strong><input type="text" maxlength="9" onkeydown="return validateNum(event)" onblur="cambiaDet()" class="amarillo" style="width:120px;height:30px ;background:#FF3;" name="val4" id="val4" value=""></div>
		<div class="divhead"><strong>SERV  5:</strong><input type="text" maxlength="53" class="amarillo" style="width:600px;height:30px ;background:#FF3;" name="serv5" id="serv5" value=""></div>
		<div class="divhead"><strong>VALOR 5:</strong><input type="text" maxlength="9" onkeydown="return validateNum(event)" onblur="cambiaDet()" class="amarillo" style="width:120px;height:30px ;background:#FF3;" name="val5" id="val5" value=""></div>
		
		<div class="divhead"><strong>SERV  6:</strong><input type="text" maxlength="53" class="amarillo" style="width:600px;height:30px ;background:#FF3;" name="serv6" id="serv6" value=""></div>
		<div class="divhead"><strong>VALOR 6:</strong><input type="text" maxlength="9" onkeydown="return validateNum(event)" onblur="cambiaDet()" class="amarillo" style="width:120px;height:30px ;background:#FF3;" name="val6" id="val6" value=""></div>
		<div class="divhead"><strong>SERV  7:</strong><input type="text" maxlength="53" class="amarillo" style="width:600px;height:30px ;background:#FF3;" name="serv7" id="serv7" value=""></div>
		<div class="divhead"><strong>VALOR 7:</strong><input type="text" maxlength="9" onkeydown="return validateNum(event)" onblur="cambiaDet()" class="amarillo" style="width:120px;height:30px ;background:#FF3;" name="val7" id="val7" value=""></div>
		<div class="divhead"><strong>SERV  8:</strong><input type="text" maxlength="53" class="amarillo" style="width:600px;height:30px ;background:#FF3;" name="serv8" id="serv8" value=""></div>
		<div class="divhead"><strong>VALOR 8:</strong><input type="text" maxlength="9" onkeydown="return validateNum(event)" onblur="cambiaDet()" class="amarillo" style="width:120px;height:30px ;background:#FF3;" name="val8" id="val8" value=""></div>
		<div class="divhead"><strong>SERV  9:</strong><input type="text" maxlength="53" class="amarillo" style="width:600px;height:30px ;background:#FF3;" name="serv9" id="serv9" value=""></div>
		<div class="divhead"><strong>VALOR 9:</strong><input type="text" maxlength="9" onkeydown="return validateNum(event)" onblur="cambiaDet()" class="amarillo" style="width:120px;height:30px ;background:#FF3;" name="val9" id="val9" value=""></div>
		<div class="divhead"><strong>SERV  10:</strong><input type="text" maxlength="53" class="amarillo" style="width:600px;height:30px ;background:#FF3;" name="serv10" id="serv10" value=""></div>
		<div class="divhead"><strong>VALOR 10:</strong><input type="text" maxlength="9" onkeydown="return validateNum(event)" onblur="cambiaDet()" class="amarillo" style="width:120px;height:30px ;background:#FF3;" name="val10" id="val10" value=""></div>
		<div class="divhead"><strong>SERV  11:</strong><input type="text" maxlength="53" class="amarillo" style="width:600px;height:30px ;background:#FF3;" name="serv11" id="serv11" value=""></div>
		<div class="divhead"><strong>VALOR 11:</strong><input type="text" maxlength="9" onkeydown="return validateNum(event)" onblur="cambiaDet()" class="amarillo" style="width:120px;height:30px ;background:#FF3;" name="val11" id="val11" value=""></div>
		<div class="divhead"><strong>SERV  12:</strong><input type="text" maxlength="53" class="amarillo" style="width:600px;height:30px ;background:#FF3;" name="serv12" id="serv12" value=""></div>
		<div class="divhead"><strong>VALOR 12:</strong><input type="text" maxlength="9" onkeydown="return validateNum(event)" onblur="cambiaDet()" class="amarillo" style="width:120px;height:30px ;background:#FF3;" name="val12" id="val12" value=""></div>
		<div class="divhead"><strong>SERV  13:</strong><input type="text" maxlength="53" class="amarillo" style="width:600px;height:30px ;background:#FF3;" name="serv13" id="serv13" value=""></div>
		<div class="divhead"><strong>VALOR 13:</strong><input type="text" maxlength="9" onkeydown="return validateNum(event)" onblur="cambiaDet()" class="amarillo" style="width:120px;height:30px ;background:#FF3;" name="val13" id="val13" value=""></div>
		<div class="divhead"><strong>SERV  14:</strong><input type="text" maxlength="53" class="amarillo" style="width:600px;height:30px ;background:#FF3;" name="serv14" id="serv14" value=""></div>
		<div class="divhead"><strong>VALOR 14:</strong><input type="text" maxlength="9" onkeydown="return validateNum(event)" onblur="cambiaDet()" class="amarillo" style="width:120px;height:30px ;background:#FF3;" name="val14" id="val14" value=""></div>
		
		</div>
	</div>
	<div class=" cuadroblanco" style="height:120px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>SUBTOTAL:</strong><input name="total" id="total" type="text" style="width:150px;height:30px ;background:#FFF;" value=""  readonly="readonly"></div>
		<div class="divhead"><strong>MONTO DESCUENTO:</strong><input type="text" name="desc"  id="desc" onkeydown="return validateNum(event)" class="amarillo" style="width:150px;height:30px ;background:#FF3;"  value="0"  maxlength="12" ></div>
		<div class="divhead"><strong>GLOSA DESCUENTO:</strong><input type="text" name="glosa_desc"  id="glosa_desc" class="amarillo" style="width:350px;height:30px ;background:#FF3;" maxlength="30"  value=""  ></div>
		<div class="divhead"><strong id="netolabel">AFECTO:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;" readonly="readonly" value="" id="netoneto" name="netoneto"></div>
		
		<div class="divhead"><strong>IVA 19%:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;" readonly="readonly" value="" id="iva" name="iva"></div>
		<div class="divhead"><strong>TOTAL:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" readonly="readonly" value="" id="totaltotal" name="totaltotal"></div>
		
	</div>
	</div>
	
	<div class=" cuadroblanco" style="height:50px;margin-top: 5px">
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

	<div class=" cuadroblanco" style="height:120px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>N DE IMPRESIONES:</strong><input type="text" name="fac_servim_n_impresiones" id="fac_servim_n_impresiones" onkeydown="return validateNum(event)" class="amarillo" maxlength="10" style="width:130px;height:30px ;background:#FF3;" value=""><span class="cabecera" style="color:#F00">*</span></div>
		<div class="divhead"><strong>TIPO CAMBIO:</strong><input type="text" name="fac_servim_tcambio" id="fac_servim_tcambio" class="amarillo" maxlength="9" style="width:120px;height:30px ;background:#FF3;text-align: right;" value="" onkeydown="return validateNum(event)"><span class="cabecera" style="color:#F00">*</span></div>
		<div class="divhead"><strong>OBSERVACIONES:</strong><input type="text" name="fac_servim_obs" id="fac_servim_obs" class="amarillo" maxlength="64" style="width:720px;height:30px ;background:#FF3;" value=""><span class="cabecera" style="color:#F00">*</span></div>
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
			<input type="hidden" name="giro_prev" id="giro_prev">
			<input type="hidden" name="rz_prev" id="rz_prev">
			<input type="hidden" name="tipodte_prev" id="tipodte_prev">
				<input type="hidden" name="detalle_descuento_prev" id="detalle_descuento_prev">
			
			<input type="hidden" name="detalle_prev[]" id="detalle_prev1">
			<input type="hidden" name="detalle_prev[]" id="detalle_prev2">
			<input type="hidden" name="detalle_prev[]" id="detalle_prev3">
			<input type="hidden" name="detalle_prev[]" id="detalle_prev4">
			<input type="hidden" name="detalle_prev[]" id="detalle_prev5">
			<input type="hidden" name="detalle_prev[]" id="detalle_prev6">
			<input type="hidden" name="detalle_prev[]" id="detalle_prev7">
			<input type="hidden" name="detalle_prev[]" id="detalle_prev8">
			<input type="hidden" name="detalle_prev[]" id="detalle_prev9">		
			<input type="hidden" name="detalle_prev[]" id="detalle_prev10">
			<input type="hidden" name="detalle_prev[]" id="detalle_prev11">
			<input type="hidden" name="detalle_prev[]" id="detalle_prev12">
			<input type="hidden" name="detalle_prev[]" id="detalle_prev13">
			<input type="hidden" name="detalle_prev[]" id="detalle_prev14">
	
			<input type="hidden" name="ref_prev" id="ref_prev" value="">
			<input type="hidden" value="" name="fec_prev" id="fec_prev">
			<button type="submit" name="previ" id="previ" class="btn btn-success" onclick="previsualizar()"  >PREVISUALIZAR</button>
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

    <script src="lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>
    
  </body>
</html>


