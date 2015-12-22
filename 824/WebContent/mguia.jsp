<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 	

String guia_des_traszf_id =(String)request.getAttribute("guia_des_traszf_id");
String did =(String)request.getAttribute("did");
String clientes_id =(String)request.getAttribute("clientes_id");
String empresas_id =(String)request.getAttribute("empresas_id");
String contactos_id =(String)request.getAttribute("contactos_id");
String estados_vig_novig_id =(String)request.getAttribute("estados_vig_novig_id");

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
String ciu2 =(String)request.getAttribute("cui2");
String CONT_NOMBRE =(String)request.getAttribute("CONT_NOMBRE");
String CONT_TELEFONO =(String)request.getAttribute("CONT_TELEFONO");
String CONT_TELEFONOC =(String)request.getAttribute("CONT_TELEFONOC");
String CONT_EMAIL =(String)request.getAttribute("CONT_EMAIL");
String RESP_NOMBRE =(String)request.getAttribute("PERS_NOMBRE");

String obs=(String)request.getAttribute("obs");
String obs2 =(String)request.getAttribute("obs2");
String fecha =(String)request.getAttribute("fecha");
String Usu_nom=(String)request.getAttribute("usuname");
String dire_id=(String)request.getAttribute("dire_id");
String dire_id2=(String)request.getAttribute("dire_id2");

String RESPONSABLE =(String)request.getAttribute("RESPONSABLE");

String[] ar_empresas =(String[]) request.getAttribute("ar_empresas");
String[] ar_contactos =(String[]) request.getAttribute("ar_contactos");
String[] ar_guias =(String[]) request.getAttribute("ar_guias");

int numguias =0;

if(ar_guias!=null && ar_guias.length>0) numguias= ar_guias.length;
String empresas_giro =(String)request.getAttribute("empresas_giro");
String numticket =(String)request.getAttribute("numticket");


String tipo_dteref=(String)request.getAttribute("tipo_dteref");	
String folioref=(String)request.getAttribute("folioref");	
String fec_ref=(String)request.getAttribute("fec_ref");



String subtotal=(String)request.getAttribute("subtotal");
String neto=(String)request.getAttribute("neto");
String total=(String)request.getAttribute("total");
String desc=(String)request.getAttribute("descuento");
String gr_glosa_desc=(String)request.getAttribute("glosadescuento");
String iva=(String)request.getAttribute("iva");

String[] ar_estados =(String[]) request.getAttribute("ar_estados");

