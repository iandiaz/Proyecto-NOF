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

import Constantes.Constantes;
import Constantes.Controlador;

/**
 * Servlet implementation class e1
 */
@WebServlet("/e1")
public class e1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public e1() {
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
		//System.out.println("1");
		
		if(request.getParameter("logout") !=  null){
				Controlador.eraseCookie(request, response);
				response.sendRedirect("/001/");	
				return;
		}
					
		request.setAttribute("usuname", Controlador.getUsunameSession(request));
		Statement state = null;
		Statement state1 = null;
		ResultSet RS_DTE = null;
		ResultSet RS_DATOS = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		state1 = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 
		 	String SQL_DTE = "select "
    				+ "				count(*) as cuenta"
    				+ "				, modulos.`Modulos_nombre`"
    				+ "				, `dte_encabezado`.`Modulo`"
    				+ "		  from  `dte_encabezado`  "
    				+ "			inner join `modulos` on modulos.`Modulos_codigo`=dte_encabezado.`Modulo` "
    				+ " WHERE modulos.`Modulos_codigo` IN ('821','822','823','824') "
    				+ "			group by dte_encabezado.`Modulo` ";
    		
    		System.out.println(SQL_DTE);
			RS_DTE =  state.executeQuery(SQL_DTE);
 		    
 		    ArrayList<String> alertas = new ArrayList<String>();
 		   
 		    while(RS_DTE.next()){
		       	   
         	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
 		    	alertas.add(RS_DTE.getString("Modulo")+"/"+RS_DTE.getString("Modulos_nombre")+"/"+RS_DTE.getString("cuenta"));
 		    	
     	    }
 		   RS_DTE.close();
		   state.close();
           conexion.close();
                
          String[] ar_alertas = new String[alertas.size()];
          for(int x=0; x < alertas.size(); x++){
        	  ar_alertas[x]=alertas.get(x);
          }
             
          request.setAttribute("ar_alertas", ar_alertas);
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("Error "+ex);
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("e1.jsp");
        rd.forward(request, response);
		
	}

}
