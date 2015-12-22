

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
 * Servlet implementation class mres_guias
 */
@WebServlet("/mres_guias")
public class mres_guias extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mres_guias() {
        super();
        // TODO Auto-generated constructor stub
    }
    
public boolean validateSession(HttpServletRequest request, HttpServletResponse response){
		
  		boolean user_in_session=false;
  		Cookie [] cookies=request.getCookies();
  		
  		if (cookies != null) {
  		    for (Cookie cookie : cookies) {
  		        //work with cookies
  		    	 //System.out.println("cookie: "+cookie.getName());
  		    	if(cookie.getName().equals("Usuarios_id")) user_in_session=true;
  		    }
  		}
  		//refrescamos la session 
  		
  		if (user_in_session && cookies != null) {
  		    for (Cookie cookie : cookies) {
  		        //work with cookies
  		    	 //System.out.println("cookie: "+cookie.getName());
  		    	if(cookie.getName().equals("Usuarios_id")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
  		    	if(cookie.getName().equals("Usuarios_nombre")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
  		    	if(cookie.getName().equals("Usuarios_login")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
  		    	if(cookie.getName().equals("Usuarios_email")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
  		    	if(cookie.getName().equals("tipo_usu_id")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
  		    }
  		}
  		
  		
  		return user_in_session;
  		
      }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(validateSession(request, response)){
			
			 mt(request,response);
		}
		else response.sendRedirect("/001/");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(validateSession(request, response)){
			
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
		
		
		
	    //--------------------- GUIAS ----------------------//
	    
	 
		
		
		
		Integer GV_TOTAL = 0;
	    
	    
		Statement state = null;
		Statement statefac = null;
		ResultSet Alertas_RS = null;
		ResultSet RS_FAC = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		statefac = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
    		String EID=request.getParameter("clientes_id");
    		request.setAttribute("clientes_id",request.getParameter("clientes_id"));
    		request.setAttribute("guiaresumen_id",request.getParameter("guiaresumen_id"));
    		
    		String empresa_emisor=request.getParameter("empresa_id");
    		
    		String tipodte=request.getParameter("tipodte");
    		
    		String fechafac =request.getParameter("datepickerfechafactura");
    		String fac_servim_fecvencimiento =request.getParameter("fac_servim_fecvencimiento");
    		String fac_servim_tipodte =request.getParameter("tipodte");
    		
    		String desc =request.getParameter("desc");
    		String glosa_desc =request.getParameter("glosa_desc");
    		
    		
    		String afecta="";
    		if(tipodte.equals("33")){
    			afecta="1";
    		}
    		if(tipodte.equals("34")){
    			afecta="0";
    		}
    		
    		
    		
    		String guias_select="'0'";
    		String[] seleccionado = request.getParameterValues("detguias[]");
		    if(seleccionado!=null){
		    	
    		for (int i = 0; i < seleccionado.length; i++) {
				String guia[] = seleccionado[i].split("/");
				guias_select+=",'"+guia[0]+"'";
			}
	        request.setAttribute("ar_detguias", seleccionado);
	     
		    
		    
		    }
    		
    		
    		
    		
    		  String SQL_Alertas = "(SELECT CONCAT_WS('','S',G.822_ID) as GD_ID,	e.empresas_razonsocial,	G.822_ESTADO, "
  		 			+ " DATE_FORMAT(G.822_FECHA, '%d-%m-%Y') AS GD_FECHA,	"
  		 			+ "	G.822_neto AS GD_TOTAL,	"
  		 			+ "	G.822_folio as folio,"
   				 	+ "	'1' as estados_vig_novig_id "
  		 			+ " FROM `822` G "
  		 			+ " LEFT JOIN empresas e ON G.clpr_id = e.empresas_id "
  		 			+ " WHERE G.822_FOLIO is not null AND (G.en_factura IS NULL  OR G.822_ID IN ("+guias_select.replace("S", "").replace("A", "")+")) AND CLPR_ID = "+EID+" "
  		 			+ " AND G.822_empresa_emisora ="+empresa_emisor+""
    		 		+ " AND G.822_afecta="+afecta
    				 
  				 	+ " GROUP BY G.822_ID ORDER BY G.822_ID DESC) "
  				 	+ "UNION "
  				 	+ "	(SELECT"
  				 	+ "		CONCAT_WS('','A',G.821_ID) as GD_ID,"
  				 	+ "		e.empresas_razonsocial,"
  				 	+ "		G.821_ESTADO as GD_ESTADO,"
  				 	+ "		DATE_FORMAT(G.821_FECHA, '%d-%m-%Y') AS GD_FECHA,"
  				 	+ "		G.821_neto as GD_TOTAL,"
  					+ "		G.821_folio as folio,"
   				 
  				 	+ "		'1' as estados_vig_novig_id"
  				 	
  				 	+ "	FROM"
  				 	+ "		`821` G"
  				 	+ "	LEFT JOIN empresas e ON G.clpr_id = e.empresas_id"
  				 	+ "	WHERE"
  				 	+ "		G.821_FOLIO IS NOT NULL"
  				 	+ "	AND (G.en_factura IS NULL OR G.821_ID IN ("+guias_select.replace("S", "").replace("A", "")+") )"
  				 	+ "	AND CLPR_ID = "+EID+""
  				 	+ " AND G.821_empresa_emisora="+empresa_emisor
    				+ " AND G.821_afecta="+afecta
    				 	
  				 	+ "	GROUP BY"
  				 	+ "		G.821_ID"
  				 	+ "	ORDER BY"
  				 	+ "		G.821_ID DESC)";
		    System.out.println(SQL_Alertas);
		    Alertas_RS =  state.executeQuery(SQL_Alertas);
		    if(request.getAttribute("filtro")!=null){
		    	request.setAttribute("filtro",request.getAttribute("filtro"));
		    }		    
		    ArrayList<String> alertas = new ArrayList<String>();
		    while(Alertas_RS.next()){
		    	alertas.add(Alertas_RS.getString("GD_ID")+"/"+Alertas_RS.getString("empresas_razonsocial")
		    			+"/"+Alertas_RS.getString("GD_TOTAL")+"/"+Alertas_RS.getString("GD_FECHA")+"/"+Alertas_RS.getString("GD_FECHA")+"/"+Alertas_RS.getString("GD_FECHA")+"/"+Alertas_RS.getString("folio"));
    	    }
		    Alertas_RS.close();
		    state.close();
            conexion.close();
                
            String[] ar_alertas = new String[alertas.size()];
            for(int x=0; x < alertas.size(); x++){
            	ar_alertas[x]=alertas.get(x);
            }
             
          request.setAttribute("ar_alertas", ar_alertas);
          
          request.setAttribute("empresa_emisor", empresa_emisor);
          
          request.setAttribute("datepickerfechafactura", fechafac);
          request.setAttribute("fac_servim_fecvencimiento", fac_servim_fecvencimiento);
          request.setAttribute("fac_servim_tipodte", fac_servim_tipodte);
          request.setAttribute("desc", desc);
          request.setAttribute("glosa_desc", glosa_desc);
          
          
          request.setAttribute("afecta", afecta);
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("ERROR "+ex);
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("mres_guias.jsp");
        rd.forward(request, response);
		
		
	}

}
