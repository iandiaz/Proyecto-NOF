

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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

import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class c2
 */
@WebServlet("/c2")
public class c2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public c2() {
        super();
        // TODO Auto-generated constructor stub
    }


public boolean validateSession(HttpServletRequest request, HttpServletResponse response){
		
		boolean user_in_session=false;
		boolean username_in_session=false;
		boolean perf_in_session=false;
		Cookie [] cookies=request.getCookies();
		
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName()+"-"+cookie.getValue());
		    	if(cookie.getName().equals("Usuarios_id") && cookie.getValue()!=null) user_in_session=true;
		    	if(cookie.getName().equals("Usuarios_nombre") && cookie.getValue()!=null) username_in_session=true;
		    	if(cookie.getName().equals("perfilusu_id") && cookie.getValue()!=null && !cookie.getValue().equals("")) perf_in_session=true;
		    	
		    }
		}
		
		
		if(user_in_session && username_in_session && perf_in_session) user_in_session=true;
		else user_in_session=false;
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
		    	if(cookie.getName().equals("perfilusu_id")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
		    	
		    	
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
		
		// logout
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
		//fin logout
		
		String Usuarios_nombre="";
		
		//fecha 
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		String Datenow=ahoraCal.get(Calendar.YEAR)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.DATE)+" "+ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		String U_ID="";
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName());
		    	if(cookie.getName().equals("Usuarios_id")) { U_ID=cookie.getValue();}
		    	if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}
		request.setAttribute("usuname", Usuarios_nombre);
		
		
		
		Statement state = null;
		Statement state2 = null;
		ResultSet ESTADOS_RS = null;
		ResultSet GRUPOS_RS = null;
		ResultSet ESTCLPR_RS = null;
		ResultSet CLPR_RS = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 state2 = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 
    		 String id_prod = request.getParameter("prod_id");
    		 
    		  //:::::::::::::BUSCA Y TRAE PRECIO PROD SELECCIONADO::::::::::::::::::::::::::::::::::::::
    		 
    		  state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		  String find_pp = "SELECT count(*) as n FROM precio_guia_traslado WHERE pgt_prod_id="+id_prod;
    		  
    		  System.out.println(find_pp);
    		  
    		  ResultSet findpp_RS = state.executeQuery(find_pp);		    
    		  
    		  findpp_RS.next();
    		  if(findpp_RS.getInt("n")>0){
    			  //ya existe
    			  String find_ppp = "SELECT *,"
    			  		+ "		CONCAT_WS(' ' , "
     		    		+ "			`usuarios`.`Usuarios_nombre`, "
     		    		+ "			`usuarios`.`Usuarios_ape_p` "
     		    		+ "		) AS perfilusu_creador, "
     		    		+ "		DATE_FORMAT(precio_guia_traslado.pgt_feccreacion,'%d-%m-%Y %H:%i:%s') AS perfilusu_feccreacion, "
     		    		+ "		IF ( "
     		    		+ "			precio_guia_traslado.pgt_fecmod IS NULL,"
     		    		+ "			' ',"
     		    		+ "			DATE_FORMAT(precio_guia_traslado.pgt_fecmod,'%d-%m-%Y %H:%i:%s')"
     		    		+ "		) AS perfilusu_fecmod,"
     		    		+ "		IF ("
     		    		+ "			precio_guia_traslado.pgt_modificador IS NULL,"
     		    		+ "			' ',"
     		    		+ "			CONCAT_WS(' ', "
     		    		+ "			`u2`.`Usuarios_nombre`,"
     		    		+ "			`u2`.`Usuarios_ape_p`"
     		    		+ "			)"
     		    		+ "		) AS perfilusu_modificador  "
    		    		
    			  		+ " FROM precio_guia_traslado "
    			  		+ " INNER JOIN `usuarios` ON `precio_guia_traslado`.`pgt_creador` = `usuarios`.`Usuarios_id` "
     		    		+ " LEFT JOIN `usuarios` u2 ON `precio_guia_traslado`.`pgt_modificador` = `u2`.`Usuarios_id` "
     		    		
    			  		+ "	WHERE pgt_prod_id="+id_prod;
        		  
        		  System.out.println(find_ppp);
        		  
        		  ResultSet findppp_RS = state.executeQuery(find_ppp);	
        		  
        		  if(findppp_RS.next()){
        			  String prod_nom = request.getParameter("nom");
        			  request.setAttribute("cuc", findppp_RS.getString("pgt_cuc"));
        			  request.setAttribute("id_prod", findppp_RS.getString("pgt_prod_id"));
        			  
        			  request.setAttribute("precio_guia_desp", findppp_RS.getString("pgt_precio_guia_traslado"));
        			  request.setAttribute("porcentaje", findppp_RS.getString("pgt_porcentaje"));
        			  request.setAttribute("prod_nom", prod_nom);
        			  

      		    	String fec_crea=findppp_RS.getString("perfilusu_feccreacion");
      		    	String user_crea=findppp_RS.getString("perfilusu_creador");
      		    	String fec_mod=findppp_RS.getString("perfilusu_fecmod");
      		    	String user_mod=findppp_RS.getString("perfilusu_modificador") ;
      		    	
        			  request.setAttribute("fec_crea", (fec_crea)+"");
      	            request.setAttribute("fec_mod", (fec_mod)+"");
      	            request.setAttribute("user_mod", (user_mod)+"");
      	            request.setAttribute("user_crea", (user_crea)+"");
      		    	
        			  
        		  }
    			  
    		  }
    		  else{
    			  //no existe
    			  String cuc = request.getParameter("cuc");
    			  String prod_nom = request.getParameter("nom");
    			  if(cuc!=null && !cuc.equals("")) cuc=((int)Double.parseDouble(cuc))+"";
    			  else cuc="0";
    			  cuc=cuc.replaceAll("$", "");
    			  request.setAttribute("cuc", cuc);
    			  request.setAttribute("id_prod", id_prod);
    			  
    			  request.setAttribute("precio_guia_desp", ((int)(Double.parseDouble(cuc)*1.30))+"");
    			  request.setAttribute("porcentaje", "30");
    			  request.setAttribute("prod_nom", prod_nom);
    			
    			  
    			  String fec_crea= "";
    		    	String user_crea="";
    		    	String fec_mod="";
    		    	String user_mod="";
    		    	
      			  request.setAttribute("fec_crea", (fec_crea)+"");
    	            request.setAttribute("fec_mod", (fec_mod)+"");
    	            request.setAttribute("user_mod", (user_mod)+"");
    	            request.setAttribute("user_crea", (user_crea)+"");
    			  
    		  }
    		  
    		   
		   
    		//:::::::::::::::::::::::::: sql trae estados vig no vig para select option:::::::::::::::::::::::::::::::::::::
 		    String SQL_ESTADOS = "SELECT * FROM estados_vig_novig";
 		    ESTADOS_RS =  state.executeQuery(SQL_ESTADOS);
 		    //definimos un arreglo para los resultados
 		    ArrayList<String> estados = new ArrayList<String>();
 		    //recorremos los resultados de la consulta
 		    while(ESTADOS_RS.next()){        	   
         	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
 		    	estados.add(ESTADOS_RS.getString("estados_vig_novig_id")+"/"+ESTADOS_RS.getString("estados_vig_novig_nombre"));    
     	    }
 		  ESTADOS_RS.close();
 		  state.close();
 		  String[] ar_estados = new String[estados.size()];
           for(int x=0; x < estados.size(); x++){
            	ar_estados[x]=estados.get(x);
           }
                 
           request.setAttribute("ar_estados", ar_estados);
 		  
 
 		
           ESTADOS_RS.close();
           conexion.close();
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("Error "+ex);
		}
		
	
		RequestDispatcher rd = request.getRequestDispatcher("c2.jsp");
        rd.forward(request, response);
		
	}

}
