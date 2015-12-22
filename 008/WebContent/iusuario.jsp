<%@page import="java.util.ArrayList"%>
<%
String Usuarios_nombre_rs="";
String Usuarios_ape_p="";
String Usuarios_ape_m="";
String Usuarios_login="";
String Usuarios_pass="";
String Usuarios_email="";
String Usuarios_telefono="";
String tipo_usu_id="";
String perfilusu_id="";
String estados_vig_novig_id="";
String empresas_id="";


if(request.getAttribute("Usuarios_nombre_rs")!=null && !request.getAttribute("Usuarios_nombre_rs").equals(""))Usuarios_nombre_rs =(String) request.getAttribute("Usuarios_nombre_rs");
if(request.getAttribute("Usuarios_ape_p")!=null && !request.getAttribute("Usuarios_ape_p").equals(""))Usuarios_ape_p=(String) request.getAttribute("Usuarios_ape_p");
if(request.getAttribute("Usuarios_ape_m")!=null && !request.getAttribute("Usuarios_ape_m").equals(""))Usuarios_ape_m=(String)request.getAttribute("Usuarios_ape_m");
if(request.getAttribute("Usuarios_login")!=null && !request.getAttribute("Usuarios_login").equals(""))Usuarios_login=(String)request.getAttribute("Usuarios_login");	
if(request.getAttribute("Usuarios_pass")!=null && !request.getAttribute("Usuarios_pass").equals(""))Usuarios_pass=(String)request.getAttribute("Usuarios_pass");
if(request.getAttribute("Usuarios_email")!=null && !request.getAttribute("Usuarios_email").equals(""))Usuarios_email=(String)request.getAttribute("Usuarios_email");
if(request.getAttribute("Usuarios_telefono")!=null && !request.getAttribute("Usuarios_telefono").equals(""))Usuarios_telefono=(String)request.getAttribute("Usuarios_telefono");
if(request.getAttribute("tipo_usu_id")!=null && !request.getAttribute("tipo_usu_id").equals(""))tipo_usu_id=(String)request.getAttribute("tipo_usu_id");
if(request.getAttribute("perfilusu_id")!=null && !request.getAttribute("perfilusu_id").equals(""))perfilusu_id=(String)request.getAttribute("perfilusu_id");
if(request.getAttribute("estados_vig_novig_id")!=null && !request.getAttribute("estados_vig_novig_id").equals(""))estados_vig_novig_id=(String)request.getAttribute("estados_vig_novig_id");
if(request.getAttribute("empresas_id")!=null && !request.getAttribute("empresas_id").equals(""))empresas_id=(String)request.getAttribute("empresas_id");	
	
	String[] ar_miusuemp= (String[]) request.getAttribute("ar_miusuemp");
	String[] ar_empresas =(String[]) request.getAttribute("ar_empresas");
	String[] ar_perfilusu =(String[]) request.getAttribute("ar_perfilusu");
	String[] ar_tipo_usu =(String[]) request.getAttribute("ar_tipo_usu");
	String Usu_nom=(String)request.getAttribute("usuname");
	String correlativo=(String)request.getAttribute("correlativo");
	
%>
<!DOCTYPE html>
<html lang="en"><head>

<!-- VALIDANDO QUE LOS CAMPOS VALLAN CON ALGO -->
	<script type="text/javascript">
	function validarIngreso(){
		if(document.getElementById('Usuarios_nombre').value==""){
		alert('DEBE INGRESAR UN NOMBRE');
		document.getElementById('Usuarios_nombre').focus();
		return false;
		}
		
		if(document.getElementById('Usuarios_ape_p').value==""){
		alert('DEBE INGRESAR APELLIDO PATERNO');
		document.getElementById('Usuarios_ape_p').focus();
		return false;
		}
		
		if(document.getElementById('Usuarios_ape_m').value==""){
			alert('DEBE INGRESAR APELLIDO MATERNO');
			document.getElementById('Usuarios_ape_m').focus();
			return false;
		}
		
		if(document.getElementById('Usuarios_login').value==""){
			alert('DEBE INGRESAR EL NOMBRE DE USUARIO');
			document.getElementById('Usuarios_login').focus();
			return false;
		}
		
		if(document.getElementById('Usuarios_pass').value==""){
			alert('DEBE INGRESAR UNA CONTRASEÑA');
			document.getElementById('Usuarios_pass').focus();
			return false;
		}
		
		if(document.getElementById('Usuarios_email').value==""){
			alert('DEBE INGRESAR UN EMAIL');
			document.getElementById('Usuarios_email').focus();
			return false;
		}
		
		if(document.getElementById('Usuarios_telefono').value==""){
			alert('DEBE INGRESAR UN TELEFONO');
			document.getElementById('Usuarios_telefono').focus();
			return false;
		}
		
		}
	</script>
	
	<!-- FIN VALIDANDO QUE LOS CAMPOS VALLAN CON ALGO -->

 <script type="text/javascript">
	    function confpass(){
	    	var pass=document.getElementById("Usuarios_pass").value;
	    	
	    	if(pass.length <6 ||  pass.length >8){
	    		document.getElementById("Usuarios_pass").value="";
	    		alert("LA CONTRASE\u00d1A DEBE TENER UN LARGO ENTRE 6 Y 8 CARACTERES.");
	    		return false;
	    		
	    	}
	    	return true;
	    }
