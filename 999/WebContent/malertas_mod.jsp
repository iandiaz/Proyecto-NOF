<%@page import="java.util.ArrayList"%>
<%
	String correlativo=(String)request.getAttribute("alertas_id");
	String alertas_nombre =(String) request.getAttribute("alertas_nombre");
	String estados_vig_novig_id=(String) request.getAttribute("estados_vig_novig_id");

	String[] ar_gruposusualertas =(String[]) request.getAttribute("ar_gruposusualertas");
	String[] ar_estados =(String[]) request.getAttribute("ar_estados");
	String[] ar_gruposusu =(String[]) request.getAttribute("ar_gruposusu");
	
     String[] ar_eventos =(String[]) request.getAttribute("ar_eventos");
    
	String Usu_nom=(String)request.getAttribute("usuname");
	
	String user_crea=(String)request.getAttribute("user_crea"); 
	String user_mod=(String)request.getAttribute("user_mod"); 
	String fec_crea=(String)request.getAttribute("fec_crea"); 
	String fec_mod=(String)request.getAttribute("fec_mod");
	
%>
<!DOCTYPE html>
<html lang="en">
<head>
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
    
    $("#todos2").click(function(e) {
        if($("#todos2").attr('checked')){
   		$('.dale2').attr('checked',true);
        }else{
   		$('.dale2').attr('checked',false);
        }
   	});
   });
  </script>
    <meta charset="utf-8">
    <title>New Office - Alertas</title>
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
   .divhead{
       float: left;
       font-size: 20px;
       margin-right: 15px;
       margin-bottom: 2px;
       height: 36px;
       text-align: left;
       text-transform:uppercase;
       
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
			width:225px;
				}
		
	@media screen and (min-width: 700px) {
		.cuadroblanco{
			box-shadow:0px 2px 5px 0px rgba(50, 50, 50, 0.75);
			border: 1px solid black;
			min-height:100%; 
			height:120px;
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
	<div class="version" align="right"><p>N999.M.02.001</p></div>
	<form method="get" action="" style="margin:0px 0px 0px 0px">                 
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>
	<button  type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onClick="location='malertas_search'">VOLVER</button>
	<div align="center" class="title" ><p >MODIFICAR ALERTA POR EVENTO</p></div>
</div>    
<div class="content" >
    <form action="malertas_mod" name="form1" method="post">
	<div class="cuadroblanco" >
		
		<input type="hidden" name="id_a" value="<%=correlativo %>">
		<div class="divhead">
 			<strong>ID:</strong><input type="text" value="<%=correlativo %>" style="width:50px;height:30px; background:#FFF;color: #000;" disabled="disabled" >
		</div>		
		<div class="divhead">
			<strong>NOM:</strong><input maxlength="50" autofocus="autofocus" type="text" class="amarillo" style="width:570px;height:30px ;background:#FF3;border: 1px solid #FFFF00;" name="alertas_nombre" id="alertas_nombre" value="<%=alertas_nombre %>" required ><span class="cabecera" style="color:#F00">*</span></div>
		<div class="divhead"><strong>ESTADO:</strong><select name="estados_vig_novig_id" id="estados_vig_novig_id" style="margin:0px 0px 0px 0px;width: 147px">
	    <% for(int i =0; i<ar_estados.length; i++){
	       		String[] estados_vig_novig = ar_estados[i].split("/");
	    %>
	      <option value="<%=estados_vig_novig[0]%>" <% if(estados_vig_novig_id.equals(estados_vig_novig[0]))%><%="selected"%>><%=estados_vig_novig[1] %></option>
	    <% } %>
	    </select><span class="cabecera" style="color:#F00">*</span>
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
                <th scope="col" style="background:#39F; width: 47px;" >ID</th>
                <th scope="col" style="background:#39F" width="25px"><input type="checkbox" id="todos" name="todos"></th>
                <th scope="col" style="background:#39F ; width: 950px;" >GRUPOS DE PERFIL USUARIO PARA ALERTAS</th>
            </tr>
        </thead>
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
                <td class="int" width="47px"><%=usuemp[0]%></td>
                <td class="int" width="25px" align="center"><input class="dale" name="permisos[]" <% if(usuario_has_emp){%> checked="checked" <% } %> value="<%=usuemp[0]%>" type="checkbox"></td>
                <td class="int" width="950px"><%=usuemp[1]%></td>
            </tr>
            <%           	
                }
                %>
        </tbody>
    </table>
    
    <table class="table004det">
        <thead style="border-bottom: 1px solid black">
            <tr>
                <th scope="col" style="background:#39F; width: 47px;" >ID</th>
                <th scope="col" style="background:#39F" width="25px"><input type="checkbox" id="todos2" name="todos"></th>
                <th scope="col" style="background:#39F ; width: 790px;" >EVENTOS</th>
                <th scope="col" style="background:#39F ; width: 150px;" >MODULO</th>
                
            </tr>
        </thead>
        <tbody class="scroll" style="overflow-y: scroll;">
            <%
                
                for(int i =0; i<ar_eventos.length; i++){
                    if(ar_eventos[i]==null) break;
                        String[] evento  = ar_eventos[i].toString().split("/");
                        
                        
                    %>
            <tr>
                <td class="int" width="47px"><%=evento[0]%></td>
                <td class="int" width="25px" align="center"><input class="dale2" name="eventos[]" value="<%=evento[0]%>" type="checkbox" <% if(evento[3].equals("1")){%> checked <% } %> ></td>
                <td class="int" width="790px"><%=evento[1]%></td>
                <td class="int" width="150px">N<%=evento[2]%></td>
                
            </tr>
            <%
                }
                %>
        </tbody>
    </table>
    
    <div class="bgrabar">
        <button type="submit" name="grabar" id="grabar"  class="btn btn-success">GRABAR</button>
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
