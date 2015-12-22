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
 * Servlet implementation class e2
 */
@WebServlet("/e2")
public class e2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public e2() {
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
		String Usuarios_nombre=Controlador.getUsunameSession(request);
		request.setAttribute("usuname", Usuarios_nombre);
		
		request.setAttribute("f1", request.getParameter("f1"));
		request.setAttribute("f2", request.getParameter("f2"));
		


		Statement state = null;
		Statement state_fac = null;
		ResultSet Alertas_RS = null;
		ResultSet RS_FAC = null;
		ResultSet RS_FEC = null;
		try {
			String TDTE=request.getParameter("tipo_dte");
			String f1="",f2="";
			String filtrofec="";
			if(!request.getParameter("f1").isEmpty() && !request.getParameter("f1").isEmpty()){
				f1=request.getParameter("f1");
				String d=f1.substring(0, 2);
				String m=f1.substring(3, 5);
				String y=f1.substring(6, 10);
				f1=y+"-"+m+"-"+d;
				f2=request.getParameter("f2");
				String dia=f2.substring(0, 2);
				String m1=f2.substring(3, 5);
				String y1=f2.substring(6, 10);
				f2=y1+"-"+m1+"-"+dia;
				
				filtrofec=" AND `dte_encabezado`.`FechaEmision`<'"+f2+"' AND `dte_encabezado`.`FechaEmision` > '"+f1+"'";
			}
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
   			 id_e="AND `dte_encabezado`.`empresa_emisora_id`='"+id_e+"'"; 
   		 }
   		 else{
   			 id_em="123";
   			 id_e="AND `dte_encabezado`.`empresa_emisora_id`='123'"; 
   		 }
   		 
   		String SQL_DATOS_m = "select "
				+ "		`modulos`.`Modulos_nombre` as m"
				+ "	from `modulos`"
				+ "	where `modulos`.`Modulos_codigo` ="+TDTE+" ";
		System.out.println(SQL_DATOS_m);
   		ResultSet M_RS = state.executeQuery(SQL_DATOS_m);
   		if(M_RS.next()) request.setAttribute("mod", M_RS.getString("m"));
   		
   		
    		String SQL_DATOS = "select "
    				+ "		`dte_encabezado`.`Folio` as folio"
        			+ "		,`dte_encabezado`.`Codigo_relacionado` as id"
    				+ "		, `dte_encabezado`.`RSCliente` as clpr_razon_social"
    				+ "		, `dte_encabezado`.`Total` as tot"
    				+ "		, DATE_FORMAT(`dte_encabezado`.`FechaEmision`,'%d-%m-%Y')  as fecha"
    				+ "	from `dte_encabezado`"
    				+ "	where `dte_encabezado`.`Modulo` ="+TDTE+" "+id_e
    						+ " "+filtrofec;
		
    		System.out.println(SQL_DATOS);
 		    if(request.getAttribute("filtro")!=null){
 		    	request.setAttribute("filtro",request.getAttribute("filtro"));
 		    }
 		    Alertas_RS =  state.executeQuery(SQL_DATOS);
 		    ArrayList<String> alertas = new ArrayList<String>();
 		    while(Alertas_RS.next()){
 		    	alertas.add(Alertas_RS.getString("id")+"/"+Alertas_RS.getString("clpr_razon_social")+"/"+Alertas_RS.getString("tot")+"/"+
 		    			Alertas_RS.getString("fecha")+"/ENVIADA/"+Alertas_RS.getString("folio"));
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
		    out.println("error "+ex);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("e2.jsp");
        rd.forward(request, response);
		
		}

}
