<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Locale"%>
<%
String Usu_nom=(String)request.getAttribute("usuname").toString();
String[] ar_grupos =(String[]) request.getAttribute("ar_grupos");

String[] periodos_para_tc=(String[])request.getAttribute("periodos_para_tc");
String[] periodosfechas_para_tc=(String[])request.getAttribute("periodosfechas_para_tc");
String[] periodos_para_tc_nimps=(String[])request.getAttribute("periodos_para_tc_nimps");

String[] periodos_para_tc_nmaquinas=(String[])request.getAttribute("periodos_para_tc_nmaquinas");
String[] periodos_para_tc_totalventasimps=(String[])request.getAttribute("periodos_para_tc_totalventasimps");
String[] periodos_para_tc_totaldolaresimps=(String[])request.getAttribute("periodos_para_tc_totaldolaresimps");
String[] periodos_para_tc_valorUnitarioimps=(String[])request.getAttribute("periodos_para_tc_valorUnitarioimps");
String[] periodos_para_tc_mediaTC=(String[])request.getAttribute("periodos_para_tc_mediaTC");
String[] periodos_para_tc_nsme=(String[])request.getAttribute("periodos_para_tc_nsme");
String[] periodos_para_tc_ntickets=(String[])request.getAttribute("periodos_para_tc_ntickets");
String[] periodos_para_tc_rendimiento=(String[])request.getAttribute("periodos_para_tc_rendimiento");
String[] periodos_para_tc_cuc=(String[])request.getAttribute("periodos_para_tc_cuc");
String[] periodos_para_tc_valorUnitarioimpsCL=(String[])request.getAttribute("periodos_para_tc_valorUnitarioimpsCL");

String[] periodos_para_tc_nkite=(String[])request.getAttribute("periodos_para_tc_nkite");
String[] periodos_para_tc_kiterendimiento=(String[])request.getAttribute("periodos_para_tc_kiterendimiento");
String[] periodos_para_tc_kitecuc=(String[])request.getAttribute("periodos_para_tc_kitecuc");

String[] periodos_para_tc_nticketsLog=(String[])request.getAttribute("periodos_para_tc_nticketsLog");
String[] periodos_para_tc_nticketsServTec=(String[])request.getAttribute("periodos_para_tc_nticketsServTec");
String[] periodos_para_tc_totales=(String[])request.getAttribute("periodos_para_tc_totales");

String[] periodos_para_tc_totalventasnoimps=(String[])request.getAttribute("periodos_para_tc_totalventasnoimps");
String[] periodos_para_tc_totalIngresos=(String[])request.getAttribute("periodos_para_tc_totalIngresos");
String[] periodos_para_tc_ndires=(String[])request.getAttribute("periodos_para_tc_ndires");

String[] periodos_para_tc_n_env_fotoconductor=(String[])request.getAttribute("periodos_para_tc_n_env_fotoconductor");
String[] periodos_para_tc_rendimiento_fotocond=(String[])request.getAttribute("periodos_para_tc_rendimiento_fotocond");
String[] periodos_para_tc_cuc_fotocond=(String[])request.getAttribute("periodos_para_tc_cuc_fotocond");

String[] periodos_para_tc_n_env_repuestos=(String[])request.getAttribute("periodos_para_tc_n_env_repuestos");
String[] periodos_para_tc_rendimiento_repuestos=(String[])request.getAttribute("periodos_para_tc_rendimiento_repuestos");
String[] periodos_para_tc_cuc_repuestos=(String[])request.getAttribute("periodos_para_tc_cuc_repuestos");

String[] periodos_para_tc_n_env_consumibles=(String[])request.getAttribute("periodos_para_tc_n_env_consumibles");
String[] periodos_para_tc_rendimiento_consumibles=(String[])request.getAttribute("periodos_para_tc_rendimiento_consumibles");
String[] periodos_para_tc_cuc_consumibles=(String[])request.getAttribute("periodos_para_tc_cuc_consumibles");

String select_empresa =(String)request.getAttribute("select_empresa");
String select_periodo =(String)request.getAttribute("select_periodo");

ArrayList<String> ar_empresas =(ArrayList<String>) request.getAttribute("ar_empresas");
ArrayList<String> ar_select_periodo =(ArrayList<String>) request.getAttribute("ar_select_periodo");


DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.GERMAN);
otherSymbols.setDecimalSeparator(',');
otherSymbols.setGroupingSeparator('.');
DecimalFormat dfDecimal6digitos = new DecimalFormat( "#,###,###,##0.000000",otherSymbols );
DecimalFormat dfDecimal2digitos = new DecimalFormat( "#,###,###,##0.00",otherSymbols );
DecimalFormat dfNodecimal = new DecimalFormat( "#,###,###,##0",otherSymbols );


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
	.inputDetail{
		width: 143px;
		margin-bottom: 0px !important;
		text-align: right;
	}
	.hov>td{
		padding-left: 2px;
		padding-right: 2px;
		padding-top: 1px;
		padding-bottom: 1px;
		width: 147px;
	}
	.headDetail{
		width: 142px;
	}
	.hovHead>td{
		width: 147px;
		
	}
	.divhead{
			float: left;
			font-size: 20px; 
			margin-right: 15px;
			margin-bottom: 2px;
			height: 36px;
			text-align: left;
			text-transform:uppercase;
		
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
			font-size: 20px;
			
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
                        
                         $('#select_periodo').on('change', function() {
                          
                        	 $('#formSub').submit();
                                
       					 });
                         
                         $('#select_empresa').on('change', function() {
                             var emp_seleccionada= $(this).val();
                           $('#select_periodo').empty();
                           
                   
                           if(emp_seleccionada!=''){
                             
                               for(var ii=0; ii< periodos_ar.length;ii++){
             	                  var per_ar =periodos_ar[ii].split(";;");
             	                  if(per_ar[2]==emp_seleccionada){
             	                    $('#select_periodo').append($('<option>', {
             	                                                value: per_ar[0],
             	                                                text: per_ar[1]
             	                                                }));
             	                    	
             	                 	 }
             	                
             	                }
                               $("#select_periodo option[value='<%=select_periodo %>']").prop('selected', true);
                         
                           }
                                                               
                       });
                         
                         $('#select_empresa').change();
                         
                       
        });
  		var periodos_ar =new Array();
        
   		<%  for(int i =0; i<ar_select_periodo.size(); i++){  %>
        		periodos_ar[<%=i%>]="<%=ar_select_periodo.get(i)%>";
        
        <% } %>
        
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
	  <p>N996.R.02.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/996/rpt'">VOLVER</button> 
	<div align="center" class="title">
		<p >REPORTE 9</p>
	</div>
