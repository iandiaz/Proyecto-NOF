
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
 * Servlet implementation class iguias_ventaserv
 */
@WebServlet("/iguias_ventaserv")
public class iguias_ventaserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public iguias_ventaserv() {
        super();
        // 
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
		if(Controlador.validateSession(request, response)){
			
			 mt(request,response);
		}
		else response.sendRedirect("/001/");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
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
				String FID=request.getParameter("d_id");
				String TDTE=request.getParameter("tdte");
						
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
			    Connection conexion=(Connection) DriverManager.getConnection
			    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
			    state = (Statement) ((java.sql.Connection) conexion).createStatement();
				stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
				Statement stateFolio=(Statement) ((java.sql.Connection) conexion).createStatement();
				Statement state2=(Statement) ((java.sql.Connection) conexion).createStatement();
					
				//System.out.println("Docs: "+FID);
				
				  String SQL_DATOS = "SELECT DATE_FORMAT(822_FECHA, '%Y-%m-%d') as gd_fecha,"
			    			+ " g.822_FOLIO AS GD_FOLIO,			e2.empresas_nombre as emi,	v.estados_vig_novig_nombre,	e.empresas_nombre,	e.empresas_rut,"
			    			+ " e.empresas_id,		e.empresas_razonsocial,		d.DIRE_DIRECCION, 			r.REGI_NOMBRE,		d.DIRE_CIUDAD ,"
			    			+ " c.CONT_NOMBRE, 		c.CONT_TELEFONO,			c.CONT_EMAIL,			g.822_responsable_name AS GD_RESPONSBALE, 		"
			    			
			    			+ " g.822_obs1 AS guia_obs1, g.822_empresa_emisora, 		g.822_obs2 AS guia_obs2, 				o.COMU_NOMBRE , 			e.empresas_giro, "
			    			
			    			+ " CONCAT(u.Usuarios_nombre,' ',u.Usuarios_ape_p) AS perfilusu_creador,"
				    		+ "	DATE_FORMAT(g.822_feccreacion,'%d-%m-%Y %H:%i:%s') AS perfilusu_feccreacion,"
				    		+ "	IF (g.822_fecmod IS NULL,' ',DATE_FORMAT(g.822_fecmod,'%d-%m-%Y %H:%i:%s')) AS perfilusu_fecmod,"
				    		+ "	IF (g.822_modificador IS NULL,' ',CONCAT(u2.Usuarios_nombre,' ',u2.Usuarios_ape_p)) AS perfilusu_modificador, "
				    		+ " IF(g.id_dte is null,'1','0') as dte, "
				    		+ "	g.tipo_guia_serv,  "
				    		+ " 822_subtotal,822_neto,822_iva,822_total,822_descuento,822_glosa_descuento,  "
				    		
				    		+ "		CONCAT_WS(' ',u3.`Usuarios_nombre`,u3.`Usuarios_ape_p`,u3.`Usuarios_ape_m`) AS Usuarios_nombre_com "
				    		
				    		+ " , if(g.`tipo_dteref` is null,'',g.`tipo_dteref`) as  tipo_dteref  "
							+ " , g.folioref "
							+ " , DATE_FORMAT(g.fec_ref,'%Y-%m-%d') as fec_ref  "
							+ "	, g.822_afecta as g_afecta "
			    			
			    			+ " FROM `822` g"
			    			+ " LEFT JOIN empresas e ON g.CLPR_ID = e.empresas_id"
			    			+ " LEFT JOIN empresas e2 ON g.822_empresa_emisora = e2.empresas_id "
			    			+ " LEFT JOIN direccion d ON g.DIRE_ID = d.DIRE_ID"
			    			+ " LEFT JOIN comuna o ON d.COMU_ID = o.COMU_ID"
			    			+ " LEFT JOIN region r ON o.REGI_ID = r.REGI_ID"
		 	    			+ " LEFT JOIN contacto c ON g.CONT_ID = c.CONT_ID "
		 	    			+ " INNER JOIN estados_vig_novig v ON g.estados_vig_novig_id = v.estados_vig_novig_id "
		 	    			
							+ " INNER JOIN usuarios u ON g.822_creador = u.Usuarios_id "
							+ " LEFT JOIN usuarios u2 ON g.822_modificador = u2.Usuarios_id "
							+ " INNER JOIN `usuarios` u3 ON u3.`Usuarios_id` = e.`responsable_id` "
					    	