String g_afecta=(String)request.getAttribute("g_afecta");	

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
    <title>New Office - Guia de Despacho Traslado Activos Zona Franca</title>
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
	//arreglo de contactos
	var cont_ar =new Array();
	<% for(int i =0; i<ar_contactos.length; i++){ %>
			cont_ar[<%=i%>]="<%=ar_contactos[i]%>";
	<% } %>
    $( document ).ready(function() {
    	
		$('#cont_id').on('change', function() {
			var cont_seleccionada= $(this).val();
			for(var ii=0; ii< cont_ar.length;ii++){
				var c_ar =cont_ar[ii].split("/");
				if(c_ar[0]==cont_seleccionada){
					$('#cont_phone').val(c_ar[3]);
					$('#cont_mail').val(c_ar[4]);
					$('#cont_phonec').val(c_ar[6]);
					break;
				}
			}
		});
		
		$('#cont_id').change();
		

		$('#doc1').on('click', function() {
			$('#file1').click();
			
		});
		
		$('#doc2').on('click', function() {
			$('#file2').click();
			
		});
		
		$('#doc3').on('click', function() {
			$('#file3').click();
			
		});
    });
    
    
  

        function validateSubmit(){
        	$("#form1").attr("action", "");
        	$( "#form1" ).attr( "enctype", "multipart/form-data" );
        	
        	$("#empresa_emisora_nombre").val($("#empresas_id").find('option:selected').text());
        	$("#cont_nombre").val($("#cont_id").find('option:selected').text());
    	
        	
        	
        	var validate= true;
        	var msg="ERRORES: \n";
        	
        	if(document.getElementById('obs1').value==""){
        		msg+="DEBE INGRESAR EL NUMERO DE TICKET\n";
        		validate=false;
        	}        	        	
        	if(document.getElementById('obs').value==""){
        		msg+="DEBE INGRESAR LA FORMA DE TRASLADO\n";
        		validate=false;
        	}
        	if(<%=numguias%> == 0){
        		msg+="DEBE INGRESAR GUIAS.";
        		validate=false;	
        	}
        	if(validate){
        		return true;
        	}else{
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
    </script>
    <script>

    $( document ).ready(function() {
                        // Handler for .ready() called.
                        
                        
           $( "#datepicker" ).datepicker({ dateFormat: "dd-mm-yy", monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
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
          
    });
    function previsualizar(){
		
   	 $("#form1").attr("action", "http://186.67.10.115:81/NOF/factura_formato_venta.php");
   	 $( "#form1" ).attr( "enctype", "" );
   	 
   		$("#dir_prev").val($("#dire_id").val());
   		
   		$("#comuna_prev").val($("#gv_comuna").val());
   		$("#ciudad_prev").val($("#gv_ciudad").val());
   		$("#telefono_prev").val($("#cont_phone").val());
   		$("#rut_prev").val($("#empresas_rut").val());
   		$("#rz_prev").val($("#rz_destino").val());
   		
   		$("#obs_prev").val($("#obs1").val()+'-'+$("#obs").val());
   		$("#obs_prev2").val($("#cont_id :selected").text()+'-'+$("#cont_mail").val());
   		
   		
   		
   			
   			$("#neto_prev").val($("#subtotal").val());
   			$("#iva_prev").val($("#iva").val());
   			$("#total_prev").val($("#total").val());
   			$("#dsco_prev").val($("#desc").val());
   			
   			if($("#dsco_prev").val()>0){
   				//generamos linea de descuento para previsualizacion 
   				
   				$("#detalle_descuento_prev").val($("#glosa_desc").val()+" $"+$("#desc").val());
   				
   			}
   			
   			 return true;
   		
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
	  <p>N824.M.02.001</p>
	</div>
	<form method="get" action="iperusu" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/824/mguia_search'">VOLVER</button> 
	<div align="center" class="title">
		<p >MODIFICAR GUIA DE DESPACHO TRASLADO ACTIVOS ZONA FRANCA</p>
	</div>
</div>
<div class="content" >
  <form action="mguia" name="form1" id="form1" method="post">
  <input type="hidden" name="guia_des_traszf_id" value="<%=guia_des_traszf_id%>" >
  <input type="hidden" name="did" value="<%=did%>" >
  <input type="hidden" name="o_id" value="<%=dire_id  %>">
  
  <input type="hidden" name="guia_des_tras_normal_id" value="<%=request.getParameter("guia_des_tras_normal_id")  %>">
  
  
 	<input type="hidden" name="d_id" value="<%=dire_id2  %>">
	<div class=" cuadroblanco" style="height:80px;">
	<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>NRO.GUIA:</strong><input  type="text" style="width:100px;height:30px ;background:#FFF;" disabled="disabled" value="<%=id_dte%>"></div>
		<div class="divhead"><strong>FECHA:</strong><input  type="text" name="datepicker" id="datepicker" class="amarillo" readonly="readonly" style="width:150px;height:30px ;background:#FF3;" value="<%=fecha%>"><span class="cabecera" style="color:#F00">*</span></div>
		<div class="divhead"><strong>CONTROL GUIA:</strong><input type="text" maxlength="30" style="width:350px;height:30px ;background:#FFF" disabled="disabled" value="POR EMITIR" ></div>
		<div class="divhead"><strong>EMISOR:</strong><select name="empresas_id" id="empresas_id" style="margin:0px 0px 0px 0px;width: 135px;" >
				<% for(int i =0; i<ar_empresas.length; i++){
					String[] empresas = ar_empresas[i].split("/");%>
				<option value="<%=empresas[0]%>"<% if(empresas_id.equals(empresas[0]))%><%="selected"%>><%=empresas[1] %></option>
			<% } %>
    		</select><span class="cabecera" style="color:#F00">*</span></div>
		<div class="divhead"><strong>ESTADO SII:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="NO ENVIADA"></div>
				<div class="divhead"><strong>ESTADO:</strong><select name="estados_vig_novig_id" id="estados_vig_novig_id" style="margin:0px 0px 0px 0px;width: 167px">
				<% for(int i =0; i<ar_estados.length; i++){
				String[] estados_vig_novig = ar_estados[i].split("/");%>
				<option value="<%=estados_vig_novig[0]%>"<% if(estados_vig_novig_id.equals(estados_vig_novig[0]))%><%="selected"%>><%=estados_vig_novig[1] %></option>
    			<% } %>
    		</select><span class="cabecera" style="color:#F00">*</span></div>		
    		
    		<div class="divhead" style="vertical-align:middle;padding: 8px 0 8px 0;" ><strong>AFECTA:</strong><input type="checkbox" value="1" name="g_afecta" id="g_afecta"  class="searchInput" <% if(g_afecta.equals("1")){%> checked="checked" <%} %>  ></div>
</div>
	</div>
	
	<div class=" cuadroblanco" style="height:160px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS ORIGEN</p></h3>
		<div style="padding: 5px 5px 5px 5px;">
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
		<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>RAZON SOCIAL:</strong><input type="text"  id="rz_destino"  style="width:600px;height:30px ;background:#FFF;" disabled="disabled" value="<%=nom2%>"></div>
		<div class="divhead"><strong>RUT:</strong><input type="text" id="empresas_rut" style="width:145px;height:30px ;background:#FFF;" disabled="disabled" value="<%=rut2%>" ></div>
		<div class="divhead"><strong>COD CLIENTE:</strong><input type="text" style="width:70px;height:30px ;background:#FFF;" disabled="disabled" value="<%=id2%>" ></div>
		<div class="divhead"><strong>DIRECCION:</strong><input type="text"  id="dire_id" style="width:500px;height:30px ;background:#FFF;" disabled="disabled" value="<%=dir2%>"></div>
		<div class="divhead"><strong>REGION:</strong><input type="text" style="width:450px;height:30px ;background:#FFF;" disabled="disabled" value="<%=reg2%>"></div>
		<div class="divhead"><strong>COMUNA:</strong><input type="text" id="gv_comuna" style="width:250px;height:30px ;background:#FFF;" disabled="disabled" value="<%=com2%>" ></div>		
		<div class="divhead"><strong>CIUDAD:</strong><input type="text" id="gv_ciudad" name="gv_ciudad" style="width:250px;height:30px ;background:#FFF;" readonly="readonly" value="<%=ciu2%>" ></div>
		<div class="divhead"><strong>RESPONSABLE CUENTA:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" disabled="disabled" value="<%=RESPONSABLE  %>"></div>
	</div>
	</div> 

	<div class=" cuadroblanco" style="height:120px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CONTACTO</p></h3>
		<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>CONTACTO:</strong><select name="cont_id" id="cont_id" style="margin:0px 0px 0px 0px; width: 366px">
				<% for(int i =0; i<ar_contactos.length; i++){
					String[] contactos = ar_contactos[i].split("/");%>
				<option value="<%=contactos[0]%>"<% if(contactos_id.equals(contactos[0]))%><%="selected"%>><%=contactos[1] %></option>
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
			<thead style="border: 1px solid black;">
			   <tr style="background-color:#0101DF;color:#FFFFFF">
				<td width="80" style="font-size:20px"><strong>ID</strong></td>
				<td width="850" style="font-size:20px"><strong>DESCRIPCION</strong></td>
				<td width="200" style="font-size:20px"><strong>SERIE</strong></td>
				<td width="200" style="font-size:20px"><strong>UBICACION</strong></td>
				<td width="200" style="font-size:20px"><strong>FECHA</strong></td>
				<td width="200" style="font-size:20px"><strong>VALOR</strong></td>
			  </tr>  
			<tbody id="fbody" class="scroll" style="border: 1px solid black">
			<%    
			 int valor=0;
				if(ar_guias!=null)
					for(int i =0; i<ar_guias.length; i++){
				    	String[] Guias = ar_guias[i].split("/");
				    	valor+=Integer.parseInt(Guias[12]);
			%>
			
			<tr class="hov">
				<td width="80"><%=Guias[0]%>
					<input type="hidden" value="<%=Guias[0]%>;;<%=Guias[1]%>;;<%=Guias[2]%>;;<%=Guias[3]%>;;<%=Guias[4]%>;;<%=Guias[5]%>;;<%=Guias[6]%>;;<%=Guias[7]%>;;<%=Guias[8]%>;;<%=Guias[9]%>;;<%=Guias[10]%>;;<%=Guias[11]%>;;<%=Guias[12]%>" name="detguias[]" style="width:100px;height:30px ;background:#FFF;">
					<input type="hidden" name="detalle_prev[]" value="<%=Guias[0]%>;<%=Guias[1]%> - <%=Guias[2]%> - <%=Guias[6]%>;1;<%=Guias[12]%>;0;<%=Guias[12]%>">
					</td>
				<td width="850"><%=Guias[2]%> - <%=Guias[3]%></td>
				<td width="200"><%=Guias[4]%></td>
				<td width="200"><%=Guias[6]%></td>
				<td width="200"><%=Guias[7]%></td>
				<td width="200"><%=Guias[12]%></td>
			</tr>
			<% } %>
			</tbody>
			 
		  </table>
	<div class="bgrabar" style="width: 1200px; padding-left: 5px;padding-right: 5px">
		<button type="submit" name="agregarguias" id="agregarguias"  class="btn btn-success" onclick="$('#form1').attr('action', 'mguia_tras'); return true;" style="float: right;margin-right: 5px" >AGREGAR</button>
	</div>
	</div>
	
		<div class=" cuadroblanco" style="height:80px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>SUBTOTAL:</strong><input name="subtotal" id="subtotal" type="text" style="width:150px;height:30px ;background:#FFF;" value="<%=valor %>" readonly="readonly" ></div>
		<div class="divhead"><strong>MONTO DESCUENTO:</strong><input type="text" name="desc"  id="desc" onkeydown="return validateNum(event)" class="amarillo" style="width:150px;height:30px ;background:#FF3;"  value="<%=desc %>"  maxlength="12"  ></div>
		<div class="divhead" ><strong>GLOSA DESCUENTO:</strong><input type="text" name="glosa_desc"  id="glosa_desc" class="amarillo" style="width:350px;height:30px ;background:#FF3;" maxlength="30"  value="<%=gr_glosa_desc %>"  ></div>
		<div class="divhead"><strong id="netolabel">NETO:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;" readonly="readonly" value="<%=neto %>" id="neto" name="neto"></div>
		
		<div class="divhead"><strong>IVA 19%:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;" readonly="readonly" value="<%=iva %>" id="iva" name="iva"></div>
		<div class="divhead"><strong>TOTAL:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" readonly="readonly" value="<%=total %>" id="total" name="total"></div>
		
	</div>
	</div>
			<div class=" cuadroblanco" style="height:40px;margin-top: 5px">
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
	<div class=" cuadroblanco" style="height:215px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>NUMERO DE TICKET:</strong><input type="text" name="ref" maxlength="255" id="obs1" value="<%=numticket %>" class="amarillo" style="width:750px;height:30px ;background:#FF3;"><span class="cabecera" style="color:#F00">*</span></div>
		<div class="divhead"><strong>FORMA TRASLADO:</strong><input type="text" name="obs" maxlength="255" id="obs" value="<%=obs %>" class="amarillo" style="width:720px;height:30px ;background:#FF3;"><span class="cabecera" style="color:#F00">*</span></div>
		<div class="divhead"><strong>MOTIVO:</strong><textarea disabled="disabled" style="width:1000px;height:80px ;background:#FFF;">TRASLADO DE BIENES POR INGRESO TEMPORAL, OPERACIÓN NO CONSTITUYE VENTA POR TRATARSE DE ARTÍCULOS PROPORCIONADOS EN ARRIENDO CONFORME AL CONTRATO ENTRE BARBARA RYAN Y <%=nom2%>.</textarea></div>
		</div>
		<div class="bgrabar" style="width: 1200px; padding-left: 5px;padding-right: 5px">
			<input type="hidden" name="empresa_emisora_nombre" id="empresa_emisora_nombre" >
			<input type="hidden" name="cont_nombre" id="cont_nombre" >
			
			<input type="hidden" name="total_prev" id="total_prev" value="0">
			<input type="hidden" name="iva_prev" id="iva_prev" value="0">
			<input type="hidden" name="neto_prev" id="neto_prev" value="0">
			<input type="hidden" name="dsco_prev" id="dsco_prev" value="0">
			<input type="hidden" name="detalle_descuento_prev" id="detalle_descuento_prev">
				
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
			<input type="hidden" value="<%=fecha%>" name="fec_prev">
			<input type="hidden" name="telefono_prev" id="telefono_prev">
			<input type="hidden" name="folio_prev" id="folio_prev" value="<%=id_dte%>">
			
			<input type="hidden" name="tipo_doc_titulo_prev" id="tipo_doc_titulo_prev" value="GUIA ELECTRONICA">
			
			<input type="file" name="file1" id="file1" style="height: 0px;">
			<input type="file" name="file2" id="file2" style="height: 0px;">
			<input type="file" name="file3" id="file3" style="height: 0px;">
			<button type="button" name="doc1" id="doc1" class="btn btn-primary"   >CONTRATO</button>
			<button type="button" name="doc2" id="doc2" class="btn btn-primary"   >GUIA</button>
			<button type="button" name="doc3" id="doc3" class="btn btn-primary"   >PODER ADUANA</button>
			
			
          <button type="submit" name="grabar" id="grabar"  class="btn btn-success" onclick="return validateSubmit();" style="float: right; margin-left: 5px" >GRABAR</button>
          <button type="submit" name="previ" id="previ" class="btn btn-success" onclick="previsualizar()" style="float: right;" >PREVISUALIZAR</button>
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


