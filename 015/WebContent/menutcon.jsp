<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%
	String Usu_nom=(String)request.getAttribute("usuname");
	String perb_ingresar=  request.getAttribute("perb_ingresar").toString();
	String perb_eliminar=request.getAttribute("perb_eliminar").toString();
	String perb_modificar=request.getAttribute("perb_modificar").toString();
	String perb_consultar=request.getAttribute("perb_consultar").toString();
	String perb_reportes=request.getAttribute("perb_reportes").toString();
    
	String manual=request.getAttribute("manual").toString();
    %>
<!DOCTYPE html>
<html>
<head>
 <% 
  if(request.getParameter("Exito")!=null && !request.getParameter("Exito").equals("")){
  		if(request.getParameter("Exito").equals("OK")){
			out.println("<script>alert('OPERACI\u00d3N REALIZADA CON \u00c9XITO')</script>");
			//out.println("<script>window.location = '/001/'</script>");
		} 
		
	}
 %>
   
  <meta charset="utf-8">
<title>New Office - CONTACTO</title>
 <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/butons.css">
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">

    <script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>

    <!-- Demo page code -->

   
    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
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
		.movile{
			margin-top:5px;
			margin-left:5px;
			float: left;
				
		}
		.menu{
				
				min-height:45px;
				max-width:790px;
				width:535px; 
			}
		@media screen and (max-width: 700px) {
			.content{
				width:100%;
				max-width:350px;
				min-width: 100%;
				}
				
			.movile{
				margin-top:5px;
				width:107px;
				
			}
				
			.menu{
				min-width:100%; 
				min-height:350px;width:350px;
				background:#FFF;
				position: relative;
				margin: 0 auto;
			}
				.containermenu{
					min-width: 1px;
					}
			.movilButons{
				max-width: 120px;
				margin: 0 auto;
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
<body>


<!--<![endif]-->
    
    <div class="navbar">
                        
                     
      <div class="brand"><img src="lib/bootstrap/img/logonew_big.png"></div> 
                     <div class="version" align="right">
                     <p>N015.1.01.001</p>
                     </div>
                   <form method="get" action="" style="margin: 0px 0px 0px 0px">                
                   <button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESI&Oacute;N</button>
                   
                   </form>
                    <button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onClick="location='../003/'">VOLVER</button>
                    <button type="button" class="btn btn-warning pull-right" style="margin-right: 5px; margin-top:5px;color:#FFF" onclick="location='<%=manual %>'"><i class="icon-question-sign" style="font-size: 20px;line-height: 5px;"></i></button>
      <div align="center" class="title">
     <p >MENU TIPO CONTACTO</p>
                     </div>
                             
    </div>
         
  <div class="content">
        
         <div class="containermenu">
         <div class="menu" style="min-height: 45px;width: 537px;min-width: 537px;max-width: 537px;" >
        <div class="movilButons">
                
         <button type="button" class="btn btn-success movile" <% if(perb_ingresar.equals("0")){%> disabled="disabled" <%}else{%> onClick="location='itcon1'" <%} %> >INGRESAR</button>
         <button type="button" class="btn btn-danger movile" <% if(perb_eliminar.equals("0")){%> disabled="disabled" <%}else{%> onClick="location='etcon1'" <%} %> >ELIMINAR</button>
         <button type="button" class="btn btn-warning movile" <% if(perb_modificar.equals("0")){%> disabled="disabled" <%}else{%> onClick="location='mtcon1'" <%} %> >MODIFICAR</button>
         <button type="button" class="btn btn-primary movile" <% if(perb_consultar.equals("0")){%> disabled="disabled" <%}else{%> onClick="location='ctcon1'" <%} %>>CONSULTAR</button>
         <button type="button" class="btn btn-info movile" <% if(perb_reportes.equals("0")){%> disabled="disabled" <%}else{%> onClick="location='rpt'" <%} %>  >REPORTES</button>
        </div>
         
      
         
        
         </div>
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