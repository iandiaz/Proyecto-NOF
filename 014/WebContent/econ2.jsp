<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    
    
    <%
    String Usu_nom=(String)request.getAttribute("usuname");
    String empresas_id=(String)request.getAttribute("empresas_id");
    String empresas_rut=(String)request.getAttribute("empresas_rut");
    String grupos_nombre=(String)request.getAttribute("grupos_nombre");
    if(grupos_nombre==null || grupos_nombre.equals("null")) grupos_nombre="";
    String empresas_nombrenof=(String)request.getAttribute("empresas_nombrenof");
    String empresas_razonsocial=(String)request.getAttribute("empresas_razonsocial");
    String estados_vig_novig_nombre=(String)request.getAttribute("estados_vig_novig_nombre");
    
    String empresas_escliente=(String)request.getAttribute("empresas_escliente");
    String empresas_esproveedor=(String)request.getAttribute("empresas_esproveedor");
    String empresas_esprospeccion=(String)request.getAttribute("empresas_esprospeccion");
    String empresas_relacionada=(String)request.getAttribute("empresas_relacionada");
	
    
 	String CONT_ID=(String)request.getAttribute("CONT_ID");
    
   
    
    String CONT_NOMBRE=(String)request.getAttribute("CONT_NOMBRE");
    String CONT_APEP=(String)request.getAttribute("CONT_APEP");
    String CONT_APEM=(String)request.getAttribute("CONT_APEM");
    String CONT_CARGO=(String)request.getAttribute("CONT_CARGO");
   
    String CONT_EMAIL=(String)request.getAttribute("CONT_EMAIL");
    String CONT_TELEFONO=(String)request.getAttribute("CONT_TELEFONO");
    String CONT_TELEFONOC=(String)request.getAttribute("CONT_TELEFONOC");
    
    String DIRE_DIRECCION=(String)request.getAttribute("DIRE_DIRECCION");
    String TICON_DESCRIPCION=(String)request.getAttribute("TICON_DESCRIPCION");
    String estvnv=(String)request.getAttribute("estvnv");
    String CONT_OBS=(String)request.getAttribute("CONT_OBS");
    String Estado_Clpr_nombre=(String)request.getAttribute("Estado_Clpr_nombre");
    
    
    %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>New Office - CONTACTO</title>
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
    <script type="text/javascript" src="/801/lib/jquery.Rut.min.js"></script>

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
 <link rel="stylesheet" href="lib/fancy/jquery.fancybox.css?v=2.1.5" type="text/css" media="screen" />
