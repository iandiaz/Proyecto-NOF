

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
 * Servlet implementation class mguia
 */
@WebServlet("/mguia")
public class mguia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mguia() {
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
		    		Statement stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
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
		            String estados_vig_novig_id=null;
		            String cont_email=null;
					String cont_phone=null;
					String cont_nombre= null;
					String cont_telefonoc=null;
					
					String gv_ciudad= null;
					String empresa_emisora_nombre= null;
						
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
			  				      if(key.equals("detguias[]"))detalleseleccionado.add(valor);
			  				      
			  				      if(key.equals("subtotal"))SUBTOTAL=valor;
			  				      if(key.equals("neto"))NETO=valor;
			  				      if(key.equals("iva"))IVA=valor;
			  				      if(key.equals("total"))TOTAL=valor;
			  				      if(key.equals("desc"))desc=valor;
			  				      if(key.equals("glosa_desc"))glosa_desc=valor;
					  				
			  				      if(key.equals("datepicker")){
					  					 fec=valor.split("-");
										 gv_fecha="'"+fec[2]+"-"+fec[1]+"-"+fec[0]+"'";
			  				      }
			  				      
			  				      
			  				    if(key.equals("tipo_dteref"))tipo_dteref=valor;
			  				    if(key.equals("folioref"))folioref=valor;
			  				    if(key.equals("fec_ref"))fec_ref=valor;
			  				    if(key.equals("g_afecta"))g_afecta=valor;
			  				    if(key.equals("estados_vig_novig_id"))estados_vig_novig_id=valor;
			  				    if(key.equals("cont_email"))cont_email=valor;
			  				    if(key.equals("cont_phone"))cont_phone=valor;
			  				    if(key.equals("cont_nombre"))cont_nombre=valor;
			  				    if(key.equals("cont_telefonoc"))cont_telefonoc=valor;
			  				    
			  				    if(key.equals("gv_ciudad"))gv_ciudad=valor;
			  				    if(key.equals("empresa_emisora_nombre"))empresa_emisora_nombre=valor;
			  				  
			  				    if(key.equals("guia_des_traszf_id"))GID=valor;
				  				
			  				
			  				    			
			                    	
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
							String SQL_FAC = "UPDATE `824` "
									+ " SET "
									+ " estados_vig_novig_id="+estados_vig_novig_id+", "
									+ " 824_empresa_emisora="+empresas_id+","
									+ " cont_id="+cont_id+","
									+ " 824_obs='"+obs+"',"
									+ " 824_fecmod=now(),"
									+ " 824_modificador="+id_usuario+","
									+ " 824_fecha='"+fec[2]+"-"+fec[1]+"-"+fec[0]+"' "
									
									+ "	,824_SUBTOTAL='"+SUBTOTAL+"'"
						    		+ "	,824_TOTAL='"+TOTAL+"' "
						    		+ "	,824_descuento='"+desc+"' "
						    		+ "	,824_glosa_descuento='"+glosa_desc.toUpperCase()+"'"
						    		+ "	,824_NETO='"+NETO+"' "
						    		+ "	,824_IVA='"+IVA+"' "
									
									+ "	,`824`.`tipo_dteref`='"+tipo_dteref+"' "
									+ "	,`824`.`folioref`='"+folioref+"' "
									+ "	,`824`.`fec_ref`="+fec_ref+"  "
									
									+ "	,824_ciudad='"+gv_ciudad+"'"
						    		+ "	,824_empresa_emisora_nombre='"+empresa_emisora_nombre+"'"
						    		+ "	,cont_nombre='"+cont_nombre.toUpperCase()+"'"
						    		+ "	,cont_telefonoc='"+cont_telefonoc+"'"
						    		+ "	,cont_email='"+cont_email+"'"
						    		+ "	,cont_telefono='"+cont_phone+"'"
						    		+ "	,`824`.`824_afecta`='"+g_afecta+"'  "
									
									+ " WHERE 824_id = "+GID;
							System.out.println(SQL_FAC);
							state.executeUpdate(SQL_FAC);
						    
