

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
 * Servlet implementation class cnc1
 */
@WebServlet("/cnc1")
public class cnc1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cnc1() {
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
	// TODO Auto-generated method stub
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
	String Usuarios_nombre=Controlador.getUsunameSession(request);
	
	
	request.setAttribute("usuname", Usuarios_nombre);
	Statement state = null;
	ResultSet clpr_rs = null;
	try {
		//import java.io.IOException;
		Class.forName("com.mysql.jdbc.Driver");
		Connection conexion=(Connection) DriverManager.getConnection
	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
		
		 String id_e = request.getParameter("id_e");
		 
			String TDTE="841";
			
		 String id_em="";
		 if(id_e!=null){
			 id_em=id_e;
			 id_e="AND `f`.`"+TDTE+"_empresa_emisora`='"+id_e+"'"; 
		 }
		 else{
			 id_em="123";
			 id_e="AND `f`.`"+TDTE+"_empresa_emisora`='123'"; 
		 }
		 
		 String SQL_DATOS = "SELECT "
 				+ "	f.841_id as id"
 				+ "	,c.empresas_razonsocial as clpr_razon_social"
 				+ "	,f.841_estado as est"
 				+ "	,841_total as tot"
 				+ "	,DATE_FORMAT(f.841_fecha, '%d-%m-%Y') AS fecha"
 				+ "	,f.estados_vig_novig_id AS estadovignovig"
 				
 				
		 			+ " FROM `841` f "
		 			+ "	INNER JOIN empresas c ON f.clpr_id = c.empresas_id "
		 			+ "	ORDER BY f.841_id";
	    
	    System.out.println(SQL_DATOS);
	    clpr_rs =  state.executeQuery(SQL_DATOS);
	    ArrayList<String> estado_clpral = new ArrayList<String>();
	    
	    while(clpr_rs.next()){
	    	estado_clpral.add(clpr_rs.getString("id")+"/"+clpr_rs.getString("clpr_razon_social")+"/"+clpr_rs.getString("tot")+"/"+clpr_rs.getString("fecha")+"/"+clpr_rs.getString("estadovignovig"));
	    }	
	
            
      String[] ar_ncs = new String[estado_clpral.size()];
      for(int x=0; x < estado_clpral.size(); x++){
    	  ar_ncs[x]=estado_clpral.get(x);
      }
            
      request.setAttribute("ar_alertas", ar_ncs);
      
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
	    
	    
	    
	  
	    clpr_rs.close();
		  state.close();
	      conexion.close();
        
      
	}catch(Exception ex){
		ex.printStackTrace();
	    out.println("Error: "+ex);
	}
	RequestDispatcher rd = request.getRequestDispatcher("cnc1.jsp");
    rd.forward(request, response);
	
}


}
