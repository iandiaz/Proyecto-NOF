<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 	

String empresas_id="";
String contactos_id="";


String guia_des_tras_normal_id =(String)request.getAttribute("guia_des_tras_normal_id");
String id_dte =(String)request.getAttribute("id_dte");
String dte =(String)request.getAttribute("dte");
String emisor =(String)request.getAttribute("emisor");
String nom1 =(String)request.getAttribute("nom1");
String rut1 =(String)request.getAttribute("rut1");
String id1 =(String)request.getAttribute("id1");
String dir1 =(String)request.getAttribute("dir1");
String reg1 =(String)request.getAttribute("reg1");
String com1 =(String)request.getAttribute("com1");
String ciu1 =(String)request.getAttribute("cui1");
String nom2 =(String)request.getAttribute("nom2");
String rut2 =(String)request.getAttribute("rut2");
String id2 =(String)request.getAttribute("id2");
String dir2 =(String)request.getAttribute("dir2");
String reg2 =(String)request.getAttribute("reg2");
String com2 =(String)request.getAttribute("com2");
String cui2 =(String)request.getAttribute("cui2");
String CONT_NOMBRE =(String)request.getAttribute("CONT_NOMBRE");
String CONT_TELEFONO =(String)request.getAttribute("CONT_TELEFONO");
String CONT_TELEFONO2 =(String)request.getAttribute("CONT_TELEFONO2");
String CONT_EMAIL =(String)request.getAttribute("CONT_EMAIL");
String RESP_NOMBRE =(String)request.getAttribute("PERS_NOMBRE");

String obs =(String)request.getAttribute("obs");
String obs2 =(String)request.getAttribute("obs2");
String fecha =(String)request.getAttribute("fecha");
String g_afecta=(String)request.getAttribute("g_afecta");
String Usu_nom=(String)request.getAttribute("usuname");

String[] ar_guias =(String[]) request.getAttribute("ar_guias");
String RESPONSABLE =(String)request.getAttribute("RESPONSABLE");
String numtick =(String)request.getAttribute("numtick");

String subtotal=(String)request.getAttribute("subtotal");
String neto=(String)request.getAttribute("neto");
String total=(String)request.getAttribute("total");
String desc=(String)request.getAttribute("descuento");
String gr_glosa_desc=(String)request.getAttribute("glosadescuento");
String iva=(String)request.getAttribute("iva");

java.text.NumberFormat nff = java.text.NumberFormat.getInstance();
subtotal=nff.format(Integer.parseInt(subtotal));
neto=nff.format(Integer.parseInt(neto));
total=nff.format(Integer.parseInt(total));
desc=nff.format(Integer.parseInt(desc));
iva=nff.format(Integer.parseInt(iva));

String tipo_dteref=(String)request.getAttribute("tipo_dteref");	
String folioref=(String)request.getAttribute("folioref");	
String fec_ref=(String)request.getAttribute("fec_ref");
String empresas_giro=(String)request.getAttribute("empresas_giro");

if(tipo_dteref!=null && !tipo_dteref.equals("null")){
	
	if(tipo_dteref.equals("801")) tipo_dteref="ORDEN DE COMPRA";
	if(tipo_dteref.equals("802")) tipo_dteref="NOTA DE PEDIDO";
	if(tipo_dteref.equals("803")) tipo_dteref="CONTRATO";
	if(tipo_dteref.equals("HES")) tipo_dteref="HOJA DE ENTRADA";
}


    %>
