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
 * Servlet implementation class iguias_desn
 */
@WebServlet("/N800_I_03_001_823")
public class N800_I_03_001_823 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public N800_I_03_001_823() {
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
				Controlador.eraseCookie(request, response);
				response.sendRedirect("/001/");
				return;
		}
		//fin logout
		String id_iusuario=Controlador.getUsuIDSession(request);
			
		request.setAttribute("usuname", Controlador.getUsunameSession(request));
		
		//grabar

		if(request.getParameter("grabar") != null){
			Statement stategrabar = null;	
			ResultSet CLIENTE_RS = null;
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
				Statement state2=(Statement) ((java.sql.Connection) conexion).createStatement();
				Statement stateFolio=(Statement) ((java.sql.Connection) conexion).createStatement();
				
				System.out.println("Docs: "+DID);
				
				String SQL_DATOS = "SELECT DATE_FORMAT(f.823_fecha, '%Y-%m-%d') as fecha, "
						+ " DATE_FORMAT(f.823_fecha, '%Y-%m-%d') as fecha_emision"
						+ "	, f.823_numticket as guia_des_tras_normal_numticket"
						+ "	,f.823_obs as guia_des_tras_normal_obs"
						+ "	,f.823_obs2 as obs2, f.823_condiciones as condiciones,"
						+ " 823_subtotal,823_neto,823_iva,823_total,823_descuento,823_glosa_descuento,823_empresa_emisora,  "
			    		
						+ " C2.empresas_id as id_cliente,"
						+ " C2.empresas_rut as CLPR_RUT,"
						+ " C2.empresas_razonsocial as CLPR_RAZON_SOCIAL,"
						+ " C2.empresas_giro as CLPR_GIRO,"
						+ " DIRECCION.DIRE_DIRECCION,"
						+ " COMUNA.COMU_NOMBRE,"
						+ " DIRECCION.DIRE_CIUDAD,"
						+ "	f.cont_nombre as CONT_NOMBRE,"
						+ " CONTACTO.CONT_EMAIL"
						
						+ " , if(f.`tipo_dteref` is null,'',f.`tipo_dteref`) as  tipo_dteref  "
						+ " , f.folioref "
						+ " , DATE_FORMAT(f.fec_ref,'%Y-%m-%d') as fec_ref  "
						+ "	, f.823_afecta as g_afecta "

						+ " FROM `823` f"
						
						+ " LEFT JOIN empresas as C2 ON f.clientes_id = C2.empresas_id"
						+ " LEFT JOIN DIRECCION ON f.DIRE_ID2 = DIRECCION.DIRE_ID"
						+ " LEFT JOIN COMUNA ON DIRECCION.COMU_ID = COMUNA.COMU_ID"
						+ " LEFT JOIN REGION ON COMUNA.REGI_ID = REGION.REGI_ID"
						+ " LEFT JOIN CONTACTO ON f.CONT_ID = CONTACTO.CONT_ID"
						+ " LEFT JOIN USUARIOS ON f.823_creador = USUARIOS.USUARIOS_ID"
						+ " WHERE f.823_id ="+DID;
				System.out.println(SQL_DATOS);
				CLIENTE_RS =  state.executeQuery(SQL_DATOS);
				
				CLIENTE_RS.next();
				//String tipodte=CLIENTE_RS.getString("tipodte");
				String fecha_fac = CLIENTE_RS.getString("FECHA");
				
				
				String OBS0 = CLIENTE_RS.getString("guia_des_tras_normal_numticket").replace(";", "")+"-"+CLIENTE_RS.getString("guia_des_tras_normal_obs").replace(";", "");
				
				String OBS1 = CLIENTE_RS.getString("CONT_NOMBRE").replace(";", "")+"-"+CLIENTE_RS.getString("CONT_EMAIL").replace(";", "");
				String OBS2 = CLIENTE_RS.getString("obs2").replace(";", "");
			
				
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
    	 		String id_emp_emisora = CLIENTE_RS.getString("823_empresa_emisora");
    	 		
    	 		String tipo_dteref = CLIENTE_RS.getString("tipo_dteref");
				String folioref = CLIENTE_RS.getString("folioref");
				String fec_ref = CLIENTE_RS.getString("fec_ref");
				String g_afecta = CLIENTE_RS.getString("g_afecta");
				
	//buscamos folio disponisble para este tipo de doc y lo insertamos al archivo y a la bd / si no tiene folio lanzamos error 
				
				String SQL_findfolio = "select `dte_encabezado`.`Folio` from `dte_encabezado` where `dte_encabezado`.`TipoDTE`='52' and  `dte_encabezado`.`empresa_emisora_id`='"+id_emp_emisora+"' ORDER BY `dte_encabezado`.`Folio` DESC limit 1 "; 
	    		System.out.println(SQL_findfolio);
	 		    ResultSet RS_FINDFOLIO = stateFolio.executeQuery(SQL_findfolio);
	 		    
	 		    String folio="0";
	 		    if(RS_FINDFOLIO.next()){
	 		    	folio=(RS_FINDFOLIO.getInt("Folio")+1)+"";
	 		    }
	 		    
	 		    boolean puedeenviar=false;
	 		    
	 		    
	 		   if(folio.equals("0")){
		 			 //si no existen folios anteriores debemos tomar el primero que exista en la tabla de folios
		 			 String SQL_findfolioadm = "select admfolios_desde from `adm_folios` where `adm_folios`.`empresas_id`="+id_emp_emisora+" and `adm_folios`.`tipodte`='52'" ; 
						
		 			 System.out.println(SQL_findfolioadm);
			 		    ResultSet RS_FINDFOLIOADM = stateFolio.executeQuery(SQL_findfolioadm);
			 		    if(RS_FINDFOLIOADM.next()){folio=RS_FINDFOLIOADM.getString("admfolios_desde");puedeenviar=true;} 
			 		 
		 		  }	else{
		 			 String SQL_findfolioadm = "select count(*) as n from `adm_folios` where '"+folio+"' >=`adm_folios`.`admfolios_desde` and '"+folio+"' <=`adm_folios`.`admfolios_hasta` and `adm_folios`.`empresas_id`="+id_emp_emisora+" and `adm_folios`.`tipodte`='52'" ; 
						
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
			    		"52;"+folio+";"+CLIENTE_RS.getString("fecha_emision")+";0;0;"+
			    		CLIENTE_RS.getString("CLPR_RUT")+";"+CLIENTE_RS.getString("CLPR_RAZON_SOCIAL").replace(";", "")+";"+CLIENTE_RS.getString("CLPR_GIRO").replace(";", "")+";"+
			    		CLIENTE_RS.getString("DIRE_DIRECCION").replace(";", "")+";"+CLIENTE_RS.getString("COMU_NOMBRE")+";"+CLIENTE_RS.getString("DIRE_CIUDAD")+";"+
			    		CLIENTE_RS.getString("CONT_EMAIL")+";\r\n";
			  
			    
			    
			    String SQL_INSERTe = "INSERT INTO dte_encabezado (TipoDTE, Folio,FechaEmision, TipoDespacho, IndTraslado, "
			    		+ "RUTCliente, RSCliente, GiroCliente, "
			    		+ "DirCliente, ComCliente, CiuCliente, Email,empresa_emisora_id,Modulo,Codigo_relacionado,Contacto,cliente_id) "
			    		+ "VALUES ('52','"+folio+"','"+CLIENTE_RS.getString("fecha_emision")+"','0','0'"
			    		+ ",'"+CLIENTE_RS.getString("CLPR_RUT")+"','"+CLIENTE_RS.getString("CLPR_RAZON_SOCIAL")+"','"+CLIENTE_RS.getString("CLPR_GIRO")+"'"
			    		+ ",'"+CLIENTE_RS.getString("DIRE_DIRECCION")+"','"+CLIENTE_RS.getString("COMU_NOMBRE")+"','"+CLIENTE_RS.getString("DIRE_CIUDAD")+"'"
			    		+ ",'"+CLIENTE_RS.getString("CONT_EMAIL")+"','"+id_emp_emisora+"','823','"+DID+"','"+CLIENTE_RS.getString("CONT_NOMBRE")+"','"+CLIENTE_RS.getString("id_cliente")+"')";
			    //System.out.println("SQL Instert: "+SQL_INSERTe);
			    stategrabar.executeUpdate(SQL_INSERTe,Statement.RETURN_GENERATED_KEYS);

			    ResultSet generatedKeys = null;
	    		generatedKeys = stategrabar.getGeneratedKeys();
	    		String id_usu_last="";
	    		if (generatedKeys.next()) {
	    			id_usu_last=generatedKeys.getString(1);
	    		}
			    //stategrabar.close();
			    CLIENTE_RS.close();
			    
			    String SQL_UPFAC = "UPDATE `823` SET id_dte = "+id_usu_last+" WHERE 823_id = "+DID;
			    //System.out.println("SQL Instert: "+SQL_UPFAC);
			    stategrabar.executeUpdate(SQL_UPFAC);

			    
			    String SQL_DET = "SELECT "
			    		+ " d.ALT_ID as ALT_ID, "
			    		+ " d.PROD_DESC_CORTO as PROD_DESC_CORTO, "
			    		+ " d.PROD_PN_TLI_CODFAB as PROD_PN_TLI_CODFAB, "
			    		+ " d.PROD_DESC_CORTO as PROD_DESC_LARGO,"
			    		+ " d.alt_serie,"
			    		+ " '0' as DETI_UNITARIO,"
			    		+ "	d.ubi_id,"
			    		+ "	d.tick_id, "
			    		+ "	d.d823_valor  "
			    		+ " FROM detalle_823 d "
			    		+ " WHERE d.823_id = "+DID;
				RS_DET =  state.executeQuery(SQL_DET);
	    
				
				
		   		String SQL_DET2 = "SELECT count(*) as n"
						+ " FROM detalle_823 d "
						//+ " INNER JOIN activo ON d.ALT_ID = activo.ALT_ID"
						//+ " INNER JOIN producto ON activo.PROD_ID = producto.PROD_ID"
						+ " WHERE d.823_ID= "+DID;
			
				ResultSet RS_DET2 = state2.executeQuery(SQL_DET2);
				
				
				RS_DET2.next();
				
				int numlines=RS_DET2.getInt("n");
				
				
				Integer c=1;
				String detalle="->Detalle<-\r\n";
				while(RS_DET.next()){
					
					String descLarga="";
					if(Integer.parseInt(desc)>0 && c==numlines)descLarga=glosa_desc+" $"+desc;
					
					String detexento="0";
					if(g_afecta.equals("0")) detexento=RS_DET.getString("d823_valor");
					
					
					detalle+=c+";"+RS_DET.getString("ALT_ID")+"-"+RS_DET.getString("ubi_id")+"-"+RS_DET.getString("tick_id")+";"+RS_DET.getString("PROD_DESC_CORTO")+"-"+RS_DET.getString("PROD_PN_TLI_CODFAB")+"-"+RS_DET.getString("alt_serie")+";1;"
		            		+ RS_DET.getString("d823_valor")+";0;0;0;0;"+detexento+";"
		            		+ RS_DET.getInt("d823_valor")+";INT1;UN;"
		            		+ descLarga+";\r\n";
		 			
					
					String SQL_INSERTd = "INSERT INTO dte_detalle (id_enc, NroLinea, Codigo, Descripcion, Cantidad, "
							+ "Precio, PorcDescto, ValorDescto, PorcRecgo, ValorRecgo, ValorExento, Valor, "
							+ "TipoCodigo, UniMedida, DescLarga) "
				    		+ "VALUES ("+id_usu_last+","+c+",'"+RS_DET.getString("ALT_ID")+"-"+RS_DET.getString("ubi_id")+"-"+RS_DET.getString("tick_id")+"','"+RS_DET.getString("PROD_DESC_CORTO")+"-"+RS_DET.getString("PROD_PN_TLI_CODFAB")+"',1,"
				    		+ ""+RS_DET.getString("d823_valor")+",0,0,0,0,'"+detexento+"',"+RS_DET.getString("d823_valor")+","
				    		+ "'INT1','UN','"+descLarga.toUpperCase()+"')";
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
					exento=NETO;
					NETO="0";
				} 
				
				String totales="->Totales<-\r\n"+"0;0;0;0;"+NETO+";"+exento+";"+ivapercent+";"+IVA+";"+TOTAL+";\r\n";
				
				String SQL_INSERTt = "INSERT INTO dte_totales (id_enc, PorcDesctoTot, ValorDesctoTot, PorcRecgoTot, ValorRecgoTot, "
			    		+ "Neto, Exento, TasaIVA, IVA, Total)"
			    		+ " VALUES ("+id_usu_last+",0,0,0,0,"+NETO+","+exento+","+ivapercent+","+IVA+","+TOTAL+")";
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
	    if(Integer.parseInt(desc)>0){
	    	descuentoss="->DescRec<-\r\n" + 
	    			"1;D;Deducible;$;"+desc+";1;\r\n";
	    	
	    }
	    
	    
	    
	    
	    
  	//String data = encabezado+""+totales+""+detalle+""+referencia+""+obs+"\r\n";
	    String data = encabezado+""+totales+""+detalle+""+referencia+""+descuentoss+""+obs+"\r\n";
    	
		
			    
				
			    String directorio="";
		    	if(id_emp_emisora.equals("123"))directorio=Constantes.DIR_FILES;
		    	if(id_emp_emisora.equals("118"))directorio=Constantes.DIR_FILESNOF;
	        	//byte dataToWrite[] =data.getBytes();
	    		Writer outFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(""+directorio+"52_823_"+DID+"_"+folio+"_"+id_emp_emisora+".txt"),"UTF-8"));
				outFile.write(data);
				outFile.close();
				
				
				String SQL_findfoliocorreo = "select max(`adm_folios`.`admfolios_hasta`) as n,max(`adm_folios`.`admfolios_correo_aviso`) as correoaviso from `adm_folios` where  `adm_folios`.`empresas_id`="+id_emp_emisora+" and `adm_folios`.`tipodte`='52'" ; 
				  
		  		
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
	 		    		 		"values ('AVISO FOLIOS','ATENCION: QUEDAN "+foliosrestantes+" FOLIOS RESTANTES PARA EL TIPO DE DOCUMENTO 52','"+RS_FINDFOLIOCORREO.getString("correoaviso")+"',now())";
						    System.out.println("SQL Instert: "+SQL_INSERTO);
						    stategrabar.executeUpdate(SQL_INSERTO);
	 		    	}
	 		    }
	 		   
	 		    
	 		   String SQL_UPENC = "UPDATE dte_encabezado SET Total = '"+TOTAL+"' WHERE id_dte = "+id_usu_last;
			    System.out.println("SQL Instert: "+SQL_UPENC);
			    stategrabar.executeUpdate(SQL_UPENC);
			    
			    String SQL_822 = "UPDATE `823` SET 823_folio = '"+folio+"' WHERE 823_ID = "+DID;
			    System.out.println("SQL Instert: "+SQL_822);
			    stategrabar.executeUpdate(SQL_822);
				
				
				
				
				
				
				conexion.close();
	        	
				
				
				
				
	        
			    stategrabar.close();
					    
				RequestDispatcher rd_up = request.getRequestDispatcher("menuguias?Exito=OK");
				rd_up.forward(request, response);
				}
				else{
					//enviamos email 
		    		 String SQL_INSERTOo = "insert into correos (correos.correos_asunto,correos.correos_cuerpo,correos.correos_enviadoa,correos.correos_fechaingreso) " + 
		    		 		"values ('AVISO FOLIOS','ATENCION: QUEDAN "+0+" FOLIOS RESTANTES PARA EL TIPO DE DOCUMENTO 52','gerente@newoffice.cl;david.alexis.sb@gmail.com',now())";
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
				ResultSet CLIENTE_RS = null;
				ResultSet GUIAS_RS = null;
				
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		String GID = request.getParameter("d_id");
	    		String TDTE = request.getParameter("tdte");

	    		//-------------------------CABECERA GUIA RESUMEN-------------------------//
	    		String SQL_Cliente = "SELECT "
	    				+ " g.id_dte, DATE_FORMAT(823_fecha, '%d-%m-%Y') as fecha, "
	    				+ " e1.empresas_nombrenof as emisor, IF(id_dte is null, 'NO ENVIADA', 'ENVIADA') as dte, "
	    				
	    				+ " e2.empresas_razonsocial as nom1, e2.empresas_rut as rut1, e2.empresas_id as id1,  "
	    				+ " IF(d.dire_direccion ='','SD',d.DIRE_DIRECCION) as dir1,"
	    				+ " r.REGI_NOMBRE as reg1, c.COMU_NOMBRE as com1, d.DIRE_CIUDAD as cui1, "
	    				+ " CONCAT_WS(' ',o.CONT_NOMBRE,o.CONT_APEP,o.CONT_APEM) as CONT_NOMBRE, "
	    				+ " o.CONT_TELEFONO, o.CONT_EMAIL, o.CONT_TELEFONOC,"
	    				
						+ " e.empresas_razonsocial as nom2, e.empresas_rut as rut2, e.empresas_id as id2,  "
						+ " IF(d1.dire_direccion ='','SD',d1.DIRE_DIRECCION) as dir2,"
						+ " r1.REGI_NOMBRE as reg2, c1.COMU_NOMBRE as com2, d1.DIRE_CIUDAD as cui2, "
						
	    				+ " g.823_condiciones, g.823_numticket, "
	    				+ " 823_subtotal,823_neto,823_iva,823_total,823_descuento,823_glosa_descuento,  "
			    		
	    				+ " g.823_obs, "
	    				+ " 823_obs2, "
	    				+ "		CONCAT_WS(' ',`usuarios`.`Usuarios_nombre`,`usuarios`.`Usuarios_ape_p`,`usuarios`.`Usuarios_ape_m`) AS Usuarios_nombre,	"
	    				
	    				+ "		IF(`g`.`tipo_dteref` is null,'NINGUNA',`g`.`tipo_dteref`) as tipo_dteref, "
						+ "		IF(`g`.`folioref` is null ,'',`g`.`folioref`) as  folioref, "
						+ "		IF(`g`.`fec_ref` is null,'',DATE_FORMAT(`g`.`fec_ref`,'%d-%m-%Y')) as fec_ref,  "
						+ "		g.823_afecta as g_afecta "
						
			    		
	    				//+ " p.PERS_NOMBRE"
	    				+ " FROM `823` g "
	    				+ " INNER JOIN empresas e ON e.empresas_id = g.clientes_id "
	    				+ " INNER JOIN empresas e1 ON e1.empresas_id = g.823_empresa_emisora "
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
			    System.out.println(SQL_Cliente);
	 		    CLIENTE_RS =  state.executeQuery(SQL_Cliente);
	 		    if(CLIENTE_RS.next()){
	 		    	request.setAttribute("id_dte", CLIENTE_RS.getString("id_dte"));
	 		    	request.setAttribute("dte", CLIENTE_RS.getString("dte"));
	 		    	request.setAttribute("fecha", CLIENTE_RS.getString("fecha"));
	 		    	request.setAttribute("emisor", CLIENTE_RS.getString("emisor"));
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
			    	request.setAttribute("CONT_NOMBRE", CLIENTE_RS.getString("CONT_NOMBRE"));
			    	request.setAttribute("CONT_TELEFONO", CLIENTE_RS.getString("CONT_TELEFONO"));
			    	request.setAttribute("CONT_TELEFONOC", CLIENTE_RS.getString("CONT_TELEFONOC"));
			    	request.setAttribute("PERS_NOMBRE", " ");
			    	request.setAttribute("CONT_EMAIL", CLIENTE_RS.getString("CONT_EMAIL"));
	 		    	request.setAttribute("condicion", CLIENTE_RS.getString("823_condiciones"));
			    	
			    	request.setAttribute("obs", CLIENTE_RS.getString("823_obs"));
			    	request.setAttribute("obs2", CLIENTE_RS.getString("823_obs2"));
			    	
			    	request.setAttribute("RESPONSABLE", CLIENTE_RS.getString("Usuarios_nombre"));
			    	request.setAttribute("numtick",CLIENTE_RS.getString("823_numticket"));
			    	
			    	
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
	    	    	
	    	    	request.setAttribute("g_afecta",CLIENTE_RS.getString("g_afecta"));
	 		    }
		 		//-------------------------CABECERA -------------------------//
			    
			    //-------------------------DETALLE-------------------------//
			    String SQL_GUIAS = "SELECT "
			    		+ " d.ALT_ID, d.PROD_PN_TLI_CODFAB,d.d823_valor ,d.PROD_DESC_CORTO, d.PROD_COD_BAR_FAB, d.ALT_SERIE, d.UBI_DESCRIPCION, "
			    		+ " DATE_FORMAT(d.TRAS_FECHA, '%d-%m-%Y') as fecha"
			    		+ " FROM detalle_823 d "
			    		+ " WHERE d.823_id = "+GID;
			    System.out.println(SQL_GUIAS);
			    GUIAS_RS =  state.executeQuery(SQL_GUIAS);
			    ArrayList<String> guias = new ArrayList<String>();
			    while(GUIAS_RS.next()){ guias.add(GUIAS_RS.getString("ALT_ID")+"/"+GUIAS_RS.getString("PROD_PN_TLI_CODFAB")
			    		+"/"+GUIAS_RS.getString("PROD_DESC_CORTO")+"/"+GUIAS_RS.getString("PROD_COD_BAR_FAB")+"/"+GUIAS_RS.getString("ALT_SERIE")
			    		+"/"+GUIAS_RS.getString("UBI_DESCRIPCION")+"/"+GUIAS_RS.getString("fecha")+"/"+GUIAS_RS.getString("d823_valor")); }
			    GUIAS_RS.close();
			    String[] ar_guias = new String[guias.size()];
			    for(int x=0; x < guias.size(); x++){ ar_guias[x]=guias.get(x);}
			    request.setAttribute("ar_guias", ar_guias);
			    state.close();
			    conexion.close();
			    //-------------------------DETALLE GUIA RESUMEN-------------------------//
			   
			   request.setAttribute("d_id", GID); 
	 		   request.setAttribute("tdte", TDTE);
			}catch(Exception ex){
			    out.println("ERROR: "+ex);
			    ex.printStackTrace();
			}
		//fin grabar
			
		RequestDispatcher rd = request.getRequestDispatcher("N800_I_03_001_823.jsp");
        rd.forward(request, response);
		
		}
		
	}

}
