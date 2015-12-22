

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class idir2
 */
@WebServlet("/idir2")
public class idir2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public idir2() {
        super();
        // TODO Auto-generated constructor stub
    }
    
public boolean validateSession(HttpServletRequest request, HttpServletResponse response){
		
		boolean user_in_session=false;
		boolean username_in_session=false;
		Cookie [] cookies=request.getCookies();
		
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 System.out.println("cookie: "+cookie.getName()+"-"+cookie.getValue());
		    	if(cookie.getName().equals("Usuarios_id") && cookie.getValue()!=null) user_in_session=true;
		    	if(cookie.getName().equals("Usuarios_nombre") && cookie.getValue()!=null) username_in_session=true;
		    }
		}
		
		
		if(user_in_session && username_in_session) user_in_session=true;
		else user_in_session=false;
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
		    	if(cookie.getName().equals("perfilusu_id")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
		    	
		    	
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
	
	

	private void mt(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
				
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
				
				//////////////////////////////////////////////////
				////////DEFINE PARAMETROS DE USUARIO//////////////
				String Usuarios_nombre="";
				String Perfil_id="";
				String Usuarios_ID="";
				
				Calendar ahoraCal = Calendar.getInstance();
				String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
				String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
				if (cookies != null) {
					for (Cookie cookie : cookies) {
						if(cookie.getName().equals("Usuarios_id")) { Usuarios_ID=cookie.getValue();}
						if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
						if(cookie.getName().equals("perfilusu_id")) Perfil_id=cookie.getValue();
					}
				}
				request.setAttribute("usuname", Usuarios_nombre);
				////////////////////////////////////////////////////////////
				//////////////////////////////////////////////////////////
				
				if(request.getParameter("grabar") !=  null){
					
					
					
					String CLPR_ID = request.getParameter("empresas_id");
			    	String COMU_ID = request.getParameter("comuna");
			    	String CUAD_ID = request.getParameter("cua");
			    	String DIRE_CIUDAD = request.getParameter("ciudad");
			    	String DIRE_COD_POSTAL = request.getParameter("cod_postal");
			    	String DIRE_DIRECCION = request.getParameter("dir");
			    	String DIRE_NOMBRE = request.getParameter("nom_dir");
			    	String DIRE_SECTOR = request.getParameter("sector");
			    	
			    	String TIDIR_ID = request.getParameter("t_dir");
			    	String lat = request.getParameter("lat");
			    	String lon = request.getParameter("lon");
			    	String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
			    	
			    	String nsa_tec = request.getParameter("nsa_tec");
			    	String nsa_log = request.getParameter("nsa_log");
			    	String nsa_pv = request.getParameter("nsa_pv");
			    	
			    	if(nsa_tec.equals("")) nsa_tec="0";
			    	if(nsa_log.equals("")) nsa_log="0";
			    	if(nsa_pv.equals("")) nsa_pv="0";
			    	
			    	String use_dir_despacho = request.getParameter("use_dir_despacho");
			    	String use_dir_fac = request.getParameter("use_dir_fac");
			    	String use_dir_cobranza = request.getParameter("use_dir_cobranza");
			    	String use_dir_corres = request.getParameter("use_dir_corres");
			    	String use_dir_otrofin = request.getParameter("use_dir_otrofin");

			    	
			    	
			    	if(use_dir_despacho==null || use_dir_despacho.equals("")) use_dir_despacho="0";
			    	if(use_dir_fac==null || use_dir_fac.equals("")) use_dir_fac="0";
			    	if(use_dir_cobranza==null || use_dir_cobranza.equals("")) use_dir_cobranza="0";
			    	if(use_dir_corres==null || use_dir_corres.equals("")) use_dir_corres="0";
			    	if(use_dir_otrofin==null || use_dir_otrofin.equals("")) use_dir_otrofin="0";
					
			    	//insertamos en birt 
	    			
	    			if(Constantes.SYNCDB==1){
	       			 ConBirt birtBD= new ConBirt();
	       			 
	       			 String insert_birt="INSERT INTO [DIRECCION] ("
	       			 		+ "	[DIRECCION].[CLPR_ID],"
	       			 		+ "	[DIRECCION].[COMU_ID],"
	       			 		+ "	[DIRECCION].[CUAD_ID],"
	       			 		+ "	[DIRECCION].[DIRE_CIUDAD],"
	       			 		+ "	[DIRECCION].[DIRE_COD_POSTAL],"
	       			 		+ "	[DIRECCION].[DIRE_DIRECCION],"
	       			 		+ "	[DIRECCION].[DIRE_ESTADO],"
	       			 		+ "	[DIRECCION].[DIRE_NOMBRE],"
	       			 		+ "	[DIRECCION].[DIRE_SECTOR],"
	       			 		+ "	[DIRECCION].[TIDIR_ID],"
	       			 		+ "	[DIRECCION].[DIRE_FECHA_UM],"
	       			 		+ "	[DIRECCION].[USU_ID_UM]"
	       			 		+ "	)"
	       			 		+ "	VALUES"
	       			 		+ "	('"+CLPR_ID+"',"+COMU_ID+","+CUAD_ID+",'"+DIRE_CIUDAD.toUpperCase().replace("'", " ")+"',	'"+DIRE_COD_POSTAL+"','"+DIRE_DIRECCION.toUpperCase().replace("'", " ")+"','VIGENTE','"+DIRE_NOMBRE.toUpperCase().replace("'", " ")+"','"+DIRE_SECTOR.toUpperCase().replace("'", " ")+"','"+TIDIR_ID+"',GETDATE(),'"+Usuarios_ID+"'	)";
	       			 	System.out.println("BIRT: "+insert_birt);
	       			 	birtBD.ConsultaINUP(insert_birt);
	       		      
	       		        birtBD.Desconectar();
	       			}
			    	
					String sql_in="	INSERT INTO `direccion` ("
							+ "	`direccion`.`CLPR_ID`,"
							+ "	`direccion`.`COMU_ID`,"
							+ "	`direccion`.`CUAD_ID`,"
							+ "	`direccion`.`DIRE_CIUDAD`,"
							+ "	`direccion`.`DIRE_COD_POSTAL`,"
							+ "	`direccion`.`DIRE_DIRECCION`,"
							+ "	`direccion`.`DIRE_NOMBRE`,"
							+ "	`direccion`.`DIRE_SECTOR`,"
							+ "	`direccion`.`estados_vig_novig_id`,"
							+ "	`direccion`.`TIDIR_ID`,"
							+ "	`direccion`.`dire_creador`,"
							+ "	`direccion`.`dire_feccreacion`, "
							+ "	`direccion`.`dire_lat`, "
							+ "	`direccion`.`dire_lon`, "
							+ "	`direccion`.`dire_nsa_tecnico`, "
							+ "	`direccion`.`dire_nsa_logistica`, "
							+ "	`direccion`.`dire_nsa_postventa`, "
							
							+ "	`direccion`.`use_dir_despacho`, "
							+ "	`direccion`.`use_dir_fac`, "
							+ "	`direccion`.`use_dir_cobranza`, "
							+ "	`direccion`.`use_dir_corres`, "
							+ "	`direccion`.`use_dir_otrofin`  "

							+ "	)"
							+ "	VALUES"
							+ "		("+CLPR_ID+","+COMU_ID+","+CUAD_ID+",'"+DIRE_CIUDAD.toUpperCase().replace("'", " ")+"','"+DIRE_COD_POSTAL+"'"
									+ ",'"+DIRE_DIRECCION.toUpperCase().replace("'", " ")+"','"+DIRE_NOMBRE.toUpperCase().replace("'", " ")+"'"
									+ ",'"+DIRE_SECTOR.toUpperCase().replace("'", " ")+"',1"
									+ ",'"+TIDIR_ID+"'"
									+ ","+Usuarios_ID+",NOW(),'"+lat+"'"
									+ ",'"+lon+"','"+nsa_tec+"','"+nsa_log+"'"
									+ ",'"+nsa_pv+"' "
									
									+ ",'"+use_dir_despacho+"' "
									+ ",'"+use_dir_fac+"' "
									+ ",'"+use_dir_cobranza+"' "
									+ ",'"+use_dir_corres+"' "
									+ ",'"+use_dir_otrofin+"' "
											+ ")";
					
						System.out.println(sql_in);
						
						try{
						Class.forName("com.mysql.jdbc.Driver");
			    		Connection conexion=(Connection) DriverManager.getConnection
			    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
			    		 Statement state = (Statement) ((java.sql.Connection) conexion).createStatement();
			    		 
			    		 
			    		 state.executeUpdate(sql_in);
			    		 out.print("<script>location='/010/?Exito=OK'</script>");
			    		// response.sendRedirect("/010/");
			    		 return;
			    		 
						}catch(Exception e ){
							e.printStackTrace();
						}
				}
				
				
				
				
				try {
					//import java.io.IOException;
					Class.forName("com.mysql.jdbc.Driver");
		    		Connection conexion=(Connection) DriverManager.getConnection
		    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
		    		 Statement state = (Statement) ((java.sql.Connection) conexion).createStatement();
		    		
		    		 String SQL_clpr = "SELECT"
		    		 		+ "		`empresas`.`empresas_id`,"
		    		 		+ "		`empresas`.`empresas_rut`,"
		    		 		+ "		`grupos`.`grupos_nombre`,"
		    		 		+ "		`empresas`.`empresas_nombrenof`,"
		    		 		+ "		`empresas`.`empresas_razonsocial`, "
		    		 		+ " IF(empresas.empresas_relacionada='1','SI','NO') AS empresas_rel, "
		    		 		+ "		`estados_vig_novig`.`estados_vig_novig_nombre`,"
		    		 		+ " 	`empresas`.`empresas_escliente`,`empresas`.`empresas_esproveedor`,`empresas`.`empresas_esprospeccion` "
		    		 		+ "	FROM"
		    		 		+ " 	`empresas`"
		    		 		+ "	LEFT JOIN `grupos` ON `grupos`.`grupos_id` = `empresas`.`grupos_id`"
		    		 		+ "	INNER JOIN `estados_vig_novig` ON `estados_vig_novig`.`estados_vig_novig_id` = `empresas`.`estados_vig_novig_id`"
		    		 		+ "	WHERE"
		    		 		+ "		`empresas`.`empresas_id` = "+request.getParameter("empresas_id");
		 		    System.out.println(SQL_clpr);
		 		    ResultSet clpr_rs = state.executeQuery(SQL_clpr);
				     //recorremos los resultados de la consulta
				    while(clpr_rs.next()){        	   
		        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
				    	request.setAttribute("empresas_id", clpr_rs.getString("empresas_id"));
				    	request.setAttribute("empresas_rut", clpr_rs.getString("empresas_rut"));
				    	request.setAttribute("grupos_nombre", clpr_rs.getString("grupos_nombre"));
				    	request.setAttribute("empresas_nombrenof", clpr_rs.getString("empresas_nombrenof"));
				    	request.setAttribute("empresas_razonsocial", clpr_rs.getString("empresas_razonsocial"));
				    	request.setAttribute("estados_vig_novig_nombre", clpr_rs.getString("estados_vig_novig_nombre"));
				    	request.setAttribute("empresas_escliente", clpr_rs.getString("empresas_escliente"));
				    	request.setAttribute("empresas_esproveedor", clpr_rs.getString("empresas_esproveedor"));
				    	request.setAttribute("empresas_esprospeccion", clpr_rs.getString("empresas_esprospeccion"));
				    	request.setAttribute("empresas_relacionada",clpr_rs.getString("empresas_rel"));
				    	
				    	
		    	    }
				  //System.out.println("SIZE LIST: "+grupos.size());
				    	
			
				  
				  
				  ///////////////////////////TRAEMOS ULT DIR
				  
				  
				  
				  state = (Statement) ((java.sql.Connection) conexion).createStatement();
		    		
		    		 String SQL_dir = "SELECT 	`direccion`.`DIRE_ID` FROM 	direccion ORDER BY 	`direccion`.`DIRE_ID` DESC LIMIT 1";
		 		    System.out.println(SQL_dir);
		 		    ResultSet dir_rs = state.executeQuery(SQL_dir);
		 		   dir_rs.next();
		 		    int id_dir = dir_rs.getInt("DIRE_ID")+1;
		 		   request.setAttribute("id_dir", id_dir+"");
				  
		 		   
		 		  ///////////////////////////TRAEMOS tipo dir para selcet 
					  
					  
					  
					  state = (Statement) ((java.sql.Connection) conexion).createStatement();
			    		
			    		 String SQL_tdir = "SELECT	`tipo_direccion`.`TIDIR_ID`,`tipo_direccion`.`TIDIR_NOMBRE` FROM `tipo_direccion` WHERE `tipo_direccion`.`estados_vig_novig_id`=1";
			 		    System.out.println(SQL_tdir);
			 		    ResultSet tdir_rs = state.executeQuery(SQL_tdir);
			 		    
			 		   ArrayList<String> t_dirs = new ArrayList<String>();
					   
			 		   //recorremos los resultados de la consulta
					    while(tdir_rs.next()){        	   
			        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
					    	t_dirs.add(tdir_rs.getString("TIDIR_ID")+"/"+tdir_rs.getString("TIDIR_NOMBRE"));
			        	    
			    	    }
			 		    
					    String[] ar_tdir = new String[t_dirs.size()];
				          for(int x=0; x < t_dirs.size(); x++){
				        	  ar_tdir[x]=t_dirs.get(x);
				          }
				                
				          request.setAttribute("ar_tdir", ar_tdir);
				          
				          ///////////////////////////TRAEMOS comuna para selcet 
						  
						  state = (Statement) ((java.sql.Connection) conexion).createStatement();
				    		
				    		 String SQL_comu = ""
				    		 		+ "SELECT "
				    		 		+ "		`comuna`.`COMU_ID`,"
				    		 		+ "		`comuna`.`COMU_NOMBRE`,"
				    		 		+ "		`region`.`REGI_NOMBRE`"
				    		 		+ "	FROM"
				    		 		+ "		`comuna`"
				    		 		+ "	INNER JOIN `region` ON `region`.`REGI_ID` = `comuna`.`REGI_ID`"
				    		 		+ "	GROUP BY"
				    		 		+ "		`comuna`.`COMU_ID`"
				    		 		+ "	ORDER BY"
				    		 		+ "		`comuna`.`COMU_NOMBRE`";
				 		    System.out.println(SQL_comu);
				 		    ResultSet comu_rs = state.executeQuery(SQL_comu);
				 		    
				 		   ArrayList<String> comus = new ArrayList<String>();
						   
				 		   //recorremos los resultados de la consulta
						    while(comu_rs.next()){        	   
				        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
						    	comus.add(comu_rs.getString("COMU_ID")+"/"+comu_rs.getString("COMU_NOMBRE")+"/"+comu_rs.getString("REGI_NOMBRE"));
				        	}
				 		    
						    String[] ar_comus = new String[comus.size()];
					          for(int x=0; x < comus.size(); x++){
					        	  ar_comus[x]=comus.get(x);
					          }
					                
					          request.setAttribute("ar_comus", ar_comus);
				            
					          ///////////////////////////TRAEMOS cuadrante para selcet 
							  
							  state = (Statement) ((java.sql.Connection) conexion).createStatement();
					    		
					    		 String SQL_cua = "SELECT	`cuadrante`.`CUAD_ID`, 	`cuadrante`.`CUAD_NOMBRE` FROM 	`cuadrante` ORDER BY 	`cuadrante`.`CUAD_NOMBRE`";
					 		    System.out.println(SQL_cua);
					 		    ResultSet cua_rs = state.executeQuery(SQL_cua);
					 		    
					 		   ArrayList<String> cuas = new ArrayList<String>();
							   
					 		   //recorremos los resultados de la consulta
							    while(cua_rs.next()){        	   
					        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
							    	cuas.add(cua_rs.getString("CUAD_ID")+"/"+cua_rs.getString("CUAD_NOMBRE"));
					        	}
					 		    
							    String[] ar_cuas = new String[cuas.size()];
						          for(int x=0; x < cuas.size(); x++){
						        	  ar_cuas[x]=cuas.get(x);
						          }
						                
						          request.setAttribute("ar_cuas", ar_cuas);
						          
						          
						         
					          
		 		  dir_rs.close();
		 		  clpr_rs.close();
				  state.close();
		          conexion.close();
		                
				}catch(Exception ex){
					ex.printStackTrace();
				    out.println("Error "+ex);
				}
				
				RequestDispatcher rd = request.getRequestDispatcher("idir2.jsp");
				rd.forward(request, response);
		
	}

	
	
	

}
