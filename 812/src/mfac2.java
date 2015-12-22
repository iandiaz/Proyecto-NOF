

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
 * Servlet implementation class mfac2
 */
@WebServlet("/mfac2")
public class mfac2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mfac2() {
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
		    	if(cookie.getName().equals("perfilusu_id") && cookie.getValue()!=null) perf_in_session=true;
		    	
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
				
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		state = (Statement) ((java.sql.Connection) conexion).createStatement();

				//----------------- CABECERA -----------------------//
	    		String ID_FAC=request.getParameter("gd_id");
				
				String CONT_ID=request.getParameter("cont_id");
				
				String fac_servim_condiciones=request.getParameter("fac_servim_condiciones");
				String empresa_id=request.getParameter("empresa_id");
				String tipodte=request.getParameter("tipodte");
				String fac_servim_fecvencimiento_ar[]=request.getParameter("fac_servim_fecvencimiento").toString().split("-");
				String fac_servim_fecvencimiento=fac_servim_fecvencimiento_ar[2]+"-"+fac_servim_fecvencimiento_ar[1]+"-"+fac_servim_fecvencimiento_ar[0];
				String fac_servim_emisor=request.getParameter("fac_servim_emisor");
				String fac_servim_obs=request.getParameter("fac_servim_obs");
				String fac_servim_ref=request.getParameter("fac_servim_ref");
				String dire_id=request.getParameter("dire_id");
				
				String total=request.getParameter("total");
				
				String descuento=request.getParameter("desc");
				String glosa_desc=request.getParameter("glosa_desc");
				
				String fac_servim_neto=request.getParameter("netoneto");
				String fac_servim_totalfinal=request.getParameter("totaltotal");
				
				String fac_comserv_valor_ivaret=request.getParameter("iva");
				String fac_comserv_porcentaje_ivaret=request.getParameter("ivap");
				String fac_comserv_valor_ivanoret=request.getParameter("valivanoret");
				
				
				
				
				String fac_servim_tcambio=request.getParameter("fac_servim_tcambio");
				
				
				//INSERTAMOS TODOS LOS DATOS EN NOF LA CABECERA DE LA FACTURA
			    String SQL_GUIA = ""
			    		+ " UPDATE factura_compra_serv set  "
			    		+ "	 `factura_compra_serv`.`fac_comserv_condiciones` = '"+fac_servim_condiciones.toUpperCase()+"',"
			    		+ "	 `factura_compra_serv`.`fac_comserv_emisor` = '"+fac_servim_emisor+"',"
			    		+ "	 `factura_compra_serv`.`fac_comserv_fecvencimiento` = '"+fac_servim_fecvencimiento+"',"
			    		+ "	 `factura_compra_serv`.`fac_comserv_tipodte` = '"+tipodte+"',"
			    		+ "	 `factura_compra_serv`.`empresas_id` = '"+empresa_id+"',"
			    		+ "	 `factura_compra_serv`.`fac_comserv_ref` = '"+fac_servim_ref.toUpperCase()+"',"
			    		+ "	 `factura_compra_serv`.`fac_comserv_obs` = '"+fac_servim_obs.toUpperCase()+"',"
			    		+ "	 `factura_compra_serv`.`dire_id` = '"+dire_id+"',"
			    		+ "	 `factura_compra_serv`.`CONT_ID` = '"+CONT_ID+"',"
			    		+ "	 `factura_compra_serv`.`fac_comserv_total` = '"+total+"',"
			    		+ "	 `factura_compra_serv`.`fac_comserv_fecmod` = now(),"
			    		+ "	 `factura_compra_serv`.`fac_comserv_modificador` = '"+id_iusuario+"' ,"
			    		+ "	 `factura_compra_serv`.`fac_comserv_descuento` = '"+descuento+"' ,"
			    		+ "	 `factura_compra_serv`.`fac_comserv_glosa_desc` = '"+glosa_desc.toUpperCase()+"', " 
			    		+ "	 `factura_compra_serv`.`fac_comserv_totalfinal` = '"+fac_servim_totalfinal+"', "
			    		+ "	 `factura_compra_serv`.`fac_comserv_neto` = '"+fac_servim_neto+"', "
			    		+ "	 `factura_compra_serv`.`fac_comserv_valor_ivaret` = '"+fac_comserv_valor_ivaret+"', "
			    		+ "	 `factura_compra_serv`.`fac_comserv_porcentaje_ivaret` = '"+fac_comserv_porcentaje_ivaret+"', "
			    		+ "	 `factura_compra_serv`.`fac_comserv_valor_ivanoret` = '"+fac_comserv_valor_ivanoret+"', "
			    		+ "	 `factura_compra_serv`.`fac_comserv_tcambio` = '"+fac_servim_tcambio+"' "
			    		+ "	WHERE"
			    		+ "		`factura_compra_serv`.`fac_comserv_id` = '"+ID_FAC+"' ";
			    	
