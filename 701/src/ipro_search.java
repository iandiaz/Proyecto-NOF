

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
 * Servlet implementation class ipro_search
 */
@WebServlet("/ipro_search")
public class ipro_search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ipro_search() {
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
		//System.out.println("1");
		
		Cookie [] cookies=request.getCookies();
		if(request.getParameter("logout") !=  null){
			if (cookies != null) {
			    for (Cookie cookie : cookies) {
			        //work with cookies
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
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName());
		    	if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}		
		request.setAttribute("usuname", Usuarios_nombre);
		Statement state = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
  		   ConBirt birtBD2= new ConBirt();
  		   	
  		 String sql_clpr=""
	   				+ "	SELECT "
	   				+ "		[CLIENTEPROVEEDOR].[CLPR_ID],"
	   				+ "		[CLIENTEPROVEEDOR].[CLPR_NOMBRE_FANTASIA]"
	   				+ "	FROM"
	   				+ "		[CLIENTEPROVEEDOR]"
	   				+ "	INNER JOIN [DIRECCION] ON [DIRECCION].[CLPR_ID] = [CLIENTEPROVEEDOR].[CLPR_ID]"
	   				+ "	INNER JOIN [UBICACION] ON ("
	   				+ "		[UBICACION].[DIRE_ID] = [DIRECCION].[DIRE_ID]"
	   				+ "		AND [UBICACION].[UBI_ESTADO] = 'VIGENTE'"
	   				+ "	)"
	   				+ "	INNER JOIN [ACTIVO] ON ("
	   				+ "		[ACTIVO].[UBI_ID] = [UBICACION].[UBI_ID]"
	   				+ "		AND [ACTIVO].[ALT_ESTADO] = 'VIGENTE'"
	   				+ "	)"
	   				+ "	INNER JOIN [PRODUCTO] ON [PRODUCTO].[PROD_ID] = [ACTIVO].[PROD_ID]"
	   				+ "	INNER JOIN [FUNCIONALIDAD] ON [FUNCIONALIDAD].[FUNC_ID] = [PRODUCTO].[FUNC_ID]"
	   				+ "	WHERE"
	   				+ "		[FUNCIONALIDAD].[FUNC_ID] = 6"
	   				+ "	GROUP BY"
	   				+ "		[CLIENTEPROVEEDOR].[CLPR_ID],"
	   				+ "		[CLIENTEPROVEEDOR].[CLPR_NOMBRE_FANTASIA]"
	   				+ "	ORDER BY [CLIENTEPROVEEDOR].[CLPR_NOMBRE_FANTASIA]";
  		   		System.out.println(sql_clpr);
	 		  ResultSet Alertas_RS= birtBD2.Consulta(sql_clpr); 
	 		  
		    if(request.getAttribute("filtro")!=null){
		    	request.setAttribute("filtro",request.getAttribute("filtro"));
		    }
		    /*System.out.println("Consulta: "+SQL_Alertas);*/
		    //definimos un arreglo para los resultados
		    
		    ArrayList<String> alertas = new ArrayList<String>();
		   
		    //recorremos los resultados de la consulta
		    while(Alertas_RS.next()){        	   
		    	alertas.add(Alertas_RS.getString("CLPR_ID")+"/"+Alertas_RS.getString("CLPR_NOMBRE_FANTASIA"));
    	    }
		    Alertas_RS.close();
		  state.close();
          conexion.close();
                
          String[] ar_alertas = new String[alertas.size()];
          for(int x=0; x < alertas.size(); x++){
        	  ar_alertas[x]=alertas.get(x);
          }
             
          request.setAttribute("ar_alertas", ar_alertas);
		}catch(Exception ex){
			ex.printStackTrace();
			out.println("ERROR: "+ex);
		  
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("ipro_search.jsp");
        rd.forward(request, response);
		
	}

}
