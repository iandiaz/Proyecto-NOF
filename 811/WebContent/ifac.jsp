<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 		

String oc_id =(String)request.getAttribute("OC_ID");
String FACT_FECHA=(String)request.getAttribute("FACT_FECHA");
String CLPR_ID=(String)request.getAttribute("CLPR_ID");

String FACT_CONDICIONES=(String)request.getAttribute("FACT_CONDICIONES");
String CLPR_NOMBRE_FANTASIA=(String)request.getAttribute("CLPR_NOMBRE_FANTASIA");
String CLPR_GIRO=(String)request.getAttribute("CLPR_GIRO");
String FACT_TIPO_CAMBIO=(String)request.getAttribute("FACT_TIPO_CAMBIO");
String CONT_NOMBRE=(String)request.getAttribute("CONT_NOMBRE");
String CONT_TELEFONO=(String)request.getAttribute("CONT_TELEFONO");
//String FACT_TOTAL_TEXTO=(String)request.getAttribute("FACT_TOTAL_TEXTO");
//String FACT_OBS=(String)request.getAttribute("FACT_OBS");
String FACT_ESTADO=(String)request.getAttribute("FACT_ESTADO");
String FACT_FECHA_EMISION=(String)request.getAttribute("FACT_FECHA_EMISION");
String USU_INICIAL=(String)request.getAttribute("USU_INICIAL");

String empresas_rut=(String)request.getAttribute("CLPR_RUT")+"-"+(String)request.getAttribute("CLPR_DV");
String empresas_rut_ar[]=empresas_rut.split("-");
java.text.NumberFormat nff = java.text.NumberFormat.getInstance();
String valRut = nff.format(Integer.parseInt(empresas_rut_ar[0]));
empresas_rut=valRut+"-"+empresas_rut_ar[1];

String CLPR_RAZON_SOCIAL=(String)request.getAttribute("CLPR_RAZON_SOCIAL");
String DIRE_DIRECCION=(String)request.getAttribute("DIRE_DIRECCION");
String DIRE_CIUDAD=(String)request.getAttribute("DIRE_CIUDAD");
String REGI_NOMBRE=(String)request.getAttribute("REGI_NOMBRE");
String PERS_NOMBRE=(String)request.getAttribute("USU_NOMBRE");
String COMU_NOMBRE=(String)request.getAttribute("COMU_NOMBRE");

String neto= (String)request.getAttribute("NETO");
	
String iva=(String)request.getAttribute("IVA");
	
String totalfinal=(String)request.getAttribute("TOTAL");

String[] ar_productos =(String[])request.getAttribute("ar_productos");
String[] ar_dire =(String[])request.getAttribute("ar_dire");

String Usu_nom=(String)request.getAttribute("usuname");

    %>
