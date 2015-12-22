<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
String[] arAlertas =(String[]) request.getAttribute("ar_alertas");
String filtro = "";
if(request.getAttribute("filtro")!=null){
filtro = (String)request.getAttribute("filtro");
}
String Usu_nom=(String)request.getAttribute("usuname");
String[] ar_empresas =(String[]) request.getAttribute("ar_empresas");
String id_e=(String)request.getAttribute("id_e");

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
         
         $("#emisor_filter").change(function(){
       	   	
   	         var data = this.value;//.split(" ");
   	     
             $('#fsearch').submit();

      
         });

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
	  <p>N821.E.01.001</p>
	</div>
	<form method="get" action="iperusu" style="margin: 0px 0px 0px 0px;">       
		<button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESI�N</button>
	</form>  
	<button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/821/'">VOLVER</button> 
	<div align="center" class="title">
		<p >ELIMINAR GUIA DE DESPACHO VENTA ACTIVO</p>
	</div>
</div>
     <form action="" method="get" id="fsearch" >
<div align="center" class="form-group has-warning" style="font-size: 20px"> 
		<strong>EMISOR:</strong><select name="id_e" id="emisor_filter" style="margin:0px 0px 0px 0px;" >
				<% for(int i =0; i<ar_empresas.length; i++){
					String[] empresas = ar_empresas[i].split("/");%>
				<option value="<%=empresas[0]%>" <% if(id_e.equals(empresas[0])){ %> selected="selected" <%} %> ><%=empresas[1] %></option>
			<% } %>
    		</select>
	</div>
	</form>
         <div align="center" class="form-group has-warning">
         <label class="control-label" for="inputWarning" style="font-size:20px;color:#000"><strong>TEXTO PARA FILTRAR:</strong></label>
         <input maxlength="25" type="text" id="searchInput" class="form-control span12" style="width:290px;height:30px" autofocus="autofocus">
         </div>

<div class="content">        
	<div class="containermenu">
         <div class="menu">
         	<div class="titulomenu" style="border: 1px solid black">
         		<h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px">
         		<p style="margin:0px 0px;font-size:20px;background-color:#39F;width:1198px">ELEGIR GUIA A ELIMINAR DTE PARA SII</p> </h3>
			</div>
        	<div style=" max-height:300px; width:1200px; ">
  <table class="table" style="margin-left:0px; width:100%;">
<thead style="border: 1px solid black">
<tr style="background-color:#0101DF;color:#FFFFFF">

<td width="630px" style="font-size:20px"><strong>CLIENTE</strong></td>
<td width="130px" style="font-size:20px"><strong>MONTO</strong></td>
<td width="140px" style="font-size:20px"><strong>FECHA</strong></td>
<td width="150px" style="font-size:20px"><strong>ESTADO</strong></td>
<td width="150px" style="font-size:20px"><strong>ESTADO SII</strong></td>
</tr>  
<tbody id="fbody" class="scroll" style="border: 1px solid black">

 <%        
         for(int i =0; i<arAlertas.length; i++){
        	 String[] Alertas = arAlertas[i].split("/");

        	 java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
        	 String valString = nf.format(Integer.parseInt(Alertas[2])).replace(",",".");
        	 %>
        		
			<tr class="hov" style="color:<%=Alertas[4].equals("1") ? "#000" :"#FFF"%>;font-size:20px;background-color:<%=Alertas[4].equals("1") ? "#00FF00" :"#F00"%>;font-size:20px;" onclick="location='e2?gv_id=<%=Alertas[0]%>';">
				
				<td width="630px" ><%=Alertas[1]%></td>
				<td width="130px" >$<%=valString%></td>
				<td width="140px" ><%=Alertas[3]%></td>
				<td width="150px" ><%=Alertas[4].equals("1") ? "VIGENTE" :"NO VIGENTE"%></td>
				<td width="150px" ><%=Alertas[5]%></td>
			</tr>
			
        	<% } %>

</table>
	</div>
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

