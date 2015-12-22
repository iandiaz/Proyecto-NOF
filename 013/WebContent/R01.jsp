<%@page import="VO.GrupoVO"%>
<%@page import="VO.EmpresaVO"%>
<%@page import="java.util.ArrayList"%>
<%
String Usu_nom=(String)request.getAttribute("usuname").toString();


ArrayList<EmpresaVO> empresas =(ArrayList<EmpresaVO>) request.getAttribute("empresas");
ArrayList<GrupoVO> grupos =(ArrayList<GrupoVO>) request.getAttribute("grupos");



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

     	<script src="lib/jquery-ui/js/jquery-1.10.2.js"></script>

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
		
		
	@media screen and (min-width: 700px) {
		.cuadroblanco{
			box-shadow:0px 2px 5px 0px rgba(50, 50, 50, 0.75);
			border: 1px solid black;
			min-height:100%; 
			height:350px;  
			min-width: 350px;
			max-width:790px;
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
    
    <script>
    
   
    $( document ).ready(function() {
    
    	 $('#select_grupo').select2();
    	 $('#select_empresa').select2();
    //CADENA GRUPO - EMPRESA            
    $('#select_grupo').on('change', function() {
         var grup_seleccionada= $(this).val();
         //alert(emp_seleccionada+" Direccion seleccionada");
       $('#select_empresa').empty();
       //recorremos array de direcciones para buscar las que correspondan a la empresa
       $('#select_empresa').append($('<option>', {
                                                           value: '',
                                                           text: 'TODOS'
                                                           }));
       if(grup_seleccionada!=''){
           for(var ii=0; ii< empresas_ar.length;ii++){
          
               var emp_ar =empresas_ar[ii].split("/");
               if(emp_ar[2]==grup_seleccionada){
                  $('#select_empresa').append($('<option>', {
                                                   value: emp_ar[0],
                                                   text: emp_ar[1]
                                                   }));
               }
           
           }
      
       }
                                           
   });
    
    
    });
       
    
    </script>

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
    
    <div class="navbar">
                        
                     
      <div class="brand"><img src="lib/bootstrap/img/logonew_big.png"></div>
                     <div class="version" align="right">
                     <p>N013.R.01.001</p>
                     </div>
                     <form method="get" action="Igrupo" style="margin:0px 0px 0px 0px">                 
                    <button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
                    </form>
                    <button  type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onClick="location='/013/'">VOLVER</button>
                   
                    <div align="center" class="title" >
                    <p >REPORTE ANEXO CONTRATO</p>
                    </div>
                   
                    
                    
               
                             
    </div>
         
  <div class="content" >
                   <div class=" cuadroblanco" >
         
          <form action="" name="form1" method="post" target="_blank">
         <input type="hidden" name="id_g" value="<%=request.getParameter("id_g")%>">
           
           
           
                                <div class="divhead"><strong >INFORME:</strong><select name="informe" id="informe" class="informe">
  <option value="1" selected="selected" >1.- REPORTE UBICACIONES MAQUINA SIN ANEXO CONTRATO</option>
  
 
      	 		    </select>
                    </div>
  
                    <div class="divhead"><strong>GRUPO:</strong><select name="select_grupo" id="select_grupo">
  <option value="" selected="selected">TODOS</option>
      <% for(GrupoVO grupo:grupos){
       		
    
    %>
    
      <option value="<%=grupo.getId()%>"><%=grupo.getNombre() %></option>
    <% } %>
	 		   
	 		    </select>
                  </div>
  <div class="divhead"><strong>EMPRESA:</strong><select name="select_empresa" id="select_empresa">
  <option value="" selected="selected">TODOS</option>
   <% for(EmpresaVO emp:empresas){
       		
    
    %>
    
      <option value="<%=emp.getEmpresas_id()%>"><%=emp.getEmpresas_nombrenof() %></option>
    <% } %>
	 		   
	 		    </select>
                    </div>

         
          
       <div class="bgrabar">
        <button type="submit" name="grabar_web" id="grabar" class="btn btn-success " >GENERAR WEB</button>
            <button type="submit"  name="grabar_pdf" id="grabar" class="btn btn-success " >GENERAR PDF</button>
           <button type="submit"  name="grabar_excel" id="grabar" class="btn btn-success " >GENERAR EXCEL</button>
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
