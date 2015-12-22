

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
      
    }

    /**
   	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   	 */
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		if(Controlador.validateSession(request, response)){
   			
   			 mt(request,response);
   		}
   		else response.sendRedirect("/001/");
   	}

   	/**
   	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   	 */
   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		if(Controlador.validateSession(request, response)){
   			
   			 mt(request,response);
   		}
   		else response.sendRedirect("/001/");
   	}
   	
   public void mt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
   		
   	PrintWriter out = response.getWriter();		
   	
   	if(request.getParameter("logout") !=  null){
   			Controlador.eraseCookie(request, response);
   			response.sendRedirect("/001/");	
   			return;
   	}

   	//////////////////////////////////////////////////
   	////////VERIFICAMOS PERMISO ASOCIADO/////////////
   	
   	String p=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), "210");
   	
   	if(p.equals("0")){
   		response.sendRedirect("/001/");	
   		return;
   	}

   	
   	//////////////////////////////////////////////////
   	////////DEFINE PARAMETROS DE USUARIO//////////////
   	
   	request.setAttribute("modname", Controlador.getNameModulo());
   	
   	String Perfil_id=Controlador.getPerfilIDSession(request);
   	
   	request.setAttribute("usuname", Controlador.getUsunameSession(request));
   	
   	////////////////////////////////////////////////////////////
   	//////////////////////////////////////////////////////////
   		try {
   			//import java.io.IOException;
   			Class.forName("com.mysql.jdbc.Driver");
       		Connection conexion=(Connection) DriverManager.getConnection
       	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
       		Statement state = (Statement) ((java.sql.Connection) conexion).createStatement();
   		    
       		
   		    String SQL_FUNC = ""
   		    		+ "SELECT func_id,func_nombre,estados_vig_novig_nombre,"
   		    		+ "		CONCAT( "
   		    		+ "			`usuarios`.`Usuarios_nombre`, "
   		    		+ "			' ',"
   		    		+ "			`usuarios`.`Usuarios_ape_p` "
   		    		+ "		) AS creador, "
   		    		+ "		DATE_FORMAT(f.feccreacion,'%d-%m-%Y %H:%i:%s') AS feccreacion, "
   		    		+ "		IF ( "
   		    		+ "			f.fecmod IS NULL,"
   		    		+ "			' ',"
   		    		+ "			DATE_FORMAT(f.fecmod, '%d-%m-%Y %H:%i:%s')"
   		    		+ "		) AS fecmod,"
   		    		+ "		IF ("
   		    		+ "			f.modificador IS NULL,"
   		    		+ "			' ',"
   		    		+ "			CONCAT( "
   		    		+ "			`u2`.`Usuarios_nombre`,"
   		    		+ "			' ',"
   		    		+ "			`u2`.`Usuarios_ape_p`"
   		    		+ "			)"
   		    		+ "		) AS modificador "
   		    		+ " FROM funcionalidad f "
   		    		+ " 	INNER JOIN estados_vig_novig e ON e.estados_vig_novig_id=f.estados_vig_novig_id"
   		    		+ " 	INNER JOIN `usuarios` ON `f`.`creador` = `usuarios`.`Usuarios_id` "
   		    		+ " 	LEFT JOIN `usuarios` u2 ON `f`.`modificador` = `u2`.`Usuarios_id` "
   		    		+ " WHERE func_id="+Integer.parseInt(request.getParameter("id_m"));
   		    System.out.println(SQL_FUNC);
   		    ResultSet Func_RS = state.executeQuery(SQL_FUNC);
   		    if(Func_RS.next()){ 
   		    	
   		    	request.setAttribute("func_id",Func_RS.getString("func_id"));
   		    	request.setAttribute("func_nombre",Func_RS.getString("func_nombre"));
   		    	request.setAttribute("estados_vig_novig_nombre",Func_RS.getString("estados_vig_novig_nombre"));
   		    	
   		    	String fec_crea=Func_RS.getString("feccreacion");
   		    	String user_crea=Func_RS.getString("creador");
   		    	String fec_mod=Func_RS.getString("fecmod");
   		    	String user_mod=Func_RS.getString("modificador") ;
   		    	
   		    	
   		    	request.setAttribute("fec_crea", (fec_crea)+"");
   	            request.setAttribute("fec_mod", (fec_mod)+"");
   	            request.setAttribute("user_mod", (user_mod)+"");
   	            request.setAttribute("user_crea", (user_crea)+"");
       	    }
   		    Func_RS.close();
   		    state.close();	    
            conexion.close();
   		}catch(Exception ex){
   			ex.printStackTrace();
   		    out.println("Error "+ex);
   		}
   		RequestDispatcher rd = request.getRequestDispatcher("c2.jsp");
           rd.forward(request, response);
   		
   	}

}
