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
import Constantes.Controlador;

/**
 * Servlet implementation class ifac_serv
 */
@WebServlet("/ifac_serv")
public class ifac_serv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ifac_serv() {
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

				//System.out.println("Docs: "+FID);
				//System.out.println("Docs: "+FID);				
				String SQL_DATOS = "SELECT f.fac_comserv_tipodte, DATE_FORMAT(f.fac_comserv_fecfac, '%Y-%m-%d') as FACT_FECHA, "
						+ "DATE_FORMAT(f.fac_comserv_fecfac, '%Y-%m-%d') as FACT_FECHA_EMISION, f.fac_comserv_ref as factura_referencia, "
						+ "f.fac_comserv_obs as factura_obs1, ' ' as factura_obs2, "
						+ "C2.empresas_id as id_cliente, "
						+ "C2.empresas_rut as CLPR_RUT, "
						+ "C2.empresas_razonsocial as CLPR_RAZON_SOCIAL, "
						+ "C2.empresas_giro as CLPR_GIRO, "
						+ "DIRECCION.DIRE_DIRECCION, "
						+ "COMUNA.COMU_NOMBRE, "
						+ "DIRECCION.DIRE_CIUDAD, "
						+ "CONTACTO.CONT_EMAIL, "
			    		+ "fac_comserv_condiciones as FACT_CONDICIONES, "
			    		+ "fac_comserv_ref as fac_servim_ref, "
			    		+ "fac_comserv_tcambio AS fac_servim_tcambio, "
			    		+ "		f.fac_comserv_iva, "
						+ "		f.fac_comserv_ivaporcentaje, "
						+ "		f.fac_comserv_neto, "
						+ "		f.fac_comserv_total, "
						+ "		f.fac_comserv_totalfinal, "
						+ "		f.fac_comserv_descuento"
						
						
						+ " FROM factura_compra_serv f"
						+ " LEFT JOIN empresas as C1 ON f.fac_comserv_emisor = C1.empresas_id"
						+ " LEFT JOIN empresas as C2 ON f.empresas_id = C2.empresas_id"
						+ " LEFT JOIN DIRECCION ON f.DIRE_ID = DIRECCION.DIRE_ID"
						+ " LEFT JOIN COMUNA ON DIRECCION.COMU_ID = COMUNA.COMU_ID"
						+ " LEFT JOIN REGION ON COMUNA.REGI_ID = REGION.REGI_ID"
						+ " LEFT JOIN CONTACTO ON f.CONT_ID = CONTACTO.CONT_ID"
						+ " WHERE f.fac_comserv_id ="+FID;
				//System.out.println(SQL_DATOS);
				RS_DATOS =  state.executeQuery(SQL_DATOS);
				
				RS_DATOS.next();
				String tipodte=RS_DATOS.getString("fac_comserv_tipodte");
				String fecha_fac = RS_DATOS.getString("FACT_FECHA");
				String REF = RS_DATOS.getString("factura_referencia").replace(";", "");
				String condiciones = RS_DATOS.getString("FACT_CONDICIONES").replace(";", "");
				String OBS1 = RS_DATOS.getString("factura_obs1").replace(";", "");
				String OBS2 = RS_DATOS.getString("factura_obs2").replace(";", "");
				
		 		Float N=0.0f, I=0.0f, T=0.0f, D=0.0f;
		 		N=RS_DATOS.getFloat("fac_comserv_total");
	 			I=RS_DATOS.getFloat("fac_comserv_iva");
	 			T=RS_DATOS.getFloat("fac_comserv_totalfinal");
	 			D=RS_DATOS.getFloat("fac_comserv_descuento");
	 			String neto = (N.intValue())+"";
	 			String iva = (I.intValue())+"";
	 			String total = (T.intValue())+"";
	 			String desc = (D.intValue())+"";
				