			    System.out.println(SQL_GUIA);
			    state.executeUpdate(SQL_GUIA);
			    
				//--------------------- FIN -----------------------//
				
				//----------------- DETALLE -----------------------//
				String SQL_DEL = "DELETE FROM detail_fac_compraserv WHERE fac_comserv_id = "+ID_FAC;
				state.executeUpdate(SQL_DEL);
				
				//----------------- DETALLE -----------------------//
				if(request.getParameter("serv1")!="" && request.getParameter("val1")!=""){
					String serv1=request.getParameter("serv1");
					String val1=request.getParameter("val1");
					String SQL_FACDET = "INSERT INTO detail_fac_compraserv (`detail_fac_compraserv`.`fac_comserv_id`,`detail_fac_compraserv`.`dfcs_valor`,`detail_fac_compraserv`.`dfcs_descripcion`) VALUES ("+ID_FAC+","+val1+",'"+serv1+"')";
					state.executeUpdate(SQL_FACDET);}
				if(request.getParameter("serv2")!="" && request.getParameter("val2")!=""){
					String serv2=request.getParameter("serv2");
					String val2=request.getParameter("val2");
			        String SQL_FACDET = "INSERT INTO detail_fac_compraserv (`detail_fac_compraserv`.`fac_comserv_id`,`detail_fac_compraserv`.`dfcs_valor`,`detail_fac_compraserv`.`dfcs_descripcion`) VALUES ("+ID_FAC+","+val2+",'"+serv2+"')";
		            state.executeUpdate(SQL_FACDET);}
				if(request.getParameter("serv3")!="" && request.getParameter("val3")!=""){
					String serv3=request.getParameter("serv3");
					String val3=request.getParameter("val3");
			        String SQL_FACDET = "INSERT INTO detail_fac_compraserv (`detail_fac_compraserv`.`fac_comserv_id`,`detail_fac_compraserv`.`dfcs_valor`,`detail_fac_compraserv`.`dfcs_descripcion`) VALUES ("+ID_FAC+","+val3+",'"+serv3+"')";
		            state.executeUpdate(SQL_FACDET);}
				if(request.getParameter("serv4")!="" && request.getParameter("val4")!=""){
					String serv4=request.getParameter("serv4");
					String val4=request.getParameter("val4");
			        String SQL_FACDET = "INSERT INTO detail_fac_compraserv (`detail_fac_compraserv`.`fac_comserv_id`,`detail_fac_compraserv`.`dfcs_valor`,`detail_fac_compraserv`.`dfcs_descripcion`) VALUES ("+ID_FAC+","+val4+",'"+serv4+"')";
		            state.executeUpdate(SQL_FACDET);}
				if(request.getParameter("serv5")!="" && request.getParameter("val5")!=""){
					String serv5=request.getParameter("serv5");
					String val5=request.getParameter("val5");
			        String SQL_FACDET = "INSERT INTO detail_fac_compraserv (`detail_fac_compraserv`.`fac_comserv_id`,`detail_fac_compraserv`.`dfcs_valor`,`detail_fac_compraserv`.`dfcs_descripcion`) VALUES ("+ID_FAC+","+val5+",'"+serv5+"')";
		            state.executeUpdate(SQL_FACDET);}
				
