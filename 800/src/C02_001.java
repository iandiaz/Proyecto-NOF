
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
import VO.DteVO;

/**
 * Servlet implementation class iguias_search
 */
@WebServlet("/C02_001")
public class C02_001 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public C02_001() {
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
		String Usuarios_nombre=Controlador.getUsunameSession(request);
		
		String modulo=request.getParameter("modulo");
		
		
		
		request.setAttribute("usuname", Usuarios_nombre);
		request.setAttribute("f1", request.getParameter("f1"));
		request.setAttribute("f2", request.getParameter("f2"));
		request.setAttribute("modulo", modulo);


		Statement state = null;
		Statement state_fac = null;
		ResultSet RS_FAC = null;
		ResultSet RS_FEC = null;
		try {
			
			DteVO dte_filter= new DteVO();
			
			String f1="",f2="";
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
				
				dte_filter.setFechaEmision1(f1);
				dte_filter.setFechaEmision2(f2);
				
				
			}
			//System.out.println(TDTE);
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		state_fac = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
    		
    	
   		String SQL_DATOS_m = "select "
				+ "		`modulos`.`Modulos_nombre` as m"
				+ "	from `modulos`"
				+ "	where `modulos`.`Modulos_codigo` ="+modulo+" AND modulos.estados_vig_novig_id=1";
		System.out.println(SQL_DATOS_m);
   		ResultSet M_RS = state.executeQuery(SQL_DATOS_m);
   		if(M_RS.next()) request.setAttribute("mod", M_RS.getString("m"));
   		
   		
    		
            
            
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
    	    
    	    
    	    
    	    
		    state.close();
		    conexion.close();
            
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("error "+ex);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("C02_001.jsp");
        rd.forward(request, response);
		
		}

}
