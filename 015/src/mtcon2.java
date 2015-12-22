

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
 * Servlet implementation class mtcon2
 */
@WebServlet("/mtcon2")
public class mtcon2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mtcon2() {
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
			    	 System.out.println("cookie logout: "+cookie.getName());
			    	if(cookie.getName().equals("Usuarios_id")) {cookie.setMaxAge(0);cookie.setPath("/");response.addCookie(cookie);}
			    }
			}
				response.sendRedirect("/001/");
				return;
		}
		
		//fecha 
				Calendar ahoraCal = Calendar.getInstance();
				String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
				String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
			
		
		String Usuarios_nombre="";
		
		String id_iusuario="";
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName());
		    	if(cookie.getName().equals("Usuarios_id") && cookie.getValue()!=null) id_iusuario=cookie.getValue();
		    	if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}
		//fin logout
		//grabar
				boolean existeGrupo = false;
				if(request.getParameter("grabar") !=  null){
					Statement stategrabar = null;
					Statement stateval = null;
					ResultSet rs_valgrupos= null;
					try {
						String grupos_nombre = request.getParameter("tcon_nombre");
				    	String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
						//import java.io.IOException;
						Class.forName("com.mysql.jdbc.Driver");
			    		Connection conexion=(Connection) DriverManager.getConnection
			    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
			    		 stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
			    		 
			    		stateval = (Statement) ((java.sql.Connection) conexion).createStatement();
			    		String sql_valgrupo="SELECT * FROM tipo_contacto WHERE TICON_DESCRIPCION='"+grupos_nombre.toUpperCase()+"' AND TICON_ID<>"+request.getParameter("id_g");
				    	rs_valgrupos=stateval.executeQuery(sql_valgrupo);
				    	
				    	
				    	if(rs_valgrupos.next()){
				    		stateval.close();
			    			rs_valgrupos.close();conexion.close();
			    			existeGrupo=true;
			    			
			    		}else{
			    			
			    			if(Constantes.SYNCDB==1){
			    			//actualizamos grupo en birt
			    			
			    			ConBirt birtBD= new ConBirt();
			       			 
			       			 String estado_v_nv="VIGENTE";
			       			 if(estados_vig_novig_id.equals("2")) estado_v_nv="NO VIGENTE";
			       			 
			       			 String id_en_birt=request.getParameter("id_g");
			       			 if(id_en_birt.equals("7")) id_en_birt="8";
			       			 else if(id_en_birt.equals("8")) id_en_birt="22";
			       			else if(id_en_birt.equals("9")) id_en_birt="23";
			       			else if(id_en_birt.equals("10")) id_en_birt="27";
			       			else if(id_en_birt.equals("11")) id_en_birt="28";
			       			else if(id_en_birt.equals("12")) id_en_birt="29";
			       			else if(id_en_birt.equals("13")) id_en_birt="30";
			       			else if(Integer.parseInt(id_en_birt)>13) id_en_birt=(Integer.parseInt(id_en_birt)+17)+"";
			       			 String insert_birt=" UPDATE [TIPO_CONTACTO] "
			 	       			 		+ "	SET [TIPO_CONTACTO].[TICON_DESCRIPCION] = '"+grupos_nombre.toUpperCase()+"',"
			 	       			 		+ "	 [TIPO_CONTACTO].[TICON_ESTADO] = '"+estado_v_nv+"',"
			 	       			 		+ "	 [TIPO_CONTACTO].[TICON_FECHA_UM] = GETDATE(),"
			 	       			 		+ "	 [TIPO_CONTACTO].[USU_ID_UM] = '"+id_iusuario+"'"
			 	       			 		+ "	WHERE"
			 	       			 		+ "		[TIPO_CONTACTO].[TICON_ID] = "+id_en_birt;
			       		      birtBD.ConsultaINUP(insert_birt);
			       		     
			       		     
			       		        birtBD.Desconectar();
			       			}
			    			
			    			
					    String SQL_UPDATE = ""
					    		+ "UPDATE tipo_contacto "
					    		+ " SET ticon_accion_alertada=0 "
					    		+ "		,ticon_ult_idper_exec=130 "
					    		+ "		,TICON_DESCRIPCION='"+grupos_nombre.toUpperCase()+"', estados_vig_novig_id="+estados_vig_novig_id+" "
					    		+ " 	,ticon_fecmod=now() "
					    		+ " 	,ticon_modificador= "+id_iusuario
					    		+ " WHERE TICON_ID="+request.getParameter("id_g");
					    System.out.println(SQL_UPDATE);
					    stategrabar.executeUpdate(SQL_UPDATE);
					    stategrabar.close();
					    stateval.close();
					    rs_valgrupos.close();
			            conexion.close();
			            existeGrupo=false;
			            System.out.println("UP: "+SQL_UPDATE);  
			            out.print("<script>location='/015/?Exito=OK'</script>");
			    		 return;
			    		}
					}catch(Exception ex){
						ex.printStackTrace();
					    out.println("ERROR: "+ex);
					}
					if(!existeGrupo)return;
				}
				
		
			
		request.setAttribute("usuname", Usuarios_nombre);
		Statement state2 = null;		
		ResultSet Grupo_RS = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		state2 = (Statement) ((java.sql.Connection) conexion).createStatement();
		    
    		String SQL_GRUPO = ""
		    		+ "SELECT *,e.estados_vig_novig_id,"
		    		+ "		CONCAT( "
		    		+ "			`usuarios`.`Usuarios_nombre`, "
		    		+ "			' ',"
		    		+ "			`usuarios`.`Usuarios_ape_p` "
		    		+ "		) AS perfilusu_creador, "
		    		+ "		DATE_FORMAT(g.ticon_feccreacion,'%d-%m-%Y %H:%i:%s') AS perfilusu_feccreacion, "
		    		+ "		IF ( "
		    		+ "			g.ticon_fecmod IS NULL,"
		    		+ "			' ',"
		    		+ "			DATE_FORMAT(g.ticon_fecmod,'%d-%m-%Y %H:%i:%s')"
		    		+ "		) AS perfilusu_fecmod,"
		    		+ "		IF ("
		    		+ "			g.ticon_modificador IS NULL,"
		    		+ "			' ',"
		    		+ "			CONCAT( "
		    		+ "			`u2`.`Usuarios_nombre`,"
		    		+ "			' ',"
		    		+ "			`u2`.`Usuarios_ape_p`"
		    		+ "			)"
		    		+ "		) AS perfilusu_modificador "
		    		+ " FROM tipo_contacto g "
		    		+ " 	INNER JOIN estados_vig_novig e ON e.estados_vig_novig_id=g.estados_vig_novig_id"
		    		+ " 	INNER JOIN `usuarios` ON `g`.`ticon_creador` = `usuarios`.`Usuarios_id` "
		    		+ " 	LEFT JOIN `usuarios` u2 ON `g`.`ticon_modificador` = `u2`.`Usuarios_id` "
		    		+ " WHERE TICON_ID="+Integer.parseInt(request.getParameter("id_g"));
		    System.out.println("SQL: "+SQL_GRUPO);
		    Grupo_RS =  state2.executeQuery(SQL_GRUPO);
		    if(Grupo_RS.next()){ 
		    	request.setAttribute("grupos_id",Grupo_RS.getString("TICON_ID"));
		    	request.setAttribute("grupos_nombre",Grupo_RS.getString("TICON_DESCRIPCION"));
		    	request.setAttribute("estados_vig_novig_nombre",Grupo_RS.getString("estados_vig_novig_nombre"));
		    	request.setAttribute("estados_vig_novig_id",Grupo_RS.getString("estados_vig_novig_id"));
		    	
		    	
		    	
		    	String fec_crea=Grupo_RS.getString("perfilusu_feccreacion");
		    	String user_crea=Grupo_RS.getString("perfilusu_creador");
		    	String fec_mod=Grupo_RS.getString("perfilusu_fecmod");
		    	String user_mod=Grupo_RS.getString("perfilusu_modificador") ;
		    	
		    	request.setAttribute("fec_crea", (fec_crea)+"");
	            request.setAttribute("fec_mod", (fec_mod)+"");
	            request.setAttribute("user_mod", (user_mod)+"");
	            request.setAttribute("user_crea", (user_crea)+"");
		    	
    	    }
		    
		    
		    String SQL_ESTADOS = "SELECT * FROM estados_vig_novig";
		    System.out.println("SQL: "+SQL_ESTADOS);
		    ResultSet ESTADOS_RS = state2.executeQuery(SQL_ESTADOS);
		    
		    //definimos un arreglo para los resultados
		    
		    ArrayList<String> estados = new ArrayList<String>();
		   
		    //recorremos los resultados de la consulta
		    while(ESTADOS_RS.next()){        	   
        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	estados.add(ESTADOS_RS.getString("estados_vig_novig_id")+"/"+ESTADOS_RS.getString("estados_vig_novig_nombre"));
        	    
    	    }
		    
		    String[] ar_estados = new String[estados.size()];
	          for(int x=0; x < estados.size(); x++){
	           	ar_estados[x]=estados.get(x);
	          }
	          request.setAttribute("ar_estados", ar_estados);
		    
		    
		  Grupo_RS.close();
		  state2.close();	    
          conexion.close();
		
		String msg="";
		if(existeGrupo){
			msg="?Exito=NOK";
			String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
			String grupos_nombre = request.getParameter("tcon_nombre");
			request.setAttribute("grupos_nombre",grupos_nombre);
			request.setAttribute("estados_vig_novig_id",estados_vig_novig_id);
			//System.out.println("grupos_nombre "+grupos_nombre);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("mtcon2.jsp"+msg);
        rd.forward(request, response);
        
        
		}catch(Exception ex){
		    out.println("ERROR "+ex);
		}
		
		
	}
}
