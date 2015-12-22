<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String gruposusu_id=(String)request.getAttribute("gruposusu_id");
String gruposusu_nombre=(String)request.getAttribute("gruposusu_nombre");
String estados_vig_novig_nombre=(String)request.getAttribute("estados_vig_novig_nombre");
	String Usu_nom=(String)request.getAttribute("usuname");
	String user_crea=(String)request.getAttribute("user_crea"); 
	String user_mod=(String)request.getAttribute("user_mod"); 
	String fec_crea=(String)request.getAttribute("fec_crea"); 
	String fec_mod=(String)request.getAttribute("fec_mod");
    String[] ar_perfiles =(String[]) request.getAttribute("ar_perfiles");
	
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>New Office - Grupo de Perfiles</title>
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
    tbody {
        
	    height: 125px;
	    overflow-y: scroll;
        background-color: #FFF;
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
                     <p>N009.C.02.001</p>
                     </div>
                    <form method="get" action="Mgrupo" style="margin:0px 0px 0px 0px">                 
                    <button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
                    </form>
                    <button type="button" onclick="location='cgruper'" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">VOLVER</button>
                   
                    <div align="center" class="title" >
                    <p >CONSULTAR GRUPO DE PERFILES USUARIO PARA ALERTAS</p>
                    </div>
                    
               
                             
    </div>
       
  <div class="content">
        
        <div class="content" >
                   <div class=" cuadroblanco" >
         
 <div class="divhead"><strong>ID:</strong><input type="text" value="<%=gruposusu_id %>" style="width:50px;height:30px; background:#FFF;color: #000;" disabled="disabled">
                    </div>
 <div class="divhead"><strong>NOMBRE:</strong><input type="text" value="<%=gruposusu_nombre%>" style="width:565px;height:30px; background:#FFF;color: #000;" disabled="disabled" >
					</div>
 <div class="divhead" style="width:750px;"><strong>ESTADO:</strong><input type="text" value="<%=estados_vig_novig_nombre%>" style="width:144px;height:30px; background:#FFF;color: #000;" disabled="disabled">
					</div>
 <div class="divhead"><strong>Fec crea:</strong><input type="text" value="<%=fec_crea%>" style="width:225px;height:30px; background:#FFF;color: #000 " disabled="disabled">
						</div>
 <div class="divhead"><strong>Usu crea:</strong><input type="text" value="<%=user_crea%>" style="width:400px;height:30px; background:#FFF;color: #000 " disabled="disabled">
					</div>
 <div class="divhead"><strong>Fec ult mod:</strong><input type="text" value="<%=fec_mod%>" style="width:225px;height:30px; background:#FFF;color: #000 " disabled="disabled">
					</div>
 <div class="divhead"><strong>Usu ult mod:</strong><input type="text" value="<%=user_mod%>" style="width:400px;height:30px; background:#FFF;color: #000 " disabled="disabled">
					</div>
 
       </div> 
                   <table  class="table004det">
                       <thead style="border-bottom: 1px solid black">
                           <tr>
                               <th scope="col" style="background:#39F; width: 34px;" >ID</th>
                             
                               <th scope="col" style="background:#39F ; width: 980px;" >PERFIL USUARIO</th>
                           </tr>
                       </thead>
                       <tbody class="scroll">
                           <%
                               
                               
                              if(ar_perfiles!=null) for(int i =0; i<ar_perfiles.length; i++){
                                   
                                   if(ar_perfiles[i]==null) break;
                                       String[] perfil  = ar_perfiles[i].toString().split("/");
                                       
                                       
                                       
                                   %>
                           <tr>
                               <td class="int" style="width: 34px;"><%=perfil[0]%></td>
                               
                               <td class="int" width="980px"><%=perfil[1]%></td>
                               
                           </tr>
                           
                           <%  }
                               
                               %>
                       </tbody>
                   </table>
        
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
