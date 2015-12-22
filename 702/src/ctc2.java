

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
 * Servlet implementation class ctc2
 */
@WebServlet("/ctc2")
public class ctc2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ctc2() {
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
		
		if(request.getParameter("logout") !=  null){
				Controlador.eraseCookie(request, response);
				response.sendRedirect("/001/");	
				return;
		}
		
		//////////////////////////////////////////////////
		////////VERIFICAMOS PERMISO ASOCIADO/////////////

		String p=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), Constantes.PCONSULTAR);
		
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
		
		
		Statement state = null;
		ResultSet ACTIVO_RS = null;
		ResultSet ESTADOS_RS = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		Statement state2 = (Statement) ((java.sql.Connection) conexion).createStatement();
		    
    			
 				if(request.getParameter("id_per") !=  null){
 					//EXTRAEMOS FOTO ACTUAL DE ACTIVOS PARA EL PERIODO ACTUAL
 	 				String SQL_ACTIVOS_ACTUAL = ""
 				    		+ " SELECT a.id_activo   "
 				    		+ " FROM activos_historia a"
 				    		+ " INNER JOIN producto p ON p.PROD_ID = a.PROD_ID "
 				    		+ " INNER JOIN funcionalidad f ON f.FUNC_ID = p.FUNC_ID"
 				    		+ " INNER JOIN (SELECT 		`periodos_tc`.`peri_tc_fdesde`,`periodos_tc`.`peri_tc_fhasta`	,periodos_tc.peri_tc_correlativo	"
 				    		+ "					FROM			`periodos_tc`		"
 				    		+ "					WHERE 			`periodos_tc`.`peri_tc_id` ="+request.getParameter("id_per")
 				    		+ "						AND `periodos_tc`.`peri_tc_id_emp` = "+request.getParameter("empresas_id")
 				    		+ " ) per "
 				    		+ " WHERE p.FUNC_ID = 6  "
 				    		+ " AND a.empresas_id = "+request.getParameter("empresas_id")+""
 				    		+ " AND a.`fecha_captura`>=per.peri_tc_fdesde AND a.`fecha_captura`<=peri_tc_fhasta"
 				    		+ "	GROUP BY a.`id_activo`  "
 				    		+ " ORDER BY p.PROD_DESC_CORTO ";
 				    System.out.println(SQL_ACTIVOS_ACTUAL);
 				    ResultSet ACTIVOACTUAL_RS = state.executeQuery(SQL_ACTIVOS_ACTUAL);
 				    ArrayList<String> activos_actual = new ArrayList<String>();
 				    while(ACTIVOACTUAL_RS.next()){
 				    	activos_actual.add(ACTIVOACTUAL_RS.getString("id_activo"));
 				    	
 		    	    }
 				  
 			             
 				    request.setAttribute("activos_actual", activos_actual);
 					
 					
 					//EXTRAEMOS FOTO DE ACTIVOS (TODOS LOS QUE TUVO HACIA ATRAS 
 				   String SQL_ACTIVO = ""
				    		+ " SELECT a.id_activo as ALT_ID,a.serie, p.PROD_DESC_CORTO, e.empresas_nombrenof,per.peri_tc_correlativo,"
				    		+ "		u.ubi_fisica, d.DIRE_DIRECCION, e.empresas_nombrenof  "
				    		+ "		, `tipo_contador_principal`.`tp_tc_id`"
				    		+ "		,`tipo_contador_principal`.tp_tc_nombre    "
				    	
				    		+ " FROM activos_historia a"
				    		+ " INNER JOIN producto p ON p.PROD_ID = a.PROD_ID "
				    		+ " INNER JOIN funcionalidad f ON f.FUNC_ID = p.FUNC_ID"
				    		+ " INNER JOIN ubicacion u ON u.UBI_ID = a.UBI_ID"
				    		
							+ " INNER JOIN `activo_tptc` ON (`activo_tptc`.`estados_vig_novig_id`=1 AND `activo_tptc`.`id_activo`=a.`id_activo`) "
							+ " INNER JOIN `tipo_contador_principal` ON ( `tipo_contador_principal`.`tp_tc_id`=activo_tptc.`tp_tc_id`) "
		
				    		+ " INNER JOIN direccion d ON d.DIRE_ID = u.DIRE_ID"
				    		+ " INNER JOIN empresas e ON e.empresas_id = d.CLPR_ID"
				    		
				    		+ " INNER JOIN (SELECT 		`periodos_tc`.`peri_tc_fdesde`,`periodos_tc`.`peri_tc_fhasta`	,periodos_tc.peri_tc_correlativo	"
				    		+ "					FROM			`periodos_tc`		"
				    		+ "					WHERE 			`periodos_tc`.`peri_tc_id` ="+request.getParameter("id_per")
				    		+ "						AND `periodos_tc`.`peri_tc_id_emp` = "+request.getParameter("empresas_id")
				    		+ " ) per "
				    		+ " INNER JOIN (SELECT 		MIN(`periodos_tc`.`peri_tc_fdesde`) as peri_tc_fdesde 	"
				    		+ "					FROM			`periodos_tc`		"
				    		+ "					WHERE 			`periodos_tc`.`peri_tc_id` < "+request.getParameter("id_per")
				    		+ "						AND `periodos_tc`.`peri_tc_id_emp` = "+request.getParameter("empresas_id")+" "
				    		+ "					LIMIT 6 "
				    		+ " ) perFinal "
				    		+ " WHERE p.FUNC_ID = 6  "
				    		+ " AND a.empresas_id = "+request.getParameter("empresas_id")+""
				    		+ " AND a.fecha_captura >=IF(perFinal.peri_tc_fdesde IS NULL,per.peri_tc_fdesde,perFinal.peri_tc_fdesde) AND a.`fecha_captura`<=per.peri_tc_fhasta"
				    		+ "	GROUP BY a.`id_activo`,  `tipo_contador_principal`.`tp_tc_id`  "
				    		+ " ORDER BY d.DIRE_DIRECCION,u.ubi_fisica, p.PROD_DESC_CORTO ";
 				    System.out.println(SQL_ACTIVO);
 				    ACTIVO_RS =  state.executeQuery(SQL_ACTIVO);
 				    ArrayList<String> activos = new ArrayList<String>();
 				    while(ACTIVO_RS.next()){
 				    	activos.add(ACTIVO_RS.getString("ALT_ID")
 				    			+";;"+ACTIVO_RS.getString("PROD_DESC_CORTO")+" - "+ACTIVO_RS.getString("serie")
 				    			+";;"+ACTIVO_RS.getString("ubi_fisica")
 				    			+";;"+ACTIVO_RS.getString("DIRE_DIRECCION")
 				    			+";;"+ACTIVO_RS.getString("empresas_nombrenof")
 				    			+";;"+ACTIVO_RS.getString("tp_tc_nombre")
 				    			+";;"+ACTIVO_RS.getString("tp_tc_id"));
 				    	
 		    	    }
 				   ACTIVO_RS.close();
 				    
 				    String[] ar_activos = new String[activos.size()];
 				    for(int x=0; x < activos.size(); x++){
 				    	ar_activos[x]=activos.get(x);
 				    }
 			             
 				    request.setAttribute("ar_activos", ar_activos);
 				    
 				   ///////////////////////////////////////////////////
 					  //EXTRAEMOS PERRIODO SELECCIONADO DE EMPRESA 
 		 				
 		 				
 		 					
 		 					ArrayList<String> cont6_activo = new ArrayList<String>();
 		 	 				ArrayList<String> cont6_values = new ArrayList<String>();
 		 	 				ArrayList<String> cont6_difs = new ArrayList<String>();
 		 		 	 		
 		 					String SQL_VALUES = "select * from `toma_contador` "
 		 									+ "		where `toma_contador`.`estados_vig_novig_id`=1 and `tc_periodo_id` = '"+request.getParameter("id_per")+"'   ";
 		 	 				System.out.println(SQL_VALUES);
 		 	 				ResultSet RS_VALUES = state2.executeQuery(SQL_VALUES);
 		 					while(RS_VALUES.next()){
 		 							//System.out.println("agregando al valor 6 "+RS_VALUES.getInt("tc_activo_id")+" "+RS_VALUES.getString("tc_valor"));
 		 							
 		 							cont6_activo.add(RS_VALUES.getString("tc_activo_id")+";;"+RS_VALUES.getString("tp_tc_id"));
 		 							cont6_values.add(RS_VALUES.getString("tc_valor"));
 		 							cont6_difs.add(RS_VALUES.getString("tc_valor_diferencia"));
 		 						
 		 					}
 		 					request.setAttribute("cont6_activo", cont6_activo);
 		 	 				request.setAttribute("cont6_values", cont6_values);
 		 	 				request.setAttribute("cont6_difs", cont6_difs);
 		 				
 		 				
 		 				
 		 				
 		 				
 		 	 			//buscamos periodos anteriores
 	 		 				String SQL_PERIODO_PREV = "SELECT "
 	 		 						+ "		`periodos_tc`.`peri_tc_id`,"
 	 		 						+ "		`periodos_tc`.`peri_tc_correlativo`, "
 	                                + "		DATE_FORMAT(`periodos_tc`.`peri_tc_fdesde`,'%d-%m-%Y') AS peri_tc_fdesde,"
 	 			    		        + "		DATE_FORMAT(`periodos_tc`.`peri_tc_fhasta`,'%d-%m-%Y') AS peri_tc_fhasta "
 	                                + "		FROM"
 	 		 						+ "			`periodos_tc`"
 	 		 						+ "		WHERE "
 	 		 						+ "			`periodos_tc`.`peri_tc_id`< "+request.getParameter("id_per")
 	 		 						+ "			AND `periodos_tc`.`peri_tc_id_emp` = "+request.getParameter("empresas_id")
 	 		 						+ "			AND `periodos_tc`.`estados_vig_novig_id` = 1 "
 	 		 						+ "			ORDER BY `periodos_tc`.`peri_tc_correlativo` DESC LIMIT 6";
 	 		 				System.out.println(SQL_PERIODO_PREV);
 		 				System.out.println(SQL_PERIODO_PREV);
 		 				ResultSet RS_PERIODO_PREV = state.executeQuery(SQL_PERIODO_PREV);
 		 				
 		 				String periodos_para_tc[] = new String[7];
 		 				String periodosfechas_para_tc[] = new String[7];
                        
 		 				ArrayList<String> cont5_activo = new ArrayList<String>();
 		 				ArrayList<String> cont5_values = new ArrayList<String>();
 		 				ArrayList<String> cont5_difs = new ArrayList<String>();
		 		 	 		
 		 				ArrayList<String> cont4_activo = new ArrayList<String>();
 		 				ArrayList<String> cont4_values = new ArrayList<String>();
 		 				ArrayList<String> cont4_difs = new ArrayList<String>();
		 		 	 		
 		 				ArrayList<String> cont3_activo = new ArrayList<String>();
 		 				ArrayList<String> cont3_values = new ArrayList<String>();
 		 				ArrayList<String> cont3_difs = new ArrayList<String>();
		 		 	 		
 		 				ArrayList<String> cont2_activo = new ArrayList<String>();
 		 				ArrayList<String> cont2_values = new ArrayList<String>();
 		 				ArrayList<String> cont2_difs = new ArrayList<String>();
		 		 	 		
 		 				ArrayList<String> cont1_activo = new ArrayList<String>();
 		 				ArrayList<String> cont1_values = new ArrayList<String>();
 		 				ArrayList<String> cont1_difs = new ArrayList<String>();
		 		 	 		
 		 				ArrayList<String> cont0_activo = new ArrayList<String>();
 		 				ArrayList<String> cont0_values = new ArrayList<String>();
 		 				ArrayList<String> cont0_difs = new ArrayList<String>();
		 		 	 		
 		 				int cont=5;
 		 				while(RS_PERIODO_PREV.next()){
 		 					periodos_para_tc[cont]=RS_PERIODO_PREV.getString("peri_tc_correlativo");
 		 					periodosfechas_para_tc[cont]=RS_PERIODO_PREV.getString("peri_tc_fdesde")+" "+RS_PERIODO_PREV.getString("peri_tc_fhasta");

 		 					//sacamos para este periodo la toma de contadores 
 		 					
 		 					String SQL_VALUES_ANT = "select * from `toma_contador` where `toma_contador`.`estados_vig_novig_id`=1 and  `tc_periodo_id` = '"+RS_PERIODO_PREV.getString("peri_tc_id")+"'   ";
 		 	 				System.out.println(SQL_VALUES_ANT);
 		 	 				ResultSet RS_VALUES_ANT = state2.executeQuery(SQL_VALUES_ANT);
 		 					while(RS_VALUES_ANT.next()){
 		 						if(cont==5){
 		 							cont5_activo.add(RS_VALUES_ANT.getString("tc_activo_id")+";;"+RS_VALUES_ANT.getString("tp_tc_id"));
 		 							cont5_values.add(RS_VALUES_ANT.getString("tc_valor"));
 		 							cont5_difs.add(RS_VALUES_ANT.getString("tc_valor_diferencia"));
 		 						}
 		 						if(cont==4){
 		 							cont4_activo.add(RS_VALUES_ANT.getString("tc_activo_id")+";;"+RS_VALUES_ANT.getString("tp_tc_id"));
 		 							cont4_values.add(RS_VALUES_ANT.getString("tc_valor"));
 		 							cont4_difs.add(RS_VALUES_ANT.getString("tc_valor_diferencia"));
 		 						}
 		 						if(cont==3){
 		 							cont3_activo.add(RS_VALUES_ANT.getString("tc_activo_id")+";;"+RS_VALUES_ANT.getString("tp_tc_id"));
 		 							cont3_values.add(RS_VALUES_ANT.getString("tc_valor"));
 		 							cont3_difs.add(RS_VALUES_ANT.getString("tc_valor_diferencia"));
 		 						}
 		 						if(cont==2){
 		 							cont2_activo.add(RS_VALUES_ANT.getString("tc_activo_id")+";;"+RS_VALUES_ANT.getString("tp_tc_id"));
 		 							cont2_values.add(RS_VALUES_ANT.getString("tc_valor"));
 		 							cont2_difs.add(RS_VALUES_ANT.getString("tc_valor_diferencia"));
 		 						}
 		 						if(cont==1){
 		 							cont1_activo.add(RS_VALUES_ANT.getString("tc_activo_id")+";;"+RS_VALUES_ANT.getString("tp_tc_id"));
 		 							cont1_values.add(RS_VALUES_ANT.getString("tc_valor"));
 		 							cont1_difs.add(RS_VALUES_ANT.getString("tc_valor_diferencia"));
 		 						}
 		 						if(cont==0){
 		 							cont0_activo.add(RS_VALUES_ANT.getString("tc_activo_id")+";;"+RS_VALUES_ANT.getString("tp_tc_id"));
 		 							cont0_values.add(RS_VALUES_ANT.getString("tc_valor"));
 		 							cont0_difs.add(RS_VALUES_ANT.getString("tc_valor_diferencia"));
 		 						}
 		 						
 		 					}
 		 					
 		 					
 		 					cont--;
 		 				}
 		 				
 		 				request.setAttribute("cont5_activo", cont5_activo);
 		 				request.setAttribute("cont5_values", cont5_values);
 		 				request.setAttribute("cont5_difs", cont5_difs);
 		 				
 		 				request.setAttribute("cont4_activo", cont4_activo);
 		 				request.setAttribute("cont4_values", cont4_values);
 		 				request.setAttribute("cont4_difs", cont4_difs);
 		 				
 		 				request.setAttribute("cont3_activo", cont3_activo);
 		 				request.setAttribute("cont3_values", cont3_values);
 		 				request.setAttribute("cont3_difs", cont3_difs);
 		 				
 		 				request.setAttribute("cont2_activo", cont2_activo);
 		 				request.setAttribute("cont2_values", cont2_values);
 		 				request.setAttribute("cont2_difs", cont2_difs);
 		 				
 		 				request.setAttribute("cont1_activo", cont1_activo);
 		 				request.setAttribute("cont1_values", cont1_values);
 		 				request.setAttribute("cont1_difs", cont1_difs);
 		 				
 		 				request.setAttribute("cont0_activo", cont0_activo);
 		 				request.setAttribute("cont0_values", cont0_values);
 		 				request.setAttribute("cont0_difs", cont0_difs);
 		 				
 		 				request.setAttribute("periodos_para_tc", periodos_para_tc);
 		 				request.setAttribute("periodosfechas_para_tc", periodosfechas_para_tc);
 		 				
 		 				request.setAttribute("id_per", request.getParameter("id_per"));
 		 				
 		 				
 		 				
 		 				
 				}//fin seleccion periodo
 				
			    
			  
 				
 				
 				 //--------------------- EMPRESA SELECCIONADA ----------------------//
 				
 					String SQL_EMP = ""
				    		+ " SELECT a.empresas_nombrenof "
				    		+ " FROM empresas a"
				    		+ " WHERE a.empresas_id = "+request.getParameter("empresas_id");
				    	
				    System.out.println(SQL_EMP);
				    ResultSet EMP_RS = state.executeQuery(SQL_EMP);
				     if(EMP_RS.next()){
				    	 request.setAttribute("empresas_nombrenof", EMP_RS.getString("empresas_nombrenof"));
		    	    }
 				
 				 //--------------------- PERIODOS ----------------------//
 			    String SQL_PERIODOS = "SELECT"
 			    		+ "		`periodos_tc`.`peri_tc_id`,"
 			    		+ "		`periodos_tc`.`peri_tc_id_emp`,"
 			    		+ "		`periodos_tc`.`peri_tc_correlativo`,"
 			    		+ "		DATE_FORMAT(`periodos_tc`.`peri_tc_fdesde`,'%d-%m-%Y') AS peri_tc_fdesde,"
 			    		+ "		DATE_FORMAT(`periodos_tc`.`peri_tc_fhasta`,'%d-%m-%Y') AS peri_tc_fhasta"
 			    		+ "	FROM"
 			    		+ "		`periodos_tc` "
 			    		+ "	WHERE `periodos_tc`.`estados_vig_novig_id` =1 "
 			    		+ "		AND `periodos_tc`.`peri_tc_id_emp`="+request.getParameter("empresas_id");
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
 				
 				conexion.close();
			    state.close();
			    RequestDispatcher rd = request.getRequestDispatcher("ctc2.jsp");
		        rd.forward(request, response);
 			
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("ERROR "+ex.getMessage());
		}
		
		
		
		
	
		
	
	}
	
}
