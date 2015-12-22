

import java.io.FileOutputStream;
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
 * Servlet implementation class Iusuario
 */
@WebServlet("/Iusuario")
public class Iusuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Iusuario() {
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
		//fin logout
		
		//calendario
		
		String Usuarios_nombre="";
		
		//fecha 
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		String Datenow=ahoraCal.get(Calendar.YEAR)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.DATE)+" "+ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		String Usuarios_ID="";
		System.out.print(Datenow);
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName());
		    	if(cookie.getName().equals("Usuarios_id")) { Usuarios_ID=cookie.getValue();}
		    	if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}
		request.setAttribute("usuname", Usuarios_nombre);
		//System.out.print("Usuarios_ID: "+Usuarios_ID);
		// end calendario
		
		
		
		
		//grabar
		boolean existeclpr = false;
		boolean existeclpr2 = false;
		if(request.getParameter("grabar") !=  null){
			Statement stategrabar = null;
			Statement stateval = null;
			ResultSet rs_valclpr= null;
			Statement stateval2 = null;
			ResultSet rs_valclpr2= null;
			try {
				String Usuarios_nombre_rs = request.getParameter("Usuarios_nombre");
				String Usuarios_ape_p = request.getParameter("Usuarios_ape_p");
				String Usuarios_ape_m = request.getParameter("Usuarios_ape_m");
				String Usuarios_login = request.getParameter("Usuarios_login");
				String Usuarios_pass = request.getParameter("Usuarios_pass");
				String Usuarios_email = request.getParameter("Usuarios_email");
				String Usuarios_telefono = request.getParameter("Usuarios_telefono");
				String tipo_usu_id = request.getParameter("tipo_usu_id");
				String perfilusu_id = request.getParameter("perfilusu_id");				
		    	String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
		    	String empresas_id = request.getParameter("empresas_id");
		    	
		    	String Usuarios_inicial= request.getParameter("Usuarios_inicial").toUpperCase();
		    	String Usuarios_all_emp=request.getParameter("todos");	
		    	String Usuarios_imei=request.getParameter("Usuarios_imei");	
		    	
		    	
		    	
		    	if(Usuarios_all_emp==null) Usuarios_all_emp="0";
		    	
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		
	    		stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		stateval = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		stateval2 = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		String sql_valgrupo="SELECT * FROM usuarios WHERE Usuarios_login='"+Usuarios_login.toUpperCase()+"'";
	    		System.out.println("sql_valgrupo: "+sql_valgrupo);
	    		rs_valclpr=stateval.executeQuery(sql_valgrupo);
	    		
	    		String sql_valgrupo2="SELECT * FROM usuarios WHERE Usuarios_nombre='"+Usuarios_nombre_rs.toUpperCase()+"' AND Usuarios_ape_p='"+Usuarios_ape_p.toUpperCase()+"' AND Usuarios_ape_m='"+Usuarios_ape_m.toUpperCase()+"'";
	    		System.out.println("sql_valgrupo2: "+sql_valgrupo2);
	    		rs_valclpr2=stateval2.executeQuery(sql_valgrupo2);
	    		System.out.println("OUT : "+sql_valgrupo2);
	    		if(rs_valclpr.next()){
	    			stateval.close();
	    			rs_valclpr.close();
	    			existeclpr=true;
	    			//response.sendRedirect("Iusuario?Exito=NOK");
	    		}if(rs_valclpr2.next()){
	    			//stateval.close();
	    			//rs_valclpr.close();
	    			stateval2.close();
	    			rs_valclpr2.close();
	    			existeclpr2=true;   		
	    		}
	    		if(existeclpr  || existeclpr2){conexion.close();}
	    		System.out.println("pre if(!existeclpr2  && !existeclpr2){: ");
	    		if(!existeclpr  && !existeclpr2){
	    			
	    		//insertamos en birt 
	    			
	    			if(Constantes.SYNCDB==1){
	       			 ConBirt birtBD= new ConBirt();
	       			 
	       			 String estado_v_nv="VIGENTE";
	       			 if(estados_vig_novig_id.equals("2")) estado_v_nv="NO VIGENTE";
	       			 
	       			 String nombre_enbirt=Usuarios_nombre_rs.toUpperCase()+" "+Usuarios_ape_p.toUpperCase();
	       			 if(nombre_enbirt.length()>30)nombre_enbirt=nombre_enbirt.substring(0, 29);
	       			 
	       			 
	       			 
	       			 String insert_birt="INSERT INTO [USUARIO] ("
	       			 		+ "		[USUARIO].[PERF_ID],"
	       			 		+ "		[USUARIO].[USU_EMAIL],"
	       			 		+ "		[USUARIO].[USU_ESTADO],"
	       			 		+ "		[USUARIO].[USU_NOMBRE],"
	       			 		+ "		[USUARIO].[USU_PASSWORD],"
	       			 		+ "		[USUARIO].[USU_TELEFONO],"
	       			 		+ "		[USUARIO].[USU_USERNAME],"
	       			 		+ "		[USUARIO].[USU_ID_UM],"
	       			 		+ "		[USUARIO].[USU_FECHA_UM], "
	       			 		+ "		[USUARIO].[USU_INICIAL] "
	       			 		+ "	)"
	       			 		+ "	VALUES ("+perfilusu_id+",'"+Usuarios_email.toUpperCase()+"','"+estado_v_nv+"','"+nombre_enbirt+"','"+Usuarios_pass+"','"+Usuarios_telefono+"','"+Usuarios_login.toUpperCase()+"',"+Usuarios_ID+",GETDATE(),'"+Usuarios_inicial+"')";
	       			 	System.out.println("BIRT: "+insert_birt);
	       			 	birtBD.ConsultaINUP(insert_birt);
	       		      
	       		        birtBD.Desconectar();
	       			}
	    			
			    String SQL_INSERT = "INSERT INTO usuarios (Usuarios_nombre, tipo_usu_id, perfilusu_id,Usuarios_accion_alertada,Usuarios_ult_idper_exec,estados_vig_novig_id,"
			    		+ " Usuarios_ape_p,Usuarios_ape_m,Usuarios_login,Usuarios_pass,Usuarios_email,Usuarios_telefono"
			    		+ ", Usuarios_feccreacion, Usuarios_creador, empresas_id,Usuarios_inicial,Usuarios_all_emp,Usuarios_imei) VALUES('"+Usuarios_nombre_rs.toUpperCase()+"',"+tipo_usu_id+","+perfilusu_id+",0,20,"+estados_vig_novig_id+",'"
			    		+ Usuarios_ape_p.toUpperCase()+"','"+Usuarios_ape_m.toUpperCase()+"','"+Usuarios_login.toUpperCase()+"',"
			    		+ "'"+Usuarios_pass+"','"+Usuarios_email.toUpperCase()+"','"+Usuarios_telefono+"','"+Datenow+"',"+Usuarios_ID+","+empresas_id+",'"+Usuarios_inicial+"','"+Usuarios_all_emp+"','"+Usuarios_imei+"')";
			    System.out.print("SQL_INSERT usuarios : "+SQL_INSERT+"\n");
			    stategrabar.executeUpdate(SQL_INSERT,Statement.RETURN_GENERATED_KEYS);
			    
			    
			   
			    ResultSet generatedKeys = null;
	    		  generatedKeys = stategrabar.getGeneratedKeys();
	    		  String id_usu_last="";
	    		  if (generatedKeys.next()) {
	    			  id_usu_last=generatedKeys.getString(1);
	    		  }
	    		  
	    		  //System.out.println("Nuevo id perfil: "+id_usu_last);
	    		
	    		  String[] seleccionado = request.getParameterValues("permisos[]");
		    		if(seleccionado!=null) for (String id_per: seleccionado) {
		    			if(id_per!=null && !id_per.equals("-1")){
		    				String insertusuemp=""
			    					+ " INSERT INTO `usuarios_has_empresas` ("
			    					+ " 	`usuarios_has_empresas`.`Usuarios_id`,"
			    					+ " 	`usuarios_has_empresas`.`empresas_id` "
			    					+ " ) "
			    					+ " VALUES"
			    					+ " 	('"+id_usu_last+"', '"+id_per+"')";
			    			
		    				stategrabar.executeUpdate(insertusuemp);
			    			System.out.println("Permiso insertado: "+id_per+" perfiln: "+id_usu_last);
		    			}	    			
		    		}
			    
	    		stategrabar.close(); 
			    conexion.close();
	            RequestDispatcher rd_up = request.getRequestDispatcher("Menuusuario?Exito=OK");
	            rd_up.forward(request, response);
	    		}
	    		System.out.println("saliendo del grabar");
			}catch(Exception ex){
                ex.printStackTrace();
			    //out.println("Unable to connect to database "+ex);
			}
			
			if(!existeclpr  && !existeclpr2)return;
		}
		
		//fin grabar
		
		
		
		Statement state = null;
		ResultSet EMPRESAS_RS = null;
		ResultSet TIPO_USU_RS = null;
		ResultSet PERFILUSU_RS = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 
    	//:::::::::::::::::::::::::: sql trae estados vig no vig para select option:::::::::::::::::::::::::::::::::::::
		    String SQL_EMPRESAS = "SELECT * FROM empresas WHERE estados_vig_novig_id=1";
		    EMPRESAS_RS =  state.executeQuery(SQL_EMPRESAS);
		    //definimos un arreglo para los resultados
		    ArrayList<String> empresas = new ArrayList<String>();
		    //recorremos los resultados de la consulta
		    while(EMPRESAS_RS.next()){        	   
        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	empresas.add(EMPRESAS_RS.getString("empresas_id")+"/"+EMPRESAS_RS.getString("empresas_nombre"));    
    	    }
		  System.out.println("SIZE LIST: "+empresas.size());	
		  EMPRESAS_RS.close();
		  state.close();
		  String[] ar_empresas = new String[empresas.size()];
          for(int x=0; x < empresas.size(); x++){
        	 ar_empresas[x]=empresas.get(x);
          }
                
          request.setAttribute("ar_empresas", ar_empresas);
		//::::::::::::::::::::::::::fin sql trae estados vig no vig para select option:::::::::::::::::::::::::::::::::::::
		  
		  //::::::::::::::::::::::::::::::::::::::::::sql tipo_usu para select option::::::::::::::::::::::::::::::::::::::
		  state = (Statement) ((java.sql.Connection) conexion).createStatement();
		  String SQL_tipo_usu = "SELECT tipo_usu_id,tipo_usu_nombre FROM tipo_usu";
		    TIPO_USU_RS =  state.executeQuery(SQL_tipo_usu);	    
		    //definimos un arreglo para los resultados		    
		    ArrayList<String> tipo_usu = new ArrayList<String>();		   
		    //recorremos los resultados de la consulta
		    while(TIPO_USU_RS.next()){        	   
      	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	tipo_usu.add(TIPO_USU_RS.getString("tipo_usu_id")+"/"+TIPO_USU_RS.getString("tipo_usu_nombre"));
      	    }
		  System.out.println("SIZE LIST: "+tipo_usu.size());  	
		  TIPO_USU_RS.close();	
		  state.close();
		  String[] ar_tipo_usu = new String[tipo_usu.size()];
          for(int x=0; x < tipo_usu.size(); x++){
        	  ar_tipo_usu[x]=tipo_usu.get(x);
          }
                
          request.setAttribute("ar_tipo_usu", ar_tipo_usu);
		//::::::::::::::::::::::::::::::::::::::::fin sql tipo_usu para select option::::::::::::::::::::::::::::::::::::::
		  
		//::::::::::::::::::::::::::::::::::::::::::sql estado clpr para select option::::::::::::::::::::::::::::::::::::::  
		  state = (Statement) ((java.sql.Connection) conexion).createStatement();
		  String SQL_perfilusu = "SELECT perfilusu_id,perfilusu_nombre FROM perfilusu WHERE estados_vig_novig_id=1";
		    PERFILUSU_RS =  state.executeQuery(SQL_perfilusu);		    
		    //definimos un arreglo para los resultados		    
		    ArrayList<String> perfilusu = new ArrayList<String>();		   
		    //recorremos los resultados de la consulta
		    while(PERFILUSU_RS.next()){        	   
    	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	perfilusu.add(PERFILUSU_RS.getString("perfilusu_id")+"/"+PERFILUSU_RS.getString("perfilusu_nombre"));
    	    }
		  System.out.println("SIZE LIST: "+perfilusu.size());  	
		  PERFILUSU_RS.close();
		  String[] ar_perfilusu = new String[perfilusu.size()];
          for(int x=0; x < perfilusu.size(); x++){
        	  ar_perfilusu[x]=perfilusu.get(x);
          }
                
          request.setAttribute("ar_perfilusu", ar_perfilusu);
		//::::::::::::::::::::::::::::::::::::::::::fin sql estado clpr para select option::::::::::::::::::::::::::::::::::::::  
		  state.close();
		  
		//id cor
  		Statement statecor = null;
  		ResultSet CORRELATIVO_RS = null;
  		statecor = (Statement) ((java.sql.Connection) conexion).createStatement();
  		 String last_id_grupos_sql="SELECT"
  		    		+ " 	`usuarios`.`Usuarios_id`"
  		    		+ " FROM"
  		    		+ " 	`usuarios`"
  		    		+ " ORDER BY"
  		    		+ " 	`usuarios`.`Usuarios_id` DESC"
  		    		+ " LIMIT 1";
         System.out.println("correlativo : no pasa na"+last_id_grupos_sql);
         CORRELATIVO_RS =  statecor.executeQuery(last_id_grupos_sql);
        
         int correlativo=0;
  		  if(CORRELATIVO_RS.next()) correlativo=CORRELATIVO_RS.getInt("Usuarios_id")+1;
  		  //System.out.println("correlativo : no pasa na2 "+correlativo);
         request.setAttribute("correlativo", correlativo+"");
         
         statecor.close();		  
          conexion.close();
                
          
		}catch(Exception ex){
		    out.println("Unable to connect to database "+ex);
		}
		
		String msg="";
		if(existeclpr){
			msg="?Exito=NOK1";
		}
		if(existeclpr2){
			msg="?Exito=NOK2";
		}
		
		if(existeclpr || existeclpr2){			
			String Usuarios_nombre_rs = request.getParameter("Usuarios_nombre");
			String Usuarios_ape_p = request.getParameter("Usuarios_ape_p");
			String Usuarios_ape_m = request.getParameter("Usuarios_ape_m");
			String Usuarios_login = request.getParameter("Usuarios_login");
			String Usuarios_pass = request.getParameter("Usuarios_pass");
			String Usuarios_email = request.getParameter("Usuarios_email");
			String Usuarios_telefono = request.getParameter("Usuarios_telefono");
			String tipo_usu_id = request.getParameter("tipo_usu_id");
			String perfilusu_id = request.getParameter("perfilusu_id");				
	    	String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
	    	String empresas_id = request.getParameter("empresas_id");
	    	ArrayList<String> miusuemp = new ArrayList<String>();
	    	String[] seleccionado = request.getParameterValues("permisos[]");
            if(seleccionado!=null) for (String id_per: seleccionado) {
	    			if(id_per!=null && !id_per.equals("-1")){
	    				miusuemp.add(id_per);
	    			}
	    		}
	    	System.out.println("SIZE LIST: "+miusuemp.size());  	
	   		
	   		String[] ar_miusuemp = new String[miusuemp.size()];
	        for(int x=0; x < miusuemp.size(); x++){
	        	ar_miusuemp[x]=miusuemp.get(x);
	        }
	                   
	        request.setAttribute("ar_miusuemp", ar_miusuemp);
			request.setAttribute("Usuarios_nombre_rs",Usuarios_nombre_rs);
	    	request.setAttribute("Usuarios_ape_p",Usuarios_ape_p);
	    	request.setAttribute("Usuarios_ape_m",Usuarios_ape_m);
	    	request.setAttribute("Usuarios_login",Usuarios_login);
	    	request.setAttribute("Usuarios_pass",Usuarios_pass);
	    	request.setAttribute("Usuarios_email",Usuarios_email);
	    	request.setAttribute("Usuarios_telefono",Usuarios_telefono);
	    	request.setAttribute("tipo_usu_id",tipo_usu_id);
	    	request.setAttribute("perfilusu_id",perfilusu_id);
	    	request.setAttribute("estados_vig_novig_id",estados_vig_novig_id);
	    	request.setAttribute("empresas_id",empresas_id);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("iusuario.jsp"+msg);
        rd.forward(request, response);
		
	}


}
