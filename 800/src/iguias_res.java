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
 * Servlet implementation class iguias_res
 */
@WebServlet("/iguias_res")
public class iguias_res extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public iguias_res() {
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
			    Controlador.eraseCookie(request, response);
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

				
				System.out.println("Docs: "+DID);
				
				String SQL_DATOS = "SELECT "
						+ "	f.802_tipodte tipodte"
						+ "	, DATE_FORMAT(f.802_fecha, '%Y-%m-%d') as fecha"
						+ "	,DATE_FORMAT(f.802_fecha, '%Y-%m-%d') as fecha_emision "
						+ " ,f.802_obs "
						
						+ " ,C2.empresas_id as id_cliente"
						+ " ,C2.empresas_rut as CLPR_RUT"
						+ " ,C2.empresas_razonsocial as CLPR_RAZON_SOCIAL"
						+ " ,C2.empresas_giro as CLPR_GIRO"
						+ " ,DIRECCION.DIRE_DIRECCION"
						+ " ,COMUNA.COMU_NOMBRE"
						+ " ,DIRECCION.DIRE_CIUDAD"
						+ " ,f.CONT_EMAIL ,f.CONT_NOMBRE "
						+ "	,f.802_descuento, f.802_empresa_emisora,f.802_glosa_desc, f.802_neto,f.802_iva,802_total "
						+ " ,USUARIOS.Usuarios_inicial as USU_INICIAL_EMISOR "
						+ " , if(f.`tipo_dteref` is null,'',f.`tipo_dteref`) as  tipo_dteref  "
						+ " , f.folioref "
						+ " , DATE_FORMAT(f.fec_ref,'%Y-%m-%d') as fec_ref  "

						+ " FROM `802` f"
						
						+ " LEFT JOIN empresas as C2 ON f.cliente_id = C2.empresas_id"
						+ " LEFT JOIN DIRECCION ON f.DIRECCION_ID = DIRECCION.DIRE_ID"
						+ " LEFT JOIN COMUNA ON DIRECCION.COMU_ID = COMUNA.COMU_ID"
						+ " LEFT JOIN REGION ON COMUNA.REGI_ID = REGION.REGI_ID"
						+ " LEFT JOIN CONTACTO ON f.CONTACTO_ID = CONTACTO.CONT_ID"
						+ " LEFT JOIN USUARIOS ON f.802_creador = USUARIOS.USUARIOS_ID"
						+ " WHERE f.802_id ="+DID;
				RS_DATOS =  state.executeQuery(SQL_DATOS);
				
				RS_DATOS.next();
				String tipodte=RS_DATOS.getString("tipodte");
				String fecha_fac = RS_DATOS.getString("FECHA");
				
				
				String desc=RS_DATOS.getString("802_descuento").replace(";", "");
				String glosa_descuento= RS_DATOS.getString("802_glosa_desc").replace(";", "");
				
				String id_emp_emisora = RS_DATOS.getString("802_empresa_emisora");
				String neto=RS_DATOS.getString("802_neto").replace(";", "");
				String iva=RS_DATOS.getString("802_iva").replace(";", "");
				String total = RS_DATOS.getString("802_total").replace(";", "");
				
				String OBS0 = RS_DATOS.getString("USU_INICIAL_EMISOR").replace(";", "");
				
				String OBS1 = RS_DATOS.getString("CONT_NOMBRE").replace(";", "")+"-"+RS_DATOS.getString("CONT_EMAIL").replace(";", "");
				String OBS2 = RS_DATOS.getString("802_obs").replace(";", "");
				
			
				String tipo_dteref = RS_DATOS.getString("tipo_dteref");
				String folioref = RS_DATOS.getString("folioref");
				String fec_ref = RS_DATOS.getString("fec_ref");
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
			    		""+tipodte+";"+folio+";"+RS_DATOS.getString("fecha_emision")+";0;0;"+
			    		RS_DATOS.getString("CLPR_RUT")+";"+RS_DATOS.getString("CLPR_RAZON_SOCIAL").replace(";", "")+";"+RS_DATOS.getString("CLPR_GIRO").replace(";", "")+";"+
			    		RS_DATOS.getString("DIRE_DIRECCION").replace(";", "")+";"+RS_DATOS.getString("COMU_NOMBRE")+";"+RS_DATOS.getString("DIRE_CIUDAD")+";"+
			    		RS_DATOS.getString("CONT_EMAIL")+";\r\n";
			 
