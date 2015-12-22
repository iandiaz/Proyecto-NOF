<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String alertas_temp_id=(String)request.getAttribute("alertas_temp_id");
	String alertas_temp_nombre=(String)request.getAttribute("alertas_temp_nombre");
	String alertas_temp_minutos=(String)request.getAttribute("alertas_temp_minutos");
	
	String alertas_temp_minutos_revisar=(String)request.getAttribute("alertas_temp_minutos_revisar");
	
	
	String ticket_estados_nombre=(String)request.getAttribute("ticket_estados_nombre");
	String estados_vig_novig_nombre=(String)request.getAttribute("estados_vig_novig_nombre");
    String alertas_temp_descripcion=(String) request.getAttribute("alertas_temp_descripcion");
    String tipo_ticket_nombre=(String) request.getAttribute("tipo_ticket_nombre");
    String alertas_temp_notiftype=(String) request.getAttribute("alertas_temp_notiftype");

	String Usu_nom=(String)request.getAttribute("usuname");
	
	String[] ar_gruposusualertas =(String[]) request.getAttribute("ar_gruposusualertas");
	String[] ar_gruposusu =(String[]) request.getAttribute("ar_gruposusu");	
	
	String user_crea=(String)request.getAttribute("user_crea"); 
	String user_mod=(String)request.getAttribute("user_mod"); 
	String fec_crea=(String)request.getAttribute("fec_crea"); 
	String fec_mod=(String)request.getAttribute("fec_mod");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
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
			width:225px;
				}
		
	@media screen and (min-width: 700px) {
		.cuadroblanco{
			box-shadow:0px 2px 5px 0px rgba(50, 50, 50, 0.75);
			border: 1px solid black;
			min-height:100%;
			height:300px;
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
                     <p>N995.E.02.001</p>
                     </div>
                    <form method="get" action="ealertemp" >                 
                    <button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
                    </form>
                    <button type="button" onclick="location='ealertemp'" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">VOLVER</button>
                   
                    <div align="center" class="title" >
                    <p >ELIMINACION DE ALERTA TEMPORAL</p>
                    </div>
                    
    </div>
        
  <div class="content">
        
         <div class="cuadroblanco">
            
 <div class="divhead"><strong>ID:</strong><input type="text" value="<%=alertas_temp_id %>" style="width:50px;height:30px; background:#FFF; color: #000" disabled="disabled">
	                    </div>
 <div class="divhead"><strong>NOM:</strong><input type="text" value="<%=alertas_temp_nombre%>" style="width:570px;height:30px; background:#FFF;color: #000" disabled="disabled" >
						</div>
 <div class="divhead"><strong>MINUTOS:</strong><input type="text" value="<%=alertas_temp_minutos%>" style="width:60px;height:30px; background:#FFF;color: #000 " disabled="disabled">
						</div>
<div class="divhead"><strong>MINUTOS PARA REVISAR:</strong><input type="text" value="<%=alertas_temp_minutos_revisar%>" style="width:60px;height:30px; background:#FFF;color: #000 " disabled="disabled">
						</div>
						
 <div class="divhead"><strong>ESTADO TICKET:</strong><input type="text" value="<%=ticket_estados_nombre%>" style="width:290px;height:30px; background:#FFF;color: #000 " disabled="disabled">
						</div>
 <div class="divhead"><strong>TIPO TICKET:</strong><input type="text" value="<%=tipo_ticket_nombre%>" style="width:290px;height:30px; background:#FFF;color: #000;" disabled="disabled" >
     </div>
 <div class="divhead" style="height: 25px;"><input  type="radio" <% if(alertas_temp_notiftype.equals("1")){ %> checked <% } %> disabled >
     <label ><strong>NOTIFICAR SI NO CAMBIA ESTADO</strong></label>
     <input  type="radio" <% if(alertas_temp_notiftype.equals("2")){ %> checked <% } %> disabled >
     <label ><strong >NOTIFICAR SI NO SE HA CERRADO</strong></label>
     </div>
 <div class="divhead"><strong>MENSAJE:</strong><input maxlength="70" autofocus="autofocus" type="text" style="width:800px;height:30px; background:#FFF;color: #000;" value="<%=alertas_temp_descripcion %>" disabled="disabled" >
                        </div>
 <div class="divhead"><strong>ESTADO:</strong><input type="text" value="<%=estados_vig_novig_nombre%>" style="width:144px;height:30px; background:#FFF;color: #000 " disabled="disabled">
     </div>
 
  <div class="divhead"><strong>FEC CREA:</strong><input type="text" value="<%=fec_crea%>" style="width:225px;height:30px; background:#FFF;color: #000 " disabled="disabled">
						</div>
 <div class="divhead"><strong>USU CREA:</strong><input type="text" value="<%=user_crea%>" style="width:400px;height:30px; background:#FFF;color: #000 " disabled="disabled">
					</div>
 <div class="divhead"><strong>FEC ULT MOD:</strong><input type="text" value="<%=fec_mod%>" style="width:225px;height:30px; background:#FFF;color: #000 " disabled="disabled">
					</div>
 <div class="divhead"><strong>USU ULT MOD:</strong><input type="text" value="<%=user_mod%>" style="width:400px;height:30px; background:#FFF;color: #000 " disabled="disabled">
					</div>
    </div>
<table class="table004det">
  <thead style="border-bottom: 1px solid black">
  <tr>
    <th scope="col" style="background:#39F; width: 34px;" >ID</th>
    <th scope="col" style="background:#39F" width="25px"><input type="checkbox" id="todos" name="todos" disabled="disabled"></th>
    <th scope="col" style="background:#39F ; width: 950px;" >GRUPOS DE PERFILES USUARIO PARA ALERTAS</th>
  </tr>
  </thead>
 
         </div>
         <tbody class="scroll" style="overflow-y: scroll;">
             <%
                 String mod_actual="";
                 for(int i =0; i<ar_gruposusu.length; i++){
                     if(ar_gruposusu[i]==null) break;
                         String[] usuemp = ar_gruposusu[i].toString().split("/");
                         boolean usuario_has_emp=false;
                         for(int j =0; j<ar_gruposusualertas.length; j++){
                             if(ar_gruposusualertas[j]!=null && !ar_gruposusualertas[j].equals("-1")){
                                 if(ar_gruposusualertas[j].equals(usuemp[0])){
                                     usuario_has_emp=true; break;
                                 }
                                 }
                                 }
             %>
             <tr>
                 <td class="int" style="width: 34px;"><%=usuemp[0]%></td>
                 <td class="int" style="width: 25px;" align="center"><input class="dale" disabled="disabled" name="permisos[]" <% if(usuario_has_emp){%> checked="checked" <% } %> value="<%=usuemp[0]%>" type="checkbox"></td>
                 <td class="int" width="950px"><%=usuemp[1]%></td>
             </tr>
             <%           	
                 }
                 %>
         </tbody>
         </table>  
         
         <div class="bgrabar" >
             <button onclick="location='ealertemp_del?delete_id=<%=alertas_temp_id%>';" type="button" name="grabar" id="grabar" class="btn btn-success" >GRABAR</button>
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
