<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%

String modname=(String)request.getAttribute("modname");

String Usu_nom=(String)request.getAttribute("usuname");


	String marca_id=(String)request.getAttribute("marca_id");
String marca_nombre=(String)request.getAttribute("marca_nombre");
String estados_vig_novig_nombre=(String)request.getAttribute("estados_vig_novig_nombre");
	
	
	String user_crea=(String)request.getAttribute("user_crea"); 
	String user_mod=(String)request.getAttribute("user_mod"); 
	String fec_crea=(String)request.getAttribute("fec_crea"); 
	String fec_mod=(String)request.getAttribute("fec_mod");
	
	String[] ar_estados =(String[]) request.getAttribute("ar_estados");
	String estados_vig_novig_id=(String) request.getAttribute("estados_vig_novig_id");
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
			height:345px;  
			min-width: 350px;
			max-width:650px;
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
                     <p>N021.M.02.001</p>
                     </div>
                    <form method="get" action="" >                 
                    <button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESI�N</button>
                    </form>
                    <button type="button" onclick="location='/021/m1'" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">VOLVER</button>
                   
                    <div align="center" class="title" >
                    <p >MODIFICAR <%=modname %></p>
                    </div>
                    
               
                             
    </div>
        
  <div class="content">
        
         <div class="cuadroblanco">
         <form action="" name="form1" method="post">
         <input type="hidden" name="id_m" value="<%=marca_id%>" /> 
            
  <table class="table-movil" border="0" align="center">
	<tr>
	    <td><div class="divhead"><strong>CORRELATIVO:</strong><input type="text" value="<%=marca_id %>" style="width:50px;height:30px; background:#FFF; color: #000" disabled="disabled">
	                    </div></td>
	    </tr>
	<tr>
		<td><div class="divhead"><strong>NOMBRE:</strong><input type="text" value="<%=marca_nombre%>" maxlength="30" class="amarillo" style="width:350px;height:30px ;background:#FF3;border: 1px solid #FFFF00;" name="marca_nombre" id="marca_nombre" >
						</div></td>
						
	</tr>
	<tr>
    <td><div class="divhead"><strong>ESTADO:</strong><select name="estados_vig_novig_id" id="estados_vig_novig_id" style="margin:0px 0px 0px 0px;width: 167px">
    <% for(int i =0; i<ar_estados.length; i++){
       		String[] estados_vig_novig = ar_estados[i].split("/");
    
    %>
    
      <option value="<%=estados_vig_novig[0]%>"<% if(estados_vig_novig_id.equals(estados_vig_novig[0]))%><%="selected"%>><%=estados_vig_novig[1] %></option>
    <% } %>
    </select><span class="cabecera" style="color:#F00"> *</span></div></td>
    </tr>
	<tr>
		<td><div class="divhead"><strong>Fecha creaci�n:</strong><input type="text" value="<%=fec_crea%>" style="width:225px;height:30px; background:#FFF;color: #000 " disabled="disabled">
						</div></td>
	</tr>
					
	<tr>
		<td><div class="divhead"><strong>Usuario creador:</strong><input type="text" value="<%=user_crea%>" style="width:400px;height:30px; background:#FFF;color: #000 " disabled="disabled">
					</div></td>
	</tr>
					
	<tr>
		<td><div class="divhead"><strong>Fecha �ltima mod:</strong><input type="text" value="<%=fec_mod%>" style="width:225px;height:30px; background:#FFF;color: #000 " disabled="disabled">
					</div></td>
	</tr>
	<tr>			
	<td><div class="divhead"><strong>Usuario �ltima mod:</strong><input type="text" value="<%=user_mod%>" style="width:400px;height:30px; background:#FFF;color: #000 " disabled="disabled">
					</div></td>
	</tr>
					
					
 

</table>

          <div class="bgrabar" >
           <button type="submit" name="grabar" id="grabar" class="btn btn-success" >GRABAR</button>
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
