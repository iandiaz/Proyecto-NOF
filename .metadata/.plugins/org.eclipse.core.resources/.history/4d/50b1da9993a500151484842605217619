function addProducto(urlredirok){
	
	if(calc_valores()){
		if($('#prod_id option:selected').val()==''){
			 alert('POR FAVOR SELECCIONE UN PRODUCTO');
		 }else{
			 $('.loadingButton').show();
			 $('.addprod').hide();
			 
			 $.post( "addProductosAsync?mod=I", $( "#form1" ).serialize())
		        .done(function( data ) {
		        	if(data.trim()=="OK") location=urlredirok;
		        	else if(data.trim()=="ERROR"){
		        		alert("HA OCURRIDO UN ERROR POR FAVOR RECARGAR LA PAGINA");
		        		$('.loadingButton').hide();
					 	$('.addprod').show();
					 } 
		        	else if(data.trim()=="ERRORYAEXISTE"){
		        		alert("EL PRODUCTO YA EXISTE");	
		        		$('.loadingButton').hide();
					 	$('.addprod').show();
		        	}
		        	else if(data.trim()=="ERRORSPECIALBID"){
		        		alert("NO EXISTE SPECIAL BID PARA ESTE PRODUCTO");	
		        		$('.loadingButton').hide();
					 	$('.addprod').show();
		        	}
		        	else{
		        		alert("HA OCURRIDO UN ERROR FAVOR CONTACTARSE CON EL ADMINISTRADOR DEL SISTEMA COD ERROR: "+data);
		        		$('.loadingButton').hide();
					 	$('.addprod').show();
		        	} 
		        	
		        	
		        	
		        });
		 }
	}
}

function removeProducto(id_prod_remove,urlredirok){
	
	if(calc_valores()){
			 $('.loadingButton').show();
			 $('.addprod').hide();
			 
			 $.post( "removeProductosAsync?id_prod_remove="+id_prod_remove, $( "#form1" ).serialize())
		        .done(function( data ) {
		        	if(data.trim()=="OK") location=urlredirok;
		        	else if(data.trim()=="ERROR"){
		        		alert("HA OCURRIDO UN ERROR POR FAVOR RECARGAR LA PAGINA");
		        		$('.loadingButton').hide();
					 	$('.addprod').show();
					 } 
		        	else{
		        		alert("HA OCURRIDO UN ERROR FAVOR CONTACTARSE CON EL ADMINISTRADOR DEL SISTEMA COD ERROR: "+data);
		        		$('.loadingButton').hide();
					 	$('.addprod').show();
		        	} 
		        });
		 
	}
}

function getProveedor(id_prov){	 
	  
	$.post( "getEmpresaAsync", {id_emp:id_prov })
    .done(function( data ) {
    		var proveedor = $.parseJSON(data);
      		
    		$('#rut_proveedor').val(proveedor.rut);
    		$('#razonsocial_proveedor').val(proveedor.razonsocial);
    });
}
function getComprador(id_comp){
  
	$.post( "getEmpresaAsync", {id_emp:id_comp })
	.done(function( data ) {
			var comprador = $.parseJSON(data);
	  		
			$('#rut_comprador').val(comprador.rut);
			$('#razonsocial_comprador').val(comprador.razonsocial);
			
	});
}