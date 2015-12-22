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
 * Servlet implementation class rptperusu
 */
@WebServlet("/R01")
public class R01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public R01() {
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
		
		if(request.getParameter("grabar_excel") !=  null){
			PrintWriter out = response.getWriter();
			String filtros="a=1";
			if(request.getParameter("select_perfil") !=  null && !request.getParameter("select_perfil").equals("") ){
				filtros+="&id_p="+request.getParameter("select_perfil")+"";
				
			}
			if(request.getParameter("select_usuario") !=  null && !request.getParameter("select_usuario").equals("") ){
				filtros+="&id_u="+request.getParameter("select_usuario")+"";
				
			}
			if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
			
			
			//out.write("<script>window.open(\"www.google.com\");</script>");
			
			out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_perfilusu_xls.php?"+filtros+"';setInterval(\"close()\",10000);</script>");
		}
		if(request.getParameter("grabar_pdf") !=  null){
			PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_perfil") !=  null && !request.getParameter("select_perfil").equals("") ){
				filtros+="&id_p="+request.getParameter("select_perfil")+"";
				
			}
			if(request.getParameter("select_usuario") !=  null && !request.getParameter("select_usuario").equals("") ){
				filtros+="&id_u="+request.getParameter("select_usuario")+"";
				
			}
			if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
			
			
			//out.write("<script>window.open(\"www.google.com\");</script>");
			
			out.write("<script>window.location='"+Constantes.URL_SITIO+"generaPDF.php?inf=004-1"+filtros+"';setInterval(\"close()\",10000);</script>");
		}
		
		
		if(request.getParameter("informe").equals("2")){
			
           	RequestDispatcher a = request.getRequestDispatcher("R_GEN2");
      		a.forward(request, response);
      		return;
          
		}
		
		if(request.getParameter("informe").equals("1")){
			
           	RequestDispatcher a = request.getRequestDispatcher("R_GEN1");
      		a.forward(request, response);
      		return;
          
		}
		
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
	   	
	 
	   	
	   	//////////////////////////////////////////////////
	   	////////DEFINE PARAMETROS DE USUARIO//////////////
	   	
	   	request.setAttribute("modname", Controlador.getNameModulo());
	   	
	   	String Perfil_id=Controlador.getPerfilIDSession(request);
	   	
	   	request.setAttribute("usuname", Controlador.getUsunameSession(request));
	   	////////////////////////////////////////////////////////////
	   	//////////////////////////////////////////////////////////

		
		//verificamos permisos 
		
		String p5=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), "36");
		String p284=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), "284");
		
		request.setAttribute("p5", p5);
		request.setAttribute("p284", p284);
				
		
		Statement state = null;
		
		ResultSet PERFILUSU_RS=null;
		ResultSet USUARIOS_RS=null;
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conexion=(Connection) DriverManager.getConnection
	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
		
		
		//::::::::::::::::::::::::::::::::::::::::::sql perfil usuario para select option::::::::::::::::::::::::::::::::::::::  
		  state = (Statement) ((java.sql.Connection) conexion).createStatement();
		  String SQL_perfilusu = "SELECT perfilusu_id,perfilusu_nombre FROM perfilusu WHERE estados_vig_novig_id=1";
		    PERFILUSU_RS =  state.executeQuery(SQL_perfilusu);		    
		    //definimos un arreglo para los resultados		    
		    ArrayList<String> perfilusu = new ArrayList<String>();		   
		    //recorremos los resultados de la consulta
		    while(PERFILUSU_RS.next()){        	   
  	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	perfilusu.add(PERFILUSU_RS.getString("perfilusu_id")+"/"+PERFILUSU_RS.getString("perfilusu_nombre"));
  	    }
		  System.out.println("SIZE LIST: "+perfilusu.size());  	
		  PERFILUSU_RS.close();
		  String[] ar_perfilusu = new String[perfilusu.size()];
        for(int x=0; x < perfilusu.size(); x++){
      	  ar_perfilusu[x]=perfilusu.get(x);
        }
              
        request.setAttribute("ar_perfilusu", ar_perfilusu);
        
    	//::::::::::::::::::::::::::::::::::::::::::sql usuarios para select option::::::::::::::::::::::::::::::::::::::  
		  state = (Statement) ((java.sql.Connection) conexion).createStatement();
		  String SQL_usu = "SELECT "
		  		+ "		CONCAT_WS( "
		  		+ "			' ',"
		  		+ "			`usuarios`.`Usuarios_nombre`,"
		  		+ "			`usuarios`.`Usuarios_ape_p`,"
		  		+ "			`usuarios`.`Usuarios_ape_m`"
		  		+ "		) AS nombre_usuario,"
		  		+ "		`usuarios`.`Usuarios_id`"
		  		+ "	FROM"
		  		+ "		`usuarios`"
		  		+ "	WHERE"
		  		+ "		`usuarios`.`estados_vig_novig_id` = 1 "
		  		+ "	ORDER BY `usuarios`.`Usuarios_nombre`, `usuarios`.`Usuarios_ape_p` ";
		  
		    USUARIOS_RS =  state.executeQuery(SQL_usu);		    
		    //definimos un arreglo para los resultados		    
		    ArrayList<String> usuarios = new ArrayList<String>();		   
		    //recorremos los resultados de la consulta
		    while(USUARIOS_RS.next()){        	   
	    //SI HAY usuarios, ENTONCES GUARDAMOS LOS usuarios
		    	usuarios.add(USUARIOS_RS.getString("Usuarios_id")+"/"+USUARIOS_RS.getString("nombre_usuario"));
		    }
		  USUARIOS_RS.close();
		  String[] ar_usuarios = new String[usuarios.size()];
	      for(int x=0; x < usuarios.size(); x++){
	    	  ar_usuarios[x]=usuarios.get(x);
	      }
	            
	      request.setAttribute("ar_usuarios", ar_usuarios);
	        
		
	        RequestDispatcher a = request.getRequestDispatcher("R01.jsp");
	    	a.forward(request, response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

}