<!DOCTYPE html>
<html lang="en"><head>
    <meta charset="utf-8">
    <title>New Office - Guia de Despacho Traslado Activos Normal</title>
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
		
		$("#dsco_prev").val($("#desc").val());
		
		
		
		var indice =0;
				
		if($("#serv1").val()!=""){
			var servicio1=(indice+1)+";"+$("#serv1").val()+";1"+";"+$("#val1").val()+";0;"+$("#val1").val();
			indice++;
			$("#detalle_prev1").val(servicio1);
			neto+=Number($("#val1").val());
		}
		 
		if($("#serv2").val()!=""){
			var servicio2=(indice+1)+";"+$("#serv2").val()+";1"+";"+$("#val2").val()+";0;"+$("#val2").val();
			indice++;
			$("#detalle_prev2").val(servicio2);
			neto+=Number($("#val2").val());
		}
		if($("#serv3").val()!=""){
			var servicio3 = (indice+1)+";"+$("#serv3").val()+";1"+";"+$("#val3").val()+";0;"+$("#val3").val();
			indice++;
			$("#detalle_prev3").val(servicio3);
			neto+=Number($("#val3").val());
		}
		if($("#serv4").val()!=""){
			var servicio4= (indice+1)+";"+$("#serv4").val()+";1"+";"+$("#val4").val()+";0;"+$("#val4").val();
			indice++;
			$("#detalle_prev4").val(servicio4);
			neto+=Number($("#val4").val());
		}
		if($("#serv5").val()!=""){
			var servicio5= (indice+1)+";"+$("#serv5").val()+";1"+";"+$("#val5").val()+";0;"+$("#val5").val();
			indice++;
			$("#detalle_prev5").val(servicio5);
			neto+=Number($("#val5").val());
		}
		
		if($("#dsco_prev").val()>0){
			//generamos linea de descuento para previsualizacion 
			
			$("#detalle_descuento_prev").val($("#glosa_desc").val()+" $"+$("#desc").val());
			
		}
		
		
		if($("#tipo_dteref").val()!="NINGUNA"){
			$("#ref_prev").val($("#tipo_dteref").val()+": Nro. "+$("#folioref").val()+" del "+$("#datepickerref").val());
				
		}
	
		
		$("#neto_prev").val($("#subtotal").val());
		$("#iva_prev").val($("#iva").val());
		$("#total_prev").val($("#total").val());
		
		
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
	  <p>N823.C.02.001</p>
	</div>
	<form method="get" action="iperusu" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/823/cguia_search'">VOLVER</button> 
	<div align="center" class="title">
		<p >CONSULTAR GUIA DE DESPACHO TRASLADO ACTIVOS NORMAL</p>
	</div>
