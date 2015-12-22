

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
 * Servlet implementation class Mtipocontrato2
 */
@WebServlet("/Mtipocontrato2")
public class Mtipocontrato2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Mtipocontrato2() {
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
		
		boolean existeGrupo = false;
		
		if(request.getParameter("grabar") !=  null)
		{
			
			Statement stategrabar = null;
			
			Statement stateval = null;
			
			ResultSet rs_valgrupos= null;
			
			try 
			{
				String grupos_nombre = request.getParameter("tipo_contrato_nombre");
				
		    	String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
		    	
				//import java.io.IOException;
		    	
				Class.forName("com.mysql.jdbc.Driver");
				
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		
	    		 stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		 
	    		stateval = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		String sql_valgrupo="SELECT * FROM tipo_contrato WHERE tipo_contrato_nombre='"+grupos_nombre.toUpperCase()+"' AND tipo_contrato_id<>"+request.getParameter("id_g");
	    		
		    	rs_valgrupos=stateval.executeQuery(sql_valgrupo);
		    	
		    	
		    	if(rs_valgrupos.next())
		    	{
		    		stateval.close();
		    		
	    			rs_valgrupos.close();conexion.close();
	    			
	    			existeGrupo=true;
	    			
	    			System.out.println("if(rs_valgrupos.next()){"+existeGrupo);
	    			
	    		}
		    	else
		    	{
	    			/*
	    			if(Constantes.SYNCDB==1)
	    			{
	    			
	    				//actualizamos grupo en birt
	    			
	    				ConBirt birtBD= new ConBirt();
	       			 
	       			 	String estado_v_nv="VIGENTE";
	       			 	
	       			 	if(estados_vig_novig_id.equals("2")) 
	       			 		estado_v_nv="NO VIGENTE";
	       			 
	       			 	String insert_birt=" UPDATE [GRUPO] "
	       			 		+ "	SET [GRUPO].[GRUP_ESTADO] = '"+estado_v_nv+"',"
	       			 		+ "	 [GRUPO].[GRUP_NOMBRE] = '"+grupos_nombre.toUpperCase()+"',"
	       			 		+ "	 [GRUPO].[USU_ID_UM] = "+id_iusuario+","
	       			 		+ "	 [GRUPO].[GRUP_FECHA_UM] = getdate()"
	       			 		+ "	WHERE"
	       			 		+ "		[GRUPO].[GRUP_ID] = "+request.getParameter("id_g");
	       			 	
	       			 	System.out.println("BIRT: "+insert_birt);
	       			 	
	       			 	birtBD.ConsultaINUP(insert_birt);
	       		     	       		     
	       		        birtBD.Desconectar();
	       			}
	    			*/
	    			
	    			String SQL_UPDATE = ""
			    		+ "UPDATE tipo_contrato "
			    		+ " SET tipo_contrato_accion_alertada=0 "
			    		+ "		,tipo_contrato_ult_idper_exec=10 "
			    		+ "		,tipo_contrato_nombre='"+grupos_nombre.toUpperCase()+"', estados_vig_novig_id="+estados_vig_novig_id+" "
			    		+ " 	,tipo_contrato_fecmod=now() "
			    		+ " 	,tipo_contrato_modificador= "+id_iusuario
			    		+ " WHERE tipo_contrato_id="+request.getParameter("id_g");
			    
	    			System.out.println(SQL_UPDATE);
	    			
	    			stategrabar.executeUpdate(SQL_UPDATE);
	    			
	    			stategrabar.close();
	    			
	    			stateval.close();
	    			
	    			rs_valgrupos.close();
	    			
	    			conexion.close();
	    			
	    			existeGrupo=false;
	    			
	    			System.out.println("UP: "+SQL_UPDATE);
	    			
	    			RequestDispatcher rd_up = request.getRequestDispatcher("Mtipocontrato?mExito=OK");
	    			
	    			rd_up.forward(request, response);
	    		}
			}
			catch(Exception ex)
			{
			    out.println("Unable to connect to database "+ex);
			}
			
			if(!existeGrupo)return;
			
		}
		
		//fin grabar
		
		System.out.println("existeGrupo"+existeGrupo);
		
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
		    	