</div>
<div class="content" >
<form action="" name="form1" id="formSub" method="post">
  
  	
	<div class=" cuadroblanco" style="height:90px;">
	
	<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CLIENTE</p></h3>
	<div style="padding: 5px 5px 5px 5px;">
		<div class="divhead select_empresa"><strong >EMPRESA:</strong><select name="select_empresa" id="select_empresa" style="width: 380px">
			   <option value="" selected="selected">TODOS</option>
			   <% for(int i =0; i<ar_empresas.size(); i++){
			       		String[] empresa = ar_empresas.get(i).split(";;");
			    
			    %>
			    
			      <option value="<%=empresa[0]%>" <% if(select_empresa.equals(empresa[0])){%> selected="selected" <% } %> ><%=empresa[1] %></option>
			    <% } %>
			 	 		    </select>
       </div>
       <div class="divhead select_periodo"><strong >PERIODO:</strong><select name="select_periodo" id="select_periodo" style="width: 300px">
			   <option value="" selected="selected">TODOS</option>
			 	 		    </select>
       </div>
       
       </div>
	</div>

		<div class="cuadroblanco" style="height:350px;margin-top: 5px">
	<div style="width:100%;">
		<table class="table" style="margin-left:0px; width: 1200px;">
		<thead style="border: 1px solid black;background-color:#0101DF">
		<tr style="background-color:#0101DF;color:#FFFFFF" class="hovHead">
			<td width="142px" style="font-size:20px" colspan="2"><strong>TOTALES</strong></td>
			<td style="font-size:20px"></td>
			<td style="font-size:20px"><span id="p2t">0</span></td>
			<td style="font-size:20px"><span id="p3t">0</span></td>
			<td style="font-size:20px"><span id="p4t">0</span></td>
			<td style="font-size:20px"><span id="p5t">0</span></td> 
			<td style="font-size:20px"><span id="p6t">0</span></td>
			<td style="font-size:20px"><span id="p7t">0</span></td>
		</tr>  
		</thead>
		<thead style="border: 1px solid black;background-color:#0101DF">
		<tr style="background-color:#0101DF;color:#FFFFFF" class="hovHead">
			<td width="142px" style="font-size:20px" colspan="2">&nbsp;</td>
			<td style="font-size:20px"><% if(periodos_para_tc[1]!=null){%> <strong>P<%=periodos_para_tc[1] %></strong><br><%=periodosfechas_para_tc[1] %> <% }else{%> ND <% } %></td>
			<td style="font-size:20px"><% if(periodos_para_tc[2]!=null){%> <strong>P<%=periodos_para_tc[2] %></strong><br><%=periodosfechas_para_tc[2] %> <% }else{%> ND <% } %></td>
			<td style="font-size:20px"><% if(periodos_para_tc[3]!=null){%> <strong>P<%=periodos_para_tc[3] %></strong><br><%=periodosfechas_para_tc[3] %> <% }else{%> ND <% } %></td>
			<td style="font-size:20px"><% if(periodos_para_tc[4]!=null){%> <strong>P<%=periodos_para_tc[4] %></strong><br><%=periodosfechas_para_tc[4] %> <% }else{%> ND <% } %></td> 
			<td style="font-size:20px"><% if(periodos_para_tc[5]!=null){%> <strong>P<%=periodos_para_tc[5] %></strong><br><%=periodosfechas_para_tc[5] %> <% } else{%> ND <% } %></td>
			<td style="font-size:20px"><% if(periodos_para_tc[6]!=null){%> <strong>P<%=periodos_para_tc[6] %></strong><br><%=periodosfechas_para_tc[6] %> <% } else{%> ND <% } %></td>
			<td style="font-size:20px">TOTALES<br>(12 PERIODOS)</td>
		
		</tr>
		</thead>
		<tbody id="fbody" class="scroll" style="border: 1px solid black">
		
		        	<tr class="hov" >
						<td width="142px"  colspan="2"><b>TOT ING</b></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totalIngresos[1]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totalIngresos[1])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totalIngresos[2]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totalIngresos[2])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totalIngresos[3]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totalIngresos[3])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totalIngresos[4]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totalIngresos[4])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totalIngresos[5]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totalIngresos[5])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totalIngresos[6]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totalIngresos[6])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totales[15]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totales[15])) %>" disabled class="inputDetail"></td>
						
					</tr>
					<tr class="hov" >
						<td width="142px"  colspan="2">VTA S. IMP $</td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totalventasimps[1]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totalventasimps[1])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totalventasimps[2]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totalventasimps[2])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totalventasimps[3]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totalventasimps[3])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totalventasimps[4]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totalventasimps[4])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totalventasimps[5]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totalventasimps[5])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totalventasimps[6]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totalventasimps[6])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totales[0]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totales[0])) %>" disabled class="inputDetail"></td>
						
					</tr>
					<tr class="hov" >
						<td width="142px"  colspan="2">Nº IMP</td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nimps[1]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_nimps[1])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nimps[2]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_nimps[2])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nimps[3]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_nimps[3])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nimps[4]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_nimps[4])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nimps[5]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_nimps[5])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nimps[6]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_nimps[6])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totales[1]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totales[1])) %>" disabled class="inputDetail"></td>
					</tr>
					<tr class="hov" >
						<td width="142px"  colspan="2">T/C</td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_mediaTC[1]==null? "0":dfDecimal2digitos.format(Float.parseFloat(periodos_para_tc_mediaTC[1])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_mediaTC[2]==null? "0":dfDecimal2digitos.format(Float.parseFloat(periodos_para_tc_mediaTC[2])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_mediaTC[3]==null? "0":dfDecimal2digitos.format(Float.parseFloat(periodos_para_tc_mediaTC[3])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_mediaTC[4]==null? "0":dfDecimal2digitos.format(Float.parseFloat(periodos_para_tc_mediaTC[4])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_mediaTC[5]==null? "0":dfDecimal2digitos.format(Float.parseFloat(periodos_para_tc_mediaTC[5])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_mediaTC[6]==null? "0":dfDecimal2digitos.format(Float.parseFloat(periodos_para_tc_mediaTC[6])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totales[5]==null? "0":dfDecimal2digitos.format(Float.parseFloat(periodos_para_tc_totales[5])) %>" disabled class="inputDetail"></td>
					</tr>
					
					<tr class="hov" >
						<td width="142px"  colspan="2">VTA S IMP T/C</td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totaldolaresimps[1]==null? "0":dfDecimal2digitos.format(Float.parseFloat(periodos_para_tc_totaldolaresimps[1])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totaldolaresimps[2]==null? "0":dfDecimal2digitos.format(Float.parseFloat(periodos_para_tc_totaldolaresimps[2])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totaldolaresimps[3]==null? "0":dfDecimal2digitos.format(Float.parseFloat(periodos_para_tc_totaldolaresimps[3])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totaldolaresimps[4]==null? "0":dfDecimal2digitos.format(Float.parseFloat(periodos_para_tc_totaldolaresimps[4])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totaldolaresimps[5]==null? "0":dfDecimal2digitos.format(Float.parseFloat(periodos_para_tc_totaldolaresimps[5])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totaldolaresimps[6]==null? "0":dfDecimal2digitos.format(Float.parseFloat(periodos_para_tc_totaldolaresimps[6])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totales[2]==null? "0":dfDecimal2digitos.format(Float.parseFloat(periodos_para_tc_totales[2])) %>" disabled class="inputDetail"></td>
					</tr>
					<tr class="hov" >
						<td width="142px"  colspan="2">V.UN.IMP T/C</td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_valorUnitarioimps[1]==null? "0":dfDecimal6digitos.format(Float.parseFloat(periodos_para_tc_valorUnitarioimps[1])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_valorUnitarioimps[2]==null? "0":dfDecimal6digitos.format(Float.parseFloat(periodos_para_tc_valorUnitarioimps[2])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_valorUnitarioimps[3]==null? "0":dfDecimal6digitos.format(Float.parseFloat(periodos_para_tc_valorUnitarioimps[3])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_valorUnitarioimps[4]==null? "0":dfDecimal6digitos.format(Float.parseFloat(periodos_para_tc_valorUnitarioimps[4])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_valorUnitarioimps[5]==null? "0":dfDecimal6digitos.format(Float.parseFloat(periodos_para_tc_valorUnitarioimps[5])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_valorUnitarioimps[6]==null? "0":dfDecimal6digitos.format(Float.parseFloat(periodos_para_tc_valorUnitarioimps[6])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totales[13]==null? "0":dfDecimal6digitos.format(Float.parseFloat(periodos_para_tc_totales[13])) %>" disabled class="inputDetail"></td>
					</tr>
					
					<tr class="hov" >
						<td width="142px"  colspan="2">V.UN.IMP $</td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_valorUnitarioimpsCL[1]==null? "0":dfDecimal6digitos.format(Float.parseFloat(periodos_para_tc_valorUnitarioimpsCL[1])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_valorUnitarioimpsCL[2]==null? "0":dfDecimal6digitos.format(Float.parseFloat(periodos_para_tc_valorUnitarioimpsCL[2])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_valorUnitarioimpsCL[3]==null? "0":dfDecimal6digitos.format(Float.parseFloat(periodos_para_tc_valorUnitarioimpsCL[3])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_valorUnitarioimpsCL[4]==null? "0":dfDecimal6digitos.format(Float.parseFloat(periodos_para_tc_valorUnitarioimpsCL[4])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_valorUnitarioimpsCL[5]==null? "0":dfDecimal6digitos.format(Float.parseFloat(periodos_para_tc_valorUnitarioimpsCL[5])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_valorUnitarioimpsCL[6]==null? "0":dfDecimal6digitos.format(Float.parseFloat(periodos_para_tc_valorUnitarioimpsCL[6])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totales[16]==null? "0":dfDecimal6digitos.format(Float.parseFloat(periodos_para_tc_totales[16])) %>" disabled class="inputDetail"></td>
						
					</tr>
					<tr class="hov" >
						<td width="142px"  colspan="2">VTA.OTROS</td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totalventasnoimps[1]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totalventasnoimps[1])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totalventasnoimps[2]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totalventasnoimps[2])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totalventasnoimps[3]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totalventasnoimps[3])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totalventasnoimps[4]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totalventasnoimps[4])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totalventasnoimps[5]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totalventasnoimps[5])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totalventasnoimps[6]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totalventasnoimps[6])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totales[14]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totales[14])) %>" disabled class="inputDetail"></td>
						
					</tr>
					<tr class="hov" style="background-color: #000" >
						<td width="142px"  colspan="9"></td>
						
						
					</tr>
					<tr class="hov" >
						<td width="142px"  colspan="2">SUM.ENV</td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nsme[1]==null? "0":periodos_para_tc_nsme[1] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nsme[2]==null? "0":periodos_para_tc_nsme[2] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nsme[3]==null? "0":periodos_para_tc_nsme[3] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nsme[4]==null? "0":periodos_para_tc_nsme[4] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nsme[5]==null? "0":periodos_para_tc_nsme[5] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nsme[6]==null? "0":periodos_para_tc_nsme[6] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totales[6]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totales[6])) %>" disabled class="inputDetail"></td>
					</tr>
					<tr class="hov" >
						<td width="142px"  colspan="2">SUM.REND.</td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_rendimiento[1]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_rendimiento[1])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_rendimiento[2]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_rendimiento[2])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_rendimiento[3]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_rendimiento[3])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_rendimiento[4]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_rendimiento[4])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_rendimiento[5]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_rendimiento[5])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_rendimiento[6]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_rendimiento[6])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totales[8]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totales[8])) %>" disabled class="inputDetail"></td>
					</tr>
					<tr class="hov" >
						<td width="142px"  colspan="2">CTO.SUM.</td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_cuc[1]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_cuc[1])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_cuc[2]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_cuc[2])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_cuc[3]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_cuc[3])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_cuc[4]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_cuc[4])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_cuc[5]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_cuc[5])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_cuc[6]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_cuc[6])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totales[7]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totales[7])) %>" disabled class="inputDetail"></td>
					</tr>
					<tr class="hov" style="background-color: #000" >
						<td width="142px"  colspan="9"></td>
					</tr>
					
					<tr class="hov" >
						<td width="142px"  colspan="2">KIT ENV.</td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nkite[1]==null? "0":periodos_para_tc_nkite[1] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nkite[2]==null? "0":periodos_para_tc_nkite[2] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nkite[3]==null? "0":periodos_para_tc_nkite[3] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nkite[4]==null? "0":periodos_para_tc_nkite[4] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nkite[5]==null? "0":periodos_para_tc_nkite[5] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nkite[6]==null? "0":periodos_para_tc_nkite[6] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totales[10]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totales[10])) %>" disabled class="inputDetail"></td>
						
					</tr>
					
					<tr class="hov" >
						<td width="142px"  colspan="2">KIT REND.</td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_kiterendimiento[1]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_kiterendimiento[1])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_kiterendimiento[2]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_kiterendimiento[2])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_kiterendimiento[3]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_kiterendimiento[3])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_kiterendimiento[4]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_kiterendimiento[4])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_kiterendimiento[5]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_kiterendimiento[5])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_kiterendimiento[6]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_kiterendimiento[6])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totales[12]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totales[12])) %>" disabled class="inputDetail"></td>
						
					</tr>
					
					<tr class="hov" >
						<td width="142px"  colspan="2">CTO. KIT.</td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_kitecuc[1]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_kitecuc[1])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_kitecuc[2]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_kitecuc[2])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_kitecuc[3]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_kitecuc[3])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_kitecuc[4]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_kitecuc[4])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_kitecuc[5]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_kitecuc[5])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_kitecuc[6]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_kitecuc[6])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totales[11]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totales[11])) %>" disabled class="inputDetail"></td>
					
					</tr>
					<tr class="hov" style="background-color: #000" >
						<td width="142px"  colspan="9"></td>
						
						
					</tr>
					
					<tr class="hov" >
						<td width="142px"  colspan="2">FTOCOND ENV.</td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_n_env_fotoconductor[1]==null? "0":periodos_para_tc_n_env_fotoconductor[1] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_n_env_fotoconductor[2]==null? "0":periodos_para_tc_n_env_fotoconductor[2] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_n_env_fotoconductor[3]==null? "0":periodos_para_tc_n_env_fotoconductor[3] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_n_env_fotoconductor[4]==null? "0":periodos_para_tc_n_env_fotoconductor[4] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_n_env_fotoconductor[5]==null? "0":periodos_para_tc_n_env_fotoconductor[5] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_n_env_fotoconductor[6]==null? "0":periodos_para_tc_n_env_fotoconductor[6] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totales[17]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totales[17])) %>" disabled class="inputDetail"></td>
						
					</tr>
					
					<tr class="hov" >
						<td width="142px"  colspan="2">FTOCOND REND.</td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_rendimiento_fotocond[1]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_rendimiento_fotocond[1])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_rendimiento_fotocond[2]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_rendimiento_fotocond[2])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_rendimiento_fotocond[3]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_rendimiento_fotocond[3])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_rendimiento_fotocond[4]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_rendimiento_fotocond[4])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_rendimiento_fotocond[5]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_rendimiento_fotocond[5])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_rendimiento_fotocond[6]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_rendimiento_fotocond[6])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totales[19]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totales[19])) %>" disabled class="inputDetail"></td>
						
					</tr>
					
					<tr class="hov" >
						<td width="142px"  colspan="2">CTO FTOCOND.</td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_cuc_fotocond[1]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_cuc_fotocond[1])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_cuc_fotocond[2]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_cuc_fotocond[2])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_cuc_fotocond[3]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_cuc_fotocond[3])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_cuc_fotocond[4]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_cuc_fotocond[4])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_cuc_fotocond[5]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_cuc_fotocond[5])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_cuc_fotocond[6]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_cuc_fotocond[6])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totales[18]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totales[18])) %>" disabled class="inputDetail"></td>
					
					</tr>
					<tr class="hov" style="background-color: #000" >
						<td width="142px"  colspan="9"></td>
					</tr>
					<tr class="hov" >
						<td width="142px"  colspan="2">CONS ENV.</td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_n_env_consumibles[1]==null? "0":periodos_para_tc_n_env_consumibles[1] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_n_env_consumibles[2]==null? "0":periodos_para_tc_n_env_consumibles[2] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_n_env_consumibles[3]==null? "0":periodos_para_tc_n_env_consumibles[3] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_n_env_consumibles[4]==null? "0":periodos_para_tc_n_env_consumibles[4] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_n_env_consumibles[5]==null? "0":periodos_para_tc_n_env_consumibles[5] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_n_env_consumibles[6]==null? "0":periodos_para_tc_n_env_consumibles[6] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totales[20]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totales[20])) %>" disabled class="inputDetail"></td>
						
					</tr>
					
					<tr class="hov" >
						<td width="142px"  colspan="2">CONS REND.</td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_rendimiento_consumibles[1]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_rendimiento_consumibles[1])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_rendimiento_consumibles[2]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_rendimiento_consumibles[2])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_rendimiento_consumibles[3]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_rendimiento_consumibles[3])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_rendimiento_consumibles[4]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_rendimiento_consumibles[4])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_rendimiento_consumibles[5]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_rendimiento_consumibles[5])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_rendimiento_consumibles[6]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_rendimiento_consumibles[6])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totales[22]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totales[22])) %>" disabled class="inputDetail"></td>
						
					</tr>
					
					<tr class="hov" >
						<td width="142px"  colspan="2">CTO CONS.</td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_cuc_consumibles[1]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_cuc_consumibles[1])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_cuc_consumibles[2]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_cuc_consumibles[2])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_cuc_consumibles[3]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_cuc_consumibles[3])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_cuc_consumibles[4]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_cuc_consumibles[4])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_cuc_consumibles[5]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_cuc_consumibles[5])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_cuc_consumibles[6]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_cuc_consumibles[6])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totales[21]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totales[21])) %>" disabled class="inputDetail"></td>
					
					</tr>
					<tr class="hov" style="background-color: #000" >
						<td width="142px"  colspan="9"></td>
					</tr>
					<tr class="hov" >
						<td width="142px"  colspan="2">REP ENV.</td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_n_env_repuestos[1]==null? "0":periodos_para_tc_n_env_repuestos[1] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_n_env_repuestos[2]==null? "0":periodos_para_tc_n_env_repuestos[2] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_n_env_repuestos[3]==null? "0":periodos_para_tc_n_env_repuestos[3] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_n_env_repuestos[4]==null? "0":periodos_para_tc_n_env_repuestos[4] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_n_env_repuestos[5]==null? "0":periodos_para_tc_n_env_repuestos[5] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_n_env_repuestos[6]==null? "0":periodos_para_tc_n_env_repuestos[6] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totales[23]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totales[23])) %>" disabled class="inputDetail"></td>
						
					</tr>
					
					<tr class="hov" >
						<td width="142px"  colspan="2">REP REND.</td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_rendimiento_repuestos[1]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_rendimiento_repuestos[1])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_rendimiento_repuestos[2]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_rendimiento_repuestos[2])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_rendimiento_repuestos[3]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_rendimiento_repuestos[3])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_rendimiento_repuestos[4]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_rendimiento_repuestos[4])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_rendimiento_repuestos[5]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_rendimiento_repuestos[5])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_rendimiento_repuestos[6]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_rendimiento_repuestos[6])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totales[25]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totales[25])) %>" disabled class="inputDetail"></td>
						
					</tr>
					
					<tr class="hov" >
						<td width="142px"  colspan="2">CTO REP.</td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_cuc_repuestos[1]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_cuc_repuestos[1])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_cuc_repuestos[2]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_cuc_repuestos[2])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_cuc_repuestos[3]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_cuc_repuestos[3])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_cuc_repuestos[4]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_cuc_repuestos[4])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_cuc_repuestos[5]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_cuc_repuestos[5])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_cuc_repuestos[6]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_cuc_repuestos[6])) %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totales[24]==null? "0":dfNodecimal.format(Float.parseFloat(periodos_para_tc_totales[24])) %>" disabled class="inputDetail"></td>
					
					</tr>
					<tr class="hov" style="background-color: #000" >
						<td width="142px"  colspan="9"></td>
					</tr>
					<tr class="hov" >
						<td width="142px"  colspan="2">CANT MAQUINAS</td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nmaquinas[1]==null? "0":periodos_para_tc_nmaquinas[1] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nmaquinas[2]==null? "0":periodos_para_tc_nmaquinas[2] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nmaquinas[3]==null? "0":periodos_para_tc_nmaquinas[3] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nmaquinas[4]==null? "0":periodos_para_tc_nmaquinas[4] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nmaquinas[5]==null? "0":periodos_para_tc_nmaquinas[5] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nmaquinas[6]==null? "0":periodos_para_tc_nmaquinas[6] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nmaquinas[6]==null? "0":periodos_para_tc_nmaquinas[6] %>" disabled class="inputDetail"></td>
					</tr>
					
					<tr class="hov" >
						<td width="142px"  colspan="2">CANT DIREC.</td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_ndires[1]==null? "0":periodos_para_tc_ndires[1] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_ndires[2]==null? "0":periodos_para_tc_ndires[2] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_ndires[3]==null? "0":periodos_para_tc_ndires[3] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_ndires[4]==null? "0":periodos_para_tc_ndires[4] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_ndires[5]==null? "0":periodos_para_tc_ndires[5] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_ndires[6]==null? "0":periodos_para_tc_ndires[6] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_ndires[6]==null? "0":periodos_para_tc_ndires[6] %>" disabled class="inputDetail"></td>
					</tr>
					<tr class="hov" > 
						<td width="142px"  colspan="2">Nº TICK LOG.</td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nticketsLog[1]==null? "0":periodos_para_tc_nticketsLog[1] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nticketsLog[2]==null? "0":periodos_para_tc_nticketsLog[2] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nticketsLog[3]==null? "0":periodos_para_tc_nticketsLog[3] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nticketsLog[4]==null? "0":periodos_para_tc_nticketsLog[4] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nticketsLog[5]==null? "0":periodos_para_tc_nticketsLog[5] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nticketsLog[6]==null? "0":periodos_para_tc_nticketsLog[6] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totales[3]==null? "0":periodos_para_tc_totales[3] %>" disabled class="inputDetail"></td>
					</tr>
					<tr class="hov" >
						<td width="142px"  colspan="2">Nº TICK S.TEC</td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nticketsServTec[1]==null? "0":periodos_para_tc_nticketsServTec[1] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nticketsServTec[2]==null? "0":periodos_para_tc_nticketsServTec[2] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nticketsServTec[3]==null? "0":periodos_para_tc_nticketsServTec[3] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nticketsServTec[4]==null? "0":periodos_para_tc_nticketsServTec[4] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nticketsServTec[5]==null? "0":periodos_para_tc_nticketsServTec[5] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_nticketsServTec[6]==null? "0":periodos_para_tc_nticketsServTec[6] %>" disabled class="inputDetail"></td>
						<td><input type="text" style="height:30px ;" value="<%=periodos_para_tc_totales[4]==null? "0":periodos_para_tc_totales[4] %>" disabled class="inputDetail"></td>
					</tr>
					
		       
		        	</tbody>
		</table>
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
