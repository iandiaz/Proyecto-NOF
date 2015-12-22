

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class iguia
 */
@WebServlet("/iguia")
public class iguia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public iguia() {
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

		String Usuarios_nombre="",id_usuario="";
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName());
		    	if(cookie.getName().equals("Usuarios_id") && cookie.getValue()!=null) id_usuario=cookie.getValue();
		    	if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}		
		request.setAttribute("usuname", Usuarios_nombre);	
		
		//grabar
		boolean guiaexiste=false;
		
			Statement stategrabar = null;	
		
				
				try {
					 
					 boolean isMultipart = ServletFileUpload.isMultipartContent(request);
					 
				       
						if (isMultipart) {
							
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
							String clientes_id = "";
							String empresas_id = "";
							String did = "";
							String cont_id = "";
							String numtick = "";
							String obs = "";
							String obs2 = "SOLO TRASLADO NO CONSTITUYE VENTA";
							
							String dire_id="";
							String dire_id2="";
							String[] seleccionado_detguias = null;
							
							String fec[] =null;
							String gv_fecha="";
							
							
							ArrayList<String> detalleseleccionado= new ArrayList<String>();
						
							
							
							String SUBTOTAL="";
					        String NETO="";
				            String IVA="";
				            String TOTAL="";
				            String desc="";
				            String glosa_desc="";
				            String tipo_dteref="";
				            String folioref="";
				            String fec_ref=null;
				            String g_afecta=null;
							
				        	try {
								
								 List<FileItem> multiparts = new ServletFileUpload(
		                                 new DiskFileItemFactory()).parseRequest(request);
								
				            	 for(FileItem item : multiparts){
									 
										 
					                    if(!item.isFormField()){
					                       
					                        
					                    }
					                    else{
					                    	
					                    	String key=item.getFieldName();
					                    	String valor =item.getString();
					                    	
					                    if(key.equals("clientes_id"))clientes_id = valor;
					  				      
					  				      if(key.equals("empresas_id"))empresas_id = valor;
					  				      if(key.equals("did"))did = valor;
					  				      if(key.equals("cont_id"))cont_id = valor;
					  				      
					  				      if(key.equals("numtick"))numtick = valor;
					  				      if(key.equals("obs"))obs = valor;
					  				      if(key.equals("o_id"))dire_id = valor;
					  				      
					  				      if(key.equals("d_id"))dire_id2 = valor;
					  				      if(key.equals("tras_id[]"))detalleseleccionado.add(valor);
					  				      
					  				      if(key.equals("subtotal"))SUBTOTAL=valor;
					  				      if(key.equals("neto"))NETO=valor;
					  				      if(key.equals("iva"))IVA=valor;
					  				      if(key.equals("total"))TOTAL=valor;
					  				      if(key.equals("desc"))desc=valor;
					  				      if(key.equals("glosa_desc"))glosa_desc=valor;
							  				
					  				      if(key.equals("gv_fecha")){
							  					 fec=valor.split("-");
												 gv_fecha="'"+fec[2]+"-"+fec[1]+"-"+fec[0]+"'";
					  				      }
					  				      
					  				      
					  				    if(key.equals("tipo_dteref"))tipo_dteref=valor;
					  				    if(key.equals("folioref"))folioref=valor;
					  				    if(key.equals("fec_ref"))fec_ref=valor;
					  				    if(key.equals("g_afecta"))g_afecta=valor;
					  				  
					  				 	
							  				
											
					                    	
					                    }
					                }
								 
								 
				            	 if(tipo_dteref==null)tipo_dteref="";
									if(folioref==null)folioref="";
									if(fec_ref==null){fec_ref="NULL";}
									else{ 
										String fecrefar[]=fec_ref.split("-");
										fec_ref ="'"+fecrefar[2]+"-"+fecrefar[1]+"-"+fecrefar[0]+"'";
										}
									
									
									if(g_afecta==null || g_afecta.equals(""))g_afecta="0";
									
									
								 
								seleccionado_detguias = new String[detalleseleccionado.size()];
								    for(int x=0; x < detalleseleccionado.size(); x++){ seleccionado_detguias[x]=detalleseleccionado.get(x);}

								 
									
									//INSERTAMOS TODOS LOS DATOS EN NOF EN EL DETALLE DE LA FACTURA
									String SQL_FACDET = "INSERT INTO `824` ("
											+ "	clientes_id"
											+ "	,dire_id"
											+ "	,dire_id2"
											+ "	,824_empresa_emisora"
											+ " ,cont_id"
											+ "	,824_obs"
											+ "	,824_obs2"
											+ "	,824_feccreacion"
											+ "	,824_creador, estados_vig_novig_id "
											+ "	,824_numticket"
											
											+ "	,824_SUBTOTAL"
								    		+ "	,824_TOTAL"
								    		+ "	,824_descuento"
								    		+ "	,824_glosa_descuento"
								    		+ "	,824_NETO"
								    		+ "	,824_IVA"
								    		+ "	,824_fecha"
								    		
											+ "	,`824`.`tipo_dteref` "
											+ "	,`824`.`folioref` "
											+ "	,`824`.`fec_ref` "
											
											+ "	,`824`.`824_afecta`  "

								    		+ ") "
									    			+ "VALUES ("+clientes_id+""
									    					+ ","+dire_id+""
									    					+ ","+dire_id2+""
									    					+ ","+empresas_id+""
									    					+ ","+cont_id+""
									    					+ ",'"+obs.toUpperCase()+"'"
									    					+ ",'"+obs2.toUpperCase()+"'"
									    					+ ",now()"
									    					+ ","+id_usuario+""
									    					+ ",1"
									    					+ ",'"+numtick.toUpperCase()+"'"
									    					+ ",'"+SUBTOTAL+"'"
													    	+ ",'"+TOTAL+"'"
													    	+ ",'"+desc+"'"
													    	+ ",'"+glosa_desc.toUpperCase()+"'"
													    	+ ",'"+NETO+"'"
													    	+ ",'"+IVA+"'"
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
								    
								    if (generatedKeys.next()) {
								    	GID=generatedKeys.getString(1);
						    		}
								   
								    
								    String file1="";
								    String file2="";
								    String file3="";
								    
								    for(FileItem item : multiparts){
										// System.out.println(item.getFieldName()+" "+item.getString());
									
											 
						                    if(!item.isFormField()){
						                        String name = new File(item.getName()).getName();
						                     System.out.println(name);
						                     
												
						                        String[] name_part=name.split("\\.");
						                        
						                        
						                        if(name!=null && !name.equals("")){
						                        	item.write( new File(Constantes.DIR_DOCS + File.separator + GID+"_"+item.getFieldName()+"."+name_part[name_part.length-1]));
								                       
							                        if(item.getFieldName().equals("file1"))file1=GID+"_"+item.getFieldName()+"."+name_part[name_part.length-1];
							                        if(item.getFieldName().equals("file2"))file2=GID+"_"+item.getFieldName()+"."+name_part[name_part.length-1];
							                        if(item.getFieldName().equals("file3"))file3=GID+"_"+item.getFieldName()+"."+name_part[name_part.length-1];
							                    }	
						                        }
						              }
								    
								    
								    String SQL_FACf = "update `824` set "
											+ "	file1='"+file1+"'"
											+ "	,file2='"+file2+"'"
											+ "	,file3='"+file3+"'"
											+ " WHERE 824_id="+GID;
									
									System.out.println(SQL_FACf);
									state.executeUpdate(SQL_FACf);
								    
								    
								    
						    		
						    		if(seleccionado_detguias!=null)
						    					for(int i =0; i<seleccionado_detguias.length; i++){
								    				String[] Guias = seleccionado_detguias[i].split(";;");
								    				String fec_ar[]=Guias[6].split("-");
								    				
								    				String SQL_GUIADET = "INSERT INTO detalle_824"
								    						+ "(824_id, alt_id, PROD_PN_TLI_CODFAB, PROD_DESC_CORTO, PROD_COD_BAR_FAB,"
								    						+ " ALT_SERIE, UBI_DESCRIPCION, clientes_id, estados_vig_novig_id, TRAS_FECHA,tras_id,d824_valor) "
									  	    				+ "VALUES ("+GID+","+Guias[0]+",'"+Guias[1]+"','"+Guias[2]+"','"+Guias[3]+"','"+Guias[4]+"','"+Guias[5]+"',"
									  	    						+ ""+clientes_id+",1,'"+fec_ar[0]+"-"+fec_ar[1]+"-"+fec_ar[2]+"','"+Guias[7]+"','"+Guias[10]+"' )";
								    				System.out.println(SQL_GUIADET);
									      			state_ins.executeUpdate(SQL_GUIADET);
							    				}
						    		
						    		

									  //insertamos logs
							    		String log_sql=""
							    				+ " INSERT INTO `log` ("
							    				+ "	`log`.`log_fec`, "
							    				+ "	`log`.`log_nombre`, " 
							    				+ "	`log`.`Usuarios_id` "
							    				+ " ) "
							    				+ " VALUES "
							    				+ " (now(), 'USUARIO INGRESA REGISTRO "+GID+" EN TABLA 824', '"+id_usuario+"') ";
							    		state.executeUpdate(log_sql);
							    		
						        	RequestDispatcher rd_up = request.getRequestDispatcher("menuguia?Exito=OK");
						        	rd_up.forward(request, response);
						        	return ;
								 
				                ///FIN GRABAR 
				            } catch (FileUploadException e) {
				                e.printStackTrace();
				            } catch (Exception e) {
				                e.printStackTrace();
				            }
						}
					
					    
		    		
				}catch(Exception ex){
				    ex.printStackTrace();
				}
				
				
			
	        	
	        	
		
			

		
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
			    //System.out.println("DESTINO: "+SQL_DESTINO);
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
			    String SQL_CONTACTO = "SELECT *,CONCAT_WS(' ',cont_nombre,CONT_APEP,CONT_APEM) as con FROM contacto WHERE CLPR_ID = "+id_emp_destino+" ORDER BY cont_nombre ";
			    CONTACTOS_RS =  state.executeQuery(SQL_CONTACTO);
			    ArrayList<String> contactos = new ArrayList<String>();
			    while(CONTACTOS_RS.next()){ contactos.add(	CONTACTOS_RS.getString("cont_id")+"/"+
			    											CONTACTOS_RS.getString("con").replace("/", " ")+"/"+
			    											CONTACTOS_RS.getString("CLPR_ID")+"/"+
			    											//CONTACTOS_RS.getString("PERS_ID")+"/"+
			    											CONTACTOS_RS.getString("CONT_TELEFONO").replace("/", " ")+"/"+
			    											CONTACTOS_RS.getString("CONT_EMAIL")+"/"+
			    											CONTACTOS_RS.getString("CONT_TELEFONOC")); }
			    CONTACTOS_RS.close();
			    String[] ar_contactos = new String[contactos.size()];
			    for(int x=0; x < contactos.size(); x++){ ar_contactos[x]=contactos.get(x);}
			    request.setAttribute("ar_contactos", ar_contactos);
			    //----------------------- FIN ------------------------//
			   
			    
	 	   		//--------------------- TRASLADOS ----------------------//
			    request.setAttribute("d_id", request.getParameter("d_id"));
			    request.setAttribute("o_id", request.getParameter("o_id"));
		    			    	
			    
			    if(request.getParameter("agregar") != null){
			    	String[] seleccionado = request.getParameterValues("permisos[]");
				    if(seleccionado!=null){
				    	request.setAttribute("ar_guias", seleccionado);
				    	
				    	 request.setAttribute("empresas_id", request.getParameter("empresas_id"));
						    request.setAttribute("fecha", request.getParameter("gv_fecha"));
						    
						    request.setAttribute("numtick", request.getParameter("numtick"));
						    request.setAttribute("obs", request.getParameter("obs"));
						    request.setAttribute("desc", request.getParameter("desc"));
						    request.setAttribute("glosa_desc", request.getParameter("glosa_desc"));
				    }
				}
			    //----------------------- FIN ------------------------//
			    
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
			//System.out.println("grupos_nombre "+grupos_nombre);
		}
		RequestDispatcher rd = request.getRequestDispatcher("iguia.jsp"+msg);
	    rd.forward(request, response);
		
		}
		
	}