<script type="text/javascript" src="lib/fancy/jquery.fancybox.pack.js?v=2.1.5"></script>
 
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
	.detailcb{
		height:158px;  
		overflow-y: scroll;
		
	}
		
	@media screen and (min-width: 700px) {
	.cuadroblanco{
			box-shadow:0px 2px 5px 0px rgba(50, 50, 50, 0.75);
			border: 1px solid black;
			min-height:100%; height:330px;  
		min-width: 350px;
			max-width:1200px;
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
    	
    	var validate= true;
    	var msg="ERRORES: \n";
    	
    	if(document.getElementById('nom').value==""){
    		msg+="DEBE INGRESAR EL NOMBRE DEL CONTACTO\n";
    		validate=false;
    	}
    	if(document.getElementById('cargo').value==""){
    		msg+="DEBE INGRESAR EL CARGO DEL CONTACTO\n";
    		validate=false;
    	}
    	if(document.getElementById('email').value==""){
    		msg+="DEBE INGRESAR EL EMAIL DEL CONTACTO\n";
    		validate=false;
    	}
    	if(document.getElementById('fono').value==""){
    		msg+="DEBE INGRESAR EL TELEFONO DEL CONTACTO\n";
    		validate=false;
    	}
    	
    	if(validate){
    		
    		return true;
    	}
    	else{
    		alert(msg);
    		return false;
    	}
    	
    }
    
    $(document).ready(function() {
    	$(".various").fancybox({
    		maxWidth	: 800,
    		maxHeight	: 600,
    		fitToView	: false,
    		width		: '90%',
    		height		: '90%',
    		autoSize	: false,
    		closeClick	: false,
    		openEffect	: 'none',
    		closeEffect	: 'none'
    	});
    });

    
    function validateNum(event) {
        var key = window.event ? event.keyCode : event.which;

        if (event.keyCode == 8 || event.keyCode == 46
         || event.keyCode == 37 || event.keyCode == 39) {
            return true;
        }
        else if ( key < 48 || key > 57 ) {
            return false;
        }
        else return true;
    };
</script>
  </head>
<body >


<div class="navbar">
	<div class="brand">
	  <img src="lib/bootstrap/img/logonew_big.png">
	</div>
	<div class="version" align="right">
	  <p>N014.E.02.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESI&Oacute;N</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/014/econ1'">VOLVER</button> 
	<div align="center" class="title">
		<p >ELIMINAR CONTACTO</p>
	</div>
</div>
<div class="content" >
  <form action="" name="form1" method="post" style="margin: 0px 0px 0px 0px">
  <input type="hidden" name="CONT_ID" value="<%=CONT_ID %>" >
  <div class=" cuadroblanco" style="height:235px;">
  	<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CLIENTE/PROVEEDOR</p></h3>
  	   
		<div class="divhead"><strong>COD CLIENTE/PROVEEDOR:</strong><input  type="text" style="width:100px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_id%>"></div>
		<div class="divhead"><strong>RUT:</strong><input  type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_rut%>"></div>
		<div class="divhead"><strong>GRUPO:</strong><input type="text" style="width:290px;height:30px ;background:#FFF;" disabled="disabled" value="<%=grupos_nombre%>"></div>
		<div class="divhead"><strong>NOM FANTAS&Iacute;A:</strong><input type="text" style="width:620px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_nombrenof%>"></div>
		<div class="divhead" style="padding-top:10px;"><strong>RELACI&Oacute;N:</strong><input type="checkbox" disabled="disabled" <% if(empresas_escliente.equals("1")){%> checked="checked" <% }  %> >ES CLIENTE  <input type="checkbox" disabled="disabled" <% if(empresas_esproveedor.equals("1")){%> checked="checked" <% } %> >ES PROVEEDOR <input type="checkbox" disabled="disabled" <% if(empresas_esprospeccion.equals("1")){%> checked="checked" <% } %> >ES PROSPECCION</div>
		<div class="divhead" style="width: 500px;"><strong>EMP RELACIONADA:</strong><input type="text" value="<%=empresas_relacionada%>" style="width:45px;height:30px; background:#FFF; " disabled="disabled"></div>
  
		<div class="divhead"><strong>RAZ&Oacute;N SOCIAL:</strong><input type="text" style="width:620px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_razonsocial%>"></div>
		 <div class="divhead"><strong>ESTADO CLI-PROV:</strong><input type="text" value="<%=Estado_Clpr_nombre%>" style="width:370px;height:30px; background:#FFF; " disabled="disabled"></div>
		<div class="divhead"><strong>ESTADO:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=estados_vig_novig_nombre%>"></div>
	</div>
	
	<div class=" cuadroblanco" style="height:200px;margin-top: 2px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DETALLE CONTACTO</p></h3>
		<div class="detailcb scroll">	
			<div class="divhead"><strong>ID CONTACTO:</strong><input type="text" style="width:60px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CONT_ID%>"></div>
			<div class="divhead"><strong>TIPO CONTACTO:</strong><input type="text" style="width:260px;height:30px ;background:#FFF;" disabled="disabled" value="<%=TICON_DESCRIPCION %>" ></div>
			<div class="divhead"><strong>NOMBRE:</strong><input type="text" maxlength="30"  style="width:350px;height:30px ;background-color: #FFF"  value="<%=CONT_NOMBRE %>" disabled="disabled" ></div>
			<div class="divhead"><strong>APE P:</strong><input type="text" maxlength="30"   style="width:350px;height:30px ;background-color: #FFF"   value="<%=CONT_APEP %>" disabled="disabled"  ></div>
			<div class="divhead"><strong>APE M:</strong><input type="text" maxlength="30"  style="width:350px;height:30px ;background-color: #FFF"   value="<%=CONT_APEM %>" disabled="disabled" ></div>
			<div class="divhead"><strong>CARGO:</strong><input type="text" style="width:460px;height:30px ;background-color: #FFF" maxlength="40"  value="<%=CONT_CARGO %>" disabled="disabled"  ></div>		
			<div class="divhead"><strong>DIRECCION:</strong><input type="text" style="width:700px;height:30px ;background-color: #FFF" maxlength="40"  value="<%=DIRE_DIRECCION %>" disabled="disabled"  ></div>
			<div class="divhead"><strong>ESTADO CONTACTO:</strong><input type="text" style="width:167px;height:30px ;background-color: #FFF"  value="<%=estvnv %>"  disabled="disabled"></div>
			<div class="divhead"><strong>EMAIL:</strong><input type="text" style="width:620px;height:30px ;background-color: #FFF"  value="<%=CONT_EMAIL %>" readonly="readonly" ></div>
			<div class="divhead"><strong>TELEFONO:</strong><input type="text" maxlength="20"  style="width:240px;height:30px ;background-color: #FFF"  value="<%=CONT_TELEFONO %>" disabled="disabled" ></div>
			<div class="divhead"><strong>CELULAR:</strong><input type="text" maxlength="20" style="width:240px;height:30px ;background-color: #FFF"  value="<%=CONT_TELEFONOC %>" disabled="disabled"></div>
			<div class="divhead"><strong>OBS:</strong><input type="text" style="width:350px;height:30px ;background-color: #FFF"  value="<%=CONT_OBS %>" disabled="disabled"></div>
		</div>
	</div> 
	 
	<div class="bgrabar">
	      <button type="submit" name="grabar" id="grabar" class="btn btn-success">GRABAR</button>
       </div> 
	
</form>
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