<%@page import="java.util.ArrayList"%>
<%
	String modname=(String)request.getAttribute("modname");
	
	String Usu_nom=(String)request.getAttribute("usuname");

	ArrayList<String> usuarios_tick=(ArrayList<String>)request.getAttribute("usuarios_tick");
	ArrayList<String> selecciones_usu=(ArrayList<String>)request.getAttribute("selecciones_usu");
	ArrayList<String> tptick=(ArrayList<String>)request.getAttribute("tptick");
	
	ArrayList<String> emps=(ArrayList<String>)request.getAttribute("emps");
	
%>
<!DOCTYPE html>
<html lang="en"><head>

<!-- VALIDANDO QUE LOS CAMPOS VALLAN CON ALGO -->
	<script type="text/javascript">
	function validarIngreso(){
		}
	</script>
	
	<!-- FIN VALIDANDO QUE LOS CAMPOS VALLAN CON ALGO -->



<script type="text/javascript" src="lib/jquery-1.7.2.min.js"></script>
 
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
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" type="text/css" href="stylesheets/inputs.css">

   	<script src="lib/jquery-ui/js/jquery-1.10.2.js"></script>
    <link rel="stylesheet" href="lib/jquery-ui/css/ui-lightness/jquery-ui-1.10.4.custom.css">
    <script src="lib/jquery-ui/js/jquery-ui-1.10.4.custom.js"></script>
    
    <!-- Demo page code -->

    <style type="text/css">
    .clearer { clear: both; }
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
	  
	    height: 165px;
	    overflow-y: scroll;
	}
	thead, tbody {
		 min-width:100%;
    	
	}
			
		select{
			width:225px;
				}
		
	@media screen and (min-width: 700px) {
.cuadroblanco{
			box-shadow:0px 2px 5px 0px rgba(50, 50, 50, 0.75);
			border: 1px solid black;
			min-height:100%; 
			height:230px;  
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
  
  	<script type="text/javascript">
  	function validarIngreso(){
  		var checkboxes = $("input[type='checkbox']");
	    
  		if(!checkboxes.is(":checked")){
			alert('DEBE SELECCIONAR AL MENOS UN USUARIO');
			return false;
		}
		
		
		
		}
  	
  	function toggleTodos(){
  		var inputTodos=$(".todos");
  		var checkboxes = $("input[type='checkbox']");

  		if(inputTodos.is(":checked")){
  			checkboxes.prop('checked', true);
		}
  		else{
  			checkboxes.prop('checked', false);
  		}
  		
  		
  	}
  	
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
                 $(this).css({"color":"black"});
                 $(this).unbind('focus');
             }).css({"color":"#000"}); 
    
    
    $( "#datepicker" ).datepicker({ dateFormat: "dd-mm-yy", monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
        monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
        dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
        dayNamesShort: ['Dom','Lun','Mar','Mie','Juv','Vie','Sab'],
        dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa']});
$( "#datepicker2" ).datepicker({ dateFormat: "dd-mm-yy", monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
         monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
         dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
         dayNamesShort: ['Dom','Lun','Mar','Mie','Juv','Vie','Sab'],
         dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa']});
    
    
  	 });
  
  	</script>
  </head>

  
  <body class=""> 
    
    <div class="navbar"> 
                        
                     
      <div class="brand"><img src="lib/bootstrap/img/logonew_big.png"></div>
                     <div class="version" align="right">
                     <p>N302.C.01.001</p>
                     </div>
                     <form method="get" action="" style="margin:0px 0px 0px 0px">                 
                    <button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESI�N</button>
                    </form>
                    <button  type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/302/'">VOLVER</button>
                   
                    <div align="center" class="title" >
                    <p >CONSULTAR <%=modname %></p>
                    </div>              
                             
    </div>
         <div align="center" class="form-group has-warning">
         <label class="control-label" for="inputWarning" style="font-size:20px;color:#000"><strong>TEXTO PARA FILTRAR:</strong></label>
         <input maxlength="25" type="text" id="searchInput" class="form-control span12" style="width:290px;height:30px" autofocus="autofocus">
         </div>
         
   <div class="content" style="min-height: 100%; margin-bottom: 50px;">
        
         <form action="" name="form1" method="post">
           
 <!-- Table -->

<div align="center" class="form-group has-warning" style="font-size: 20px"> 
		<strong>ESTADO TICKET:</strong><select name="estticket" id="estticket" style="margin:0px 0px 0px 0px;" >
				<option value="1"  >NO CERRADOS</option>
				<option value="2"  >CERRADOS</option>
			
    		</select>
	</div>
	<div align="center" class="form-group has-warning" style="font-size: 20px"> 
		<strong>TIPO TICKET:</strong><select name="tipoticket" id="tipoticket" style="margin:0px 0px 0px 0px;width: 295px" >
		 <option value=""  >TODOS</option>
		<% 
 
	 for(String tptick_ : tptick){
	 	String tipoticket_ar[]= tptick_.split(";");
	 %>
	 
		 <option value="<%=tipoticket_ar[0] %>"  ><%=tipoticket_ar[1] %></option>
				
	 
	 <% }
	 %>
		
		
				
			
    		</select>
	</div>
	
	<div align="center" class="form-group has-warning" style="font-size: 20px"> 
		<strong>EMPRESA:</strong><select name="emp" id="emp" style="margin:0px 0px 0px 0px;width: 435px" >
		 <option value=""  >TODOS</option>
		<% 
 
	 for(String emps_ : emps){
	 	String emps_ar[]= emps_.split(";");
	 %>
	 
		 <option value="<%=emps_ar[0] %>"  ><%=emps_ar[1] %></option>
				
	 
	 <% }
	 %>
		
		
				
			
    		</select>
	</div>
	
	
	<div align="center" class="form-group has-warning" style="font-size: 20px"> 
	<strong>FEC DESDE:</strong><input maxlength="35" type="text" id="datepicker" name="f1" class="amarillo" style="width:130px;height:30px ;background:#FF3;" >
    <strong>FEC HASTA:</strong><input maxlength="35" type="text" id="datepicker2" name="f2" class="amarillo" style="width:130px;height:30px ;background:#FF3;" >
 	</div>
 <table  class="table004det" style="margin: 0 auto;float: none;width: 400px">
  <thead style="border-bottom: 1px solid black;background:#39F;color: white;display: inline-block;">
  <tr>
  <th scope="col" align="center"><input class="todos"  type="checkbox" onclick="toggleTodos()" disabled="disabled"></th>
    <th scope="col" align="center">SELECCIONE USUARIOS PARA VER TICKET</th>
    
  </tr>
  </thead>
  
  <tbody class="scroll cuerpo" id="fbody" style="display: inline-block;">
  
    
  
  <% 
 
	 for(String usus_tick : usuarios_tick){
		String usu_t_ar[]=usus_tick.split(";");
		
		String nom = "";
		
		try{
			nom = usu_t_ar[1];
		}
		catch(Exception d){}
		 %>
			 <tr>
			 <td class="int" align="center"><input class="dale" name="usus[]" value="<%=usu_t_ar[0]%>" type="checkbox" <% if(selecciones_usu.contains(usu_t_ar[0])){%> checked="checked" <%} %> disabled="disabled" ></td>
			    
			    <td class="int"><%=usu_t_ar[0]%> - <%=nom%></td>
			    
			    
			  </tr>
		 <%
	 	
	 } %> 
	 
	
   
  
        
  </tbody>
  
  <tbody  class="loading" style="display: none">
   <tr>
			    <td class="int" colspan="3" align="center" width="400px" ><img alt="" src="images/loading.GIF" height="50px"></td>
			    
			    
	</tr>
  </tbody>
</table> 

<div class="bgrabar2" style="width: 400px">
           <button type="submit" onclick="return validarIngreso()" name="grabar" id="grabar" class="btn btn-success " >GRABAR</button>
          </div> 
	 
 
           
  
 
        </form>
         <div class="clearer"></div>
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

