

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
 * Servlet implementation class eubi3
 */
@WebServlet("/eubi3")
public class eubi3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public eubi3() {
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
					
					
					
					String dir_id = request.getParameter("dir_id");
			    	String UBI_DESCRIPCION = request.getParameter("UBI_DESCRIPCION");
			    	String tubi = request.getParameter("tubi");
			    	String UBI_USUARIO = request.getParameter("UBI_USUARIO");
			    	String ubi_id = request.getParameter("ubi_id");
			  
			    
			    	//insertamos en birt 
	    			
	    			if(Constantes.SYNCDB==1){
	       			 ConBirt birtBD= new ConBirt();
	       			 
	       			 String insert_birt="UPDATE [UBICACION] "
	       			 		+ "	SET [UBICACION].[UBI_ESTADO] = 'NO VIGENTE', "
	       			 		+ "	 [UBICACION].[UBI_FECHA_UM] = GETDATE(), "
	       			 		+ "	 [UBICACION].[UBI_USUARIO] = '"+Usuarios_ID+"' "
	       			 		+ "	WHERE"
	       			 		+ "		[UBICACION].[UBI_ID] = "+ubi_id;
	       			 
	       			 	System.out.println("BIRT: "+insert_birt);
	       			 	birtBD.ConsultaINUP(insert_birt);
	       		      
	       		        birtBD.Desconectar();
	       			}
			    	
	    			
	    			//desabilitamos la ubicacion 
	    			
	    			
					String sql_up="	UPDATE `ubicacion`"
							+ "		SET `ubicacion`.`estados_vig_novig_id` = 2,"
							+ "			`ubicacion`.`ubi_modificador` = "+Usuarios_ID+","
							+ "			`ubicacion`.`ubi_fecmod` = now()"
							+ "		WHERE"
							+ "			`ubicacion`.`UBI_ID` = "+ubi_id;
					
						System.out.println(sql_up);
						