							String sql_delete="delete from detalle_824 where 824_id="+GID;
							state.addBatch(sql_delete);
			      			state.executeBatch();
			      			
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
						    				System.out.println(Guias[6]);
						    				String fec_ar[]=Guias[7].split("-");
						    				clientes_id=Guias[11];
						    				String SQL_GUIADET = "INSERT INTO detalle_824"
						    						+ "(824_id, alt_id, PROD_PN_TLI_CODFAB, PROD_DESC_CORTO, PROD_COD_BAR_FAB,"
						    						+ " ALT_SERIE, UBI_DESCRIPCION, clientes_id, estados_vig_novig_id, TRAS_FECHA,tras_id,d824_valor) "
							  	    				+ "VALUES ("+GID+","+Guias[0]+",'"+Guias[2]+"','"+Guias[3]+"','"+Guias[3]+"','"+Guias[4]+"','"+Guias[6]+"',"
							  	    						+ ""+clientes_id+",1,"
							  	    								+ "'"+fec_ar[2]+"-"+
							  	    										fec_ar[1]+"-"+
							  	    											fec_ar[0]+"','"+Guias[1]+"','"+Guias[12]+"')";
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
					    				+ " (now(), 'USUARIO MODIFICA REGISTRO "+GID+" EN TABLA 824', '"+id_usuario+"') ";
					    		state.executeUpdate(log_sql);
							
						    
				        	
				        	response.sendRedirect("menuguia?Exito=OK");
				        	return;
						 
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
	    		
	    		String GID = request.getParameter("guia_des_traszf_id");
	    		request.setAttribute("guia_des_traszf_id", GID);
	    		
	    		String empresas_id = request.getParameter("empresas_id");
				
	    		
	    		String SQL_Cliente = "SELECT "
	    				+ " IF(id_dte is null, 'ND', g.id_dte) as id_dte, DATE_FORMAT(824_fecha, '%d-%m-%Y') as fecha, "
	    				+ " g.824_empresa_emisora as empresa_id, IF(id_dte is null, 'NO ENVIADA', 'ENVIADA') as dte,"
	    				
	    				+ " e2.empresas_razonsocial as nom1, e2.empresas_rut as rut1, e2.empresas_id as id1,  "
	    				+ " IF(d.dire_direccion ='','SD',d.DIRE_DIRECCION) as dir1,"
	    				+ " r.REGI_NOMBRE as reg1, c.COMU_NOMBRE as com1, d.DIRE_CIUDAD as cui1, g.CONT_ID, o.CONT_NOMBRE, o.CONT_TELEFONO, o.CONT_EMAIL, "
	    				+ " g.CONT_ID,"
	    				
						+ " e.empresas_razonsocial as nom2, e.empresas_rut as rut2, e.empresas_id as id2,e.empresas_giro,  "
						+ " IF(d1.dire_direccion ='','SD',d1.DIRE_DIRECCION) as dir2,"
						+ " r1.REGI_NOMBRE as reg2, c1.COMU_NOMBRE as com2, d1.DIRE_CIUDAD as cui2, "
						+ " 824_subtotal,824_neto,824_iva,824_total,824_descuento,824_glosa_descuento,  "
			    		
