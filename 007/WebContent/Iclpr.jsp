<%@page import="java.util.ArrayList"%>
<%

String empresas_nombre="";
String estados_vig_novig_id="";
String grupos_id="";
String empresas_esprospeccion="";
String Estado_Clpr_id="";
String empresas_rut="";
String empresas_escliente="";
String empresas_esproveedor="";
String empresas_razonsocial="";
String empresas_nombrenof="";
String empresas_giro="";
String empresas_web="";
String empresas_email="";

if(request.getAttribute("empresas_nombre")!=null && !request.getAttribute("empresas_nombre").equals(""))empresas_nombre =(String) request.getAttribute("empresas_nombre");
if(request.getAttribute("estados_vig_novig_id")!=null && !request.getAttribute("estados_vig_novig_id").equals(""))estados_vig_novig_id=(String) request.getAttribute("estados_vig_novig_id");
if(request.getAttribute("grupos_id")!=null && !request.getAttribute("grupos_id").equals(""))grupos_id=(String)request.getAttribute("grupos_id");
if(request.getAttribute("empresas_esprospeccion")!=null && !request.getAttribute("empresas_esprospeccion").equals(""))empresas_esprospeccion=(String)request.getAttribute("empresas_esprospeccion");	
if(request.getAttribute("Estado_Clpr_id")!=null && !request.getAttribute("Estado_Clpr_id").equals(""))Estado_Clpr_id=(String)request.getAttribute("Estado_Clpr_id");
if(request.getAttribute("empresas_rut")!=null && !request.getAttribute("empresas_rut").equals(""))empresas_rut=(String)request.getAttribute("empresas_rut");
if(request.getAttribute("empresas_escliente")!=null && !request.getAttribute("empresas_escliente").equals(""))empresas_escliente=(String)request.getAttribute("empresas_escliente");
if(request.getAttribute("empresas_esproveedor")!=null && !request.getAttribute("empresas_esproveedor").equals(""))empresas_esproveedor=(String)request.getAttribute("empresas_esproveedor");
if(request.getAttribute("empresas_razonsocial")!=null && !request.getAttribute("empresas_razonsocial").equals(""))empresas_razonsocial=(String)request.getAttribute("empresas_razonsocial");
if(request.getAttribute("empresas_nombrenof")!=null && !request.getAttribute("empresas_nombrenof").equals(""))empresas_nombrenof=(String)request.getAttribute("empresas_nombrenof");
if(request.getAttribute("empresas_giro")!=null && !request.getAttribute("empresas_giro").equals(""))empresas_giro=(String)request.getAttribute("empresas_giro");
if(request.getAttribute("empresas_web")!=null && !request.getAttribute("empresas_web").equals(""))empresas_web=(String)request.getAttribute("empresas_web");
if(request.getAttribute("empresas_email")!=null && !request.getAttribute("empresas_email").equals(""))empresas_email=(String)request.getAttribute("empresas_email");

	String[] ar_estados =(String[]) request.getAttribute("ar_estados");
	String[] ar_estclpr =(String[]) request.getAttribute("ar_estclpr");
	String Usu_nom=(String)request.getAttribute("usuname");	
	String correlativo=(String)request.getAttribute("correlativo");
	
	String[] ar_responsables =(String[]) request.getAttribute("ar_responsables");
	
	
	
%>
<!DOCTYPE html>
<html lang="en"><head>
 <script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/007/lib/jquery.Rut.min.js"></script>

<script type='text/javascript'>
$(function(){
 $('.searchInput').change(function(e) {
        if($('.searchInput:checked').length>0)$('.searchInput2:checked').attr('checked',false);
    });
 $('.searchInput2').change(function(e) {
        if($('.searchInput2:checked').length>0)$('.searchInput:checked').attr('checked',false);
    });
});
</script>
<% 
  if(request.getParameter("Exito")!=null && !request.getParameter("Exito").equals("")){
  		if(request.getParameter("Exito").equals("NOK1")){
  			out.println("<script>alert('EL NOMBRE NOF YA EXISTE')</script>");
			//out.println("<script>window.location = '/001/'</script>");
		} 
		
	}

if(request.getParameter("Exito")!=null && !request.getParameter("Exito").equals("")){
		if(request.getParameter("Exito").equals("NOK2")){
			out.println("<script>alert('LA RAZON SOCIAL YA EXISTE')</script>");
			
		//out.println("<script>window.location = '/001/'</script>");
	} 
	
}

