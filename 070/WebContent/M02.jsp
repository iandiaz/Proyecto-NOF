
<%@page import="VO.PedidoVO"%>
<%@page import="VO.PedidoDetalleVO"%>
<%@page import="VO.EmpresaVO"%>
<%@page import="java.util.ArrayList"%>
<% 	
String modname=(String)request.getAttribute("modname");
String Usu_nom=(String)request.getAttribute("usuname");	
String idtick=(String)request.getAttribute("idtick");
PedidoVO pedido=(PedidoVO)request.getAttribute("pedido");





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
<script src="lib/util.js"></script>
  	<script src="lib/jquery-ui/js/jquery-1.10.2.js"></script>
    <link rel="stylesheet" href="lib/jquery-ui/css/ui-lightness/jquery-ui-1.10.4.custom.css">
    <script src="lib/jquery-ui/js/jquery-ui-1.10.4.custom.js"></script>

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
	tr.hov:hover{
		opacity:0.6;
		/*cursor: pointer;*/
	}
	.inputDetail{
		margin-bottom: 0px !important;
		text-align: right;
		width: 112px;
	}
	td.detailID{
		width: 80px;
	}
	td.detailDir{
		width: 300px;
	}
	td.detailUbi{
		width: 250px;
	}
	td.detailFec{
		width: 100px;
	}
	td.detailCant{
		width: 120px;
		text-align: center;
	}
	td.checkUbi{
		width: 30px;
	}
	td.detailProd{
	width: 150px;
	}
	td.detailPn{
	width: 150px;
	}	
		
	@media screen and (min-width: 700px) {
	.cuadroblanco{
			box-shadow:0px 2px 5px 0px rgba(50, 50, 50, 0.75);
			border: 1px solid black;
			min-height:100%; height:330px;  
		min-width: 350px;
			max-width:1100px;
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
    	
    	    $('.noloading').hide();
	        $('.loading').hide();
	        
	       
	        
	        $('.noloadingTraslados').hide();
	        $('.loadingTraslados').hide();
	       
	        $('#direcciones_id').select2();
	        $('#ubi_id').select2();
	        $('#ubi_id_linea').select2();
	        $('#func_id').select2();
	        $('#prod_id').select2();
	        
	        
	        
			getDireccionesSelect('<%=pedido.getCliente().getId()%>');
	        getFuncionalidadesSelect();
	        
	        //traemos ubicaciones cuando seleccione direccion
	        
	       $('#direcciones_id').on('change', function() {
	    	   $( ".trasladosData" ).html("");
	       		var dir_seleccionada = $(this).val();
	       		if(dir_seleccionada!="") getUbicacionesSelect(dir_seleccionada);
	       });
	        
	        
	       $('#ubi_id').on('change', function() {
	    	  
	       		var ubi_seleccionada = $(this).val();
	       		if(ubi_seleccionada!="") getTraslados(ubi_seleccionada);
	       });
	       
	       
	       $('#func_id').on('change', function() {
		    	  
	       		var func_seleccionado = $(this).val();
	       		if(func_seleccionado!="") getProductos(func_seleccionado);
	       });
	        
	        
	        //getProductos();
	        
	        
	        
	        
	        
	        
		
    
	    
	});
    	
    function getProductos(func_id){
    	
    	$.post( "getProductosSelect", {func_id: func_id })
	        .done(function( data ) {
	        	  $( ".productosData" ).html( (data) );
	        	  $('#prod_id').select2("val","");
	        });
    }
    	
    function getTraslados(ubi_id){
    	$( ".trasladosData" ).html("");
    	$('.loadingTraslados').show();
    	$('.noloadingTraslados').hide();
    	 
    	 $.post( "getTraslados", {ubi_id: ubi_id })
	        .done(function( data ) {
	        	  $( ".trasladosData" ).html( (data) );
	        	  $('.noloadingTraslados').show();
		  	      $('.loadingTraslados').hide();
		    });
    }  
    
    function getDireccionesSelect(id_cli){
    	
   	 	$.post( "getDireccionesSelect", {id_cliente: id_cli ,direcciones_id: '<%=pedido.getDireccion().getId()%>'  })
	        .done(function( data ) {
	        	$( ".direccionesData" ).html( (data) );
	        	
	        	$('#direcciones_id').select2("val","<%=pedido.getDireccion().getId()%>");
	        	
	        	
		
	        });
   	}  
    
    function getUbicacionesSelect(id_dir){
    	
    	 $.post( "getUbicacionesSelect", {id_direccion: id_dir})
   	        .done(function( data ) {
   	        	  $( ".ubicacionesData" ).html( (data) );
   	        	 $('#ubi_id').select2("val","");
   	        	$('#ubi_id_linea').select2("val","");
   	        	
   	    
   	        });
    }  
    
    function getFuncionalidadesSelect(){
    	
     	 $.post( "getFuncionalidadesSelect", {})
  	        .done(function( data ) {
  	        	  $( ".funcionalidadesData" ).html( (data) );
  	        	 $('#func_id').select2("val","");
  	    
  	        });
   }  
    
    
    function validateOtroTicket(){
        
    	var validate= true;
    	var msg="";
    	
    	if($('#prod_id').val()!="" &&  $('#prod_id').val()!=null){
    		if($('#ubi_id_linea').val()=="" || $('#ubi_id_linea').val()==null){
        		msg+="DEBE SELECCIONAR UNA UBICACIÓN\n";
        		validate=false;
        	}
        	
        	if($('#prod_id').val()=="" || $('#prod_id').val()==null){
        		msg+="DEBE SELECCIONAR UN PRODUCTO\n";
        		validate=false;
        	}
        	if($('#cantidad').val()=="" || $('#cantidad').val()==null || Number($('#cantidad').val())<=0){
        		msg+="DEBE SELECCIONAR UNA CANTIDAD MAYOR O IGUAL A 1 \n";
        		validate=false;
        	}
    	}
    	
    	
    	
    	if(validate){
    		
    		return true;
    	}
    	else{
    		alert(msg);
    		return false;
    	}
    	
    	
    }
    
    
	function validateSubmit(){
    	//OTRO PRODUCTO MISMO TICKET 
    	
    	var validate= true;
    	var msg="";
    	if($('#ubi_id_linea').val()=="" || $('#ubi_id_linea').val()==null){
    		msg+="DEBE SELECCIONAR UNA UBICACIÓN\n";
    		validate=false;
    	}
    	
    	if($('#prod_id').val()=="" || $('#prod_id').val()==null){
    		msg+="DEBE SELECCIONAR UN PRODUCTO\n";
    		validate=false;
    	}
    	if($('#cantidad').val()=="" || $('#cantidad').val()==null){
    		msg+="DEBE SELECCIONAR UNA CANTIDAD\n";
    		validate=false;
    	}
    	if($('#idtick').val()=="" || $('#idtick').val()==null){
    		msg+="DEBE SELECCIONAR UN TICKET\n";
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
	
	function validateSubmitGrabar(){
	    
    	var validate= true;
    	var msg="";
    	
    	if($('#prod_id').val()!=null && $('#prod_id').val()!=""){
    		if($('#ubi_id_linea').val()=="" || $('#ubi_id_linea').val()==null){
        		msg+="DEBE SELECCIONAR UNA UBICACIÓN\n";
        		validate=false;
        	}
        	
        	if($('#cantidad').val()=="" || $('#cantidad').val()==null){
        		msg+="DEBE SELECCIONAR UNA CANTIDAD\n";
        		validate=false;
        	}
        	if($('#idtick').val()=="" || $('#idtick').val()==null){
        		msg+="DEBE SELECCIONAR UN TICKET\n";
        		validate=false;
        	}
        	
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
	  <p>N070.M.02.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/070/M01'">VOLVER</button> 
	<div align="center" class="title">
		<p>MODIFICAR <%=modname %></p>
	</div>
</div>
<div class="content" >
  <form action="M02?id_pedido=${pedido.getId()}" name="form1" method="post" id="form1" > 
  
  <div class=" cuadroblanco" style="height:165px;padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>ID PEDIDO:</strong><input type="text" name="id_pedido" id="id_pedido" style="width:145px;height:30px ;background:#FFF;" readonly="readonly" value="${pedido.getId()}" > </div>
		<div class="divhead"><strong>CLIENTE:</strong><input type="text" style="width:345px;height:30px ;background:#FFF;" readonly="readonly" value="${pedido.getCliente().getNombre_nof()}" > </div>
		<div class="divhead"><strong>PROVEEDOR:</strong><input type="text" style="width:345px;height:30px ;background:#FFF;" readonly="readonly" value="${pedido.getProveedor().getNombre_nof()}" > </div>
		<div class="divhead"><strong>TICKET SELECCIONADO:</strong><input type="text" style="width:145px;height:30px ;background:#FFF;" readonly="readonly" value="${pedido.getId_ticket()}" name="idtick" id="idtick" > </div>
		<div class="divhead"><strong>ESTADO PEDIDO:</strong><input type="text" style="width:145px;height:30px ;background:#FFF;" readonly="readonly" value="${pedido.getEstado_p()}" > </div>
		<div class="divhead"><strong>FECHA PEDIDO:</strong><input type="text" style="width:145px;height:30px ;background:#FFF;" readonly="readonly" value="${pedido.getFec_crea()}" > </div>
		<div class="divhead"><strong>OBS:</strong><input type="text" id="pedido_obs" name="pedido_obs" maxlength="50"  style="width:515px;height:30px ;background:#FF3;" value="${pedido.getObs()}" ></div>
		
	</div>

	<div class=" cuadroblanco" style="height:45px;margin-top: 5px;">
	
	<div class="divhead"><strong>DIRECCI&Oacute;N:</strong><span class="loadingDirecciones" style="display: none;">Cargando..</span><select name=direcciones_id id="direcciones_id" class="direccionesData" style="margin:0px 0px 0px 0px;width: 580px;" >
				
    		</select><span class="cabecera" style="color:#F00">*</span></div>
    
    <div class="divhead"><strong>UBICACI&Oacute;N:</strong><select name=ubi_id id="ubi_id" class="ubicacionesData" style="margin:0px 0px 0px 0px;width: 250px" >
				
    		</select><span class="cabecera" style="color:#F00">*</span></div>
		
	</div>
	<div class=" cuadroblanco" style="height:300px;margin-top: 5px;">
	<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">HISTORIA PRODUCTOS CLIENTE</p></h3>		
		<div style="padding: 5px 5px 5px 5px;" >
		<p class="loadingTraslados" style="display: none;" >Cargando..</p>
		
		<div class=" divscroll scroll noloadingTraslados trasladosData" style="display: inline-block !important;max-height:250px;
    overflow:auto;width: 1090px;"></div>
		
		</div>
		
	</div>
	<div class=" cuadroblanco" style="height:160px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">SELECCIONAR PRODUCTOS PEDIDO Y UBICACI&Oacute;N</p></h3>		
		
		<div class="divhead"><strong>UBICACI&Oacute;N LINEA:</strong><select name="ubi_id_linea" id="ubi_id_linea" class="ubicacionesData" style="margin:0px 0px 0px 0px;width: 350px" >
				
    		</select><span class="cabecera" style="color:#F00">*</span></div>
    		
		<div class="divhead"><strong>FUNCIONALIDAD:</strong><select name="func_id" id="func_id" class="funcionalidadesData" style="margin:0px 0px 0px 0px;width: 580px;" >
				
    		</select><span class="cabecera" style="color:#F00">*</span></div>
    	
    		
    	<div class="divhead"><strong>PRODUCTO:</strong><select name="prod_id" id="prod_id" class="productosData" style="margin:0px 0px 0px 0px;width: 580px;" >
				
    		</select><span class="cabecera" style="color:#F00">*</span></div>
   		
		<div class="divhead"><strong>CANTIDAD:</strong><input type="text" name="cantidad" id="cantidad" style="width:145px;height:30px ;background:#FF3;" onkeydown="return validateNum(event)" > </div>
		
		
	</div>
	
	<div class=" cuadroblanco" style="height:300px;margin-top: 5px;">
	<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">PRODUCTOS SELECCIONADOS</p></h3>		
		<div style="padding: 5px 5px 5px 5px;" class="divscroll scroll">
		
		
		<table class="table " style="margin-left:0px; ">
		
		<thead style="border: 1px solid black;background-color:#0101DF">
		<tr style="background-color:#0101DF;color:#FFFFFF">
			<td class="detailID" style="font-size:20px"><strong>ID</strong></td>
			<td class="detailPn" style="font-size:20px"><strong>PNUMBER </strong></td>
			<td class="detailDir" style="font-size:20px"><strong>DESCRIPCI&Oacute;N </strong></td>
			<td class="detailUbi" style="font-size:20px"><strong>UBICACI&Oacute;N </strong></td>
			<td class="detailCant" style="font-size:20px"><strong>CANTIDAD </strong></td>
			<td class="detailID" style="font-size:20px"><strong>TICKET </strong></td>
			
		</tr>
		</thead>
		<tbody id="fbody" class="scroll " style="border: 1px solid black">
		<%for(PedidoDetalleVO detallepedido: pedido.getDetallePedido()){%>
		<tr class='hov' >
			<td class="detailID" style="font-size:20px"><strong><%=detallepedido.getProducto().getId() %></strong></td>
			<td class="detailPn" style="font-size:20px"><strong><%=detallepedido.getProducto().getPn() %> </strong></td>
			<td class="detailDir" style="font-size:20px"><strong><%=detallepedido.getProducto().getDesc_corto() %> </strong></td>
			<td class="detailUbi" style="font-size:20px"><strong><%=detallepedido.getUbicacion().getNom_fisica() %> </strong></td>
			<td class="detailCant" style="font-size:20px"><strong><%=detallepedido.getCantidad() %> </strong></td>
			<td class="detailID" style="font-size:20px"><strong><%=detallepedido.getId_ticket() %> </strong></td>
			
		</tr>
		<% } %>
		</tbody>
		</table>
		</div>
		
	</div>
	
	<div class=" cuadroblanco" style="height:45px;margin-top: 5px;">
		<div class="bgrabar">
		
		  <button type="submit" name="grabar" id="grabar" class="btn btn-success" onclick="return validateSubmitGrabar()" >GRABAR PEDIDO</button>
		  <button type="submit" name="grabarOtroTicket" id="grabarOtroTicket" class="btn btn-success" onclick="return validateOtroTicket()" >OTRO TICKET MISMO CLIENTE MISMA DIRECCION</button>
		  <button type="submit" name="grabarMismoTicket" id="grabarMismoTicket" class="btn btn-success" onclick="return validateSubmit()" >OTRO PRODUCTO MISMO TICKET</button>
         
       </div> 
	</div>
	
</form>
</div><br><br><br>
<div class="footerapp">               
	<p style="float: left;"><i class="icon-user"></i>	
	<strong><%=Usu_nom %></strong></p>
	<button type="button" onclick="window.open('/003/','_blank')" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:2px;">MENU</button>
</div>
    
  </body>
</html>


