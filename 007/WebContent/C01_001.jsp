<%@page import="VO.EmpresaVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	
String modname=(String)request.getAttribute("modname");
String Usu_nom=(String)request.getAttribute("usuname");


%>
<!DOCTYPE html>
<html lang="en">
  <head>
  <% 
  if(request.getParameter("mExito")!=null && !request.getParameter("mExito").equals("")){
  		if(request.getParameter("mExito").equals("OK")){
			out.println("<script>alert('OPERACI\u00d3N REALIZADA CON \u00c9XITO')</script>");
			//out.println("<script>window.location = '/001/'</script>");
		} 
	} 
	%>
    <meta charset="utf-8">
    <title>New Office - Cliente proveedor</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href='http://fonts.googleapis.com/css?family=Inconsolata:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/butons.css">
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" type="text/css" href="stylesheets/inputs.css">

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
	}
	tr.hov:hover{
		opacity:0.5;
		cursor: pointer;
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
    
   <script>
    $(document).ready(function() {
    	getEmpresas();
    	
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
    
    
    function getEmpresas(){
    	$('.loading').show();
    	$('.noloading').hide();
        $.post( "getEmpresasAsync", {  })
        .done(function( data ) {
        	$('#fbody').empty();
          		var empresas_arr = $.parseJSON(data);
          		
          		$.each( empresas_arr, function( key, empresa ) {
          			
          			var classvnv ="";
          			
          			
          			if(empresa.estadoVigNoVig.id==1){
          				classvnv="vig";
          			}
          				
          			if(empresa.estadoVigNoVig.id==2){
          				classvnv="novig";
          			}
          			
          			
          			
          			var line="<tr class='hov "+classvnv+"' onclick=\"location='Cclpr2?empresas_id="+empresa.id+"';\" style='font-size:20px'  >";
          			
          			line+="<td width=\"47px\" >"+empresa.id+"</td>";
          			
          			line+="<td width='620px' >"+empresa.nombre_nof+"</td>";
          			
          			
          			line+="<td width='155px' >"+empresa.estadoVigNoVig.nombre+"</td>";
          			
          			line+="</tr>";
          			
          			
          			$('#fbody').append(line);
          		 
          		});
          		
          		$('.loading').hide();
            	$('.noloading').show();	
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
                        
                     
                     <div class="brand"><img src="lib/bootstrap/img/logonew_big.png"></div>
                     <div class="version" align="right">
                     <p>N007.C.01.001</p>
                     </div>
                    <form method="get" action="" style="margin: 0px 0px 0px 0px;">                 
                    <button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESI�N</button>
                    </form>
                    <button type="button" onclick="location='/007/'" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">VOLVER</button>
                   
                    <div align="center" class="title" >
                    <p>CONSULTAR <%=modname %></p>
                    </div>
                    
               
                             
    </div>
         <div align="center" class="form-group has-warning">
         <label class="control-label" for="inputWarning" style="font-size:20px;color:#000"><strong>TEXTO PARA FILTRAR:</strong></label>
         <input maxlength="30" type="text" id="searchInput" class="form-control span12" style="width:348px;height:30px"	autofocus="autofocus">
         </div>
</div>
  <div class="content">
        
         <div class="containermenu">
         <div class="menu">
         <div class="titulomenu" style="border: 1px solid black">
         <h3 class="subtitle" align="center" style="margin:0px 0px 0px 0px">
         
         <p  style="margin:0px 0px;font-size:20px;background-color: #39F">ELEGIR CLIENTE-PROVEEDOR A CONSULTAR</p> </h3>
  
        </div>
        
        
      <div style=" max-height:300px; ">
  <table class="table">
<thead style="border: 1px solid black">
<tr style="background-color:#0101DF;color:#FFFFFF">
<td width="47px" style="font-size:20px"><strong>ID</strong></td>
<td width="620px" style="font-size:20px"  ><strong>NOMBRE</strong></td>
<td width="155px" style="font-size:20px"><strong>ESTADO</strong></td>
</tr> 
</thead> 
<tbody id="fbody " class="scroll loading" style="border: 1px solid black">
<tr class="hov"  >
	<td colspan="5" width="1200px" align="center" style="text-align: center;"><img src="images/loading.GIF" /></td>
</tr> 
</tbody>
<tbody id="fbody" class="scroll noloading" style="border: 1px solid black;display: none;">
</tbody>
</table>
       </div> 
  <!-- Table -->

         
        
         </div>
         </div>
        
    </div>
    
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