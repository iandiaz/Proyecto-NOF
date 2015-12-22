

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
 * Servlet implementation class i1
 */
@WebServlet("/i1")
public class i1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public i1() {
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

		String p=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), "213");
		
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
		
		String id_iusuario=Controlador.getUsuIDSession(request);
		
		//grabar
		if(request.getParameter("grabar") !=  null){
			Statement stategrabar = null;
			Statement stateval = null;
			try {
				String marca_nombre = request.getParameter("marca_nombre");
		    	String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		stateval = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		String sql_valmarca="SELECT * FROM marca WHERE marca_nombre='"+marca_nombre.toUpperCase()+"'";
	    		ResultSet rs_valmarca = stateval.executeQuery(sql_valmarca);
	    		//stateval.close();
	    		if(rs_valmarca.next()){
	    			response.sendRedirect("menu?Exito=NOOK");
	    			return;
	    		}else{
	    			
	    			if(Constantes.SYNCDB==1){
	       			 ConBirt birtBD= new ConBirt();
	       			 
	       			 String estado_v_nv="VIGENTE";
	       			 if(estados_vig_novig_id.equals("2")) estado_v_nv="NO VIGENTE";
	       			
	       			 String insert_birt=" INSERT INTO [MARCA] ("
	       			 		+ "		[MARCA].[MARC_ESTADO],"
	       			 		+ "		[MARCA].[MARC_NOMBRE],"
	       			 		+ "		[MARCA].[USU_ID_UM],"
	       			 		+ "		[MARCA].[MARC_FECHA_UM]"
	       			 		+ "	)"
	       			 		+ "	VALUES"
	       			 		+ "		('"+estado_v_nv+"', '"+marca_nombre.toUpperCase()+"', "+id_iusuario+", getdate())";
	       			System.out.println("BIRT: "+insert_birt);
	       		      birtBD.ConsultaINUP(insert_birt);
	       		     
	       		      
	       		        birtBD.Desconectar();
	       			}
	    			
	    			
			    String SQL_INSERT = "INSERT INTO marca (ult_idper_exec,marca_nombre, estados_vig_novig_id,feccreacion,creador) VALUES (213,'"+marca_nombre.toUpperCase()+"',"+estados_vig_novig_id+",now(),"+id_iusuario+")";
			    System.out.println(SQL_INSERT);
			    stategrabar.executeUpdate(SQL_INSERT);
			    stateval.close();
    			stategrabar.close();
	            conexion.close(); 
	            
	            //RequestDispatcher rd_up = request.getRequestDispatcher("menu?Exito=OK");
	            //rd_up.forward(request, response);
	            
	            response.sendRedirect("menu?Exito=OK");
	            
	            return;
	    		}
			}catch(Exception ex){
				ex.printStackTrace();
			    out.println("Error "+ex);
			}
			
		}
		
		Statement state = null;
		Statement statecor = null;		
		ResultSet ESTADOS_RS = null;
		ResultSet CORRELATIVO_RS = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 statecor = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
		    String SQL_ESTADOS = "SELECT * FROM estados_vig_novig";
		    ESTADOS_RS =  state.executeQuery(SQL_ESTADOS);
		    
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
        
          String last_id_marca_sql="SELECT"
		    		+ " 	`marca`.`marca_id`"
		    		+ " FROM"
		    		+ " 	`marca`"
		    		+ " ORDER BY"
		    		+ " 	`marca`.`marca_id` DESC"
		    		+ " LIMIT 1";
          System.out.println("correlativo : "+last_id_marca_sql);
          CORRELATIVO_RS =  statecor.executeQuery(last_id_marca_sql);
         
          int correlativo=1;
		  if(CORRELATIVO_RS.next()) correlativo=CORRELATIVO_RS.getInt("marca_id")+1;
		  System.out.println("correlativo : "+correlativo);
          request.setAttribute("correlativo", correlativo+"");
         
          
          ESTADOS_RS.close();
		  state.close();
         
          statecor.close();
          conexion.close();
          
		}catch(Exception ex){
		    out.println("ERROR "+ex);
		    ex.printStackTrace();
		}
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("i1.jsp");
        rd.forward(request, response);
		
        
        
	}

}
