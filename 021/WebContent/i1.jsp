<%@page import="java.util.ArrayList"%>
<%
String modname=(String)request.getAttribute("modname");

String Usu_nom=(String)request.getAttribute("usuname");

String marca_nombre="";
String estados_vig_novig_id="";
if(request.getAttribute("marca_nombre")!=null && !request.getAttribute("marca_nombre").equals("")) marca_nombre =(String) request.getAttribute("marca_nombre");
if(request.getAttribute("estados_vig_novig_id")!=null && !request.getAttribute("estados_vig_novig_id").equals(""))estados_vig_novig_id=(String) request.getAttribute("estados_vig_novig_id");

	String[] ar_estados =(String[]) request.getAttribute("ar_estados");
	

	String correlativo=(String)request.getAttribute("correlativo");
%>
<!DOCTYPE html>
<html lang="en"><head>

<!-- VALIDANDO QUE LOS CAMPOS VALLAN CON ALGO -->
	<script type="text/javascript">
	function validarIngreso(){
		if(document.getElementById('marca_nombre').value==""){
			alert('DEBE INGRESAR UN NOMBRE DE MARCA');
			document.getElementById('marca_nombre').focus();
			return false;
		}
		
		}
	</script>
	
	<!-- FIN VALIDANDO QUE LOS CAMPOS VALLAN CON ALGO -->
 <% 
  if(request.getParameter("Exito")!=null && !request.getParameter("Exito").equals("")){
  		if(request.getParameter("Exito").equals("NOK")){
			out.println("<script>alert('EL NOMBRE DE GRUPO YA EXISTE')</script>");
			//out.println("<script>window.location = '/001/'</script>");
		} 
		
	}
   
	%>
    <meta charset="utf-8">
    <title>New Office</title>
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
	  form{
	    	margin-top:10px;
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
			min-height:100%; 
			height:175px;  
			min-width: 350px;
			max-width:500px;
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
                     <p>N021.I.01.001</p>
                     </div>
                     <form method="get" action="" style="margin:0px 0px 0px 0px">                 
                    <button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESI�N</button>
                    </form>
                    <button  type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/021/'">VOLVER</button>
                   
                    <div align="center" class="title" >
                    <p >INGRESAR <%=modname %></p>
                    </div>
                   
                    
                    
               
                             
    </div>
         
  <div class="content" >
                   <div class=" cuadroblanco" >
         
         <form action="" name="form1" method="post">
           	<table class="table-movil" border="0" align="center">
  <tr>
    <td >
    	<div class="divhead"><strong >CORRELATIVO:</strong><input type="text" value="<%=correlativo %>" style="width:50px;height:30px; background:#FFF;color: #000;" disabled="disabled"></div>
    </td>
  </tr>
  <tr>
    <td><div class="divhead"><strong>NOMBRE:</strong><input maxlength="30" autofocus="autofocus" type="text" class="amarillo" style="width:350px;height:30px ;background:#FF3;border: 1px solid #FFFF00;" name="marca_nombre" id="marca_nombre" value="<%=marca_nombre %>" required >
                    <span class="cabecera" style="color:#F00">*</span></div> 
 	</td>
  </tr>
  <tr>
    <td><div class="divhead"><strong>ESTADO:</strong><select name="estados_vig_novig_id" id="estados_vig_novig_id" style="margin:0px 0px 0px 0px;width: 167px">
    <% for(int i =0; i<ar_estados.length; i++){
       		String[] estados_vig_novig = ar_estados[i].split("/");
    
    %>
    
      <option value="<%=estados_vig_novig[0]%>"<% if(estados_vig_novig_id.equals(estados_vig_novig[0]))%><%="selected"%>><%=estados_vig_novig[1] %></option>
    <% } %>
    </select><span class="cabecera" style="color:#F00"> *</span></div></td>
    </tr>
</table>
            
            <div class="bgrabar">
           <button type="submit" onclick="return validarIngreso()" name="grabar" id="grabar" class="btn btn-success" >GRABAR</button>
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
