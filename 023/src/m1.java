

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
 * Servlet implementation class m1
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

   	//////////////////////////////////////////////////
   	////////VERIFICAMOS PERMISO ASOCIADO/////////////
   	
   	String p=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), Constantes.PMODIFICAR);
   	
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
       		
       		String SQL_Func = ""
		    		+ "SELECT funcionalidad.func_nombre,prod_id,prod_pn_tli_codfab,f.estados_vig_novig_id,estados_vig_novig_nombre,prod_desc_corto  "
		    		+ " FROM producto f"
		    		+ "	INNER JOIN funcionalidad on funcionalidad.func_id=f.func_id "
		    		+ "INNER JOIN estados_vig_novig e ON e.estados_vig_novig_id=f.estados_vig_novig_id "
		    		+ "ORDER BY f.prod_pn_tli_codfab";
    		    System.out.println(SQL_Func);
    		    ResultSet Funcs_RS = state.executeQuery(SQL_Func);
   		    
   		    //definimos un arreglo para los resultados
   		    
   		    ArrayList<String> productos = new ArrayList<String>();
   		   
   		    //recorremos los resultados de la consulta
   		    while(Funcs_RS.next()){        	   
           	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
   		    	productos.add(Funcs_RS.getString("prod_id")+";;"+Funcs_RS.getString("prod_pn_tli_codfab")+";;"+Funcs_RS.getString("estados_vig_novig_id")+";;"+Funcs_RS.getString("estados_vig_novig_nombre")+";;"+Funcs_RS.getString("prod_desc_corto")+";;"+Funcs_RS.getString("func_nombre"));
           	    
       	    }
   		    	
   		    Funcs_RS.close();
   		  	state.close();
   		    conexion.close();
                   
            request.setAttribute("productos", productos);
             
   		}catch(Exception ex){
   			ex.printStackTrace();
   		    out.println("ERROR "+ex);
   		}
   			
   		RequestDispatcher rd = request.getRequestDispatcher("m1.jsp");
           rd.forward(request, response);
   		
   	}


}