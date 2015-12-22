

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class i1
 */
@WebServlet("/m1")
public class m1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public m1() {
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
		String Usuarios_ID=Controlador.getUsuIDSession(request);
		request.setAttribute("usuname", Controlador.getUsunameSession(request));
		request.setAttribute("modname", Controlador.getNameModulo("890"));
   		
		
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 Statement state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
		 String SQL_clpr = "SELECT * FROM empresas em INNER JOIN estados_vig_novig e ON e.estados_vig_novig_id=em.estados_vig_novig_id "
		    		+ "	INNER JOIN `usuarios_has_empresas` ON (	`usuarios_has_empresas`.`empresas_id` = em.empresas_id 	AND `usuarios_has_empresas`.`estados_vig_novig_id` = 1 )"
		    		+ "	WHERE 	`usuarios_has_empresas`.`Usuarios_id` = "+Usuarios_ID+" AND em.empresas_relacionada=1"
		    		+ " ORDER BY em.empresas_nombre";
		    System.out.println(SQL_clpr);
	    ResultSet clpr_rs = state.executeQuery(SQL_clpr);
	    //definimos un arreglo para los resultados
	    
	    ArrayList<String> estado_clpral = new ArrayList<String>();
	   
	    //recorremos los resultados de la consulta
	    while(clpr_rs.next()){        	   
   	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
	    	estado_clpral.add(clpr_rs.getString("empresas_id")+"/"+clpr_rs.getString("empresas_nombre")+"/"+clpr_rs.getString("estados_vig_novig_id")+"/"+clpr_rs.getString("estados_vig_novig_nombre"));
   	    
	    }
	       
     String[] ar_clpr = new String[estado_clpral.size()];
     for(int x=0; x < estado_clpral.size(); x++){
   	  ar_clpr[x]=estado_clpral.get(x);
     }
           
     request.setAttribute("ar_clpr", ar_clpr);
     
     	state.close();
		conexion.close();
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("Error "+ex);
		}
		RequestDispatcher rd = request.getRequestDispatcher("m1.jsp");
        rd.forward(request, response);
		
	}

}
