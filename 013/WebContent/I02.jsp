<%@page import="VO.MaquinaContadorVO"%>
<%@page import="VO.UbicacionVO"%>
<%@page import="VO.EstructuraCobroVO"%>
<%@page import="VO.AnexoContratoVO"%>
<%@page import="java.util.ArrayList"%>
<% 	

String modname=(String)request.getAttribute("modname");
String contrato_id=(String)request.getAttribute("contrato_id");
String cliente_firma=(String)request.getAttribute("cliente_firma");
String cliente_rut=(String)request.getAttribute("cliente_rut");
String CONT_NOMBRE=(String)request.getAttribute("CONT_NOMBRE");
String contrato_fecha_firma=(String)request.getAttribute("contrato_fecha_firma");


String Usu_nom=(String)request.getAttribute("usuname");	

String fecha=(String)request.getAttribute("fecha");

AnexoContratoVO anexoContrato=(AnexoContratoVO)request.getAttribute("anexoContrato");

ArrayList<String> tipocambios = (ArrayList<String>)request.getAttribute("tipocambios");
	
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
 	
    <script src="lib/jquery-ui/js/jquery-1.10.2.js"></script>
    <link rel="stylesheet" href="lib/jquery-ui/css/ui-lightness/jquery-ui-1.10.4.custom.css">
    <script src="lib/jquery-ui/js/jquery-ui-1.10.4.custom.js"></script>

	<link rel="stylesheet" href="lib/fancy/jquery.fancybox.css?v=2.1.5" type="text/css" media="screen" />
	<script type="text/javascript" src="lib/fancy/jquery.fancybox.pack.js?v=2.1.5"></script>
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
	  
	    height: 120px;
	    overflow-y: scroll;
	}
	
		select{
			
				}
		
	@media screen and (min-width: 700px) {
	.cuadroblanco{
			box-shadow:0px 2px 5px 0px rgba(50, 50, 50, 0.75);
			border: 1px solid black;
			min-height:100%; height:330px;  
		min-width: 350px;
			max-width:1000px;
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
        // Handler for .ready() called.
        $( ".tooltipUI" ).tooltip();
		$( "#fecha_desde,#fecha_hasta" ).datepicker({ dateFormat: "dd-mm-yy", monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
                                      monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
                                      dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
                                      dayNamesShort: ['Dom','Lun','Mar','Mie','Juv','Vie','Sab'],
                                      dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa'],
                                      changeYear: true,
                                      yearRange: "-3:+3"
                                      });
		
		
		
		
		$('.various').click(function (e) {
	     
			e.preventDefault();
	        $.fancybox({
	            href: this.href,
	            type: 'iframe',
	            'title'			: this.title,
	            fitToView	: true,
	    		autoWidth: true,
	    		autoSize	: false,
	    		closeClick	: false,
	    		openEffect	: 'none',
	    		closeEffect	: 'none',
	    		
	    		padding:0, margin:0,
	    		iframe : {
	    	        scrolling : 'no'
	    	    },
	    	    'titlePosition'	:	'over'
	        }); // fancybox
	    }); // click
		
	      
	 
	    
	    
	       });
	
	
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
    
    var submitting;
    function disableSubmitB(){
    	//submitting=true;
    	$(".divload").show();
    	$(".divgrabar").hide();
    	//document.getElementById('grabar').disabled = true;
    	document.getElementById('grabar').value = "ESPERA";
    	
    	
    	
    }
	function enableSubmitB(){
		submitting=false;
    	$(".divload").hide();
    	$(".divgrabar").show();
    	//document.getElementById('grabar').disabled = false;
    	document.getElementById('grabar').value = "GRABAR";
    	
    	
    }
	function loading(){
		//alert('cargando');
		
	true;	
	}
	
    function validateSubmit(){
    	
    	var validate= true;
    	var msg="ERRORES: \n";
    	

    	if(document.getElementById('cargoFijoUnico').value==""){
    		msg+="DEBE INGRESAR UN CARGO FIJO UNICO\n";
    		validate=false;
    	}
    	if(document.getElementById('observacion').value==""){
    		msg+="DEBE INGRESAR UNA OBSERVACION\n";
    		validate=false;
    	}
    	
    	
    	
    	<%
    	if(anexoContrato.getEstructurasCobro().size()==0){ %>
			msg+="DEBE INGRESAR ESTRUCTURAS DE COBRO\n";
			validate=false;

    	<% }
    	%>
    	        	
    	if(validate){
    		if(submitting){
    			alert("INFORMACION YA ENVIADA");
    		}else{
    			disableSubmitB();
            	
        		return true;
    			//return false;
    		}
    		
    	}
    	else{
    		enableSubmitB();
    		alert(msg);
    		return false;
    	}
    	
    	
    }
    </script>
    
    
     <script>
     
     
   $(function(){
    $("#todos").click(function(e) {
     if($("#todos").is(":checked") ){
                     $('.dale').prop('checked',true);
     }else{
      $('.dale').prop('checked',false);
     }
                });
   });
   
   
   function callAllEstrcToUbis(func){
	   
	   $('.divgrabar').hide();
       $('.divload').show();
	   
	   $.post( "procAllEstrToUbis", { func: func})
       .done(function( data ) {
       	    location.reload();
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
	  <p>N013.I.02.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESI�N</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/013/ianexo_search'">VOLVER</button> 
	<div align="center" class="title" >
		<p >INGRESAR <%=modname %></p>
	</div>
</div>
<div class="content" >
  <form action="I02" name="form1" id="formform" method="post"> 
    <input type="hidden" name="contrato_id" value="<%=contrato_id %>">
    
	

	<div class=" cuadroblanco" style="height:200px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CLIENTE</p></h3>
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>GRUPO:</strong><input type="text" value="${anexoContrato.getEmpresa().getGrupos_nombre()}" style="width:200px;height:30px ;background:#FFF;" readonly="readonly"></div> 
		<div class="divhead"><strong>EMPRESA:</strong><input type="text" value="${anexoContrato.getEmpresa().getEmpresas_nombrenof()}" style="width:500px;height:30px ;background:#FFF;" readonly="readonly"></div>		
		<div class="divhead"><strong>RAZON SOCIAL:</strong><input type="text" value="${anexoContrato.getEmpresa().getEmpresas_razonsocial()}" style="width:500px;height:30px ;background:#FFF;" readonly="readonly""></div>
		<div class="divhead"><strong>RUT:</strong><input type="text" value="${anexoContrato.getEmpresa().getEmpresas_rut()}" style="width:145px;height:30px ;background:#FFF;" disabled="disabled" ></div>
		<div class="divhead"><strong>FIRMANTE CLIENTE:</strong><input type="text" value="<%=cliente_firma %>" style="width:510px;height:30px ;background:#FFF;" disabled></div>
		<div class="divhead"><strong>RUT:</strong><input type="text" value="<%=cliente_rut %>" style="width:145px;height:30px ;background:#FFF;" disabled></div>
		<div class="divhead"><strong>CONTACTO:</strong><input type="text" value="<%=CONT_NOMBRE %>" style="width:500px;height:30px ;background:#FFF;" disabled></div>
		<div class="divhead"><strong>FECHA FIRMA:</strong><input type="text" value="<%=contrato_fecha_firma %>" style="width:130px;height:30px ;background:#FFF;" disabled></div>
		</div>
	</div>
	
	<div class=" cuadroblanco" style="height:200px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS DEL ANEXO</p></h3>
		<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead"><strong>CORRELATIVO:</strong><input type="text" style="width:80px;height:30px ;background:#FFF;" name="gv_id" id="gv_id" value="${anexoContrato.getAnc_id()}" readonly="readonly"></div>
		<div class="divhead"><strong>FECHA:</strong><input type="text" style="width:130px;height:30px ;background:#FFF;" name="gv_fecha" id="gv_fecha" value="<%=fecha %>" readonly="readonly" ></div>
		<div class="divhead"><strong>FECHA DESDE:</strong><input type="text" class="amarillo" style="width:130px;height:30px ;background:#FF3;" name="fecha_desde" id="fecha_desde" value="${anexoContrato.getFecha_desde()}" readonly="readonly" ></div>
		<div class="divhead"><strong>FECHA HASTA:</strong><input type="text" class="amarillo" style="width:130px;height:30px ;background:#FF3;" name="fecha_hasta" id="fecha_hasta" value="${anexoContrato.getFecha_hasta()}" readonly="readonly" ></div>
		<div class="divhead"><strong>CARGO FIJO MENSUAL UNICO ANEXO CONTRATO:</strong><input type="text" name="cargoFijoUnico" id="cargoFijoUnico" class="amarillo" style="width:130px;height:30px ;background:#FF3;" value="${anexoContrato.getCargoFijoUnico()}" onkeydown="return validateNum(event)" ></div>
		<div class="divhead"><strong>TIPO DE CAMBIO:</strong><select name="tipo_cambio_id">
    									<% for(String tipoCam:tipocambios){
    											String tipc_ar[]=tipoCam.split(";;");
    										%>
    										<option value="<%=tipc_ar[0] %>" <%if(tipc_ar[0].equals(anexoContrato.getTipoCambio_id())){%> selected="selected" <%} %> ><%=tipc_ar[1] %></option>	
    									<% } %>
    									
    								
    								</select></div>	
    	<div class="divhead"><strong>NOMBRE:</strong><input type="text" name="anc_nombre" id="anc_nombre" class="amarillo" maxlength="70" style="width:720px;height:30px ;background:#FF3;" value="${anexoContrato.getNombre()}" ></div>								
		<div class="divhead"><strong>OBSERVACION:</strong><input type="text" name="observacion" id="observacion" class="amarillo" maxlength="70" style="width:720px;height:30px ;background:#FF3;" value="${anexoContrato.getObservacion()}" ></div>
		</div>
		 
	</div>
	
	<table  class="table004det">
  <thead style="border-bottom: 1px solid black; background:#39F ;">
  <tr style="border-bottom: 1px solid black;">
    <th scope="col" colspan="3" style="width: 1000px" >ESTRUCTURAS DE COBRO INDIVIDUALES</th>
    
  </tr>
 
  <tr>
    <th scope="col" style="width: 50px;" >ID</th>
    <th scope="col" style="width: 450px;" >ESTRUCTURA DE COBRO NOMBRE</th>
    <th scope="col" style="width: 450px;" >ESTRUCTURA DE COBRO OBS</th>
  </tr>
  </thead>
  <tbody class="scroll" >
  <% for(EstructuraCobroVO estructura: anexoContrato.getEstructurasCobro()){
	  if(estructura.getTipo_estructuraC().getId().equals("2")) continue;
	  %>
	<tr onclick="location='I03?estr=<%=estructura.getEstrcobro_id() %>'">
    	<td class="int" width="50px"><%=estructura.getEstrcobro_id() %></td>
    	<td class="int" width="450px"><%=estructura.getEstrcobro_nombre()%></td>
    	<td class="int" width="450px"><%=estructura.getEstrcobro_observaciones()%></td>
    	<td class="int" width="50px"><button type="submit" name="deteleEstructura" id="deteleEstructura" class="btn btn-danger" value="<%=estructura.getEstrcobro_id() %>" ><i class="icon-remove"></i></button></td>
     
  	</tr>
  <% } %>
    
  
         
        
  </tbody>
</table> 

<table  class="table004det">
  <thead style="border-bottom: 1px solid black; background:#39F ;">
  <tr style="border-bottom: 1px solid black;">
    <th scope="col" colspan="3" style="width: 1000px" >ESTRUCTURAS DE COBRO GRUPALES</th>
    
  </tr>
 
  <tr>
    <th scope="col" style="width: 50px;" >ID</th>
    <th scope="col" style="width: 450px;" >ESTRUCTURA DE COBRO NOMBRE</th>
    <th scope="col" style="width: 450px;" >ESTRUCTURA DE COBRO OBS</th>
  </tr>
  </thead>
  <tbody class="scroll">
  <% for(EstructuraCobroVO estructura: anexoContrato.getEstructurasCobro()){
	  if(estructura.getTipo_estructuraC().getId().equals("1")) continue;
	  %>
	<tr onclick="location='I03?estr=<%=estructura.getEstrcobro_id() %>'" >
    	<td class="int" width="50px"><%=estructura.getEstrcobro_id() %></td>
    	<td class="int" width="450px"><%=estructura.getEstrcobro_nombre()%></td>
    	<td class="int" width="450px"><%=estructura.getEstrcobro_observaciones()%></td>
    	<td class="int" width="50px"><button type="submit" name="deteleEstructura" id="deteleEstructura" class="btn btn-danger" value="<%=estructura.getEstrcobro_id() %>" ><i class="icon-remove"></i></button></td>
    
  	</tr>
  <% } %>
    
  
         
        
  </tbody>
</table> 

<table  class="table004det">
  <thead style="border-bottom: 1px solid black;background:#39F ;">
  <tr>
    <th scope="col" style="width: 50px;" >ID</th>
    <th scope="col" style="width: 415px;" >UBICACION FISICA</th>
    <th scope="col" style="width: 410px;" >UBICACION FACTURACION</th>
    <th scope="col" style="width: 80px;" >	<a href="javascript:void(0)" onclick="callAllEstrcToUbis('add')"><i class="icon-plus" style="color: #5cb85c" ></i></a> 
    										<a href="javascript:void(0)" onclick="callAllEstrcToUbis('remove')" ><i class="icon-remove" style="color: #d43f3a" ></i></a></th>
  </tr>
  
  </thead>
  <tbody class="scroll">
  
    <%
    String mod_actual="";
   	
        for(int i =0; i<anexoContrato.getMaquinasContadores().size(); i++){ 
        	 
        	MaquinaContadorVO maqCon = anexoContrato.getMaquinasContadores().get(i);
        	
        	boolean selected=false;
        	String estructurasAsignadas="";
        	String color="";
        	//verificamos si el anexo tiene la maquinaContador seleccionada
        	if(anexoContrato.getMaquinasContadores().contains(maqCon)){
        		selected=true;
    		}
        	
        	
        	//contamos las estructuras que tiene asociada la ubicacion
        	
        	for(EstructuraCobroVO estr: anexoContrato.getEstructurasCobro()){
        		if(estr.getMaquinasContador().contains(maqCon)){
        			
        			if(estructurasAsignadas.equals(""))estructurasAsignadas=estr.getEstrcobro_id();
        			else estructurasAsignadas+=","+estr.getEstrcobro_id();
        			
        		}
        		
        	}
        	
        	if(estructurasAsignadas.equals("")) color="#d43f3a";///red 
        	else color="#428bca"; //blue
        	 
  %>
  <tr>
    <td class="int" width="50px"><%=maqCon.getUbi_id()%></td>
    <td class="int" width="415px"><%=maqCon.getUbi_fisica()%> - <%=maqCon.getId_activo()%> - <%=maqCon.getTp_tc_nombre()%></td>
    <td class="int" width="410px"><%=maqCon.getDescripcion()%></td>
    <td class="int" width="80px"><span id="<%=maqCon.getUbi_id()%>_<%=maqCon.getActivo_tptc_id()%>_setEstr"><a  data-fancybox-type="iframe" title="CONFIGURA LAS ESTRUCTURAS DE COBRO ASOCIADAS A LA UBICACI&OacuteN <%=maqCon.getUbi_id()%>." class="various tooltipUI "  href="ubiestr?ubi_id=<%=maqCon.getUbi_id()%>&activo_tptc_id=<%=maqCon.getActivo_tptc_id()%>" ><i class="icon-cog" style="color: <%=color%>"></i></a>&nbsp;<span id="<%=maqCon.getUbi_id()%>_nestructuras"><%=estructurasAsignadas %></span></span></td>
    
  </tr>
         
        <%  }
         
         %>
  </tbody>
</table> 
	

	<div class=" cuadroblanco divgrabar" style="height:45px;margin-top: 5px;">
		<div class="bgrabar">
		 <button type="submit" name="addestructura" id="addestructura" class="btn btn-success" onclick="DisableButton(this);" >AGREGAR ESTRUCTURA</button>
          <button type="submit" name="grabar" id="grabar" class="btn btn-success" onclick="return validateSubmit()" >GRABAR</button>
       	</div> 
	</div>
	<div class=" cuadroblanco divload" style="height:45px;margin-top: 5px;display: none;">
		
       	<div class="bgrabar">
		 <button type="button" class="btn btn-success" >GRABANDO POR FAVOR ESPERA</button>
          
       	</div> 
	</div>
</form>
</div><br><br><br>
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
    
    
<script>
   function DisableButton(b)
   {
      
   }
</script>
    
  </body>
</html>

