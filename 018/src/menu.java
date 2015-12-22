

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class menu
 */
@WebServlet("/menu")
public class menu extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public menu() {
       
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
      		////////DEFINE PARAMETROS DE USUARIO//////////////
      		
      		request.setAttribute("modname", Controlador.getNameModulo());
      		
      		String Perfil_id=Controlador.getPerfilIDSession(request);
      		
      		request.setAttribute("usuname", Controlador.getUsunameSession(request));
      		////////////////////////////////////////////////////////////
      		//////////////////////////////////////////////////////////
      		
      		/////////////////////PERMISOS//////////////////////////////
      		String perb_ingresar="0";
      		String perb_modificar="0";
      		String perb_eliminar="0";
      		String perb_consultar="0";
      		String perb_reportes="0";
      		
      		
      		Statement stateper = null;
      		ResultSet PERFILES_RS = null;
      		
      		try {
      			//import java.io.IOException;
      			Class.forName("com.mysql.jdbc.Driver");
          		Connection conexion=(Connection) DriverManager.getConnection
          	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
          		stateper = (Statement) ((java.sql.Connection) conexion).createStatement();
          		
      		    String SQL_PERFILES = "SELECT "
      		    		+ " 	`permisos`.`tipo_permiso_id`,"
      		    		+ " 	`modulos`.`Modulos_codigo`,"
      		    		+ " 	`modulos`.`estados_vig_novig_id`,"
      		    		+ " 	`permisos`.`permisos_nombre`, "
      		    		+ " 	`modulos`.`modulos_manualnombre` "
      		    		+ " FROM"
      		    		+ " 	`modulos`"
      		    		+ " INNER JOIN `permisos` ON `permisos`.`Modulos_id` = `modulos`.`Modulos_id`"
      		    		+ " INNER JOIN ("
      		    		+ " 	SELECT"
      		    		+ " 		`perfilusu_has_permisos`.`permisos_id`"
      		    		+ " 	FROM"
      		    		+ " 		`perfilusu_has_permisos`"
      		    		+ " 	INNER JOIN `perfilusu` ON `perfilusu`.`perfilusu_id` = "+Perfil_id
      		    		+ " 	WHERE"
      		    		+ " 		`perfilusu`.`estados_vig_novig_id` = 1"
      		    		+ " 	AND `perfilusu_has_permisos`.`estados_vig_novig_id` = 1"
      		    		+ " 	AND `perfilusu_has_permisos`.`perfilusu_id` = "+Perfil_id
      		    		+ " ) php ON php.permisos_id = `permisos`.`permisos_id`"
      		    		+ " WHERE"
      		    		+ " 	`modulos`.`estados_vig_novig_id` = 1"
      		    		+ " 	AND `permisos`.`Modulos_id` = "+Constantes.MODULOID
      		    		+ " ORDER BY"
      		    		+ " 	`modulos`.`Menus_id`,"
      		    		+ " 	`modulos`.`Modulos_codigo`";
      		   
      		    
      		    //System.out.println(SQL_PERFILES);
      		    PERFILES_RS =  stateper.executeQuery(SQL_PERFILES);
      		     
      		   
      		    //recorremos los resultados de la consulta y validamos los botones que tendra disponibles
      		    String manual="";
      		    while(PERFILES_RS.next()){
      		    	if(PERFILES_RS.getString("tipo_permiso_id")!= null && PERFILES_RS.getString("tipo_permiso_id").equals("1")) perb_ingresar="1";
              		if(PERFILES_RS.getString("tipo_permiso_id")!= null && PERFILES_RS.getString("tipo_permiso_id").equals("2"))perb_eliminar="1";
              		if(PERFILES_RS.getString("tipo_permiso_id")!= null && PERFILES_RS.getString("tipo_permiso_id").equals("3"))perb_modificar="1";
              		if(PERFILES_RS.getString("tipo_permiso_id")!= null && PERFILES_RS.getString("tipo_permiso_id").equals("4"))perb_consultar="1";
              		if(PERFILES_RS.getString("tipo_permiso_id")!= null && PERFILES_RS.getString("tipo_permiso_id").equals("5"))perb_reportes="1";
              		if(PERFILES_RS.getString("modulos_manualnombre")!= null ) manual=Constantes.URL_MANUALES+""+PERFILES_RS.getString("modulos_manualnombre");
              		        	
          	    }
      		    
      		    PERFILES_RS.close();
      		    stateper.close();
      	       	conexion.close();
             	
      	    request.setAttribute("manual", manual);
      	       	
             	
              request.setAttribute("perb_ingresar", perb_ingresar);
              request.setAttribute("perb_eliminar", perb_eliminar);
              request.setAttribute("perb_modificar", perb_modificar);
              request.setAttribute("perb_consultar", perb_consultar);
              request.setAttribute("perb_reportes", perb_reportes);
              
      		///////////////////////////////////////////////////////////
      			RequestDispatcher rd = request.getRequestDispatcher("menu.jsp");
      			rd.forward(request, response);
              
      		}catch(Exception ex){
      		    out.println("Error: "+ex);
      		}
      		
      	}

}
