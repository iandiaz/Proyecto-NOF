<%@page import="java.util.ArrayList"%>
<% 	
String gv_id="";
String estados_vig_novig_id="";
String empresas_id=(String)request.getAttribute("empresa_emisor");
if(empresas_id==null)empresas_id ="";
String clientes_id=(String)request.getAttribute("empresas_id");
String contactos_id="";
String direcciones_id="";
if(request.getAttribute("gv_id")!=null && !request.getAttribute("gv_id").equals("") && !request.getAttribute("gv_id").equals("null")) gv_id =(String) request.getAttribute("gv_id");
if(request.getAttribute("estados_vig_novig_id")!=null && !request.getAttribute("estados_vig_novig_id").equals(""))estados_vig_novig_id=(String) request.getAttribute("estados_vig_novig_id");

String[] ar_estados =(String[]) request.getAttribute("ar_estados");
String[] ar_empresas =(String[]) request.getAttribute("ar_empresas");

//String[] ar_clientes =(String[]) request.getAttribute("ar_clientes");
String empresas_nombrenof=(String)request.getAttribute("empresas_nombrenof");
String empresas_rut=(String)request.getAttribute("empresas_rut");
String empresas_razonsocial=(String)request.getAttribute("empresas_razonsocial");
String Usuarios_nombre_com=(String)request.getAttribute("Usuarios_nombre_com");



String empresas_rut_ar[]=empresas_rut.split("-");

java.text.NumberFormat nff = java.text.NumberFormat.getInstance();
	String valRut = nff.format(Integer.parseInt(empresas_rut_ar[0]));

	empresas_rut=valRut+"-"+empresas_rut_ar[1];

	
String[] ar_contactos =(String[]) request.getAttribute("ar_contactos");
String[] ar_direcciones =(String[]) request.getAttribute("ar_direcciones");

String[] ar_detguias =(String[]) request.getAttribute("ar_detguias");

int numguias =0;

if(ar_detguias!=null && ar_detguias.length>0) numguias= ar_detguias.length;


String Usu_nom=(String)request.getAttribute("usuname");	

String fecha=(String)request.getAttribute("fecha");
	
String obs=(String)request.getAttribute("obs");

String empresas_giro=(String)request.getAttribute("empresas_giro");

String empresa_emisor=(String)request.getAttribute("empresa_emisor");
String afecta=(String)request.getAttribute("afecta");

String fac_servim_fecvencimiento=(String)request.getAttribute("fac_servim_fecvencimiento");


if(fac_servim_fecvencimiento==null) fac_servim_fecvencimiento="";


if(obs==null) obs="";

String fec_ar[]=fecha.split("-");

String refdet="";


String fac_servim_tipodte=(String)request.getAttribute("fac_servim_tipodte");

if(fac_servim_tipodte==null) fac_servim_tipodte="33";


String desc=(String)request.getAttribute("desc");
if(desc==null) desc="0";

String glosa_desc=(String)request.getAttribute("glosa_desc");
if(glosa_desc==null) glosa_desc="";

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
    <title>New Office - Factura Afeca Exenta Guia Resumen</title>
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
    <script type="text/javascript" src="/802/lib/jquery.Rut.min.js"></script>
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
    	/*display: inline-block;*/ 
    	
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
                                      dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa'],
                                      minDate: new Date(<%=fec_ar[2]%>, <%=fec_ar[1]%> - 1, <%=fec_ar[0]%>),
                                      
                                      
                                      });
$( "#datepicker2" ).datepicker({ dateFormat: "dd-mm-yy", monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
                                       monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
                                       dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
                                       dayNamesShort: ['Dom','Lun','Mar','Mie','Juv','Vie','Sab'],
                                       dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa']
                                       
                                       });
                                       
$( "#datepickerfechafactura" ).datepicker({
	dateFormat: "dd-mm-yy", monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
    monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
    dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
    dayNamesShort: ['Dom','Lun','Mar','Mie','Juv','Vie','Sab'],
    dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa'],
    onClose: function( selectedDate ) {
        $( "#datepicker" ).datepicker( "option", "minDate", selectedDate );
      }
    

});

