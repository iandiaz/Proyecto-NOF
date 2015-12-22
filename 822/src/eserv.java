

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
 * Servlet implementation class eserv
 */
@WebServlet("/eserv")
public class eserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public eserv() {
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

		String Usuarios_nombre="",id_iusuario="";
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
		Statement state = null;
		Statement statecor = null;		
		ResultSet ESTADOS_RS = null;
		ResultSet EMPRESAS_RS = null;
		ResultSet CLIENTE_RS = null;
		ResultSet CONTACTOS_RS = null;
		ResultSet DIRECCION_RS = null;
		ResultSet CORRELATIVO_RS = null;
		ResultSet CAB_RS = null;
		ResultSet DET_RS = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		statecor = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
    		if(request.getParameter("delete_id") !=  null){
    			state = (Statement) ((java.sql.Connection) conexion).createStatement();
 				String UPDATE = "UPDATE `822` SET estados_vig_novig_id=2,822_fecmod=now(),822_modificador="+id_iusuario+" WHERE 822_id="+request.getParameter("delete_id");
 				System.out.println(UPDATE);
 				state.executeUpdate(UPDATE);
 				
 				 //insertamos logs
	    		String log_sql=""
	    				+ " INSERT INTO `log` ("
	    				+ "	`log`.`log_fec`, "
	    				+ "	`log`.`log_nombre`, " 
	    				+ "	`log`.`Usuarios_id` "
	    				+ " ) "
	    				+ " VALUES "
	    				+ " (now(), 'USUARIO ESTABLECE COMO NO VIGENTE REGISTRO "+request.getParameter("delete_id")+" DE LA TABLA 822', '"+id_iusuario+"') ";
	    		state.executeUpdate(log_sql);
 				
 				RequestDispatcher redir = request.getRequestDispatcher("menuserv?Exito=OK");
 				redir.forward(request, response); 		        
 			}else{
    		
    		String GID=request.getParameter("gd_id");
    		  String SQL_CAB = "SELECT DATE_FORMAT(822_FECHA, '%d-%m-%Y') as gd_fecha,"
		    			+ " g.822_FOLIO,			e2.empresas_nombre as emi,	v.estados_vig_novig_nombre,	e.empresas_nombre,	e.empresas_rut,"
		    			+ " e.empresas_id,		e.empresas_razonsocial,		d.DIRE_DIRECCION, 			r.REGI_NOMBRE,		d.DIRE_CIUDAD AS 822_CIUDAD,"
		    			+ " g.CONT_NOMBRE, 		c.CONT_TELEFONO, 			g.CONT_EMAIL, g.CONT_TELEFONOC,					g.822_responsable_name AS 822_RESPONSABLE, 		 	"
		    			+ " g.822_obs1,  		g.822_obs2, 				o.COMU_NOMBRE , "
		    			
		    			+ " CONCAT(u.Usuarios_nombre,' ',u.Usuarios_ape_p) AS perfilusu_creador,"
			    		+ "	DATE_FORMAT(g.822_feccreacion,'%d-%m-%Y %H:%i:%s') AS perfilusu_feccreacion,"
			    		+ "	IF (g.822_fecmod IS NULL,' ',DATE_FORMAT(g.822_fecmod,'%d-%m-%Y %H:%i:%s')) AS perfilusu_fecmod,"
			    		+ "	IF (g.822_modificador IS NULL,' ',CONCAT(u2.Usuarios_nombre,' ',u2.Usuarios_ape_p)) AS perfilusu_modificador, "
			    		+ " IF(g.id_dte is null,'1','0') as dte, "
			    		+ " 822_subtotal,822_neto,822_iva,822_total,822_descuento,822_glosa_descuento,  "
			    		+ "	g.tipo_guia_serv,  "
			    		+ "		CONCAT_WS(' ',u3.`Usuarios_nombre`,u3.`Usuarios_ape_p`,u3.`Usuarios_ape_m`) AS Usuarios_nombre_com ,"
			    		
			    		+ "		IF(`g`.`tipo_dteref` is null,'NINGUNA',`g`.`tipo_dteref`) as tipo_dteref, "
						+ "		IF(`g`.`folioref` is null ,'',`g`.`folioref`) as  folioref, "
						+ "		IF(`g`.`fec_ref` is null,'',DATE_FORMAT(`g`.`fec_ref`,'%d-%m-%Y')) as fec_ref , "
						+ "		g.822_afecta as g_afecta, "
						+ "		e.empresas_giro  "
		    			
		    			+ " FROM `822` g"
		    			+ " LEFT JOIN empresas e ON g.CLPR_ID = e.empresas_id"
		    			+ " LEFT JOIN empresas e2 ON g.822_EMPRESA_EMISORA = e2.empresas_id "
		    			+ " LEFT JOIN direccion d ON g.DIRE_ID = d.DIRE_ID"
		    			+ " LEFT JOIN comuna o ON d.COMU_ID = o.COMU_ID"
		    			+ " LEFT JOIN region r ON o.REGI_ID = r.REGI_ID"
	 	    			+ " LEFT JOIN contacto c ON g.CONT_ID = c.CONT_ID "
	 	    			+ " INNER JOIN estados_vig_novig v ON g.estados_vig_novig_id = v.estados_vig_novig_id "
	 	    			
						+ " INNER JOIN usuarios u ON g.822_creador = u.Usuarios_id "
						+ " LEFT JOIN usuarios u2 ON g.822_modificador = u2.Usuarios_id "
						+ " INNER JOIN `usuarios` u3 ON u3.`Usuarios_id` = e.`responsable_id` "
				    	

	 	    			+ " WHERE 822_id = "+GID;

		    System.out.println(SQL_CAB);
		    CAB_RS =  state.executeQuery(SQL_CAB);
		    if(CAB_RS.next()){
		    	request.setAttribute("822_id", GID);
		    	request.setAttribute("gd_fecha", CAB_RS.getString("gd_fecha"));
		    	request.setAttribute("GD_FOLIO", CAB_RS.getString("822_FOLIO"));
		    	request.setAttribute("emi", CAB_RS.getString("emi"));
		    	request.setAttribute("estados_vig_novig_nombre", CAB_RS.getString("estados_vig_novig_nombre"));
		    	request.setAttribute("empresas_nombre", CAB_RS.getString("empresas_nombre"));
		    	request.setAttribute("empresas_rut", CAB_RS.getString("empresas_rut"));
		    	request.setAttribute("empresas_id", CAB_RS.getString("empresas_id"));
		    	request.setAttribute("empresas_razonsocial", CAB_RS.getString("empresas_razonsocial"));
		    	request.setAttribute("DIRE_DIRECCION", CAB_RS.getString("DIRE_DIRECCION"));
		    	request.setAttribute("REGI_NOMBRE", CAB_RS.getString("REGI_NOMBRE"));
		    	request.setAttribute("GD_CIUDAD", CAB_RS.getString("822_CIUDAD"));
		    	request.setAttribute("CONT_NOMBRE", CAB_RS.getString("CONT_NOMBRE"));
		    	request.setAttribute("CONT_TELEFONO", CAB_RS.getString("CONT_TELEFONO"));
		    	
		    	request.setAttribute("CONT_EMAIL", CAB_RS.getString("CONT_EMAIL"));
		    	request.setAttribute("CONT_TELEFONOC", CAB_RS.getString("CONT_TELEFONOC"));
		    	
		    	request.setAttribute("g_afecta",CAB_RS.getString("g_afecta"));
		    	request.setAttribute("GD_RESPONSBALE", CAB_RS.getString("822_RESPONSABLE"));
		    	
		    	
		    	request.setAttribute("guia_obs1", CAB_RS.getString("822_obs1"));
		    	request.setAttribute("guia_obs2", CAB_RS.getString("822_obs2"));
		    	
		    	request.setAttribute("fec_crea", CAB_RS.getString("perfilusu_feccreacion"));
		    	request.setAttribute("fec_mod", CAB_RS.getString("perfilusu_fecmod"));
		    	request.setAttribute("user_mod", CAB_RS.getString("perfilusu_modificador"));
		    	request.setAttribute("user_crea", CAB_RS.getString("perfilusu_creador"));
		    	String estado_sii = "NO ENVIADA";
		    	if(CAB_RS.getString("dte").equals("0")){estado_sii = "ENVIADA";}
				if(CAB_RS.getString("dte").equals("1")){estado_sii = "PARA ENVIAR";}
		    	request.setAttribute("estado_sii", estado_sii);
		    	request.setAttribute("PERS_NOMBRE", CAB_RS.getString("Usuarios_nombre_com"));
		    	request.setAttribute("GD_COMUNA", CAB_RS.getString("COMU_NOMBRE"));
		    	request.setAttribute("tipo_guia_serv", CAB_RS.getString("tipo_guia_serv"));
		    	
		    	String SUBTOTAL=CAB_RS.getString("822_subtotal");
		    	String desc=CAB_RS.getString("822_descuento");
		    	String NETO=CAB_RS.getString("822_neto");
		    	String IVA=CAB_RS.getString("822_iva");
		    	String TOTAL=CAB_RS.getString("822_total");
		    	String glosa_desc=CAB_RS.getString("822_glosa_descuento");
		    	request.setAttribute("empresas_giro",CAB_RS.getString("empresas_giro"));
		    	
            	 request.setAttribute("subtotal", SUBTOTAL);
    	 		 request.setAttribute("descuento", desc);
    	 		request.setAttribute("neto", NETO);
    	 		request.setAttribute("iva", IVA);
    	 		request.setAttribute("total", TOTAL);
    	 		request.setAttribute("glosadescuento", glosa_desc);
    	 		
    	 		
    	 		request.setAttribute("fec_ref", CAB_RS.getString("fec_ref"));
    	    	request.setAttribute("folioref", CAB_RS.getString("folioref"));
    	    	String tipo_dteref=CAB_RS.getString("tipo_dteref").equals("") ? "NINGUNA": CAB_RS.getString("tipo_dteref");
    	    	request.setAttribute("tipo_dteref", tipo_dteref);
    	    	
		    	
		    	
		    }
		    CAB_RS.close();
		    
		    String SQL_DET = "SELECT * FROM detalle_822 WHERE 822_id = "+GID+" ORDER BY d822_id ";
		    DET_RS =  state.executeQuery(SQL_DET);
		    ArrayList<String> prod = new ArrayList<String>();
		    Integer c=0;
		    while(DET_RS.next()){ prod.add(c+"/"+DET_RS.getString("d822_text")+"/"+DET_RS.getString("d822_valor")); c=c+1;}
		    DET_RS.close();
		    String[] ar_prod = new String[prod.size()];
		    for(int x=0; x < prod.size(); x++){ ar_prod[x]=prod.get(x); }
		     request.setAttribute("ar_prod", ar_prod);
		   
		    //--------------------- ESTADOS ----------------------//
		    String SQL_ESTADOS = "SELECT * FROM estados_vig_novig";
		    ESTADOS_RS =  state.executeQuery(SQL_ESTADOS);
		    ArrayList<String> estados = new ArrayList<String>();
		    while(ESTADOS_RS.next()){ estados.add(ESTADOS_RS.getString("estados_vig_novig_id")+"/"+ESTADOS_RS.getString("estados_vig_novig_nombre")); }
		    ESTADOS_RS.close();
		    String[] ar_estados = new String[estados.size()];
		    for(int x=0; x < estados.size(); x++){ ar_estados[x]=estados.get(x); }
		    request.setAttribute("ar_estados", ar_estados);
		    //----------------------- FIN ------------------------//
		    
		    statecor.close();
            conexion.close();
            
            RequestDispatcher rd = request.getRequestDispatcher("eserv.jsp");
            rd.forward(request, response);
 			}
		}catch(Exception ex){
		    out.println("ERROR "+ex);
		    ex.printStackTrace();
		}

	}

}