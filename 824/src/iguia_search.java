

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
 * Servlet implementation class iguia_search
 */
@WebServlet("/iguia_search")
public class iguia_search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public iguia_search() {
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
		Statement state_borra = null;
		
		ResultSet Alertas_RS = null;
		ResultSet RS_FAC = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		state_borra = (Statement) ((java.sql.Connection) conexion).createStatement();
    		  		
    		String id_tras_existentes="0";
    		
    		String SQL_idtrasingresados = "(select `detalle_823`.`tras_id` from `detalle_823` where `detalle_823`.`estados_vig_novig_id`=1)"
    				+ "	UNION"
    				+ "	(select `detalle_824`.`tras_id` from `detalle_824` where `detalle_824`.`estados_vig_novig_id`=1)"; 
    		//System.out.println(SQL_Cliente);
 		    ResultSet TRASEXIST_RS = state.executeQuery(SQL_idtrasingresados);
    		
    		while(TRASEXIST_RS.next()){
    			if(TRASEXIST_RS.getString("tras_id")!=null && !TRASEXIST_RS.getString("tras_id").equals("null"))id_tras_existentes+=","+TRASEXIST_RS.getString("tras_id");
    		}
    		
    		
    		
    		
    		ConBirt birtBD2= new ConBirt();
    		
    		String query_birtt="SELECT "
    				+ "		c.CLPR_ID,"
    				+ "		MAX(c.CLPR_NOMBRE_FANTASIA) AS CLPR_NOMBRE_FANTASIA,"
    				+ "		d.DIRE_DIRECCION AS ORIGEN,"
    				+ "		MAX(d.DIRE_ID) AS ORIGEN_ID,"
    				+ "		MAX (c1.CLPR_NOMBRE_FANTASIA) AS CLPR_NOMBRE_FANTASIA_DEST,"
    				+ "		d1.DIRE_DIRECCION AS DESTINO, "
    				+ "		MAX(d1.DIRE_ID) AS DESTINO_ID "
    				+ "	FROM"
    				+ "		TRASLADO t"
    				+ "	INNER JOIN UBICACION u ON t.TRAS_UBI_ID_ORIGEN = u.UBI_ID"
    				+ "	INNER JOIN DIRECCION d ON d.DIRE_ID = u.DIRE_ID"
    				+ " INNER JOIN CLIENTEPROVEEDOR c1 ON d.CLPR_ID = c1.CLPR_ID"
    				+ "	INNER JOIN UBICACION u1 ON t.TRAS_UBI_ID_DESTINO = u1.UBI_ID"
    				+ "	INNER JOIN DIRECCION d1 ON d1.DIRE_ID = u1.DIRE_ID"
    				+ "	INNER JOIN CLIENTEPROVEEDOR c ON d1.CLPR_ID = c.CLPR_ID"
    				+ "	WHERE"
    				+ "		d.DIRE_ID <> d1.DIRE_ID"
    				+ "	AND t.TRAS_GD ='0' AND t.TRAS_ID NOT IN ("+id_tras_existentes+") "
    				+ "	GROUP BY"
    				+ "		c.CLPR_ID,"
    				+ "		d1.DIRE_DIRECCION,"
    				+ "		d.DIRE_DIRECCION"
    				+ "	ORDER BY"
    				+ "		CLPR_NOMBRE_FANTASIA,"
    				+ "		d.DIRE_DIRECCION,"
    				+ "		d1.DIRE_DIRECCION";
    		System.out.println(query_birtt);
            ResultSet QueryBirt2= birtBD2.Consulta(query_birtt);     
		    if(request.getAttribute("filtro")!=null){
		    	request.setAttribute("filtro",request.getAttribute("filtro"));
		    }
		    ArrayList<String> alertas = new ArrayList<String>();
		    while(QueryBirt2.next()){        	   
		    	alertas.add(	QueryBirt2.getString("CLPR_ID")+"/"+
		    					QueryBirt2.getString("CLPR_NOMBRE_FANTASIA")+"/"+
		    					QueryBirt2.getString("ORIGEN").replace("/"," ")+"/"+
		    					QueryBirt2.getString("DESTINO").replace("/"," ")+"/"+
		    					QueryBirt2.getString("ORIGEN_ID").replace("/"," ")+"/"+
		    					QueryBirt2.getString("DESTINO_ID").replace("/"," ")+"/"+
		    					QueryBirt2.getString("CLPR_NOMBRE_FANTASIA_DEST").replace("/"," "));
    	    }
		    QueryBirt2.close();
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
		
		
		RequestDispatcher rd = request.getRequestDispatcher("iguia_search.jsp");
        rd.forward(request, response);
		
	}

}