if(request.getParameter("Exito")!=null && !request.getParameter("Exito").equals("")){
	if(request.getParameter("Exito").equals("NOK3")){
		out.println("<script>alert('EL NOMBRE FANTASIA YA EXISTE')</script>");
	//out.println("<script>window.location = '/001/'</script>");
	} 
}
if(request.getParameter("Exito")!=null && !request.getParameter("Exito").equals("")){
	if(request.getParameter("Exito").equals("NOK4")){
		out.println("<script>alert('EL RUT YA EXISTE')</script>");
	//out.println("<script>window.location = '/001/'</script>");
	} 
}
	%>
	<!-- VALIDANDO QUE LOS CAMPOS VALLAN CON ALGO -->
	<script type="text/javascript">
	function validarIngreso(){
		if(document.getElementById('empresas_nombre').value==""){
		alert('DEBE INGRESAR UN NOMBRE DE EMPRESA');
		document.getElementById('empresas_nombre').focus();
		return false;
		}
		
		if(document.getElementById('empresas_razonsocial').value==""){
		alert('DEBE INGRESAR UNA RAZÓN SOCIAL');
		document.getElementById('empresas_razonsocial').focus();
		return false;
		}
		
		if(document.getElementById('empresas_nombrenof').value==""){
			alert('DEBE INGRESAR UN NOMBRE NOF');
			document.getElementById('empresas_nombrenof').focus();
			return false;
		}
		
		if(document.getElementById('empresas_rut').value==""){
			alert('DEBE INGRESAR EL RUT DE LA EMPRESA');
			document.getElementById('empresas_rut').focus();
			return false;
		}
		
		if(document.getElementById('empresas_giro').value==""){
			alert('DEBE INGRESAR EL GIRO DE LA EMPRESA');
			document.getElementById('empresas_giro').focus();
			return false;
		}
		
		if(document.getElementById('empresas_web').value==""){
			alert('DEBE INGRESAR LA WEB DE LA EMPRESA');
			document.getElementById('empresas_web').focus();
			return false;
		}
		
		if(!document.getElementById('empresas_escliente').checked && !document.getElementById('empresas_esproveedor').checked && !document.getElementById('empresas_esprospeccion').checked){
			alert('DEBE SELECCIONAR SI ES CLIENTE, PROVEEDOR O PROSPECCION');
			return false;
		}
		
		}
	</script>
	
	<!-- FIN VALIDANDO QUE LOS CAMPOS VALLAN CON ALGO -->
<script>
$(function(){
	 $('.rut').Rut({
	  on_error: function(e){ alert('Rut incorrecto');$('#empresas_rut').val(''); $('#empresas_rut').focus(); },
	 
	  format_on: 'keyup'
	 });
	});
</script>
	<!-- VALIDANDO QUE EL RUT SEA VALIDO -->

