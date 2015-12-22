
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Constantes.Constantes;
/**
 * Servlet implementation class cguias_desn
 */
@WebServlet("/cguias_desn")
public class cguias_desn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cguias_desn() {
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
		String id_iusuario="";
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName()+"-"+cookie.getValue());
		    	if(cookie.getName().equals("Usuarios_id") && cookie.getValue()!=null) id_iusuario=cookie.getValue();
		    }
		}

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
				ResultSet CLIENTE_RS = null;
				ResultSet GUIAS_RS = null;
				
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		String GID = request.getParameter("d_id");
	    		String TDTE = request.getParameter("tdte");

	    		String SQL_Cliente = "SELECT "
	    				+ " g.id_dte, DATE_FORMAT(823_fecha, '%d-%m-%Y') as fecha, "
	    				+ " e1.empresas_nombrenof as emisor, IF(id_dte is null, 'NO ENVIADA', 'ENVIADA') as dte, "
	    				
	    				+ " e2.empresas_razonsocial as nom1, e2.empresas_rut as rut1, e2.empresas_id as id1,  "
	    				+ " IF(d.dire_direccion ='','SD',d.DIRE_DIRECCION) as dir1,"
	    				+ " r.REGI_NOMBRE as reg1, c.COMU_NOMBRE as com1, d.DIRE_CIUDAD as cui1, "
	    				+ " CONCAT_WS(' ',o.CONT_NOMBRE,o.CONT_APEP,o.CONT_APEM) as CONT_NOMBRE, "
	    				+ " o.CONT_TELEFONO, o.CONT_EMAIL, o.CONT_TELEFONOC,"
	    				
						+ " e.empresas_razonsocial as nom2, e.empresas_rut as rut2, e.empresas_id as id2, e.empresas_giro as empresas_giro2 , "
						+ " IF(d1.dire_direccion ='','SD',d1.DIRE_DIRECCION) as dir2,"
						+ " r1.REGI_NOMBRE as reg2, c1.COMU_NOMBRE as com2, d1.DIRE_CIUDAD as cui2, "
						
	    				+ " g.823_condiciones,g.823_numticket, "
	    				+ " 823_subtotal,823_neto,823_iva,823_total,823_descuento,823_glosa_descuento,  "
			    		
	    				+ " g.823_obs,"
	    				+ "	g.823_folio, "
	    				+ " 823_obs2 , "
	    				+ "		CONCAT_WS(' ',`usuarios`.`Usuarios_nombre`,`usuarios`.`Usuarios_ape_p`,`usuarios`.`Usuarios_ape_m`) AS Usuarios_nombre	,"
	    			
	    				
	    				+ "		IF(`g`.`tipo_dteref` is null,'NINGUNA',`g`.`tipo_dteref`) as tipo_dteref, "
						+ "		IF(`g`.`folioref` is null ,'',`g`.`folioref`) as  folioref, "
						+ "		IF(`g`.`fec_ref` is null,'',DATE_FORMAT(`g`.`fec_ref`,'%d-%m-%Y')) as fec_ref,  "
					
						+ "		g.823_afecta as g_afecta "

						+ " FROM `823` g "
	    				+ " INNER JOIN empresas e ON e.empresas_id = g.clientes_id "
	    				+ " INNER JOIN empresas e1 ON e1.empresas_id = g.823_empresa_emisora "
	    				+ " INNER JOIN direccion d ON d.dire_id = g.dire_id "
	    				+ " INNER JOIN empresas e2 ON e2.empresas_id = d.CLPR_ID "
	    				+ " INNER JOIN comuna c ON d.COMU_ID = c.COMU_ID"
	    				+ " INNER JOIN region r ON c.regi_id = r.regi_id"
	    				+ " INNER JOIN contacto o ON g.cont_id = o.cont_id"
	    				
						+ " INNER JOIN direccion d1 ON d1.dire_id = g.dire_id2 "
						+ " INNER JOIN empresas e3 ON e3.empresas_id = d1.CLPR_ID "
						+ " INNER JOIN comuna c1 ON d1.COMU_ID = c1.COMU_ID"
						+ " INNER JOIN region r1 ON c1.regi_id = r1.regi_id"
						+ "	INNER JOIN `usuarios` ON `usuarios`.`Usuarios_id` = e.`responsable_id` "
	    				
	    				//+ " INNER JOIN personal p ON o.pers_id = p.PERS_ID"
	    				+ " WHERE g.823_id ='"+GID+"'"
	    				+ " GROUP BY g.823_id"
	    				+ " ORDER BY e.empresas_nombrenof, d.dire_direccion"; 
	    		//System.out.println(SQL_Cliente);
	 		    CLIENTE_RS =  state.executeQuery(SQL_Cliente);
	 		    if(CLIENTE_RS.next()){
	 		    	request.setAttribute("823_id", GID);
	 		    	request.setAttribute("id_dte", CLIENTE_RS.getString("id_dte"));
	 		    	request.setAttribute("dte", CLIENTE_RS.getString("dte"));
	 		    	request.setAttribute("fecha", CLIENTE_RS.getString("fecha"));
	 		    	request.setAttribute("emisor", CLIENTE_RS.getString("emisor"));
	 		    	request.setAttribute("nom1", CLIENTE_RS.getString("nom1"));
			    	request.setAttribute("rut1", CLIENTE_RS.getString("rut1"));
			    	request.setAttribute("id1", CLIENTE_RS.getString("id1"));
			    	request.setAttribute("dir1", CLIENTE_RS.getString("dir1"));
			    	request.setAttribute("reg1", CLIENTE_RS.getString("reg1"));
			    	request.setAttribute("com1", CLIENTE_RS.getString("com1"));
			    	request.setAttribute("cui1", CLIENTE_RS.getString("cui1"));
			    	request.setAttribute("nom2", CLIENTE_RS.getString("nom2"));
			    	request.setAttribute("rut2", CLIENTE_RS.getString("rut2"));
			    	request.setAttribute("id2", CLIENTE_RS.getString("id2"));
			    	request.setAttribute("dir2", CLIENTE_RS.getString("dir2"));
			    	request.setAttribute("reg2", CLIENTE_RS.getString("reg2"));
			    	request.setAttribute("com2", CLIENTE_RS.getString("com2"));
			    	request.setAttribute("cui2", CLIENTE_RS.getString("cui2"));
			    	request.setAttribute("CONT_NOMBRE", CLIENTE_RS.getString("CONT_NOMBRE"));
			    	request.setAttribute("CONT_TELEFONO", CLIENTE_RS.getString("CONT_TELEFONO"));
			    	request.setAttribute("CONT_TELEFONOC", CLIENTE_RS.getString("CONT_TELEFONOC"));
			    	request.setAttribute("PERS_NOMBRE", " ");
			    	request.setAttribute("CONT_EMAIL", CLIENTE_RS.getString("CONT_EMAIL"));
	 		    	request.setAttribute("condicion", CLIENTE_RS.getString("823_condiciones"));
	 		    	request.setAttribute("folio", CLIENTE_RS.getString("823_folio"));
	 		    	
			    	request.setAttribute("obs", CLIENTE_RS.getString("823_obs"));
			    	request.setAttribute("obs2", CLIENTE_RS.getString("823_obs2"));
			    	request.setAttribute("RESPONSABLE", CLIENTE_RS.getString("Usuarios_nombre"));
			    	request.setAttribute("numticket",CLIENTE_RS.getString("823_numticket"));
			    	
			    	String SUBTOTAL=CLIENTE_RS.getString("823_subtotal");
			    	String desc=CLIENTE_RS.getString("823_descuento");
			    	String NETO=CLIENTE_RS.getString("823_neto");
			    	String IVA=CLIENTE_RS.getString("823_iva");
			    	String TOTAL=CLIENTE_RS.getString("823_total");
			    	String glosa_desc=CLIENTE_RS.getString("823_glosa_descuento");
			    	
			    	request.setAttribute("g_afecta",CLIENTE_RS.getString("g_afecta"));
			    	
	            	 request.setAttribute("subtotal", SUBTOTAL);
	    	 		 request.setAttribute("descuento", desc);
	    	 		request.setAttribute("neto", NETO);
	    	 		request.setAttribute("iva", IVA);
	    	 		request.setAttribute("total", TOTAL);
	    	 		request.setAttribute("glosadescuento", glosa_desc);
	    	 		
	    	 		request.setAttribute("fec_ref", CLIENTE_RS.getString("fec_ref"));
	    	    	request.setAttribute("folioref", CLIENTE_RS.getString("folioref"));
	    	    	String tipo_dteref=CLIENTE_RS.getString("tipo_dteref").equals("") ? "NINGUNA": CLIENTE_RS.getString("tipo_dteref");
	    	    	request.setAttribute("tipo_dteref", tipo_dteref);
	    	    	request.setAttribute("empresas_giro",CLIENTE_RS.getString("empresas_giro2"));
	    	    	
	    	    	
	    	    
	 		    }
	 		    
	 	   		//--------------------- TRASLADOS ----------------------//
			    String SQL_GUIAS = "SELECT "
			    		+ " d.ALT_ID, d.PROD_PN_TLI_CODFAB,d.d823_valor, d.PROD_DESC_CORTO, d.PROD_COD_BAR_FAB, d.ALT_SERIE, d.UBI_DESCRIPCION, "
			    		+ " DATE_FORMAT(d.TRAS_FECHA, '%d-%m-%Y') as fecha"
			    		+ " FROM detalle_823 d "
			    		+ " WHERE d.823_id = "+GID;
			    System.out.println(SQL_GUIAS);
			    GUIAS_RS =  state.executeQuery(SQL_GUIAS);
			    ArrayList<String> guias = new ArrayList<String>();
			    while(GUIAS_RS.next()){ guias.add(GUIAS_RS.getString("ALT_ID")+"/"+GUIAS_RS.getString("PROD_PN_TLI_CODFAB")
			    		+"/"+GUIAS_RS.getString("PROD_DESC_CORTO")+"/"+GUIAS_RS.getString("PROD_COD_BAR_FAB")+"/"+GUIAS_RS.getString("ALT_SERIE")
			    		+"/"+GUIAS_RS.getString("UBI_DESCRIPCION")+"/"+GUIAS_RS.getString("fecha")+"/"+GUIAS_RS.getString("d823_valor")); }
			    GUIAS_RS.close();
			    String[] ar_guias = new String[guias.size()];
			    for(int x=0; x < guias.size(); x++){ ar_guias[x]=guias.get(x);}
			    request.setAttribute("ar_guias", ar_guias);
			    //----------------------- FIN ------------------------//
			    state.close();
			    conexion.close();
			    //-------------------------DETALLE GUIA RESUMEN-------------------------//
			   
			   request.setAttribute("d_id", GID); 
	 		   request.setAttribute("tdte", TDTE);
			}catch(Exception ex){
			    out.println("ERROR: "+ex);
			    ex.printStackTrace();
			}
		//fin grabar
			
		RequestDispatcher rd = request.getRequestDispatcher("cguias_desn.jsp");
        rd.forward(request, response);
		
		}

}
