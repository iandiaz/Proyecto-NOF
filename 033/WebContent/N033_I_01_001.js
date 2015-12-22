	
	function grabar(){
	  	
        $.post( "grabarRCAsync", $( "#form1" ).serialize())
        .done(function( data ) {
        	
          		
        });
    }
	
	function getOcDetalle(id_oc){
		if(id_oc!=null && id_oc!=""){
			$('#fbody').html("");
			$.post( "getOCDetallesAsync?mod=I", {id_oc:id_oc,estados_vig_novig_id:1 })
	        .done(function( data ) {
	        		var detalles_oc_arr = $.parseJSON(data);
	        		
	          		$.each( detalles_oc_arr, function( key, detalle_oc ) {
	          			
	          			for(var i=0;i<detalle_oc.cantidad;i++){
		          			$('#fbody').append("<tr>");
		          				$('#fbody').append("<td class='detailID'>"+detalle_oc.producto.id+"</td>");
		          				$('#fbody').append("<td class='detailProdPn'>"+detalle_oc.producto.pn+"</td>");
		          				$('#fbody').append("<td class='detailProdDesc'>"+detalle_oc.producto.desc_corto+"</td>");
		          				$('#fbody').append("<td class='detailActivoSerie'><input type='text' class='amarillo' style='width:220px;height:30px ;' name='"+detalle_oc.id+"_series' /></td>");
		          				
		          				$('#fbody').append("<td class='detailProdPrecio'>"+detalle_oc.precio_unit+"</td>");
		          				$('#fbody').append("<td >&nbsp;</td>");
		          				
		          			$('#fbody').append("</tr>");
	          			}
	          			
	          		});
	          		
	        });
				
		}else{
			$('#fbody').html("");
		}
		
}