<!DOCTYPE html>
<html lang="en"><head>
    <meta charset="utf-8">
    <title>New Office -FACTURA DE COMPRA AFECTA - EXENTA ACTIVO</title>
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
    
    <script>
    function previsualizar(){
		
		$("#formdata").attr("action", "http://186.67.10.115:81/NOF/factura_formato_compra.php");
		$("#total_prev").val($("#totaltotal").val());
		$("#iva_prev").val($("#iva").val());
		$("#neto_prev").val($("#total").val());
		
		$("#dir_prev").val($("#dire_id").find('option:selected').text());
		$("#obs_prev").val('<%=USU_INICIAL%>-'+$("#obs1").val()+'-'+$("#condiciones").val());
		$("#obs_prev2").val($("#obs2").val());
		
		$("#comuna_prev").val($("#gv_comuna").val());
		$("#ciudad_prev").val($("#gv_ciudad").val());
		$("#telefono_prev").val($("#cont_phone").val());
		$("#rut_prev").val($("#empresas_rut").val());
		$("#rz_prev").val($("#rz_cliente").val());
		
		$("#ref_prev").val($("#ref").val());
		$("#giro_prev").val($("#empresas_giro").val());
		
		$("#dsco_prev").val($("#desc").val());
		
		
	}
	
	function graba(){
		
		$("#formdata").attr("action", "")
	}
    
    $( document ).ready(function() {
                    // Handler for .ready() called.
                    
                   
                    $('#total').on('blur', function() {
                    	$('#perivanoret').val(19-$('#ivap').val());
                    	$('#iva').val(Math.round((Number($('#total').val())-Number($('#desc').val()))*Number($('#ivap').val())/100));
                    	
                    	$('#valivanoret').val(Math.round((Number($('#total').val())-Number($('#desc').val()))*Number($('#perivanoret').val())/100));
                    	
                    	$('#totaltotal').val(Math.round( (Number($('#total').val())-Number($('#desc').val()))+Number($('#valivanoret').val())));
                    	$('#netoneto').val(Math.round( (Number($('#total').val())-Number($('#desc').val()))));
                    	
   					 });
                    
                    $('#desc').on('blur', function() {
                    	$('#total').blur();
                            
   					 });
                   
                    $('#tipodte1, #tipodte11, #tipodte2, #tipodte22').on('change', function() {
                    	$('#total').blur();
                            
   					 });
                    $('#ivap').on('blur', function() {
                    	$('#total').blur();
                            
   					 });
                    
                  
    });
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
	  <p>N811.I.02.001</p>
	</div>
	<form method="get" action="iperusu" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/811/ifac_search'">VOLVER</button> 
	<div align="center" class="title">
		<p >INGRESAR FACTURA DE COMPRA AFECTA - EXENTA ACTIVO</p>
	</div>
