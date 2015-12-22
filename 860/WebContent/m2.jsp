<%@page import="java.util.ArrayList"%>
<%

	String Usu_nom=(String)request.getAttribute("usuname");

	String id_prod=(String)request.getAttribute("id_prod");
	String cuc=(String)request.getAttribute("cuc");
	String prod_nom=(String)request.getAttribute("prod_nom");;
	
	String precio_guia_desp=(String)request.getAttribute("precio_guia_desp");
	String porcentaje=(String)request.getAttribute("porcentaje");
	
%>
<!DOCTYPE html>
<html lang="en"><head>
<script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/007/lib/jquery.Rut.min.js"></script>
<script type='text/javascript'>
$(function(){
 $('.searchInput').change(function(e) {
        if($('.searchInput:checked').length>0)$('.searchInput2:checked').attr('checked',false);
    });
 $('.searchInput2').change(function(e) {
        if($('.searchInput2:checked').length>0)$('.searchInput:checked').attr('checked',false);
    });
});
</script>
<script type="text/javascript">

$( document ).ready(function() {
    $('#porcentaje').on('blur', function() {
    	
    	
    	var newvalue=$('#cuc').val()*(($('#porcentaje').val()/100) +1);
    	 $('#precio_guia_desp').val(newvalue);
    	
    	
    	
    	
    	
		 });
});

	function validarIngreso(){
		var validate= true;
    	var msg="ERRORES: \n";
    	
		
		if(document.getElementById('porcentaje').value==""){
			msg+='DEBE INGRESAR UN PORCENTAJE';
			validate=false;
		document.getElementById('porcentaje').focus();
		
		}
		
		if(document.getElementById('precio_guia_desp').value==""){
			msg+='DEBE INGRESAR PRECIO PARA GUIA DE TRASLADO';
		validate=false;
		document.getElementById('precio_guia_desp').focus();
		
		}
		
		if(Number(document.getElementById('precio_guia_desp').value)<0){
    		msg+="DEBE INGRESAR EL PRECIO MAYOR A 0\n";
    		validate=false;
    	}
    	
		if(validate){
    		
    		return true;
    	}
    	else{
    		alert(msg);
    		return false;
    	}
    	
	
		
		}
	 function validateNum(event) {
         var key = window.event ? event.keyCode : event.which;
			//alert(event.keyCode);
         if (event.keyCode == 8 || event.keyCode == 46
          || event.keyCode == 37 || event.keyCode == 39 || event.keyCode == 9 || event.keyCode == 190 || (key >= 96 && key <= 105)) {
             return true;
         }
         else if ( key < 48 || key > 57 ) {
             return false;
         }
         else return true;
     };
	</script>

	
	<!-- FIN VALIDANDO QUE LOS CAMPOS VALLAN CON ALGO -->
<script>

</script>
	<!-- VALIDANDO QUE EL RUT SEA VALIDO -->
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
			min-height:100%; height:170px;  
		min-width: 350px;
			max-width:900px;
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
                     <p>N860.M.02.001</p>
                     </div>
                     <form method="get" action="" style="margin:0px 0px 0px 0px">                 
                    <button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
                    </form>
                    <button  type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onClick="location='/860/m1'">VOLVER</button>
                   
                    <div align="center" class="title">
                    <p >MODIFICAR PRECIO GUIA TRASLADO</p>
                    </div>
                   
                    
                    
               
                             
    </div>
         
   <div class="content" >
        
           <div class=" cuadroblanco">
         
         
         <form action="" name="form1" method="post">
         <input type="hidden" name="empresas_id" value="<%=request.getParameter("empresas_id")%>">
  <div class="divhead"><strong >ID PROD:</strong><input type="text" value="<%=id_prod %>" name="prod_id" style="width:100px;height:30px; background:#FFF;" readonly="readonly"></div>
  <div class="divhead"><strong>NOMBRE PRODUCTO:</strong><input  type="text"  style="width:620px;height:30px ;background:#FFF;" name="prod_nom" id="prod_nom" value="<%=prod_nom%>" readonly="readonly"  ></div>
  <div class="divhead"><strong>CUC:</strong><input type="text"  style="width:105px;height:30px ;background:#FFF;" name="cuc" id="cuc" value="<%=cuc%>" readonly="readonly"  ></div>
  <div class="divhead"><strong>%:</strong><input onkeydown="return validateNum(event)" maxlength="3" type="text" class="amarillo" style="width:50px;height:30px ;background:#FF3;" name="porcentaje" id="porcentaje" value="<%=porcentaje%>"  ><span class="cabecera" style="color:#F00">*</span></div>        
  <div class="divhead"><strong>PRECIO GUIA DESPACHO:</strong><input onkeydown="return validateNum(event)" autofocus="autofocus" type="text" class="amarillo" style="width:105px;height:30px ;background:#FF3;" name="precio_guia_desp" id="precio_guia_desp" maxlength="8" value="<%=precio_guia_desp %>" ><span class="cabecera" style="color:#F00" >*</span></div>
   
    
                    
   
    
   
   
    
   
    
   
            
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