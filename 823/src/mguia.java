
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Constantes.Constantes;

/**
 * Servlet implementation class mguia
 */
@WebServlet("/mguia")
public class mguia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mguia() {
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

		String Usuarios_nombre="",id_usuario="";
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName());
		    	if(cookie.getName().equals("Usuarios_id") && cookie.getValue()!=null) id_usuario=cookie.getValue();
		    	if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}		
		request.setAttribute("usuname", Usuarios_nombre);	
		
		//grabar
		boolean guiaexiste=false;
		if(request.getParameter("grabar") != null){
			Statement stategrabar = null;	
			try {
				Statement state = null;
				Statement state_fac = null;
				Statement state_facdet = null;
				Statement state_ins = null;
				Statement state_des = null;
				
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
				stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
				state_fac = (Statement) ((java.sql.Connection) conexion).createStatement();
				state_facdet = (Statement) ((java.sql.Connection) conexion).createStatement();
				state_ins = (Statement) ((java.sql.Connection) conexion).createStatement();
				state_des = (Statement) ((java.sql.Connection) conexion).createStatement();
				
			    //DATOS DE LA TABLA FACTURA
				String GID=request.getParameter("guia_des_tras_normal_id");
				String cond = request.getParameter("condiciones");
				String clientes_id = request.getParameter("clientes_id");
				String empresas_id = request.getParameter("empresas_id");
				String cont_id = request.getParameter("cont_id");
				String ref = request.getParameter("ref");
				String obs = request.getParameter("obs");
				String f1 = request.getParameter("datepicker");
				String fec[]=f1.split("-");
				
				
				String SUBTOTAL=request.getParameter("subtotal");
		        String NETO=request.getParameter("neto");
	            String IVA=request.getParameter("iva");
	            String TOTAL=request.getParameter("total");
	            String desc=request.getParameter("desc");
	            String glosa_desc=request.getParameter("glosa_desc");
	            String resp=request.getParameter("resp");
	            
	            
	            
	            String gv_ciudad= request.getParameter("gv_ciudad");
				String empresa_emisora_nombre= request.getParameter("empresa_emisora_nombre");
				String cont_nombre= request.getParameter("cont_nombre");
				String cont_telefonoc=request.getParameter("cont_telefonoc");
				String cont_email=request.getParameter("cont_email");
				String cont_phone=request.getParameter("cont_phone");
				
				String estados_vig_novig_id=request.getParameter("estados_vig_novig_id");
				
				
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
	        
				
				//INSERTAMOS TODOS LOS DATOS EN NOF EN EL DETALLE DE LA FACTURA
				String SQL_FAC = "UPDATE `823`"
						+ " SET "
						+ " estados_vig_novig_id="+estados_vig_novig_id+", "
			    		
							+ "823_condiciones='"+cond+"',"
						+ " 823_empresa_emisora="+empresas_id+","
						+ " cont_id="+cont_id+","
						+ " 823_obs='"+obs+"',"
						+ " 823_fecmod=now(),"
						+ " 823_modificador="+id_usuario+","
						+ " 823_fecha='"+fec[2]+"-"+fec[1]+"-"+fec[0]+"'"
						+ "	,823_SUBTOTAL='"+SUBTOTAL+"'"
			    		+ "	,823_TOTAL='"+TOTAL+"' "
			    		+ "	,823_descuento='"+desc+"' "
			    		+ "	,823_glosa_descuento='"+glosa_desc.toUpperCase()+"'"
			    		+ "	,823_NETO='"+NETO+"' "
			    		+ "	,823_IVA='"+IVA+"' "
			    		
			    		+ "	,823_ciudad='"+gv_ciudad+"'"
			    		+ "	,823_empresa_emisora_nombre='"+empresa_emisora_nombre+"'"
			    		+ "	,cont_nombre='"+cont_nombre.toUpperCase()+"'"
			    		+ "	,cont_telefonoc='"+cont_telefonoc+"'"
			    		+ "	,cont_email='"+cont_email+"'"
			    		+ "	,cont_telefono='"+cont_phone+"'"
			    		+ "	,`823`.`tipo_dteref`='"+tipo_dteref+"' "
						+ "	,`823`.`folioref`='"+folioref+"' "
						+ "	,`823`.`fec_ref`="+fec_ref+"  "
			    		+ "	,`823`.`823_afecta`='"+g_afecta+"'  "
			    		+ "	,`823`.`823_numticket`='"+ref.toUpperCase()+"'  "
			    		
			    		
						+ " WHERE 823_id = "+GID;
				//System.out.println(SQL_FAC);
				state.executeUpdate(SQL_FAC);
			    
				String sql_delete="delete from detalle_823 where 823_id="+GID;
				state.addBatch(sql_delete);
      			state.executeBatch();
      			
      			String[] seleccionado_detguias = request.getParameterValues("detguias[]");
	    		if(seleccionado_detguias!=null)
		    				for(int i =0; i<seleccionado_detguias.length; i++){
			    				String[] Guias = seleccionado_detguias[i].split(";;");
			    				
			    				String fec_ar=Guias[8];
			    				
			    				String SQL_GUIADET = "INSERT INTO detalle_823"
			    						+ "(823_id, alt_id, UBI_DESCRIPCION,ALT_SERIE ,PROD_PN_TLI_CODFAB,TRAS_FECHA,PROD_DESC_CORTO,clientes_id, estados_vig_novig_id,tras_id,ubi_id,tick_id,d823_valor) "
				  	    				+ "VALUES (" 
				  	    						+GID+""
				  	    						+ ",'"+Guias[0]+"'"
				  	    						
				  	    						+ ",'"+Guias[5]+"'"
				  	    						+ ",'"+Guias[4]+"'"
				  	    						+ ",'"+Guias[3]+"'"
				  	    						+ ",'"+fec_ar+"'"
				  	    						+ ",'"+Guias[2]+"'"
				  	    						
				  	    						+ ",'"+clientes_id+"'"
				  	    						+ ",1"
				  	    						+ ",'"+Guias[9]+"','"+Guias[10]+"','"+Guias[11]+"','"+Guias[12]+"' "
				  	    						+ ")";
				      			System.out.println(SQL_GUIADET);
				      			state_ins.executeUpdate(SQL_GUIADET);
		    				}
	    		
	    		
	    		 //insertamos logs
	    		String log_sql=""
	    				+ " INSERT INTO `log` ("
	    				+ "	`log`.`log_fec`, "
	    				+ "	`log`.`log_nombre`, " 
	    				+ "	`log`.`Usuarios_id` "
	    				+ " ) "
	    				+ " VALUES "
	    				+ " (now(), 'USUARIO MODIFICA REGISTRO "+GID+" DE LA TABLA 823', '"+id_usuario+"') ";
	    		state.executeUpdate(log_sql);
	        	
	        	RequestDispatcher rd_up = request.getRequestDispatcher("menuguia?Exito=OK");
	        	rd_up.forward(request, response);
	        	//return;
	        	
			}catch(Exception ex){
				ex.printStackTrace();
			    out.println("Error "+ex);
			}
			
		}else{
		
			try {
				Statement state = null;
				ResultSet CLIENTE_RS = null;
				ResultSet EMPRESAS_RS = null;
				ResultSet CONTACTOS_RS = null;
				ResultSet GUIAS_RS = null;
				
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		String condiciones=request.getParameter("condiciones");
	    		String ref=request.getParameter("ref");
	    		String obs=request.getParameter("obs");
	    		
	    		String empresas_id = request.getParameter("empresas_id");
				
	    		
	    		request.setAttribute("condiciones",condiciones);
	    		request.setAttribute("ref",ref);
	    		request.setAttribute("obs",obs);
	    		
	    		String GID = request.getParameter("guia_des_tras_normal_id");
	    		request.setAttribute("guia_des_tras_normal_id", GID);
	    		
	    		String SQL_Cliente = "SELECT "
	    				+ " IF(id_dte is null, 'ND', g.id_dte) as id_dte, DATE_FORMAT(823_fecha, '%d-%m-%Y') as fecha, "
	    				+ " g.823_empresa_emisora as empresa_id, IF(id_dte is null, 'NO ENVIADA', 'ENVIADA') as dte,"
	    				
	    				+ " e2.empresas_razonsocial as nom1, e2.empresas_rut as rut1, e2.empresas_id as id1,  "
	    				+ " IF(d.dire_direccion ='','SD',d.DIRE_DIRECCION) as dir1,"
	    				+ " r.REGI_NOMBRE as reg1, c.COMU_NOMBRE as com1, d.DIRE_CIUDAD as cui1, g.CONT_ID, o.CONT_NOMBRE, o.CONT_TELEFONO, o.CONT_EMAIL, o.CONT_TELEFONOC,"
	    				+ " g.CONT_ID,"
	    				
						+ " e.empresas_razonsocial as nom2, e.empresas_rut as rut2, e.empresas_id as id2,e.empresas_giro,  "
						+ " IF(d1.dire_direccion ='','SD',d1.DIRE_DIRECCION) as dir2,"
						+ " r1.REGI_NOMBRE as reg2, c1.COMU_NOMBRE as com2, d1.DIRE_CIUDAD as cui2, "
						
	    				+ " g.823_condiciones,g.dire_id2,g.dire_id, "
	    				+ " g.823_obs, g.clientes_id, g.823_numticket,  "
	    				+ " 823_subtotal,823_neto,823_iva,823_total,823_descuento,823_glosa_descuento,  "
			    		
	    				+ " 823_obs2, g.estados_vig_novig_id, "
	    				+ "		CONCAT_WS(' ',`usuarios`.`Usuarios_nombre`,`usuarios`.`Usuarios_ape_p`,`usuarios`.`Usuarios_ape_m`) AS Usuarios_nombre,"
	    				+ "	g.estados_vig_novig_id ,	"
	    				+ "		IF(`g`.`tipo_dteref` is null,'NINGUNA',`g`.`tipo_dteref`) as tipo_dteref, "
						+ "		IF(`g`.`folioref` is null ,'',`g`.`folioref`) as  folioref, "
						+ "		IF(`g`.`fec_ref` is null,'',DATE_FORMAT(`g`.`fec_ref`,'%d-%m-%Y')) as fec_ref , "
						+ "		g.823_afecta as g_afecta "
						
	    				+ " FROM `823` g "
	    				+ " INNER JOIN empresas e ON e.empresas_id = g.clientes_id "
	    				+ " INNER JOIN direccion d ON d.dire_id = g.dire_id "
	    				+ " INNER JOIN empresas e2 ON e2.empresas_id = d.CLPR_ID "
	    				+ " INNER JOIN comuna c ON d.COMU_ID = c.COMU_ID"
	    				+ " INNER JOIN region r ON c.regi_id = r.regi_id"
	    				+ " INNER JOIN contacto o ON g.cont_id = o.cont_id"
	    				
						+ " INNER JOIN direccion d1 ON d1.dire_id = g.dire_id2 "
						+ " INNER JOIN empresas e3 ON e3.empresas_id = d1.CLPR_ID "
						+ " INNER JOIN comuna c1 ON d1.COMU_ID = c1.COMU_ID"
						+ " INNER JOIN region r1 ON c1.regi_id = r1.regi_id"
						+ "	INNER JOIN `usuarios` ON `usuarios`.`Usuarios_id` = e.`responsable_id` "
	    				
	    				//+ " INNER JOIN personal p ON o.pers_id = p.PERS_ID"
	    				+ " WHERE g.823_id ='"+GID+"'"
	    				+ " GROUP BY g.823_id"
	    				+ " ORDER BY e.empresas_nombrenof, d.dire_direccion"; 
	    		System.out.println(SQL_Cliente);
	 		    CLIENTE_RS =  state.executeQuery(SQL_Cliente);
	 		    String CID = "";
	 		    if(CLIENTE_RS.next()){
	 		    	CID = CLIENTE_RS.getString("id2");
	 		    	request.setAttribute("estados_vig_novig_id", CLIENTE_RS.getString("estados_vig_novig_id"));
	 		    	request.setAttribute("id_dte", CLIENTE_RS.getString("id_dte"));
	 		    	request.setAttribute("dte", CLIENTE_RS.getString("dte"));
	 		    	request.setAttribute("fecha", CLIENTE_RS.getString("fecha"));
	 		    	request.setAttribute("empresas_id", CLIENTE_RS.getString("empresa_id"));
	 		    	request.setAttribute("contactos_id", CLIENTE_RS.getString("CONT_ID"));
	 		    	request.setAttribute("nom1", CLIENTE_RS.getString("nom1"));
			    	request.setAttribute("rut1", CLIENTE_RS.getString("rut1"));
			    	request.setAttribute("id1", CLIENTE_RS.getString("id1"));
			    	request.setAttribute("dir1", CLIENTE_RS.getString("dir1"));
			    	request.setAttribute("reg1", CLIENTE_RS.getString("reg1"));
			    	request.setAttribute("com1", CLIENTE_RS.getString("com1"));
			    	request.setAttribute("cui1", CLIENTE_RS.getString("cui1"));
			    	request.setAttribute("nom2", CLIENTE_RS.getString("nom2"));
			    	request.setAttribute("rut2", CLIENTE_RS.getString("rut2"));
			    	request.setAttribute("id2", CLIENTE_RS.getString("id2"));
			    	request.setAttribute("dir2", CLIENTE_RS.getString("dir2"));
			    	request.setAttribute("reg2", CLIENTE_RS.getString("reg2"));
			    	request.setAttribute("com2", CLIENTE_RS.getString("com2"));
			    	request.setAttribute("cui2", CLIENTE_RS.getString("cui2"));
			    	request.setAttribute("CONT_ID", CLIENTE_RS.getString("CONT_ID"));
			    	request.setAttribute("CONT_NOMBRE", CLIENTE_RS.getString("CONT_NOMBRE"));
			    	request.setAttribute("CONT_TELEFONO", CLIENTE_RS.getString("CONT_TELEFONO"));
			    	request.setAttribute("CONT_TELEFONOC", CLIENTE_RS.getString("CONT_TELEFONOC"));
			    	request.setAttribute("PERS_NOMBRE", " ");
			    	request.setAttribute("CONT_EMAIL", CLIENTE_RS.getString("CONT_EMAIL"));
			    	request.setAttribute("estados_vig_novig_id", CLIENTE_RS.getString("estados_vig_novig_id"));
	 		    	request.setAttribute("condicion", CLIENTE_RS.getString("823_condiciones"));
			    	
			    	request.setAttribute("obs", CLIENTE_RS.getString("823_obs"));
			    	request.setAttribute("obs2", CLIENTE_RS.getString("823_obs2"));
			    	request.setAttribute("RESPONSABLE", CLIENTE_RS.getString("Usuarios_nombre"));
			    	request.setAttribute("dire_id2", CLIENTE_RS.getString("dire_id2"));
			    	request.setAttribute("dire_id", CLIENTE_RS.getString("dire_id"));
			    	request.setAttribute("empresas_giro", CLIENTE_RS.getString("empresas_giro"));
			    	request.setAttribute("clientes_id",CLIENTE_RS.getString("clientes_id"));
			    	request.setAttribute("numtick",CLIENTE_RS.getString("823_numticket"));
			    	
			    	request.setAttribute("g_afecta",CLIENTE_RS.getString("g_afecta"));
			    	
			    	String SUBTOTAL=CLIENTE_RS.getString("823_subtotal");
			    	String desc=CLIENTE_RS.getString("823_descuento");
			    	String NETO=CLIENTE_RS.getString("823_neto");
			    	String IVA=CLIENTE_RS.getString("823_iva");
			    	String TOTAL=CLIENTE_RS.getString("823_total");
			    	String glosa_desc=CLIENTE_RS.getString("823_glosa_descuento");
			    	
	            	 request.setAttribute("subtotal", SUBTOTAL);
	    	 		 request.setAttribute("descuento", desc);
	    	 		request.setAttribute("neto", NETO);
	    	 		request.setAttribute("iva", IVA);
	    	 		request.setAttribute("total", TOTAL);
	    	 		request.setAttribute("glosadescuento", glosa_desc);
	    	 		
	    	 		
	    	 		request.setAttribute("fec_ref", CLIENTE_RS.getString("fec_ref"));
	    	    	request.setAttribute("folioref", CLIENTE_RS.getString("folioref"));
	    	    	String tipo_dteref=CLIENTE_RS.getString("tipo_dteref").equals("") ? "NINGUNA": CLIENTE_RS.getString("tipo_dteref");
	    	    	request.setAttribute("tipo_dteref", tipo_dteref);
			    	
	 		    }

	 		    //--------------------- DETALLE TRASLADOS ----------------------//
	 		   if(request.getParameter("agregar") != null){
				    String[] seleccionado = request.getParameterValues("permisos[]");
				    if(seleccionado!=null){
				    	request.setAttribute("ar_guias", seleccionado);
				    }
			    }else{
			    	String SQL_GUIAS = "SELECT "
				    		+ " d.ALT_ID, d.PROD_PN_TLI_CODFAB, d.d823_valor,d.PROD_DESC_CORTO, d.PROD_COD_BAR_FAB, d.ALT_SERIE, d.UBI_DESCRIPCION,d.TRAS_FECHA,d.tras_id, "
				    		+ " DATE_FORMAT(d.TRAS_FECHA, '%d-%m-%Y') as fecha, d.UBI_ID, d.TICK_ID  "
				    		+ " FROM detalle_823 d "
				    		+ " WHERE d.823_id = "+GID;
				    System.out.println(SQL_GUIAS);
				    GUIAS_RS =  state.executeQuery(SQL_GUIAS);
				    ArrayList<String> guias = new ArrayList<String>();
				    while(GUIAS_RS.next()){ guias.add(
				    				GUIAS_RS.getString("ALT_ID")+"/"+
				    				GUIAS_RS.getString("tras_id")+"/"+
				    				GUIAS_RS.getString("PROD_DESC_CORTO")+"/"+
				    				GUIAS_RS.getString("PROD_PN_TLI_CODFAB")+"/"+
				    				GUIAS_RS.getString("ALT_SERIE")+"/"+
				    				GUIAS_RS.getString("UBI_DESCRIPCION")+"/"+
				    				GUIAS_RS.getString("fecha")+"/"+
				    				GUIAS_RS.getString("PROD_COD_BAR_FAB")+"/"+
				    				GUIAS_RS.getString("TRAS_FECHA")+"/"+
				    				GUIAS_RS.getString("tras_id")+"/"+
				    				GUIAS_RS.getString("UBI_ID")+"/"+
				    				GUIAS_RS.getString("TICK_ID")+"/"+
				    				GUIAS_RS.getString("d823_valor")); }
				    GUIAS_RS.close();
				    String[] ar_guias = new String[guias.size()];
				    for(int x=0; x < guias.size(); x++){ ar_guias[x]=guias.get(x);}
				    request.setAttribute("ar_guias", ar_guias);
			    }
			    
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

			    //--------------------- CONTACTO ----------------------//
			    String SQL_CONTACTO = "SELECT *,CONCAT_WS(' ',cont_nombre,CONT_APEP,CONT_APEM) as con FROM contacto WHERE CLPR_ID = "+CID+" ORDER BY cont_nombre ";
			    //System.out.println(SQL_CONTACTO);
			    CONTACTOS_RS =  state.executeQuery(SQL_CONTACTO);
			    ArrayList<String> contactos = new ArrayList<String>();
			    while(CONTACTOS_RS.next()){ contactos.add(	CONTACTOS_RS.getString("cont_id")+"/"+
			    											CONTACTOS_RS.getString("con").replace("/", " ")+"/"+
			    											CONTACTOS_RS.getString("CLPR_ID")+"/"+
			    											//CONTACTOS_RS.getString("PERS_ID")+"/"+
			    											CONTACTOS_RS.getString("CONT_TELEFONO").replace("/", " ")+"/"+
			    											CONTACTOS_RS.getString("CONT_EMAIL")); }
			    CONTACTOS_RS.close();
			    String[] ar_contactos = new String[contactos.size()];
			    for(int x=0; x < contactos.size(); x++){ ar_contactos[x]=contactos.get(x);}
			    request.setAttribute("ar_contactos", ar_contactos);
			    
			    
				//--------------------- ESTADOS ----------------------//
			    String SQL_ESTADOS = "SELECT * FROM estados_vig_novig";
			    ResultSet ESTADOS_RS = state.executeQuery(SQL_ESTADOS);
			    ArrayList<String> estados = new ArrayList<String>();
			    while(ESTADOS_RS.next()){ estados.add(ESTADOS_RS.getString("estados_vig_novig_id")+"/"+ESTADOS_RS.getString("estados_vig_novig_nombre")); }
			    ESTADOS_RS.close();
			    String[] ar_estados = new String[estados.size()];
			    for(int x=0; x < estados.size(); x++){ ar_estados[x]=estados.get(x); }
			    request.setAttribute("ar_estados", ar_estados);
			    //----------------------- FIN ------------------------//
			    request.setAttribute("guia_des_tras_normal_id", request.getAttribute("guia_des_tras_normal_id"));
				    
			    if(empresas_id!=null)request.setAttribute("empresas_id",empresas_id);
	    		
			    String sql_percontable="SELECT "
						+" MIN(`adm_periodos_contables`.`admpc_year`) AS minyear,"
						+" MIN(`adm_periodos_contables`.`admpc_mes`) AS minmes,"
						+" MAX(`adm_periodos_contables`.`admpc_year`) AS maxyear,"
						+" MAX(`adm_periodos_contables`.`admpc_mes`) AS maxmes"
					+" FROM"
						+" `adm_periodos_contables`"
					+" WHERE"
						+" `adm_periodos_contables`.`estados_vig_novig_id` = 1";

					ResultSet RS_percontable = state.executeQuery(sql_percontable);
					
					
					RS_percontable.next();
					request.setAttribute("minyear", RS_percontable.getString("minyear"));
					request.setAttribute("minmes", RS_percontable.getString("minmes"));
					request.setAttribute("maxyear", RS_percontable.getString("maxyear"));
					request.setAttribute("maxmes", RS_percontable.getString("maxmes"));
					
					state.close();
					conexion.close();
			    
			}catch(Exception ex){
				 ex.printStackTrace();
			    out.println("ERROR: "+ex);
			   
			}
		String msg="";
		if(guiaexiste){
			msg="?Exito=NOK";
			String condiciones = request.getParameter("condiciones");
			String ref = request.getParameter("ref");
			String obs = request.getParameter("obs");
			request.setAttribute("condiciones",condiciones);
			request.setAttribute("ref",ref);
			request.setAttribute("obs",obs);
			//System.out.println("grupos_nombre "+grupos_nombre);
		}
		RequestDispatcher rd = request.getRequestDispatcher("mguia.jsp"+msg);
	    rd.forward(request, response);
		
		}
		
	}

}