		 	    			+ " WHERE 822_id = "+FID;
				System.out.println(SQL_DATOS);
				RS_DATOS =  state.executeQuery(SQL_DATOS);
				
				RS_DATOS.next();
				String tipodte="52";
				
				String OBS0 = RS_DATOS.getString("guia_obs1").toUpperCase();
				
				String OBS1 = RS_DATOS.getString("CONT_NOMBRE").replace(";", "")+"-"+RS_DATOS.getString("CONT_EMAIL").replace(";", "");
				String OBS2 = RS_DATOS.getString("guia_obs2").toUpperCase();
			
				
				String SUBTOTAL=RS_DATOS.getString("822_subtotal");
		    	String desc=RS_DATOS.getString("822_descuento");
		    	String NETO=RS_DATOS.getString("822_neto");
		    	String IVA=RS_DATOS.getString("822_iva");
		    	String TOTAL=RS_DATOS.getString("822_total");
		    	String glosa_desc=RS_DATOS.getString("822_glosa_descuento");
		    	String id_emp_emisora = RS_DATOS.getString("822_empresa_emisora");
		    	
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
			    		"52;"+folio+";"+RS_DATOS.getString("gd_fecha")+";0;0;"+
			    		RS_DATOS.getString("empresas_rut")+";"+RS_DATOS.getString("empresas_razonsocial").replace(";", "")+";"+RS_DATOS.getString("empresas_giro").replace(";", "")+";"+
			    		RS_DATOS.getString("DIRE_DIRECCION").replace(";", "")+";"+RS_DATOS.getString("COMU_NOMBRE")+";"+RS_DATOS.getString("DIRE_CIUDAD")+";"+
			    		RS_DATOS.getString("CONT_EMAIL")+";\r\n";
			    System.out.println(encabezado);
			    
			    
			    String SQL_INSERTe = "INSERT INTO dte_encabezado (TipoDTE, Folio,FechaEmision, TipoDespacho, IndTraslado, "
			    		+ "RUTCliente, RSCliente, GiroCliente, "
			    		+ "DirCliente, ComCliente, CiuCliente, Email,empresa_emisora_id,Modulo,Codigo_relacionado,cliente_id) "
			    		+ "VALUES ('52','"+folio+"','"+RS_DATOS.getString("gd_fecha")+"','0','0'"
			    		+ ",'"+RS_DATOS.getString("empresas_rut")+"','"+RS_DATOS.getString("empresas_razonsocial")+"','"+RS_DATOS.getString("empresas_giro")+"'"
			    		+ ",'"+RS_DATOS.getString("DIRE_DIRECCION")+"','"+RS_DATOS.getString("COMU_NOMBRE")+"','"+RS_DATOS.getString("DIRE_CIUDAD")+"'"
			    		+ ",'"+RS_DATOS.getString("CONT_EMAIL")+"','"+id_emp_emisora+"','822','"+FID+"','"+RS_DATOS.getString("empresas_id")+"')";
			    //System.out.println("SQL Instert: "+SQL_INSERTe);
			    stategrabar.executeUpdate(SQL_INSERTe,Statement.RETURN_GENERATED_KEYS);

			    ResultSet generatedKeys = null;
	    		generatedKeys = stategrabar.getGeneratedKeys();
	    		String id_usu_last="";
	    		if (generatedKeys.next()) {
	    			id_usu_last=generatedKeys.getString(1);
	    		}
			    //stategrabar.close();
			  
			    
			    String SQL_UPFAC = "UPDATE `822` SET id_dte = "+id_usu_last+" WHERE 822_id = "+FID;
			    //System.out.println("SQL Instert: "+SQL_UPFAC);
			    stategrabar.executeUpdate(SQL_UPFAC);

			    String SQL_DET = "SELECT * FROM detalle_822 WHERE 822_id = "+FID+" ORDER BY d822_id";
			    System.out.println(SQL_DET);
				   		RS_DET =  state.executeQuery(SQL_DET);
	    
				   		
				   		
				   		
				   		String SQL_DET2 = "SELECT count(*) as n"
								+ " FROM detalle_822 d "
								//+ " INNER JOIN activo ON d.ALT_ID = activo.ALT_ID"
								//+ " INNER JOIN producto ON activo.PROD_ID = producto.PROD_ID"
								+ " WHERE d.822_ID= "+FID;
					