				//--------------------- FIN -----------------------//
				
			
				state.close();
	        	
	        	RequestDispatcher rd_up = request.getRequestDispatcher("menufacserv?Exito=OK");
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
	
	
		
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		statecor = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
    		String GID=request.getParameter("gd_id");
    		String SQL_CAB = "SELECT "
 		    		+ "		`factura_compra_serv`.`fac_comserv_folio` as fac_servim_folio, "
 		    		+ "		IF(`factura_compra_serv`.`id_dte` is null , '1','0') as dte , "
 		    		+ "		DATE_FORMAT(`factura_compra_serv`.`fac_comserv_fecfac`, '%d-%m-%Y') AS FAC_FECHA ,"
 		    		+ "		DATE_FORMAT(`factura_compra_serv`.`fac_comserv_fecvencimiento`, '%d-%m-%Y') AS fac_servim_fecvencimiento ,"
 		    		+ "		emisor.empresas_nombrenof as empresas_nombrenofemisor  ,"
 		    		+ "		emisor.empresas_id as empresas_idemisor  ,"
 		    		
 		    		+ "		`factura_compra_serv`.`fac_comserv_tipodte` as fac_servim_tipodte  ,"
 		    		+ "		cliente.empresas_razonsocial as empresas_razonsocialcliente, "
 		    		+ "		cliente.empresas_rut as empresas_rutcliente,"
 		    		+ "		cliente.empresas_id as empresas_idcliente,"
 		    		+ "		cliente.empresas_nombrenof as empresas_nombrenofcliente,"
 		    		+ "		`factura_compra_serv`.`fac_comserv_obs` as fac_servim_obs,"
 		    		+ "		`factura_compra_serv`.`id_dte`,"
		    		+ "		`factura_compra_serv`.`fac_comserv_condiciones` as fac_servim_condiciones, "
 		    		+ "		`factura_compra_serv`.`fac_comserv_ref` as fac_servim_ref, "
 		    		+ "		`factura_compra_serv`.`fac_comserv_descuento`, "
 		    		
					+ "		`factura_compra_serv`.`fac_comserv_tcambio` AS fac_servim_tcambio, "
					+ "		`factura_compra_serv`.`fac_comserv_glosa_desc`, "

 					+ "		`contacto`.`CONT_EMAIL`, "
 					+ "		`contacto`.`CONT_ID`, "
 					+ "		`contacto`.`CONT_NOMBRE`, "
 					+ "		`contacto`.`CONT_TELEFONO`,  "
 				
 					+ "		`direccion`.`DIRE_DIRECCION` , "
 					+ "		`direccion`.`DIRE_ID` , "
 					+ "		`direccion`.`DIRE_CIUDAD`,  "
 					+ "		`comuna`.`COMU_NOMBRE`,  "
 					+ "		`region`.`REGI_NOMBRE`, "
 					+ "		`factura_compra_serv`.`fac_comserv_porcentaje_ivaret`, "
 					+ "		`factura_compra_serv`.`fac_comserv_valor_ivaret`,"
 					+ "		`factura_compra_serv`.`fac_comserv_valor_ivanoret`,	 "
 					+ "		`factura_compra_serv`.`fac_comserv_total` as fac_servim_total, "
 					+ "		CONCAT_WS(' ',`usuarios`.`Usuarios_nombre`,`usuarios`.`Usuarios_ape_p`,`usuarios`.`Usuarios_ape_m`) AS Usuarios_nombre_com "
 					
