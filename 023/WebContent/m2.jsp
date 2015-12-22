<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
<%

String modname=(String)request.getAttribute("modname");

String Usu_nom=(String)request.getAttribute("usuname");
String prod_id=(String)request.getAttribute("prod_id");
String prod_pn_tli_codfab=(String)request.getAttribute("prod_pn_tli_codfab");
String prod_cod_bar_fab=(String)request.getAttribute("prod_cod_bar_fab");
String prod_vida_util_contable=(String)request.getAttribute("prod_vida_util_contable");
String prod_desc_corto=(String)request.getAttribute("prod_desc_corto");

String prod_vida_util_ano=(String)request.getAttribute("prod_vida_util_ano");

String prod_desc_largo=(String)request.getAttribute("prod_desc_largo");
String prod_cm_contable=(String)request.getAttribute("prod_cm_contable");


String prod_vida_util_imp=(String)request.getAttribute("prod_vida_util_imp");

String prod_observacion=(String)request.getAttribute("prod_observacion");
String marca_nombre=(String)request.getAttribute("marca_nombre");
String marca_id=(String)request.getAttribute("marca_id");

String func_nombre=(String)request.getAttribute("func_nombre");
String func_id=(String)request.getAttribute("func_id");
String estados_vig_novig_id=(String)request.getAttribute("estados_vig_novig_id");
String est_prod_id=(String)request.getAttribute("est_prod_id");

String prod_tipo=(String)request.getAttribute("prod_tipo");
String prod_es_color=(String)request.getAttribute("prod_es_color");


String estados_vig_novig_nombre=(String)request.getAttribute("estados_vig_novig_nombre");
ArrayList<String> marcas =(ArrayList<String>) request.getAttribute("marcas");
ArrayList<String> funcionalidades =(ArrayList<String>) request.getAttribute("funcionalidades");
String[] ar_estados =(String[]) request.getAttribute("ar_estados");

