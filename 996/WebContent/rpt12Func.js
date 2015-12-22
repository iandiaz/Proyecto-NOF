function limpiarBusqueda(){
	for(var count =1; count<=6;count++){
		$("#P"+count).html("");
		$("#VENTASIMP_CL_P"+count).val("");
		$("#IMP_N_P"+count).val("");
		$("#SUMENV_P"+count).val("");
		$("#SUMREND_P"+count).val("");
		$("#KITENV_P"+count).val("");
		$("#KITREND_P"+count).val("");
		$("#FOTOCONDENV_P"+count).val("");
		$("#FOTOCONDREND_P"+count).val("");
		$("#CONSUMIBLESENV_P"+count).val("");
		$("#CONSUMIBLESREND_P"+count).val("");
		$("#REPUESTOSENV_P"+count).val("");
		$("#REPUESTOSREND_P"+count).val("");
		$("#TICKLOG_P"+count).val("");
		$("#TICKSERVTEC_P"+count).val("");
		$("#MAQUINAS_P"+count).val("");
		
		$("#DIRS_P"+count).val("");
		
	}
}	
function getPeriodos(select_year,select_month){
		$('.loading_p').show();
    	
        	$.post( "getRepAsync", {func:"get6Periodos",select_year:select_year,select_month:select_month})
            .done(function( data ) {
            	
            	var periodos_arr = $.parseJSON(data);
          		
            	var count=6;
            	$.each( periodos_arr, function( key, periodo ) {
          			
            		$("#P"+count).html("P"+count+"<br>"+periodo.nombre);
            		count--;
            	 
          		});
            
            	$(".loading_p").hide();
        	 });
         	
       	}  
	
	function getVentasImp(select_year,select_month,select_empresa,select_grupo){
		
    	$.post( "getRepAsync", {func:"getVentasImp",select_year:select_year,select_month:select_month,select_grupo:select_grupo,select_empresa:select_empresa})
        .done(function( data ) {
        	
        	var imps_arr = $.parseJSON(data);
      		
        	var count=6;
        	$.each( imps_arr, function( key, imp ) {
        		
        		$("#VENTASIMP_CL_P"+count).val(imp.total_ventas);
        		count--;
        	 
      		});
        	
    	 });
     	
   	}  
	
	function getNImp(select_year,select_month,select_empresa,select_grupo){
		
    	$.post( "getRepAsync", {func:"getNImp",select_year:select_year,select_month:select_month,select_grupo:select_grupo,select_empresa:select_empresa})
        .done(function( data ) {
        	
        	var imps_arr = $.parseJSON(data);
      		
        	var count=6;
        	$.each( imps_arr, function( key, imp ) {
        		
        		$("#IMP_N_P"+count).val(imp.numero);
        		count--;
        	 
      		});
        	
    	 });
     	
   	}  
	function getEnvSumMaq(select_year,select_month,select_empresa,select_grupo){
		
    	$.post( "getRepAsync", {func:"getEnvSumMaq",select_year:select_year,select_month:select_month,select_grupo:select_grupo,select_empresa:select_empresa})
        .done(function( data ) {
        	
        	var sum_arr = $.parseJSON(data);
      		
        	var count=6;
        	$.each( sum_arr, function( key, sum ) {
        		
        		$("#SUMENV_P"+count).val(sum.numeroEnviados);
        		$("#SUMREND_P"+count).val(sum.rendimiento);
        		count--;
        	 
      		});
        	
    	 });
     	
   	} 
	
	function getEnvKit(select_year,select_month,select_empresa,select_grupo){
		
    	$.post( "getRepAsync", {func:"getEnvKit",select_year:select_year,select_month:select_month,select_grupo:select_grupo,select_empresa:select_empresa})
        .done(function( data ) {
        	
        	var kit_arr = $.parseJSON(data);
      		
        	var count=6;
        	$.each( kit_arr, function( key, kit ) {
        		
        		$("#KITENV_P"+count).val(kit.numeroEnviados);
        		$("#KITREND_P"+count).val(kit.rendimiento);
        		count--;
        	 
      		});
        	
    	 });
     	
   	} 
	function getEnvFotoCond(select_year,select_month,select_empresa,select_grupo){
		
    	$.post( "getRepAsync", {func:"getEnvFotoCond",select_year:select_year,select_month:select_month,select_grupo:select_grupo,select_empresa:select_empresa})
        .done(function( data ) {
        	
        	var kit_arr = $.parseJSON(data);
      		
        	var count=6;
        	$.each( kit_arr, function( key, kit ) {
        		
        		$("#FOTOCONDENV_P"+count).val(kit.numeroEnviados);
        		$("#FOTOCONDREND_P"+count).val(kit.rendimiento);
        		count--;
        	 
      		});
        	
    	 });
     	
   	} 
	function getEnvConsumibles(select_year,select_month,select_empresa,select_grupo){
		
    	$.post( "getRepAsync", {func:"getEnvConsumibles",select_year:select_year,select_month:select_month,select_grupo:select_grupo,select_empresa:select_empresa})
        .done(function( data ) {
        	
        	var cons_arr = $.parseJSON(data);
      		
        	var count=6;
        	$.each( cons_arr, function( key, cons ) {
        		
        		$("#CONSUMIBLESENV_P"+count).val(cons.numeroEnviados);
        		$("#CONSUMIBLESREND_P"+count).val(cons.rendimiento);
        		count--;
        	 
      		});
        	
    	 });
     	
   	} 
	
	function getEnvRepuestos(select_year,select_month,select_empresa,select_grupo){
		
    	$.post( "getRepAsync", {func:"getEnvRepuestos",select_year:select_year,select_month:select_month,select_grupo:select_grupo,select_empresa:select_empresa})
        .done(function( data ) {
        	
        	var kit_arr = $.parseJSON(data);
      		
        	var count=6;
        	$.each( kit_arr, function( key, kit ) {
        		
        		$("#REPUESTOSENV_P"+count).val(kit.numeroEnviados);
        		$("#REPUESTOSREND_P"+count).val(kit.rendimiento);
        		count--;
        	 
      		});
        	
    	 });
     	
   	} 
	function getTickLog(select_year,select_month,select_empresa,select_grupo){
		
    	$.post( "getRepAsync", {func:"getTickLog",select_year:select_year,select_month:select_month,select_grupo:select_grupo,select_empresa:select_empresa})
        .done(function( data ) {
        	
        	var tick_arr = $.parseJSON(data);
      		
        	var count=6;
        	$.each( tick_arr, function( key, tick ) {
        		
        		$("#TICKLOG_P"+count).val(tick.n_tickets_envio);
        		count--;
        	 
      		});
        	
    	 });
     	
   	} 
	
	function getTickServTec(select_year,select_month,select_empresa,select_grupo){
		
    	$.post( "getRepAsync", {func:"getTickServTec",select_year:select_year,select_month:select_month,select_grupo:select_grupo,select_empresa:select_empresa})
        .done(function( data ) {
        	
        	var tick_arr = $.parseJSON(data);
      		
        	var count=6;
        	$.each( tick_arr, function( key, tick ) {
        		
        		$("#TICKSERVTEC_P"+count).val(tick.n_tickets_envio);
        		count--;
        	 
      		});
        	
    	 });
    }
	function getNmaquinas(select_year,select_month,select_empresa,select_grupo){
		
    	$.post( "getRepAsync", {func:"getNmaquinas",select_year:select_year,select_month:select_month,select_grupo:select_grupo,select_empresa:select_empresa})
        .done(function( data ) {
        	
        	var activo_arr = $.parseJSON(data);
      		
        	var count=6;
        	$.each( activo_arr, function( key, activo ) {
        		
        		$("#MAQUINAS_P"+count).val(activo.n_maquinas);
        		count--;
        	 
      		});
        	
    	 });
     	
   	} 
	
	function getDirs(select_year,select_month,select_empresa,select_grupo){
		
    	$.post( "getRepAsync", {func:"getDirs",select_year:select_year,select_month:select_month,select_grupo:select_grupo,select_empresa:select_empresa})
        .done(function( data ) {
        	
        	var dir_arr = $.parseJSON(data);
      		
        	var count=6;
        	$.each( dir_arr, function( key, dir ) {
        		
        		$("#DIRS_P"+count).val(dir.n_direcciones);
        		count--;
        	 
      		});
        	
    	 });
     	
   	} 
	
	