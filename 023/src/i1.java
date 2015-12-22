

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

/**
 * Servlet implementation class i1
 */
@WebServlet("/i1")
public class i1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public i1() {
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
		
		String id_iusuario=Controlador.getUsuIDSession(request);
		
		//grabar
		if(request.getParameter("grabar") !=  null){
			Statement stategrabar = null;
			Statement stateval = null;
			try {
				String prod_desc_corto = request.getParameter("prod_desc_corto");
				prod_desc_corto=prod_desc_corto.replace("'", "\'");
		    	
		    	String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
		    	
		    	String prod_desc_largo = request.getParameter("prod_desc_largo");
		    	prod_desc_largo=prod_desc_largo.replace("'", "\'");
		    	String prod_observacion = request.getParameter("prod_observacion");
		    	prod_observacion=prod_observacion.replace("'", "\'");
		    	String prod_vida_util_imp = request.getParameter("prod_vida_util_imp");
		    	String prod_vida_util_ano = request.getParameter("prod_vida_util_ano");
		    	
		    	String prod_vida_util_contable = request.getParameter("prod_vida_util_contable");
		    	String prod_cm_contable = request.getParameter("prod_cm_contable");
		    	String prod_tipo = request.getParameter("prod_tipo");
		    	String marca_id = request.getParameter("marca_id");
		    	
		    	String func_id = request.getParameter("func_id");
		    	String prod_pn_tli_codfab = request.getParameter("prod_pn_tli_codfab");
		    	prod_pn_tli_codfab=prod_pn_tli_codfab.replace("'", "\'");
		    	
		    	String prod_cod_bar_fab = request.getParameter("prod_cod_bar_fab");
		    	prod_cod_bar_fab=prod_cod_bar_fab.replace("'", "\'");
		    	
		    	String est_prod_id = request.getParameter("est_prod_id");
		    	String prod_es_color = request.getParameter("prod_es_color");
		    	if(prod_es_color==null)prod_es_color="0";
		    	
		    	
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		stateval = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		String sql_valfunc="SELECT * FROM producto WHERE prod_pn_tli_codfab='"+prod_pn_tli_codfab.toUpperCase()+"'";
	    		ResultSet rs_valfunc = stateval.executeQuery(sql_valfunc);
	    		//stateval.close();
	    		if(rs_valfunc.next()){
	    			response.sendRedirect("menu?Exito=NOOK");
	    			return;
	    		}else{
	    			
	    			if(Constantes.SYNCDB==1){
	       			 ConBirt birtBD= new ConBirt();
	       			 
	       			 String estado_v_nv="VIGENTE";
	       			 if(estados_vig_novig_id.equals("2")) estado_v_nv="NO VIGENTE";
	       			 
	       			String prod_tipo_birt="";
	       			 if(prod_tipo.equals("1")) prod_tipo_birt="PRIMARIO";
	       			 if(prod_tipo.equals("2")) prod_tipo_birt="SECUNDARIO";
	       			 if(prod_tipo.equals("3")) prod_tipo_birt="SECUNDARIO";
	       			 
	       			String prod_cm_contable_birt="";
	       			 if(prod_cm_contable.equals("1")) prod_cm_contable_birt="SI";
	       			 if(prod_cm_contable.equals("2")) prod_cm_contable_birt="NO";
	       			 
	       			 
	       			 
	       			
	       			
	       			 String insert_birt=" INSERT INTO [PRODUCTO] ("
	       			 		+ "		[PRODUCTO].[PROD_ESTADO],"
	       			 		+ "		[PRODUCTO].[MARC_ID],"
	       			 		+ "		[PRODUCTO].[FUNC_ID],"
	       			 		+ "		[PRODUCTO].[PROD_PN_TLI_CODFAB],"
							+ "		[PRODUCTO].[PROD_COD_BAR_FAB],"
							+ "		[PRODUCTO].[PROD_DESC_LARGO],"
							+ "		[PRODUCTO].[PROD_DESC_CORTO],"
							+ "		[PRODUCTO].[PROD_OBSERVACION],"
							+ "		[PRODUCTO].[PROD_VIDA_UTIL_IMP],"
							+ "		[PRODUCTO].[PROD_VIDA_UTIL_ANO],"
							+ "		[PRODUCTO].[PROD_VIDA_UTIL_CONTABLE],"

							+ "		[PRODUCTO].[PROD_CM_CONTABLE],"
							+ "		[PRODUCTO].[PROD_TIPO],"
	       			 		+ "		[PRODUCTO].[USU_ID_UM],"
	       			 		+ "		[PRODUCTO].[PROD_FECHA_UM]"
	       			 		+ "	)"
	       			 		+ "	VALUES"
	       			 		+ "		(		"
	       			 				+ "		'"+estado_v_nv+"'"
	       			 				+ "	,	'"+marca_id+"'"
	       			 				+ "	,  '"+func_id+"' "
	       			 				+ "	,  '"+prod_pn_tli_codfab.toUpperCase()+"' "
	       			 				+ "	,  '"+prod_cod_bar_fab.toUpperCase()+"' "
	       			 				+ "	,  '"+prod_desc_largo.toUpperCase()+"' "
	       			 				+ "	,  '"+prod_desc_corto.toUpperCase()+"' "
	       			 				+ "	,  '"+prod_observacion.toUpperCase()+"' "
	       			 				+ "	,  '"+prod_vida_util_imp+"' "
	       			 				+ "	,  '"+prod_vida_util_ano+"' "
	       			 				+ "	,  '"+prod_vida_util_contable+"' "
	       			 				+ "	,  '"+prod_cm_contable_birt+"' "
	       			 				+ "	,  '"+prod_tipo_birt+"' "
	       			 				+ "	,	"+id_iusuario+""
	       			 				+ "	, 	getdate() "
	       			 				+ " )";
	       			
	       			 System.out.println("BIRT: "+insert_birt);
	       		     birtBD.ConsultaINUP(insert_birt);
	       		     
	       		      
	       		     birtBD.Desconectar();
	       			}
	    			
	    			
			    String SQL_INSERT = "INSERT INTO producto (ult_idper_exec"
			    		+ ",marca_id,func_id,prod_pn_tli_codfab,prod_cod_bar_fab,prod_desc_largo,prod_desc_corto,prod_observacion,prod_vida_util_imp,prod_vida_util_ano,prod_vida_util_contable,prod_cm_contable,prod_tipo ,estados_vig_novig_id,est_prod_id"
			    		+ ",feccreacion,creador,prod_es_color) "
			    		+ "	VALUES ("+Constantes.PINGRESAR
			    		+",'"+marca_id+"'"
			    		+",'"+func_id+"'"
			    		+",'"+prod_pn_tli_codfab.toUpperCase()+"'"
			    		+",'"+prod_cod_bar_fab.toUpperCase()+"'"
			    		+",'"+prod_desc_largo.toUpperCase()+"'"
			    		+",'"+prod_desc_corto.toUpperCase()+"'"
			    		+",'"+prod_observacion.toUpperCase()+"'"
	    				+",'"+prod_vida_util_imp+"'"
	    				+",'"+prod_vida_util_ano+"'"
	    				+",'"+prod_vida_util_contable+"'"
	    				+",'"+prod_cm_contable+"'"
	    				+",'"+prod_tipo+"'"
	    				+ ","+estados_vig_novig_id+""
	    				+ ","+est_prod_id+""
	    				+ ",now(),"+id_iusuario+",'"+prod_es_color+"')";
			    System.out.println(SQL_INSERT);
			    stategrabar.executeUpdate(SQL_INSERT);
			    stateval.close();
    			stategrabar.close();
	            conexion.close(); 
	            
	            //RequestDispatcher rd_up = request.getRequestDispatcher("menu?Exito=OK");
	            //rd_up.forward(request, response);
	            
	            response.sendRedirect("menu?Exito=OK");
	            
	            return;
	    		}
			}catch(Exception ex){
				ex.printStackTrace();
			    out.println("Error "+ex);
			    return;
			}
			
		}
		
