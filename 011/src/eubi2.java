

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
 * Servlet implementation class eubi2
 */
@WebServlet("/eubi2")
public class eubi2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public eubi2() {
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
		// 
	}
	
	private void mt(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
		// TODO Auto-generated method stub
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
    		
    		 
    		 String empresas_id = request.getParameter("empresas_id");
    		 request.setAttribute("empresas_id", empresas_id);
    		 
    		 String SQL_clpr = "SELECT "
     		 		+ "		`em`.`empresas_id`,"
     		 		+ "		`em`.`empresas_nombrenof`,"
     		 		+ "		`e`.`estados_vig_novig_id`,"
     		 		+ "		`e`.`estados_vig_novig_nombre`,"
     		 		+ "		`em`.`empresas_rut`,"
     		 		+ "		`direccion`.`DIRE_NOMBRE`,"
     		 		+ "		`direccion`.`DIRE_DIRECCION`, "
     		 		+ "		`direccion`.`DIRE_ID`, "
     		 		+ "		`comuna`.`COMU_NOMBRE`, "
     		 		+ "		UCASE(`ubicacion`.`UBI_DESCRIPCION`) as UBI_DESCRIPCION, "
     		 		+ "		UCASE(`ubicacion`.`ubi_fisica`) as ubi_fisica, "	
     		 		+ "		`ubicacion`.`UBI_ID` "
     		 		+ "	FROM"
     		 		+ "		empresas em"
     		 		+ "	INNER JOIN `usuarios_has_empresas` ON ("
     		 		+ "		`usuarios_has_empresas`.`empresas_id` = em.empresas_id"
     		 		+ "		AND `usuarios_has_empresas`.`estados_vig_novig_id` = 1"
     		 		+ "	)"
     		 		+ "	INNER JOIN `direccion` ON `direccion`.`CLPR_ID` = `em`.`empresas_id`"
     		 		+ " INNER JOIN `ubicacion` ON `ubicacion`.`DIRE_ID` = `direccion`.`DIRE_ID` "
     		 		+ "	INNER JOIN estados_vig_novig e ON e.estados_vig_novig_id = `ubicacion`.`estados_vig_novig_id`"
     		 		+ " INNER JOIN `comuna` ON `comuna`.`COMU_ID` = `direccion`.`COMU_ID` "
     		 		+ "	WHERE 	`usuarios_has_empresas`.`Usuarios_id` = "+Usuarios_ID 
     		 		
  		    		+ " ORDER BY em.empresas_nombre,`ubicacion`.`ubi_fisica` ";
 		    System.out.println(SQL_clpr);
 		    ResultSet clpr_rs = state.executeQuery(SQL_clpr);
		    //definimos un arreglo para los resultados
		    
		    ArrayList<String> estado_clpral = new ArrayList<String>();
		   
		    //recorremos los resultados de la consulta
		    while(clpr_rs.next()){        	   
        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	estado_clpral.add(clpr_rs.getString("empresas_id")+"/"+
        	    clpr_rs.getString("empresas_nombrenof").replace("/", " ")+"/"+
        	    clpr_rs.getString("estados_vig_novig_id")+"/"+
        	    clpr_rs.getString("estados_vig_novig_nombre")+"/"+
        	    clpr_rs.getString("empresas_rut")+"/"+
        	    clpr_rs.getString("DIRE_DIRECCION").replace("/", " ")+"/"+
        	    clpr_rs.getString("DIRE_ID")+"/"+
        	    clpr_rs.getString("COMU_NOMBRE")+"/"+
        	    clpr_rs.getString("ubi_fisica").replace("/", " ")+"/"+
        	    clpr_rs.getString("UBI_ID"));
        	    
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
		
		RequestDispatcher rd = request.getRequestDispatcher("eubi2.jsp");
		rd.forward(request, response);
		
		
		
	}


}