 					+ "	FROM"
 		    		+ "		`factura_compra_serv`"
 		    		+ "	INNER JOIN `empresas` emisor ON `emisor`.`empresas_id` = `factura_compra_serv`.`fac_comserv_emisor` "
 		    		+ " INNER JOIN `empresas` cliente ON cliente.empresas_id = `factura_compra_serv`.`empresas_id` "
 		    		+ " INNER JOIN `contacto` ON `contacto`.`CONT_ID` = `factura_compra_serv`.`CONT_ID` "
 		    		+ " INNER JOIN `direccion` ON `direccion`.`DIRE_ID` = `factura_compra_serv`.`dire_id` "
 		    		+ " INNER JOIN `comuna` ON `comuna`.`COMU_ID` = `direccion`.`COMU_ID` "
 		    		+ " INNER JOIN `region` ON `region`.`REGI_ID` = `comuna`.`REGI_ID` "
 		    		+ " INNER JOIN `usuarios` ON `usuarios`.`Usuarios_id` = cliente.`responsable_id` "
			    	
 		    		

 		    		+ "	WHERE"
 		    		+ "		`factura_compra_serv`.`fac_comserv_id`= "+GID;
		    System.out.println(SQL_CAB);
		    ResultSet CAB_RS = state.executeQuery(SQL_CAB);
		    String peri_tc_id="";
		    if(CAB_RS.next()){
		    	String estado_sii="";
		    	if(CAB_RS.getString("dte").equals("0")){estado_sii = "ENVIADA";}
				if(CAB_RS.getString("dte").equals("1")){estado_sii = "PARA ENVIAR";}
		    	request.setAttribute("estado_sii", estado_sii);
		    	request.setAttribute("folio", CAB_RS.getString("fac_servim_folio"));
		    	request.setAttribute("FAC_FECHA", CAB_RS.getString("FAC_FECHA"));
		    	request.setAttribute("fac_servim_condiciones", CAB_RS.getString("fac_servim_condiciones"));
		    	request.setAttribute("fac_servim_fecvencimiento", CAB_RS.getString("fac_servim_fecvencimiento"));
		    	request.setAttribute("empresas_nombrenofemisor", CAB_RS.getString("empresas_nombrenofemisor"));
		    	request.setAttribute("fac_servim_tipodte", CAB_RS.getString("fac_servim_tipodte"));
		    	request.setAttribute("empresas_razonsocialcliente", CAB_RS.getString("empresas_razonsocialcliente"));
		    	request.setAttribute("empresas_idcliente", CAB_RS.getString("empresas_idcliente"));
		    	request.setAttribute("empresas_rutcliente", CAB_RS.getString("empresas_rutcliente"));
		    	request.setAttribute("empresas_nombrenofcliente", CAB_RS.getString("empresas_nombrenofcliente"));
		    	
		    	request.setAttribute("fac_servim_obs", CAB_RS.getString("fac_servim_obs"));
		    	request.setAttribute("fac_servim_ref", CAB_RS.getString("fac_servim_ref"));
		    	
		    	
		    	
		    	request.setAttribute("CONT_EMAIL", CAB_RS.getString("CONT_EMAIL"));
		    	request.setAttribute("CONT_NOMBRE", CAB_RS.getString("CONT_NOMBRE"));
		    	request.setAttribute("CONT_TELEFONO", CAB_RS.getString("CONT_TELEFONO"));
		    	request.setAttribute("PERS_NOMBRE", CAB_RS.getString("Usuarios_nombre_com"));
		    	request.setAttribute("DIRE_DIRECCION", CAB_RS.getString("DIRE_DIRECCION"));
		    	request.setAttribute("DIRE_CIUDAD", CAB_RS.getString("DIRE_CIUDAD"));
		    	request.setAttribute("COMU_NOMBRE", CAB_RS.getString("COMU_NOMBRE"));
		    	request.setAttribute("REGI_NOMBRE", CAB_RS.getString("REGI_NOMBRE"));
		    	request.setAttribute("fac_comserv_porcentaje_ivaret", CAB_RS.getString("fac_comserv_porcentaje_ivaret"));
		    	request.setAttribute("fac_comserv_valor_ivaret", CAB_RS.getString("fac_comserv_valor_ivaret"));
		    	request.setAttribute("fac_comserv_valor_ivanoret", CAB_RS.getString("fac_comserv_valor_ivanoret"));
		    	
		    	request.setAttribute("fac_servim_total", CAB_RS.getString("fac_servim_total"));
		    	
		    	request.setAttribute("empresas_idemisor", CAB_RS.getString("empresas_idemisor"));
		    	request.setAttribute("clientes_id", CAB_RS.getString("empresas_idcliente"));
		    	request.setAttribute("direcciones_id", CAB_RS.getString("DIRE_ID"));
		    	request.setAttribute("contactos_id", CAB_RS.getString("CONT_ID"));
		    	
		    	request.setAttribute("fac_servim_glosa_desc", CAB_RS.getString("fac_comserv_glosa_desc"));
		    	request.setAttribute("DESC", CAB_RS.getString("fac_comserv_descuento"));
		    	
		    	request.setAttribute("fac_servim_tcambio", CAB_RS.getString("fac_servim_tcambio"));
		    	
		    	
		    	String id_dte= CAB_RS.getString("id_dte");
		    	if(id_dte==null || id_dte.equals(""))id_dte="0";
		    	else id_dte="1";
		    	
		    	request.setAttribute("id_dte", id_dte);
		    	

		    }
		    CAB_RS.close();
		    
