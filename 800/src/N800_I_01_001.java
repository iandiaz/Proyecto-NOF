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
 * Servlet implementation class iguias_stipo
 */
@WebServlet("/N800_I_01_001")
public class N800_I_01_001 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public N800_I_01_001() {
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
    		 
		    /*String SQL_Alertas = "SELECT * FROM alertas a "
		    		+ "INNER JOIN estados_vig_novig e ON e.estados_vig_novig_id=a.estados_vig_novig_id "
		    		+ "ORDER BY a.alertas_nombre";*/
		    
    		String SQL_DTE = "SELECT * FROM modulos WHERE Menus_id = 6 and Modulos_codigo IN(801,802,803,804,821,822,823,824,841)  ORDER BY Modulos_codigo";
			RS_DTE =  state.executeQuery(SQL_DTE);
 		    ArrayList<String> alertas = new ArrayList<String>();
 		    //recorremos los resultados de la consulta
 		    String SQL_DATOS="",cuenta="0";
 		    while(RS_DTE.next()){
 		    	if(RS_DTE.getString("Modulos_codigo").equals("801")){ 
 		    		SQL_DATOS = "SELECT count(*) as cuenta FROM `801` WHERE id_dte IS NULL AND `801`.estados_vig_novig_id=1";
 		    		System.out.println(SQL_DATOS);
 		    		RS_DATOS =  state1.executeQuery(SQL_DATOS);
 	 		    	RS_DATOS.next();
 	 		    	cuenta = RS_DATOS.getString("cuenta");
 		    	}
 		    	if(RS_DTE.getString("Modulos_codigo").equals("802")){ 
 		    		SQL_DATOS = "SELECT count(*) as cuenta FROM  `802` WHERE id_dte IS NULL AND `802`.estados_vig_novig_id=1";
 		    		System.out.println(SQL_DATOS);
 		    		RS_DATOS =  state1.executeQuery(SQL_DATOS);
 	 		    	RS_DATOS.next();
 	 		    	cuenta = RS_DATOS.getString("cuenta");
 		    	}
 		    	if(RS_DTE.getString("Modulos_codigo").equals("803")){ 
 		    		SQL_DATOS = "SELECT count(*) as cuenta FROM `803` WHERE id_dte IS NULL AND (803_tipodte='33' or 803_tipodte='34') AND `803`.estados_vig_novig_id=1 ";
 		    		System.out.println(SQL_DATOS);
 		    		RS_DATOS =  state1.executeQuery(SQL_DATOS);
 	 		    	RS_DATOS.next();
 	 		    	cuenta = RS_DATOS.getString("cuenta");
 		    	}
 		    	if(RS_DTE.getString("Modulos_codigo").equals("804")){ 
 		    		SQL_DATOS = "SELECT count(*) as cuenta FROM `804` WHERE id_dte IS NULL AND (804_tipodte='33' or 804_tipodte='34')  AND `804`.estados_vig_novig_id=1 ";
 		    		System.out.println(SQL_DATOS);
 		    		RS_DATOS =  state1.executeQuery(SQL_DATOS);
 	 		    	RS_DATOS.next();
 	 		    	cuenta = RS_DATOS.getString("cuenta");
 		    	}
 		    	/*if(RS_DTE.getString("Modulos_codigo").equals("811")){ 
 		    		SQL_DATOS = "SELECT count(*) as cuenta FROM factura_compra_serv WHERE id_dte IS NULL";
 		    		System.out.println(SQL_DATOS);
 		    		RS_DATOS =  state1.executeQuery(SQL_DATOS);
 	 		    	RS_DATOS.next();
 	 		    	cuenta = RS_DATOS.getString("cuenta");
 		    	}
 		    	if(RS_DTE.getString("Modulos_codigo").equals("812")){ 
 		    		SQL_DATOS = "SELECT count(*) as cuenta FROM factura_compra_activo WHERE id_dte IS NULL";
 		    		System.out.println(SQL_DATOS);
 		    		RS_DATOS =  state1.executeQuery(SQL_DATOS);
 	 		    	RS_DATOS.next();
 	 		    	cuenta = RS_DATOS.getString("cuenta");
 		    	}
 		    	*/
 		    	if(RS_DTE.getString("Modulos_codigo").equals("821")){ 
 		    		SQL_DATOS = "SELECT count(*) as cuenta FROM `821` WHERE id_dte IS NULL AND `821`.estados_vig_novig_id=1";
 		    		System.out.println(SQL_DATOS);
 		    		RS_DATOS =  state1.executeQuery(SQL_DATOS);
 	 		    	RS_DATOS.next();
 	 		    	cuenta = RS_DATOS.getString("cuenta");
 		    	}
 		    	if(RS_DTE.getString("Modulos_codigo").equals("822")){ 
 		    		SQL_DATOS = "SELECT count(*) as cuenta FROM `822` WHERE id_dte IS NULL AND `822`.estados_vig_novig_id=1";
 		    		System.out.println(SQL_DATOS);
 		    		RS_DATOS =  state1.executeQuery(SQL_DATOS);
 	 		    	RS_DATOS.next();
 	 		    	cuenta = RS_DATOS.getString("cuenta");
 		    	}
 		    	
 		    	
 		    	if(RS_DTE.getString("Modulos_codigo").equals("823")){ 
 		    		SQL_DATOS = "SELECT count(*) as cuenta FROM `823` WHERE id_dte IS NULL AND `823`.estados_vig_novig_id=1";
 		    		System.out.println(SQL_DATOS);
 		    		RS_DATOS =  state1.executeQuery(SQL_DATOS);
 	 		    	RS_DATOS.next();
 	 		    	cuenta = RS_DATOS.getString("cuenta");
 		    	}
 		    	if(RS_DTE.getString("Modulos_codigo").equals("824")){ 
 		    		SQL_DATOS = "SELECT count(*) as cuenta FROM `824` WHERE id_dte IS NULL AND `824`.estados_vig_novig_id=1";
 		    		System.out.println(SQL_DATOS);
 		    		RS_DATOS =  state1.executeQuery(SQL_DATOS);
 	 		    	RS_DATOS.next();
 	 		    	cuenta = RS_DATOS.getString("cuenta");
 		    	}
 		    	if(RS_DTE.getString("Modulos_codigo").equals("841")){ 
 		    		SQL_DATOS = "SELECT count(*) as cuenta FROM `841` WHERE id_dte IS NULL AND `841`.estados_vig_novig_id=1 AND `841`.`841_est_aprobacion`=2";
 		    		System.out.println(SQL_DATOS);
 		    		RS_DATOS =  state1.executeQuery(SQL_DATOS);
 	 		    	RS_DATOS.next();
 	 		    	cuenta = RS_DATOS.getString("cuenta");
 		    	}
 		    	
 		    	if(cuenta.equals("0")){ cuenta = "0";}
         	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
 		    	alertas.add(RS_DTE.getString("Modulos_codigo")+"/"+RS_DTE.getString("Modulos_nombre")+"/"+cuenta);
         	    cuenta="0";
     	    }
 		   RS_DTE.close();
		   state.close();
           conexion.close();
                
          String[] ar_alertas = new String[alertas.size()];
          for(int x=0; x < alertas.size(); x++){
        	  //System.out.println(alertas.get(x));
        	  ar_alertas[x]=alertas.get(x);
          }
             
          request.setAttribute("ar_alertas", ar_alertas);
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("Error "+ex);
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("N800_I_01_001.jsp");
        rd.forward(request, response);
		
	}

}
