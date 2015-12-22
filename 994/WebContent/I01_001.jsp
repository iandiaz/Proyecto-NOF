<%@page import="java.util.ArrayList"%>
<%

	String Usu_nom=(String)request.getAttribute("usuname");	
	String modname=(String)request.getAttribute("modname");
	
	String date=(String)request.getAttribute("date");
	String time=(String)request.getAttribute("time");
	
	
	
	
%>
<!DOCTYPE html>
<html lang="en"><head>
 <script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>


<script type='text/javascript'>

</script>
<% 
  if(request.getParameter("Exito")!=null && !request.getParameter("Exito").equals("")){
  		if(request.getParameter("Exito").equals("OK")){
  			out.println("<script>alert('EL TICKET SE HA INGRESADO CON EXITO')</script>");
		} 
		
	}


	%>
	<!-- VALIDANDO QUE LOS CAMPOS VALLAN CON ALGO -->
	<script type="text/javascript">
	function validarIngreso(){
		var msg="";
		var validado=true; 
		if(document.getElementById('CLPR_NOMBRE_FANTASIA').value==""){
			msg+=('DEBE INGRESAR UN NOMBRE DE EMPRESA\n');
			document.getElementById('CLPR_NOMBRE_FANTASIA').focus();
			validado= false;
		}
		
		if(document.getElementById('TICK_USUARIO').value==""){
			msg+=('DEBE INGRESAR UN USUARIO\n');
			document.getElementById('TICK_USUARIO').focus();
			validado= false;
		}
		
		if(document.getElementById('TICK_EMAIL').value==""){
			msg+=('DEBE INGRESAR UN CORREO\n');
			document.getElementById('TICK_EMAIL').focus();
			validado= false;
		}
		
		if(document.getElementById('TICK_DIRECCION').value==""){
			msg+=('DEBE INGRESAR UNA DIRECCION\n');
			document.getElementById('TICK_DIRECCION').focus();
			validado= false;
		}
		
		if(document.getElementById('TICK_TELEFONO').value==""){
			msg+=('DEBE INGRESAR UN TELEFONO\n');
			document.getElementById('TICK_TELEFONO').focus();
			validado= false;
		}
		
		if(document.getElementById('TICK_MODELO_SERIE').value==""){
			msg+=('DEBE INGRESAR UN MODELO\n');
			document.getElementById('TICK_MODELO_SERIE').focus();
			validado= false;
		}
		if(document.getElementById('TICK_SERIE').value==""){
			msg+=('DEBE INGRESAR UNA SERIE\n');
			document.getElementById('TICK_SERIE').focus();
			validado= false;
		}
		if(document.getElementById('TICK_CODIGO_FALLA').value==""){
			msg+=('DEBE INGRESAR UN CODIGO DE FALLA\n');
			document.getElementById('TICK_CODIGO_FALLA').focus();
			validado= false;
		}
		if(document.getElementById('TICK_FALLA_DENUNCIA').value==""){
			msg+=('DEBE INGRESAR UN DETALLE DE TICKET\n');
			document.getElementById('TICK_FALLA_DENUNCIA').focus();
			validado= false;
		}
		if(document.getElementById('TICK_FECHA_ENVIO').value==""){
			msg+=('DEBE INGRESAR UNA FECHA\n');
			document.getElementById('TICK_FECHA_ENVIO').focus();
			validado= false;
		}
		if(document.getElementById('TICK_HORA_ENVIO').value==""){
			msg+=('DEBE INGRESAR UNA HORA\n');
			document.getElementById('TICK_HORA_ENVIO').focus();
			validado= false;
		}
		
		if(validado){
			$('.loading').show();
			$('.noloading').hide();
			$.post( "putTicketAsync",$( "#form1" ).serialize())
	        .done(function( data ) {
	        	 	var arr = $.parseJSON(data);
	        		alert(arr[1]);
	        	  
		        	  if(arr[0]=="ERROR"){
		        		  	$('.loading').hide();
		      				$('.noloading').show();
		        	  }
		        	  else if(arr[0]=="OK"){
		        		  location="http://www.newoffice.cl";
		        	  }
	        });
		}
		else{
			alert(msg);
		}
		
		
		
		return false; 
	}
	</script>
	
	<!-- FIN VALIDANDO QUE LOS CAMPOS VALLAN CON ALGO -->

    <meta charset="utf-8">
    <title>New Office </title>
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
				
		.select2-results {
		  max-height: 200px;
		  
		}
		
	
