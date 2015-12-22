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
    
    String id_dir=(String)request.getAttribute("id_dir");
    String ar_tdir[]=(String[])request.getAttribute("ar_tdir");
    String ar_comus[]=(String[])request.getAttribute("ar_comus");
    String ar_cuas[]=(String[])request.getAttribute("ar_cuas");
   
    String empresas_relacionada=(String)request.getAttribute("empresas_relacionada");
	
    
    %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>New Office - DIRECCION</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
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
		height:188px;  
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


var ar_comus =new Array();

<%  for(int i =0; i<ar_comus.length; i++){
    %>
    ar_comus[<%=i%>]="<%=ar_comus[i]%>";
    
    <% } %>
    

    $( document ).ready(function() {
                    // Handler for .ready() called.
                    
                    $('#comuna').on('change', function() {
                                        var com_seleccionada= $(this).val();
                                		    
                                        if(com_seleccionada!=''){
                                           
                                            for(var ii=0; ii< ar_comus.length;ii++){
                                            
                                              var data_reg =ar_comus[ii].split("/");
                                              if(data_reg[0]==com_seleccionada){
                                                $('#region').val(data_reg[2]);
                                          		break;
                                              
                                              }
                                            
                                            }
                                        }
                                            
                    });
                    $('#comuna').change();
                
    });

    
    function validateSubmit(){
    	
    	var validate= true;
    	var msg="ERRORES: \n";
    	
    	if(document.getElementById('nom_dir').value==""){
    		msg+="DEBE INGRESAR EL NOMBRE A LA DIRECCION\n";
    		validate=false;
    	}
    	if(document.getElementById('sector').value==""){
    		msg+="DEBE INGRESAR EL SECTOR A LA DIRECCION\n";
    		validate=false;
    	}
    	if(document.getElementById('dir').value==""){
    		msg+="DEBE INGRESAR LA DIRECCION\n";
    		validate=false;
    	}
    	if(document.getElementById('ciudad').value==""){
    		msg+="DEBE INGRESAR LA CIUDAD DE LA DIRECCION\n";
    		validate=false;
    	}
    	
    	
    	
    	if(document.getElementById('cod_postal').value==""){
    		msg+="DEBE INGRESAR COD POSTAL DE LA DIRECCION\n";
    		validate=false;
    	}
    	
    	if(document.getElementById('lat').value==""){
    		msg+="DEBE INGRESAR LATITUD DE LA DIRECCION\n";
    		validate=false;
    	}
    	if(document.getElementById('lon').value==""){
    		msg+="DEBE INGRESAR LONGITUD DE LA DIRECCION\n";
    		validate=false;
    	}
    	
    	if(isNaN(Number(document.getElementById('lat').value))){
    		msg+="FORMATO LAT INCORRECTO\n";
    		validate=false;
    	}

    	if(isNaN(Number(document.getElementById('lon').value))){
    		msg+="FORMATO LON INCORRECTO\n";
    		validate=false;
    	}
    	if(document.getElementById('lat').value!="" && Number(document.getElementById('lat').value)>-12 || Number(document.getElementById('lat').value)<-69){
    		msg+="RANGOS LATITUD INCORRECTOS\n";
    		validate=false;
    	}
    	
    	if(document.getElementById('lon').value!="" && Number(document.getElementById('lon').value)>-40 || Number(document.getElementById('lon').value)<-90){
    		msg+="RANGOS LONGITUD INCORRECTOS\n";
    		validate=false;
    	}
    	
	//si la empresa es cliente entonces dejamlos los sla obligatorios >0 
    	
    	<% if(empresas_escliente.equals("1")){%>
    	if(document.getElementById('nsa_tec').value=="" || Number(document.getElementById('nsa_tec').value)<1){
    		msg+="SLA TECNICO DEBE SER MAYOR A 0\n";
    		validate=false;
    	}
    	if(document.getElementById('nsa_log').value=="" || Number(document.getElementById('nsa_log').value)<1){
    		msg+="SLA LOGISTICA DEBE SER MAYOR A 0\n";
    		validate=false;
    	}
    	if(document.getElementById('nsa_pv').value=="" || Number(document.getElementById('nsa_pv').value)<1){
    		msg+="SLA POSTVENTA DEBE SER MAYOR A 0\n";
    		validate=false;
    	}
    	
    	<% } %>
    	
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
		//alert(event.keyCode);
        if (event.keyCode == 8 || event.keyCode == 46
         || event.keyCode == 37 || event.keyCode == 39 || event.keyCode == 9 || event.keyCode == 190 || (key >= 96 && key <= 105)) {
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
	  <p>N010.I.02.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESI&Oacute;N</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/010/idir1'">VOLVER</button> 
	<div align="center" class="title">
		<p >INGRESAR DIRECCION</p>
	</div>
</div>
<div class="content" >
  <form action="" name="form1" onsubmit="return validateSubmit()" method="post" style="margin: 0px 0px 0px 0px">
  <div class=" cuadroblanco" style="height:200px;">
  	<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CLIENTE/PROVEEDOR</p></h3>
  	   
		
		<div class="divhead"><strong>COD CLIENTE/PROVEEDOR:</strong><input  type="text" style="width:100px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_id%>"></div>
		<div class="divhead"><strong>RUT:</strong><input  type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_rut%>"></div>
		<div class="divhead"><strong>GRUPO:</strong><input type="text" style="width:290px;height:30px ;background:#FFF;" disabled="disabled" value="<%=grupos_nombre%>"></div>
		<div class="divhead"><strong>NOM FANTAS&Iacute;A:</strong><input type="text" style="width:620px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_nombrenof%>"></div>
		<div class="divhead" style="padding-top:10px;"><strong>RELACI&Oacute;N:</strong><input type="checkbox" disabled="disabled" <% if(empresas_escliente.equals("1")){%> checked="checked" <% }  %> >ES CLIENTE  <input type="checkbox" disabled="disabled" <% if(empresas_esproveedor.equals("1")){%> checked="checked" <% } %> >ES PROVEEDOR <input type="checkbox" disabled="disabled" <% if(empresas_esprospeccion.equals("1")){%> checked="checked" <% } %> >ES PROSPECCION</div>
		 <div class="divhead" style="width: 500px;"><strong>EMP RELACIONADA:</strong>
   <input type="text" value="<%=empresas_relacionada%>" style="width:45px;height:30px; background:#FFF; " disabled="disabled">   
  </div>
  
		<div class="divhead"><strong>RAZ&Oacute;N SOCIAL:</strong><input type="text" style="width:620px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_razonsocial%>"></div>
		<div class="divhead"><strong>EST CLIENTE/PROVEEDOR:</strong><input type="text" style="width:150px;height:30px ;background:#FFF;" disabled="disabled" value="<%=estados_vig_novig_nombre%>"></div>
	</div>
	
	<div class=" cuadroblanco" style="height:230px;margin-top: 2px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DETALLE DIRECCION CLIENTE/PROVEEDOR</p></h3>
		<div class="detailcb scroll">	
			<div class="divhead"><strong>COD DIRECCION:</strong><input type="text" style="width:60px;height:30px ;background:#FFF;" disabled="disabled" value="<%=id_dir%>"></div>
			<div class="divhead"><strong>TIPO DIRECCION:</strong><select name="t_dir">
			<%        
				for(int i =0; i<ar_tdir.length; i++){
	       	 	String[] DireDato = ar_tdir[i].split("/");
	       	 %>
	       	 	<option value="<%=DireDato[0]%>"><%=DireDato[1]%></option>
	       	 <% } %>
			</select></div>
			<div class="divhead"><strong>COD POSTAL:</strong><input type="text" onkeydown="return validateNum(event)" maxlength="7" name="cod_postal" id="cod_postal"  style="width:90px;height:30px ;" class="amarillo"  value="" required="required" ><span class="cabecera" style="color:#F00">*</span></div>
			<div class="divhead"><strong>NOMBRE DIRECCION:</strong><input type="text" name="nom_dir" id="nom_dir" style="width:290px;height:30px ;" class="amarillo" maxlength="25"  value="" required="required" ><span class="cabecera" style="color:#F00">*</span></div>		
			<div class="divhead"><strong>SECTOR:</strong><input type="text" name="sector" id="sector" style="width:250px;height:30px ;" class="amarillo" value="" required="required"><span class="cabecera" style="color:#F00">*</span></div>
			<div class="divhead"><strong>DIRECCION:</strong><input type="text" maxlength="60" name="dir"  id="dir" style="width:680px;height:30px ;" class="amarillo"  value="" required="required"><span class="cabecera" style="color:#F00">*</span></div>
			<div class="divhead"><strong>COMUNA:</strong><select id="comuna" name="comuna">
			<%        
				for(int i =0; i<ar_comus.length; i++){
	       	 	String[] DireDato = ar_comus[i].split("/");
	       	 %>
	       	 	<option value="<%=DireDato[0]%>"><%=DireDato[1]%></option>
	       	 <% } %>
			</select></div>
			<div class="divhead"><strong>REGION:</strong><input  type="text" style="width:520px;height:30px ;background:#FFF;" disabled="disabled" id="region"></div>
			<div class="divhead"><strong>CUADRANTE:</strong><select name="cua">
			<%        
				for(int i =0; i<ar_cuas.length; i++){
	       	 	String[] DireDato = ar_cuas[i].split("/");
	       	 %>
	       	 	<option value="<%=DireDato[0]%>"><%=DireDato[1]%></option>
	       	 <% } %>
			</select></div>
			<div class="divhead"><strong>CIUDAD:</strong><input type="text" name="ciudad" id="ciudad" style="width:187px;height:30px ;" class="amarillo" value="" required="required"><span class="cabecera" style="color:#F00">*</span></div>
			<div class="divhead"><strong>LAT:</strong><input type="text" maxlength="18" name="lat" id="lat" style="width:220px;height:30px ;" class="amarillo" value="" required="required"><span class="cabecera" style="color:#F00">*</span></div>
			<div class="divhead"><strong>LON:</strong><input type="text" maxlength="18" name="lon" id="lon" style="width:220px;height:30px ;" class="amarillo" value="" required="required"><span class="cabecera" style="color:#F00">*</span></div>
			
			<div class="divhead"><strong>SLA TEC:</strong><input type="text" onkeydown="return validateNum(event)" maxlength="7" name="nsa_tec" id="nsa_tec" style="width:100px;height:30px ;" class="amarillo" value="" required="required"><span class="cabecera" style="color:#F00">*</span></div>
			<div class="divhead"><strong>SLA LOG:</strong><input type="text" onkeydown="return validateNum(event)" maxlength="7" name="nsa_log" id="nsa_log" style="width:100px;height:30px ;" class="amarillo" value="" required="required"><span class="cabecera" style="color:#F00">*</span></div>
			<div class="divhead"><strong>SLA PV:</strong><input type="text" onkeydown="return validateNum(event)" maxlength="7" name="nsa_pv" id="nsa_pv" style="width:100px;height:30px ;" class="amarillo" value="" required="required"><span class="cabecera" style="color:#F00">*</span></div>
			<div class="divhead"><strong>USO DIRECCION:</strong>DESPACHO<input type="checkbox" name="use_dir_despacho" id="use_dir_despacho" value="1" > FACTURACION<input type="checkbox" name="use_dir_fac" id="use_dir_fac" value="1" > COBRANZA<input type="checkbox" name="use_dir_cobranza" id="use_dir_cobranza" value="1" > CORRESPONDENCIA<input type="checkbox" name="use_dir_corres" id="use_dir_corres" value="1" > OTRO FIN<input type="checkbox" name="use_dir_otrofin" id="use_dir_otrofin" value="1" ></div>
		</div>
	</div> 
	 
	<div class="bgrabar">
	<a  class="btn btn-success various " data-fancybox-type="iframe" href="vermapa.jsp">VER MAPA</a>
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