				Integer c=1,exento=0;
				if(tipodte.equals("33")){
					//neto=N.intValue();
					exento=0;
				}
				if(tipodte.equals("34")){
					neto="0";
					exento=N.intValue();
				}
				//TipoDTE;Folio;FechaEmision;TipoDespacho;IndTraslado;RUTCliente;RSClienente;GiroCliente;DirCliente;ComCliente;CiuCliente;Email;
			    String encabezado="->Encabezado<-\r\n"+
			    		RS_DATOS.getString("fac_comserv_tipodte")+";0;"+RS_DATOS.getString("FACT_FECHA_EMISION")+";0;0;"+
			    		RS_DATOS.getString("CLPR_RUT")+";"+RS_DATOS.getString("CLPR_RAZON_SOCIAL").replace(";", "")+";"+RS_DATOS.getString("CLPR_GIRO").replace(";", "")+";"+
			    		RS_DATOS.getString("DIRE_DIRECCION").replace(";", "")+";"+RS_DATOS.getString("COMU_NOMBRE")+";"+RS_DATOS.getString("DIRE_CIUDAD")+";"+
			    		RS_DATOS.getString("CONT_EMAIL")+";\r\n";
			   
			    String SQL_INSERTe = "INSERT INTO dte_encabezado (TipoDTE, Folio,FechaEmision, TipoDespacho, IndTraslado, "
			    		+ "RUTCliente, RSClienente, GiroCliente, "
			    		+ "DirCliente, ComCliente, CiuCliente, Email,cliente_id) "
			    		+ "VALUES ('"+RS_DATOS.getString("fac_comserv_tipodte")+"','0','"+RS_DATOS.getString("FACT_FECHA_EMISION")+"','0','0'"
			    		+ ",'"+RS_DATOS.getString("CLPR_RUT")+"','"+RS_DATOS.getString("CLPR_RAZON_SOCIAL")+"','"+RS_DATOS.getString("CLPR_GIRO")+"'"
			    		+ ",'"+RS_DATOS.getString("DIRE_DIRECCION")+"','"+RS_DATOS.getString("COMU_NOMBRE")+"','"+RS_DATOS.getString("DIRE_CIUDAD")+"'"
			    		+ ",'"+RS_DATOS.getString("CONT_EMAIL")+"','"+RS_DATOS.getString("id_cliente")+"')";
			    System.out.println(SQL_INSERTe);
			    stategrabar.executeUpdate(SQL_INSERTe,Statement.RETURN_GENERATED_KEYS);
			    RS_DATOS.close();
			    
			    ResultSet generatedKeys = null;
	    		generatedKeys = stategrabar.getGeneratedKeys();
	    		String id_usu_last="";
	    		if (generatedKeys.next()) {
	    			id_usu_last=generatedKeys.getString(1);
	    		}
			    //stategrabar.close();
			    
			    
			    String SQL_UPFAC = "UPDATE factura_compra_serv SET id_dte = "+id_usu_last+" WHERE fac_comserv_id = "+FID;
			    System.out.println("SQL Instert: "+SQL_UPFAC);
			    stategrabar.executeUpdate(SQL_UPFAC);

				String SQL_DET = "SELECT "
						+ " ' ' as ALT_ID,"
						+ " d.dfcs_descripcion as PROD_DESC_CORTO,"
						+ " ' ' as PROD_PN_TLI_CODFAB, "
						+ " d.dfcs_descripcion as PROD_DESC_LARGO,"
						+ " d.dfcs_valor as DETI_UNITARIO"
						+ " FROM detail_fac_compraserv d "
						+ " WHERE d.fac_comserv_id= "+FID;
				//System.out.println(SQL_DATOS);
				RS_DET =  state.executeQuery(SQL_DET);
	    
				String detalle="->Detalle<-\r\n";
				while(RS_DET.next()){
					detalle+=c+";"+RS_DET.getString("ALT_ID")+";"+RS_DET.getString("PROD_DESC_CORTO")+";1;"
		            		+ RS_DET.getString("DETI_UNITARIO")+";0;0;0;0;"+RS_DET.getString("DETI_UNITARIO")+";"
		            		+ RS_DET.getInt("DETI_UNITARIO")+";"+RS_DET.getString("PROD_PN_TLI_CODFAB").replace(";", "")+";UN;"
		            		+ RS_DET.getString("PROD_DESC_LARGO").replace(";", "")+"\r\n";
					
					String SQL_INSERTd = "INSERT INTO dte_detalle (id_enc, NroLinea, Codigo, Descripcion, Cantidad, "
							+ "Precio, PorcDescto, ValorDescto, PorcRecgo, ValorRecgo, ValorExento, Valor, "
							+ "TipoCodigo, UniMedida, DescLarga) "
				    		+ "VALUES ("+id_usu_last+","+c+",'"+RS_DET.getString("ALT_ID")+"','"+RS_DET.getString("PROD_DESC_CORTO")+"',1,"
				    		+ ""+RS_DET.getString("DETI_UNITARIO")+",0,0,0,0,'"+RS_DET.getString("DETI_UNITARIO")+"',"+RS_DET.getString("DETI_UNITARIO")+","
				    		+ "'"+RS_DET.getString("PROD_PN_TLI_CODFAB")+"','UN','"+RS_DET.getString("PROD_DESC_LARGO")+"')";
				    //NroLinea;Codigo;Descripcion;Cantidad;Precio;PorcDescto;ValorDescto;PorcRecgo;ValorRecgo;ValorExento;Valor;TipoCodigo;UniMedida;DescLarga;
				    System.out.println(SQL_INSERTd);
				    stategrabar.executeUpdate(SQL_INSERTd);
					c=c+1;
				}
				RS_DET.close();
				