		Statement state = null;
		Statement statecor = null;		
		ResultSet ESTADOS_RS = null;
		ResultSet CORRELATIVO_RS = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 statecor = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
		    String SQL_ESTADOS = "SELECT * FROM estados_vig_novig";
		    ESTADOS_RS =  state.executeQuery(SQL_ESTADOS);
		    
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
        
          ///////SELECT DE ESTADO PRODUCTO //////////////
          
          String SQL_estprod = ""
 		    		+ "SELECT est_prod_id,est_prod_name "
 		    		+ " FROM estado_producto  "
 		    		+ "ORDER BY est_prod_name";
 		    System.out.println(SQL_estprod);
 		    ResultSet EstProd_RS = state.executeQuery(SQL_estprod);
		    
		    //definimos un arreglo para los resultados
		    
		    ArrayList<String> estProd = new ArrayList<String>();
		   
		    //recorremos los resultados de la consulta
		    while(EstProd_RS.next()){        	   
        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	estProd.add(EstProd_RS.getString("est_prod_id")+";;"+EstProd_RS.getString("est_prod_name"));
        	    
    	    }
		    request.setAttribute("estProd", estProd);
          ///////SELECT DE MARCAS //////////////
          
          String SQL_Marca = ""
 		    		+ "SELECT marca_id,marca_nombre "
 		    		+ " FROM marca m "
 		    		+ "ORDER BY m.marca_nombre";
 		    System.out.println(SQL_Marca);
 		    ResultSet Marca_RS = state.executeQuery(SQL_Marca);
		    
