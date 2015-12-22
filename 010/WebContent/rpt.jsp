<%@page import="java.util.ArrayList"%>
<%
String Usu_nom=(String)request.getAttribute("usuname").toString();
String[] ar_grupos =(String[]) request.getAttribute("ar_grupos");
String[] ar_empresas =(String[]) request.getAttribute("ar_empresas");

String[] ar_direcciones =(String[]) request.getAttribute("ar_direcciones");
%>
<!DOCTYPE html>
<html lang="en">
<head>
 
    <meta charset="utf-8">
    <title>New Office - DIRECCION</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/butons.css">
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">
       <link rel="stylesheet" type="text/css" href="stylesheets/inputs.css">

    <script src="lib/jquery-ui/js/jquery-1.10.2.js"></script>
    <link rel="stylesheet" href="lib/jquery-ui/css/ui-lightness/jquery-ui-1.10.4.custom.css">
    <script src="lib/jquery-ui/js/jquery-ui-1.10.4.custom.js"></script>
    
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
        
      
    
  

        
        ///////////////////////////////////////////////////////////////////
        ////////////////definimos array de direcciones/////////////////////

        var direcciones_ar =new Array();
    
        <%  for(int i =0; i<ar_direcciones.length; i++){
                //String[] direcciones = ar_direcciones[i].split("/");
        %>
                direcciones_ar[<%=i%>]="<%=ar_direcciones[i]%>";
        
        <% } %>
    
    
    
    
      
      
         
        
    </script>
  
  
    <div class="navbar">
                        
                     
      <div class="brand"><img src="lib/bootstrap/img/logonew_big.png"></div>
                     <div class="version" align="right">
                     <p>N010.R.01.001</p>
                     </div>
                     <form method="get" action="Igrupo" style="margin:0px 0px 0px 0px">                 
                    <button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
                    </form>
                    <button  type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onClick="location='/010/'">VOLVER</button>
                   
                    <div align="center" class="title" >
                    <p >REPORTES DIRECCION</p>
                    </div>
                   
                    
                    
               
                             
    </div>
              <form action="" name="form1" method="post" target="_blank">
  <div class="content" >
                   <div class=" cuadroblanco" >
     
            <div class="divhead"><strong >INFORME:</strong><select name="informe" id="informe" class="informe">
				  <option value="1" selected="selected" >1.- REPORTE DIRECCION</option>
				  <option value="2" >2.- REPORTE DIRECCION VIGENTE SIN LATITUD O LONGITUD</option>
				  <option value="3" >3.- REPORTE DIRECCION VIGENTE SIN SLA</option>
				  <option value="4" >4.- REPORTE DIRECCIONES VIGENTES CLIENTES SIN UBICACION</option>
  
      	 		</select>
                    </div>
       <div class="divhead"><strong >GRUPO:</strong><select name="select_grupo" id="select_grupo">
  
  <option value="" selected="selected">TODOS</option>
   <% for(int i =0; i<ar_grupos.length; i++){
       		String[] grupo = ar_grupos[i].split("/");
    
    %>
    
      <option value="<%=grupo[0]%>"><%=grupo[1] %></option>
    <% } %>
      	 		    </select>
                    </div>
                    
        <div class="divhead"><strong >EMPRESA:</strong><select name="select_empresa" id="select_empresa">
			   <option value="" selected="selected">TODOS</option>
			   <% for(int i =0; i<ar_empresas.length; i++){
			       		String[] empresa = ar_empresas[i].split("/");
			    
			    %>
			    
			      <option value="<%=empresa[0]%>"><%=empresa[1] %></option>
			    <% } %>
			 	 		    </select>
       </div>
      
      <div class="divhead"><strong>ESTADO DIRECCION:</strong><select name="select_estado" id="select_estado">
    			<option value="" selected="selected">TODOS</option>
	 		    <option value="1" >VIGENTE</option>
	 		    <option value="2">NO VIGENTE</option>
	 		    </select>
      </div>  
     <div class="divhead"><strong>ESTADO EMPRESA:</strong><select name="select_estado_emp" id="select_estado_emp">
    			<option value="" selected="selected">TODOS</option>
	 		    <option value="1" >VIGENTE</option>
	 		    <option value="2">NO VIGENTE</option>
	 		    </select>
      </div>  
      <div class="divhead"><strong>USO DIRECCION:</strong>DESPACHO<input type="checkbox" name="use_dir_despacho" id="use_dir_despacho" value="1" > FACTURACION<input type="checkbox" name="use_dir_fac" id="use_dir_fac" value="1" > COBRANZA<input type="checkbox" name="use_dir_cobranza" id="use_dir_cobranza" value="1" > CORRESPONDENCIA<input type="checkbox" name="use_dir_corres" id="use_dir_corres" value="1" > OTRO FIN<input type="checkbox" name="use_dir_otrofin" id="use_dir_otrofin" value="1" ></div>
       
      
               
      
             
      
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
