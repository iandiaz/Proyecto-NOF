<%@page import="java.util.ArrayList"%>
<% 	

String estados_vig_novig_id=(String)request.getAttribute("estados_vig_novig_id");

String[] arProd =(String[]) request.getAttribute("ar_prod");
String[] ar_estados =(String[]) request.getAttribute("ar_estados");
String[] ar_empresas =(String[]) request.getAttribute("ar_empresas");
String[] ar_clientes =(String[]) request.getAttribute("ar_clientes");
String[] ar_contactos =(String[]) request.getAttribute("ar_contactos");
String[] ar_direcciones =(String[]) request.getAttribute("ar_direcciones");

String Usu_nom=(String)request.getAttribute("usuname");	
String g_afecta=(String)request.getAttribute("g_afecta");	
String user_crea=(String)request.getAttribute("user_crea"); 
String user_mod=(String)request.getAttribute("user_mod"); 
String fec_crea=(String)request.getAttribute("fec_crea"); 
String fec_mod=(String)request.getAttribute("fec_mod");

String gd_id=(String)request.getAttribute("gd_id");
String gd_fecha=(String)request.getAttribute("gd_fecha");
String emi=(String)request.getAttribute("emi");
String clientes_id=(String)request.getAttribute("CLPR_ID");
String empresas_nombre=(String)request.getAttribute("empresas_nombre");
String estados_vig_novig_nombre=(String)request.getAttribute("estados_vig_novig_nombre");
String empresas_rut=(String)request.getAttribute("empresas_rut");


String empresas_id=(String)request.getAttribute("empresas_id");
String empresas_razonsocial=(String)request.getAttribute("empresas_razonsocial");
String direccion_id=(String)request.getAttribute("direccion_id");
String REGI_NOMBRE=(String)request.getAttribute("REGI_NOMBRE");
String cont_id=(String)request.getAttribute("cont_id");
String CONT_TELEFONO=(String)request.getAttribute("CONT_TELEFONO");
String GD_RESPONSBALE=(String)request.getAttribute("GD_RESPONSBALE");
String GD_TRANSPORTE=(String)request.getAttribute("GD_TRANSPORTE");

String guia_obs1=(String)request.getAttribute("guia_obs1");
String guia_obs2=(String)request.getAttribute("guia_obs2");
String estado_sii=(String)request.getAttribute("estado_sii");	

String GD_FOLIO=(String)request.getAttribute("GD_FOLIO");

if(GD_FOLIO==null || GD_FOLIO.equals("") || GD_FOLIO.equals("null")) GD_FOLIO ="ND";

String tipo_guia_serv=(String)request.getAttribute("tipo_guia_serv");




String subtotal=(String)request.getAttribute("subtotal");
String neto=(String)request.getAttribute("neto");
String total=(String)request.getAttribute("total");
String desc=(String)request.getAttribute("descuento");
String gr_glosa_desc=(String)request.getAttribute("glosadescuento");
String iva=(String)request.getAttribute("iva");



String tipo_dteref=(String)request.getAttribute("tipo_dteref");	
String folioref=(String)request.getAttribute("folioref");	
String fec_ref=(String)request.getAttribute("fec_ref");

    %>
