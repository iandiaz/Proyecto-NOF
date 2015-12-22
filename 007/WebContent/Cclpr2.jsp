<%@page import="java.util.ArrayList"%>
<%
	String empresas_nombre =(String) request.getAttribute("empresas_nombre");
	String estados_vig_novig_nombre=(String) request.getAttribute("estados_vig_novig_nombre");
	String Usu_nom=(String)request.getAttribute("usuname");
	String empresas_id=(String)request.getAttribute("empresas_id");
	String grupos_nombre=(String)request.getAttribute("grupos_nombre");
	String Estado_Clpr_nombre=(String)request.getAttribute("Estado_Clpr_nombre");
	String empresas_rut=(String)request.getAttribute("empresas_rut");
	String empresas_escliente=(String)request.getAttribute("empresas_escliente");
	String empresas_esproveedor=(String)request.getAttribute("empresas_esproveedor");
	String empresas_razonsocial=(String)request.getAttribute("empresas_razonsocial");
	String empresas_nombrenof=(String)request.getAttribute("empresas_nombrenof");
	String empresas_giro=(String)request.getAttribute("empresas_giro");
	String empresas_web=(String)request.getAttribute("empresas_web");
	String empresas_espro=(String)request.getAttribute("empresas_espro");
	String user_crea=(String)request.getAttribute("user_crea"); 
	String user_mod=(String)request.getAttribute("user_mod"); 
	String fec_crea=(String)request.getAttribute("fec_crea"); 
	String fec_mod=(String)request.getAttribute("fec_mod");
	String empresas_email=(String)request.getAttribute("empresas_email");
	
	String empresas_relacionada=(String)request.getAttribute("empresas_relacionada");
	String responsable_nombre=(String)request.getAttribute("responsable_nombre");
	

