<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
String[] arAlertas =(String[]) request.getAttribute("ar_alertas");
String filtro = "";
if(request.getAttribute("filtro")!=null){
filtro = (String)request.getAttribute("filtro");
}
String cuenta=(String)request.getAttribute("cuenta");
String Usu_nom=(String)request.getAttribute("usuname");


    %>
<!DOCTYPE html>
<html lang="en"><head>
    <meta charset="utf-8">
    <title>New Office - Generador Documento DTE</title>
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

<script>
	   $(function(){
	    $("#todos").click(function(e) {
	     if($("#todos").attr('checked')){
			$('.dale').attr('checked',true);
	     }else{
			$('.dale').attr('checked',false);
	     }
		});
	   });
	  </script>
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
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
		
  	tbody {
	    height: 225px;
	    overflow-y: scroll;
	    
	}
	thead, tbody {
		 min-width:100%;
    	
	}
	tr{
		 width:100%;
	}
	tr.hov:hover{
		opacity:0.5;
		cursor: pointer;
	}
  
    </style>
    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
    
   <script>
    $(document).ready(function() {

         $("#searchInput").keyup(function(){
   	//hide all the rows
             $("#fbody").find("tr").hide();

   	//split the current value of searchInput
             var data = this.value;//.split(" ");
   	//create a jquery object of the rows
             var jo = $("#fbody").find("tr");
             
   	//Recusively filter the jquery object to get results.
            // $.each(data, function(i, v){
                 jo = jo.filter("*:contains('"+data.toUpperCase()+"')");
             //});
           //show the rows that match.
             jo.show();
        //Removes the placeholder text  
      
         }).focus(function(){
         //    this.value="";
             $(this).css({"color":"black"});
             $(this).unbind('focus');
         }).css({"color":"#000"});

     });

    $( document ).ready(function() {
                        // Handler for .ready() called.
           $( "#datepicker" ).datepicker({ dateFormat: "dd-mm-yy", monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
                                                      monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
                                                      dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
                                                      dayNamesShort: ['Dom','Lun','Mar','Mie','Juv','Vie','Sab'],
                                                      dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa']});
           $( "#datepicker2" ).datepicker({ dateFormat: "dd-mm-yy", monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
                                                       monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
                                                       dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
                                                       dayNamesShort: ['Dom','Lun','Mar','Mie','Juv','Vie','Sab'],
                                                       dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa']});

                        
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
	  <p>N800.C.01.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESI�N</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/800/'">VOLVER</button> 
	<div align="center" class="title">
		<p >CONSULTAR GENERADOR DOCUMENTO DTE</p>
	</div>
</div>
<div class="content">
<form action="C02_001" name="form1" method="get">
<input type="hidden"  name="modulo" id="modulo"   >

	<div align="center" class="form-group has-warning">
		<strong style="font-size:20px;">FEC DESDE:</strong><input maxlength="35" type="text" id="datepicker" name="f1" class="amarillo" style="width:130px;height:30px ;background:#FF3;" >
        <strong style="font-size:20px;">FEC HASTA:</strong><input maxlength="35" type="text" id="datepicker2" name="f2" class="amarillo" style="width:130px;height:30px ;background:#FF3;" >
	</div>
	<div class="containermenu">
		<div class="menu" style="width:800px">
			<div class="titulomenu" style="border: 1px solid black; width:800px">
				<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px">
				<p style="margin:0px 0px;font-size:20px;background-color:#39F;width:798px">ELEGIR MODULO A CONSULTAR DTE</p> </h3>
			</div>
			<div style="max-height:400px; width:800px;">
				<table class="table" style="margin-left:0px">
					<thead style="border: 1px solid black;display: inline-block;">
					<tr style="background-color:#0101DF;color:#FFFFFF">
					<td width="50px" style="font-size:20px"><strong>ID</strong></td>
					<td width="700px" style="font-size:20px"><strong>TIPO DOCUMENTO</strong></td>
					<td width="50px" style="font-size:20px"><strong>TOTAL</strong></td>
					</tr>  
					<tbody id="fbody" class="scroll" style="border: 1px solid black;display: inline-block;font-size:20px;">
<% 
					for(int i =0; i<arAlertas.length; i++){
						if(arAlertas[i]==null) break;
						String[] Alertas = arAlertas[i].toString().split("/");
						boolean alertas_has_emp=false;
					%>        		
						<tr class="hov" onclick="document.getElementById('modulo').value='<%=Alertas[0]%>';document.form1.submit();">
							<td width="50px" ><%=Alertas[0]%></td>
							<td width="700px" ><%=Alertas[1]%></td>
							<td width="50px" ><%=Alertas[2]%></td>
						</tr>
			        	<% 
			        	cuenta="0";} %>
				</table>
			</div>
		</div>
	</div>
</form> 
</div>
<br><br><br>
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