		    	if(cookie.getName().equals("Usuarios_nombre")) 
		    		Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}
		
		request.setAttribute("usuname", Usuarios_nombre);
		
		Statement state = null;
		
		Statement state2 = null;
		
		ResultSet ESTADOS_RS = null;
		
		ResultSet Grupo_RS = null;
		
		try 
		{
			//import java.io.IOException;
			
			Class.forName("com.mysql.jdbc.Driver");
			
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 
    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 
    		state2 = (Statement) ((java.sql.Connection) conexion).createStatement();
		    
    		String SQL_ESTADOS = "SELECT * FROM estados_vig_novig";
    		
		    ESTADOS_RS =  state.executeQuery(SQL_ESTADOS);
		    
		    String SQL_GRUPO = ""
		    		+ "SELECT *,"
		    		+ "		CONCAT( "
		    		+ "			`usuarios`.`Usuarios_nombre`, "
		    		+ "			' ',"
		    		+ "			`usuarios`.`Usuarios_ape_p` "
		    		+ "		) AS perfilusu_creador, "
		    		+ "		DATE_FORMAT(g.tipo_contrato_feccreacion, '%d-%m-%Y %H:%i:%s') AS perfilusu_feccreacion, "
		    		+ "		IF ( "
		    		+ "			g.tipo_contrato_fecmod IS NULL,"
		    		+ "			' ',"
		    		+ "			DATE_FORMAT(g.tipo_contrato_fecmod, '%d-%m-%Y %H:%i:%s') "
		    		+ "		) AS perfilusu_fecmod,"
		    		+ "		IF ("
		    		+ "			g.tipo_contrato_modificador IS NULL,"
		    		+ "			' ',"
		    		+ "			CONCAT( "
		    		+ "			`u2`.`Usuarios_nombre`,"
		    		+ "			' ',"
		    		+ "			`u2`.`Usuarios_ape_p`"
		    		+ "			)"
		    		+ "		) AS perfilusu_modificador "
		    		+ " FROM tipo_contrato g "
		    		+ " 	INNER JOIN estados_vig_novig e ON e.estados_vig_novig_id=g.estados_vig_novig_id"
		    		+ " 	INNER JOIN `usuarios` ON `g`.`tipo_contrato_creador` = `usuarios`.`Usuarios_id` "
		    		+ " 	LEFT JOIN `usuarios` u2 ON `g`.`tipo_contrato_modificador` = `u2`.`Usuarios_id` "
		    		+ " WHERE tipo_contrato_id="+Integer.parseInt(request.getParameter("id_g"));
		    
		    Grupo_RS =  state2.executeQuery(SQL_GRUPO);
		    
		    if(Grupo_RS.next())
		    {
		    	
		    	request.setAttribute("tipo_contrato_id",Grupo_RS.getString("tipo_contrato_id"));
		    	
		    	request.setAttribute("tipo_contrato_nombre",Grupo_RS.getString("tipo_contrato_nombre"));
		    	
		    	request.setAttribute("estados_vig_novig_id",Grupo_RS.getString("estados_vig_novig_id"));
		    	
		    	String fec_crea=Grupo_RS.getString("perfilusu_feccreacion");
		    	
		    	String user_crea=Grupo_RS.getString("perfilusu_creador");
		    	
		    	String fec_mod=Grupo_RS.getString("perfilusu_fecmod");
		    	
		    	String user_mod=Grupo_RS.getString("perfilusu_modificador") ;
		    	
		    	request.setAttribute("fec_crea", (fec_crea)+"");
		    	
	            request.setAttribute("fec_mod", (fec_mod)+"");
	            
	            request.setAttribute("user_mod", (user_mod)+"");
	            
	            request.setAttribute("user_crea", (user_crea)+"");
    	    }
		    
		    Grupo_RS.close();
		    
		    state2.close();	    
		    
		    //definimos un arreglo para los resultados
		    
		    ArrayList<String> estados = new ArrayList<String>();
		   
		    //recorremos los resultados de la consulta
		    
		    while(ESTADOS_RS.next())
		    {        	   
        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	
		    	estados.add(ESTADOS_RS.getString("estados_vig_novig_id")+"/"+ESTADOS_RS.getString("estados_vig_novig_nombre"));
        	    
    	    }
		    
		  //System.out.println("SIZE LIST: "+estados.size());
		    	
		    ESTADOS_RS.close();
		    
		    state.close();
		    
		    conexion.close();
                
		    String[] ar_estados = new String[estados.size()];
		    
		    for(int x=0; x < estados.size(); x++)
		    {
		    	ar_estados[x]=estados.get(x);
		    }
                
		    request.setAttribute("ar_estados", ar_estados);
		    
		}
		catch(Exception ex)
		{
		    out.println("Unable to connect to database "+ex);
		}
		
		System.out.println("bool: "+existeGrupo);
		
		String msg="";
		
		if(existeGrupo)
		{
			msg="?Exito=NOK";
			
			String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
			
			String grupos_nombre = request.getParameter("tipo_contrato_nombre");
			
			request.setAttribute("tipo_contrato_nombre",grupos_nombre);
			
			request.setAttribute("estados_vig_novig_id",estados_vig_novig_id);
			
			//System.out.println("grupos_nombre "+grupos_nombre);
		}
		
		System.out.println("msg: "+msg);
		
		RequestDispatcher rd = request.getRequestDispatcher("Mtipocontrato2.jsp"+msg);
		
        rd.forward(request, response);
		
	}

	
	

}
