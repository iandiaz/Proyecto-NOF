

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
 * Servlet implementation class m2
 */
@WebServlet("/m2")
public class m2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public m2() {
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
	String id_iusuario="";
	String Usuarios_nombre="";
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
	request.setAttribute("usuname", Usuarios_nombre);	
	
	
	if(request.getParameter("grabar") != null){
		try {
			Statement state = null;
			
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		state = (Statement) ((java.sql.Connection) conexion).createStatement();

			//----------------- CABECERA -----------------------//
    		String ID_FAC=request.getParameter("gv_id");
			
			
			
    		String OBS1=request.getParameter("obs1");
			String OBS2=request.getParameter("obs2");
			
			///gv_fecha al reves 
			String fec[] =request.getParameter("gv_fecha").split("-");
			String GV_FECHA="'"+fec[2]+"-"+fec[1]+"-"+fec[0]+"'";
			
			
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
			
			
			

            String GUIA_SUBTOTAL=request.getParameter("subtotal");
	        String GUIA_NETO=request.getParameter("neto");
            String desc=request.getParameter("desc");
            String glosa_desc=request.getParameter("glosa_desc");
            String GUIA_IVA=request.getParameter("iva");
            String GUIA_TOTAL=request.getParameter("total");
			
            String estados_vig_novig_id=request.getParameter("estados_vig_novig_id");
			
            
            
    		String g_afecta=request.getParameter("g_afecta");
			if(g_afecta==null || g_afecta.equals(""))g_afecta="0";
            
			//INSERTAMOS TODOS LOS DATOS EN NOF LA CABECERA DE LA FACTURA
		    String SQL_GUIA = ""
		    		+ " UPDATE `821` set  "
		    		+ "	 `estados_vig_novig_id`="+estados_vig_novig_id+", "
		    		+ "  821_FECHA="+GV_FECHA+", "
		    		+ "	 `821`.`821_fecmod` = now(),"
		    		+ "	 `821`.`821_modificador` = '"+id_iusuario+"',"
		    			+ " 821_SUBTOTAL='"+GUIA_SUBTOTAL+"', "
			    		+ " 821_TOTAL='"+GUIA_TOTAL+"',"
			    		+ "	821_descuento='"+desc+"',"
			    		+ "	821_glosa_descuento='"+glosa_desc+"', "
			    		+ " 821_NETO='"+GUIA_NETO+"',"
			    		+ " 821_IVA='"+GUIA_IVA+"', "
			    		+ "	`821`.`tipo_dteref`='"+tipo_dteref+"', "
						+ "	`821`.`folioref`='"+folioref+"', "
						+ "	`821`.`fec_ref`="+fec_ref+",  "
						+ " 821_obs1='"+OBS1+"', "
			    		+ " 821_obs2='"+OBS2+"' , "
			    		+ "	`821`.`821_afecta`='"+g_afecta+"'  "
			    		
			    		
		    		+ "	WHERE"
		    		+ "		`821`.`821_id` = '"+ID_FAC+"' ";
		    	
		    System.out.println(SQL_GUIA);
		    state.executeUpdate(SQL_GUIA);
		    
		    
		  //insertamos logs
    		String log_sql=""
    				+ " INSERT INTO `log` ("
    				+ "	`log`.`log_fec`, "
    				+ "	`log`.`log_nombre`, " 
    				+ "	`log`.`Usuarios_id` "
    				+ " ) "
    				+ " VALUES "
    				+ " (now(), 'USUARIO MODIFICA REGISTRO "+ID_FAC+" DE LA TABLA 821', '"+id_iusuario+"') ";
    		state.executeUpdate(log_sql);
			state.close();
        	
        	RequestDispatcher rd_up = request.getRequestDispatcher("menufac?Exito=OK");
        	rd_up.forward(request, response);
        	return;
        	
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("ERROR "+ex);
		}
		
		return;
		
	}
	
	
	