						try{
						Class.forName("com.mysql.jdbc.Driver");
			    		Connection conexion=(Connection) DriverManager.getConnection
			    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
			    		 Statement state = (Statement) ((java.sql.Connection) conexion).createStatement();
			    		 
			    		 
			    		 state.executeUpdate(sql_up);
			    		 out.print("<script>location='/011/?Exito=OK'</script>");
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
			    		 		+ "		`empresas`.`empresas_id`, "
			    		 		+ "		`empresas`.`empresas_rut`, "
			    		 		+ "		`grupos`.`grupos_nombre`, "
			    		 		+ "		`empresas`.`empresas_nombrenof`, "
			    		 		+ "		`empresas`.`empresas_razonsocial`, "
			    		 		+ "		`estados_vig_novig`.`estados_vig_novig_nombre`, "
			    		 		+ " 	`empresas`.`empresas_escliente`, "
			    		 		+ "		`empresas`.`empresas_esproveedor`, "
			    		 		+ "		`empresas`.`empresas_esprospeccion`, "
			    		 		+ " 	IF(empresas.empresas_relacionada='1','SI','NO') AS empresas_rel, "
			    		 		+ " 	`direccion`.`DIRE_ID` , "
			    		 		+ "		`direccion`.`DIRE_NOMBRE`, "
			    		 		+ "		`direccion`.`DIRE_DIRECCION`, "
			    		 		+ "		`direccion`.`DIRE_SECTOR`, "
			    		 		+ "		`direccion`.`dire_lat`, "
			    		 		+ "		`direccion`.`dire_lon`, "
			    		 		+ "		`direccion`.`dire_nsa_tecnico`, "
			    		 		+ "		`direccion`.`dire_nsa_logistica`, "
			    		 		+ "		`direccion`.`dire_nsa_postventa`, "
			    		 		+ "		`tipo_direccion`.`TIDIR_NOMBRE`, "
			    		 		+ "		`direccion`.`DIRE_COD_POSTAL`, "
			    		 		+ "		`comuna`.`COMU_NOMBRE`, "	
			    		 		+ "		`cuadrante`.`CUAD_NOMBRE`, "
			    		 		+ "		`direccion`.`DIRE_CIUDAD`, "
			    		 		+ "		`region`.`REGI_NOMBRE`,  "
			    		 		+ "		`ubicacion`.`UBI_DESCRIPCION`, "
			    		 		+ "		`ubicacion`.`UBI_ID`, "
			    		 		+ "		`ubicacion`.`UBI_USUARIO`, " 
			    		 		+ "		`ubicacion`.`UBI_TIPO`, "
			    		 		+ "		`ubicacion`.`tpg_id`,  "
			    		 		+ "		`ubicacion`.`UBI_FISICA`,  "
			    		 		+ "		`ubicacion`.`ubi_usa_sububicacion`,  "
			    		 		+ "		IF(`ubicacion`.`ubi_usullave` is null, '',`ubicacion`.`ubi_usullave`) as ubi_usullave ,  "
			    		 		+ "		`configuracion_equipos`.`confe_nombre`   "
			    		 		
			    		 		+ "	FROM "
			    		 		+ " 	`empresas` "
			    		 		+ "	LEFT JOIN `grupos` ON `grupos`.`grupos_id` = `empresas`.`grupos_id` "
			    		 		+ "	INNER JOIN `direccion` ON (`direccion`.`CLPR_ID` = `empresas`.`empresas_id` AND `direccion`.`DIRE_ID` = "+request.getParameter("dir_id")+") "
			    		 		+ " INNER JOIN `ubicacion` ON (`ubicacion`.`DIRE_ID` = `direccion`.`DIRE_ID` AND `ubicacion`.`UBI_ID`= "+request.getParameter("ubi_id")+")"
			    		 		+ "	INNER JOIN `estados_vig_novig` ON `estados_vig_novig`.`estados_vig_novig_id` = `empresas`.`estados_vig_novig_id` "
			    		 		+ "	LEFT JOIN `configuracion_equipos` ON `configuracion_equipos`.`id_confe` = `ubicacion`.`id_confe` "
			    		 		
			    		 		+ "	INNER JOIN `tipo_direccion` ON `tipo_direccion`.`TIDIR_ID` = `direccion`.`TIDIR_ID` "	
			    		 		+ " INNER JOIN `comuna` ON `comuna`.`COMU_ID` = `direccion`.`COMU_ID` "
			    		 		+ "	INNER JOIN `cuadrante` ON `cuadrante`.`CUAD_ID` = `direccion`.`CUAD_ID`"
			    		 		+ " INNER JOIN `region` ON `region`.`REGI_ID` = `comuna`.`REGI_ID` ";
		    		 
		    		 
		    		 	
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
				    	request.setAttribute("conf", clpr_rs.getString("confe_nombre")==null?"":clpr_rs.getString("confe_nombre"));
				    	
				    	request.setAttribute("DIRE_ID", clpr_rs.getString("DIRE_ID"));
				    	request.setAttribute("DIRE_NOMBRE", clpr_rs.getString("DIRE_NOMBRE"));
				    	request.setAttribute("DIRE_DIRECCION", clpr_rs.getString("DIRE_DIRECCION"));
				    	request.setAttribute("DIRE_SECTOR", clpr_rs.getString("DIRE_SECTOR"));
				    	request.setAttribute("TIDIR_NOMBRE", clpr_rs.getString("TIDIR_NOMBRE"));
				    	request.setAttribute("DIRE_COD_POSTAL", clpr_rs.getString("DIRE_COD_POSTAL"));
				    	request.setAttribute("COMU_NOMBRE", clpr_rs.getString("COMU_NOMBRE"));
				    	request.setAttribute("CUAD_NOMBRE", clpr_rs.getString("CUAD_NOMBRE"));
				    	request.setAttribute("DIRE_CIUDAD", clpr_rs.getString("DIRE_CIUDAD"));
				    	request.setAttribute("REGI_NOMBRE", clpr_rs.getString("REGI_NOMBRE"));
				    	request.setAttribute("dire_lat", clpr_rs.getString("dire_lat"));	
				    	request.setAttribute("dire_lon", clpr_rs.getString("dire_lon"));
				    	
				    	request.setAttribute("dire_nsa_tecnico", clpr_rs.getString("dire_nsa_tecnico"));
				    	request.setAttribute("dire_nsa_logistica", clpr_rs.getString("dire_nsa_logistica"));
				    	request.setAttribute("dire_nsa_postventa", clpr_rs.getString("dire_nsa_postventa"));
				    	
				    	request.setAttribute("UBI_DESCRIPCION", clpr_rs.getString("UBI_DESCRIPCION"));
				    	request.setAttribute("UBI_ID", clpr_rs.getString("UBI_ID"));
				    	request.setAttribute("UBI_USUARIO", clpr_rs.getString("UBI_USUARIO"));
				    	request.setAttribute("id_ubi",  clpr_rs.getString("UBI_ID"));
				    	 
				    	request.setAttribute("UBI_TIPO",  clpr_rs.getString("UBI_TIPO"));
				    	request.setAttribute("tpg_id",  clpr_rs.getString("tpg_id"));
				    	request.setAttribute("UBI_FISICA",  clpr_rs.getString("UBI_FISICA"));
				    	request.setAttribute("ubi_usa_sububicacion",  clpr_rs.getString("ubi_usa_sububicacion"));
				    	request.setAttribute("ubi_usullave",  clpr_rs.getString("ubi_usullave"));
				    	 
				    	
		    	    }
				  	 
		 		  clpr_rs.close();
				  state.close();
		          conexion.close();
		                
				}catch(Exception ex){
					ex.printStackTrace();
				    out.println("Error "+ex);
				}
				
				RequestDispatcher rd = request.getRequestDispatcher("eubi3.jsp");
				rd.forward(request, response);
		
	}

}