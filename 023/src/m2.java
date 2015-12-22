

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
 * Servlet implementation class m2
 */
@WebServlet("/m2")
public class m2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public m2() {
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
  	
  	String p=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), Constantes.PMODIFICAR);
  	
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
  		try {
  			//import java.io.IOException;
  			Class.forName("com.mysql.jdbc.Driver");
      		Connection conexion=(Connection) DriverManager.getConnection
      	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
      		Statement state = (Statement) ((java.sql.Connection) conexion).createStatement();
      		Statement stateval = (Statement) ((java.sql.Connection) conexion).createStatement();
  		    
      		 if(request.getParameter("grabar") !=  null){
      			 
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
		    	 
		    	String sql_valfunc="SELECT * FROM producto WHERE prod_pn_tli_codfab='"+prod_pn_tli_codfab.toUpperCase()+"' AND prod_id<>'"+request.getParameter("id_p")+"'";
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
	       			
			       	 String up=" UPDATE [PRODUCTO] SET "
	       			 		+ "		[PRODUCTO].[PROD_ESTADO]='"+estado_v_nv+"',"
	       			 		+ "		[PRODUCTO].[MARC_ID]='"+marca_id+"',"
	       			 		+ "		[PRODUCTO].[FUNC_ID]='"+func_id+"',"
	       			 		+ "		[PRODUCTO].[PROD_PN_TLI_CODFAB]='"+prod_pn_tli_codfab.toUpperCase()+"',"
							+ "		[PRODUCTO].[PROD_COD_BAR_FAB]='"+prod_cod_bar_fab.toUpperCase()+"',"
							+ "		[PRODUCTO].[PROD_DESC_LARGO]='"+prod_desc_largo.toUpperCase()+"',"
							+ "		[PRODUCTO].[PROD_DESC_CORTO]='"+prod_desc_corto.toUpperCase()+"',"
							+ "		[PRODUCTO].[PROD_OBSERVACION]='"+prod_observacion.toUpperCase()+"',"
							+ "		[PRODUCTO].[PROD_VIDA_UTIL_IMP]='"+prod_vida_util_imp+"',"
							+ "		[PRODUCTO].[PROD_VIDA_UTIL_ANO]='"+prod_vida_util_ano+"',"
							+ "		[PRODUCTO].[PROD_VIDA_UTIL_CONTABLE]='"+prod_vida_util_contable+"',"
							+ "		[PRODUCTO].[PROD_CM_CONTABLE]='"+prod_cm_contable_birt+"',"
							+ "		[PRODUCTO].[PROD_TIPO]='"+prod_tipo_birt+"',"
	       			 		+ "		[PRODUCTO].[USU_ID_UM]='"+Controlador.getUsuIDSession(request)+"',"
	       			 		+ "		[PRODUCTO].[PROD_FECHA_UM]=getdate() "
	       			 		+ "	WHERE"
	       			 		+ "		[PRODUCTO].[PROD_ID] = "+request.getParameter("id_p");
	       			
			       	 System.out.println("BIRT: "+up);
	       		     birtBD.ConsultaINUP(up);
	       		     
	       		      
	       		     birtBD.Desconectar();
	       			}
	    			
	    			
	    			state = (Statement) ((java.sql.Connection) conexion).createStatement();
	   				String UPDATE = "UPDATE producto SET "
		   						+ "		estados_vig_novig_id="+estados_vig_novig_id
		   						+ "		,prod_desc_corto='"+prod_desc_corto.toUpperCase()+"' "
		   						+ "		,prod_desc_largo='"+prod_desc_largo.toUpperCase()+"' "
		   						+ "		,prod_observacion='"+prod_observacion.toUpperCase()+"' "
		   						+ "		,prod_vida_util_imp='"+prod_vida_util_imp+"' "
		   						+ "		,prod_vida_util_ano='"+prod_vida_util_ano+"' "
		   						+ "		,prod_vida_util_contable='"+prod_vida_util_contable+"' "
		   						+ "		,prod_cm_contable='"+prod_cm_contable+"' "
		   						+ "		,prod_tipo='"+prod_tipo+"' "
		   						+ "		,marca_id='"+marca_id+"' "
		   						+ "		,func_id='"+func_id+"' "
		   						+ "		,prod_pn_tli_codfab='"+prod_pn_tli_codfab+"' "
		   						+ "		,prod_cod_bar_fab='"+prod_cod_bar_fab+"' "
		   						+ "		,est_prod_id='"+est_prod_id+"' "
                                + "		,prod_es_color='"+prod_es_color+"' "
                                + "		,accion_alertada=0"
		   						+ "		,ult_idper_exec="+Constantes.PMODIFICAR+" "
		   						+ "		,fecmod=now() "
		   						+ "		,modificador='"+Controlador.getUsuIDSession(request)+"' "
	   							+ "	WHERE prod_id="+request.getParameter("id_p");
	   				System.out.println(UPDATE);
	   				
			    state.executeUpdate(UPDATE);
			    stateval.close();
			    state.close();
	            conexion.close(); 
	            
	            response.sendRedirect("menu?Exito=OK");
	            
	            return;
	    		}
	    		
   			}
      		 
      		String SQL_PROD = ""
  		    		+ "SELECT prod_id,prod_pn_tli_codfab,prod_cod_bar_fab"
  		    		+ "	,estados_vig_novig_nombre"
  		    		+ "	,f.estados_vig_novig_id"
  		    		+ "	,prod_vida_util_contable"
  		    		+ "	,ma.marca_nombre"
  		    		+ "	,ma.marca_id"
  		    		+ "	,fu.func_nombre"
  		    		+ "	,fu.func_id"
  					+ "	,prod_tipo"
                    + "	,prod_es_color"
                    
  					+ "	,est_prod_id"
  					
		    		+ "	,prod_desc_corto,prod_vida_util_imp"
  		    		+ "	,prod_desc_largo,prod_vida_util_ano,prod_observacion,prod_cm_contable,"
  		    		+ "		CONCAT( "
  		    		+ "			`usuarios`.`Usuarios_nombre`, "
  		    		+ "			' ',"
  		    		+ "			`usuarios`.`Usuarios_ape_p` "
  		    		+ "		) AS creador, "
  		    		+ "		DATE_FORMAT(f.feccreacion,'%d-%m-%Y %H:%i:%s') AS feccreacion, "
  		    		+ "		IF ( "
  		    		+ "			f.fecmod IS NULL,"
  		    		+ "			' ',"
  		    		+ "			DATE_FORMAT(f.fecmod, '%d-%m-%Y %H:%i:%s')"
  		    		+ "		) AS fecmod,"
  		    		+ "		IF ("
  		    		+ "			f.modificador IS NULL,"
  		    		+ "			' ',"
  		    		+ "			CONCAT( "
  		    		+ "			`u2`.`Usuarios_nombre`,"
  		    		+ "			' ',"
  		    		+ "			`u2`.`Usuarios_ape_p`"
  		    		+ "			)"
  		    		+ "		) AS modificador "
  		    		+ " FROM producto f "
  		    		+ " 	INNER JOIN marca ma ON ma.marca_id=f.marca_id"
  		    		+ " 	INNER JOIN funcionalidad fu ON fu.func_id=f.func_id"
  		    		+ " 	INNER JOIN estados_vig_novig e ON e.estados_vig_novig_id=f.estados_vig_novig_id"
  		    		+ " 	INNER JOIN `usuarios` ON `f`.`creador` = `usuarios`.`Usuarios_id` "
  		    		+ " 	LEFT JOIN `usuarios` u2 ON `f`.`modificador` = `u2`.`Usuarios_id` "
  		    		+ " WHERE prod_id="+Integer.parseInt(request.getParameter("id_p"));
  		    System.out.println(SQL_PROD);
  		    ResultSet Prod_RS = state.executeQuery(SQL_PROD);
  		    if(Prod_RS.next()){ 
		    	
		    	request.setAttribute("prod_id",Prod_RS.getString("prod_id"));
		    	request.setAttribute("prod_pn_tli_codfab",Prod_RS.getString("prod_pn_tli_codfab"));
		    	request.setAttribute("prod_cod_bar_fab",Prod_RS.getString("prod_cod_bar_fab"));
		    	request.setAttribute("prod_vida_util_contable",Prod_RS.getString("prod_vida_util_contable"));
		    	
		    	request.setAttribute("prod_desc_corto",Prod_RS.getString("prod_desc_corto"));
		    	
		    	request.setAttribute("prod_vida_util_imp",Prod_RS.getString("prod_vida_util_imp"));
		    	
		    	request.setAttribute("prod_desc_largo",Prod_RS.getString("prod_desc_largo"));
		    	
		    	request.setAttribute("prod_vida_util_ano",Prod_RS.getString("prod_vida_util_ano"));
		    	
		    	request.setAttribute("prod_observacion",Prod_RS.getString("prod_observacion"));
		    	request.setAttribute("marca_nombre",Prod_RS.getString("marca_nombre"));
		    	request.setAttribute("marca_id",Prod_RS.getString("marca_id"));
		    	request.setAttribute("est_prod_id",Prod_RS.getString("est_prod_id"));
		    	
		    	request.setAttribute("func_nombre",Prod_RS.getString("func_nombre"));
		    	request.setAttribute("func_id",Prod_RS.getString("func_id"));
		    	
		    	request.setAttribute("prod_cm_contable",Prod_RS.getString("prod_cm_contable"));
		    	
		    	request.setAttribute("prod_tipo",Prod_RS.getString("prod_tipo"));
		    	request.setAttribute("prod_es_color",Prod_RS.getString("prod_es_color"));
		    	
                
		    	request.setAttribute("estados_vig_novig_nombre",Prod_RS.getString("estados_vig_novig_nombre"));
		    	request.setAttribute("estados_vig_novig_id",Prod_RS.getString("estados_vig_novig_id"));
		    	
		    	String fec_crea=Prod_RS.getString("feccreacion");
		    	String user_crea=Prod_RS.getString("creador");
		    	String fec_mod=Prod_RS.getString("fecmod");
		    	String user_mod=Prod_RS.getString("modificador") ;
		    	
		    	
		    	request.setAttribute("fec_crea", (fec_crea)+"");
	            request.setAttribute("fec_mod", (fec_mod)+"");
	            request.setAttribute("user_mod", (user_mod)+"");
	            request.setAttribute("user_crea", (user_crea)+"");
   	    }
  		    
  		    
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
  			    
  			    
  		///////SELECT DE ESTADOS //////////////
  			    
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
            
            
  		 Prod_RS.close();
  		   
  		  state.close();	    
            conexion.close();
  		}catch(Exception ex){
  			ex.printStackTrace();
  		    out.println("Error "+ex);
  		}
  		RequestDispatcher rd = request.getRequestDispatcher("m2.jsp");
          rd.forward(request, response);
  		
  	}

}
