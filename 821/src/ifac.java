

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
				String GID=request.getParameter("gv_id");
				String OBS1=request.getParameter("obs1");
				String OBS2=request.getParameter("obs2");
				
				///gv_fecha al reves 
				String fec[] =request.getParameter("gv_fecha").split("-");
				String GV_FECHA="'"+fec[2]+"-"+fec[1]+"-"+fec[0]+"'";
				
				
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
				
				String g_afecta=request.getParameter("g_afecta");
				if(g_afecta==null || g_afecta.equals(""))g_afecta="0";
				
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
	            ResultSet QueryBirtF= birtBDF.Consulta("SELECT * FROM GUIA_VENTA WHERE GV_ID ='"+GID+"'");
	            QueryBirtF.next();
	            String CLPR_ID = QueryBirtF.getString("GV_CLPR_ID");
	            String DIRE_ID = QueryBirtF.getString("GV_DIRE_ID");
	            String CONT_ID = QueryBirtF.getString("GV_CONT_ID");
	            
	            String GV_OBS = QueryBirtF.getString("GV_OBS");
	            String GV_CIUDAD = QueryBirtF.getString("GV_CIUDAD");
	            String GV_FECHA_EMISION = QueryBirtF.getString("GV_FECHA_EMISION");
	            String GV_USU_EMISION = QueryBirtF.getString("GV_USU_EMISION");
	            String GV_RESPONSABLE = QueryBirtF.getString("GV_CONT_ID");
	            String GV_TRANSPORTE = QueryBirtF.getString("GV_TRANSPORTE");
	            String GV_FECHA_UM = QueryBirtF.getString("GV_FECHA_UM");
	            String USU_ID_UM = QueryBirtF.getString("USU_ID_UM");
	            String GV_EMPRESA_EMISORA = QueryBirtF.getString("GV_EMPRESA_EMISORA");
	            
	            String GV_RESPONSBALE_NAME=request.getParameter("GV_RESPONSBALE_NAME");
	            
	            String EMISOR_INICIAL=request.getParameter("EMISOR_INICIAL");
	            
	            String GUIA_SUBTOTAL=request.getParameter("subtotal");
		        String GUIA_NETO=request.getParameter("neto");
	            String desc=request.getParameter("desc");
	            String glosa_desc=request.getParameter("glosa_desc");
	         
	            String empresa_emisora_nombre=request.getParameter("empresa_emisora_nombre");
	            String contacto_nombre=request.getParameter("contacto_nombre");
		            
	            String GUIA_IVA=request.getParameter("iva");
	            String GUIA_TOTAL=request.getParameter("total");
	            
	            
	            QueryBirtF.close();
	 		    
	         
			
				
	 		    //INSERTAMOS TODOS LOS DATOS EN NOF LA CABECERA DE LA FACTURA
			    String SQL_FAC = "INSERT INTO `821` ("
			    		+ " 821_folio_birt, "
			    		
			    		+ " CLPR_ID, "
			    		+ " DIRE_ID, "
			    		+ " CONT_ID, "
			    		+ " 821_FECHA, "
			    		+ " 821_SUBTOTAL, "
			    		+ " 821_TOTAL,"
			    		+ "	821_descuento,"
			    		+ "	821_glosa_descuento, "
			    		+ " 821_OBS, "
			    		+ " 821_CIUDAD, "
			    		+ " 821_ESTADO, "
			    		+ " 821_EMPRESA_EMISORA, "
			    		+ " 821_FECHA_EMISION, "
			    		+ " 821_USU_EMISION, "
			    		+ " 821_RESPONSABLE,"
			    		+ " 821_feccreacion, "
			    		+ " 821_fecmod, "
			    		+ " 821_creador, "
			    		+ " 821_modificador, "
			    		+ " 821_obs1, "
			    		+ " 821_obs2, "
			    		+ " 821_TRANSPORTE,"
			    		+ " 821_responsable_name,"
			    		+ " usu_inicial_emisor,"
			    		+ " 821_NETO,"
			    		+ " 821_IVA, "
			    		+ " 821_empresa_emisora_nombre,"
			    		+ " contacto_nombre,"

						+ "	`821`.`tipo_dteref`, "
						+ "	`821`.`folioref`, "
						+ "	`821`.`fec_ref`,"
						+ "	`821`.`821_afecta`  "
			    		+ " )"
			    		+ " VALUES ('"+GID+"',"
		    			
		    			+ "'"+CLPR_ID+"',"
		    			+ "'"+DIRE_ID+"',"
		    			+ "'"+CONT_ID+"',"
		    			+ ""+GV_FECHA+","
		    			+ "'"+GUIA_SUBTOTAL+"',"
		    			+ "'"+GUIA_TOTAL+"',"
		    			+ "'"+desc+"',"
		    			+ "'"+glosa_desc.toUpperCase()+"',"
		    			+ "'"+GV_OBS.toUpperCase()+"',"
		    			+ "'"+GV_CIUDAD+"',"
		    			+ "'NO EMITIDA',"
		    			+ "'"+GV_EMPRESA_EMISORA+"',"
		    			+ "'"+GV_FECHA_EMISION+"',"
		    			+ "'"+GV_USU_EMISION+"',"
		    			+ "'"+GV_RESPONSABLE+"',"
		    			+ "'"+GV_FECHA_EMISION+"',"
		    			+ "'"+GV_FECHA_UM+"',"
		    			+ "'"+GV_USU_EMISION+"',"
		    			+ "'"+USU_ID_UM+"',"
		    			+ "'"+OBS1.toUpperCase()+"','"+OBS2.toUpperCase()+"',"
		    			+ "'"+GV_TRANSPORTE+"','"+GV_RESPONSBALE_NAME+"',"
		    			+ "'"+EMISOR_INICIAL+"',"
		    			+ "'"+GUIA_NETO+"','"+GUIA_IVA+"','"+empresa_emisora_nombre.toUpperCase()+"'"
		    			+ ",'"+contacto_nombre+"'"
		    			+ ",'"+tipo_dteref+"'"
		    			+ ", '"+folioref.toUpperCase()+"'"
		    			+ ","+fec_ref+" "
		    			+ ",'"+g_afecta+"' "
		    		
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
			    		 
			    		 
			    		   String VID ="";
				             ConBirt birtBD2= new ConBirt();
				            ResultSet QueryBirt2= birtBD2.Consulta("SELECT "
					 		   		+ " v.VTA_ID, v.ALT_ID, p.PROD_PN_TLI_CODFAB, p.PROD_DESC_CORTO, a.ALT_SERIE, d.DETIGV_PRECIO_NETO,v.UBI_ID,ven.VTA_TICK_ID"
					 		   		+ " FROM DETALLE_GUIA_VENTA d"
					 		   		+ " LEFT JOIN DETALLE_VENTA v ON d.ALT_ID = v.DETIVTA_ID"
					 		   		+ " LEFT JOIN VENTA ven ON v.VTA_ID = ven.VTA_ID"
				 		   		
					 		   		+ " LEFT JOIN ACTIVO a ON v.ALT_ID = a.ALT_ID"
					 		   		+ " LEFT JOIN PRODUCTO p ON p.PROD_ID = a.PROD_ID"
					 		   		+ " WHERE d.GV_ID = '"+GID+"'");     
							while(QueryBirt2.next()){
								//INSERTAMOS TODOS LOS DATOS EN NOF EN EL DETALLE DE LA FACTURA
							    String SQL_FACDET = "INSERT INTO detalle_821 (821_id, d821_valor, alt_id,prod_pn_tli_codfab,prod_desc_corto,alt_serie,UBI_ID,TICK_ID) "
							    			+ "VALUES ("+id_cabecera_insertado+",'"+QueryBirt2.getString("DETIGV_PRECIO_NETO")+"'"
							    					+ "	,'"+QueryBirt2.getString("ALT_ID")+"','"+QueryBirt2.getString("PROD_PN_TLI_CODFAB")+"'"
							    					+ "	,'"+QueryBirt2.getString("PROD_DESC_CORTO")+"'"
							    					+ "	,'"+QueryBirt2.getString("ALT_SERIE")+"','"+QueryBirt2.getString("UBI_ID")+"','"+QueryBirt2.getString("VTA_TICK_ID")+"' )";
							    //System.out.println("SQL Instert: "+SQL_FACDET);
							    state_facdet.executeUpdate(SQL_FACDET);
							    VID = QueryBirt2.getString("VTA_ID");
							    
							}
							QueryBirt2.close();
			    		 
			    		 
			    		 
			    		 
			    		 
			    		 
			    		 
			    		 

			  //insertamos logs
	    		String log_sql=""
	    				+ " INSERT INTO `log` ("
	    				+ "	`log`.`log_fec`, "
	    				+ "	`log`.`log_nombre`, " 
	    				+ "	`log`.`Usuarios_id` "
	    				+ " ) "
	    				+ " VALUES "
	    				+ " (now(), 'USUARIO INGRESA REGISTRO "+id_cabecera_insertado+" A LA TABLA 821', '"+id_iusuario+"') ";
	    		state.executeUpdate(log_sql);
			    
			    state_fac.close();

	        	birtBDF.Desconectar();
	        	birtBD2.Desconectar();
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
	    		
	    		String GID = request.getParameter("gv_id");

	    		//DATOS DE LA TABLA FACTURA
	    		ConBirt birtBDF= new ConBirt();
	    		String query_birt="SELECT  "
	            		+ " G.GV_ID,"
	            		+ " CONVERT(varchar, G.GV_FECHA, 105) AS GV_FECHA,"
	            		+ " C2.CLPR_NOMBRE_FANTASIA as EMISORA, "
	            		+ " C.CLPR_RAZON_SOCIAL, C.CLPR_RUT, C.CLPR_DV,C.CLPR_GIRO,"
	            		+ " D.DIRE_DIRECCION, C1.CONT_TELEFONO,"
	            		+ " R.REGI_NOMBRE, COMUNA.COMU_NOMBRE,"
			    		+ " G.GV_CIUDAD, C1.CONT_NOMBRE,"
			    		+ " P.PERS_NOMBRE, "
			    		+ " G.GV_TRANSPORTE, "
	            		+ " G.GV_OBS,"
	            		+ " G.GV_ESTADO, "
	            		+ " CONVERT(varchar, G.GV_FECHA_EMISION, 105)+ ' ' +CONVERT(varchar, G.GV_FECHA_EMISION, 108) AS GV_FECHA_EMISION,"
	            		+ " USUARIO.USU_INICIAL "
	            		+ " FROM GUIA_VENTA G"
	            		+ " INNER JOIN CLIENTEPROVEEDOR as C ON G.GV_CLPR_ID = C.CLPR_ID "
	            		+ " INNER JOIN CLIENTEPROVEEDOR as C2 ON G.GV_EMPRESA_EMISORA = C2.CLPR_ID "
	            		+ " INNER JOIN DIRECCION D ON G.GV_DIRE_ID = D.DIRE_ID"
	            		+ " INNER JOIN COMUNA ON D.COMU_ID = COMUNA.COMU_ID"
			    		+ " INNER JOIN REGION R ON COMUNA.REGI_ID = R.REGI_ID"
	            		+ " INNER JOIN CONTACTO C1 ON G.GV_CONT_ID = C1.CONT_ID "
	            		+ " INNER JOIN PERSONAL P ON C1.PERS_ID = P.PERS_ID "
	            		+ " INNER JOIN USUARIO ON G.GV_USU_EMISION = USUARIO.USU_ID "
	            		+ " WHERE G.GV_ID ='"+GID+"'";
	    		System.out.println("BIRT: "+query_birt);
	            ResultSet QueryBirtF= birtBDF.Consulta(query_birt);
	            QueryBirtF.next();
	            request.setAttribute("GV_ID",GID);
		 		request.setAttribute("GV_FECHA",QueryBirtF.getString("GV_FECHA"));
		 		request.setAttribute("EMISORA",QueryBirtF.getString("EMISORA"));
		 		request.setAttribute("CLPR_RAZON_SOCIAL",QueryBirtF.getString("CLPR_RAZON_SOCIAL"));
		 		request.setAttribute("CLPR_RUT",QueryBirtF.getString("CLPR_RUT"));
		 		request.setAttribute("CLPR_DV",QueryBirtF.getString("CLPR_DV"));
		 		request.setAttribute("DIRE_DIRECCION",QueryBirtF.getString("DIRE_DIRECCION"));
		 		request.setAttribute("CONT_TELEFONO",QueryBirtF.getString("CONT_TELEFONO"));
		 		request.setAttribute("REGI_NOMBRE",QueryBirtF.getString("REGI_NOMBRE"));
		 		request.setAttribute("COMU_NOMBRE",QueryBirtF.getString("COMU_NOMBRE"));
		 		request.setAttribute("GV_CIUDAD",QueryBirtF.getString("GV_CIUDAD"));
		 		request.setAttribute("CONT_NOMBRE",QueryBirtF.getString("CONT_NOMBRE"));
		 		request.setAttribute("PERS_NOMBRE",QueryBirtF.getString("PERS_NOMBRE"));
		 		request.setAttribute("GV_TRANSPORTE",QueryBirtF.getString("GV_TRANSPORTE"));
		 		request.setAttribute("GV_OBS",QueryBirtF.getString("GV_OBS"));
		 		request.setAttribute("GV_ESTADO",QueryBirtF.getString("GV_ESTADO"));
		 		request.setAttribute("GV_FECHA_EMISION",QueryBirtF.getString("GV_FECHA_EMISION"));
		 		request.setAttribute("USU_INICIAL",QueryBirtF.getString("USU_INICIAL"));
		 		
		 		request.setAttribute("CLPR_GIRO",QueryBirtF.getString("CLPR_GIRO"));
	            QueryBirtF.close();
	            
	    		String SQL_Guias = "SELECT count(*) as id_guia_estado FROM `821` WHERE 821_id='"+GID+"'"; 
	    		//System.out.println(SQL_Guias);
	 		    GUIAS_RS =  state.executeQuery(SQL_Guias);
			    if(GUIAS_RS.next()){ 
			    	if(GUIAS_RS.getInt("id_guia_estado")==0){
			    		request.setAttribute("id_guia_estado","NO ENVIADA");
			    	}else{
			    		request.setAttribute("id_guia_estado","ENVIADA");
			    	}
			    }
	 		    GUIAS_RS.close();
	 		    state.close();
	 		   
	 		   ConBirt birtBD2= new ConBirt();
	 		  ResultSet QueryBirt2= birtBD2.Consulta("SELECT "
		 		   		+ " v.VTA_ID,  v.ALT_ID, p.PROD_PN_TLI_CODFAB, p.PROD_DESC_CORTO, a.ALT_SERIE, d.DETIGV_PRECIO_NETO,v.UBI_ID,ven.VTA_TICK_ID "
		 		   		+ " FROM DETALLE_GUIA_VENTA d"
		 		   		+ " LEFT JOIN DETALLE_VENTA v ON d.ALT_ID = v.DETIVTA_ID"
		 		   		+ " LEFT JOIN VENTA ven ON v.VTA_ID = ven.VTA_ID"
		 		   		+ " LEFT JOIN ACTIVO a ON v.ALT_ID = a.ALT_ID"
		 		   		+ " LEFT JOIN PRODUCTO p ON p.PROD_ID = a.PROD_ID"
		 		   		+ " WHERE d.GV_ID = '"+GID+"'"); 
	 		   ArrayList<String> prod_res = new ArrayList<String>();
	 		   
	 		   	int neto=0;
	 		   	int iva=0;
	 		   	int total=0;
	 		   	
	 		   while(QueryBirt2.next()){
	 			   prod_res.add(QueryBirt2.getString("ALT_ID")+";;"+
	 					  QueryBirt2.getString("PROD_PN_TLI_CODFAB")+";;"+
	 					  QueryBirt2.getString("PROD_DESC_CORTO")+";;"+
	 					  QueryBirt2.getString("ALT_SERIE")+";;"+
	 					  QueryBirt2.getInt("DETIGV_PRECIO_NETO")+";;"+
	 					 QueryBirt2.getInt("UBI_ID")+";;"+
	 					 QueryBirt2.getString("VTA_TICK_ID").replace("/", " ")
	 					   );
	 			   neto+=QueryBirt2.getInt("DETIGV_PRECIO_NETO");
	 		   }
	 		   QueryBirt2.close();
	 		   
	 		   iva=(int) (neto*0.19);
	 		   total=iva+neto;
	 		   
	 		   request.setAttribute("neto",neto+"");
	 		   request.setAttribute("iva",iva+"");
	 		   request.setAttribute("total",total+"");
	 		   
	 		   String[] ar_productos = new String[prod_res.size()];
	 		   for(int x=0; x < prod_res.size(); x++){
	 			   ar_productos[x]=prod_res.get(x);
	 		   }
	 		   request.setAttribute("ar_productos", ar_productos);
	           
	           birtBD2.Desconectar();
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
