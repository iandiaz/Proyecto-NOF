<%@page import="java.util.ArrayList"%>
<%
	String gruposusu_id=(String)request.getAttribute("gruposusu_id");
	String[] ar_gruposusu =(String[]) request.getAttribute("ar_gruposusu");
	String[] ar_estados_ticket =(String[]) request.getAttribute("ar_estados_ticket");
    String[] ar_tipotck =(String[]) request.getAttribute("ar_tipotck");



	String alertemp_nombre="";
	String alertemp_minutos="";
	String estados_vig_novig_id="";
	
	if(request.getAttribute("alertemp_nombre")!=null && !request.getAttribute("alertemp_nombre").equals("")) alertemp_nombre =(String) request.getAttribute("alertemp_nombre");
	if(request.getAttribute("alertemp_minutos")!=null && !request.getAttribute("alertemp_minutos").equals("")) alertemp_minutos =(String) request.getAttribute("alertemp_minutos");
	if(request.getAttribute("estados_vig_novig_id")!=null && !request.getAttribute("estados_vig_novig_id").equals(""))estados_vig_novig_id=(String) request.getAttribute("estados_vig_novig_id");

	String[] ar_estados =(String[]) request.getAttribute("ar_estados");
	String Usu_nom=(String)request.getAttribute("usuname");	
	String correlativo=(String)request.getAttribute("correlativo");
    
%>
<!DOCTYPE html>
<html lang="en"><head>

