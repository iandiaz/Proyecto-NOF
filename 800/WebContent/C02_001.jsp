<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%

String modulo=(String)request.getAttribute("modulo");
String Usu_nom=(String)request.getAttribute("usuname");
String mod=(String)request.getAttribute("mod");

String url="";
if(modulo.equals("801")){ url = "C03_001_801";}
if(modulo.equals("802")){ url = "cguias_res";}
if(modulo.equals("803")){ url = "cfac_servim";}
if(modulo.equals("804")){ url = "cfac_servotros";}
if(modulo.equals("811")){ url = "cfac_act";}
if(modulo.equals("812")){ url = "cfac_serv";}
if(modulo.equals("821")){ url = "cguias_venta";}
if(modulo.equals("822")){ url = "c822";}
if(modulo.equals("823")){ url = "cguias_desn";}
if(modulo.equals("824")){ url = "cguias_dest";}
if(modulo.equals("831")){ url = "C03_001_831";}


String[] ar_empresas =(String[]) request.getAttribute("ar_empresas");


String tipo_dte=(String)request.getAttribute("tipo_dte");

String f1=(String)request.getAttribute("f1");
String f2=(String)request.getAttribute("f2");
%>
<!DOCTYPE html>
<html lang="en"><head>
    <meta charset="utf-8">
    <title>New Office - Generador Documento DTE</title>
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
<script>
	   $(function(){
	    $("#todos").click(function(e) {
	     if($("#todos").attr('checked')){
			$('.dale').attr('checked',true);
	     }else{
			$('.dale').attr('checked',false);
	     }
		});
	   });
	  </script>
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
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
		
  	tbody {
	    height: 225px;
	    overflow-y: scroll;
	    font-size:20px;
	}
	thead, tbody {
		 min-width:100%;
    	display: inline-block;
	}
	tr{
		 width:100%;
	}
	tr.hov:hover{
		opacity:0.5;
		cursor: pointer;
	}
  
    </style>
    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
    
   <script>
    $(document).ready(function() {
    	
    	getDataAsync($('#emisor_filter :selected').val());

    	
    	$('#emisor_filter').change(function(){
    		getDataAsync($('#emisor_filter :selected').val());

    	});
    	
    	
         $("#searchInput").keyup(function(){
        	
   	//hide all the rows
             $("#fbody").find("tr").hide();

   	//split the current value of searchInput
             var data = this.value;//.split(" ");
   	//create a jquery object of the rows
             var jo = $("#fbody").find("tr");
             
   	//Recusively filter the jquery object to get results.
            // $.each(data, function(i, v){
                 jo = jo.filter("*:contains('"+data.toUpperCase()+"')");
             //});
           //show the rows that match.
             jo.show();
        //Removes the placeholder text  
      
         }).focus(function(){
         //    this.value="";
             $(this).css({"color":"black"});
             $(this).unbind('focus');
         }).css({"color":"#000"});
         

     });
    
    
    function getDataAsync(id_emisor){
    	$('#fbody').html("");
    	$('.loading_p').show();
    	$('#fbody').hide();
    	
    	
    	$.post( "getDteEncAsync", {modulo:"<%=modulo%>", id_emisor:id_emisor})
        .done(function( data ) {
        	
        	var data_arr = $.parseJSON(data);
      		
        	$.each( data_arr, function( key, data_ ) {
        		$('#fbody').append("<tr class='hov' onclick=\"location='<%=url%>?id_dte="+data_.id+"';\">"
									+"<td width='120px'>"+data_.Folio+"</td>"
									+"<td width='400px'>"+data_.cliente.nombre_nof+"</td>"
									+"<td width='150px'>$"+data_.Total+"</td>"
									+"<td width='150px'>"+data_.FechaEmision+"</td>"
									+"<td width='90px'>EMITIDA</td>"
									+"<td width='150px'>ENVIADA</td>"
									+"</tr>");
      		}); 
        	
        	
        	$('.loading_p').hide();
        	$('#fbody').show();
        	
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
	  <p>N800.C.02.001</p>
	</div>
	<form method="get" action="iperusu" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/800/cguias_stipo'">VOLVER</button> 
	<div align="center" class="title">
		<p >CONSULTAR GENERADOR DOCUMENTO DTE</p>
	</div>
</div>
    <form action="" method="get" id="fsearch" >
     <input type="hidden" name="f1" id="f1" value="<%=f1%>">
     <input type="hidden" name="f2" id="f2" value="<%=f2%>">
     
     <input type="hidden" name="tipo_dte" id="tipo_dte" value="<%=tipo_dte%>">
<div align="center" class="form-group has-warning" style="font-size: 20px"> 
		<strong>EMISOR:</strong><select name="id_e" id="emisor_filter" style="margin:0px 0px 0px 0px;" >
				<% for(int i =0; i<ar_empresas.length; i++){
					String[] empresas = ar_empresas[i].split("/");%>
				<option value="<%=empresas[0]%>"  ><%=empresas[1] %></option>
			<% } %>
    		</select>
	</div>
	</form>
<div align="center" class="form-group has-warning">
	<label class="control-label" for="inputWarning" style="font-size:20px;color:#000"><strong>TEXTO PARA FILTRAR:</strong></label>
	<input maxlength="25" type="text" id="searchInput" class="form-control span12" style="width:290px;height:30px" autofocus="autofocus">
</div>
<div class="content">
<form action="cguias_search" name="form1" method="get">
	<div class="containermenu">
		<div class="menu">
			<div class="titulomenu" style="border: 1px solid black;">
				<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px">
				<p style="margin:0px 0px;font-size:20px;background-color:#39F;width:1198px">ELEGIR DOCUMENTO SII - <%=mod %></p> </h3>
			</div>
			<div style="max-height:400px; width:1200px;">
				<table class="table" style="margin-left:0px">
					<thead style="border: 1px solid black;background-color: #0101DF">
					<tr style="background-color:#0101DF;color:#FFFFFF">
						<td width="120px" style="font-size:20px"><strong>FOLIO</strong></td>
						<td width="400px" style="font-size:20px"><strong>CLIENTE</strong></td>
						<td width="150px" style="font-size:20px"><strong>MONTO</strong></td>
						<td width="150px" style="font-size:20px"><strong>FECHA</strong></td>
						<td width="90px" style="font-size:20px"><strong>ESTADO</strong></td>
						<td width="150px" style="font-size:20px"><strong>ESTADO SII</strong></td>						
					</tr>  
					<tbody id="fbody" class="scroll" style="border: 1px solid black">
					</tbody>
					
					<tbody style="display: none;" class="scroll loading_p" style="border: 1px solid black">
					<tr ><td width="1200px" align="center" style="text-align: center;"><img alt="" src="/011/images/loading.GIF" style="height:100px"></td></tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</form> 
</div>
<br><br><br>
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


