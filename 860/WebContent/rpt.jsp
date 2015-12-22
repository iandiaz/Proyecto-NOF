<%@page import="java.util.ArrayList"%>
<%
String Usu_nom=(String)request.getAttribute("usuname").toString();
String[] ar_grupos =(String[]) request.getAttribute("ar_grupos");
String[] ar_empresas =(String[]) request.getAttribute("ar_empresas");

String[] ar_direcciones =(String[]) request.getAttribute("ar_direcciones");
String[] ar_ubi =(String[]) request.getAttribute("ar_ubi");
String[] ar_tubi =(String[]) request.getAttribute("ar_tubi");
String[] ar_funcionalidades =(String[]) request.getAttribute("ar_funcionalidades");
String[] ar_productos =(String[]) request.getAttribute("ar_productos");
%>
<!DOCTYPE html>
<html lang="en">
<head>
 
    <meta charset="utf-8">
    <title>New Office - REPORTES</title>
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

        //////////////////////////////////////////////////////////////////
        ///////definimos array con ubicaciones////////////////////////////
        
    
    
    var productos_ar= new Array();
    var productos_func_ar= new Array();
    <%  for(int i =0; i<ar_productos.length; i++){
        %>
        productos_ar[<%=i%>]="<%=ar_productos[i].replace("\"","")%>";
        
        <% } %>

        
        $( document ).ready(function() {
        	
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
                       var ubi_ar =productos_ar[ii].split("/");
                       if(ubi_ar[2]==fun_seleccionada){
                         $('#select_prod').append($('<option>', {
                                                     value: ubi_ar[0],
                                                     text: ubi_ar[1]
                                                     }));
                         	productos_func_ar.push(ubi_ar[0]+"/"+ubi_ar[1]+"/"+ubi_ar[2]);
                      	 }
                     
                     }
                 }else{
                 	 for(var ii=0; ii< productos_ar.length;ii++){
                          var ubi_ar =productos_ar[ii].split("/");
                            $('#select_prod').append($('<option>', {
                                                        value: ubi_ar[0],
                                                        text: ubi_ar[1]
                                                        }));
                            	productos_func_ar.push(ubi_ar[0]+"/"+ubi_ar[1]+"/"+ubi_ar[2]);
                         	 
                        
                        }
                 	
                 }
                     
});
             $('#select_funcionalidad').change();
        	
        	
        	
        	
                            
        	$('#prod_filter').on('keyup blur', function() {
                var textoIn= $(this).val();
                         //alert(textoIn);
                $('#select_prod').empty();
                                            //recorremos array de direcciones para buscar las que correspondan a la empresa
                $('#select_prod').append($('<option>', {
                                                   value: '',
                                                   text: 'TODOS'
                                                   }));
                         
                 for(var ii=0; ii< productos_func_ar.length;ii++){
                         var producto_ar =productos_func_ar[ii].split("/");
                         if(producto_ar[1].indexOf(textoIn.toUpperCase())!=-1){
                             $('#select_prod').append($('<option>', { value: producto_ar[0], text: producto_ar[1] }));
                         }
                }
                                            
    });
                            
            
                            

        });
        
        
    </script>
  
  
    <div class="navbar">
                        
                     
      <div class="brand"><img src="lib/bootstrap/img/logonew_big.png"></div>
                     <div class="version" align="right">
                     <p>N860.R.01.001</p>
                     </div>
                     <form method="get" action="Igrupo" style="margin:0px 0px 0px 0px">                 
                    <button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
                    </form>
                    <button  type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onClick="location='/860/'">VOLVER</button>
                   
                    <div align="center" class="title" >
                    <p >REPORTES PRECIO GUIA TRASLADO</p>
                    </div>
                   
                    
                    
               
                             
    </div>
              <form action="" name="form1" method="post" target="_blank">
  <div class="content" >
                   <div class=" cuadroblanco" >
     
            <div class="divhead"><strong >INFORME:</strong><select name="informe" id="informe" class="informe">
  <option value="1" selected="selected" >1.- PRECIO GUIA TRASLADO</option>
  <option value="2" >2.- PRODUCTOS SIN PRECIO GUIA TRASLADO</option>
 
  
      	 		    </select>
                    </div>
                    
               <div class="divhead"><strong >FUNC:</strong><select name="select_funcionalidad" id="select_funcionalidad" style="width: 280px;">
           <option value="" selected="selected">TODOS</option>
		   <% for(int i =0; i<ar_funcionalidades.length; i++){
               String[] funcionalidad= ar_funcionalidades[i].split("/");
               
           %>
           
           <option value="<%=funcionalidad[0]%>"><%=funcionalidad[1] %></option>
           <% } %>
      	 		    </select>
                    </div>
       
      <div class="divhead selprod"><strong>PRODUCTO:</strong><select name="select_prod" id="select_prod" style="width: 280px;">
          <option value="" selected="selected">TODOS</option>
          <% for(int i =0; i<ar_productos.length; i++){
              String[] prod = ar_productos[i].split("/");
              
          %>
          
          <option value="<%=prod[0]%>"><%=prod[1] %></option>
          <% } %>
      </select> <strong>FILTRO PROD:</strong><input maxlength="35" type="text" id="prod_filter"  class="amarillo" style="width:280px;height:30px ;background:#FF3;" ></div>
      
      
        <div class="bgrabar">
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
