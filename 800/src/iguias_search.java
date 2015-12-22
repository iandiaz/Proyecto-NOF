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

/**
 * Servlet implementation class iguias_search
 */
@WebServlet("/iguias_search")
public class iguias_search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public iguias_search() {
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
		//System.out.println("1");
		
		if(request.getParameter("logout") !=  null){
			Controlador.eraseCookie(request, response);
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
		Statement state_fac = null;
		ResultSet Alertas_RS = null;
		ResultSet RS_FAC = null;
		try {
			String TDTE=request.getParameter("tipo_dte");
			//System.out.println(TDTE);
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		state_fac = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
    		 String id_e = request.getParameter("id_e");
    		 
    		 
    		 String id_em="";
    		 if(id_e!=null){
    			 id_em=id_e;
    			 id_e="AND `f`.`"+TDTE+"_empresa_emisora`='"+id_e+"'"; 
    		 }
    		 else{
    			 id_em="123";
    			 id_e="AND `f`.`"+TDTE+"_empresa_emisora`='123'"; 
    		 }
    		
    		
    		String SQL_DATOS="",d1="",d2="",d3="",d4="",d5="";
    		if(TDTE.equals("801")){ SQL_DATOS = "SELECT f.801_id as id, c.empresas_razonsocial as clpr_razon_social, f.801_ESTADO as est, f.801_TOTAL as tot,"
    		 			+ " DATE_FORMAT(f.801_FECHA, '%d-%m-%Y') AS fecha"
    		 			+ " FROM `801` f INNER JOIN empresas c ON f.CLPR_ID = c.empresas_id "
    		 			+ "	WHERE f.id_dte IS NULL AND f.estados_vig_novig_id=1  "+id_e
    		 			+ "	ORDER BY f.801_FECHA";}
    		if(TDTE.equals("802")){ SQL_DATOS = "SELECT f.802_id as id, c.empresas_razonsocial as clpr_razon_social, f.802_estado as est, f.802_total as tot,"
		 			+ " DATE_FORMAT(f.802_fecha, '%d-%m-%Y') AS fecha"
		 			+ " FROM `802` f INNER JOIN empresas c ON f.cliente_id = c.empresas_id "
		 			+ "	WHERE f.id_dte IS NULL AND f.estados_vig_novig_id=1  "+id_e
		 			+ "	ORDER BY f.802_fecha";}
    		if(TDTE.equals("803")){ SQL_DATOS = "SELECT f.803_id as id, c.empresas_razonsocial as clpr_razon_social, f.803_estado as est, "
    				+ "f.803_total as tot,"
		 			+ " DATE_FORMAT(f.803_fecha, '%d-%m-%Y') AS fecha"
		 			+ " FROM `803` f INNER JOIN empresas c ON f.clpr_id = c.empresas_id "
		 			+ "	WHERE f.id_dte IS NULL AND (f.803_tipodte='33' or f.803_tipodte='34') AND f.estados_vig_novig_id=1 "+id_e
		 			+ "	ORDER BY f.803_fecha";}
    		if(TDTE.equals("804")){ SQL_DATOS = "SELECT f.804_id as id, c.empresas_razonsocial as clpr_razon_social, f.804_estado as est, "
    				+ "f.804_total as tot,"
		 			+ " DATE_FORMAT(f.804_fecha, '%d-%m-%Y') AS fecha"
		 			+ " FROM `804` f INNER JOIN empresas c ON f.clpr_id = c.empresas_id "
		 			+ "	WHERE f.id_dte IS NULL AND (f.804_tipodte='33' or f.804_tipodte='34') AND f.estados_vig_novig_id=1 "+id_e
		 			+ "	ORDER BY f.804_fecha";}
    		if(TDTE.equals("811")){ SQL_DATOS = "SELECT f.fac_comactivo_id as id, c.empresas_razonsocial as clpr_razon_social, f.fac_comactivo_estado as est, "
    				+ "f.fac_comactivo_totalfinal as tot,"
		 			+ " DATE_FORMAT(f.fac_comactivo_fecfac, '%d-%m-%Y') AS fecha"
		 			+ " FROM factura_compra_activo f INNER JOIN empresas c ON f.empresas_id = c.empresas_id WHERE f.id_dte IS NULL ORDER BY f.fac_comactivo_id";}
    		if(TDTE.equals("812")){ SQL_DATOS = "SELECT f.fac_comserv_id as id, c.empresas_razonsocial as clpr_razon_social, f.fac_comserv_estado as est, "
    				+ "f.fac_comserv_totalfinal as tot,"
		 			+ " DATE_FORMAT(f.fac_comserv_fecfac, '%d-%m-%Y') AS fecha"
		 			+ " FROM factura_compra_serv f INNER JOIN empresas c ON f.empresas_id = c.empresas_id WHERE f.id_dte IS NULL "
		 			+ "	ORDER BY f.fac_comserv_id";}
    		if(TDTE.equals("821")){ SQL_DATOS = "SELECT f.821_id as ID, c.empresas_razonsocial as clpr_razon_social, f.821_ESTADO as Est, f.821_TOTAL as tot,"
    		 			+ " DATE_FORMAT(f.821_FECHA, '%d-%m-%Y') AS fecha "
    		 			+ " FROM `821` f INNER JOIN empresas c ON f.CLPR_ID = c.empresas_id "
    		 			+ "	WHERE f.id_dte IS NULL AND f.estados_vig_novig_id=1 "+id_e
    		 			+ "	ORDER BY f.821_FECHA ";}
    		
    		if(TDTE.equals("822")){ SQL_DATOS = "SELECT f.822_ID as id, c.empresas_razonsocial as clpr_razon_social, "
    				+ " f.822_ESTADO as est, f.822_TOTAL as tot,"
		 			+ " DATE_FORMAT(f.822_FECHA, '%d-%m-%Y') AS fecha"
		 			+ " FROM `822` f INNER JOIN empresas c ON f.CLPR_ID = c.empresas_id "
		 			+ "	WHERE f.id_dte IS NULL AND f.estados_vig_novig_id=1 "+id_e
		 			+ "	ORDER BY f.822_FECHA ";}
    	
    		if(TDTE.equals("823")){ SQL_DATOS = "SELECT f.823_id as id, c.empresas_razonsocial as clpr_razon_social, "
    				+ " f.823_estado as est, 823_total as tot,"
		 			+ " DATE_FORMAT(f.823_fecha, '%d-%m-%Y') AS fecha"
		 			+ " FROM `823` f INNER JOIN empresas c ON f.clientes_id = c.empresas_id "
		 			+ "	WHERE f.id_dte IS NULL AND f.estados_vig_novig_id=1 "+id_e
		 			+ "	ORDER BY f.823_fecha DESC";}
    		if(TDTE.equals("824")){ SQL_DATOS = "SELECT f.824_id as id, c.empresas_razonsocial as clpr_razon_social, "
    				+ " f.824_estado as est, 824_total as tot,"
		 			+ " DATE_FORMAT(f.824_fecha, '%d-%m-%Y') AS fecha"
		 			+ " FROM `824` f INNER JOIN empresas c ON f.clientes_id = c.empresas_id "
		 			+ "	WHERE f.id_dte IS NULL AND f.estados_vig_novig_id=1 "+id_e
		 			+ "	ORDER BY f.824_id";}
    		
    		if(TDTE.equals("841")){ SQL_DATOS = "SELECT f.841_id as id, c.empresas_razonsocial as clpr_razon_social, "
    				+ " f.841_estado as est, 841_total as tot,"
		 			+ " DATE_FORMAT(f.841_fecha, '%d-%m-%Y') AS fecha"
		 			+ " FROM `841` f INNER JOIN empresas c ON f.clpr_id = c.empresas_id "
		 			+ "	WHERE  f.`841_est_aprobacion`=2 AND f.id_dte IS NULL AND f.estados_vig_novig_id=1 "+id_e
		 			+ "	ORDER BY f.841_id";}
    		System.out.println(SQL_DATOS);
 		    if(request.getAttribute("filtro")!=null){
 		    	request.setAttribute("filtro",request.getAttribute("filtro"));
 		    }
 		    Alertas_RS =  state.executeQuery(SQL_DATOS);
 		    ArrayList<String> alertas = new ArrayList<String>();
 		    while(Alertas_RS.next()){
 		    	if(TDTE.equals("801")){ 
 		    		String SQL_FAC = "SELECT IF(id_dte is null,'1','0') as dte FROM `801` WHERE 801_ID= "+Alertas_RS.getString("id");
 		    		//System.out.println(SQL_FAC);
 					RS_FAC =  state_fac.executeQuery(SQL_FAC);
 					if(RS_FAC.next()){
 						if(RS_FAC.getString("dte").equals("1")){
 							d1=Alertas_RS.getString("id"); d2=Alertas_RS.getString("clpr_razon_social"); d3=Alertas_RS.getString("tot");
 							d4=Alertas_RS.getString("fecha"); d5=Alertas_RS.getString("est");
 						}
 					}
 		    	}
 		    	if(TDTE.equals("802")){ 
 		    		String SQL_FAC = "SELECT IF(id_dte is null,'1','0') as dte FROM `802` WHERE 802_id= "+Alertas_RS.getString("id");
 		    		//System.out.println(SQL_FAC);
 					RS_FAC =  state_fac.executeQuery(SQL_FAC);
 					if(RS_FAC.next()){
 						if(RS_FAC.getString("dte").equals("1")){
 							d1=Alertas_RS.getString("id"); d2=Alertas_RS.getString("clpr_razon_social"); d3=Alertas_RS.getString("tot");
 							d4=Alertas_RS.getString("fecha"); d5=Alertas_RS.getString("est");
 						}
 					}
 		    	}
 		    	if(TDTE.equals("803")){ 
 		    		String SQL_FAC = "SELECT IF(id_dte is null,'1','0') as dte FROM `803` WHERE 803_id= "+Alertas_RS.getString("id");
 		    		//System.out.println(SQL_FAC);
 					RS_FAC =  state_fac.executeQuery(SQL_FAC);
 					if(RS_FAC.next()){
 						if(RS_FAC.getString("dte").equals("1")){
 							d1=Alertas_RS.getString("id"); d2=Alertas_RS.getString("clpr_razon_social"); d3=Alertas_RS.getString("tot");
 							d4=Alertas_RS.getString("fecha"); d5=Alertas_RS.getString("est");
 						}
 					}
 		    	}
 		    	if(TDTE.equals("804")){ 
 		    		String SQL_FAC = "SELECT IF(id_dte is null,'1','0') as dte FROM `804` WHERE 804_id= "+Alertas_RS.getString("id");
 		    		//System.out.println(SQL_FAC);
 					RS_FAC =  state_fac.executeQuery(SQL_FAC);
 					if(RS_FAC.next()){
 						if(RS_FAC.getString("dte").equals("1")){
 							d1=Alertas_RS.getString("id"); d2=Alertas_RS.getString("clpr_razon_social"); d3=Alertas_RS.getString("tot");
 							d4=Alertas_RS.getString("fecha"); d5=Alertas_RS.getString("est");
 						}
 					}
 		    	}
 		    	if(TDTE.equals("811")){ 
 		    		String SQL_FAC = "SELECT IF(id_dte is null,'1','0') as dte FROM factura_compra_activo WHERE fac_comactivo_id= "+Alertas_RS.getString("id");
 		    		//System.out.println(SQL_FAC);
 					RS_FAC =  state_fac.executeQuery(SQL_FAC);
 					if(RS_FAC.next()){
 						if(RS_FAC.getString("dte").equals("1")){
 							d1=Alertas_RS.getString("id"); d2=Alertas_RS.getString("clpr_razon_social"); d3=Alertas_RS.getString("tot");
 							d4=Alertas_RS.getString("fecha"); d5=Alertas_RS.getString("est");
 						}
 					}
 		    	}
 		    	if(TDTE.equals("812")){ 
 		    		String SQL_FAC = "SELECT IF(id_dte is null,'1','0') as dte FROM factura_compra_serv WHERE fac_comserv_id= "+Alertas_RS.getString("id");
 		    		//System.out.println(SQL_FAC);
 					RS_FAC =  state_fac.executeQuery(SQL_FAC);
 					if(RS_FAC.next()){
 						if(RS_FAC.getString("dte").equals("1")){
 							d1=Alertas_RS.getString("id"); d2=Alertas_RS.getString("clpr_razon_social"); d3=Alertas_RS.getString("tot");
 							d4=Alertas_RS.getString("fecha"); d5=Alertas_RS.getString("est");
 						}
 					}
 		    	}
 		    	
 		    	if(TDTE.equals("821")){ 
 		    		String SQL_FAC = "SELECT IF(id_dte is null,'1','0') as dte FROM `821` WHERE 821_ID= "+Alertas_RS.getString("id");
 		    		//System.out.println(SQL_FAC);
 					RS_FAC =  state_fac.executeQuery(SQL_FAC);
 					if(RS_FAC.next()){
 						if(RS_FAC.getString("dte").equals("1")){
 							d1=Alertas_RS.getString("id"); d2=Alertas_RS.getString("clpr_razon_social"); d3=Alertas_RS.getString("tot");
 							d4=Alertas_RS.getString("fecha"); d5=Alertas_RS.getString("est");
 						}
 					}
 		    	}
 		    	if(TDTE.equals("822")){ 
 		    		String SQL_FAC = "SELECT IF(id_dte is null,'1','0') as dte FROM `822` WHERE 822_ID= "+Alertas_RS.getString("id");
 		    		//System.out.println(SQL_FAC);
 					RS_FAC =  state_fac.executeQuery(SQL_FAC);
 					if(RS_FAC.next()){
 						if(RS_FAC.getString("dte").equals("1")){
 							d1=Alertas_RS.getString("id"); d2=Alertas_RS.getString("clpr_razon_social"); d3=Alertas_RS.getString("tot");
 							d4=Alertas_RS.getString("fecha"); d5=Alertas_RS.getString("est");
 						}
 					}
 		    	}
 		    	
 		    	if(TDTE.equals("823")){ 
 		    		String SQL_FAC = "SELECT IF(id_dte is null,'1','0') as dte FROM `823` WHERE 823_id= "+Alertas_RS.getString("id");
 		    		//System.out.println(SQL_FAC);
 					RS_FAC =  state_fac.executeQuery(SQL_FAC);
 					if(RS_FAC.next()){
 						if(RS_FAC.getString("dte").equals("1")){
 							d1=Alertas_RS.getString("id"); d2=Alertas_RS.getString("clpr_razon_social"); d3=Alertas_RS.getString("tot");
 							d4=Alertas_RS.getString("fecha"); d5=Alertas_RS.getString("est");
 						}
 					}
 		    	}
 		    	if(TDTE.equals("824")){ 
 		    		String SQL_FAC = "SELECT IF(id_dte is null,'1','0') as dte FROM `824` WHERE 824_id= "+Alertas_RS.getString("id");
 		    		//System.out.println(SQL_FAC);
 					RS_FAC =  state_fac.executeQuery(SQL_FAC);
 					if(RS_FAC.next()){
 						if(RS_FAC.getString("dte").equals("1")){
 							d1=Alertas_RS.getString("id"); d2=Alertas_RS.getString("clpr_razon_social"); d3=Alertas_RS.getString("tot");
 							d4=Alertas_RS.getString("fecha"); d5=Alertas_RS.getString("est");
 						}
 					}
 		    	}
 		    	if(TDTE.equals("841")){ 
 		    		String SQL_FAC = "SELECT IF(id_dte is null,'1','0') as dte FROM `841` WHERE `841`.`841_est_aprobacion`=2 AND 841_id= "+Alertas_RS.getString("id");
 		    		//System.out.println(SQL_FAC);
 					RS_FAC =  state_fac.executeQuery(SQL_FAC);
 					if(RS_FAC.next()){
 						if(RS_FAC.getString("dte").equals("1")){
 							d1=Alertas_RS.getString("id"); d2=Alertas_RS.getString("clpr_razon_social"); d3=Alertas_RS.getString("tot");
 							d4=Alertas_RS.getString("fecha"); d5=Alertas_RS.getString("est");
 						}
 					}
 		    	}
 		    	alertas.add(d1+"/"+d2+"/"+d3+"/"+d4+"/"+d5);
     	    }
		 
                
		    String[] ar_alertas = new String[alertas.size()];
		    for(int x=0; x < alertas.size(); x++){
		    	//System.out.println(alertas.get(x));
		    	ar_alertas[x]=alertas.get(x);
		    }
            request.setAttribute("ar_alertas", ar_alertas);
            request.setAttribute("tdte", TDTE);
            
            
            //--------------------- EMISOR ----------------------//
    	    String SQL_EMPRESA = "SELECT * FROM empresas WHERE estados_vig_novig_id = 1 AND empresas_relacionada = 1 ORDER BY empresas_nombre";
    	    ResultSet EMPRESAS_RS = state.executeQuery(SQL_EMPRESA);
    	    ArrayList<String> empresas = new ArrayList<String>();
    	    while(EMPRESAS_RS.next()){ empresas.add(EMPRESAS_RS.getString("empresas_id")+"/"+EMPRESAS_RS.getString("empresas_nombre")); }
    	    EMPRESAS_RS.close();
    	    String[] ar_empresas = new String[empresas.size()];
    	    for(int x=0; x < empresas.size(); x++){ ar_empresas[x]=empresas.get(x);}
    	    request.setAttribute("ar_empresas", ar_empresas);
    	    //----------------------- FIN ------------------------//
    	    request.setAttribute("id_e", id_em);
    	    request.setAttribute("tipo_dte",TDTE);
    	    
    	    
    	    
    	    Alertas_RS.close();
		    state.close();
		    conexion.close();
            
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("Error "+ex);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("iguias_search.jsp");
        rd.forward(request, response);
		
		}

}
