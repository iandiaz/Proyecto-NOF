

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
 * Servlet implementation class E01
 */
@WebServlet("/E01")
public class E01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public E01() {
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

		String p=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), Constantes.PELIMINAR);
		
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
	    		
	    		 
	    		 String SQL_anexos = "	SELECT "
	    				 + "			anexo_contrato.anc_observaciones "
	    		 		+ "				,anexo_contrato.anc_id "
	    		 		+ "				,estados_vig_novig.estados_vig_novig_nombre "
	    		 		+ "				,estados_vig_novig.estados_vig_novig_id "
	    		 		+ "  			,`empresas`.`empresas_nombrenof` AS empresa"
	    		 	    + "				,`empresas`.`empresas_id`  "
	    		 	    + "				,`contrato`.`contrato_nombre`  "
	    		 		+ "				FROM anexo_contrato  "
	 		    		+ "				INNER JOIN estados_vig_novig ON estados_vig_novig.estados_vig_novig_id=anexo_contrato.estados_vig_novig_id "
	 		    		+ "				INNER JOIN contrato ON contrato.contrato_id=anexo_contrato.contrato_id "
	 		    		
	 		    		+ "				INNER JOIN empresas ON empresas.empresas_id = anexo_contrato.empresas_id"
	 		    	 	+ "				ORDER BY anexo_contrato.anc_observaciones ";
			    
	    		 
			  ResultSet Anexos_RS = state.executeQuery(SQL_anexos);
			    //definimos un arreglo para los resultados
			    
			  ArrayList<String> anexos = new ArrayList<String>();
			   
			    //recorremos los resultados de la consulta
			  while(Anexos_RS.next()){        	   
			    	anexos.add(	Anexos_RS.getString("anc_id")
			    				+";;"+Anexos_RS.getString("anc_observaciones")
			    				+";;"+Anexos_RS.getString("estados_vig_novig_id")
			    				+";;"+Anexos_RS.getString("estados_vig_novig_nombre")
			    				+";;"+Anexos_RS.getString("empresa")
			    				+";;"+Anexos_RS.getString("empresas_id")
			    				+";;"+Anexos_RS.getString("contrato_nombre"));
			    	
	    	  }
			      
			  Anexos_RS.close();
			  state.close();
	          conexion.close();
	                
	        
	          request.setAttribute("anexos", anexos);
			}catch(Exception ex){
			
			    out.println("Error "+ex);
			    ex.printStackTrace();
			}
			
			
			RequestDispatcher rd = request.getRequestDispatcher("E01.jsp");
	        rd.forward(request, response);
			
		}
}
