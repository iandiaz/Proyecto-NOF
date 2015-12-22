
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
 * Servlet implementation class iguia_search
 */
@WebServlet("/I01_001")
public class I01_001 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public I01_001() {
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
		Statement state_borra = null;
		
		ResultSet Alertas_RS = null;
		ResultSet RS_FAC = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		state_borra = (Statement) ((java.sql.Connection) conexion).createStatement();
    		  			
    		
    		
    		String SQL_idtrasingresados = "(select `detalle_823`.`tras_id` from `detalle_823` where `detalle_823`.`estados_vig_novig_id`=1)"
    				+ "	UNION"
    				+ "	(select `detalle_824`.`tras_id` from `detalle_824` where `detalle_824`.`estados_vig_novig_id`=1)"; 
    		System.out.println(SQL_idtrasingresados);
 		    ResultSet TRASEXIST_RS = state.executeQuery(SQL_idtrasingresados);
    		
    		 ArrayList<String> id_tras_existentes= new ArrayList<String>();
   		    while(TRASEXIST_RS.next()){   
   		    	id_tras_existentes.add(TRASEXIST_RS.getString("tras_id"));
   		    	
   		    	
   			}
    		
    		System.out.println("IDTRAS EXISTENTES:"+id_tras_existentes);
    		ConBirt birtBD2= new ConBirt();
    		
    		String query_birtt="SELECT "
    				+ "		c.CLPR_ID,"
    				+ "		MAX(c.CLPR_NOMBRE_FANTASIA) AS CLPR_NOMBRE_FANTASIA,"
    				+ "		d.DIRE_DIRECCION AS ORIGEN,"
    				+ "		MAX(d.DIRE_ID) AS ORIGEN_ID,"
    				+ "		MAX (c1.CLPR_NOMBRE_FANTASIA) AS CLPR_NOMBRE_FANTASIA_DEST,"
    				+ "		d1.DIRE_DIRECCION AS DESTINO, "
    				+ "		MAX(d1.DIRE_ID) AS DESTINO_ID,"
    				+ "		t.TRAS_ID  "
    				+ "	FROM"
    				+ "		TRASLADO t"
    				+ "	INNER JOIN UBICACION u ON t.TRAS_UBI_ID_ORIGEN = u.UBI_ID"
    				+ "	INNER JOIN DIRECCION d ON d.DIRE_ID = u.DIRE_ID"
    			
    				+ "	INNER JOIN UBICACION u1 ON t.TRAS_UBI_ID_DESTINO = u1.UBI_ID"
    				+ "	INNER JOIN DIRECCION d1 ON d1.DIRE_ID = u1.DIRE_ID"
    				+ "	INNER JOIN CLIENTEPROVEEDOR c ON d.CLPR_ID = c.CLPR_ID"
    				+ " INNER JOIN CLIENTEPROVEEDOR c1 ON d1.CLPR_ID = c1.CLPR_ID"
    				+ "	WHERE"
    				+ "		d.DIRE_ID <> d1.DIRE_ID"
    				+ "	AND t.TRAS_FECHA>'2015-03-15 00:00:00' "
    				+ "	GROUP BY"
    				+ "		c.CLPR_ID,"
    				+ "		d1.DIRE_DIRECCION,"
    				+ "		d.DIRE_DIRECCION,"
    				+ "		t.TRAS_ID "
    				+ "	ORDER BY"
    				+ "		CLPR_NOMBRE_FANTASIA,"
    				+ "		d.DIRE_DIRECCION,"
    				+ "		d1.DIRE_DIRECCION";
    		System.out.println(query_birtt);
            ResultSet QueryBirt2= birtBD2.Consulta(query_birtt);     
		    if(request.getAttribute("filtro")!=null){
		    	request.setAttribute("filtro",request.getAttribute("filtro"));
		    }
		    ArrayList<String> traslados = new ArrayList<String>();
		    while(QueryBirt2.next()){    
		    	
		    	boolean lomuestra=true; 
		    	/*
		    	for(String id_tras_existente:id_tras_existentes){
		    		if(QueryBirt2.getString("TRAS_ID").equals(id_tras_existente)){
		    			lomuestra=false;
		    			break;
		    		}
		    	}
		    	*/
		    	if(id_tras_existentes.contains(QueryBirt2.getString("TRAS_ID"))){
		    		lomuestra=false;
	    			
		    	}
		    	
		    	if(lomuestra){
		    		addUnique(traslados, QueryBirt2.getString("CLPR_ID")+"/"+
	    					QueryBirt2.getString("CLPR_NOMBRE_FANTASIA")+"/"+
	    					QueryBirt2.getString("ORIGEN").replace("/"," ")+"/"+
	    					QueryBirt2.getString("DESTINO").replace("/"," ")+"/"+
	    					QueryBirt2.getString("ORIGEN_ID").replace("/"," ")+"/"+
	    					QueryBirt2.getString("DESTINO_ID").replace("/"," ")+"/"+
	    					QueryBirt2.getString("CLPR_NOMBRE_FANTASIA_DEST").replace("/"," "));
	
		    	}
		    	
    	    }
		    QueryBirt2.close();
		    state.close();
            conexion.close();
                
          
             
            
          
            request.setAttribute("traslados", traslados);
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("Error "+ex);
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("I01_001.jsp");
        rd.forward(request, response);
		
	}
	
	public void addUnique(ArrayList<String> lista, String elemento){

		if(!lista.contains(elemento))
		lista.add(elemento);
		}

}
