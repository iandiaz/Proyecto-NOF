
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
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
 * Servlet implementation class ifac_servotros
 */
@WebServlet("/ifac_servotros")
public class ifac_servotros extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ifac_servotros() {
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
			try {
				Statement state = null;
				String FID=request.getParameter("fact_id");
				String TDTE=request.getParameter("tdte");
						
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
			    Connection conexion=(Connection) DriverManager.getConnection
			    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
			    state = (Statement) ((java.sql.Connection) conexion).createStatement();
			    Statement state2 = (Statement) ((java.sql.Connection) conexion).createStatement();
				
			    stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
			    Statement stateFolio=(Statement) ((java.sql.Connection) conexion).createStatement();
				
				//System.out.println("Docs: "+FID);
				
				String SQL_DATOS = "SELECT f.804_tipodte as fac_servotros_tipodte, DATE_FORMAT(f.804_fecha, '%Y-%m-%d') as FACT_FECHA, "
						+ "DATE_FORMAT(f.804_fecha, '%Y-%m-%d') as FACT_FECHA_EMISION,  "
						+ "f.804_obs, "
						
						+ "C2.empresas_id as id_cliente, "

						+ "C2.empresas_rut as CLPR_RUT, "
						+ "C2.empresas_razonsocial as CLPR_RAZON_SOCIAL, "
						+ "C2.empresas_giro as CLPR_GIRO, "
						+ "DIRECCION.DIRE_DIRECCION, "
						+ "COMUNA.COMU_NOMBRE, "
						+ "DIRECCION.DIRE_CIUDAD, "
						+ "f.804_condiciones, "
						+ "CONTACTO.CONT_EMAIL, "
						+ "		CONCAT_WS(' ',`CONTACTO`.CONT_NOMBRE,`CONTACTO`.CONT_APEP,`CONTACTO`.CONT_APEM) AS CONT_NOMBRE, "
	 					
						+ "	f.804_subtotal, "
						+ "	f.804_total, "
						+ "	f.804_descuento, "
						+ "	f.804_neto, "
						+ "	f.804_iva "
						+ " , 	if(f.`tipo_dteref` is null,'NINGUNA',f.`tipo_dteref`) as  tipo_dteref  "
						
						+ " , IF(f.`folioref` is null ,'',f.`folioref`) as  folioref "
						+ "	,	IF(f.`fec_ref` is null,'',DATE_FORMAT(f.`fec_ref`,'%Y-%m-%d')) as fec_ref  "

						+ "	, if(f.`804_tipo_cambio` is null,'',f.`804_tipo_cambio`) AS fac_servotros_tcambio"
						+ " , f.804_glosa_descuento, f.804_empresa_emisora  "

			    		
						+ " FROM `804` f"
						+ " LEFT JOIN empresas as C1 ON f.804_empresa_emisora = C1.empresas_id"
						+ " LEFT JOIN empresas as C2 ON f.clpr_id = C2.empresas_id"
						+ " LEFT JOIN DIRECCION ON f.DIRE_ID = DIRECCION.DIRE_ID"
						+ " LEFT JOIN COMUNA ON DIRECCION.COMU_ID = COMUNA.COMU_ID"
						+ " LEFT JOIN REGION ON COMUNA.REGI_ID = REGION.REGI_ID"
						+ " LEFT JOIN CONTACTO ON f.CONT_ID = CONTACTO.CONT_ID"
						//+ " LEFT JOIN USUARIOS ON f.FACT_USU_EMISION = USUARIOs.USUARIOS_ID"
						+ " WHERE f.804_id ="+FID;
				System.out.println(SQL_DATOS);
				RS_DATOS =  state.executeQuery(SQL_DATOS);
				
				RS_DATOS.next();
				String tipodte=RS_DATOS.getString("fac_servotros_tipodte");
				String condiciones = RS_DATOS.getString("804_condiciones").replace(";", "");
				String OBS0 = RS_DATOS.getString("804_condiciones").replace(";", "");
				String OBS1 = RS_DATOS.getString("CONT_NOMBRE").replace(";", "")+"-"+RS_DATOS.getString("CONT_EMAIL").replace(";", "");
				String OBS2 = RS_DATOS.getString("804_obs").replace(";", "")+"-TC:"+RS_DATOS.getString("fac_servotros_tcambio").replace(";", "");
			
				String netodesc = RS_DATOS.getString("804_subtotal");
				String total = RS_DATOS.getString("804_total");
				String desc = RS_DATOS.getString("804_descuento");
				String glosa_desc = RS_DATOS.getString("804_glosa_descuento").replace(";", "");
				String neto = RS_DATOS.getString("804_neto");
				String iva = RS_DATOS.getString("804_iva");
				
				String tipo_dteref = RS_DATOS.getString("tipo_dteref");
				String folioref = RS_DATOS.getString("folioref");
				String fec_ref = RS_DATOS.getString("fec_ref");
				
				String id_emp_emisora = RS_DATOS.getString("804_empresa_emisora");
//buscamos folio disponisble para este tipo de doc y lo insertamos al archivo y a la bd / si no tiene folio lanzamos error 
				
				String SQL_findfolio = "select `dte_encabezado`.`Folio` from `dte_encabezado` where `dte_encabezado`.`TipoDTE`='"+tipodte+"' and  `dte_encabezado`.`empresa_emisora_id`='"+id_emp_emisora+"' ORDER BY `dte_encabezado`.`Folio` DESC limit 1 "; 
	    		System.out.println(SQL_findfolio);
	 		    ResultSet RS_FINDFOLIO = stateFolio.executeQuery(SQL_findfolio);
	 		    
	 		    String folio="0";
	 		    if(RS_FINDFOLIO.next()){
	 		    	folio=(RS_FINDFOLIO.getInt("Folio")+1)+"";
	 		    }

	 		    boolean puedeenviar=false;
	 		    
	 		    if(folio.equals("0")){
		 			 //si no existen folios anteriores debemos tomar el primero que exista en la tabla de folios
		 			 String SQL_findfolioadm = "select admfolios_desde from `adm_folios` where `adm_folios`.`empresas_id`="+id_emp_emisora+" and `adm_folios`.`tipodte`='"+tipodte+"'" ; 
						
		 			 System.out.println(SQL_findfolioadm);
			 		    ResultSet RS_FINDFOLIOADM = stateFolio.executeQuery(SQL_findfolioadm);
			 		    if(RS_FINDFOLIOADM.next()){folio=RS_FINDFOLIOADM.getString("admfolios_desde");puedeenviar=true;} 
			 		 
		 		  }	else{
		 			 String SQL_findfolioadm = "select count(*) as n from `adm_folios` where '"+folio+"' >=`adm_folios`.`admfolios_desde` and '"+folio+"' <=`adm_folios`.`admfolios_hasta` and `adm_folios`.`empresas_id`="+id_emp_emisora+" and `adm_folios`.`tipodte`='"+tipodte+"'" ; 
						
		 			 System.out.println(SQL_findfolioadm);
			 		    ResultSet RS_FINDFOLIOADM = stateFolio.executeQuery(SQL_findfolioadm);
			 		    if(RS_FINDFOLIOADM.next()){
			 		    	if(RS_FINDFOLIOADM.getInt("n")>0){
			 		    		//entonces dentro del rango si existe el folio
			 		    		puedeenviar=true;
			 		    		
			 		    	}
			 		    }
		 		  }
	 		    
				if(puedeenviar){
				//TipoDTE;Folio;FechaEmision;TipoDespacho;IndTraslado;RUTCliente;RSClienente;GiroCliente;DirCliente;ComCliente;CiuCliente;Email;
			    String encabezado="->Encabezado<-\r\n"+
			    		RS_DATOS.getString("fac_servotros_tipodte")+";"+folio+";"+RS_DATOS.getString("FACT_FECHA_EMISION")+";0;0;"+
			    		RS_DATOS.getString("CLPR_RUT")+";"+RS_DATOS.getString("CLPR_RAZON_SOCIAL").replace(";", "")+";"+RS_DATOS.getString("CLPR_GIRO").replace(";", "")+";"+
			    		RS_DATOS.getString("DIRE_DIRECCION").replace(";", "")+";"+RS_DATOS.getString("COMU_NOMBRE")+";"+RS_DATOS.getString("DIRE_CIUDAD")+";"+
			    		RS_DATOS.getString("CONT_EMAIL")+";\r\n";
			    
			    
			    String SQL_INSERTe = "INSERT INTO dte_encabezado (TipoDTE, Folio,FechaEmision, TipoDespacho, IndTraslado, "
			    		+ "RUTCliente, RSCliente, GiroCliente, "
			    		+ "DirCliente, ComCliente, CiuCliente, Email,empresa_emisora_id,Modulo,Codigo_relacionado,cliente_id) "
			    		+ "VALUES ('"+RS_DATOS.getString("fac_servotros_tipodte")+"','"+folio+"','"+RS_DATOS.getString("FACT_FECHA_EMISION")+"','0','0'"
			    		+ ",'"+RS_DATOS.getString("CLPR_RUT")+"','"+RS_DATOS.getString("CLPR_RAZON_SOCIAL")+"','"+RS_DATOS.getString("CLPR_GIRO")+"'"
			    		+ ",'"+RS_DATOS.getString("DIRE_DIRECCION")+"','"+RS_DATOS.getString("COMU_NOMBRE")+"','"+RS_DATOS.getString("DIRE_CIUDAD")+"'"
			    		+ ",'"+RS_DATOS.getString("CONT_EMAIL")+"','"+id_emp_emisora+"','804','"+FID+"','"+RS_DATOS.getString("id_cliente")+"')";
			    //System.out.println("SQL Instert: "+SQL_INSERTe);
			    stategrabar.executeUpdate(SQL_INSERTe,Statement.RETURN_GENERATED_KEYS);

			    ResultSet generatedKeys = null;
	    		generatedKeys = stategrabar.getGeneratedKeys();
	    		String id_usu_last="";
	    		if (generatedKeys.next()) {
	    			id_usu_last=generatedKeys.getString(1);
	    		}
			    //stategrabar.close();
			   
			    
			    String SQL_UPFAC = "UPDATE `804` SET id_dte = "+id_usu_last+" WHERE 804_id = "+FID;
			    //System.out.println("SQL Instert: "+SQL_UPFAC);
			    stategrabar.executeUpdate(SQL_UPFAC);

				

				String SQL_DET2 = "SELECT count(*) as n"
						+ " FROM detalle_804 d "
						//+ " INNER JOIN activo ON d.ALT_ID = activo.ALT_ID"
						//+ " INNER JOIN producto ON activo.PROD_ID = producto.PROD_ID"
						+ " WHERE d.804_id= "+FID;
				ResultSet RS_DET2 = state2.executeQuery(SQL_DET2);
				
				
				RS_DET2.next();
				int numlines=RS_DET2.getInt("n");
			    
			    
			    String SQL_DET = "SELECT "
						+ " d.d804_id as ALT_ID,"
						+ " d.d804_descripcion as PROD_DESC_CORTO,"
						+ " '' as PROD_PN_TLI_CODFAB, "
						+ " '' as PROD_DESC_LARGO,"
						+ " d.d804_valor as DETI_UNITARIO"
						+ " FROM detalle_804 d "
						//+ " INNER JOIN activo ON d.ALT_ID = activo.ALT_ID"
						//+ " INNER JOIN producto ON activo.PROD_ID = producto.PROD_ID"
						+ " WHERE d.804_id= "+FID;
			    System.out.println(SQL_DET);
				RS_DET =  state.executeQuery(SQL_DET);
				
				
				
				Integer c=1; String exento="0";
			
				String detalle="->Detalle<-\r\n";
				while(RS_DET.next()){
					
					
					int neto_de=RS_DET.getInt("DETI_UNITARIO");
					int exento_det=RS_DET.getInt("DETI_UNITARIO");
					
					if(tipodte.equals("33")){  exento_det=0; }
					if(tipodte.equals("34")){    }
					
					String descLarga="";
					
					
					if(Integer.parseInt(desc)>0 && c==numlines){descLarga=glosa_desc+" $"+desc;}
					
					detalle+=c+";SERV;"+RS_DET.getString("PROD_DESC_CORTO").replace(";", "").toUpperCase()+";1;"
		            		+ RS_DET.getString("DETI_UNITARIO")+";0;0;0;0;"+exento_det+";"
		            		+ neto_de+";INT1;UN;"+descLarga+";\r\n";
					
					String SQL_INSERTd = "INSERT INTO dte_detalle (id_enc, NroLinea, Codigo, Descripcion, Cantidad, "
							+ "Precio, PorcDescto, ValorDescto, PorcRecgo, ValorRecgo, ValorExento, Valor, "
							+ "TipoCodigo, UniMedida, DescLarga) "
				    		+ "VALUES ("+id_usu_last+","+c+",'','"+RS_DET.getString("PROD_DESC_CORTO")+"',1,"
				    		+ ""+RS_DET.getString("DETI_UNITARIO")+",0,0,0,0,'"+RS_DET.getString("DETI_UNITARIO")+"',"+RS_DET.getString("DETI_UNITARIO")+","
				    		+ "'INT1','UN','"+descLarga+"')";
				    //NroLinea;Codigo;Descripcion;Cantidad;Precio;PorcDescto;ValorDescto;PorcRecgo;ValorRecgo;ValorExento;Valor;TipoCodigo;UniMedida;DescLarga;
				    System.out.println(SQL_INSERTd);
				    stategrabar.executeUpdate(SQL_INSERTd);
					c=c+1;
				}
				
				
				

				/*if(Integer.parseInt(desc)>0){
					
					detalle+=c+";DESC;"+glosa_desc.replace(";", "").toUpperCase()+";1;"
		            		+ "0;0;"+desc+";0;0;0;"
		            		+ "0;INT1;UN;"
		            		+ glosa_desc.replace(";", "").toUpperCase()+";\r\n";
					
					
				}*/
				String ivapercent="19";
				if(tipodte.equals("33")){ neto=neto; exento="0"; }
				if(tipodte.equals("34")){  exento=neto;neto="0";ivapercent="0"; }
				
				String totales="->Totales<-\r\n"+"0;0;0;0;"+neto+";"+exento+";"+ivapercent+";"+iva+";"+total+";\r\n";
				//PorcDesctoTot;ValorDesctoTot;PorcRecgoTot;ValorRecgoTot;Neto;Exento;TasaIVA;IVA;Total;
			    String SQL_INSERTt = "INSERT INTO dte_totales (id_enc, PorcDesctoTot, ValorDesctoTot, PorcRecgoTot, ValorRecgoTot, "
			    		+ "Neto, Exento, TasaIVA, IVA, Total)"
			    		+ " VALUES ("+id_usu_last+",0,0,0,0,"+neto+","+exento+","+ivapercent+","+iva+","+total+")";
			    System.out.println("SQL Instert: "+SQL_INSERTt);
			    stategrabar.executeUpdate(SQL_INSERTt);
			    
			    
			    
			    //////////////////////////DESCUENTOS//////////////
			    
			    
			    String descuentoss="";
			    if(Integer.parseInt(desc)>0){
			    	descuentoss="->DescRec<-\r\n" + 
			    			"1;D;Deducible;$;"+desc+";1;\r\n";
			    	
			    }
			    
			    
			    
			    
			    
			    //update de la cabecera 
			    
			    String SQL_UPENC = "UPDATE dte_encabezado SET Total = '"+total+"' WHERE id_dte = "+id_usu_last;
			    System.out.println("SQL Instert: "+SQL_UPENC);
			    stategrabar.executeUpdate(SQL_UPENC);
			    
			    String SQL_804 = "UPDATE `804` SET 804_folio = '"+folio+"' WHERE 804_ID = "+FID;
			    System.out.println("SQL Instert: "+SQL_804);
			    stategrabar.executeUpdate(SQL_804);
			    
			    String referencia="";
			    
			    if(!tipo_dteref.equals("") && !tipo_dteref.equals("NINGUNA")){
			    	    referencia="->Referencia<-\r\n"+"1;"+tipo_dteref+";"+folioref+";"+fec_ref+";0;;\r\n";
			    	    
			    	    
			    	    String fec_ref_in="null";
			    	    
			    	    if(!fec_ref.equals("")){
			    	    	fec_ref_in="'"+fec_ref+"'";
			    	    }
			    	   
					    //NroLineaRef;TipoDTERef;FolioRef;FechaRef;CodigoRef;RazonRef;
					    String SQL_INSERTr = "INSERT INTO dte_referencia (id_enc, NroLineaRef, TipoDTERef, FolioRef, FechaRef, CodigoRef, RazonRef) "
					    		+ "VALUES ("+id_usu_last+",1,'"+tipo_dteref+"','"+folioref+"',"+fec_ref_in+",'0','0')";
					    System.out.println("SQL Instert: "+SQL_INSERTr);
					    stategrabar.executeUpdate(SQL_INSERTr);
				  
			    	
			    }
			    
			    String obs="->Observacion<-\r\n"+condiciones+";\r\n"+OBS1+";\r\n"+OBS2+";";
			    //Linea1;Linea2;Linea3;
			    String SQL_INSERTo = "INSERT INTO dte_observacion (id_enc, Linea1, Linea2, Linea3) "
			    		+ "VALUES ("+id_usu_last+",'"+condiciones+"','"+OBS1+"','"+OBS2+"')";
			    System.out.println("SQL Instert: "+SQL_INSERTo);
			    stategrabar.executeUpdate(SQL_INSERTo);
			   
			    String data = encabezado+""+totales+""+detalle+""+referencia+""+descuentoss+""+obs+"\r\n";
		        
			    String directorio="";
	        	if(id_emp_emisora.equals("123"))directorio=Constantes.DIR_FILES;
	        	if(id_emp_emisora.equals("118"))directorio=Constantes.DIR_FILESNOF;
			    
	        	Writer outFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(""+directorio+tipodte+"_804_"+FID+"_"+folio+"_"+id_emp_emisora+".txt"),"UTF-8"));
				outFile.write(data);
				outFile.close();
				
				
				
