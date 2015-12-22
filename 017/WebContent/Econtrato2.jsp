<%@page import="java.util.ArrayList"%>
<%
	String[] ar_usuariosempresas =(String[]) request.getAttribute("ar_usuariosempresas");
	String[] ar_usuariosempresa =(String[]) request.getAttribute("ar_usuariosempresa");
	String[] ar_estados =(String[]) request.getAttribute("ar_empresas");
	String[] ar_estado =(String[]) request.getAttribute("ar_empresa");
	String Usuarios_nombre =(String) request.getAttribute("Usuarios_nombre");
	String estados_vig_novig_nombre=(String) request.getAttribute("estados_vig_novig_nombre");
	String Usu_nom=(String)request.getAttribute("usuname");
	String Usuarios_id=(String)request.getAttribute("Usuarios_id");
	String Usuarios_ape_p=(String)request.getAttribute("Usuarios_ape_p");
	String Usuarios_ape_m=(String)request.getAttribute("Usuarios_ape_m");
	String Usuarios_pass=(String)request.getAttribute("Usuarios_pass");
	String Usuarios_login=(String)request.getAttribute("Usuarios_login");
	String Usuarios_telefono=(String)request.getAttribute("Usuarios_telefono");
	String Usuarios_email=(String)request.getAttribute("Usuarios_email");
	String tipo_usu_nombre=(String)request.getAttribute("tipo_usu_nombre");
	String perfilusu_nombre=(String)request.getAttribute("perfilusu_nombre");
	String empresas_nombre=(String)request.getAttribute("empresas_nombre");
	String Usuarios_inicial=(String)request.getAttribute("Usuarios_inicial");
	String Usuarios_all_emp=(String)request.getAttribute("Usuarios_all_emp");
	String Usuarios_imei=(String)request.getAttribute("Usuarios_imei");
	

	String user_crea=(String)request.getAttribute("user_crea");
	String user_mod=(String)request.getAttribute("user_mod");
	String fec_crea=(String)request.getAttribute("fec_crea");
	String fec_mod=(String)request.getAttribute("fec_mod");
	String file1=(String)request.getAttribute("file1");
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
	  
	    height: 95px;
	    overflow-y: scroll;
	}
		
		thead, tbody {
			 min-width:100%;
	    	display: inline-block;
	    	
		}
		select{
			width:225px;
				}
		
	@media screen and (min-width: 700px) {
		.cuadroblanco{
			box-shadow:0px 2px 5px 0px rgba(50, 50, 50, 0.75);
			border: 1px solid black;
			min-height:100%; 
			height:195px; 
			min-width: 350px;
			max-width:1000px;
			position: relative;
			background:#ccc;
			margin: 0 auto;
			padding-left: 10px;
			padding-top: 5px;
		}
		.inputMovil{
			width:100%;
		}

	}
	@media screen and (max-width: 699px) {
		.cuadroblanco{
			min-width:100%; 
			 height:100px; min-height:250px;width:350px;
			background:#ccc;
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
                     <p>N008.E.02.001</p>
                     </div>
                     <form method="get" action="Econtrato2" style="margin:0px 0px 0px 0px">                 
                    <button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
                    </form>
                    <button  type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onClick="location='Econtrato'">VOLVER</button>
                   
                    <div align="center" class="title">
                    <p >ELIMINAR CONTRATO</p>
                    </div>
                   
                    
                    
               
                             
    </div>
         
   <div class="content" >
        <form action="Econtrato2" name="form1" method="post">
           <div class=" cuadroblanco" >
         
         <input type="hidden" name="Usuarios_id" value="<%=request.getParameter("Usuarios_id")%>">
			<div class="divhead">
				<strong >ID:</strong><input type="text" value="<%=Usuarios_id %>" style="width:50px;height:30px; background:#FFF;" disabled="disabled">
			</div>
			                    
			<div class="divhead">
				<strong>NOM:</strong><input maxlength="35" type="text" style="width:290px;height:30px; background:#FFF;" value="<%=Usuarios_nombre%>" disabled="disabled" >
			</div>
			<div class="divhead">
				<strong>ETAPA CONTRATO:</strong><input maxlength="25" type="text" style="width:200px;height:30px; background:#FFF;" value="<%=estados_vig_novig_nombre%>" disabled="disabled" >
			</div>
				<div class="divhead">
				<strong>FECHA:</strong><input maxlength="15" type="text" style="width:130px;height:30px; background:#FFF;" value="<%=Usuarios_ape_m%>" disabled="disabled" >
			</div>
			
			<div class="divhead">
				<strong>FECHA INICIO:</strong><input maxlength="25" type="text" style="width:130px;height:30px; background:#FFF;" value="<%=Usuarios_pass%>" disabled="disabled" >
			</div>
			
			<div class="divhead">
				<strong>FECHA TERMINO:</strong><input maxlength="25" type="text" style="width:130px;height:30px; background:#FFF;" value="<%=Usuarios_login%>" disabled="disabled" >
			</div>
			
			<div class="divhead">
				<strong>ESTADO:</strong><input maxlength="15" type="text" style="width:157px;height:30px; background:#FFF;" value="<%=Usuarios_ape_p%>" disabled="disabled" >
			</div>
			
		
			
			
			<div class="divhead">
				<strong>DURAC INIC:</strong><input maxlength="25" type="text" style="width:50px;height:30px; background:#FFF;" value="<%=Usuarios_email%>" disabled="disabled" >
				<strong>(MESES)</strong>
			</div>
			
			<div class="divhead">
				<strong>PLAZO RENOV:</strong><input maxlength="25" type="text" style="width:50px;height:30px; background:#FFF;" value="<%=Usuarios_telefono%>" disabled="disabled" >
				<strong>(MESES)</strong>
			</div>
			
			
			      
			
			
			
			
			
			<div class="divhead">
				<strong>Usu creador:</strong>
				<input type="text" value="<%=tipo_usu_nombre%>" style="width:200px;height:30px; background:#FFF; " disabled="disabled">
			</div>
			
			<div class="divhead"><strong>Fec. creación:</strong><input type="text" value="<%=fec_crea%>" style="width:225px;height:30px; background:#FFF;color: #000 " disabled="disabled">
	</div>
				
			<div class="divhead">
				<strong>Fec. últ mod:</strong>
				<input type="text" value="<%=fec_mod%>" style="width:225px;height:30px; background:#FFF;color: #000 " disabled="disabled">
			</div>
			
			<div class="divhead">
				<strong>Usu últ mod:</strong>
				<input type="text" value="<%=user_mod%>" style="width:400px;height:30px; background:#FFF;color: #000 " disabled="disabled">
			</div>
	
   
         </div> 
         <table  class="table004det"  >
  <thead style="border-bottom: 1px solid black">
  <tr>
    <th scope="col" style="background:#39F; width: 46px;" >ID</th>
    <th scope="col" style="background:#39F" width="25px"></th>
    <th scope="col" style="background:#39F ; width: 940px;" >EMPRESA</th>
  </tr>
  </thead>
  <tbody class="scroll" >
  
     <%
    String mod_actual="";
   
         for(int i =0; i<ar_estados.length; i++){
        	
        	 if(ar_estados[i]==null) break;
        	String[] usuemp = ar_estados[i].toString().split("/");
        	boolean usuario_has_emp=false;
        	
        	 for(int j =0; j<ar_usuariosempresas.length; j++){
            	 if(ar_usuariosempresas[j].equals(usuemp[0])){  usuario_has_emp=true; break;}   
        	 }
        	 
        	 if(usuario_has_emp){
        	%>
         
  <tr>
     <td class="int" style="width: 46px;"><%=usuemp[0]%></td>
    <td class="int" align="center"><input name="permisos[]" <% if(usuario_has_emp){%> checked="checked" <% } %> value="<%=usuemp[0]%>" type="checkbox" disabled="disabled"></td>
    <td class="int" width="940px"><%=usuemp[1]%></td>
    
  </tr>
         
        <%    }
        	 }       	
         %>
  </tbody>
</table> 

<table  class="table004det"  >
  <thead style="border-bottom: 1px solid black">
  <tr>
    <th scope="col" style="background:#39F; width: 12px;" >ID</th>
    <th scope="col" style="background:#39F" width="25px"></th>
    <th scope="col" style="background:#39F ; width: 940px;" colspan="2" >TIPO CONTRATO</th>
  </tr>
  </thead>
  <tbody class="scroll" >
  
     <%
    String mod_actua="";
   
         for(int i =0; i<ar_estado.length; i++){
        	
        	 if(ar_estado[i]==null) break;
        	String[] usuem = ar_estado[i].toString().split("/");
        	boolean usuario_has_em=false;
        	boolean marca=false;
        	for(int j =0; j<ar_usuariosempresa.length; j++){
       		 String[] ar= ar_usuariosempresa[j].split(";");
       		 
           	if(ar[0].equals(usuem[0])){  
           		usuario_has_em=true; 
           		 if(ar[1].equals("1")) marca=true;
           		 break;
           	}   
       	 }
        	%>
         
  <tr>
    <td class="int"><%=usuem[0]%></td>
    <td class="int" align="center"><input name="permisos[]" <% if(usuario_has_em){%> checked="checked" <% } %> value="<%=usuem[0]%>" type="checkbox" disabled="disabled"></td>
    <td class="int" width="500px"><%=usuem[1]%></td>
    <td class="int" width="500px">SE COBRA:<input class="dal" name="se_cobra[]"  type="checkbox" <% if(marca){%> checked="checked"  <% } %> disabled="disabled"></td>
  </tr>
         
        <%    }       	
         %>
  </tbody>
</table>



 
  <div class="bgrabar">
  <button type="button" name="doc1" id="doc1" class="btn btn-primary" <% if(file1!=null && !file1.equals("null")){ %>onclick="location='<%=file1 %>'" <% }else{%> onclick="alert('NO EXISTE DOCUMENTO');" <% } %>   >CONTRATO</button>
           <button type="submit" name="grabar" id="grabar" class="btn btn-success ">GRABAR</button>
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