	    				+ " g.dire_id2,g.dire_id, "
	    				+ "  g.824_obs as guia_des_traszf_obs, "
	    				+ " g.824_obs2 as guia_des_traszf_obs2, g.estados_vig_novig_id,g.824_numticket, "
	    				+ "		CONCAT_WS(' ',`usuarios`.`Usuarios_nombre`,`usuarios`.`Usuarios_ape_p`,`usuarios`.`Usuarios_ape_m`) AS Usuarios_nombre,	"
	    				+ "		IF(`g`.`tipo_dteref` is null,'NINGUNA',`g`.`tipo_dteref`) as tipo_dteref, "
						+ "		IF(`g`.`folioref` is null ,'',`g`.`folioref`) as  folioref, "
						+ "		IF(`g`.`fec_ref` is null,'',DATE_FORMAT(`g`.`fec_ref`,'%d-%m-%Y')) as fec_ref , "
						+ "		g.824_afecta as g_afecta "
	    				//+ " p.PERS_NOMBRE"
	    				+ " FROM `824` g "
	    				//+ " INNER JOIN empresas e ON e.empresas_id = g.clientes_id "
	    				+ " INNER JOIN empresas e ON e.empresas_id = g.clientes_id "
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
	    				+ " WHERE g.824_id ='"+GID+"'"
	    				+ " GROUP BY g.824_id"
	    				+ " ORDER BY e.empresas_nombrenof, d.dire_direccion"; 
	    		System.out.println(SQL_Cliente);
	 		    CLIENTE_RS =  state.executeQuery(SQL_Cliente);
	 		    String CID = ""; 
	 		    if(CLIENTE_RS.next()){
	 		    	CID = CLIENTE_RS.getString("id2");
	 		    	request.setAttribute("id_dte", CLIENTE_RS.getString("id_dte"));
	 		    	request.setAttribute("dte", CLIENTE_RS.getString("dte"));
	 		    	request.setAttribute("fecha", CLIENTE_RS.getString("fecha"));
	 		    	request.setAttribute("empresas_id", CLIENTE_RS.getString("empresa_id"));
	 		    	request.setAttribute("contactos_id", CLIENTE_RS.getString("CONT_ID"));
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
			    	request.setAttribute("CONT_ID", CLIENTE_RS.getString("CONT_ID"));
			    	request.setAttribute("CONT_NOMBRE", CLIENTE_RS.getString("CONT_NOMBRE"));
			    	request.setAttribute("CONT_TELEFONO", CLIENTE_RS.getString("CONT_TELEFONO"));
			    	request.setAttribute("PERS_NOMBRE", " ");
			    	request.setAttribute("CONT_EMAIL", CLIENTE_RS.getString("CONT_EMAIL"));
			    	request.setAttribute("estados_vig_novig_id", CLIENTE_RS.getString("estados_vig_novig_id"));
	 		    	
			    	request.setAttribute("obs", CLIENTE_RS.getString("guia_des_traszf_obs"));
			    	request.setAttribute("obs2", CLIENTE_RS.getString("guia_des_traszf_obs2"));
			    	request.setAttribute("RESPONSABLE", CLIENTE_RS.getString("Usuarios_nombre"));
			    	request.setAttribute("dire_id2", CLIENTE_RS.getString("dire_id2"));
			    	request.setAttribute("dire_id", CLIENTE_RS.getString("dire_id"));
			    	request.setAttribute("empresas_giro", CLIENTE_RS.getString("empresas_giro"));
			    	request.setAttribute("numticket", CLIENTE_RS.getString("824_numticket"));
			    	
			    	request.setAttribute("fec_ref", CLIENTE_RS.getString("fec_ref"));
	    	    	request.setAttribute("folioref", CLIENTE_RS.getString("folioref"));
	    	    	String tipo_dteref=CLIENTE_RS.getString("tipo_dteref").equals("") ? "NINGUNA": CLIENTE_RS.getString("tipo_dteref");
	    	    	request.setAttribute("tipo_dteref", tipo_dteref);
	    	    	
	    	    	request.setAttribute("g_afecta",CLIENTE_RS.getString("g_afecta"));
			    	
	    	    	
	    	    	String SUBTOTAL=CLIENTE_RS.getString("824_subtotal");
			    	String desc=CLIENTE_RS.getString("824_descuento");
			    	String NETO=CLIENTE_RS.getString("824_neto");
			    	String IVA=CLIENTE_RS.getString("824_iva");
			    	String TOTAL=CLIENTE_RS.getString("824_total");
			    	String glosa_desc=CLIENTE_RS.getString("824_glosa_descuento");
			    	
	            	 request.setAttribute("subtotal", SUBTOTAL);
	    	 		 request.setAttribute("descuento", desc);
	    	 		request.setAttribute("neto", NETO);
	    	 		request.setAttribute("iva", IVA);
	    	 		request.setAttribute("total", TOTAL);
	    	 		request.setAttribute("glosadescuento", glosa_desc);
			    	
	 		    }

