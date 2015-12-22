

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
 * Servlet implementation class cfac
 */
@WebServlet("/cfac")
public class cfac extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cfac() {
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
		
			try {
				Statement state = null;
				ResultSet GUIAS_RS = null;
				ResultSet FAC_RS = null;
				
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		String GID = request.getParameter("gv_id");

	    		
	    		
	    		   
	            String SUBTOTAL="";
		        String NETO="";
	            String IVA="";
	            String TOTAL="";
	            String desc="";
	            String glosa_desc="";
	            
	            String  g_afecta="1";
	            
	            String guia_obs1="";
	            
	            String guia_obs2="";
	            String guia_tipodte="52";
	            String GV_FECHA;
				
	    		//DATOS DE LA TABLA FACTURA
	    		ConBirt birtBDF= new ConBirt();
	            ResultSet QueryBirtF= birtBDF.Consulta("SELECT  "
	            		+ " G.GV_ID,"
	            		+ " CONVERT(varchar, G.GV_FECHA, 105) AS GV_FECHA,"
	            		+ " C2.CLPR_NOMBRE_FANTASIA as EMISORA, "
	            		+ " C.CLPR_RAZON_SOCIAL, C.CLPR_RUT, C.CLPR_DV,C.CLPR_GIRO as empresas_giro,"
	            		+ " D.DIRE_DIRECCION, C1.CONT_TELEFONO,"
	            		+ " R.REGI_NOMBRE, COMUNA.COMU_NOMBRE,"
			    		+ " G.GV_CIUDAD, C1.CONT_NOMBRE,"
			    		+ " P.PERS_NOMBRE, "
			    		+ " G.GV_TRANSPORTE, "
	            		+ " G.GV_OBS,"
	            		+ " G.GV_ESTADO, "
	            		+ " CONVERT(varchar, G.GV_FECHA_EMISION, 105) AS GV_FECHA_EMISION,"
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
	            		+ " WHERE G.GV_ID ='"+GID+"'");
	            if(QueryBirtF.next()){
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
		 		request.setAttribute("empresas_giro",QueryBirtF.getString("empresas_giro"));
		 		request.setAttribute("estadovignovig","1");
	          
	            }
	            else{
	            	
	            	String SQL_FINDID = "SELECT 821_folio_birt FROM `821` WHERE 821_id='"+GID+"'"; 
		    		System.out.println(SQL_FINDID);
		 		    ResultSet FINDID_RS = state.executeQuery(SQL_FINDID);
		            if(FINDID_RS.next()){
		            	GID=FINDID_RS.getString("821_folio_birt");
		            	
		            }
	            	
	            	//buscamos primero cual es el idbirt q le corresponde
	            	String querrbirt="SELECT  "
		            		+ " G.GV_ID,"
		            		+ " CONVERT(varchar, G.GV_FECHA, 105) AS GV_FECHA,"
		            		+ " C2.CLPR_NOMBRE_FANTASIA as EMISORA, "
		            		+ " C.CLPR_RAZON_SOCIAL, C.CLPR_RUT, C.CLPR_DV,C.CLPR_GIRO as empresas_giro,"
		            		+ " D.DIRE_DIRECCION, C1.CONT_TELEFONO,"
		            		+ " R.REGI_NOMBRE, COMUNA.COMU_NOMBRE,"
				    		+ " G.GV_CIUDAD, C1.CONT_NOMBRE,"
				    		+ " P.PERS_NOMBRE, "
				    		+ " G.GV_TRANSPORTE, "
		            		+ " G.GV_OBS,"
		            		+ " G.GV_ESTADO, "
		            		+ " CONVERT(varchar, G.GV_FECHA_EMISION, 105) AS GV_FECHA_EMISION,"
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
	            	System.out.println(querrbirt);
	            	ResultSet QueryBirtFF= birtBDF.Consulta(querrbirt);
	            	QueryBirtFF.next();
	            	request.setAttribute("GV_ID",GID);
			 		request.setAttribute("GV_FECHA",QueryBirtFF.getString("GV_FECHA"));
			 		request.setAttribute("EMISORA",QueryBirtFF.getString("EMISORA"));
			 		request.setAttribute("CLPR_RAZON_SOCIAL",QueryBirtFF.getString("CLPR_RAZON_SOCIAL"));
			 		request.setAttribute("CLPR_RUT",QueryBirtFF.getString("CLPR_RUT"));
			 		request.setAttribute("CLPR_DV",QueryBirtFF.getString("CLPR_DV"));
			 		request.setAttribute("DIRE_DIRECCION",QueryBirtFF.getString("DIRE_DIRECCION"));
			 		request.setAttribute("CONT_TELEFONO",QueryBirtFF.getString("CONT_TELEFONO"));
			 		request.setAttribute("REGI_NOMBRE",QueryBirtFF.getString("REGI_NOMBRE"));
			 		request.setAttribute("COMU_NOMBRE",QueryBirtFF.getString("COMU_NOMBRE"));
			 		request.setAttribute("GV_CIUDAD",QueryBirtFF.getString("GV_CIUDAD"));
			 		request.setAttribute("CONT_NOMBRE",QueryBirtFF.getString("CONT_NOMBRE"));
			 		request.setAttribute("PERS_NOMBRE",QueryBirtFF.getString("PERS_NOMBRE"));
			 		request.setAttribute("GV_TRANSPORTE",QueryBirtFF.getString("GV_TRANSPORTE"));
			 		request.setAttribute("GV_OBS",QueryBirtFF.getString("GV_OBS"));
			 		request.setAttribute("GV_ESTADO",QueryBirtFF.getString("GV_ESTADO"));
			 		request.setAttribute("GV_FECHA_EMISION",QueryBirtFF.getString("GV_FECHA_EMISION"));
			 		request.setAttribute("USU_INICIAL",QueryBirtFF.getString("USU_INICIAL"));
			 		request.setAttribute("empresas_giro",QueryBirtFF.getString("empresas_giro"));
			 		request.setAttribute("estadovignovig","1");
			 		QueryBirtFF.close();
	            	
	            }
	            QueryBirtF.close();
	    		String SQL_Guias = "SELECT f.estados_vig_novig_id,IF(f.id_dte is null,'1','0') as dte,"
	    				+ "		IF(`f`.`tipo_dteref` is null,'NINGUNA',`f`.`tipo_dteref`) as tipo_dteref, "
						+ "		IF(`f`.`folioref` is null ,'',`f`.`folioref`) as  folioref, "
						+ "		IF(`f`.`fec_ref` is null,'',DATE_FORMAT(`f`.`fec_ref`,'%d-%m-%Y')) as fec_ref "
			
	    				+ "  FROM `821` f WHERE 821_folio_birt='"+GID+"'"; 
	    		System.out.println(SQL_Guias);
	 		    GUIAS_RS =  state.executeQuery(SQL_Guias);
			    if(GUIAS_RS.next()){ 
			    	if(GUIAS_RS.getInt("dte")==0){request.setAttribute("id_guia_estado","ENVIADA");}
			    	if(GUIAS_RS.getInt("dte")==1){request.setAttribute("id_guia_estado","PARA ENVIAR");}
			    	
			    	request.setAttribute("fec_ref", GUIAS_RS.getString("fec_ref"));
			    	request.setAttribute("folioref", GUIAS_RS.getString("folioref"));
			    	String tipo_dteref=GUIAS_RS.getString("tipo_dteref").equals("") ? "NINGUNA": GUIAS_RS.getString("tipo_dteref");
			    	request.setAttribute("tipo_dteref", tipo_dteref);
			    	request.setAttribute("estadovignovig",GUIAS_RS.getString("estados_vig_novig_id"));
			    	
			    }else{
			    	request.setAttribute("id_guia_estado","NO ENVIADA");
			    	
			    	request.setAttribute("fec_ref", "");
			    	request.setAttribute("folioref", "");
			    	String tipo_dteref="NINGUNA";
			    	request.setAttribute("tipo_dteref", tipo_dteref);
			    	
			    }
	 		    GUIAS_RS.close();
	 		   String folio="ND";
	 		 //SI SE PASO A NOF
	            String SQL_FAC = "SELECT *,date_format(821_fecha,'%d-%m-%Y') as grfecha FROM `821` WHERE 821_folio_birt='"+GID+"'"; 
	    		System.out.println(SQL_FAC);
	 		    FAC_RS =  state.executeQuery(SQL_FAC);
	            if(FAC_RS.next()){
	            	guia_obs1=FAC_RS.getString("821_obs1");
	            	guia_obs2=FAC_RS.getString("821_obs2");
	            	guia_tipodte=FAC_RS.getString("821_tipodte");
	            	request.setAttribute("GV_FECHA",FAC_RS.getString("grfecha"));
	            	
	            	folio =FAC_RS.getString("821_folio");
	            	
	            	if(folio==null || folio.equals("null") || folio.equals("NULL") || folio.equals("")) folio="ND";
	            	SUBTOTAL=FAC_RS.getString("821_subtotal");
	            	desc=FAC_RS.getString("821_descuento");
	            	NETO=FAC_RS.getString("821_neto");
	            	IVA=FAC_RS.getString("821_iva");
	            	TOTAL=FAC_RS.getString("821_total");
	            	glosa_desc=FAC_RS.getString("821_glosa_descuento");
	            			
	            	g_afecta=FAC_RS.getString("821_afecta");
	            	
	            }
	            FAC_RS.close();
	            state.close();
	            request.setAttribute("folio",folio);
	 		   ConBirt birtBD2= new ConBirt();
	 		   ResultSet QueryBirt2= birtBD2.Consulta("SELECT "
		 		   		+ " v.VTA_ID, v.ALT_ID, p.PROD_PN_TLI_CODFAB, p.PROD_DESC_CORTO, a.ALT_SERIE, d.DETIGV_PRECIO_NETO,v.UBI_ID,ven.VTA_TICK_ID "
		 		   		+ " FROM DETALLE_GUIA_VENTA d"
		 		   		+ " LEFT JOIN DETALLE_VENTA v ON d.ALT_ID = v.DETIVTA_ID"
		 		   		+ " LEFT JOIN VENTA ven ON v.VTA_ID = ven.VTA_ID"
		 		   		+ " LEFT JOIN ACTIVO a ON v.ALT_ID = a.ALT_ID"
		 		   		+ " LEFT JOIN PRODUCTO p ON p.PROD_ID = a.PROD_ID"
		 		   		+ " WHERE d.GV_ID = '"+GID+"'"); 
	 		   ArrayList<String> prod_res = new ArrayList<String>();
	 		   while(QueryBirt2.next()){
	 			   prod_res.add(QueryBirt2.getString("ALT_ID")+"/"+
	 					  QueryBirt2.getString("PROD_PN_TLI_CODFAB").replace("/", " ")+"/"+
	 					  QueryBirt2.getString("PROD_DESC_CORTO").replace("/", " ")+"/"+
	 					  QueryBirt2.getString("ALT_SERIE").replace("/", " ")+"/"+
	 					  QueryBirt2.getInt("DETIGV_PRECIO_NETO")+"/"+
	 					  QueryBirt2.getInt("UBI_ID")+"/"+
	 					  QueryBirt2.getString("VTA_TICK_ID").replace("/", " ")
					   );
	 		   }
	 		   QueryBirt2.close();
	 		 
	 		   String[] ar_productos = new String[prod_res.size()];
	 		   for(int x=0; x < prod_res.size(); x++){
	 			   ar_productos[x]=prod_res.get(x);
	 		   }
	 		   request.setAttribute("ar_productos", ar_productos);
	           
	 		  request.setAttribute("subtotal", SUBTOTAL);
	 		 request.setAttribute("descuento", desc);
	 		request.setAttribute("neto", NETO);
	 		request.setAttribute("iva", IVA);
	 		request.setAttribute("total", TOTAL);
	 		request.setAttribute("glosadescuento", glosa_desc);
	           
	 		request.setAttribute("g_afecta",g_afecta);
	 		   
	 		request.setAttribute("guia_obs1",guia_obs1);
        	request.setAttribute("guia_obs2",guia_obs2);
        	request.setAttribute("guia_tipodte",guia_tipodte);
        	
	 		
	           birtBD2.Desconectar();
	           birtBDF.Desconectar();
	           conexion.close();
			}catch(Exception ex){
			    out.println("ERROR: "+ex);
			    ex.printStackTrace();
			}
		//fin grabar
			
		RequestDispatcher rd = request.getRequestDispatcher("cfac.jsp");
        rd.forward(request, response);
		
	}

}