</div>
<div class="content" >
<form action="" name="form1" id="form1" method="post" >
  <input type="hidden" name="guia_des_tras_normal_id" value="<%=guia_des_tras_normal_id%>" >
	<div class=" cuadroblanco" style="height:80px;">
	<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>NRO.GUIA:</strong><input  type="text" style="width:100px;height:30px ;background:#FFF;" disabled="disabled" value="ND"></div>
		<div class="divhead"><strong>FECHA:</strong><input  type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=fecha%>"></div>
		<div class="divhead"><strong>CONTROL GUIA:</strong><input type="text" maxlength="30" style="width:350px;height:30px ;background:#FFF" disabled="disabled" value="POR EMITIR" ></div>
		<div class="divhead"><strong>EMISOR:</strong><input type="text" maxlength="30" style="width:135px;height:30px ;background:#FFF;" disabled value="<%=emisor%>"></div>
		<div class="divhead"><strong>ESTADO SII:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=dte%>"></div>
		<div class="divhead" style="vertical-align:middle;padding: 8px 0 8px 0;" ><strong>AFECTA:</strong><input type="checkbox" value="1" name="g_afecta" id="g_afecta"  class="searchInput" <% if(g_afecta.equals("1")){%> checked="checked" <%} %> disabled="disabled" ></div>
	</div>
	</div>

	<div class=" cuadroblanco" style="height:160px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS ORIGEN</p></h3>
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>RAZON SOCIAL:</strong><input type="text" style="width:600px;height:30px ;background:#FFF;" disabled="disabled" value="<%=nom1%>"></div>
		<div class="divhead"><strong>RUT:</strong><input type="text" style="width:145px;height:30px ;background:#FFF;" disabled="disabled" value="<%=rut1%>" ></div>
		<div class="divhead"><strong>COD CLIENTE:</strong><input type="text" style="width:70px;height:30px ;background:#FFF;" disabled="disabled" value="<%=id1%>" ></div>
		<div class="divhead"><strong>DIRECCION:</strong><input type="text" style="width:500px;height:30px ;background:#FFF;" disabled="disabled" value="<%=dir1%>"></div>
		<div class="divhead"><strong>REGION:</strong><input type="text" style="width:450px;height:30px ;background:#FFF;" disabled="disabled" value="<%=reg1%>"></div>
		<div class="divhead"><strong>COMUNA:</strong><input type="text" style="width:250px;height:30px ;background:#FFF;" disabled="disabled" value="<%=com1%>" ></div>		
		<div class="divhead"><strong>CIUDAD:</strong><input type="text" style="width:250px;height:30px ;background:#FFF;" disabled="disabled" value="<%=ciu1%>" ></div>
	</div>
	</div> 	
	<div class=" cuadroblanco" style="height:200px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS DESTINO</p></h3>
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>RAZON SOCIAL:</strong><input type="text" style="width:600px;height:30px ;background:#FFF;" disabled="disabled" value="<%=nom2%>"></div>
		<div class="divhead"><strong>RUT:</strong><input type="text" style="width:145px;height:30px ;background:#FFF;" disabled="disabled" value="<%=rut2%>" ></div>
		<div class="divhead"><strong>COD CLIENTE:</strong><input type="text" style="width:70px;height:30px ;background:#FFF;" disabled="disabled" value="<%=id2%>" ></div>
		<div class="divhead"><strong>DIRECCION:</strong><input type="text" style="width:500px;height:30px ;background:#FFF;" disabled="disabled" value="<%=dir2%>"></div>
		<div class="divhead"><strong>REGION:</strong><input type="text" style="width:450px;height:30px ;background:#FFF;" disabled="disabled" value="<%=reg2%>"></div>
		<div class="divhead"><strong>COMUNA:</strong><input type="text" style="width:250px;height:30px ;background:#FFF;" disabled="disabled" value="<%=com2%>" ></div>		
		<div class="divhead"><strong>CIUDAD:</strong><input type="text" style="width:250px;height:30px ;background:#FFF;" disabled="disabled" value="<%=cui2%>" ></div>
		<div class="divhead"><strong>RESPONSABLE CUENTA:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" disabled="disabled" value="<%=RESPONSABLE  %>"></div>
		</div>
	</div> 

	<div class=" cuadroblanco" style="height:120px;margin-top: 5px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CONTACTO</p></h3>
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>CONTACTO:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" disabled value="<%=CONT_NOMBRE%>"></div>
		<div class="divhead"><strong>E-MAIL:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CONT_EMAIL%>"></div>
		<div class="divhead"><strong>TELEFONO:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CONT_TELEFONO%>"></div>
		<div class="divhead"><strong>CELULAR:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CONT_TELEFONO2%>"></div>
		</div>
	</div>

	<div class=" cuadroblanco" style="height:220px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS ACTIVOS TRASLADOS</p></h3>
		<table class="table">
			<thead style="border: 1px solid black;">
			   <tr style="background-color:#0101DF;color:#FFFFFF">
				<td width="80" style="font-size:20px"><strong>ID</strong></td>
				<td width="850" style="font-size:20px"><strong>DESCRIPCION</strong></td>
				<td width="200" style="font-size:20px"><strong>SERIE</strong></td>
				<td width="200" style="font-size:20px"><strong>UBICACON</strong></td>
				<td width="200" style="font-size:20px"><strong>FECHA</strong></td>
				<td width="200" style="font-size:20px"><strong>VALOR</strong></td>
			  </tr>  
			<tbody id="fbody" class="scroll" style="border: 1px solid black">
			<%        
				 String c="0";
					for(int i =0; i<ar_guias.length; i++){
						c="1";
				    	String[] Guias = ar_guias[i].split(";;");
			%>
			<tr class="hov">
				<td width="80"><%=Guias[0]%><input type="hidden" name="detalle_prev[]" value="<%=Guias[0]%>;<%=Guias[1]%> - <%=Guias[2]%> - <%=Guias[6]%>;1;<%=Guias[7]%>;0;<%=Guias[7]%>"></td>
				<td width="850"><%=Guias[1]%> - <%=Guias[2]%> - <%=Guias[3]%></td>
				<td width="200"><%=Guias[4]%></td>
				<td width="200"><%=Guias[5]%></td>
				<td width="200"><%=Guias[6]%></td>
				<td width="200"><%=Guias[7]%></td>
			</tr>
			<% } %>
			</tbody>
		
		  </table>
	</div>
		<div class=" cuadroblanco" style="height:80px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>SUBTOTAL:</strong><input type="text" id="subtotal" style="width:150px;height:30px ;background:#FFF;text-align: right;" readonly="readonly" value="<%=subtotal%>"></div>
		<div class="divhead"><strong>MONTO DESCUENTO:</strong><input type="text" name="desc"  id="desc"  style="width:150px;height:30px ;background:#FFF;text-align: right;"  value="<%=desc %>" readonly="readonly" disabled="disabled" ></div>
		<div class="divhead" ><strong>GLOSA DESCUENTO:</strong><input type="text"  id="glosa_desc" style="width:350px;height:30px ;background:#FFF;"  value="<%=gr_glosa_desc %>" readonly="readonly" ></div>
		<div class="divhead"><strong>NETO:</strong><input type="text" id="neto" style="width:130px;height:30px ;background:#FFF;text-align: right;" readonly="readonly" value="<%=neto %>" ></div>
		
		<div class="divhead"><strong>IVA 19%:</strong><input type="text" id="iva" style="width:120px;height:30px ;background:#FFF;text-align: right;" readonly="readonly" value="<%=iva%>"></div>
		<div class="divhead"><strong>TOTAL:</strong><input type="text" id="total" style="width:150px;height:30px ;background:#FFF;text-align: right;" readonly="readonly" value="<%=total%>"></div>
		
	</div>
	</div>
		<div class=" cuadroblanco" style="height:40px;margin-top: 5px">
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>TIPO REF:</strong><input type="text" maxlength="36" style="width:200px;height:30px ;background:#FFF;" value="<%=tipo_dteref%>" readonly="readonly" id="tipo_dteref"></div>
		<div class="divhead"><strong>FOLIO REF:</strong><input type="text" name="folioref" id="folioref" maxlength="36" style="width:410px;height:30px ;background:#FFF;" value="<%=folioref%>" readonly="readonly"></div>
		<div class="divhead"><strong>FECHA REF:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;" name="fec_ref" id="datepickerref"  readonly="readonly" value="<%=fec_ref%>" ></div>
	</div>
		
	</div>
	<div class=" cuadroblanco" style="height:160px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>NUMERO DE TICKET:</strong><input type="text" name="ref" value="<%=numtick %>" style="width:750px;height:30px ;background:#FFF;" disabled></div>
		<div class="divhead"><strong>FORMA TRASLADO:</strong><input type="text" name="obs" value="<%=obs %>" style="width:720px;height:30px ;background:#FFF;" disabled></div>
		<div class="divhead"><strong>MOTIVO:</strong><input type="text" name="obs" value="SOLO TRASLADO NO CONSTITUYE VENTA" style="width:720px;height:30px ;background:#FFF;" disabled></div>
		</div>
		<div class="bgrabar">
		
		<input type="hidden" name="empresa_emisora_nombre" id="empresa_emisora_nombre"  value="<%=emisor %>" >
			<input type="hidden" name="cont_nombre" id="cont_nombre" value="<%=CONT_NOMBRE %>" >
		
			<input type="hidden"  name="rut_prev"  id="rut_prev" value="<%=rut2 %>">
			<input type="hidden" value="<%=fecha%>" name="fec_prev" id="fec_prev">
			<input type="hidden" name="total_prev" id="total_prev" value="0">
			<input type="hidden" name="iva_prev" id="iva_prev" value="0">
			<input type="hidden" name="neto_prev" id="neto_prev" value="0">
			<input type="hidden" name="dsco_prev" id="dsco_prev" value="0">
			
			<input type="hidden"  name="ref_prev" id="ref_prev">
			
			<input type="hidden" name="dir_prev" id="dir_prev" value="<%=dir2 %>">
			<input type="hidden"  name="ciudad_prev" id="ciudad_prev" value="<%=cui2%>">
			<input type="hidden"  name="comuna_prev" id="comuna_prev" value="<%=com2%>">
			<input type="hidden" name="obs_prev" id="obs_prev" value="<%=obs%>">
			<input type="hidden" name="obs_prev2" id="obs_prev2" value="<%=CONT_NOMBRE %>-<%=CONT_EMAIL %>">
			<input type="hidden" name="obs_prev3" id="obs_prev3" value="<%=obs2%>">
			<input type="hidden" name="giro_prev" id="giro_prev" value="<%=empresas_giro%>" >
			<input type="hidden" name="rz_prev" id="rz_prev" value="<%=nom2%>">
			<input type="hidden" name="tipodte_prev" id="tipodte_prev" value="52">
			<input type="hidden" name="telefono_prev" id="telefono_prev" value="<%=CONT_TELEFONO%>">
			
			
				<input type="hidden" name="detalle_descuento_prev" id="detalle_descuento_prev">
				<input type="hidden" name="tipo_doc_titulo_prev" id="tipo_doc_titulo_prev" value="GUIA ELECTRONICA">
			<input type="hidden" name="afecta_prev" id="afecta_prev" value="<%=g_afecta%>">
		
		<button type="submit" name="previ" id="previ" class="btn btn-success" onclick="previsualizar()"  >PREVISUALIZAR</button>
          
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