.cuadroblanco{
			box-shadow:0px 2px 5px 0px rgba(50, 50, 50, 0.75);
			border: 1px solid black;
			min-height:100%; height:460px;  
			min-width: 1000px;
			max-width:1000px;
			position: relative;
			background:#CCC;
			margin: 0 auto;
			padding-left: 10px;
		}
		.inputMovil{
			width:100%;
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
    <link href="lib/select2/css/select2.min.css" rel="stylesheet" />
	<script src="lib/select2/js/select2.min.js"></script>
	
	
	
	 <link rel="stylesheet" href="lib/jquery-ui/css/ui-lightness/jquery-ui-1.10.4.custom.css">
    
    <script src="lib/jquery-ui/js/jquery-ui-1.10.4.custom.js"></script>
    
    <link rel="stylesheet" href="lib/timepicker/jquery.timepicker.css">
    
    <script src="lib/timepicker/jquery.timepicker.min.js"></script>
    
    <script type="text/javascript">
	 $( document ).ready(function() {
		 $( "#TICK_FECHA_ENVIO" ).datepicker({ dateFormat: "dd-mm-yy", monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
             monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
             dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
             dayNamesShort: ['Dom','Lun','Mar','Mie','Juv','Vie','Sab'],
             dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa']});
		 $( "#TICK_HORA_ENVIO" ).timepicker({ 'timeFormat': 'H:i' });
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
                     
      <div class="brand"><img src="lib/bootstrap/img/logonew_big.png"></div>
                     <div class="version" align="right">
                     <p>N994.I.01.001</p>
                     </div>
                    
                    <div align="center" class="title" >
                    <p >INGRESAR TICKET</p>
                    </div>
                   
                    
                    
               
                             
    </div>
         
   <div class="content" >
        
           <div class=" cuadroblanco" >
         
           <br>
         <form action="" name="form1" id="form1" method="post">
          
    <div class="divhead"><strong>EMPRESA:</strong><input maxlength="60" autofocus="autofocus" type="text" class="amarillo" style="width:620px;height:30px ;" name="CLPR_NOMBRE_FANTASIA" id="CLPR_NOMBRE_FANTASIA" required ><span class="cabecera" style="color:#F00">*</span></div>
    <div class="divhead"><strong>ESTADO TICKET:</strong><input type="text" style="width:100px;height:30px ;background:#FFF;" value="ABIERTO" readonly="readonly" ></div>
    <div class="divhead"><strong>USUARIO:</strong><input maxlength="30" type="text" class="amarillo" style="width:320px;height:30px ;" name="TICK_USUARIO" id="TICK_USUARIO" required ><span class="cabecera" style="color:#F00">*</span></div>        
    <div class="divhead"><strong>E-MAIL:</strong><input maxlength="40" type="text" class="amarillo" style="width:420px;height:30px ;" name="TICK_EMAIL" id="TICK_EMAIL" required><span class="cabecera" style="color:#F00">*</span></div> 
    <div class="divhead"><strong>DIRECCION - UBICACION:</strong><input maxlength="70" type="text" class="amarillo" style="width:720px;height:30px ;" name="TICK_DIRECCION" id="TICK_DIRECCION" required ><span class="cabecera" style="color:#F00">*</span></div>
    <div class="divhead"><strong>TELEFONO:</strong><input maxlength="20" type="text" class="amarillo" style="width:220px;height:30px ;" name="TICK_TELEFONO" id="TICK_TELEFONO" required ><span class="cabecera" style="color:#F00">*</span></div>
    <div class="divhead"><strong>MODELO:</strong><input maxlength="30" type="text" class="amarillo" style="width:320px;height:30px ;" name="TICK_MODELO_SERIE" id="TICK_MODELO_SERIE" required ><span class="cabecera" style="color:#F00">*</span></div>
    <div class="divhead"><strong>SERIE:</strong><input maxlength="30" type="text" class="amarillo" style="width:320px;height:30px ;" name="TICK_SERIE" id="TICK_SERIE" required ><span class="cabecera" style="color:#F00">*</span></div>
         
     
     <div class="divhead"><strong>COD FALLA:</strong><input maxlength="30" type="text" class="amarillo" style="width:320px;height:30px ;" name="TICK_CODIGO_FALLA" id="TICK_CODIGO_FALLA" required ><span class="cabecera" style="color:#F00">*</span></div>
     <div class="divhead"><strong>FECHA SITUACION:</strong><input type="text" class="amarillo" style="width:120px;height:30px ;" name="TICK_FECHA_ENVIO" id="TICK_FECHA_ENVIO" readonly="readonly" value="<%=date %>" ><span class="cabecera" style="color:#F00">*</span></div>
     <div class="divhead"><strong>HORA SITUACION:</strong><input type="text" class="amarillo" style="width:70px;height:30px ;" name="TICK_HORA_ENVIO" id="TICK_HORA_ENVIO" value="<%=time %>" ><span class="cabecera" style="color:#F00">*</span></div>
     <div class="divhead" style="height: 105px"><strong>DETALLE DE TICKET:</strong><textarea maxlength="1000" name="TICK_FALLA_DENUNCIA" id="TICK_FALLA_DENUNCIA" class="amarillo" style="width: 850px; height: 80px "></textarea><span class="cabecera" style="color:#F00">*</span></div>
     
     <div class="divhead"><strong>INGRESADO AL SISTEMA POR:</strong><input type="text" style="width:50px;height:30px ;" value="CLI" readonly="readonly" ></div>
     <div class="divhead"><strong>FECHA:</strong><input type="text" style="width:120px;height:30px ;" readonly="readonly" value="<%=date %>" ></div>
     <div class="divhead"><strong>HORA:</strong><input type="text" style="width:70px;height:30px ;" readonly="readonly" value="<%=time %>"></div>
     <div class="divhead"><strong style="color: red">SI TIENE TONERS VACIOS INGRESE SU TICKET PARA COORDINAR SU RETIRO.</strong></div>
     <div class="divhead"><strong>SITIO OPTIMIZADO PARA INTERNET EXPLORER Y UNA RESOLUCION DE 1024X768.</strong></div>
     
      	
      	
            <div class="bgrabar noloading">
           <button type="submit" name="grabar" id="grabar" onclick="return validarIngreso()" class="btn btn-success" >GRABAR</button>
          </div> 
          <div class="bgrabar loading" style="display: none;">
           <button type="button" class="btn btn-success" >ENVIANDO..</button>
          </div> 
</form>
 
 
       </div> 
  <!-- Table -->

         
        
         </div>
      
				<div class="footerapp">
               
                  <p style="float: left;"><i class="icon-user"></i>
                  <strong>NewOffice</strong></p>
                  
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
