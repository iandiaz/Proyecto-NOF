<%@page import="java.util.ArrayList"%>
<%
	String[] ar_tticket =(String[]) request.getAttribute("ar_tticket");
	String[] ar_tusu =(String[]) request.getAttribute("ar_tusu");
	
	String[] ar_usuarios =(String[]) request.getAttribute("ar_usuarios");
	String[] ar_usugps =(String[]) request.getAttribute("ar_usugps");
	
	String Usu_nom=(String)request.getAttribute("usuname");	
	String correlativo=(String)request.getAttribute("correlativo");
	String Usuarios_nombre =(String) request.getAttribute("Usuarios_nombre");
	String estados_vig_novig_id1=(String) request.getAttribute("estados_vig_novig_id1");
	String Usuarios_ape_p=(String)request.getAttribute("Usuarios_ape_p");
	String Usuarios_ape_m=(String)request.getAttribute("Usuarios_ape_m");
	String Usuarios_pass=(String)request.getAttribute("Usuarios_pass");
	String Usuarios_login=(String)request.getAttribute("Usuarios_login");
	String Usuarios_telefono=(String)request.getAttribute("Usuarios_telefono");
	String Usuarios_email=(String)request.getAttribute("Usuarios_email");
	String tipo_usu_id1=(String)request.getAttribute("tipo_usu_id1");
	String perfilusu_id1=(String)request.getAttribute("perfilusu_id1");
	String empresas_id1=(String)request.getAttribute("empresas_id1");
	
	String Usuarios_id=(String)request.getAttribute("Usuarios_id");
	
	
	
	String user_crea=(String)request.getAttribute("user_crea"); 
	String user_mod=(String)request.getAttribute("user_mod"); 
	String fec_crea=(String)request.getAttribute("fec_crea"); 
	String fec_mod=(String)request.getAttribute("fec_mod");
%>
<!DOCTYPE html>
<html lang="en"><head>
<!-- VALIDANDO QUE LOS CAMPOS VALLAN CON ALGO -->
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
    <title>New Office - Configuracion GPS Dinamico</title>
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
		  
		    height: 315px;
		    overflow-y: scroll;
		    background-color: #fff;
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
			height:345px;  
		min-width: 350px;
			max-width:636px;
position: relative;
background:#FFF;
margin: 0 auto;
	padding-left: 10px;
	padding-top: 5px;
	background-color: #ccc;
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
	<div class="brand">
		<img src="lib/bootstrap/img/logonew_big.png">
	</div>
	<div class="version" align="right">
		<p>N301.C.01.001</p>
	</div>
	<form method="get" action="Musuario2" style="margin:0px 0px 0px 0px">                 
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/301/'">VOLVER</button>
	<div align="center" class="title" >
		<p>CONSULTAR CONFIGURACION GPS DINAMICO</p>
	</div>
</div>       
<div class="content" >
	<form action="mconf" name="form1" method="post">
	<div class=" cuadroblanco" style="height:80px;">
		<input type="hidden" name="Usuarios_id" value="<%=correlativo%>">
<div class="divhead"><strong>ID:</strong><input type="text" value="<%=correlativo %>" style="width:50px;height:30px; background:#FFF;" disabled="disabled">
                    </div>
<div class="divhead"><strong>NOM:</strong><input type="text" style="width:290px;height:30px ;background:#FFF;" value="<%=Usuarios_nombre %>" disabled></div>
<div class="divhead"><strong>APE PAT:</strong><input type="text" style="width:180px;height:30px ;background:#FFF;" value="<%=Usuarios_ape_p %>" disabled></div>
<div class="divhead"><strong>APE MAT:</strong><input type="text" style="width:180px;height:30px ;background:#FFF;" value="<%=Usuarios_ape_m %>" disabled></div> 

</div>
<br>
<table  class="table004det" style="height: 350px">
  <thead style="border-bottom: 1px solid black">
  <tr>
    <th scope="col" style="background:#39F; width: 47px;" >ID</th>
    <th scope="col" style="background:#39F; width: 24px">&nbsp;</th>
    <th scope="col" style="background:#39F; width: 580px;" >TIPO TICKET</th>
  </tr>
  </thead>
  <tbody class="scroll">
      <%
    String mod_actual="";  
         for(int i =0; i<ar_tticket.length; i++){
        	if(ar_tticket[i]==null) break;
        	String[] usutic = ar_tticket[i].toString().split("/");
        	boolean usuario_ticket=false;
        	 for(int j =0; j<ar_tusu.length; j++){
        		 if(ar_tusu[j]!=null && !ar_tusu[j].equals("-1")){
            		 if(ar_tusu[j].equals(usutic[0])){  usuario_ticket=true; break;}   
        		 }
        	 }
        	%>
  <tr>
    <td class="int" width="47px" ><%=usutic[0]%></td>
    <td class="int" width="24px" align="center"><input class="dale"  <% if(usuario_ticket){%> checked="checked" <% } %> value="<%=usutic[0]%>" type="checkbox" disabled="disabled"></td>
    <td class="int" width="580px"><%=usutic[1]%></td>
  </tr>
        <%           	
         }
         %>
  </tbody>
</table>  
   
            <div class="bgrabar">
            <a type="button" class="btn btn-success " href="http://www.gpsdroid.cl/mapNOF.php?id_u=<%=Usuarios_id %>" target="_blank" >VER MAPA</a>
           
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