	 		    //--------------------- DETALLE TRASLADOS ----------------------//
	 		   if(request.getParameter("agregar") != null){
				    String[] seleccionado = request.getParameterValues("permisos[]");
				    if(seleccionado!=null){
				    	request.setAttribute("ar_guias", seleccionado);
				    }
			    }else{
			    	String SQL_GUIAS = "SELECT "
				    		+ " d.clientes_id,d.TRAS_FECHA,d.TRAS_ID,d.ALT_ID,d.d824_valor, d.PROD_PN_TLI_CODFAB, d.PROD_DESC_CORTO, d.PROD_COD_BAR_FAB, d.ALT_SERIE, d.UBI_DESCRIPCION, "
				    		+ " DATE_FORMAT(d.TRAS_FECHA, '%d-%m-%Y') as fecha"
				    		+ " FROM detalle_824 d "
				    		+ " WHERE d.824_id = "+GID;
				    System.out.println(SQL_GUIAS);
				    GUIAS_RS =  state.executeQuery(SQL_GUIAS);
				    ArrayList<String> guias = new ArrayList<String>();
				    
				    while(GUIAS_RS.next()){ guias.add(
				    		GUIAS_RS.getString("ALT_ID")+"/"+
				    		GUIAS_RS.getString("tras_id")+"/"+
				    		GUIAS_RS.getString("PROD_PN_TLI_CODFAB").replace("/", " ")+"/"+
				    		GUIAS_RS.getString("PROD_DESC_CORTO").replace("/", " ")+"/"+
				    		GUIAS_RS.getString("ALT_SERIE").replace("/", " ")+"//"+
				    		GUIAS_RS.getString("UBI_DESCRIPCION")+"/"+
		    						
				    		GUIAS_RS.getString("fecha").replace("/", " ")+"/"+
				    		GUIAS_RS.getString("PROD_DESC_CORTO").replace("/", " ")+"/"+
				    		
				    		GUIAS_RS.getString("TRAS_FECHA")+"/"+
		    				
							GUIAS_RS.getString("tras_id")+"/"+

		    				
		    				
		    				GUIAS_RS.getString("clientes_id")+"/"+
		    				
		    				GUIAS_RS.getString("d824_valor")+"/"+
		    					GUIAS_RS.getString("d824_valor")); 
				    	 }
				    GUIAS_RS.close();
				    String[] ar_guias = new String[guias.size()];
				    for(int x=0; x < guias.size(); x++){ ar_guias[x]=guias.get(x);}
				    request.setAttribute("ar_guias", ar_guias);
			    }
			    
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

			    //--------------------- CONTACTO ----------------------//
			    String SQL_CONTACTO = "SELECT *,CONCAT_WS(' ',cont_nombre,CONT_APEP,CONT_APEM) as con FROM contacto WHERE CLPR_ID = "+CID+" ORDER BY cont_nombre ";
			    //System.out.println(SQL_CONTACTO);
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
			    
			    
				//--------------------- ESTADOS ----------------------//
			    String SQL_ESTADOS = "SELECT * FROM estados_vig_novig";
			    ResultSet ESTADOS_RS = state.executeQuery(SQL_ESTADOS);
			    ArrayList<String> estados = new ArrayList<String>();
			    while(ESTADOS_RS.next()){ estados.add(ESTADOS_RS.getString("estados_vig_novig_id")+"/"+ESTADOS_RS.getString("estados_vig_novig_nombre")); }
			    ESTADOS_RS.close();
			    String[] ar_estados = new String[estados.size()];
			    for(int x=0; x < estados.size(); x++){ ar_estados[x]=estados.get(x); }
			    request.setAttribute("ar_estados", ar_estados);
			    //----------------------- FIN ------------------------//
			    //----------------------- FIN ------------------------//
			    request.setAttribute("guia_des_traszf_id", request.getAttribute("guia_des_traszf_id"));
				   
			    if(empresas_id!=null)request.setAttribute("empresas_id",empresas_id);
			    
			}catch(Exception ex){
				 ex.printStackTrace();
			    out.println("ERROR: "+ex);
			   
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
		RequestDispatcher rd = request.getRequestDispatcher("mguia.jsp"+msg);
	    rd.forward(request, response);
		
		
		
	}

}
