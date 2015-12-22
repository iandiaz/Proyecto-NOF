

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
 * Servlet implementation class mserv
 */
@WebServlet("/mserv")
public class mserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String det3;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mserv() {
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

		if(request.getParameter("grabar") != null){
			try {
				Statement state = null;
				ResultSet DET_RS = null;

				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		state = (Statement) ((java.sql.Connection) conexion).createStatement();

				//----------------- CABECERA -----------------------//
				String GID=request.getParameter("gd_id");
				String empresa_id=request.getParameter("empresa_id");
				String estados_vig_novig_id=request.getParameter("estados_vig_novig_id");
				String CLPR_ID=request.getParameter("clientes_id");
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
				
				//----------------- DETALLE -----------------------//
				String SQL_DEL = "DELETE FROM detalle_822 WHERE 822_ID = "+GID;
				state.executeUpdate(SQL_DEL);
				if(request.getParameter("serv1")!="" && request.getParameter("val1")!=""){
					String serv1=request.getParameter("serv1");
					String val1=request.getParameter("val1");
					String SQL_FACDET = "INSERT INTO detalle_822 (822_ID, d822_valor, d822_text) VALUES ("+GID+","+val1+",'"+serv1+"')";
					state.executeUpdate(SQL_FACDET);}
				if(request.getParameter("serv2")!="" && request.getParameter("val2")!=""){
					String serv2=request.getParameter("serv2");
					String val2=request.getParameter("val2");
					String SQL_FACDET = "INSERT INTO detalle_822 (822_ID, d822_valor, d822_text) VALUES ("+GID+","+val2+",'"+serv2+"')";
		            state.executeUpdate(SQL_FACDET);}
				if(request.getParameter("serv3")!="" && request.getParameter("val3")!=""){
					String serv3=request.getParameter("serv3");
					String val3=request.getParameter("val3");
					String SQL_FACDET = "INSERT INTO detalle_822 (822_ID, d822_valor, d822_text) VALUES ("+GID+","+val3+",'"+serv3+"')";
		            state.executeUpdate(SQL_FACDET);}
				if(request.getParameter("serv4")!="" && request.getParameter("val4")!=""){
					String serv4=request.getParameter("serv4");
					String val4=request.getParameter("val4");
					String SQL_FACDET = "INSERT INTO detalle_822 (822_ID, d822_valor, d822_text) VALUES ("+GID+","+val4+",'"+serv4+"')";
		            state.executeUpdate(SQL_FACDET);}
				if(request.getParameter("serv5")!="" && request.getParameter("val5")!=""){
					String serv5=request.getParameter("serv5");
					String val5=request.getParameter("val5");
					String SQL_FACDET = "INSERT INTO detalle_822 (822_ID, d822_valor, d822_text) VALUES ("+GID+","+val5+",'"+serv5+"')";
		            state.executeUpdate(SQL_FACDET);}
				//--------------------- FIN -----------------------//
			    
			    String SQL_GUIA = ""
			    		+ "UPDATE `822` "
			    		+ " SET "
			    		+ "	822_FECHA="+gv_fecha+","
			    		+ " CLPR_ID='"+CLPR_ID+"', "
			    		+ " DIRE_ID='"+DIRE_ID+"',"
			    		+ " CONT_ID='"+CONT_ID+"',"
			    		+ " 822_EMPRESA_EMISORA='"+empresa_id+"',"
			    	
			    		+ " 822_obs1='"+OBS1+"',"
			    		+ " 822_obs2='"+OBS2+"',"
			    		+ " 822_fecmod=now(),"
			    		+ " 822_modificador='"+id_iusuario+"'"
			    		+ " ,estados_vig_novig_id="+estados_vig_novig_id+""
			    		+ " ,tipo_guia_serv='"+tipo_guia_serv+"'"
			    		
			    		+ "	,822_ciudad='"+gv_ciudad+"'"
			    		+ "	,822_empresa_emisora_nombre='"+empresa_emisora_nombre+"'"
			    		+ "	,cont_nombre='"+cont_nombre.toUpperCase()+"'"
			    		+ "	,cont_telefonoc='"+cont_telefonoc+"'"
			    		+ "	,cont_email='"+cont_email+"'"
			    		+ "	,cont_telefono='"+cont_phone+"'"
			    		
			    		
			    		+ "	,822_SUBTOTAL='"+SUBTOTAL+"'"
			    		+ "	,822_TOTAL='"+TOTAL+"' "
			    		+ "	,822_descuento='"+desc+"' "
			    		+ "	,822_glosa_descuento='"+glosa_desc+"'"
			    		+ "	,822_NETO='"+NETO+"' "
			    		+ "	,822_IVA='"+IVA+"' "
			    		+ "	,822_responsable_name='"+resp+"' "
			    		+ "	,`822`.`tipo_dteref`='"+tipo_dteref+"' "
						+ "	,`822`.`folioref`='"+folioref+"' "
						+ "	,`822`.`fec_ref`="+fec_ref+"  "
						+ "	,`822`.`822_afecta`='"+g_afecta+"'  "
			    		
			    		
			    		+ " WHERE 822_id="+GID;
			    state.executeUpdate(SQL_GUIA);
			    System.out.println(SQL_GUIA);
			    
			    //insertamos logs
	    		String log_sql=""
	    				+ " INSERT INTO `log` ("
	    				+ "	`log`.`log_fec`, "
	    				+ "	`log`.`log_nombre`, " 
	    				+ "	`log`.`Usuarios_id` "
	    				+ " ) "
	    				+ " VALUES "
	    				+ " (now(), 'USUARIO MODIFICA REGISTRO "+GID+" DE LA TABLA 822', '"+id_iusuario+"') ";
	    		state.executeUpdate(log_sql);
			    
				state.close();
	        	
	        	RequestDispatcher rd_up = request.getRequestDispatcher("menuserv?Exito=OK");
	        	rd_up.forward(request, response);
	        	//return;
	        	
			}catch(Exception ex){
				ex.printStackTrace();
			    out.println("ERROR "+ex);
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
		ResultSet CAB_RS = null;
		ResultSet DET_RS = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		statecor = (Statement) ((java.sql.Connection) conexion).createStatement();

    		String GID=request.getParameter("gd_id");
    		   String SQL_CAB = "SELECT DATE_FORMAT(822_FECHA, '%d-%m-%Y') as gd_fecha,"
		    			+ " g.822_FOLIO,			e2.empresas_nombre as emi,	v.estados_vig_novig_nombre,	v.estados_vig_novig_id,	e.empresas_nombre,	e.empresas_rut,"
		    			+ " e.empresas_id,		e.empresas_razonsocial,		d.DIRE_DIRECCION, 			r.REGI_NOMBRE,		d.DIRE_CIUDAD AS 822_CIUDAD,"
		    			+ " g.CONT_NOMBRE, 		c.CONT_TELEFONO,			g.822_responsable_name as 822_RESPONSABLE,	g.CONT_ID, 		 	"
		    			+ " g.822_obs1,  		g.822_obs2, 				o.COMU_NOMBRE , 	g.CLPR_ID, g.DIRE_ID,  "
		    			
		    			+ " CONCAT(u.Usuarios_nombre,' ',u.Usuarios_ape_p) AS perfilusu_creador,"
			    		+ "	DATE_FORMAT(g.822_feccreacion,'%d-%m-%Y %H:%i:%s') AS perfilusu_feccreacion,"
			    		+ "	IF (g.822_fecmod IS NULL,' ',DATE_FORMAT(g.822_fecmod,'%d-%m-%Y %H:%i:%s')) AS perfilusu_fecmod,"
			    		+ "	IF (g.822_modificador IS NULL,' ',CONCAT(u2.Usuarios_nombre,' ',u2.Usuarios_ape_p)) AS perfilusu_modificador, "
			    		+ " IF(g.id_dte is null,'1','0') as dte, "
			    		+ "	g.tipo_guia_serv,  "
			    		+ " 822_subtotal,822_neto,822_iva,822_total,822_descuento,822_glosa_descuento,  "
			    		
			    		+ "		CONCAT_WS(' ',u3.`Usuarios_nombre`,u3.`Usuarios_ape_p`,u3.`Usuarios_ape_m`) AS Usuarios_nombre_com, "
			    		
			    		+ "		IF(`g`.`tipo_dteref` is null,'NINGUNA',`g`.`tipo_dteref`) as tipo_dteref, "
						+ "		IF(`g`.`folioref` is null ,'',`g`.`folioref`) as  folioref, "
						+ "		IF(`g`.`fec_ref` is null,'',DATE_FORMAT(`g`.`fec_ref`,'%d-%m-%Y')) as fec_ref , "
						+ "		g.822_afecta as g_afecta, "
						+ "		e.empresas_giro  "

		    			+ " FROM `822` g"
		    			+ " LEFT JOIN empresas e ON g.CLPR_ID = e.empresas_id"
		    			+ " LEFT JOIN empresas e2 ON g.822_EMPRESA_EMISORA = e2.empresas_id "
		    			+ " LEFT JOIN direccion d ON g.DIRE_ID = d.DIRE_ID"
		    			+ " LEFT JOIN comuna o ON d.COMU_ID = o.COMU_ID"
		    			+ " LEFT JOIN region r ON o.REGI_ID = r.REGI_ID"
	 	    			+ " LEFT JOIN contacto c ON g.CONT_ID = c.CONT_ID "
	 	    			+ " INNER JOIN estados_vig_novig v ON g.estados_vig_novig_id = v.estados_vig_novig_id "
	 	    			
						+ " INNER JOIN usuarios u ON g.822_creador = u.Usuarios_id "
						+ " LEFT JOIN usuarios u2 ON g.822_modificador = u2.Usuarios_id "
						+ " INNER JOIN `usuarios` u3 ON u3.`Usuarios_id` = e.`responsable_id` "
				    	

	 	    			+ " WHERE 822_id = "+GID;
		    System.out.println(SQL_CAB);
		    CAB_RS =  state.executeQuery(SQL_CAB);
		    if(CAB_RS.next()){
		    	request.setAttribute("estados_vig_novig_id", CAB_RS.getString("estados_vig_novig_id"));
		    	request.setAttribute("CLPR_ID", CAB_RS.getString("CLPR_ID"));
		    	request.setAttribute("direccion_id", CAB_RS.getString("DIRE_ID"));
		    	request.setAttribute("cont_id", CAB_RS.getString("CONT_ID"));
		    	request.setAttribute("g_afecta",CAB_RS.getString("g_afecta"));
		    	
		    	request.setAttribute("822_id", GID);
		    	request.setAttribute("gd_fecha", CAB_RS.getString("gd_fecha"));
		    	request.setAttribute("GD_FOLIO", CAB_RS.getString("822_FOLIO"));
		    	request.setAttribute("emi", CAB_RS.getString("emi"));
		    	request.setAttribute("estados_vig_novig_nombre", CAB_RS.getString("estados_vig_novig_nombre"));
		    	request.setAttribute("empresas_nombre", CAB_RS.getString("empresas_nombre"));
		    	request.setAttribute("empresas_rut", CAB_RS.getString("empresas_rut"));
		    	request.setAttribute("empresas_id", CAB_RS.getString("empresas_id"));
		    	request.setAttribute("empresas_razonsocial", CAB_RS.getString("empresas_razonsocial"));
		    	request.setAttribute("DIRE_DIRECCION", CAB_RS.getString("DIRE_DIRECCION"));
		    	request.setAttribute("REGI_NOMBRE", CAB_RS.getString("REGI_NOMBRE"));
		    	request.setAttribute("GD_CIUDAD", CAB_RS.getString("822_CIUDAD"));
		    	request.setAttribute("CONT_NOMBRE", CAB_RS.getString("CONT_NOMBRE"));
		    	request.setAttribute("CONT_TELEFONO", CAB_RS.getString("CONT_TELEFONO"));
		    	request.setAttribute("GD_RESPONSBALE", CAB_RS.getString("822_RESPONSABLE"));
		    	request.setAttribute("guia_obs1", CAB_RS.getString("822_obs1"));
		    	request.setAttribute("guia_obs2", CAB_RS.getString("822_obs2"));
		    	request.setAttribute("fec_crea", CAB_RS.getString("perfilusu_feccreacion"));
		    	request.setAttribute("fec_mod", CAB_RS.getString("perfilusu_fecmod"));
		    	request.setAttribute("user_mod", CAB_RS.getString("perfilusu_modificador"));
		    	request.setAttribute("user_crea", CAB_RS.getString("perfilusu_creador"));
		    	String estado_sii = "NO ENVIADA";
		    	if(CAB_RS.getString("dte").equals("0")){estado_sii = "ENVIADA";}
				if(CAB_RS.getString("dte").equals("1")){estado_sii = "PARA ENVIAR";}
		    	request.setAttribute("estado_sii", estado_sii);
		    	request.setAttribute("PERS_NOMBRE", CAB_RS.getString("Usuarios_nombre_com"));
		    	request.setAttribute("GD_COMUNA", CAB_RS.getString("COMU_NOMBRE"));
		    	request.setAttribute("tipo_guia_serv", CAB_RS.getString("tipo_guia_serv"));
		    	
		    	request.setAttribute("empresas_giro",CAB_RS.getString("empresas_giro"));
		    	
		    	String SUBTOTAL=CAB_RS.getString("822_subtotal");
		    	String desc=CAB_RS.getString("822_descuento");
		    	String NETO=CAB_RS.getString("822_neto");
		    	String IVA=CAB_RS.getString("822_iva");
		    	String TOTAL=CAB_RS.getString("822_total");
		    	String glosa_desc=CAB_RS.getString("822_glosa_descuento");
		    	
            	 request.setAttribute("subtotal", SUBTOTAL);
    	 		 request.setAttribute("descuento", desc);
    	 		request.setAttribute("neto", NETO);
    	 		request.setAttribute("iva", IVA);
    	 		request.setAttribute("total", TOTAL);
    	 		request.setAttribute("glosadescuento", glosa_desc);
    	 		
    	 		
    	 		request.setAttribute("fec_ref", CAB_RS.getString("fec_ref"));
    	    	request.setAttribute("folioref", CAB_RS.getString("folioref"));
    	    	String tipo_dteref=CAB_RS.getString("tipo_dteref").equals("") ? "NINGUNA": CAB_RS.getString("tipo_dteref");
    	    	request.setAttribute("tipo_dteref", tipo_dteref);
    	    	
		    }
		    CAB_RS.close();
		    
		    String SQL_DET = "SELECT * FROM detalle_822 WHERE 822_id = "+GID+" ORDER BY d822_id";
			System.out.println(SQL_DET);
		    DET_RS =  state.executeQuery(SQL_DET);
		    ArrayList<String> prod = new ArrayList<String>();
		    Integer c=0;
		    while(DET_RS.next()){ prod.add(c+"/"+DET_RS.getString("d822_text")+"/"+DET_RS.getString("d822_valor")); c=c+1;}
		    DET_RS.close();
		    String[] ar_prod = new String[prod.size()];
		    for(int x=0; x < prod.size(); x++){ ar_prod[x]=prod.get(x); }
		    request.setAttribute("ar_prod", ar_prod);
		    request.setAttribute("ar_prod", ar_prod);
		    
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
		    System.out.println(SQL_CLIENTE);
		    CLIENTE_RS =  state.executeQuery(SQL_CLIENTE);
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
		    while(CONTACTOS_RS.next()){ contactos.add(
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
		    
		  //--------------------- PERSONAL ----------------------//
		    String SQL_PER = "SELECT * FROM personal";
		    ResultSet PER_RS = state.executeQuery(SQL_PER);
		    ArrayList<String> personal = new ArrayList<String>();
		    while(PER_RS.next()){ 
		    	personal.add(PER_RS.getString("PERS_ID")+"/"+PER_RS.getString("PERS_NOMBRE").replace("/", " ")); }
		    PER_RS.close();
		    String[] ar_per = new String[personal.size()];
		    for(int x=0; x < personal.size(); x++){ ar_per[x]=personal.get(x);}
		    request.setAttribute("ar_per", ar_per);
		    //----------------------- FIN ------------------------//
		    
            statecor.close();
            conexion.close();
            
            RequestDispatcher rd = request.getRequestDispatcher("mserv.jsp");
            rd.forward(request, response);
            
		}catch(Exception ex){
		    out.println("ERROR "+ex);
		    ex.printStackTrace();
		}
	
		}
	}

}