				String SQL_findfoliocorreo = "select max(`adm_folios`.`admfolios_hasta`) as n,max(`adm_folios`.`admfolios_correo_aviso`) as correoaviso from `adm_folios` where  `adm_folios`.`empresas_id`="+id_emp_emisora+" and `adm_folios`.`tipodte`='"+tipodte+"'" ; 
				  
		  		
	    		System.out.println(SQL_findfoliocorreo);
	 		    ResultSet RS_FINDFOLIOCORREO = stateFolio.executeQuery(SQL_findfoliocorreo);
	 		    if(RS_FINDFOLIOCORREO.next()){
	 		    	//quedan folios
	 		    	//vemos cuantos quedan
	 		    	
	 		    	int ultimofolioparaingresar=RS_FINDFOLIOCORREO.getInt("n");
	 		    	int foliosrestantes= ultimofolioparaingresar-Integer.parseInt(folio);
	 		    	System.out.println("FOLIOS RESTANTES: "+foliosrestantes);
	 		    	if(foliosrestantes<=25){
	 		    		//enviamos email 
	 		    		 String SQL_INSERTO = "insert into correos (correos.correos_asunto,correos.correos_cuerpo,correos.correos_enviadoa,correos.correos_fechaingreso) " + 
	 		    		 		"values ('AVISO FOLIOS','ATENCION: QUEDAN "+foliosrestantes+" FOLIOS RESTANTES PARA EL TIPO DE DOCUMENTO "+tipodte+"','"+RS_FINDFOLIOCORREO.getString("correoaviso")+"',now())";
						    System.out.println("SQL Instert: "+SQL_INSERTO);
						    stategrabar.executeUpdate(SQL_INSERTO);
	 		    	}
	 		    }
	 		   
