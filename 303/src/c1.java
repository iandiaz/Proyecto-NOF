

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
 * Servlet implementation class c1
 */
@WebServlet("/c1")
public class c1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public c1() {
    
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
  	
  	// logout
  	if(request.getParameter("logout") !=  null){
  			Controlador.eraseCookie(request, response);
  			response.sendRedirect("/001/");	
  			return;
  	}
  	//fin logout
  	
  	//////////////////////////////////////////////////
  	////////DEFINE PARAMETROS DE USUARIO//////////////

  	request.setAttribute("modname", Controlador.getNameModulo(Constantes.MODULO));
  	
  	request.setAttribute("usuname", Controlador.getUsunameSession(request));
  	
  	
  	if(request.getParameter("grabar") !=  null){
  		try {
  			
  				String estticket = request.getParameter("estticket");
  		    	String tipoticket = request.getParameter("tipoticket");
  		    	String f1 = request.getParameter("f1");
  		    	String f2 = request.getParameter("f2");
  		    	String emp = request.getParameter("emp");
  		    	
  		    	response.sendRedirect("c2?estticket="+estticket+"&tipoticket="+tipoticket+"&f1="+f1+"&f2="+f2+"&emp="+emp);	
  	      		return ;
  	    	
  		}catch(Exception ex){
  		    ex.printStackTrace();
  		}
  		
  	}
  		
  	
  	
  	Statement state = null;
  	try {
  		//import java.io.IOException;
  		Class.forName("com.mysql.jdbc.Driver");
  		Connection conexion=(Connection) DriverManager.getConnection
  	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
  		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
  		 
  		//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
  		
  		 //traemos las empresas registradoas en sistema
  		 ArrayList<String> emps = new ArrayList<String>();
   		
  		 String find_emps_sql="SELECT empresas_id,empresas_nombrenof FROM empresas ORDER BY empresas_nombrenof ";
  		 ResultSet EMPS_RS = state.executeQuery(find_emps_sql);
  	     while(EMPS_RS.next()){
  	    	 emps.add(EMPS_RS.getString("empresas_id")+";"+EMPS_RS.getString("empresas_nombrenof"));	      	
  	     }
  		  	
        	//traemos los tipo ticket 
        	ArrayList<String> tptick = new ArrayList<String>();
       		
        	String find_tptickt="SELECT tipo_ticket_id,tipo_ticket_nombre FROM tipo_ticket ";
      	ResultSet TPTICKT_RS = state.executeQuery(find_tptickt);
        	while(TPTICKT_RS.next()){
        		tptick.add(TPTICKT_RS.getString("tipo_ticket_id")+";"+TPTICKT_RS.getString("tipo_ticket_nombre"));	      	
        	}
        	
  			
  		request.setAttribute("tptick", tptick);
  		
  		request.setAttribute("emps", emps);
  		
  		
  	  conexion.close();
              
        
  	}catch(Exception ex){
  	    out.println("Error "+ex);
  	}
  	
  		
  		RequestDispatcher rd = request.getRequestDispatcher("c1.jsp");
          rd.forward(request, response);
  		
  	}

}
