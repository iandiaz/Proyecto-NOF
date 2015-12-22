<%@page import="VO.FuncionalidadVO"%>
<%@page import="VO.ItemFuncMascaraConfVO"%>
<%@page import="VO.FuncMascaraConfVO"%>
<%@page import="VO.MascaraConfEquipoVO"%>
<%@page import="Constantes.Constantes"%>
<%@page import="VO.EmpresaVO"%>
<%@page import="java.util.ArrayList"%>
<% 	
String modname=(String)request.getAttribute("modname");
String Usu_nom=(String)request.getAttribute("usuname");	

MascaraConfEquipoVO mascara=(MascaraConfEquipoVO)request.getAttribute("mascara");	


%>
<!DOCTYPE html>
<html lang="en"><head>
    <meta charset="utf-8">
    <title>New Office</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href='http://fonts.googleapis.com/css?family=Inconsolata:400,700' rel='stylesheet' type='text/css'>
    
    <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
   	<link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
   	<link rel="stylesheet" type="text/css" href="stylesheets/butons.css">
   	<link rel="stylesheet" type="text/css" href="stylesheets/inputs.css">
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">
	
  	<script src="lib/jquery-2.1.4.min.js"></script>
    

	<link href="//cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/css/select2.min.css" rel="stylesheet" />
	<script src="//cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/js/select2.min.js"></script>
  
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
	  
	    height: 205px;
	    overflow-y: scroll;
	}
	thead, tbody {
		 min-width:100%;
		 display: inline-block;
    	
    	
	}
	select{
			
				}
	.hov>td{
		padding-left: 5px;
		padding-right: 2px;
		padding-top: 1px;
		padding-bottom: 1px;
		width: 116px;
		
	}
	.inputDetail{
		margin-bottom: 0px !important;
		text-align: right;
		width: 112px;
	}
	td.detailID{
		width: 90px;
	}
	td.detailDir{
		width: 570px;
	}
	td.detailUbi{
		width: 300px;
	}
	td.detailFec{
		width: 100px;
	}
	
	td.checkUbi{
		width: 30px;
	}
		
		
	@media screen and (min-width: 700px) {
	.cuadroblanco{
			box-shadow:0px 2px 5px 0px rgba(50, 50, 50, 0.75);
			border: 1px solid black;
			min-height:100%; height:330px;  
		min-width: 350px;
			max-width:900px;
position: relative;
background:#CCC;
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
    
    
    <script type="text/javascript">
    

    
    $( document ).ready(function() {
    	
    	
	    
	});
    	
    
 	function addMascaraFunc(){
    	
    	$.post( "addMascaraFuncAsync", $( "#form1" ).serialize())
	        .done(function( data ) {
	        	  if(data.trim()=="OK") location="N024_M_01_001";
	        	  else alert(data);
	        	  
	        });
    }
 	
	function addFuncItem(idfunc){
		
		$('<input />').attr('type', 'hidden')
        .attr('name', "additemtofunc")
        .attr('value', idfunc)
        .appendTo('#form1');
    	
    	$.post( "addMascaraFuncItemAsync", $( "#form1" ).serialize())
	        .done(function( data ) {
	        	  if(data.trim()=="OK") location="N024_M_01_001";
	        	  else alert(data);
	        	  
	        });
    }
    	
	function grabar(){
		$('.loaded').hide();
		$('.loading').show();
		$.post( "grabarMascaraAsync", $( "#form1" ).serialize())
	        .done(function( data ) {
	        	
	        	  if(data.trim()=="OK") {
	        		  alert("OPERACION REALIZADA CON EXITO");
	        		  location="N024_1_01_001";  
	        	  }
	        	  else {
	        		  alert(data);
	        		  $('.loaded').show();
	        			$('.loading').hide();
	        	  }
	        	  
	        });
    }

    </script>
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
	  <p>N<%=Constantes.MODULO %>.M.01.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/024'">VOLVER</button> 
	<div align="center" class="title">
		<p>MODIFICAR <%=modname %></p>
	</div>
</div>
<div class="content" >
  <form action="" name="form1" method="post" id="form1" > 
  
	<%for(FuncMascaraConfVO func:mascara.getFuncionalidadesMascara()){ %>
	<div class=" cuadroblanco" style="height:auto;margin-top: 5px;overflow: auto; min-height: 100px">
	<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><span style="margin:0px 0px;font-size:20px;"><%=func.getNombre() %> </span><div style="float: right;"><strong>N:</strong><input type="text" maxlength="3" class="amarillo" name="orderfunc_<%=func.getId() %>" id="orderfunc_<%=func.getId() %>" style="width:50px;height:30px ;" value="<%=func.getN()==null?"": func.getN()%>" ></div> </h3>
	
	<%for(ItemFuncMascaraConfVO item :func.getItems()){ %>
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>NOMBRE ITEM:</strong><input type="text" maxlength="40" class="amarillo" name="item_<%=func.getId() %>_<%=item.getId() %>" id="item_<%=func.getId() %>_<%=item.getId() %>" style="width:420px;height:30px ;" value="<%=item.getNombre()==null?"":item.getNombre() %>" required="required" > </div>
		
		<div class="divhead"><strong>ORDEN:</strong><input type="text" maxlength="3" class="amarillo" name="itemorder_<%=func.getId() %>_<%=item.getId() %>" id="itemorder_<%=func.getId() %>_<%=item.getId() %>" style="width:50px;height:30px ;" value="<%=item.getOrden()==null?"":item.getOrden() %>" required="required"> </div>
		<div class="divhead"><strong>PADRE:</strong><input type="checkbox" name="item_ispadre_<%=func.getId() %>_<%=item.getId() %>" id="item_ispadre_<%=func.getId() %>_<%=item.getId() %>" value="1" <%if(item.getIsPadre()!=null && item.getIsPadre().equals("1")){%> checked="checked" <% } %> > </div>
	</div>
		
	<%} %>
	
		
	<button type="button" onclick="addFuncItem('<%=func.getId() %>')" class="btn btn-success" style="position: absolute;bottom: 0; right: 0;margin: 2px 2px 2px 2px;" value="<%=func.getId()%>">AGREGAR ITEM</button>	
	</div>
	<% } %>
	
	<div class=" cuadroblanco loaded" style="height:auto;margin-top: 5px;overflow: auto; min-height: 40px">
	<button type="button" onclick="grabar()" class="btn btn-success" style="position: absolute;bottom: 0; right: 0;margin: 2px 2px 2px 2px;" >GRABAR</button>	
	</div>
	<div class=" cuadroblanco loading" style="height:auto;margin-top: 5px;overflow: auto; min-height: 40px;display: none">
	<button type="button" class="btn btn-success" style="position: absolute;bottom: 0; right: 0;margin: 2px 2px 2px 2px;" >GRABANDO..</button>	
	</div>
	
  <!-- Modal PARA AGREGAR ITEM-->
<div id="addFunc" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">AGREGAR ITEAM A LA MÁSCARA</h4>
      </div>
      <div class="modal-body">
       <div class="divhead"> <p><strong>NOMBRE ITEM:</strong><input type="text" maxlength="40" class="amarillo" name="func_nombre" id="func_nombre" style="width:420px;height:30px ;"  ></p></div>
      </div>
      <div class="modal-footer">
      	<button type="button" onclick="addMascaraFunc()" class="btn btn-success" >AGREGAR</button>
        
      </div>
    </div>

  </div>
</div>
	
</form>
</div><br><br><br>
<div class="footerapp">               
	<p style="float: left;"><i class="icon-user"></i>	
	<strong><%=Usu_nom %></strong></p>
	<button type="button" onclick="window.open('/003/','_blank')" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:2px;">MENU</button>
</div>



  

<script src="lib/bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>


