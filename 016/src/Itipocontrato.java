

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
 * Servlet implementation class Itipocontrato
 */
@WebServlet("/Itipocontrato")
public class Itipocontrato extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Itipocontrato() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    public boolean validateSession(HttpServletRequest request, HttpServletResponse response)
    {
		
  		boolean user_in_session=false;
  		
  		Cookie [] cookies=request.getCookies();
  		
  		if (cookies != null) 
  		{
  		    for (Cookie cookie : cookies) 
  		    {
  		        //work with cookies
  		    	 //System.out.println("cookie: "+cookie.getName());
  		    	
  		    	if(cookie.getName().equals("Usuarios_id")) user_in_session=true;
  		    	
  		    }
  		}
  		
  		//refrescamos la session 
  		
  		if (user_in_session && cookies != null) 
  		{
  		    for (Cookie cookie : cookies) 
  		    {
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
		
		if(validateSession(request, response))
		{
			
			 mt(request,response);
		}
		else 
			response.sendRedirect("/001/");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(validateSession(request, response))
		{
			
			 mt(request,response);
		}
		else 
			response.sendRedirect("/001/");
		
	}
	
	
	public void mt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		
		PrintWriter out = response.getWriter();		
		
		// logout
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
		
		//fin logout
		
		String id_iusuario="";
		
		if (cookies != null) 
		{
		    for (Cookie cookie : cookies) 
		    {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName()+"-"+cookie.getValue());
		    	
		    	if(cookie.getName().equals("Usuarios_id") && cookie.getValue()!=null) id_iusuario=cookie.getValue();
		    }
		}
		
		//grabar
		
		boolean tipocontratoexiste=false;
		
		if(request.getParameter("grabar") !=  null)
		{
			Statement stategrabar = null;
			
			Statement stateval = null;
			
			ResultSet rs_valtipocontrato= null;
			
			try 
			{
				
				String tipo_contrato_nombre = request.getParameter("tipo_contrato_nombre");
				
		    	String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
		    	
				//import java.io.IOException;
		    	
				Class.forName("com.mysql.jdbc.Driver");
				
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		
	    		stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		stateval = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		String sql_valgrupo="SELECT * FROM tipo_contrato WHERE tipo_contrato_nombre='"+tipo_contrato_nombre.toUpperCase()+"'";
	    		
	    		rs_valtipocontrato=stateval.executeQuery(sql_valgrupo);
	    		
	    		//stateval.close();
	    		
	    		if(rs_valtipocontrato.next())
	    		{
	    			stateval.close();
	    			
	    			rs_valtipocontrato.close();conexion.close();
	    			
	    			tipocontratoexiste=true;
	    			
	    			//response.sendRedirect("Igrupo?Exito=NOK");
	    		}
	    		else
	    		{
	    			
	    			
	    				    			
	    			String SQL_INSERT = "INSERT INTO tipo_contrato (tipo_contrato_ult_idper_exec,tipo_contrato_nombre, estados_vig_novig_id,tipo_contrato_feccreacion,tipo_contrato_creador) VALUES (8,'"+tipo_contrato_nombre.toUpperCase()+"',"+estados_vig_novig_id+",now(),"+id_iusuario+")";
	    			
	    			System.out.println(SQL_INSERT);
	    			
	    			stategrabar.executeUpdate(SQL_INSERT);
	    			
	    			stateval.close();
	    			
	    			rs_valtipocontrato.close();
	    			
	    			stategrabar.close();
	    			
	    			conexion.close(); 
	            
	    			RequestDispatcher rd_up = request.getRequestDispatcher("Menutipocontratos?Exito=OK");
	    			
	    			rd_up.forward(request, response);
	    		}
			}
			catch(Exception ex)
			{
			    out.println("Unable to connect to database "+ex);
			}
			
			if(!tipocontratoexiste)
			{
				return;
			}
			
		}
		
		//fin grabar
		
		
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
		
		Statement statecor = null;	
		
		ResultSet ESTADOS_RS = null;
		
		ResultSet CORRELATIVO_RS = null;
		
		try
		{
			//import java.io.IOException;
			
			Class.forName("com.mysql.jdbc.Driver");
			
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		
    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 
    		statecor = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
		    String SQL_ESTADOS = "SELECT * FROM estados_vig_novig";
		    
		    ESTADOS_RS =  state.executeQuery(SQL_ESTADOS);
		    
		    //definimos un arreglo para los resultados
		    
		    ArrayList<String> estados = new ArrayList<String>();
		   
		    //recorremos los resultados de la consulta
		   
		    while(ESTADOS_RS.next())
		    {        	   
        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	
		    	estados.add(ESTADOS_RS.getString("estados_vig_novig_id")+"/"+ESTADOS_RS.getString("estados_vig_novig_nombre"));
        	    
    	    }
		  
		    System.out.println("SIZE LIST: "+estados.size());
		    	
		    ESTADOS_RS.close();
		    
		    state.close();
          
		    String[] ar_estados = new String[estados.size()];
		    
		    for(int x=0; x < estados.size(); x++)
		    {
		    	ar_estados[x]=estados.get(x);
		    }
		    
		    request.setAttribute("ar_estados", ar_estados);
		    
		    //id correlativo
		    
		    String last_id_tipo_contrato_sql="SELECT"
		    		+ " 	`tipo_contrato`.`tipo_contrato_id`"
		    		+ " FROM"
		    		+ " 	`tipo_contrato`"
		    		+ " ORDER BY"
		    		+ " 	`tipo_contrato`.`tipo_contrato_id` DESC"
		    		+ " LIMIT 1";
		    
		    System.out.println("correlativo : no pasa na"+last_id_tipo_contrato_sql);
		    
		    CORRELATIVO_RS =  statecor.executeQuery(last_id_tipo_contrato_sql);
         
		    int correlativo=0;
		    
		    if(CORRELATIVO_RS.next()) correlativo=CORRELATIVO_RS.getInt("tipo_contrato_id")+1;
		    
		    System.out.println("correlativo : no pasa na2 "+correlativo);
		    
		    request.setAttribute("correlativo", correlativo+"");
          
		    statecor.close();
		    
		    conexion.close();
          
		}
		catch(Exception ex)
		{
		    out.println("Unable to connect to database "+ex);
		}
		
		String msg="";
		
		if(tipocontratoexiste)
		{
			
			msg="?Exito=NOK";
			
			String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
			
			String tipo_contrato_nombre = request.getParameter("tipo_contrato_nombre");
			
			request.setAttribute("tipo_contrato_nombre",tipo_contrato_nombre);
			
			request.setAttribute("estados_vig_novig_id",estados_vig_novig_id);
			
			//System.out.println("tipo_contrato_nombre "+tipo_contrato_nombre);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("Itipocontrato.jsp"+msg);
		
        rd.forward(request, response);
		
	}
	

}