				//una vez que generamos el archivo , vemos si es necesario generar un correo. 
				
	 		   stategrabar.close();
			  
			  
				
				
				 RS_DATOS.close();
				 RS_DET.close();
				conexion.close();
					    
				RequestDispatcher rd_up = request.getRequestDispatcher("menuguias?Exito=OK");
				rd_up.forward(request, response);
				}
				else{
					//enviamos email 
		    		 String SQL_INSERTOo = "insert into correos (correos.correos_asunto,correos.correos_cuerpo,correos.correos_enviadoa,correos.correos_fechaingreso) " + 
		    		 		"values ('AVISO FOLIOS','ATENCION: QUEDAN "+0+" FOLIOS RESTANTES PARA EL TIPO DE DOCUMENTO "+tipodte+"','gerente@newoffice.cl;david.alexis.sb@gmail.com',now())";
					    System.out.println("SQL Instert: "+SQL_INSERTOo);
					    stategrabar.executeUpdate(SQL_INSERTOo);
					response.sendRedirect("menuguias?Exito=NOOK1");
				}	
			}catch(Exception ex){
				ex.printStackTrace();
				out.println("Error: "+ex);
			}
		}else{
		
			try {
				Statement state = null;
				ResultSet RS_DATOS = null;
				ResultSet RS_DATOSD = null;
				
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		String GID = request.getParameter("d_id");
	    		String TDTE = request.getParameter("tdte");

	    		String SQL_CAB = "SELECT "
			    		+ "		`804`.`804_folio` as fac_servotros_folio, "
			    		+ "		IF(`804`.`id_dte` is null , '1','0') as dte , "
			    		+ "		DATE_FORMAT(`804`.`804_fecha`, '%d-%m-%Y') AS FAC_FECHA ,"
			    		+ "		DATE_FORMAT(`804`.`804_fecvencimiento`, '%d-%m-%Y') AS fac_servotros_fecvencimiento ,"
			    		+ "		emisor.empresas_nombrenof as empresas_nombrenofemisor  ,"
			    		+ "		`804`.`804_tipodte` as fac_servotros_tipodte  ,"
			    		+ "		cliente.empresas_razonsocial as empresas_razonsocialcliente, "
			    		+ "		cliente.empresas_rut as empresas_rutcliente,"
			    		+ "		cliente.empresas_id as empresas_idcliente,"
			    		+ "		cliente.empresas_nombrenof as empresas_nombrenofcliente,"
			    		+ "		`804`.`804_obs` as fac_servotros_obs,"
			    		 
			    		+ "		`804`.`804_condiciones` as fac_servotros_condiciones, "
			    		
			    		+ "		`804`.`804_tipo_cambio` AS fac_servim_tcambio, "
			    		
						+ "		IF(`804`.`tipo_dteref` is null,'NINGUNA',`804`.`tipo_dteref`) as tipo_dteref, "
						+ "		IF(`804`.`folioref` is null ,'',`804`.`folioref`) as  folioref, "
	 		    		+ "		IF(`804`.`fec_ref` is null,'',DATE_FORMAT(`804`.`fec_ref`,'%d-%m-%Y')) as fec_ref, "

						
						+ "		`contacto`.`CONT_EMAIL`, "
						+ "		CONCAT_WS(' ',`CONTACTO`.CONT_NOMBRE,`CONTACTO`.CONT_APEP,`CONTACTO`.CONT_APEM) AS CONT_NOMBRE, "
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
			    	
			    		+ "		`804`.`804_estado`, "
			    		+ "		CONCAT_WS(' ',`usuarios`.`Usuarios_nombre`,`usuarios`.`Usuarios_ape_p`,`usuarios`.`Usuarios_ape_m`) AS Usuarios_nombre_com, "
			    		+ " 	`804`.`804_neto`, `804`.`804_iva` "
						
						+ "	FROM"
			    		+ "		`804` "
			    		+ "	INNER JOIN `empresas` emisor ON `emisor`.`empresas_id` = `804`.`804_empresa_emisora` "
			    		+ " INNER JOIN `empresas` cliente ON cliente.empresas_id = `804`.`clpr_id` "
			    		+ " INNER JOIN `contacto` ON `contacto`.`CONT_ID` = `804`.`CONT_ID` "
			    		+ " INNER JOIN `direccion` ON `direccion`.`DIRE_ID` = `804`.`dire_id` "
			    		+ " INNER JOIN `comuna` ON `comuna`.`COMU_ID` = `direccion`.`COMU_ID` "
			    		+ " INNER JOIN `region` ON `region`.`REGI_ID` = `comuna`.`REGI_ID` "
			    		+ " INNER JOIN `usuarios` ON `usuarios`.`Usuarios_id` = cliente.`responsable_id` "
				    	
			    		
			    		

			    		+ "	WHERE"
			    		+ "		`804`.`804_id`= "+GID;
			    System.out.println(SQL_CAB);
			    ResultSet CAB_RS = state.executeQuery(SQL_CAB);
			    if(CAB_RS.next()){
			    	String estado_sii="";
			    	if(CAB_RS.getString("dte").equals("0")){estado_sii = "ENVIADA";}
					if(CAB_RS.getString("dte").equals("1")){estado_sii = "PARA ENVIAR";}
			    	request.setAttribute("estado_sii", estado_sii);
			    	request.setAttribute("folio", CAB_RS.getString("fac_servotros_folio"));
			    	request.setAttribute("FAC_FECHA", CAB_RS.getString("FAC_FECHA"));
			    	request.setAttribute("fac_servotros_condiciones", CAB_RS.getString("fac_servotros_condiciones"));
			    	request.setAttribute("fac_servotros_fecvencimiento", CAB_RS.getString("fac_servotros_fecvencimiento"));
			    	request.setAttribute("empresas_nombrenofemisor", CAB_RS.getString("empresas_nombrenofemisor"));
			    	request.setAttribute("fac_servotros_tipodte", CAB_RS.getString("fac_servotros_tipodte"));
			    	request.setAttribute("empresas_razonsocialcliente", CAB_RS.getString("empresas_razonsocialcliente"));
			    	request.setAttribute("empresas_idcliente", CAB_RS.getString("empresas_idcliente"));
			    	request.setAttribute("empresas_rutcliente", CAB_RS.getString("empresas_rutcliente"));
			    	request.setAttribute("empresas_nombrenofcliente", CAB_RS.getString("empresas_nombrenofcliente"));
			    	request.setAttribute("fac_servim_tcambio", CAB_RS.getString("fac_servim_tcambio"));
			    	request.setAttribute("fac_servotros_obs", CAB_RS.getString("fac_servotros_obs"));
			    	request.setAttribute("CONT_EMAIL", CAB_RS.getString("CONT_EMAIL"));
			    	request.setAttribute("CONT_NOMBRE", CAB_RS.getString("CONT_NOMBRE"));
			    	request.setAttribute("CONT_TELEFONO", CAB_RS.getString("CONT_TELEFONO"));
			    	request.setAttribute("CONT_TELEFONOC", CAB_RS.getString("CONT_TELEFONOC"));
			    	
			    	
			    	request.setAttribute("PERS_NOMBRE", CAB_RS.getString("Usuarios_nombre_com"));
			    	request.setAttribute("DIRE_DIRECCION", CAB_RS.getString("DIRE_DIRECCION"));
			    	request.setAttribute("DIRE_CIUDAD", CAB_RS.getString("DIRE_CIUDAD"));
			    	request.setAttribute("COMU_NOMBRE", CAB_RS.getString("COMU_NOMBRE"));
			    	request.setAttribute("REGI_NOMBRE", CAB_RS.getString("REGI_NOMBRE"));
			    	
			    	request.setAttribute("fac_servotros_total", CAB_RS.getString("804_subtotal"));
			    	
			    	request.setAttribute("fac_servotros_glosa_desc", CAB_RS.getString("804_glosa_descuento"));
			    	request.setAttribute("fac_servotros_estado", CAB_RS.getString("804_estado"));
			    	request.setAttribute("DESC", CAB_RS.getString("804_descuento"));
			    	
			    	
			    	request.setAttribute("fac_servotros_neto", CAB_RS.getString("804_neto"));
			    	request.setAttribute("fac_servotros_iva", CAB_RS.getString("804_iva"));
			    	request.setAttribute("fac_servotros_totalfinal", CAB_RS.getString("804_total"));
			    	
			    	request.setAttribute("fec_ref", CAB_RS.getString("fec_ref"));
			    	request.setAttribute("folioref", CAB_RS.getString("folioref"));
			    	request.setAttribute("tipo_dteref", CAB_RS.getString("tipo_dteref"));

			    }
			    CAB_RS.close();
			    
			    String SQL_DET = "SELECT * FROM detalle_804 WHERE 804_id = "+GID;
			    //System.out.println(SQL_DET);
			    ResultSet DET_RS = state.executeQuery(SQL_DET);
			    ArrayList<String> prod = new ArrayList<String>();
			    Integer c=0;
			    while(DET_RS.next()){ prod.add(c+"/"+DET_RS.getString("d804_descripcion")+"/"+DET_RS.getString("d804_valor")); c=c+1;}
			    DET_RS.close();	
			    String[] ar_prod = new String[14];
			    for(int x=0; x < prod.size(); x++){
			    	//System.out.println(prod.get(x));
			    		ar_prod[x]=prod.get(x);	
			    }
			    request.setAttribute("ar_prod", ar_prod);
//			   
	            conexion.close();
	            
	 		    request.setAttribute("fact_id", GID);
	 		    request.setAttribute("tdte", TDTE);
			}catch(Exception ex){
			    out.println("ERROR: "+ex);
			    ex.printStackTrace();
			}
		//fin grabar
			
		RequestDispatcher rd = request.getRequestDispatcher("ifac_servotros.jsp");
        rd.forward(request, response);
		
		}
		
	}

}