				String totales="->Totales<-\r\n"+"0;"+desc+";0;0;"+neto+";"+exento+";19;"+iva+";"+total+";\r\n";
				//PorcDesctoTot;ValorDesctoTot;PorcRecgoTot;ValorRecgoTot;Neto;Exento;TasaIVA;IVA;Total;
			    String SQL_INSERTt = "INSERT INTO dte_totales (id_enc, PorcDesctoTot, ValorDesctoTot, PorcRecgoTot, ValorRecgoTot, "
			    		+ "Neto, Exento, TasaIVA, IVA, Total)"
			    		+ " VALUES ("+id_usu_last+",0,"+desc+",0,0,"+neto+","+exento+",19,"+iva+","+total+")";
			    System.out.println("SQL Instert: "+SQL_INSERTt);
			    stategrabar.executeUpdate(SQL_INSERTt);
			    
			    String referencia="->Referencia<-\r\n"+"1;812;"+FID+";"+fecha_fac+";0;"+REF+";\r\n";
			    //NroLineaRef;TipoDTERef;FolioRef;FechaRef;CodigoRef;RazonRef;
			    String SQL_INSERTr = "INSERT INTO dte_referencia (id_enc, NroLineaRef, TipoDTERef, FolioRef, FechaRef, CodigoRef, RazonRef) "
			    		+ "VALUES ("+id_usu_last+",1,'812',"+FID+",'"+fecha_fac+"','0','"+REF+"')";
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
		
