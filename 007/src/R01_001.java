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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Constantes.Constantes;
import Constantes.Controlador;

/**
 * Servlet implementation class rptclpr
 */
@WebServlet("/R01_001")
public class R01_001 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public R01_001() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean sesion_valida=Controlador.validateSession(request,response);
		
		if(!sesion_valida) response.sendRedirect("/001/");
		else {
			 mt(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!Controlador.validateSession(request,response)) response.sendRedirect("/001/");
      
	
		/////////////////////GENERAR ////////////////////////
        
        if(request.getParameter("informe").equals("1")){
         	RequestDispatcher a = request.getRequestDispatcher("R_GEN1");
      		a.forward(request, response);
      		return;
		}
        if(request.getParameter("informe").equals("2")){
        	RequestDispatcher a = request.getRequestDispatcher("R_GEN2");
      		a.forward(request, response);
      		return;
		}
       
	}
	public void mt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
		
		///////////////////////////////////////////
		//////DEFINE DESLOGEO//////////////////////
		
		Cookie [] cookies=request.getCookies();
		if(request.getParameter("logout") !=  null){
				Controlador.eraseCookie(request, response);
				response.sendRedirect("/001/");
				return;
			
		}
		
		
		//////////////////////////////////////////////////
		////////DEFINE PARAMETROS DE USUARIO//////////////
		
		
		request.setAttribute("usuname", Controlador.getUsunameSession(request));
		
		//////////////////////////////////////////////////
		
		String PR1=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), Constantes.PREP1);
		String PR2=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), Constantes.PREP2);
		request.setAttribute("PR1", PR1);
		request.setAttribute("PR2", PR2);
		
		
		
		Statement state = null;
		
		ResultSet ESTCLPR_RS=null;
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conexion=(Connection) DriverManager.getConnection
	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
		
		//::::::::::::::::::::::::::::::::::::::::::sql estado clpr para select option::::::::::::::::::::::::::::::::::::::  
		  state = (Statement) ((java.sql.Connection) conexion).createStatement();
		  String SQL_estclpr = "SELECT * FROM estado_clpr WHERE estados_vig_novig_id=1 ORDER BY Estado_Clpr_nombre";
		    ESTCLPR_RS =  state.executeQuery(SQL_estclpr);		    
		    //definimos un arreglo para los resultados		    
		    ArrayList<String> estclpr = new ArrayList<String>();		   
		    //recorremos los resultados de la consulta
		    while(ESTCLPR_RS.next()){        	   
  	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	estclpr.add(ESTCLPR_RS.getString("Estado_Clpr_id")+"/"+ESTCLPR_RS.getString("Estado_Clpr_nombre"));
  	    }
		  ESTCLPR_RS.close();
		  String[] ar_estclpr = new String[estclpr.size()];
        for(int x=0; x < estclpr.size(); x++){
      	  ar_estclpr[x]=estclpr.get(x);
        }
              
        request.setAttribute("ar_estclpr", ar_estclpr);
		
	
      //::::::::::::::::::::::::::::::::::::::::::sql usuarios internos para responsable select option::::::::::::::::::::::::::::::::::::::  
	  state = (Statement) ((java.sql.Connection) conexion).createStatement();
	  String SQL_resp = "SELECT "
	  		+ "		`usuarios`.`Usuarios_id`,"
	  		+ "		CONCAT_WS(' ',`usuarios`.`Usuarios_nombre`,`usuarios`.`Usuarios_ape_p`,`usuarios`.`Usuarios_ape_m`) AS Usuarios_nombre "
	  		+ "	FROM"
	  		+ "		`usuarios`"
	  		+ "	WHERE "
	  		+ "		`usuarios`.`tipo_usu_id` = 1 "
	  		+ "	ORDER BY `usuarios`.`Usuarios_nombre` ";
	  
	    ResultSet resp_RS = state.executeQuery(SQL_resp);		    
	    //definimos un arreglo para los resultados		    
	    ArrayList<String> responsables = new ArrayList<String>();		   
	    //recorremos los resultados de la consulta
	    while(resp_RS.next()){        	   
	    	responsables.add(resp_RS.getString("Usuarios_id")+"/"+resp_RS.getString("Usuarios_nombre"));
	    }
	  String[] ar_responsables = new String[responsables.size()];
      for(int x=0; x < responsables.size(); x++){
    	  ar_responsables[x]=responsables.get(x);
      }
            
      request.setAttribute("ar_responsables", ar_responsables);
	        
		conexion.close();
        RequestDispatcher a = request.getRequestDispatcher("R01_001.jsp");
    	a.forward(request, response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

}