		    //definimos un arreglo para los resultados
		    
		    ArrayList<String> marcas = new ArrayList<String>();
		   
		    //recorremos los resultados de la consulta
		    while(Marca_RS.next()){        	   
        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	marcas.add(Marca_RS.getString("marca_id")+";;"+Marca_RS.getString("marca_nombre"));
        	    
    	    }
		    request.setAttribute("marcas", marcas);
		    
		    ///////SELECT DE FUNCIONALIDAD //////////////
	          
		    String SQL_Func = ""
   		    		+ "SELECT func_id,func_nombre"
   		    		+ " FROM funcionalidad f "
   		    		+ "	WHERE f.estados_vig_novig_id=1  "
   		    		+ "ORDER BY f.func_nombre";
   		    System.out.println(SQL_Func);
   		    ResultSet Funcs_RS = state.executeQuery(SQL_Func);
  		    
  		    //definimos un arreglo para los resultados
  		    
  		    ArrayList<String> funcionalidades = new ArrayList<String>();
  		   
  		    //recorremos los resultados de la consulta
  		    while(Funcs_RS.next()){        	   
          	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
  		    	funcionalidades.add(Funcs_RS.getString("func_id")+";;"+Funcs_RS.getString("func_nombre"));
          	    
      	    }
  		    	
			    request.setAttribute("funcionalidades", funcionalidades);
          
		    ///////CORRELATIVO //////////////
          
          
          String last_id_func_sql="SELECT"
		    		+ " 	`producto`.`prod_id`"
		    		+ " FROM"
		    		+ " 	`producto`"
		    		+ " ORDER BY"
		    		+ " 	`producto`.`prod_id` DESC"
		    		+ " LIMIT 1";
          System.out.println("correlativo : "+last_id_func_sql);
          CORRELATIVO_RS =  statecor.executeQuery(last_id_func_sql);
         
          int correlativo=1;
		  if(CORRELATIVO_RS.next()) correlativo=CORRELATIVO_RS.getInt("prod_id")+1;
		  System.out.println("correlativo : "+correlativo);
          request.setAttribute("correlativo", correlativo+"");
         
          
          ESTADOS_RS.close();
		  state.close();
         
          statecor.close();
          conexion.close();
          
		}catch(Exception ex){
		    out.println("ERROR "+ex);
		    ex.printStackTrace();
		}
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("i1.jsp");
        rd.forward(request, response);
		
        
        
	}

}
