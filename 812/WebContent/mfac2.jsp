<%@page import="java.util.ArrayList"%>
<% 	


String gd_id=(String)request.getAttribute("gd_id");	

//data factura 
String[] arProd =(String[]) request.getAttribute("ar_prod");

String folio=(String)request.getAttribute("folio");	
if(folio==null) folio="ND";

String estado_sii=(String)request.getAttribute("estado_sii");	
String FAC_FECHA=(String)request.getAttribute("FAC_FECHA");	
String fac_servim_condiciones=(String)request.getAttribute("fac_servim_condiciones");	
String fac_servim_fecvencimiento=(String)request.getAttribute("fac_servim_fecvencimiento");	
String empresas_nombrenofemisor=(String)request.getAttribute("empresas_nombrenofemisor");	
String fac_servim_tipodte=(String)request.getAttribute("fac_servim_tipodte");	
String empresas_razonsocialcliente=(String)request.getAttribute("empresas_razonsocialcliente");	
String empresas_idcliente=(String)request.getAttribute("empresas_idcliente");	
String empresas_rutcliente=(String)request.getAttribute("empresas_rutcliente");	
String empresas_rut_ar[]=empresas_rutcliente.split("-");
java.text.NumberFormat nff = java.text.NumberFormat.getInstance();
String valRut = nff.format(Integer.parseInt(empresas_rut_ar[0]));
empresas_rutcliente=valRut+"-"+empresas_rut_ar[1];

String empresas_nombrenofcliente=(String)request.getAttribute("empresas_nombrenofcliente");	
	
String fac_servim_obs=(String)request.getAttribute("fac_servim_obs");	
String fac_servim_ref=(String)request.getAttribute("fac_servim_ref");	

String fac_servim_tcambio=(String)request.getAttribute("fac_servim_tcambio");	


String CONT_EMAIL=(String)request.getAttribute("CONT_EMAIL");	
String CONT_NOMBRE=(String)request.getAttribute("CONT_NOMBRE");	
String CONT_TELEFONO=(String)request.getAttribute("CONT_TELEFONO");	
String PERS_NOMBRE=(String)request.getAttribute("PERS_NOMBRE");
String DIRE_DIRECCION=(String)request.getAttribute("DIRE_DIRECCION");
String DIRE_CIUDAD=(String)request.getAttribute("DIRE_CIUDAD");
String COMU_NOMBRE=(String)request.getAttribute("COMU_NOMBRE");
String REGI_NOMBRE=(String)request.getAttribute("REGI_NOMBRE");
String sub_fac_servim_total=(String)request.getAttribute("fac_servim_total");

String empresas_idemisor=(String)request.getAttribute("empresas_idemisor");
String clientes_id=(String)request.getAttribute("clientes_id");
String direcciones_id=(String)request.getAttribute("direcciones_id");
String contactos_id=(String)request.getAttribute("contactos_id");
String ivap=(String)request.getAttribute("fac_comserv_porcentaje_ivaret");
String fac_comserv_valor_ivanoret=(String)request.getAttribute("fac_comserv_valor_ivanoret");	


String id_dte=(String)request.getAttribute("id_dte");

String DESC=(String)request.getAttribute("DESC");	

String netoneto = (Math.round(((Double.parseDouble(sub_fac_servim_total)-Double.parseDouble(DESC)))))+"";

double iva_ = Integer.parseInt(netoneto)*Integer.parseInt(ivap)/100;

double total_= iva_+Integer.parseInt(netoneto);

String iva= Math.round(Double.parseDouble(iva_+""))+"";
String total= Math.round(Double.parseDouble(total_+""))+"";



String Usu_nom=(String)request.getAttribute("usuname");	

String[] ar_estados =(String[]) request.getAttribute("ar_estados");
String[] ar_empresas =(String[]) request.getAttribute("ar_empresas");
String[] ar_clientes =(String[]) request.getAttribute("ar_clientes");
String[] ar_contactos =(String[]) request.getAttribute("ar_contactos");
String[] ar_direcciones =(String[]) request.getAttribute("ar_direcciones");


