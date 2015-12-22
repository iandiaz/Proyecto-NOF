

import java.io.IOException;
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
		
		// logout
		if(request.getParameter("logout") !=  null){
				Controlador.eraseCookie(request, response);
				response.sendRedirect("/001/");
				return;
		}
		//fin logout
		
		 request.setAttribute("usuname", Controlador.getUsunameSession(request));
		 request.setAttribute("modname", Controlador.getNameModulo("890"));
	   	 request.setAttribute("tipodte", request.getParameter("tipodte"));
		 request.setAttribute("em_id", request.getParameter("em_id"));
		
		 String tipodte=request.getParameter("tipodte");
		 String emp_emisor=request.getParameter("em_id");
		 
		 
		//grabar
		
				
		//fin grabar
		
		Statement state2 = null;
		Statement state = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state2 = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 
    		
 		//::::::::::::::::::::::::::::::::::::::::::traemos nombre empresa emisora::::::::::::::::::::::::::::::::::::::  
 		  state = (Statement) ((java.sql.Connection) conexion).createStatement();
 		  String SQL_emp = "SELECT empresas_nombre "
 		  		+ "	FROM empresas WHERE empresas_id="+emp_emisor;
 		  System.out.println(SQL_emp);
 		    ResultSet Emp_RS = state.executeQuery(SQL_emp);		
 		    
 		    if(Emp_RS.next())request.setAttribute("emp_emisor", Emp_RS.getString("empresas_nombre"));   
 		   
 		   
         //::::::::::::::::::::::::::::::::::::::::::sql estado clpr para select option::::::::::::::::::::::::::::::::::::::  
  		  state = (Statement) ((java.sql.Connection) conexion).createStatement();
  		  String SQL_usus = "SELECT Usuarios_id,CONCAT_WS(' ',Usuarios_nombre,Usuarios_ape_p,Usuarios_ape_m) as Usuarios_nombre "
  		  		+ "	FROM usuarios WHERE estados_vig_novig_id=1"
  		  		+ "	ORDER BY Usuarios_nombre ";
  		  System.out.println(SQL_usus);
  		    ResultSet USUS_RS = state.executeQuery(SQL_usus);		    
  		    //definimos un arreglo para los resultados		    
  		    ArrayList<String> usuarios = new ArrayList<String>();		   
  		    //recorremos los resultados de la consulta
  		    while(USUS_RS.next()){        	   
      	    	usuarios.add(USUS_RS.getString("Usuarios_id")+"/"+USUS_RS.getString("Usuarios_nombre").replace("/", " "));
      	    }
  		  String[] ar_usuarios = new String[usuarios.size()];
            for(int x=0; x < usuarios.size(); x++){
         	   ar_usuarios[x]=usuarios.get(x);
            }
                  
            request.setAttribute("ar_usuarios", ar_usuarios);
            //::::::::::::::::::::::::::::::::::::::::::traemos usuarios seleccionados para este tipo dte::::::::::::::::::::::::::::  
    		  state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		  String SQL_ususselect = "SELECT Usuarios_id "
    		  		+ "	FROM correos_internos_dte WHERE estados_vig_novig_id=1 AND cidte_tipodte="+tipodte+" AND empresas_id="+emp_emisor;
    		  System.out.println(SQL_ususselect);
    		    ResultSet USUSSEL_RS = state.executeQuery(SQL_ususselect);		    
    		    //definimos un arreglo para los resultados		    
    		    ArrayList<String> usuariossel = new ArrayList<String>();		   
    		    //recorremos los resultados de la consulta
    		    while(USUSSEL_RS.next()){        	   
    		    	usuariossel.add(USUSSEL_RS.getString("Usuarios_id"));
        	    }
    		  String[] ar_usuariossel = new String[usuariossel.size()];
              for(int x=0; x < usuariossel.size(); x++){
            	  ar_usuariossel[x]=usuariossel.get(x);
              }
                    
              request.setAttribute("ar_usuariossel", ar_usuariossel);   
              
              USUS_RS.close();
              state.close();
		  
			  state2.close();	    
			  conexion.close();
		}catch(Exception ex){
            ex.printStackTrace();
        }
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("c2.jsp");
        rd.forward(request, response);
		
	}

}
