<%@page import="VO.FacturaDetalleVO"%>
<%@page import="VO.FacturaVO"%>
<%@page import="VO.ActivoVO"%>
<%@page import="VO.UbicacionVO"%>
<%@page import="VO.EstructuraCobroVO"%>
<%@page import="VO.AnexoContratoVO"%>
<%@page import="VO.PeriodoTcVO"%>
<%@page import="VO.DireccionVO"%>
<%@page import="VO.EmpresaVO"%>
<%@page import="java.util.ArrayList"%>
<% 	
String modname=(String)request.getAttribute("modname");
String Usu_nom=(String)request.getAttribute("usuname");	

FacturaVO factura = (FacturaVO)request.getAttribute("factura");
ArrayList<FacturaDetalleVO> detalles_factura = (ArrayList<FacturaDetalleVO>)request.getAttribute("detalles_factura");


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






String peri_tc_correlativo_actual=null;
String peri_tc_fechad_actual=null;
String peri_tc_fechah_actual=null;




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
    
    $( document ).ready(function() {

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
	  <p>N703.E.02.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/703/E01'">VOLVER</button> 
	<div align="center" class="title">
		<p>ELIMINAR <%=modname %></p>
	</div>
</div>
<div class="content" >
  <form action="" name="form1" method="post" id="form1" > 
  <input type="hidden" name="factura_id" value="${factura.getId()}"> 
	<div class=" cuadroblanco" style="height:50px;padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>FECHA:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;" value="${factura.getFecha()}" readonly="readonly"></div>
		<div class="divhead"><strong>COND PAGO:</strong><input type="text" maxlength="30" style="width:350px;height:30px ;background:#FFF;" readonly="readonly" value="${factura.getCondpago()} "></div>
		
		<div class="divhead"><strong>EMISOR:</strong><input type="text" maxlength="30" style="width:129px;height:30px ;background:#FFF;" readonly="readonly" value="${factura.getEmisor().getEmpresas_nombrenof()} "></div>
		
		
		
	</div>

	<div class=" cuadroblanco" style="height:275px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CLIENTE</p></h3>
		<div style="padding: 5px 5px 5px 5px">
		
		<div class="divhead"><strong>RUT:</strong><input type="text" name="empresas_rut" id="empresas_rut" style="width:145px;height:30px ;background:#FFF;" readonly="readonly" value="${factura.getCliente_rut()}" ><input type="hidden"  name="rut_prev"  id="rut_prev"></div>
		<div class="divhead"><strong>COD CLIENTE:</strong><input type="text" name="cod_cliente" id="cod_cliente" style="width:70px;height:30px ;background:#FFF;" readonly="readonly" value="${factura.getId_cliente()}" ></div>
		<div class="divhead"><strong>RAZON SOCIAL:</strong><input type="text" name="rz_cliente" id="rz_cliente" style="width:500px;height:30px ;background:#FFF;" readonly="readonly" value="${factura.getCliente_rz()}"></div>
		<div class="divhead"><strong>DIRECCION:</strong><input type="text" style="width:700px;height:30px ;background:#FFF;" readonly="readonly" value="${factura.getDir_direccion()}"></div>
		<div class="divhead"><strong>REGION:</strong><input type="text" style="width:510px;height:30px ;background:#FFF;" readonly="readonly" value="${factura.getDir_region()}"></div>
		<div class="divhead"><strong>CIUDAD:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" value="${factura.getDir_ciudad()}" readonly="readonly" value="" ><input type="hidden"  name="ciudad_prev" id="ciudad_prev"></div>
		<div class="divhead"><strong>COMUNA:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" value="${factura.getDir_comuna()}" readonly="readonly" value="" ><input type="hidden"  name="comuna_prev" id="comuna_prev"></div>
		<div class="divhead"><strong>RESPONSABLE CUENTA:</strong><input type="text" id="resp" name="resp" style="width:350px;height:30px ;background:#FFF;" readonly="readonly" value=""></div>
		<div class="divhead"><strong>PER FACT.:</strong><input type="text" style="width:325px;height:30px ;background:#FFF;" readonly="readonly" value="${factura.getPeri_tc_idNombre() }"></div>
	<div class="divhead"><strong>TIPO CAMBIO:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;text-align: right;" value="${factura.getTipo_cambio().getNombre()}" readonly="readonly"></div>
	<div class="divhead"><strong>TIPO CAMBIO:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;text-align: right;" value="${factura.getTcambio()}" readonly="readonly"></div>				
	</div>
	
	</div>
	
	<div class=" cuadroblanco" style="height:335px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS SERVICIOS IMPRESION</p></h3>		
		
		<div style="padding: 5px 5px 5px 5px;" class="divscroll scroll">
		
		
			<table class="table" style="margin-left:0px; ">
		
		<thead style="border: 1px solid black;background-color:#0101DF">
		<tr style="background-color:#0101DF;color:#FFFFFF">
			<td class="detailID" style="font-size:20px"><strong>ID</strong></td>
			<td class="detailActivo" style="font-size:20px"><strong>ACTIVO</strong></td>
			<td width="116px" style="font-size:20px"><% if(periodos_para_tc[4]!=null){%> 
			<strong>P<%=periodos_para_tc[4] %></strong><br>
			<%=periodosfechas_para_tc[4] %> <% }else{%> ND <% } %></td> 
			<td width="116px" style="font-size:20px"><% if(periodos_para_tc[5]!=null){%> <strong>P<%=periodos_para_tc[5] %></strong><br><%=periodosfechas_para_tc[5] %> <% } else{%> ND <% } %></td>
			<td width="116px" style="font-size:20px"><strong>P<%=factura.getPeriodoFac().getCorrelativo() %></strong><br><%=factura.getPeriodoFac().getFdesde() %> <%=factura.getPeriodoFac().getFhasta() %></td>
			<td width="116px" style="font-size:20px"><strong>C.FIJO</strong></td>
			<td width="116px" style="font-size:20px"><strong>C.VARIABLE</strong></td>
		</tr>
		</thead>
		<tbody id="fbody" class="scroll" style="border: 1px solid black">
		
		 <%       
		 		String ubi_ant="";
		
		         for(int i =0; i<arActivos.length; i++){
		        	 
		        	
		        	 
		        	 String[] Activos = arActivos[i].split(";;");
		        	 
		        	 //buscamos cual es el costo fijo y variable para este activo 
		        	 String CF="0";
		        	 String CV="0";
		        	 
		        	 for(FacturaDetalleVO dfactura : detalles_factura){
		        		 if(dfactura.getEs_activo().equals("1") && dfactura.getId_activo().equals(Activos[0]) && dfactura.getEstrcobro_id().equals(Activos[9]) && dfactura.getUbi_id().equals(Activos[7])){
     						CF=dfactura.getPrecioCF();
     						CV=dfactura.getPrecioCV();
     					 }
		        		 
		        		 
		        	 }
		        	
		        	if(!ubi_ant.equals(Activos[3]+" - "+Activos[2])){
		        		
		        	%>
		        	<tr class="hov" >
						<td colspan="8"><strong><%=Activos[3]%> - <%=Activos[2]%></strong></td>
						
					</tr>
		        	<% 
		        	}ubi_ant=Activos[3]+" - "+Activos[2];
		        	%>
		        	
					<tr class="hov" >
						<td class="detailID" ><%=Activos[0]%> <input type="hidden" name="activosdetail[]" value="<%=Activos[0]%>" />
																<input type="hidden" name="ubisdetail[]" value="<%=Activos[7]%>" />
																<input type="hidden" name="estrucdetail[]" value="<%=Activos[9]%>" /></td>
						<td class="detailActivo"><%=Activos[1]%> - <%=Activos[5]%></td>
						<td><input type="text" class="inputDetail" name="p5" <% if(periodos_para_tc[4]!=null){if(cont4_activo.indexOf(Activos[0]+";;"+Activos[6])!=-1){%> value="<%=cont4_values.get(cont4_activo.indexOf(Activos[0]+";;"+Activos[6])) %>" <%}else{%> value="0" <% } %> <% } else{%> value="ND" <% } %> style="height:30px ;" disabled></td>
						<td><input type="text" class="inputDetail" id="p6_<%=Activos[0] %>;<%=Activos[6] %>" name="p6" <% if(periodos_para_tc[5]!=null){if(cont5_activo.indexOf(Activos[0]+";;"+Activos[6])!=-1){%> value="<%=cont5_values.get(cont5_activo.indexOf(Activos[0]+";;"+Activos[6])) %>" <%}else{%> value="0" <% } %> <% } else{%> value="ND" <% } %> style="height:30px ;" disabled></td>
						<td><input type="text" class="inputDetail" id="p7" name="p7_<%=Activos[0] %>;<%=Activos[6] %>" maxlength="8" <% if(cont6_activo.indexOf(Activos[0]+";;"+Activos[6])!=-1){%> value="<%=cont6_values.get(cont6_activo.indexOf(Activos[0]+";;"+Activos[6])) %>" <%}else{%> value="0" <% } %>   style="height:30px ;background:#FFF;" disabled="disabled"    ></td>
						<td><input type="text" class="inputDetail" id="p7" name="detallePreciosCF[]" maxlength="8" style="height:30px ;background:#FFF;" readonly="readonly" value="<%=CF %>"  ></td>
						<td><input type="text" class="inputDetail" id="p7" name="detallePreciosCV[]" maxlength="8" style="height:30px ;background:#FFF;" readonly="readonly" value="<%=CV %>"  ></td>
						
					</tr>
					<tr class="hov" style="border-bottom: 3px solid black; ">
						<td class="detailID" ></td>
						<td class="detailActivo" >ESTRUCTURA: <a target="_blank" href="/013/C03?estr=<%=Activos[9]%>"><%=Activos[9]%></a></td>
						<td><input type="text" class="inputDetail" <% if(periodos_para_tc[4]!=null){if(cont4_activo.indexOf(Activos[0]+";;"+Activos[6])!=-1){%> value="<%=cont4_difs.get(cont4_activo.indexOf(Activos[0]+";;"+Activos[6])) %>" <%}else{%> value="0" <% } %> <% } else{%> value="ND" <% } %> style="height:30px ;" disabled></td>
						<td><input type="text" class="inputDetail" <% if(periodos_para_tc[5]!=null){if(cont5_activo.indexOf(Activos[0]+";;"+Activos[6])!=-1){%> value="<%=cont5_difs.get(cont5_activo.indexOf(Activos[0]+";;"+Activos[6])) %>" <%}else{%> value="0" <% } %> <% } else{%> value="ND" <% } %> style="height:30px ;" disabled></td>
						<td><input type="text" class="inputDetail" name="detalleImps[]" <% if(cont6_activo.indexOf(Activos[0]+";;"+Activos[6])!=-1){ %> value="<%=cont6_difs.get(cont6_activo.indexOf(Activos[0]+";;"+Activos[6])) %>" <%}else{%> value="0" <% } %> style="height:30px ;" readonly="readonly"></td>
						<td >&nbsp;</td>
						<td >&nbsp;</td>
						
					</tr>
		        	<% } 
		        	
		         for(FacturaDetalleVO dfactura : detalles_factura){
	        		 if(!dfactura.getEs_anexo().equals("1") ){
 						continue;
 					 }
	        		 
	        		 %>
    				 <tr class="hov" style="border-bottom: 1px solid black; ">
    				 	<td class="detailID" ><%=dfactura.getAnc_id() %></td>
						<td class="detailActivo">ANEXO CONTRATO</td>
						<td colspan="3"><%=dfactura.getDescripcion()%></td>
						<td><input type="text" class="inputDetail" id="p7" name="detallePreciosAnexoCF[]" maxlength="8" style="height:30px ;background:#FFF;" readonly="readonly" value="<%=dfactura.getPrecioCF() %>"  ></td>
						<td><input type="text" class="inputDetail" id="p7" maxlength="8" style="height:30px ;background:#FFF;" readonly="readonly" value="0"  ></td>
					</tr>
    				 <%
	        		 
	        		 
	        		 
	        		 
	        		 
	        		 
	        	 }
		         for(FacturaDetalleVO dfactura : detalles_factura){
	        		 if(!dfactura.getEs_estructuracobro().equals("1") ){
 						continue;
 					 }
	        		 
	        		 %>
    				<tr class="hov" style="border-bottom: 1px solid black; ">
	        					<td class="detailID" ><%=dfactura.getEstrcobro_id() %></td>
								<td class="detailActivo">ESTRUCTURA DE COBRO</td>
								<td colspan="3"><%=dfactura.getDescripcion()%></td>
								<td><input type="text" class="inputDetail" name="detallePreciosEstrCF[]" maxlength="8" style="height:30px ;background:#FFF;" readonly="readonly" value="<%=dfactura.getPrecioCF() %>"  ></td>
								<td><input type="text" class="inputDetail" maxlength="8" style="height:30px ;background:#FFF;" readonly="readonly" value="<%=dfactura.getPrecioCV() %>"  ></td>
						</tr>
    				 <%
	        		 
	        		 
	        		 
	        		 
	        		 
	        		 
	        	 }
		        	%>
		        	</tbody>
		</table>
		
	
	
	
	
		
		</div>
	</div>
	
		<div class=" cuadroblanco" style="height:120px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>SUBTOTAL:</strong><input name="subtotal" id="subtotal" type="text" style="width:150px;height:30px ;background:#FFF;" value="${factura.getSubtotal()}"  readonly="readonly"></div>
		<div class="divhead"><strong>MONTO DESCUENTO:</strong><input type="text" name="desc"  id="desc"  style="width:150px;height:30px ;background:#FFF;"  value="${factura.getDesc()}"  readonly="readonly" ></div>
		<div class="divhead"><strong>GLOSA DESCUENTO:</strong><input type="text"  style="width:350px;height:30px ;background:#FFF;" readonly="readonly"  value="${factura.getGlosa_desc() }"  ></div>
		<div class="divhead"><strong id="netolabel">AFECTO:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;" readonly="readonly" value="${factura.getNeto()}" id="neto" name="neto"></div>
		
		<div class="divhead"><strong>IVA 19%:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;" readonly="readonly" value="${factura.getIva()}" id="iva" name="iva"></div>
		<div class="divhead"><strong>TOTAL:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" readonly="readonly" value="${factura.getTotal()}" id="total" name="total"></div>
		
	</div>
	</div>
	
	
	
	<div class=" cuadroblanco" style="height:90px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>N DE IMPRESIONES:</strong><input type="text"  style="width:130px;height:30px ;background:#FFF;" value="${factura.getN_imp()}" readonly="readonly"></div>
		
		<div class="divhead"><strong>OBSERVACIONES:</strong><input type="text" maxlength="64" style="width:720px;height:30px ;background:#FFF;" readonly="readonly" value="${factura.getObservaciones()}"></div>
	</div>
		
	</div>
	
	<div class=" cuadroblanco" style="height:45px;margin-top: 5px;">
		<div class="bgrabar">
		  <button type="submit" name="grabar" id="grabar" class="btn btn-success" >GRABAR</button>
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


