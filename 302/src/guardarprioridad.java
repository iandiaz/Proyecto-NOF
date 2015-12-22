

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class guardarprioridad
 */
@WebServlet("/guardarprioridad")
public class guardarprioridad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public guardarprioridad() {
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
		
		
		
		Statement state = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 
    		String tick=request.getParameter("tick");
    		String prioridad=request.getParameter("prioridad");
    		String usuInicial=request.getParameter("usu");
    		String r=request.getParameter("r");
    		if(r==null || r.equals("")) r="0";
    		
    		 //::::::::::::::::::::::::::::::::::::::::::fin sql estado clpr para select option::::::::::::::::::::::::::::::::::::::
    		
    		
			String find_tickets_sql="select admt_ticket from adm_tecnicos where admt_usu='"+usuInicial+"' AND admt_ticket='"+tick+"'";
    		ResultSet FINDTICKETS_RS = state.executeQuery(find_tickets_sql);
    		if(FINDTICKETS_RS.next()){
    			//insertamos 
    			String in="update `adm_tecnicos` SET admt_prioridad="+prioridad+" , admt_req_repuesto="+r+" WHERE admt_usu='"+usuInicial+"' AND admt_ticket='"+tick+"'";
    			state.executeUpdate(in);
    		}
    		else{
    			//insertamos 
    			String in="insert into `adm_tecnicos` (`adm_tecnicos`.`admt_ticket`,`adm_tecnicos`.`admt_usu`,`adm_tecnicos`.`admt_prioridad`,`adm_tecnicos`.`admt_req_repuesto`) " + 
    					"values ('"+tick+"','"+usuInicial+"','"+prioridad+"','"+r+"')";
    			state.executeUpdate(in);
    		}
    		
    		
    	
    	
    		
    
    		
    		
    		String resultado="OK";
        		
        		out.write(resultado);
    			
    		
		 	
    		
		  conexion.close();
                
          
		}catch(Exception ex){
		    out.println("Error "+ex);
		}
		
		
	}

}
