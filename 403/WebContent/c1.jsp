<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%
	String modname=(String)request.getAttribute("modname");
	
	String Usu_nom=(String)request.getAttribute("usuname");

	HashMap<String, ArrayList<String>> datamapa=(HashMap<String, ArrayList<String>>)request.getAttribute("datamapa");
	HashMap<String, String> dirsLat=(HashMap<String, String>)request.getAttribute("dirsLat");
	HashMap<String, String> dirsLon=(HashMap<String, String>)request.getAttribute("dirsLon");
	
	
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

   	<script src="lib/jquery-ui/js/jquery-1.10.2.js"></script>
    <link rel="stylesheet" href="lib/jquery-ui/css/ui-lightness/jquery-ui-1.10.4.custom.css">
    <script src="lib/jquery-ui/js/jquery-ui-1.10.4.custom.js"></script>
    
    <!-- Demo page code -->
 <style type="text/css">
      html { height: 100% }
      body { height: 100%; margin: 0; padding: 0 }
      #map_canvas { height: 490px; }
    </style>
    
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
	
	
		select{
			width:225px;
				}
		
	@media screen and (min-width: 700px) {
.cuadroblanco{
			box-shadow:0px 2px 5px 0px rgba(50, 50, 50, 0.75);
			border: 1px solid black;
			min-height:100%; 
			height:500px;  
		min-width: 350px;
			max-width:1000px;
position: relative;
background:#ccc;
margin: 0 auto;
	padding: 5px 5px 5px 5px;
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
  
  	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript"
      src="https://maps.googleapis.com/maps/api/js?v=3.exp">
    </script>
    <script type="text/javascript">

	
	
	/*INICIALIZACION SIN DATA */
var markers = [];
	
	function initializeNotData() {
		  geocoder = new google.maps.Geocoder();
		  
		   var mapOptions;
		   if(localStorage.mapLat!=null && localStorage.mapLng!=null && localStorage.mapZoom!=null){
            mapOptions = {
              center: new google.maps.LatLng(localStorage.mapLat,localStorage.mapLng),
              zoom: parseInt(localStorage.mapZoom),
              scaleControl: true
            };
        }else{
			mapOptions = {
          center: new google.maps.LatLng(-33.45453, -70.6634),
          zoom: 12,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        };
			}
		  
       
		
		
		
         map = new google.maps.Map(document.getElementById("map_canvas"),
            mapOptions);
		
		        mapCentre = map.getCenter();
        
		        
		     //recorremos resultado 
		     
		     
		     <% 

  
  for (String dire_id : datamapa.keySet()) {
	 
	  
	  %> 
		
	  var lat=<%=dirsLat.get(dire_id)%>;
      var lon=<%=dirsLon.get(dire_id)%>;
   	   
       if(lat!="" && lon!=""){
       	 var marker = new google.maps.Marker({
			      position: new google.maps.LatLng(lat,lon),
			      map: map,
			      title: ''
			});
       	 
       	 markers.push(marker);
       }
       
	 	
	  <%  } %> 
		     
		   
		       


		
				
	}

	
 
    </script>
  </head>

  
  <body class="" onload="initializeNotData()"> 
    
    <div class="navbar"> 
                        
                     
      <div class="brand"><img src="lib/bootstrap/img/logonew_big.png"></div>
                     <div class="version" align="right">
                     <p>N403.C.01.001</p>
                     </div>
                     <form method="get" action="" style="margin:0px 0px 0px 0px">                 
                    <button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESIÓN</button>
                    </form>
                    <button  type="button" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;" onclick="location='/403/'">VOLVER</button>
                   
                    <div align="center" class="title" >
                    <p >CONSULTAR <%=modname %></p>
                    </div>              
                             
    </div>
         
         
   <div class="content">
   
   <div class=" cuadroblanco">
  	    
         <div id="map_canvas" style="width:100%;"></div>
    <div id="errors"></div>
	
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