ArrayList<String> estProd =(ArrayList<String>) request.getAttribute("estProd");

	String user_crea=(String)request.getAttribute("user_crea"); 
	String user_mod=(String)request.getAttribute("user_mod"); 
	String fec_crea=(String)request.getAttribute("fec_crea"); 
	String fec_mod=(String)request.getAttribute("fec_mod");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
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
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" type="text/css" href="stylesheets/inputs.css">

    <script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>
	<link href="//cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/css/select2.min.css" rel="stylesheet" />
	<script src="//cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/js/select2.min.js"></script>

    <!-- Demo page code -->

    <style type="text/css">
	     form{
	    	margin-top:10px;
	    	margin-bottom:0px;
	    	
	    	height: 100%;
	    }
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
         .divhead{
		float: left;
		font-size: 20px; 
		margin-right: 15px;
		margin-bottom: 2px;
		height: 36px;
		text-align: left;
		text-transform:uppercase;
		
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
			margin-top:10px;
			font-size:20px;
			}
		select{
			width:225px;
				}
		
	@media screen and (min-width: 700px) {
		.cuadroblanco{
			box-shadow:0px 2px 5px 0px rgba(50, 50, 50, 0.75);
			border: 1px solid black;
			min-height:100%; 
			height:510px;
			min-width: 1000px;
			max-width:1000px;
			position: relative;
			background:#ccc;
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
 
 <script type="text/javascript">
 
 $( document ).ready(function() {
	 $('#marca_id').select2();
	 $('#func_id').select2();
	 
	 $('#estados_vig_novig_id').select2();
	 $('#est_prod_id').select2();
	 
	 
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
 
 function validateSubmit(){
	 	
	 	
	 	$("#form1").attr("action", "");
	 	
	 	$("#empresa_emisora_nombre").val($("#fac_servim_emisor").find('option:selected').text());
	 	$("#cont_nombre").val($("#cont_id").find('option:selected').text());
			
	 	
			
	 	var validate= true;
	 	var msg="ERRORES: \n";
	 	     
	 
	 	if(document.getElementById('prod_vida_util_imp').value==""){
	 		msg+="DEBE INGRESAR LA VIDA UTIL\n";
	 		validate=false;
	 	}
	 	if(document.getElementById('prod_vida_util_ano').value==""){
	 		msg+="DEBE INGRESAR LA VIDA UTIL\n";
	 		validate=false;
	 	}
	 	if(document.getElementById('prod_vida_util_contable').value==""){
	 		msg+="DEBE INGRESAR LA VIDA UTIL\n";
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
                     <p>N023.M.02.001</p>
                     </div>
                    <form method="get" action="" >                 
                    <button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
                    </form>
                    <button type="button" onclick="location='/023/m1'" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">VOLVER</button>
                   
                    <div align="center" class="title" >
                    <p >MODIFICAR <%=modname %></p>
                    </div>
                    
               
                             
    </div>
        
  <div class="content">
        
         <div class="cuadroblanco">
         <form action="" name="form1" method="post">
         <input type="hidden" name="id_p" value="<%=prod_id%>" /> 
            
  	<div class="divhead"><strong>CORRELATIVO:</strong><input type="text" value="<%=prod_id %>" style="width:80px;height:30px; background:#FFF; color: #000" disabled="disabled"></div>
  
 	<div class="divhead"><strong>PN/TLI/COD FABRICANTE:</strong><input maxlength="20" autofocus="autofocus" type="text" class="amarillo" style="width:220px;height:30px ;background:#FF3;border: 1px solid #FFFF00;" name="prod_pn_tli_codfab" id="prod_pn_tli_codfab" value="<%=prod_pn_tli_codfab%>" required ><span class="cabecera" style="color:#F00">*</span></div>
    <div class="divhead"><strong>COD BARRAS FABRICANTE:</strong><input maxlength="20" autofocus="autofocus" type="text" class="amarillo" style="width:220px;height:30px ;background:#FF3;border: 1px solid #FFFF00;" name="prod_cod_bar_fab" id="prod_cod_bar_fab" value="<%=prod_cod_bar_fab%>" required ><span class="cabecera" style="color:#F00">*</span></div> 
 	 <div class="divhead"><strong>MARCA:</strong><select name="marca_id" id="marca_id" style="margin:0px 0px 0px 0px;width: 280px">
    <% for(int i =0; i<marcas.size(); i++){
       		String[] marca = marcas.get(i).split(";;");
    %>
    
      <option value="<%=marca[0]%>" <% if(marca_id.equals(marca[0])){%> selected="selected" <%} %>  ><%=marca[1] %></option>
    <% } %>
    </select><span class="cabecera" style="color:#F00"> *</span></div>
    
    <div class="divhead"><strong>FUNCIONALIDAD:</strong><select name="func_id" id="func_id" style="margin:0px 0px 0px 0px;width: 360px">
    <% for(int i =0; i<funcionalidades.size(); i++){
       		String[] func = funcionalidades.get(i).split(";;");
    
    %>
      <option value="<%=func[0]%>" <% if(func_id.equals(func[0])){%> selected="selected" <%} %>><%=func[1] %></option>
    <% } %>
    </select><span class="cabecera" style="color:#F00"> *</span></div>
    
    <div class="divhead"><strong>DESCRIPTOR CORTO:</strong><input maxlength="50" autofocus="autofocus" type="text" class="amarillo" style="width:520px;height:30px ;background:#FF3;border: 1px solid #FFFF00;" name="prod_desc_corto" id="prod_desc_corto" value="<%=prod_desc_corto %>" required > <span class="cabecera" style="color:#F00">*</span></div> 
 	<div class="divhead"><strong>DESCRIPTOR LARGO:</strong><input maxlength="50" autofocus="autofocus" type="text" class="amarillo" style="width:520px;height:30px ;background:#FF3;border: 1px solid #FFFF00;" name="prod_desc_largo" id="prod_desc_largo" value="<%=prod_desc_largo %>" required > <span class="cabecera" style="color:#F00">*</span></div> 
 	
 	<div class="divhead"><strong>OBSERVACION:</strong><input maxlength="50" autofocus="autofocus" type="text" class="amarillo" style="width:520px;height:30px ;background:#FF3;border: 1px solid #FFFF00;" name="prod_observacion" id="prod_observacion" value="<%=prod_observacion %>" required > <span class="cabecera" style="color:#F00">*</span></div> 
 	
    <div class="divhead"><strong>VIDA UTIL (IMPRESIONES):</strong><input onkeydown="return validateNum(event)" maxlength="7" autofocus="autofocus" type="text" class="amarillo" style="width:90px;height:30px ;background:#FF3;border: 1px solid #FFFF00;" name="prod_vida_util_imp" id="prod_vida_util_imp" value="<%=prod_vida_util_imp %>" required >
                    <span class="cabecera" style="color:#F00">*</span></div> 
 	<div class="divhead"><strong>VIDA UTIL (AÑOS):</strong><input onkeydown="return validateNum(event)" maxlength="3" autofocus="autofocus" type="text" class="amarillo" style="width:50px;height:30px ;background:#FF3;border: 1px solid #FFFF00;" name="prod_vida_util_ano" id="prod_vida_util_ano" value="<%=prod_vida_util_ano %>" required >
                    <span class="cabecera" style="color:#F00">*</span></div> 
 	<div class="divhead"><strong>VIDA UTIL CONTABLE (MESES):</strong><input onkeydown="return validateNum(event)" maxlength="3" autofocus="autofocus" type="text" class="amarillo" style="width:50px;height:30px ;background:#FF3;border: 1px solid #FFFF00;" name="prod_vida_util_contable" id="prod_vida_util_contable" value="<%=prod_vida_util_contable %>" required >
                    <span class="cabecera" style="color:#F00">*</span></div> 
 	
 	<div class="divhead"><strong>C. MONETARIA CONTABLE:</strong><span style="height: 30px"><input type="radio" name="prod_cm_contable" value="1" <% if(prod_cm_contable.equals("1")){ %> checked="checked" <%} %> checked="checked" >SI<input type="radio" name="prod_cm_contable" value="2" <% if(prod_cm_contable.equals("2")){ %> checked="checked" <%} %> >NO </span></div>
	
 	<div class="divhead"><strong>ESTADO:</strong><select name="estados_vig_novig_id" id="estados_vig_novig_id" style="margin:0px 0px 0px 0px;width: 167px">
    <% for(int i =0; i<ar_estados.length; i++){
       		String[] estados_vig_novig = ar_estados[i].split("/");
    
    %>
    
      <option value="<%=estados_vig_novig[0]%>"<% if(estados_vig_novig_id.equals(estados_vig_novig[0]))%><%="selected"%>><%=estados_vig_novig[1] %></option>
    <% } %>
    </select><span class="cabecera" style="color:#F00"> *</span></div>
    
    <div class="divhead"><strong>ESTADO PRODUCTO:</strong><select name="est_prod_id" id="est_prod_id" style="margin:0px 0px 0px 0px;width: 280px">
    <% for(int i =0; i<estProd.size(); i++){
       		String[] estpr = estProd.get(i).split(";;");
    %>
    
      <option value="<%=estpr[0]%>" <% if(est_prod_id.equals(estpr[0])){%> selected="selected" <%} %>  ><%=estpr[1] %></option>
    <% } %>
    </select><span class="cabecera" style="color:#F00"> *</span></div>
    <div class="divhead"><span style="height: 30px"><input type="checkbox" name="prod_es_color" value="1" <% if(prod_es_color.equals("1")){ %> checked="checked" <%} %> >COLOR</span></div>
    
    <div class="divhead"><span style="height: 30px"><input type="radio" name="prod_tipo" value="1" <% if(prod_tipo.equals("1")){ %> checked="checked" <%} %> >PRODUCTO PRINCIPAL (Equipo, Maquina)<input type="radio" name="prod_tipo" value="2" <% if(prod_tipo.equals("2")){ %> checked="checked" <%} %> >PRODUCTO SECUNDARIO (Suministro, Opcional, Repuesto)<br><input type="radio" name="prod_tipo" value="3" <% if(prod_tipo.equals("3")){ %> checked="checked" <%} %>  >SERVICIO</span></div>
        
        
	
 	<div class="divhead"><strong>Fecha creación:</strong><input type="text" value="<%=fec_crea%>" style="width:225px;height:30px; background:#FFF;color: #000 " disabled="disabled"></div>
	<div class="divhead"><strong>Usuario creador:</strong><input type="text" value="<%=user_crea%>" style="width:400px;height:30px; background:#FFF;color: #000 " disabled="disabled"></div>
	<div class="divhead"><strong>Fecha última mod:</strong><input type="text" value="<%=fec_mod%>" style="width:225px;height:30px; background:#FFF;color: #000 " disabled="disabled"></div>
	<div class="divhead"><strong>Usuario última mod:</strong><input type="text" value="<%=user_mod%>" style="width:400px;height:30px; background:#FFF;color: #000 " disabled="disabled"></div>

          <div class="bgrabar" >
           <button type="submit" onclick="return validateSubmit()" name="grabar" id="grabar" class="btn btn-success" >GRABAR</button>
           </div> 
           
           </form>

 
         </div>
        
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