<!-- VALIDANDO QUE LOS CAMPOS VAYAN CON ALGO -->
	<script type="text/javascript">
	function validarIngreso(){
		if(document.getElementById('alertas_nombre').value==""){
		alert('DEBE INGRESAR UN NOMBRE DE LA ALERTA TEMPORAL');
		document.getElementById('alertas_nombre').focus();
		return false;
		}
		
		}
	</script>
	
	<!-- FIN VALIDANDO QUE LOS CAMPOS VAYAN CON ALGO -->
 <% 
  if(request.getParameter("Exito")!=null && !request.getParameter("Exito").equals("")){
  		if(request.getParameter("Exito").equals("NOK")){
			out.println("<script>alert('EL NOMBRE DE LA ALERTA TEMPORAL YA EXISTE')</script>");
			//out.println("<script>window.location = '/001/'</script>");
		} 
		
	}
   
	%>
    <meta charset="utf-8">
    <title>New Office - Alertas Temporales</title>
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
	  form{
	    	margin-top:10px;
	    	height: 100%;
	    }
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
        
	    height: 115px;
        
	    
	}
	thead, tbody {
        min-width:100%;
    	display: inline-block;
	}
		select{
			
				}
		
	@media screen and (min-width: 700px) {
		.cuadroblanco{
			box-shadow:0px 2px 5px 0px rgba(50, 50, 50, 0.75);
			border: 1px solid black;
			min-height:100%; height:230px;
		min-width: 350px;
			max-width:1000px;
position: relative;
background:#ccc;
margin: 0 auto;
padding: 5px 5px 5px 5px;
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
                     <p>N995.I.01.001</p>
                     </div>
                     <form method="get" action="ialertemp" style="margin:0px 0px 0px 0px">                 
                    <button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
                    </form>
                    <button  type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='./'">VOLVER</button>
                   
                    <div align="center" class="title" >
                    <p >INGRESAR ALERTA TEMPORAL</p>
                    </div>
                   
                    
                    
               
                             
    </div>
         
  <div class="content" >
      <form action="ialertemp" name="form1" method="post">
          
                   <div class=" cuadroblanco" >
         
         
    	<div class="divhead"><strong >ID:</strong><input type="text" value="<%=correlativo %>" style="width:50px;height:30px; background:#FFF;color: #000;" disabled="disabled"></div>
    <div class="divhead"><strong>NOM:</strong><input maxlength="50" autofocus="autofocus" type="text" class="amarillo" style="width:570px;height:30px ;background:#FF3;border: 1px solid #FFFF00;" name="alertemp_nombre" id="alertemp_nombre" value="<%=alertemp_nombre %>" required >
                    <span class="cabecera" style="color:#F00">*</span></div> 
 	
    <div class="divhead"><strong>MINUTOS:</strong><input maxlength="4" type="text" class="amarillo" style="width:60px;height:30px ;background:#FF3;border: 1px solid #FFFF00;" name="alertemp_minutos" id="alertemp_minutos" value="<%=alertemp_minutos %>" required >
                    <span class="cabecera" style="color:#F00">*</span></div> 
    <div class="divhead"><strong>MINUTOS PARA REVISAR:</strong><input maxlength="4" type="text" class="amarillo" style="width:60px;height:30px ;background:#FF3;border: 1px solid #FFFF00;" name="alertemp_minutos_revisar" id="alertemp_minutos_revisar" value="<%=alertemp_minutos %>" required >
                    <span class="cabecera" style="color:#F00">*</span></div> 
 	
 	
    <div class="divhead"><strong>ESTADO TICKET:</strong><select name="ticket_estados_id" id="ticket_estados_id" style="margin:0px 0px 0px 0px;">
    <% for(int i =0; i<ar_estados_ticket.length; i++){
       		String[] ticket_estados = ar_estados_ticket[i].split("/");
    
    %>
    
      <option value="<%=ticket_estados[0]%>"<% if(estados_vig_novig_id.equals(ticket_estados[0]))%><%="selected"%>><%=ticket_estados[1] %></option>
    <% } %>
    </select><span class="cabecera" style="color:#F00"> *</span></div>
    
    <div class="divhead"><strong>TIPO TICKET:</strong><select name="tipo_ticket" id="tipo_ticket" style="margin:0px 0px 0px 0px;">
        <% for(int i =0; i<ar_tipotck.length; i++){
            String[] tipotck = ar_tipotck[i].split("/");
            
        %>
        
        <option value="<%=tipotck[0]%>" ><%=tipotck[1] %></option>
        <% } %>
    </select><span class="cabecera" style="color:#F00"> *</span></div>
    
   <div class="divhead"><input  type="radio" name="notif_tipe" id="notif_tipe1" value="1" checked >
         <label for="notif_tipe1"><strong>NOTIFICAR SI NO CAMBIA ESTADO</strong></label>
   <input  type="radio" name="notif_tipe" id="notif_tipe2" value="2" >
        <label for="notif_tipe2"><strong >NOTIFICAR SI NO SE HA CERRADO</strong></label>
        </div>
   
    <div class="divhead"><strong>MENSAJE:</strong><input maxlength="70" type="text" class="amarillo" style="width:800px;height:30px ;background:#FF3;border: 1px solid #FFFF00;" name="alertemp_desc" id="alertemp_desc" required >
        <span class="cabecera" style="color:#F00">*</span></div>
    <div class="divhead"><strong>ESTADO:</strong><select name="estados_vig_novig_id" id="estados_vig_novig_id" style="margin:0px 0px 0px 0px;">
        <% for(int i =0; i<ar_estados.length; i++){
       		String[] estados_vig_novig = ar_estados[i].split("/");
            
        %>
        
        <option value="<%=estados_vig_novig[0]%>"<% if(estados_vig_novig_id.equals(estados_vig_novig[0]))%><%="selected"%>><%=estados_vig_novig[1] %></option>
        <% } %>
    </select><span class="cabecera" style="color:#F00"> *</span></div>
   
    
    
         </div>
                   <table class="table004det">
                       <thead style="border-bottom: 1px solid black">
                           <tr>
                               <th scope="col" style="background:#39F; width: 34px;" >ID</th>
                               <th scope="col" style="background:#39F" width="25px"><input type="checkbox" id="todos" name="todos"></th>
                               <th scope="col" style="background:#39F ; width: 950px;" >GRUPOS DE PERFILES USUARIO PARA ALERTAS</th>
                           </tr>
                       </thead>
                       <tbody class="scroll" style="overflow-y: scroll;" >
                           <%
                               String mod_actual="";
                               for(int i =0; i<ar_gruposusu.length; i++){
                                   if(ar_gruposusu[i]==null) break;
                                       String[] usuemp = ar_gruposusu[i].toString().split("/");
                                       boolean usuario_has_emp=false;
                                       
                           %>
                           <tr>
                               <td class="int" style="width: 34px;"><%=usuemp[0]%></td>
                               <td class="int" style="width: 25px;" align="center"><input class="dale" name="permisos[]" value="<%=usuemp[0]%>" type="checkbox"></td>
                               <td class="int" width="950px"><%=usuemp[1]%></td>
                           </tr>
                           <%           	
                               }
                               %>
                           
                           
                       </tbody>
                   </table>
                   
                   <div class="bgrabar">
                       <button type="submit" onclick="return validarIngreso()" name="grabar" id="grabar" class="btn btn-success" >GRABAR</button>
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
