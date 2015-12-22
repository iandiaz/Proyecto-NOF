<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 		

String empresas_id =(String)request.getAttribute("empresas_id");
String empresas_razonsocial =(String)request.getAttribute("empresas_razonsocial");
String empresas_nombrenof =(String)request.getAttribute("empresas_nombrenof");
String hoy =(String)request.getAttribute("hoy");
String cant_preperiodos =(String)request.getAttribute("cant_preperiodos");

String[] ar_productos =(String[])request.getAttribute("ar_productos");

String Usu_nom=(String)request.getAttribute("usuname");
String fec_limite=(String)request.getAttribute("fec_limite");
if(fec_limite==null || fec_limite.equals("NULL")) fec_limite="";
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
    <title>New Office - Ingresar Periodo Toma de Contadores</title>
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
			min-height:100%; height:330px;  
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
                        
                        
           $( "#datepicker" ).datepicker({ dateFormat: "dd-mm-yy", monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
                                                      monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
                                                      dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
                                                      dayNamesShort: ['Dom','Lun','Mar','Mie','Juv','Vie','Sab'],
                                                      dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa']
											           <%
											           if(fec_limite!=null && !fec_limite.equals("")){
											           	String[] fec_parse = fec_limite.split("-");
											           %>
											           , minDate: new Date(<%=fec_parse[2]%>, <%=fec_parse[1]%> - 1, <%=fec_parse[0]%> + 1),
											           onClose: function( selectedDate ) {
											               $( "#datepicker2" ).datepicker( "option", "minDate", selectedDate );
											             }
											           <% } %>
           											});
           $( "#datepicker2" ).datepicker({ dateFormat: "dd-mm-yy", monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
                                                       monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
                                                       dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
                                                       dayNamesShort: ['Dom','Lun','Mar','Mie','Juv','Vie','Sab'],
                                                       dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa']
											           <%
											           if(fec_limite!=null && !fec_limite.equals("")){
											           	String[] fec_parse = fec_limite.split("-");
											           %>
											           , minDate: new Date(<%=fec_parse[2]%>, <%=fec_parse[1]%> - 1, <%=fec_parse[0]%> + 1),
											           onClose: function( selectedDate ) {
											               $( "#datepicker3" ).datepicker( "option", "minDate", selectedDate );
											             }
											           <% } %>
           												});
           $( "#datepicker3" ).datepicker({ dateFormat: "dd-mm-yy", monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
               monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
               dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
               dayNamesShort: ['Dom','Lun','Mar','Mie','Juv','Vie','Sab'],
               dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa']
	           <%
	           if(fec_limite!=null && !fec_limite.equals("")){
	           	String[] fec_parse = fec_limite.split("-");
	           %>
	           , minDate: new Date(<%=fec_parse[2]%>, <%=fec_parse[1]%> - 1, <%=fec_parse[0]%> + 1) 
	           <% } %>
					});
    });
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
	  <p>N701.I.02.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESI�N</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/701/ipro_search'">VOLVER</button> 
	<div align="center" class="title">
		<p >INGRESAR PERIODOS TOMA DE CONTADORES Y FACTURACION</p>
	</div>
</div>
<div class="content" >
  <form action="ipro" name="form1" method="post">
  <input type="hidden" name="empresas_id" value="<%=empresas_id%>" >
	<div class=" cuadroblanco" style="height:200px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CLIENTE</p></h3>
		<div class="divhead"><strong>RAZON SOCIAL:</strong><input type="text" style="width:600px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_razonsocial%>"></div>
		<div class="divhead"><strong>NOMBRE NOF:</strong><input type="text" style="width:650px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_nombrenof%>"></div>
		
		
		
		<center>
		<strong style="font-size:20px;">FECHA DESDE:</strong><input maxlength="35" value="<%=fec_limite %>" type="text" <% if(cant_preperiodos.equals("NO")){%> id="datepicker" class="amarillo" <% } %>  readonly="readonly" name="f1"  style="width:130px;height:30px ;<% if(cant_preperiodos.equals("NO")){%> background:#FF3; <% } %>" >
        <strong style="font-size:20px;">FECHA HASTA:</strong><input maxlength="35" value="<%=fec_limite %>" type="text" id="datepicker2" readonly="readonly" name="f2" class="amarillo" style="width:130px;height:30px ;background:#FF3;" >
		<br><strong style="font-size:20px;">FECHA FACTURACION:</strong><input maxlength="35" value="<%=fec_limite %>" type="text" id="datepicker3" readonly="readonly" name="f3" class="amarillo" style="width:130px;height:30px ;background:#FF3;" >
		
		</center>
		<div class="bgrabar"  >
           <button type="submit" name="grabar" class="btn btn-success ">GRABAR</button>
           </div>
	</div> 
	<br>
	<div class=" cuadroblanco" style="height:200px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px">
		<p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS PERIODOS</p></h3>
		<table class="table table1" style="width:100%;">
			<thead style="border: 1px solid black;background-color:#0101DF;" class="thead1" >
			   <tr style="background-color:#0101DF;color:#FFFFFF">
				<td width="100px" style="font-size:20px" ><strong>PERIODO</strong></td>
				<td width="150px" style="font-size:20px"><strong>DESDE</strong></td>
				<td width="150px" style="font-size:20px"><strong>HASTA</strong></td>
				<td width="150px" style="font-size:20px"><strong>FACTURACION</strong></td>
				<td width="157px" style="font-size:20px"><strong>ESTADO PER</strong></td>
			  </tr>  
			<tbody id="fbody" class="scroll tbody1" style="border: 1px solid black">
			 <%   
			 String des;
        for(int i =0; i<ar_productos.length; i++){
       	 String[] ProdDato = ar_productos[i].split("/");
       	 %>
			<tr class="hov">
				<td width="100px" align="center"><%=ProdDato[0]%></td>
				<td width="150px"><%=ProdDato[1]%></td>
				<td width="150px"><%=ProdDato[2]%></td>
				<td width="150px"><%=ProdDato[3]%></td>
				<td width="150px"><%=ProdDato[4]%></td>
			</tr>
       	<% } %>
			</tbody>
		  </table>
	</div>
</form>
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