			    String SQL_INSERTe = "INSERT INTO dte_encabezado (TipoDTE, Folio,FechaEmision, TipoDespacho, IndTraslado, "
			    		+ "RUTCliente, RSCliente, GiroCliente, "
			    		+ "DirCliente, ComCliente, CiuCliente, Email,empresa_emisora_id,Modulo,Codigo_relacionado,cliente_id) "
			    		+ "VALUES ('"+tipodte+"','"+folio+"','"+RS_DATOS.getString("fecha_emision")+"','0','0'"
			    		+ ",'"+RS_DATOS.getString("CLPR_RUT")+"','"+RS_DATOS.getString("CLPR_RAZON_SOCIAL")+"','"+RS_DATOS.getString("CLPR_GIRO")+"'"
			    		+ ",'"+RS_DATOS.getString("DIRE_DIRECCION")+"','"+RS_DATOS.getString("COMU_NOMBRE")+"','"+RS_DATOS.getString("DIRE_CIUDAD")+"'"
			    		+ ",'"+RS_DATOS.getString("CONT_EMAIL")+"','"+id_emp_emisora+"','802','"+DID+"','"+RS_DATOS.getString("id_cliente")+"')";
			    System.out.println("SQL Instert: "+SQL_INSERTe);
			    stategrabar.executeUpdate(SQL_INSERTe,Statement.RETURN_GENERATED_KEYS);

			    ResultSet generatedKeys = null;
	    		generatedKeys = stategrabar.getGeneratedKeys();
	    		String id_usu_last="";
	    		if (generatedKeys.next()) {
	    			id_usu_last=generatedKeys.getString(1);
	    		}
			    //stategrabar.close();
			    RS_DATOS.close();
			    
			    String SQL_UPFAC = "UPDATE `802` SET id_dte = "+id_usu_last+" WHERE 802_id = "+DID;
			    //System.out.println("SQL Instert: "+SQL_UPFAC);
			    stategrabar.executeUpdate(SQL_UPFAC);

				String SQL_DET = "SELECT "
						+ " d.d802_folio as ALT_ID,"
						+ " d.d802_cod as PROD_DESC_CORTO,"
						+ " ' ' as PROD_PN_TLI_CODFAB, "
						+ " ' ' as PROD_DESC_LARGO,"
						+ " d.d802_TOTAL as DETI_UNITARIO, "
						+ " d.d802_fecha "
						
						+ " FROM detalle_802 d "
						+ " WHERE d.802_id= "+DID;
				RS_DET =  state.executeQuery(SQL_DET);
	    
				
				String SQL_DET2 = "SELECT count(*) as n"
						+ " FROM detalle_802 d "
						+ " WHERE d.802_id= "+DID;
			
				ResultSet RS_DET2 = state2.executeQuery(SQL_DET2);
				
				
				RS_DET2.next();
				
				int numlines=RS_DET2.getInt("n");
				Integer c=1;
				String detalle="->Detalle<-\r\n";
				String descLarga="";
				int detexento=0;
				int detprecio=0;
				String codigo ="";
				String refdet="";
				while(RS_DET.next()){
					
					
					if(Integer.parseInt(desc)>0 && c==numlines)descLarga=glosa_descuento+" $"+desc;
					
					if(tipodte.equals("34")) detexento+=Integer.parseInt(RS_DET.getString("DETI_UNITARIO"));
					
					detprecio+=Integer.parseInt(RS_DET.getString("DETI_UNITARIO"));
					codigo+=","+RS_DET.getString("ALT_ID");
					
					
					//agregamos el documento a la referencia
					
					refdet+=c+";52;"+RS_DET.getString("ALT_ID")+";"+RS_DET.getString("d802_fecha")+";0;;\r\n";
				    //NroLineaRef;TipoDTERef;FolioRef;FechaRef;CodigoRef;RazonRef;
				    String SQL_INSERTr = "INSERT INTO dte_referencia (id_enc, NroLineaRef, TipoDTERef, FolioRef, FechaRef, CodigoRef, RazonRef) "
				    		+ "VALUES ("+id_usu_last+",1,'52','"+RS_DET.getString("ALT_ID")+"','"+RS_DET.getString("d802_fecha")+"','0','')";
				    System.out.println("SQL Instert: "+SQL_INSERTr);
				    stategrabar.executeUpdate(SQL_INSERTr);
					
					c=c+1;
					
					
				}
				
				if(codigo.length()>0 && (codigo.charAt(0))==',' ) codigo=codigo.substring(1);
				
				detalle+="1;GUIA;DE ACUERDO A LAS GUIAS DE LA REFERENCIA;1;"
	            		+ detprecio+";0;0;0;0;"+detexento+";"
	            		+ detprecio+";INT1;UN;"
	            		+ descLarga+";\r\n";
	 			
					
				
