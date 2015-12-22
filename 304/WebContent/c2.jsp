<%@page import="java.util.ArrayList"%>
<%
	String modname=(String)request.getAttribute("modname");
	
	String Usu_nom=(String)request.getAttribute("usuname");

	ArrayList<String> tickets=(ArrayList<String>)request.getAttribute("tickets");
	ArrayList<String> usuarios_tick=(ArrayList<String>)request.getAttribute("usuarios_tick");
	
	String estticket=(String)request.getAttribute("estticket");
	String tipoticket=(String)request.getAttribute("tipoticket");
	
	String f1=(String)request.getAttribute("f1");
	String f2=(String)request.getAttribute("f2");
	String emp=(String)request.getAttribute("emp");
	
	if(tipoticket==null || tipoticket.equals("")) tipoticket="''";
	if(f1==null || f1.equals("")) f1="";
	if(f2==null || f2.equals("")) f2="";
	
	if(emp==null || emp.equals("")) emp="";
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
    <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/butons.css">
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">
       <link rel="stylesheet" type="text/css" href="stylesheets/inputs.css">

    <script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>

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
	  
	    height: 125px;
	    
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
  	
  		function ordenar(incialesUsu,tipo){
  			
  			 $('.loading_'+incialesUsu).show();
  			$('.'+incialesUsu).hide();
  			$.ajax({
  			  url: '/302/ordena',
  			  type: 'GET', // o 'POST',
  			  data: { read:1,usu: incialesUsu, tipoOrden:tipo,estticket:<%=estticket%> , tipoticket:<%=tipoticket%>,f1:'<%=f1%>',f2:'<%=f2%>',emp:'<%=emp%>'},
  			  success: function(data) {
  				 $('.loading_'+incialesUsu).hide();
  			  	$('.'+incialesUsu).html(data);
  				$('.'+incialesUsu).show();
  			  }
  			});
  		}
  		
  		function guardarPrioridad(ticket,incialesUsu){
  			
  			var req=0;
  				var prio=$('#prio_'+ticket).val();
  				
  				var checkbox = $("#"+ticket+"_r");
  			    
  		  		if(checkbox.is(":checked")){
  					var req=1;
  					
  				}
  				
  				if(prio==''){
  					alert('INGRESA UNA PRIORIDAD');
  				}else{
  					$('.loading_'+ticket).show();
  		 			$('.'+ticket).hide();
  		 			$.ajax({
  		 			  url: '/302/guardarprioridad',
  		 			  type: 'GET', // o 'POST',
  		 			  data: { usu: incialesUsu,tick: ticket,prioridad:prio, r:req },
  		 			  success: function(data) {
  		 				 $('.loading_'+ticket).hide();
  			 			  	
  		 				$('.'+ticket).show();
  		 			  }
  		 			});
  				}
 			
 		}
  	</script>
  </head>

  
  <body class=""> 
    
    <div class="navbar"> 
                        
                     
      <div class="brand"><img src="lib/bootstrap/img/logonew_big.png"></div>
                     <div class="version" align="right">
                     <p>N304.C.02.001</p> 
                     </div>
                     <form method="get" action="" style="margin:0px 0px 0px 0px">                 
                    <button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
                    </form>
                    <button  type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/303/c1'">VOLVER</button>
                   
                    <div align="center" class="title" >
                    <p >CONSULTAR <%=modname %></p>
                    </div>              
                             
    </div>
 <form action="" name="form1" method="post">
         
         <input type="hidden" name="estticket" value="<%=estticket %>" >
         <input type="hidden" name="tipoticket" value="<%=tipoticket %>" >
         
         
   <div class="content" style="min-height: 100%; ">
        
           
 <!-- Table -->

 <%
 
 for(String usuarios_tick_ : usuarios_tick){
	 String usuarios_tick_ar[]=usuarios_tick_.split(";");
	String nombre ="";
	
	try{
		nombre=usuarios_tick_ar[1];
	}
	catch(Exception e){}
 //traemos arraylist contenido	%>
 <table  class="table004det">
  <thead style="border-bottom: 1px solid black;background:#39F;color: white">
  <tr>
    <th scope="col" colspan="4" align="center"><%=usuarios_tick_ar[0] %> - <%=nombre %></th>
    
  </tr>
  </thead>
  <tbody style="height: 80px;" >
   <tr>
    <td class="int" colspan="7">
	    <button type="button" onclick="ordenar('<%=usuarios_tick_ar[0] %>',1)"  class="btn btn-success " name="cercanianof" >CERCANIA NOF</button>
	    <button type="button" onclick="ordenar('<%=usuarios_tick_ar[0] %>',2)"  class="btn btn-success "  >POS TECNICO</button>
	    <button type="button" onclick="ordenar('<%=usuarios_tick_ar[0] %>',3)"  class="btn btn-success " >POS TICKET</button>
	    <button type="button" onclick="ordenar('<%=usuarios_tick_ar[0] %>',4)"  class="btn btn-success " >PRIORIDAD</button>
	</td>
    
  </tr>
   <tr style="background-color: #00FFC4">
    <td class="int" width="110px"><b>TICKET</b></td>
    <td class="int" width="200px"><b>EMPRESA</b></td>
    <td class="int" width="145px"><b>TIPO TICKET</b></td>
    <td class="int" width="120px"><b>D.NOF</b></td>
    <%if(estticket.equals("1")){%><td class="int" width="120px"><b>D.TEC</b></td><%} %>
    <td class="int" width="87px"><b>TIEMPO</b></td>
    <td class="int" width="43px"><span style="color: red">REP</span></td>
    <%if(estticket.equals("1")){%><td class="int" ><b>PRIO</b></td><%} %>
    <%if(estticket.equals("1")){%><td class="int" width="45px"><b>VIS</b></td><%} %>
    
    <td class="int" width="120px"><b>F.RECIBIDO</b></td>
    <%if(estticket.equals("2")){%><td class="int" width="120px"><b>F.CIERRE</b></td><%} %>
    <%if(estticket.equals("2")){%><td class="int" width="87px"><b>T.CIERRE</b></td><%} %>
     <td class="int" width="120px"><b>F.TEC</b></td>
    
  </tr>
  </tbody>
  <tbody class="scroll <%=usuarios_tick_ar[0] %>" style="overflow-y: scroll;">
  
  <% 
 
	 for(String tick : tickets){
		 String ar_t[]=tick.split(";");
		 if(ar_t[0].equals(usuarios_tick_ar[0])){%>
			 <tr style="border-top: 1px solid #FFF" >
			    <td class="int" width="110px" ><%=ar_t[1] %></td>
			    <td class="int" width="200px"><%=ar_t[3] %></td>
			    <td class="int" width="145px"><%=ar_t[8] %></td>
			    <td class="int" width="120px"><%=ar_t[5] %>Km</td>
			   <%if(estticket.equals("1")){%> <td class="int" width="120px"><%=ar_t[6] %>Km</td><%} %>
			    <td class="int" width="87px" style="background-color:<%=ar_t[11] %>; <%=ar_t[10] %>  "><%=ar_t[7] %></td>
			    <td class="int" width="43px"><input disabled="disabled" id="<%=ar_t[1] %>_r" name="<%=ar_t[1] %>_r" value="1" type="checkbox" <%if(ar_t[4].equals("1")){%> checked="checked" <%} %>  /> </td>
			    <%if(estticket.equals("1")){%><td class="int" ><input disabled="disabled" maxlength="3" type="text"  style="width:50px;height:30px ;background:#FFF;margin: 0px 0px 0px 0px;" onkeydown="return validateNum(event)" id="prio_<%=ar_t[1] %>" name="prio_<%=ar_t[1] %>" value="<%=ar_t[2].equals("null")?"":ar_t[2] %>" ></td><%} %>
			    <%if(estticket.equals("1")){%><td class="int" width="45px"><input disabled="disabled" id="<%=ar_t[1] %>_visible" name="<%=ar_t[1] %>_visible" value="1" type="checkbox" <%if(ar_t[9].equals("1")){%> checked="checked" <%} %>  /></td><%} %>
			    
			    <td class="int" width="120px"><%=ar_t[12] %></td>
   				<%if(estticket.equals("2")){%><td class="int" width="120px"><%=ar_t[13] %></td><%} %>
   				<%if(estticket.equals("2")){%><td class="int" width="87px"><%=ar_t[14] %></td><%} %>
    <td class="int" width="120px"><%=ar_t[15] %></td>
			    
			  </tr>
			 
		 <% }
	 	
	 } %> 
	 
	
   
  
        
  </tbody>
  
  <tbody  class="loading_<%=usuarios_tick_ar[0] %>" style="display: none">
    <tr>
		<td class="int" colspan="7" align="center" width="400px" ><img alt="" src="images/loading.GIF" height="50px"></td>
	</tr>
  </tbody>
</table> 
	 
 <%} %> 
           
          <div class="clearer"></div>
         </div>
         <div class="bgrabar2" style="margin-bottom: 50px;width: 1200px">
           <button type="submit" onclick="" name="grabar" id="grabar" class="btn btn-success " >GRABAR</button>
          </div> 
 
        </form>
 
      
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

