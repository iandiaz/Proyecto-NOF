

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
 * Servlet implementation class Ccontrato
 */
@WebServlet("/Ccontrato")
public class Ccontrato extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ccontrato() {
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
		//System.out.println("1");
		
		Cookie [] cookies=request.getCookies();
		
		if(request.getParameter("logout") !=  null)
		{
			if (cookies != null) 
			{
			    for (Cookie cookie : cookies) 
			    {
			        //work with cookies
			    	 System.out.println("cookie logout: "+cookie.getName());
			    	if(cookie.getName().equals("Usuarios_id")) {cookie.setMaxAge(0);cookie.setPath("/");response.addCookie(cookie);}
			    }
			}
				response.sendRedirect("/001/");	
				return;
		}
		
		String Usuarios_nombre="";
		
		//fecha
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		
		if (cookies != null) 
		{
		    for (Cookie cookie : cookies) 
		    {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName());
		    	if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}		
		
		request.setAttribute("usuname", Usuarios_nombre);
		
		Statement state = null;
		
		ResultSet usuarios_rs = null;
		
		try 
		{
			
			//import java.io.IOException;
			
			Class.forName("com.mysql.jdbc.Driver");
			
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
    		 System.out.println("1"); 
    		 
		    String SQL_usuarios = "	SELECT *, DATE_FORMAT(u.fecha_inicio,'%d-%m-%Y') as fecha_inicio1 "
		    		+ "				FROM contrato u "
		    		+ "				INNER JOIN estados_vig_novig e ON e.estados_vig_novig_id=u.estados_vig_novig_id "
		    		+ "				ORDER BY u.contrato_id";
		    
		    
		    usuarios_rs =  state.executeQuery(SQL_usuarios);
		    
		    //definimos un arreglo para los resultados
		    
		    ArrayList<String> usuariosal = new ArrayList<String>();
		   
		    //recorremos los resultados de la consulta
		    
		    
		    
		    while(usuarios_rs.next())
		    {        	   
        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	
		    	usuariosal.add(usuarios_rs.getString("contrato_id")+"/"+usuarios_rs.getString("contrato_nombre")+"/"+usuarios_rs.getString("estados_vig_novig_id")+"/"+usuarios_rs.getString("estados_vig_novig_nombre")
		    			+"/"+usuarios_rs.getString("fecha_inicio1"));
		    	
		  
		    	
    	    }
		  
		    //System.out.println("SIZE LIST: "+grupos.size());
		    	
		    usuarios_rs.close();
		    
		    state.close();
                
		    String[] arusuarios = new String[usuariosal.size()];
          
		    for(int x=0; x < usuariosal.size(); x++)
		    {
        	  arusuarios[x]=usuariosal.get(x);
        	  
        	  
		    }
            
            
            //traemos empresas asociadas al usuario
            
		    state = (Statement) ((java.sql.Connection) conexion).createStatement();
		    
		    
          String SQL_usu_empresa = ""
          + "SELECT "
          + " `contrato`.`contrato_id`, "
          + " `contrato`.`contrato_nombre`, "
          + "  CONCAT_WS(' ', `empresas`.`empresas_razonsocial`, `empresas`.`empresas_nombre`, `empresas`.`empresas_nombrenof`) AS empresa "
          + " FROM "
          + "     `contrato` "
          + " INNER JOIN `contrato_empresa` ON `contrato_empresa`.`contrato_id` = `contrato`.`contrato_id` "
          + " INNER JOIN `empresas` ON `contrato_empresa`.`empresas_id` = `empresas`.`empresas_id` "
          + " WHERE  "
          + "     `contrato_empresa`.`estados_vig_novig_id` = 1  "
          + "     AND `empresas`.`estados_vig_novig_id` = 1 "
          + "ORDER BY "
          + " contrato.contrato_nombre ";
          
          
          System.out.println("SQL_usuario_empresa : "+SQL_usu_empresa);
          
         ResultSet usuario_empresa_rs =  state.executeQuery(SQL_usu_empresa);
         
         
            ArrayList<String> usu_emp = new ArrayList<String>();
            
            
          //recorremos los resultados de la consulta
            
          while(usuario_empresa_rs.next())
          {
        	  
          	
          	
              usu_emp.add(usuario_empresa_rs.getString("contrato_id")+"/"+usuario_empresa_rs.getString("empresa"));
              
          }
          
          usuario_empresa_rs.close();
          
          
          String[] ar_usu_emp = new String[usu_emp.size()];
          
          
          for(int x=0; x < usu_emp.size(); x++)
          {
        	  
          	
          	
              ar_usu_emp[x]=usu_emp.get(x);
          }

          
          
          
           state.close();
            conexion.close();
            
            
          request.setAttribute("arusuarios", arusuarios);
          
            request.setAttribute("ar_usuarios_emp", ar_usu_emp);
            
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		   // out.println("Unable to connect to database "+ex);
		}
		RequestDispatcher rd = request.getRequestDispatcher("Ccontrato.jsp");
        rd.forward(request, response);
		
	}
}