$( "#datepickerref" ).datepicker({ dateFormat: "dd-mm-yy", monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
    monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
    dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
    dayNamesShort: ['Dom','Lun','Mar','Mie','Juv','Vie','Sab'],
    dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa']
});


        
});
    
   
    
    //DEFINIMOS ACTION DE EMPRESAS 
    var empresas_ar = new Array();
 
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
                        
                        $('#clpr_id').on('change', function() {
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
                        
                        
                        $('#neto').on('blur', function() {
                        	var tipdt=$('input[name=tipodte]:checked').val();
                        	
                        	$('#tipodte_prev').val(tipdt);
                        	
                        	if(tipdt=='33') $('#iva').val(Math.round((Number($('#neto').val())-Number($('#desc').val()))*0.19));
                        	if(tipdt=='34') $('#iva').val("0");
                        	$('#totaltotal').val(Math.round( (Number($('#neto').val())-Number($('#desc').val()))+Number($('#iva').val())));
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
                        
                        
                        $('#cont_id').change();
                         $('#dire_id').change();
                         $('#neto').blur();
                         
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
        
function validateSubmit(){
	
	$("#cont_nombre").val($("#cont_id").find('option:selected').text());
	$("#empresa_emisora_nombre").val($("#empresa_id").find('option:selected').text());
	
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
        	
        	if(document.getElementById('obs').value==""){
        		msg+="DEBE INGRESAR LA OBSERVACION\n";
        		validate=false;
        	}
        
        	
        	if(document.getElementById('datepicker').value==""){
        		msg+="DEBE INGRESAR FECHA DE VENCIMIENTO\n";
        		validate=false;
        	}
        	
        	if(document.getElementById('desc').value==""){
        		msg+="DEBE INGRESAR DESCUENTO\n";
        		validate=false;
        	}
        	
        	
        	if($("#empresa_id").find('option:selected').val()!=$("#empresa_emisor").val()){
        		msg+="LA EMPRESA EMISORA EN EL DETALLE DE LA FACTURA NO ES IGUAL A LA EMPRESA SELECCIONADA EN EL ENCABEZADO\n";
        		validate=false;
        	}
        	
        	var facafec="";
        	if($('input[name=tipodte]:checked').val()=='33')facafec=1;
        	if($('input[name=tipodte]:checked').val()=='34')facafec=0;
        	
        	if(facafec!=<%=afecta%>){
        		msg+="EL TIPO DE DOCUMENTO EN EL DETALLE DE LA FACTURA NO ES IGUAL AL TIPO DE DOCUMENTO SELECCIONADO EN EL ENCABEZADO\n";
        		validate=false;
        	}
        	        	
        	if(<%=numguias%> == 0){
        		msg+="DEBE INGRESAR GUIAS.";
        		validate=false;
        	}
        	
        	if(validate){
        		
        		return true;
        	}
        	else{
        		alert(msg);
        		return false;
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
	  <p>N802.I.02.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/802/ires_search'">VOLVER</button> 
	<div align="center" class="title">
		<p>INGRESAR FACTURA AFECTA - EXENTA GUIA RESUMEN</p>
	</div>
</div>
<div class="content" >
  <form action="ires_add" name="form1" id="form1" method="post"  > 
  	<input type="hidden" name="clientes_id" value="<%=clientes_id %>" >
  	
  	<input type="hidden" name="empresa_emisor" id="empresa_emisor" value="<%=empresa_emisor %>" >
	<input type="hidden" name="afecta" id="afecta" value="<%=afecta %>" >
			
  	
	<div class=" cuadroblanco" style="height:80px;padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>NRO.FACTURA:</strong><input type="text" style="width:80px;height:30px ;background:#FFF;" name="gv_id" id="gv_id" value="ND" disabled="disabled"></div>
		<div class="divhead"><strong>FECHA:</strong><input type="text" style="width:130px;height:30px ;background:#FF3;" value="<%=fecha %>" class="amarillo" id="datepickerfechafactura" name="datepickerfechafactura" readonly="readonly" ><input type="hidden" value="<%=fecha%>" name="fec_prev"></div>
		<div class="divhead"><strong>EMISOR:</strong><select name="empresa_id" id="empresa_id" style="margin:0px 0px 0px 0px;" >
				<% for(int i =0; i<ar_empresas.length; i++){
					String[] empresas = ar_empresas[i].split("/");%>
				<option value="<%=empresas[0]%>"<% if(empresas_id.equals(empresas[0]))%><%="selected"%>><%=empresas[1] %></option>
			<% } %>
    		</select><span class="cabecera" style="color:#F00">*</span></div>
    		<div class="divhead"><strong>TIPO DOCUMENTO:</strong><input type="radio" name="tipodte"  id="tipodte1" value="33"  <% if(fac_servim_tipodte.equals("33")){%> checked="checked" <%} %>>(33)AFECTA <input type="radio" name="tipodte" id="tipodte2" value="34"  <% if(fac_servim_tipodte.equals("34")){%> checked="checked" <%} %>>(34)EXENTA</div>
			<div class="divhead"><strong>FEC. VENCIMIENTO:</strong><input maxlength="10" type="text" style="width:130px;height:30px ;background:#FF3;" value="<%=fac_servim_fecvencimiento %>" class="amarillo" id="datepicker" name="fac_servim_fecvencimiento" readonly="readonly"><span class="cabecera" style="color:#F00">*</span></div>
		
	</div>

	<div class=" cuadroblanco" style="height:275px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CLIENTE</p></h3>
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>CLIENTE:</strong><input type="text"  style="width:450px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_nombrenof %>" ></div>
		<div class="divhead"><strong>RUT:</strong><input type="text" style="width:145px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_rut %>" ><input type="hidden" value="<%=empresas_rut%>" name="rut_prev"></div>
		<div class="divhead"><strong>COD CLIENTE:</strong><input type="text" name="clientes_id" style="width:70px;height:30px ;background:#FFF;" disabled="disabled" value="<%=clientes_id %>" ></div>
		<div class="divhead"><strong>RAZON SOCIAL:</strong><input type="text" style="width:600px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_razonsocial %>"><input type="hidden" value="<%=empresas_razonsocial%>" name="rz_prev"></div>
		<div class="divhead"><strong>DIRECCION:</strong><select name="dire_id" id="dire_id" style="margin:0px 0px 0px 0px;width: 700px">
				<% for(int i =0; i<ar_direcciones.length; i++){
					String[] direcciones = ar_direcciones[i].split("/");%>
				<option value="<%=direcciones[0]%>"<% if(direcciones_id.equals(direcciones[0]))%><%="selected"%>><%=direcciones[1] %></option>
			<% } %>
    		</select><span class="cabecera" style="color:#F00">*</span></div>
		<div class="divhead"><strong>REGION:</strong><input type="text" id="regi" style="width:510px;height:30px ;background:#FFF;" disabled="disabled" value=""></div>
		<div class="divhead"><strong>CIUDAD:</strong><input type="text" name="gv_ciudad" style="width:350px;height:30px ;background:#FFF;" id="gv_ciudad" readonly="readonly" value="" ><input type="hidden"  name="ciudad_prev" id="ciudad_prev"></div>
		<div class="divhead"><strong>COMUNA:</strong><input type="text" style="width:350px;height:30px ;background:#FFF;" id="gv_comuna" disabled="disabled" value="" ><input type="hidden"  name="comuna_prev" id="comuna_prev"></div>
		<div class="divhead"><strong>RESPONSABLE CUENTA:</strong><input type="text"  style="width:450px;height:30px ;background:#FFF;" name="resp" value="<%=Usuarios_nombre_com%>"></div>
	</div>
	
	</div>
	
	<div class=" cuadroblanco" style="height:120px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CONTACTO Y GUIA</p></h3>
		
		<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>CONTACTO:</strong><select name="cont_id" id="cont_id" style="margin:0px 0px 0px 0px; width: 366px">
				<% for(int i =0; i<ar_contactos.length; i++){
					String[] contactos = ar_contactos[i].split("/");%>
				<option value="<%=contactos[0]%>"<% if(contactos_id.equals(contactos[0]))%><%="selected"%>><%=contactos[1] %></option>
			<% } %>
    		</select><span class="cabecera" style="color:#F00">*</span></div>
		<div class="divhead"><strong>E-MAIL:</strong><input type="text" id="cont_mail" name="cont_mail" style="width:350px;height:30px ;background:#FFF;" readonly="readonly" value=""></div>
		<div class="divhead"><strong>TELEFONO:</strong><input type="text" id="cont_phone"  name="cont_phone" style="width:240px;height:30px ;background:#FFF;" readonly="readonly" value=""><input type="hidden" name="telefono_prev" id="telefono_prev"></div>
		<div class="divhead"><strong>CELULAR:</strong><input type="text" id="cont_phonec" name="cont_telefonoc" style="width:150px;height:30px ;background:#FFF;" readonly="readonly" value=""></div>		
	
	</div>
	
	</div>
	
	<div class=" cuadroblanco" style="height:250px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">GUIAS DE DESPACHO VENTA ACTIVO O SERVICIO</p></h3>
		<div style=" max-height:300px; width:1000px; ">
			<table class="table" style="margin-left:0px; width:100%;">
			<thead style="border: 1px solid black;display: inline-block;">
				<tr style="background-color:#0101DF;color:#FFFFFF">
					<td width="300px" style="font-size:20px"><strong>FOLIO</strong></td>
					<td width="350px" style="font-size:20px"><strong>MONTO</strong></td>
					<td width="350px" style="font-size:20px"><strong>FECHA</strong></td>
				</tr>  
			<tbody id="fbody" class="scroll" style="border: 1px solid black;display: inline-block;">
			 <%        
			 int valor=0;
			
			 String codigo="";
				if(ar_detguias!=null)for(int i =0; i<ar_detguias.length; i++){
					String[] Alertas = ar_detguias[i].split("/");
			
			        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
			        String valString = nf.format(Integer.parseInt(Alertas[1])).replace(",",".");
			        
			        valor+=Integer.parseInt(Alertas[1]);
			        refdet+="GUIA DE DESPACHO ELECTRONICA: Nro. "+Alertas[3]+" del "+Alertas[2]+"<br>";
			        codigo+=","+Alertas[3];
					
			        
			 %>
			 
					<tr class="hov">
						<td width="300px" ><%=Alertas[3]%><input type="hidden" value="<%=Alertas[0]%>/<%=Alertas[1] %>/<%=Alertas[2] %>/<%=Alertas[3] %>" name="detguias[]"></td>
						<td width="350px" >$<%=valString%></td>
						<td width="350px" ><%=Alertas[2]%></td>
					</tr>
				<% } %>
			</table>
			
			<input type="hidden" name="detalle_prev[]" value="<%=codigo%>;DE ACUERDO A LAS GUIAS <%=codigo%>;1;<%=valor%>;0;<%=valor%>">
			
			<div class="bgrabar" style="width: 600px;">
			<span style="text-align: center; float: initial; ">SELECCIONAR MAXIMO 14 GUIAS</span>
				
				<button type="submit" name="agregarguias" id="agregarguias" class="btn btn-success" onclick="$('#form1').attr('action', 'ires_guias'); return true;" style="float: right;" >AGREGAR</button>
       		</div>
		</div>
	</div>
	<div class=" cuadroblanco" style="height:120px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>SUBTOTAL:</strong><input type="text" name="subtotal" id="neto" style="width:150px;height:30px ;background:#FFF;" value="<%=valor %>" id="neto"  readonly="readonly"></div>
		<div class="divhead"><strong>MONTO DESCUENTO:</strong><input type="text" name="desc"  id="desc" class="amarillo" style="width:150px;height:30px ;background:#FF3;"  value="<%=desc %>"  maxlength="12" ></div>
		<div class="divhead" style="width:800px;"><strong>GLOSA DESCUENTO:</strong><input type="text" name="glosa_desc"  id="glosa_desc" class="amarillo" style="width:350px;height:30px ;background:#FF3;" maxlength="30"  value="<%=glosa_desc %>"  ></div>
		<div class="divhead"><strong>NETO:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;" readonly="readonly" value="" id="netoneto" name="neto"></div>
		<div class="divhead"><strong>IVA 19%:</strong><input type="text" style="width:120px;height:30px ;background:#FFF;" readonly="readonly" value="" id="iva" name="iva"></div>
		<div class="divhead"><strong>TOTAL:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" readonly="readonly" value="" id="totaltotal" name="total"></div>
	
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
		<div class="divhead"><strong>FECHA REF:</strong><input type="text" style="width:130px;height:30px ;background:#FF3;" name="fec_ref" id="datepickerref"  readonly="readonly" class="amarillo" ></div>
	</div>
		
	</div>
	
	<div class=" cuadroblanco" style="height:80px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>OBSERVACION:</strong><input type="text" name="obs" id="obs" class="amarillo" maxlength="64" style="width:720px;height:30px ;background:#FF3;" value="<%=obs%>" ></div>
	</div>
		<div class="bgrabar">
		<input type="hidden" name="cont_nombre" id="cont_nombre" >
		<input type="hidden" name="empresa_emisora_nombre" id="empresa_emisora_nombre" >
			<input type="hidden" name="ref_prev" id="ref_prev" value="<%=refdet%>">
		<input type="hidden" name="tipodte_prev" id="tipodte_prev">
		
			<input type="hidden" name="total_prev" id="total_prev" value="0">
			<input type="hidden" name="iva_prev" id="iva_prev" value="0">
			<input type="hidden" name="neto_prev" id="neto_prev" value="0">
			<input type="hidden" name="dsco_prev" id="dsco_prev" value="0">
			
			<input type="hidden" name="dir_prev" id="dir_prev" value="">
			<input type="hidden" name="obs_prev" id="obs_prev" value="">
			<input type="hidden" value="<%=empresas_giro%>" name="giro_prev">
			<input type="hidden" name="detalle_descuento_prev" id="detalle_descuento_prev" >
			
			<input type="hidden" name="obs_prev2" id="obs_prev2" value="">
			<input type="hidden" name="obs_prev3" id="obs_prev3" value="">
			
			<button type="submit" name="previ" id="previ" class="btn btn-success" onclick="previsualizar()"  >PREVISUALIZAR</button>
          <button type="submit" name="grabar" id="grabar" class="btn btn-success" onclick="return validateSubmit();">GRABAR</button>
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
    
	function previsualizar(){
		
		$("#form1").attr("action", "http://186.67.10.115:81/NOF/factura_formato_venta.php");
		$("#total_prev").val($("#totaltotal").val());
		$("#iva_prev").val($("#iva").val());
		$("#neto_prev").val($("#neto").val());
		
		$("#dir_prev").val($("#dire_id").find('option:selected').text());
		$("#obs_prev").val($("#obs").val());
		$("#obs_prev2").val($("#cont_id :selected").text()+'-'+$("#cont_mail").val());
		
		$("#comuna_prev").val($("#gv_comuna").val());
		$("#ciudad_prev").val($("#gv_ciudad").val());
		$("#telefono_prev").val($("#cont_phone").val());
		$("#dsco_prev").val($("#desc").val());
		
		if($("#dsco_prev").val()>0){
			//generamos linea de descuento para previsualizacion 
			$("#detalle_descuento_prev").val($("#glosa_desc").val()+" $"+$("#desc").val());
			
		}
		
		if($("#tipo_dteref").find('option:selected').text()!="NINGUNA"){
			$("#ref_prev").val("<%=refdet%>"+$("#tipo_dteref").find('option:selected').text()+": Nro. "+$("#folioref").val()+" del "+$("#datepickerref").val());
				
		}
		
	}
       
    </script>
    
  </body>
</html>


