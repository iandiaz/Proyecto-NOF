

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
 * Servlet implementation class eguia_search
 */
@WebServlet("/eguia_search")
public class eguia_search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public eguia_search() {
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

		String p=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), Constantes.PELIMINAR);
		
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
		Statement state_borra = null;
		
		ResultSet Alertas_RS = null;
		ResultSet RS_FAC = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		state_borra = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 
    		
    		String id_e = request.getParameter("id_e");
     		 String id_em="";
     		 if(id_e!=null){
     			 id_em=id_e;
     			 id_e="AND `g`.`823_empresa_emisora`='"+id_e+"'"; 
     		 }
     		 else{
     			 id_em="123";
     			 id_e="AND `g`.`823_empresa_emisora`='123'"; 
     		 }
    		String SQL_Alertas = "SELECT g.823_folio,g.823_total,g.823_id, e.empresas_nombrenof, d1.DIRE_DIRECCION, "
	    			+ " if(g.id_dte is null,'NO ENVIADA','ENVIADA') as dte, "
    				+ " DATE_FORMAT(g.823_fecha, '%d-%m-%Y') as fecha, g.estados_vig_novig_id "
    				+ " FROM `823` g "
    				+ " INNER JOIN empresas e ON g.clientes_id = e.empresas_id "
				 	+ " INNER JOIN direccion d1 ON g.dire_id2 = d1.DIRE_ID "
				 	+ " WHERE 1=1 and g.id_dte is null  "+id_e
				 	+ " ORDER BY e.empresas_nombrenof, d1.dire_direccion";
	    if(request.getAttribute("filtro")!=null){
	    	request.setAttribute("filtro",request.getAttribute("filtro"));
	    }
	    System.out.println(SQL_Alertas);
	    Alertas_RS =  state.executeQuery(SQL_Alertas);
	    //definimos un arreglo para los resultados
	    
	    ArrayList<String> alertas = new ArrayList<String>();
	   
	    while(Alertas_RS.next()){        	   
	    	alertas.add(Alertas_RS.getString("823_id")+"/"+Alertas_RS.getString("empresas_nombrenof")
	    			+"/"+Alertas_RS.getString("DIRE_DIRECCION").replace("/"," ")+"/"+Alertas_RS.getString("fecha")
	    			+"/"+Alertas_RS.getString("dte")+"/"+Alertas_RS.getString("estados_vig_novig_id")+"/"+Alertas_RS.getString("823_total")+"/"+Alertas_RS.getString("823_folio"));
	    }
	    	
                
          String[] ar_alertas = new String[alertas.size()];
          for(int x=0; x < alertas.size(); x++){
        	  //System.out.println(alertas.get(x));
        	  ar_alertas[x]=alertas.get(x);
          }
             
          request.setAttribute("ar_alertas", ar_alertas);
          
          
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
		    
		    
		    Alertas_RS.close();
	    	state.close();
	    	conexion.close();
		    
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("Error "+ex);
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("eguia_search.jsp");
        rd.forward(request, response);
		
	}

}
