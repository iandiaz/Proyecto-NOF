<%@page import="VO.EmpresaVO"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Locale"%>
<%@page import="VO.UsuarioVO"%>
<%
String Usu_nom=(String)request.getAttribute("usuname").toString();

/*
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


String[] periodos_para_tc_n_env_fotoconductor=(String[])request.getAttribute("periodos_para_tc_n_fotocond");
String[] periodos_para_tc_n_env_consumibles=(String[])request.getAttribute("periodos_para_tc_n_consumibles");
String[] periodos_para_tc_n_env_repuestos=(String[])request.getAttribute("periodos_para_tc_n_repuestos");

String[] periodos_para_tc_rendimiento_fotocond=(String[])request.getAttribute("periodos_para_tc_rendimiento_fotocond");
String[] periodos_para_tc_rendimiento_consumibles=(String[])request.getAttribute("periodos_para_tc_rendimiento_consumibles");
String[] periodos_para_tc_rendimiento_repuestos=(String[])request.getAttribute("periodos_para_tc_rendimiento_repuestos");

String[] periodos_para_tc_cuc_fotocond=(String[])request.getAttribute("periodos_para_tc_cuc_fotocond");
String[] periodos_para_tc_cuc_consumibles=(String[])request.getAttribute("periodos_para_tc_cuc_consumibles");
String[] periodos_para_tc_cuc_repuestos=(String[])request.getAttribute("periodos_para_tc_cuc_repuestos");
*/



