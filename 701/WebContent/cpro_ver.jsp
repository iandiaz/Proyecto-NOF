<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 		

String peri_tc_id =(String)request.getAttribute("peri_tc_id");
String empresas_id =(String)request.getAttribute("empresas_id");
String empresas_razonsocial =(String)request.getAttribute("empresas_razonsocial");
String peri_tc_fdesde =(String)request.getAttribute("peri_tc_fdesde");
String peri_tc_fhasta =(String)request.getAttribute("peri_tc_fhasta");
String peri_ffac =(String)request.getAttribute("peri_ffac");
String empresas_nombrenof =(String)request.getAttribute("empresas_nombrenof");
String estados_vig_novig_nombre =(String)request.getAttribute("estados_vig_novig_nombre");

String user_crea=(String)request.getAttribute("user_crea"); 
String user_mod=(String)request.getAttribute("user_mod"); 
String fec_crea=(String)request.getAttribute("fec_crea"); 
String fec_mod=(String)request.getAttribute("fec_mod");
String estado_periodo_nombre=(String)request.getAttribute("estado_periodo_nombre");

String Usu_nom=(String)request.getAttribute("usuname");
    %>
<!DOCTYPE html>
<html lang="en"><head>
    <meta charset="utf-8">
    <title>New Office - Consultar Periodos Toma de Contadores</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href='http://fonts.googleapis.com/css?family=Inconsolata:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/butons.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/inputs.css">
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">
	
	<script src="lib/jquery-ui/js/jquery-1.10.2.js"></script>
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
    	display: inline-block;
    	
	}
		select{
			width:225px;
				}
		
	@media screen and (min-width: 700px) {
	.cuadroblanco{
			box-shadow:0px 2px 5px 0px rgba(50, 50, 50, 0.75);
			border: 1px solid black;
			min-height:100%; height:330px;  
			min-width: 350px;
			max-width:850px;
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
	  <p>N701.C.03.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESI�N</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/701/cpro_tomas?empresas_id=<%=empresas_id%>'">VOLVER</button> 
	<div align="center" class="title">
		<p >CONSULTAR PERIODOS TOMA DE CONTADORES Y FACTURACION</p>
	</div>
</div>
<div class="content" >
	<div class=" cuadroblanco" style="height:400px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CLIENTE</p></h3>
		<div class="divhead"><strong>PERIODO:</strong>
			<input type="text" style="width:80px;height:30px ;background:#FFF;" disabled="disabled" value="<%=peri_tc_id%>"></div>
		<div class="divhead"><strong>RAZON SOCIAL:</strong>
			<input type="text" style="width:600px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_razonsocial%>"></div>
		<div class="divhead"><strong>NOMBRE NOF:</strong>	
			<input type="text" style="width:650px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_nombrenof%>"></div>
		
		<div class="divhead"><strong>FECHA DESDE:</strong>	
			<input type="text" style="width:130px;height:30px ;background:#FFF;" disabled="disabled" value="<%=peri_tc_fdesde%>"></div>
		<div class="divhead"><strong>FECHA HASTA:</strong>
			<input type="text" style="width:130px;height:30px ;background:#FFF;" disabled="disabled" value="<%=peri_tc_fhasta%>"></div>
		<div class="divhead"><strong>FECHA FACTURACION:</strong>	
			<input type="text" style="width:130px;height:30px ;background:#FFF;" disabled="disabled" value="<%=peri_ffac%>"></div>
		<div class="divhead"><strong>ESTADO:</strong>
			<input type="text" style="width:144px;height:30px; background:#FFF;" disabled="disabled" value="<%=estados_vig_novig_nombre%>"></div>
		<div class="divhead"><strong>ESTADO P:</strong>
			<input type="text" style="width:157px;height:30px; background:#FFF;" disabled="disabled" value="<%=estado_periodo_nombre%>"></div>
		<div class="divhead"><strong>Fecha creaci�n:</strong>
			<input type="text" style="width:225px;height:30px; background:#FFF;" disabled="disabled" value="<%=fec_crea%>"></div>
		<div class="divhead"><strong>Usuario creador:</strong>
			<input type="text" style="width:400px;height:30px; background:#FFF;" disabled="disabled" value="<%=user_crea%>"></div>
		<div class="divhead"><strong>Fecha �ltima mod:</strong>
			<input type="text" style="width:225px;height:30px; background:#FFF;" disabled="disabled" value="<%=fec_mod%>"></div>
		<div class="divhead"><strong>Usuario �ltima mod:</strong>
			<input type="text" style="width:400px;height:30px; background:#FFF;" disabled="disabled" value="<%=user_mod%>"></div>
	</div>
</div><br><br><br>
<div class="footerapp">               
	<p style="float: left;"><i class="icon-user"></i><strong><%=Usu_nom %></strong></p>
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