<!DOCTYPE html>
<html lang="en"><head>
    <meta charset="utf-8">
    <title>New Office - Modificar Guia de Despacho Venta Servicio</title>
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

    
        
        $( document ).ready(function() {
                        // Handler for .ready() called.
                        
                        
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
                        
                        $('#clientes_id').on('change', function() {
                                            var cli_seleccionada= $(this).val();
                                               
                                            $('#dire_id').empty();
                                            $('#cont_id').empty(); 
                                            
                                            
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
                                                        $('#giro_prev').val(e_ar[5]);
                                                         
                                                        
                                                        
                                                        break;
                                                      }
                                                    
                                                    }
                                                for(var ii=0; ii< dirs_ar.length;ii++){
                                             
                                                  var ubi_ar =dirs_ar[ii].split("/");
                                                  if(ubi_ar[2]==cli_seleccionada){
                                                	  
                                                	  if(ubi_ar[0]==<%=direccion_id%>){
                                                		  $('#dire_id').append($('<option>', {
                                                              value: ubi_ar[0],
                                                              text: ubi_ar[1],
                                                              selected:'selected'
                                                              }));
                                                	  }
                                                	  else{
                                                		  $('#dire_id').append($('<option>', {
                                                              value: ubi_ar[0],
                                                              text: ubi_ar[1]
                                                              })); 
                                                	  }
                                                	  
                                                	  
                                                    
                                                  }
                                                
                                                }
                                                
                                                for(var ii=0; ii< cont_ar.length;ii++){
                                                    
                                                    var ubi_ar =cont_ar[ii].split("/");
                                                    if(ubi_ar[2]==cli_seleccionada){
                                                    	
                                                    	
                                                    	if(ubi_ar[0]==<%=cont_id%>){
                                                    		$('#cont_id').append($('<option  >', {
                                                                value: ubi_ar[0],
                                                                text: ubi_ar[1],
                                                                selected:'selected'
                                                                }));
                                                    		
                                                    		
                                                    	} else{
                                                    	
                                                      $('#cont_id').append($('<option  >', {
                                                                                  value: ubi_ar[0],
                                                                                  text: ubi_ar[1]
                                                                                 
                                                                                  }));
                                                    	}
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
                              	
                              	$('#cont_mail').val(c_ar[6]);
            					$('#cont_phonec').val(c_ar[5]);
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
                        
                        
                        $('#clientes_id').change();
                        
                        
  
                        
                        $('#subtotal').on('blur', function() {
                        	
                        	$('#subtotal').val(Number($('#val1').val())+Number($('#val2').val())+Number($('#val3').val())+Number($('#val4').val())+Number($('#val5').val()));
                        	
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
                       
                        
                    
                      
                    
        });
        
        function validateSubmit(){
        	$("#form1").attr("action", "");
        	
        	$("#empresa_emisora_nombre").val($("#empresa_id").find('option:selected').text());
        	$("#cont_nombre").val($("#cont_id").find('option:selected').text());
    		
        	
        	var validate= true;
        	var msg="ERRORES: \n";
        	
        	if(document.getElementById('obs1').value==""){
        		msg+="DEBE INGRESAR LA FORMA DE DESPACHO\n";
        		validate=false;
        	}
        	if(document.getElementById('obs2').value==""){
        		msg+="DEBE INGRESAR UNA OBSERVACION\n";
        		validate=false;
        	}
        	
        	if(document.getElementById('total').value<0){
        		validate=false;
        		msg+="NO SE PUEDE INGRESAR LA GUIA CON VALORES NEGATIVOS\n";
        	}
        	
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
    		$("#rz_prev").val($("#rz_cliente").val());
    		
    		
    		
    		$("#dsco_prev").val($("#desc").val());
    		
    		$("#fec_prev").val($("#datepicker3").val());
    		
    		
    		
    		$("#dir_prev").val($("#dire_id :selected").text());
    		
    		$("#comuna_prev").val($("#gv_comuna").val());
    		$("#ciudad_prev").val($("#gv_ciudad").val());
    		$("#telefono_prev").val($("#cont_phone").val());
    		
    		
    		$("#obs_prev").val($("#obs1").val());
    		
    		$("#obs_prev2").val($("#cont_id :selected").text()+'-'+$("#cont_mail").val());
    		
    		//$("#obs_prev2").val($("#ref").val());
    		$("#obs_prev3").val($("#obs2").val());
    		
    	
    		
    		
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
    		

    		if($("#tipo_dteref").find('option:selected').text()!="NINGUNA"){
    			$("#ref_prev").val($("#tipo_dteref").find('option:selected').text()+": Nro. "+$("#folioref").val()+" del "+$("#datepickerref").val());
    				
    		}
    		
    		$("#neto_prev").val($("#subtotal").val());
    		$("#iva_prev").val($("#iva").val());
    		$("#total_prev").val($("#total").val());
    		
    		
    	}
    
        function validateNum(event) {
            var key = window.event ? event.keyCode : event.which;

            if (event.keyCode == 8 || event.keyCode == 46
             || event.keyCode == 37 || event.keyCode == 39) {
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
	  <p>N822.M.02.001</p>
	</div>
	<form method="get" action="iperusu" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/822/mserv_search'">VOLVER</button> 
	<div align="center" class="title">
		<p>MODIFICAR GUIA DE DESPACHO VENTA SERVICIO</p>
	</div>
</div>
<div class="content" >
  <form action="" name="form1" id="form1" method="post"> 
  <input type="hidden" name="gd_id" id="gd_id" value="<%=gd_id %>">
	<div class=" cuadroblanco" style="height:80px;padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>FEC CREA:</strong><input type="text" style="width:225px;height:30px ;background:#FFF;" value="<%=fec_crea %>" disabled></div>
		<div class="divhead"><strong>USU CREA:</strong><input type="text" style="width:400px;height:30px ;background:#FFF;" value="<%=user_crea %>" disabled></div>
		<div class="divhead"><strong>FEC ULT MOD:</strong><input type="text" style="width:225px;height:30px ;background:#FFF;" value="<%=fec_mod %>" disabled></div>
		<div class="divhead"><strong>USU MOD:</strong><input type="text" style="width:400px;height:30px ;background:#FFF;" value="<%=user_mod %>" disabled></div>
	</div>

	<div class=" cuadroblanco" style="height:80px;padding: 5px 5px 5px 5px;margin-top: 5px">
		<div class="divhead"><strong>NRO.GUIA:</strong><input type="text" style="width:80px;height:30px ;background:#FFF;" value="<%=GD_FOLIO %>" disabled></div>
		<div class="divhead"><strong>FECHA:</strong><input type="text" style="width:130px;height:30px ;background:#FF3;" value="<%=gd_fecha %>" readonly="readonly" class="amarillo" id="datepicker3" name="gv_fecha" ></div>
		<div class="divhead"><strong>EMISOR:</strong><select name="empresa_id" id="empresa_id" style="margin:0px 0px 0px 0px;width: 150px" >
				<% for(int i =0; i<ar_empresas.length; i++){
					String[] empresas = ar_empresas[i].split("/");%>
				<option value="<%=empresas[0]%>"<% if(empresas_id.equals(empresas[0]))%><%="selected"%>><%=empresas[1] %></option>
			<% } %>
    		</select><span class="cabecera" style="color:#F00">*</span></div>
		
		<div class="divhead"><strong>ESTADO SII:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" value="<%=estado_sii%>" disabled></div>
		<div class="divhead"><strong>ESTADO:</strong><select name="estados_vig_novig_id" id="estados_vig_novig_id" style="margin:0px 0px 0px 0px;width: 167px">
				<% for(int i =0; i<ar_estados.length; i++){
				String[] estados_vig_novig = ar_estados[i].split("/");%>
				<option value="<%=estados_vig_novig[0]%>"<% if(estados_vig_novig_id.equals(estados_vig_novig[0]))%><%="selected"%>><%=estados_vig_novig[1] %></option>
    			<% } %>
    		</select><span class="cabecera" style="color:#F00">*</span></div>
    	<div class="divhead"><strong>TIPO GUIA SERV:</strong><select name="tipo_guia_serv" id="tipo_guia_serv" style="margin:0px 0px 0px 0px;" >
				<option value="1" <% if(tipo_guia_serv.equals("1")){%> selected="selected" <%} %>>SERVICIO IMPRESION</option>
				<option value="2" <% if(tipo_guia_serv.equals("2")){%> selected="selected" <%} %> >SERVICIO TÉCNICO</option>
			
    		</select><span class="cabecera" style="color:#F00">*</span></div>
    		<div class="divhead" style="vertical-align:middle;padding: 8px 0 8px 0;" ><strong>AFECTA:</strong><input type="checkbox" value="1" name="g_afecta" id="g_afecta"  class="searchInput" <% if(g_afecta.equals("1")){%> checked="checked" <%} %>  ></div>
	</div>

	<div class=" cuadroblanco" style="height:275px;margin-top: 5px">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CLIENTE</p></h3>
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>CLIENTE:</strong><select name="clientes_id" id="clientes_id" style="margin:0px 0px 0px 0px;">
				<% for(int i =0; i<ar_clientes.length; i++){
					String[] clientes = ar_clientes[i].split("/");%>
				<option value="<%=clientes[0]%>"<% if(clientes_id.equals(clientes[0]))%><%="selected"%>><%=clientes[1] %></option>
			<% } %>
    		</select><span class="cabecera" style="color:#F00">*</span></div>
		<div class="divhead"><strong>RUT:</strong><input type="text" name="empresas_rut" id="empresas_rut" style="width:145px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_rut %>" ></div>
		<div class="divhead"><strong>COD CLIENTE:</strong><input type="text" name="cod_cliente" id="cod_cliente"  style="width:70px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_id %>" ></div>
		<div class="divhead"><strong>RAZON SOCIAL:</strong><input type="text" name="rz_cliente" id="rz_cliente" style="width:600px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_razonsocial %>"></div>
		<div class="divhead"><strong>DIRECCION:</strong><select name="dire_id" id="dire_id" style="margin:0px 0px 0px 0px;width: 700px">
				<% for(int i =0; i<ar_direcciones.length; i++){
					String[] direcciones = ar_direcciones[i].split("/");%>
				<option value="<%=direcciones[0]%>"<% if(direccion_id.equals(direcciones[0]))%><%="selected"%>><%=direcciones[1] %></option>
			<% } %>
    		</select><span class="cabecera" style="color:#F00">*</span></div>
		<div class="divhead"><strong>REGION:</strong><input type="text" id="regi"  style="width:510px;height:30px ;background:#FFF;" disabled="disabled" value=""></div>
		<div class="divhead"><strong>CIUDAD:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" id="gv_ciudad" name="gv_ciudad" readonly="readonly" value=""></div>
		<div class="divhead"><strong>COMUNA:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" id="gv_comuna" disabled="disabled" value="" ></div>
		<div class="divhead"><strong>RESPONSABLE CUENTA:</strong><input type="text" name="resp" id="resp" style="width:350px;height:30px ;background:#FFF;" readonly="readonly" value="<%=GD_RESPONSBALE%>"></div>
	</div>
	
	</div>
	
	<div class=" cuadroblanco" style="height:120px;margin-top: 5px">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CONTACTO Y GUIA</p></h3>
				<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>CONTACTO:</strong><select name="cont_id" id="cont_id" style="margin:0px 0px 0px 0px;width: 366px">
				<% for(int i =0; i<ar_contactos.length; i++){
					String[] contactos = ar_contactos[i].split("/");%>
				<option value="<%=contactos[0]%>"<% if(cont_id.equals(contactos[0]))%><%="selected"%>><%=contactos[1] %></option>
			<% } %>
    		</select><span class="cabecera" style="color:#F00">*</span></div>
					<div class="divhead"><strong>E-MAIL:</strong><input type="text" id="cont_mail" name="cont_email" style="width:350px;height:30px ;background:#FFF;" readonly="readonly" value=""></div>
	
		<div class="divhead"><strong>TELEFONO:</strong><input type="text" id="cont_phone" name="cont_phone" style="width:150px;height:30px ;background:#FFF;" readonly="readonly" value="<%=CONT_TELEFONO %>"></div>
		<div class="divhead"><strong>CELULAR:</strong><input type="text" id="cont_phonec" name="cont_telefonoc" style="width:150px;height:30px ;background:#FFF;" readonly="readonly" value=""></div>
		
		</div>
	</div>

	<div class=" cuadroblanco" style="height:235px;margin-top: 5px">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS SERVICIOS</p></h3>
			<div style="padding: 5px 5px 5px 5px">
		<%        
		for(int i =0; i<5; i++){
			String servicio="";
			String valor="";
			try{
			if(arProd[i] !=null){
				String[] Prod = arProd[i].split("/");
				java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
       	 		String valString = nf.format(Integer.parseInt(Prod[2])).replace(",",".");
       	 		servicio=Prod[1];
       	 		valor=Prod[2];
			}
			}catch(Exception e){}
		%>
		<div class="divhead"><strong>SERVICIO <%=i+1%>:</strong><input type="text" class="amarillo" maxlength="53" style="width:600px;height:30px ;background:#FF3;" name="serv<%=i+1%>" id="serv<%=i+1%>" value="<%=servicio%>" <% if(i==0){%> autofocus="autofocus" <%} %>></div>
		<div class="divhead"><strong>VALOR <%=i+1%>:</strong><input type="text" onkeydown="return validateNum(event)" class="amarillo" maxlength="9" style="width:120px;height:30px ;background:#FF3;" name="val<%=i+1%>" id="val<%=i+1%>" value="<%=valor%>" onblur="$('#subtotal').blur();"></div>
		<% } %>
		</div>
	</div>
	
	
	<div class=" cuadroblanco" style="height:120px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>SUBTOTAL:</strong><input name="subtotal" id="subtotal" type="text" style="width:150px;height:30px ;background:#FFF;" value="<%=subtotal %>"  readonly="readonly"></div>
		<div class="divhead"><strong>MONTO DESCUENTO:</strong><input type="text" name="desc"  id="desc" onkeydown="return validateNum(event)" class="amarillo" style="width:150px;height:30px ;background:#FF3;"  value="<%=desc %>"  maxlength="12"  ></div>
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

	<div class=" cuadroblanco" style="height:120px;padding: 5px 5px 5px 5px; margin-top: 5px">
		<div class="divhead"><strong>FORMA DE DESPACHO:</strong><input  type="text" name="obs1" id="obs1" class="amarillo" maxlength="64" style="width:720px;height:30px ;background:#FF3;" value="<%=guia_obs1%>"></div>
		<div class="divhead"><strong>OBSERVACION:</strong><input type="text" name="obs2" id="obs2" class="amarillo" maxlength="64" style="width:720px;height:30px ;background:#FF3;" value="<%=guia_obs2%>"></div>
		<div class="bgrabar">
		<input type="hidden" name="empresa_emisora_nombre" id="empresa_emisora_nombre" >
		<input type="hidden" name="cont_nombre" id="cont_nombre" >
		
		
		
			
			<input type="hidden"  name="rut_prev"  id="rut_prev">
			<input type="hidden" value="" name="fec_prev" id="fec_prev">
			<input type="hidden" name="total_prev" id="total_prev" value="0">
			<input type="hidden" name="iva_prev" id="iva_prev" value="0">
			<input type="hidden" name="neto_prev" id="neto_prev" value="0">
			<input type="hidden" name="dsco_prev" id="dsco_prev" value="0">
			
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
			
			<input type="hidden"  name="ref_prev" id="ref_prev">
			
			<input type="hidden" name="detalle_prev[]" id="detalle_prev1">
			<input type="hidden" name="detalle_prev[]" id="detalle_prev2">
			<input type="hidden" name="detalle_prev[]" id="detalle_prev3">
			<input type="hidden" name="detalle_prev[]" id="detalle_prev4">
			<input type="hidden" name="detalle_prev[]" id="detalle_prev5">
				<input type="hidden" name="detalle_descuento_prev" id="detalle_descuento_prev">
				<input type="hidden" name="tipo_doc_titulo_prev" id="tipo_doc_titulo_prev" value="GUIA ELECTRONICA">
			
		
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


