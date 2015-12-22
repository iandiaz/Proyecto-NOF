

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class ifac1
 */
@WebServlet("/ifac1")
public class ifac1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ifac1() {
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
		if(request.getParameter("logout") !=  null){
				Controlador.eraseCookie(request, response);
				response.sendRedirect("/001/");
				return;
		}
		//fin logout

		String Usuarios_nombre=Controlador.getUsunameSession(request),id_iusuario=Controlador.getUsuIDSession(request);
		

		boolean guiaexiste=false;
		if(request.getParameter("grabar") != null){
			try {
				Statement state = null;

				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		state = (Statement) ((java.sql.Connection) conexion).createStatement();

				//----------------- CABECERA -----------------------//
				String GID=request.getParameter("gv_id");
				
				String CONT_ID=request.getParameter("cont_id");
				
				String fac_servim_condiciones=request.getParameter("fac_servim_condiciones");
				String empresa_id=request.getParameter("empresa_id");
				String tipodte=request.getParameter("tipodte");
				String fac_servim_fecvencimiento_ar[]=request.getParameter("fac_servim_fecvencimiento").toString().split("-");
				String fac_servim_fecvencimiento=fac_servim_fecvencimiento_ar[2]+"-"+fac_servim_fecvencimiento_ar[1]+"-"+fac_servim_fecvencimiento_ar[0];
				String fac_servim_emisor=request.getParameter("fac_servim_emisor");
				String peri_tc_id=request.getParameter("peri_tc_id");
				String fac_servim_n_impresiones=request.getParameter("fac_servim_n_impresiones");
				String fac_servim_obs=request.getParameter("fac_servim_obs");
				String dire_id=request.getParameter("dire_id");
				String total=request.getParameter("total");
				String descuento=request.getParameter("desc");
				String glosa_desc=request.getParameter("glosa_desc");
				
			
				String gv_ciudad=request.getParameter("gv_ciudad");
				
				String empresa_emisora_nombre=request.getParameter("empresa_emisora_nombre");
				
				
				
				
				String fac_servim_neto=request.getParameter("netoneto");
				String fac_servim_totalfinal=request.getParameter("totaltotal");
				String fac_servim_iva=request.getParameter("iva");
				
				String fac_servim_tcambio=request.getParameter("fac_servim_tcambio");
				
				String tipo_dteref=request.getParameter("tipo_dteref");
				String folioref=request.getParameter("folioref");
				String fec_ref=request.getParameter("fec_ref");
				
				String cont_mail=request.getParameter("cont_mail");
				String cont_phone=request.getParameter("cont_phone");
				String cont_nombre=request.getParameter("cont_nombre");
				String resp=request.getParameter("resp");
				
				
				
				if(tipo_dteref==null)tipo_dteref="";
				if(folioref==null)folioref="";
				if(fec_ref==null){fec_ref="NULL";}
				else{ 
					String fecrefar[]=fec_ref.split("-");
					fec_ref ="'"+fecrefar[2]+"-"+fecrefar[1]+"-"+fecrefar[0]+"'";
					}
				
				//--------------------- FIN -----------------------//
				//INSERTAMOS TODOS LOS DATOS EN NOF LA CABECERA DE LA FACTURA
				String fac_servotros_fecfac="now()";
			    String inFolio="";
			    String inFolio2="";
				//si es 30 o 32 deberemos tambien insertar folio y fecha 
				
				if(tipodte.equals("30") || tipodte.equals("32")){
					
					
					
					//insertamos folio
					inFolio=" ,`803`.`803_folio` ";
					inFolio2=" ,'"+request.getParameter("gv_id").toUpperCase()+"'";
				}
				
				///gv_fecha al reves 
				String fec[] =request.getParameter("gv_fecha").split("-");
				fac_servotros_fecfac="'"+fec[2]+"-"+fec[1]+"-"+fec[0]+"'";
				
				//INSERTAMOS TODOS LOS DATOS EN NOF LA CABECERA DE LA FACTURA
			    String SQL_GUIA = ""
			    		+ " INSERT INTO `803` ("
			    		+ "	`803`.`803_fecha`,"
			    		+ "	`803`.`803_condiciones`,"
			    		+ "	`803`.`803_empresa_emisora`,"
			    		+ "	`803`.`peri_tc_id`,"
			    		+ "	`803`.`803_fecvencimiento`,"
			    		+ "	`803`.`803_tipodte`,"
			    		+ "	`803`.`clpr_id`,"
			    		+ "	`803`.`803_obs`,"
			    		+ "	`803`.`803_n_impresiones`, "
			    		+ "	`803`.`dire_id` , "
			    		+ "	`803`.`CONT_ID`, "
			    		+ "	`803`.`803_estado`,  "
			    		+ "	`803`.`803_subtotal`, "
			    		+ "	`803`.`803_creador`, "
			    		+ "	`803`.`803_feccreacion`,"
			    		+ "	`803`.`803_descuento`,"
			    		+ "	`803`.`803_glosa_descuento`,"
						+ "	`803`.`803_neto`,"
						+ "	`803`.`803_total`,"
						+ "	`803`.`803_iva`, "
						+ "	`803`.`803_tipo_cambio`, "
						
						+ "	`803`.`tipo_dteref`, "
						+ "	`803`.`folioref`, "
						+ "	`803`.`fec_ref`,  "
						+ "	`803`.`803_ciudad`,  "
						+ "	`803`.`803_empresa_emisora_nombre`,  "
					
						+ "	`803`.`cont_email`,  "
						+ "	`803`.`cont_telefono`,  "
						+ "	`803`.`cont_nombre`,  "
						+ "	`803`.`803_responsable`  "
						
						
						
						+ inFolio
				    	+ " ) "
			    		+ "	VALUES"
			    		+ "		("+fac_servotros_fecfac+""
			    				+ ", '"+fac_servim_condiciones.toUpperCase()+"'"
			    				+ ", "+fac_servim_emisor+""
			    				+ ", "+peri_tc_id+""
			    				+ ", '"+fac_servim_fecvencimiento+"'"
			    				+ ", "+tipodte+""
			    				+ ", "+empresa_id+""
			    				+ ", '"+fac_servim_obs.toUpperCase()+"'"
			    				+ ", '"+fac_servim_n_impresiones+"'"
			    				+ ",'"+dire_id+"'"
			    				+ ",'"+CONT_ID+"'"
			    				+ ",'NO ENVIADA'"
			    				+ ",'"+total+"'"
			    				+ ",'"+id_iusuario+"'"
			    				+ ",now(),'"+descuento+"'"
			    				+ ",'"+glosa_desc.toUpperCase()+"'"
			    				+ ",'"+fac_servim_neto+"'"
			    				+ ",'"+fac_servim_totalfinal+"'"
			    				+ ",'"+fac_servim_iva+"'"
			    				+ ",'"+fac_servim_tcambio+"'"
			    				+ ",'"+tipo_dteref+"'"
				    			+ ", '"+folioref.toUpperCase()+"'"
				    			+ ","+fec_ref+" "
				    			+ ",'"+gv_ciudad+"' "
				    			+ ",'"+empresa_emisora_nombre+"' "
				    			
				    			+ ",'"+cont_mail+"' "
				    			+ ",'"+cont_phone+"' "
				    			+ ",'"+cont_nombre+"' "
				    			+ ",'"+resp+"' "
				    			
				    			
				    			
				    			
			    				+ ""+inFolio2+" )";
			    System.out.println(SQL_GUIA);
			    state.executeUpdate(SQL_GUIA,Statement.RETURN_GENERATED_KEYS);
			    
			    ResultSet generatedKeys = null;
	    		  generatedKeys = state.getGeneratedKeys();
	    		  //String id_g="";
	    		  if (generatedKeys.next()) {
	    			  GID=generatedKeys.getString(1);
	    		  }
	    		  
	    		  System.out.println("Nuevo id g: "+GID);
	    		  
				//----------------- DETALLE -----------------------//
	    		  
	    		  for(int i=1 ; i<=14; i++){
	    			  if(request.getParameter("serv"+i)!="" && request.getParameter("val"+i)!=""){
	  					String serv1=request.getParameter("serv"+i);
	  					String val1=request.getParameter("val"+i);
	  					String SQL_FACDET = "INSERT INTO detalle_803 (`detalle_803`.`803_id`,`detalle_803`.`d803_valor`,`detalle_803`.`d803_descripcion`) VALUES ("+GID+","+val1+",'"+serv1.toUpperCase()+"')";
	  					 System.out.println(SQL_FACDET);
	  					state.executeUpdate(SQL_FACDET);}
	    		  }
	    		  
				//--------------------- FIN -----------------------//
				
				//insertamos logs
	    		String log_sql=""
	    				+ " INSERT INTO `log` ("
	    				+ "	`log`.`log_fec`, "
	    				+ "	`log`.`log_nombre`, " 
	    				+ "	`log`.`Usuarios_id` "
	    				+ " ) "
	    				+ " VALUES "
	    				+ " (now(), 'USUARIO INGRESA REGISTRO "+GID+" EN TABLA 803', '"+id_iusuario+"') ";
	    		state.executeUpdate(log_sql);
	    		
				
				state.close();
	        	conexion.close();
	        	RequestDispatcher rd_up = request.getRequestDispatcher("menufacservim?Exito=OK");
	        	rd_up.forward(request, response);
	        	//return;
	        	
			}catch(Exception ex){
				ex.printStackTrace();
			    out.println("Error "+ex);
			}
		}else{
		
		request.setAttribute("usuname", Usuarios_nombre);
		Statement state = null;
		Statement statecor = null;		
		ResultSet ESTADOS_RS = null;
		ResultSet EMPRESAS_RS = null;
		ResultSet CLIENTE_RS = null;
		ResultSet CONTACTOS_RS = null;
		ResultSet DIRECCION_RS = null;
		try {
			
			//import java.io.IOException;
			
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		statecor = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
    		//--------------------- ESTADOS ----------------------//
		    
    		String SQL_ESTADOS = "SELECT * FROM estados_vig_novig";
		    ESTADOS_RS =  state.executeQuery(SQL_ESTADOS);
		    ArrayList<String> estados = new ArrayList<String>();
		    while(ESTADOS_RS.next()){ estados.add(ESTADOS_RS.getString("estados_vig_novig_id")+"/"+ESTADOS_RS.getString("estados_vig_novig_nombre")); }
		    ESTADOS_RS.close();
		    String[] ar_estados = new String[estados.size()];
		    for(int x=0; x < estados.size(); x++){ ar_estados[x]=estados.get(x); }
		    request.setAttribute("ar_estados", ar_estados);
		    //----------------------- FIN ------------------------//

    		//--------------------- EMISOR ----------------------//
		    String SQL_EMPRESA = "SELECT * FROM empresas WHERE estados_vig_novig_id = 1 AND empresas_relacionada = 1 ORDER BY empresas_nombre";
		    EMPRESAS_RS =  state.executeQuery(SQL_EMPRESA);
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
		    CLIENTE_RS =  state.executeQuery(SQL_CLIENTE);
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
		    //----------------------- FIN ------------------------//
		    
    		//--------------------- CONTACTO ----------------------//
		    String SQL_CONTACTO = "SELECT cont_id,CLPR_ID,PERS_ID,CONT_TELEFONO,CONT_EMAIL,CONT_TELEFONOC, CONCAT_WS(' ',CONT_NOMBRE,CONT_APEP,CONT_APEM) AS CONT_NOMBRE  FROM contacto ORDER BY cont_nombre";
		    CONTACTOS_RS =  state.executeQuery(SQL_CONTACTO);
		    ArrayList<String> contactos = new ArrayList<String>();
		    while(CONTACTOS_RS.next()){ contactos.add(	CONTACTOS_RS.getString("cont_id")+"/"+
					CONTACTOS_RS.getString("CONT_NOMBRE").replace("/", " ")+"/"+
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
		    DIRECCION_RS =  state.executeQuery(SQL_DIRECCION);
		    ArrayList<String> direcciones = new ArrayList<String>();
		    while(DIRECCION_RS.next()){
		    	direcciones.add(DIRECCION_RS.getString("dire_id")+"/"+DIRECCION_RS.getString("DIRE_DIRECCION").replace("/", " ")+"/"+DIRECCION_RS.getString("CLPR_ID")+"/"+DIRECCION_RS.getString("REGI_NOMBRE")+"/"+DIRECCION_RS.getString("DIRE_CIUDAD")+"/"+DIRECCION_RS.getString("COMU_NOMBRE")); }
		    DIRECCION_RS.close();
		    String[] ar_direcciones = new String[direcciones.size()];
		    for(int x=0; x < direcciones.size(); x++){ ar_direcciones[x]=direcciones.get(x);}
		    request.setAttribute("ar_direcciones", ar_direcciones);
		    //----------------------- FIN ------------------------//
		  
		    
		  //--------------------- PERIODOS ----------------------//
		    String SQL_PERIODOS = "SELECT"
		    		+ "		`periodos_tc`.`peri_tc_id`,"
		    		+ "		`periodos_tc`.`peri_tc_id_emp`,"
		    		+ "		`periodos_tc`.`peri_tc_correlativo`,"
		    		+ "		DATE_FORMAT(`periodos_tc`.`peri_tc_fdesde`,'%d-%m-%Y') AS peri_tc_fdesde,"
		    		+ "		DATE_FORMAT(`periodos_tc`.`peri_tc_fhasta`,'%d-%m-%Y') AS peri_tc_fhasta"
		    		+ "	FROM"
		    		+ "		`periodos_tc` ";
		    		//+ "	LEFT JOIN `803` ON `803`.`peri_tc_id` = `periodos_tc`.`peri_tc_id`";
		    	//	+ "	WHERE `periodos_tc`.`peri_tc_id_estperiodo`=1 OR "
		    		//+ "		`factura_servim`.`fac_servim_id` IS NULL";
		    
		    System.out.println(SQL_PERIODOS);
		    ResultSet PERIODOS_RS = state.executeQuery(SQL_PERIODOS);
		    ArrayList<String> periodos = new ArrayList<String>();
		    while(PERIODOS_RS.next()){ 
		    	periodos.add(PERIODOS_RS.getString("peri_tc_id")+"/"+PERIODOS_RS.getString("peri_tc_id_emp")+"/"+PERIODOS_RS.getString("peri_tc_correlativo")+"/"+PERIODOS_RS.getString("peri_tc_fdesde").substring(0,10)+"/"+PERIODOS_RS.getString("peri_tc_fhasta").substring(0,10)); }
		    PERIODOS_RS.close();
		    String[] ar_periodos = new String[periodos.size()];
		    for(int x=0; x < periodos.size(); x++){ ar_periodos[x]=periodos.get(x);}
		    request.setAttribute("ar_periodos", ar_periodos);
		    //----------------------- FIN ------------------------//
		    
		    
		    //--------------------- FOLIOS ----------------------//
		    String SQL_FOLIOS = "SELECT "
		    		+ "		`803`.`803_folio`"
		    		+ "	FROM"
		    		+ "		`803`"
		    		+ "	WHERE"
		    		+ "		`803`.`803_folio` IS NOT NULL";
		    
		    System.out.println(SQL_FOLIOS);
		    ResultSet FOLIOS_RS = state.executeQuery(SQL_FOLIOS);
		    ArrayList<String> folios = new ArrayList<String>();
		    while(FOLIOS_RS.next()){ 
		    	folios.add(FOLIOS_RS.getString("803_folio")); }
		    FOLIOS_RS.close();
		    String[] ar_folios = new String[folios.size()];
		    for(int x=0; x < folios.size(); x++){ ar_folios[x]=folios.get(x);}
		    request.setAttribute("ar_folios", ar_folios);
		    //----------------------- FIN ------------------------//
		     
            //-------------------------NOW-------------------------//
            state = (Statement) ((java.sql.Connection) conexion).createStatement();
            
            
            String fec_sql="SELECT DATE_FORMAT(now(), '%d-%m-%Y') as fecha ";
            ResultSet fec_rs = state.executeQuery(fec_sql);
         
            fec_rs.next();
            
            request.setAttribute("fecha", fec_rs.getString("fecha"));
            
            String sql_percontable="SELECT "
					+" MIN(`adm_periodos_contables`.`admpc_year`) AS minyear,"
					+" MIN(`adm_periodos_contables`.`admpc_mes`) AS minmes,"
					+" MAX(`adm_periodos_contables`.`admpc_year`) AS maxyear,"
					+" MAX(`adm_periodos_contables`.`admpc_mes`) AS maxmes"
				+" FROM"
					+" `adm_periodos_contables`"
				+" WHERE"
					+" `adm_periodos_contables`.`estados_vig_novig_id` = 1";

				ResultSet RS_percontable = state.executeQuery(sql_percontable);
				
				
				RS_percontable.next();
				request.setAttribute("minyear", RS_percontable.getString("minyear"));
				request.setAttribute("minmes", RS_percontable.getString("minmes"));
				request.setAttribute("maxyear", RS_percontable.getString("maxyear"));
				request.setAttribute("maxmes", RS_percontable.getString("maxmes"));

            
            statecor.close();
            conexion.close();
		}catch(Exception ex){
		    out.println("Error "+ex);
		    ex.printStackTrace();
		}
		String msg="";
		if(guiaexiste){
			msg="?Exito=NOK";
			String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
			String gv_id = request.getParameter("gv_id");
			request.setAttribute("gv_id",gv_id);
			request.setAttribute("estados_vig_novig_id",estados_vig_novig_id);
			//System.out.println("grupos_nombre "+grupos_nombre);
		}
		RequestDispatcher rd = request.getRequestDispatcher("ifac1.jsp");
        rd.forward(request, response);
		}
	}

}