		try {
			Statement state = null;
			ResultSet GUIAS_RS = null;
			ResultSet FAC_RS = null;
			
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
    		String GID = request.getParameter("gv_id");

    		
    		
    	
            
            String query="	select  "
            		+ "	f.821_id as GV_ID"
            		+ "	,f.821_folio_birt "
            		+ "	,DATE_FORMAT(f.821_fecha,'%d-%m-%Y') as GV_FECHA"
            		+ "	, emisor.empresas_nombrenof as EMISORA,"
            		
					+ "		cliente.empresas_razonsocial as empresas_razonsocialcliente, "
					+ "		cliente.empresas_rut as empresas_rutcliente,"
					+ "		cliente.empresas_id as empresas_idcliente,"
					+ "		cliente.empresas_nombrenof as empresas_nombrenofcliente,"
					+ "		cliente.empresas_giro ,"
					
					+ "		`f`.`821_obs`,"
					+ "		`f`.`821_obs1`,"
					+ "		`f`.`821_obs2`,"
					
					
					
					+ "		`f`.`id_dte`,"
					+ "		if(`f`.`id_dte` is null,0,1) as dte,"
					+ "		`f`.`821_condiciones` as fac_servim_condiciones, "
				
			
					+ "		IF(`f`.`tipo_dteref` is null,'NINGUNA',`f`.`tipo_dteref`) as tipo_dteref, "
					+ "		IF(`f`.`folioref` is null ,'',`f`.`folioref`) as  folioref, "
					+ "		IF(`f`.`fec_ref` is null,'',DATE_FORMAT(`f`.`fec_ref`,'%d-%m-%Y')) as fec_ref, "
			
					+ "		CONCAT_WS(' ',`contacto`.CONT_NOMBRE,`contacto`.CONT_APEP,`contacto`.CONT_APEM) AS CONT_NOMBRE, "
					+ "		`contacto`.`CONT_NOMBRE`, "
					+ "		`contacto`.`CONT_TELEFONO`,  "
					+ "		`contacto`.`CONT_TELEFONOC`,  "
					+ "		`contacto`.`CONT_EMAIL`,  "
					+ "		`direccion`.`DIRE_DIRECCION` , "
					+ "		`direccion`.`DIRE_CIUDAD`,  "
					+ "		`comuna`.`COMU_NOMBRE`,  "
					+ "		`region`.`REGI_NOMBRE`, "
					+ "		`f`.`821_subtotal` , "
					+ "		`f`.`821_total`, "
					+ "		`f`.`821_descuento` , "
					+ "		`f`.`821_glosa_descuento`, "
					+ "		CONCAT_WS(' ',`usuarios`.`Usuarios_nombre`,`usuarios`.`Usuarios_ape_p`,`usuarios`.`Usuarios_ape_m`) AS Usuarios_nombre_com, "
					+ " 	`f`.`821_neto` , "
					+ "		`f`.`821_iva`,"
					+ "		f.estados_vig_novig_id, f.821_fecha_emision,f.usu_inicial_emisor ,f.821_folio , f.821_responsable_name,f.821_transporte,"
					+ "		f.821_afecta as g_afecta "

            		+ "	from `821` f "
            		+ "	INNER JOIN `empresas` emisor ON `emisor`.`empresas_id` = `f`.`821_empresa_emisora` "
  		    		+ " INNER JOIN `empresas` cliente ON cliente.empresas_id = `f`.`clpr_id` "
  		    		+ " INNER JOIN `contacto` ON `contacto`.`CONT_ID` = `f`.`CONT_ID` "
  		    		+ " INNER JOIN `direccion` ON `direccion`.`DIRE_ID` = `f`.`dire_id` "
  		    		+ " INNER JOIN `comuna` ON `comuna`.`COMU_ID` = `direccion`.`COMU_ID` "
  		    		+ " INNER JOIN `region` ON `region`.`REGI_ID` = `comuna`.`REGI_ID` "
  		    		+ " INNER JOIN `usuarios` ON `usuarios`.`Usuarios_id` = cliente.`responsable_id` "
  			    	
            		+ "	where f.821_id="+GID;
            
			System.out.println(query);
            ResultSet CAB_RS = state.executeQuery(query);
            CAB_RS.next();
            request.setAttribute("GV_ID",GID);
	 		request.setAttribute("GV_FECHA",CAB_RS.getString("GV_FECHA"));
	 		request.setAttribute("EMISORA",CAB_RS.getString("EMISORA"));
	 		request.setAttribute("CLPR_RAZON_SOCIAL",CAB_RS.getString("empresas_razonsocialcliente"));
	 		request.setAttribute("CLPR_RUT",CAB_RS.getString("empresas_rutcliente"));
	 		
	 		request.setAttribute("DIRE_DIRECCION",CAB_RS.getString("DIRE_DIRECCION"));
	 		request.setAttribute("CONT_TELEFONO",CAB_RS.getString("CONT_TELEFONO"));
	 		request.setAttribute("REGI_NOMBRE",CAB_RS.getString("REGI_NOMBRE"));
	 		request.setAttribute("COMU_NOMBRE",CAB_RS.getString("COMU_NOMBRE"));
	 		request.setAttribute("GV_CIUDAD",CAB_RS.getString("DIRE_CIUDAD"));
	 		request.setAttribute("CONT_NOMBRE",CAB_RS.getString("CONT_NOMBRE"));
	 		
	 		
	 		request.setAttribute("GV_OBS",CAB_RS.getString("821_obs"));
	 		request.setAttribute("guia_obs1",CAB_RS.getString("821_obs1"));
	 		request.setAttribute("guia_obs2",CAB_RS.getString("821_obs2"));
	 		
	 		
	 		request.setAttribute("GV_ESTADO",CAB_RS.getString("estados_vig_novig_id"));
	 		request.setAttribute("GV_FECHA_EMISION",CAB_RS.getString("821_fecha_emision"));
	 		request.setAttribute("USU_INICIAL",CAB_RS.getString("usu_inicial_emisor"));
	 		
	 		if(CAB_RS.getInt("dte")==0){request.setAttribute("id_guia_estado","ENVIADA");}
	    	if(CAB_RS.getInt("dte")==1){request.setAttribute("id_guia_estado","PARA ENVIAR");}
	    	request.setAttribute("fec_ref", CAB_RS.getString("fec_ref"));
	    	request.setAttribute("folioref", CAB_RS.getString("folioref"));
	    	String tipo_dteref=CAB_RS.getString("tipo_dteref").equals("") ? "NINGUNA": CAB_RS.getString("tipo_dteref");
	    	request.setAttribute("tipo_dteref", tipo_dteref);
	    	
	    	String folio="";
	    	if(CAB_RS.getString("821_folio")==null) folio ="ND";
	    	else folio = CAB_RS.getString("821_folio");
            request.setAttribute("folio",folio);
            request.setAttribute("PERS_NOMBRE",CAB_RS.getString("821_responsable_name"));
            request.setAttribute("GV_TRANSPORTE",CAB_RS.getString("821_transporte"));
            request.setAttribute("empresas_giro",CAB_RS.getString("empresas_giro"));
            request.setAttribute("g_afecta",CAB_RS.getString("g_afecta"));
            
            
            request.setAttribute("subtotal", CAB_RS.getString("821_subtotal"));
    		 request.setAttribute("descuento", CAB_RS.getString("821_descuento"));
    		request.setAttribute("neto", CAB_RS.getString("821_neto"));
    		request.setAttribute("iva", CAB_RS.getString("821_iva"));
    		request.setAttribute("total", CAB_RS.getString("821_total"));
    		request.setAttribute("glosadescuento", CAB_RS.getString("821_glosa_descuento"));
    		request.setAttribute("nrobirt", CAB_RS.getString("821_folio_birt"));
    		
            String SQL_DET = "SELECT * FROM detalle_821 WHERE 821_id = "+GID+" ORDER BY d821_id";
			  System.out.println(SQL_DET);
		    ResultSet DET_RS = state.executeQuery(SQL_DET);
		    ArrayList<String> prod = new ArrayList<String>();
		  
		    while(DET_RS.next()){
		    	prod.add(
		    			DET_RS.getString("alt_id")+";;"+
		    			DET_RS.getString("prod_pn_tli_codfab")+";;"+
		    			DET_RS.getString("prod_desc_corto")+";;"+
		    			DET_RS.getString("alt_serie")+";;"+
		    DET_RS.getString("d821_valor")+";;"+
		    DET_RS.getString("ubi_id")+";;"+DET_RS.getString("tick_id"));
		    	}
		    
		    String[] ar_prod = new String[prod.size()];
		    for(int x=0; x < prod.size(); x++){ 
		    	System.out.println(x);
		    		ar_prod[x]=prod.get(x);	
		    	
		    	 
		    
		    }
		
 		   request.setAttribute("ar_productos", ar_prod);
 		   
 		   
 		    //--------------------- ESTADOS ----------------------//
		    
   		String SQL_ESTADOS = "SELECT * FROM estados_vig_novig";
   		System.out.println(SQL_ESTADOS);
		    ResultSet ESTADOS_RS = state.executeQuery(SQL_ESTADOS);
		    ArrayList<String> estados = new ArrayList<String>();
		    while(ESTADOS_RS.next()){ estados.add(ESTADOS_RS.getString("estados_vig_novig_id")+"/"+ESTADOS_RS.getString("estados_vig_novig_nombre")); }
		    ESTADOS_RS.close();
		    String[] ar_estados = new String[estados.size()];
		    for(int x=0; x < estados.size(); x++){ ar_estados[x]=estados.get(x); }
		    request.setAttribute("ar_estados", ar_estados);
		    //----------------------- FIN ------------------------//
           
 		  
           
 		  DET_RS.close();	
 		CAB_RS.close();
           
           
           conexion.close();
		}catch(Exception ex){
		    out.println("ERROR: "+ex);
		    ex.printStackTrace();
		}
	//fin grabar
		
	RequestDispatcher rd = request.getRequestDispatcher("m2.jsp");
    rd.forward(request, response);
	
}


}
