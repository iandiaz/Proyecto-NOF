
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Constantes.Constantes;
import Constantes.Controlador;

/**
 * Servlet implementation class MenuServlet
 */
@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MenuServlet() {
       
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
		
	}
	
	public void mt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		//
		PrintWriter out = response.getWriter();
		
		if(request.getParameter("logout") !=  null){
				Controlador.eraseCookie(request, response);
				response.sendRedirect("/001/");	
				return;
		}
		
		
		request.setAttribute("usuname", Controlador.getUsunameSession(request));
		
		Statement state = null;
		ResultSet MENUS_RS = null;
		
		ArrayList<String> modulos = new ArrayList<String>();
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
		    String SQL_MENUS = "SELECT "
		    		+ " 	`menus`.`Menus_nombre`,"
		    		+ " 	`modulos`.`Modulos_nombre`,"
		    		+ " 	`modulos`.`Modulos_codigo`,"
		    		+ " 	`modulos`.`estados_vig_novig_id`,"
		    		+ " 	`modulos`.`Modulos_id`,"
		    		+ " 	`permisos`.`permisos_nombre`,"
		    		+ " 	`permisos`.`permisos_id`,"
		    		+ " 	php.permisos_id"
		    		+ " FROM"
		    		+ " 	`menus`"
		    		+ " INNER JOIN `modulos` ON `modulos`.`Menus_id` = `menus`.`Menus_id`"
		    		+ " LEFT JOIN `permisos` ON `permisos`.`Modulos_id` = `modulos`.`Modulos_id`"
		    		+ " INNER JOIN ("
		    		+ " 	SELECT"
		    		+ " 		`perfilusu_has_permisos`.`permisos_id`"
		    		+ " 	FROM"
		    		+ " 		`perfilusu_has_permisos`"
		    		+ "		INNER JOIN `perfilusu` ON `perfilusu`.`perfilusu_id` = "+Controlador.getPerfilIDSession(request)
		    		+ " 	WHERE"
		    		+ "		`perfilusu`.`estados_vig_novig_id` = 1"
		    		+ " 	AND	`perfilusu_has_permisos`.`estados_vig_novig_id` = 1"
		    		+ " 	AND `perfilusu_has_permisos`.`perfilusu_id` = "+Controlador.getPerfilIDSession(request)
		    		+ " ) php ON php.permisos_id = `permisos`.`permisos_id`"
		    		+ " WHERE"
		    		+ " 	`modulos`.`estados_vig_novig_id` = 1"
		    		+ " GROUP BY"
		    		+ " 	`modulos`.`Modulos_codigo`"
		    		+ " ORDER BY"
		    		+ " 	`modulos`.`Menus_id`,"
		    		+ " 	`modulos`.`Modulos_codigo`";
		    		    
		    System.out.println("SQL: "+SQL_MENUS);
		    MENUS_RS =  state.executeQuery(SQL_MENUS);
		    
		    //definimos un arreglo para los resultados
		    
		    ArrayList<String> menus = new ArrayList<String>();
		   
		    //recorremos los resultados de la consulta
		    while(MENUS_RS.next()){
        	   
        	    //SI HAY MODULOS PARA EL MENU, ENTONCES MOSTRAMOS EL MENU
		    	menus.add(MENUS_RS.getString("Menus_nombre")+"/"+MENUS_RS.getString("Modulos_nombre")+"/"+MENUS_RS.getString("Modulos_codigo"));
        	    
    	    }
		    	MENUS_RS.close();
		    	
            	state.close();
            	conexion.close();
                
                String[] arMenus = new String[menus.size()];
                for(int x=0; x < menus.size(); x++){
                	arMenus[x]=menus.get(x);
                }
                
                request.setAttribute("armenus", arMenus);
              
                RequestDispatcher rd = request.getRequestDispatcher("menu.jsp");
            	rd.forward(request, response); 
		    
		}catch(Exception ex){
		    out.println("ERROR: "+ex);
		    ex.printStackTrace();
		}
		
	}

}
