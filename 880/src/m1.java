

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
        // TODO Auto-generated constructor stub
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
		//System.out.println("1");
		
		if(request.getParameter("logout") !=  null){
			Controlador.eraseCookie(request, response);
				response.sendRedirect("/001/");	
				return;
		}
		request.setAttribute("usuname", Controlador.getUsunameSession(request));
		request.setAttribute("modname", Controlador.getNameModulo("880"));
		
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 Statement state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
    		 String SQL_adm = "SELECT" + 
    		 		"	`adm_periodos_contables`.`admpc_mes`, " + 
    		 		"	`adm_periodos_contables`.`admpc_year`, " + 
    		 		"	`adm_periodos_contables`.`estados_vig_novig_id`, " + 
    		 		"	`adm_periodos_contables`.`admpc_id` " + 
    		 		" FROM " +  
    		 		"	`adm_periodos_contables` "
    		 		+ "	ORDER BY `adm_periodos_contables`.`admpc_year` DESC, `adm_periodos_contables`.`admpc_mes` DESC "; 		    	
 		    System.out.println(SQL_adm);
 		    ResultSet adm_rs = state.executeQuery(SQL_adm);
		    //definimos un arreglo para los resultados
		    
		    ArrayList<String> adm_r = new ArrayList<String>();
		   
		    //recorremos los resultados de la consulta
		    while(adm_rs.next()){        	   
        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	adm_r.add(adm_rs.getString("admpc_id")+"/"+adm_rs.getString("admpc_year")+"/"+adm_rs.getString("admpc_mes")+"/"+adm_rs.getString("estados_vig_novig_id"));
        	    
    	    }
		    	
		  state.close();
          conexion.close();
                
          String[] ar_adm = new String[adm_r.size()];
          for(int x=0; x < adm_r.size(); x++){
        	  ar_adm[x]=adm_r.get(x);
          }
                
          request.setAttribute("ar_adm", ar_adm);
		}catch(Exception ex){
		    out.println("Error: "+ex);
		}
		RequestDispatcher rd = request.getRequestDispatcher("m1.jsp");
        rd.forward(request, response);
		
	}

}
