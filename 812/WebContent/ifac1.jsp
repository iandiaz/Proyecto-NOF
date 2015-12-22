<%@page import="java.util.ArrayList"%>
<% 	
String gv_id="";
String estados_vig_novig_id="";
String empresas_id="";
String clientes_id="";
String contactos_id="";
String direcciones_id="";
if(request.getAttribute("gv_id")!=null && !request.getAttribute("gv_id").equals("") && !request.getAttribute("gv_id").equals("null")) gv_id =(String) request.getAttribute("gv_id");
if(request.getAttribute("estados_vig_novig_id")!=null && !request.getAttribute("estados_vig_novig_id").equals(""))estados_vig_novig_id=(String) request.getAttribute("estados_vig_novig_id");

String[] ar_estados =(String[]) request.getAttribute("ar_estados");
String[] ar_empresas =(String[]) request.getAttribute("ar_empresas");
String[] ar_clientes =(String[]) request.getAttribute("ar_clientes");
String[] ar_contactos =(String[]) request.getAttribute("ar_contactos");
String[] ar_direcciones =(String[]) request.getAttribute("ar_direcciones");
String[] ar_folios =(String[]) request.getAttribute("ar_folios");




String Usu_nom=(String)request.getAttribute("usuname");	

String fecha=(String)request.getAttribute("fecha");

%>
<!DOCTYPE html>
<html lang="en"><head>
    <meta charset="utf-8">
    <title>New Office - FACTURA DE COMPRA AFECTA - EXENTA SERVICIO</title>
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
        // Handler for .ready() called.