						ResultSet RS_DET2 = state2.executeQuery(SQL_DET2);
						
						
						RS_DET2.next();
						
						int numlines=RS_DET2.getInt("n");
				Integer c=1; 
				String detalle="->Detalle<-\r\n";
				while(RS_DET.next()){
					
					String descLarga="";
					if(Integer.parseInt(desc)>0 && c==numlines)descLarga=glosa_desc+" $"+desc;
				
					String detexento="0";
					if(g_afecta.equals("0")) detexento=RS_DET.getString("d822_valor");
					
					
					detalle+=c+";SERV;"+RS_DET.getString("d822_text").replace(";", "").toUpperCase()+";1;"
		            		+ RS_DET.getString("d822_valor")+";0;0;0;0;"+detexento+";"
		            		+ RS_DET.getInt("d822_valor")+";INT1;UN;"
		            		+ descLarga.toUpperCase()+";\r\n";
					
					String SQL_INSERTd = "INSERT INTO dte_detalle (id_enc, NroLinea, Codigo, Descripcion, Cantidad, "
							+ "Precio, PorcDescto, ValorDescto, PorcRecgo, ValorRecgo, ValorExento, Valor, "
							+ "TipoCodigo, UniMedida, DescLarga) "
				    		+ "VALUES ("+id_usu_last+","+c+",'SERV','"+RS_DET.getString("d822_text")+"',1,"
				    		+ ""+RS_DET.getString("d822_valor")+",0,0,0,0,"+detexento+","+RS_DET.getString("d822_valor")+","
				    		+ "'INT1','UN','"+descLarga.toUpperCase()+"')";
				    //NroLinea;Codigo;Descripcion;Cantidad;Precio;PorcDescto;ValorDescto;PorcRecgo;ValorRecgo;ValorExento;Valor;TipoCodigo;UniMedida;DescLarga;
				 
				    stategrabar.executeUpdate(SQL_INSERTd);
					c=c+1;
				}
				
				   System.out.println(detalle);
				
					
					String ivapercent="19";
					String exento="0";
					if(g_afecta.equals("0")){
						ivapercent="0";
						exento=NETO;
						NETO="0";
					} 
				
					String totales="->Totales<-\r\n"+"0;0;0;0;"+NETO+";"+exento+";"+ivapercent+";"+IVA+";"+TOTAL+";\r\n";
					System.out.println(totales);
				//PorcDesctoTot;ValorDesctoTot;PorcRecgoTot;ValorRecgoTot;Neto;Exento;TasaIVA;IVA;Total;
			    String SQL_INSERTt = "INSERT INTO dte_totales (id_enc, PorcDesctoTot, ValorDesctoTot, PorcRecgoTot, ValorRecgoTot, "
			    		+ "Neto, Exento, TasaIVA, IVA, Total)"
			    		+ " VALUES ("+id_usu_last+",0,0,0,0,"+NETO+",0,"+ivapercent+","+IVA+","+TOTAL+")";
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
			    System.out.println(obs);
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
	        	
