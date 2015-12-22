

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class ifac
 */
@WebServlet("/ifac")
public class ifac extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ifac() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public boolean validateSession(HttpServletRequest request, HttpServletResponse response){
		
  		boolean user_in_session=false;
  		Cookie [] cookies=request.getCookies();
  		
  		if (cookies != null) {
  		    for (Cookie cookie : cookies) {
  		        //work with cookies
  		    	 //System.out.println("cookie: "+cookie.getName());
  		    	if(cookie.getName().equals("Usuarios_id")) user_in_session=true;
  		    }
  		}
  		//refrescamos la session 
  		
  		if (user_in_session && cookies != null) {
  		    for (Cookie cookie : cookies) {
  		        //work with cookies
  		    	 //System.out.println("cookie: "+cookie.getName());
  		    	if(cookie.getName().equals("Usuarios_id")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
  		    	if(cookie.getName().equals("Usuarios_nombre")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
  		    	if(cookie.getName().equals("Usuarios_login")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
  		    	if(cookie.getName().equals("Usuarios_email")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
  		    	if(cookie.getName().equals("tipo_usu_id")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
  		    }
  		}
  		
  		
  		return user_in_session;
  		
      }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(validateSession(request, response)){
			
			 mt(request,response);
		}
		else response.sendRedirect("/001/");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(validateSession(request, response)){
			
			 mt(request,response);
		}
		else response.sendRedirect("/001/");
	}
	
	public void mt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();		
		
		// logout
		Cookie [] cookies=request.getCookies();
		if(request.getParameter("logout") !=  null){
			if (cookies != null) {
			    for (Cookie cookie : cookies) {
			        //work with cookies
			    	 System.out.println("cookie logout: "+cookie.getName());
			    	 
			    	if(cookie.getName().equals("Usuarios_id")) {cookie.setMaxAge(0);cookie.setPath("/");response.addCookie(cookie);}
			    }
			}
				response.sendRedirect("/001/");
				return;
		}
		//fin logout

		String Usuarios_nombre="";
		String id_iusuario="";
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName());
		    	if(cookie.getName().equals("Usuarios_id") && cookie.getValue()!=null) id_iusuario=cookie.getValue();
		    	if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}		
		request.setAttribute("usuname", Usuarios_nombre);	
		
		//grabar

		if(request.getParameter("grabar") != null){
			Statement stategrabar = null;	
			try {
				Statement state = null;
				Statement state_fac = null;
				Statement state_facdet = null;
				String FID=request.getParameter("fact_id");
				String tipodte=request.getParameter("tipodte");
				String REF=request.getParameter("ref");
				String OBS1=request.getParameter("obs1");
				String OBS2=request.getParameter("obs2");
				String DIRE_ID = request.getParameter("dire_id");
				
				String CONT_EMAIL=request.getParameter("CONT_EMAIL");
				String CONT_TELEFONO=request.getParameter("CONT_TELEFONO");
				String CONT_NOMBRE=request.getParameter("CONT_NOMBRE");
				String USU_INICIAL=request.getParameter("USU_INICIAL");
				
				String FACT_EMPRESA_EMISORA_NOMBRE=request.getParameter("FACT_EMPRESA_EMISORA_NOMBRE");
				
				String FACT_NETO=request.getParameter("FACT_NETO");;
				String FACT_DESCUENTO=request.getParameter("desc");
				String FACT_SUBTOTAL=request.getParameter("FACT_SUBTOTAL");
				String FACT_IVA=request.getParameter("FACT_IVA");
				String FACT_TOTAL=request.getParameter("FACT_TOTAL");
				String FACT_GLOSA_DESC=request.getParameter("glosa_desc");
				String FACT_FECVENCIMIENTO_AR[]=request.getParameter("fecvencimiento").toString().split("-");
				String FACT_FECVENCIMIENTO=FACT_FECVENCIMIENTO_AR[2]+"-"+FACT_FECVENCIMIENTO_AR[1]+"-"+FACT_FECVENCIMIENTO_AR[0];
				
				String tipo_dteref=request.getParameter("tipo_dteref");
				String folioref=request.getParameter("folioref");
				String fec_ref=request.getParameter("fec_ref");
				
				if(tipo_dteref==null)tipo_dteref="";
				if(folioref==null)folioref="";
				if(fec_ref==null){fec_ref="NULL";}
				else{ 
					String fecrefar[]=fec_ref.split("-");
					fec_ref ="'"+fecrefar[2]+"-"+fecrefar[1]+"-"+fecrefar[0]+"'";
					}
				
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
				stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
				state_fac = (Statement) ((java.sql.Connection) conexion).createStatement();
				state_facdet = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
			    //DATOS DE LA TABLA FACTURA
	    		ConBirt birtBDF= new ConBirt();
	            ResultSet QueryBirtF= birtBDF.Consulta("SELECT * FROM FACTURA WHERE FACTURA.FACT_ID ='"+FID+"'");
	            QueryBirtF.next();
			    String FACT_TIPO_CAMBIO = QueryBirtF.getString("FACT_TIPO_CAMBIO");
	            String CLPR_ID = QueryBirtF.getString("CLPR_ID");
	            //String DIRE_ID = QueryBirtF.getString("DIRE_ID");
	            String CONT_ID = QueryBirtF.getString("CONT_ID");
	            String FACT_FECHA = QueryBirtF.getString("FACT_FECHA");
	            String FACT_CONDICIONES = QueryBirtF.getString("FACT_CONDICIONES");
	            String FACT_OBS = QueryBirtF.getString("FACT_OBS");
	            String FACT_CIUDAD = QueryBirtF.getString("FACT_CIUDAD");
	            String FECHA_UM = QueryBirtF.getString("FECHA_UM");
	            String USU_ID_UM = QueryBirtF.getString("USU_ID_UM");
	            String FACT_FECHA_EMISION = QueryBirtF.getString("FACT_FECHA_EMISION");
	            String FACT_USU_EMISION = QueryBirtF.getString("FACT_USU_EMISION");
	            String FACT_EMPRESA_EMISORA = QueryBirtF.getString("FACT_EMPRESA_EMISORA");
	            QueryBirtF.close();
	 		    
	    		ConBirt birtBD0= new ConBirt();
	            ResultSet QueryBirt0= birtBD0.Consulta("SELECT TOP 1 DETALLE_VENTA.VTA_ID "
	            		+ " FROM DETALLE_FACTURA INNER JOIN DETALLE_VENTA ON DETALLE_FACTURA.DETI_TRAS_ID = DETALLE_VENTA.DETIVTA_ID "
	            		+ " WHERE DETALLE_FACTURA.FACT_ID ='"+FID+"'");
	            QueryBirt0.next();
	            String VID = QueryBirt0.getString("VTA_ID");
	            QueryBirt0.close();	            

	            ConBirt birtBDV= new ConBirt();
			    ResultSet QueryBirt1= birtBDV.Consulta("SELECT VTA_RESPONSABLE FROM VENTA WHERE VTA_ID ='"+VID+"'");
	            QueryBirt1.next();
	            String VTA_RESPONSABLE = QueryBirt1.getString("VTA_RESPONSABLE");
	 		    QueryBirt1.close();
	 		    
	 		    Float N=0.0f, I=0.0f;
	            ConBirt birtBD2= new ConBirt();
	            String query_d="SELECT "
	 		   		+ "		A.ALT_ID,"
	 		   		+ "		p.PROD_DESC_CORTO,"
	 		   		+ "		DETALLE_VENTA.DETIVTA_PV,"
	 		   		+ "		p.PROD_PN_TLI_CODFAB,"
	 		   		+ "		A.ALT_SERIE,"
	 		   		+ "		DETALLE_VENTA.UBI_ID,"
	 		   		+ "		[dbo].[VENTA].[VTA_TICK_ID], "
	 		   		+ "		[dbo].[VENTA].[VTA_OC] "
	 		   		+ "	FROM"
	 		   		+ "		[dbo].[FACTURA]"
	 		   		+ "	INNER JOIN [dbo].[DETALLE_FACTURA] ON [dbo].[DETALLE_FACTURA].[FACT_ID] = [dbo].[FACTURA].[FACT_ID]"
	 		   		+ "	INNER JOIN [dbo].[DETALLE_VENTA] ON [dbo].[DETALLE_FACTURA].[DETI_TRAS_ID] = [dbo].[DETALLE_VENTA].[DETIVTA_ID]"
	 		   		+ "	INNER JOIN [dbo].[VENTA] ON [dbo].[VENTA].[VTA_ID] = [dbo].[DETALLE_VENTA].[VTA_ID] "
	 		   		+ "	INNER JOIN activo a ON DETALLE_VENTA.alt_id = a.alt_id"
	 		   		+ "	INNER JOIN producto p ON a.prod_id = p.prod_id"
	 		   		+ "	WHERE"
	 		   		+ "		[dbo].[FACTURA].[FACT_ID] ='"+FID+"'";
	            ResultSet QueryBirt2= birtBD2.Consulta(query_d);	           
				
				
				
	 		    //INSERTAMOS TODOS LOS DATOS EN NOF LA CABECERA DE LA FACTURA
			    String SQL_FAC = "INSERT INTO `801` ("
			    		+ "801_folio_birt"
			    		+ ", vta_id"
			    		+ ", 801_tipo_cambio"
			    		+ ", CLPR_ID"
			    		+ ", DIRE_ID"
			    		+ ", CONT_ID"
			    		+ ", 801_fecha"
			    		+ ", 801_subtotal"
			    		+ ", 801_IVA"
			    		+ ", 801_TOTAL"
			    		+ ", 801_CONDICIONES"
			    		+ ", 801_OBS"
			    		+ ", 801_CIUDAD"
			    		+ ", 801_feccreacion"
			    		+ ", 801_creador"
			    		+ ", 801_fecmod"
			    		+ ", 801_modificador"
			    		+ ", 801_ESTADO"
			    		+ ", 801_FECHA_EMISION"
			    		+ ", 801_USU_EMISION"
			    		+ ", 801_EMPRESA_EMISORA"
			    		+ ", 801_RESPONSABLE"
			    		+ ", 801_otrasobs"
			    		+ ", 801_obs1"
			    		+ ", 801_obs2"
			    		+ ", 801_tipodte"
			    		+ ",CONT_EMAIL"
			    		+ ",CONT_TELEFONO"
			    		+ ",CONT_NOMBRE"
			    		+ ",USU_INICIAL_EMISOR"
			    		+ " ,801_EMPRESA_EMISORA_NOMBRE"
			    		+ " ,tipo_dteref"
			    		+ " ,folioref"
			    		+ " ,fec_ref"
			    		+ " ,801_NETO"
			    		+ " ,801_descuento "
			    		+ "	,801_glosa_descuento"
			    		+ " ,801_fecvencimiento ) "
			    	+ " VALUES ('"+FID+"'"
			    			+ ",'"+VID+"'"
			    			+ ",'"+FACT_TIPO_CAMBIO+"'"
			    			+ ",'"+CLPR_ID+"'"
			    			+ ",'"+DIRE_ID+"'"
			    			+ ",'"+CONT_ID+"'"
			    			+ ",'"+FACT_FECHA+"'"
			    			+ ",'"+FACT_SUBTOTAL+"'"
			    			+ ",'"+FACT_IVA+"'"
			    			+ ",'"+FACT_TOTAL+"'"
			    			+ ",'"+FACT_CONDICIONES.toUpperCase()+"'"
			    			+ ",'"+FACT_OBS.toUpperCase()+"'"
			    			+ ",'"+FACT_CIUDAD+"'"
			    			+ ",'"+FECHA_UM+"'"
			    			+ ",'"+USU_ID_UM+"'"
			    			+ ",'"+FECHA_UM+"'"
			    			+ ",'"+USU_ID_UM+"'"
			    			+ ",'NO EMITIDA'"
			    			+ ",'"+FACT_FECHA_EMISION+"'"
			    			+ ",'"+FACT_USU_EMISION+"','"+FACT_EMPRESA_EMISORA+"'"
			    			+ ",'"+VTA_RESPONSABLE+"','"+REF+"'"
			    			+ ",'"+OBS1.toUpperCase()+"','"+OBS2.toUpperCase()+"'"
			    			+ ","+tipodte+""
			    			+ ",'"+CONT_EMAIL.toUpperCase()+"','"+CONT_TELEFONO+"'"
			    			+ ",'"+CONT_NOMBRE+"'"
			    			+ ",'"+USU_INICIAL+"',"
			    			+ "'"+FACT_EMPRESA_EMISORA_NOMBRE+"'"
			    			+ ",'"+tipo_dteref+"'"
			    			+ ", '"+folioref+"'"
			    			+ ","+fec_ref+""
			    			+ ","+FACT_NETO+""
			    			+ ","+FACT_DESCUENTO+""
			    			+ ", '"+FACT_GLOSA_DESC.toUpperCase()+"'"
			    			+ ", '"+FACT_FECVENCIMIENTO+"' "
			    			+ ")";
			    System.out.println(SQL_FAC);
			    state_fac.executeUpdate(SQL_FAC,Statement.RETURN_GENERATED_KEYS);
			    
			    ResultSet generatedKeys = null;
	    		  generatedKeys = state_fac.getGeneratedKeys();
	    		  //String id_g="";
	    		 
			    
			    String id_cabecera_insertado="";
			    		 if (generatedKeys.next()) {
			    			 id_cabecera_insertado=generatedKeys.getString(1);
			    		  }

	            while(QueryBirt2.next()){
					
					//INSERTAMOS TODOS LOS DATOS EN NOF EN EL DETALLE DE LA FACTURA
				    String SQL_FACDET = "INSERT INTO detalle_801 (FACT_ID, DETI_UNITARIO, ALT_ID,PROD_PN_TLI_CODFAB,PROD_DESC_CORTO,ALT_SERIE,UBI_ID,VTA_TICK_ID, VTA_OC) "
				    			+ "VALUES ("+id_cabecera_insertado+","+QueryBirt2.getInt("DETIVTA_PV")+","+QueryBirt2.getInt("ALT_ID")+",'"+QueryBirt2.getString("PROD_PN_TLI_CODFAB")+"'"
				    					+ ",'"+QueryBirt2.getString("PROD_DESC_CORTO")+"','"+QueryBirt2.getString("ALT_SERIE")+"'"
				    					+ ",'"+QueryBirt2.getString("UBI_ID")+"','"+QueryBirt2.getString("VTA_TICK_ID")+"','"+QueryBirt2.getString("VTA_OC")+"')";
				    //System.out.println("SQL Instert: "+SQL_FACDET);
				    state_facdet.executeUpdate(SQL_FACDET);
				}
				QueryBirt2.close();
				state_facdet.close();
			    
			    
			    
			    state_fac.close();

	        	birtBD0.Desconectar();
	        	birtBDF.Desconectar();
	        	birtBD2.Desconectar();
	        	
	        	
	        	
	        	//insertamos logs
	    		String log_sql=""
	    				+ " INSERT INTO `log` ("
	    				+ "	`log`.`log_fec`, "
	    				+ "	`log`.`log_nombre`, " 
	    				+ "	`log`.`Usuarios_id` "
	    				+ " ) "
	    				+ " VALUES "
	    				+ " (now(), 'USUARIO INGRESA EL REGISTRO "+id_cabecera_insertado+" A LA TABLA 801', '"+id_iusuario+"') ";
	    		state.executeUpdate(log_sql);
	    		
	        	
	        	conexion.close();
	        	RequestDispatcher rd_up = request.getRequestDispatcher("menufac?Exito=OK");
	        	rd_up.forward(request, response);
	        	//return;
	        	
			}catch(Exception ex){
				ex.printStackTrace();
			    out.println("Error "+ex);
			}
	  
			
		}else{
		
			try {
				Statement state = null;
				ResultSet GUIAS_RS = null;
				
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		String FID = request.getParameter("fact_id");

	    		//DATOS DE LA TABLA FACTURA
	    		ConBirt birtBDF= new ConBirt();
	    		String q_fac="SELECT  "
	            		+ " CONVERT(varchar, FACTURA.FACT_FECHA, 105) AS FACT_FECHA, "
	            		+ " FACTURA.FACT_CONDICIONES, "
	            		+ " C1.CLPR_NOMBRE_FANTASIA, "
	            		+ " C2.CLPR_RAZON_SOCIAL, C2.CLPR_RUT, C2.CLPR_DV, C2.CLPR_GIRO,"	
	            		+ " FACTURA.DIRE_ID, COMUNA.COMU_NOMBRE, "
			    		+ " DIRECCION.DIRE_ID, DIRECCION.DIRE_CIUDAD, FACTURA.CLPR_ID,"
			    		+ " REGION.REGI_NOMBRE, "
	            		+ " FACTURA.FACT_TIPO_CAMBIO, "
	            		+ " CONTACTO.CONT_NOMBRE, "
	            		+ " CONTACTO.CONT_TELEFONO, "
	            		+ "	CONTACTO.CONT_EMAIL, "
	            		+ " FACTURA.FACT_TOTAL_TEXTO, "
	            		+ " FACTURA.FACT_OBS, "
	            		+ " FACTURA.FACT_ESTADO, "
	            		+ " CONVERT(varchar, FACTURA.FACT_FECHA_EMISION, 105) AS FACT_FECHA_EMISION, "
	            		+ " USUARIO.USU_INICIAL "
	            		+ " FROM FACTURA "
	            		+ " INNER JOIN CLIENTEPROVEEDOR as C1 ON FACTURA.FACT_EMPRESA_EMISORA = C1.CLPR_ID "
	            		+ " INNER JOIN CLIENTEPROVEEDOR as C2 ON FACTURA.CLPR_ID = C2.CLPR_ID "
	            		+ " INNER JOIN DIRECCION ON FACTURA.DIRE_ID = DIRECCION.DIRE_ID"
	            		+ " INNER JOIN COMUNA ON DIRECCION.COMU_ID = COMUNA.COMU_ID"
			    		+ " INNER JOIN REGION ON COMUNA.REGI_ID = REGION.REGI_ID"
	            		+ " INNER JOIN CONTACTO ON FACTURA.CONT_ID = CONTACTO.CONT_ID "
	            		+ " INNER JOIN USUARIO ON FACTURA.FACT_USU_EMISION = USUARIO.USU_ID "
	            		+ " WHERE FACTURA.FACT_ID ='"+FID+"'";
	    		
	    		System.out.println(q_fac);
	    		
	            ResultSet QueryBirtF= birtBDF.Consulta(q_fac);
	            QueryBirtF.next();
	            request.setAttribute("FACT_ID",FID);
		 		request.setAttribute("FACT_FECHA",QueryBirtF.getString("FACT_FECHA"));
		 		request.setAttribute("FACT_CONDICIONES",QueryBirtF.getString("FACT_CONDICIONES"));
		 		request.setAttribute("CLPR_NOMBRE_FANTASIA",QueryBirtF.getString("CLPR_NOMBRE_FANTASIA"));
		 		request.setAttribute("CLPR_RAZON_SOCIAL",QueryBirtF.getString("CLPR_RAZON_SOCIAL"));
		 		request.setAttribute("CLPR_RUT",QueryBirtF.getString("CLPR_RUT"));
		 		request.setAttribute("CLPR_DV",QueryBirtF.getString("CLPR_DV"));
		 		request.setAttribute("CLPR_GIRO",QueryBirtF.getString("CLPR_GIRO"));
		 		//request.setAttribute("DIRE_DIRECCION",QueryBirtF.getString("DIRE_DIRECCION"));
		 		request.setAttribute("COMU_NOMBRE",QueryBirtF.getString("COMU_NOMBRE"));
		 		request.setAttribute("DIRE_CIUDAD",QueryBirtF.getString("DIRE_CIUDAD"));
		 		request.setAttribute("REGI_NOMBRE",QueryBirtF.getString("REGI_NOMBRE"));
		 		Float cambio = QueryBirtF.getFloat("FACT_TIPO_CAMBIO");
		 		request.setAttribute("FACT_TIPO_CAMBIO",cambio.intValue()+"");
		 		//request.setAttribute("FACT_TIPO_CAMBIO",QueryBirtF.getString("FACT_TIPO_CAMBIO"));
		 		request.setAttribute("CONT_NOMBRE",QueryBirtF.getString("CONT_NOMBRE"));
		 		request.setAttribute("CONT_TELEFONO",QueryBirtF.getString("CONT_TELEFONO"));
		 		request.setAttribute("CONT_EMAIL",QueryBirtF.getString("CONT_EMAIL"));
		 		
		 		request.setAttribute("FACT_TOTAL_TEXTO",QueryBirtF.getString("FACT_TOTAL_TEXTO"));
		 		request.setAttribute("FACT_OBS",QueryBirtF.getString("FACT_OBS"));
		 		request.setAttribute("FACT_ESTADO",QueryBirtF.getString("FACT_ESTADO"));
		 		request.setAttribute("FACT_FECHA_EMISION",QueryBirtF.getString("FACT_FECHA_EMISION"));
		 		request.setAttribute("USU_INICIAL",QueryBirtF.getString("USU_INICIAL"));
		 		
		 		String clpr_id=QueryBirtF.getString("CLPR_ID");
	            QueryBirtF.close();
	    		
	            ConBirt birtBDD= new ConBirt();
		 		ResultSet QueryBirtD= birtBDD.Consulta("SELECT * FROM DIRECCION WHERE CLPR_ID ='"+clpr_id+"'");
		 		ArrayList<String> dire_res = new ArrayList<String>();
		 		while(QueryBirtD.next()){
		 			dire_res.add(QueryBirtD.getString("DIRE_ID")+"/"+QueryBirtD.getString("DIRE_DIRECCION"));
		 		}
		 		QueryBirtD.close();
		 		 
		 		String[] ar_dire = new String[dire_res.size()];
		 		for(int x=0; x < dire_res.size(); x++){
		 			ar_dire[x]=dire_res.get(x);
		 		}
		 		request.setAttribute("ar_dire", ar_dire);
	            
	            String VID;
	    		ConBirt birtBD0= new ConBirt();
	            ResultSet QueryBirt0= birtBD0.Consulta("SELECT TOP 1 DETALLE_VENTA.VTA_ID "
	            		+ " FROM DETALLE_FACTURA INNER JOIN DETALLE_VENTA ON DETALLE_FACTURA.DETI_TRAS_ID = DETALLE_VENTA.DETIVTA_ID "
	            		+ " WHERE DETALLE_FACTURA.FACT_ID ='"+FID+"'");
	            if(QueryBirt0.next()){
	            	VID = QueryBirt0.getString("VTA_ID");
	            QueryBirt0.close();
	            
	            ConBirt birtBD1= new ConBirt();
			    ResultSet QueryBirt1= birtBD1.Consulta("SELECT USUARIO.USU_NOMBRE FROM VENTA "
			    		+ " INNER JOIN USUARIO ON VENTA.VTA_RESPONSABLE = USUARIO.USU_ID"
			    		+ " WHERE VTA_ID ='"+VID+"'");
	            QueryBirt1.next();
		 		request.setAttribute("USU_NOMBRE",QueryBirt1.getString("USU_NOMBRE"));
	 		    QueryBirt1.close();
	            
	    		String SQL_Guias = "SELECT count(*) as n FROM `801` WHERE `801_folio_birt`='"+FID+"'"; 
	    		System.out.println(SQL_Guias);
	 		    GUIAS_RS =  state.executeQuery(SQL_Guias);
			    if(GUIAS_RS.next()){ 
			    	if(GUIAS_RS.getInt("n")==0){
			    		request.setAttribute("id_fac_estado","NO ENVIADA");
			    	}else{
			    		request.setAttribute("id_fac_estado","ENVIADA");
			    	}
			    }
	 		    GUIAS_RS.close();
	 		    state.close();
	 		   
	 		   Float N=0.0f, I=0.0f;
	 		   ConBirt birtBD2= new ConBirt();
	 		   ResultSet QueryBirt2= birtBD2.Consulta("SELECT "
	 		   		+ "		A.ALT_ID,"
	 		   		+ "		p.PROD_DESC_CORTO,"
	 		   		+ "		DETALLE_VENTA.DETIVTA_PV,"
	 		   		+ "		p.PROD_PN_TLI_CODFAB,"
	 		   		+ "		A.ALT_SERIE,"
	 		   		+ "		DETALLE_VENTA.UBI_ID,"
	 		   		+ "		[dbo].[VENTA].[VTA_TICK_ID], "
	 		   		+ "		[dbo].[VENTA].[VTA_OC] "
	 		   		+ "	FROM"
	 		   		+ "		[dbo].[FACTURA]"
	 		   		+ "	INNER JOIN [dbo].[DETALLE_FACTURA] ON [dbo].[DETALLE_FACTURA].[FACT_ID] = [dbo].[FACTURA].[FACT_ID]"
	 		   		+ "	INNER JOIN [dbo].[DETALLE_VENTA] ON [dbo].[DETALLE_FACTURA].[DETI_TRAS_ID] = [dbo].[DETALLE_VENTA].[DETIVTA_ID]"
	 		   		+ "	INNER JOIN [dbo].[VENTA] ON [dbo].[VENTA].[VTA_ID] = [dbo].[DETALLE_VENTA].[VTA_ID] "
	 		   		+ "	INNER JOIN activo a ON DETALLE_VENTA.alt_id = a.alt_id"
	 		   		+ "	INNER JOIN producto p ON a.prod_id = p.prod_id"
	 		   		+ "	WHERE"
	 		   		+ "		[dbo].[FACTURA].[FACT_ID] ='"+FID+"'");
	 		   ArrayList<String> prod_res = new ArrayList<String>();
	 		   while(QueryBirt2.next()){
	 			   N=N+QueryBirt2.getFloat("DETIVTA_PV");
	 			   I=I+((QueryBirt2.getFloat("DETIVTA_PV")*19)/100);
	 			  request.setAttribute("VTA_OC",QueryBirt2.getString("VTA_OC"));
			 		
	 			   prod_res.add(QueryBirt2.getString("ALT_ID")+"/"+
	 					  QueryBirt2.getString("PROD_PN_TLI_CODFAB").replace("/", " ")+"/"+
	 					  QueryBirt2.getString("PROD_DESC_CORTO").replace("/", " ")+"/"+
	 					  QueryBirt2.getString("ALT_SERIE").replace("/", " ")+"/"+
	 					  QueryBirt2.getInt("DETIVTA_PV")+"/"+
	 					 QueryBirt2.getInt("UBI_ID")+"/"+
	 					QueryBirt2.getInt("VTA_TICK_ID"));
	 		   }
	 		   QueryBirt2.close();
	 		   Float total=(I+N);
	 		   String total2 = (total.intValue())+"";
	 		   String i2 = (I.intValue())+"";
	 		   String n2 = (N.intValue())+"";
	 		   request.setAttribute("IVA",i2.toString());
	 		   request.setAttribute("NETO",n2.toString());
	 		   request.setAttribute("TOTAL",total2.toString());
	 		 
	 		   String[] ar_productos = new String[prod_res.size()];
	 		   for(int x=0; x < prod_res.size(); x++){
	 			   ar_productos[x]=prod_res.get(x);
	 		   }
	 		   request.setAttribute("ar_productos", ar_productos);
	           
	           birtBD1.Desconectar();
	           birtBD2.Desconectar();
	            }
	           birtBDF.Desconectar();
	           conexion.close();
			}catch(Exception ex){
			    out.println("ERROR: "+ex);
			    ex.printStackTrace();
			}
		//fin grabar
			
		RequestDispatcher rd = request.getRequestDispatcher("ifac.jsp");
        rd.forward(request, response);
		
		}
		
	}

}
