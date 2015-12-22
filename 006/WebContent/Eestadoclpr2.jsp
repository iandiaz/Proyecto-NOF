<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String Estado_Clpr_id=(String)request.getAttribute("Estado_Clpr_id");
String Estado_Clpr_nombre=(String)request.getAttribute("Estado_Clpr_nombre");
String estados_vig_novig_nombre=(String)request.getAttribute("estados_vig_novig_nombre");
	String Usu_nom=(String)request.getAttribute("usuname");
	
	String user_crea=(String)request.getAttribute("user_crea"); 
	String user_mod=(String)request.getAttribute("user_mod"); 
	String fec_crea=(String)request.getAttribute("fec_crea"); 
	String fec_mod=(String)request.getAttribute("fec_mod");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>New Office - Estado cliente proveedor</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
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
			margin-top:10px;
			font-size:20px;
			}
		select{
			width:225px;
				}
		
	@media screen and (min-width: 700px) {
		.cuadroblanco{
			box-shadow:0px 2px 5px 0px rgba(50, 50, 50, 0.75);
			border: 1px solid black;
			min-height:100%; height:345px;  
		min-width: 350px;
			max-width:650px;
position: relative;
background:#ccc;
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
                     <p>N006.E.02.001</p>
                     </div>
                    <form method="get" action="Cestadoclpr2" style="margin:0px 0px 0px 0px">                 
                    <button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESI�N</button>
                    </form>
                    <button type="button" onclick="location='Eestadoclpr'" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">VOLVER</button>
                   
                    <div align="center" class="title" >
                    <p >ELIMINAR ESTADO CLIENTE-PROVEEDOR</p>
                    </div>
                    
               
                             
    </div>
         <div align="center" class="form-group has-warning">
         <form action="Mgrupo" method="get" name="form2">
   <!--<label class="control-label" for="inputWarning" style="font-size:20px;color:#000"><strong>TEXTO PARA FILTRAR</strong></label>  <input type="text" class="form-control span12" style="width:500px;height:30px" autofocus="autofocus" name="filtro_txt"> 
   <input name="buscar" id="buscar" type="submit" value="BUSCAR" class="btn btn-primary" style="margin-bottom: 10px" > -->
         </form>
                    </div>

  <div class="content" >
        
           <div class=" cuadroblanco" >
         
       <table class="table-movil" border="0" align="center">
<tr>
    <td><div class="divhead"><strong >CORRELATIVO:</strong><input type="text" value="<%=Estado_Clpr_id %>" style="width:50px;height:30px; background:#FFF;" disabled="disabled">
                    </div></td>
    </tr>
<tr>
	<td><div class="divhead"><strong>NOMBRE:</strong><input type="text" value="<%=Estado_Clpr_nombre%>" style="width:348px;height:30px; background:#FFF;" disabled="disabled" >
					</div></td>
					
</tr>
<tr>
	<td><div class="divhead"><strong>ESTADO:</strong><input type="text" value="<%=estados_vig_novig_nombre%>" style="width:144px;height:30px; background:#FFF; " disabled="disabled">
				</div>	</td>
					
</tr> 
<tr>
		<td><div class="divhead"><strong>Fecha creaci�n:</strong><input type="text" value="<%=fec_crea%>" style="width:225px;height:30px; background:#FFF;color: #000 " disabled="disabled">
						</div></td>
	</tr>
					
	<tr>
		<td><div class="divhead"><strong>Usuario creador:</strong><input type="text" value="<%=user_crea%>" style="width:400px;height:30px; background:#FFF;color: #000 " disabled="disabled">
					</div></td>
	</tr>
					
	<tr>
		<td><div class="divhead"><strong>Fecha �ltima mod:</strong><input type="text" value="<%=fec_mod%>" style="width:225px;height:30px; background:#FFF;color: #000 " disabled="disabled">
					</div></td>
	</tr>
	<tr>			
	<td><div class="divhead"><strong>Usuario �ltima mod:</strong><input type="text" value="<%=user_mod%>" style="width:400px;height:30px; background:#FFF;color: #000 " disabled="disabled">
					</div></td>
	</tr>
	
</table>

		<div class="bgrabar">
           <button onclick="location='Eestadoclpr2?delete_id=<%=Estado_Clpr_id%>';" type="button" name="grabar" id="grabar" class="btn btn-success " >GRABAR</button>
   	    </div> 
       </div> 
  <!-- Table -->

         
         
         </div>
       
        
    
    <div class="footerapp">
                        
                  <p><i class="icon-user"></i>
                  <strong><%=Usu_nom %></strong></p>
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