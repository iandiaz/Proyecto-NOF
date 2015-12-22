<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	
Object[] arMenus =(Object[]) request.getAttribute("armenus");

String Usu_nom=(String)request.getAttribute("usuname");

String correlativo=(String)request.getAttribute("perfil_id");
String[] per_otor=(String[])request.getAttribute("per_otor");

String per_name =(String)request.getAttribute("perfil_name"); 

String estado_vignovig=(String)request.getAttribute("estado_vignovig"); 
String estado_vignovig_id=(String)request.getAttribute("estado_vignovig_id"); 

String user_crea=(String)request.getAttribute("user_crea"); 
String user_mod=(String)request.getAttribute("user_mod"); 
String fec_crea=(String)request.getAttribute("fec_crea"); 
String fec_mod=(String)request.getAttribute("fec_mod");
    %>
<!DOCTYPE html>
<html lang="en"><head>
    <meta charset="utf-8">
    <title>New Office- Perfil usuario</title>
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
     .divrep{
  border: 1px solid black;
width: 400px;
text-align: left;
box-shadow: 0px 2px 5px 0px rgba(50, 50, 50, 0.75);
border-bottom: 1px solid black;
position: absolute;
background-color: #fff;
padding: 0px 5px 5px 5px;
right: 10px;
  }
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
  
    tbody {
	   
	    height: 150px;
	    overflow-y: scroll;
	    position: relative;
	}
	thead, tbody {
		 min-width:100%;
    	display: inline-block;
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
	.amarillo{
			border-color: #FFFF00;
			 background-color: #FFFF00;
		}
	.amarillo:focus{
		border-color: #F4FA58;
		box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 6px #F0EC33;
	}
	@media screen and (max-width: 700px) {
		.menu2{
			float: left;
		}
		
	}
	
  </style>
  </head>

  <!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
  <!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
  <!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
  <!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
  <!--[if (gt IE 9)|!(IE)]><!--> 
 
  
  <body class="" onload="activeChecks()"> 
  <!--<![endif]-->
    
    <script type="text/javascript">
    	function validate(){
    		
    		if(document.getElementById('perfilusu_nombre').value==""){
    			alert("DEBE ASIGNAR UN NOMBRE AL PERFIL USUARIO");
    			document.getElementById('perfilusu_nombre').focus();
    			return false;
    		}
    		if(document.getElementById('select_estado').value==0){
    			alert("DEBE SELECCIONAR UN ESTADO");
    			document.getElementById('select_estado').focus();
    			return false;
    		}
    		
    		
    		return true;
    	}
    	
    	var pers_o = new Array();
    	
    	<% for(int i =0 ; i< per_otor.length; i++){%>
    		
    			pers_o[<%=i%>] = "<%=per_otor[i]%>";
    	<% } %>
    	
    	
    	function validaPermiso(cod_per){
    		existe = false;
    		for (var i = 0; i < pers_o.length; i++) {
				if(pers_o[i]==cod_per){ existe =true;break;}
			}
    		return existe;
    	}
    	
    	
    	function activeChecks(){
    		
    		//var sList = "";
    		$(':checkbox').each(function () {
    		   //alert($(this).val());
    		   if(validaPermiso($(this).val())){
    			   $(this).attr("checked","checked");
    			   //alert($(this).val());
    		   }
    		});
    		//console.log (sList);
    		
    	}
		function mostrarDiv(elem) {
    		
			$('.'+elem).toggle();
		}
    </script>
    
    <div class="navbar">
                        
                     
                     <div class="brand"><img src="lib/bootstrap/img/logonew_big.png"></div>
                     <div class="version" align="right">
                     <p>N004.M.02.001</p>
                     </div>
                    <form method="post" action="mperusu" style="margin: 0px 0px 0px 0px;">    
                    <input type="hidden" name="id_p" value="<%=request.getParameter("id_p")%>">   
                    <button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
                     	</form>  
                    <button type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/004/mperusu'">VOLVER</button>
                   
                    <div align="center" class="title" >
                    <p >MODIFICAR PERFIL USUARIO</p>
                    </div>
                   
                    
                    
               
                             
    </div>
         
  <div class="content">
        <form action="" method="post" onsubmit="return validate()"  >
         <div class="containermenu">
         <div class="menu_perfilusuario_cabecera" align="center">
        	<div class="divhead">
				<strong>CORRELATIVO:</strong><input type="text" value="<%=correlativo %>" style="width:50px;height:30px; background:#FFF; margin: 0px 0px 0px 0px;color: black;" readonly="readonly" disabled="disabled">
		    </div>       
		   
		    
		    <div class="divhead">
		 		<strong>ESTADO:</strong><select name="select_estado" id="select_estado">
	 		    <option value="1" <% if(estado_vignovig_id.equals("1")){%> selected="selected" <% } %>>VIGENTE</option>
	 		    <option value="2" <% if(estado_vignovig_id.equals("2")){%> selected="selected" <% } %>>NO VIGENTE</option>
	 		    </select> <span class="cabecera" style="color:#F00">*</span>
 		    </div>
 		    
 		    <div class="divhead" style=" min-width: 500px;" >
		     	<strong >NOMBRE PERFIL USUARIO:</strong><input type="text" id="perfilusu_nombre" name="perfilusu_nombre" maxlength="25" value="<%=per_name %>" class=" amarillo" style="width:290px;height:30px; background:#FF3;margin: 0px 0px 0px 0px;" required="required" autofocus="autofocus">
			   	<span class="cabecera" style="color:#F00">*</span>
			</div>
			
			<div class="divhead" style=" min-width: 500px;" >
		     	<strong >Fecha creación:</strong><input type="text" value="<%=fec_crea %>"  style="width:225px;height:30px; background:#FFF;margin: 0px 0px 0px 0px;color: black;" disabled="disabled" readonly="readonly">
			</div>
			 <div class="divhead" style=" min-width: 500px;" >
		     	<strong >Usuario creador:</strong><input type="text" value="<%=user_crea %>"  style="width:400px;height:30px; background:#FFF;margin: 0px 0px 0px 0px;color: black;" disabled="disabled" readonly="readonly">
			</div>
			 <div class="divhead" style=" min-width: 500px;" >
		     	<strong >Fecha última mod:</strong><input type="text" value="<%=fec_mod %>"  style="width:225px;height:30px; background:#FFF;margin: 0px 0px 0px 0px;color: black;" disabled="disabled" readonly="readonly">
			</div>
          	<div class="divhead" style=" min-width: 500px;" >
		     	<strong >Usuario última mod:</strong><input type="text" value="<%=user_mod %>"  style="width:400px;height:30px; background:#FFF;margin: 0px 0px 0px 0px;color: black;" disabled="disabled" readonly="readonly">
			</div>
         
           </div>
         
         
         
         
  <table class="table004det">
  <thead style="border-bottom: 1px solid black">
  <tr>
     <th scope="col" style="background:#39F" width="32px">ID</th>
    <th scope="col" style="background:#39F" width="54px">PRG</th>
    <th scope="col" style="background:#39F" width="645px" >NOMBRE MODULO</th>
    <th scope="col" style="background:#093" width="33px"  >IN</th>
    <th scope="col" style="background:#F00" width="33px">EL</th>
    <th scope="col" style="background:#FF0; color:#000" width="33px">MO</th>
    <th scope="col" style="background:#39F" width="33px">CO</th>
    <th scope="col" style="background:#609" width="37px">RE</th>
  </tr>
  </thead>
  <tbody class="scroll">
  
    <%
    String mod_actual="";
         for(int i =0; i<arMenus.length; i++){
        	 if(arMenus[i]==null) break;
        	String[] modulos = arMenus[i].toString().split("/");
        	
        	%>
         
  <tr>
    <td class="int" width="32px"><%=modulos[0]%></td>
    <td class="int" width="54px">N<%=modulos[1]%></td>
    <td class="int" width="645px"><%=modulos[2]%></td>
    <td class="int" align="center"width="32px"><input id="permisos" name="permisos[]" value="<%=modulos[3]==null? "":modulos[3] %>" type="checkbox" ></td>
    <td class="int" align="center"width="32px"><input id="permisos" name="permisos[]" value="<%=modulos[4]==null? "":modulos[4]%>" type="checkbox" ></td>
    <td class="int" align="center"width="32px"><input id="permisos" name="permisos[]" value="<%=modulos[5]==null? "":modulos[5]%>" type="checkbox"  ></td>
   <td class="int" align="center"width="32px"><input id="permisos" name="permisos[]" value="<%=modulos[6]==null? "":modulos[6]%>" type="checkbox" ></td>
    <td class="int" align="center"width="32px" ><% if(modulos[7].equals("-1")){%><input name="permisos[]" value="<%=modulos[7]==null? "":modulos[7]%>"  type="checkbox">
     <% }else{%>
     <input type="button" value="R" class="" onclick="mostrarDiv('div<%=i%>')"> 
	<div style="display: none;width: auto;" class="div<%=i%> divrep">
     	<% String[] per_repotes_ar=modulos[7].toString().split("y");
    	
    	for(int x=0 ; x<per_repotes_ar.length;x++ ){
    		String[] date_rep=per_repotes_ar[x].toString().split("__");
    		%>
    		<p><input name="permisos[]" value="<%=date_rep[0]%>"  type="checkbox"><%=date_rep[1]%></p>
    	<% } %>
    	</div> 	
   <% } %></td>
  </tr>
         
        <%  }
         %>
  </tbody>
</table>
         
          
            
            <div class="bgrabar">
           <button type="submit" name="grabar" class="btn btn-success " >GRABAR</button>
           </div>
           
          
           
        
        
        </form>
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


