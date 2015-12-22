<%@page import="VO.ProductoVO"%>
<%@page import="VO.SpecialBidVO"%>
<%@page import="Constantes.Constantes"%>

<%@page import="java.util.ArrayList"%>
<% 	
String modname=(String)request.getAttribute("modname");
String Usu_nom=(String)request.getAttribute("usuname");	
SpecialBidVO specialbid=(SpecialBidVO)request.getAttribute("specialbid");	


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
    <link rel="stylesheet" href="lib/jquery-ui/css/ui-lightness/jquery-ui-1.10.4.custom.css">
    
    <script src="lib/jquery-ui/js/jquery-ui-1.10.4.custom.js"></script>

	<link href="lib/select2/css/select2.min.css" rel="stylesheet" />
	<script src="lib/select2/js/select2.min.js"></script>
	
	<script src="lib/util.js"></script>
  
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
		width: 370px;
	}
	td.detailUbi{
		width: 450px;
	}
	td.detailFec{
		width: 100px;
	}
	
	td.checkUbi{
		width: 30px;
	}
		
		
	.cuadroblanco{
			box-shadow:0px 2px 5px 0px rgba(50, 50, 50, 0.75);
			border: 1px solid black;
			min-height:100%; height:330px;  
		min-width: 1250px;
			max-width:1250px;
position: relative;
background:#CCC;
margin: 0 auto;
		}
		.inputMovil{
			width:100%;
		}

	
	@media screen and (max-width: 699px) {
		
		.content{
			min-width: 1px;
			}

	}
    </style>
    
    
    <script type="text/javascript">
    

    
    $( document ).ready(function() {
    	
    	getProductos();
    	getTipoMoneda();
    	
    	$( "#sb_fecsolicitud,#sb_fecaprobacion,#sb_fecinicio,#sb_fectermino" ).datepicker({ dateFormat: "dd-mm-yy", monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
            monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
            dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
            dayNamesShort: ['Dom','Lun','Mar','Mie','Juv','Vie','Sab'],
            dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa']});

	    
	});
    	
    
 	
    	
	function grabar(){
		
		if(!validateGrabar()) return ;
		$('.loaded').hide();
		$('.loading').show();
		
		$.post( "grabarConfAsync", $( "#form1" ).serialize())
	        .done(function( data ) {
	        	  if(data.trim()=="OK") {
	        		  alert("OPERACION REALIZADA CON EXITO");
	        		  location="N025_1_01_001";  
	        	  }
	        	  else{
	        			alert(data);
	        		 	$('.loaded').show();
	        			$('.loading').hide();
	        	  } 
	        	  
	        });
    }
	function validateGrabar(){
		var validate =true; 
		var msg="";
		if($('#confe_nombre').val()==""){
			validate=false; 
			msg+="DEBE INGRESAR UN NOMBRE A LA CONFIGURACION<br>";
		}	
		
		
    	
		
		if(!validate){
			$('#msg').html(msg);
			$('#mensaje').modal('show');
		} 
		
		return validate;
	}
	
	
	function getProductos(){
		 
		 $('#prod_id').empty();
		  $('#prod_id').append($('<option>', {
             value: '',
             text: 'SELECCIONAR'
             }));
		  
		  $('#prod_id').select2();
		  
	    	$.post( "getProductosAsync", { })
	        .done(function( data ) {
	        		var productos_arr = $.parseJSON(data);
	          		
	          		$.each( productos_arr, function( key, producto ) {
	          			
	          		  $('#prod_id').append($('<option>', {
                         value: producto.id,
                         text: producto.id+' - '+producto.pn+' - '+ producto.desc_corto+' - R:'+producto.vida_util_imp
                         }));
                    
	          		});
	          		
	          		
	          		
	          		 $('#prod_id').select2("val","");
	          		
	        });
	    }
	
	
	function getTipoMoneda(){
		 
		 $('#tipo_cambio_id').empty();
		  
		  
		  $('#tipo_cambio_id').select2();
		  
	    	$.post( "getTipoMonedaAsync", { })
	        .done(function( data ) {
	        		var tipomoneda_arr = $.parseJSON(data);
	          		
	          		$.each( tipomoneda_arr, function( key, tipomoneda ) {
	          			
	          		  $('#tipo_cambio_id').append($('<option>', {
                        value: tipomoneda.id,
                        text: tipomoneda.nombre  
                        }));
                   
	          		});
	          		
	          		$('#tipo_cambio_id').select2("val","<%=specialbid.getTipo_moneda_id()==0?"1":specialbid.getTipo_moneda_id()%>");
	          		
	        });
	    }
	
	
	function grabar(){
		 if(<%=specialbid.getProductos().size()%>>0){
			 
			 if(calc_valores()){
				var validate=true; 
				var msg="";
				if($('#cod').val()==""){
					msg+=('<br>DEBES INGRESAR UN CODIGO PARA EL SPECIAL BID');
					validate=false;
				}
				if($('#sb_valor_tc').val()==""){
					msg+=('<br>DEBES INGRESAR UN VALOR PARA EL TIPO CAMBIO');
					validate=false;
				}
				if($('#sb_empresa').val()==""){
					msg+=('<br>DEBES INGRESAR UNA EMPRESA PARA EL SPECIAL BID');
					validate=false;
				}
				if($('#sb_fecsolicitud').val()==""){
					msg+=('<br>DEBES INGRESAR UNA FECHA SOLICITUD PARA EL SPECIAL BID');
					validate=false;
				}
				if($('#sb_fecaprobacion').val()==""){
					msg+=('<br>DEBES INGRESAR UNA FECHA APROBACION PARA EL SPECIAL BID');
					validate=false;
				}
				if($('#sb_fecinicio').val()==""){
					msg+=('<br>DEBES INGRESAR UNA FECHA INICIO PARA EL SPECIAL BID');
					validate=false;
				}
				if($('#sb_fectermino').val()==""){
					msg+=('<br>DEBES INGRESAR UNA FECHA TERMINO PARA EL SPECIAL BID');
					validate=false;
				}
				
				
					 
				if(!validate){
					$('#msg').html(msg);
					$('#mensaje').modal('show');
				}
				else{
					$.post( "grabarSBAsync", $( "#form1" ).serialize())
					    .done(function( data ) {
					    	if(data.trim()=="OK"){
					    		alert("OPERACION REALIZADA CON EXITO");
				        		  location="N<%=Constantes.MODULO%>_1_01_001";  
					    	}
					    	else if(data.trim()=="ERROR"){
					    		alert("HA OCURRIDO UN ERROR FAVOR CONTACTARSE CON EL ADMINISTRADOR DEL SISTEMA COD ERROR: "+data);
				        		
					    	}
				          		//TODO PENDIENTE
				        });
					}
					
					
					
			 }
			
		 }
		 else{
			 $('#msg').html("DEBE INGRESAR AL MENOS 1 PRODUCTO");
			$('#mensaje').modal('show');
		 }
	    	
	    }
	
	function addProducto(){
		
		if(calc_valores()){
			if($('#prod_id option:selected').val()==''){
				 alert('POR FAVOR SELECCIONE UN PRODUCTO');
			 }else{
				 $('.loadingButton').show();
				 $('.addprod').hide();
				 
				 $.post( "addProductosAsync", $( "#form1" ).serialize())
			        .done(function( data ) {
			        	if(data.trim()=="OK") location="N<%=Constantes.MODULO%>_I_01_001";
			        	else if(data.trim()=="ERROR"){
			        		alert("HA OCURRIDO UN ERROR POR FAVOR RECARGAR LA PAGINA");
			        		$('.loadingButton').hide();
						 	$('.addprod').show();
						 } 
			        	else if(data.trim()=="ERRORYAEXISTE"){
			        		alert("EL PRODUCTO YA EXISTE");	
			        		$('.loadingButton').hide();
						 	$('.addprod').show();
			        	}
			        	else{
			        		alert("HA OCURRIDO UN ERROR FAVOR CONTACTARSE CON EL ADMINISTRADOR DEL SISTEMA COD ERROR: "+data);
			        		$('.loadingButton').hide();
						 	$('.addprod').show();
			        	} 
			        });
			 }
		}
	}
	
	function removeProducto(id_prod_remove){
		
		if(calc_valores()){
				 $('.loadingButton').show();
				 $('.addprod').hide();
				 
				 $.post( "removeProductosAsync?id_prod_remove="+id_prod_remove, $( "#form1" ).serialize())
			        .done(function( data ) {
			        	if(data.trim()=="OK") location="N<%=Constantes.MODULO%>_I_01_001";
			        	else if(data.trim()=="ERROR"){
			        		alert("HA OCURRIDO UN ERROR POR FAVOR RECARGAR LA PAGINA");
			        		$('.loadingButton').hide();
						 	$('.addprod').show();
						 } 
			        	else{
			        		alert("HA OCURRIDO UN ERROR FAVOR CONTACTARSE CON EL ADMINISTRADOR DEL SISTEMA COD ERROR: "+data);
			        		$('.loadingButton').hide();
						 	$('.addprod').show();
			        	} 
			        });
			 
		}
	}
	function calc_valores(){
		var validate=true; 
		var msg="";
		if($('#sb_valor_tc').val()==""){
			msg+=('DEBES INGRESAR UN VALOR PARA EL TIPO CAMBIO');
			validate=false;
		}else{
			<%for(ProductoVO prod:specialbid.getProductos()){ %>
				var totalus=$('#total_us_<%=prod.getId()%>').val();
				if(totalus==""){
					msg+=('<br>DEBES INGRESAR UN VALOR US PARA EL PROD ID:<%=prod.getId()%>');
					validate=false;
					
				}
				var cantidad=$('#cant_<%=prod.getId()%>').val();
				if(cantidad==""){
					msg+=('<br>DEBES INGRESAR UNA CANTIDAD PARA EL PROD ID:<%=prod.getId()%>');
					validate=false; 
				}
				if(validate){
					var totalLinea_cl=$('#sb_valor_tc').val()*$('#total_us_<%=prod.getId()%>').val()*$('#cant_<%=prod.getId()%>').val();
					
					$('#total_cl_<%=prod.getId()%>').val(Math.round(Number(totalLinea_cl)));
				}
				
			<%}%>
			
			
		}
		if(!validate){
			$('#msg').html(msg);
			$('#mensaje').modal('show');
		}
		return validate;
    	
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
	  <p>N<%=Constantes.MODULO %>.I.01.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/<%=Constantes.MODULO%>'">VOLVER</button> 
	<div align="center" class="title">
		<p>INGRESAR <%=modname %></p>
	</div>
</div>
<div class="content" >
  <form action="" name="form1" method="post" id="form1" > 
  	<div class=" cuadroblanco" style="height:auto;margin-top: 5px;overflow: auto; min-height: 50px">
  	<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><span style="margin:0px 0px;font-size:20px;">DATOS SPECIAL BID </span> </h3>
	
		<div style="padding: 5px 5px 5px 5px;">
			<div class="divhead vertical-middle"><strong>ID BID:</strong><input type="text" style="width:80px;height:30px ;" readonly="readonly" value="${specialbid.id}"  ></div>
			<div class="divhead vertical-middle"><strong>COD BID:</strong><input type="text" class="amarillo" maxlength="20" style="width:220px;height:30px ;" id="cod" name="cod" value="${specialbid.cod}" ></div>
			<div class="divhead vertical-middle"><strong>TIPO CAMBIO:</strong><select id="tipo_cambio_id" name="tipo_cambio_id" style="width: 70px"></select></div>
			<div class="divhead vertical-middle"><strong>VALOR T/C:</strong> <input type="text" class="amarillo" maxlength="8" style="width:100px;height:30px ;" id="sb_valor_tc" name="sb_valor_tc" value="${specialbid.valor_tc}" ></div>
			<div class="divhead vertical-middle"><strong>EMPRESA:</strong><input type="text" class="amarillo" maxlength="35" style="width:370px;height:30px ;" id="sb_empresa" name="sb_empresa" value="${specialbid.empresa}"></div>
			<div class="divhead vertical-middle"><strong>FECHA SOLICITUD:</strong> <input type="text" class="amarillo" maxlength="20" style="width:120px;height:30px ;" id="sb_fecsolicitud" name="sb_fecsolicitud"  value="${specialbid.fecSolicitud}"></div>
			<div class="divhead vertical-middle"><strong>FECHA APROBACION:</strong> <input type="text" class="amarillo" maxlength="20" style="width:120px;height:30px ;" id="sb_fecaprobacion" name="sb_fecaprobacion" value="${specialbid.fecAprobacion}"></div>
			<div class="divhead vertical-middle"><strong>FECHA INICIO:</strong> <input type="text" class="amarillo" maxlength="20" style="width:120px;height:30px ;" id="sb_fecinicio" name="sb_fecinicio" value="${specialbid.fecInicio}"></div>
			<div class="divhead vertical-middle"><strong>FECHA TERMINO:</strong> <input type="text" class="amarillo" maxlength="20" style="width:120px;height:30px ;" id="sb_fectermino" name="sb_fectermino" value="${specialbid.fecTermino}" ></div>
		</div>
	</div>
	<div class=" cuadroblanco" style="height:auto;margin-top: 5px;overflow: auto; min-height: 50px">
  	<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><span style="margin:0px 0px;font-size:20px;">DATOS PRODUCTOS SOLICITADOS </span> </h3>
	
		<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead vertical-middle"><strong>PRODUCTO:</strong> <select id="prod_id" name="prod_id" style="width: 640px"></select></div>
		<div class="divhead vertical-middle"><button type="button" onclick="addProducto()" class="btn btn-success addprod" ><i class="icon-plus"></i></button> 
											<button type="button" class="btn btn-success loadingButton" style="display: none" >CARGANDO...</button> </div>
		
		
		<br><br><br>
		<table class="table noloading" style="margin-left:0px; ">
		
		<thead style="border: 1px solid black;background-color:#0101DF;display: inline-block;">
		<tr style="background-color:#0101DF;color:#FFFFFF">
			<td class="detailID" style="font-size:20px"><strong>ID</strong></td>
			<td class="detailDir" style="font-size:20px"><strong>PN/TLI</strong></td>
			<td class="detailUbi" style="font-size:20px"><strong>PRODUCTO</strong></td> 
			<td class="detailFec" style="font-size:20px"><strong>CANT</strong></td>
			<td class="detailFec" style="font-size:20px"><strong>TOTAL US$</strong></td>
			<td class="detailFec" style="font-size:20px"><strong>TOTAL $CL</strong></td>
			<td style="font-size:20px">&nbsp;</td>
		</tr>
		</thead>
		<tbody id="fbody" class="scroll" style="border: 1px solid black;display: inline-block;">
		<%
			for(ProductoVO prod:
				specialbid.getProductos()){ %>
		<tr>
			<td class="detailID" style="font-size:20px"><strong><%=prod.getId() %></strong></td>
			<td class="detailDir" style="font-size:20px"><strong><%=prod.getPn() %></strong></td>
			<td class="detailUbi" style="font-size:20px"><strong><%=prod.getDesc_corto() %></strong></td> 
			<td class="detailFec" style="font-size:20px"><strong><input type="text" class="amarillo" maxlength="20" style="width:80px;height:30px ;" id="cant_<%=prod.getId() %>" name="cant_<%=prod.getId() %>" value="<%=prod.getCantidad()%>"></strong></td>
			<td class="detailFec" style="font-size:20px"><strong><input type="text" class="amarillo" maxlength="20" style="width:80px;height:30px ;" id="total_us_<%=prod.getId() %>" name="total_us_<%=prod.getId() %>" value="<%=prod.getPrecio_us()%>"></strong></td>
			<td class="detailFec" style="font-size:20px"><strong><input type="text" maxlength="20" style="width:80px;height:30px ;" id="total_cl_<%=prod.getId() %>" name="total_cl_<%=prod.getId() %>" value="<%=prod.getPrecio_cl()%>" readonly="readonly"></strong></td>
			<td style="font-size:20px"><button type="button" onclick="removeProducto('<%=prod.getId() %>')" class="btn btn-danger" ><i class="icon-remove"></i></button> </td>
			
		</tr>
		<% } %>
		
		</tbody>
		</table>
		</div>
	</div>
	
	<div class=" cuadroblanco loaded" style="height:auto;margin-top: 5px;overflow: auto; min-height: 40px">
	<button type="button" onclick="calc_valores()" class="btn btn-success" style="position: absolute;bottom: 0; right: 100px;margin: 2px 2px 2px 2px;" >CALCULAR</button>
	<button type="button" onclick="grabar()" class="btn btn-success" style="position: absolute;bottom: 0; right: 0;margin: 2px 2px 2px 2px;" >GRABAR</button>	
	</div>
	<div class=" cuadroblanco loading" style="height:auto;margin-top: 5px;overflow: auto; min-height: 40px;display: none;">
	<button type="button" class="btn btn-success" style="position: absolute;bottom: 0; right: 0;margin: 2px 2px 2px 2px;" >GRABANDO..</button>	
	</div>
	
	
<!-- Modal PARA LANZAR MENSAJE-->
<div id="mensaje" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">ATENCI&Oacute;N</h4>
      </div>
      <div class="modal-body">
       <span id="msg"></span>
      </div>
      <div class="modal-footer">
      	
        
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


