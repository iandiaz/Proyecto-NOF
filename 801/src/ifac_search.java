

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
		ResultSet Alertas_RS = null;
		ResultSet RS_FAC = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
    		 
    		 //vemos todas las facturas ingresadas en sistema para q luego compare con el arreglo que armaremos, asi nos ahorramos consultas y tiempo de ejecucion 
    		 
    		 
    		 String SQL_FAC = "SELECT `801_folio_birt` FROM `801`";
		    	System.out.println(SQL_FAC);
				RS_FAC =  state.executeQuery(SQL_FAC);
			
    		 ArrayList<String> facturas_en_nof= new ArrayList<String>();
    		 while(RS_FAC.next()){
    			 facturas_en_nof.add(RS_FAC.getString("801_folio_birt"));
    			 
    		 }
    		 
    		 String SQL_Birt = "SELECT "
 		  	 		+ "		f.fact_id, "
 		  	 		+ "		MAX(c.clpr_razon_social) as clpr_razon_social,"
 		  	 		+ "		MAX(f.FACT_ESTADO) as FACT_ESTADO,"
 		  	 		+ "		max(f.FACT_TOTAL) as FACT_TOTAL, "
 		  	 		+ "		max(f.FACT_FECHA) as FACT_FECHA,"
 		  	 		+ "		CONVERT (VARCHAR, max(f.FACT_FECHA), 105) AS FACT_FECHA1 "
     		 		+ " FROM factura f "
     		 		+ "	INNER JOIN clienteproveedor c ON f.clpr_id = c.clpr_id "
 		  	 		+ " INNER JOIN [dbo].[DETALLE_FACTURA] ON [dbo].[DETALLE_FACTURA].[FACT_ID] = f.[FACT_ID] "
 		  	 		+ "	INNER JOIN [dbo].[DETALLE_VENTA] ON [dbo].[DETALLE_FACTURA].[DETI_TRAS_ID] = [dbo].[DETALLE_VENTA].[DETIVTA_ID]"
 		  	 		+ "	INNER JOIN [dbo].[VENTA] ON [dbo].[VENTA].[VTA_ID] = [dbo].[DETALLE_VENTA].[VTA_ID]"
 		  	 		+ "	INNER JOIN activo a ON DETALLE_VENTA.alt_id = a.alt_id"
 		  	 		+ "	INNER JOIN producto p ON a.prod_id = p.prod_id"
 		  	 		+ " WHERE f.FACT_ESTADO = 'EMITIDA'"
     				+ " GROUP BY f.fact_id"
     				+ "	ORDER BY FACT_FECHA DESC";
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
		    	
		    	//buscamos el numero de la factura en el arreglo que armamos arriba 
		    	boolean existe = false; 
		    	for (int i = 0; i < facturas_en_nof.size(); i++) {
					if(facturas_en_nof.get(i).equals(QueryBirt.getString("fact_id"))) {existe=true;break;}
				}
		    
					if(!existe){
			    		 //SI la factura no existe en NOF en tonces buscamos el detalle para mostrar los valores en el listado
			    		/*ConBirt birtBD0= new ConBirt();
			            ResultSet QueryBirt0= birtBD0.Consulta("SELECT TOP 1 DETALLE_VENTA.VTA_ID "
			            		+ " FROM DETALLE_FACTURA INNER JOIN DETALLE_VENTA ON DETALLE_FACTURA.DETI_TRAS_ID = DETALLE_VENTA.DETIVTA_ID "
			            		+ " WHERE DETALLE_FACTURA.FACT_ID ='"+QueryBirt.getString("fact_id")+"'");
			           // if(QueryBirt0.next()){
*/			            	alertas.add(QueryBirt.getString("fact_id")+"/"+QueryBirt.getString("clpr_razon_social")
			            			+"/"+QueryBirt.getString("FACT_TOTAL")+"/"+QueryBirt.getString("FACT_FECHA1")+"/"+QueryBirt.getString("FACT_ESTADO"));
			            	//request.setAttribute("id_fac_estado","NO ENVIADA");
			            //}
			    	}else{
			    	}
				 
    	    }
		    RS_FAC.close();
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
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("Error "+ex);
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("ifac_search.jsp");
        rd.forward(request, response);
		
	}

}
