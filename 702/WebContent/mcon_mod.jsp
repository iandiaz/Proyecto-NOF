<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import ="java.util.*" %>
<% 


String peri_tc_id_last =(String)request.getAttribute("peri_tc_id_last");
String empresas_id =(String)request.getAttribute("empresas_id");
String empresas_razonsocial =(String)request.getAttribute("empresas_razonsocial");
String peri_tc_fdesde =(String)request.getAttribute("peri_tc_fdesde");
String peri_tc_fhasta =(String)request.getAttribute("peri_tc_fhasta");
String peri_ffac =(String)request.getAttribute("peri_ffac");
String empresas_nombrenof =(String)request.getAttribute("empresas_nombrenof");
String[] ar_estados =(String[]) request.getAttribute("ar_estados");
String estados_vig_novig_id =(String)request.getAttribute("estados_vig_novig_id");
String fec_limite=(String)request.getAttribute("fec_limite");

String user_crea=(String)request.getAttribute("user_crea"); 
String user_mod=(String)request.getAttribute("user_mod"); 
String fec_crea=(String)request.getAttribute("fec_crea"); 
String fec_mod=(String)request.getAttribute("fec_mod");

String Usu_nom=(String)request.getAttribute("usuname");

String peri_tc_correlativo_actual=null;
String peri_tc_fechad_actual=null;
String peri_tc_fechah_actual=null;


String[] ar_periodos =(String[]) request.getAttribute("ar_periodos");
String[] periodos_para_tc=(String[])request.getAttribute("periodos_para_tc");
String[] periodosfechas_para_tc=(String[])request.getAttribute("periodosfechas_para_tc");

String id_per=(String)request.getAttribute("id_per");

ArrayList<String> activos_actual =(ArrayList<String>) request.getAttribute("activos_actual");


String[] arActivos =(String[]) request.getAttribute("ar_activos");


ArrayList<String> cont0_activo =(ArrayList<String>) request.getAttribute("cont0_activo");
ArrayList<String> cont0_values =(ArrayList<String>) request.getAttribute("cont0_values");

ArrayList<String> cont1_activo =(ArrayList<String>) request.getAttribute("cont1_activo");
ArrayList<String> cont1_values =(ArrayList<String>) request.getAttribute("cont1_values");


ArrayList<String> cont2_activo =(ArrayList<String>) request.getAttribute("cont2_activo");
ArrayList<String> cont2_values =(ArrayList<String>) request.getAttribute("cont2_values");

ArrayList<String> cont3_activo =(ArrayList<String>) request.getAttribute("cont3_activo");
ArrayList<String> cont3_values =(ArrayList<String>) request.getAttribute("cont3_values");

ArrayList<String> cont4_activo =(ArrayList<String>) request.getAttribute("cont4_activo");
ArrayList<String> cont4_values =(ArrayList<String>) request.getAttribute("cont4_values");

ArrayList<String> cont5_activo =(ArrayList<String>) request.getAttribute("cont5_activo");
ArrayList<String> cont5_values =(ArrayList<String>) request.getAttribute("cont5_values");

ArrayList<String> cont6_activo =(ArrayList<String>) request.getAttribute("cont6_activo");
ArrayList<String> cont6_values =(ArrayList<String>) request.getAttribute("cont6_values");


    %>
