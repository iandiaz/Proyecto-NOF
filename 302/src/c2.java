

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class c2
 */
@WebServlet("/c2")
public class c2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public c2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(Controlador.validateSession(request, response)){
   			
			 mt(request,response);
		}
		else response.sendRedirect("/001/");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(Controlador.validateSession(request, response)){
   			
			 mt(request,response);
		}
		else response.sendRedirect("/001/");
	}
	
public void mt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();		
		
		// logout
		if(request.getParameter("logout") !=  null){
				Controlador.eraseCookie(request, response);
				response.sendRedirect("/001/");	
				return;
		}
		//fin logout
				
		//////////////////////////////////////////////////
		////////DEFINE PARAMETROS DE USUARIO//////////////

		request.setAttribute("modname", Controlador.getNameModulo(Constantes.MODULO));
		
		request.setAttribute("usuname", Controlador.getUsunameSession(request));
		
			
		
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 Statement state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 Statement stateIn = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 
    		 String estticket = request.getParameter("estticket");
    		 
    		 String tipoticket = request.getParameter("tipoticket");
    		 
    		 String f1 = request.getParameter("f1");
    		 String f2 = request.getParameter("f2");
    		 
    		 String emp = request.getParameter("emp");
     		
    		 
    		 request.setAttribute("f1", f1);
    		 request.setAttribute("f2", f2);
    		 request.setAttribute("emp", emp);
    		 
    		 request.setAttribute("estticket", estticket);
    		 request.setAttribute("tipoticket", tipoticket);
    		 
    		//::::::::::::::::::::::::::::::::::::::::::fin sql estado clpr para select option::::::::::::::::::::::::::::::::::::::
    	
    		 
    			//traemos nombres de empresas por id
       		ArrayList<String> empresas=new ArrayList<String>();
       		 
       		String emp_sql="select empresas_id,empresas_nombrenof from empresas ";
     		  ResultSet EMP_RS = state.executeQuery(emp_sql);
     		  if(EMP_RS.next()){
     			 empresas.add(EMP_RS.getString("empresas_id")+";"+EMP_RS.getString("empresas_nombrenof"));
     			 
       		  
     		  } 
  	    	
    		 
    		 //traemos solo los usuarios seleccionados
  	    	
    		String iniciales="'0'";
  	    	 
    		String find_usus_sql="SELECT admt_s_ususelect FROM adm_tecnicos_selectusu WHERE admt_s_id_usuario="+Controlador.getUsuIDSession(request);
    		ResultSet USUS_RS = state.executeQuery(find_usus_sql);
      		while(USUS_RS.next()){
      			iniciales+=",'"+USUS_RS.getString("admt_s_ususelect")+"' ";
    			
      		}
      		
      		//traemos lat y lon de todas las direcciones
      		ArrayList<String> latlondires=new ArrayList<String>();
      		 
      		String dirtcik_sql="select DIRE_ID,dire_lat,dire_lon from direccion ";
    		  ResultSet DITICK_RS = state.executeQuery(dirtcik_sql);
    		  while(DITICK_RS.next()){
    			  latlondires.add(DITICK_RS.getString("DIRE_ID")+";"+DITICK_RS.getDouble("dire_lat")+";"+DITICK_RS.getDouble("dire_lon"));
    			 
      		  
    		  } 
 	    	
      		
 	    	 //traemos las lat y ong de los usuarios seleccionados
   		 
   		 ArrayList<String> latlonusus=new ArrayList<String>();
   		 
   		 String find_usu_sql="select "
   				 
	      				+ "				`usuarios`.`Usuarios_inicial`, "
	      				+ "				`usuarios`.`Usuarios_imei` , "
	      				+ "				`usuarios`.`last_lat`, "
	      				+ "				`usuarios`.`last_lon`,"
	      				+ "				CONCAT_WS(' ',`Usuarios_nombre`,`Usuarios_ape_p`,`Usuarios_ape_m`) as nombre  "
	      				+ "			from usuarios where `usuarios`.`Usuarios_inicial` IN ("+iniciales+")";
	      		ResultSet USU_RS = state.executeQuery(find_usu_sql);
	      		while(USU_RS.next()){
	      			latlonusus.add(USU_RS.getString("Usuarios_inicial")+";"+USU_RS.getString("last_lat")+";"+USU_RS.getString("last_lon")+";"+USU_RS.getString("nombre"));
	      		}
 	    	
 	    	
 	    	//antes de borrar verificamos si existen los ticket para rescatar las prioridades
    		
    		String find_ticketsprev_sql="select "
    				+ "	admt_ticket"
    				+ "	,admt_prioridad"
    				+ "	,admt_req_repuesto"
    				+ "	,admt_visible"
    				
					+ "	,IF(adm_colorfont IS NULL ,'',adm_colorfont) as adm_colorfont "
					+ "	,IF(adm_colorback IS NULL ,'',adm_colorback) as adm_colorback"
    				
    				+ " from adm_tecnicos where admt_usu IN ("+iniciales+") ";
    		ResultSet FINDTICKETSPREV_RS = state.executeQuery(find_ticketsprev_sql);
    		
    		ArrayList<String> prevticks=new ArrayList<String>();
    		
	    	while(FINDTICKETSPREV_RS.next()){
	    		prevticks.add(FINDTICKETSPREV_RS.getString("admt_ticket")+";"+FINDTICKETSPREV_RS.getString("admt_prioridad")+";"+FINDTICKETSPREV_RS.getString("admt_req_repuesto")+";"+FINDTICKETSPREV_RS.getString("admt_visible")+";"+FINDTICKETSPREV_RS.getString("adm_colorfont").replace(";", "")+";"+FINDTICKETSPREV_RS.getString("adm_colorback").replace(";", ""));
	    	}
    		
    	  	//borramos registros para este usu
    		
    		/*String delete="delete from adm_tecnicos where `adm_tecnicos`.`admt_usu` IN ("+iniciales+")";
  		  	state.executeUpdate(delete);*/
 	    	
 	    	String dirnof_sql="select dire_lat,dire_lon from direccion where DIRE_ID=763";
 	    	ResultSet DIRNOF_RS = state.executeQuery(dirnof_sql);
   		  DIRNOF_RS.next();
   		  
   		  double dirnof_lat= DIRNOF_RS.getDouble("dire_lat");
   		  double dirnof_lon= DIRNOF_RS.getDouble("dire_lon");
 	    	
    		ConBirt birtBD= new ConBirt();
   			 
    			
    		String  ticketusu="SELECT" + 
    		 		"	[TICKET].[TICK_DIRECCION]," + 
    		 		"	[TICKET].[TICK_ESTADO]," + 
    		 		"	[TICKET].[TICK_ID]," + 
    		 		"	[TICKET].[TICK_CODIGO_FALLA]," + 
    		 		"	[TICKET].[TICK_FECHA_ENVIO]," +
    		 		"	[TICKET].[CLPR_NOMBRE_FANTASIA]," +
    		 		
					"	[TICKET].[TICK_FECHA_ENVIO]," +
    		 		
    		 		"	[DETALLE_TECNICO_TICKET].[DIRE_ID]," + 
    		 		"	[DETALLE_TECNICO_TICKET].[DETI_USU_NOF]," + 
    		 		"	[DETALLE_TECNICO_TICKET].[DETI_TIPO_TICKET]," + 
    		 		"	[DETALLE_TECNICO_TICKET].[DETI_EMPRESA]," + 
    		 		
    		 		"	ll.USU_ASIG ," +
    		 		"	ll.FECHA_MOD ," +
    		 		"	ll.DETI_ESTADO ," +
    		 		
    		 		"	DATEDIFF(" + 
    		 		"		MINUTE," + 
    		 		"		[TICKET].[TICK_FECHA_ENVIO]," + 
    		 		"		GETDATE()" + 
    		 		"	) AS DiffDate ," +
    		 		"	DATEDIFF(" + 
    		 		"		MINUTE," + 
    		 		"		[TICKET].[TICK_FECHA_ENVIO]," + 
    		 		"		ll.FECHA_MOD " + 
    		 		"	) AS DiffDateCierre ," + 
    		 		
    		 		" [DETALLE_TECNICO_TICKET].[DETI_TIPO_TICKET], " + 
    		 		" [DETALLE_TECNICO_TICKET].[DETI_SERIE] " + 
    		 	
    		 		" FROM " + 
    		 		"	[TICKET]" + 
    		 		" INNER JOIN [DETALLE_TECNICO_TICKET] ON [DETALLE_TECNICO_TICKET].[TICK_ID] = [TICKET].[TICK_ID]" + 
    		 		" INNER JOIN (" + 
    		 		"	SELECT" + 
    		 		"		[TICKET_ASIGNACION].[USU_ASIG]," + 
    		 		"		[TICKET_ASIGNACION].[TICK_ID]," + 
    		 		"		[TICKET_ASIGNACION].[FECHA_MOD]," +
    		 		"		[TICKET_ASIGNACION].[DETI_ESTADO]" +

    		 		"	FROM" + 
    		 		"		[TICKET_ASIGNACION]" + 
    		 		"	INNER JOIN (" + 
    		 		"		SELECT" + 
    		 		"			MAX ([TICKET_ASIGNACION].[TASIG_ID]) AS TASIG_ID" + 
    		 		"		FROM" + 
    		 		"			[TICKET_ASIGNACION]" + 
    		 		"		GROUP BY" + 
    		 		"			[TICKET_ASIGNACION].[TICK_ID]" + 
    		 		"	) t1 ON t1.TASIG_ID = [TICKET_ASIGNACION].[TASIG_ID]" + 
    		 		" ) ll ON ll.TICK_ID = [TICKET].[TICK_ID]" + 
    		 		"" + 
    		 		" WHERE";
    		
    				if(estticket.equals("1")){
    					ticketusu+=" NOT ( " + 
    							"    [TICKET].[TICK_ESTADO] LIKE '%CERRADO%' " + 
    							"   	)"+
    							" AND NOT (" + 
    							"    	[TICKET].[TICK_ESTADO] LIKE '%CIERRE%'  " + 
    							"    	) ";
    				}
    				if(estticket.equals("2")){
    					ticketusu+="  " + 
    							"    [TICKET].[TICK_ESTADO] LIKE '%CERRADO%' " + 
    							"   	";
    				}
    				
    				
    		 		ticketusu+=" " + 
    		 		" AND NOT (" + 
    		 		"	[TICKET].[TICK_ESTADO] LIKE '%ELIMINADO%'" + 
    		 		" )" + 
    		 		"  "+
    		 		" AND ll.USU_ASIG IN ("+iniciales+")"
    		 		+ "";
    		
    		
    		if(tipoticket!=null && !tipoticket.equals("")){
    			String findtipotick="SELECT tipo_ticket_nombre FROM tipo_ticket WHERE tipo_ticket_id="+tipoticket;
    			ResultSet findtipotick_rs = state.executeQuery(findtipotick);
    			findtipotick_rs.next();
    			
    			
    			ticketusu+=" AND [DETALLE_TECNICO_TICKET].[DETI_TIPO_TICKET]='"+findtipotick_rs.getString("tipo_ticket_nombre")+"' ";
    			
    		}
    		
    		System.out.println(ticketusu);
    		
    		ResultSet ticketusu_rs =birtBD.Consulta(ticketusu);
    		
    		ArrayList<String> tickets = new ArrayList<String>();
    		ArrayList<String> usuarios_tick = new ArrayList<String>();
    		
    		String tickets_cadena="'0'";
    		
    		while(ticketusu_rs.next()){
    			tickets_cadena+=",'"+ticketusu_rs.getString("TICK_ID")+"'";
    			
    			double dirtick_lat=0;
    			double dirtick_lon=0;
    			//buscamos direccion para traer la lat y lon 

    			  for(String dire: latlondires){
	      			  String dire_ar[]=dire.split(";");
	      			if(dire_ar[0].equals(ticketusu_rs.getString("DIRE_ID"))){
		      			  dirtick_lat= Double.parseDouble(dire_ar[1]);
			      		  dirtick_lon= Double.parseDouble(dire_ar[2]);
		        		  
		      		  } 
	      		  }
	      		   
	      		  //buscamos si existe prioridad
	      		  String priori="NULL";
	      		  String admt_req_repuesto="0";
	      		 
	      		  String visible="1";
	      		  String colorfont="-";
	      		  String colorback="-";
	      		  boolean existe=false;
	      		  
	      		for(String prevticks_:prevticks){
	      			  String prevs[]= prevticks_.split(";");
	      			  if(prevs[0].equals(ticketusu_rs.getString("TICK_ID"))){
	      				existe=true;
	      				if( prevs[1]!=null && !prevs[1].equals(""))priori= prevs[1];
	      				admt_req_repuesto=prevs[2];
	      				visible=prevs[3];
	      				try{
	      					colorfont=prevs[4];
		      				colorback=prevs[5];
		      					
	      				}
	      				catch(Exception e){}
	      				
	      			  }
	      		  }
	      		  
	      		  
	      		double usu_lat = 0;
	    		double usu_lon = 0;
	    		String usunombre="";
	    		for(int x=0; x<latlonusus.size();x++){
	    			String u=latlonusus.get(x);
	    			String u_ar[]= u.split(";");
	    			
	    			if(u_ar[0].equals(ticketusu_rs.getString("USU_ASIG"))){
	    				 usu_lat = Double.parseDouble(u_ar[1]);
		        		 usu_lon = Double.parseDouble(u_ar[2]);
		        		 try{
		        			 usunombre=u_ar[3];
		        		 }
		        		 catch(Exception e){}
		        		
	    			}
	    			
	    		}
	    		
	    		//buscamos la empresa
	    		
	    		
	    		String nombrenofemp=ticketusu_rs.getString("CLPR_NOMBRE_FANTASIA");
	    		for(int x=0; x<empresas.size();x++){
	    			String emp_ar[]=empresas.get(x).split(";");
	    			
	    			if(emp_ar[0].equals(ticketusu_rs.getString("DETI_EMPRESA"))){
	    				nombrenofemp =emp_ar[1];
		        		
		        		
		        		
	    			}
	    			
	    		}
	    		
	    		if(usu_lat==0 && usu_lon==0){
	    			usu_lat = dirnof_lat;
	        		 usu_lon = dirnof_lon;
	    		}
	    		
	    		if(!existe){
	    			//insertamos 
	    			String in="INSERT INTO `adm_tecnicos` ("
	    					+ "	`adm_tecnicos`.`admt_ticket`"
	    					+ "	,`adm_tecnicos`.`admt_usu`"
	    					+ "	,`adm_tecnicos`.`admt_distnof`"
	    					+ "	,`adm_tecnicos`.`admt_distusu`"
	    					+ "	,`adm_tecnicos`.`admt_prioridad`"
	    					+ "	,`adm_tecnicos`.`admt_clpr_nombre`"
	    					+ "	,`adm_tecnicos`.`admt_req_repuesto`"
	    					+ "	,`adm_tecnicos`.`admt_tiempo`"
	    					+ "	,`adm_tecnicos`.`admt_visible`"
	    					+ "	,`adm_tecnicos`.`adm_colorfont`"
	    					+ "	,`adm_tecnicos`.`adm_colorback`"
	    					+ "	,`adm_tecnicos`.`admt_tipoticket`"
	    					+ "	,`adm_tecnicos`.`adm_fecha_recibido`"
	    					+ "	,`adm_tecnicos`.`adm_fecha_cierre`"
	    					+ "	,`adm_tecnicos`.`adm_tiempocierre`"
	    					+ "	,`adm_tecnicos`.`adm_serie`"
	    					+ "	,`adm_tecnicos`.`adm_deti_estado`"
	    					
	    					+ "	) " 
	    					+ " VALUES ( "
	    					+ "	 '"+ticketusu_rs.getString("TICK_ID")+"'"
	    					+ "	,'"+ticketusu_rs.getString("USU_ASIG")+"'"
	    					+ "	,'"+Controlador.distFrom(dirtick_lat,dirtick_lon,dirnof_lat,dirnof_lon)+"'"
	    					+ "	,'"+Controlador.distFrom(dirtick_lat,dirtick_lon,usu_lat,usu_lon)+"'"
	    					+ "	,"+priori+""
	    					+ "	,'"+nombrenofemp+"'"
	    					+ "	,"+admt_req_repuesto+""
	    					+ "	,'"+ticketusu_rs.getString("DiffDate")+"'"
	    					+ "	,'"+visible+"'"
	    					+ "	,'"+colorfont+"'"
	    					+ "	,'"+colorback+"'"
	    					+ "	,'"+ticketusu_rs.getString("DETI_TIPO_TICKET")+"'"
	    					+ "	,'"+ticketusu_rs.getString("TICK_FECHA_ENVIO")+"'"
	    					+ "	,'"+ticketusu_rs.getString("FECHA_MOD")+"'"
	    					+ "	,'"+ticketusu_rs.getString("DiffDateCierre")+"'"
	    					+ "	,'"+ticketusu_rs.getString("DETI_SERIE")+"'"
	    					+ "	,'"+ticketusu_rs.getString("DETI_ESTADO")+"'"
	    					+ "	)";
	    			stateIn.addBatch(in);
	    				
	    		}
	    		else{
	    			//updateamos
	    			//System.out.println(usu_lat+","+usu_lon+" "+dirtick_lat+","+dirtick_lon);
	    			String up=" UPDATE `adm_tecnicos` "
	    					+ "	SET "
	    					+ "		admt_distusu='"+Controlador.distFrom(dirtick_lat,dirtick_lon,usu_lat,usu_lon)+"'"
	    					+ " 	,admt_tipoticket='"+ticketusu_rs.getString("DETI_TIPO_TICKET")+"'"
	    					+ " 	,adm_fecha_recibido='"+ticketusu_rs.getString("TICK_FECHA_ENVIO")+"'"
	    					+ " 	,adm_fecha_cierre='"+ticketusu_rs.getString("FECHA_MOD")+"'"
	    					+ " 	,adm_tiempocierre='"+ticketusu_rs.getString("DiffDateCierre")+"'"
	    					+ " 	,cliente_id='"+ticketusu_rs.getString("DETI_EMPRESA")+"'"
	    					+ " 	,admt_clpr_nombre='"+nombrenofemp+"'"
	    					+ " 	,admt_distnof='"+Controlador.distFrom(dirtick_lat,dirtick_lon,dirnof_lat,dirnof_lon)+"'"
	    					+ "		,`adm_tecnicos`.`admt_tiempo`='"+ticketusu_rs.getString("DiffDate")+"'"
	    					+ "		,`adm_tecnicos`.`adm_serie`='"+ticketusu_rs.getString("DETI_SERIE")+"'"
	    					+ "		,`adm_tecnicos`.`adm_deti_estado`='"+ticketusu_rs.getString("DETI_ESTADO")+"'"
	    					+ "	WHERE admt_ticket='"+ticketusu_rs.getString("TICK_ID")+"' ";
	    			System.out.println(up);
	    			stateIn.addBatch(up);
	    		}
	      		
    			
    			
    			if(!usuarios_tick.contains(ticketusu_rs.getString("USU_ASIG")+";"+usunombre))usuarios_tick.add(ticketusu_rs.getString("USU_ASIG")+";"+usunombre);
    			
    			
    		}
 		      
    		//separamos los tikcet en conjuntos, estas seran las tablas 
    		
    		
    		
    		stateIn.executeBatch();
    		
    		
    		//traemos todos los tickers. para ver si el ticket tiene otro pendiente en el mismo mes
    		
    		String find_ticketsand_sql="select "
    				+ "	admt_ticket"
    				+ "	,IF(admt_prioridad IS NULL, '',admt_prioridad) as admt_prioridad"
    				+ "	,admt_clpr_nombre"
    				+ "	,admt_distnof"
    				+ "	,admt_distusu"
    				+ "	,admt_req_repuesto"
    				+ "	,admt_tiempo"
    				+ "	,admt_tipoticket"
    				+ "	,admt_visible "
					+ "	,adm_colorfont "
					+ "	,adm_colorback "
					+ "	,admt_usu "
					+ "	,DATE_FORMAT(adm_fecha_recibido,'%d-%m-%Y %H:%i:%s') as adm_fecha_recibido "
					+ "	,DATE_FORMAT(adm_fecha_cierre,'%d-%m-%Y %H:%i:%s') as adm_fecha_cierre "
					+ "	,adm_tiempocierre "
					+ "	,IF(adm_fecllegadatec IS NULL ,'-',adm_fecllegadatec) as adm_fecllegadatec  "
					+ "	,DATEDIFF(now(),adm_tecnicos.`adm_fecha_recibido`) as diasDesdeEnvio  "
					+ "	,cliente_id"
					+ "	,adm_serie"
					+ "	,adm_deti_estado"
						
    				+ "	FROM adm_tecnicos"
    				+ " WHERE admt_tipoticket NOT IN ('SOLICITUD TONER NORMAL','SOLICITUD TONER CRITICO','FACTURACION','CONTADOR','MXM POR AUTORIZAR')  ";
    		
    		
    	
        	
			System.out.println(find_ticketsand_sql);
			ResultSet FINDTICKETSANT_RS = state.executeQuery(find_ticketsand_sql);
			ArrayList<String> alltickets30 = new ArrayList<String>();
			while(FINDTICKETSANT_RS.next()){
				if(FINDTICKETSANT_RS.getInt("diasDesdeEnvio")<=30){
					alltickets30.add(FINDTICKETSANT_RS.getString("admt_ticket")+";"+FINDTICKETSANT_RS.getString("cliente_id")+";"+FINDTICKETSANT_RS.getString("adm_serie"));
				}
			}
			
    		String find_tickets_sql="select "
    				+ "	admt_ticket"
    				+ "	,IF(admt_prioridad IS NULL, '',admt_prioridad) as admt_prioridad"
    				+ "	,admt_clpr_nombre"
    				+ "	,admt_distnof"
    				+ "	,admt_distusu"
    				+ "	,admt_req_repuesto"
    				+ "	,admt_tiempo"
    				+ "	,admt_tipoticket"
    				+ "	,admt_visible "
					+ "	,adm_colorfont "
					+ "	,adm_colorback "
					+ "	,admt_usu "
					+ "	,DATE_FORMAT(adm_fecha_recibido,'%d-%m-%Y %H:%i:%s') as adm_fecha_recibido "
					+ "	,DATE_FORMAT(adm_fecha_cierre,'%d-%m-%Y %H:%i:%s') as adm_fecha_cierre "
					+ "	,adm_tiempocierre "
					+ "	,IF(adm_fecllegadatec IS NULL ,'-',adm_fecllegadatec) as adm_fecllegadatec  "
					+ "	,cliente_id  "
					+ "	,adm_serie"
					+ "	,adm_deti_estado"
					+ "	FROM adm_tecnicos "
    				+ "	WHERE admt_ticket IN ("+tickets_cadena+")";
    		
    		if(f1!=null && !f1.equals("") && f2!=null && !f2.equals("") ){
    			
    			String f1_ar[]=f1.split("-");
    			String f2_ar[]=f2.split("-");
    			
    			find_tickets_sql+=" AND adm_fecha_recibido>'"+f1_ar[2]+"-"+f1_ar[1]+"-"+f1_ar[0]+" 00:00:00' AND adm_fecha_recibido<'"+f2_ar[2]+"-"+f2_ar[1]+"-"+f2_ar[0]+" 23:59:59'";	
    		}
			if(emp!=null && !emp.equals("")){
    			
    			
    			
    			find_tickets_sql+=" AND cliente_id='"+emp+"'  ";	
    		}
    		
    		find_tickets_sql+= " ORDER BY admt_distnof";
        	
			System.out.println(find_tickets_sql);
			ResultSet FINDTICKETS_RS = state.executeQuery(find_tickets_sql);
    		while(FINDTICKETS_RS.next()){
    			
    			//buscamos si el ticket tiene predecesores en menos de 30 dias 
    			
    			String semaforo="0";
    			boolean revisar=true;
    			if(FINDTICKETS_RS.getString("admt_tipoticket").equals("SOLICITUD TONER NORMAL"))revisar=false;
    			else if(FINDTICKETS_RS.getString("admt_tipoticket").equals("SOLICITUD TONER CRITICO"))revisar=false;
    			else if(FINDTICKETS_RS.getString("admt_tipoticket").equals("FACTURACION"))revisar=false;
    			else if(FINDTICKETS_RS.getString("admt_tipoticket").equals("CONTADOR"))revisar=false;
    			else if(FINDTICKETS_RS.getString("admt_tipoticket").equals("MXM POR AUTORIZAR"))revisar=false;
    			
    			
    			
    			if(revisar)for(String tick30:alltickets30){
    				String ar_tick30[]=tick30.split(";");
    				if(ar_tick30[0].equals(FINDTICKETS_RS.getString("admt_ticket"))) continue;
    				
    				if(ar_tick30[1].equals(FINDTICKETS_RS.getString("cliente_id")) && ar_tick30[2].equals(FINDTICKETS_RS.getString("adm_serie"))){
    					System.out.println(ar_tick30[0]+" "+ar_tick30[2]+" "+FINDTICKETS_RS.getString("adm_serie"));
    					semaforo="1";
    					
    					break;
    				}
    				
    				
    			}
    				
    			tickets.add(FINDTICKETS_RS.getString("admt_usu")
    						+";"+FINDTICKETS_RS.getString("admt_ticket")
    						+";"+FINDTICKETS_RS.getString("admt_prioridad")
    						+";"+FINDTICKETS_RS.getString("admt_clpr_nombre")
    						+";"+FINDTICKETS_RS.getString("admt_req_repuesto")
    						+";"+FINDTICKETS_RS.getString("admt_distnof")
    						+";"+FINDTICKETS_RS.getString("admt_distusu")
    						+";"+Controlador.getHrsMinutes(FINDTICKETS_RS.getInt("admt_tiempo"))
    						+";"+FINDTICKETS_RS.getString("admt_tipoticket")
    						+";"+FINDTICKETS_RS.getString("admt_visible")
    						+";"+FINDTICKETS_RS.getString("adm_colorfont").replace(";","") 
    						+";"+FINDTICKETS_RS.getString("adm_colorback").replace(";","")
    						+";"+FINDTICKETS_RS.getString("adm_fecha_recibido").replace(";","")
    						+";"+FINDTICKETS_RS.getString("adm_fecha_cierre").replace(";","")
    						+";"+Controlador.getHrsMinutes(FINDTICKETS_RS.getInt("adm_tiempocierre"))
    						+";"+FINDTICKETS_RS.getString("adm_fecllegadatec")
    						+";"+semaforo
    						+";"+FINDTICKETS_RS.getString("adm_deti_estado")
    						
    						);
 
    			
    			
    			
    		}
    		
    		
    		
    		
    		request.setAttribute("tickets", tickets);
    		request.setAttribute("usuarios_tick", usuarios_tick);
		    birtBD.Desconectar();
    		
		    conexion.close();
                
          
		}catch(Exception ex){
		    ex.printStackTrace();
			//out.println("Error "+ex);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("c2.jsp");
        rd.forward(request, response);
		
	}

}
