<%@page import="Constantes.Constantes"%>
<%@page import="java.util.ArrayList"%>
<%
String Usu_nom=(String)request.getAttribute("usuname").toString();
String modname=(String)request.getAttribute("modname").toString();

String p1=(String)request.getAttribute("p1");
String p2=(String)request.getAttribute("p2");
String p3=(String)request.getAttribute("p3");

%>
<!DOCTYPE html>
<html lang="en">
<head>
 
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
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">
       <link rel="stylesheet" type="text/css" href="stylesheets/inputs.css">

    
     	<script src="lib/jquery-2.1.4.min.js"></script>
    

	<link href="lib/select2/css/select2.min.css" rel="stylesheet" />
	<script src="lib/select2/js/select2.min.js"></script>
    
    
    <!-- Demo page code -->

   <style type="text/css">
   .bgrabar1{
	    	bottom: 0;
			position: absolute;
			right: 0;
			margin-right: 5px;
			margin-bottom: 5px;
	    }
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
			height:290px;
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
        	
        	getFuncionalidadesSelect();
        	getGruposSelect();
        	getEmpresasSelect(null);
        	getConfiguracionesSelect();
        	
                            
        });
        
        $( document ).ready(function() {
            
        	
        /**********************************
         ******CADENA FUNCIONALIDAD 
         ***********************************/
        
        
        $('#select_funcionalidad').on('change', function() {
	    	  
       		var func_seleccionado = $(this).val();
       		if(func_seleccionado!="") getProductosSelect(func_seleccionado);
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
                
                $('.select_funcionalidad').show();
                $('.selprod').show();
                $('.selconf').show();
                  
            }
            if(inf_sel==2){
	           	 sacaFiltros();
	                
	           	 $('.select_funcionalidad').show();
	             $('.selprod').show();
	             $('.selconf').show();
            }
            if(inf_sel==3){
           	 
				sacaFiltros();
                
                $('.select_grupo').show();
                $('.select_empresa').show();
                $('.select_direccion').show();
                $('.select_funcionalidad').show();
                $('.selprod').show();
           	
            }
           
			            
		});
		$('#informe').change();
              
                 
        });
        
        function getProductosSelect(func_id){
        	
        	$.post( "getProductosSelect", {func_id: func_id })
    	        .done(function( data ) {
    	        	  $( "#select_prod" ).html( (data) );
    	        	  $('#select_prod').select2();
    	        	  $('#select_prod').select2("val","");
    	        });
        }
        function getFuncionalidadesSelect(){
        	
        	 $.post( "getFuncionalidadesSelect", {})
     	        .done(function( data ) {
     	        	  $( "#select_funcionalidad" ).html( (data) );
     	        	 $('#select_funcionalidad').select2();
     	        	 $('#select_funcionalidad').select2("val","");
     	    
     	        });
      	}  
        
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
        function getDireccionesSelect(id_cliente){
        	 
	        $.post( "getDireccionesSelect", {id_cliente:id_cliente  })
	        .done(function( data ) {
	        	  $( "#select_direccion" ).html( (data) );
	        	  $('#select_direccion').select2();
	        	  $('#select_direccion').select2("val","");
	          	
	        });
        }
        
        function getConfiguracionesSelect(){
   		 
   		  $('#select_conf').select2();
   		  
   	    	$.post( "getConfiguracionesAsync", { })
   	        .done(function( data ) {
   	        	
   	        		var conf_arr = $.parseJSON(data);
   	          		
   	          		$.each( conf_arr, function( key, conf ) {
   	          			
   	          		  $('#select_conf').append($('<option>', {
                             value: conf.id,
                             text: conf.nombre
                             }));
                        
   	          			});
   	          		
   	          		 $('#select_conf').select2("val","");
   	          		
   	        });
   	    }
        
        function sacaFiltros(){
        	
       	 $('.select_grupo').hide();
         $('.select_empresa').hide();
         $('.select_direccion').hide();
         $('.select_funcionalidad').hide();
         $('.selprod').hide();
         $('.selconf').hide();
         
         
           
    	
    	}
        
    </script>
  
  
    <div class="navbar">
                        
                     
      <div class="brand"><img src="lib/bootstrap/img/logonew_big.png"></div>
                     <div class="version" align="right">
                     <p>N<%=Constantes.MODULO %>.R.01.001</p>
                     </div>
                     <form method="get" action="" style="margin:0px 0px 0px 0px">                 
                    	<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
                    	</form>
                    <button  type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onClick="location='/<%=Constantes.MODULO %>/'">VOLVER</button>
                   
                    <div align="center" class="title" >
                    <p >REPORTES <%=modname %></p>
                    </div>
                         
    </div>
    <form action="" name="form1" method="post" target="_blank">
  		<div class="content" >
           
		<div class=" cuadroblanco" >
     
            <div class="divhead"><strong >INFORME:</strong><select name="informe" id="informe" class="informe">
            
           	  	<% if(p1.equals("1")){%><option value="1" selected="selected" >1.- CONTENIDO CONFIGURACION</option><%} %>
           	  	<% if(p2.equals("1")){%><option value="2"  >2.- CONFIGURACIONES POR PRODUCTO</option><%} %>
           	  	<% if(p3.equals("1")){%><option value="3"  >3.- COMPARACION UBICACIONES / CONFIGURACIONES</option><%} %>
				 
      	 		</select>
                    </div>
                    <div class="divhead select_grupo"><strong >GRUPO:</strong><select name="select_grupo" id="select_grupo" style="width: 280px"></select></div>
                    
        <div class="divhead select_empresa"><strong >EMPRESA:</strong><select name="select_empresa" id="select_empresa" style="width: 380px"></select></div>
     
     <div class="divhead select_direccion"><strong >DIRECCION:</strong><select name="select_direccion" id="select_direccion" style="width: 780px;"></select>
                    </div>
 
                  <div class="divhead select_funcionalidad"><strong >FUNC:</strong><select name="select_funcionalidad" id="select_funcionalidad" style="width: 280px;">
           <option value="" selected="selected">TODOS</option>
		   
      	 		    </select>
                    </div>
                    
                    <div class="divhead selprod"><strong>PRODUCTO:</strong><select name="select_prod" id="select_prod" style="width: 480px;">
          <option value="" selected="selected">TODOS</option>
         
      </select> </div>  
      
      <div class="divhead selconf"><strong>CONFIGURACION:</strong><select name="select_conf" id="select_conf" style="width: 480px;">
          <option value="" selected="selected">TODOS</option>
         
      </select> </div>  
      
      
      
        <div class="bgrabar1">
            <button type="submit" onclick="return validarIngreso()" name="grabar_web" id="grabar" class="btn btn-success " >GENERAR WEB</button>
            <button type="submit" onclick="return validarIngreso()" name="grabar_pdf" id="grabar" class="btn btn-success " >GENERAR PDF</button>
           <button type="submit" onclick="return validarIngreso()" name="grabar_excel" id="grabar" class="btn btn-success " >GENERAR EXCEL</button>
          </div> 
          </div>
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