			    Writer outFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(""+directorio+tipodte+"_822_"+FID+"_"+folio+"_"+id_emp_emisora+".txt"),"UTF-8"));
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
			    
			    String SQL_UPENC = "UPDATE dte_encabezado SET Total = '"+TOTAL+"' WHERE id_dte = "+id_usu_last;
			    System.out.println("SQL Instert: "+SQL_UPENC);
			    stategrabar.executeUpdate(SQL_UPENC);
			    
			    
			    String SQL_822 = "UPDATE `822` SET 822_folio = '"+folio+"' WHERE 822_ID = "+FID;
			    System.out.println("SQL Instert: "+SQL_822);
			    stategrabar.executeUpdate(SQL_822);
			    
			    
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

	    		
	    		  String SQL_CAB = "SELECT DATE_FORMAT(822_FECHA, '%d-%m-%Y') as gd_fecha,"
			    			+ " g.822_FOLIO as GD_FOLIO,			"
			    			+ "	g.822_empresa_emisora_nombre as emi,	"
			    			+ "	v.estados_vig_novig_nombre,	"
			    			+ "	e.empresas_nombre,	"
			    			+ "	e.empresas_rut,"
			    			+ " e.empresas_id,		"
			    			+ "	e.empresas_razonsocial,		"
			    			+ "	d.DIRE_DIRECCION, 			"
			    			+ "	r.REGI_NOMBRE,		"
			    			+ "	d.DIRE_CIUDAD AS GD_CIUDAD,"
			    			+ " g.CONT_NOMBRE, 		g.CONT_TELEFONO,	g.CONT_EMAIL, g.CONT_TELEFONOC,"
			    			+ "	g.822_responsable_name as GD_RESPONSBALE, 			"
			    			+ " g.822_obs1 as guia_obs1,"
			    			+ "	g.822_obs2 as guia_obs2, 				"
			    			+ "	o.COMU_NOMBRE , "
			    			
			    			+ " CONCAT(u.Usuarios_nombre,' ',u.Usuarios_ape_p) AS perfilusu_creador,"
				    		+ "	DATE_FORMAT(g.822_feccreacion,'%d-%m-%Y %H:%i:%s') AS perfilusu_feccreacion,"
				    		+ "	IF (g.822_fecmod IS NULL,' ',DATE_FORMAT(g.822_fecmod,'%d-%m-%Y %H:%i:%s')) AS perfilusu_fecmod,"
				    		+ "	IF (g.822_modificador IS NULL,' ',CONCAT(u2.Usuarios_nombre,' ',u2.Usuarios_ape_p)) AS perfilusu_modificador, "
				    		+ " IF(g.id_dte is null,'1','0') as dte, "
				    		+ "	g.tipo_guia_serv,  "
				    		+ " 822_subtotal,822_neto,822_iva,822_total,822_descuento,822_glosa_descuento,  "
				    		
				    		+ "	CONCAT_WS(' ',u3.`Usuarios_nombre`,u3.`Usuarios_ape_p`,u3.`Usuarios_ape_m`) AS Usuarios_nombre_com ,"
				    		+ "		g.822_afecta as g_afecta, "
				    		
						+ "		IF(`g`.`tipo_dteref` is null,'NINGUNA',`g`.`tipo_dteref`) as tipo_dteref, "
						+ "		IF(`g`.`folioref` is null ,'',`g`.`folioref`) as  folioref, "
						+ "		IF(`g`.`fec_ref` is null,'',DATE_FORMAT(`g`.`fec_ref`,'%d-%m-%Y')) as fec_ref  "

			    			
			    			+ " FROM `822` g"
			    			+ " LEFT JOIN empresas e ON g.CLPR_ID = e.empresas_id"
			    			+ " LEFT JOIN direccion d ON g.DIRE_ID = d.DIRE_ID"
			    			+ " LEFT JOIN comuna o ON d.COMU_ID = o.COMU_ID"
			    			+ " LEFT JOIN region r ON o.REGI_ID = r.REGI_ID"
		 	    			+ " LEFT JOIN contacto c ON g.CONT_ID = c.CONT_ID "
		 	    			+ " INNER JOIN estados_vig_novig v ON g.estados_vig_novig_id = v.estados_vig_novig_id "
		 	    			
							+ " INNER JOIN usuarios u ON g.822_creador = u.Usuarios_id "
							+ " LEFT JOIN usuarios u2 ON g.822_modificador = u2.Usuarios_id "
							+ " INNER JOIN `usuarios` u3 ON u3.`Usuarios_id` = e.`responsable_id` "
					    	

		 	    			+ " WHERE 822_id = "+GID;
	    		
	    		
			  
			    System.out.println(SQL_CAB);
			    ResultSet CAB_RS = state.executeQuery(SQL_CAB);
			    if(CAB_RS.next()){
			    	request.setAttribute("gd_id", GID);
			    	request.setAttribute("gd_fecha", CAB_RS.getString("gd_fecha"));
			    	request.setAttribute("GD_FOLIO", CAB_RS.getString("GD_FOLIO"));
			    	request.setAttribute("emi", CAB_RS.getString("emi"));
			    	request.setAttribute("estados_vig_novig_nombre", CAB_RS.getString("estados_vig_novig_nombre"));
			    	request.setAttribute("empresas_nombre", CAB_RS.getString("empresas_nombre"));
			    	request.setAttribute("empresas_rut", CAB_RS.getString("empresas_rut"));
			    	request.setAttribute("empresas_id", CAB_RS.getString("empresas_id"));
			    	request.setAttribute("empresas_razonsocial", CAB_RS.getString("empresas_razonsocial"));
			    	request.setAttribute("DIRE_DIRECCION", CAB_RS.getString("DIRE_DIRECCION"));
			    	request.setAttribute("REGI_NOMBRE", CAB_RS.getString("REGI_NOMBRE"));
			    	request.setAttribute("GD_CIUDAD", CAB_RS.getString("GD_CIUDAD"));
			    	request.setAttribute("CONT_NOMBRE", CAB_RS.getString("CONT_NOMBRE"));
			    	request.setAttribute("CONT_TELEFONO", CAB_RS.getString("CONT_TELEFONO"));
			    	request.setAttribute("CONT_EMAIL", CAB_RS.getString("CONT_EMAIL"));
			    	request.setAttribute("CONT_TELEFONOC", CAB_RS.getString("CONT_TELEFONOC"));
			    	request.setAttribute("GD_RESPONSBALE", CAB_RS.getString("GD_RESPONSBALE"));
			    	request.setAttribute("guia_obs1", CAB_RS.getString("guia_obs1"));
			    	request.setAttribute("guia_obs2", CAB_RS.getString("guia_obs2"));
			    	
			    	request.setAttribute("fec_crea", CAB_RS.getString("perfilusu_feccreacion"));
			    	request.setAttribute("fec_mod", CAB_RS.getString("perfilusu_fecmod"));
			    	request.setAttribute("user_mod", CAB_RS.getString("perfilusu_modificador"));
			    	request.setAttribute("user_crea", CAB_RS.getString("perfilusu_creador"));
			    	String estado_sii = "NO ENVIADA";
			    	if(CAB_RS.getString("dte").equals("0")){estado_sii = "ENVIADA";}
					if(CAB_RS.getString("dte").equals("1")){estado_sii = "PARA ENVIAR";}
			    	request.setAttribute("estado_sii", estado_sii);
			    	request.setAttribute("PERS_NOMBRE", CAB_RS.getString("Usuarios_nombre_com"));
			    	request.setAttribute("GD_COMUNA", CAB_RS.getString("COMU_NOMBRE"));
			    	request.setAttribute("tipo_guia_serv", CAB_RS.getString("tipo_guia_serv"));
			    	String SUBTOTAL=CAB_RS.getString("822_subtotal");
			    	String desc=CAB_RS.getString("822_descuento");
			    	String NETO=CAB_RS.getString("822_neto");
			    	String IVA=CAB_RS.getString("822_iva");
			    	String TOTAL=CAB_RS.getString("822_total");
			    	String glosa_desc=CAB_RS.getString("822_glosa_descuento");
			    	
	            	 request.setAttribute("subtotal", SUBTOTAL);
	    	 		 request.setAttribute("descuento", desc);
	    	 		request.setAttribute("neto", NETO);
	    	 		request.setAttribute("iva", IVA);
	    	 		request.setAttribute("total", TOTAL);
	    	 		request.setAttribute("glosadescuento", glosa_desc);
	    	 		
	    	 		request.setAttribute("g_afecta",CAB_RS.getString("g_afecta"));
	    	 		
	    	 		
	    	 		request.setAttribute("fec_ref", CAB_RS.getString("fec_ref"));
	    	    	request.setAttribute("folioref", CAB_RS.getString("folioref"));
	    	    	String tipo_dteref=CAB_RS.getString("tipo_dteref").equals("") ? "NINGUNA": CAB_RS.getString("tipo_dteref");
	    	    	request.setAttribute("tipo_dteref", tipo_dteref);
	    	 		
			    }
			    CAB_RS.close();
			    
			    String SQL_DET = "SELECT * FROM detalle_822 WHERE 822_id = "+GID+" ORDER BY d822_id";
			    ResultSet DET_RS = state.executeQuery(SQL_DET);
			    ArrayList<String> prod = new ArrayList<String>();
			    Integer c=0;
			    while(DET_RS.next()){ prod.add(c+"/"+DET_RS.getString("d822_text")+"/"+DET_RS.getString("d822_valor")); c=c+1;}
			    DET_RS.close();
			    String[] ar_prod = new String[prod.size()];
			    for(int x=0; x < prod.size(); x++){ ar_prod[x]=prod.get(x); }
			    request.setAttribute("ar_prod", ar_prod);
			   
	           state.close();
	            conexion.close();
			}catch(Exception ex){
			    out.println("ERROR: "+ex);
			    ex.printStackTrace();
			}
		//fin grabar
			
		RequestDispatcher rd = request.getRequestDispatcher("iguias_ventaserv.jsp");
        rd.forward(request, response);
		
		}
		
	}

}
