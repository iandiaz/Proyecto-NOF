<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
String[] arAlertas =(String[]) request.getAttribute("ar_alertas");
String filtro = "";
if(request.getAttribute("filtro")!=null){
filtro = (String)request.getAttribute("filtro");
}
String Usu_nom=(String)request.getAttribute("usuname");
String clientes_id=(String)request.getAttribute("clientes_id");

String obs=(String)request.getAttribute("obs");
String ref=(String)request.getAttribute("ref");
String[] ar_detguias =(String[]) request.getAttribute("ar_detguias");

String empresa_emisor=(String)request.getAttribute("empresa_emisor");
String afecta=(String)request.getAttribute("afecta");
String datepickerfechafactura=(String)request.getAttribute("datepickerfechafactura");
String fac_servim_fecvencimiento=(String)request.getAttribute("fac_servim_fecvencimiento");
String fac_servim_tipodte=(String)request.getAttribute("fac_servim_tipodte");

String desc=(String)request.getAttribute("desc");
String glosa_desc=(String)request.getAttribute("glosa_desc");

    %>
<!DOCTYPE html>
<html lang="en"><head>
    <meta charset="utf-8">
    <title>New Office</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/butons.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/inputs.css">
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">

    <script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>
<script>
$(function(){
    $(".allCheck").click(function() {
		 if($(this).is(':checked')){
			//alert('chek');
			$(".ck_deta").prop("checked",true);
			$(".ck_deta").change();
		 }else{ 
		 //alert('no chek');
		
		  	$(".ck_deta").prop("checked",false);
			$(".ck_deta").change();
			//$(this).attr("checked",false);
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
	  
	    height: 255px;
	    overflow-y: scroll;
	}
	thead, tbody {
		 min-width:100%;
    	display: inline-block;
	}
	tr{
		 width:100%;
		 font-size:20px;
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
	  <p>N802.I.03.001</p>
	</div>
	<form method="get" action="" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESI�N</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/802/ires_add?n=0&clientes_id=<%=clientes_id %>'">VOLVER</button> 
	<div align="center" class="title">
		<p >INGRESAR FACTURA AFECTA - EXENTA GUIA RESUMEN</p>
	</div>
</div>
    
         <div align="center" class="form-group has-warning">
         <label class="control-label" for="inputWarning" style="font-size:20px;color:#000"><strong>TEXTO PARA FILTRAR:</strong></label>
         <input maxlength="25" type="text" id="searchInput" class="form-control span12" style="width:290px;height:30px" autofocus="autofocus">
         </div>

<div class="content">        
	<div class="containermenu">
         <div class="menu">
         	<div class="titulomenu" style="border: 1px solid black">
         		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px">
         		<p style="margin:0px 0px;font-size:20px;background-color:#39F;width:998px">ELEGIR GUIA DE DESPACHO VENTA ACTIVO O SERVICIO</p> </h3>
			</div>
			<form method="post" action="ires_add" style="margin:0px 0px 0px 0px">
			<input type="hidden" name="ref" value="<%=ref%>"><input type="hidden" name="obs" value="<%=obs%>" >
			<input type="hidden" style="width:120px" name="clientes_id" value="<%=clientes_id %>" >
			<input type="hidden" name="n" value="0" >
			
			
			<input type="hidden" name="empresa_emisor" value="<%=empresa_emisor %>" >
			<input type="hidden" name="datepickerfechafactura" value="<%=datepickerfechafactura %>" >
			<input type="hidden" name="afecta" value="<%=afecta %>" >
			<input type="hidden" name="fac_servim_fecvencimiento" value="<%=fac_servim_fecvencimiento %>" >
			<input type="hidden" name="fac_servim_tipodte" value="<%=fac_servim_tipodte %>" >
			
			<input type="hidden" name="desc" value="<%=desc %>" >
			<input type="hidden" name="glosa_desc" value="<%=glosa_desc %>" >
			
			
        	<div style=" max-height:400px; width:1000px; ">
				<table class="table" style="height:340px; margin-left:0px; width:100%;">
				<thead style="border: 1px solid black">
					<tr style="background-color:#0101DF;color:#FFFFFF">
						<td width="10px" style="font-size:20px"><input type="checkbox" class="allCheck"></td>
						<td width="80px" style="font-size:20px"><strong>FOLIO</strong></td>
						<td width="590px" style="font-size:20px"><strong>CLIENTE</strong></td>
						<td width="130px" style="font-size:20px"><strong>MONTO</strong></td>
						<td width="170px" style="font-size:20px"><strong>FECHA</strong></td>
					</tr>  
				<tbody id="fbody" class="scroll" style="border: 1px solid black">
				 <%        
				 	String cuenta="0";
					for(int i =0; i<arAlertas.length; i++){
						cuenta = "1";
				    	String[] Alertas = arAlertas[i].split("/");
				        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
				        String valString = nf.format(Integer.parseInt(Alertas[2])).replace(",",".");
				        
				        String check = "";
				        if(ar_detguias!=null){
				        	//buscamos el id en el arreglo anterior
				        	for(int j =0; j<ar_detguias.length; j++){
				        		String[] det_g = ar_detguias[j].split("/");
				        		if(Alertas[0].equals(det_g[0])){check = "checked='checked'";break;}
				        	}
				        	
				        	
				        	
				        	
				        }
				%>
						<tr class="hov">
							<td width="10px" ><input type="checkbox" value="<%=Alertas[0]%>/<%=Alertas[2]%>/<%=Alertas[3]%>/<%=Alertas[4]%>" name="permisos[]" class="ck_deta" <%=check %> ></td>
							<td width="80px" ><%=Alertas[4]%></td>
							<td width="590px" ><%=Alertas[1]%></td>
							<td width="130px" >$<%=valString%></td>
							<td width="170px" ><%=Alertas[3]%></td>
						</tr>
				<% } %>
				</table>
			</div>
			<div class="bgrabar">
		          <button type="submit" name="agregar" <% if(cuenta == "0"){ %> disabled <% } %> class="btn btn-success" >AGREGAR</button>
			</div> 
			</form>
	</div>
	<br><br><br>
<div class="footerapp">               
	<p style="float: left;"><i class="icon-user"></i><strong><%=Usu_nom %></strong></p>
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


