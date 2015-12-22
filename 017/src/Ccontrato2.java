

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
 * Servlet implementation class Ccontrato2
 */
@WebServlet("/Ccontrato2")
public class Ccontrato2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ccontrato2() {
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
		
		//fecha 
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
		
		Statement state2 = null;
		Statement state = null;
		ResultSet USUARIOS_RS = null;
		ResultSet PERUSUEMP_RS = null;
		ResultSet EMPRESAS_RS = null;
		ResultSet EMPRESAS_R = null;
		ResultSet PERUSUEMP_R = null;
		
		System.out.println("1");
		
		try 
		{
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
			
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state2 = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 
    		//:::::::::::::::::::::::::: SQL TRAE PERMISOS QUE TIENE EL USU EMP:::::::::::::::::::::::::::::::::::::
    		 
   		    String SQL_PERUSUEMP = "SELECT * FROM contrato_empresa WHERE estados_vig_novig_id=1 AND contrato_id="+Integer.parseInt(request.getParameter("Usuarios_id"));
   		    
   		    System.out.println("SQL_PERUSUEMP: "+SQL_PERUSUEMP);
   		    
   		    PERUSUEMP_RS =  state.executeQuery(SQL_PERUSUEMP);
   		    
   		    //definimos un arreglo para los resultados
   		    
   		    ArrayList<String> usuariosempresas = new ArrayList<String>();
   		    
   		    //recorremos los resultados de la consulta
   		    
   		    while(PERUSUEMP_RS.next())
   		    {        	   
           	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
   		    	usuariosempresas.add(PERUSUEMP_RS.getString("empresas_id"));
       	    }
   		  
   		  PERUSUEMP_RS.close();
   		  
   		  state.close();
   		  
   		  String[] ar_usuariosempresas = new String[usuariosempresas.size()];
   		  
   		System.out.println("7");
   		  	
             for(int x=0; x < usuariosempresas.size(); x++)
             {
             	ar_usuariosempresas[x]=usuariosempresas.get(x);
             }
                 
              request.setAttribute("ar_usuariosempresas", ar_usuariosempresas);
             
   		//::::::::::::::::::::::::::SQL TRAE PERMISOS QUE TIENE EL USU EMP:::::::::::::::::::::::::::::::::::::
    		 
           //:::::::::::::::::::::::::: sql trae estados vig no vig para select option:::::::::::::::::::::::::::::::::::::
             
             state = (Statement) ((java.sql.Connection) conexion).createStatement();
             
  		    String SQL_EMPRESAS = "SELECT * FROM empresas WHERE estados_vig_novig_id=1";
  		    
  		  System.out.println("9");
  		    
  		    EMPRESAS_RS =  state.executeQuery(SQL_EMPRESAS);
  		    
  		    //definimos un arreglo para los resultados
  		    
  		    ArrayList<String> empresas = new ArrayList<String>();
  		    
  		  System.out.println("10");
  		    
  		    //recorremos los resultados de la consulta
  		    
  		    while(EMPRESAS_RS.next())
  		    {        	   
          	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
  		    	
  		    	empresas.add(EMPRESAS_RS.getString("empresas_id")+"/"+EMPRESAS_RS.getString("empresas_nombre"));
  		    	
  		     	
      	    }
  		    
  		
  		  EMPRESAS_RS.close();
  		  
  		  state.close();
  		  
  		  
  		  String[] ar_empresas = new String[empresas.size()];
  		  
            for(int x=0; x < empresas.size(); x++)
            {
            	
          	 ar_empresas[x]=empresas.get(x);
          	 
          	 
            }
                  
            request.setAttribute("ar_empresas", ar_empresas);
            
            // tipo contrato
            
            state = (Statement) ((java.sql.Connection) conexion).createStatement();
   		 
   		
   		//:::::::::::::::::::::::::: SQL TRAE PERMISOS QUE TIENE EL USU EMP:::::::::::::::::::::::::::::::::::::
   		 
  		    String SQL_PERUSUEM = "SELECT * FROM contrato_tipocontrato WHERE estados_vig_novig_id=1 AND contrato_id="+Integer.parseInt(request.getParameter("Usuarios_id"));
  		    
  		    
  		    System.out.println("SQL_PERUSUEMP: "+SQL_PERUSUEM);
  		    
  		    PERUSUEMP_R =  state.executeQuery(SQL_PERUSUEM);
  		    
  		    //definimos un arreglo para los resultados
  		    
  		    ArrayList<String> usuariosempresa = new ArrayList<String>();
  		    
  		    //recorremos los resultados de la consulta
  		    
  		     while(PERUSUEMP_R.next())
  		    {        	   
          	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
  		    	usuariosempresa.add(PERUSUEMP_R.getString("tipo_contrato_id")+";"+PERUSUEMP_R.getString("se_cobra"));
      	    }
  		    
  		  
  		  PERUSUEMP_R.close();
  		  
  		  state.close();
  		  
  		  String[] ar_usuariosempresa = new String[usuariosempresa.size()];
  		  
  		  	
            for(int x=0; x < usuariosempresa.size(); x++)
            {
            	ar_usuariosempresa[x]=usuariosempresa.get(x);
            }
                
            
            request.setAttribute("ar_usuariosempresa", ar_usuariosempresa);
            
  		//::::::::::::::::::::::::::SQL TRAE PERMISOS QUE TIENE EL USU EMP:::::::::::::::::::::::::::::::::::::
            
            
          //:::::::::::::::::::::::::: sql trae estados vig no vig para select option:::::::::::::::::::::::::::::::::::::
            
            state = (Statement) ((java.sql.Connection) conexion).createStatement();
            
 		    String SQL_EMPRESA = "SELECT * FROM tipo_contrato WHERE estados_vig_novig_id=1";
 		    
 		     
 		    EMPRESAS_R =  state.executeQuery(SQL_EMPRESA);
 		    
 		    //definimos un arreglo para los resultados
 		    
 		    ArrayList<String> empresa = new ArrayList<String>();
 		    
 		     
 		    //recorremos los resultados de la consulta
 		    
 		    while(EMPRESAS_R.next())
 		    {        	   
         	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
 		    	
 		    	
 		    	empresa.add(EMPRESAS_R.getString("tipo_contrato_id")+"/"+EMPRESAS_R.getString("tipo_contrato_nombre"));
 		    	
 		    	
     	    }
 		    
 		     
 		   
 		  EMPRESAS_R.close();
 		  
 		  state.close();
 		  
 		  
 		  String[] ar_empresa = new String[empresa.size()];
 		  
           for(int x=0; x < empresa.size(); x++)
           {
           	
           	
         	 ar_empresa[x]=empresa.get(x);
         	 
           }
                 
           request.setAttribute("ar_empresa", ar_empresa);
            
            // fin tipo contrato
            
        //::::::::::::::::::::::::::fin sql trae estados vig no vig para select option:::::::::::::::::::::::::::::::::::::
             
             
            String SQL_Usuarios = "SELECT "
             		+ "u.contrato_id, " 		    		
 		    		+ " u.contrato_nombre, "
  		    		+ " es.estadocontrato_nombre, "
  		    		+ " e.estados_vig_novig_nombre, "
  		    		+ " DATE_FORMAT(u.fecha,'%d-%m-%Y') as fecha,"
		    		+ " DATE_FORMAT(u.fecha_inicio,'%d-%m-%Y') as fecha_inicio,"
		    		+ " DATE_FORMAT(u.fecha_termino,'%d-%m-%Y') as fecha_termino,"
		    		+ " u.durac_inic,"
 		    		+ " u.plazo_renov,"
 		    		+ "	u.contrato_file, "
 		    		+ " CONCAT_WS(' ',us.Usuarios_nombre,us.Usuarios_ape_p) as nombre,"
 		    		+ " DATE_FORMAT(u.contrato_feccreacion,'%d-%m-%Y %H:%i:%s') AS fec_crea,"
 		    		+ " IF ( u.contrato_fecmod IS NULL,' ',DATE_FORMAT(u.contrato_fecmod,'%d-%m-%Y %H:%i:%s')) AS contrato_fecmod,"
 		    		+ " IF (u.contrato_modificador IS NULL,' ',CONCAT_WS(' ', u2.Usuarios_nombre,u2.Usuarios_ape_p)) AS contrato_modificador"
 		    		+ " FROM contrato u"
 		    		+ " INNER JOIN estadocontrato es ON u.estadocontrato_id = es.estadocontrato_id"
 		    		+ " INNER JOIN estados_vig_novig e ON u.estados_vig_novig_id = e.estados_vig_novig_id"
 		    		+ " INNER JOIN usuarios us ON u.contrato_creador = us.Usuarios_id"
 		    		+ " LEFT JOIN usuarios u2 ON u.contrato_modificador = u2.Usuarios_id"
 		    		+ " WHERE u.contrato_id="+Integer.parseInt(request.getParameter("Usuarios_id"));
            
            
		    System.out.println("SQL_Usuarios: "+SQL_Usuarios);
		    
		    USUARIOS_RS =  state2.executeQuery(SQL_Usuarios);
		    
		    
		    if(USUARIOS_RS.next())
		    {   	
		    	
		    	request.setAttribute("Usuarios_id",USUARIOS_RS.getString("contrato_id")+"");
 		    	request.setAttribute("Usuarios_nombre",USUARIOS_RS.getString("contrato_nombre"));
 		    	request.setAttribute("estados_vig_novig_nombre",USUARIOS_RS.getString("estadocontrato_nombre"));
 		    	request.setAttribute("Usuarios_ape_p",USUARIOS_RS.getString("estados_vig_novig_nombre"));
 		    	request.setAttribute("Usuarios_ape_m",USUARIOS_RS.getString("fecha"));
 		    	request.setAttribute("Usuarios_pass",USUARIOS_RS.getString("fecha_inicio"));
 		    	request.setAttribute("Usuarios_login",USUARIOS_RS.getString("fecha_termino"));
 		    	request.setAttribute("Usuarios_telefono",USUARIOS_RS.getString("durac_inic"));
 		    	request.setAttribute("Usuarios_email",USUARIOS_RS.getString("plazo_renov"));
 		    	request.setAttribute("tipo_usu_nombre",USUARIOS_RS.getString("nombre"));
 		    	
 		     	
 		    	String fec_crea=USUARIOS_RS.getString("fec_crea");
 		    	
 		    	String fec_mod=USUARIOS_RS.getString("contrato_fecmod");
 		    	
 		    	String user_mod=USUARIOS_RS.getString("contrato_modificador") ;
 		
 		    	request.setAttribute("fec_crea", (fec_crea)+"");
 		    	
 		    	request.setAttribute("fec_mod", (fec_mod)+"");
 		    	
 		    	request.setAttribute("user_mod", (user_mod)+"");
 		    	
 		    	if(!USUARIOS_RS.getString("contrato_file").equals("")) request.setAttribute("file1", Constantes.URL_DOCS+USUARIOS_RS.getString("contrato_file"));
		    
    	    }
		    
		    USUARIOS_RS.close();
		    state2.close();	    
		    
		   
		}
		catch(Exception ex)
		{
		    out.println("Error "+ex);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("Ccontrato2.jsp");
		
        rd.forward(request, response);
		
	}

}
