

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class c1
 */
@WebServlet("/c1")
public class c1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public c1() {
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
	
	// logout
	if(request.getParameter("logout") !=  null){
			Controlador.eraseCookie(request, response);
			response.sendRedirect("/001/");	
			return;
	}
	//fin logout
	
	
	String p114=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), "114");
	
	
	if(p114.equals("0")){
		response.sendRedirect("/001/");	
		return;
	}
	
	//////////////////////////////////////////////////
	////////DEFINE PARAMETROS DE USUARIO//////////////

	request.setAttribute("modname", Controlador.getNameModulo());
	
	request.setAttribute("usuname", Controlador.getUsunameSession(request));
	
	
	
	
	
	if(request.getParameter("grabar") !=  null){
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
	   		Connection conexion=(Connection) DriverManager.getConnection
	 	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	  		  	
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
		
		 //buscamos todos los activos actuales. 
		 
		 
		 
		 ConBirt birtBD= new ConBirt();
			 
			
		String  sql_maquinas_map=""
				+ "	SELECT" + 
				"		DIRECCION.DIRE_ID," + 
				"		ACTIVO.ALT_ID" + 
				"	FROM" + 
				"		[ACTIVO]" + 
				"	INNER JOIN UBICACION ON UBICACION.UBI_ID = ACTIVO.UBI_ID" + 
				"	INNER JOIN DIRECCION ON DIRECCION.DIRE_ID = UBICACION.DIRE_ID" + 
				"	WHERE "+ 
				"		ACTIVO.ALT_ESTADO = 'VIGENTE' " +
				"		AND DIRECCION.CLPR_ID = '"+Controlador.getEmpIdUsu(Controlador.getUsuIDSession(request))+"' " +
				"	ORDER BY" + 
				"		DIRECCION.DIRE_ID," + 
				"		ACTIVO.ALT_ID" ;
		System.out.println(sql_maquinas_map);
		
		ResultSet maquinas_map_rs =birtBD.Consulta(sql_maquinas_map);
		
		HashMap<String, ArrayList<String>> datamapa = new HashMap<String, ArrayList<String>>();
		
		while(maquinas_map_rs.next()){
			
  			ArrayList<String> activosDir=datamapa.get(maquinas_map_rs.getString("DIRE_ID"));
  			if(activosDir==null) activosDir= new ArrayList<String>();
  			
  			activosDir.add(maquinas_map_rs.getString("ALT_ID"));
  			
  			datamapa.remove(maquinas_map_rs.getString("DIRE_ID"));
  			datamapa.put(maquinas_map_rs.getString("DIRE_ID"), activosDir);
		
		}
		
		
		String find_dirs_sql="select "
							+ "	DIRE_ID"
							+ "	,dire_lat"
							+ "	,dire_lon"
							+ "	FROM direccion ";
				
		System.out.println(find_dirs_sql);
		ResultSet DIRS_RS = state.executeQuery(find_dirs_sql);
		
		HashMap<String, String> dirsLat = new HashMap<String, String>();
		HashMap<String, String> dirsLon = new HashMap<String, String>();
		
		while(DIRS_RS.next()){
			
			dirsLat.put(DIRS_RS.getString("DIRE_ID"), DIRS_RS.getString("dire_lat"));
			dirsLon.put(DIRS_RS.getString("DIRE_ID"), DIRS_RS.getString("dire_lon"));
			
		}
		
		
		
		//metemos en un hashmap la tabla direccione son sus coordenadas. 
		
		 
		
      	request.setAttribute("datamapa", datamapa);
    	request.setAttribute("dirsLat", dirsLat);
    	request.setAttribute("dirsLon", dirsLon);
	
	    birtBD.Desconectar();
		
		
	  conexion.close();
          
      
	}catch(Exception ex){
		ex.printStackTrace();
	    out.println("Error "+ex);
	}
	
		
		RequestDispatcher rd = request.getRequestDispatcher("c1.jsp");
        rd.forward(request, response);
		
	}

}
