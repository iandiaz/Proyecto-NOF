

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
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName());
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
				ResultSet RES_RS = null;
				String DID="";
				String OID=request.getParameter("oc_id");
				String tipodte=request.getParameter("tipodte");
				String OBS1=request.getParameter("obs1");
				String OBS2=request.getParameter("obs2");
				String DIRE_ID = request.getParameter("dire_id");
				String netoneto = request.getParameter("netoneto");
				 String FACT_NETO = request.getParameter("total");
				 String FACT_TOTAL = request.getParameter("totaltotal");
				 String CLPR_ID = request.getParameter("CLPR_ID");
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
	            ResultSet QueryBirtF= birtBDF.Consulta("SELECT * FROM ORDEN_COMPRA WHERE OC_ID ='"+OID+"'");
	            QueryBirtF.next();
			    String FACT_TIPO_CAMBIO = QueryBirtF.getString("OC_TIPO_CAMBIO");
	            
	            String CONT_ID = QueryBirtF.getString("CONT_ID");
	            String FACT_FECHA = QueryBirtF.getString("OC_FECHA_CREACION");
	            String cond = QueryBirtF.getString("OC_FORMA_PAGO");
	            String FECHA_UM = QueryBirtF.getString("OC_FECHA_UM");
	            String USU_ID_UM = QueryBirtF.getString("OC_USU_ID_UM");
	           
	            String fac_comserv_valor_ivaret=request.getParameter("iva");
				String fac_comserv_porcentaje_ivaret=request.getParameter("ivap");
				String fac_comserv_valor_ivanoret=request.getParameter("valivanoret");
	            
	            QueryBirtF.close();
				
				String SQL_RES = "SELECT u.Usuarios_nombre, u.Usuarios_ape_p, u.Usuarios_ape_m "
		 				+ "FROM empresas e "
		 				+ "INNER JOIN usuarios u ON e.responsable_id = u.Usuarios_id "
		 				+ "WHERE e.empresas_id='"+CLPR_ID+"'"; 
	    		//System.out.println(SQL_RES);
	 		    RES_RS =  state.executeQuery(SQL_RES);
	 		    RES_RS.next();
	 		    String res = RES_RS.getString("Usuarios_nombre")+" "+RES_RS.getString("Usuarios_ape_p")+" "+RES_RS.getString("Usuarios_ape_m");
	 		    
	 		    //INSERTAMOS TODOS LOS DATOS EN NOF LA CABECERA DE LA FACTURA
			   String SQL_FAC = "INSERT INTO factura_compra_activo (oc_id, fac_comactivo_tipo_cambio, empresas_id, DIRE_ID, CONT_ID, fac_comactivo_fecfac, "
						+ " fac_comactivo_total, "
				    	+ " fac_comactivo_totalfinal, fac_comactivo_feccreacion,"
				    	+ " fac_comactivo_creador, fac_comactivo_fecmod, fac_comactivo_modificador, fac_comactivo_estado, "
				    	+ " FACT_EMPRESA_EMISORA,"
				    	+ " fac_comactivo_emisor, fac_comactivo_obs, fac_comactivo_obs1, fac_comactivo_tipodte, fac_comactivo_condiciones,"
				    	+ " `fac_comactivo_valor_ivaret`, "
				    	+ "	`fac_comactivo_porcentaje_ivaret`, "
				    	+ "	`fac_comactivo_valor_ivanoret`,fac_comactivo_neto ) "
				    	+ " VALUES ('"+OID+"','"+FACT_TIPO_CAMBIO+"','"+CLPR_ID+"','"+DIRE_ID+"','"+CONT_ID+"','"+FACT_FECHA+"',"
				    	+ "'"+FACT_NETO+"',"
				    	+ " '"+FACT_TOTAL+"','"+FECHA_UM+"',"
				    	+ "'"+USU_ID_UM+"','"+FECHA_UM+"','"+USU_ID_UM+"','NO EMITIDA',"
				    	+ "'BIRT',"
				    	+ "1,'"+OBS1+"','"+OBS2+"',"+tipodte+",'"+cond+"',"
				    	+ " '"+fac_comserv_valor_ivaret+"' ,'"+fac_comserv_porcentaje_ivaret+"','"+fac_comserv_valor_ivanoret+"','"+netoneto+"' )";
			    System.out.println(SQL_FAC);
			    state.executeUpdate(SQL_FAC,Statement.RETURN_GENERATED_KEYS);
			    
			    ResultSet generatedKeys = null;
	    		  generatedKeys = state.getGeneratedKeys();
	    		  //String id_g="";
	    		  if (generatedKeys.next()) {
	    			  DID=generatedKeys.getString(1);
	    		  }
	    		  
	    		  //System.out.println("Nuevo id g: "+DID);

	    		  
	    		  String[] seleccionado = request.getParameterValues("recepcionesselec[]");
		    		if(seleccionado!=null) for (String datarec: seleccionado) {
		    			if(datarec!=null){
		    				
		    				String datarec_ar[]=datarec.split(";");
		    				
		    				  String SQL_FACDET = "INSERT INTO detail_fac_comprasactivo (fac_comactivo_id, dfca_valor, dfca_descripcion,recepcion_id,productos_id) "
						    			+ "VALUES ("+DID+","+datarec_ar[2]+",'"+datarec_ar[1]+"','"+datarec_ar[3]+"','"+datarec_ar[0]+"')";
						    System.out.println(SQL_FACDET);
						    state_facdet.executeUpdate(SQL_FACDET);
		    				
		    				
		    				
		    			}	    			
		    		}
		    		
	    		  
	    		  
	    		  
	    		  
	    		  
	    		  
	    		  
			
				state_facdet.close();
				
	        	birtBDF.Desconectar();
	        	
	        	
	        	RequestDispatcher rd_up = request.getRequestDispatcher("menufacact?Exito=OK");
	        	rd_up.forward(request, response);
	        	//return;
	        	
			}catch(Exception ex){
				ex.printStackTrace();
			    out.println("ERROR "+ex);
			}
	  
			
		}else{
		
			try {
				Statement state = null;
				ResultSet RES_RS = null;
				ResultSet GUIAS_RS = null;
				
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		String OID = "";

	    		String recepciones_selec="0";
	    		  String[] seleccionado = request.getParameterValues("recepciones[]");
		    		if(seleccionado!=null) for (String id_rec: seleccionado) {
		    			if(id_rec!=null){
		    				recepciones_selec+=","+id_rec;
		    			}	    			
		    		}
		    		
		    		//buscamos primero el detalle con los id de recepciones seleccionados 
		    		
		    		 ConBirt birtBD2= new ConBirt();
		    		 String querybirt="SELECT "
				 		   		+ "		[PRODUCTO].[PROD_ID],"
				 		   		+ "		[PRODUCTO].[PROD_PN_TLI_CODFAB],"
				 		   		+ "		[PRODUCTO].[PROD_DESC_CORTO],"
				 		   		+ "		[DETALLE_RECEPCION].[DETIRECEP_PRECIO],"
				 		   		+ "		[RECEPCION].[OC_ID],"
				 		   		+ "		[DETALLE_RECEPCION].[RECEP_ID] "
				 		   		+ "	FROM"
				 		   		+ "		[DETALLE_RECEPCION]"
				 		   		+ "	INNER JOIN [RECEPCION] ON [RECEPCION].[RECEP_ID] = [DETALLE_RECEPCION].[RECEP_ID] "
				 		   		+ "	INNER JOIN [ACTIVO] ON [ACTIVO].[ALT_ID] = [DETALLE_RECEPCION].[ALT_ID]"
				 		   		+ "	INNER JOIN [PRODUCTO] ON [PRODUCTO].[PROD_ID] = [ACTIVO].[PROD_ID]"
				 		   		+ "	WHERE"
				 		   		+ "		[DETALLE_RECEPCION].[RECEP_ID] IN ("+recepciones_selec+")";
		    		 
		    		 System.out.println(querybirt);
			 		   ResultSet QueryBirt2= birtBD2.Consulta(querybirt);
			 		   ArrayList<String> prod_res = new ArrayList<String>();
			 		   
			 		   int total=0;
			 		   while(QueryBirt2.next()){
			 			  OID=QueryBirt2.getString("OC_ID");
			 			 total+= QueryBirt2.getInt("DETIRECEP_PRECIO");
				 			
			 			   prod_res.add(QueryBirt2.getString("PROD_ID")+"/"+
			 					  QueryBirt2.getString("PROD_PN_TLI_CODFAB").replace("/", " ")+"/"+
			 					  QueryBirt2.getString("PROD_DESC_CORTO").replace("/", " ")+"/"+
			 					  QueryBirt2.getInt("DETIRECEP_PRECIO")+"/"+
			 					  QueryBirt2.getInt("RECEP_ID"));
			 		   }
			 		   QueryBirt2.close();
			 		   
			 		   int neto = (int) (total/1.19);
			 		   int iva = total-neto;
			 		  request.setAttribute("NETO",neto+"");
			 			request.setAttribute("IVA",iva+"");
			 			request.setAttribute("TOTAL",total+"");
			 		 
			 		   String[] ar_productos = new String[prod_res.size()];
			 		   for(int x=0; x < prod_res.size(); x++){
			 			   ar_productos[x]=prod_res.get(x);
			 		   }
			 		   request.setAttribute("ar_productos", ar_productos);
			           
			           birtBD2.Desconectar();
		    		
		    		
		    		
		    		
		    		
		    		
		    		
		    		
	    		

	    		//DATOS DE LA TABLA FACTURA
	    		ConBirt birtBDF= new ConBirt();
	            ResultSet QueryBirtF= birtBDF.Consulta("SELECT"
	            		+ "		[dbo].[RECEPCION].[RECEP_ID],"
	            		+ "		CONVERT ("
	            		+ "			VARCHAR,"
	            		+ "			[dbo].[RECEPCION].[RECEP_FECHA],"
	            		+ "			105"
	            		+ "		) AS RECEP_FECHA,"
	            		+ "		[dbo].[RECEPCION].[RECEP_ESTADO], "
	            		+ "		[dbo].[ORDEN_COMPRA].[OC_FORMA_PAGO],"
	            		+ "		clpr_emisor.CLPR_NOMBRE_FANTASIA AS clpr_emisor_nom_fantasia,"
	            		+ "		clpr_prov.CLPR_RAZON_SOCIAL AS clpr_prov_razon_social,"
	            		+ "		clpr_prov.CLPR_RUT,"
	            		+ "		clpr_prov.CLPR_DV,"
	            		+ "		clpr_prov.CLPR_GIRO,"
	            		+ "		clpr_prov.CLPR_ID,"
	            		+ "		COMUNA.COMU_NOMBRE,"
	            		+ "		REGION.REGI_NOMBRE,"
	            		+ "		CONTACTO.CONT_NOMBRE,"
	            		+ "		CONTACTO.CONT_TELEFONO,"
	            		+ "		DIRECCION.DIRE_ID,"
	            		+ "		DIRECCION.DIRE_CIUDAD,"
	            		+ "		CONTACTO.CONT_NOMBRE,"
	            		+ "		CONTACTO.CONT_TELEFONO,"
	            		+ "		[dbo].[ORDEN_COMPRA].[OC_TIPO_CAMBIO],"
	            		+ "		[dbo].[ORDEN_COMPRA].[OC_SOLICITANTE], "
	            		+ "		USUARIO.USU_INICIAL,"
	            		+ "		CAST(ROUND([dbo].[RECEPCION].[RECEP_TOTAL_FACT],0) as INT) AS RECEP_TOTAL_FACT,"
	            		+ "		CAST(ROUND([dbo].[RECEPCION].[RECEP_TOTAL_FACT]/1.19,0)as INT) AS NETO,"
	            		+ "		CAST(([dbo].[RECEPCION].[RECEP_TOTAL_FACT]-ROUND([dbo].[RECEPCION].[RECEP_TOTAL_FACT]/1.19,0)) AS INT) AS IVA "
	            		+ "	FROM"
	            		+ "		[dbo].[RECEPCION]"
	            		+ "	INNER JOIN [dbo].[ORDEN_COMPRA] ON [dbo].[ORDEN_COMPRA].[OC_ID] = [dbo].[RECEPCION].[OC_ID]"
	            		+ "	INNER JOIN [dbo].[CLIENTEPROVEEDOR] clpr_emisor ON clpr_emisor.CLPR_ID = [dbo].[ORDEN_COMPRA].[OC_SOLICITANTE]"
	            		+ "	INNER JOIN [dbo].[CLIENTEPROVEEDOR] clpr_prov ON clpr_prov.CLPR_ID = [dbo].[ORDEN_COMPRA].[OC_PROVEEDOR]"
	            		+ "	INNER JOIN dbo.DIRECCION ON ORDEN_COMPRA.OC_DIRECCION_PROVEEDOR = dbo.DIRECCION.DIRE_ID"
	            		+ "	INNER JOIN dbo.CONTACTO ON ORDEN_COMPRA.CONT_ID = dbo.CONTACTO.CONT_ID"
	            		+ "	INNER JOIN dbo.USUARIO ON ORDEN_COMPRA.OC_USU_CREACION = dbo.USUARIO.USU_ID"
	            		+ "	INNER JOIN dbo.COMUNA ON dbo.DIRECCION.COMU_ID = dbo.COMUNA.COMU_ID"
	            		+ "	INNER JOIN dbo.REGION ON dbo.COMUNA.REGI_ID = dbo.REGION.REGI_ID"
	            		+ "	WHERE"
	            		+ "		[dbo].[ORDEN_COMPRA].[OC_ID] ='"+OID+"'");
	            QueryBirtF.next();
	            request.setAttribute("OC_ID",OID);
		 		request.setAttribute("FACT_FECHA",QueryBirtF.getString("RECEP_FECHA"));
		 		request.setAttribute("FACT_CONDICIONES",QueryBirtF.getString("OC_FORMA_PAGO"));
		 		request.setAttribute("CLPR_NOMBRE_FANTASIA",QueryBirtF.getString("clpr_emisor_nom_fantasia"));
		 		request.setAttribute("CLPR_RAZON_SOCIAL",QueryBirtF.getString("clpr_prov_razon_social"));
		 		request.setAttribute("CLPR_RUT",QueryBirtF.getString("CLPR_RUT"));
		 		request.setAttribute("CLPR_DV",QueryBirtF.getString("CLPR_DV"));
		 		request.setAttribute("CLPR_GIRO",QueryBirtF.getString("CLPR_GIRO"));
		 		//request.setAttribute("DIRE_DIRECCION",QueryBirtF.getString("DIRE_DIRECCION"));
		 		request.setAttribute("COMU_NOMBRE",QueryBirtF.getString("COMU_NOMBRE"));
		 		request.setAttribute("DIRE_CIUDAD",QueryBirtF.getString("DIRE_CIUDAD"));
		 		request.setAttribute("REGI_NOMBRE",QueryBirtF.getString("REGI_NOMBRE"));
		 		//Float cambio = QueryBirtF.getFloat("OC_TIPO_CAMBIO");
		 		//request.setAttribute("OC_TIPO_CAMBIO",cambio.intValue()+"");
		 		request.setAttribute("FACT_TIPO_CAMBIO",QueryBirtF.getString("OC_TIPO_CAMBIO"));
		 		request.setAttribute("CONT_NOMBRE",QueryBirtF.getString("CONT_NOMBRE"));
		 		request.setAttribute("CONT_TELEFONO",QueryBirtF.getString("CONT_TELEFONO"));
		 		//request.setAttribute("FACT_TOTAL_TEXTO",QueryBirtF.getString("FACT_TOTAL_TEXTO"));
		 		//request.setAttribute("FACT_OBS",QueryBirtF.getString("FACT_OBS"));
		 		request.setAttribute("FACT_ESTADO",QueryBirtF.getString("RECEP_ESTADO"));
		 		request.setAttribute("FACT_FECHA_EMISION",QueryBirtF.getString("RECEP_FECHA"));
		 		request.setAttribute("USU_INICIAL",QueryBirtF.getString("USU_INICIAL"));
		 		request.setAttribute("CLPR_ID",QueryBirtF.getString("CLPR_ID"));
		 		

		 		String clpr_id=QueryBirtF.getString("OC_SOLICITANTE");
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
	            
		 		String SQL_RES = "SELECT u.Usuarios_nombre, u.Usuarios_ape_p, u.Usuarios_ape_m "
		 				+ "FROM empresas e "
		 				+ "INNER JOIN usuarios u ON e.responsable_id = u.Usuarios_id "
		 				+ "WHERE e.empresas_id='"+clpr_id+"'"; 
	    		//System.out.println(SQL_RES);
	 		    RES_RS =  state.executeQuery(SQL_RES);
	 		    RES_RS.next();
	 		    request.setAttribute("USU_NOMBRE",RES_RS.getString("Usuarios_nombre")+" "+RES_RS.getString("Usuarios_ape_p")+" "+RES_RS.getString("Usuarios_ape_m"));
	          
	 		    state.close();
	 		   
	 		  
	           birtBDF.Desconectar();
	           
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
