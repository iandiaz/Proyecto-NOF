

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
    		 
    		 String id_e = request.getParameter("id_e");
      		 String id_em="";
      		 if(id_e!=null){
      			 id_em=id_e;
      			 id_e="AND G.GV_EMPRESA_EMISORA='"+id_e+"'"; 
      		 }
      		 else{
      			 id_em="123";
      			 id_e="AND G.GV_EMPRESA_EMISORA='123'"; 
      		 }
		 	 String SQL_Birt = "SELECT G.GV_ID, c.clpr_razon_social, G.GV_ESTADO, "
    		 			+ "CONVERT(varchar, G.GV_FECHA, 105) AS GV_FECHA "
    		 			+ "FROM GUIA_VENTA G"+
    				 	" INNER JOIN clienteproveedor c ON G.GV_clpr_id = c.clpr_id "
    				 	+ "WHERE G.GV_ESTADO = 'EMITIDA' "+id_e+ 
    				 	" ORDER BY G.GV_ID DESC";
    		 System.out.println(SQL_Birt);
    		 ConBirt birtBD= new ConBirt();
    		 ResultSet QueryBirt= birtBD.Consulta(SQL_Birt);
		    
		    if(request.getAttribute("filtro")!=null){
		    	request.setAttribute("filtro",request.getAttribute("filtro"));
		    }
		    /*System.out.println("Consulta: "+SQL_Alertas);*/
		    //Alertas_RS =  state.executeQuery(SQL_Alertas);
		    //definimos un arreglo para los resultados
		    
		    ArrayList<String> alertas = new ArrayList<String>();
		   
		    //recorremos los resultados de la consulta
		    while(QueryBirt.next()){        	   
		    	
		    	String SQL_FAC = "SELECT count(*) as id_guia_estado FROM `821` WHERE 821_folio_birt= "+QueryBirt.getString("gv_id");
		    	System.out.println(SQL_FAC);
				RS_FAC =  state.executeQuery(SQL_FAC);
				if(RS_FAC.next()){
					if(RS_FAC.getInt("id_guia_estado")==0){
			    		 //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
			    		ConBirt birtBD0= new ConBirt();
			            ResultSet QueryBirt0= birtBD0.Consulta("SELECT SUM(DETIGV_PRECIO_NETO) as TOTAL "
			            		+ "FROM DETALLE_GUIA_VENTA WHERE GV_ID ='"+QueryBirt.getString("gv_id")+"' GROUP BY GV_ID");
			            if(QueryBirt0.next()){
			            	alertas.add(QueryBirt.getString("GV_ID")+"/"+QueryBirt.getString("clpr_razon_social")
			            			+"/"+QueryBirt0.getString("TOTAL")+"/"+QueryBirt.getString("GV_FECHA")+"/"+QueryBirt.getString("GV_ESTADO"));
			            	//request.setAttribute("id_fac_estado","NO ENVIADA");
			            }
			    	}else{
			    	}
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
          
          
          //--------------------- EMISOR ----------------------//
    	    String SQL_EMPRESA = "SELECT * FROM empresas WHERE estados_vig_novig_id = 1 AND empresas_relacionada = 1 ORDER BY empresas_nombre";
    	    System.out.println(SQL_EMPRESA);
    	    ResultSet EMPRESAS_RS = state.executeQuery(SQL_EMPRESA);
    	    ArrayList<String> empresas = new ArrayList<String>();
    	    while(EMPRESAS_RS.next()){ empresas.add(EMPRESAS_RS.getString("empresas_id")+"/"+EMPRESAS_RS.getString("empresas_nombre")); }
    	    EMPRESAS_RS.close();
    	    String[] ar_empresas = new String[empresas.size()];
    	    for(int x=0; x < empresas.size(); x++){ ar_empresas[x]=empresas.get(x);}
    	    request.setAttribute("ar_empresas", ar_empresas);
    	    //----------------------- FIN ------------------------//
    	    request.setAttribute("id_e", id_em);
    	    RS_FAC.close();
 		   
          conexion.close();
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("ERROR "+ex);
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("ifac_search.jsp");
        rd.forward(request, response);
		
	}

}
