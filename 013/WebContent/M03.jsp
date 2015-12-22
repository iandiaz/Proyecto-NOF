<%@page import="VO.RangoEstructuraCobroVO"%>
<%@page import="VO.EstructuraCobroVO"%>
<%@page import="java.util.ArrayList"%>
<% 	

String modname=(String)request.getAttribute("modname");

String Usu_nom=(String)request.getAttribute("usuname");	

EstructuraCobroVO estructuraCobro= (EstructuraCobroVO)request.getAttribute("estructuraCobro");

ArrayList<RangoEstructuraCobroVO> rangos = estructuraCobro.getRangosEstCobro();

ArrayList<String> tipoestructura = (ArrayList<String>)request.getAttribute("tipoestructura");
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
	thead, tbody {
		 min-width:100%;
    	display: inline-block;
    	
	}
		select{
			
				}
	.inputRangos{
		width: 100px;
	}
	.tdrangos{
		width: 111px;
	}
	.inputFijoVariable{
		width: 120px;
	}
	.tdFijoVariable{
		width: 131px;
	}
	.tdTipoRango{
		width: 220px;
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
	
	 function validateSubmit(){
	    	
	    	var validate= true;
	    	var msg="ERRORES: \n";
	    	

	    	if(document.getElementById('estrcobro_observaciones').value==""){
	    		msg+="DEBE INGRESAR UNA OBSERVACION PARA LA ESTRUCTURA DE COBRO\n";
	    		validate=false;
	    	}
	    	if(document.getElementById('estrcobro_nombre').value==""){
	    		msg+="DEBE INGRESAR UN NOMBRE PARA LA ESTRUCTURA DE COBRO\n";
	    		validate=false;
	    	}
	    	
	    	if(document.getElementById('estrcobro_cxa').value==""){
	    		msg+="DEBE INGRESAR UN COSTO POR ADICION\n";
	    		validate=false;
	    	}
	    	
	    	
	    	
	    	<%
	    	if(rangos.size()>0)for(RangoEstructuraCobroVO rangoValidate:rangos ){
	    		String idrango=rangoValidate.getRango_id();
	    		%>
	    		
	    		//validamos tangos finales 
	    	<% }
	    	else{%>
				msg+="DEBE INGRESAR RANGOS PARA LA ESTRUCTURA DE COBRO\n";
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
 	function validateSubmitAgregar(){
 		
    	var validate= true;
    	var msg="ERRORES: \n";
    	

    	<%
    	if(rangos.size()>0)for(RangoEstructuraCobroVO rangoValidate:rangos ){
    		String idrango=rangoValidate.getRango_id();
    		%>
    		if(document.getElementById('<%=idrango%>_rangoFinal').value==""){
        		msg+="DEBE INGRESAR EL RANGO FINAL DEL RANGO N <%=idrango%>\n";
        		validate=false;
        	}
        	
    		//validamos tangos finales 
    	<% }
    	
    	%>
    	        	
    	if(validate){
    		if(submitting){
    			alert("INFORMACION YA ENVIADA");
    		}else{
    			disableSubmitB();
            	$('#agregarRango').click();
        		
    		}
    		
    	}
    	else{
    		enableSubmitB();
    		alert(msg);
    		
    	}
    	
    	
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
	  <p>N013.M.02.001</p>
	</div>
	<form method="get" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/013/'">VOLVER</button> 
	<div align="center" class="title">
		<p >MODIFICAR <%=modname %></p>
	</div>
</div>
<div class="content" >
  <form name="form1" id="formSub" method="post" action="M03"> 
    <input type="hidden" name="contrato_id" value="">
    
		<div class=" cuadroblanco" style="height:200px;margin-top: 5px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">ESTRUCTURA DE COBRO</p></h3>
		<div style="padding: 5px 5px 5px 5px">
		<div class="divhead"><strong>ID ESTRUCTURA:</strong><input type="text" value="${estructuraCobro.getEstrcobro_id()}" style="width:70px;height:30px ;background:#FFF;" readonly="readonly"></div> 
		<div class="divhead"><strong>CONTRATO:</strong><input type="text" value="${anexoContrato.getContrato().getContrato_nombre()}" style="width:290px;height:30px ;background:#FFF;" readonly="readonly"></div>		
		<div class="divhead"><strong>COSTO POR ADICION:</strong><input type="text" name="estrcobro_cxa" id="estrcobro_cxa" onkeydown="return validateNum(event)" value="${estructuraCobro.getEstrcobro_cxa()}" style="width:120px;height:30px ;background:#FF3;" maxlength="10" class="amarillo" ></div>
		<div class="divhead"><strong>TIPO CAMBIO:</strong><select name="tipo_cambio_id">
    									<% for(String tipoCam:tipocambios){
    											String tipc_ar[]=tipoCam.split(";;");
    										%>
    										<option value="<%=tipc_ar[0] %>" <%if(tipc_ar[0].equals(estructuraCobro.getTipo_cambio_id())){%> selected="selected" <%} %> ><%=tipc_ar[1] %></option>	
    									<% } %>
    									
    								
    								</select></div>
    	<div class="divhead"><strong>INDIVIDUAL/GRUPAL:</strong><select name="tipo_estcobro_id">
			<% for(int j =0; j<tipoestructura.size();j++){
    											String tipoestr_ar[]=tipoestructura.get(j).split(";;");
    										%>
    										<option value="<%=tipoestr_ar[0] %>" <%if(estructuraCobro.getTipo_estructuraC()!=null && estructuraCobro.getTipo_estructuraC().getId().equals(tipoestr_ar[0])){%> selected="selected" <%} %> ><%=tipoestr_ar[1] %></option>	
    									<% } %>
			
		</select></div>							
    	<div class="divhead"><strong>NOMBRE:</strong><input type="text" name="estrcobro_nombre" id="estrcobro_nombre" class="amarillo" maxlength="255" style="width:720px;height:30px ;background:#FF3;" value="${estructuraCobro.getEstrcobro_nombre()}"></div>
		<div class="divhead"><strong>OBSERVACION:</strong><input type="text" name="estrcobro_observaciones" id="estrcobro_observaciones" class="amarillo" maxlength="255" style="width:720px;height:30px ;background:#FF3;" value="${estructuraCobro.getEstrcobro_observaciones()}"></div>
		
		
		</div>
	</div>
	<table  class="table004det">
  <thead style="border-bottom: 1px solid black;background:#39F">
  <tr style="background:#39F">
   	<th scope="col" style="width: 50px;" >ID</th>
    <th scope="col" class="tdrangos" >INICIAL</th>
    <th scope="col" class="tdrangos" >FINAL</th>
    <th scope="col" class="tdFijoVariable">FIJO</th>
    <th scope="col" class="tdFijoVariable">VARIABLE</th>
  </tr>
  </thead>
  <tbody class="scroll">
  <% if(rangos!=null) for(int i =0; i<rangos.size();i++){ 
  		RangoEstructuraCobroVO rango = (RangoEstructuraCobroVO)rangos.get(i);
  		
  		boolean edit=false;
  		if(i==rangos.size()-1) edit=true;
  	%>
	  <tr>
    <td class="int" width="50px"><%=rango.getRango_id() %></td>
    <td class="int" align="center"><input type="text" value="<%=rango.getRango_inicial() %>" class="inputRangos" style="height:30px ;background:#FFF;" readonly="readonly" onkeydown="return validateNum(event)"></td>
    <td class="int" align="center"><input type="text" value="<%=rango.getRango_final() %>" name="<%=rango.getRango_id() %>_rangoFinal" id="<%=rango.getRango_id() %>_rangoFinal"   maxlength="8" <%if(edit){%> class="amarillo inputRangos" style="height:30px ;background:#FF3;" <%}else{%> class="inputRangos" style="height:30px ;background:#FFF;" readonly="readonly" <% } %> onkeydown="return validateNum(event)"></td>
   
    <td class="int tdFijoVariable" align="center"><input type="text" name="<%=rango.getRango_id() %>_rangoCFijo" value="<%=rango.getRango_costo_fijo()%>" class="inputFijoVariable" style="height:30px ;background:#FF3;" maxlength="10" onkeydown="return validateNum(event)" ></td>
    <td class="int tdFijoVariable" align="center"><input type="text" name="<%=rango.getRango_id() %>_rangoCVariable" value="<%=rango.getRango_costo_variable() %>" class="inputFijoVariable" style="height:30px ;background:#FF3;" maxlength="10" onkeydown="return validateNum(event)" ></td>
   <%if(edit){%>  <td class="int tdFijoVariable" align="center"><button type="submit" name="deteleRango" id="deteleRango" class="btn btn-danger" value="<%=rango.getRango_id() %>" ><i class="icon-remove"></i></button></td> <%}%>
   
  </tr>
  <%
   } %>
    
  
         
     
  </tbody>
</table> 
	
	
	
	
	<div class=" cuadroblanco divgrabar" style="height:45px;margin-top: 5px;">
		<div class="bgrabar">
		 <button type="submit" name="agregarRango" id="agregarRango" class="btn btn-success" style="display: none;">AGREGAR</button>
		 <button type="button"  class="btn btn-success" onclick="validateSubmitAgregar()" >AGREGAR</button>
		 
          <button type="submit" name="grabarEstructura" id="grabar" class="btn btn-success" onclick="return validateSubmit()" >GRABAR</button>
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
    
  </body>
</html>


