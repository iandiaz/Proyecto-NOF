

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
 * Servlet implementation class mpro_mod
 */
@WebServlet("/mpro_mod")
public class mpro_mod extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mpro_mod() {
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
		
		// logout
		Cookie [] cookies=request.getCookies();
		if(request.getParameter("logout") !=  null){
			if (cookies != null) {
			    for (Cookie cookie : cookies) {
			        //work with cookies
			    	//System.out.println("cookie logout: "+cookie.getName());
			    	if(cookie.getName().equals("Usuarios_id")) {cookie.setMaxAge(0);cookie.setPath("/");response.addCookie(cookie);}
			    }
			}
				response.sendRedirect("/001/");
				return;
		}
		//fin logout

		String Usuarios_nombre="";
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		String Usuarios_ID="";
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName());
		    	if(cookie.getName().equals("Usuarios_id")) { Usuarios_ID=cookie.getValue();}
		    	if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}		
		request.setAttribute("usuname", Usuarios_nombre);	
		
		request.setAttribute("usuname", Usuarios_nombre);
		Statement state2 = null;
		Statement state4 = null;
		ResultSet Grupo_RS = null;
		ResultSet Existe_RS = null;
		ResultSet ESTADOS_RS = null;
		Statement state3 = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		state2 = (Statement) ((java.sql.Connection) conexion).createStatement();
    		state3 = (Statement) ((java.sql.Connection) conexion).createStatement();
    		state4 = (Statement) ((java.sql.Connection) conexion).createStatement();
		    
    		if(request.getParameter("delete_id") !=  null){
 			 	state3 = (Statement) ((java.sql.Connection) conexion).createStatement();
 			 	String f1="",f2="",f3="";
				if(!request.getParameter("f1").isEmpty() && !request.getParameter("f1").isEmpty()){
					f1=request.getParameter("f1");
					String d=f1.substring(0, 2);
					String m=f1.substring(3, 5);
					String y=f1.substring(6, 10);
					f1=y+"-"+m+"-"+d+" 00:00:00";
					f2=request.getParameter("f2");
					String dia=f2.substring(0, 2);
					String m1=f2.substring(3, 5);
					String y1=f2.substring(6, 10);
					f2=y1+"-"+m1+"-"+dia+" 23:59:59";
					f3=request.getParameter("f3");
					String dia3=f3.substring(0, 2);
					String m3=f3.substring(3, 5);
					String y3=f3.substring(6, 10);
					f3=y3+"-"+m3+"-"+dia3+" 23:59:59";
				}
				//antes de actualizar preguntamos si ya existe un periodo abierto para esta empresa
				
				
				String id_emp = request.getParameter("peri_tc_id_emp");
				
				 String SQL_ps = "SELECT"
				 		+ "		count(*) as n"
				 		+ "	FROM "
				 		+ "		`periodos_tc`"
				 		+ "	WHERE"
				 		+ "	`periodos_tc`.`estados_vig_novig_id` = 1"
				 		+ "	AND `periodos_tc`.`peri_tc_id_emp` = "+id_emp
				 		+ "	AND `periodos_tc`.`peri_tc_id_estperiodo` = 1 AND `periodos_tc`.`peri_tc_id`<> "+Integer.parseInt(request.getParameter("id_p"));
				 
				    System.out.println(SQL_ps);
				    ResultSet PERIODOSACT_RS = state2.executeQuery(SQL_ps);
				
				    PERIODOSACT_RS.next();
				    
				    int catn_p_activos=PERIODOSACT_RS.getInt("n");
				    String estado_periodo= request.getParameter("estado_periodo");
				    
				    if(estado_periodo.equals("1") && catn_p_activos>0  ){
				    	
				    	//existe mas de un periodo activo
				    	RequestDispatcher redir = request.getRequestDispatcher("mpro_tomas?Exito=NOOK1&empresas_id="+request.getParameter("peri_tc_id_emp"));
		 				redir.forward(request, response);
		 				return;
				    	
				    }else{
						
		 				String UPDATE = "UPDATE periodos_tc SET "
		 						+ "estados_vig_novig_id="+request.getParameter("estados_vig_novig_id")+", peri_tc_fdesde='"+f1+"', peri_tc_fhasta='"+f2+"', peri_ffac='"+f3+"',peri_tc_id_estperiodo= '"+estado_periodo+"' "
		 						+ "WHERE peri_tc_id="+request.getParameter("delete_id");
		 				state3.executeUpdate(UPDATE);
		 				
		 				String query_correlativo= "select `periodos_tc`.`peri_tc_correlativo` from `periodos_tc` where `periodos_tc`.`peri_tc_id`="+request.getParameter("delete_id"); 
		 				 System.out.println(query_correlativo);
		 			    ResultSet query_correlativo_rs = state3.executeQuery(query_correlativo);
		 			    int cor_actual=0;
		 			    if(query_correlativo_rs.next()){
		 			    	
		 			    	cor_actual=query_correlativo_rs.getInt("peri_tc_correlativo");
		 			    }
		 			    
		 			    if(cor_actual!=0){
		 			    	String up_siguiente_periodo="update `periodos_tc` set `periodos_tc`.`peri_tc_fdesde`=DATE_ADD('"+f2.substring(0, 10)+"',INTERVAL 1 DAY) where `periodos_tc`.`peri_tc_correlativo`="+(cor_actual+1)+" and `periodos_tc`.`peri_tc_id_emp`="+id_emp;
		 			    	System.out.println(up_siguiente_periodo);
		 			    	state3.executeUpdate(up_siguiente_periodo);
		 			    }
		 				//debemos actualizar la fecha del periodo siguiente
		 				//capturamos primero el correlativo del periodo actual
		 				
		 				
		 				state3.close();
		 				RequestDispatcher redir = request.getRequestDispatcher("mpro_tomas?Exito=OK&empresas_id="+request.getParameter("peri_tc_id_emp"));
		 				redir.forward(request, response);
 		        return;
				    }
 			}
    		
		    String SQL_GRUPO = ""
		    		+ " SELECT ep.estado_periodo_id, p.peri_tc_id, m.empresas_id, m.empresas_razonsocial, m.empresas_nombrenof, p.estados_vig_novig_id,"
		    		+ " DATE_FORMAT(p.peri_ffac,'%d-%m-%Y') as peri_ffac,"
		    		+ " DATE_FORMAT(p.peri_tc_fdesde,'%d-%m-%Y') as peri_tc_fdesde,"
		    		+ " DATE_FORMAT(p.peri_tc_fhasta,'%d-%m-%Y') as peri_tc_fhasta,"
		    		+ " DATE_FORMAT(DATE_ADD(p.peri_tc_fhasta,INTERVAL 1 DAY), '%d-%m-%Y') as peri_tc_fhasta1,"
		    		+ "	CONCAT(u.Usuarios_nombre, ' ', u.Usuarios_ape_p) AS creador, "
		    		+ "	DATE_FORMAT(p.peri_tc_feccreacion,'%d-%m-%Y %H:%i:%s') AS feccreacion, "
		    		+ "	IF (p.peri_tc_fecmod IS NULL,' ',DATE_FORMAT(p.peri_tc_fecmod, '%d-%m-%Y %H:%i:%s')) AS fecmod,"
		    		+ "	IF (p.peri_tc_modificador IS NULL,' ',CONCAT(u2.Usuarios_nombre,' ',u2.Usuarios_ape_p)) AS modificador "
		    		+ " FROM periodos_tc p "
		    		+ " 	INNER JOIN empresas m ON p.peri_tc_id_emp=m.empresas_id"
		    		+ " 	INNER JOIN estados_vig_novig e ON e.estados_vig_novig_id=p.estados_vig_novig_id"
		    		+ " 	INNER JOIN `usuarios` u ON `p`.`peri_tc_creador` = u.`Usuarios_id` "
		    		+ " 	LEFT JOIN `usuarios` u2 ON `p`.`peri_tc_modificador` = `u2`.`Usuarios_id` "
		    		+ " 	LEFT JOIN estado_periodo ep ON ep.estado_periodo_id=p.peri_tc_id_estperiodo "
		    		+ " WHERE peri_tc_id="+Integer.parseInt(request.getParameter("id_p"));
		    System.out.println(SQL_GRUPO);
		    Grupo_RS =  state2.executeQuery(SQL_GRUPO);
		    if(Grupo_RS.next()){ 
		    	
		    	request.setAttribute("fec_limite", Grupo_RS.getString("peri_tc_fhasta1"));
		    	request.setAttribute("estado_periodo_id",Grupo_RS.getString("estado_periodo_id"));
		    	
		    	request.setAttribute("peri_tc_id",Grupo_RS.getString("peri_tc_id"));
		    	request.setAttribute("empresas_id",Grupo_RS.getString("empresas_id"));
		    	request.setAttribute("empresas_razonsocial",Grupo_RS.getString("empresas_razonsocial"));
		    	request.setAttribute("peri_tc_fdesde",Grupo_RS.getString("peri_tc_fdesde"));
		    	request.setAttribute("peri_tc_fhasta",Grupo_RS.getString("peri_tc_fhasta"));
		    	request.setAttribute("peri_ffac",Grupo_RS.getString("peri_ffac"));
		    	request.setAttribute("empresas_nombrenof",Grupo_RS.getString("empresas_nombrenof"));
		    	request.setAttribute("estados_vig_novig_id",Grupo_RS.getString("estados_vig_novig_id"));
		    	
		    	request.setAttribute("user_crea",Grupo_RS.getString("creador"));
		    	request.setAttribute("fec_crea",Grupo_RS.getString("feccreacion"));
		    	request.setAttribute("user_mod",Grupo_RS.getString("modificador"));
		    	request.setAttribute("fec_mod",Grupo_RS.getString("fecmod"));
		    	
		    	String SQL_EXISTE = "SELECT peri_tc_id FROM periodos_tc WHERE peri_tc_id_emp="+Grupo_RS.getString("empresas_id")+" AND estados_vig_novig_id = 1 "
		    			+ " ORDER BY peri_tc_id DESC LIMIT 1";
		    	Existe_RS =  state3.executeQuery(SQL_EXISTE);
		    	if(Existe_RS.next()){
		    		request.setAttribute("peri_tc_id_last",Existe_RS.getString("peri_tc_id"));
		    	}
    	    }
		  Grupo_RS.close();
		  state2.close();	
		  
		  String SQL_ESTADOS = "SELECT * FROM estados_vig_novig";
		  ESTADOS_RS =  state4.executeQuery(SQL_ESTADOS);
		  ArrayList<String> estados = new ArrayList<String>();
		    while(ESTADOS_RS.next()){        	   
      	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	estados.add(ESTADOS_RS.getString("estados_vig_novig_id")+"/"+ESTADOS_RS.getString("estados_vig_novig_nombre"));
      	    
  	    }
		  //System.out.println("SIZE LIST: "+estados.size());
		    	
		  ESTADOS_RS.close();
		  state4.close();
              
        String[] ar_estados = new String[estados.size()];
        for(int x=0; x < estados.size(); x++){
         	ar_estados[x]=estados.get(x);
        }
              
        request.setAttribute("ar_estados", ar_estados);  
      //::::::::::::::::::::::::::::::::::::::::::sql estado periodos select option::::::::::::::::::::::::::::::::::::::  
		  Statement state = (Statement) ((java.sql.Connection) conexion).createStatement();
		  String SQL_estperi = "SELECT	`estado_periodo`.`estado_periodo_id`,`estado_periodo`.`estado_periodo_nombre` FROM 	`estado_periodo` WHERE 	`estado_periodo`.`estados_vig_novig_id` = 1 ORDER BY `estado_periodo`.`estado_periodo_orden`";
		    ResultSet ESTPERI_RS = state.executeQuery(SQL_estperi);		    
		    //definimos un arreglo para los resultados		    
		    ArrayList<String> estperi = new ArrayList<String>();		   
		    //recorremos los resultados de la consulta
		    while(ESTPERI_RS.next()){        	   
   	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	estperi.add(ESTPERI_RS.getString("estado_periodo_id")+"/"+ESTPERI_RS.getString("estado_periodo_nombre"));
   	    }
		  System.out.println("SIZE LIST: "+estperi.size());  	
		 ESTPERI_RS.close();
		  String[] ar_estperi = new String[estperi.size()];
         for(int x=0; x < estperi.size(); x++){
       	 ar_estperi[x]=estperi.get(x);
         }
               
         request.setAttribute("ar_estperi", ar_estperi);
        
        
		  
          conexion.close();
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("Error "+ex);
		}
		RequestDispatcher rd = request.getRequestDispatcher("mpro_mod.jsp");
        rd.forward(request, response);
		
	}

}
