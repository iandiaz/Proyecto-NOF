<%@page import="java.util.ArrayList"%>
<%
String Usu_nom=(String)request.getAttribute("usuname").toString();




String[] ar_ubi =(String[]) request.getAttribute("ar_ubi");
String[] ar_tubi =(String[]) request.getAttribute("ar_tubi");
String[] ar_funcionalidades =(String[]) request.getAttribute("ar_funcionalidades");
String[] ar_productos =(String[]) request.getAttribute("ar_productos");



//necesitamos los 6 permisos para validar 36 60 61 87

String p36=(String)request.getAttribute("p36");
String p60=(String)request.getAttribute("p60");
String p61=(String)request.getAttribute("p61");
String p87=(String)request.getAttribute("p87");
String p185=(String)request.getAttribute("p185");
String p186=(String)request.getAttribute("p186");
String p191=(String)request.getAttribute("p191");
String p192=(String)request.getAttribute("p192");
String p229=(String)request.getAttribute("p229");

String p242=(String)request.getAttribute("p242");
String p243=(String)request.getAttribute("p243");
String p247=(String)request.getAttribute("p247");


%>
<!DOCTYPE html>
<html lang="en">
<head>
 
    <meta charset="utf-8">
    <title>New Office - </title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href='http://fonts.googleapis.com/css?family=Inconsolata:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/butons.css">
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">
       <link rel="stylesheet" type="text/css" href="stylesheets/inputs.css">

    <script src="lib/jquery-ui/js/jquery-1.10.2.js"></script>
    <link rel="stylesheet" href="lib/jquery-ui/css/ui-lightness/jquery-ui-1.10.4.custom.css">
    
    <script src="lib/jquery-ui/js/jquery-ui-1.10.4.custom.js"></script>
    <link href="//cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/css/select2.min.css" rel="stylesheet" />
	<script src="//cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/js/select2.min.js"></script>
  
    <!-- Demo page code -->

   	<style type="text/css">
   		form{
	    	margin-top:0px;
	    	height: 100%;
	    }
      
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
         .divhead{
			float: left;
			font-size: 20px; 
			margin-right: 15px;
			margin-bottom: 2px;
			height: 36px;
			text-align: left;
			text-transform:uppercase;
		
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
		.informe{
			 width:800px; 
		}
		.informe option{
			 width:800px !important; 
			 min-width:800px !important;
			 max-width:800px;  
			 
		}
		
	@media screen and (min-width: 700px) {
		.cuadroblanco{
			box-shadow:0px 2px 5px 0px rgba(50, 50, 50, 0.75);
			border: 1px solid black;
			min-height:100%; 
			height:310px;
			min-width: 350px;
			max-width:950px;
			position: relative;
			background:#ccc;
			margin: 0 auto;
			padding: 5px 5px 5px 5px;
			

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
  </head>

  <!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
  <!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
  <!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
  <!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
  <!--[if (gt IE 9)|!(IE)]><!--> 
  <body class=""> 
  <!--<![endif]-->
  
    <script>
        $( document ).ready(function() {
        	
        	getGruposSelect();
        	getEmpresasSelect(null);
        	
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

                            
        });
        //////////////////////////////////////////////////////////////////
        ///////definimos array con ubicaciones////////////////////////////
        
        var ubicaciones_ar =new Array();
        
        <%  for(int i =0; i<ar_ubi.length; i++){
            %>
            ubicaciones_ar[<%=i%>]="<%=ar_ubi[i]%>";
            
            <% } %>
    
    
    var productos_ar= new Array();
    var productos_func_ar= new Array();
    <%  for(int i =0; i<ar_productos.length; i++){
        %>
        productos_ar[<%=i%>]="<%=ar_productos[i].replace("\"","")%>";
        
        <% } %>

        
    
        $( document ).ready(function() {
                        // Handler for .ready() called.
                        
                        
                        $('#select_unou').select2();
                        ////COMBO ANIDADO PARA FUNCIONALIDAD 
                       $('#select_funcionalidad').select2();
                        $('#select_funcionalidad').on('change', function() {
                        	productos_func_ar.length=0;
                            
                            var fun_seleccionada= $(this).val();
                            $('#select_prod').empty();
                                  $('#select_prod').append($('<option>', {
                                                                  value: '',
                                                                  text: 'TODOS'
                                                                  }));

                                
                            if(fun_seleccionada!=''){
                            	
                                for(var ii=0; ii< productos_ar.length;ii++){
                                  var prod_ar =productos_ar[ii].split(";;");
                                  if(prod_ar[2]==fun_seleccionada){
                                    $('#select_prod').append($('<option>', {
                                                                value: prod_ar[0],
                                                                text: prod_ar[1]
                                                                }));
                                    	productos_func_ar.push(prod_ar[0]+";;"+prod_ar[1]+";;"+prod_ar[2]);
                                 	 }
                                
                                }
                            }else{
                            	 for(var ii=0; ii< productos_ar.length;ii++){
                                     var ubi_ar =productos_ar[ii].split(";;");
                                       $('#select_prod').append($('<option>', {
                                                                   value: ubi_ar[0],
                                                                   text: ubi_ar[1]
                                                                   }));
                                       	productos_func_ar.push(ubi_ar[0]+";;"+ubi_ar[1]+";;"+ubi_ar[2]);
                                    	 
                                   
                                   }
                            	
                            }
                                
        });
                        $('#select_funcionalidad').change();
                        $('#select_direccion').select2();
                        $('#select_ubicacion').select2();
                        
                        
                        $('#select_direccion').on('change', function() {
                                            var dir_seleccionada= $(this).val();
                                               
                                            $('#select_ubicacion').empty();
                                            
                                                  $('#select_ubicacion').append($('<option>', {
                                                                                  value: '',
                                                                                  text: 'TODOS'
                                                                                  }));
                                                  $('#select_ubicacion').select2("val","");

                                                //recorremos array de direcciones para buscar las que correspondan a la empresa
                                            $('#select_tubi').empty();
                                                  $('#select_tubi').append($('<option>', {
                                                                                  value: '',
                                                                                  text: 'TODOS'
                                                                                  }));

                                                  
                                                
                                            if(dir_seleccionada!=''){
                                                  var tubica ="";
                                                for(var ii=0; ii< ubicaciones_ar.length;ii++){
                                                  var ubi = ubicaciones_ar[ii];
                                                  var ubi_ar =ubicaciones_ar[ii].split("/");
                                                  if(ubi_ar[2]==dir_seleccionada){
                                                    $('#select_ubicacion').append($('<option>', {
                                                                                value: ubi_ar[0],
                                                                                text: ubi_ar[0]+" "+ubi_ar[1]
                                                                                }));
                                                  if(tubica.indexOf(ubi_ar[3])<0){
                                                    $('#select_tubi').append($('<option>', {
                                                                             value: ubi_ar[3],
                                                                             text: ubi_ar[3]
                                                                             }));
                                                    tubica+=","+ubi_ar[3];

                                                  }
                                                  
                                                  }
                                                
                                                }
                                            }
                                                
                        });
                        
       
                    
     
                        
             /**********************************
             ******CADENA GRUPO 
             ***********************************/
             
            $('#select_grupo').on('change', function() {
                  var grup_seleccionada= $(this).val();
                  getEmpresasSelect(grup_seleccionada);
            });
                            
            /**********************************
             ******CADENA EMPRESA 
             ***********************************/
             
            
            $('#select_empresa').on('change', function() {
                  var emp_seleccionada= $(this).val();
                
                if(emp_seleccionada!=''){
                	
                	
                	getDireccionesSelect(emp_seleccionada);
               		getPeriodosSelect(emp_seleccionada);
                    
                	$('#select_direccion').change();
                }
                else{
                	$('#select_direccion').empty();
                	$('#select_direccion').append($('<option>', {
                         value: "",
                         text: "TODOS"
                         }));
                	$('#select_direccion').select2("val","");
                	
                	$('#select_periodo').empty();
                	$('#select_periodo').append($('<option>', {
                         value: "",
                         text: "TODOS"
                         }));
                	$('#select_periodo').select2("val","");
                }
                                                    
            });
                        
           
                            
            $('#informe').on('change', function() {
                             //alert('h');
                var inf_sel= $(this).val();
                
                             if(inf_sel==1){
                            	 sacaFiltros();
                                
                            	 $('.select_grupo').show();
                                 $('.select_empresa').show();
                                 $('.select_direccion').show();
                                
                                 
                                 $('.select_ubicacion').show();
                                 $('.select_tubi').show();
                                 $('.select_funcionalidad').show();
                                 $('#grabar_web').show();
                                 $('#grabar_pdf').show();
                                 $('#grabar_excel').show();
                                
                             }
                             if(inf_sel==2){
                            	 sacaFiltros();
                                 
                                 $('.select_grupo').show();
                                 $('.select_empresa').show();
                                 $('.select_direccion').show();
                                
                                 
                                 $('.select_ubicacion').show();
                                 $('.select_tubi').show();
                                 $('.select_funcionalidad').show();
                             
                                $('.fecs').show();
                                $('#grabar_web').show();
                                $('#grabar_pdf').show();
                                $('#grabar_excel').show();
                             }
                             if(inf_sel==3){
                            	 
 								sacaFiltros();
                                 
                                 $('.select_grupo').show();
                                 $('.select_empresa').show();
                                 $('.select_direccion').show();
                                 $('.select_ubicacion').show();
                                 $('.select_tubi').show();
                                 $('.select_funcionalidad').show();
                            	 
                            	 $('.selprod').show();
                            	 $('#grabar_web').show();
                                 $('#grabar_pdf').show();
                                 $('#grabar_excel').show();
                             }
                             if(inf_sel==4){
                            	 
								sacaFiltros();
                                 
                                 $('.select_grupo').show();
                                 $('.select_empresa').show();
                                 $('.select_direccion').show();
                                 $('.select_ubicacion').show();
                                 $('.select_tubi').show();
                                 $('.select_funcionalidad').show();
                            	 $('.selprod').show();
                                 $('.fecs').show();
                                 $('#grabar_web').show();
                                 $('#grabar_pdf').show();
                                 $('#grabar_excel').show();
                              }
                             
                            
 							if(inf_sel==5){
 								sacaFiltros();
 								 $('.select_grupo').show();
                                 $('.select_empresa').show();
                                 $('.select_direccion').show();
                                 $('.select_ubicacion').show();
                                 $('.select_tubi').show();
                                 $('.selprod').show();
                                 
                                 $('#grabar_web').show();
                                 $('#grabar_pdf').show();
                                 $('#grabar_excel').show();
                                 
                                
                              }
 							if(inf_sel==6){
 								sacaFiltros();
                                
                                $('.select_grupo').show();
                                $('.select_empresa').show();
                                $('.select_direccion').show();
                                $('.select_ubicacion').show();
                                $('.select_tubi').show();
                                $('.select_funcionalidad').show();
                           	 
                                $('.selprod').show();

                                $('.selusadonousado').show();
                                $('#grabar_web').show();
                                $('#grabar_pdf').show();
                                $('#grabar_excel').show();
 								 
                              }
 							
 							if(inf_sel==7){
 								
 								 sacaFiltros();
                                 
                            	 $('.select_grupo').show();
                                 $('.select_empresa').show();
                                 $('.select_direccion').show();
                                
                                 
                                 $('.select_ubicacion').show();
                                 $('.select_tubi').show();
                                 $('.select_funcionalidad').show();
                                 
 								 $('#grabar_web').show();
 	                             $('#grabar_pdf').show();
 	                             $('#grabar_excel').show();
 							}
 							if(inf_sel==8){
 								 $('#grabar_web').show();
 	                             $('#grabar_pdf').show();
 	                             $('#grabar_excel').show();
 							}
 							
 							if(inf_sel==9){
 								sacaFiltros();
 								$('.select_grupo').show();
 	                               
                                $('.select_empresa').show();
                                $('.select_periodo').show();
                                $('#grabar_web').show();
                               
                               	 
                              }
 							if(inf_sel==10){
 								sacaFiltros();
 								$('.select_grupo').show();
 	                               
                                $('.select_empresa').show();
                                $('.select_year').show();
                                $('.select_month').show();
                                
                                $('#grabar_web').show();
                                
                                
                               	 
                              }
                            if(inf_sel==11){
 								sacaFiltros();
 							    
                                $('.select_grupo').show();
                                $('.select_empresa').show();
                                
                                $('.select_direccion').show();
                                //$('.select_ubicacion').show();
                                //$('.select_tubi').show();
                                $('.select_funcionalidad').show();
                           	 
                                $('.selprod').show();

                                $('.selusadonousado').show();
                                
                               
                                $('#grabar_excel').show();
                               
                                 
                              }
                            if(inf_sel==12){
 								sacaFiltros();
 								
                                
                                location='rpt12';
                               	 
                              }
                             
            });
            $('#informe').change();
                            
            $('#prod_filter').on('keyup blur', function() {
                        var textoIn= $(this).val();
                                 //alert(textoIn);
                        $('#select_prod').empty();
                                                    
                        $('#select_prod').append($('<option>', {
                                                           value: '',
                                                           text: 'TODOS'
                                                           }));
                                 
                         for(var ii=0; ii< productos_func_ar.length;ii++){
                                 var producto_ar =productos_func_ar[ii].split(";;");
                                 if(producto_ar[1].indexOf(textoIn.toUpperCase())!=-1){
                                     $('#select_prod').append($('<option>', { value: producto_ar[0], text: producto_ar[1] }));
                                 }
                        }
                                                    
            });
            $('.loading').hide();   
            $('.noloading').show();
                            

        });
        
        function getGruposSelect(){
        	 
	        $.post( "getGruposSelect", {  })
	        .done(function( data ) {
	        	  $( "#select_grupo" ).html( (data) );
	        	  $('#select_grupo').select2();
	          	
	        });
        }
        
        function getEmpresasSelect(id_grupo){
       	 
	        $.post( "getEmpresasSelect", {id_grupo:id_grupo  })
	        .done(function( data ) {
	        	  $( "#select_empresa" ).html( (data) );
	        	  $('#select_empresa').select2();
	        	  $('#select_empresa').select2("val","");
	          	
	        });
        }
        
        function getPeriodosSelect(id_cliente){
          	 
	        $.post( "getPeriodosSelect", {id_cliente:id_cliente  })
	        .done(function( data ) {
	        	  $( "#select_periodo" ).html( (data) );
	        	  $('#select_periodo').select2();
	        	  $('#select_periodo').select2("val","");
	          	
	        });
        }
        
        function getDireccionesSelect(id_cliente){
         	 
	        $.post( "getDireccionesSelect", {id_cliente:id_cliente  })
	        .done(function( data ) {
	        	  $( "#select_direccion" ).html( (data) );
	        	  $('#select_direccion').select2();
	        	  $('#select_direccion').select2("val","");
	          	
	        });
        }
        
        function sacaFiltros(){
        	
        	 $('.select_grupo').hide();
            $('.select_empresa').hide();
            $('.select_direccion').hide();
           
            
            $('.select_ubicacion').hide();
            $('.select_tubi').hide();
            $('.select_funcionalidad').hide();
            
            $('.fecs').hide();
            $('.selprod').hide();
            $('.selprod').hide();
            $('.selusadonousado').hide();
            $('.select_periodo').hide();
            $('.select_year').hide();
            $('.select_month').hide();
            
            $('#grabar_web').hide();
            $('#grabar_pdf').hide();
            $('#grabar_excel').hide();
            
            
     	
     	}
        
        function validarIngreso(){
    		
        	var validate= true;
        	var msg="ERRORES: \n";
        	
        	//si el reporte es el 9 entonces validamos.
        	
        	var infselect= $('#informe').find('option:selected').val();
        	if(infselect==9){
        		var perselect= $('#select_periodo').find('option:selected').val();
        		if(perselect==""){
        			msg+="DEBE INGRESAR UN PERIODO\n";
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
		function validarIngresoWEB(){
    		
        	var validate= true;
        	var msg="ERRORES: \n";
        	
        	//si el reporte es el 9 entonces validamos.
        	
        	var infselect= $('#informe').find('option:selected').val();
        	if(infselect==9){
        		$('#form1').attr("action","rpt9_2");
        		
        		var perselect= $('#select_periodo').find('option:selected').val();
        		if(perselect==""){
        			msg+="DEBE INGRESAR UN PERIODO\n";
            		validate=false;
        		}
        		
        	}
        	else if(infselect==10){
        		$('#form1').attr("action","rpt10");
        		
        		
        		
        	}
        	else if(infselect==12){
        		$('#form1').attr("action","rpt12");
        		
        		
        		
        	}
        	else{
        		$('#form1').attr("action","");
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
  
  
    <div class="navbar">
                        
                     
      <div class="brand"><img src="lib/bootstrap/img/logonew_big.png"></div>
                     <div class="version" align="right">
                     <p>N996.R.01.001</p>
                     </div>
                     <form method="get" action="" style="margin:0px 0px 0px 0px">                 
                    <button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
                    </form>
                    <button  type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onClick="location='/996/'">VOLVER</button>
                   
                    <div align="center" class="title" >
                    <p >REPORTES</p>
                    </div>
                   
                    
                    
               
                             
    </div>
              <form action="" name="form1" method="post" target="_blank" id="form1">
  <div class="content" >
                   <div class=" cuadroblanco noloading" style="display: none" >
     
            <div class="divhead"><strong >INFORME:</strong><select name="informe" id="informe" class="informe">
            <% if(p36.equals("1")){%> <option value="1" selected="selected" >1.- GRUPO/EMPRESA/DIRECCION/UBICACION/FUNCIONALIDAD</option> <%} %>
 			<% if(p60.equals("1")){%> <option value="2" >2.- MOVIMIENTOS TRASLADO ACTIVOS POR FUNCIONALIDAD</option><%} %>
			<% if(p61.equals("1")){%> <option value="3" >3.- GRUPO/EMPRESA/DIRECCION/UBICACION/FUNCIONALIDAD/PRODUCTO</option><%} %>
			<% if(p87.equals("1")){%> <option value="4" >4.- MOVIMIENTOS TRASLADO ACTIVOS POR FUNCIONALIDAD/PRODUCTO</option><%} %>
  			<% if(p185.equals("1")){%><option value="5" >5.- GRUPO/EMPRESA/DIRECCION/UBICACION/PART NUMBER</option><%} %>
  			<% if(p186.equals("1")){%><option value="6" >6.- REPORTE ACTIVOS USADOS / NUEVOS</option><%} %>
 			<% if(p191.equals("1")){%><option value="7" >7.- GRUPO/EMPRESA/DIRECCION/UBICACION/FUNCIONALIDAD PARA USUARIO</option><%} %>
			<% if(p192.equals("1")){%><option value="8" >8.- MOVIMIENTOS TRASLADO ACTIVOS POR FUNCIONALIDAD PARA USUARIO</option><%} %>
 			<% if(p229.equals("1")){%><option value="9" >9.- RESUMEN TOTAL EMPRESA POR PERIODO</option><%} %>
 			<% if(p242.equals("1")){%><option value="10" >10.- RESUMEN TOTAL EMPRESA POR MES</option><%} %>
            <% if(p243.equals("1")){%><option value="11" >11.- RESUMEN DE PRODUCTOS POR EMPRESA </option><%} %>
            <% if(p247.equals("1")){%><option value="12" >12.- RESUMEN DE PRODUCTOS POR EMPRESA EJECUTIVO </option><%} %>
 
  
      	 		    </select>
                    </div>
       <div class="divhead select_grupo"><strong >GRUPO:</strong><select name="select_grupo" id="select_grupo" style="width: 280px"></select></div>
                    
        <div class="divhead select_empresa"><strong >EMPRESA:</strong><select name="select_empresa" id="select_empresa" style="width: 380px"></select></div>
     
         <div class="divhead select_direccion"><strong >DIRECCION:</strong><select name="select_direccion" id="select_direccion" style="width: 780px;">
 
                    <option value="" selected="selected">TODOS</option>
  
      	 		    </select>
                    </div>
                     <div class="divhead select_ubicacion"><strong >UBI:</strong><select name="select_ubicacion" id="select_ubicacion" style="width: 370px;">
   <option value="" selected="selected">TODOS</option>
  
      	 		    </select>
                    </div>
      
               <div class="divhead select_tubi"><strong >TIPO UBI:</strong><select name="select_tubi" id="select_tubi" style="width: 115px;">
		   <option value="" selected="selected">TODOS</option>
		  
	 </select>
                    </div>
      
       <div class="divhead select_funcionalidad"><strong >FUNC:</strong><select name="select_funcionalidad" id="select_funcionalidad" style="width: 280px;">
           <option value="" selected="selected">TODOS</option>
		   <% for(int i =0; i<ar_funcionalidades.length; i++){
               String[] funcionalidad= ar_funcionalidades[i].split("/");
               
           %>
           
           <option value="<%=funcionalidad[0]%>"><%=funcionalidad[1] %></option>
           <% } %>
      	 		    </select>
                    </div>
       
             <div class="divhead fecs"><strong>Fec Desde:</strong><input maxlength="35" type="text" id="datepicker" name="f1" class="amarillo" style="width:130px;height:30px ;background:#FF3;" ></div>
      
            <div class="divhead fecs"><strong>Fec Hasta:</strong><input maxlength="35" type="text" id="datepicker2" name="f2" class="amarillo" style="width:130px;height:30px ;background:#FF3;" ></div>
      <div class="divhead selusadonousado"><strong>ESTADO PRODUCTO:</strong><select name="select_unou" id="select_unou" style="width: 120px;">
          <option value="" selected="selected">TODOS</option>
          <option value="1" >USADO</option>
          <option value="0" >NUEVO</option>
      
      </select> </div>
      <div class="divhead selprod"><strong>PRODUCTO:</strong><select name="select_prod" id="select_prod" style="width: 280px;">
          <option value="" selected="selected">TODOS</option>
          <% for(int i =0; i<ar_productos.length; i++){
              String[] prod = ar_productos[i].split(";;");
              
          %>
          
          <option value="<%=prod[0]%>"><%=prod[1] %></option>
          <% } %>
      </select> <strong>FILTRO PROD:</strong><input maxlength="35" type="text" id="prod_filter"  class="amarillo" style="width:280px;height:30px ;background:#FF3;" ></div>
      
      <div class="divhead select_periodo"><strong >PERIODO:</strong><select name="select_periodo" id="select_periodo" style="width: 300px">
			   <option value="" selected="selected">TODOS</option>
			 	 		    </select>
       </div>
       
       <div class="divhead select_year"><strong >AÑO:</strong><select name="select_year" id="select_year" style="width: 280px;">
            
           <option value="2014" >2014</option>
           <option value="2015" >2015</option>
           <option value="2016" >2016</option>
           <option value="2017" >2017</option>
           <option value="2018" >2018</option>
           <option value="2019" >2019</option>
           <option value="2020" >2020</option>
		   
		   
      	 		    </select>
        </div>
        <div class="divhead select_month"><strong >MES:</strong><select name="select_month" id="select_month" style="width: 280px;">
           <option value="01" >ENERO</option>
           <option value="02" >FEBRERO</option>
           <option value="03"  >MARZO</option>
           <option value="04" >ABRIL</option>
           <option value="05" >MAYO</option>
           <option value="06" >JUNIO</option>
           <option value="07" >JULIO</option>
           <option value="08" >AGOSTO</option>
           <option value="09" >SEPTIEMBRE</option>
           <option value="10" >OCTUBRE</option>
           <option value="11"  >NOVIEMBRE</option>
           <option value="12"  >DICIEMBRE</option>
		   
      	 		    </select>
        </div>
      
      
        <div class="bgrabar">
            <button type="submit" onclick="return validarIngresoWEB()" name="grabar_web" id="grabar_web" class="btn btn-success " >GENERAR WEB</button>
            <button type="submit" onclick="return validarIngreso()" name="grabar_pdf" id="grabar_pdf" class="btn btn-success " >GENERAR PDF</button>
           <button type="submit" onclick="return validarIngreso()" name="grabar_excel" id="grabar_excel" class="btn btn-success " >GENERAR EXCEL</button>
          </div> 
          </div>
          
          <div class=" cuadroblanco loading" >CARGANDO...</div>
         </div>        
    
           
            
     </form>
    
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
