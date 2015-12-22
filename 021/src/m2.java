

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class m2
 */
@WebServlet("/m2")
public class m2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public m2() {
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
	
	String p=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), "211");
	
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
		    
    		 if(request.getParameter("grabar") !=  null){
    			 
    			String marca_nombre = request.getParameter("marca_nombre");
 		    	String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
 		    	String sql_valmarca="SELECT * FROM marca WHERE marca_nombre='"+marca_nombre.toUpperCase()+"' AND marca_id<>'"+request.getParameter("id_m")+"'";
	    		ResultSet rs_valmarca = state.executeQuery(sql_valmarca);
	    		
	    		if(rs_valmarca.next()){
	    			response.sendRedirect("menu?Exito=NOOK");
	    			return;
	    		}else{
 		    	//validamos que no este otra marca con el mismo nombre 
 		    	
    			 if(Constantes.SYNCDB==1){
    				
 	    			//actualizamos grupo en birt
 	    			
 	    			ConBirt birtBD= new ConBirt();
 	    			 String estado_v_nv="VIGENTE";
	       			 if(estados_vig_novig_id.equals("2")) estado_v_nv="NO VIGENTE";
 	       			 
 	       			 String insert_birt=" UPDATE [MARCA] "
 	       			 		+ "	SET [MARCA].[MARC_ESTADO] = '"+estado_v_nv+"',"
 	       			 		+ "	 [MARCA].[USU_ID_UM] = "+Controlador.getUsuIDSession(request)+","
 	       			 		+ "	 [MARCA].[MARC_NOMBRE] = '"+marca_nombre.toUpperCase()+"' ,"
 	       			 		+ "	 [MARCA].[MARC_FECHA_UM] = getdate() "
 	       			 		+ "	WHERE"
 	       			 		+ "		[MARCA].[MARC_ID] = "+request.getParameter("id_m");
 	       		 System.out.println("BIRT: "+insert_birt);
 	       		      birtBD.ConsultaINUP(insert_birt);
 	       		     
 	       		     
 	       		        birtBD.Desconectar();
 	       		        
 	       			}
    			 
    			 
    			 
    			 state = (Statement) ((java.sql.Connection) conexion).createStatement();
 				String UPDATE = "UPDATE marca SET "
 						+ "	estados_vig_novig_id="+estados_vig_novig_id
 						+ "	,accion_alertada=0"
 						+ "	,ult_idper_exec=211 "
 						+ "	,marca_nombre='"+marca_nombre+"' "
 						+ "	,fecmod=now() "
 						+ "	,modificador='"+Controlador.getUsuIDSession(request)+"' "
 						+ "	WHERE marca_id="+request.getParameter("id_m");
 				System.out.println("delete : "+UPDATE);
 				state.executeUpdate(UPDATE);
 				
 				state.close();
 				conexion.close();
 				
 				response.sendRedirect("menu?Exito=OK");
 			    return;
    		 }
 			}
    		 
    		 
    		 String SQL_ESTADOS = "SELECT * FROM estados_vig_novig";
 		    ResultSet ESTADOS_RS = state.executeQuery(SQL_ESTADOS);
 		    
 		    //definimos un arreglo para los resultados
 		    
 		    ArrayList<String> estados = new ArrayList<String>();
 		   
 		    //recorremos los resultados de la consulta
 		    while(ESTADOS_RS.next()){        	   
         	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
 		    	estados.add(ESTADOS_RS.getString("estados_vig_novig_id")+"/"+ESTADOS_RS.getString("estados_vig_novig_nombre"));
         	    
     	    }
 		    	
           String[] ar_estados = new String[estados.size()];
           for(int x=0; x < estados.size(); x++){
            	ar_estados[x]=estados.get(x);
           }
           request.setAttribute("ar_estados", ar_estados);
    		
		    String SQL_MARCA = "" 
		    		+ "SELECT marca_id,marca_nombre,estados_vig_novig_nombre,m.estados_vig_novig_id,"
		    		+ "		CONCAT( "
		    		+ "			`usuarios`.`Usuarios_nombre`, "
		    		+ "			' ',"
		    		+ "			`usuarios`.`Usuarios_ape_p` "
		    		+ "		) AS creador, "
		    		+ "		DATE_FORMAT(m.feccreacion,'%d-%m-%Y %H:%i:%s') AS feccreacion, "
		    		+ "		IF ( "
		    		+ "			m.fecmod IS NULL,"
		    		+ "			' ',"
		    		+ "			DATE_FORMAT(m.fecmod, '%d-%m-%Y %H:%i:%s')"
		    		+ "		) AS fecmod,"
		    		+ "		IF ("
		    		+ "			m.modificador IS NULL,"
		    		+ "			' ',"
		    		+ "			CONCAT( "
		    		+ "			`u2`.`Usuarios_nombre`,"
		    		+ "			' ',"
		    		+ "			`u2`.`Usuarios_ape_p`"
		    		+ "			)"
		    		+ "		) AS modificador "
		    		+ " FROM marca m "
		    		+ " 	INNER JOIN estados_vig_novig e ON e.estados_vig_novig_id=m.estados_vig_novig_id"
		    		+ " 	INNER JOIN `usuarios` ON `m`.`creador` = `usuarios`.`Usuarios_id` "
		    		+ " 	LEFT JOIN `usuarios` u2 ON `m`.`modificador` = `u2`.`Usuarios_id` "
		    		+ " WHERE marca_id="+Integer.parseInt(request.getParameter("id_m"));
		    System.out.println(SQL_MARCA);
		    ResultSet Marca_RS = state.executeQuery(SQL_MARCA);
		    if(Marca_RS.next()){ 
		    	
		    	request.setAttribute("marca_id",Marca_RS.getString("marca_id"));
		    	request.setAttribute("marca_nombre",Marca_RS.getString("marca_nombre"));
		    	request.setAttribute("estados_vig_novig_nombre",Marca_RS.getString("estados_vig_novig_nombre"));
		    	
		    	String fec_crea=Marca_RS.getString("feccreacion");
		    	String user_crea=Marca_RS.getString("creador");
		    	String fec_mod=Marca_RS.getString("fecmod");
		    	String user_mod=Marca_RS.getString("modificador") ;
		    	
		    	
		    	request.setAttribute("fec_crea", (fec_crea)+"");
	            request.setAttribute("fec_mod", (fec_mod)+"");
	            request.setAttribute("user_mod", (user_mod)+"");
	            request.setAttribute("user_crea", (user_crea)+"");
	            request.setAttribute("estados_vig_novig_id", (Marca_RS.getString("estados_vig_novig_id"))+"");
	            
	            
    	    }
		    Marca_RS.close();
		  state.close();	    
          conexion.close();
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("Error "+ex);
		}
		RequestDispatcher rd = request.getRequestDispatcher("m2.jsp");
        rd.forward(request, response);
		
	}

}