%>
<!DOCTYPE html>
<html lang="en"><head>
    <meta charset="utf-8">
    <title>New Office - Cliente proveedor</title>
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

    <script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>

    <!-- Demo page code -->

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
		select{
			width:225px;
				}
		
	@media screen and (min-width: 700px) {
.cuadroblanco{
			box-shadow:0px 2px 5px 0px rgba(50, 50, 50, 0.75);
			border: 1px solid black;
			min-height:100%; height:460px;  
		min-width: 350px;
			max-width:1000px;
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
    
    <div class="navbar">
                        
                     
      <div class="brand"><img src="lib/bootstrap/img/logonew_big.png"></div>
                     <div class="version" align="right">
                     <p>N007.C.02.001</p>
                     </div>
                     <form method="get" action="Cclpr2" style="margin:0px 0px 0px 0px">                 
                    <button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
                    </form>
                    <button  type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onClick="location='C01_001'">VOLVER</button>
                   
                    <div align="center" class="title">
                    <p >CONSULTAR CLIENTE-PROVEEDOR</p>
                    </div>
                   
                    
                    
               
                             
    </div>
         
   <div class="content" >
        
           <div class=" cuadroblanco" >
         
         
         <form action="Eclpr2" name="form1" method="post">
         <input type="hidden" name="empresas_id" value="<%=request.getParameter("empresas_id")%>">
          <div class="divhead"><strong >ID:</strong><input type="text" value="<%=empresas_id %>" style="width:50px;height:30px; background:#FFF;" disabled="disabled">
                    </div>
  <div class="divhead"><strong>NOM FANTASIA:</strong>
  <input maxlength="55" autofocus="autofocus" type="text" class="amarillo" style="width:620px;height:30px ;background:#FFF;" value="<%=empresas_nombre%>" disabled="disabled" >
  </div>
  <div class="divhead"><strong>RAZÓN SOCIAL:</strong>
  <input maxlength="55" type="text" class="amarillo" style="width:620px;height:30px ;background:#FFF;" value="<%=empresas_razonsocial %>" disabled="disabled" >
  </div>
  <div class="divhead"><strong>NOM NOF:</strong>
  <input maxlength="35" type="text" class="amarillo" style="width:400px;height:30px ;background:#FFF;" value="<%=empresas_nombrenof %>" disabled="disabled" >
  </div>        
 <div class="divhead"><strong>RUT:</strong>
    <input type="text" class="amarillo rut" style="width:145px;height:30px ;background:#FFF;" maxlength="16" value="<%=empresas_rut %>" disabled="disabled">
    </div>
   
    <div class="divhead"><strong>GIRO:</strong>
    <input maxlength="55" type="text" class="amarillo" style="width:620px;height:30px ;background:#FFF;" value="<%=empresas_giro %>" disabled="disabled">
    
    </div>
    <div class="divhead"><strong>EMAIL:</strong>
    <input maxlength="55" type="text" class="amarillo" style="width:620px;height:30px ;background:#FFF;" value="<%=empresas_email %>" disabled="disabled">
    
    </div>
    <div class="divhead"><strong>WEB:</strong>
    <input maxlength="40" type="text" class="amarillo" style="width:455px;height:30px ;background:#FFF;" value="<%=empresas_web %>" disabled="disabled" >
    </div>
   
    <div class="divhead">           
     <table>
     	<tr>
     		<td><strong>CLIENTE:</strong>
                   </td><td><input maxlength="5" type="text" style="width:45px;height:30px; background:#FFF;" value="<%=empresas_escliente%>" disabled="disabled" ></td>
     	<td>&nbsp;&nbsp;<strong>PROSPECCION:</strong>
                   </td><td><input maxlength="5" type="text" style="width:45px;height:30px; background:#FFF;" value="<%=empresas_espro %>" disabled="disabled" ></td>
     	</tr>
     	<tr>
     		<td><strong>PROVEEDOR:</strong>
                   </td><td><input maxlength="5" type="text" style="width:45px;height:30px; background:#FFF;" value="<%=empresas_esproveedor%>" disabled="disabled" ></td>
     		<td></td>
     		<td></td>
     	</tr>
     	
     </table>
     </div>      
   
   <div class="divhead" style="width: 500px;"><strong>GRUPO:</strong>
   <input type="text" value="<%=grupos_nombre%>" style="width:320px;height:30px; background:#FFF; " disabled="disabled">   
  </div>
   <div class="divhead" style="width: 500px;"><strong>EMP RELACIONADA:</strong>
   <input type="text" value="<%=empresas_relacionada%>" style="width:45px;height:30px; background:#FFF; " disabled="disabled">   
  </div>
    
    
     <div class="divhead"><strong>ESTADO CLI-PROV:</strong>
   <input type="text" value="<%=Estado_Clpr_nombre%>" style="width:370px;height:30px; background:#FFF; " disabled="disabled">
	</div>
    
   
    <div class="divhead"><strong>ESTADO:</strong>
   <input type="text" value="<%=estados_vig_novig_nombre%>" style="width:167px;height:30px; background:#FFF; " disabled="disabled">
   </div>
      <div class="divhead"><strong>RESPONSABLE CUENTA:</strong><input type="text" style="width:500px;height:30px ;background:#FFF;" value="<%=responsable_nombre%>" disabled="disabled" >
  </div>
    <div class="divhead"><strong>Fec. creación:</strong><input type="text" value="<%=fec_crea%>" style="width:225px;height:30px; background:#FFF;color: #000 " disabled="disabled">
						</div>
	<div class="divhead"><strong>Usu creador:</strong><input type="text" value="<%=user_crea%>" style="width:400px;height:30px; background:#FFF;color: #000 " disabled="disabled">
					</div>
	<div class="divhead"><strong>Fec. últ mod:</strong><input type="text" value="<%=fec_mod%>" style="width:225px;height:30px; background:#FFF;color: #000 " disabled="disabled">
					</div>
	<div class="divhead"><strong>Usu últ mod:</strong><input type="text" value="<%=user_mod%>" style="width:400px;height:30px; background:#FFF;color: #000 " disabled="disabled">
					</div>
            
            
     </form>
       
         
         </div>        
    </div>
    
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