

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
 * Servlet implementation class mres_mod
 */
@WebServlet("/mres_mod")
public class mres_mod extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mres_mod() {
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
				Statement state_guia = null;
				ResultSet RS_DET = null;
				ResultSet TOTFIN_RS = null;
				

				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		state_guia = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
				//----------------- CABECERA -----------------------//
	    		String GRID=request.getParameter("guiaresumen_id");
				String estados_vig_novig_id=request.getParameter("estados_vig_novig_id");
				String DIRE_ID=request.getParameter("dire_id");
				String gr_ciudad=request.getParameter("gv_ciudad");
				String cont_id=request.getParameter("cont_id");
				
				String OBS=request.getParameter("obs");
				String neto=request.getParameter("neto");
				String empresa_id=request.getParameter("empresa_id");
				
				
				
				String tipodte=request.getParameter("tipodte");
				String fac_servim_fecvencimiento_ar[]=request.getParameter("fac_servim_fecvencimiento").toString().split("-");
				String fac_servim_fecvencimiento=fac_servim_fecvencimiento_ar[2]+"-"+fac_servim_fecvencimiento_ar[1]+"-"+fac_servim_fecvencimiento_ar[0];
				String descuento=request.getParameter("desc");
				String glosa_desc=request.getParameter("glosa_desc");
				
				
				String SUBTOTAL=request.getParameter("subtotal");
		        String IVA=request.getParameter("iva");
	            String TOTAL=request.getParameter("total");
	            String resp=request.getParameter("resp");
	            String empresa_emisora_nombre= request.getParameter("empresa_emisora_nombre");
				
	        	String cont_mail=request.getParameter("cont_mail");
				String cont_phone=request.getParameter("cont_phone");
				String cont_nombre=request.getParameter("cont_nombre");
				String cont_phonec=request.getParameter("cont_telefonoc");
				
				
				 String datepickerfechafactura= request.getParameter("datepickerfechafactura");
		            String datepickerfechafactura_[]=datepickerfechafactura.split("-");
		            datepickerfechafactura ="'"+datepickerfechafactura_[2]+"-"+datepickerfechafactura_[1]+"-"+datepickerfechafactura_[0]+"'";
		           
		            request.setAttribute("empresa_emisor", request.getParameter("empresa_emisor"));
		    		request.setAttribute("afecta",request.getParameter("afecta"));
		    		
				
				
				//--------------------- FIN -----------------------//
				
				//INSERTAMOS TODOS LOS DATOS EN NOF LA CABECERA DE LA FACTURA
				String SQL_up = " UPDATE `802` SET "
						+ " 	direccion_id ='"+DIRE_ID+"', "
						+ " 	802_ciudad ='"+gr_ciudad.toUpperCase()+"', "
						+ " 	contacto_id ='"+cont_id+"', "
						+ " 	802_obs ='"+OBS+"', "
						+ " 	estados_vig_novig_id ='"+estados_vig_novig_id+"', "
						+ " 	802_fecmod =now(), "
						+ " 	802_modificador ='"+id_iusuario+"', "
						+ " 	802_neto='"+neto+"', "
						+ "		802_empresa_emisora='"+empresa_id+"',"
						+ "		802_descuento='"+descuento+"',"
						+ "		802_fecvencimiento='"+fac_servim_fecvencimiento+"',"
						+ "		802_tipodte='"+tipodte+"',"
						+ "		802_glosa_desc='"+glosa_desc.toUpperCase()+"', "
						
						+ "		802_subtotal='"+SUBTOTAL+"', "
						+ "		802_iva='"+IVA+"', "
						+ "		802_total='"+TOTAL+"', "
						+ "		802_responsable_name='"+resp+"', "
						+ "		802_empresa_emisora_nombre='"+empresa_emisora_nombre+"', "
					
						+ "		cont_nombre='"+cont_nombre.toUpperCase()+"', "
						+ "		cont_email='"+cont_mail.toUpperCase()+"', "
						+ "		cont_telefono='"+cont_phone+"', "
						+ "		cont_telefonoc='"+cont_phonec.toUpperCase()+"', "
						+ "		802_fecha="+datepickerfechafactura
					