$( "#datepicker" ).datepicker({ dateFormat: "dd-mm-yy", monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
                                      monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
                                      dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
                                      dayNamesShort: ['Dom','Lun','Mar','Mie','Juv','Vie','Sab'],
                                      dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa']});
$( "#datepicker2" ).datepicker({ dateFormat: "dd-mm-yy", monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
                                       monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
                                       dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
                                       dayNamesShort: ['Dom','Lun','Mar','Mie','Juv','Vie','Sab'],
                                       dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa']});
                                       
$( "#datepicker3" ).datepicker({ dateFormat: "dd-mm-yy", monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
    monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
    dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
    dayNamesShort: ['Dom','Lun','Mar','Mie','Juv','Vie','Sab'],
    dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa']});



        
});
    


    
    
    //DEFINIMOS ACTION DE EMPRESAS 
    var empresas_ar =new Array();
    
    <%  for(int i =0; i<ar_clientes.length; i++){
        %>
        empresas_ar[<%=i%>]="<%=ar_clientes[i]%>";
        
        <% } %>

    var dirs_ar =new Array();
    
    <%  for(int i =0; i<ar_direcciones.length; i++){
        %>
        dirs_ar[<%=i%>]="<%=ar_direcciones[i]%>";
        
        <% } %>

   //arreglo de contractos
   
   
   var cont_ar =new Array();
   
   <%  for(int i =0; i<ar_contactos.length; i++){
       %>
       cont_ar[<%=i%>]="<%=ar_contactos[i]%>";
       
       <% } %>
        
 //arreglo de folios 
       
         var folios_ar =new Array();
   
   <%  for(int i =0; i<ar_folios.length; i++){
       %>
       folios_ar[<%=i%>]="<%=ar_folios[i]%>";
       
       <% } %>

    
           
           
//arreglo periodos 
       
     
        $( document ).ready(function() {
                        // Handler for .ready() called.
                        
                        $('#empresa_id').on('change', function() {
                                            var cli_seleccionada= $(this).val();
                                               
                                            $('#dire_id').empty();
                                            $('#cont_id').empty(); 
                                            
                                            
                                            //pendiente el cambio de periodos en el select
                                            
                                            
                                            
                                            
                                                //recorremos array de direcciones para buscar las que correspondan a la empresa
                                           
                                            if(cli_seleccionada!=''){
                                            	
                                           	   for(var ii=0; ii< empresas_ar.length;ii++){
                                                      
                                                      var e_ar =empresas_ar[ii].split("/");
                                                      if(e_ar[0]==cli_seleccionada){
                                                    	  //alert('esta');
                                                    	  
                                                    	  $('#cod_cliente').val(e_ar[0]);
                                                        $('#empresas_rut').val(e_ar[2]);
                                                        $('#rz_cliente').val(e_ar[3]);
                                                        $('#resp').val(e_ar[4]);
                                                        $('#rz_prev').val(e_ar[3]);
                                                        $('#giro_prev').val(e_ar[5]);
                                                        
                                                        break;
                                                      }
                                                    
                                                    }
                                                for(var ii=0; ii< dirs_ar.length;ii++){
                                             
                                                  var ubi_ar =dirs_ar[ii].split("/");
                                                  if(ubi_ar[2]==cli_seleccionada){
                                                    $('#dire_id').append($('<option>', {
                                                                                value: ubi_ar[0],
                                                                                text: ubi_ar[1]
                                                                                }));
                                                  }
                                                
                                                }
                                                
                                                for(var ii=0; ii< cont_ar.length;ii++){
                                                    
                                                    var ubi_ar =cont_ar[ii].split("/");
                                                    if(ubi_ar[2]==cli_seleccionada){
                                                      $('#cont_id').append($('<option>', {
                                                                                  value: ubi_ar[0],
                                                                                  text: ubi_ar[1]
                                                                                  }));
                                                    }
                                                  
                                                  }
                                            }
                                                
                                            $('#dire_id').change();               
                                            $('#cont_id').change();
                                                
                                                
                        });
                        
                        
                        
                        
                        
                        $('#cont_id').on('change', function() {
                            var cont_seleccionada= $(this).val();
                               
                            for(var ii=0; ii< cont_ar.length;ii++){
                                
                                var c_ar =cont_ar[ii].split("/");
                                if(c_ar[0]==cont_seleccionada){
                              	  //alert('esta');
                              	 
                              	  
                              	  	
                              	  	
                              	  $('#cont_phone').val(c_ar[4]);
                              	$('#cont_mail').val(c_ar[5]);
                              	$('#cont_phonec').val(c_ar[6]);
                              	
                              	  
                              	  break;
                                }
                              
                              }
                                
       					 });
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
                        
                        $('#empresa_id').change();
                        
                    
        });
    
      
        function validateNum(event) {
            var key = window.event ? event.keyCode : event.which;
			//alert(event.keyCode);
            if (event.keyCode == 8 || event.keyCode == 46
             || event.keyCode == 37 || event.keyCode == 39 || event.keyCode == 9 || event.keyCode == 190) {
                return true;
            }
            else if ( key < 48 || key > 57 ) {
                return false;
            }
            else return true;
        };
        
        
        function validateSubmit(){
        	$("#form1").attr("action", "");
            
        	var validate= true;
        	var msg="ERRORES: \n";
        	
        	if(document.getElementById('fac_servim_obs').value==""){
        		msg+="DEBE INGRESAR LA OBSERVACION\n";
        		validate=false;
        	}
        
        	if(document.getElementById('fac_servim_ref').value==""){
        		msg+="DEBE INGRESAR LA REFERENCIA\n";
        		validate=false;
        	}
        	if(document.getElementById('total').value==""){
        		msg+="DEBE INGRESAR EL SUBTOTAL DE FACTURA\n";
        		validate=false;
        	}
        	if(Number(document.getElementById('total').value)<0){
        		msg+="DEBE INGRESAR EL SUBTOTAL MAYOR A 0\n";
        		validate=false;
        	}
        	
        	if(document.getElementById('datepicker').value==""){
        		msg+="DEBE INGRESAR FECHA DE VENCIMIENTO\n";
        		validate=false;
        	}
        	
        	if(document.getElementById('fac_servim_condiciones').value==""){
        		msg+="DEBE INGRESAR CONDICIONES DE FACTURA\n";
        		validate=false;
        	}
        	for (var ii = 0; ii < folios_ar.length; ii++) {
				if(folios_ar[ii]==document.getElementById('gv_id').value){
					msg+="FOLIO YA EXISTE EN SISTEMA.\n";
	        		validate=false;
					break;
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
    		$("#total_prev").val($("#totaltotal").val());
    		$("#iva_prev").val($("#iva").val());
    		$("#neto_prev").val($("#netoneto").val());
    		
    		$("#dir_prev").val($("#dire_id").find('option:selected').text());
    		$("#obs_prev").val($("#fac_servim_obs").val());
    		
    		$("#comuna_prev").val($("#gv_comuna").val());
    		$("#ciudad_prev").val($("#gv_ciudad").val());
    		$("#telefono_prev").val($("#cont_phone").val());
    		$("#rut_prev").val($("#empresas_rut").val());
    		
    		
    		$("#ref_prev").val($("#fac_servim_ref").val());
    		
    		//asignamos el detalle prev 
    		//primero hacemos el array
    		var indice =0;
    		var detalle = new Array();
    		if($("#serv1").val()!=""){
    			var servicio1=(indice+1)+";"+$("#serv1").val()+";1"+";"+$("#val1").val()+";0;"+$("#val1").val();
    			indice++;
    			$("#detalle_prev1").val(servicio1);
    		}
			 
    		if($("#serv2").val()!=""){
    			var servicio2=(indice+1)+";"+$("#serv2").val()+";1"+";"+$("#val2").val()+";0;"+$("#val2").val();
    			indice++;
    			$("#detalle_prev2").val(servicio2);
    		}
    		if($("#serv3").val()!=""){
    			var servicio3 = (indice+1)+";"+$("#serv3").val()+";1"+";"+$("#val3").val()+";0;"+$("#val3").val();
    			indice++;
    			$("#detalle_prev3").val(servicio3);
    		}
    		if($("#serv4").val()!=""){
    			var servicio4= (indice+1)+";"+$("#serv4").val()+";1"+";"+$("#val4").val()+";0;"+$("#val4").val();
    			indice++;
    			$("#detalle_prev4").val(servicio4);
    		}
    		if($("#serv5").val()!=""){
    			var servicio5= (indice+1)+";"+$("#serv5").val()+";1"+";"+$("#val5").val()+";0;"+$("#val5").val();
    			indice++;
    			$("#detalle_prev5").val(servicio5);
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
	  <p>N812.I.01.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/812/'">VOLVER</button> 
	<div align="center" class="title">
		<p>INGRESAR FACTURA DE COMPRA AFECTA - EXENTA SERVICIO</p>
	</div>
</div>
<div class="content" >
  <form action="" name="form1" id="form1" method="post" > 
	<div class=" cuadroblanco" style="height:120px;padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>NRO.FACT:</strong><input type="text" style="width:80px;height:30px ;background:#FFF;" name="gv_id" id="gv_id" value="ND" disabled="disabled"></div>
		<div class="divhead"><strong>FECHA:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;" name="gv_fecha" id="datepicker3" value="<%=fecha %>" disabled="disabled" ></div>
		<div class="divhead"><strong>COND PAGO:</strong><input name="fac_servim_condiciones" id="fac_servim_condiciones" type="text" maxlength="30" style="width:350px;height:30px ;background:#FF3;" class="amarillo" value=""><span class="cabecera" style="color:#F00">*</span></div>
		
		<div class="divhead"><strong>EMISOR:</strong><select name="fac_servim_emisor" id="fac_servim_emisor" style="margin:0px 0px 0px 0px;" >
				<% for(int i =0; i<ar_empresas.length; i++){
					String[] empresas = ar_empresas[i].split("/");%>
				<option value="<%=empresas[0]%>"<% if(empresas_id.equals(empresas[0]))%><%="selected"%>><%=empresas[1] %></option>
			<% } %>
    		</select><span class="cabecera" style="color:#F00">*</span></div>
		<div class="divhead"><strong>ESTADO SII:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" value="NO ENVIADA" readonly="readonly"></div>
		<div class="divhead"><strong>TIPO DTE:</strong><input type="radio" name="tipodte" id="tipodte1" value="46" checked="checked">(46) FACT COMPRA ELECT</div>
		
		<div class="divhead"><strong>FEC. VENCIMIENTO:</strong><input maxlength="10" type="text" style="width:130px;height:30px ;background:#FF3;" value="" class="amarillo" id="datepicker" name="fac_servim_fecvencimiento" readonly="readonly"><span class="cabecera" style="color:#F00">*</span></div>
		
		
	</div>

	<div class=" cuadroblanco" style="height:275px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CLIENTE</p></h3>
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>CLIENTE:</strong><select name="empresa_id" id="empresa_id" style="margin:0px 0px 0px 0px;">
				<% for(int i =0; i<ar_clientes.length; i++){
					String[] clientes = ar_clientes[i].split("/");%>
				<option value="<%=clientes[0]%>"<% if(clientes_id.equals(clientes[0]))%><%="selected"%>><%=clientes[1] %></option>
			<% } %>
    		</select><span class="cabecera" style="color:#F00">*</span></div>
		<div class="divhead"><strong>RUT:</strong><input type="text" name="empresas_rut" id="empresas_rut" style="width:145px;height:30px ;background:#FFF;" disabled="disabled" value="" ></div>
		<div class="divhead"><strong>COD CLIENTE:</strong><input type="text" name="cod_cliente" id="cod_cliente" style="width:70px;height:30px ;background:#FFF;" disabled="disabled" value="" ></div>
		<div class="divhead"><strong>RAZON SOCIAL:</strong><input type="text" name="rz_cliente" id="rz_cliente" style="width:500px;height:30px ;background:#FFF;" disabled="disabled" value=""></div>
		<div class="divhead"><strong>DIRECCION:</strong><select name="dire_id" id="dire_id" style="margin:0px 0px 0px 0px;width: 700px">
				<% for(int i =0; i<ar_direcciones.length; i++){
					String[] direcciones = ar_direcciones[i].split("/");%>
				<option value="<%=direcciones[0]%>"<% if(direcciones_id.equals(direcciones[0]))%><%="selected"%>><%=direcciones[1] %></option>
			<% } %>
    		</select><span class="cabecera" style="color:#F00">*</span></div>
		<div class="divhead"><strong>REGION:</strong><input type="text" id="regi" style="width:510px;height:30px ;background:#FFF;" disabled="disabled" value=""></div>
		<div class="divhead"><strong>CIUDAD:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" id="gv_ciudad" disabled="disabled" value="" ></div>
		<div class="divhead"><strong>COMUNA:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" id="gv_comuna" disabled="disabled" value="" ></div>
		<div class="divhead"><strong>RESPONSABLE CUENTA:</strong><input type="text" id="resp" style="width:350px;height:30px ;background:#FFF;" disabled="disabled" value=""></div>		
		
	</div>
	
	</div>
	
	<div class=" cuadroblanco" style="height:120px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CONTACTO</p></h3>
		
		<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>CONTACTO:</strong><select name="cont_id" id="cont_id" style="margin:0px 0px 0px 0px; width: 550px">
				<% for(int i =0; i<ar_contactos.length; i++){
					String[] contactos = ar_contactos[i].split("/");%>
				<option value="<%=contactos[0]%>"<% if(contactos_id.equals(contactos[0]))%><%="selected"%>><%=contactos[1] %></option>
			<% } %>
    		</select><span class="cabecera" style="color:#F00">*</span></div>
		<div class="divhead"><strong>E-MAIL:</strong><input type="text" id="cont_mail" style="width:350px;height:30px ;background:#FFF;" disabled="disabled" value=""></div>
		
		<div class="divhead"><strong>TELEFONO:</strong><input type="text" id="cont_phone" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value=""></div>
		<div class="divhead"><strong>CELULAR:</strong><input type="text" id="cont_phonec" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value=""></div>
		
	</div>
	
	</div>
	
	<div class=" cuadroblanco" style="height:235px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS SERVICIOS</p></h3>
		
		<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>SERV 1:</strong><input type="text" maxlength="53"  class="amarillo" style="width:650px;height:30px ;background:#FF3;" name="serv1" id="serv1" value="" autofocus="autofocus" ></div>
		<div class="divhead"><strong>VALOR 1:</strong><input type="text" maxlength="9" onkeydown="return validateNum(event)" onblur="$('#total').val(Number($('#val1').val())+Number($('#val2').val())+Number($('#val3').val())+Number($('#val4').val())+Number($('#val5').val()));$('#total').blur();" class="amarillo" style="width:120px;height:30px ;background:#FF3;" name="val1" id="val1" value=""></div>
		<div class="divhead"><strong>SERV 2:</strong><input type="text" maxlength="53" class="amarillo" style="width:650px;height:30px ;background:#FF3;" name="serv2" id="serv2" value=""></div>
		<div class="divhead"><strong>VALOR 2:</strong><input type="text" maxlength="9" onkeydown="return validateNum(event)" onblur="$('#total').val(Number($('#val1').val())+Number($('#val2').val())+Number($('#val3').val())+Number($('#val4').val())+Number($('#val5').val()));$('#total').blur();" class="amarillo" style="width:120px;height:30px ;background:#FF3;" name="val2" id="val2" value=""></div>
		<div class="divhead"><strong>SERV 3:</strong><input type="text" maxlength="53" class="amarillo" style="width:650px;height:30px ;background:#FF3;" name="serv3" id="serv3" value=""></div>
		<div class="divhead"><strong>VALOR 3:</strong><input type="text" maxlength="9" onkeydown="return validateNum(event)" onblur="$('#total').val(Number($('#val1').val())+Number($('#val2').val())+Number($('#val3').val())+Number($('#val4').val())+Number($('#val5').val()));$('#total').blur();" class="amarillo" style="width:120px;height:30px ;background:#FF3;" name="val3" id="val3" value=""></div>
		<div class="divhead"><strong>SERV 4:</strong><input type="text" maxlength="53" class="amarillo" style="width:650px;height:30px ;background:#FF3;" name="serv4" id="serv4" value=""></div>
		<div class="divhead"><strong>VALOR 4:</strong><input type="text" maxlength="9" onkeydown="return validateNum(event)" onblur="$('#total').val(Number($('#val1').val())+Number($('#val2').val())+Number($('#val3').val())+Number($('#val4').val())+Number($('#val5').val()));$('#total').blur();" class="amarillo" style="width:120px;height:30px ;background:#FF3;" name="val4" id="val4" value=""></div>
		<div class="divhead"><strong>SERV 5:</strong><input type="text" maxlength="53" class="amarillo" style="width:650px;height:30px ;background:#FF3;" name="serv5" id="serv5" value=""></div>
		<div class="divhead"><strong>VALOR 5:</strong><input type="text" maxlength="9" onkeydown="return validateNum(event)" onblur="$('#total').val(Number($('#val1').val())+Number($('#val2').val())+Number($('#val3').val())+Number($('#val4').val())+Number($('#val5').val()));$('#total').blur();" class="amarillo" style="width:120px;height:30px ;background:#FF3;" name="val5" id="val5" value=""></div>
		</div>
	</div>
	<div class=" cuadroblanco" style="height:220px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>SUBTOTAL:</strong><input name="total" id="total" type="text" style="width:150px;height:30px ;background:#FFF;" value=""  readonly="readonly"></div>
		<div class="divhead"><strong>MONTO DESCUENTO:</strong><input type="text" name="desc"  id="desc"  onkeydown="return validateNum(event)" class="amarillo" style="width:150px;height:30px ;background:#FF3;" maxlength="12"  value="0" required="required"   ></div>
		<div class="divhead"><strong>GLOSA DESCUENTO:</strong><input type="text"  name="glosa_desc"  id="glosa_desc" class="amarillo" style="width:600px;height:30px ;background:#FF3;" maxlength="53" value=""  required="required" ></div>
		<div class="divhead"><strong>NETO:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;" value="" id="netoneto" name="netoneto" readonly="readonly"></div>
		<div class="divhead"><strong>%IVA RETENIDO:</strong><input type="text" onKeyPress="if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false;"  style="width:120px;height:30px ;background:#FF3;" class="amarillo" value="0" id="ivap" name="ivap" > $<input type="text" readonly="readonly" style="width:120px;height:30px ;background:#FFF;" value="" id="iva" name="iva" ></div>
		<div class="divhead"><strong>%IVA NO RETENIDO:</strong><input type="text" onKeyPress="if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false;"  style="width:120px;height:30px ;background:#FFF;"  value="19" id="perivanoret" name="perivanoret" readonly="readonly"  > $<input type="text" readonly="readonly" style="width:120px;height:30px ;background:#FFF;" value="" id="valivanoret" name="valivanoret" ></div>
		<div class="divhead"><strong>TOTAL:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" value="" id="totaltotal" name="totaltotal" readonly="readonly"></div>
		<div class="divhead"><strong>REFERENCIA:</strong><input type="text"  name="fac_servim_ref" id="fac_servim_ref" class="amarillo" maxlength="66" style="width:750px;height:30px ;background:#FF3;" value="" required><span class="cabecera" style="color:#F00">*</span></div>
	</div>
	</div>

	<div class=" cuadroblanco" style="height:120px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>TIPO CAMBIO:</strong><input type="text" name="fac_servim_tcambio" id="fac_servim_tcambio" class="amarillo" maxlength="9" style="width:120px;height:30px ;background:#FF3;" value="" onkeydown="return validateNum(event)" required="required"><span class="cabecera" style="color:#F00">*</span></div>
		
		<div class="divhead"><strong>OBSERVACIONES:</strong><input type="text" name="fac_servim_obs" id="fac_servim_obs" class="amarillo" maxlength="64" style="width:720px;height:30px ;background:#FF3;" value=""><span class="cabecera" style="color:#F00">*</span></div>
	</div>
		<div class="bgrabar">
		<input type="hidden" name="total_prev" id="total_prev" value="0">
			<input type="hidden" name="iva_prev" id="iva_prev" value="0">
			<input type="hidden" name="neto_prev" id="neto_prev" value="0">
			<input type="hidden" name="dir_prev" id="dir_prev" value="">
			<input type="hidden" name="obs_prev" id="obs_prev" value="">
			<input type="hidden" name="giro_prev" id="giro_prev">
			<input type="hidden" name="rz_prev" id="rz_prev">
			<input type="hidden" name="detalle_prev[]" id="detalle_prev1">
			<input type="hidden" name="detalle_prev[]" id="detalle_prev2">
			<input type="hidden" name="detalle_prev[]" id="detalle_prev3">
			<input type="hidden" name="detalle_prev[]" id="detalle_prev4">
			<input type="hidden" name="detalle_prev[]" id="detalle_prev5">
			<input type="hidden"  name="ciudad_prev" id="ciudad_prev">
			<input type="hidden"  name="comuna_prev" id="comuna_prev">
			<input type="hidden"  name="rut_prev"  id="rut_prev">
			<input type="hidden" name="telefono_prev" id="telefono_prev">
			<input type="hidden"  name="ref_prev" id="ref_prev">
			<input type="hidden" value="<%=fecha%>" name="fec_prev">
			
			<button type="submit" name="previ" id="previ" class="btn btn-success" onclick="previsualizar()"  >PREVISUALIZAR</button>
          <button type="submit" name="grabar" id="grabar" class="btn btn-success" onclick="return validateSubmit()" >GRABAR</button>
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


