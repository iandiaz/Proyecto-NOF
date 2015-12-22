
import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class mcon2
 */
@WebServlet("/mcon2")
public class mcon2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mcon2() {
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
  					
  					
  			    	String CONT_ID = request.getParameter("CONT_ID");
  			    	
  			    	String t_con = request.getParameter("t_con");
					String nom = request.getParameter("nom");
					String cargo = request.getParameter("cargo");
					String DIRE = request.getParameter("dir");
					String email = request.getParameter("email");
			    	String fono = request.getParameter("fono");
			    	String obs = request.getParameter("obs");
					
					
				
			    	String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
			    	
			    	String fono_c = request.getParameter("fono_c");
			    	String apep = request.getParameter("apep");
			    	String apem = request.getParameter("apem");
  			    	
  			    	//insertamos en birt 
			    	
			     	if(Constantes.SYNCDB==1){
	   	       			 ConBirt birtBD= new ConBirt();
	   	       			 
	   	       		String est_vnv_birt="";
	       			 
	       			 if(estados_vig_novig_id.equals("1")){est_vnv_birt="VIGENTE";}
	       			 else{est_vnv_birt="NO VIGENTE";}
	       			 
	       			 if(email==null) email="";
	       			 String email_birt="";
	       			 if(email.length()>30) {
	       				email_birt=email.substring(0, 29);
	       			 }else{
	       				email_birt= email;
	       			 }
	       			
	       			 
	   	       			 String insert_birt="UPDATE [CONTACTO] "
	   	       			 		+ "	SET [CONTACTO].[CONT_CARGO] = '"+cargo.toUpperCase()+"',"
	   	       			 		+ "	 [CONTACTO].[USU_ID_UM] = '"+Usuarios_ID+"', "
	   	       			 		+ "	 [CONTACTO].[CONT_EMAIL]='"+email_birt.toUpperCase()+"',"
	   	       			 		+ "	 [CONTACTO].[CONT_ESTADO]='"+est_vnv_birt+"', "
	   	       			 		+ "	 [CONTACTO].[CONT_NOMBRE]='"+nom.toUpperCase()+"',"
	   	       			 		+ "	 [CONTACTO].[CONT_TELEFONO]='"+fono.toUpperCase()+"',"
	   	       			 		+ "	 [CONTACTO].[DIRE_ID]='"+DIRE+"',"
	   	       			 		+ "	 [CONTACTO].[TICON_ID]='"+t_con+"', "
	   	       			 		+ "	 [CONTACTO].[CONT_FECHA_UM] = GETDATE() "
	   	       			 		+ "	WHERE "
	   	       			 		+ "		[CONTACTO].[CONT_ID] = '"+CONT_ID+"'";
	   	       			 		
	   	       			 	System.out.println("BIRT: "+insert_birt);
	   	       			 	birtBD.ConsultaINUP(insert_birt);
	   	       		      
	   	       		        birtBD.Desconectar();
	   	       			}
  	    			
  			    	String sql_in="	UPDATE `contacto` "
  			    	+ "	SET `contacto`.`estados_vig_novig_id` = 2, `contacto`.`TICON_ID`='"+t_con+"' ,`contacto`.`CONT_NOMBRE`='"+nom+"',`contacto`.`CONT_CARGO`='"+cargo+"'"
  			    	+ " , `contacto`.`DIRE_ID`='"+DIRE+"', `contacto`.`CONT_EMAIL`='"+email+"',`contacto`.`CONT_TELEFONO`='"+fono+"', "
  			    	+ " `contacto`.`estados_vig_novig_id` = '"+estados_vig_novig_id+"', `contacto`.`CONT_TELEFONOC`='"+fono_c+"', `contacto`.`CONT_APEM`='"+apem+"'"
  			    	+ " ,`contacto`.`CONT_APEP`='"+apep+"', "
  			    	+ "	 `contacto`.`CONT_modificador` = "+Usuarios_ID+","
  			    	+ "	 `contacto`.`CONT_fecmod` = now(),"
  			    	+ "	 `contacto`.`CONT_OBS` = '"+obs+"' "
  			    	+ "	WHERE"
  			    	+ "	 `contacto`.`CONT_ID` = "+CONT_ID;
  						System.out.println(sql_in);
  						