				String SQL_INSERTd = "INSERT INTO dte_detalle (id_enc, NroLinea, Codigo, Descripcion, Cantidad, "
						+ "Precio, PorcDescto, ValorDescto, PorcRecgo, ValorRecgo, ValorExento, Valor, "
						+ "TipoCodigo, UniMedida, DescLarga) "
			    		+ "VALUES ("+id_usu_last+",1,'GUIA','DE ACUERDO A LAS GUIAS "+codigo+"',1,"
			    		+ ""+detprecio+",0,0,0,0,'"+detexento+"',"+detprecio+","
			    		+ "'INT1','UN','"+descLarga+"')";
			    System.out.println(SQL_INSERTd);
			    stategrabar.executeUpdate(SQL_INSERTd);
				
				RS_DET.close();
				
				
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
			    
			  
			    
			    String  referencia="->Referencia<-\r\n"+refdet;
			    
			    if(!tipo_dteref.equals("")){
			    	    referencia+=""
			    	    		+ ""+(c)+";"+tipo_dteref+";"+folioref+";"+fec_ref+";0;;\r\n";
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
	    if(Integer.parseInt(desc)>0){
	    	descuentoss="->DescRec<-\r\n" + 
	    			"1;D;Deducible;$;"+desc+";1;\r\n";
	    	
	    }
				
			    String data = encabezado+""+totales+""+detalle+""+referencia+""+descuentoss+""+obs+"\r\n";
	        	
			    String directorio="";
	        	if(id_emp_emisora.equals("123"))directorio=Constantes.DIR_FILES;
	        	if(id_emp_emisora.equals("118"))directorio=Constantes.DIR_FILESNOF;
			     
	        	//byte dataToWrite[] =data.getBytes();
	        	Writer outFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(""+directorio+tipodte+"_802_"+DID+"_"+folio+"_"+id_emp_emisora+".txt"),"UTF-8"));
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
	 		   
	 		   String SQL_UPENC = "UPDATE dte_encabezado SET Total = '"+total+"' WHERE id_dte = "+id_usu_last;
			    System.out.println("SQL Instert: "+SQL_UPENC);
			    stategrabar.executeUpdate(SQL_UPENC);
			    
			    String SQL_802 = "UPDATE `802` SET 802_folio = '"+folio+"' WHERE 802_ID = "+DID;
			    System.out.println("SQL Instert: "+SQL_802);
			    stategrabar.executeUpdate(SQL_802);
				
			    stategrabar.close();
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
	    		
	    		String GRID = request.getParameter("d_id");
	    		String TDTE = request.getParameter("tdte");
	    		//-------------------------CABECERA GUIA RESUMEN-------------------------//
	    		String SQL_CAB = "SELECT *"
	    				
	    				+ "	, g.cont_nombre as cont_nombre "
	    				
	    				+ "	, e2.empresas_id as cliente_id, "
	    				+ "		CONCAT_WS(' ',`usuarios`.`Usuarios_nombre`,`usuarios`.`Usuarios_ape_p`,`usuarios`.`Usuarios_ape_m`) AS Usuarios_nombre_com"
	    				+ "	, e2.empresas_nombrenof as cliente_nombre"
	    				+ "	, e2.empresas_rut as cliente_rut"
	    				+ "	, e2.empresas_razonsocial as cliente_razonsocial"
	    				+ "	, DATE_FORMAT(g.802_fecha, '%d-%m-%Y') as gr_fecha"
	    				+ "	, DATE_FORMAT(g.802_fecvencimiento, '%d-%m-%Y') as 802_fecvencimiento1"
	    				
	    				
	    				+ "	, ifnull(g.802_folio, 'ND') as gr_folio2, "
	    				+ "		IF(`g`.`tipo_dteref` is null,'NINGUNA',`g`.`tipo_dteref`) as tipo_dteref, "
						+ "		IF(`g`.`folioref` is null ,'',`g`.`folioref`) as  folioref, "
						+ "		IF(`g`.`fec_ref` is null,'',DATE_FORMAT(`g`.`fec_ref`, '%d-%m-%Y')) as fec_ref1 "

	    			
	    				+ " FROM `802` g"
			    		+ " INNER JOIN empresas e ON e.empresas_id=g.802_empresa_emisora"
			    		+ " INNER JOIN empresas e2 ON e2.empresas_id=g.cliente_id"
			    		+ " INNER JOIN direccion d ON d.DIRE_ID=g.direccion_id"
			    		+ " INNER JOIN comuna c ON c.comu_id=d.comu_id"
			    		+ " INNER JOIN region r ON r.regi_id=c.regi_id"
			    		+ " INNER JOIN contacto o ON o.cont_id=g.contacto_id"
			    		+ " INNER JOIN estados_vig_novig s ON s.estados_vig_novig_id=g.estados_vig_novig_id"
			    		+ " INNER JOIN `usuarios` ON `usuarios`.`Usuarios_id` = e2.`responsable_id` "
			    		+ " WHERE g.802_id = "+GRID+" "
			    		+ " ORDER BY g.802_id";
			    System.out.println(SQL_CAB);
			    ResultSet RS_CAB = state.executeQuery(SQL_CAB);
			    if(RS_CAB.next()){
			    	request.setAttribute("guiaresumen_id", GRID);
			    	request.setAttribute("cliente_id", RS_CAB.getString("cliente_id"));
				    request.setAttribute("id_dte", RS_CAB.getString("id_dte"));
				    request.setAttribute("gr_folio", RS_CAB.getString("gr_folio2"));
				    request.setAttribute("gr_fecha", RS_CAB.getString("gr_fecha"));
				    request.setAttribute("empresas_nombrenof", RS_CAB.getString("empresas_nombrenof"));
				    request.setAttribute("cliente_nombre", RS_CAB.getString("cliente_nombre"));
				    request.setAttribute("cliente_rut", RS_CAB.getString("cliente_rut"));
				    request.setAttribute("cliente_razonsocial", RS_CAB.getString("cliente_razonsocial"));
				    request.setAttribute("estados_vig_novig_nombre", RS_CAB.getString("estados_vig_novig_nombre"));
				    
				    request.setAttribute("direccion_nombre", RS_CAB.getString("DIRE_DIRECCION"));
				    request.setAttribute("regi_nombre", RS_CAB.getString("regi_nombre"));
				    request.setAttribute("gr_ciudad", RS_CAB.getString("DIRE_CIUDAD"));
				    request.setAttribute("comu_nombre", RS_CAB.getString("comu_nombre"));
				    request.setAttribute("cont_nombre", RS_CAB.getString("cont_nombre"));
				    request.setAttribute("cont_telefono", RS_CAB.getString("cont_telefono"));
				    request.setAttribute("CONT_EMAIL", RS_CAB.getString("CONT_EMAIL"));
				    request.setAttribute("PERS_NOMBRE", RS_CAB.getString("Usuarios_nombre_com"));
					    
				    request.setAttribute("gr_obs", RS_CAB.getString("802_obs"));
				    
				    request.setAttribute("fac_servim_fecvencimiento",RS_CAB.getString("802_fecvencimiento1").substring(0, 10));
				    request.setAttribute("DESC",RS_CAB.getString("802_descuento"));
				    request.setAttribute("fac_servim_tipodte",RS_CAB.getString("802_tipodte"));
				    request.setAttribute("gr_glosa_desc",RS_CAB.getString("802_glosa_desc"));
				    
				    request.setAttribute("NETO",RS_CAB.getString("802_neto"));
				    request.setAttribute("subtotal",RS_CAB.getString("802_subtotal"));
				    request.setAttribute("iva",RS_CAB.getString("802_iva"));
				    request.setAttribute("total",RS_CAB.getString("802_total"));
				   
				    request.setAttribute("fec_ref", RS_CAB.getString("fec_ref1"));
			    	request.setAttribute("folioref", RS_CAB.getString("folioref"));
			    	request.setAttribute("tipo_dteref", RS_CAB.getString("tipo_dteref"));
				 
			    }
		
		 		
		 		//-------------------------CABECERA GUIA RESUMEN-------------------------//
			    
			    //-------------------------DETALLE GUIA RESUMEN-------------------------//
			    String SQL_DETALLE = "SELECT 802_ID,d802_cod, d802_TOTAL, DATE_FORMAT(d802_FECHA, '%d-%m-%Y') as GD_FECHA,d802_folio "
	    				+ " FROM detalle_802 "
	    				+ " WHERE 802_id = "+GRID+" AND estados_vig_novig_id = 1 ORDER BY d802_id";
			    System.out.println(SQL_DETALLE);
			    ResultSet RS_DETALLE = state.executeQuery(SQL_DETALLE);
			    ArrayList<String> gr_detalle = new ArrayList<String>();
			    while(RS_DETALLE.next()){
				    gr_detalle.add(RS_DETALLE.getString("802_ID")+"/"+RS_DETALLE.getString("d802_TOTAL")+"/"+RS_DETALLE.getString("GD_FECHA")+"/"+RS_DETALLE.getString("d802_cod")+"/"+RS_DETALLE.getString("d802_folio"));
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
			   
			   request.setAttribute("d_id", GRID); 
	 		   request.setAttribute("tdte", TDTE);
			}catch(Exception ex){
			    out.println("ERROR: "+ex);
			    ex.printStackTrace();
			}
		//fin grabar
			
		RequestDispatcher rd = request.getRequestDispatcher("iguias_res.jsp");
        rd.forward(request, response);
		
		}
		
	}

}
