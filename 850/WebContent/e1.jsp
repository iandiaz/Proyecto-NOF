<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 		


String[] ar_admfolios =(String[])request.getAttribute("ar_admfolios");

String Usu_nom=(String)request.getAttribute("usuname");


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
	
	tr.hov:hover{
		opacity:0.5;
		cursor: pointer;
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
			max-width:770px;
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
	  <p>N850.E.01.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/850/'">VOLVER</button> 
	<div align="center" class="title">
		<p >ELIMINAR ADMINISTRACION DE FOLIOS</p>
	</div>
</div>
<div class="content" >
  <form action="" name="form1" method="post">

	
	<div class=" cuadroblanco" style="height:200px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px">
		<p style="margin:0px 0px;font-size:20px;background-color: #39F">SELECCIONAR RANGO DE FOLIOS A ELIMINAR </p></h3>
		<table class="table table1" style="width:100%;">
			<thead style="border: 1px solid black;background-color:#0101DF;" class="thead1" >
			   <tr style="background-color:#0101DF;color:#FFFFFF">
				<td width="160px" style="font-size:20px" ><strong>FECHA</strong></td>
				<td width="150px" style="font-size:20px"><strong>DESDE</strong></td>
				<td width="150px" style="font-size:20px"><strong>HASTA</strong></td>
				<td width="150px" style="font-size:20px"><strong>TIPO DTE</strong></td>
				<td width="150px" style="font-size:20px"><strong>EMP</strong></td>
			  </tr>  
			<tbody id="fbody" class="scroll tbody1" style="border: 1px solid black">
			 <%   
			 String des;
        for(int i =0; i<ar_admfolios.length; i++){
       	 String[] ProdDato = ar_admfolios[i].split("/");
       	 %>
			<tr class="hov" style="color:<%=(ProdDato[7].equals("1") && ProdDato[5].equals("1")) ? "#000" :"#FFF" %>;font-size:20px;background-color:<%=ProdDato[7].equals("1") && ProdDato[5].equals("1") ? "#00FF00" :ProdDato[5].equals("1") ? "#759AFF": "#F00" %>;font-size:20px;" 
			onclick="location='e2?id=<%=ProdDato[4]%>';">
				<td width="160px" ><%=ProdDato[0]%></td>
				<td width="150px"><%=ProdDato[1]%></td>
				<td width="150px"><%=ProdDato[2]%></td>
				<td width="150px"><%=ProdDato[3]%></td>
				<td width="150px"><%=ProdDato[6]%></td>
				
			</tr>
       	<% } %>
			</tbody>
		  </table>
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


