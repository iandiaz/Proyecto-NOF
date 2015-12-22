<%
	String login= request.getParameter("login");
	String pass=request.getParameter("pass");
	login=login==null? "" : login;
	pass=pass==null? "" : pass;
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
   
    <title>New Office</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
<link href='http://fonts.googleapis.com/css?family=Inconsolata:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
    
    <link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">
	<link rel="stylesheet" type="text/css" href="stylesheets/butons.css">
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
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
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
    <style type="text/css">
  		@media screen and (min-width: 440px) {
			#specialw{
				width: 450px;
			}
			.specialbuton{
			
				margin-right: 5px;
			}
		
		}
		@media screen and (max-width: 440px) {
			
			.specialbuton{
			
				margin-top: 5px;
			}
		
		}
  	</style>
  </head>

  <!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
  <!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
  <!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
  <!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
  <!--[if (gt IE 9)|!(IE)]><!--> 
  <body class=""> 
  <!--<![endif]-->
  
  <script type="text/javascript">
  	function validarRempass(){
  		
  		if(document.getElementById('login').value==""){
  			alert('DEBE INGRESAR UN NOMBRE DE USUARIO');
  			document.getElementById('login').focus();
  			return false;
  		}
  		
  		return true;
  	}
  	function validarIngreso(){
  		
  		if(document.getElementById('login').value==""){
  			alert('DEBE INGRESAR UN NOMBRE DE USUARIO');
  			document.getElementById('login').focus();
  			return false;
  		}
  		else if(document.getElementById('pass').value==""){
  			alert('DEBE INGRESAR UNA CONTRASEÑA');
  			document.getElementById('pass').focus();
  			return false;
  		}
  		return true;
  	}
  </script>
    
    
    
    <div class="navbar">
        <div class="navbar-inner" >
             <ul class="nav pull-right" style="color:#FFF">
             	<li>N001.1.01.001</li> 
             </ul>
                <img src="lib/bootstrap/img/logonew.png">
        </div>
    </div>
    
        <div class="row-fluid">
    <div class="dialog">
        <div class="block" id="specialw" >
            
            <p class="block-heading" align="center">INGRESO</p>

            
            <div class="block-body">
                <form method="post" action="login">
                    <label>NOMBRE DE USUARIO <SPAN style="color:#F00">*</SPAN></label>
                    <div class="form-group has-warning">
                    <input type="text" class="form-control span12" name="login" maxlength="25" id="login" value="<%=login%>" required="required" autofocus="autofocus">
                    </div>
                    <label>CONTRASEÑA <SPAN style="color:#F00">*</SPAN></label>
                    <div class="form-group has-warning">
                    <input type="password" class="form-control span12" name="pass" id="pass" maxlength="8" value="<%=pass%>"  >
                    </div>
                   
                     <button onclick="return validarIngreso()" type="submit" name="ingresar" class="btn btn-success pull-right">INGRESAR</button>
                    <br>
                    <br>
                    <button type="button" class="btn btn-warning pull-right" onclick="location='/002/'">CAMBIAR CONTRASEÑA</button>
                    <button onclick="return validarRempass()" type="submit" name="rempass" id="rempass" class="btn btn-danger pull-right specialbuton" >¿OLVIDÓ SU CONTRASEÑA?</button>
                    <div class="clearfix"></div>
                   
                </form>
            </div>
        </div>
        
        
    </div>
</div>


    <%if(request.getParameter("msg")!=null){ %>
    	<script type="text/javascript">
    		alert('<%=request.getParameter("msg")%>');
    	</script>
    <% } %>


    <script src="lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>
    
  </body>
</html>