<!DOCTYPE html>
<html lang="en"><head>
    <meta charset="utf-8">
    <title>New Office -  Toma de Contadores</title>
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
	  
	    height: 232px;
	    overflow-y: scroll;
	}
	thead, tbody {
		 min-width:100%;
    	display: inline-block;
    	
	}
			select{
			width:225px;
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
		width: 70px;
	}
	td.detailActivo{
		width: 300px;
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
                        
                         $('#id_per').on('change', function() {
                          
                        	 $('#formSub').submit();
                                
       					 });
                         
                         document.getElementById('p2t').innerHTML=formatNumberSeparadorMiles(Number(document.getElementById('p2t').innerHTML));
                         document.getElementById('p3t').innerHTML=formatNumberSeparadorMiles(Number(document.getElementById('p3t').innerHTML));
                         document.getElementById('p4t').innerHTML=formatNumberSeparadorMiles(Number(document.getElementById('p4t').innerHTML));
                         document.getElementById('p5t').innerHTML=formatNumberSeparadorMiles(Number(document.getElementById('p5t').innerHTML));
                         document.getElementById('p6t').innerHTML=formatNumberSeparadorMiles(Number(document.getElementById('p6t').innerHTML));
                         document.getElementById('p7t').innerHTML=formatNumberSeparadorMiles(Number(document.getElementById('p7t').innerHTML));
        });
        
        function formatNumberSeparadorMiles(n){
        	var number = new String(n);
        	var result='',isNegative = false;
        	if(number.indexOf("-")>-1){ number=number.substr(1); isNegative=true;}
        	while( number.length > 3 )
        	{
        	result = '.' + number.substr(number.length - 3) + result;
        	number = number.substring(0, number.length - 3);
        	}
        	result = number + result;
        	if(isNegative) result='-'+result;
        	return result;
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
        
 		function changeContador(elem,idname){
        	
        	var contador6=document.getElementById('p6_'+idname).value;
        	if(contador6==0)contador6=document.getElementById('cont_init_'+idname).value;
        	var contador7=elem.value;
        	
        	var difc=document.getElementById('difcont6_'+idname);
        	if(contador6!='ND') difc.value=Number(contador7)-Number(contador6);
        	else difc.value=Number(contador7);
        	
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
	  <p>N702.M.02.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/702/mcon_search'">VOLVER</button> 
	<div align="center" class="title">
		<p >MODIFICAR TOMA DE CONTADORES</p>
	</div>
</div>
<div class="content" >
<form action="" name="form1" id="formSub" method="post">
  <input type="hidden" name="empresas_id" value="<%=empresas_id%>" >
  
	<div class=" cuadroblanco" style="height:130px;">
		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CLIENTE</p></h3>
		<div class="divhead"><strong>NOMBRE NOF:</strong><input type="text" style="width:650px;height:30px ;background:#FFF;" disabled="disabled" value="<%=empresas_nombrenof%>"></div>
		<div class="divhead"><strong>PER FACT.:</strong><select name="id_per" id="id_per" style="margin:0px 0px 0px 0px;width: 325px" >
		<option value=""  >NINGUNO</option>
					<% for(int i =0; i<ar_periodos.length; i++){
						String[] periodos = ar_periodos[i].split("/");
						if(id_per!=null && id_per.equals(periodos[0])){
							peri_tc_correlativo_actual=periodos[2];
							peri_tc_fechad_actual=periodos[3];
							peri_tc_fechah_actual=periodos[4];
						}
						%>
					<option value="<%=periodos[0]%>" <%if(id_per!=null && id_per.equals(periodos[0])){%> selected="selected" <% } %> ><%=periodos[2]+" "+periodos[3]+" AL "+periodos[4] %></option>
				<% } %>
				</select><span class="cabecera" style="color:#F00">*</span></div>
	</div>

	<%if(id_per!=null){%>
		<div class="cuadroblanco" style="height:350px;margin-top: 5px">
	<div style="width:100%;">
		<table class="table" style="margin-left:0px; width: 1200px;">
		<thead style="border: 1px solid black;background-color:#0101DF">
		<tr style="background-color:#0101DF;color:#FFFFFF">
			<td width="370px" style="font-size:20px" colspan="2"><strong>TOTALES</strong></td>
			<td width="116px" style="font-size:20px"></td>
			<td width="116px" style="font-size:20px"><span id="p2t">0</span></td>
			<td width="116px" style="font-size:20px"><span id="p3t">0</span></td>
			<td width="116px" style="font-size:20px"><span id="p4t">0</span></td>
			<td width="116px" style="font-size:20px"><span id="p5t">0</span></td> 
			<td width="116px" style="font-size:20px"><span id="p6t">0</span></td>
			<td width="116px" style="font-size:20px"><span id="p7t">0</span></td>
		</tr>  
		</thead>
		<thead style="border: 1px solid black;background-color:#0101DF">
		<tr style="background-color:#0101DF;color:#FFFFFF">
			<td class="detailID" style="font-size:20px"><strong>ID</strong></td>
			<td class="detailActivo" style="font-size:20px"><strong>ACTIVO</strong></td>
			<td width="116px" style="font-size:20px"><% if(periodos_para_tc[0]!=null){%> <strong>P<%=periodos_para_tc[0]%></strong><br><%=periodosfechas_para_tc[0] %> <% }else{%> ND <% } %></td>
			<td width="116px" style="font-size:20px"><% if(periodos_para_tc[1]!=null){%> <strong>P<%=periodos_para_tc[1] %></strong><br><%=periodosfechas_para_tc[1] %> <% }else{%> ND <% } %></td>
			<td width="116px" style="font-size:20px"><% if(periodos_para_tc[2]!=null){%> <strong>P<%=periodos_para_tc[2] %></strong><br><%=periodosfechas_para_tc[2] %> <% }else{%> ND <% } %></td>
			<td width="116px" style="font-size:20px"><% if(periodos_para_tc[3]!=null){%> <strong>P<%=periodos_para_tc[3] %></strong><br><%=periodosfechas_para_tc[3] %> <% }else{%> ND <% } %></td>
			<td width="116px" style="font-size:20px"><% if(periodos_para_tc[4]!=null){%> <strong>P<%=periodos_para_tc[4] %></strong><br><%=periodosfechas_para_tc[4] %> <% }else{%> ND <% } %></td> 
			<td width="116px" style="font-size:20px"><% if(periodos_para_tc[5]!=null){%> <strong>P<%=periodos_para_tc[5] %></strong><br><%=periodosfechas_para_tc[5] %> <% } else{%> ND <% } %></td>
			<td width="116px" style="font-size:20px"><strong>P<%=peri_tc_correlativo_actual %></strong><br><%=peri_tc_fechad_actual %> <%=peri_tc_fechah_actual %></td>
		</tr>
		</thead>
		<tbody id="fbody" class="scroll" style="border: 1px solid black">
		
		 <%        String ubi_ant="";
		         for(int i =0; i<arActivos.length; i++){
		        	 
		        	 String[] Activos = arActivos[i].split(";;");
		        	 
		        	//marmamos si tiene o no actualmente el activo
		        	 boolean habilitarEdit=false;
		        	 for(String activoAhora:activos_actual){
		        		 if(activoAhora.equals(Activos[0])){
		        			 habilitarEdit=true;
		        			 break;
		        		 }
		        		 
		        	 }
		        	 
		        	 int cont0=0;
		        	 int dif_cont0=0;
		        		if(periodos_para_tc[0]!=null){
		        				if(cont0_activo.indexOf(Activos[0]+";;"+Activos[6])!=-1){
		        					dif_cont0=Integer.parseInt(cont0_activo.get(cont0_activo.indexOf(Activos[0]+";;"+Activos[6]))) ;
		        					cont0=Integer.parseInt(cont0_values.get(cont0_activo.indexOf(Activos[0]+";;"+Activos[6]))) ;
								}
		        		}
		        		
		        	 int cont1=0;	
		        	 int dif_cont1=0;
		        		if(periodos_para_tc[1]!=null){
		        				if(cont1_activo.indexOf(Activos[0]+";;"+Activos[6])!=-1){
		        					 dif_cont1=Integer.parseInt(cont1_values.get(cont1_activo.indexOf(Activos[0]+";;"+Activos[6])))-cont0 ;
		        					 cont1=Integer.parseInt(cont1_values.get(cont1_activo.indexOf(Activos[0]+";;"+Activos[6])));
								}
		        		}
		        	
		        	int cont2=0;
		        	int dif_cont2=0;	
		        		if(periodos_para_tc[2]!=null){
	        				if(cont2_activo.indexOf(Activos[0]+";;"+Activos[6])!=-1){
	        					 dif_cont2=Integer.parseInt(cont2_values.get(cont2_activo.indexOf(Activos[0]+";;"+Activos[6])))-cont1;
	        					 cont2=Integer.parseInt(cont2_values.get(cont2_activo.indexOf(Activos[0]+";;"+Activos[6])));
	        				}
	        			}
		        		
		        	int cont3=0;
		        	int dif_cont3=0;	
		        		if(periodos_para_tc[3]!=null){
	        				if(cont3_activo.indexOf(Activos[0]+";;"+Activos[6])!=-1){
	        					 dif_cont3=Integer.parseInt(cont3_values.get(cont3_activo.indexOf(Activos[0]+";;"+Activos[6])))-cont2;
	        					 cont3=Integer.parseInt(cont3_values.get(cont3_activo.indexOf(Activos[0]+";;"+Activos[6])));
							}
	        			}
		        		
		        	int cont4=0;
		        	int dif_cont4=0;	
		        		if(periodos_para_tc[4]!=null){
	        				if(cont4_activo.indexOf(Activos[0]+";;"+Activos[6])!=-1){
	        					 dif_cont4=Integer.parseInt(cont4_values.get(cont4_activo.indexOf(Activos[0]+";;"+Activos[6])))-cont3;
	        					 cont4=Integer.parseInt(cont4_values.get(cont4_activo.indexOf(Activos[0]+";;"+Activos[6])));
							}
	        			}
		        	int cont5=0;
		        	int dif_cont5=0;	
		        		if(periodos_para_tc[5]!=null){
	        				if(cont5_activo.indexOf(Activos[0]+";;"+Activos[6])!=-1){
	        					 dif_cont5=Integer.parseInt(cont5_values.get(cont5_activo.indexOf(Activos[0]+";;"+Activos[6])))-cont4;
	        					 cont5=Integer.parseInt(cont5_values.get(cont5_activo.indexOf(Activos[0]+";;"+Activos[6])));
							}
	        			}
		        	int cont6=0;	
		        	int dif_cont6=0;	
		        			if(cont6_activo.indexOf(Activos[0]+";;"+Activos[6])!=-1){
	        					 dif_cont6=Integer.parseInt(cont6_values.get(cont6_activo.indexOf(Activos[0]+";;"+Activos[6])))-cont5 ;
	        					 cont6=Integer.parseInt(cont6_values.get(cont6_activo.indexOf(Activos[0]+";;"+Activos[6])));
							}
	        			
		        	
		        			if(!ubi_ant.equals(Activos[3]+" - "+Activos[2])){%>
		        	<tr class="hov" >
						
						<td width="550px" colspan="9"><strong><%=Activos[3]%> - <%=Activos[2]%></strong></td>
						
					</tr>
					
					<% 
		        	}ubi_ant=Activos[3]+" - "+Activos[2];
		        	%>
					<tr class="hov" >
						<td class="detailID" ><%=Activos[0]%></td>
						<td class="detailActivo" ><%=Activos[1]%> - <%=Activos[5]%></td>
						<td ><input type="text" class="inputDetail" name="p1" <% if(periodos_para_tc[0]!=null){if(cont0_activo.indexOf(Activos[0]+";;"+Activos[6])!=-1){%> value="<%=cont0_values.get(cont0_activo.indexOf(Activos[0]+";;"+Activos[6])) %>" <%}else{%> value="0" <% } %> <%  } else{%> value="ND" <% } %> style="height:30px ;" disabled></td>
						<td ><input type="text" class="inputDetail" name="p2" <% if(periodos_para_tc[1]!=null){if(cont1_activo.indexOf(Activos[0]+";;"+Activos[6])!=-1){%> value="<%=cont1_values.get(cont1_activo.indexOf(Activos[0]+";;"+Activos[6])) %>" <%}else{%> value="0" <% } %> <%  } else{%> value="ND" <% } %> style="height:30px ;" disabled></td>
						<td ><input type="text" class="inputDetail" name="p3" <% if(periodos_para_tc[2]!=null){if(cont2_activo.indexOf(Activos[0]+";;"+Activos[6])!=-1){%> value="<%=cont2_values.get(cont2_activo.indexOf(Activos[0]+";;"+Activos[6])) %>" <%}else{%> value="0" <% } %> <%  } else{%> value="ND" <% } %> style="height:30px ;" disabled></td>
						<td ><input type="text" class="inputDetail" name="p4" <% if(periodos_para_tc[3]!=null){if(cont3_activo.indexOf(Activos[0]+";;"+Activos[6])!=-1){%> value="<%=cont3_values.get(cont3_activo.indexOf(Activos[0]+";;"+Activos[6])) %>" <%}else{%> value="0" <% } %> <%  } else{%> value="ND" <% } %> style="height:30px ;" disabled></td>
						<td ><input type="text" class="inputDetail" name="p5" <% if(periodos_para_tc[4]!=null){if(cont4_activo.indexOf(Activos[0]+";;"+Activos[6])!=-1){%> value="<%=cont4_values.get(cont4_activo.indexOf(Activos[0]+";;"+Activos[6])) %>" <%}else{%> value="0" <% } %> <% } else{%> value="ND" <% } %> style="height:30px ;" disabled></td>
						<td ><input type="text" class="inputDetail" id="p6_<%=Activos[0] %>;<%=Activos[6] %>" name="p6" <% if(periodos_para_tc[5]!=null){if(cont5_activo.indexOf(Activos[0]+";;"+Activos[6])!=-1){%> value="<%=cont5_values.get(cont5_activo.indexOf(Activos[0]+";;"+Activos[6])) %>" <%}else{%> value="0" <% } %> <% } else{%> value="ND" <% } %> style="height:30px ;" disabled></td>
						<td ><input type="text" class="inputDetail" id="p7" name="p7_<%=Activos[0] %>;<%=Activos[6] %>" maxlength="8" <% if(cont6_activo.indexOf(Activos[0]+";;"+Activos[6])!=-1){%> value="<%=cont6_values.get(cont6_activo.indexOf(Activos[0]+";;"+Activos[6])) %>" <%}else{%> value="0" <% } %>   <%if(habilitarEdit){%> style="height:30px ;background:#FF3;" onkeydown="return validateNum(event)" onblur="changeContador(this,'<%=Activos[0] %>;<%=Activos[6] %>')" tabindex="1" <%}else{%> style="height:30px ;background:#FFF;" disabled="disabled" <% }%>    ></td>
					</tr>
					<tr class="hov" style="border-bottom: 2px solid black; " >
						<td class="detailID" ></td>
						<td class="detailActivo" ><input type="text" id="cont_init_<%=Activos[0] %>;<%=Activos[6] %>" name="cont_init_<%=Activos[0] %>;<%=Activos[6] %>"  maxlength="8"  style="width:180px ;height:30px ;background:#FF3;" onkeydown="return validateNum(event)" placeholder="Contador inicial"  ></td>
						<td ></td>
						<td ><input type="text" class="inputDetail" value="<%=dif_cont1 %>" style="height:30px ;" disabled></td>
						<td ><input type="text" class="inputDetail" value="<%=dif_cont2 %>" style="height:30px ;" disabled></td>
						<td ><input type="text" class="inputDetail" value="<%=dif_cont3 %>" style="height:30px ;" disabled></td>
						<td ><input type="text" class="inputDetail" value="<%=dif_cont4 %>" style="height:30px ;" disabled></td>
						<td  ><input type="text" class="inputDetail" value="<%=dif_cont5 %>" style="height:30px ;" disabled></td>
						<td ><input type="text" class="inputDetail" id="difcont6_<%=Activos[0] %>;<%=Activos[6] %>" name="difcont6_<%=Activos[0] %>;<%=Activos[6] %>" value="<%=dif_cont6 %>" style="height:30px ;" readonly="readonly">
						<script>document.getElementById('p7t').innerHTML=Number(document.getElementById('p7t').innerHTML)+Number(<%=dif_cont6 %>);
								document.getElementById('p6t').innerHTML=Number(document.getElementById('p6t').innerHTML)+Number(<%=dif_cont5 %>);
								document.getElementById('p5t').innerHTML=Number(document.getElementById('p5t').innerHTML)+Number(<%=dif_cont4 %>);
								document.getElementById('p4t').innerHTML=Number(document.getElementById('p4t').innerHTML)+Number(<%=dif_cont3 %>);
								document.getElementById('p3t').innerHTML=Number(document.getElementById('p3t').innerHTML)+Number(<%=dif_cont2 %>);
								document.getElementById('p2t').innerHTML=Number(document.getElementById('p2t').innerHTML)+Number(<%=dif_cont1 %>);
								</script></td>
					</tr>
		        	<% } %>
		        	</tbody>
		</table>
	</div>
	
	</div>
	<div class="bgrabar2" style="width: 1200px">
          <button type="submit" name="grabar" id="grabar" class="btn btn-success" >GRABAR</button>
       </div> 	
	<% } %>

		
</form>
</div><br><br><br>
<div class="footerapp">               
	<p style="float: left;"><i class="icon-user"></i>
	<strong><%=Usu_nom %></strong></p>
	<button type="button" onclick="window.open('/003/','_blank')" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:2px;">MENU</button>
</div>

   
    
  </body>
</html>


