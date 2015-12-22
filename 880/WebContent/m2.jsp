<%@page import="java.util.ArrayList"%>
<%
	
	String modname=(String)request.getAttribute("modname");
	String Usu_nom=(String)request.getAttribute("usuname");
	String user_crea=(String)request.getAttribute("user_crea"); 
	String user_mod=(String)request.getAttribute("user_mod"); 
	String fec_crea=(String)request.getAttribute("fec_crea"); 
	String fec_mod=(String)request.getAttribute("fec_mod");
	
	String admpc_id=(String)request.getAttribute("admpc_id");
	
	String admpc_year=(String)request.getAttribute("admpc_year");
	String admpc_mes=(String)request.getAttribute("admpc_mes");
	String estados_vig_novig_id=(String)request.getAttribute("estados_vig_novig_id");
	String[] ar_estados =(String[]) request.getAttribute("ar_estados");
	
	
	
	
%>
<!DOCTYPE html>
<html lang="en"><head>
<script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/007/lib/jquery.Rut.min.js"></script>
<script type="text/javascript">
	function validarIngreso(){
		
		}
	</script>


    <meta charset="utf-8">
    <title>New Office</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
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
		
	@media screen and (min-width: 700px) {
.cuadroblanco{
			box-shadow:0px 2px 5px 0px rgba(50, 50, 50, 0.75);
			border: 1px solid black;
			min-height:100%; 
			height:160px;  
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
                     <p>N880.M.02.001</p>
                     </div>
                     <form method="get" action="Mclpr2" style="margin:0px 0px 0px 0px">                 
                    <button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
                    </form>
                    <button  type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onClick="location='m1'">VOLVER</button>
                   
                    <div align="center" class="title">
                    <p >MODIFICAR <%=modname %></p>
                    </div>
                   
                    
                    
               
                             
    </div>
         
   <div class="content" >
        
           <div class=" cuadroblanco">
         
         
         <form action="" name="form1" method="post">
         <input type="hidden" name="id" value="<%=admpc_id%>">
  <div class="divhead"><strong >ID:</strong><input type="text" value="<%=admpc_id %>" style="width:50px;height:30px; background:#FFF;" readonly="readonly"></div>
  <div class="divhead"><strong>A&Ntilde;O:</strong><input maxlength="55" autofocus="autofocus" type="text" style="width:60px;height:30px ;background:#FFF;" readonly="readonly"  value="<%=admpc_year%>"  ></div>
  <div class="divhead"><strong>MES:</strong><input maxlength="55" type="text"  style="width:60px;height:30px ;background:#FFF;" readonly="readonly" value="<%=admpc_mes %>"  ></div>
  <div class="divhead"><strong>ESTADO:</strong><select name="estados_vig_novig_id" id="estados_vig_novig_id" style="margin:0px 0px 0px 0px;width: 167px; margin-bottom: 9px">
    <% for(int i =0; i<ar_estados.length; i++){
       		String[] estados_vig_novig = ar_estados[i].split("/");
    
    %>
      <option value="<%=estados_vig_novig[0]%>" <% if(estados_vig_novig_id.equals(estados_vig_novig[0]))%><%="selected"%>><%=estados_vig_novig[1] %></option>
    <% } %>
    </select><span class="cabecera" style="color:#F00">*</span></div>
         
    <div class="divhead"><strong>Fec. creación:</strong><input type="text" value="<%=fec_crea%>" style="width:225px;height:30px; background:#FFF;color: #000 " disabled="disabled"></div>
	<div class="divhead"><strong>Usu creador:</strong><input type="text" value="<%=user_crea%>" style="width:400px;height:30px; background:#FFF;color: #000 " disabled="disabled"></div>
	<div class="divhead"><strong>Fec. últ mod:</strong><input type="text" value="<%=fec_mod%>" style="width:225px;height:30px; background:#FFF;color: #000 " disabled="disabled"></div>
	<div class="divhead"><strong>Usu últ mod:</strong><input type="text" value="<%=user_mod%>" style="width:400px;height:30px; background:#FFF;color: #000 " disabled="disabled"></div>
            
            <div class="bgrabar">
           <button type="submit" name="grabar" id="grabar" onclick="return validarIngreso()" class="btn btn-success" >GRABAR</button>
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