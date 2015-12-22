
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Constantes.Constantes;
import Constantes.Controlador;
import DAO.ContactosDAO;
import VO.ContactoVO;
import VO.EmpresaVO;
import VO.EstadosVigNoVigVO;

/**
 * Servlet implementation class iguia
 */
@WebServlet("/I02_001")
public class I02_001 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public I02_001() {
        super();
        // TODO Auto-generated constructor stub
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
		
		if(request.getParameter("logout") !=  null){
				Controlador.eraseCookie(request, response);
				response.sendRedirect("/001/");	
				return;
		}
		
		//////////////////////////////////////////////////
		////////VERIFICAMOS PERMISO ASOCIADO/////////////

		String p=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), Constantes.PINGRESAR);
		
		if(p.equals("0")){
			response.sendRedirect("/001/");	
			return;
		}
		
		//////////////////////////////////////////////////
		////////DEFINE PARAMETROS DE USUARIO//////////////
		
		request.setAttribute("modname", Controlador.getNameModulo());
		
		String Perfil_id=Controlador.getPerfilIDSession(request);
		
		request.setAttribute("usuname", Controlador.getUsunameSession(request));
		////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////
		
		//grabar
		boolean guiaexiste=false;
		if(request.getParameter("grabar") != null){
			Statement stategrabar = null;	
			try {
				Statement state = null;
				Statement state_fac = null;
				Statement state_facdet = null;
				Statement state_ins = null;
				Statement state_des = null;
				
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
				stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
				state_fac = (Statement) ((java.sql.Connection) conexion).createStatement();
				state_facdet = (Statement) ((java.sql.Connection) conexion).createStatement();
				state_ins = (Statement) ((java.sql.Connection) conexion).createStatement();
				state_des = (Statement) ((java.sql.Connection) conexion).createStatement();
				
			    //DATOS DE LA TABLA FACTURA
				String GID="";
				String clientes_id = request.getParameter("clientes_id");
				String empresas_id = request.getParameter("empresas_id");
				String cond = request.getParameter("condiciones");
				String did = request.getParameter("did");
				String cont_id = request.getParameter("cont_id");
				String ref = request.getParameter("ref");
				String obs = request.getParameter("obs");
				String resp = request.getParameter("resp");
				
				String SUBTOTAL=request.getParameter("subtotal");
		        String NETO=request.getParameter("neto");
	            String IVA=request.getParameter("iva");
	            String TOTAL=request.getParameter("total");
	            String desc=request.getParameter("desc");
	            String glosa_desc=request.getParameter("glosa_desc");
	            
	            
	            String gv_ciudad= request.getParameter("gv_ciudad");
				String empresa_emisora_nombre= request.getParameter("empresa_emisora_nombre");
				String cont_nombre= request.getParameter("cont_nombre");
				String cont_telefonoc=request.getParameter("cont_telefonoc");
				String cont_email=request.getParameter("cont_email");
				String cont_phone=request.getParameter("cont_phone");
				
	            
				String obs2 = "SOLO TRASLADO NO CONSTITUYE VENTA";
				
				String dire_id=request.getParameter("o_id");
				String dire_id2=request.getParameter("d_id");
				
				String fec[] =request.getParameter("gv_fecha").split("-");
				String gv_fecha="'"+fec[2]+"-"+fec[1]+"-"+fec[0]+"'";
				
				
				String tipo_dteref=request.getParameter("tipo_dteref");
				String folioref=request.getParameter("folioref");
				String fec_ref=request.getParameter("fec_ref");
				
				
				if(tipo_dteref==null)tipo_dteref="";
				if(folioref==null)folioref="";
				if(fec_ref==null){fec_ref="NULL";}
				else{ 
					String fecrefar[]=fec_ref.split("-");
					fec_ref ="'"+fecrefar[2]+"-"+fecrefar[1]+"-"+fecrefar[0]+"'";
					}
				
				String g_afecta=request.getParameter("g_afecta");
				if(g_afecta==null || g_afecta.equals(""))g_afecta="0";
				
				String SQL_FACDET = "INSERT INTO `823` (clientes_id, dire_id, dire_id2, 823_empresa_emisora,"
						+ " cont_id"
						+ "	,823_numticket, 823_obs, 823_obs2"
						+ "	,823_feccreacion,"
						+ " 823_creador"
						+ "	,estados_vig_novig_id, 823_fechaemision"
						+ "	,823_responsable_name"
						+ "	,823_SUBTOTAL"
			    		+ "	,823_TOTAL"
			    		+ "	,823_descuento"
			    		+ "	,823_glosa_descuento"
			    		+ "	,823_NETO"
			    		+ "	,823_IVA"
			    		+ "	,823_ciudad"
			    		+ "	,823_empresa_emisora_nombre"
			    		+ "	,cont_nombre"
			    		+ "	,cont_telefonoc"
			    		+ "	,cont_email"
			    		+ "	,cont_telefono"
			    		+ "	,823_fecha"
			    		
			    		
			    		+ "	,`823`.`tipo_dteref` "
						+ "	,`823`.`folioref` "
						+ "	,`823`.`fec_ref` "
						+ "	,`823`.`823_afecta`  "
			    		
						+ ") "
				    	+ "VALUES ("+clientes_id+","+dire_id+","+dire_id2+","+empresas_id+","+cont_id+",'"+ref.toUpperCase()+"','"+obs.toUpperCase()+"','"+obs2.toUpperCase()+"',"
				    	+ "now(),"+Controlador.getUsuIDSession(request)+",1,now()"
				    	+ ",'"+resp+"'"
				    	+ ",'"+SUBTOTAL+"'"
				    	+ ",'"+TOTAL+"'"
				    	+ ",'"+desc+"'"
				    	+ ",'"+glosa_desc.toUpperCase()+"'"
				    	+ ",'"+NETO+"'"
				    	+ ",'"+IVA+"'"
				    	+ ",'"+gv_ciudad+"','"+empresa_emisora_nombre+"','"+cont_nombre+"','"+cont_telefonoc+"','"+cont_email+"'"
				    	+ ",'"+cont_phone+"'"
				    	+ ","+gv_fecha+""
				    	+ ",'"+tipo_dteref+"'"
				    	+ ", '"+folioref.toUpperCase()+"'"
				    	+ ","+fec_ref+" "
				    	+ ",'"+g_afecta+"' "
		    		
				    	+ ")";
				System.out.println(SQL_FACDET);
				state.executeUpdate(SQL_FACDET,Statement.RETURN_GENERATED_KEYS);
			    
			    ResultSet generatedKeys = null;
			    generatedKeys = state.getGeneratedKeys();
			    //String id_g="";
			    if (generatedKeys.next()) {
			    	GID=generatedKeys.getString(1);
	    		}
			   
	    		String[] seleccionado_detguias = request.getParameterValues("tras_id[]");
	    		if(seleccionado_detguias!=null)
	    			//for (String id_gi: seleccionado_detguias) {
		    		//	if(id_gi!=null && !id_gi.equals("-1")){
		    				for(int i =0; i<seleccionado_detguias.length; i++){
			    				String[] Guias = seleccionado_detguias[i].split(";;");
			    				String fec_ar[]=Guias[6].split("-");
			    				String SQL_GUIADET = "INSERT INTO detalle_823"
			    						+ "(823_id, alt_id, PROD_PN_TLI_CODFAB, PROD_DESC_CORTO, PROD_COD_BAR_FAB,"
			    						+ " ALT_SERIE, UBI_DESCRIPCION, clientes_id, estados_vig_novig_id, TRAS_FECHA,tras_id,ubi_id,tick_id,d823_valor) "
				  	    				+ "VALUES "
				  	    				
				  	    				+ "("+GID+","+Guias[0]+",'"+Guias[2]+"','"+Guias[1]+"','"+Guias[3]+"','"+Guias[4]+"','"+Guias[5]+"',"
				  	    						+ ""+clientes_id+",1"
				  	    								+ ",'"+fec_ar[0]+"-"+fec_ar[1]+"-"+fec_ar[2]+"','"+Guias[7]+"','"+Guias[8]+"','"+Guias[9]+"','"+Guias[10]+"'"
				  	    										+ ")";
			    				System.out.println(SQL_GUIADET);
				      			state_ins.executeUpdate(SQL_GUIADET);
		    				}
		    		//	}
		    		//}
	    		
	    		
	    		 //insertamos logs
	    		String log_sql=""
	    				+ " INSERT INTO `log` ("
	    				+ "	`log`.`log_fec`, "
	    				+ "	`log`.`log_nombre`, " 
	    				+ "	`log`.`Usuarios_id` "
	    				+ " ) "
	    				+ " VALUES "
	    				+ " (now(), 'USUARIO INGRESA REGISTRO "+GID+" A LA TABLA 823', '"+Controlador.getUsuIDSession(request)+"') ";
	    		state.executeUpdate(log_sql);
	        	
	        	RequestDispatcher rd_up = request.getRequestDispatcher("menuguia?Exito=OK");
	        	rd_up.forward(request, response);
	        	//return;
	        	
			}catch(Exception ex){
				ex.printStackTrace();
			    out.println("ERROR "+ex);
			}
			
		}else{
		
			try {
				Statement state = null;
				ResultSet CLIENTE_RS = null;
				ResultSet EMPRESAS_RS = null;
				ResultSet CONTACTOS_RS = null;
				ResultSet GUIAS_RS = null;
				
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		String condiciones=request.getParameter("condiciones");
	    		String ref=request.getParameter("ref");
	    		String obs=request.getParameter("obs");
	    		
	    		request.setAttribute("condiciones",condiciones);
	    		request.setAttribute("ref",ref);
	    		request.setAttribute("obs",obs);
	    		
	    		String EID = request.getParameter("clientes_id");
	    		String DID = request.getParameter("did");
	    		request.setAttribute("did",DID);
	    		request.setAttribute("clientes_id",EID);
	    		
	    		ConBirt birtBD2= new ConBirt();
	    		
	    		String id_emp_destino = null;
	    		
	    		
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

			   
			    //--------------------- EMPRESA ORIGEN ----------------------//
			    String SQL_ORIGEN = "SELECT "
			    		+ "		`direccion`.`DIRE_DIRECCION`,"
			    		+ "		`empresas`.`empresas_razonsocial`,"
			    		+ "		`empresas`.`empresas_id`,"
			    		+ "		`empresas`.`empresas_giro`,"
			    		+ "		`empresas`.`empresas_rut`,"
			    		+ "		`direccion`.`DIRE_CIUDAD`,"
			    		+ "		`comuna`.`COMU_NOMBRE`,"
			    		+ "		`region`.`REGI_NOMBRE`"
			    		+ "	FROM"
			    		+ "		`direccion`"
			    		+ "	INNER JOIN `empresas` ON `empresas`.`empresas_id` = `direccion`.`CLPR_ID`"
			    		+ "	INNER JOIN `comuna` ON `comuna`.`COMU_ID` = `direccion`.`COMU_ID`"
			    		+ "	INNER JOIN `region` ON `region`.`REGI_ID` = `comuna`.`REGI_ID`"
			    		+ "	WHERE"
			    		+ "		`direccion`.`DIRE_ID` = "+request.getParameter("o_id");
			    
			    ResultSet ORIGEN_RS = state.executeQuery(SQL_ORIGEN);
			    while(ORIGEN_RS.next()){ 
			    	
			    	request.setAttribute("ORIGEN_DIRE_DIRECCION", ORIGEN_RS.getString("DIRE_DIRECCION"));
			    	request.setAttribute("ORIGEN_empresas_razonsocial", ORIGEN_RS.getString("empresas_razonsocial"));
			    	request.setAttribute("ORIGEN_empresas_id", ORIGEN_RS.getString("empresas_id"));
			    	request.setAttribute("ORIGEN_empresas_rut", ORIGEN_RS.getString("empresas_rut"));
			    	request.setAttribute("ORIGEN_DIRE_CIUDAD", ORIGEN_RS.getString("DIRE_CIUDAD"));
			    	
			    	request.setAttribute("ORIGEN_COMU_NOMBRE", ORIGEN_RS.getString("COMU_NOMBRE"));
			    	request.setAttribute("ORIGEN_REGI_NOMBRE", ORIGEN_RS.getString("REGI_NOMBRE"));
			    	
			    	
			   }
			    ORIGEN_RS.close();
			   
			   
			    //----------------------- FIN ------------------------//
			    //--------------------- EMPRESA DESTINO ----------------------//
			    String SQL_DESTINO = "SELECT "
			    		+ "		DATE_FORMAT(now(),'%d-%m-%Y') AS fecha, "
			    		+ "		`direccion`.`DIRE_DIRECCION`,"
			    		+ "		`empresas`.`empresas_razonsocial`,"
			    		+ "		`empresas`.`empresas_id`,"
			    		+ "		`empresas`.`empresas_rut`,"
			    		+ "		`empresas`.`empresas_giro`,"
			    		+ "		`direccion`.`DIRE_CIUDAD`,"
			    		+ "		`comuna`.`COMU_NOMBRE`,"
			    		+ "		`region`.`REGI_NOMBRE`, "
			    		+ "		CONCAT_WS(' ',`usuarios`.`Usuarios_nombre`,`usuarios`.`Usuarios_ape_p`,`usuarios`.`Usuarios_ape_m`) AS Usuarios_nombre	"
			    		+ "	FROM"
			    		+ "		`direccion`"
			    		+ "	INNER JOIN `empresas` ON `empresas`.`empresas_id` = `direccion`.`CLPR_ID`"
			    		+ "	INNER JOIN `comuna` ON `comuna`.`COMU_ID` = `direccion`.`COMU_ID`"
			    		+ "	INNER JOIN `region` ON `region`.`REGI_ID` = `comuna`.`REGI_ID` "
			    		+ "	INNER JOIN `usuarios` ON `usuarios`.`Usuarios_id` = `empresas`.`responsable_id` "
			    		+ "	WHERE "
			    		+ "		`direccion`.`DIRE_ID` = "+request.getParameter("d_id");
			    System.out.println("DESTINO: "+SQL_DESTINO);
			    ResultSet DESTINO_RS = state.executeQuery(SQL_DESTINO);
			    while(DESTINO_RS.next()){ 
			    	
			    	request.setAttribute("DESTINO_DIRE_DIRECCION", DESTINO_RS.getString("DIRE_DIRECCION"));
			    	request.setAttribute("DESTINO_empresas_razonsocial", DESTINO_RS.getString("empresas_razonsocial"));
			    	request.setAttribute("DESTINO_empresas_id", DESTINO_RS.getString("empresas_id"));
			    	request.setAttribute("DESTINO_empresas_rut", DESTINO_RS.getString("empresas_rut"));
			    	request.setAttribute("DESTINO_DIRE_CIUDAD", DESTINO_RS.getString("DIRE_CIUDAD"));
			    	
			    	request.setAttribute("DESTINO_COMU_NOMBRE", DESTINO_RS.getString("COMU_NOMBRE"));
			    	request.setAttribute("DESTINO_REGI_NOMBRE", DESTINO_RS.getString("REGI_NOMBRE"));
			    	request.setAttribute("clientes_id", DESTINO_RS.getString("empresas_id"));
			    	request.setAttribute("fecha", DESTINO_RS.getString("fecha"));
			    	request.setAttribute("RESPONSABLE", DESTINO_RS.getString("Usuarios_nombre"));
			    	request.setAttribute("empresas_giro", DESTINO_RS.getString("empresas_giro"));
			    	
			    	id_emp_destino=DESTINO_RS.getString("empresas_id");
			    	
			   }
			    DESTINO_RS.close();
			    //--------------------- CONTACTO ----------------------//
			    ContactoVO contacto_filter = new ContactoVO();
			    contacto_filter.setEstadoVigNoVig(new EstadosVigNoVigVO("1"));
			    contacto_filter.setEmpresa(new EmpresaVO(id_emp_destino));
			    
			    request.setAttribute("contactos", ContactosDAO.getContactos(contacto_filter));
			    
			    
			    //----------------------- FIN ------------------------//
			   
			    
	 	   		//--------------------- TRASLADOS ----------------------//
			    request.setAttribute("d_id", request.getParameter("d_id"));
			    request.setAttribute("o_id", request.getParameter("o_id"));
		    			    	
			    
			    if(request.getParameter("agregar") != null){
			    	String[] seleccionado = request.getParameterValues("permisos[]");
				    if(seleccionado!=null){
				    	request.setAttribute("ar_guias", seleccionado);
				    }
				    
				    request.setAttribute("empresas_id", request.getParameter("empresas_id"));
				    request.setAttribute("fecha", request.getParameter("gv_fecha"));
				}
			    
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
			    //----------------------- FIN ------------------------//
			    conexion.close();
			}catch(Exception ex){
			    out.println("ERROR: "+ex);
			    ex.printStackTrace();
			}
		String msg="";
		if(guiaexiste){
			msg="?Exito=NOK";
			String condiciones = request.getParameter("condiciones");
			String ref = request.getParameter("ref");
			String obs = request.getParameter("obs");
			request.setAttribute("condiciones",condiciones);
			request.setAttribute("ref",ref);
			request.setAttribute("obs",obs);
			
		}
		RequestDispatcher rd = request.getRequestDispatcher("I02_001.jsp"+msg);
	    rd.forward(request, response);
		
		}
		
	}

}
