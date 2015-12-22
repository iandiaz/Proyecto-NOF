

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class mnc1
 */
@WebServlet("/mnc1")
public class mnc1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mnc1() {
        super();
        // TODO Auto-generated constructor stub
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
		
		Cookie [] cookies=request.getCookies();
		if(request.getParameter("logout") !=  null){
			if (cookies != null) {
			    for (Cookie cookie : cookies) {
			        //work with cookies
			    	if(cookie.getName().equals("Usuarios_id")) {cookie.setMaxAge(0);cookie.setPath("/");response.addCookie(cookie);}
			    }
			}
				response.sendRedirect("/001/");	
				return;
		}
		String Usuarios_nombre="";
		
		//fecha
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName());
		    	if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}		
		request.setAttribute("usuname", Usuarios_nombre);
			
		Statement state = null;
		Statement state_fac = null;
		ResultSet Alertas_RS = null;
		ResultSet RS_FAC = null;
		try {
			String TDTE="841";
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
    	
    		if(TDTE.equals("841")){ SQL_DATOS = "SELECT "
    				+ "	f.841_id as id"
    				+ "	,c.empresas_razonsocial as clpr_razon_social"
    				+ "	,f.841_estado as est"
    				+ "	,841_total as tot"
    				+ "	,DATE_FORMAT(f.841_fecha, '%d-%m-%Y') AS fecha"
    				+ "	,f.estados_vig_novig_id AS estadovignovig"
    				
    				
		 			+ " FROM `841` f "
		 			+ "	INNER JOIN empresas c ON f.clpr_id = c.empresas_id "
		 			+ "	WHERE f.id_dte IS NULL  "+id_e
		 			+ "	ORDER BY f.841_id";}
    		System.out.println(SQL_DATOS);
 		    if(request.getAttribute("filtro")!=null){
 		    	request.setAttribute("filtro",request.getAttribute("filtro"));
 		    }
 		    Alertas_RS =  state.executeQuery(SQL_DATOS);
 		    ArrayList<String> alertas = new ArrayList<String>();
 		    while(Alertas_RS.next()){
 		    
 		    
 		    	
 		    	if(TDTE.equals("841")){ 
 		    		String SQL_FAC = "SELECT IF(id_dte is null,'1','0') as dte FROM `841` WHERE 841_id= "+Alertas_RS.getString("id");
 		    		//System.out.println(SQL_FAC);
 					RS_FAC =  state_fac.executeQuery(SQL_FAC);
 					if(RS_FAC.next()){
 						if(RS_FAC.getString("dte").equals("1")){
 							d1=Alertas_RS.getString("id"); d2=Alertas_RS.getString("clpr_razon_social"); d3=Alertas_RS.getString("tot");
 							d4=Alertas_RS.getString("fecha"); d5=Alertas_RS.getString("estadovignovig");
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
		
		RequestDispatcher rd = request.getRequestDispatcher("mnc1.jsp");
        rd.forward(request, response);
		
		}

}