/*
ArrayList<EmpresaVO> ar_empresas =(ArrayList<EmpresaVO>) request.getAttribute("ar_empresas");
ArrayList<String> ar_select_periodo =(ArrayList<String>) request.getAttribute("ar_select_periodo");
ArrayList<String> ar_grupos =(ArrayList<String>) request.getAttribute("ar_grupos");
ArrayList<UsuarioVO> ar_vendedores =(ArrayList<UsuarioVO>) request.getAttribute("ar_vendedores");
*/


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
    <link href="//cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/css/select2.min.css" rel="stylesheet" />
	  <script src="//cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/js/select2.min.js"></script>
   <script src="rpt12Func.js"></script>
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
			width:145px;
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
    input.select2-search__field{
      height: 30px;
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
                    
                    $('#select_month').select2();  
                    $('#select_year').select2();  
                    getGruposSelect();
                	getEmpresasSelect(null);
                	getVendedoresSelect();
                	/**********************************
                     ******CADENA GRUPO 
                     ***********************************/
                     
                    $('#select_grupo').on('change', function() {
                          var grup_seleccionada= $(this).val();
                          getEmpresasSelect(grup_seleccionada);
                    });
                      
                   
                       
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
        	var contador7=elem.value;
        	
        	var difc=document.getElementById('difcont6_'+idname);
        	if(contador6!='ND') difc.value=Number(contador7)-Number(contador6);
        	else difc.value=Number(contador7);
        	
        }
        
 		
 		function getGruposSelect(){
       	 
	        $.post( "getGruposSelect", {  })
	        .done(function( data ) {
	        	  $( "#select_grupo" ).html( (data) );
	        	  $('#select_grupo').select2();
	          	
	        });
        }
        
        function getEmpresasSelect(id_grupo){
       	 
	        $.post( "getEmpresasSelect", {id_grupo:id_grupo  })
	        .done(function( data ) {
	        	  $( "#select_empresa" ).html( (data) );
	        	  $('#select_empresa').select2();
	        	  $('#select_empresa').select2("val","");
	          	
	        });
        }
        
        function getEmpresasSelect(id_grupo){
          	 
	        $.post( "getEmpresasSelect", {id_grupo:id_grupo  })
	        .done(function( data ) {
	        	  $( "#select_empresa" ).html( (data) );
	        	  $('#select_empresa').select2();
	        	  $('#select_empresa').select2("val","");
	          	
	        });
        }
        
        function buscar(){
        	limpiarBusqueda();
        	getPeriodos($('#select_year').val(),$('#select_month').val());
        	getVentasImp($('#select_year').val(),$('#select_month').val(),$('#select_empresa').val(),$('#select_grupo').val());
        	getNImp($('#select_year').val(),$('#select_month').val(),$('#select_empresa').val(),$('#select_grupo').val());
        	getEnvSumMaq($('#select_year').val(),$('#select_month').val(),$('#select_empresa').val(),$('#select_grupo').val());
        	getEnvKit($('#select_year').val(),$('#select_month').val(),$('#select_empresa').val(),$('#select_grupo').val());
        	getEnvFotoCond($('#select_year').val(),$('#select_month').val(),$('#select_empresa').val(),$('#select_grupo').val());
        	getEnvConsumibles($('#select_year').val(),$('#select_month').val(),$('#select_empresa').val(),$('#select_grupo').val());
        	getEnvRepuestos($('#select_year').val(),$('#select_month').val(),$('#select_empresa').val(),$('#select_grupo').val());
        	getTickLog($('#select_year').val(),$('#select_month').val(),$('#select_empresa').val(),$('#select_grupo').val());
        	getTickServTec($('#select_year').val(),$('#select_month').val(),$('#select_empresa').val(),$('#select_grupo').val());
        	getNmaquinas($('#select_year').val(),$('#select_month').val(),$('#select_empresa').val(),$('#select_grupo').val());
        	getDirs($('#select_year').val(),$('#select_month').val(),$('#select_empresa').val(),$('#select_grupo').val());
        	
        }
        
		function getVendedoresSelect(){
        	
        	$.post( "getUsuariosAsync", {func:"getVendedores"})
            .done(function( data ) {
            	
            	$('#select_vendedor').empty();
            	
            	var usuarios_arr = $.parseJSON(data);
          		
            	$.each( usuarios_arr, function( key, usuario ) {
          			
            		 $('#select_vendedor').append($('<option>', {
                         value: usuario.id,
                         text: usuario.nombre+' '+usuario.ape_p+' '+usuario.ape_m
                         }));
          		});
            	
            	$('#select_vendedor').select2();
            	$('#select_vendedor').select2("val","");
            	
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
	  <p>N996.R.02.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/996/rpt'">VOLVER</button> 
	<div align="center" class="title">
		<p >REPORTE RESUMEN TOTAL EMPRESA POR MES EJECUTIVO</p>
	</div>
</div>
<div class="content" >
<form action="" name="form1" id="formSub" method="post">
  
  	
	<div class=" cuadroblanco" style="height:180px;">
	
	<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px"><p style="margin:0px 0px;font-size:20px;background-color: #39F">DATOS CLIENTE</p></h3>
	<div style="padding: 5px 5px 5px 5px;">
	<div class="divhead "><strong >GRUPO:</strong><select name="select_grupo" id="select_grupo" style="width: 290px">
			   <option value="" selected="selected">TODOS</option></select>
       </div>
		<div class="divhead select_empresa"><strong >EMPRESA:</strong><select name="select_empresa" id="select_empresa" style="width: 380px">
			   <option value="" selected="selected">TODOS</option>
			  	 		    </select>
       </div>
       
       <div class="divhead select_year"><strong >AÑO:</strong><select name="select_year" id="select_year" style="width:75px;" >
           
           <option value="2014" >2014</option>
           <option value="2015" >2015</option>
           <option value="2016" >2016</option>
           <option value="2017" >2017</option>
           <option value="2018" >2018</option>
           <option value="2019" >2019</option>
           <option value="2020" >2020</option>
		   
      	 		    </select>
        </div>
        <div class="divhead select_month"><strong >MES:</strong><select name="select_month" id="select_month" >
           <option value="01" >ENERO</option>
           <option value="02" >FEBRERO</option>
           <option value="03" >MARZO</option>
           <option value="04" >ABRIL</option>
           <option value="05" >MAYO</option>
           <option value="06" >JUNIO</option>
           <option value="07" >JULIO</option>
           <option value="08" >AGOSTO</option>
           <option value="09" >SEPTIEMBRE</option>
           <option value="10" >OCTUBRE</option>
           <option value="11" >NOVIEMBRE</option>
           <option value="12" >DICIEMBRE</option>
		   
      	 		    </select>
        </div>

        <div class="divhead "><strong >VENDEDOR:</strong><select name="select_vendedor[]" id="select_vendedor" multiple style="width: 1000px">
      			   
      			 	 		    </select>
             </div>
         </div>
        </div>
        <div class=" cuadroblanco" style="height:45px;">
		<div style="padding: 5px 5px 5px 5px;">
	
         <div class="divhead " style="float: right;margin-right: 0px;">
        <button type="button" onclick="buscar()" class="btn btn-success " >BUSCAR</button>
         </div>
        </div>
      
		</div>

		<div class="cuadroblanco" style="height:300px;margin-top: 5px">
	<div style="width:100%;">
		<table class="table" style="margin-left:0px; width: 1200px;">
		<thead style="border: 1px solid black;background-color:#0101DF">
		<tr style="background-color:#0101DF;color:#FFFFFF" class="hovHead">
			<td width="142px" style="font-size:20px" colspan="2">&nbsp;</td>
			<td style="font-size:20px"><label id="P1"></label><span class="loading_p" style="display: none"><img alt="" src="/011/images/loading.GIF" style="height:30px"></span></td>
			<td style="font-size:20px"><label id="P2"></label><span class="loading_p" style="display: none"><img alt="" src="/011/images/loading.GIF" style="height:30px"></span></td>
			<td style="font-size:20px"><label id="P3"></label><span class="loading_p" style="display: none"><img alt="" src="/011/images/loading.GIF" style="height:30px"></span></td>
			<td style="font-size:20px"><label id="P4"></label><span class="loading_p" style="display: none"><img alt="" src="/011/images/loading.GIF" style="height:30px"></span></td> 
			<td style="font-size:20px"><label id="P5"></label><span class="loading_p" style="display: none"><img alt="" src="/011/images/loading.GIF" style="height:30px"></span></td>
			<td style="font-size:20px"><label id="P6"></label><span class="loading_p" style="display: none"><img alt="" src="/011/images/loading.GIF" style="height:30px"></span></td>
			<td style="font-size:20px">TOTALES<br>(12 PERIODOS)</td>
		
		</tr>
		</thead>
		<tbody id="fbody" class="scroll" style="border: 1px solid black">
		
		        	<tr class="hov" >
						<td width="142px"  colspan="2">VTA S. IMP $</td>
						<td><input type="text" style="height:30px ;"  disabled class="inputDetail" id="VENTASIMP_CL_P1"></td>
						<td><input type="text" style="height:30px ;"  disabled class="inputDetail" id="VENTASIMP_CL_P2"></td>
						<td><input type="text" style="height:30px ;"  disabled class="inputDetail" id="VENTASIMP_CL_P3"></td>
						<td><input type="text" style="height:30px ;"  disabled class="inputDetail" id="VENTASIMP_CL_P4"></td>
						<td><input type="text" style="height:30px ;"  disabled class="inputDetail" id="VENTASIMP_CL_P5"></td>
						<td><input type="text" style="height:30px ;"  disabled class="inputDetail" id="VENTASIMP_CL_P6"></td>
						<td><input type="text" style="height:30px ;"  disabled class="inputDetail"></td>
						
					</tr>
					
					<tr class="hov" >
						<td width="142px"  colspan="2">Nº IMP</td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="IMP_N_P1"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="IMP_N_P2"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="IMP_N_P3"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="IMP_N_P4"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="IMP_N_P5"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="IMP_N_P6"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" ></td>
					</tr>
					
					
					
					<tr class="hov" style="background-color: #000" >
						<td width="142px"  colspan="9"></td>
						
						
					</tr>
					<tr class="hov" >
						<td width="142px"  colspan="2">SUM.ENV</td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="SUMENV_P1"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="SUMENV_P2"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="SUMENV_P3"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="SUMENV_P4"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="SUMENV_P5"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="SUMENV_P6"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail"></td>
					</tr>
					<tr class="hov" >
						<td width="142px"  colspan="2">SUM.REND.</td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="SUMREND_P1"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="SUMREND_P2"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="SUMREND_P3"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="SUMREND_P4"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="SUMREND_P5"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="SUMREND_P6"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail"></td>
					</tr>
					
					<tr class="hov" style="background-color: #000" >
						<td width="142px"  colspan="9"></td>
					</tr>
					
					<tr class="hov" >
						<td width="142px"  colspan="2">KIT ENV.</td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="KITENV_P1"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="KITENV_P2"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="KITENV_P3"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="KITENV_P4"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="KITENV_P5"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="KITENV_P6"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail"></td>
						
					</tr>
					
					<tr class="hov" >
						<td width="142px"  colspan="2">KIT REND.</td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="KITREND_P1"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="KITREND_P2"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="KITREND_P3"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="KITREND_P4"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="KITREND_P5"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="KITREND_P6"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail"></td>
						
					</tr>
					<tr class="hov" style="background-color: #000" >
						<td width="142px"  colspan="9"></td>
						
						
					</tr>
					
					<tr class="hov" >
						<td width="142px"  colspan="2">FTOCOND ENV.</td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="FOTOCONDENV_P1"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="FOTOCONDENV_P2"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="FOTOCONDENV_P3"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="FOTOCONDENV_P4"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="FOTOCONDENV_P5"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="FOTOCONDENV_P6"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail"></td>
						
					</tr>
					
					<tr class="hov" >
						<td width="142px"  colspan="2">FTOCOND REND.</td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="FOTOCONDREND_P1"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="FOTOCONDREND_P2"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="FOTOCONDREND_P3"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="FOTOCONDREND_P4"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="FOTOCONDREND_P5"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="FOTOCONDREND_P6"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail"></td>
						
					</tr>
					
					
					<tr class="hov" style="background-color: #000" >
						<td width="142px"  colspan="9"></td>
					</tr>
					<tr class="hov" >
						<td width="142px"  colspan="2">CONS ENV.</td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="CONSUMIBLESENV_P1"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="CONSUMIBLESENV_P2"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="CONSUMIBLESENV_P3"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="CONSUMIBLESENV_P4"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="CONSUMIBLESENV_P5"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="CONSUMIBLESENV_P6"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail"></td>
						
					</tr>
					
					<tr class="hov" >
						<td width="142px"  colspan="2">CONS REND.</td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="CONSUMIBLESREND_P1"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="CONSUMIBLESREND_P2"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="CONSUMIBLESREND_P3"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="CONSUMIBLESREND_P4"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="CONSUMIBLESREND_P5"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="CONSUMIBLESREND_P6"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail"></td>
						
					</tr>
					
					
					<tr class="hov" style="background-color: #000" >
						<td width="142px"  colspan="9"></td>
					</tr>
					<tr class="hov" >
						<td width="142px"  colspan="2">REP ENV.</td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="REPUESTOSENV_P1"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="REPUESTOSENV_P2"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="REPUESTOSENV_P3"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="REPUESTOSENV_P4"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="REPUESTOSENV_P5"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="REPUESTOSENV_P6"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail"></td>
						
					</tr>
					
					<tr class="hov" >
						<td width="142px"  colspan="2">REP REND.</td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="REPUESTOSREND_P1"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="REPUESTOSREND_P2"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="REPUESTOSREND_P3"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="REPUESTOSREND_P4"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="REPUESTOSREND_P5"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="REPUESTOSREND_P6"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail"></td>
						
					</tr>
					
					
					<tr class="hov" style="background-color: #000" >
						<td width="142px"  colspan="9"></td>
					</tr>
					<tr class="hov" >
						<td width="142px"  colspan="2">CANT MAQUINAS</td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="MAQUINAS_P1"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="MAQUINAS_P2"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="MAQUINAS_P3"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="MAQUINAS_P4"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="MAQUINAS_P5"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="MAQUINAS_P6"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail"></td>
					</tr>
					
					<tr class="hov" >
						<td width="142px"  colspan="2">CANT DIREC.</td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="DIRS_P1"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="DIRS_P2"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="DIRS_P3"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="DIRS_P4"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="DIRS_P5"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="DIRS_P6"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail"></td>
					</tr>
					<tr class="hov" > 
						<td width="142px"  colspan="2">Nº TICK LOG.</td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="TICKLOG_P1"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="TICKLOG_P2"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="TICKLOG_P3"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="TICKLOG_P4"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="TICKLOG_P5"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="TICKLOG_P6"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail"></td>
					</tr>
					<tr class="hov" >
						<td width="142px"  colspan="2">Nº TICK S.TEC</td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="TICKSERVTEC_P1"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="TICKSERVTEC_P2"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="TICKSERVTEC_P3"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="TICKSERVTEC_P4"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="TICKSERVTEC_P5"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail" id="TICKSERVTEC_P6"></td>
						<td><input type="text" style="height:30px ;" disabled class="inputDetail"></td>
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
