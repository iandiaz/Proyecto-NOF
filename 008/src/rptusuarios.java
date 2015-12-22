

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
 * Servlet implementation class rptperusu
 */
@WebServlet("/rptusuarios")
public class rptusuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public rptusuarios() {
        super();
        // TODO Auto-generated constructor stub
    }
public boolean validateSession(HttpServletRequest request, HttpServletResponse response){
		
		boolean user_in_session=false;
		boolean username_in_session=false;
		Cookie [] cookies=request.getCookies();
		
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 System.out.println("cookie: "+cookie.getName()+"-"+cookie.getValue());
		    	if(cookie.getName().equals("Usuarios_id") && cookie.getValue()!=null) user_in_session=true;
		    	if(cookie.getName().equals("Usuarios_nombre") && cookie.getValue()!=null) username_in_session=true;
		    }
		}
		
		
		if(user_in_session && username_in_session) user_in_session=true;
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
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				boolean sesion_valida=validateSession(request,response);
				
				if(!sesion_valida) response.sendRedirect("/001/");
				else {
					 mt(request,response);
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("grabar_excel") !=  null){
			PrintWriter out = response.getWriter();
			String filtros="a=1";
			
			if(request.getParameter("select_usuario") !=  null && !request.getParameter("select_usuario").equals("") ){
				filtros+="&id_u="+request.getParameter("select_usuario")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
			if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}


			
			//out.write("<script>window.open(\"www.google.com\");</script>");
			
			out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_usuarios_xls.php?"+filtros+"';setInterval(\"close()\",10000);</script>");
		}
		
		if(request.getParameter("grabar_pdf") !=  null){
			PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_usuario") !=  null && !request.getParameter("select_usuario").equals("") ){
				filtros+="&id_u="+request.getParameter("select_usuario")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
			if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
			
			
			
			//out.write("<script>window.open(\"www.google.com\");</script>");
			
			out.write("<script>window.location='"+Constantes.URL_SITIO+"generaPDF.php?inf=008-1"+filtros+"';setInterval(\"close()\",10000);</script>");
		}
	}

public void mt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
		
		///////////////////////////////////////////
		//////DEFINE DESLOGEO//////////////////////
		
		Cookie [] cookies=request.getCookies();
		if(request.getParameter("logout") !=  null){
			if (cookies != null) {
			    for (Cookie cookie : cookies) {
			    	//System.out.println("cookie logout: "+cookie.getName());
			    	if(cookie.getName().equals("Usuarios_id")) {cookie.setMaxAge(0);cookie.setPath("/");response.addCookie(cookie);}
			    }
			}
				response.sendRedirect("/001/");
				return;
			
		}
		
		
		//////////////////////////////////////////////////
		////////DEFINE PARAMETROS DE USUARIO//////////////
		
		String Usuarios_nombre="";
		
		
		
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}
		request.setAttribute("usuname", Usuarios_nombre);
		
		//////////////////////////////////////////////////
		
		
		Statement state = null;
		
		ResultSet GRUPOS_RS=null;
		ResultSet EMPRESAS_RS=null;
		ResultSet ESTCLPR_RS=null;
		ResultSet USUARIOS_RS=null;
		
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conexion=(Connection) DriverManager.getConnection
	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
		 state = (Statement) ((java.sql.Connection) conexion).createStatement();

		
		
      //:::::::::::::::::::::::::: sql trae empresas para select option:::::::::::::::::::::::::::::::::::::
         state = (Statement) ((java.sql.Connection) conexion).createStatement();
	    String SQL_EMPRESAS = "SELECT * FROM empresas WHERE estados_vig_novig_id=1";
	    EMPRESAS_RS =  state.executeQuery(SQL_EMPRESAS);
	    //definimos un arreglo para los resultados
	    ArrayList<String> empresas = new ArrayList<String>();
	    //recorremos los resultados de la consulta
	    while(EMPRESAS_RS.next()){        	   
    	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
	    	empresas.add(EMPRESAS_RS.getString("empresas_id")+"/"+EMPRESAS_RS.getString("empresas_nombre"));    
	    }
	  System.out.println("SIZE LIST: "+empresas.size());	
	  EMPRESAS_RS.close();
	  state.close();
	  String[] ar_empresas = new String[empresas.size()];
      for(int x=0; x < empresas.size(); x++){
    	 ar_empresas[x]=empresas.get(x);
      }
            
      request.setAttribute("ar_empresas", ar_empresas);
	        
  	//::::::::::::::::::::::::::::::::::::::::::sql usuarios para select option::::::::::::::::::::::::::::::::::::::  
	  state = (Statement) ((java.sql.Connection) conexion).createStatement();
	  String SQL_usu = "SELECT "
	  		+ "		CONCAT_WS( "
	  		+ "			' ',"
	  		+ "			`usuarios`.`Usuarios_nombre`,"
	  		+ "			`usuarios`.`Usuarios_ape_p`,"
	  		+ "			`usuarios`.`Usuarios_ape_m`"
	  		+ "		) AS nombre_usuario,"
	  		+ "		`usuarios`.`Usuarios_id`"
	  		+ "	FROM"
	  		+ "		`usuarios`"
	  		+ "	WHERE"
	  		+ "		`usuarios`.`estados_vig_novig_id` = 1";
	  
	    USUARIOS_RS =  state.executeQuery(SQL_usu);		    
	    //definimos un arreglo para los resultados		    
	    ArrayList<String> usuarios = new ArrayList<String>();		   
	    //recorremos los resultados de la consulta
	    while(USUARIOS_RS.next()){        	   
    //SI HAY usuarios, ENTONCES GUARDAMOS LOS usuarios
	    	usuarios.add(USUARIOS_RS.getString("Usuarios_id")+"/"+USUARIOS_RS.getString("nombre_usuario"));
    }
	  System.out.println("SIZE LIST: "+usuarios.size());  	
	  USUARIOS_RS.close();
	  String[] ar_usuarios = new String[usuarios.size()];
      for(int x=0; x < usuarios.size(); x++){
    	  ar_usuarios[x]=usuarios.get(x);
      }
            
      request.setAttribute("ar_usuarios", ar_usuarios);
      
      
      
		conexion.close();
        RequestDispatcher a = request.getRequestDispatcher("rpt_usuarios.jsp");
    	a.forward(request, response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
}
