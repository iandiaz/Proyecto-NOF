

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

/**
 * Servlet implementation class m2
 */
@WebServlet("/m2")
public class m2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public m2() {
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
		request.setAttribute("usuname", Controlador.getUsunameSession(request));
		request.setAttribute("modname", Controlador.getNameModulo("880"));
		
		
		
		//grabar
		if(request.getParameter("grabar") !=  null){
			
			try {
				String id = request.getParameter("id");
		    	String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
				
		    	
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		 Statement state = (Statement) ((java.sql.Connection) conexion).createStatement();
	    			
			    String SQL_UP = ""
			    		+ "UPDATE adm_periodos_contables "
			    		+ "SET estados_vig_novig_id='"+estados_vig_novig_id+"' "
			    		+ " WHERE admpc_id="+id;
			    System.out.println("UP: "+SQL_UP); 
			    state.executeUpdate(SQL_UP);
			    state.close();
	            conexion.close();
	            
	            response.sendRedirect("menu?Exito=OK");
	            return;
			}catch(Exception ex){
				ex.printStackTrace();
			    out.println("Error "+ex);
			}
			
		}
		
		//fin grabar
		
		
		
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 Statement state = (Statement) ((java.sql.Connection) conexion).createStatement();
    	   
    		//:::::::::::::::::::::::::: sql trae estados vig no vig para select option:::::::::::::::::::::::::::::::::::::
 		    String SQL_ESTADOS = "SELECT * FROM estados_vig_novig";
 		    ResultSet ESTADOS_RS = state.executeQuery(SQL_ESTADOS);
 		    //definimos un arreglo para los resultados
 		    ArrayList<String> estados = new ArrayList<String>();
 		    //recorremos los resultados de la consulta
 		    while(ESTADOS_RS.next()){        	   
         	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
 		    	estados.add(ESTADOS_RS.getString("estados_vig_novig_id")+"/"+ESTADOS_RS.getString("estados_vig_novig_nombre"));    
     	    }
 		  ESTADOS_RS.close();
 		
 		  String[] ar_estados = new String[estados.size()];
           for(int x=0; x < estados.size(); x++){
            	ar_estados[x]=estados.get(x);
           }
                 
           request.setAttribute("ar_estados", ar_estados);
 		//::::::::::::::::::::::::::fin sql trae estados vig no vig para select option:::::::::::::::::::::::::::::::::::::
          
 		    String SQL_CLPR = "SELECT *,"
		    		+ "		CONCAT_WS(' ' , "
 		    		+ "			`usuarios`.`Usuarios_nombre`, "
 		    		+ "			`usuarios`.`Usuarios_ape_p` "
 		    		+ "		) AS perfilusu_creador, "
 		    		+ "		DATE_FORMAT(em.feccreacion,'%d-%m-%Y %H:%i:%s') AS perfilusu_feccreacion, "
 		    		+ "		IF ( "
 		    		+ "			em.fecmod IS NULL,"
 		    		+ "			' ',"
 		    		+ "			DATE_FORMAT(em.fecmod,'%d-%m-%Y %H:%i:%s')"
 		    		+ "		) AS perfilusu_fecmod,"
 		    		+ "		IF ("
 		    		+ "			em.modificador IS NULL,"
 		    		+ "			' ',"
 		    		+ "			CONCAT_WS(' ', "
 		    		+ "			`u2`.`Usuarios_nombre`,"
 		    		+ "			`u2`.`Usuarios_ape_p`"
 		    		+ "			)"
 		    		+ "		) AS perfilusu_modificador "
		    		+ " FROM adm_periodos_contables em"
		    		+ " INNER JOIN `usuarios` ON `em`.`creador` = `usuarios`.`Usuarios_id` "
 		    		+ " LEFT JOIN `usuarios` u2 ON `em`.`modificador` = `u2`.`Usuarios_id` "
 		    		+ " WHERE em.admpc_id="+request.getParameter("id");
		    
		    System.out.println(SQL_CLPR);
		    ResultSet ADM_RS = state.executeQuery(SQL_CLPR);
		    if(ADM_RS.next()){  
		    	
		    	request.setAttribute("admpc_id",ADM_RS.getString("admpc_id")+"");
		    	request.setAttribute("admpc_year",ADM_RS.getString("admpc_year")+"");
		    	request.setAttribute("admpc_mes",ADM_RS.getString("admpc_mes")+"");
		    	request.setAttribute("estados_vig_novig_id",ADM_RS.getString("estados_vig_novig_id")+"");
		    	
		    	String fec_crea=ADM_RS.getString("perfilusu_feccreacion");
		    	String user_crea=ADM_RS.getString("perfilusu_creador");
		    	String fec_mod=ADM_RS.getString("perfilusu_fecmod");
		    	String user_mod=ADM_RS.getString("perfilusu_modificador");
		    	
		    	request.setAttribute("fec_crea", (fec_crea)+"");
	            request.setAttribute("fec_mod", (fec_mod)+"");
	            request.setAttribute("user_mod", (user_mod)+"");
	            request.setAttribute("user_crea", (user_crea)+"");
    	    }
		    ADM_RS.close();
		 	
		    state.close();
		    conexion.close();
		    
		   
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("Error "+ex);
		}
	
	
		RequestDispatcher rd = request.getRequestDispatcher("m2.jsp");
        rd.forward(request, response);
		
	}

}
