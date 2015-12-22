import java.io.FileOutputStream;
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
 * Servlet implementation class ifac_act
 */
@WebServlet("/ifac_act")
public class ifac_act extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ifac_act() {
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
		String id_iusuario="";
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName()+"-"+cookie.getValue());
		    	if(cookie.getName().equals("Usuarios_id") && cookie.getValue()!=null) id_iusuario=cookie.getValue();
		    }
		}

		String Usuarios_nombre="";
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName());
		    	if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}		
		request.setAttribute("usuname", Usuarios_nombre);
		request.setAttribute("usuname", Usuarios_nombre);
		//grabar

		if(request.getParameter("grabar") != null){
			Statement stategrabar = null;	
			ResultSet RS_DATOS = null;
			ResultSet RS_DET = null;
			ResultSet FAC_RS = null;
			
			try {
				Statement state = null;
				String FID=request.getParameter("fact_id");
				String TDTE=request.getParameter("tdte");
						
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
			    Connection conexion=(Connection) DriverManager.getConnection
			    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
			    state = (Statement) ((java.sql.Connection) conexion).createStatement();
				stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
				
	 			String SQL_FAC = "SELECT DATE_FORMAT(f.fac_comactivo_fecfac, '%Y-%m-%d') as FACT_FECHA_EMISION, "
	 					+ " cliente.empresas_razonsocial as CLPR_RAZON_SOCIAL, "
			    		+ "	cliente.empresas_rut as CLPR_RUT, cliente.empresas_giro as CLPR_GIRO, "
			    		+ "	cliente.empresas_id as empresas_idcliente,"
			    		+ " `direccion`.`DIRE_DIRECCION`, `comuna`.`COMU_NOMBRE`, `direccion`.`DIRE_CIUDAD`, "
			    		+ " `contacto`.`CONT_EMAIL`, f.fac_comactivo_total, f.fac_comactivo_totalfinal, f.fac_comactivo_iva,"
			    		+ " f.fac_comactivo_tipodte, f.fac_comactivo_ref, f.fac_comactivo_condiciones, f.fac_comactivo_obs, f.fac_comactivo_obs1 "
	 					+ " FROM factura_compra_activo f"
			    		+ " INNER JOIN `empresas` cliente ON cliente.empresas_id = f.`empresas_id` "
			    		+ " LEFT JOIN `contacto` ON `contacto`.`CONT_ID` = f.`CONT_ID` "
			    		+ " INNER JOIN `direccion` ON `direccion`.`DIRE_ID` = f.`dire_id` "
			    		+ " INNER JOIN `comuna` ON `comuna`.`COMU_ID` = `direccion`.`COMU_ID` "
			    		+ " INNER JOIN `region` ON `region`.`REGI_ID` = `comuna`.`REGI_ID` "
			    		+ " INNER JOIN `usuarios` ON `usuarios`.`Usuarios_id` = cliente.`responsable_id` "
	 					+ "WHERE f.fac_comactivo_id='"+FID+"'"; 
	    		System.out.println(SQL_FAC);
	 		    FAC_RS =  state.executeQuery(SQL_FAC);
	            FAC_RS.next();
	            String tipodte=FAC_RS.getString("fac_comactivo_tipodte");
				String fecha_fac = FAC_RS.getString("FACT_FECHA_EMISION");
				String REF = FAC_RS.getString("fac_comactivo_ref").replace(";", "");
				String condiciones = FAC_RS.getString("fac_comactivo_condiciones").replace(";", "");
				String OBS1 = FAC_RS.getString("fac_comactivo_obs").replace(";", "");
				String OBS2 = FAC_RS.getString("fac_comactivo_obs1").replace(";", "");	            
	            
		 		Float N=0.0f, I=0.0f, T=0.0f;
		 		N=FAC_RS.getFloat("fac_comactivo_total");
	 			I=FAC_RS.getFloat("fac_comactivo_iva");
	 			T=FAC_RS.getFloat("fac_comactivo_totalfinal");
	 			String n2 = (N.intValue())+"";
	 			String iva = (I.intValue())+"";
	 			String total = (T.intValue())+"";
				
				Integer c=1,neto=0,exento=0;
				if(tipodte.equals("33")){
					neto=N.intValue();
					exento=0;
				}
				if(tipodte.equals("34")){
					neto=0;
					exento=N.intValue();
				}
				//TipoDTE;Folio;FechaEmision;TipoDespacho;IndTraslado;RUTCliente;RSClienente;GiroCliente;DirCliente;ComCliente;CiuCliente;Email;
			    String encabezado="->Encabezado<-\r\n"+
			    		FAC_RS.getString("fac_comactivo_tipodte")+";0;"+FAC_RS.getString("FACT_FECHA_EMISION")+";0;0;"+
			    		FAC_RS.getString("CLPR_RUT")+";"+FAC_RS.getString("CLPR_RAZON_SOCIAL").replace(";", "")+";"+FAC_RS.getString("CLPR_GIRO").replace(";", "")+";"+
			    		FAC_RS.getString("DIRE_DIRECCION").replace(";", "")+";"+FAC_RS.getString("COMU_NOMBRE")+";"+FAC_RS.getString("DIRE_CIUDAD")+";"+
			    		FAC_RS.getString("CONT_EMAIL")+";\r\n";
			    String SQL_INSERTe = "INSERT INTO dte_encabezado (TipoDTE, Folio,FechaEmision, TipoDespacho, IndTraslado, "
			    		+ "RUTCliente, RSClienente, GiroCliente, "
			    		+ "DirCliente, ComCliente, CiuCliente, Email) "
			    		+ "VALUES ('"+FAC_RS.getString("fac_comactivo_tipodte")+"','0','"+FAC_RS.getString("FACT_FECHA_EMISION")+"','0','0'"
			    		+ ",'"+FAC_RS.getString("CLPR_RUT")+"','"+FAC_RS.getString("CLPR_RAZON_SOCIAL")+"','"+FAC_RS.getString("CLPR_GIRO")+"'"
			    		+ ",'"+FAC_RS.getString("DIRE_DIRECCION")+"','"+FAC_RS.getString("COMU_NOMBRE")+"','"+FAC_RS.getString("DIRE_CIUDAD")+"'"
			    		+ ",'"+FAC_RS.getString("CONT_EMAIL")+"')";
			    //System.out.println("SQL Instert: "+SQL_INSERTe);
			    stategrabar.executeUpdate(SQL_INSERTe,Statement.RETURN_GENERATED_KEYS);
			    FAC_RS.close();
			    
			    ResultSet generatedKeys = null;
	    		generatedKeys = stategrabar.getGeneratedKeys();
	    		String id_usu_last="";
	    		if (generatedKeys.next()) {
	    			id_usu_last=generatedKeys.getString(1);
	    		}
			    //stategrabar.close();
			    
			    String SQL_UPFAC = "UPDATE factura_compra_activo SET id_dte = "+id_usu_last+" WHERE fac_comactivo_id = "+FID;
			    System.out.println("SQL Instert: "+SQL_UPFAC);
			    stategrabar.executeUpdate(SQL_UPFAC);

			    String SQL_DET = "SELECT "
			    		+ " ' ' as ALT_ID, "
			    		+ " dfca_descripcion as PROD_DESC_CORTO, "
			    		+ " ' ' as PROD_PN_TLI_CODFAB, "
			    		+ " ' ' as DETIOC_CANT, "
		 		   		+ " dfca_valor as DETI_UNITARIO, "
		 		   		+ " dfca_descripcion as PROD_DESC_LARGO"
		 		   		+ " FROM detail_fac_comprasactivo "
		 		   		+ "WHERE fac_comactivo_id = "+FID;
			    //System.out.println(SQL_DET);
			    ResultSet DET_RS = state.executeQuery(SQL_DET);
			    
				String detalle="->Detalle<-\r\n";
				while(DET_RS.next()){
					detalle+=c+";"+DET_RS.getString("ALT_ID")+";"+DET_RS.getString("PROD_DESC_CORTO")+";1;"
		            		+ DET_RS.getString("DETI_UNITARIO")+";0;0;0;0;"+DET_RS.getString("DETI_UNITARIO")+";"
		            		+ DET_RS.getInt("DETI_UNITARIO")+";"+DET_RS.getString("PROD_PN_TLI_CODFAB").replace(";", "")+";UN;"
		            		+ DET_RS.getString("PROD_DESC_LARGO").replace(";", "")+"\r\n";

					String SQL_INSERTd = "INSERT INTO dte_detalle (id_enc, NroLinea, Codigo, Descripcion, Cantidad, "
							+ "Precio, PorcDescto, ValorDescto, PorcRecgo, ValorRecgo, ValorExento, Valor, "
							+ "TipoCodigo, UniMedida, DescLarga) "
				    		+ "VALUES ("+id_usu_last+","+c+",'"+DET_RS.getString("ALT_ID")+"','"+DET_RS.getString("PROD_DESC_CORTO")+"',1,"
				    		+ ""+DET_RS.getString("DETI_UNITARIO")+",0,0,0,0,'"+DET_RS.getString("DETI_UNITARIO")+"',"+DET_RS.getString("DETI_UNITARIO")+","
				    		+ "'"+DET_RS.getString("PROD_PN_TLI_CODFAB")+"','UN','"+DET_RS.getString("PROD_DESC_LARGO")+"')";
				    //NroLinea;Codigo;Descripcion;Cantidad;Precio;PorcDescto;ValorDescto;PorcRecgo;ValorRecgo;ValorExento;Valor;TipoCodigo;UniMedida;DescLarga;
				    System.out.println(SQL_INSERTd);
				    stategrabar.executeUpdate(SQL_INSERTd);
					c=c+1;
				}
				DET_RS.close();
				
				String totales="->Totales<-\r\n"+"0;0;0;0;"+neto+";"+exento+";19;"+iva+";"+total+";\r\n";
				//PorcDesctoTot;ValorDesctoTot;PorcRecgoTot;ValorRecgoTot;Neto;Exento;TasaIVA;IVA;Total;
			    String SQL_INSERTt = "INSERT INTO dte_totales (id_enc, PorcDesctoTot, ValorDesctoTot, PorcRecgoTot, ValorRecgoTot, "
			    		+ "Neto, Exento, TasaIVA, IVA, Total)"
			    		+ " VALUES ("+id_usu_last+",0,0,0,0,"+neto+","+exento+",19,"+iva+","+total+")";
			    System.out.println("SQL Instert: "+SQL_INSERTt);
			    stategrabar.executeUpdate(SQL_INSERTt);
			    
			    String referencia="->Referencia<-\r\n"+"1;811;"+FID+";"+fecha_fac+";0;"+REF+";\r\n";
			    //NroLineaRef;TipoDTERef;FolioRef;FechaRef;CodigoRef;RazonRef;
			    String SQL_INSERTr = "INSERT INTO dte_referencia (id_enc, NroLineaRef, TipoDTERef, FolioRef, FechaRef, CodigoRef, RazonRef) "
			    		+ "VALUES ("+id_usu_last+",1,'811',"+FID+",'"+fecha_fac+"','0','"+REF+"')";
			    System.out.println("SQL Instert: "+SQL_INSERTr);
			    stategrabar.executeUpdate(SQL_INSERTr);
			    
			    String obs="->Observacion<-\r\n"+condiciones+";"+OBS1+";"+OBS2+";";
			    //Linea1;Linea2;Linea3;
			    String SQL_INSERTo = "INSERT INTO dte_observacion (id_enc, Linea1, Linea2, Linea3) "
			    		+ "VALUES ("+id_usu_last+",'"+condiciones+"','"+OBS1+"','"+OBS2+"')";
			    System.out.println("SQL Instert: "+SQL_INSERTo);
			    stategrabar.executeUpdate(SQL_INSERTo);
			    stategrabar.close();
				
	        	String data = encabezado+""+totales+""+detalle+""+referencia+""+obs+"\r\n";
	        	
				byte dataToWrite[] =data.getBytes();
				FileOutputStream outFile = new FileOutputStream("/Users/eugenioallendes/Documents/workspace/800/"+FID+".txt",true);
				outFile.write(dataToWrite);
				outFile.close();
				conexion.close();
					    
				RequestDispatcher rd_up = request.getRequestDispatcher("menuguias?Exito=OK");
				rd_up.forward(request, response);
			        	
			}catch(Exception ex){
				ex.printStackTrace();
				out.println("Unable to connect to database "+ex);
			}
		}else{
		
			try {
				Statement state = null;
				
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		String GID = request.getParameter("d_id");
	    		request.setAttribute("FACT_ID", GID);
	    		String TDTE = request.getParameter("tdte");

	    		//DATOS DE LA TABLA FACTURA
	    		String SQL_CAB = "SELECT factura_compra_activo.oc_id, "
			    		+ "		`factura_compra_activo`.`fac_comactivo_folio` as fac_servim_folio, "
			    		+ "		IF(`factura_compra_activo`.`id_dte` is null , '1','0') as dte , "
			    		+ "		DATE_FORMAT(`factura_compra_activo`.`fac_comactivo_fecfac`, '%d-%m-%Y') AS FAC_FECHA ,"
			    		+ "factura_compra_activo.FACT_EMPRESA_EMISORA, "
			    		+ "		DATE_FORMAT(`factura_compra_activo`.`fac_comactivo_fecvencimiento`, '%d-%m-%Y') AS fac_servim_fecvencimiento ,"
			    		+ "		emisor.empresas_nombrenof as empresas_nombrenofemisor  ,"
			    		+ "		`factura_compra_activo`.`fac_comactivo_tipodte` as fac_servim_tipodte  ,"
			    		+ "		cliente.empresas_razonsocial as empresas_razonsocialcliente, "
			    		+ "		cliente.empresas_rut as empresas_rutcliente,"
			    		+ "		cliente.empresas_id as empresas_idcliente,"
			    		+ "		cliente.empresas_nombrenof as empresas_nombrenofcliente,"
			    		+ "		`factura_compra_activo`.`fac_comactivo_obs` as fac_servim_obs,"
			    		+ "		`factura_compra_activo`.`fac_comactivo_obs1` as fac_servim_obs1,"
			    		 
			    		+ "		`factura_compra_activo`.`fac_comactivo_condiciones` as fac_servim_condiciones, "
			    		+ "		`factura_compra_activo`.`fac_comactivo_ref` as fac_servim_ref, "
			    		+ "		`factura_compra_activo`.`fac_comactivo_tipo_cambio` AS fac_servim_tcambio, "
						
			    		
						+ "		`contacto`.`CONT_EMAIL`, "
						+ "		`contacto`.`CONT_NOMBRE`, "
						+ "		`contacto`.`CONT_TELEFONO`,  "
						+ "		`contacto`.`CONT_TELEFONOC`,  "
						
						+ "		`direccion`.`DIRE_DIRECCION` , "
						+ "		`direccion`.`DIRE_CIUDAD`,  "
						+ "		`comuna`.`COMU_NOMBRE`,  "
						+ "		`region`.`REGI_NOMBRE`, "
						+ "		`factura_compra_activo`.`fac_comactivo_total` as fac_servim_total , "
						+ "		`factura_compra_activo`.`fac_comactivo_totalfinal`, "
						+ "		`factura_compra_activo`.`fac_comactivo_descuento`, "
			    		+ "		`factura_compra_activo`.`fac_comactivo_glosa_desc`, "
			    		+ "		CONCAT_WS(' ',`usuarios`.`Usuarios_nombre`,`usuarios`.`Usuarios_ape_p`,`usuarios`.`Usuarios_ape_m`) AS Usuarios_nombre_com,"
			    		+ " factura_compra_activo.fac_comactivo_neto, factura_compra_activo.fac_comactivo_iva "					
						+ "	FROM"
			    		+ "		`factura_compra_activo`"
			    		+ "	INNER JOIN `empresas` emisor ON `emisor`.`empresas_id` = `factura_compra_activo`.`fac_comactivo_emisor` "
			    		+ " INNER JOIN `empresas` cliente ON cliente.empresas_id = `factura_compra_activo`.`empresas_id` "
			    		+ " LEFT JOIN `contacto` ON `contacto`.`CONT_ID` = `factura_compra_activo`.`CONT_ID` "
			    		+ " INNER JOIN `direccion` ON `direccion`.`DIRE_ID` = `factura_compra_activo`.`dire_id` "
			    		+ " INNER JOIN `comuna` ON `comuna`.`COMU_ID` = `direccion`.`COMU_ID` "
			    		+ " INNER JOIN `region` ON `region`.`REGI_ID` = `comuna`.`REGI_ID` "
			    		+ " INNER JOIN `usuarios` ON `usuarios`.`Usuarios_id` = cliente.`responsable_id` "
			    		+ "	WHERE"
			    		+ "		`factura_compra_activo`.`fac_comactivo_id`= "+GID;
			    //System.out.println(SQL_CAB);
			    ResultSet CAB_RS = state.executeQuery(SQL_CAB);
			    int ocid=0;
			    if(CAB_RS.next()){
			    	ocid = CAB_RS.getInt("oc_id");
			    	request.setAttribute("oc_id", CAB_RS.getString("oc_id"));
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
			    	request.setAttribute("fac_servim_obs1", CAB_RS.getString("fac_servim_obs1"));
			    	request.setAttribute("fac_servim_ref", CAB_RS.getString("fac_servim_ref"));
			    	
			    	request.setAttribute("CONT_EMAIL", CAB_RS.getString("CONT_EMAIL"));
			    	request.setAttribute("CONT_NOMBRE", CAB_RS.getString("CONT_NOMBRE"));
			    	request.setAttribute("CONT_TELEFONO", CAB_RS.getString("CONT_TELEFONO"));
			    	request.setAttribute("CONT_TELEFONOC", CAB_RS.getString("CONT_TELEFONOC"));
			    	
			    	
			    	request.setAttribute("PERS_NOMBRE", CAB_RS.getString("Usuarios_nombre_com"));
			    	request.setAttribute("DIRE_DIRECCION", CAB_RS.getString("DIRE_DIRECCION"));
			    	request.setAttribute("DIRE_CIUDAD", CAB_RS.getString("DIRE_CIUDAD"));
			    	request.setAttribute("COMU_NOMBRE", CAB_RS.getString("COMU_NOMBRE"));
			    	request.setAttribute("REGI_NOMBRE", CAB_RS.getString("REGI_NOMBRE"));
			    	request.setAttribute("fac_servim_total", CAB_RS.getString("fac_servim_total"));
			    	
			    	request.setAttribute("fac_servim_glosa_desc", CAB_RS.getString("fac_comactivo_glosa_desc"));
			    	request.setAttribute("DESC", CAB_RS.getString("fac_comactivo_descuento"));
			    	
			    	request.setAttribute("fac_servim_tcambio", CAB_RS.getString("fac_servim_tcambio"));
			    	request.setAttribute("fac_comactivo_neto", CAB_RS.getString("fac_comactivo_neto"));
			    	request.setAttribute("fac_comactivo_iva", CAB_RS.getString("fac_comactivo_iva"));
			    	request.setAttribute("fac_comactivo_totalfinal", CAB_RS.getString("fac_comactivo_totalfinal"));
			    	
			    }
			    CAB_RS.close();

			    ConBirt birtBDF= new ConBirt();
	            ResultSet QueryBirtF= birtBDF.Consulta("SELECT "
	            		+ "o.OC_ESTADO, "
	            		+ "CONVERT(varchar, o.OC_FECHA_EMISION, 105) as OC_FECHA_EMISION, "
	            		+ "u.USU_INICIAL "
	            		+ "FROM ORDEN_COMPRA o "
	            		+ "INNER JOIN USUARIO u ON o.OC_USU_CREACION = u.USU_ID "
	            		+ "WHERE o.OC_ID = "+ocid);
	            QueryBirtF.next();	           
		 		request.setAttribute("FACT_ESTADO",QueryBirtF.getString("OC_ESTADO"));
		 		request.setAttribute("FACT_FECHA_EMISION",QueryBirtF.getString("OC_FECHA_EMISION"));
		 		request.setAttribute("USU_INICIAL",QueryBirtF.getString("USU_INICIAL"));
	            QueryBirtF.close();			    
			    
			    
			    String SQL_DET = "SELECT * FROM detail_fac_comprasactivo WHERE fac_comactivo_id = "+GID;
			    //System.out.println(SQL_DET);
			    ResultSet DET_RS = state.executeQuery(SQL_DET);
			    ArrayList<String> prod = new ArrayList<String>();
			    Integer c=1;
			    while(DET_RS.next()){ prod.add(c+"/"+DET_RS.getString("dfca_descripcion").replace("/", " ")+"/"+DET_RS.getString("dfca_valor")); c=c+1;}
			    DET_RS.close();	
			    String[] ar_prod = new String[prod.size()];
			    for(int x=0; x < prod.size(); x++){ 
			    	//System.out.println(prod.get(x));
			    	ar_prod[x]=prod.get(x);	
			    }
			    request.setAttribute("ar_prod", ar_prod);
			}catch(Exception ex){
			    out.println("ERROR: "+ex);
			    ex.printStackTrace();
			}
		//fin grabar
			
		RequestDispatcher rd = request.getRequestDispatcher("ifac_act.jsp");
        rd.forward(request, response);
		
		}
		
	}

}