String gr_glosa_desc=(String)request.getAttribute("fac_servim_glosa_desc");	
String[] ar_folios =(String[]) request.getAttribute("ar_folios");

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
                                                	  sel="";
                                                	  if(<%=direcciones_id%>==ubi_ar[0]){sel="selected='selected'";}
                                                	  
                                                	  $('#dire_id').append('<option '+sel+' value="'+ubi_ar[0]+'" >'+ubi_ar[1]+'</option>');
                                                  }
                                                
                                                }
                                                
                                                for(var ii=0; ii< cont_ar.length;ii++){
                                                    
                                                    var ubi_ar =cont_ar[ii].split("/");
                                                    if(ubi_ar[2]==cli_seleccionada){
                                                    	
                                                    	 sel="";
                                                   	  if(<%=contactos_id%>==ubi_ar[0]){sel="selected='selected'";}
                                                   	  
                                                   	  $('#cont_id').append('<option '+sel+' value="'+ubi_ar[0]+'" >'+ubi_ar[1]+'</option>');
                                                    	
                                                    
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
                        $('#ivap').on('blur', function() {
                        	$('#total').blur();
                                
       					 });
                       
                        $('#tipodte1, #tipodte11, #tipodte2, #tipodte22').on('change', function() {
                        	$('#total').blur();
                                
       					 });
                     
                        $('#tipodte1, #tipodte11, #tipodte2, #tipodte22').change();
                        
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
	  <p>N812.M.02.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/812/mfac1'">VOLVER</button> 
	<div align="center" class="title">
		<p>MODIFICAR FACTURA DE COMPRA AFECTA - EXENTA SERVICIO</p>
	</div>
</div>
<div class="content" >
    <form action="" name="form1" id="form1" method="post" > 
    <input type="hidden" name="gd_id" value="<%=gd_id %>" >
	<div class=" cuadroblanco" style="height:120px;padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>NRO.FACT:</strong><input type="text" name="gv_id" id="gv_id" style="width:80px;height:30px ;background:#FFF;"  value="<%=folio%>" disabled="disabled"></div>
		<div class="divhead"><strong>FECHA:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;"  value="<%=FAC_FECHA %>" disabled="disabled" id="datepicker3" name="gv_fecha" ></div>
		<div class="divhead"><strong>COND PAGO:</strong><input name="fac_servim_condiciones" id="fac_servim_condiciones" type="text" maxlength="30" style="width:350px;height:30px ;background:#FF3;" class="amarillo" value="<%=fac_servim_condiciones%>"></div>
		
		<div class="divhead"><strong>EMISOR:</strong><select name="fac_servim_emisor" id="fac_servim_emisor" style="margin:0px 0px 0px 0px;" >
				<% for(int i =0; i<ar_empresas.length; i++){
					String[] empresas = ar_empresas[i].split("/");%>
				<option value="<%=empresas[0]%>"<% if(empresas_idemisor.equals(empresas[0]))%><%="selected"%>><%=empresas[1] %></option>
			<% } %>
    		</select></div>
		<div class="divhead"><strong>ESTADO SII:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" value="<%=estado_sii%>" disabled="disabled"></div>
		<div class="divhead"><strong>TIPO DTE:</strong><input type="radio" name="tipodte" id="tipodte1" value="46" checked="checked">(46) FACT COMPRA ELECT</div>
		
		<div class="divhead"><strong>FEC. VENCIMIENTO:</strong><input maxlength="10" type="text" style="width:130px;height:30px ;background:#FF3;" value="<%=fac_servim_fecvencimiento %>" class="amarillo" id="datepicker" name="fac_servim_fecvencimiento" readonly="readonly" ></div>
		
		
	</div>

	<div class=" cuadroblanco" style="height:275px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CLIENTE</p></h3>
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>CLIENTE:</strong><select name="empresa_id" id="empresa_id" style="margin:0px 0px 0px 0px;">
				<% for(int i =0; i<ar_clientes.length; i++){
					String[] clientes = ar_clientes[i].split("/");%>
				<option value="<%=clientes[0]%>"<% if(clientes_id.equals(clientes[0]))%><%="selected"%>><%=clientes[1] %></option>
			<% } %>
    		</select></div>
		<div class="divhead"><strong>RUT:</strong><input type="text" id="empresas_rut" style="width:145px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_rutcliente%>" ></div>
		<div class="divhead"><strong>COD CLIENTE:</strong><input type="text"  style="width:70px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_idcliente%>"></div>
		<div class="divhead"><strong>RAZON SOCIAL:</strong><input type="text" id="rz_cliente"  style="width:500px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_razonsocialcliente%>"></div>
		<div class="divhead"><strong>DIRECCION:</strong><select name="dire_id" id="dire_id" style="margin:0px 0px 0px 0px;width: 700px">
				<% for(int i =0; i<ar_direcciones.length; i++){
					String[] direcciones = ar_direcciones[i].split("/");%>
				<option value="<%=direcciones[0]%>"<% if(direcciones_id.equals(direcciones[0]))%><%="selected"%>><%=direcciones[1] %></option>
			<% } %>
    		</select></div>
		<div class="divhead"><strong>REGION:</strong><input type="text" id="regi" style="width:510px;height:30px ;background:#FFF;" disabled="disabled" value="<%=REGI_NOMBRE%>"></div>
		<div class="divhead"><strong>CIUDAD:</strong><input id="gv_ciudad" type="text" style="width:350px;height:30px ;background:#FFF;" disabled="disabled" value="<%=DIRE_CIUDAD %>" ></div>
		<div class="divhead"><strong>COMUNA:</strong><input id="gv_comuna" type="text" style="width:350px;height:30px ;background:#FFF;"  disabled="disabled" value="<%=COMU_NOMBRE %>" ></div>
		<div class="divhead"><strong>RESPONSABLE CUENTA:</strong><input type="text" id="resp" style="width:350px;height:30px ;background:#FFF;" disabled="disabled" value="<%=PERS_NOMBRE%>"></div>
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
    		</select></div>
		<div class="divhead"><strong>E-MAIL:</strong><input type="text" id="cont_mail"  style="width:350px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CONT_EMAIL%>"></div>
		
		<div class="divhead"><strong>TELEFONO:</strong><input type="text" id="cont_phone"  style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CONT_TELEFONO%>"></div>
		<div class="divhead"><strong>CELULAR:</strong><input type="text" id="cont_phonec" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value=""></div>
		
		
	</div>
	
	</div>
	
	<div class=" cuadroblanco" style="height:235px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS SERVICIOS</p></h3>
		
		<div style="padding: 5px 5px 5px 5px;">
		<%        
		for(int i =0; i<5; i++){
			String servicio="";
			String valor="";
			try{
			if(arProd[i] !=null){
				String[] Prod = arProd[i].split("/");
       	 		servicio=Prod[1];
       	 		valor=Prod[2];
			}
			}catch(Exception e){}
		%>
		<div class="divhead"><strong>SERV <%=i+1%>:</strong><input type="text" maxlength="53"  class="amarillo" style="width:650px;height:30px ;background:#FF3;" name="serv<%=i+1 %>" id="serv<%=i+1 %>" value="<%=servicio%>" ></div>
		<div class="divhead"><strong>VALOR <%=i+1%>:</strong><input type="text" maxlength="9" onkeydown="return validateNum(event)" onblur="$('#total').val(Number($('#val1').val())+Number($('#val2').val())+Number($('#val3').val())+Number($('#val4').val())+Number($('#val5').val()));$('#total').blur();" class="amarillo" style="width:120px;height:30px ;background:#FF3;" name="val<%=i+1 %>" id="val<%=i+1 %>" value="<%=valor%>" ></div>
		<% } %>
		</div>
	</div>
	<div class=" cuadroblanco" style="height:200px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>SUBTOTAL:</strong><input name="total" id="total" type="text" style="width:150px;height:30px ;background:#FFF;"  value="<%=sub_fac_servim_total%>"  readonly="readonly"></div>
		<div class="divhead"><strong>MONTO DESCUENTO:</strong><input type="text" name="desc"  id="desc" onkeydown="return validateNum(event)" class="amarillo" style="width:150px;height:30px ;background:#FF3;" maxlength="12"  value="<%=DESC %>" required="required"   ></div>
		<div class="divhead"><strong>GLOSA DESCUENTO:</strong><input type="text"  name="glosa_desc"  id="glosa_desc" class="amarillo" style="width:600px;height:30px ;background:#FF3;" maxlength="53" value="<%=gr_glosa_desc %>"  required="required" ></div>
		<div class="divhead"><strong>NETO:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;" readonly="readonly" value="<%=netoneto %>" id="netoneto" name="netoneto" ></div>
		<div class="divhead"><strong>%IVA RETENIDO:</strong><input type="text" onKeyPress="if (event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;" style="width:120px;height:30px ;background:#FF3;" class="amarillo" value="<%=ivap%>" id="ivap" name="ivap" > $<input type="text" style="width:120px;height:30px ;background:#FFF;" readonly="readonly" value="<%=iva%>" id="iva" name="iva"></div>
		<div class="divhead"><strong>%IVA NO RETENIDO:</strong><input type="text" onKeyPress="if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false;"  style="width:120px;height:30px ;background:#FFF;"  value="<%=((19-Integer.parseInt(ivap))+"") %>" id="perivanoret" name="perivanoret" readonly="readonly"  > $<input type="text" readonly="readonly" style="width:120px;height:30px ;background:#FFF;" value="<%=fac_comserv_valor_ivanoret%>" id="valivanoret" name="valivanoret" ></div>
		
		<div class="divhead"><strong>TOTAL:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" readonly="readonly"  value="<%=total%>" id="totaltotal" name="totaltotal"></div>
		<div class="divhead"><strong>REFERENCIA:</strong><input type="text"  name="fac_servim_ref" id="fac_servim_ref" class="amarillo" maxlength="66" style="width:750px;height:30px ;background:#FF3;" value="<%=fac_servim_ref%>" required></div>
	</div>
	</div>

	<div class=" cuadroblanco" style="height:120px;margin-top: 5px;">
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>TIPO CAMBIO:</strong><input type="text" name="fac_servim_tcambio" id="fac_servim_tcambio" class="amarillo" maxlength="9" style="width:120px;height:30px ;background:#FF3;" value="<%=fac_servim_tcambio%>" onkeydown="return validateNum(event)"><span class="cabecera" style="color:#F00">*</span></div>
		
		<div class="divhead"><strong>OBSERVACIONES:</strong><input type="text" name="fac_servim_obs" id="fac_servim_obs" class="amarillo" maxlength="64" style="width:720px;height:30px ;background:#FF3;" value="<%=fac_servim_obs%>"></div>
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
			<input type="hidden" value="<%=FAC_FECHA%>" name="fec_prev">
			
			<button type="submit" name="previ" id="previ" class="btn btn-success" onclick="previsualizar()"  >PREVISUALIZAR</button>
          <button type="submit" name="grabar" id="grabar" class="btn btn-success" <% if(id_dte.equals("1")){%> disabled="disabled" <% } %> onclick="return validateSubmit()" >GRABAR</button>
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


