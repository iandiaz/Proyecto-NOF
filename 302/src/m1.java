

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class m1
 */
@WebServlet("/m1")
public class m1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public m1() {
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
		
		//////////////////////////////////////////////////
		////////DEFINE PARAMETROS DE USUARIO//////////////

		request.setAttribute("modname", Controlador.getNameModulo(Constantes.MODULO));
		
		request.setAttribute("usuname", Controlador.getUsunameSession(request));
		
		
		if(request.getParameter("grabar") !=  null){
			try {
				
				Class.forName("com.mysql.jdbc.Driver");
		   		Connection conexion=(Connection) DriverManager.getConnection
		 	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
		  		 Statement state = (Statement) ((java.sql.Connection) conexion).createStatement();
		  		
		  		
		  		 //borramos todos los usuarios seleccionados para el usuario logeado
		  		 
		  		 String del="delete from adm_tecnicos_selectusu where admt_s_id_usuario="+Controlador.getUsuIDSession(request);
		  		 state.executeUpdate(del);
		  		 //traemos solo los usuarios seleccionados
		 	     
		 	    	String[] seleccionado = request.getParameterValues("usus[]");
			    	if(seleccionado!=null) for (String incialesusu: seleccionado) {
		    			if(incialesusu!=null){
		    				 
			    			  String in="INSERT INTO `adm_tecnicos_selectusu` (admt_s_ususelect,admt_s_id_usuario) VALUES ('"+incialesusu+"','"+Controlador.getUsuIDSession(request)+"')  ";
			      				System.out.println(in);
			    			  state.executeUpdate(in);
		    				
		    			}	    			
		    		}
		 	    	
			    	String estticket = request.getParameter("estticket");
			    	String tipoticket = request.getParameter("tipoticket");
			    	String f1 = request.getParameter("f1");
			    	String f2 = request.getParameter("f2");
			    	String emp = request.getParameter("emp");
			    	
					
	    			response.sendRedirect("m2?estticket="+estticket+"&tipoticket="+tipoticket+"&f1="+f1+"&f2="+f2+"&emp="+emp);	
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
    		 
    		 //traemos las empresas registradoas en sistema
    		 ArrayList<String> emps = new ArrayList<String>();
     		
    		 String find_emps_sql="SELECT empresas_id,empresas_nombrenof FROM empresas ORDER BY empresas_nombrenof ";
    		 ResultSet EMPS_RS = state.executeQuery(find_emps_sql);
		     while(EMPS_RS.next()){
		    	 emps.add(EMPS_RS.getString("empresas_id")+";"+EMPS_RS.getString("empresas_nombrenof"));	      	
		     }
    		 
    		 //traemos los usuarios registradoas en sistema
    		 ArrayList<String> usus_inciales = new ArrayList<String>();
     		
    		 String find_ususR_sql="SELECT Usuarios_inicial,CONCAT_WS(' ',`Usuarios_nombre`,`Usuarios_ape_p`,`Usuarios_ape_m`) as nombre FROM usuarios ";
    		 ResultSet USUSR_RS = state.executeQuery(find_ususR_sql);
		     while(USUSR_RS.next()){
		     	usus_inciales.add(USUSR_RS.getString("Usuarios_inicial")+";"+USUSR_RS.getString("nombre"));	      	
		     }
		      	
	      	//traemos los tipo ticket 
	      	ArrayList<String> tptick = new ArrayList<String>();
	     		
	      	String find_tptickt="SELECT tipo_ticket_id,tipo_ticket_nombre FROM tipo_ticket ";
	    	ResultSet TPTICKT_RS = state.executeQuery(find_tptickt);
	      	while(TPTICKT_RS.next()){
	      		tptick.add(TPTICKT_RS.getString("tipo_ticket_id")+";"+TPTICKT_RS.getString("tipo_ticket_nombre"));	      	
	      	}
	      	
    		 ConBirt birtBD= new ConBirt();
   			 
    			
    		String  ticketusu="SELECT" + 
    		 		"	[TICKET].[TICK_DIRECCION]," + 
    		 		"	[TICKET].[TICK_ESTADO]," + 
    		 		"	[TICKET].[TICK_ID]," + 
    		 		"	[TICKET].[TICK_CODIGO_FALLA]," + 
    		 		"	[TICKET].[TICK_FECHA_ENVIO]," +
    		 		"	[TICKET].[CLPR_NOMBRE_FANTASIA]," +
    		 		
    		 		"	[DETALLE_TECNICO_TICKET].[DIRE_ID]," + 
    		 		"	[DETALLE_TECNICO_TICKET].[DETI_USU_NOF]," + 
    		 		
    		 		"	ll.USU_ASIG ," + 
    		 		"	DATEDIFF(" + 
    		 		"		MINUTE," + 
    		 		"		[TICKET].[TICK_FECHA_ENVIO]," + 
    		 		"		GETDATE()" + 
    		 		"	) AS DiffDate ," + 
    		 		" [DETALLE_TECNICO_TICKET].[DETI_TIPO_TICKET] " + 
    		 		" FROM " + 
    		 		"	[TICKET]" + 
    		 		" INNER JOIN [DETALLE_TECNICO_TICKET] ON [DETALLE_TECNICO_TICKET].[TICK_ID] = [TICKET].[TICK_ID]" + 
    		 		" INNER JOIN (" + 
    		 		"	SELECT" + 
    		 		"		[TICKET_ASIGNACION].[USU_ASIG]," + 
    		 		"		[TICKET_ASIGNACION].[TICK_ID]" + 
    		 		"	FROM" + 
    		 		"		[TICKET_ASIGNACION]" + 
    		 		"	INNER JOIN (" + 
    		 		"		SELECT" + 
    		 		"			MAX ([TICKET_ASIGNACION].[TASIG_ID]) AS TASIG_ID" + 
    		 		"		FROM" + 
    		 		"			[TICKET_ASIGNACION]" + 
    		 		"		GROUP BY" + 
    		 		"			[TICKET_ASIGNACION].[TICK_ID]" + 
    		 		"	) t1 ON t1.TASIG_ID = [TICKET_ASIGNACION].[TASIG_ID]" + 
    		 		" ) ll ON ll.TICK_ID = [TICKET].[TICK_ID]" + 
    		 		"" + 
    		 		" WHERE" + 
    		 		"	NOT (" + 
    		 		"		[TICKET].[TICK_ESTADO] LIKE '%CERRADO%'" + 
    		 		"	)" + 
    		 		" AND NOT (" + 
    		 		"	[TICKET].[TICK_ESTADO] LIKE '%CIERRE%'" + 
    		 		" )" + 
    		 		" AND NOT (" + 
    		 		"	[TICKET].[TICK_ESTADO] LIKE '%ELIMINADO%'" + 
    		 		" )" + 
    		 		" "
    		 		+ " ORDER BY ll.USU_ASIG ";
    		
    		ResultSet ticketusu_rs =birtBD.Consulta(ticketusu);
    		
    		ArrayList<String> tickets = new ArrayList<String>();
    		ArrayList<String> usuarios_tick = new ArrayList<String>();
    		
    		
    		
    		while(ticketusu_rs.next()){
    			String usunombre="";
				
				for(int i =0; i<usus_inciales.size();i++){
					String subusu= usus_inciales.get(i);
					String subusu_r[]= subusu.split(";");
					
					if(subusu_r[0].equals(ticketusu_rs.getString("USU_ASIG")))usunombre=subusu_r[1];
				}
				
    			boolean existe=usuarios_tick.contains(ticketusu_rs.getString("USU_ASIG")+ ";"+usunombre);
    			if(!existe){
    				
    				
    				usuarios_tick.add(ticketusu_rs.getString("USU_ASIG")+";"+usunombre);
    			}
    		}
 		      
    		//separamos los tikcet en conjuntos, estas seran las tablas 
    		
    		ArrayList<String> selecciones_usu = new ArrayList<String>();
    		
    		
    		 String find_usus_sql="SELECT admt_s_ususelect FROM adm_tecnicos_selectusu WHERE admt_s_id_usuario="+Controlador.getUsuIDSession(request);
	    		ResultSet USUS_RS = state.executeQuery(find_usus_sql);
	      		while(USUS_RS.next()){
	      			selecciones_usu.add(USUS_RS.getString("admt_s_ususelect"));
	    		}
 		 
    		
	      	request.setAttribute("selecciones_usu", selecciones_usu);
	    		
    		request.setAttribute("usuarios_tick", usuarios_tick);
    		
    		request.setAttribute("tptick", tptick);
    		
    		request.setAttribute("emps", emps);
    		
    		
		    birtBD.Desconectar();
    		
    		
		  conexion.close();
                
          
		}catch(Exception ex){
		    out.println("Error "+ex);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("m1.jsp");
        rd.forward(request, response);
		
	}

}