</div>
<div class="content" >
  <form action="ifac" name="form1" id="formdata" method="post">
  <input type="hidden" name="oc_id" value="<%=oc_id%>" >
	<div class=" cuadroblanco" style="height:110px;">
		<div class="divhead"><strong>NRO.OC:</strong><input  type="text" style="width:100px;height:30px ;background:#FFF;" disabled="disabled" value="<%=oc_id%>"></div>
		<div class="divhead"><strong>FOLIO:</strong><input  type="text" style="width:100px;height:30px ;background:#FFF;" disabled="disabled" value="ND"></div>
		<div class="divhead"><strong>FECHA:</strong><input  type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=FACT_FECHA%>"></div>
		<div class="divhead"><strong>CONDICIONES:</strong><input id="condiciones" type="text" style="width:380px;height:30px ;background:#FFF;" readonly="readonly" value="<%=FACT_CONDICIONES%>"></div>
		<div class="divhead"><strong>EMISOR:</strong><input type="text" style="width:90px;height:30px ;background:#FFF;" disabled="disabled" value="BIRT"></div>
		<div class="divhead"><strong>ESTADO SII:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="NO ENVIADA"></div>
		<div class="divhead"><strong>TIPO DOCUMENTO:</strong><input type="radio" name="tipodte" value="46" checked="checked">(46) FACT COMPRA ELECT</div>
	</div>
	<br>
	<div class=" cuadroblanco" style="height:270px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS PROVEEDOR</p></h3>
		<div class="divhead"><strong>RAZON SOCIAL:</strong><input type="text" id="rz_cliente" style="width:600px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CLPR_RAZON_SOCIAL%>"><input type="hidden" name="CLPR_ID" value="<%=CLPR_ID%>"></div>
		<div class="divhead"><strong>RUT:</strong><input type="text" name="empresas_rut" id="empresas_rut" style="width:145px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_rut%>" ></div>
		<div class="divhead"><strong>DIRECCION:</strong>
		<select name="dire_id" id="dire_id" style="width:550px;height:30px;">
		<%        
			for(int i =0; i<ar_dire.length; i++){
       	 	String[] DireDato = ar_dire[i].split("/");
       	 %>
       	 	<option value="<%=DireDato[0]%>"><%=DireDato[1]%></option>
       	 <% } %>
       	 </select>
       	 </div>
		<div class="divhead"><strong>COMUNA:</strong><input type="text" style="width:200px;height:30px ;background:#FFF;" disabled="disabled" value="<%=COMU_NOMBRE%>" ></div>		
		<div class="divhead"><strong>CIUDAD:</strong><input type="text" id="gv_ciudad" style="width:250px;height:30px ;background:#FFF;" disabled="disabled" value="<%=DIRE_CIUDAD%>" ></div>
		<div class="divhead"><strong>REGION:</strong><input type="text" id="gv_comuna" style="width:350px;height:30px ;background:#FFF;" disabled="disabled" value="<%=REGI_NOMBRE%>"></div>
		
		<div class="divhead"><strong>GIRO:</strong><input type="text" id="empresas_giro" style="width:600px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CLPR_GIRO%>"></div>
		
		<div class="divhead"><strong>RESPONSABLE:</strong><input type="text" style="width:400px;height:30px ;background:#FFF;" disabled="disabled" value="<%=PERS_NOMBRE%>"></div>
		<div class="divhead"><strong>TIPO CAMBIO:</strong><input  type="text" style="width:120px;height:30px ;background:#FFF;" disabled="disabled" value="<%=FACT_TIPO_CAMBIO%>"></div>
		<div class="divhead"><strong>CONTACTO:</strong><input type="text" style="width:400px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CONT_NOMBRE%>"></div>
		<div class="divhead"><strong>TELEFONO:</strong><input type="text" id="cont_phone" style="width:230px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CONT_TELEFONO%>"></div>
	</div> 
	<br>
	<div class=" cuadroblanco" style="height:200px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS PRODUCTOS A FACTURAR</p></h3>
		<table class="table">
			<thead style="border: 1px solid black;">
			   <tr style="background-color:#0101DF;color:#FFFFFF">
				<td width="80" style="font-size:20px"><strong>ID</strong></td>
				<td width="750" style="font-size:20px"><strong>PN/TLI - DESCRIPCION</strong></td>
				<td width="250" style="font-size:20px"><strong>VALOR</strong></td>
			  </tr>  
			<tbody id="fbody" class="scroll" style="border: 1px solid black">
			 <%   
			 String des;
        for(int i =0; i<ar_productos.length; i++){
       	 String[] ProdDato = ar_productos[i].split("/");
       	 String monto = nff.format(Integer.parseInt(ProdDato[3])).replace(",",".");
       	 if(ProdDato[2].length()>35){
       		 des = ProdDato[2].substring(0,36);
       	 }else{
       		 des = ProdDato[2];
       	 }
       	 %>
       		
			<tr class="hov">
				<td width="80">
					<%=ProdDato[0]%><input type="hidden" name="recepcionesselec[]" value="<%=ProdDato[0]%>;<%=ProdDato[1]%> - <%=des%>;<%=ProdDato[3]%>;<%=ProdDato[4]%>" >
					<input type="hidden" name="detalle_prev[]" value="<%=ProdDato[0]%>;<%=ProdDato[1]%> - <%=des%>;1;<%=ProdDato[3]%>;0;<%=ProdDato[3]%>">
			
				</td>
				<td width="750"><%=ProdDato[1]%> - <%=des%></td>
				<td width="250">$<%=monto%></td>
			</tr>
			
       	<% } %>
			</tbody>
			  </tr>
		  </table>
	</div>

	<div class=" cuadroblanco" style="height:160px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>SUBTOTAL:</strong><input  name="total" id="total" type="text" style="width:150px;height:30px ;background:#FFF;" readonly="readonly" value="<%=neto%>"></div>
		<div class="divhead"><strong>MONTO DESCUENTO:</strong><input type="text" name="desc"  id="desc"  onkeydown="return validateNum(event)" class="amarillo" style="width:150px;height:30px ;background:#FF3;" maxlength="12"  value="0" required="required"   ></div>
		<div class="divhead"><strong>GLOSA DESCUENTO:</strong><input type="text"  name="glosa_desc"  id="glosa_desc" class="amarillo" style="width:600px;height:30px ;background:#FF3;" maxlength="53" value=""  required="required" ></div>
		
		<div class="divhead"><strong>NETO:</strong><input id="netoneto" name="netoneto" type="text" style="width:150px;height:30px ;background:#FFF;" readonly="readonly" value="<%=neto%>"></div>
		<div class="divhead"><strong>%IVA RETENIDO:</strong><input type="text" onKeyPress="if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false;"  style="width:120px;height:30px ;background:#FF3;" class="amarillo" value="0" id="ivap" name="ivap" > $<input type="text" readonly="readonly" style="width:120px;height:30px ;background:#FFF;" value="0" id="iva" name="iva" ></div>
		<div class="divhead"><strong>%IVA NO RETENIDO:</strong><input type="text" onKeyPress="if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false;"  style="width:120px;height:30px ;background:#FFF;"  value="19" id="perivanoret" name="perivanoret" readonly="readonly"  > $<input type="text" readonly="readonly" style="width:120px;height:30px ;background:#FFF;"  value="<%=iva%>" id="valivanoret" name="valivanoret"></div>
		<div class="divhead"><strong>TOTAL:</strong><input id="totaltotal" name="totaltotal" type="text" style="width:150px;height:30px ;background:#FFF;" readonly="readonly" value="<%=totalfinal%>"></div>
		
	</div>
	</div>

	<div class=" cuadroblanco" style="height:40px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>ESTADO EN BIRT:</strong><input type="text" value="<%=FACT_ESTADO%>" style="width:200px;height:30px ;background:#FFF;" disabled="disabled" ></div>
		<div class="divhead"><strong>FECHA EMISION:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=FACT_FECHA_EMISION%>"></div>
		<div class="divhead"><strong>EMISOR:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;" disabled="disabled" value="<%=USU_INICIAL%>"></div>
	</div>
	</div>

	<div class=" cuadroblanco" style="height:120px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>FORMA DE DESPACHO:</strong><input type="text" name="obs1" id="obs1" class="amarillo" maxlength="255" style="width:720px;height:30px ;background:#FF3;" value=""></div>
		<div class="divhead"><strong>OBSERVACION:</strong><input type="text" name="obs2" id="obs2" class="amarillo" maxlength="255" style="width:720px;height:30px ;background:#FF3;" value=""></div>
		<div class="bgrabar">
		
			<input type="hidden" name="total_prev" id="total_prev" value="0">
		
			<input type="hidden" name="iva_prev" id="iva_prev" value="0">
			<input type="hidden" name="neto_prev" id="neto_prev" value="0">
			<input type="hidden" name="dsco_prev" id="dsco_prev" value="0">
			
			<input type="hidden" name="dir_prev" id="dir_prev" value="">
			<input type="hidden" name="obs_prev" id="obs_prev" value="">
			<input type="hidden" name="giro_prev" id="giro_prev">
			<input type="hidden" name="rz_prev" id="rz_prev">
			
			<input type="hidden"  name="ciudad_prev" id="ciudad_prev">
			<input type="hidden"  name="comuna_prev" id="comuna_prev">
			<input type="hidden"  name="rut_prev"  id="rut_prev">
			<input type="hidden" name="telefono_prev" id="telefono_prev">
			<input type="hidden"  name="ref_prev" id="ref_prev">
			<input type="hidden" value="<%=FACT_FECHA%>" name="fec_prev">
			<input type="hidden" name="obs_prev2" id="obs_prev2" value="">
			
			
		<button type="submit" name="previ" id="previ" class="btn btn-success" onclick="previsualizar()"  >PREVISUALIZAR</button>
          <button type="submit" name="grabar" id="grabar" class="btn btn-success"  onclick="graba()" >GRABAR</button>
       </div> 
      </div>
	</div>
</form>
</div><br><br><br>
<div class="footerapp">               
	<p style="float: left;"><i class="icon-user"></i><strong><%=Usu_nom %></strong></p>
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


