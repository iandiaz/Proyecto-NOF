

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
 * Servlet implementation class iguias
 */
@WebServlet("/iguias")
public class iguias extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public iguias() {
        super();
        
    }
    
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(Controlador.validateSession(request, response)){
			
			 mt(request,response);
		}
		else response.sendRedirect("/001/");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				String FID=request.getParameter("fact_id");
				String TDTE=request.getParameter("tdte");
						
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
			    Connection conexion=(Connection) DriverManager.getConnection
			    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
			    state = (Statement) ((java.sql.Connection) conexion).createStatement();
				stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
				Statement state2=(Statement) ((java.sql.Connection) conexion).createStatement();
				Statement stateFolio=(Statement) ((java.sql.Connection) conexion).createStatement();
				
				System.out.println("Docs: "+FID);
				
				String SQL_DATOS = "SELECT "
						+ "DATE_FORMAT(f.801_FECHA, '%Y-%m-%d') as FACT_FECHA, "
						+ "DATE_FORMAT(f.801_FECHA_EMISION, '%Y-%m-%d') as FACT_FECHA_EMISION, "
						+ " f.801_tipodte, "
						+ " f.801_otrasobs ,"
						+ " f.801_obs1, "
						+ " f.801_obs2,"
						+ " f.801_empresa_emisora,"
						
						+ " f.801_subtotal,"
						+ " f.801_descuento,"
						+ " f.801_neto,"
						+ " f.801_iva,"
						+ " f.801_total,"
						+ " f.801_glosa_descuento,"
						
						+ " f.801_TIPO_CAMBIO, "
						+ " f.801_CONDICIONES,"
						+ " f.CONT_NOMBRE, "
	            		+ " f.CONT_TELEFONO, "
	            		+ " f.CONT_EMAIL, "
	            		+ "C2.empresas_id as id_cliente,"
						
						+ "C2.empresas_rut as CLPR_RUT,"
						+ "C2.empresas_razonsocial as CLPR_RAZON_SOCIAL,"
						+ "C2.empresas_giro as CLPR_GIRO,"
						+ "DIRECCION.DIRE_DIRECCION,"
						+ "COMUNA.COMU_NOMBRE,"
						+ "DIRECCION.DIRE_CIUDAD,"
						+ " REGION.REGI_NOMBRE, "
		            	
						+ " CONCAT_WS(' ', u1.Usuarios_nombre, u1.Usuarios_ape_p) as responsable, "
						+ " f.USU_INICIAL_EMISOR "
						+ " , 	if(f.`tipo_dteref` is null,'',f.`tipo_dteref`) as  tipo_dteref  "
						+ " , f.folioref "
						+ " , DATE_FORMAT(f.fec_ref,'%Y-%m-%d') as fec_ref  "
					
						+ " FROM `801` f"
						+ " LEFT JOIN empresas as C1 ON f.801_EMPRESA_EMISORA = C1.empresas_id"
						+ " LEFT JOIN empresas as C2 ON f.CLPR_ID = C2.empresas_id"
						+ " LEFT JOIN DIRECCION ON f.DIRE_ID = DIRECCION.DIRE_ID"
						+ " LEFT JOIN COMUNA ON DIRECCION.COMU_ID = COMUNA.COMU_ID"
						+ " LEFT JOIN REGION ON COMUNA.REGI_ID = REGION.REGI_ID"
						+ " LEFT JOIN CONTACTO ON f.CONT_ID = CONTACTO.CONT_ID"
						+ " LEFT JOIN USUARIOS ON f.801_USU_EMISION = USUARIOS.USUARIOS_ID"
						+ " LEFT JOIN USUARIOS u1 ON F.801_RESPONSABLE = u1.Usuarios_id "
						+ " WHERE f.801_ID ="+FID;
				RS_DATOS =  state.executeQuery(SQL_DATOS);
				
				RS_DATOS.next();
				String tipodte=RS_DATOS.getString("801_tipodte");
				String fecha_fac = RS_DATOS.getString("FACT_FECHA");
				String condiciones = RS_DATOS.getString("801_CONDICIONES").replace(";", "");
				
				String neto=RS_DATOS.getString("801_neto").replace(";", "");
				String iva=RS_DATOS.getString("801_iva").replace(";", "");
				String total = RS_DATOS.getString("801_total").replace(";", "");
				String descuento= RS_DATOS.getString("801_descuento").replace(";", "");
				if(descuento==null || descuento.equals(""))descuento="0";
				String glosa_descuento= RS_DATOS.getString("801_glosa_descuento").replace(";", "");
				
				String OBS0 = RS_DATOS.getString("USU_INICIAL_EMISOR").replace(";", "")+"-"+RS_DATOS.getString("801_obs1").replace(";", "")+"-"+RS_DATOS.getString("801_CONDICIONES").replace(";", "");
					
				String OBS1 = RS_DATOS.getString("CONT_NOMBRE").replace(";", "")+"-"+RS_DATOS.getString("CONT_EMAIL").replace(";", "");
				String OBS2 = RS_DATOS.getString("801_otrasobs").replace(";", "")+"-"+RS_DATOS.getString("801_obs2").replace(";", "")+"-TC:"+RS_DATOS.getString("801_TIPO_CAMBIO").replace(";", "");
				
				String tipo_dteref = RS_DATOS.getString("tipo_dteref");
				String folioref = RS_DATOS.getString("folioref");
				String fec_ref = RS_DATOS.getString("fec_ref");
				
				String id_emp_emisora = RS_DATOS.getString("801_empresa_emisora");
				
				//TipoDTE;Folio;FechaEmision;TipoDespacho;IndTraslado;RUTCliente;RSClienente;GiroCliente;DirCliente;ComCliente;CiuCliente;Email;
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
					 String encabezado="->Encabezado<-\r\n"+
					    		RS_DATOS.getString("801_tipodte")+";"+folio+";"+fecha_fac+";0;0;"+
					    		RS_DATOS.getString("CLPR_RUT")+";"+RS_DATOS.getString("CLPR_RAZON_SOCIAL").replace(";", "")+";"+RS_DATOS.getString("CLPR_GIRO").replace(";", "")+";"+
					    		RS_DATOS.getString("DIRE_DIRECCION").replace(";", "")+";"+RS_DATOS.getString("COMU_NOMBRE")+";"+RS_DATOS.getString("DIRE_CIUDAD")+";"+
					    		RS_DATOS.getString("CONT_EMAIL").replace(";", "")+";\r\n";
					    
					    
					    String SQL_INSERTe = "INSERT INTO dte_encabezado (TipoDTE, Folio,FechaEmision, TipoDespacho, IndTraslado, "
					    		+ "RUTCliente, RSCliente, GiroCliente, "
					    		+ "DirCliente, ComCliente, CiuCliente, Email, Modulo,Codigo_relacionado,Condiciones,Region,Responsable,Tipo_cambio,Contacto,Telefono,empresa_emisora_id,cliente_id) "
					    		+ "VALUES ('"+RS_DATOS.getString("801_tipodte")+"','"+folio+"','"+fecha_fac+"','0','0'"
					    		+ ",'"+RS_DATOS.getString("CLPR_RUT")+"','"+RS_DATOS.getString("CLPR_RAZON_SOCIAL")+"','"+RS_DATOS.getString("CLPR_GIRO")+"'"
					    		+ ",'"+RS_DATOS.getString("DIRE_DIRECCION")+"','"+RS_DATOS.getString("COMU_NOMBRE")+"','"+RS_DATOS.getString("DIRE_CIUDAD")+"'"
					    		+ ",'"+RS_DATOS.getString("CONT_EMAIL")+"','801','"+FID+"','"+condiciones+"','"+RS_DATOS.getString("REGI_NOMBRE").replace("'", "\'")+"'"
					    		+ " ,'"+RS_DATOS.getString("responsable")+"','"+RS_DATOS.getString("801_TIPO_CAMBIO")+"' ,'"+RS_DATOS.getString("CONT_NOMBRE")+"','"+RS_DATOS.getString("CONT_TELEFONO")+"','"+id_emp_emisora+"','"+RS_DATOS.getString("id_cliente")+"')";
					    
					    System.out.println("SQL Instert: "+SQL_INSERTe);
					    stategrabar.executeUpdate(SQL_INSERTe,Statement.RETURN_GENERATED_KEYS);

					    ResultSet generatedKeys = null;
			    		generatedKeys = stategrabar.getGeneratedKeys();
			    		String id_usu_last="";
			    		if (generatedKeys.next()) {
			    			id_usu_last=generatedKeys.getString(1);
			    		}
					    //stategrabar.close();
					   
					    
					    String SQL_UPFAC = "UPDATE `801` SET id_dte = "+id_usu_last+" WHERE 801_ID = "+FID;
					    System.out.println("SQL Instert: "+SQL_UPFAC);
					    stategrabar.executeUpdate(SQL_UPFAC);

						String SQL_DET = "SELECT "
								+ " d.ALT_ID,"
								+ " d.PROD_DESC_CORTO,"
								+ " d.PROD_PN_TLI_CODFAB,"
								+ " d.ALT_SERIE, "
						
								+ " d.DETI_UNITARIO, "
								+ " d.UBI_ID, "
								+ " d.VTA_TICK_ID, "
								+ " d.VTA_OC "
								+ " FROM detalle_801 d "
								
								+ " WHERE d.FACT_ID= "+FID;
						System.out.println(SQL_DET);
						RS_DET =  state.executeQuery(SQL_DET);
						
						
						String SQL_DET2 = "SELECT count(*) as n"
								+ " FROM detalle_801 d "
								//+ " INNER JOIN activo ON d.ALT_ID = activo.ALT_ID"
								//+ " INNER JOIN producto ON activo.PROD_ID = producto.PROD_ID"
								+ " WHERE d.FACT_ID= "+FID;
					
						ResultSet RS_DET2 = state2.executeQuery(SQL_DET2);
						
						
						RS_DET2.next();
						
						int numlines=RS_DET2.getInt("n");
			    
			    
						String detalle="->Detalle<-\r\n";
						int c=1;
						while(RS_DET.next()){
							
							String descLarga="";
							if(Integer.parseInt(descuento)>0 && c==numlines)descLarga=glosa_descuento+" $"+descuento;
								
							
							String detexento="0";
							if(tipodte.equals("34")) detexento=RS_DET.getString("DETI_UNITARIO");
							detalle+=c+""
									+ ";"+RS_DET.getString("ALT_ID")+"-"+RS_DET.getString("UBI_ID")+"-"+RS_DET.getString("VTA_TICK_ID")+""
									+ ";"+RS_DET.getString("PROD_DESC_CORTO")+"-"+RS_DET.getString("ALT_SERIE")+"-"+RS_DET.getString("PROD_PN_TLI_CODFAB")+";1;"
				            		+ RS_DET.getString("DETI_UNITARIO")+";0;0;0;0;"+detexento+";"
				            		+ RS_DET.getInt("DETI_UNITARIO")+";INT1;UN;"+descLarga+";\r\n";
				 			
				 			
							String SQL_INSERTd = "INSERT INTO dte_detalle (id_enc, NroLinea, Codigo, Descripcion, Cantidad, "
									+ "Precio, PorcDescto, ValorDescto, PorcRecgo, ValorRecgo, ValorExento, Valor, "
									+ "TipoCodigo, UniMedida, DescLarga) "
						    		+ "VALUES ("+id_usu_last+","+c+",'"+RS_DET.getString("ALT_ID")+"-"+RS_DET.getString("UBI_ID")+"-"+RS_DET.getString("VTA_TICK_ID")+"','"+RS_DET.getString("PROD_DESC_CORTO")+"-"+RS_DET.getString("ALT_SERIE")+"-"+RS_DET.getString("PROD_PN_TLI_CODFAB")+"',1,"
						    		+ ""+RS_DET.getString("DETI_UNITARIO")+",0,0,0,0,'"+detexento+"',"+RS_DET.getString("DETI_UNITARIO")+","
						    		+ "'INT1','UN','"+descLarga+"')";
						    //NroLinea;Codigo;Descripcion;Cantidad;Precio;PorcDescto;ValorDescto;PorcRecgo;ValorRecgo;ValorExento;Valor;TipoCodigo;UniMedida;DescLarga;
						    System.out.println(SQL_INSERTd);
						    stategrabar.executeUpdate(SQL_INSERTd);
							c=c+1;
						}
						
						
						String ivapercent="19";
						String exento="0";
						if(tipodte.equals("34")){
							ivapercent="0";
							exento=neto;
							neto="0";
						} 
						String totales="->Totales<-\r\n"+"0;0;0;0;"+neto+";"+exento+";"+ivapercent+";"+iva+";"+total+";\r\n";
						//PorcDesctoTot;ValorDesctoTot;PorcRecgoTot;ValorRecgoTot;Neto;Exento;TasaIVA;IVA;Total;
					    String SQL_INSERTt = "INSERT INTO dte_totales (id_enc, PorcDesctoTot, ValorDesctoTot, PorcRecgoTot, ValorRecgoTot, "
					    		+ "Neto, Exento, TasaIVA, IVA, Total)"
					    		+ " VALUES ("+id_usu_last+",0,0,0,0,"+neto+","+exento+","+ivapercent+","+iva+","+total+")";
					    System.out.println("SQL Instert: "+SQL_INSERTt);
					    stategrabar.executeUpdate(SQL_INSERTt);
					    
					    //////////////////////////DESCUENTOS//////////////
					    
					    
					    String descuentoss="";
					    if(Integer.parseInt(descuento)>0){
					    	descuentoss="->DescRec<-\r\n" + 
					    			"1;D;Deducible;$;"+descuento+";1;\r\n";
					    	
					    }
					    
					    
					    
					    
					    
					    //update de la cabecera 
					    
					    String SQL_UPENC = "UPDATE dte_encabezado SET Total = '"+total+"' WHERE id_dte = "+id_usu_last;
					    System.out.println("SQL Instert: "+SQL_UPENC);
					    stategrabar.executeUpdate(SQL_UPENC);
					    
					    
					    String SQL_801 = "UPDATE `801` SET 801_folio = '"+folio+"' WHERE 801_ID = "+FID;
					    System.out.println("SQL Instert: "+SQL_801);
					    stategrabar.executeUpdate(SQL_801);
					    
					    
					    
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
					  
						
			        	//String data = encabezado+""+totales+""+detalle+""+referencia+""+obs+"\r\n";
			        	String data = encabezado+""+totales+""+detalle+""+referencia+""+descuentoss+""+obs+"\r\n";
			        	
			        	
			        	
			        	String directorio="";
			        	if(id_emp_emisora.equals("123"))directorio=Constantes.DIR_FILES;
			        	if(id_emp_emisora.equals("118"))directorio=Constantes.DIR_FILESNOF;
					        		
			        		
						//byte dataToWrite[] =data.getBytes();
						Writer outFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(""+directorio+tipodte+"_801_"+FID+"_"+folio+"_"+id_emp_emisora+".txt"),"UTF-8"));
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
						
						RS_DET.close();
						 RS_DATOS.close();
						 stateFolio.close();
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
				out.println("Error "+ex);
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
	    		
	    		String FID = request.getParameter("d_id");
	    		String TDTE = request.getParameter("tdte");

	    		//DATOS DE LA TABLA FACTURA
	    		String SQL_DATOS = "SELECT  "
	            		+ " DATE_FORMAT(F.801_FECHA, '%d-%m-%Y') AS FACT_FECHA, "
	            		+ " F.801_CONDICIONES,801_folio_birt, "
						
						+ " C1.empresas_nombrenof as CLPR_NOMBRE_FANTASIA,"
						+ " C2.empresas_razonsocial as CLPR_RAZON_SOCIAL,"
	            		+ " C2.empresas_rut as CLPR_RUT,"
	            		+ " C2.empresas_giro ,"
						
	            		+ " DIRECCION.DIRE_DIRECCION, COMUNA.COMU_NOMBRE, "
			    		+ " DIRECCION.DIRE_ID, DIRECCION.DIRE_CIUDAD, "
			    		+ " REGION.REGI_NOMBRE, "
	            		+ " F.801_TIPO_CAMBIO, "
	            		
	            		+ " F.CONT_NOMBRE, "
	            		+ " F.CONT_TELEFONO, "
	            		+ " F.CONT_EMAIL, "
	            		+ " F.801_OBS, "
	            		+ " F.801_ESTADO, "
	            		
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
						+ " F.id_dte , F.estados_vig_novig_id"
			    		
						
	            		+ " FROM `801` F "
	            		+ " LEFT JOIN empresas as C1 ON F.801_EMPRESA_EMISORA = C1.empresas_id "
	            		+ " LEFT JOIN empresas as C2 ON F.CLPR_ID = C2.empresas_id "
	            		+ " LEFT JOIN DIRECCION ON F.DIRE_ID = DIRECCION.DIRE_ID"
	            		+ " LEFT JOIN COMUNA ON DIRECCION.COMU_ID = COMUNA.COMU_ID"
			    		+ " LEFT JOIN REGION ON COMUNA.REGI_ID = REGION.REGI_ID"
	            		+ " LEFT JOIN USUARIOS ON F.801_USU_EMISION = USUARIOS.Usuarios_id "
	            		+ " LEFT JOIN USUARIOS u1 ON F.801_RESPONSABLE = u1.Usuarios_id "
	            		+ " WHERE F.801_ID ='"+FID+"'";
	    		//System.out.println(SQL_DATOS);
	    		RS_DATOS =  state.executeQuery(SQL_DATOS);
	            if(RS_DATOS.next()){
		            request.setAttribute("FACT_ID",FID);
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
			 		
			 		request.setAttribute("fec_vencimiento",RS_DATOS.getString("801_fecvencimiento"));
			 		
			 		
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
			    	
			    	
			    		request.setAttribute("SUBTOTAL",RS_DATOS.getString("801_subtotal"));
			    		request.setAttribute("DESCUENTO",RS_DATOS.getString("801_descuento"));
			 			request.setAttribute("IVA",RS_DATOS.getString("801_iva"));
			 			request.setAttribute("NETO",RS_DATOS.getString("801_neto"));
			 			request.setAttribute("TOTAL",RS_DATOS.getString("801_total"));
			 			request.setAttribute("GLOSADESC",RS_DATOS.getString("801_glosa_descuento"));
			 			request.setAttribute("estadovignovig",RS_DATOS.getString("estados_vig_novig_id"));
			 			
	            }
	            RS_DATOS.close();
	 		   
	 		   Float N=0.0f, I=0.0f;
	 		   String SQL_DATOSD = "SELECT D.ALT_ID, IFNULL(D.PROD_DESC_CORTO, ' ') as PROD_DESC_CORTO,"
	 		   				+ " D.DETI_UNITARIO, IFNULL(D.PROD_PN_TLI_CODFAB, ' ') as PROD_PN_TLI_CODFAB, IFNULL(D.ALT_SERIE, ' ') as ALT_SERIE "
		    		 		+ " FROM detalle_801 D"
		    		 		+ " WHERE D.FACT_ID ='"+FID+"'";
	 		   System.out.println(SQL_DATOSD);
	 		   RS_DATOSD =  state.executeQuery(SQL_DATOSD);
	 		   ArrayList<String> prod_res = new ArrayList<String>();
	 		   while(RS_DATOSD.next()){
	 			   N=N+RS_DATOSD.getFloat("DETI_UNITARIO");
	 			   I=I+((RS_DATOSD.getFloat("DETI_UNITARIO")*19)/100);
	 			   prod_res.add(RS_DATOSD.getString("ALT_ID")+"/"+
	 					  RS_DATOSD.getString("PROD_PN_TLI_CODFAB")+"/"+
	 					  RS_DATOSD.getString("PROD_DESC_CORTO").replace("/", " ")+"/"+
	 					  RS_DATOSD.getString("ALT_SERIE").replace("/","")+"/"+
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
			
		RequestDispatcher rd = request.getRequestDispatcher("iguias.jsp");
        rd.forward(request, response);
		
		}
		
	}

}