			Statement state = null;
			Statement statecor = null;	
			try {
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		statecor = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		String FID = request.getParameter("d_id");
	    		String TDTE = request.getParameter("tdte");

			    String SQL_CAB = "SELECT "
			    		+ "		`factura_compra_serv`.`fac_comserv_folio` as fac_servim_folio, "
			    		+ "		IF(`factura_compra_serv`.`id_dte` is null , '1','0') as dte , "
			    		+ "		DATE_FORMAT(`factura_compra_serv`.`fac_comserv_fecfac`, '%d-%m-%Y') AS FAC_FECHA ,"
			    		+ "		DATE_FORMAT(`factura_compra_serv`.`fac_comserv_fecvencimiento`, '%d-%m-%Y') AS fac_servim_fecvencimiento ,"
			    		+ "		emisor.empresas_nombrenof as empresas_nombrenofemisor  ,"
			    		+ "		`factura_compra_serv`.`fac_comserv_tipodte` as fac_servim_tipodte  ,"
			    		+ "		cliente.empresas_razonsocial as empresas_razonsocialcliente, "
			    		+ "		cliente.empresas_rut as empresas_rutcliente,"
			    		+ "		cliente.empresas_id as empresas_idcliente,"
			    		+ "		cliente.empresas_nombrenof as empresas_nombrenofcliente,"
			    		+ "		`factura_compra_serv`.`fac_comserv_obs` as fac_servim_obs,"
			    		 
			    		+ "		`factura_compra_serv`.`fac_comserv_condiciones`, "
			    		+ "		`factura_compra_serv`.`fac_comserv_ref` as fac_servim_ref, "
			    		+ "		`factura_compra_serv`.`fac_comserv_tcambio` AS fac_servim_tcambio, "
						
						+ "		`contacto`.`CONT_EMAIL`, "
						+ "		CONCAT_WS(' ', cont_nombre, CONT_APEP, CONT_APEM) as CONT_NOMBRE, "
						+ "		`contacto`.`CONT_TELEFONO`,  "
						+ "		`contacto`.`CONT_TELEFONOC`,  "
						
						+ "		`direccion`.`DIRE_DIRECCION` , "
						+ "		`direccion`.`DIRE_CIUDAD`,  "
						+ "		`comuna`.`COMU_NOMBRE`,  "
						+ "		`region`.`REGI_NOMBRE`, "
						+ "		`factura_compra_serv`.`fac_comserv_iva`, "
						+ "		`factura_compra_serv`.`fac_comserv_ivaporcentaje`, "
						+ "		`factura_compra_serv`.`fac_comserv_neto`, "
						+ "		`factura_compra_serv`.`fac_comserv_total`, "
						+ "		`factura_compra_serv`.`fac_comserv_totalfinal`, "
						+ "		`factura_compra_serv`.`fac_comserv_descuento`, "
			    		+ "		`factura_compra_serv`.`fac_comserv_glosa_desc`, fac_comserv_estado, "
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
			    		+ "	WHERE `factura_compra_serv`.`fac_comserv_id`= "+FID;
			    System.out.println(SQL_CAB);
			    ResultSet CAB_RS = state.executeQuery(SQL_CAB);
			    if(CAB_RS.next()){
			    	String estado_sii="";
			    	if(CAB_RS.getString("dte").equals("0")){estado_sii = "ENVIADA";}
					if(CAB_RS.getString("dte").equals("1")){estado_sii = "PARA ENVIAR";}
			    	request.setAttribute("estado_sii", estado_sii);
			    	request.setAttribute("folio", CAB_RS.getString("fac_servim_folio"));
			    	request.setAttribute("FACT_ID", FID);
			    	request.setAttribute("FAC_FECHA", CAB_RS.getString("FAC_FECHA"));
			    	request.setAttribute("FAC_ESTADO", CAB_RS.getString("fac_comserv_estado"));
			    	request.setAttribute("fac_comserv_condiciones", CAB_RS.getString("fac_comserv_condiciones"));
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
			    	request.setAttribute("CONT_TELEFONOC", CAB_RS.getString("CONT_TELEFONOC"));
			    	
			    	
			    	request.setAttribute("PERS_NOMBRE", CAB_RS.getString("Usuarios_nombre_com"));
			    	request.setAttribute("DIRE_DIRECCION", CAB_RS.getString("DIRE_DIRECCION"));
			    	request.setAttribute("DIRE_CIUDAD", CAB_RS.getString("DIRE_CIUDAD"));
			    	request.setAttribute("COMU_NOMBRE", CAB_RS.getString("COMU_NOMBRE"));
			    	request.setAttribute("REGI_NOMBRE", CAB_RS.getString("REGI_NOMBRE"));
			    	request.setAttribute("fac_comserv_neto", CAB_RS.getString("fac_comserv_neto"));
			    	request.setAttribute("fac_comserv_total", CAB_RS.getString("fac_comserv_total"));
			    	request.setAttribute("fac_comserv_ivap", CAB_RS.getString("fac_comserv_ivaporcentaje"));
			    	request.setAttribute("fac_comserv_iva", CAB_RS.getString("fac_comserv_iva"));
			    	request.setAttribute("fac_comserv_totalfinal", CAB_RS.getString("fac_comserv_totalfinal"));
			    	
			    	request.setAttribute("fac_servim_glosa_desc", CAB_RS.getString("fac_comserv_glosa_desc"));
			    	request.setAttribute("DESC", CAB_RS.getString("fac_comserv_descuento"));
			    	
			    	request.setAttribute("fac_servim_tcambio", CAB_RS.getString("fac_servim_tcambio"));
			    	
			    }
			    CAB_RS.close();
			    
			    String SQL_DET = "SELECT * FROM detail_fac_compraserv WHERE fac_comserv_id = "+FID;
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
			    request.setAttribute("tdte", TDTE);
			}catch(Exception ex){
			    out.println("ERROR: "+ex);
			    ex.printStackTrace();
			}
		//fin grabar
			
		RequestDispatcher rd = request.getRequestDispatcher("ifac_serv.jsp");
        rd.forward(request, response);
		
		}
		
	}

}
