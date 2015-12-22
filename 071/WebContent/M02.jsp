<%@page import="VO.EstadosVigNoVigVO"%>
<%@page import="VO.OcDetalleVO"%>
<%@page import="VO.OcVO"%>
<%@page import="VO.CorreoVO"%>
<%@page import="VO.PedidoDetalleVO"%>
<%@page import="VO.PedidoVO"%>
<%@page import="VO.EmpresaVO"%>
<%@page import="java.util.ArrayList"%>
<% 	
String modname=(String)request.getAttribute("modname");
String Usu_nom=(String)request.getAttribute("usuname");


ArrayList<String> tipomonedas = (ArrayList<String>)request.getAttribute("tipocambios");
ArrayList<EstadosVigNoVigVO> estadosvignovig = (ArrayList<EstadosVigNoVigVO>)request.getAttribute("estadosvignovig");



OcVO OC =(OcVO) request.getAttribute("OC");
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
		padding-left: 2px;
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
		width: 100px;
	}
	td.detailDir{
		width: 470px;
	}
	td.detailUbi{
		width: 200px;
	}
	td.detailFec{
		width: 120px;
	}
	
	td.checkUbi{
		width: 30px;
	}
	td.detailProd{
		width: 120px;
	}
	td.detailBid{
		width: 320px;
	}
		
		
	@media screen and (min-width: 700px) {
	.cuadroblanco{
			box-shadow:0px 2px 5px 0px rgba(50, 50, 50, 0.75);
			border: 1px solid black;
			min-height:100%; height:330px;  
		min-width: 350px;
			max-width:1200px;
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
    	
    	getSpecialBidSelect();
    	getContactosSelect('${OC.getCliente().getId()}');
    	
    	$('#estados_vig_novig_id').select2();
    	$('#estados_vig_novig_id').select2("val","<%=OC.getEstadoVigNoVig().getId()%>");
    	
    	$('#tipo_moneda').select2();
    	$('#tipo_moneda').select2("val","<%=OC.getTipomoneda().getId() %>");
    	
    	
    	$( "#tcambio_ref" ).on( "blur", function() {
    		<% for(OcDetalleVO pedido_detalle : OC.getDetalle_oc()){ %>
    			var dolares = $('#precio_<%=pedido_detalle.getId()%>').val(); 
    			if(dolares=="")dolares=0;
    			var tcambio=$('#tcambio_ref').val();
    			if(tcambio=="")tcambio=0;
    			
    			var pesos = Math.round(dolares*tcambio);
        		$('#preciocl_<%=pedido_detalle.getId()%>').val(pesos);
        		
        	<%}%>
    	});
    	
    	
    	
	});
    	
    
    
    
    function getSpecialBidSelect(){
    	
    	<% for(OcDetalleVO pedido_detalle : OC.getDetalle_oc()){ %> 
    	
	    	$.post( "getSBSelect", {id_prod: <%=pedido_detalle.getProducto().getId()%>})
	        .done(function( data ) {
	        	$( "#special_bid_<%=pedido_detalle.getId()%>" ).html( (data) );
	        	$( "#special_bid_<%=pedido_detalle.getId()%>" ).select2();
	        	if("<%=pedido_detalle.getSb_id()%>"!="0"){
	        		$( "#special_bid_<%=pedido_detalle.getId()%>" ).select2("val","<%=pedido_detalle.getSb_id()%>");
	        	}
	        	
	        	
	    	 });
    	<% } %>
    	
   	 	
   	}  
    
    function changeSb(sb,id_prod,iddet,cantidad){
    	
    	if($(sb).val()!=""){
    		$.post( "getSBPrecio", {id_prod: id_prod,sb_id:$(sb).val()})
            .done(function( precio ) {
            	
            	if(precio!=null && precio!="" && precio!="null"){
            		$('#precio_'+iddet).val(Number(precio)*Number(cantidad));
                	$('#precio_'+iddet).prop('readonly',true);
                	$('#precio_'+iddet).css('background-color','#FFF');
                	$('#special_bid_name_'+iddet).val($(sb).find('option:selected').text());
                	
                	
                	$( "#tcambio_ref" ).blur();
                		
            	}
            	
    	
        	 });
    	}else{
    		$('#precio_'+iddet).prop('readonly',false);
        	$('#precio_'+iddet).css('background-color','#FF3');
        	
    	}
    	
    }
    
	function getContactosSelect(id_cliente){
    	
    	$.post( "getContactosAsync", {id_cliente:id_cliente, estadovignovig:1})
        .done(function( data ) {
        	
        	$('#id_contacto').empty();
        	
        	var contactos_arr = $.parseJSON(data);
      		
        	$('#id_contacto').append($('<option>', {
                value: '',
                text: 'SELECCIONAR'
                }));
        	
        	$.each( contactos_arr, function( key, contacto ) {
      			
        		 $('#id_contacto').append($('<option>', {
                     value: contacto.id,
                     text: contacto.nombre+' '+contacto.ape_p+' '+contacto.ape_m
                     }));
        	 
      		});
        	
        	$('#id_contacto').select2();
        	<%if(OC.getContacto()!=null){%>
        	$('#id_contacto').select2("val","<%=OC.getContacto().getId()%>");
        	<% }else{%>
        	$('#id_contacto').select2("val","");	
        	<%} %>
        	
        	
    	 });
     	
   	}

    function validateSubmit(){
        
    	var validate= true;
    	var msg="";
    	
    	if($('#tcambio_ref').val()=="" || $('#tcambio_ref').val()==null){
    		msg+=" - DEBE SELECCIONAR UN TIPO CAMBIO DE REFERENCIA\n";
    		validate=false;
    	}
    	if($('#correo_aviso').val()=="" || $('#correo_aviso').val()==null){
    		msg+=" - DEBE SELECCIONAR UN DESTINATARIO DE CORREO ELECTRONICO\n";
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
	  <p>N071.M.02.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/071/M01'">VOLVER</button> 
	<div align="center" class="title">
		<p>MODIFICAR <%=modname %></p>
	</div>
</div>
<div class="content" >
  <form action="M02" name="form1" method="post" id="form1" > 
  
  <input type="hidden" name="id_cliente" id="id_cliente" value="<%=OC.getCliente().getId() %>" >
  <input type="hidden" name="id_proveedor" id="id_proveedor"  value="<%=OC.getProveedor().getId() %>" >
  <input type="hidden" name="id_direccion" id="id_direccion"  value="<%=OC.getDireccion().getId() %>" >
  <input type="hidden" name="id" value="<%=OC.getId() %>" >
  
  	<div class=" cuadroblanco" style="height:205px;padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>ID:</strong><input type="text"  style="width:65px;height:30px ;background:#FFF;" readonly="readonly" value="<%=OC.getId() %>" ></div>
		<div class="divhead"><strong>NRO:</strong><input type="text"  style="width:85px;height:30px ;background:#FFF;" readonly="readonly" value="<%=OC.getNro()==null? "":OC.getNro() %>"  ></div>
		
		<div class="divhead"><strong>CLIENTE:</strong><input type="text"  style="width:345px;height:30px ;background:#FFF;" readonly="readonly" value="<%=OC.getCliente().getNombre_nof() %>" ></div>
		<div class="divhead"><strong>RUT CLIENTE:</strong><input type="text"  style="width:155px;height:30px ;background:#FFF;" readonly="readonly" value="${OC.getCliente().getRut()}" ></div>
		
		<div class="divhead"><strong>PROVEEDOR:</strong><input type="text"  style="width:345px;height:30px ;background:#FFF;" readonly="readonly" value="<%=OC.getProveedor().getNombre_nof() %>" ></div>
		<div class="divhead"><strong>TIPO MONEDA:</strong><select name="tipo_moneda" id="tipo_moneda">
    									<% for(String tipoCam:tipomonedas){
    											String tipc_ar[]=tipoCam.split(";;");
    										%>
    										<option value="<%=tipc_ar[0] %>" ><%=tipc_ar[1] %></option>	
    									<% } %>
    									
    								</select></div>
		
		<div class="divhead"><strong>TIPO CAMBIO REFERENCIAL:</strong><input type="text" id="tcambio_ref" name="tcambio_ref"  style="width:145px;height:30px ;background:#FF3;" value="<%=OC.getTcambio_ref() %>" > <span class="cabecera" style="color:#F00">*</span></div>
		
		<div class="divhead"><strong>DIRECCI&Oacute;N:</strong><input type="text"  style="width:545px;height:30px ;background:#FFF;" readonly="readonly" value="<%=OC.getDireccion().getDireccion() %>" ></div>
		<div class="divhead"><strong>COMUNA:</strong><input type="text"  style="width:200px;height:30px ;background:#FFF;" readonly="readonly" value="<%=OC.getDireccion().getComu_nombre() %>" ></div>
		<div class="divhead"><strong>REGI&Oacute;N:</strong><input type="text"  style="width:400px;height:30px ;background:#FFF;" readonly="readonly" value="<%=OC.getDireccion().getRegi_nombre() %>" ></div>
		<div class="divhead"><strong>CONTACTO:</strong><select name="id_contacto" id="id_contacto" style="width: 360px">
															<option value="" >SELECCIONAR</option>	
    													</select></div>
    	<div class="divhead"><strong>ESTADO:</strong><select name="estados_vig_novig_id" id="estados_vig_novig_id" style="width: 130px">
    									<% for(EstadosVigNoVigVO est:estadosvignovig){
    										
    										%>
    										<option value="<%=est.getId() %>" ><%=est.getNombre() %></option>	
    									<% } %>
    									
    								</select></div>												
		<div class="divhead"><strong>OBS:</strong><input type="text" id="oc_obs" name="oc_obs" maxlength="50"  style="width:515px;height:30px ;background:#FF3;" value="<%=OC.getObs() %>" ></div>
		<div class="divhead"><strong>FECHA OC:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;" value="<%=OC.getFec_crea() %>" readonly="readonly" ></div>
		    													
		
	</div>
		<div class=" cuadroblanco" style="height:300px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DETALLE PEDIDO</p></h3>		
		
		<div style="padding: 5px 5px 5px 5px;" class="divscroll scroll">
			<table class="table noloading" style="margin-left:0px; ">
		
		<thead style="border: 1px solid black;background-color:#0101DF">
		<tr style="background-color:#0101DF;color:#FFFFFF">
			<td class="detailID" style="font-size:20px"><strong>ID PROD</strong></td>
			<td class="detailProd" style="font-size:20px"><strong>PNUMBER</strong></td>
			<td class="detailFec" style="font-size:20px"><strong>CANTIDAD</strong></td>
			
			<td class="detailUbi" style="font-size:20px"><strong>UBICACI&Oacute;N</strong></td>
			<td class="detailBid" style="font-size:20px"><strong>SPECIAL BID</strong></td>
			<td class="detailFec" style="font-size:20px"><strong>PRECIO</strong></td>
			<td class="detailFec" style="font-size:20px"><strong>PRECIO $</strong></td>
			
		</tr>
		</thead>
		<tbody id="fbody" class="scroll ticketsData" style="border: 1px solid black">
		<% for(OcDetalleVO oc_detalle : OC.getDetalle_oc()){ %>
			<tr class='hov'>
			<td class="detailID" style="font-size:20px"><strong><%=oc_detalle.getProducto().getId() %></strong> 
				<input type="hidden" name="id_det_pedidos[]" value="<%=oc_detalle.getId() %>" >
				<input type="hidden" name="id_prod_<%=oc_detalle.getId() %>" value="<%=oc_detalle.getProducto().getId() %>" >
				<input type="hidden" name="cantidad_<%=oc_detalle.getId() %>" value="<%=oc_detalle.getCantidad() %>" >
				</td>
			<td class="detailProd" style="font-size:20px"><strong><%=oc_detalle.getProducto().getPn() %></strong></td>
			<td class="detailFec" style="font-size:20px"><strong><%=oc_detalle.getCantidad()%></strong></td>
			
			<td class="detailUbi" style="font-size:20px"><strong><%=oc_detalle.getUbicacion().getNom_fisica() %></strong></td>
			
			<td class="detailBid" style="font-size:20px"><select id="special_bid_<%=oc_detalle.getId() %>" name="special_bid_<%=oc_detalle.getId() %>" style="width: 310px" onchange="changeSb(this,'<%=oc_detalle.getProducto().getId() %>','<%=oc_detalle.getId() %>','<%=oc_detalle.getCantidad()%>')">
    													 </select>
    													 <input type="hidden" name="special_bid_name_<%=oc_detalle.getId() %>" id="special_bid_name_<%=oc_detalle.getId() %>" >
    		</td>
			
			
			<td class="detailFec" style="font-size:20px"><input type="text" value="<%=oc_detalle.getPrecio_pesos() %>"  id="precio_<%=oc_detalle.getId() %>" name="precio_<%=oc_detalle.getId() %>"  style="width:145px;height:30px ;background:#FF3;" onblur="$( '#tcambio_ref' ).blur();" onkeydown="return validateNum(event)"></td>
			<td class="detailFec" style="font-size:20px"><input type="text" value="<%=oc_detalle.getPrecio_pesos() %>" id="preciocl_<%=oc_detalle.getId() %>" name="preciocl_<%=oc_detalle.getId() %>"  style="width:145px;height:30px ;background:#FF3;" onkeydown="return validateNum(event)" ></td>
			
		</tr>
		<% } %>
		        	
		</tbody>
		</table>
	
		</div>
	</div>
	
	
	
	<div class=" cuadroblanco" style="height:130px;margin-top: 5px;">
	<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">ENVIAR CORREO A</p></h3>
	<div style="padding: 5px 5px 5px 5px;" class="divscroll scroll">
		<div class="divhead"><textarea maxlength="195"   name="correo_aviso" id="correo_aviso" class="amarillo" style="width:730px;height:70px ;background:#FF3;text-transform:uppercase;" ><%=OC.getCorreoa()==null?"":OC.getCorreoa() %></textarea> <span class="cabecera" style="color:#F00">*</span></div>
	
	</div>
		
	</div>
	<div class=" cuadroblanco" style="height:45px;margin-top: 5px;">
		<div class="bgrabar">
		
		  <button type="submit" name="grabar" id="grabar" class="btn btn-success" onclick="return validateSubmit()" >GRABAR</button>
		  
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