		    String SQL_DET = "SELECT * FROM detail_fac_compraserv WHERE fac_comserv_id = "+GID;
			  
		    ResultSet DET_RS = state.executeQuery(SQL_DET);
		    ArrayList<String> prod = new ArrayList<String>();
		    Integer c=0;
		    while(DET_RS.next()){ prod.add(c+"/"+DET_RS.getString("dfcs_descripcion")+"/"+DET_RS.getString("dfcs_valor")); c=c+1;}
		    DET_RS.close();	
		    String[] ar_prod = new String[5];
		    for(int x=0; x < prod.size(); x++){ 
		    	
		    		ar_prod[x]=prod.get(x);	
		    	
		    	 
		    
		    }
		    
		    
		    request.setAttribute("ar_prod", ar_prod);
		    
		    ////////////////////--FIN FACTURA--////////////////////////
		    
		    
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

    		//--------------------- EMISOR ----------------------//
		    String SQL_EMPRESA = "SELECT * FROM empresas WHERE estados_vig_novig_id = 1 AND empresas_relacionada = 1 ORDER BY empresas_nombre";
		    ResultSet EMPRESAS_RS = state.executeQuery(SQL_EMPRESA);
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
		    ResultSet CLIENTE_RS = state.executeQuery(SQL_CLIENTE);
		    ArrayList<String> clientes = new ArrayList<String>();
		    while(CLIENTE_RS.next()){ 

				String empresas_rut_ar[]=CLIENTE_RS.getString("empresas_rut").split("-");
				java.text.NumberFormat nff = java.text.NumberFormat.getInstance();
				String valRut = nff.format(Integer.parseInt(empresas_rut_ar[0]));
				
				String empresas_rut=valRut+"-"+empresas_rut_ar[1];

		    	clientes.add(CLIENTE_RS.getString("empresas_id")+"/"+CLIENTE_RS.getString("empresas_nombrenof")+"/"+empresas_rut+"/"+CLIENTE_RS.getString("empresas_razonsocial")+"/"+CLIENTE_RS.getString("Usuarios_nombre_com")+"/"+CLIENTE_RS.getString("empresas_giro")); }
		    CLIENTE_RS.close();
		    String[] ar_clientes = new String[clientes.size()];
		    for(int x=0; x < clientes.size(); x++){ ar_clientes[x]=clientes.get(x);}
		    request.setAttribute("ar_clientes", ar_clientes);
		    
