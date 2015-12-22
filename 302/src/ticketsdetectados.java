

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class ticketsdetectados
 */
@WebServlet("/ticketsdetectados")
public class ticketsdetectados extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ticketsdetectados() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		mt(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		mt(request,response);
	}
	

public void mt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
		//System.out.println("1");
		
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
		String Usuarios_nombre="";
		
		//fecha
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName());
		    	if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}		
		request.setAttribute("usuname", Usuarios_nombre);
	
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 Statement state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
    		 String tick = request.getParameter("tick");
    		 //capturamos primero el id del clpr y la serie de la maqiuna
    		 
    		 String find_ticketsand_sql="select "
     				+ "	admt_ticket"
     				
 					+ "	,cliente_id"
 					+ "	,adm_serie"
 					
     				+ "	FROM adm_tecnicos"
     				+ " WHERE admt_ticket='"+tick+"' ";
     		
     		
     	
         	
 			System.out.println(find_ticketsand_sql);
 			ResultSet FINDTICKETSANT_RS = state.executeQuery(find_ticketsand_sql);
 			
 			String cliente_id="";
 			String adm_serie="";
 			
 			if(FINDTICKETSANT_RS.next()){
 				cliente_id=FINDTICKETSANT_RS.getString("cliente_id");
 				adm_serie=FINDTICKETSANT_RS.getString("adm_serie");
 			}
 			
 			
 			
 			
 			
 			
 			//TRAEMOS TODOS LOS TICKET QUE TENGAN ESTA CARATERISTICA
 			
 			
 			String find_ticketsandparecidos_sql="select "
    				+ "	admt_ticket"
    				+ "	,IF(admt_prioridad IS NULL, '',admt_prioridad) as admt_prioridad"
    				+ "	,admt_clpr_nombre"
    				+ "	,admt_distnof"
    				+ "	,admt_distusu"
    				+ "	,admt_req_repuesto"
    				+ "	,admt_tiempo"
    				+ "	,admt_tipoticket"
    				+ "	,admt_visible "
					+ "	,adm_colorfont "
					+ "	,adm_colorback "
					+ "	,admt_usu "
					+ "	,DATE_FORMAT(adm_fecha_recibido,'%d-%m-%Y %H:%i:%s') as adm_fecha_recibido "
					+ "	,DATE_FORMAT(adm_fecha_cierre,'%d-%m-%Y %H:%i:%s') as adm_fecha_cierre "
					+ "	,adm_tiempocierre "
					+ "	,IF(adm_fecllegadatec IS NULL ,'-',adm_fecllegadatec) as adm_fecllegadatec  "
					+ "	,DATEDIFF(now(),adm_tecnicos.`adm_fecha_recibido`) as diasDesdeEnvio  "
					+ "	,cliente_id"
					+ "	,adm_serie"
					+ "	,adm_deti_estado"
					
    				+ "	FROM adm_tecnicos"
    				+ " WHERE admt_tipoticket NOT IN ('SOLICITUD TONER NORMAL','SOLICITUD TONER CRITICO','FACTURACION','CONTADOR','MXM POR AUTORIZAR')"
    				+ "	AND cliente_id='"+cliente_id+"' AND adm_serie='"+adm_serie+"'  ";
    		
    		
    	
        	
			System.out.println(find_ticketsandparecidos_sql);
			ResultSet FINDTICKETSANTPARECIDOS_RS = state.executeQuery(find_ticketsandparecidos_sql);
			ArrayList<String> alltickets30 = new ArrayList<String>();
			while(FINDTICKETSANTPARECIDOS_RS.next()){
				if(FINDTICKETSANTPARECIDOS_RS.getInt("diasDesdeEnvio")<=30){
					alltickets30.add(FINDTICKETSANTPARECIDOS_RS.getString("admt_ticket")+";"+FINDTICKETSANTPARECIDOS_RS.getString("cliente_id")+";"+FINDTICKETSANTPARECIDOS_RS.getString("adm_serie")+";"+FINDTICKETSANTPARECIDOS_RS.getString("admt_usu")+";"+FINDTICKETSANTPARECIDOS_RS.getString("adm_deti_estado"));
				}
			}
		    //definimos un arreglo para los resultados
		    
		  
		    	
		   
		  state.close();
         
                
         
          
          conexion.close();
          request.setAttribute("alltickets30", alltickets30);
         
          
       
		}catch(Exception ex){
		    out.println("Error "+ex);
		}
		RequestDispatcher rd = request.getRequestDispatcher("ticketsdetectados.jsp");
        rd.forward(request, response);
		
	}

}