<!--FIN VALIDANDO QUE EL RUT SEA VALIDO -->
    <meta charset="utf-8">
    <title>New Office - Cliente proveedor</title>
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
		
	@media screen and (min-width: 700px) {
.cuadroblanco{
			box-shadow:0px 2px 5px 0px rgba(50, 50, 50, 0.75);
			border: 1px solid black;
			min-height:100%; height:400px;  
		min-width: 350px;
			max-width:1000px;
position: relative;
background:#CCC;
margin: 0 auto;
padding-left: 10px;
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
    <link href="lib/select2/css/select2.min.css" rel="stylesheet" />
	<script src="lib/select2/js/select2.min.js"></script>
    <script type="text/javascript">
	 $( document ).ready(function() {
		 $( "#responsable_id" ).select2();
		 $( "#Estado_Clpr_id" ).select2();
		 $( "#estados_vig_novig_id" ).select2();
		 
		 getGrupos();
		  
	 });
    function getGrupos(){
		$( "#grupos_id" ).empty();
	    $.post( "getGruposAsync", {  })
	    .done(function( data ) {
	    	  
	    	  var grupos_arr = $.parseJSON(data);
        
	    		$.each( grupos_arr, function( key, grupo ) {
        			
        			
        			$('#grupos_id').append($('<option>', {
                        value: grupo.id,
                        text: grupo.nombre
                        }));
        			
        		 
        		});
        		
        		  $('#grupos_id').select2();
    	    	  $('#grupos_id').select2("val","");
	      	
	    });
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
                     <p>N007.I.01.001</p>
                     </div>
                     <form method="get" action="Iclpr" style="margin:0px 0px 0px 0px">                 
                    <button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
                    </form>
                    <button  type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/007/'">VOLVER</button>
                   
                    <div align="center" class="title" >
                    <p >INGRESAR CLIENTE-PROVEEDOR</p>
                    </div>
                   
                    
                    
               
                             
    </div>
         
   <div class="content" >
        
           <div class=" cuadroblanco" >
         
           <br>
         <form action="Iclpr" name="form1" method="post">
          <div class="divhead"><strong >ID:</strong><input type="text" value="<%=correlativo %>" style="width:50px;height:30px; background:#FFF;" disabled="disabled">
                    </div>
      
   
    <div class="divhead"><strong>NOMBRE FANTASIA:</strong><input maxlength="55" autofocus="autofocus" type="text" class="amarillo" style="width:620px;height:30px ;background:#FF3;" name="empresas_nombre" id="empresas_nombre" value="<%=empresas_nombre %>" required ><span class="cabecera" style="color:#F00">*</span></div>
    <div class="divhead"><strong>RAZÓN SOCIAL:</strong><input maxlength="55" type="text" class="amarillo" style="width:620px;height:30px ;background:#FF3;" name="empresas_razonsocial" id="empresas_razonsocial" value="<%=empresas_razonsocial %>" required ><span class="cabecera" style="color:#F00">*</span></div>
    <div class="divhead"><strong>NOMBRE NOF:</strong><input maxlength="35" type="text" class="amarillo" style="width:400px;height:30px ;background:#FF3;" name="empresas_nombrenof" id="empresas_nombrenof" value="<%=empresas_nombrenof %>" required ><span class="cabecera" style="color:#F00">*</span></div>        
    <div class="divhead"><strong>RUT:</strong><input type="text" class="amarillo rut" style="width:145px;height:30px ;background:#FF3;" name="empresas_rut" id="empresas_rut" maxlength="12" value="<%=empresas_rut %>" required><span class="cabecera" style="color:#F00">*</span></div> 
    <div class="divhead"><strong>GIRO:</strong><input maxlength="55" type="text" class="amarillo" style="width:620px;height:30px ;background:#FF3;" name="empresas_giro" id="empresas_giro" value="<%=empresas_giro %>" required ><span class="cabecera" style="color:#F00">*</span></div>
    <div class="divhead"><strong>EMAIL:</strong><input maxlength="55" type="text" class="amarillo" style="width:620px;height:30px ;background:#FF3;" name="empresas_email" id="empresas_email" value="<%=empresas_email %>" required ><span class="cabecera" style="color:#F00">*</span></div>
    <div class="divhead"><strong>WEB:</strong><input maxlength="40" type="text" class="amarillo" style="width:455px;height:30px ;background:#FF3;" name="empresas_web" id="empresas_web" value="<%=empresas_web %>" required ><span class="cabecera" style="color:#F00">*</span></div>
                    
    
         <div class="divhead">           
     <table>
     	<tr>
     		<td><strong>CLIENTE:</strong>
                   </td><td><input type="checkbox" value="1" name="empresas_escliente" id="empresas_escliente" <%if(empresas_escliente.equals("1")) out.print("checked");%> class="searchInput" ></td>
     		<td>&nbsp;&nbsp;<strong>PROSPECCION:</strong>
                   </td><td><input type="checkbox" name="empresas_esprospeccion" id="empresas_esprospeccion" <%if(empresas_esprospeccion.equals("1")) out.print("checked");%> value="1" class="searchInput2"></td>
     	
     	</tr>
     	<tr>
     		<td><strong>PROVEEDOR:</strong>
                   </td><td><input type="checkbox" value="1"  name="empresas_esproveedor" id="empresas_esproveedor" <%if(empresas_esproveedor.equals("1")) out.print("checked");%> class="searchInput"></td>
     		<td></td>
     		<td></td>
     	</tr>
     </table>
     </div>               
   
    
   
   <div class="divhead"><strong>GRUPO:</strong><select name="grupos_id" id="grupos_id" style="margin:0px 0px 0px 0px;width: 320px; margin-bottom: 9px">
    </select><span class="cabecera" style="color:#F00">*</span></div>
      <div class="divhead" style="vertical-align:middle;padding: 8px 0 8px 0;" ><strong>EMP RELACIONADA:</strong><input type="checkbox" value="1" name="empresas_relacionada" id="empresas_relacionada"  class="searchInput" ><span class="cabecera" style="color:#F00">*</span></div>
    
   <div class="divhead"><strong>ESTADO CLIENTE-PROVEEDOR:</strong><select name="Estado_Clpr_id" id="Estado_Clpr_id" style="margin:0px 0px 0px 0px;width: 370px; margin-bottom: 9px">
    <% for(int i =0; i<ar_estclpr.length; i++){
       		String[] estclpr = ar_estclpr[i].split("/");
    
    %>
    
      <option value="<%=estclpr[0]%>" <% if(Estado_Clpr_id.equals(estclpr[0]))%><%="selected"%>><%=estclpr[1] %></option>
    <% } %>
    </select><span class="cabecera" style="color:#F00">*</span></div>
    <div class="divhead"><strong>ESTADO:</strong><select name="estados_vig_novig_id" id="estados_vig_novig_id"  style="margin:0px 0px 0px 0px;width: 167px; margin-bottom: 9px">
    <% for(int i =0; i<ar_estados.length; i++){
       		String[] estados_vig_novig = ar_estados[i].split("/");
    
    %>
    
      <option value="<%=estados_vig_novig[0]%>" <% if(estados_vig_novig_id.equals(estados_vig_novig[0]))%><%="selected"%>><%=estados_vig_novig[1] %></option>
    <% } %>
    </select><span class="cabecera" style="color:#F00"> *</span></div>
     <div class="divhead"><strong>RESPONSABLE CUENTA:</strong><select name="responsable_id" id="responsable_id" style="margin:0px 0px 0px 0px;width: 500px; margin-bottom: 9px">
    <% for(int i =0; i<ar_responsables.length; i++){
       		String[] respon = ar_responsables[i].split("/");
    
    %>
    
      <option value="<%=respon[0]%>" ><%=respon[1] %></option>
    <% } %>
    </select><span class="cabecera" style="color:#F00"> *</span></div>
            <div class="bgrabar">
           <button type="submit" name="grabar" id="grabar" onclick="return validarIngreso()" class="btn btn-success" >GRABAR</button>
          </div> 
</form>
 
 
       </div> 
  <!-- Table -->

         
        
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
