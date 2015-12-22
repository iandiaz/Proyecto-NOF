<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>

 <style type="text/css">
      html { height: 100% }
      body { height: 100%; margin: 0; padding: 0 }
      #map_canvas { height: 100% }
    </style>

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript"
      src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDx3pTgGIpz5s5g8L9KEwlNjulndFwIl8g&sensor=true">
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
        
		        
		        //evaluamos si ya dispone de una lat y lon ingresada 
		        
		        var prev_lat=$('#lat', $(parent.document)).val();
		       var prev_lon=$('#lon', $(parent.document)).val();
		    	   
	            if(prev_lat!="" && prev_lon!=""){
	            	 var marker = new google.maps.Marker({
					      position: new google.maps.LatLng(prev_lat,prev_lon),
					      map: map,
					      title: ''
					});
	            	 
	            	 markers.push(marker);
	            }
		        
		       

        
      
        
        google.maps.event.addListener(map, 'click', function(event) {
        	//map.setAllMap(null);
        	for (var i = 0; i < markers.length; i++) {
   				 markers[i].setMap(null);
  			}
            marker = new google.maps.Marker({position: event.latLng, map: map});
            markers.push(marker);
            
            var lat="lat";
            var lon="lon";
            
            var latlon=event.latLng+"";
           // alert(latlon);
            var ar_latlon=latlon.split(',');
            
            lat=ar_latlon[0].replace("(", "");
            lon=ar_latlon[1].replace(")", "");
            
            lat=lat.replace(" ", "");
            lon=lon.replace(" ", "");
            
            
            //alert(lat);
             
            $('#lat', $(parent.document)).val(lat);
            $('#lon', $(parent.document)).val(lon);
				//alert(event.latLng);
        });

		
				
	}

	
 
    </script>
</head>
<body onload="initializeNotData()">
    <div id="map_canvas" style="width:100%; height:100%"></div>
    <div id="errors"></div>

</body>
</html>