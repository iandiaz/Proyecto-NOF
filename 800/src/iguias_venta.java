
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
import Constantes.Controlador;

/**
 * Servlet implementation class iguias_venta
 */
@WebServlet("/iguias_venta")
public class iguias_venta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public iguias_venta() {
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
			try {
				Statement state = null;
				String DID=request.getParameter("d_id");
				String TDTE=request.getParameter("tdte");
						
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
			    Connection conexion=(Connection) DriverManager.getConnection
			    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
			    state = (Statement) ((java.sql.Connection) conexion).createStatement();
				stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
					Statement stateFolio=(Statement) ((java.sql.Connection) conexion).createStatement();
					Statement state2=(Statement) ((java.sql.Connection) conexion).createStatement();
					
				//System.out.println("Docs: "+DID);
				
				String SQL_DATOS = "SELECT f.821_tipodte tipodte, DATE_FORMAT(f.821_FECHA, '%Y-%m-%d') as fecha, "
						+ " DATE_FORMAT(f.821_FECHA_EMISION, '%Y-%m-%d') as fecha_emision, "
						+ "	f.821_OBS,"
						+ " f.821_obs1 as obs1, f.821_obs2 as obs2,"
						
						+ " C2.empresas_id as id_cliente,"
						+ " C2.empresas_rut as CLPR_RUT,"
						+ " C2.empresas_razonsocial as CLPR_RAZON_SOCIAL,"
						+ " C2.empresas_giro as CLPR_GIRO,"
						
						+ " DIRECCION.DIRE_DIRECCION,"
						+ " COMUNA.COMU_NOMBRE,"
						+ " DIRECCION.DIRE_CIUDAD,"
						+ " f.821_CONDICIONES as condiciones,"
						+ " CONTACTO.CONT_EMAIL, "
						+ " f.821_RESPONSABLE_NAME AS 821_RESPONSBALE_NAME , "
						+ " f.usu_inicial_emisor AS EMISOR_INICIAL, "
						+ "	f.821_subtotal, "
	            		+ "	f.821_iva, "
	            		+ "	f.821_neto, "
	            		+ "	f.821_total,"
	            		+ "	f.821_descuento,"
	            		+ "	f.821_glosa_descuento, f.821_empresa_emisora   "
	            		+ " , 	if(f.`tipo_dteref` is null,'',f.`tipo_dteref`) as  tipo_dteref  "
						+ " , f.folioref "
						+ " , DATE_FORMAT(f.fec_ref,'%Y-%m-%d') as fec_ref  "
						+ "	,f.821_afecta as g_afecta "
					
						+ " FROM `821` f"
						
						+ " LEFT JOIN empresas as C2 ON f.CLPR_ID = C2.empresas_id"
						+ " LEFT JOIN DIRECCION ON f.DIRE_ID = DIRECCION.DIRE_ID"
						+ " LEFT JOIN COMUNA ON DIRECCION.COMU_ID = COMUNA.COMU_ID"
						+ " LEFT JOIN REGION ON COMUNA.REGI_ID = REGION.REGI_ID"
						+ " LEFT JOIN CONTACTO ON f.CONT_ID = CONTACTO.CONT_ID"
						+ " LEFT JOIN USUARIOS ON f.821_usu_emision = USUARIOS.USUARIOS_ID"
						+ " WHERE f.821_ID ="+DID;
				System.out.println(SQL_DATOS);
				RS_DATOS =  state.executeQuery(SQL_DATOS);
				
				RS_DATOS.next();
				String tipodte=RS_DATOS.getString("tipodte");
				String fecha_fac = RS_DATOS.getString("FECHA");
				
				String OBS0 = RS_DATOS.getString("EMISOR_INICIAL").replace(";", "")+"-"+RS_DATOS.getString("obs1").replace(";", "");
				String OBS1 = RS_DATOS.getString("821_RESPONSBALE_NAME").replace(";", "");
				String OBS2 = RS_DATOS.getString("821_OBS").replace(";", "")+"-"+RS_DATOS.getString("obs2").replace(";", "");
				
				String neto =RS_DATOS.getString("821_NETO").replace(";", "");
				String iva =RS_DATOS.getString("821_IVA").replace(";", "");
				String total=RS_DATOS.getString("821_TOTAL").replace(";", "");
				String descuento=RS_DATOS.getString("821_descuento"); 
			 	String glosadescuento=RS_DATOS.getString("821_glosa_descuento");
				String id_emp_emisora = RS_DATOS.getString("821_empresa_emisora");
				
				String tipo_dteref = RS_DATOS.getString("tipo_dteref");
				String folioref = RS_DATOS.getString("folioref");
				String fec_ref = RS_DATOS.getString("fec_ref");
				String g_afecta = RS_DATOS.getString("g_afecta");
				
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
			    		"52;"+folio+";"+RS_DATOS.getString("fecha_emision")+";0;0;"+
			    		RS_DATOS.getString("CLPR_RUT")+";"+RS_DATOS.getString("CLPR_RAZON_SOCIAL").replace(";", "")+";"+RS_DATOS.getString("CLPR_GIRO").replace(";", "")+";"+
			    		RS_DATOS.getString("DIRE_DIRECCION").replace(";", "")+";"+RS_DATOS.getString("COMU_NOMBRE")+";"+RS_DATOS.getString("DIRE_CIUDAD")+";"+
			    		RS_DATOS.getString("CONT_EMAIL")+";\r\n";
			    
			    String SQL_INSERTe = "INSERT INTO dte_encabezado (TipoDTE, Folio,FechaEmision, TipoDespacho, IndTraslado, "
			    		+ "RUTCliente, RSCliente, GiroCliente, "
			    		+ "DirCliente, ComCliente, CiuCliente, Email,empresa_emisora_id,Modulo,Codigo_relacionado,cliente_id) "
			    		+ "VALUES ('52','"+folio+"','"+RS_DATOS.getString("fecha_emision")+"','0','0'"
			    		+ ",'"+RS_DATOS.getString("CLPR_RUT")+"','"+RS_DATOS.getString("CLPR_RAZON_SOCIAL")+"','"+RS_DATOS.getString("CLPR_GIRO")+"'"
			    		+ ",'"+RS_DATOS.getString("DIRE_DIRECCION")+"','"+RS_DATOS.getString("COMU_NOMBRE")+"','"+RS_DATOS.getString("DIRE_CIUDAD")+"'"
			    		+ ",'"+RS_DATOS.getString("CONT_EMAIL")+"','"+id_emp_emisora+"','821','"+DID+"','"+RS_DATOS.getString("id_cliente")+"')";
			    //System.out.println("SQL Instert: "+SQL_INSERTe);
			    stategrabar.executeUpdate(SQL_INSERTe,Statement.RETURN_GENERATED_KEYS);

			    ResultSet generatedKeys = null;
	    		generatedKeys = stategrabar.getGeneratedKeys();
	    		String id_usu_last="";
	    		if (generatedKeys.next()) {
	    			id_usu_last=generatedKeys.getString(1);
	    		}
			    //stategrabar.close();
			    RS_DATOS.close();
			    
			    String SQL_UPFAC = "UPDATE `821` SET id_dte = "+id_usu_last+" WHERE 821_ID = "+DID;
			    System.out.println("SQL Instert: "+SQL_UPFAC);
			    stategrabar.executeUpdate(SQL_UPFAC);

				String SQL_DET = "SELECT "
						+ " d.ALT_ID,"
						+ "	d.ALT_SERIE,"
						+ " d.PROD_DESC_CORTO,"
						+ " d.PROD_PN_TLI_CODFAB, "
						+ " d.d821_valor AS DETI_UNITARIO,"
						+ "	d.UBI_ID,"
						+ "	d.TICK_ID "
						+ " FROM detalle_821 d "
						+ " WHERE d.821_ID= "+DID;
				
				RS_DET =  state.executeQuery(SQL_DET);
				
				
				String SQL_DET2 = "SELECT count(*) as n"
						+ " FROM detalle_821 d "
						//+ " INNER JOIN activo ON d.ALT_ID = activo.ALT_ID"
						//+ " INNER JOIN producto ON activo.PROD_ID = producto.PROD_ID"
						+ " WHERE d.821_ID= "+DID;
			
				ResultSet RS_DET2 = state2.executeQuery(SQL_DET2);
				
				
				RS_DET2.next();
				
				int numlines=RS_DET2.getInt("n");
	    
				Integer c=1;
				
				String detalle="->Detalle<-\r\n";
				while(RS_DET.next()){
					
					String descLarga="";
					if(Integer.parseInt(descuento)>0 && c==numlines)descLarga=glosadescuento+" $"+descuento;
					
					String detexento="0";
					if(g_afecta.equals("0")) detexento=RS_DET.getString("DETI_UNITARIO");
					
				
					detalle+=c+";"+RS_DET.getString("ALT_ID")+"-"+RS_DET.getString("UBI_ID")+"-"+RS_DET.getString("TICK_ID")+";"+RS_DET.getString("PROD_PN_TLI_CODFAB")+"-"+RS_DET.getString("ALT_SERIE")+"-"+RS_DET.getString("PROD_DESC_CORTO")+";1;"
		            		+ RS_DET.getString("DETI_UNITARIO")+";0;0;0;0;"+detexento+";"
		            		+ RS_DET.getInt("DETI_UNITARIO")+";INT1;UN;"
		            		+ descLarga+";\r\n";
					
		 			System.out.println(detalle);
					
					String SQL_INSERTd = "INSERT INTO dte_detalle (id_enc, NroLinea, Codigo, Descripcion, Cantidad, "
							+ "Precio, PorcDescto, ValorDescto, PorcRecgo, ValorRecgo, ValorExento, Valor, "
							+ "TipoCodigo, UniMedida, DescLarga) "
				    		+ "VALUES ("+id_usu_last+","+c+",'"+RS_DET.getString("ALT_ID")+"-"+RS_DET.getString("UBI_ID")+"-"+RS_DET.getString("TICK_ID")+"','"+RS_DET.getString("PROD_PN_TLI_CODFAB")+"-"+RS_DET.getString("ALT_SERIE")+"-"+RS_DET.getString("PROD_DESC_CORTO")+"',1,"
				    		+ ""+RS_DET.getString("DETI_UNITARIO")+",0,0,0,0,'"+detexento+"',"+RS_DET.getString("DETI_UNITARIO")+","
				    		+ "'INT1','UN','"+descLarga+"')";
				    //NroLinea;Codigo;Descripcion;Cantidad;Precio;PorcDescto;ValorDescto;PorcRecgo;ValorRecgo;ValorExento;Valor;TipoCodigo;UniMedida;DescLarga;
				    System.out.println(SQL_INSERTd);
				    stategrabar.executeUpdate(SQL_INSERTd);
					c=c+1;
				}
				RS_DET.close();
				
				String ivapercent="19";
				String exento="0";
				if(g_afecta.equals("0")){
					ivapercent="0";
					exento=neto;
					neto="0";
				} 
			
				
				String totales="->Totales<-\r\n"+"0;0;0;0;"+neto+";"+exento+";"+ivapercent+";"+iva+";"+total+";\r\n";
				//PorcDesctoTot;ValorDesctoTot;PorcRecgoTot;ValorRecgoTot;Neto;Exento;TasaIVA;IVA;Total;
				  String SQL_INSERTt = "INSERT INTO dte_totales (id_enc, PorcDesctoTot, ValorDesctoTot, PorcRecgoTot, ValorRecgoTot, "
				    		+ "Neto, Exento, TasaIVA, IVA, Total)"
				    		+ " VALUES ("+id_usu_last+",0,0,0,0,"+neto+",0,"+ivapercent+","+iva+","+total+")";
				  System.out.println("SQL Instert: "+SQL_INSERTt);
			    stategrabar.executeUpdate(SQL_INSERTt);
			   
			    
			    
			    String referencia="";
			    
			    if(!tipo_dteref.equals("")){
			    	    referencia="->Referencia<-\r\n"+"1;"+tipo_dteref+";"+folioref+";"+fec_ref+";0;;\r\n";
					    //NroLineaRef;TipoDTERef;FolioRef;FechaRef;CodigoRef;RazonRef;
					    String SQL_INSERTr = "INSERT INTO dte_referencia (id_enc, NroLineaRef, TipoDTERef, FolioRef, FechaRef, CodigoRef, RazonRef) "
					    		+ "VALUES ("+id_usu_last+",1,'"+tipo_dteref+"','"+folioref+"','"+fec_ref+"','0','0')";
					    System.out.println("SQL Instert: "+SQL_INSERTr);
					    stategrabar.executeUpdate(SQL_INSERTr);
				  
			    	
			    }
			    
			    String obs="->Observacion<-\r\n"+OBS0+";\r\n"+OBS1+";\r\n"+OBS2+";\r\n";
				 //Linea1;Linea2;Linea3;
			    String SQL_INSERTo = "INSERT INTO dte_observacion (id_enc, Linea1, Linea2, Linea3) "
			    		+ "VALUES ("+id_usu_last+",'"+OBS0+"','"+OBS1+"','"+OBS2+"')";
			    System.out.println("SQL Instert: "+SQL_INSERTo);
			    stategrabar.executeUpdate(SQL_INSERTo);
			    
				//////////////////////////DESCUENTOS//////////////
			    
			    String descuentoss="";
			    if(Integer.parseInt(descuento)>0){
			    	descuentoss="->DescRec<-\r\n" + 
			    			"1;D;Deducible;$;"+descuento+";1;\r\n";
			    	
			    }
			    
	        	String data = encabezado+""+totales+""+detalle+""+referencia+""+descuentoss+""+obs+"\r\n";
	        	
	            String directorio="";
	        	if(id_emp_emisora.equals("123"))directorio=Constantes.DIR_FILES;
	        	if(id_emp_emisora.equals("118"))directorio=Constantes.DIR_FILESNOF;
	        	
			    Writer outFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(""+directorio+tipodte+"_821_"+DID+"_"+folio+"_"+id_emp_emisora+".txt"),"UTF-8"));
				
				//Writer outFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(""+Constantes.DIR_FILES+"52"+"_821_"+DID+".txt"),"UTF-8"));
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
	 		    	if(foliosrestantes<=50){
	 		    		//enviamos email 
	 		    		 String SQL_INSERTO = "insert into correos (correos.correos_asunto,correos.correos_cuerpo,correos.correos_enviadoa,correos.correos_fechaingreso) " + 
	 		    		 		"values ('AVISO FOLIOS','ATENCION: QUEDAN "+foliosrestantes+" FOLIOS RESTANTES PARA EL TIPO DE DOCUMENTO "+tipodte+"','"+RS_FINDFOLIOCORREO.getString("correoaviso")+"',now())";
						    System.out.println("SQL Instert: "+SQL_INSERTO);
						    stategrabar.executeUpdate(SQL_INSERTO);
	 		    	}
	 		    }
	 		   
				
	 		   //update de la cabecera 
			    
			    String SQL_UPENC = "UPDATE dte_encabezado SET Total = '"+total+"' WHERE id_dte = "+id_usu_last;
			    System.out.println("SQL Instert: "+SQL_UPENC);
			    stategrabar.executeUpdate(SQL_UPENC);
			    
				   
			    String SQL_821 = "UPDATE `821` SET 821_folio = '"+folio+"' WHERE 821_ID = "+DID;
			    System.out.println("SQL Instert: "+SQL_821);
			    stategrabar.executeUpdate(SQL_821);
				
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
				out.println("error "+ex);
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

	    		//DATOS DE LA TABLA FACTURA
	    		String SQL_DATOS = "SELECT  "
	            		+ " DATE_FORMAT(f.821_FECHA, '%d-%m-%Y') AS fecha, "
	            		+ " F.821_CONDICIONES as condiciones, "
	            		
						+ " F.821_empresa_emisora_nombre as CLPR_NOMBRE_FANTASIA,"
						+ " C2.empresas_razonsocial as CLPR_RAZON_SOCIAL,"
						+ " C2.empresas_rut as CLPR_RUT,"
	            		
	            		+ " DIRECCION.DIRE_DIRECCION, COMUNA.COMU_NOMBRE, "
			    		+ " DIRECCION.DIRE_ID, DIRECCION.DIRE_CIUDAD, "
			    		+ " REGION.REGI_NOMBRE, "
	            		+ " F.contacto_nombre as CONT_NOMBRE, "
	            		+ " CONTACTO.CONT_TELEFONO, "
	            		+ " F.821_OBS as obs, "
	            		+ " F.821_ESTADO as estado, "
	            		+ " DATE_FORMAT(F.821_FECHA_EMISION, '%d-%m-%Y') AS fecha_emision, "
	            		+ " F.821_RESPONSABLE_NAME AS GV_RESPONSBALE_NAME, "
	            		+ "	F.usu_inicial_emisor as Usuarios_login, "
	            		+ "	F.821_tipodte as tipodte,  "
	            		+ " F.821_obs1 as obs1, "
	            		+ "	F.821_obs2 as obs2, "
	            		+ "	F.821_TRANSPORTE AS GV_TRANSPORTE,"
	            		+ "	F.usu_inicial_emisor AS EMISOR_INICIAL,"
	            		+ "	F.821_subtotal, "
	            		+ "	F.821_iva, "
	            		+ "	F.821_neto, "
	            		+ "	F.821_total,"
	            		+ "	F.821_descuento,"
	            		+ "	F.821_glosa_descuento,"
	            		+ "	F.821_folio_birt,   "
	            		+ "	F.821_folio,   "
	            		+ "		IF(`f`.`tipo_dteref` is null,'NINGUNA',`f`.`tipo_dteref`) as tipo_dteref, "
						+ "		IF(`f`.`folioref` is null ,'',`f`.`folioref`) as  folioref, "
						+ "		IF(`f`.`fec_ref` is null,'',DATE_FORMAT(`f`.`fec_ref`,'%d-%m-%Y')) as fec_ref, "
				
	            		
						+ "		est.estados_vig_novig_nombre as estadoname,"
						+ "		F.821_afecta as g_afecta "
	            		
	            		+ " FROM `821` F "
	            		
						+ " LEFT JOIN empresas as C2 ON F.CLPR_ID = C2.empresas_id "
	            		+ " LEFT JOIN DIRECCION ON F.DIRE_ID = DIRECCION.DIRE_ID"
	            		+ " LEFT JOIN COMUNA ON DIRECCION.COMU_ID = COMUNA.COMU_ID"
			    		+ " LEFT JOIN REGION ON COMUNA.REGI_ID = REGION.REGI_ID"
	            		+ " LEFT JOIN CONTACTO ON F.CONT_ID = CONTACTO.CONT_ID "
	            		+ "	INNER JOIN estados_vig_novig est on est.estados_vig_novig_id= F.estados_vig_novig_id "
	  			    	
	            		+ " WHERE F.821_ID ='"+GID+"'";
	    		System.out.println(SQL_DATOS);
	    		RS_DATOS =  state.executeQuery(SQL_DATOS);
	            if(RS_DATOS.next()){
		            request.setAttribute("d_id",GID);
		            request.setAttribute("tipodte",RS_DATOS.getString("tipodte"));
		            request.setAttribute("ref",RS_DATOS.getString("obs"));
		            request.setAttribute("obs1",RS_DATOS.getString("obs1"));
		            request.setAttribute("obs2",RS_DATOS.getString("obs2"));
			 		request.setAttribute("fecha",RS_DATOS.getString("fecha"));
			 		request.setAttribute("condiciones",RS_DATOS.getString("condiciones"));
			 		request.setAttribute("CLPR_NOMBRE_FANTASIA",RS_DATOS.getString("CLPR_NOMBRE_FANTASIA"));
			 		request.setAttribute("CLPR_RAZON_SOCIAL",RS_DATOS.getString("CLPR_RAZON_SOCIAL"));
			 		request.setAttribute("CLPR_RUT",RS_DATOS.getString("CLPR_RUT"));
			 		request.setAttribute("g_afecta",RS_DATOS.getString("g_afecta"));
			 		request.setAttribute("DIRE_DIRECCION",RS_DATOS.getString("DIRE_DIRECCION"));
			 		request.setAttribute("COMU_NOMBRE",RS_DATOS.getString("COMU_NOMBRE"));
			 		request.setAttribute("DIRE_CIUDAD",RS_DATOS.getString("DIRE_CIUDAD"));
			 		request.setAttribute("REGI_NOMBRE",RS_DATOS.getString("REGI_NOMBRE"));
			 		request.setAttribute("GV_TRANSPORTE",RS_DATOS.getString("GV_TRANSPORTE"));
			 		request.setAttribute("responsable",RS_DATOS.getString("GV_RESPONSBALE_NAME"));
			 		request.setAttribute("CONT_NOMBRE",RS_DATOS.getString("CONT_NOMBRE"));
			 		request.setAttribute("CONT_TELEFONO",RS_DATOS.getString("CONT_TELEFONO"));
			 		request.setAttribute("estado",RS_DATOS.getString("estado"));
			 		request.setAttribute("fecha_emision",RS_DATOS.getString("fecha_emision"));
			 		request.setAttribute("USU_INICIAL",RS_DATOS.getString("EMISOR_INICIAL"));
			 		request.setAttribute("folio_birt",RS_DATOS.getString("821_folio_birt"));
			 		 request.setAttribute("estadoname", RS_DATOS.getString("estadoname"));
			 		request.setAttribute("nrobirt", RS_DATOS.getString("821_folio_birt"));
		    		
			 		
			 		String folio="";
			    	if(RS_DATOS.getString("821_folio")==null) folio ="ND";
			    	else folio = RS_DATOS.getString("821_folio");
		            request.setAttribute("folio",folio);
			 		   
			 		  request.setAttribute("subtotal", RS_DATOS.getString("821_subtotal"));
				 		 request.setAttribute("descuento", RS_DATOS.getString("821_descuento"));
				 		request.setAttribute("neto", RS_DATOS.getString("821_neto"));
				 		request.setAttribute("iva", RS_DATOS.getString("821_iva"));
				 		request.setAttribute("total", RS_DATOS.getString("821_total"));
				 		request.setAttribute("glosadescuento", RS_DATOS.getString("821_glosa_descuento"));
				 		
				 		
				 		request.setAttribute("fec_ref", RS_DATOS.getString("fec_ref"));
				    	request.setAttribute("folioref", RS_DATOS.getString("folioref"));
				    	String tipo_dteref=RS_DATOS.getString("tipo_dteref").equals("") ? "NINGUNA": RS_DATOS.getString("tipo_dteref");
				    	request.setAttribute("tipo_dteref", tipo_dteref);
			 		
	            }
	            RS_DATOS.close();
	 		   
	 		   String SQL_DATOSD = "SELECT D.ALT_ID, IFNULL(D.PROD_DESC_CORTO, ' ') as PROD_DESC_CORTO,"
	 		   				+ " D.d821_valor AS DETI_UNITARIO, IFNULL(D.PROD_PN_TLI_CODFAB, ' ') as PROD_PN_TLI_CODFAB, IFNULL(D.ALT_SERIE, ' ') as ALT_SERIE "
		    		 		+ " FROM detalle_821 D"
		    		 		+ " WHERE D.821_ID ='"+GID+"'";
	 		   System.out.println(SQL_DATOSD);
	 		   RS_DATOSD =  state.executeQuery(SQL_DATOSD);
	 		   ArrayList<String> prod_res = new ArrayList<String>();
	 		   while(RS_DATOSD.next()){
	 			   prod_res.add(RS_DATOSD.getString("ALT_ID").replace("/", " ")+"//"+
	 					  RS_DATOSD.getString("PROD_PN_TLI_CODFAB").replace("/", " ")+"//"+
	 					  RS_DATOSD.getString("PROD_DESC_CORTO").replace("/", " ")+"//"+
	 					  RS_DATOSD.getString("ALT_SERIE")+"//"+
	 					  RS_DATOSD.getInt("DETI_UNITARIO"));
	 		   }
	 		   RS_DATOSD.close();
	 		 
	 		   
	 		 
	 		   String[] ar_productos = new String[prod_res.size()];
	 		   for(int x=0; x < prod_res.size(); x++){
	 			   ar_productos[x]=prod_res.get(x);
	 		   }
	 		   request.setAttribute("ar_productos", ar_productos);
	 		   request.setAttribute("tdte", TDTE);
			}catch(Exception ex){
			    out.println("ERROR: "+ex);
			    ex.printStackTrace();
			}
		//fin grabar
			
		RequestDispatcher rd = request.getRequestDispatcher("iguias_venta.jsp");
        rd.forward(request, response);
		
		}
		
	}

}