  						try{
  						Class.forName("com.mysql.jdbc.Driver");
  			    		Connection conexion=(Connection) DriverManager.getConnection
  			    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
  			    		 Statement state = (Statement) ((java.sql.Connection) conexion).createStatement();
  			    		 
  			    		 
  			    		 state.executeUpdate(sql_in);
  			    		 out.print("<script>location='/014/?Exito=OK'</script>");
  			    		// response.sendRedirect("/010/");
  			    		 return;
  			    		 
  						}catch(Exception e ){
  							e.printStackTrace();
  						}
  				}
  				
  				
  				
  				
  				try {
  					
  					String empresas_id="";
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
  		    		 		+ "		`empresas`.`empresas_razonsocial`,"
  		    		 		+ "		`contacto`.`CONT_NOMBRE`,"
  		    		 		+ "		`contacto`.`CONT_ID`, "
  		    		 		+ "		`contacto`.`CONT_APEM`, "
  		    		 		+ "		`contacto`.`CONT_APEP`, "
  		    		 		+ "		`contacto`.`CONT_CARGO`,"
  		    		 		+ " 	`contacto`.`CONT_EMAIL`, "
  		    		 		+ "		`contacto`.`CONT_TELEFONO`,"
  		    		 		+ "		`contacto`.`CONT_TELEFONOC` , "
  		    		 		+ "		`direccion`.`DIRE_DIRECCION`, "
  		    		 		+ "		`contacto`.`CONT_OBS`, "
  		    		 		+ "		`tipo_contacto`.`TICON_DESCRIPCION`,"
  		    		 		+ "		`tipo_contacto`.`TICON_ID` , "
  		    		 		+ "		`contacto`.`DIRE_ID` , "
  		    		 		+ "		`estvnv`.estados_vig_novig_id as estvnv_id, "
  		    		 		+ "		`estvnv`.estados_vig_novig_nombre AS estvnv , "
  		    		 		+ " IF(empresas.empresas_relacionada='1','SI','NO') AS empresas_rel, "
  		    		 		+ "		`estados_vig_novig`.`estados_vig_novig_nombre`,"
  		    		 		+ " 	`empresas`.`empresas_escliente`,`empresas`.`empresas_esproveedor`,`empresas`.`empresas_esprospeccion` , "
  		    		 		+ "		`estado_clpr`.`Estado_Clpr_nombre` "
  		    		 		+ "	FROM"
  		    		 		+ " 	`empresas`"
  		    		 		+ "	LEFT JOIN `grupos` ON `grupos`.`grupos_id` = `empresas`.`grupos_id`"
  		    		 		+ "	INNER JOIN `estados_vig_novig` ON `estados_vig_novig`.`estados_vig_novig_id` = `empresas`.`estados_vig_novig_id` "
  		    		 		+ "	INNER JOIN `contacto` ON `contacto`.`CLPR_ID` = `empresas`.`empresas_id` "
  		    		 		+ "	INNER JOIN `tipo_contacto` ON `tipo_contacto`.`TICON_ID` = `contacto`.`TICON_ID` "
  		    		 		+ "	INNER JOIN `direccion` ON `contacto`.`DIRE_ID` = `direccion`.`DIRE_ID` "
  		    		 		+ "	INNER JOIN `estados_vig_novig` estvnv ON `estvnv`.`estados_vig_novig_id` = `contacto`.`estados_vig_novig_id` "
  		    		 		+ " INNER JOIN `estado_clpr` ON `estado_clpr`.`Estado_Clpr_id` = `empresas`.`Estado_Clpr_id` "
  		    		 		+ "	WHERE"
  		    		 		+ "		`contacto`.`CONT_ID` ="+request.getParameter("con_id");
  		 		    System.out.println(SQL_clpr);
  		 		    ResultSet clpr_rs = state.executeQuery(SQL_clpr);
  				     //recorremos los resultados de la consulta
  				    while(clpr_rs.next()){        	   
  		        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
  				    	empresas_id=clpr_rs.getString("empresas_id");
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
  				    	
  				    	request.setAttribute("CONT_NOMBRE",clpr_rs.getString("CONT_NOMBRE"));
  				    	request.setAttribute("CONT_ID",clpr_rs.getString("CONT_ID"));
  				    	
  				    	request.setAttribute("CONT_APEM",clpr_rs.getString("CONT_APEM"));
  				    	request.setAttribute("CONT_APEP",clpr_rs.getString("CONT_APEP"));
  				    	request.setAttribute("CONT_CARGO",clpr_rs.getString("CONT_CARGO"));
  				    	
  				    	request.setAttribute("CONT_EMAIL",clpr_rs.getString("CONT_EMAIL"));
  				    	request.setAttribute("CONT_TELEFONO",clpr_rs.getString("CONT_TELEFONO"));
  				    	request.setAttribute("CONT_TELEFONOC",clpr_rs.getString("CONT_TELEFONOC"));
  				    	
  				    	request.setAttribute("TICON_DESCRIPCION",clpr_rs.getString("TICON_DESCRIPCION"));
  				    	request.setAttribute("DIRE_DIRECCION",clpr_rs.getString("DIRE_DIRECCION"));
  				    	request.setAttribute("DIRE_ID",clpr_rs.getString("DIRE_ID"));
  				    	
  				    	request.setAttribute("estvnv",clpr_rs.getString("estvnv"));
  				    	request.setAttribute("TICON_ID",clpr_rs.getString("TICON_ID"));
  				    	request.setAttribute("estvnv_id",clpr_rs.getString("estvnv_id"));
  				    	
  				    	request.setAttribute("CONT_OBS",clpr_rs.getString("CONT_OBS"));
  				    	request.setAttribute("Estado_Clpr_nombre",clpr_rs.getString("Estado_Clpr_nombre"));
  				    	
  				    	
  		    	    }
  				  //System.out.println("SIZE LIST: "+grupos.size());
  				    	
  			
  				  
  				  ///////////////////////////TRAEMOS tipo contacto para select 
					  
					  
					  
					  state = (Statement) ((java.sql.Connection) conexion).createStatement();
			    		
			    		 String SQL_tdir = "SELECT	`tipo_contacto`.`TICON_ID`,`tipo_contacto`.`TICON_DESCRIPCION` FROM `tipo_contacto` WHERE `tipo_contacto`.`estados_vig_novig_id`=1";
			 		    System.out.println(SQL_tdir);
			 		    ResultSet tdir_rs = state.executeQuery(SQL_tdir);
			 		    
			 		   ArrayList<String> t_con = new ArrayList<String>();
					   
			 		   //recorremos los resultados de la consulta
  					   	while(tdir_rs.next()){        	   
  			        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
  					    	t_con.add(tdir_rs.getString("TICON_ID")+"/"+tdir_rs.getString("TICON_DESCRIPCION"));
  			        	}
			 		    
					    	String[] ar_tcon = new String[t_con.size()];
  				        for(int x=0; x < t_con.size(); x++){
  				        	  ar_tcon[x]=t_con.get(x);
  				        }
				                
  				        request.setAttribute("ar_tcon", ar_tcon);
				        //--------------------- DIRECCION ----------------------//
						    String SQL_DIRECCION = "SELECT"
						    		+ "		`direccion`.`DIRE_ID`,`direccion`.`DIRE_DIRECCION` "
						    		+ "	FROM"
						    		+ "		direccion"
						    		+ "	WHERE"
						    		+ "		estados_vig_novig_id = 1 AND direccion.CLPR_ID = "+empresas_id
						    		+ "	ORDER BY"
						    		+ "		DIRE_DIRECCION";
						    System.out.println(SQL_DIRECCION);
						    ResultSet DIRECCION_RS = state.executeQuery(SQL_DIRECCION);
						    ArrayList<String> direcciones = new ArrayList<String>();
						    while(DIRECCION_RS.next()){
						    	direcciones.add(DIRECCION_RS.getString("DIRE_ID")+"/"+DIRECCION_RS.getString("DIRE_DIRECCION").replace("/", " ")+"&nbsp;"); }
						    DIRECCION_RS.close();
						    String[] ar_direcciones = new String[direcciones.size()];
						    for(int x=0; x < direcciones.size(); x++){ ar_direcciones[x]=direcciones.get(x);}
						    request.setAttribute("ar_direcciones", ar_direcciones);
				     
				            
						    //--------------------- PERSONAL ----------------------//
						    String SQL_PER = "SELECT * FROM personal";
						    ResultSet PER_RS = state.executeQuery(SQL_PER);
						    ArrayList<String> personal = new ArrayList<String>();
						    while(PER_RS.next()){ 
						    	personal.add(PER_RS.getString("PERS_ID")+"/"+PER_RS.getString("PERS_NOMBRE").replace("/", " ")); }
						    PER_RS.close();
						    String[] ar_per = new String[personal.size()];
						    for(int x=0; x < personal.size(); x++){ ar_per[x]=personal.get(x);}
						    request.setAttribute("ar_per", ar_per);
						          
						 //-------------------ESTADO VIG NO VIG -------------------------------//
						    
						    String SQL_ESTADOS = "SELECT * FROM estados_vig_novig";
						    ResultSet ESTADOS_RS = state.executeQuery(SQL_ESTADOS);
						    
						    //definimos un arreglo para los resultados
						    
						    ArrayList<String> estados = new ArrayList<String>();
						   
						    //recorremos los resultados de la consulta
						    while(ESTADOS_RS.next()){        	   
				        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
						    	estados.add(ESTADOS_RS.getString("estados_vig_novig_id")+"/"+ESTADOS_RS.getString("estados_vig_novig_nombre"));
				        	    
				    	    }
						    	
						  String[] ar_estados = new String[estados.size()];
				          for(int x=0; x < estados.size(); x++){
				           	ar_estados[x]=estados.get(x);
				          }
				          request.setAttribute("ar_estados", ar_estados);
						         
  				
  						         
  					          
  		 		 
  		 		  clpr_rs.close();
  				  state.close();
  		          conexion.close();
  		                
  				}catch(Exception ex){
  					ex.printStackTrace();
  				    out.println("Error "+ex);
  				}
  				
  				RequestDispatcher rd = request.getRequestDispatcher("mcon2.jsp");
  				rd.forward(request, response);
  		
  	}


}
