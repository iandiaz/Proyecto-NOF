

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

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
 * Servlet implementation class cnc2
 */
@WebServlet("/cnc2")
public class cnc2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cnc2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(Controlador.validateSession(request, response)){
			
			 mt(request,response);
		}
		else response.sendRedirect("/001/");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(Controlador.validateSession(request, response)){
			
			 mt(request,response);
		}
		else response.sendRedirect("/001/");
	}

	public void mt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
		
		Cookie [] cookies=request.getCookies();
		if(request.getParameter("logout") !=  null){
			Controlador.eraseCookie(request, response);
				response.sendRedirect("/001/");	
				return;
		}
		String Usuarios_nombre=Controlador.getUsunameSession(request);
		
		request.setAttribute("usuname", Usuarios_nombre);
		
		Statement state = null;
		ResultSet clpr_rs = null;
		
		request.setAttribute("tref", request.getParameter("tref"));
		request.setAttribute("fac_id", request.getParameter("fac_id"));
		request.setAttribute("mcod", request.getParameter("mcod"));
		
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion=(Connection) DriverManager.getConnection
		    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
			state = (Statement) ((java.sql.Connection) conexion).createStatement();
			Statement statecor = (Statement) ((java.sql.Connection) conexion).createStatement();
			Statement stateDet = (Statement) ((java.sql.Connection) conexion).createStatement();
				
			 
			 //validamos que numero de modulo es 
			
			request.setAttribute("d_id", request.getParameter("d_id"));
	    	
			 String f_nc_sql="SELECT"
				 		+ "		`841`.`841_id`,"
				 		+ "		IF (`841`.`841_folio` IS NULL,	'ND',`841`.`841_folio`) AS 841_folio,"
				 		+ "		`841`.`folioref`,"
				 		+ "		`841`.`841_modulo_ref`,"
				 		+ "		`841`.`CodigoRef`, "
				 		+ "		`841`.`CodigoRef_nombre`,"
				 		+ "		`841`.`estados_vig_novig_id`,"
				 		+ "		`841`.`841_dondedice`, "
				 		+ "		`841`.`841_debedecir`, "
				 		+ "		DATE_FORMAT(`841`.`841_fecha`,'%d-%m-%Y') as fec_nc, "
				 		
						+ "		`841`.`841_subtotal`, "
						+ "		`841`.`841_descuento`, "
						+ "		`841`.`841_total`, "
						+ "		`841`.`841_neto`, "
						+ "		`841`.`841_iva`, "
						+ "		`841`.`841_total` ,"
						+ "		s.estados_vig_novig_nombre ,  "
						+ "		s.estados_vig_novig_id ,  "
						+ "		`841`.`841_razon_nc` "
		
				 		+ "	FROM"
				 		+ "		`841`"
				 		+ " INNER JOIN estados_vig_novig s ON s.estados_vig_novig_id=`841`.estados_vig_novig_id"
				 		+ "	WHERE `841`.`841_id`="+request.getParameter("d_id");
				 	System.out.println(f_nc_sql);
				    ResultSet f_nc_RS = state.executeQuery(f_nc_sql);
				    
				    String folio_referencia="";
				    String tref="";
				    String modref="";
				    if(f_nc_RS.next()){
				    	folio_referencia=f_nc_RS.getString("folioref");
				    	request.setAttribute("tiporef", f_nc_RS.getString("CodigoRef_nombre"));
				    	tref=f_nc_RS.getString("CodigoRef");
				    	request.setAttribute("tref", f_nc_RS.getString("CodigoRef"));
				    	request.setAttribute("folionc", f_nc_RS.getString("841_folio"));
				    	request.setAttribute("nc_dondedice", f_nc_RS.getString("841_dondedice"));
				    	request.setAttribute("nc_debedecir", f_nc_RS.getString("841_debedecir"));
				    	request.setAttribute("fec_nc", f_nc_RS.getString("fec_nc"));
				    	
				    	
				    	request.setAttribute("razon_nc", f_nc_RS.getString("841_razon_nc"));
				    	
				    	request.setAttribute("estados_vig_novig_nombre", f_nc_RS.getString("estados_vig_novig_nombre"));
				    	request.setAttribute("estados_vig_novig_id", f_nc_RS.getString("estados_vig_novig_id"));
				    	request.setAttribute("estadovignovig",f_nc_RS.getString("estados_vig_novig_id"));
				    	
				    	request.setAttribute("DESCUENTO", f_nc_RS.getString("841_descuento"));
				    	request.setAttribute("TOTAL", f_nc_RS.getString("841_total"));
				    	request.setAttribute("NETO", f_nc_RS.getString("841_neto"));
				    	request.setAttribute("IVA", f_nc_RS.getString("841_iva"));
				    	request.setAttribute("SUBTOTAL", f_nc_RS.getString("841_subtotal"));
				    	modref=f_nc_RS.getString("841_modulo_ref");
				    	
				    	
				    	
				    }
				    //traemos detalle
				    
				    String SQL_DETT = "SELECT * FROM detalle_841 WHERE estados_vig_novig_id=1 AND 841_id = "+request.getParameter("d_id");
					  System.out.println(SQL_DETT);
				    ResultSet DETT_RS = state.executeQuery(SQL_DETT);
				    ArrayList<String> prod_nc = new ArrayList<String>();
				    Integer c_nc=0;
				    while(DETT_RS.next()){ prod_nc.add(c_nc+"//"+DETT_RS.getString("d841_valor")); c_nc=c_nc+1;}
				    DETT_RS.close();	
				    String[] ar_prod_nc = new String[prod_nc.size()];
				    for(int x=0; x < prod_nc.size(); x++){ 
				    	ar_prod_nc[x]=prod_nc.get(x);
				    	 
				    
				    }
				    request.setAttribute("ar_prod_nc", ar_prod_nc);
				    
				    /////////////////////FIN DATOS NC ////////////////////////////////
				    
				  //validamos que numero de modulo es 
				
				    if(modref.equals("801")){
						
						 String GID="";
						 String SQL_DATOS = "SELECT  "
						 			+ "	F.801_id, "
				            		+ " DATE_FORMAT(F.801_FECHA, '%d-%m-%Y') AS FACT_FECHA, "
				            		+ " F.801_CONDICIONES,801_folio_birt, "
									
									+ " C1.empresas_nombrenof as CLPR_NOMBRE_FANTASIA,"
									+ " C2.empresas_razonsocial as CLPR_RAZON_SOCIAL,"
				            		+ " C2.empresas_rut as CLPR_RUT,"
				            		+ " C2.empresas_giro ,"
				            		+ " F.CLPR_ID, "
				            		
									
				            		+ " DIRECCION.DIRE_DIRECCION, COMUNA.COMU_NOMBRE, "
						    		+ " DIRECCION.DIRE_ID, DIRECCION.DIRE_CIUDAD, "
						    		+ " REGION.REGI_NOMBRE, "
				            		+ " F.801_TIPO_CAMBIO, "
				            		
				            		+ " F.CONT_NOMBRE, "
				            		+ " F.CONT_TELEFONO, "
				            		+ " F.CONT_EMAIL, "
				            		+ " F.801_OBS, "
				            		+ " F.801_ESTADO,"
				            		+ "	F.estados_vig_novig_id,  "
				            		+ "	F.801_FOLIO,"
				            		
				            		+ " F.801_subtotal, "
				            		+ " F.801_descuento, "
				            		+ " F.801_neto, "
				            		+ " F.801_iva, "
				            		+ " F.801_total, "
				            		+ " F.801_glosa_descuento, "
				            		+ " DATE_FORMAT(F.801_fecvencimiento, '%d-%m-%Y') AS 801_fecvencimiento , "
				            		
				            		+ " DATE_FORMAT(F.801_FECHA_EMISION, '%d-%m-%Y') AS FACT_FECHA_EMISION, "
				            		+ " u1.Usuarios_nombre, u1.Usuarios_ape_p, "
				            		+ " USUARIOS.Usuarios_login,"
				            		+ " F.801_tipodte, "
				            		+ " F.801_obs1, F.801_obs2,F.USU_INICIAL_EMISOR,  "
				            		+ "		IF(F.`tipo_dteref` is null,'',F.`tipo_dteref`) as tipo_dteref, "
									+ "		IF(F.`folioref` is null ,'',F.`folioref`) as  folioref, "
									+ "		IF(F.`fec_ref` is null,'',DATE_FORMAT(F.`fec_ref`,'%d-%m-%Y')) as fec_ref, "
									+ " F.id_dte "
						    		
									
				            		+ " FROM `801` F "
				            		+ " LEFT JOIN empresas as C1 ON F.801_EMPRESA_EMISORA = C1.empresas_id "
				            		+ " LEFT JOIN empresas as C2 ON F.CLPR_ID = C2.empresas_id "
				            		+ " LEFT JOIN DIRECCION ON F.DIRE_ID = DIRECCION.DIRE_ID"
				            		+ " LEFT JOIN COMUNA ON DIRECCION.COMU_ID = COMUNA.COMU_ID"
						    		+ " LEFT JOIN REGION ON COMUNA.REGI_ID = REGION.REGI_ID"
				            		+ " LEFT JOIN USUARIOS ON F.801_USU_EMISION = USUARIOS.Usuarios_id "
				            		+ " LEFT JOIN USUARIOS u1 ON F.801_RESPONSABLE = u1.Usuarios_id "
				            		+ "	WHERE"
						    		+ "		`F`.`801_folio` = '"+folio_referencia+"'";
						    				    		
						 System.out.println(SQL_DATOS);
						    ResultSet RS_DATOS = state.executeQuery(SQL_DATOS);
						    if(RS_DATOS.next()){
						    	GID=RS_DATOS.getString("801_id");
						    	 request.setAttribute("FACT_ID",GID);
						            request.setAttribute("numbirt",RS_DATOS.getString("801_folio_birt"));
						            request.setAttribute("factura_tipodte",RS_DATOS.getString("801_tipodte"));
						            request.setAttribute("factura_obs1",RS_DATOS.getString("801_obs1"));
						            request.setAttribute("factura_obs2",RS_DATOS.getString("801_obs2"));
							 		request.setAttribute("FACT_FECHA",RS_DATOS.getString("FACT_FECHA"));
							 		request.setAttribute("FACT_CONDICIONES",RS_DATOS.getString("801_CONDICIONES"));
							 		request.setAttribute("CLPR_NOMBRE_FANTASIA",RS_DATOS.getString("CLPR_NOMBRE_FANTASIA"));
							 		request.setAttribute("CLPR_RAZON_SOCIAL",RS_DATOS.getString("CLPR_RAZON_SOCIAL"));
							 		request.setAttribute("CLPR_RUT",RS_DATOS.getString("CLPR_RUT"));
							 		request.setAttribute("CLPR_GIRO",RS_DATOS.getString("empresas_giro"));
							 		request.setAttribute("CLPR_ID",RS_DATOS.getString("CLPR_ID"));
							 		
							 		request.setAttribute("fec_vencimiento",RS_DATOS.getString("801_fecvencimiento"));
							 		request.setAttribute("FACT_FOLIO", RS_DATOS.getString("801_FOLIO"));
							 		
							 		//request.setAttribute("CLPR_DV",RS_DATOS.getString("CLPR_DV"));
							 		request.setAttribute("DIRE_DIRECCION",RS_DATOS.getString("DIRE_DIRECCION"));
							 		request.setAttribute("COMU_NOMBRE",RS_DATOS.getString("COMU_NOMBRE"));
							 		request.setAttribute("DIRE_CIUDAD",RS_DATOS.getString("DIRE_CIUDAD"));
							 		request.setAttribute("REGI_NOMBRE",RS_DATOS.getString("REGI_NOMBRE"));
							 		request.setAttribute("FAC_RESPONSABLE",RS_DATOS.getString("Usuarios_nombre")+" "+RS_DATOS.getString("Usuarios_ape_p"));
							 		request.setAttribute("FACT_TIPO_CAMBIO",RS_DATOS.getString("801_TIPO_CAMBIO"));
							 		request.setAttribute("CONT_NOMBRE",RS_DATOS.getString("CONT_NOMBRE"));
							 		request.setAttribute("CONT_TELEFONO",RS_DATOS.getString("CONT_TELEFONO"));
							 		request.setAttribute("CONT_EMAIL",RS_DATOS.getString("CONT_EMAIL"));
							 		
							 		request.setAttribute("FACT_OBS",RS_DATOS.getString("801_OBS"));
							 		request.setAttribute("FACT_ESTADO",RS_DATOS.getString("801_ESTADO"));
							 		request.setAttribute("FACT_FECHA_EMISION",RS_DATOS.getString("FACT_FECHA_EMISION"));
							 		request.setAttribute("USU_INICIAL",RS_DATOS.getString("USU_INICIAL_EMISOR"));
							 		
							 		
							 		request.setAttribute("fec_ref", RS_DATOS.getString("fec_ref"));
							    	request.setAttribute("folioref", RS_DATOS.getString("folioref"));
							    	request.setAttribute("tipo_dteref", RS_DATOS.getString("tipo_dteref"));
							    	
							    	
							    		request.setAttribute("GLOSADESC",RS_DATOS.getString("801_glosa_descuento"));
							 			
						    }
						    
						    
						    
						    
						    String SQL_DATOSD = "SELECT D.DETIF_ID,D.ALT_ID, IFNULL(D.PROD_DESC_CORTO, ' ') as PROD_DESC_CORTO,"
			 		   				+ " D.DETI_UNITARIO, IFNULL(D.PROD_PN_TLI_CODFAB, ' ') as PROD_PN_TLI_CODFAB, IFNULL(D.ALT_SERIE, ' ') as ALT_SERIE, D.VTA_OC,D.UBI_ID,D.VTA_TICK_ID "
				    		 		+ " FROM detalle_801 D"
				    		 		+ " WHERE D.FACT_ID ='"+GID+"'";
			 		   System.out.println(SQL_DATOSD);
			 		   ResultSet RS_DATOSD = state.executeQuery(SQL_DATOSD);
			 		   ArrayList<String> prod_res = new ArrayList<String>();
			 		   while(RS_DATOSD.next()){
			 			   
			 			   int valor= RS_DATOSD.getInt("DETI_UNITARIO");
			 			   if(tref.equals("3")){
			 				   //sacamos el valor de la tabla de 841 
			 				   String SQL_DETTALLE = "SELECT * FROM detalle_841 WHERE estados_vig_novig_id=1 AND 841_id = "+request.getParameter("d_id")+" AND d841_idref="+RS_DATOSD.getString("DETIF_ID");
			 				    System.out.println(SQL_DETTALLE);
			 				    ResultSet DETTALLE_RS = stateDet.executeQuery(SQL_DETTALLE);
			 				    if(DETTALLE_RS.next()) valor= DETTALLE_RS.getInt("d841_valor");
			 				    
			 			   }
			 			   
			 			   prod_res.add(RS_DATOSD.getString("ALT_ID")+"/"+
			 					  RS_DATOSD.getString("PROD_PN_TLI_CODFAB")+"/"+
			 					  RS_DATOSD.getString("PROD_DESC_CORTO").replace("/", " ")+"/"+
			 					  RS_DATOSD.getString("ALT_SERIE").replace("/","")+"/"+
			 					 valor+"/"+ 
			 					 RS_DATOSD.getInt("UBI_ID")+"/"+
			 					 
			 					RS_DATOSD.getInt("VTA_TICK_ID")+"/"+
			 					RS_DATOSD.getInt("DETIF_ID"));
			 			   
			 		
			 			   
			 			   
			 			  request.setAttribute("VTA_OC",RS_DATOSD.getString("VTA_OC"));
			 			   
			 			  
			 		   }
			 		   RS_DATOSD.close();
			 		  RS_DATOS.close();
			 		   
			 		   String[] ar_productos = new String[prod_res.size()];
			 		   for(int x=0; x < prod_res.size(); x++){
			 			   ar_productos[x]=prod_res.get(x);
			 		   }
			 		   request.setAttribute("ar_productos", ar_productos);
//						   
			           statecor.close();
			           conexion.close();
			            
			           RequestDispatcher rd = request.getRequestDispatcher("cnc2_801.jsp");
			           rd.forward(request, response);
					 }
				    
				    if(modref.equals("802")){
						 String GID=request.getParameter("fac_id");
						//-------------------------CABECERA GUIA RESUMEN-------------------------//
						 String SQL_CAB = "SELECT 	g.802_folio,"
				    				+ "					g.802_id,"
				    				+ "					g.802_ciudad , "
				    				+ "					g.802_neto,"
				    				+ "					g.802_glosa_desc,"
				    				+ " 				e1.empresas_nombrenof as emisor_nof, "
				    				
									+ "		e1.empresas_id as empresas_idemisor  ,"


				    				+ "					DATE_FORMAT(g.802_fecha, '%d-%m-%Y') as gr_fecha,"
				    				+ " 				ifnull(g.802_folio, 'ND') as gr_folio2 , "
				    				+ "					IF(g.`id_dte` is null , '0','1') as id_dte,"
				    				+ "					g.802_obs ,  "
				    				
				    				+ "					g.802_ciudad,"
				    				+ "					g.802_descuento,"
				    				+ "					g.802_iva,"
				    				+ "					g.802_subtotal,"
				    				+ "					g.802_total,"
				    				
				    				+ "					DATE_FORMAT(g.802_fecvencimiento, '%d-%m-%Y') as 802_fecvencimiento,"
				    				
				    				+ "					g.802_tipodte, "
				    				+ "					e.empresas_id,"
				    				+ "					e.empresas_razonsocial,"
				    				+ "					e.empresas_rut,"
				    				+ "					e.empresas_nombrenof, e.empresas_giro,"
				    				+ "		CONCAT_WS(' ',`usuarios`.`Usuarios_nombre`,`usuarios`.`Usuarios_ape_p`,`usuarios`.`Usuarios_ape_m`) AS Usuarios_nombre_com, "
				    				+ " USUEMISOR.Usuarios_inicial as USU_INICIAL_EMISOR, "
				    		  		+ "					o.CONT_EMAIL , "
				    				+ "					o.cont_telefono,"
				    				+ "					g.cont_nombre, "
				    				+ "					c.comu_nombre,"
				    				+ "					r.regi_nombre,"
				    				+ "					d.dire_nombre, "
				    				+ "					s.estados_vig_novig_nombre  , "
				    				
									+ "		IF(`g`.`tipo_dteref` is null,'NINGUNA',`g`.`tipo_dteref`) as tipo_dteref, "
									+ "		IF(`g`.`folioref` is null ,'',`g`.`folioref`) as  folioref, "
									+ "		IF(`g`.`fec_ref` is null,'',DATE_FORMAT(`g`.`fec_ref`, '%d-%m-%Y')) as fec_ref "

				    				
				    				+ " FROM `802` g"
						    		+ " INNER JOIN empresas e ON e.empresas_id=g.cliente_id"
						    		+ " INNER JOIN empresas e1 ON e1.empresas_id=g.802_empresa_emisora"
						    		+ " INNER JOIN direccion d ON d.DIRE_ID=g.direccion_id"
						    		+ " INNER JOIN comuna c ON c.comu_id=d.comu_id"
						    		+ " INNER JOIN region r ON r.regi_id=c.regi_id"
						    		+ " INNER JOIN contacto o ON o.cont_id=g.contacto_id"
						    		+ " INNER JOIN `usuarios` ON `usuarios`.`Usuarios_id` = e.`responsable_id` "

									+ " LEFT JOIN USUARIOS USUEMISOR ON g.802_creador = USUEMISOR.USUARIOS_ID"
						    		+ " INNER JOIN estados_vig_novig s ON s.estados_vig_novig_id=g.estados_vig_novig_id"
						    		+ " WHERE g.802_folio = "+folio_referencia+" "
						    		+ " ORDER BY g.802_id";
						    System.out.println(SQL_CAB);
						    ResultSet RS_CAB = state.executeQuery(SQL_CAB);
						    String id="";
						    if(RS_CAB.next()){
						    	
						    	id=RS_CAB.getString("802_id");
						    	request.setAttribute("guiaresumen_id", GID);
						    	request.setAttribute("empresas_id", RS_CAB.getString("empresas_id"));
						    	request.setAttribute("id_dte", RS_CAB.getString("id_dte"));
							    request.setAttribute("gr_folio", RS_CAB.getString("gr_folio2"));
							    
						    	request.setAttribute("gr_fecha", RS_CAB.getString("gr_fecha"));
							    request.setAttribute("emisor_nof", RS_CAB.getString("emisor_nof"));
							    
							    request.setAttribute("empresas_nombrenof", RS_CAB.getString("empresas_nombrenof"));
							    request.setAttribute("empresas_rut", RS_CAB.getString("empresas_rut"));
							    request.setAttribute("empresas_razonsocial", RS_CAB.getString("empresas_razonsocial"));
							    
							    
							    request.setAttribute("direccion_nombre", RS_CAB.getString("dire_nombre"));
							    request.setAttribute("regi_nombre", RS_CAB.getString("regi_nombre"));
							    request.setAttribute("gr_ciudad", RS_CAB.getString("802_ciudad"));
							    request.setAttribute("comu_nombre", RS_CAB.getString("comu_nombre"));
							    request.setAttribute("cont_nombre", RS_CAB.getString("cont_nombre"));
							    request.setAttribute("cont_telefono", RS_CAB.getString("cont_telefono"));
							    request.setAttribute("CONT_EMAIL", RS_CAB.getString("CONT_EMAIL"));
							    request.setAttribute("gr_responsable", RS_CAB.getString("Usuarios_nombre_com"));
							    
							    request.setAttribute("gr_obs", RS_CAB.getString("802_obs"));
							    request.setAttribute("NETO",RS_CAB.getString("802_neto"));
							    request.setAttribute("subtotal",RS_CAB.getString("802_subtotal"));
							    request.setAttribute("iva",RS_CAB.getString("802_iva"));
							    request.setAttribute("total",RS_CAB.getString("802_total"));
							    
							    request.setAttribute("fac_servim_fecvencimiento",RS_CAB.getString("802_fecvencimiento").substring(0, 10));
							    request.setAttribute("DESC",RS_CAB.getString("802_descuento"));
							    request.setAttribute("fac_servim_tipodte",RS_CAB.getString("802_tipodte"));
							 
							    request.setAttribute("gr_glosa_desc",RS_CAB.getString("802_glosa_desc"));
							    
							    request.setAttribute("fec_ref", RS_CAB.getString("fec_ref"));
						    	request.setAttribute("folioref", RS_CAB.getString("folioref"));
						    	request.setAttribute("tipo_dteref", RS_CAB.getString("tipo_dteref"));
						    	request.setAttribute("empresas_giro",RS_CAB.getString("empresas_giro"));
						    	request.setAttribute("empresas_idemisor", RS_CAB.getString("empresas_idemisor"));
						    	request.setAttribute("USU_INICIAL_EMISOR", RS_CAB.getString("USU_INICIAL_EMISOR"));
						    	
						    	
							 
						    }
					
					 		
					 		//-------------------------CABECERA GUIA RESUMEN-------------------------//
						    
						    //-------------------------DETALLE GUIA RESUMEN-------------------------//
				    		String SQL_DETALLE = "SELECT d802_id,802_ID,d802_cod, d802_TOTAL, DATE_FORMAT(d802_FECHA, '%d-%m-%Y') as GD_FECHA,d802_folio "
				    				+ " FROM detalle_802 "
				    				+ " WHERE 802_id = "+id+" AND estados_vig_novig_id = 1 ORDER BY d802_id";
						    System.out.println(SQL_DETALLE);
						    ResultSet RS_DETALLE = state.executeQuery(SQL_DETALLE);
						    ArrayList<String> gr_detalle = new ArrayList<String>();
						    while(RS_DETALLE.next()){
						    	
						    	 int valor= RS_DETALLE.getInt("d802_TOTAL");
					 			   if(tref.equals("3")){
					 				   //sacamos el valor de la tabla de 841 
					 				   String SQL_DETTALLE = "SELECT * FROM detalle_841 WHERE estados_vig_novig_id=1 AND 841_id = "+request.getParameter("d_id")+" AND d841_idref="+RS_DETALLE.getString("d802_id");
					 				    System.out.println(SQL_DETTALLE);
					 				    ResultSet DETTALLE_RS = stateDet.executeQuery(SQL_DETTALLE);
					 				    if(DETTALLE_RS.next()) valor= DETTALLE_RS.getInt("d841_valor");
					 				    
					 			   }
						    	
							    gr_detalle.add(RS_DETALLE.getString("802_ID")+"/"+valor+"/"+RS_DETALLE.getString("GD_FECHA")+"/"+RS_DETALLE.getString("d802_cod")+"/"+RS_DETALLE.getString("d802_folio")+"/"+RS_DETALLE.getString("d802_id"));
				    	    }
						    RS_DETALLE.close();
						    state.close();
						    conexion.close();
				                
						    String[] gr_det = new String[gr_detalle.size()];
						    for(int x=0; x < gr_detalle.size(); x++){
						    	gr_det[x]=gr_detalle.get(x);
						    }
						    request.setAttribute("ar_alertas", gr_det);
						    //-------------------------DETALLE GUIA RESUMEN-------------------------//
				            
				            RequestDispatcher rd = request.getRequestDispatcher("cnc2_802.jsp");
				            rd.forward(request, response);
					 }
				    
				    
				    if(modref.equals("803")){
						 
						 String SQL_CAB = "SELECT "
									+ "		`803`.`803_id`, "
							    	+ "		`803`.`803_folio`, "
							    	+ "		IF(`803`.`id_dte` is null , '1','0') as dte , "
						    		+ "		DATE_FORMAT(`803`.`803_fecha`, '%d-%m-%Y') AS FAC_FECHA ,"
						    		+ "		DATE_FORMAT(`803`.`803_fecvencimiento`, '%d-%m-%Y') AS fac_servim_fecvencimiento ,"
						    		+ "		emisor.empresas_nombrenof as empresas_nombrenofemisor  ,"
						    		+ "		emisor.empresas_id as empresas_idemisor  ,"
						    		+ "		`803`.`803_tipodte`  ,"
						    		+ "		cliente.empresas_razonsocial as empresas_razonsocialcliente, "
						    		+ "		cliente.empresas_rut as empresas_rutcliente,"
						    		+ "		cliente.empresas_id as empresas_idcliente,"
						    		+ "		cliente.empresas_nombrenof as empresas_nombrenofcliente,"
						    		+ "		cliente.empresas_giro as empresas_girocliente,"
						    		+ "		`803`.`803_n_impresiones`,"
						    		+ "		`803`.`803_obs`,"
						    		 
						    		+ "		`803`.`803_condiciones`, "
						    		+ "		`803`.`803_descuento`, "
						    		+ "		`803`.`803_glosa_descuento`, "
						    		+ "		`803`.`803_estado`, "
						    		+ "		`803`.`803_neto`, "
						    		+ "		`803`.`803_iva`, "
						    		+ "		`803`.`803_total`, "
						    		+ "		`803`.`803_subtotal`, "
						    		
									+ "		IF(`803`.`tipo_dteref` is null,'NINGUNA',`803`.`tipo_dteref`) as tipo_dteref, "
									+ "		IF(`803`.`folioref` is null ,'',`803`.`folioref`) as  folioref, "
									+ "		IF(`803`.`fec_ref` is null,'',DATE_FORMAT(`803`.`fec_ref`,'%d-%m-%Y')) as fec_ref, "

									+ "		`contacto`.`CONT_EMAIL`, "
									+ "		CONCAT_WS(' ',`CONTACTO`.CONT_NOMBRE,`CONTACTO`.CONT_APEP,`CONTACTO`.CONT_APEM) AS CONT_NOMBRE, "
				 					+ "		`contacto`.`CONT_TELEFONO`,  "
									+ "		`contacto`.`CONT_TELEFONOC`,  "
									
									+ "		`direccion`.`DIRE_DIRECCION` , "
									+ "		`direccion`.`DIRE_CIUDAD`,  "
									+ "		`comuna`.`COMU_NOMBRE`,  "
									+ "		`region`.`REGI_NOMBRE`, "
									+ "		`803`.`803_tipo_cambio`, "
									
									+ "		`periodos_tc`.`peri_tc_correlativo`, "
									+ "		`periodos_tc`.`peri_tc_fdesde`, "
									+ "		`periodos_tc`.`peri_tc_fhasta`, "
									+ "		CONCAT_WS(' ',`usuarios`.`Usuarios_nombre`,`usuarios`.`Usuarios_ape_p`,`usuarios`.`Usuarios_ape_m`) AS Usuarios_nombre_com "
				    		  		
									
									+ "	FROM"
						    		+ "		`803`"
						    		+ "	INNER JOIN `empresas` emisor ON `emisor`.`empresas_id` = `803`.`803_empresa_emisora` "
						    		+ " INNER JOIN `empresas` cliente ON cliente.empresas_id = `803`.`clpr_id` "
						    		+ " INNER JOIN `contacto` ON `contacto`.`CONT_ID` = `803`.`CONT_ID` "
						    		+ " INNER JOIN `direccion` ON `direccion`.`DIRE_ID` = `803`.`dire_id` "
						    		+ " INNER JOIN `comuna` ON `comuna`.`COMU_ID` = `direccion`.`COMU_ID` "
						    		+ " INNER JOIN `region` ON `region`.`REGI_ID` = `comuna`.`REGI_ID` "
						    		+ " INNER JOIN `usuarios` ON `usuarios`.`Usuarios_id` = cliente.`responsable_id` "
							    	+ " LEFT JOIN `periodos_tc` ON `periodos_tc`.`peri_tc_id` = `803`.`peri_tc_id` "
						    		
						    		+ "	WHERE"
						    		+ "		`803`.`803_folio` = '"+folio_referencia+"'";
						    		
						    System.out.println(SQL_CAB);
						    String id="";
						    ResultSet CAB_RS = state.executeQuery(SQL_CAB);
						    if(CAB_RS.next()){
						    	
						    	id=CAB_RS.getString("803_id");
						    	String estado_sii="";
						    	if(CAB_RS.getString("dte").equals("0")){estado_sii = "ENVIADA";}
								if(CAB_RS.getString("dte").equals("1")){estado_sii = "PARA ENVIAR";}
						    	request.setAttribute("estado_sii", estado_sii);
						    	request.setAttribute("folio", CAB_RS.getString("803_folio"));
						    	request.setAttribute("FAC_FECHA", CAB_RS.getString("FAC_FECHA"));
						    	request.setAttribute("fac_servim_condiciones", CAB_RS.getString("803_condiciones"));
						    	request.setAttribute("fac_servim_fecvencimiento", CAB_RS.getString("fac_servim_fecvencimiento"));
						    	request.setAttribute("empresas_nombrenofemisor", CAB_RS.getString("empresas_nombrenofemisor"));
						    	
						    	request.setAttribute("fac_servim_glosa_desc", CAB_RS.getString("803_glosa_descuento"));
						    	request.setAttribute("fac_servim_estado", CAB_RS.getString("803_estado"));
						    	
						    	
						    	
						    	request.setAttribute("fac_servim_tipodte", CAB_RS.getString("803_tipodte"));
						    	request.setAttribute("empresas_razonsocialcliente", CAB_RS.getString("empresas_razonsocialcliente"));
						    	request.setAttribute("empresas_idcliente", CAB_RS.getString("empresas_idcliente"));
						    	request.setAttribute("empresas_rutcliente", CAB_RS.getString("empresas_rutcliente"));
						    	request.setAttribute("empresas_nombrenofcliente", CAB_RS.getString("empresas_nombrenofcliente"));
						    	request.setAttribute("empresas_girocliente", CAB_RS.getString("empresas_girocliente"));
						    	request.setAttribute("empresas_idemisor", CAB_RS.getString("empresas_idemisor"));
						    	
						    	request.setAttribute("fac_servim_n_impresiones", CAB_RS.getString("803_n_impresiones"));
						    	request.setAttribute("fac_servim_obs", CAB_RS.getString("803_obs"));
						    	
						    	request.setAttribute("CONT_EMAIL", CAB_RS.getString("CONT_EMAIL"));
						    	request.setAttribute("CONT_NOMBRE", CAB_RS.getString("CONT_NOMBRE"));
						    	request.setAttribute("CONT_TELEFONO", CAB_RS.getString("CONT_TELEFONO"));
						    	request.setAttribute("CONT_TELEFONOC", CAB_RS.getString("CONT_TELEFONOC"));
						    	
						    	request.setAttribute("PERS_NOMBRE", CAB_RS.getString("Usuarios_nombre_com"));
						    	request.setAttribute("DIRE_DIRECCION", CAB_RS.getString("DIRE_DIRECCION"));
						    	request.setAttribute("DIRE_CIUDAD", CAB_RS.getString("DIRE_CIUDAD"));
						    	request.setAttribute("COMU_NOMBRE", CAB_RS.getString("COMU_NOMBRE"));
						    	request.setAttribute("REGI_NOMBRE", CAB_RS.getString("REGI_NOMBRE"));
						    	
						    	request.setAttribute("periodo", CAB_RS.getString("peri_tc_correlativo")+" "+CAB_RS.getString("peri_tc_fdesde").substring(0,10)+" AL "+CAB_RS.getString("peri_tc_fhasta").substring(0,10));
						    	
						    
						    	
						    	request.setAttribute("fac_servim_tcambio", CAB_RS.getString("803_tipo_cambio"));
						    	
						    	request.setAttribute("fec_ref", CAB_RS.getString("fec_ref"));
						    	request.setAttribute("folioref", CAB_RS.getString("folioref"));
						    	request.setAttribute("tipo_dteref", CAB_RS.getString("tipo_dteref"));

						    }
						    CAB_RS.close();
						    
						    String SQL_DET = "SELECT * FROM detalle_803 WHERE 803_id = "+id;
						    //System.out.println(SQL_DET);
						    ResultSet DET_RS = state.executeQuery(SQL_DET);
						    ArrayList<String> prod = new ArrayList<String>();
						    Integer c=0;
						    while(DET_RS.next()){ 
						    	
						    	 int valor= DET_RS.getInt("d803_valor");
					 			   if(tref.equals("3")){
					 				   //sacamos el valor de la tabla de 841 
					 				   String SQL_DETTALLE = "SELECT * FROM detalle_841 WHERE estados_vig_novig_id=1 AND 841_id = "+request.getParameter("d_id")+" AND d841_idref="+DET_RS.getString("d803_id");
					 				    System.out.println(SQL_DETTALLE);
					 				    ResultSet DETTALLE_RS = stateDet.executeQuery(SQL_DETTALLE);
					 				    if(DETTALLE_RS.next()) valor= DETTALLE_RS.getInt("d841_valor");
					 				    
					 			   }
						    	
						    	prod.add(c+"/"+DET_RS.getString("d803_descripcion")+"/"+valor+"/"+DET_RS.getString("d803_id")); c=c+1;
						    }
						    DET_RS.close();	
						    String[] ar_prod = new String[5];
						    for(int x=0; x < prod.size(); x++){ 
						    		ar_prod[x]=prod.get(x);
						    }
						    request.setAttribute("ar_prod", ar_prod);
//						   
//						   
				            statecor.close();
				            conexion.close();
				            
				            RequestDispatcher rd = request.getRequestDispatcher("cnc2_803.jsp");
				            rd.forward(request, response);
					 }
				    
				    if(modref.equals("804")){
						 String GID=request.getParameter("fac_id");
						 String SQL_CAB = "SELECT "
								 
								 + "		`804`.`804_id`, "
						    		+ "		`804`.`804_folio` as fac_servim_folio, "
						    		+ "		IF(`804`.`id_dte` is null , '1','0') as dte , "
						    		+ "		DATE_FORMAT(`804`.`804_fecha`, '%d-%m-%Y') AS FAC_FECHA ,"
						    		+ "		DATE_FORMAT(`804`.`804_fecvencimiento`, '%d-%m-%Y') AS fac_servim_fecvencimiento ,"
						    		+ "		emisor.empresas_nombrenof as empresas_nombrenofemisor  ,"
						    		+ "		emisor.empresas_id as empresas_idemisor  ,"
							    	
						    		+ "		`804`.`804_tipodte` as fac_servim_tipodte  ,"
						    		+ "		cliente.empresas_razonsocial as empresas_razonsocialcliente, "
						    		+ "		cliente.empresas_rut as empresas_rutcliente,"
						    		+ "		cliente.empresas_id as empresas_idcliente,"
						    		+ "		cliente.empresas_nombrenof as empresas_nombrenofcliente,"
						    		+ "		cliente.empresas_giro ,"
						    		+ "		cliente.empresas_giro as empresas_girocliente,"
							    	
						    		+ "		`804`.`804_obs` as fac_servim_obs,"
						    		 
						    		+ "		`804`.`804_condiciones` as fac_servim_condiciones, "
						    		+ "		`804`.`804_tipo_cambio` AS fac_servim_tcambio, "
						    		
						    		+ "		IF(`804`.`tipo_dteref` is null,'NINGUNA',`804`.`tipo_dteref`) as tipo_dteref, "
						    		+ "		IF(`804`.`folioref` is null ,'',`804`.`folioref`) as  folioref, "
						    		+ "		IF(`804`.`fec_ref` is null,'',DATE_FORMAT(`804`.`fec_ref`,'%d-%m-%Y')) as fec_ref, "

						    		
									+ "		`contacto`.`CONT_EMAIL`, "
									+ "		CONCAT_WS(' ',`contacto`.CONT_NOMBRE,`contacto`.CONT_APEP,`contacto`.CONT_APEM) AS CONT_NOMBRE, "
				 					+ "		`contacto`.`CONT_TELEFONO`,  "
									+ "		`contacto`.`CONT_TELEFONOC`,  "
									
									+ "		`direccion`.`DIRE_DIRECCION` , "
									+ "		`direccion`.`DIRE_CIUDAD`,  "
									+ "		`comuna`.`COMU_NOMBRE`,  "
									+ "		`region`.`REGI_NOMBRE`, "
									+ "		`804`.`804_subtotal` , "
									+ "		`804`.`804_total`, "
									+ "		`804`.`804_descuento`, "
						    		+ "		`804`.`804_glosa_descuento`, "
						    		+ "		CONCAT_WS(' ',`usuarios`.`Usuarios_nombre`,`usuarios`.`Usuarios_ape_p`,`usuarios`.`Usuarios_ape_m`) AS Usuarios_nombre_com,"
						    		+ " `804`.`804_neto`,`804`.`804_iva` "					
									+ "	FROM"
						    		+ "		`804`"
						    		+ "	INNER JOIN `empresas` emisor ON `emisor`.`empresas_id` = `804`.`804_empresa_emisora` "
						    		+ " INNER JOIN `empresas` cliente ON cliente.empresas_id = `804`.`clpr_id` "
						    		+ " INNER JOIN `contacto` ON `contacto`.`CONT_ID` = `804`.`CONT_ID` "
						    		+ " INNER JOIN `direccion` ON `direccion`.`DIRE_ID` = `804`.`dire_id` "
						    		+ " INNER JOIN `comuna` ON `comuna`.`COMU_ID` = `direccion`.`COMU_ID` "
						    		+ " INNER JOIN `region` ON `region`.`REGI_ID` = `comuna`.`REGI_ID` "
						    		+ " INNER JOIN `usuarios` ON `usuarios`.`Usuarios_id` = cliente.`responsable_id` "
						    		+ "	WHERE"
						    		+ "		`804`.`804_folio`= "+folio_referencia;
						    System.out.println(SQL_CAB);
						    ResultSet CAB_RS = state.executeQuery(SQL_CAB);
						    String id="";
						    if(CAB_RS.next()){
						    	
						    	id=CAB_RS.getString("804_id");
						    	
						    	String estado_sii="";
						    	if(CAB_RS.getString("dte").equals("0")){estado_sii = "ENVIADA";}
								if(CAB_RS.getString("dte").equals("1")){estado_sii = "PARA ENVIAR";}
								request.setAttribute("empresas_giro",CAB_RS.getString("empresas_giro"));
						    	request.setAttribute("estado_sii", estado_sii);
						    	request.setAttribute("folio", CAB_RS.getString("fac_servim_folio"));
						    	request.setAttribute("FAC_FECHA", CAB_RS.getString("FAC_FECHA"));
						    	request.setAttribute("fac_servim_condiciones", CAB_RS.getString("fac_servim_condiciones"));
						    	request.setAttribute("fac_servim_fecvencimiento", CAB_RS.getString("fac_servim_fecvencimiento"));
						    	request.setAttribute("empresas_nombrenofemisor", CAB_RS.getString("empresas_nombrenofemisor"));
						     	request.setAttribute("empresas_idemisor", CAB_RS.getString("empresas_idemisor"));
								   
						    	request.setAttribute("fac_servim_tipodte", CAB_RS.getString("fac_servim_tipodte"));
						    	request.setAttribute("empresas_razonsocialcliente", CAB_RS.getString("empresas_razonsocialcliente"));
						    	request.setAttribute("empresas_idcliente", CAB_RS.getString("empresas_idcliente"));
						    	request.setAttribute("empresas_rutcliente", CAB_RS.getString("empresas_rutcliente"));
						    	request.setAttribute("empresas_nombrenofcliente", CAB_RS.getString("empresas_nombrenofcliente"));
						    	request.setAttribute("empresas_girocliente", CAB_RS.getString("empresas_girocliente"));
						    	request.setAttribute("empresas_idemisor", CAB_RS.getString("empresas_idemisor"));
						    	
						    	request.setAttribute("fac_servim_obs", CAB_RS.getString("fac_servim_obs"));
						    	
						    	request.setAttribute("CONT_EMAIL", CAB_RS.getString("CONT_EMAIL"));
						    	request.setAttribute("CONT_NOMBRE", CAB_RS.getString("CONT_NOMBRE"));
						    	request.setAttribute("CONT_TELEFONO", CAB_RS.getString("CONT_TELEFONO"));
						    	request.setAttribute("CONT_TELEFONOC", CAB_RS.getString("CONT_TELEFONOC"));
						    	
						    	
						    	request.setAttribute("PERS_NOMBRE", CAB_RS.getString("Usuarios_nombre_com"));
						    	request.setAttribute("DIRE_DIRECCION", CAB_RS.getString("DIRE_DIRECCION"));
						    	request.setAttribute("DIRE_CIUDAD", CAB_RS.getString("DIRE_CIUDAD"));
						    	request.setAttribute("COMU_NOMBRE", CAB_RS.getString("COMU_NOMBRE"));
						    	request.setAttribute("REGI_NOMBRE", CAB_RS.getString("REGI_NOMBRE"));
						    	
						    	request.setAttribute("fac_servim_glosa_desc", CAB_RS.getString("804_glosa_descuento"));
						    	
						    	request.setAttribute("fac_servim_tcambio", CAB_RS.getString("fac_servim_tcambio"));
						    	
						    		
						    	
						    	
						    	request.setAttribute("fec_ref", CAB_RS.getString("fec_ref"));
						    	request.setAttribute("folioref", CAB_RS.getString("folioref"));
						    	request.setAttribute("tipo_dteref", CAB_RS.getString("tipo_dteref"));
						    	
						    }
						    CAB_RS.close();
						    
						    String SQL_DET = "SELECT * FROM detalle_804 WHERE 804_id = "+id+" ORDER BY d804_id";
						    System.out.println(SQL_DET);
						    ResultSet DET_RS = state.executeQuery(SQL_DET);
						    ArrayList<String> prod = new ArrayList<String>();
						    Integer c=0;
						    while(DET_RS.next()){
						    	 int valor= DET_RS.getInt("d804_valor");
					 			   if(tref.equals("3")){
					 				   //sacamos el valor de la tabla de 841 
					 				   String SQL_DETTALLE = "SELECT * FROM detalle_841 WHERE estados_vig_novig_id=1 AND 841_id = "+request.getParameter("d_id")+" AND d841_idref="+DET_RS.getString("d804_id");
					 				    System.out.println(SQL_DETTALLE);
					 				    ResultSet DETTALLE_RS = stateDet.executeQuery(SQL_DETTALLE);
					 				    if(DETTALLE_RS.next()) valor= DETTALLE_RS.getInt("d841_valor");
					 				    
					 			   }
						    	prod.add(c+"/"+DET_RS.getString("d804_descripcion")+"/"+valor+"/"+DET_RS.getString("d804_id")); c=c+1;
						    	}
							DET_RS.close();	
						    String[] ar_prod = new String[5];
						    for(int x=0; x < prod.size(); x++){ 
						    	
						    		ar_prod[x]=prod.get(x);	
						    	
						    	 
						    
						    }
						    request.setAttribute("ar_prod", ar_prod);
//						   
				            statecor.close();
				            conexion.close();
				            RequestDispatcher rd = request.getRequestDispatcher("cnc2_804.jsp");
				            rd.forward(request, response);
					 }
					 
			 
		  
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("Error: "+ex);
		}
		
		
	}

}
