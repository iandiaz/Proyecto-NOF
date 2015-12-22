
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
 * Servlet implementation class cguias_res
 */
@WebServlet("/cguias_res")
public class cguias_res extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cguias_res() {
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
		request.setAttribute("usuname", Usuarios_nombre);

			try {
				Statement state = null;
				ResultSet RS_DATOS = null;
				ResultSet RS_DATOSD = null;
				
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		String GRID = request.getParameter("d_id");
	    		String TDTE = request.getParameter("tdte");

	    		//-------------------------CABECERA GUIA RESUMEN-------------------------//
	    		String SQL_CAB = "SELECT 	g.802_folio,"
	    				+ "					g.802_id,"
	    				+ "					g.802_ciudad , "
	    				+ "					g.802_neto,"
	    				+ "					g.802_glosa_desc,"
	    				+ " 				e1.empresas_nombrenof as emisor_nof, "
	    				+ "					DATE_FORMAT(g.802_fecha, '%d-%m-%Y') as gr_fecha,"
	    				+ " 				ifnull(g.802_folio, 'ND') as gr_folio2 , "
	    				+ "					IF(g.`id_dte` is null , '0','1') as id_dte,"
	    				+ "					g.802_obs ,  "
	    				
	    				+ "					g.802_ciudad,"
	    				+ "					g.802_descuento,"
	    				+ "					g.802_iva,"
	    				+ "					g.802_subtotal,"
	    				+ "					g.802_total,"
	    				
	    				+ "					DATE_FORMAT(g.802_fecvencimiento, '%d-%m-%Y') as 802_fecvencimiento,"
	    				
	    				+ "					g.802_tipodte, "
	    				+ "					e.empresas_id,"
	    				+ "					e.empresas_razonsocial,"
	    				+ "					e.empresas_rut,"
	    				+ "					e.empresas_nombrenof, e.empresas_giro,"
	    				+ "		CONCAT_WS(' ',`usuarios`.`Usuarios_nombre`,`usuarios`.`Usuarios_ape_p`,`usuarios`.`Usuarios_ape_m`) AS Usuarios_nombre_com, "
	    		  		+ "					o.CONT_EMAIL , "
	    				+ "					o.cont_telefono,"
	    				+ "					g.cont_nombre, "
	    				+ "					c.comu_nombre,"
	    				+ "					r.regi_nombre,"
	    				+ "					d.dire_nombre, "
	    				+ "					s.estados_vig_novig_nombre  , "
	    				
						+ "		IF(`g`.`tipo_dteref` is null,'NINGUNA',`g`.`tipo_dteref`) as tipo_dteref, "
						+ "		IF(`g`.`folioref` is null ,'',`g`.`folioref`) as  folioref, "
						+ "		IF(`g`.`fec_ref` is null,'',DATE_FORMAT(`g`.`fec_ref`, '%d-%m-%Y')) as fec_ref "

	    				
	    				+ " FROM `802` g"
			    		+ " INNER JOIN empresas e ON e.empresas_id=g.cliente_id"
			    		+ " INNER JOIN empresas e1 ON e1.empresas_id=g.802_empresa_emisora"
			    		+ " INNER JOIN direccion d ON d.DIRE_ID=g.direccion_id"
			    		+ " INNER JOIN comuna c ON c.comu_id=d.comu_id"
			    		+ " INNER JOIN region r ON r.regi_id=c.regi_id"
			    		+ " INNER JOIN contacto o ON o.cont_id=g.contacto_id"
			    		+ " INNER JOIN `usuarios` ON `usuarios`.`Usuarios_id` = e.`responsable_id` "
			    		+ " INNER JOIN estados_vig_novig s ON s.estados_vig_novig_id=g.estados_vig_novig_id"
			    		+ " WHERE g.802_id = "+GRID+" "
			    		+ " ORDER BY g.802_id";
			    System.out.println(SQL_CAB);
			    ResultSet RS_CAB = state.executeQuery(SQL_CAB);
			    if(RS_CAB.next()){
			    	request.setAttribute("guiaresumen_id", GRID);
			    	request.setAttribute("empresas_id", RS_CAB.getString("empresas_id"));
			    	request.setAttribute("id_dte", RS_CAB.getString("id_dte"));
				    request.setAttribute("gr_folio", RS_CAB.getString("gr_folio2"));
				    
			    	request.setAttribute("gr_fecha", RS_CAB.getString("gr_fecha"));
				    request.setAttribute("emisor_nof", RS_CAB.getString("emisor_nof"));
				    
				    request.setAttribute("empresas_nombrenof", RS_CAB.getString("empresas_nombrenof"));
				    request.setAttribute("empresas_rut", RS_CAB.getString("empresas_rut"));
				    request.setAttribute("empresas_razonsocial", RS_CAB.getString("empresas_razonsocial"));
				    request.setAttribute("estados_vig_novig_nombre", RS_CAB.getString("estados_vig_novig_nombre"));
				    
				    request.setAttribute("direccion_nombre", RS_CAB.getString("dire_nombre"));
				    request.setAttribute("regi_nombre", RS_CAB.getString("regi_nombre"));
				    request.setAttribute("gr_ciudad", RS_CAB.getString("802_ciudad"));
				    request.setAttribute("comu_nombre", RS_CAB.getString("comu_nombre"));
				    request.setAttribute("cont_nombre", RS_CAB.getString("cont_nombre"));
				    request.setAttribute("cont_telefono", RS_CAB.getString("cont_telefono"));
				    request.setAttribute("CONT_EMAIL", RS_CAB.getString("CONT_EMAIL"));
				    request.setAttribute("gr_responsable", RS_CAB.getString("Usuarios_nombre_com"));
				    
				    request.setAttribute("gr_obs", RS_CAB.getString("802_obs"));
				    request.setAttribute("NETO",RS_CAB.getString("802_neto"));
				    request.setAttribute("subtotal",RS_CAB.getString("802_subtotal"));
				    request.setAttribute("iva",RS_CAB.getString("802_iva"));
				    request.setAttribute("total",RS_CAB.getString("802_total"));
				    
				    request.setAttribute("fac_servim_fecvencimiento",RS_CAB.getString("802_fecvencimiento").substring(0, 10));
				    request.setAttribute("DESC",RS_CAB.getString("802_descuento"));
				    request.setAttribute("fac_servim_tipodte",RS_CAB.getString("802_tipodte"));
				 
				    request.setAttribute("gr_glosa_desc",RS_CAB.getString("802_glosa_desc"));
				    
				    request.setAttribute("fec_ref", RS_CAB.getString("fec_ref"));
			    	request.setAttribute("folioref", RS_CAB.getString("folioref"));
			    	request.setAttribute("tipo_dteref", RS_CAB.getString("tipo_dteref"));
			    	request.setAttribute("empresas_giro",RS_CAB.getString("empresas_giro"));
				    
			    }
			    //-------------------------CABECERA GUIA RESUMEN-------------------------//
			    
			    //-------------------------DETALLE GUIA RESUMEN-------------------------//
	    		String SQL_DETALLE = "SELECT 802_ID,d802_cod, d802_TOTAL, DATE_FORMAT(d802_FECHA, '%d-%m-%Y') as GD_FECHA,d802_folio "
	    				+ " FROM detalle_802 "
	    				+ " WHERE 802_id = "+GRID+" AND estados_vig_novig_id = 1 ORDER BY d802_id";
			    System.out.println(SQL_DETALLE);
			    ResultSet RS_DETALLE = state.executeQuery(SQL_DETALLE);
			    ArrayList<String> gr_detalle = new ArrayList<String>();
			    while(RS_DETALLE.next()){
				    gr_detalle.add(RS_DETALLE.getString("802_ID")+"/"+RS_DETALLE.getString("d802_TOTAL")+"/"+RS_DETALLE.getString("GD_FECHA")+"/"+RS_DETALLE.getString("d802_cod")+"/"+RS_DETALLE.getString("d802_folio"));
	    	    }
			    RS_DETALLE.close();
			    state.close();
			    conexion.close();
	                
			    String[] gr_det = new String[gr_detalle.size()];
			    for(int x=0; x < gr_detalle.size(); x++){
			    	gr_det[x]=gr_detalle.get(x);
			    }
			    request.setAttribute("ar_alertas", gr_det);
			    //-------------------------DETALLE GUIA RESUMEN-------------------------//
			   
			   request.setAttribute("d_id", GRID); 
	 		   request.setAttribute("tdte", TDTE);
			}catch(Exception ex){
			    out.println("ERROR: "+ex);
			    ex.printStackTrace();
			}
		//fin grabar
			
		RequestDispatcher rd = request.getRequestDispatcher("cguias_res.jsp");
        rd.forward(request, response);
		
		}

}
