<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 		


String Usu_nom=(String)request.getAttribute("usuname");

String tipodte =(String)request.getAttribute("tipodte");
String fhasta =(String)request.getAttribute("fhasta");
String fdesde =(String)request.getAttribute("fdesde");
String fcrea= (String)request.getAttribute("fcrea");
String id= (String)request.getAttribute("id");

String estados_vig_novig_nombre= (String)request.getAttribute("estados_vig_novig_nombre");


String nomdte="";
if(tipodte.trim().equals("33")) nomdte="FACTURA ELECTRONICA";
if(tipodte.trim().equals("34")) nomdte="FACTURA EXENTA ELECTRONICA";
if(tipodte.trim().equals("46")) nomdte="FACTURA DE COMPRA ELECTRONICA";
if(tipodte.trim().equals("52")) nomdte="GUIA DE DESPACHO ELECTRONICA";
if(tipodte.trim().equals("61")) nomdte="NOTA DE CREDITO ELECTRONICA";

String emp=(String)request.getAttribute("emp");

String admfolios_correo_aviso=(String)request.getAttribute("admfolios_correo_aviso");

String em_id=(String)request.getAttribute("em_id");

String folioactual= (String)request.getAttribute("folioactual");


    %>
<!DOCTYPE html>
<html lang="en"><head>
  <% 
  if(request.getParameter("Exito")!=null && !request.getParameter("Exito").equals("")){
  		if(request.getParameter("Exito").equals("OK")){
			out.println("<script>alert('OPERACI\u00d3N REALIZADA CON \u00c9XITO')</script>");
			//out.println("<script>window.location = '/001/'</script>");
		} 
	}
   
	%>
    <meta charset="utf-8">
    <title>New Office - ADMINISTRACION DE FOLIOS</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
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
			background-color: #FF3;
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
		.table1{
			font-size:20px;
			}
	.tbody1 {
	  
	    height: 120px;
	    overflow-y: scroll;
	}
	.thead1, .tbody1 {
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
			min-height:100%; height:370px;  
			min-width: 350px;
			max-width:800px;
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
    <script>

    $( document ).ready(function() {
                        // Handler for .ready() called.
                        
                    
         
    });
    
    function validateSubmit(){
    	
    	var todobien=true;
    	var msg="";
    	
    	if(document.getElementById('fhasta').value==''){
    		todobien=false;
    		msg+="DEBE INGRESAR EL FOLIO HASTA\n";
    	}
    	
    	if(document.getElementById('fdesde').value!='' && document.getElementById('fhasta').value!=''){
    		if(Number(document.getElementById('fhasta').value)<=Number(document.getElementById('fdesde').value)){
    			todobien=false;
        		msg+="FOLIO HASTA NO PUEDE SER MENOR O IGUAL A FOLIO DESDE\n";
    		}
    	}
    	
    	
    	if(todobien){return true;}
    	else{
    		alert(msg);
    		msg="";
    		return false;
    	}
    }
   </script>
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
	  <p>N850.M.02.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/850/m1'">VOLVER</button> 
	<div align="center" class="title">
		<p >MODIFICACION ADMINISTRACION DE FOLIOS</p>
	</div>
</div>
<div class="content" >
  <form action="" name="form1" method="post">
  <input type="hidden" name="em_id" value="<%=em_id %>" >
  
  <input type="hidden" name="fhasta_real" value="<%=fhasta %>"> 
 	<div class=" cuadroblanco" style="height:280px;">
		<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>ID:</strong><input type="text" style="width:60px;height:30px ;background:#FFF;"  readonly="readonly" value="<%=id%>"></div>
		<div class="divhead"><strong>COD DTE:</strong><input type="text" style="width:60px;height:30px ;background:#FFF;"  readonly="readonly" value="<%=tipodte%>" name="tipodte"><input type="text" style="width:340px;height:30px ;background:#FFF;"  readonly="readonly" value="<%=nomdte%>"></div>
		<div class="divhead"><strong>EMP:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" name="tipodte" readonly="readonly" value="<%=emp%>"> </div>
		
		<div class="divhead"><strong style="font-size:20px;">FOLIO DESDE:</strong><input  value="<%=fdesde %>" type="text"   readonly="readonly" style="width:130px;height:30px ;background:#FFF;" name="fdesde" id="fdesde"></div>
        <div class="divhead"><strong style="font-size:20px;">FOLIO HASTA:</strong><input  value="<%=fhasta %>" type="text"   style="width:130px;height:30px ;background:#FF3;"  name="fhasta" class="amarillo" ></div>
		<div class="divhead"><strong style="font-size:20px;">FECHA:</strong><input  value="<%=fcrea %>" type="text" readonly="readonly" style="width:130px;height:30px ;background:#FFF;" ></div>
		<div class="divhead"><strong>ULTIMO FOLIO UTILIZADO:</strong><input maxlength="10" value="<%=folioactual %>" type="text"  name="folioactual" id="folioactual" readonly="readonly" style="width:130px;height:30px ;background:#FFF;text-align: right;" ></div>
		
		<div class="divhead"><strong>ESTADO:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;" name="tipodte" readonly="readonly" value="<%=estados_vig_novig_nombre%>"></div>
		<div class="divhead" style="height: 30px;vertical-align: middle;margin-top: 8px;" ><strong>CUANDO FALTEN MENOS DE 10 FOLIOS ENVIAR CORREO A:</strong></div>
		
		<div class="divhead"><textarea maxlength="195"   name="admfolios_correo_aviso" id="admfolios_correo_aviso" class="amarillo" style="width:730px;height:70px ;background:#FF3;text-transform:uppercase;"   ><%=admfolios_correo_aviso %></textarea></div>
		
		</div>
		<div class="bgrabar"  >
           <button type="submit" name="grabar" class="btn btn-success " onclick="return validateSubmit()">GRABAR</button>
           </div>
	</div> 
	
	
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


