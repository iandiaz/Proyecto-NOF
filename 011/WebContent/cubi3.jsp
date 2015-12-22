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
    String estados_vig_novig_nombre_ubi=(String)request.getAttribute("estados_vig_novig_nombre_ubi");
    String conf=(String)request.getAttribute("conf");
    
    
    String empresas_escliente=(String)request.getAttribute("empresas_escliente");
    String empresas_esproveedor=(String)request.getAttribute("empresas_esproveedor");
    String empresas_esprospeccion=(String)request.getAttribute("empresas_esprospeccion");
    
    String id_ubi=(String)request.getAttribute("id_ubi");
   
    String empresas_relacionada=(String)request.getAttribute("empresas_relacionada");
	
    String DIRE_ID=(String)request.getAttribute("DIRE_ID");
    String DIRE_NOMBRE=(String)request.getAttribute("DIRE_NOMBRE");
    String DIRE_DIRECCION=(String)request.getAttribute("DIRE_DIRECCION");
    String DIRE_SECTOR=(String)request.getAttribute("DIRE_SECTOR");
    String TIDIR_NOMBRE=(String)request.getAttribute("TIDIR_NOMBRE");
    String DIRE_COD_POSTAL=(String)request.getAttribute("DIRE_COD_POSTAL");
    String COMU_NOMBRE=(String)request.getAttribute("COMU_NOMBRE");
    String CUAD_NOMBRE=(String)request.getAttribute("CUAD_NOMBRE");
    String DIRE_CIUDAD=(String)request.getAttribute("DIRE_CIUDAD");
    String dire_lat=(String)request.getAttribute("dire_lat");
    String dire_lon=(String)request.getAttribute("dire_lon");
    if(DIRE_CIUDAD==null || DIRE_CIUDAD.equals("null")) DIRE_CIUDAD ="";
    String REGI_NOMBRE=(String)request.getAttribute("REGI_NOMBRE");
    
    String dire_nsa_tecnico=(String)request.getAttribute("dire_nsa_tecnico");
    String dire_nsa_logistica=(String)request.getAttribute("dire_nsa_logistica");
    String dire_nsa_postventa=(String)request.getAttribute("dire_nsa_postventa");
    
    String UBI_ID=(String)request.getAttribute("UBI_ID");
    String UBI_DESCRIPCION=(String)request.getAttribute("UBI_DESCRIPCION");
    String UBI_USUARIO=(String)request.getAttribute("UBI_USUARIO");
    
    String UBI_TIPO=(String)request.getAttribute("UBI_TIPO");
    String tpg_id=(String)request.getAttribute("tpg_id");
    String UBI_FISICA=(String)request.getAttribute("UBI_FISICA");
    String ubi_usa_sububicacion=(String)request.getAttribute("ubi_usa_sububicacion");
    String ubi_usullave=(String)request.getAttribute("ubi_usullave");
    
  
    
    %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>New Office - UBICACION</title>
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
    	a{
		 font-family: '.HelveticaNeueDeskInterface-Regular'}
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
		height:250px;  
		overflow-y: scroll;
		
	}
	.ubiusuariodiv{
		display: none;
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


    function validateSubmit(){
    	
    	var validate= true;
    	
    	if(document.getElementById('cod_postal').value==""){
    		validate=false;
    	}
    	if(validate){
    		
    		return true;
    	}
    	else{
    		alert('DEBE INGRESAR TODOS LOS DATOS DE LA DIRECCION');
    		return false;
    	}
    	
    	
    }
    
    $(document).ready(function() {
    	$(".various").fancybox({
    		fitToView	: true,
    		autoWidth: true,
    		autoSize	: false,
    		closeClick	: false,
    		openEffect	: 'none',
    		closeEffect	: 'none',
    		padding:0, margin:0,
    		iframe : {
    	        scrolling : 'no'
    	    }
    	});
    	
    	<%if(UBI_TIPO.equals("2")){%> $('.ubiusuariodiv').show('slow'); <%} %>
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
	  <p>N011.C.02.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESI&Oacute;N</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/011/C01'">VOLVER</button> 
	<div align="center" class="title">
		<p >CONSULTAR UBICACION</p>
	</div>
</div>
<div class="content" >
  <form action="" name="form1" onsubmit="return validateSubmit()" method="post" style="margin: 0px 0px 0px 0px">
  <input type="hidden" name="ubi_usullave" id="ubi_usullave" value="<%=ubi_usullave %>" /> 
  <div class=" cuadroblanco" style="height:295px;">
  	<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CLIENTE/PROVEEDOR/DIRECCION</p></h3>
  	   
	<div class="detailcb scroll">
		<div class="divhead"><strong>COD CLIENTE/PROVEEDOR:</strong><input  type="text" style="width:100px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_id%>"></div>
		<div class="divhead"><strong>RUT:</strong><input  type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_rut%>"></div>
		<div class="divhead"><strong>GRUPO:</strong><input type="text" style="width:290px;height:30px ;background:#FFF;" disabled="disabled" value="<%=grupos_nombre%>"></div>
		<div class="divhead"><strong>NOM FANTAS&Iacute;A:</strong><input type="text" style="width:620px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_nombrenof%>"></div>
		<div class="divhead" ><strong>RELACI&Oacute;N:</strong><input type="checkbox" disabled="disabled" <% if(empresas_escliente.equals("1")){%> checked="checked" <% }  %> >ES CLIENTE  <input type="checkbox" disabled="disabled" <% if(empresas_esproveedor.equals("1")){%> checked="checked" <% } %> >ES PROVEEDOR <input type="checkbox" disabled="disabled" <% if(empresas_esprospeccion.equals("1")){%> checked="checked" <% } %> >ES PROSPECCION</div>
		<div class="divhead" style="width: 500px;"><strong>EMP RELACIONADA:</strong>
   		<input type="text" value="<%=empresas_relacionada%>" style="width:45px;height:30px; background:#FFF; " disabled="disabled">   
  		</div>
  
		<div class="divhead"><strong>RAZ&Oacute;N SOCIAL:</strong><input type="text" style="width:620px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_razonsocial%>"></div>
		<div class="divhead"><strong>EST CLIENTE/PROVEEDOR:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=estados_vig_novig_nombre%>"></div>
		<div class="divhead"><strong>COD DIRECCION:</strong><input type="text" style="width:60px;height:30px ;background:#FFF;" disabled="disabled" value="<%=DIRE_ID%>"></div>
		<div class="divhead"><strong>TIPO DIRECCION:</strong><input type="text" style="width:200px;height:30px ;background:#FFF;" disabled="disabled" value="<%=TIDIR_NOMBRE%>">  </div>
		<div class="divhead"><strong>COD POSTAL:</strong><input type="text"   style="width:90px;height:30px ;background:#FFF;" disabled="disabled"  value="<%=DIRE_COD_POSTAL%>" ></div>
		<div class="divhead"><strong>NOMBRE DIRECCION:</strong><input type="text" style="width:290px;height:30px ;background:#FFF;" disabled="disabled"  value="<%=DIRE_NOMBRE%>" ></div>		
		<div class="divhead"><strong>SECTOR:</strong><input type="text" style="width:250px;height:30px ;background:#FFF;" disabled="disabled" value="<%=DIRE_SECTOR%>" ></div>
		<div class="divhead"><strong>DIRECCION:</strong><input type="text"  style="width:680px;height:30px ;background:#FFF;" disabled="disabled" value="<%=DIRE_DIRECCION%>"></div>
		<div class="divhead"><strong>COMUNA:</strong><input type="text"  style="width:256px;height:30px ;background:#FFF;" disabled="disabled" value="<%=COMU_NOMBRE%>"></div>
		<div class="divhead"><strong>REGION:</strong><input  type="text" style="width:520px;height:30px ;background:#FFF;" disabled="disabled" value="<%=REGI_NOMBRE%>"></div>
		<div class="divhead"><strong>CUADRANTE:</strong><input type="text" style="width:356px;height:30px ;background:#FFF;" disabled="disabled" value="<%=CUAD_NOMBRE%>"></div>
		<div class="divhead"><strong>CIUDAD:</strong><input type="text"  style="width:187px;height:30px ;background:#FFF;" disabled="disabled" value="<%=DIRE_CIUDAD%>"></div>
		<div class="divhead"><strong>LAT:</strong><input type="text" id="lat" maxlength="12"  style="width:220px;height:30px ;background:#FFF;" disabled="disabled"  value="<%=dire_lat%>"></div>
		<div class="divhead"><strong>LON:</strong><input type="text" id="lon" maxlength="12"  style="width:220px;height:30px ;background:#FFF;" disabled="disabled"  value="<%=dire_lon%>"></div>
		<div class="divhead"><strong>SLA TEC:</strong><input type="text"  style="width:100px;height:30px ;background:#FFF;"  disabled="disabled" value="<%=dire_nsa_tecnico%>"></div>
		<div class="divhead"><strong>SLA LOG:</strong><input type="text"  style="width:100px;height:30px ;background:#FFF;"  disabled="disabled" value="<%=dire_nsa_logistica%>"></div>
		<div class="divhead"><strong>SLA PV:</strong><input type="text" style="width:100px;height:30px ;background:#FFF;"  disabled="disabled" value="<%=dire_nsa_postventa%>"></div>
		</div>	
	</div> 
	<div class=" cuadroblanco" style="height:200px;margin-top: 2px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DETALLE UBICACION</p></h3>
			<div class="divhead"><strong>COD UBI:</strong><input type="text" style="width:60px;height:30px ;background:#FFF;" readonly="readonly" value="<%=UBI_ID%>"></div>
			<div class="divhead"><strong>TIPO UBI:</strong><input type="radio" <% if(UBI_TIPO.equals("1")){%> checked="checked" <%} %> disabled="disabled" style="height: 30px">BODEGA<input type="radio" <% if(UBI_TIPO.equals("2")){%> checked="checked" <%} %> disabled="disabled" >USUARIO </div>
			<div class="divhead"><strong>USUARIO:</strong><input type="text"  style="width:290px;height:30px;background-color: #FFF" value="<%=UBI_USUARIO %>" readonly="readonly" ></div>
			<div class="divhead"><strong>UBI FIS:</strong><input type="text"  style="width:350px;height:30px;background-color: #FFF"  value="<%=UBI_FISICA %>" readonly="readonly" ></div>		
			<div class="divhead"><strong>UBI FACTURACION:</strong><input type="text"  style="width:350px;height:30px ;background-color: #FFF"  maxlength="30"  value="<%=UBI_DESCRIPCION %>" readonly="readonly" ></div>		
			<div class="divhead ubiusuariodiv"><strong>TIPO TOMAR CONTADOR:</strong>
			<input type="radio" <% if(tpg_id.equals("1")){%> checked="checked" <%} %> disabled="disabled" >PRESENCIAL
			<input type="radio" <% if(tpg_id.equals("2")){%> checked="checked" <%} %> disabled="disabled" style="height: 30px">REMOTO
			<input type="radio" <% if(tpg_id.equals("3")){%> checked="checked" <%} %> disabled="disabled" style="height: 30px">REMOTO EN CLIENTE
			<input type="radio" <% if(tpg_id.equals("4")){%> checked="checked" <%} %> disabled="disabled" style="height: 30px">EJECUTIVO COMERCIAL </div>
			<div class="divhead" ><strong>USA SUBUBICACION:</strong><input type="checkbox" <% if(ubi_usa_sububicacion.equals("1")){%> checked="checked" <%} %>  readonly="readonly" style="height: 30px" ></div>
			<div class="divhead"><strong>ESTADO:</strong><input type="text"  style="width:120px;height:30px ;background-color: #FFF"  maxlength="30"  value="<%=estados_vig_novig_nombre_ubi %>" readonly="readonly" ></div>
			<div class="divhead ubiusuariodiv"><strong>CONFIGURACI&Oacute;N:</strong><input type="text"  style="width:300px;height:30px ;background-color: #FFF"  maxlength="30"  value="<%=conf %>" readonly="readonly" ></div>		
			
	</div> 
	 
	<div class="bgrabar">
	<a  class="btn btn-success various " data-fancybox-type="iframe" href="usullave?block=1">LLAVE</a>
	<a  class="btn btn-success various " data-fancybox-type="iframe" href="vermapa.jsp">VER MAPA</a>
         
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