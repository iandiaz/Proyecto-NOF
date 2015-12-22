

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
 * Servlet implementation class ires_add
 */
@WebServlet("/ires_add")
public class ires_add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ires_add() {
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
		    	//System.out.println("cookie: "+cookie.getName()+"-"+cookie.getValue());
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

		boolean guiaexiste=false;
		if(request.getParameter("grabar") != null){
			try {
				Statement state = null;
				Statement state_des = null;
				Statement state_grt = null;
				Statement state_ins = null;
				Statement state_guia = null;
				Statement state_borra = null;
				

				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		state_des = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		state_grt = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		state_ins = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		state_guia = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		state_borra = (Statement) ((java.sql.Connection) conexion).createStatement();

				//----------------- CABECERA -----------------------//
	    		String GIDR="";
				String clientes_id=request.getParameter("clientes_id");
				String empresa_id=request.getParameter("empresa_id");
				String DIRE_ID=request.getParameter("dire_id");
				String gr_ciudad=request.getParameter("gv_ciudad");
				String CONT_ID=request.getParameter("cont_id");
				
				String OBS=request.getParameter("obs");
				String tipodte=request.getParameter("tipodte");
				String fac_servim_fecvencimiento_ar[]=request.getParameter("fac_servim_fecvencimiento").toString().split("-");
				String fac_servim_fecvencimiento=fac_servim_fecvencimiento_ar[2]+"-"+fac_servim_fecvencimiento_ar[1]+"-"+fac_servim_fecvencimiento_ar[0];
				
				String cont_mail=request.getParameter("cont_mail");
				String cont_phone=request.getParameter("cont_phone");
				String cont_nombre=request.getParameter("cont_nombre");
				String cont_phonec=request.getParameter("cont_telefonoc");
				
				String neto=request.getParameter("neto");
				String descuento=request.getParameter("desc");
				String glosa_desc=request.getParameter("glosa_desc");
				
				String SUBTOTAL=request.getParameter("subtotal");
		        String IVA=request.getParameter("iva");
	            String TOTAL=request.getParameter("total");
	            String resp=request.getParameter("resp");
	            String empresa_emisora_nombre= request.getParameter("empresa_emisora_nombre");
				
	            String datepickerfechafactura= request.getParameter("datepickerfechafactura");
	            String datepickerfechafactura_[]=datepickerfechafactura.split("-");
	            datepickerfechafactura ="'"+datepickerfechafactura_[2]+"-"+datepickerfechafactura_[1]+"-"+datepickerfechafactura_[0]+"'";
	            
	            
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
	            
				//--------------------- FIN -----------------------//

					//INSERTAMOS TODOS LOS DATOS EN NOF LA CABECERA DE LA FACTURA
			    	//agregar el id_dte para cuando se mande al sii
			    	//aregar gr_folio para la respuesta del sii, este valor es el que deberia ir arriba en vez del ND
				    String SQL_GUIA = "INSERT INTO `802` ("
				    		+ "cliente_id"
				    		+ ", 802_empresa_emisora"
				    		+ ", direccion_id, 802_ciudad, contacto_id, 802_obs,"
				    		+ "802_feccreacion, 802_creador, estados_vig_novig_id"
				    		+ ",802_neto,802_tipodte,802_fecvencimiento,802_descuento,802_glosa_desc"
				    		+ ", 802_estado,cont_email"
				    		+ ",cont_telefono"
				    		+ ",cont_nombre"
				    		+ "	,802_SUBTOTAL"
				    		+ "	,802_TOTAL"
				    		+ "	,802_IVA"
				    		
				    		+ "	,802_responsable_name"
				    		+ "	,802_empresa_emisora_nombre"
				    		+ "	,cont_telefonoc"
				    		+ "	,802_fecha"
				    		+ "	,`802`.`tipo_dteref` "
							+ "	,`802`.`folioref` "
							+ "	,`802`.`fec_ref` "
				    			
				    		+ ")"
				    	+ " VALUES ('"+clientes_id+"','"+empresa_id+"','"+DIRE_ID+"','"+gr_ciudad.toUpperCase()+"','"+CONT_ID+"','"+OBS.toUpperCase()+"'"
				    	+ ",now()"
				    	+ ","+id_iusuario+",1,'"+neto+"','"+tipodte+"'"
				    	+ ",'"+fac_servim_fecvencimiento+"','"+descuento+"'"
				    	+ ",'"+glosa_desc.toUpperCase()+"','NO EMITIDA','"+cont_mail.toUpperCase()+"'"
				    	+ ",'"+cont_phone.toUpperCase()+"'"
				    	+ ",'"+cont_nombre.toUpperCase()+"'"
				    	+ ",'"+SUBTOTAL+"'"
				    	+ ",'"+TOTAL+"'"
				    	+ ",'"+IVA+"'"
				    	+ ",'"+resp+"'"
				    	+ ",'"+empresa_emisora_nombre+"'"
				    	+ ",'"+cont_phonec+"'"
				    	+ ","+datepickerfechafactura+""
				    	+ ",'"+tipo_dteref+"'"
				    	+ ", '"+folioref.toUpperCase()+"'"
				    	+ ","+fec_ref+" "
				    	
				    	+ ")";
				    System.out.println(SQL_GUIA);
				    state.executeUpdate(SQL_GUIA,Statement.RETURN_GENERATED_KEYS);
				    
				    ResultSet generatedKeys = null;
		    		generatedKeys = state.getGeneratedKeys();
		    		//String id_g="";
		    		if (generatedKeys.next()) {
		    			GIDR=generatedKeys.getString(1);
		    		}
		    	
		    		
		    		 String[] seleccionado_detguias = request.getParameterValues("detguias[]");
					    if(seleccionado_detguias!=null)
					    	
			    		for (String id_gi: seleccionado_detguias) {
			    			if(id_gi!=null && !id_gi.equals("-1")){
			    				
			    				String g[]=id_gi.split("/");
			    				String fec_[] =g[2].split("-");
			    				String fec=fec_[2]+"-"+fec_[1]+"-"+fec_[0]; 
			    				
			    				String SQL_FACDET = "INSERT INTO detalle_802 (802_id, d802_cod, d802_TOTAL, d802_FECHA, estados_vig_novig_id,d802_folio) "
				  	    				+ "VALUES ("+GIDR+",'"+g[0]+"',"+g[1]+",'"+fec+"',1,'"+g[3]+"')";
				      			System.out.println(SQL_FACDET);
				      			state_ins.executeUpdate(SQL_FACDET);
				      			
				      			String SQL_DESPACHO = "";
				      			if(g[0].indexOf("S")!=-1){
				      				 SQL_DESPACHO = "UPDATE `822` SET en_factura="+GIDR+" WHERE 822_ID ="+g[0].replace("S", "");
				      			}
				      			else{
				      				SQL_DESPACHO = "UPDATE `821` SET en_factura="+GIDR+" WHERE 821_ID ="+g[0].replace("A", "");	
				      			}
				      			
				      			System.out.println(SQL_DESPACHO);
				      			state_des.executeUpdate(SQL_DESPACHO);
				      		
			    				
			    			}
			    		}
			    		
			    	
		    		
		    		
					  //insertamos logs
			    		String log_sql=""
			    				+ " INSERT INTO `log` ("
			    				+ "	`log`.`log_fec`, "
			    				+ "	`log`.`log_nombre`, " 
			    				+ "	`log`.`Usuarios_id` "
			    				+ " ) "
			    				+ " VALUES "
			    				+ " (now(), 'USUARIO INGRESA REGISTRO "+GIDR+" EN TABLA 802', '"+id_iusuario+"') ";
			    		state.executeUpdate(log_sql);
					
				   
				   //--------------------- FIN -----------------------//
	      			
					state_grt.close();state_ins.close();state_guia.close();state_borra.close();
	      			state_des.close();
	      			state.close();
		        	
		        	RequestDispatcher rd_up = request.getRequestDispatcher("menures?Exito=OK");
		        	rd_up.forward(request, response);
		        	//return;

	        	
			}catch(Exception ex){
				ex.printStackTrace();
			    out.println("Error "+ex);
			}
		}else{
		
		request.setAttribute("usuname", Usuarios_nombre);
		Statement state = null;
		Statement statecor = null;
		Statement statefac = null;
		Statement state_fec = null;
		Statement state_valida = null;
		Statement state_verifica = null;
		Statement state_borra = null;
		
		ResultSet ESTADOS_RS = null;
		ResultSet EMPRESAS_RS = null;
		ResultSet CLIENTE_RS = null;
		ResultSet CONTACTOS_RS = null;
		ResultSet DIRECCION_RS = null;
		
	    ResultSet Alertas_RS = null;
		ResultSet RS_FAC = null;
		ResultSet RS_CUENTA = null;
		try {
			
			//import java.io.IOException;
			
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		statecor = (Statement) ((java.sql.Connection) conexion).createStatement();
    		state_fec = (Statement) ((java.sql.Connection) conexion).createStatement();
    		state_borra = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
    		String ref=request.getParameter("ref");
    		String obs=request.getParameter("obs");
    		
    		request.setAttribute("ref",ref);
    		request.setAttribute("obs",obs);
    		
    		request.setAttribute("empresa_emisor", request.getParameter("empresa_emisor"));
    		request.setAttribute("afecta",request.getParameter("afecta"));
    		
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
		    String EID=request.getParameter("clientes_id");
		    String SQL_CLIENTE = "SELECT empresas.* ,"
		    		+ "		CONCAT_WS(' ',`usuarios`.`Usuarios_nombre`,`usuarios`.`Usuarios_ape_p`,`usuarios`.`Usuarios_ape_m`) AS Usuarios_nombre_com  "
    		  		
		    		+ " FROM empresas "
		    		+ " INNER JOIN `usuarios` ON `usuarios`.`Usuarios_id` = empresas.`responsable_id` "
		    		+ " WHERE empresas.empresas_id = "+EID;
		    System.out.println(SQL_CLIENTE);
		    CLIENTE_RS =  state.executeQuery(SQL_CLIENTE);
		    if(CLIENTE_RS.next()){
			    request.setAttribute("empresas_id", CLIENTE_RS.getString("empresas_id"));
			    request.setAttribute("empresas_nombrenof", CLIENTE_RS.getString("empresas_nombrenof"));
			    request.setAttribute("empresas_rut", CLIENTE_RS.getString("empresas_rut"));
			    request.setAttribute("empresas_razonsocial", CLIENTE_RS.getString("empresas_razonsocial"));
			    request.setAttribute("empresas_razonsocial", CLIENTE_RS.getString("empresas_razonsocial"));
			    request.setAttribute("Usuarios_nombre_com", CLIENTE_RS.getString("Usuarios_nombre_com"));
			    request.setAttribute("empresas_giro", CLIENTE_RS.getString("empresas_giro"));
			    
		    }
		    //----------------------- FIN ------------------------//
		    
    		//--------------------- CONTACTO ----------------------//
		    String SQL_CONTACTO = "SELECT cont_id,CLPR_ID,PERS_ID,CONT_TELEFONO,CONT_EMAIL,CONT_TELEFONOC, CONCAT_WS(' ',CONT_NOMBRE,CONT_APEP,CONT_APEM) AS CONT_NOMBRE  FROM contacto WHERE CLPR_ID = "+EID+" ORDER BY cont_nombre";
			   
		   // String SQL_CONTACTO = "SELECT * FROM contacto WHERE CLPR_ID = "+EID+" ORDER BY cont_nombre";
		    CONTACTOS_RS =  state.executeQuery(SQL_CONTACTO);
		    ArrayList<String> contactos = new ArrayList<String>();
		    while(CONTACTOS_RS.next()){ contactos.add(	CONTACTOS_RS.getString("cont_id")+"/"+
		    											CONTACTOS_RS.getString("cont_nombre").replace("/", " ")+"/"+
		    											CONTACTOS_RS.getString("CLPR_ID")+"/"+
		    											CONTACTOS_RS.getString("PERS_ID")+"/"+
		    											CONTACTOS_RS.getString("CONT_TELEFONO").replace("/", " ")+"/"+
		    											CONTACTOS_RS.getString("CONT_EMAIL").replace("/", " ")+"/"+
		    			    							CONTACTOS_RS.getString("CONT_TELEFONOC")); }
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
		    		+ "		estados_vig_novig_id = 1 AND direccion.CLPR_ID = "+EID
		    		+ "	ORDER BY"
		    		+ "		DIRE_DIRECCION";
		    //System.out.println(SQL_DIRECCION);
		    DIRECCION_RS =  state.executeQuery(SQL_DIRECCION);
		    ArrayList<String> direcciones = new ArrayList<String>();
		    while(DIRECCION_RS.next()){
		    	direcciones.add(DIRECCION_RS.getString("dire_id")+"/"+DIRECCION_RS.getString("DIRE_DIRECCION").replace("/", " ")+"/"+DIRECCION_RS.getString("CLPR_ID")+"/"+DIRECCION_RS.getString("REGI_NOMBRE")+"/"+DIRECCION_RS.getString("DIRE_CIUDAD")+"/"+DIRECCION_RS.getString("COMU_NOMBRE")); }
		    DIRECCION_RS.close();
		    String[] ar_direcciones = new String[direcciones.size()];
		    for(int x=0; x < direcciones.size(); x++){ ar_direcciones[x]=direcciones.get(x);}
		    request.setAttribute("ar_direcciones", ar_direcciones);
		    //----------------------- FIN ------------------------//
		    
		  
		    //--------------------- GUIAS ----------------------//
		    if(request.getParameter("agregar") != null){
		    	//String SQL_BORRA = "DELETE FROM guia_rt";
      			//state.executeUpdate(SQL_BORRA);
			    String[] seleccionado = request.getParameterValues("permisos[]");
			    if(seleccionado!=null){
			    	
	    		
		        request.setAttribute("ar_detguias", seleccionado);
		     
		        
			    
			    
			    }
		    }
		    
			
	      	//-------------------------GUIAS-------------------------//
		    
	     
            //------------------------------------------FIN-------------------------------------------//
	        
            //-------------------------NOW-------------------------//
            state_fec = (Statement) ((java.sql.Connection) conexion).createStatement();
            String fec_sql="SELECT DATE_FORMAT(now(), '%d-%m-%Y') as fecha ";
            ResultSet fec_rs = state_fec.executeQuery(fec_sql);
            fec_rs.next();
            request.setAttribute("fecha", fec_rs.getString("fecha"));
            
            String datepickerfechafactura=request.getParameter("datepickerfechafactura");
            if(datepickerfechafactura!=null && !datepickerfechafactura.equals(""))request.setAttribute("fecha", datepickerfechafactura);
            
            String fac_servim_fecvencimiento=request.getParameter("fac_servim_fecvencimiento");
            if(fac_servim_fecvencimiento!=null && !fac_servim_fecvencimiento.equals(""))request.setAttribute("fac_servim_fecvencimiento", fac_servim_fecvencimiento);
           
            String fac_servim_tipodte=request.getParameter("fac_servim_tipodte");
            if(fac_servim_tipodte!=null && !fac_servim_tipodte.equals(""))request.setAttribute("fac_servim_tipodte", fac_servim_tipodte);
           
            String desc=request.getParameter("desc");
            if(desc!=null && !desc.equals(""))request.setAttribute("desc",desc);
            
            String glosa_desc=request.getParameter("desc");
            if(glosa_desc!=null && !glosa_desc.equals(""))request.setAttribute("glosa_desc",glosa_desc);
          
            
            

            //-------------------------NOW-------------------------//
            
            statecor.close();
            state_fec.close();
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
		RequestDispatcher rd = request.getRequestDispatcher("ires_add.jsp"+msg);
        rd.forward(request, response);
		}
	}

}
