<%@page import="VO.ContratoVO"%>
<%@page import="VO.RangoEstructuraCobroVO"%>
<%@page import="VO.EstructuraCobroVO"%>
<%@page import="java.util.ArrayList"%>
<% 	

String modname=(String)request.getAttribute("modname");

String Usu_nom=(String)request.getAttribute("usuname");	

EstructuraCobroVO estructuraCobro= (EstructuraCobroVO)request.getAttribute("estructuraCobro");
ContratoVO contrato= (ContratoVO)request.getAttribute("contrato");




    %>
<!DOCTYPE html>
<html lang="en"><head>
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
   <link rel="stylesheet" type="text/css" href="stylesheets/inputs.css">
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">

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
			
				}
	.inputRangos{
		width: 100px;
	}
	.tdrangos{
		width: 111px;
	}
	.inputFijoVariable{
		width: 120px;
	}
	.tdFijoVariable{
		width: 131px;
	}
	.tdTipoRango{
		width: 220px;
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
	  <p>N013.C.03.001</p>
	</div>
	<form method="get" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/013/C02?anexo=${estructuraCobro.getAnc_id()}'">VOLVER</button> 
	<div align="center" class="title">
		<p >CONSULTAR <%=modname %></p>
	</div>
</div>
<div class="content" >
  <form name="form1" id="formSub" method="post" action="I03"> 
    <input type="hidden" name="contrato_id" value="">
    
		<div class=" cuadroblanco" style="height:200px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">ESTRUCTURA DE COBRO</p></h3>
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>ID ESTRUCTURA:</strong><input type="text" value="${estructuraCobro.getEstrcobro_id()}" style="width:70px;height:30px ;background:#FFF;" readonly="readonly"></div> 
		<div class="divhead"><strong>CONTRATO:</strong><input type="text" style="width:290px;height:30px ;background:#FFF;" readonly="readonly" value="<%=contrato.getContrato_nombre()  %>"></div>		
		<div class="divhead"><strong>COSTO POR ADICION:</strong><input type="text" value="${estructuraCobro.getEstrcobro_cxa()}" style="width:120px;height:30px ;background:#FFF;" maxlength="10" readonly="readonly" ></div>
		<div class="divhead"><strong>TIPO CAMBIO:</strong><input type="text"  value="${estructuraCobro.getTipocambio().getNombre()}" style="width:120px;height:30px ;background:#FFF;" maxlength="10" readonly="readonly" ></div>
    	<div class="divhead"><strong>INDIVIDUAL/GRUPAL:</strong><input type="text"  value="${estructuraCobro.getTipo_estructuraC().getNombre()}" style="width:120px;height:30px ;background:#FFF;" maxlength="10" readonly="readonly" ></div>
    	
    	<div class="divhead"><strong>NOMBRE:</strong><input type="text"  maxlength="50" style="width:520px;height:30px ;background:#FFF;" value="${estructuraCobro.getEstrcobro_nombre()}" readonly="readonly"></div>
		
    	<div class="divhead"><strong>OBSERVACION:</strong><input type="text" style="width:720px;height:30px ;background:#FFF;" readonly="readonly" value="${estructuraCobro.getEstrcobro_observaciones()}"></div>
		
		</div>
	</div>
	<table  class="table004det">
  <thead style="border-bottom: 1px solid black;background:#39F">
  <tr style="background:#39F">
   	<th scope="col" style="width: 50px;" >ID</th>
    <th scope="col" class="tdrangos" >INICIAL</th>
    <th scope="col" class="tdrangos" >FINAL</th>
    <th scope="col" class="tdFijoVariable">FIJO</th>
    <th scope="col" class="tdFijoVariable">VARIABLE</th>
  </tr>
  </thead>
  <tbody class="scroll">
  <% if(estructuraCobro.getRangosEstCobro()!=null) for(int i =0; i<estructuraCobro.getRangosEstCobro().size();i++){ 
  		RangoEstructuraCobroVO rango = (RangoEstructuraCobroVO)estructuraCobro.getRangosEstCobro().get(i);
  		
  	%>
	  <tr>
    <td class="int" width="50px"><%=rango.getRango_id() %></td>
    <td class="int" align="center"><input type="text" value="<%=rango.getRango_inicial() %>" class="inputRangos" style="height:30px ;background:#FFF;" readonly="readonly" onkeydown="return validateNum(event)"></td>
    <td class="int" align="center"><input type="text" value="<%=rango.getRango_final() %>" name="<%=rango.getRango_id() %>_rangoFinal" id="<%=rango.getRango_id() %>_rangoFinal"    class="inputRangos" style="height:30px ;background:#FFF;" readonly="readonly" ></td>
    <td class="int tdFijoVariable" align="center"><input type="text" name="<%=rango.getRango_id() %>_rangoCFijo" value="<%=rango.getRango_costo_fijo()%>" class="inputFijoVariable" style="height:30px ;background:#FFF;" readonly="readonly" ></td>
    <td class="int tdFijoVariable" align="center"><input type="text" name="<%=rango.getRango_id() %>_rangoCVariable" value="<%=rango.getRango_costo_variable() %>" class="inputFijoVariable" style="height:30px ;background:#FFF;" readonly="readonly" ></td>
 
    
  </tr>
  <%
   } %>
    
  
         
     
  </tbody>
</table> 


	
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


