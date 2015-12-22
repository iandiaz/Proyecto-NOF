
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
 * Servlet implementation class econ2
 */
@WebServlet("/econ2")
public class econ2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public econ2() {
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
			    	
			    	
			    	//insertamos en birt 
			    	
			    	if(Constantes.SYNCDB==1){
   	       			 ConBirt birtBD= new ConBirt();
   	       			 
   	       			 
   	       			 String insert_birt="UPDATE [CONTACTO] "
   	       			 		+ "	SET [CONTACTO].[CONT_ESTADO] = 'NO VIGENTE',"
   	       			 		+ "	 [CONTACTO].[USU_ID_UM] = '"+Usuarios_ID+"', "
   	       			 		+ "	 [CONTACTO].[CONT_FECHA_UM] = GETDATE()"
   	       			 		+ "	WHERE "
   	       			 		+ "		[CONTACTO].[CONT_ID] = '"+CONT_ID+"'";
   	       			 		
   	       			 	System.out.println("BIRT: "+insert_birt);
   	       			 	birtBD.ConsultaINUP(insert_birt);
   	       		      
   	       		        birtBD.Desconectar();
   	       			}
	    			
			    	String sql_in="	UPDATE `contacto` "
			    	+ "	SET `contacto`.`estados_vig_novig_id` = 2,"
			    	+ "	 `contacto`.`CONT_modificador` = "+Usuarios_ID+","
			    	+ "	 `contacto`.`CONT_fecmod` = now()"
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
		    		 		+ "		`contacto`.`CONT_OBS`, "
		    		 		+ "		`direccion`.`DIRE_DIRECCION`, "
		    		 		+ "		`tipo_contacto`.`TICON_DESCRIPCION`,"
		    		 		+ "		`estvnv`.estados_vig_novig_nombre AS estvnv , "
		    		 		+ " IF(empresas.empresas_relacionada='1','SI','NO') AS empresas_rel, "
		    		 		+ "		`estados_vig_novig`.`estados_vig_novig_nombre`,"
		    		 		+ " 	`empresas`.`empresas_escliente`,`empresas`.`empresas_esproveedor`,`empresas`.`empresas_esprospeccion`, "
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
				    	
				    	request.setAttribute("estvnv",clpr_rs.getString("estvnv"));
				    	request.setAttribute("CONT_OBS",clpr_rs.getString("CONT_OBS"));
				    	request.setAttribute("Estado_Clpr_nombre",clpr_rs.getString("Estado_Clpr_nombre"));
  				    	
		    	    }
				  //System.out.println("SIZE LIST: "+grupos.size());
				    	
			
				  
				  
				
						         
					          
		 		 
		 		  clpr_rs.close();
				  state.close();
		          conexion.close();
		                
				}catch(Exception ex){
					ex.printStackTrace();
				    out.println("Error "+ex);
				}
				
				RequestDispatcher rd = request.getRequestDispatcher("econ2.jsp");
				rd.forward(request, response);
		
	}


}
