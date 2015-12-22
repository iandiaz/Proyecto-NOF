

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
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

public boolean validateSession(HttpServletRequest request, HttpServletResponse response){
		
		boolean user_in_session=false;
		boolean username_in_session=false;
		boolean perf_in_session=false;
		Cookie [] cookies=request.getCookies();
		
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName()+"-"+cookie.getValue());
		    	if(cookie.getName().equals("Usuarios_id") && cookie.getValue()!=null) user_in_session=true;
		    	if(cookie.getName().equals("Usuarios_nombre") && cookie.getValue()!=null) username_in_session=true;
		    	if(cookie.getName().equals("perfilusu_id") && cookie.getValue()!=null && !cookie.getValue().equals("")) perf_in_session=true;
		    	
		    }
		}
		
		
		if(user_in_session && username_in_session && perf_in_session) user_in_session=true;
		else user_in_session=false;
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
		    	if(cookie.getName().equals("perfilusu_id")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
		    	
		    	
		    }
		}
		
		
		return user_in_session;
		
    }



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(validateSession(request, response)){
			
			 mt(request,response);
		}
		else response.sendRedirect("/001/");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(validateSession(request, response)){
			
			 mt(request,response);
		}
		else response.sendRedirect("/001/");
	}
public void mt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
		//System.out.println("1");
		
		Cookie [] cookies=request.getCookies();
		if(request.getParameter("logout") !=  null){
			if (cookies != null) {
			    for (Cookie cookie : cookies) {
			        //work with cookies
			    	// System.out.println("cookie logout: "+cookie.getName());
			    	
			    	if(cookie.getName().equals("Usuarios_id")) {cookie.setMaxAge(0);cookie.setPath("/");response.addCookie(cookie);}
			    }
			}
				response.sendRedirect("/001/");	
				return;
		}
		String Usuarios_nombre="";
		String Usuarios_ID="";
		//fecha
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName());
		    	if(cookie.getName().equals("Usuarios_id")) { Usuarios_ID=cookie.getValue();}
		    	if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}		
		request.setAttribute("usuname", Usuarios_nombre);
		
		
		
		try {
			
			String id = request.getParameter("id");
	         
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		Statement state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
    	    
    		  String SQL_lastfolio = "select "
    		  		+ "	`adm_folios`.`admfolios_hasta` "
    		  		+ "	,`adm_folios`.`admfolios_desde`"
    		  		+ "	,`adm_folios`.`tipodte`"
    		  		+ "	,`adm_folios`.`admfolios_id`"
    		  		+ "	,`adm_folios`.`admfolios_correo_aviso`"
        		  	
    		  		
    		  		+ "	, DATE_FORMAT(`adm_folios`.`admfolios_feccreacion`, '%d-%m-%Y') as admfolios_feccreacion"
    		  		+ "	, estados_vig_novig.estados_vig_novig_nombre"
    		  		+ "	,`empresas`.`empresas_nombrenof`"
    		  		+ "	,`adm_folios`.`empresas_id` "
        			
  	 		  		+ "	from `adm_folios`"
  	 		  		+ "	inner join estados_vig_novig on estados_vig_novig.estados_vig_novig_id=adm_folios.estados_vig_novig_id  "
  	 		  		+ "	inner join `empresas` on `empresas`.`empresas_id` = `adm_folios`.`empresas_id`  "
	 		  		
  	 		  		+ "	where `adm_folios`.`admfolios_id`='"+id+"' "; 
  	 		 System.out.println(SQL_lastfolio);
  	 		    ResultSet LASTFOLIO_RS = state.executeQuery(SQL_lastfolio);
  	 		    
  	 		    
  	 		    String tipodte="";
  	 		    String emp_id="";
  	 		    String foliohasta="0";
  	 		    if(LASTFOLIO_RS.next()){
  	 		    	request.setAttribute("fhasta", LASTFOLIO_RS.getString("admfolios_hasta"));
  	 		    	request.setAttribute("fdesde", LASTFOLIO_RS.getString("admfolios_desde"));
  	 		    	request.setAttribute("fcrea", LASTFOLIO_RS.getString("admfolios_feccreacion"));
  	 		    	request.setAttribute("tipodte", LASTFOLIO_RS.getString("tipodte"));
  	 		    	request.setAttribute("estados_vig_novig_nombre", LASTFOLIO_RS.getString("estados_vig_novig_nombre"));
  	 		    	request.setAttribute("id", LASTFOLIO_RS.getString("admfolios_id"));
  	 		    	request.setAttribute("emp", LASTFOLIO_RS.getString("empresas_nombrenof"));
  	 		    	request.setAttribute("admfolios_correo_aviso", LASTFOLIO_RS.getString("admfolios_correo_aviso"));
  	 		    	
  	 		    	tipodte=LASTFOLIO_RS.getString("tipodte");
  	 		    	emp_id=LASTFOLIO_RS.getString("empresas_id");
  	 		    	foliohasta=LASTFOLIO_RS.getString("admfolios_hasta");
  	 		    }
  	 		    
  	 		    
  	 		    
  	 		  //buscamos el folio en el que vamos 
	 		    
		 		   String SQL_findfolio = "select `dte_encabezado`.`Folio` "
		 		   		+ " from `dte_encabezado` "
		 		   		+ " where `dte_encabezado`.`TipoDTE`='"+tipodte+"' and `dte_encabezado`.`empresa_emisora_id`="+emp_id
		 		   		+ " ORDER BY `dte_encabezado`.`Folio` DESC limit 1 "; 
		    		System.out.println(SQL_findfolio);
		 		    ResultSet RS_FINDFOLIO = state.executeQuery(SQL_findfolio);
		 		    
		 		    String folio="0";
		 		    if(RS_FINDFOLIO.next()){
		 		    	folio=(RS_FINDFOLIO.getInt("Folio"))+"";
		 		    }
		 		    
		 		    if(Integer.parseInt(folio)>Integer.parseInt(foliohasta) ){
		 		    	 request.setAttribute("folioactual", foliohasta);
		 		    }
		 		    else{
		 		    	 request.setAttribute("folioactual", folio);
		 		    }

		 		  
		 		    
	    		
  	 		    
  	 		   LASTFOLIO_RS.close();
  	 		
  	 		    state.close();
  	 		  
  	 		 conexion.close();
           
		}catch(Exception ex){
		    out.println("ERROR: "+ex);
		    ex.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("c2.jsp");
        rd.forward(request, response);
		
	}

}
