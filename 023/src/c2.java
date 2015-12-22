

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
   		
   	PrintWriter out = response.getWriter();		
   	
   	if(request.getParameter("logout") !=  null){
   			Controlador.eraseCookie(request, response);
   			response.sendRedirect("/001/");	
   			return;
   	}

   	//////////////////////////////////////////////////
   	////////VERIFICAMOS PERMISO ASOCIADO/////////////
   	
   	String p=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), Constantes.PCONSULTAR);
   	
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
   		try {
   			//import java.io.IOException;
   			Class.forName("com.mysql.jdbc.Driver");
       		Connection conexion=(Connection) DriverManager.getConnection
       	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
       		Statement state = (Statement) ((java.sql.Connection) conexion).createStatement();
   		    
       		 String SQL_PROD = ""
   		    		+ "SELECT prod_id,prod_pn_tli_codfab,prod_cod_bar_fab"
   		    		+ "	,estados_vig_novig_nombre"
   		    		+ "	,prod_vida_util_contable"
   		    		+ "	,ma.marca_nombre"
   		    		+ "	,fu.func_nombre"
   					+ "	,prod_tipo"
		    		+ "	,prod_desc_corto,prod_vida_util_imp"
   		    		+ "	,prod_desc_largo,prod_vida_util_ano,prod_observacion,prod_cm_contable "
   		    		+ "	,estp.est_prod_name"
   		    		+ "	,IF(prod_cuc IS NULL,'',prod_cuc) as prod_cuc, "
   		    		
   		    		+ "		CONCAT( "
   		    		+ "			`usuarios`.`Usuarios_nombre`, "
   		    		+ "			' ',"
   		    		+ "			`usuarios`.`Usuarios_ape_p` "
   		    		+ "		) AS creador, "
   		    		+ "		DATE_FORMAT(f.feccreacion,'%d-%m-%Y %H:%i:%s') AS feccreacion, "
   		    		+ "		IF ( "
   		    		+ "			f.fecmod IS NULL,"
   		    		+ "			' ',"
   		    		+ "			DATE_FORMAT(f.fecmod, '%d-%m-%Y %H:%i:%s')"
   		    		+ "		) AS fecmod,"
   		    		+ "		IF ("
   		    		+ "			f.modificador IS NULL,"
   		    		+ "			' ',"
   		    		+ "			CONCAT( "
   		    		+ "			`u2`.`Usuarios_nombre`,"
   		    		+ "			' ',"
   		    		+ "			`u2`.`Usuarios_ape_p`"
   		    		+ "			)"
   		    		+ "		) AS modificador "
   		    		+ " FROM producto f "
   		    		+ " 	INNER JOIN marca ma ON ma.marca_id=f.marca_id"
   		    		+ " 	INNER JOIN funcionalidad fu ON fu.func_id=f.func_id"
   		    		+ " 	INNER JOIN estados_vig_novig e ON e.estados_vig_novig_id=f.estados_vig_novig_id"
   		    		+ " 	INNER JOIN estado_producto estp ON estp.est_prod_id=f.est_prod_id"
   		    		
   		    		+ " 	INNER JOIN `usuarios` ON `f`.`creador` = `usuarios`.`Usuarios_id` "
   		    		+ " 	LEFT JOIN `usuarios` u2 ON `f`.`modificador` = `u2`.`Usuarios_id` "
   		    		+ " WHERE prod_id="+Integer.parseInt(request.getParameter("id_p"));
   		    System.out.println(SQL_PROD);
   		    ResultSet Prod_RS = state.executeQuery(SQL_PROD);
   		    if(Prod_RS.next()){ 
		    	
		    	request.setAttribute("prod_id",Prod_RS.getString("prod_id"));
		    	request.setAttribute("prod_pn_tli_codfab",Prod_RS.getString("prod_pn_tli_codfab"));
		    	request.setAttribute("prod_cod_bar_fab",Prod_RS.getString("prod_cod_bar_fab"));
		    	request.setAttribute("prod_vida_util_contable",Prod_RS.getString("prod_vida_util_contable"));
		    	
		    	request.setAttribute("prod_desc_corto",Prod_RS.getString("prod_desc_corto"));
		    	
		    	request.setAttribute("prod_vida_util_imp",Prod_RS.getString("prod_vida_util_imp"));
		    	
		    	request.setAttribute("prod_desc_largo",Prod_RS.getString("prod_desc_largo"));
		    	request.setAttribute("prod_cuc",Prod_RS.getString("prod_cuc"));
		    	
		    	request.setAttribute("prod_vida_util_ano",Prod_RS.getString("prod_vida_util_ano"));
		    	
		    	request.setAttribute("prod_observacion",Prod_RS.getString("prod_observacion"));
		    	request.setAttribute("marca_nombre",Prod_RS.getString("marca_nombre"));
		    	
		    	request.setAttribute("func_nombre",Prod_RS.getString("func_nombre"));
		    	request.setAttribute("prod_cm_contable",Prod_RS.getString("prod_cm_contable"));
		    	
		    	request.setAttribute("prod_tipo",Prod_RS.getString("prod_tipo"));
		    	request.setAttribute("est_prod_name",Prod_RS.getString("est_prod_name"));

		    	
		    	request.setAttribute("estados_vig_novig_nombre",Prod_RS.getString("estados_vig_novig_nombre"));
		    	
		    	String fec_crea=Prod_RS.getString("feccreacion");
		    	String user_crea=Prod_RS.getString("creador");
		    	String fec_mod=Prod_RS.getString("fecmod");
		    	String user_mod=Prod_RS.getString("modificador") ;
		    	
		    	
		    	request.setAttribute("fec_crea", (fec_crea)+"");
	            request.setAttribute("fec_mod", (fec_mod)+"");
	            request.setAttribute("user_mod", (user_mod)+"");
	            request.setAttribute("user_crea", (user_crea)+"");
    	    }
   		 Prod_RS.close();
   		   
   		  state.close();	    
             conexion.close();
   		}catch(Exception ex){
   			ex.printStackTrace();
   		    out.println("Error "+ex);
   		}
   		RequestDispatcher rd = request.getRequestDispatcher("c2.jsp");
           rd.forward(request, response);
   		
   	}

}
