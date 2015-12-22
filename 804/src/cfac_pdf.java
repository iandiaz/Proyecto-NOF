

import java.io.FileOutputStream;
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
 * Servlet implementation class cfac_pdf
 */
@WebServlet("/cfac_pdf")
public class cfac_pdf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cfac_pdf() {
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

		String Usuarios_nombre="",id_usuario="";
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName());
		    	if(cookie.getName().equals("Usuarios_id") && cookie.getValue()!=null) id_usuario=cookie.getValue();
		    	if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}		
		request.setAttribute("usuname", Usuarios_nombre);	
		
		//grabar
		
			try {
				Statement state = null;
				ResultSet CAB_RS = null;
				ResultSet DET_RS = null;
				
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		String FID = request.getParameter("fact_id");
	    		
	    		String SQL_CAB = "SELECT "
	    				+ " e.empresas_nombrenof as nom, e.empresas_rut as rut, d.DIRE_DIRECCION as dir, r.REGI_NOMBRE as reg,"
	    				+ " c.COMU_NOMBRE as com, d.DIRE_CIUDAD as ciu, e.empresas_giro as giro,  "
	    				+ " o.CONT_TELEFONO as tel, g.fac_servotros_ref as ref, g.fac_servotros_obs as obs, "
	    				+ " g.fac_servotros_neto as net, g.fac_servotros_iva as iva, g.fac_servotros_totalfinal as tot, g.fac_servotros_descuento as des, "
	    				+ " DAY(now()) as dia, MONTH(now()) as mes, YEAR(now()) as ano"
	    				+ " FROM factura_servotros g "
	    				+ " INNER JOIN empresas e ON e.empresas_id = g.empresas_id "
	    				+ " INNER JOIN direccion d ON d.CLPR_ID = g.empresas_id "
	    				+ " INNER JOIN comuna c ON d.COMU_ID = c.COMU_ID"
						+ " INNER JOIN region r ON c.regi_id = r.regi_id"
						+ " INNER JOIN contacto o ON g.cont_id = o.cont_id"
	    				+ " WHERE g.fac_servotros_id ='"+FID+"'"
	    				+ " ORDER BY e.empresas_nombrenof, d.dire_direccion"; 
	    		System.out.println(SQL_CAB);
	    		CAB_RS =  state.executeQuery(SQL_CAB);
	 		    if(CAB_RS.next()){
	 		    	request.setAttribute("nom", CAB_RS.getString("nom"));
	 		    	request.setAttribute("rut", CAB_RS.getString("rut"));
	 		    	request.setAttribute("dir", CAB_RS.getString("dir"));
	 		    	request.setAttribute("reg", CAB_RS.getString("reg"));
	 		    	request.setAttribute("com", CAB_RS.getString("com"));
	 		    	request.setAttribute("ciu", CAB_RS.getString("ciu"));
	 		    	request.setAttribute("giro", CAB_RS.getString("giro"));
	 		    	request.setAttribute("tel", CAB_RS.getString("tel"));
	 		    	request.setAttribute("ref", CAB_RS.getString("ref"));
	 		    	request.setAttribute("obs", CAB_RS.getString("obs"));
	 		    	request.setAttribute("net", CAB_RS.getString("net"));
	 		    	request.setAttribute("iva", CAB_RS.getString("iva"));
	 		    	request.setAttribute("tot", CAB_RS.getString("tot"));
	 		    	request.setAttribute("des", CAB_RS.getString("des"));
			    	request.setAttribute("dia", CAB_RS.getString("dia"));
			    	request.setAttribute("mes", CAB_RS.getString("mes"));
			    	request.setAttribute("ano", CAB_RS.getString("ano"));
	 		    }
	 		    
	 	   		//--------------------- TRASLADOS ----------------------//
			    String SQL_DET = "SELECT "
			    		+ " dfso_id as cod, dfso_descripcion as des, ' ' as can, dfso_valor as pre, ' ' as des"
			    		+ " FROM detail_fac_servotros "
			    		+ " WHERE fac_servotros_id = "+FID;
			    System.out.println(SQL_DET);
			    DET_RS =  state.executeQuery(SQL_DET);
			    ArrayList<String> guias = new ArrayList<String>();
			    while(DET_RS.next()){ guias.add(DET_RS.getString("cod")+"/"+DET_RS.getString("des")
			    		+"/"+DET_RS.getString("can")+"/"+DET_RS.getString("pre")+"/"+DET_RS.getString("des")); }
			    DET_RS.close();
			    String[] ar_guias = new String[guias.size()];
			    for(int x=0; x < guias.size(); x++){ ar_guias[x]=guias.get(x);}
			    request.setAttribute("ar_guias", ar_guias);
			    //----------------------- FIN ------------------------//
			    
			}catch(Exception ex){
			    out.println("ERROR: "+ex);
			    ex.printStackTrace();
			}
		//fin grabar
			
		RequestDispatcher rd = request.getRequestDispatcher("cfac_pdf.jsp");
        rd.forward(request, response);
		
		}

}
