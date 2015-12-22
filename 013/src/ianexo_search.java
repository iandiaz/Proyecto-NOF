

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

import Constantes.Constantes;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class ianexo_search
 */
@WebServlet("/ianexo_search")
public class ianexo_search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ianexo_search() {
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

	String p=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), Constantes.PINGRESAR);
	
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
	
		Statement state = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
    		 
    		 String SQL_contratos = "	SELECT "
    				 + "			contrato.contrato_nombre "
    		 		+ "				,estados_vig_novig.estados_vig_novig_id "
    		 		+ "				,es.estadocontrato_nombre"
    		 		+ "				,estados_vig_novig.estados_vig_novig_nombre "
    		 		+ "				,contrato.contrato_id "
    		 		+ "				, DATE_FORMAT(contrato.fecha_inicio,'%d-%m-%Y') as fecha_inicio1 "
    		 	    + "  			,`empresas`.`empresas_nombrenof` AS empresa"
    		 	    + "				,`empresas`.`empresas_id`  "
    			      
 		    		+ "				FROM contrato  "
 		    		+ "				INNER JOIN estados_vig_novig ON estados_vig_novig.estados_vig_novig_id=contrato.estados_vig_novig_id "
 		    		+ "				INNER JOIN estadocontrato es ON contrato.estadocontrato_id = es.estadocontrato_id"
 		    		+ " 			INNER JOIN `contrato_empresa` ON `contrato_empresa`.`contrato_id` = `contrato`.`contrato_id` "
		            + " 			INNER JOIN `empresas` ON `empresas`.`empresas_id` = `contrato_empresa`.`empresas_id` "
		         	+ "				ORDER BY contrato.contrato_id ";
		    
    		 
		  ResultSet Contratos_RS = state.executeQuery(SQL_contratos);
		    //definimos un arreglo para los resultados
		    
		  ArrayList<String> contratos = new ArrayList<String>();
		   
		    //recorremos los resultados de la consulta
		  while(Contratos_RS.next()){        	   
		    	contratos.add(Contratos_RS.getString("contrato_id")+";;"+Contratos_RS.getString("contrato_nombre")+";;"+Contratos_RS.getString("estados_vig_novig_id")+";;"+Contratos_RS.getString("estados_vig_novig_nombre")
		    			+";;"+Contratos_RS.getString("fecha_inicio1")+";;"+Contratos_RS.getString("estadocontrato_nombre")+";;"+Contratos_RS.getString("empresa")+";;"+Contratos_RS.getString("empresas_id"));
		    	
    	  }
		      
		  Contratos_RS.close();
		  state.close();
          conexion.close();
                
        
          request.setAttribute("contratos", contratos);
		}catch(Exception ex){
		
		    out.println("Error "+ex);
		    ex.printStackTrace();
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("ianexo_search.jsp");
        rd.forward(request, response);
		
	}

}
