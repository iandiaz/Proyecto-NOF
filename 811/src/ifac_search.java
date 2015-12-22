

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
 * Servlet implementation class ifac_search
 */
@WebServlet("/ifac_search")
public class ifac_search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ifac_search() {
        super();
        // TODO Auto-generated constructor stub
    }
    
public boolean validateSession(HttpServletRequest request, HttpServletResponse response){
		
  		boolean user_in_session=false;
  		Cookie [] cookies=request.getCookies();
  		
  		if (cookies != null) {
  		    for (Cookie cookie : cookies) {
  		        //work with cookies
  		    	 //System.out.println("cookie: "+cookie.getName());
  		    	if(cookie.getName().equals("Usuarios_id")) user_in_session=true;
  		    }
  		}
  		//refrescamos la session 
  		
  		if (user_in_session && cookies != null) {
  		    for (Cookie cookie : cookies) {
  		        //work with cookies
  		    	 //System.out.println("cookie: "+cookie.getName());
  		    	if(cookie.getName().equals("Usuarios_id")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
  		    	if(cookie.getName().equals("Usuarios_nombre")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
  		    	if(cookie.getName().equals("Usuarios_login")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
  		    	if(cookie.getName().equals("Usuarios_email")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
  		    	if(cookie.getName().equals("tipo_usu_id")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
  		    }
  		}
  		
  		
  		return user_in_session;
  		
      }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(validateSession(request, response)){
			
			 mt(request,response);
		}
		else response.sendRedirect("/001/");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(validateSession(request, response)){
			
			 mt(request,response);
		}
		else response.sendRedirect("/001/");
	}
	
public void mt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
		//System.out.println("1");
		
		Cookie [] cookies=request.getCookies();
		if(request.getParameter("logout") !=  null){
			if (cookies != null) {
			    for (Cookie cookie : cookies) {
			        //work with cookies
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
		
		
	
		
		
		
		
		Statement state = null;
		ResultSet RS_FAC = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
    		 
    		 
    		 
    		 
    			//////TRAEMOS LAS RECEPCIONES Q ESTAN EN NOF Y LAS GUARDAMOS EN UN ARRAY
    			
    			String SQL_FACNOF = "SELECT	`detail_fac_comprasactivo`.`recepcion_id` FROM 	`detail_fac_comprasactivo` ";
    	    	System.out.println(SQL_FACNOF);
    			ResultSet RS_FACNOF = state.executeQuery(SQL_FACNOF);
    			  ArrayList<String> facnof = new ArrayList<String>();
    			 while(RS_FACNOF.next()){      
    				 facnof.add(RS_FACNOF.getString("recepcion_id"));
    			 }
    			 RS_FACNOF.close();
    		 
    		 
    		 
    		 
    		 
			 String SQL_Birt = "SELECT "
    		 		+ "		[dbo].[RECEPCION].[RECEP_ID],"
    		 		+ "		[dbo].[RECEPCION].[OC_ID],"
    		 		+ "		[dbo].[CLIENTEPROVEEDOR].[CLPR_NOMBRE_FANTASIA],"
    		 		+ "		[dbo].[RECEPCION].[RECEP_ESTADO],"
    		 		+ "		CAST(ROUND([dbo].[RECEPCION].[RECEP_TOTAL_FACT],0) as INT) as RECEP_TOTAL_FACT,"
    		 		+ "		CONVERT ("
    		 		+ "			VARCHAR,"
    		 		+ "			[dbo].[RECEPCION].[RECEP_FECHA],"
    		 		+ "			105"
    		 		+ "		) AS RECEP_FECHA"
    		 		+ "	FROM"
    		 		+ "		[dbo].[RECEPCION]"
    		 		+ "	INNER JOIN [dbo].[ORDEN_COMPRA] ON [dbo].[ORDEN_COMPRA].[OC_ID] = [dbo].[RECEPCION].[OC_ID]"
    		 		+ "	INNER JOIN [dbo].[CLIENTEPROVEEDOR] ON [dbo].[CLIENTEPROVEEDOR].[CLPR_ID] = [dbo].[ORDEN_COMPRA].[OC_PROVEEDOR]"
    		 		+ "	WHERE [dbo].[RECEPCION].[RECEP_ESTADO]='VIGENTE'"
    		 		+ "	ORDER BY [dbo].[RECEPCION].[OC_ID] DESC,[dbo].[RECEPCION].[RECEP_ID] ";
    		 
    		 System.out.println(SQL_Birt);
    		 ConBirt birtBD= new ConBirt();
    		 ResultSet QueryBirt= birtBD.Consulta(SQL_Birt);
		    
		    if(request.getAttribute("filtro")!=null){
		    	request.setAttribute("filtro",request.getAttribute("filtro"));
		    }
		    //definimos un arreglo para los resultados
		    
		    ArrayList<String> alertas = new ArrayList<String>();
		   
		    //recorremos los resultados de la consulta
		    while(QueryBirt.next()){        	   
		    	
		    	boolean existe = false;
		    	//recorremos el array guardado con las facturas de nof para ver si existe 
		    	for (int i = 0; i < facnof.size(); i++) {
		    		if(facnof.get(i).equals(QueryBirt.getString("RECEP_ID"))){
		    			existe=true;
		    		}
		    		
					
				}
		    	
			    if(!existe){
			 		 //SI NO EXISTE LA RECEPCION EN NOF ENTONCES LA MOSTRAMOS PARA INGRESAR
		    		
					alertas.add(QueryBirt.getString("RECEP_ID")+"/"+QueryBirt.getString("CLPR_NOMBRE_FANTASIA")
		            			+"/"+QueryBirt.getString("RECEP_TOTAL_FACT")+"/"+QueryBirt.getString("RECEP_FECHA")+"/"+QueryBirt.getString("RECEP_ESTADO")+"/"+QueryBirt.getString("OC_ID"));
		    
			    }	 
    	    }
		   
		    QueryBirt.close();		    
		    birtBD.Desconectar();
		  //Alertas_RS.close();
		  //state.close();
          //conexion.close();
                
          String[] ar_alertas = new String[alertas.size()];
          for(int x=0; x < alertas.size(); x++){
        	  ar_alertas[x]=alertas.get(x);
          }
             
          request.setAttribute("ar_alertas", ar_alertas);
          conexion.close();
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("ERROR "+ex);
		}
		
	
		RequestDispatcher rd = request.getRequestDispatcher("ifac_search.jsp");
        rd.forward(request, response);
		
	}

}
