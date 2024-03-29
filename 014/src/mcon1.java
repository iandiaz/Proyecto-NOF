
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

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
 * Servlet implementation class mcon1
 */
@WebServlet("/mcon1")
public class mcon1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mcon1() {
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
	
	private void mt(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
		
		PrintWriter out = response.getWriter();
		
		Cookie [] cookies=request.getCookies();
		if(request.getParameter("logout") !=  null){
			if (cookies != null) {
			    for (Cookie cookie : cookies) {
			        //work with cookies
			    	 System.out.println("cookie logout: "+cookie.getName());
			    	if(cookie.getName().equals("Usuarios_id")) {cookie.setMaxAge(0);cookie.setPath("/");response.addCookie(cookie);}
			    }
			}
				response.sendRedirect("/001/");	
				return;
		}
		
		//////////////////////////////////////////////////
		////////DEFINE PARAMETROS DE USUARIO//////////////
		String Usuarios_nombre="";
		String Perfil_id="";
		String Usuarios_ID="";
		
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("Usuarios_id")) { Usuarios_ID=cookie.getValue();}
				if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
				if(cookie.getName().equals("perfilusu_id")) Perfil_id=cookie.getValue();
			}
		}
		request.setAttribute("usuname", Usuarios_nombre);
		////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////
		
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 Statement state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
    		 String SQL_clpr = "SELECT "
    		 		+ "		`em`.`empresas_nombrenof`,"
    		 		+ "		`e`.`estados_vig_novig_id`,"
    		 		+ "		`e`.`estados_vig_novig_nombre`,"
    		 		+ "		`em`.`empresas_rut`,"
    		 		+ "		`contacto`.`CONT_ID`, "
    		 		+ "		CONCAT_WS(' ',`contacto`.`CONT_NOMBRE`,`contacto`.`CONT_APEP`,`contacto`.`CONT_APEM`) AS CONT_NOMBRE, "	
    		 		+ "		`tipo_contacto`.`TICON_DESCRIPCION` "
    		 		+ "	FROM"
    		 		+ "		empresas em"
    		 		+ "	INNER JOIN `usuarios_has_empresas` ON ("
    		 		+ "		`usuarios_has_empresas`.`empresas_id` = em.empresas_id"
    		 		+ "		AND `usuarios_has_empresas`.`estados_vig_novig_id` = 1"
    		 		+ "	)"
    		 		+ " INNER JOIN `contacto` ON `contacto`.`CLPR_ID` = em.`empresas_id` "
    		 		+ " INNER JOIN `tipo_contacto` ON `tipo_contacto`.`TICON_ID` = `contacto`.`TICON_ID` "
    		 		
    		 		+ "	INNER JOIN estados_vig_novig e ON e.estados_vig_novig_id = `contacto`.`estados_vig_novig_id`"
    		 		+ "	WHERE 	`usuarios_has_empresas`.`Usuarios_id` = "+Usuarios_ID
 		    		+ " ORDER BY `contacto`.`CONT_NOMBRE`";
 		    System.out.println(SQL_clpr);
 		    ResultSet clpr_rs = state.executeQuery(SQL_clpr);
		    //definimos un arreglo para los resultados
		    
		    ArrayList<String> estado_clpral = new ArrayList<String>();
		   
		    //recorremos los resultados de la consulta
		    while(clpr_rs.next()){        	   
        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	estado_clpral.add(	clpr_rs.getString("CONT_ID")+"/"+
		    						clpr_rs.getString("empresas_nombrenof").replace("/", " ")+"/"+
		    						clpr_rs.getString("estados_vig_novig_id")+"/"+
		    						clpr_rs.getString("estados_vig_novig_nombre")+"/"+
		    						clpr_rs.getString("CONT_NOMBRE")+"/"+
		    						clpr_rs.getString("TICON_DESCRIPCION"));
        	    
    	    }
		  //System.out.println("SIZE LIST: "+grupos.size());
		    	
		  clpr_rs.close();
		  state.close();
          conexion.close();
                
          String[] ar_clpr = new String[estado_clpral.size()];
          for(int x=0; x < estado_clpral.size(); x++){
        	  ar_clpr[x]=estado_clpral.get(x);
          }
                
          request.setAttribute("ar_clpr", ar_clpr);
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("Error "+ex);
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("mcon1.jsp");
		rd.forward(request, response);
		
		
		
	}


}
