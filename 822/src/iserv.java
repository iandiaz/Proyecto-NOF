

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
 * Servlet implementation class iserv
 */
@WebServlet("/iserv")
public class iserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public iserv() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public boolean validateSession(HttpServletRequest request, HttpServletResponse response){
		
		boolean user_in_session=false;
		boolean username_in_session=false;
		Cookie [] cookies=request.getCookies();
		
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 System.out.println("cookie: "+cookie.getName()+"-"+cookie.getValue());
		    	if(cookie.getName().equals("Usuarios_id") && cookie.getValue()!=null) user_in_session=true;
		    	if(cookie.getName().equals("Usuarios_nombre") && cookie.getValue()!=null) username_in_session=true;
		    }
		}
		
		
		if(user_in_session && username_in_session) user_in_session=true;
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

		String Usuarios_nombre="",id_iusuario="";
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName());
		    	if(cookie.getName().equals("Usuarios_id") && cookie.getValue()!=null) id_iusuario=cookie.getValue();
		    	if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}	

		boolean guiaexiste=false;
		if(request.getParameter("grabar") != null){
			try {
				Statement state = null;

				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		state = (Statement) ((java.sql.Connection) conexion).createStatement();

				//----------------- CABECERA -----------------------//
				String GID=request.getParameter("gv_id");
				String empresa_id=request.getParameter("empresa_id");
				String CLPR_ID=request.getParameter("clpr_id");
				String DIRE_ID=request.getParameter("dire_id");
				String CONT_ID=request.getParameter("cont_id");
				String OBS1=request.getParameter("obs1");
				String OBS2=request.getParameter("obs2");
				String tipo_guia_serv= request.getParameter("tipo_guia_serv");
				
				String gv_ciudad= request.getParameter("gv_ciudad");
				String empresa_emisora_nombre= request.getParameter("empresa_emisora_nombre");
				String cont_nombre= request.getParameter("cont_nombre");
				String cont_telefonoc=request.getParameter("cont_telefonoc");
				String cont_email=request.getParameter("cont_email");
				String cont_phone=request.getParameter("cont_phone");
					
				
				String SUBTOTAL=request.getParameter("subtotal");
		        String NETO=request.getParameter("neto");
	            String IVA=request.getParameter("iva");
	            String TOTAL=request.getParameter("total");
	            String desc=request.getParameter("desc");
	            String glosa_desc=request.getParameter("glosa_desc");
	            String resp=request.getParameter("resp");
	            
	            String fec[] =request.getParameter("gv_fecha").split("-");
				String gv_fecha="'"+fec[2]+"-"+fec[1]+"-"+fec[0]+"'";
				
				String tipo_dteref=request.getParameter("tipo_dteref");
				String folioref=request.getParameter("folioref");
				String fec_ref=request.getParameter("fec_ref");
				
				
				if(tipo_dteref==null)tipo_dteref="";
				if(folioref==null)folioref="";
				if(fec_ref==null){fec_ref="NULL";}
				else{ 
					String fecrefar[]=fec_ref.split("-");
					fec_ref ="'"+fecrefar[2]+"-"+fecrefar[1]+"-"+fecrefar[0]+"'";
					}
				
				String g_afecta=request.getParameter("g_afecta");
				if(g_afecta==null || g_afecta.equals(""))g_afecta="0";
				
				//--------------------- FIN -----------------------//
				
				//INSERTAMOS TODOS LOS DATOS EN NOF LA CABECERA DE LA FACTURA
			    String SQL_GUIA = "INSERT INTO `822` ("
			    		+ "	CLPR_ID"
			    		+ "	,DIRE_ID"
			    		+ "	,CONT_ID"
			    		+ "	,822_FECHA"
			    		+ "	,822_ESTADO"
			    		+ "	,822_EMPRESA_EMISORA"
			    		+ "	,822_obs1"
			    		+ "	,822_obs2"
			    		+ "	,822_feccreacion"
			    		+ "	, 822_creador"
			    		+ "	, estados_vig_novig_id"
			    		+ "	,tipo_guia_serv"
			    		+ "	,822_ciudad"
			    		+ "	,822_empresa_emisora_nombre"
			    		+ "	,cont_nombre,cont_telefonoc,cont_email,cont_telefono"
			    		+ "	,822_SUBTOTAL"
			    		+ "	,822_TOTAL"
			    		+ "	,822_descuento"
			    		+ "	,822_glosa_descuento"
			    		+ "	,822_NETO"
			    		+ "	,822_IVA"
			    		+ "	,822_responsable_name"
			    		
						+ "	,`822`.`tipo_dteref` "
						+ "	,`822`.`folioref` "
						+ "	,`822`.`fec_ref` "
						+ "	,`822`.`822_afecta`  "
			    		

			    		
			    		+ ")"
			    	+ " VALUES ('"+CLPR_ID+"','"+DIRE_ID+"','"+CONT_ID+"',"+gv_fecha
			    	+ ",'NO EMITIDA','"+empresa_id+"' " 
			    	+ ",'"+OBS1.toUpperCase()+"','"+OBS2.toUpperCase()+"',now(),"+id_iusuario+",1,'"+tipo_guia_serv+"'"
			    	+ ",'"+gv_ciudad+"','"+empresa_emisora_nombre+"','"+cont_nombre+"','"+cont_telefonoc+"','"+cont_email+"'"
			    	+ ",'"+cont_phone+"'"
			    	+ ",'"+SUBTOTAL+"'"
			    	+ ",'"+TOTAL+"'"
			    	+ ",'"+desc+"'"
			    	+ ",'"+glosa_desc.toUpperCase()+"'"
			    	+ ",'"+NETO+"'"
			    	+ ",'"+IVA+"'"
			    	+ ",'"+resp.toUpperCase()+"'"
			    	+ ",'"+tipo_dteref+"'"
		    		+ ", '"+folioref.toUpperCase()+"'"
		    		+ ","+fec_ref+" "
		    		+ ",'"+g_afecta+"' "
			    	
			    	+ ")";
			    System.out.println(SQL_GUIA);
			    state.executeUpdate(SQL_GUIA,Statement.RETURN_GENERATED_KEYS);
			    
			    ResultSet generatedKeys = null;
	    		  generatedKeys = state.getGeneratedKeys();
	    		  //String id_g="";
	    		  if (generatedKeys.next()) {
	    			  GID=generatedKeys.getString(1);
	    		  }
	    		  
	    		  System.out.println("Nuevo id g: "+GID);
	    		  
				//----------------- DETALLE -----------------------//
				if(request.getParameter("serv1")!="" && request.getParameter("val1")!=""){
					String serv1=request.getParameter("serv1");
					String val1=request.getParameter("val1");
					String SQL_FACDET = "INSERT INTO detalle_822 (822_ID, d822_valor, D822_TEXT) VALUES ("+GID+","+val1+",'"+serv1.toUpperCase()+"')";
					state.executeUpdate(SQL_FACDET);}
				if(request.getParameter("serv2")!="" && request.getParameter("val2")!=""){
					String serv2=request.getParameter("serv2");
					String val2=request.getParameter("val2");
			        String SQL_FACDET = "INSERT INTO detalle_822 (822_ID, d822_valor, D822_TEXT) VALUES ("+GID+","+val2+",'"+serv2.toUpperCase()+"')";
		            state.executeUpdate(SQL_FACDET);}
				if(request.getParameter("serv3")!="" && request.getParameter("val3")!=""){
					String serv3=request.getParameter("serv3");
					String val3=request.getParameter("val3");
			        String SQL_FACDET = "INSERT INTO detalle_822 (822_ID, d822_valor, D822_TEXT) VALUES ("+GID+","+val3+",'"+serv3.toUpperCase()+"')";
		            state.executeUpdate(SQL_FACDET);}
				if(request.getParameter("serv4")!="" && request.getParameter("val4")!=""){
					String serv4=request.getParameter("serv4");
					String val4=request.getParameter("val4");
			        String SQL_FACDET = "INSERT INTO detalle_822 (822_ID, d822_valor, D822_TEXT) VALUES ("+GID+","+val4+",'"+serv4.toUpperCase()+"')";
		            state.executeUpdate(SQL_FACDET);}
				if(request.getParameter("serv5")!="" && request.getParameter("val5")!=""){
					String serv5=request.getParameter("serv5");
					String val5=request.getParameter("val5");
			        String SQL_FACDET = "INSERT INTO detalle_822 (822_ID, d822_valor, D822_TEXT) VALUES ("+GID+","+val5+",'"+serv5.toUpperCase()+"')";
		            state.executeUpdate(SQL_FACDET);}
				//--------------------- FIN -----------------------//
				
				 //insertamos logs
	    		String log_sql=""
	    				+ " INSERT INTO `log` ("
	    				+ "	`log`.`log_fec`, "
	    				+ "	`log`.`log_nombre`, " 
	    				+ "	`log`.`Usuarios_id` "
	    				+ " ) "
	    				+ " VALUES "
	    				+ " (now(), 'USUARIO INGRESA REGISTRO "+GID+" A LA TABLA 822', '"+id_iusuario+"') ";
	    		state.executeUpdate(log_sql);
				
				state.close();
				conexion.close();
	        	
				response.sendRedirect("menuserv?Exito=OK");
	        	//RequestDispatcher rd_up = request.getRequestDispatcher("menuserv?Exito=OK");
	        	//rd_up.forward(request, response);
	        	return;
	        	
			}catch(Exception ex){
				ex.printStackTrace();
			    out.println("Error "+ex);
			}
		}else{
		
		request.setAttribute("usuname", Usuarios_nombre);
		Statement state = null;
		Statement statecor = null;		
		ResultSet ESTADOS_RS = null;
		ResultSet EMPRESAS_RS = null;
		ResultSet CLIENTE_RS = null;
		ResultSet CONTACTOS_RS = null;
		ResultSet DIRECCION_RS = null;
		try {
			
			//import java.io.IOException;
			
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		statecor = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
    		//--------------------- ESTADOS ----------------------//
		    
    		String SQL_ESTADOS = "SELECT * FROM estados_vig_novig";
		    ESTADOS_RS =  state.executeQuery(SQL_ESTADOS);
		    ArrayList<String> estados = new ArrayList<String>();
		    while(ESTADOS_RS.next()){ estados.add(ESTADOS_RS.getString("estados_vig_novig_id")+"/"+ESTADOS_RS.getString("estados_vig_novig_nombre")); }
		    ESTADOS_RS.close();
		    String[] ar_estados = new String[estados.size()];
		    for(int x=0; x < estados.size(); x++){ ar_estados[x]=estados.get(x); }
		    request.setAttribute("ar_estados", ar_estados);
		    //----------------------- FIN ------------------------//

    		//--------------------- EMISOR ----------------------//
		    String SQL_EMPRESA = "SELECT * FROM empresas WHERE estados_vig_novig_id = 1 AND empresas_relacionada = 1 ORDER BY empresas_nombre";
		    EMPRESAS_RS =  state.executeQuery(SQL_EMPRESA);
		    ArrayList<String> empresas = new ArrayList<String>();
		    while(EMPRESAS_RS.next()){ empresas.add(EMPRESAS_RS.getString("empresas_id")+"/"+EMPRESAS_RS.getString("empresas_nombre")); }
		    EMPRESAS_RS.close();
		    String[] ar_empresas = new String[empresas.size()];
		    for(int x=0; x < empresas.size(); x++){ ar_empresas[x]=empresas.get(x);}
		    request.setAttribute("ar_empresas", ar_empresas);
		    //----------------------- FIN ------------------------//
		    
    		//--------------------- CLIENTE ----------------------//
		    String SQL_CLIENTE = "SELECT"
		    		+ " 	empresas.*, CONCAT_WS(' ',`usuarios`.`Usuarios_nombre`,`usuarios`.`Usuarios_ape_p`,`usuarios`.`Usuarios_ape_m`) AS Usuarios_nombre_com"
		    		+ "	FROM"
		    		+ "		empresas"
		    		+ "	INNER JOIN `usuarios` ON `usuarios`.`Usuarios_id` = empresas.`responsable_id`"
		    		+ "	WHERE"
		    		+ "		empresas.estados_vig_novig_id = 1 AND `empresas`.`empresas_escliente`=1"
		    		+ "	ORDER BY"
		    		+ "		empresas_nombrenof";
		    CLIENTE_RS =  state.executeQuery(SQL_CLIENTE);CLIENTE_RS =  state.executeQuery(SQL_CLIENTE);
		    ArrayList<String> clientes = new ArrayList<String>();
		    while(CLIENTE_RS.next()){ 

				String empresas_rut_ar[]=CLIENTE_RS.getString("empresas_rut").split("-");
				java.text.NumberFormat nff = java.text.NumberFormat.getInstance();
				String valRut = nff.format(Integer.parseInt(empresas_rut_ar[0]));
				
				String empresas_rut=valRut+"-"+empresas_rut_ar[1];

		    	clientes.add(
		    						CLIENTE_RS.getString("empresas_id")+"/"+
		    						CLIENTE_RS.getString("empresas_nombrenof")+"/"+
		    						empresas_rut+"/"+
		    						CLIENTE_RS.getString("empresas_razonsocial")+"/"+
		    						CLIENTE_RS.getString("Usuarios_nombre_com")+"/"+
		    						CLIENTE_RS.getString("empresas_giro")); }
		    CLIENTE_RS.close();
		    String[] ar_clientes = new String[clientes.size()];
		    for(int x=0; x < clientes.size(); x++){ ar_clientes[x]=clientes.get(x);}
		    request.setAttribute("ar_clientes", ar_clientes);
		    //----------------------- FIN ------------------------//
		    
    		//--------------------- CONTACTO ----------------------//
		    String SQL_CONTACTO = "SELECT cont_id,CLPR_ID,PERS_ID,CONT_TELEFONO,CONT_EMAIL,CONT_TELEFONOC, CONCAT_WS(' ',CONT_NOMBRE,CONT_APEP,CONT_APEM) AS CONT_NOMBRE  FROM contacto ORDER BY cont_nombre";
		     CONTACTOS_RS =  state.executeQuery(SQL_CONTACTO);
		    ArrayList<String> contactos = new ArrayList<String>();
		    while(CONTACTOS_RS.next()){ 
		    	contactos.add(
		    					CONTACTOS_RS.getString("cont_id")+"/"+
		    					CONTACTOS_RS.getString("cont_nombre").replace("/", " ")+"/"+
		    					CONTACTOS_RS.getString("CLPR_ID")+"/"+
		    					CONTACTOS_RS.getString("PERS_ID")+"/"+
		    					CONTACTOS_RS.getString("CONT_TELEFONO").replace("/", " ")+"/"+
		    					CONTACTOS_RS.getString("CONT_TELEFONOC")+"/"+
		    					CONTACTOS_RS.getString("CONT_EMAIL").replace("/", " ")); }
		    CONTACTOS_RS.close();
		    String[] ar_contactos = new String[contactos.size()];
		    for(int x=0; x < contactos.size(); x++){ ar_contactos[x]=contactos.get(x);}
		    request.setAttribute("ar_contactos", ar_contactos);
		    //----------------------- FIN ------------------------//

    		//--------------------- DIRECCION ----------------------//
		    String SQL_DIRECCION = "SELECT"
		    		+ "		direccion.*, `region`.`REGI_NOMBRE`, `comuna`.`COMU_NOMBRE` "
		    		+ "	FROM"
		    		+ "		direccion"
		    		+ "	INNER JOIN `comuna` ON `comuna`.`COMU_ID` = `direccion`.`COMU_ID`"
		    		+ "	INNER JOIN `region` ON `region`.`REGI_ID` = `comuna`.`REGI_ID`"
		    		+ "	WHERE"
		    		+ "		estados_vig_novig_id = 1"
		    		+ "	ORDER BY"
		    		+ "		DIRE_DIRECCION";
		    System.out.println(SQL_DIRECCION);
		    DIRECCION_RS =  state.executeQuery(SQL_DIRECCION);
		    ArrayList<String> direcciones = new ArrayList<String>();
		    while(DIRECCION_RS.next()){
		    	direcciones.add(DIRECCION_RS.getString("dire_id")+"/"+DIRECCION_RS.getString("DIRE_DIRECCION").replace("/", " ")+"/"+DIRECCION_RS.getString("CLPR_ID")+"/"+DIRECCION_RS.getString("REGI_NOMBRE")+"/"+DIRECCION_RS.getString("DIRE_CIUDAD")+"/"+DIRECCION_RS.getString("COMU_NOMBRE")); }
		    DIRECCION_RS.close();
		    String[] ar_direcciones = new String[direcciones.size()];
		    for(int x=0; x < direcciones.size(); x++){ ar_direcciones[x]=direcciones.get(x);}
		    request.setAttribute("ar_direcciones", ar_direcciones);
		    //----------------------- FIN ------------------------//
		   //-------------------------NOW-------------------------//
            state = (Statement) ((java.sql.Connection) conexion).createStatement();
            
            
            String fec_sql="SELECT DATE_FORMAT(now(), '%d-%m-%Y') as fecha ";
            ResultSet fec_rs = state.executeQuery(fec_sql);
         
            fec_rs.next();
            
            request.setAttribute("fecha", fec_rs.getString("fecha"));
            statecor.close();
            conexion.close();
		}catch(Exception ex){
		    out.println("Error "+ex);
		    ex.printStackTrace();
		}
		String msg="";
		if(guiaexiste){
			msg="?Exito=NOK";
			String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
			String gv_id = request.getParameter("gv_id");
			request.setAttribute("gv_id",gv_id);
			request.setAttribute("estados_vig_novig_id",estados_vig_novig_id);
			//System.out.println("grupos_nombre "+grupos_nombre);
		}
		RequestDispatcher rd = request.getRequestDispatcher("iserv.jsp"+msg);
        rd.forward(request, response);
		}
	}

}