    		//--------------------- CONTACTO ----------------------//
		    String SQL_CONTACTO = "SELECT cont_id, CLPR_ID, PERS_ID, CONT_TELEFONO, CONT_EMAIL, CONT_TELEFONOC, "
		    		+ "CONCAT_WS(' ', cont_nombre, CONT_APEP, CONT_APEM) as cont_nombre   FROM contacto ORDER BY cont_nombre";
		    ResultSet CONTACTOS_RS = state.executeQuery(SQL_CONTACTO);
		    ArrayList<String> contactos = new ArrayList<String>();
		    while(CONTACTOS_RS.next()){ contactos.add(	CONTACTOS_RS.getString("cont_id")+"/"+
					CONTACTOS_RS.getString("cont_nombre").replace("/", " ")+"/"+
					CONTACTOS_RS.getString("CLPR_ID")+"/"+
					CONTACTOS_RS.getString("PERS_ID")+"/"+
					CONTACTOS_RS.getString("CONT_TELEFONO").replace("/", " ")+"/"+
					CONTACTOS_RS.getString("CONT_EMAIL")+"/"+
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
		    		+ "		estados_vig_novig_id = 1"
		    		+ "	ORDER BY"
		    		+ "		DIRE_DIRECCION";
		    System.out.println(SQL_DIRECCION);
		    ResultSet DIRECCION_RS = state.executeQuery(SQL_DIRECCION);
		    ArrayList<String> direcciones = new ArrayList<String>();
		    while(DIRECCION_RS.next()){
		    	direcciones.add(DIRECCION_RS.getString("dire_id")+"/"+DIRECCION_RS.getString("DIRE_DIRECCION").replace("/", " ")+"/"+DIRECCION_RS.getString("CLPR_ID")+"/"+DIRECCION_RS.getString("REGI_NOMBRE")+"/"+DIRECCION_RS.getString("DIRE_CIUDAD")+"/"+DIRECCION_RS.getString("COMU_NOMBRE")); }
		    DIRECCION_RS.close();
		    String[] ar_direcciones = new String[direcciones.size()];
		    for(int x=0; x < direcciones.size(); x++){ ar_direcciones[x]=direcciones.get(x);}
		    request.setAttribute("ar_direcciones", ar_direcciones);
		    //----------------------- FIN ------------------------//
		  
		    //--------------------- FOLIOS ----------------------//
		    String SQL_FOLIOS = "SELECT "
		    		+ "		`factura_compra_serv`.`fac_comserv_folio`"
		    		+ "	FROM"
		    		+ "		`factura_compra_serv`"
		    		+ "	WHERE"
		    		+ "		`factura_compra_serv`.`fac_comserv_folio` IS NOT NULL AND `factura_compra_serv`.`fac_comserv_id` <>'"+GID+"'";
		    
		    System.out.println(SQL_FOLIOS);
		    ResultSet FOLIOS_RS = state.executeQuery(SQL_FOLIOS);
		    ArrayList<String> folios = new ArrayList<String>();
		    while(FOLIOS_RS.next()){ 
		    	folios.add(FOLIOS_RS.getString("fac_comserv_folio")); }
		    FOLIOS_RS.close();
		    String[] ar_folios = new String[folios.size()];
		    for(int x=0; x < folios.size(); x++){ ar_folios[x]=folios.get(x);}
		    request.setAttribute("ar_folios", ar_folios);
		    //----------------------- FIN ------------------------//
		    
		  
//		   
            statecor.close();
            conexion.close();
		}catch(Exception ex){
		    out.println("Error "+ex);
		    ex.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("mfac2.jsp");
        rd.forward(request, response);
		}
	}

}