</script>
<% 
  if(request.getParameter("Exito")!=null && !request.getParameter("Exito").equals("")){
  		if(request.getParameter("Exito").equals("NOK1")){
			out.println("<script>alert('EL NOMBRE DE USUARIO YA EXISTE')</script>");
			//out.println("<script>window.location = '/001/'</script>");
		} 
		
	}
if(request.getParameter("Exito")!=null && !request.getParameter("Exito").equals("")){
		if(request.getParameter("Exito").equals("NOK2")){
		//out.println("<script>alert('EL NOMBRE : "+Usuarios_nombre_rs+" "+Usuarios_ape_p+" "+Usuarios_ape_m+" YA EXISTE')</script>");
		out.println("<script>alert('EL NOMBRE Y APELLIDOS YA EXISTE')</script>");
		//out.println("<script>window.location = '/001/'</script>");
	} 
	
}
	%>
<script type="text/javascript" src="lib/jquery-1.7.2.min.js"></script>
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
    <meta charset="utf-8">
    <title>New Office - Usuario</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/butons.css">
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">
       <link rel="stylesheet" type="text/css" href="stylesheets/inputs.css">

    <script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>

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
		tbody {
	  
	    height: 125px;
	    overflow-y: scroll;
	}
	thead, tbody {
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
			min-height:100%; 
			height:230px;  
		min-width: 350px;
			max-width:1000px;
position: relative;
background:#ccc;
margin: 0 auto;
	padding-left: 10px;
	padding-top: 5px;
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
                     <p>N008.I.01.001</p>
                     </div>
                     <form method="get" action="Iusuario" style="margin:0px 0px 0px 0px">                 
                    <button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
                    </form>
                    <button  type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/008/'">VOLVER</button>
                   
                    <div align="center" class="title" >
                    <p >INGRESAR USUARIO</p>
                    </div>              
                             
    </div>
         
   <div class="content" >
        
         <form action="Iusuario" name="form1" method="post">
           <div class=" cuadroblanco" >
         
        
           	<div class="divhead"><strong >ID:</strong><input type="text" value="<%=correlativo %>" style="width:50px;height:30px; background:#FFF;" disabled="disabled">
                    </div>
            <div class="divhead"><strong>NOM:</strong><input maxlength="25" autofocus="autofocus" type="text" class="amarillo" style="width:290px;height:30px ;background:#FF3;" name="Usuarios_nombre" id="Usuarios_nombre" value="<%=Usuarios_nombre_rs %>" required >
                    <span class="cabecera" style="color:#F00">*</span></div> 
                    
    		<div class="divhead"><strong>APE PAT:</strong><input maxlength="15" type="text" class="amarillo" style="width:180px;height:30px ;background:#FF3;" name="Usuarios_ape_p" id="Usuarios_ape_p" value="<%=Usuarios_ape_p %>" required >
                    <span class="cabecera" style="color:#F00">*</span></div>
            <div class="divhead"><strong>APE MAT:</strong><input maxlength="15" type="text" class="amarillo" style="width:180px;height:30px ;background:#FF3;" name="Usuarios_ape_m" id="Usuarios_ape_m" value="<%=Usuarios_ape_m %>" required >
                    <span class="cabecera" style="color:#F00">*</span></div>
  			<div class="divhead"><strong>EMAIL:</strong><input maxlength="35" type="email" class="amarillo" style="width:400px;height:30px ;background:#FF3;" name="Usuarios_email" id="Usuarios_email" value="<%=Usuarios_email %>" required >
                    <span class="cabecera" style="color:#F00">*</span></div> 
            <div class="divhead"><strong>NOM DE USU:</strong><input maxlength="25" type="text" class="amarillo" style="width:290px;height:30px ;background:#FF3;" name="Usuarios_login" id="Usuarios_login" value="<%=Usuarios_login %>" required >
                    <span class="cabecera" style="color:#F00">*</span></div>
                    
                    
            <div class="divhead"><strong>CONTRASEÑA:</strong><input maxlength="8" onchange="confpass();" type="password" class="amarillo" style="width:105px;height:30px ;background:#FF3;" name="Usuarios_pass" id="Usuarios_pass" value="<%=Usuarios_pass %>" required >
                    <span class="cabecera" style="color:#F00">*</span></div>
   			<div class="divhead"><strong>TIPO USU:</strong><select name="tipo_usu_id" id="tipo_usu_id" style="margin:0px 0px 0px 0px;width: 120px; margin-bottom: 9px">
				    <% for(int i =0; i<ar_tipo_usu.length; i++){
				       		String[] tipo_usu = ar_tipo_usu[i].split("/");
				    
				    %>
    
			      <option value="<%=tipo_usu[0]%>" <% if(tipo_usu_id.equals(tipo_usu[0]))%><%="selected"%> ><%=tipo_usu[1] %></option>
			    <% } %>
    </select><span class="cabecera" style="color:#F00"> *</span></div>

			<div class="divhead"><strong>FONO:</strong><input maxlength="15" type="text" class="amarillo" style="width:180px;height:30px ;background:#FF3;" name="Usuarios_telefono" id="Usuarios_telefono" value="<%=Usuarios_telefono %>" required ><span class="cabecera" style="color:#F00">*</span></div>
<div class="divhead">
		 		<strong>ESTADO:</strong><select name="estados_vig_novig_id" id="estados_vig_novig_id" style="margin:0px 0px 0px 0px;width: 157px; margin-bottom: 9px">
	 		    <option value="1"<% if(estados_vig_novig_id.equals("1"))%><%="selected"%>>VIGENTE</option>
	 		    <option value="2"<% if(estados_vig_novig_id.equals("2"))%><%="selected"%>>NO VIGENTE</option>
	 		    </select><span class="cabecera" style="color:#F00">*</span>
 		    </div>

	<div class="divhead"><strong>PERFIL USU:</strong><select name="perfilusu_id" id="perfilusu_id" style="margin:0px 0px 0px 0px;width: 315px; margin-bottom: 9px">
    <% for(int i =0; i<ar_perfilusu.length; i++){
       		String[] estclpr = ar_perfilusu[i].split("/");
    
    %>
    
      <option value="<%=estclpr[0]%>" <% if(estclpr.equals(estclpr[0]))%><%="selected"%>><%=estclpr[1] %></option>
    <% } %>
    </select><span class="cabecera" style="color:#F00">*</span></div>
	<div class="divhead"><strong>EMP:</strong><select name="empresas_id" id="empresas_id" style="margin:0px 0px 0px 0px;width: 640px; margin-bottom: 9px">
    <% for(int i =0; i<ar_empresas.length; i++){
       		String[] emp = ar_empresas[i].split("/");
    
    %>
    
      <option value="<%=emp[0]%>"><%=emp[1] %></option>
    <% } %>
    </select><span class="cabecera" style="color:#F00"> *</span></div>
			
    <div class="divhead"><strong>IN:</strong><input maxlength="3" autofocus="autofocus" type="text" class="amarillo" style="width:50px;height:30px ;background:#FF3;" name="Usuarios_inicial" id="Usuarios_inicial" value="" required >
                    <span class="cabecera" style="color:#F00">*</span></div> 
                    
   	<div class="divhead"><strong>IMEI:</strong><input maxlength="25" type="text" class="amarillo" style="width:290px;height:30px ;background:#FF3;" name="Usuarios_imei" id="Usuarios_imei" value=""  >
                    <span class="cabecera" style="color:#F00">*</span></div>

 
 
       </div> 
  <!-- Table -->

                   
           
          <table  class="table004det">
  <thead style="border-bottom: 1px solid black">
  <tr>
    <th scope="col" style="background:#39F; width: 12px;" >ID</th>
    <th scope="col" style="background:#39F" width="12px"><input type="checkbox" id="todos" name="todos" value="1"></th>
    <th scope="col" style="background:#39F ; width: 940px;" >EMPRESA</th>
  </tr>
  </thead>
  <tbody class="scroll">
  
    <%
    String mod_actual="";
   	
        for(int i =0; i<ar_empresas.length; i++){ 
        	 
        	 if(ar_empresas[i]==null) break;
        	String[] usuemp = ar_empresas[i].toString().split("/");
        	boolean usuario_has_emp=false;
        	if(ar_miusuemp!=null) for(int j =0; j<ar_miusuemp.length; j++){
        		 if(ar_miusuemp[j]!=null && !ar_miusuemp[j].equals("-1")){
            	 	if(ar_miusuemp[j].equals(usuemp[0])){  usuario_has_emp=true; break;} 
        		 }
        	 }
        	 
  %>
  <tr>
    <td class="int"><%=usuemp[0]%></td>
    <td class="int" align="center"><input class="dale" name="permisos[]" <% if(usuario_has_emp){%> checked="checked" <% } %> value="<%=usuemp[0]%>" type="checkbox"></td>
    <td class="int" width="940px"><%=usuemp[1]%></td>
    
  </tr>
         
        <%  }
         
         %>
  </tbody>
</table> 
 <div class="bgrabar">
           <button type="submit" onclick="return validarIngreso()" name="grabar" id="grabar" class="btn btn-success " >GRABAR</button>
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