						+ " WHERE 802_id ="+GRID;
			    System.out.println(SQL_up);
				state.executeUpdate(SQL_up);
	    		  
	    		//----------------- DETALLE -----------------------//
				String sql_delete="delete from detalle_802 where 802_id="+GRID;
				String sql_update_del=" update `822` set en_factura=NULL where en_factura="+GRID;
				String sql_update_del2=" update `821` set en_factura=NULL where en_factura="+GRID;
				
				System.out.println(sql_delete);
				System.out.println(sql_update_del);
				System.out.println(sql_update_del2);
				
				state.addBatch(sql_delete);
      			state.addBatch(sql_update_del);
      			state.addBatch(sql_update_del2);
      			state.executeBatch();
      			
				 String[] seleccionado_detguias = request.getParameterValues("detguias[]");
				    if(seleccionado_detguias!=null)
				    	
		    		for (String id_gi: seleccionado_detguias) {
		    			if(id_gi!=null && !id_gi.equals("-1")){
		    				
		    				String g[]=id_gi.split("/");
		    				String fec_[] =g[2].split("-");
		    				String fec=fec_[2]+"-"+fec_[1]+"-"+fec_[0]; 
		    				
		    				//eliminamos el detalle 
		    				
		    				
		    				
		    				String SQL_FACDET = "INSERT INTO detalle_802 (802_id, d802_cod, d802_TOTAL, d802_FECHA, estados_vig_novig_id,d802_folio) "
			  	    				+ "VALUES ("+GRID+",'"+g[0]+"',"+g[1]+",'"+fec+"',1,'"+g[3]+"')";
			      			System.out.println(SQL_FACDET);
			      			state.executeUpdate(SQL_FACDET);
			      			
			      			String SQL_DESPACHO = "";
			      			if(g[0].indexOf("S")!=-1){
			      				 SQL_DESPACHO = "UPDATE `822` SET en_factura="+GRID+" WHERE 822_ID ="+g[0].replace("S", "");
			      			}
			      			else{
			      				SQL_DESPACHO = "UPDATE `821` SET en_factura="+GRID+" WHERE 821_ID ="+g[0].replace("A", "");	
			      			}
			      			
			      			System.out.println(SQL_DESPACHO);
			      			state.executeUpdate(SQL_DESPACHO);
			      			
			      			
			      		
		    				
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
		    				+ " (now(), 'USUARIO MODIFICA REGISTRO "+GRID+" EN TABLA 802', '"+id_iusuario+"') ";
		    		state.executeUpdate(log_sql);
				
			    
			 	
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
		Statement state_fec = null;
		Statement statedel_det = null;
		Statement state_traedet = null;
		Statement state_insdet = null;
		Statement state_verifica = null;
		Statement state_des = null;
		Statement state_guia = null;
		Statement state_guia_up = null;
		Statement state_traetot = null;
		
		
		ResultSet ESTADOS_RS = null;
		ResultSet EMPRESAS_RS = null;
		ResultSet CLIENTE_RS = null;
		ResultSet CONTACTOS_RS = null;
		ResultSet DIRECCION_RS = null;
		
	    ResultSet Alertas_RS = null;
		ResultSet RS_FAC = null;
		ResultSet TRAEDET_RS = null;
		ResultSet verifica_RS = null;
		ResultSet TRAETOT_RS = null;
		
		 
		String GRID="";
		try {
			
			//import java.io.IOException;
			
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		statecor = (Statement) ((java.sql.Connection) conexion).createStatement();
    		state_fec = (Statement) ((java.sql.Connection) conexion).createStatement();
    		statedel_det = (Statement) ((java.sql.Connection) conexion).createStatement();
    		state_traedet = (Statement) ((java.sql.Connection) conexion).createStatement();
    		state_insdet = (Statement) ((java.sql.Connection) conexion).createStatement();
    		state_verifica = (Statement) ((java.sql.Connection) conexion).createStatement();
    		state_des = (Statement) ((java.sql.Connection) conexion).createStatement();
    		state_guia = (Statement) ((java.sql.Connection) conexion).createStatement();
    		state_guia_up = (Statement) ((java.sql.Connection) conexion).createStatement();
    		state_traetot = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
    		
    		GRID=request.getParameter("guiaresumen_id");
    		request.setAttribute("guiaresumen_id", GRID);
    		
    		
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
		    String SQL_CLIENTE = "SELECT *, ifnull(g.802_folio, 'ND') as gr_folio2,"
		    		+ "					DATE_FORMAT(g.802_fecha, '%d-%m-%Y') as gr_fecha,"
		    		+ "		IF(`g`.`tipo_dteref` is null,'NINGUNA',`g`.`tipo_dteref`) as tipo_dteref, "
					+ "		IF(`g`.`folioref` is null ,'',`g`.`folioref`) as  folioref, "
					+ "		IF(`g`.`fec_ref` is null,'',DATE_FORMAT(`g`.`fec_ref`,'%d-%m-%Y')) as fec_ref1, "

    				
		    		+ "		CONCAT_WS(' ',`usuarios`.`Usuarios_nombre`,`usuarios`.`Usuarios_ape_p`,`usuarios`.`Usuarios_ape_m`) AS Usuarios_nombre_com "
    		  		+ " FROM `802` g"
		    		+ " INNER JOIN empresas e ON e.empresas_id=g.cliente_id"
		    		+ " INNER JOIN `usuarios` ON `usuarios`.`Usuarios_id` = e.`responsable_id` "
			    	+ " WHERE g.802_id = "+GRID;
		    System.out.println(SQL_CLIENTE);
		    CLIENTE_RS =  state.executeQuery(SQL_CLIENTE);
		    String CL_ID="";
		    Float N=0.0f, I=0.0f;
		    if(CLIENTE_RS.next()){
		    	CL_ID = CLIENTE_RS.getString("cliente_id");
		    	request.setAttribute("guiaresumen_id", GRID);
		    	request.setAttribute("estados_vig_novig_id", CLIENTE_RS.getString("estados_vig_novig_id"));
		    	request.setAttribute("clientes_id", CLIENTE_RS.getString("cliente_id"));
		    	request.setAttribute("id_dte", CLIENTE_RS.getString("id_dte"));
			    request.setAttribute("gr_folio", CLIENTE_RS.getString("gr_folio2"));
		    	request.setAttribute("empresas_id", CLIENTE_RS.getString("802_empresa_emisora"));
			    request.setAttribute("empresas_nombrenof", CLIENTE_RS.getString("empresas_nombrenof"));
			    request.setAttribute("empresas_rut", CLIENTE_RS.getString("empresas_rut"));
			    request.setAttribute("empresas_razonsocial", CLIENTE_RS.getString("empresas_razonsocial"));
			    
			    request.setAttribute("gr_obs", CLIENTE_RS.getString("802_obs"));
			    request.setAttribute("direcciones_id", CLIENTE_RS.getString("direccion_id"));
			    request.setAttribute("contactos_id", CLIENTE_RS.getString("contacto_id"));
			    request.setAttribute("gr_responsable", CLIENTE_RS.getString("Usuarios_nombre_com"));
			    
			    request.setAttribute("neto", CLIENTE_RS.getString("802_neto"));
			    request.setAttribute("iva", CLIENTE_RS.getString("802_iva"));
			    request.setAttribute("total", CLIENTE_RS.getString("802_total"));
			    request.setAttribute("subtotal", CLIENTE_RS.getString("802_subtotal"));
		    	
			    String fec_ven_ar[]=CLIENTE_RS.getString("802_fecvencimiento").substring(0, 10).split("-");
				String fec_ven=fec_ven_ar[2]+"-"+fec_ven_ar[1]+"-"+fec_ven_ar[0];
				request.setAttribute("fac_servim_fecvencimiento",fec_ven);
			    request.setAttribute("DESC",CLIENTE_RS.getString("802_descuento"));
			    request.setAttribute("fac_servim_tipodte",CLIENTE_RS.getString("802_tipodte"));
			    
			    request.setAttribute("gr_glosa_desc",CLIENTE_RS.getString("802_glosa_desc"));
			    request.setAttribute("empresas_giro", CLIENTE_RS.getString("empresas_giro"));
			    request.setAttribute("fecha", CLIENTE_RS.getString("gr_fecha"));
			    
			    request.setAttribute("fec_ref", CLIENTE_RS.getString("fec_ref1"));
		    	request.setAttribute("folioref", CLIENTE_RS.getString("folioref"));
		    	request.setAttribute("tipo_dteref", CLIENTE_RS.getString("tipo_dteref"));
		    	
			    
		    }
		  
		    //----------------------- FIN ------------------------//
		    
    		//--------------------- CONTACTO ----------------------//
		   
		    String SQL_CONTACTO = "SELECT cont_id,CLPR_ID,PERS_ID,CONT_TELEFONO,CONT_EMAIL,CONT_TELEFONOC, CONCAT_WS(' ',CONT_NOMBRE,CONT_APEP,CONT_APEM) AS CONT_NOMBRE  "
				    		+ "FROM contacto WHERE CLPR_ID = "+CLIENTE_RS.getString("cliente_id")+" ORDER BY cont_nombre";
		    CONTACTOS_RS =  state.executeQuery(SQL_CONTACTO);
		    ArrayList<String> contactos = new ArrayList<String>();
		    while(CONTACTOS_RS.next()){ contactos.add(CONTACTOS_RS.getString("cont_id")+"/"+
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
		    		+ "		estados_vig_novig_id = 1 AND direccion.CLPR_ID = "+CL_ID
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
		    
		   
		    
		    if(request.getParameter("agregar") != null){
		    	//String SQL_BORRA = "DELETE FROM guia_rt";
	  			//state.executeUpdate(SQL_BORRA);
			    String[] seleccionado = request.getParameterValues("permisos[]");
			    if(seleccionado!=null){
			    	
	    		
		        request.setAttribute("ar_detguias", seleccionado);
		     
			    
			    
			    }
		    }else{
		    	String SQL_Alertas = "SELECT d.802_id, d.d802_ID, d.d802_TOTAL,d.d802_cod, DATE_FORMAT(d.d802_FECHA, '%d-%m-%Y') AS GD_FECHA2, d.d802_FECHA,"
						+ " d.estados_vig_novig_id, d.d802_folio "
						+ " FROM detalle_802 d"
						+ " WHERE d.802_id = "+GRID+" ";
				System.out.println(SQL_Alertas);
			    Alertas_RS =  state.executeQuery(SQL_Alertas);
			    ArrayList<String> alertas = new ArrayList<String>();
			    while(Alertas_RS.next()){
			    	alertas.add(Alertas_RS.getString("d802_cod")+"/"+Alertas_RS.getString("d802_TOTAL")+"/"+Alertas_RS.getString("GD_FECHA2")
			    			+"/"+Alertas_RS.getString("d802_FECHA")+"/"+Alertas_RS.getString("d802_ID")+"/"+Alertas_RS.getString("estados_vig_novig_id")+"/"+Alertas_RS.getString("d802_folio"));
			    }
			    Alertas_RS.close();
			    state.close();
		            
		        String[] ar_alertas = new String[alertas.size()];
		        for(int x=0; x < alertas.size(); x++){
		        	ar_alertas[x]=alertas.get(x);
		        }
		        request.setAttribute("ar_detguias", ar_alertas);
		    }
		    
			
	      	//-------------------------GUIAS-------------------------//
		    
          
           
            
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
		RequestDispatcher rd = request.getRequestDispatcher("mres_mod.jsp"+msg);
        rd.forward(request, response);
		}
	}

}
