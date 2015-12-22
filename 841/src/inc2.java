

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
 * Servlet implementation class inc2
 */
@WebServlet("/inc2")
public class inc2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public inc2() {
        super();
        // TODO Auto-generated constructor stub
    }

public boolean validateSession(HttpServletRequest request, HttpServletResponse response){
		
		boolean user_in_session=false;
		boolean username_in_session=false;
		boolean perf_in_session=false;
		Cookie [] cookies=request.getCookies();
		
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName()+"-"+cookie.getValue());
		    	if(cookie.getName().equals("Usuarios_id") && cookie.getValue()!=null) user_in_session=true;
		    	if(cookie.getName().equals("Usuarios_nombre") && cookie.getValue()!=null) username_in_session=true;
		    	if(cookie.getName().equals("perfilusu_id") && cookie.getValue()!=null) perf_in_session=true;
		    	
		    }
		}
		
		
		if(user_in_session && username_in_session && perf_in_session) user_in_session=true;
		else user_in_session=false;
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
		    	if(cookie.getName().equals("perfilusu_id")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
		    	
		    	
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
		    	 System.out.println("cookie logout: "+cookie.getName());
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
	ResultSet clpr_rs = null;
	try {
		//import java.io.IOException;
		Class.forName("com.mysql.jdbc.Driver");
		Connection conexion=(Connection) DriverManager.getConnection
	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
		
	  /*  String SQL_clpr = "(SELECT "
	    		+ "		`803`.`803_folio` as folio,"
	    		+ "		`803`.`803_id` as id,"
	    
	    		+ "		`modulos`.`Modulos_nombre`,"
	    		+ "		`modulos`.`Modulos_codigo`,  "
	    		+ "		CASE `803`.`803_tipodte`  "
	    		+ "			WHEN 33 THEN CONCAT_WS(' - ',`803`.`803_tipodte`,'FACTURA ELECTRONICA AFECTA')"
	    		+ "			WHEN 34 THEN CONCAT_WS(' - ',`803`.`803_tipodte`,'FACTURA ELECTRONICA EXENTA')"
	    		+ "			WHEN 30 THEN CONCAT_WS(' - ',`803`.`803_tipodte`,'FACTURA AFECTA')"
	    		+ "			WHEN 32 THEN CONCAT_WS(' - ',`803`.`803_tipodte`,'FACTURA EXENTA')"
	    		+ "	    END AS 'tipodte' , "
	    		+ "		`803`.`803_total` as total, "
	    		+ "		DATE_FORMAT(`803`.`803_fecha`,'%d-%m-%Y') as fecha,"
	    		+ "		`803`.`803_fecha` as fecreal, "
	    		
	    		+ "		`empresas`.`empresas_razonsocial` as rz "
	    		+ "		FROM"
	    		+ "			`803` "
	    		+ "		INNER JOIN `modulos` ON `modulos`.`Modulos_codigo` = 803 "
	    		+ " 	INNER JOIN empresas on empresas.empresas_id=`803`.clpr_id" 
	    		+ "		WHERE"
	    		 
	    		+ "		 `803`.`803_folio` IS NOT NULL "
	    		+ ") "
	    		+ " UNION "
	    		+ "	( SELECT"
	    		+ "		`804`.`804_folio` as folio,"
	    		+ "		`804`.`804_id` as id,"
	    		+ "		`modulos`.`Modulos_nombre`,"
	    		+ "		`modulos`.`Modulos_codigo`,  "
	    		+ "		CASE `804`.`804_tipodte`  "
	    		+ "		WHEN 33 THEN CONCAT_WS(' - ',`804`.`804_tipodte`,'FACTURA ELECTRONICA AFECTA')"
	    		+ "		WHEN 34 THEN CONCAT_WS(' - ',`804`.`804_tipodte`,'FACTURA ELECTRONICA EXENTA')"
	    		+ "		WHEN 30 THEN CONCAT_WS(' - ',`804`.`804_tipodte`,'FACTURA AFECTA') "
	    		+ "		WHEN 32 THEN CONCAT_WS(' - ',`804`.`804_tipodte`,'FACTURA EXENTA') "
	    		+ "		END AS 'tipodte' , "
	    		+ "		`804`.`804_total` as total, "
	    		+ "		DATE_FORMAT(`804`.`804_fecha`,'%d-%m-%Y') as fecha,"
	    		+ "		`804`.`804_fecha` as fecreal, "
	    		
	    		+ "		`empresas`.`empresas_razonsocial` as rz "
	    	    
	    		+ "		from `804`"
	    		+ "		INNER JOIN `modulos` ON `modulos`.`Modulos_codigo` = 804"
	    		+ " 	INNER JOIN empresas on empresas.empresas_id=`804`.clpr_id" 
	    		
	    		+ "		WHERE"
	    		
	    		+ "		`804`.`804_folio` IS NOT NULL)"
	    		+ "	UNION "
	    		+ "	(SELECT "
	    		+ "		`801`.`801_folio` as folio,"
	    		+ "		`801`.`801_id` as id,"
	    	
	    		+ "		`modulos`.`Modulos_nombre`,"
	    		+ "		`modulos`.`Modulos_codigo` ,"
	    		+ "		CASE `801`.`801_tipodte`"
	    		+ "		WHEN 33 THEN CONCAT_WS(' - ',`801`.`801_tipodte`,'FACTURA ELECTRONICA AFECTA')"
	    		+ "		WHEN 34 THEN CONCAT_WS(' - ',`801`.`801_tipodte`,'FACTURA ELECTRONICA EXENTA')"
	    		+ "		WHEN 30 THEN CONCAT_WS(' - ',`801`.`801_tipodte`,'FACTURA AFECTA') "
	    		+ "		WHEN 32 THEN CONCAT_WS(' - ',`801`.`801_tipodte`,'FACTURA EXENTA') "
	    		+ "		END AS 'tipodte', "
	    		+ "		`801`.`801_total` as total," 
	    		+ "		DATE_FORMAT(`801`.`801_fecha`,'%d-%m-%Y') as fecha,"
	    		+ "		`801`.`801_fecha` as fecreal, "
	    		
	    		+ "		`empresas`.`empresas_razonsocial` as rz "
		    	
		    	
	    		+ "	FROM"
	    		+ "		`801`"
	    		+ "	INNER JOIN `modulos` ON `modulos`.`Modulos_codigo` = 801"
	    		+ " INNER JOIN empresas on empresas.empresas_id=`801`.clpr_id" 
	    		
	    		+ "	WHERE"
	    		+ "		`801`.`801_folio` IS NOT NULL"
	    		
	    		+ ")"
	    		+ " UNION "
	    		+ "	(SELECT "
	    		+ "		`802`.`802_folio` as folio,"
	    		+ "		`802`.`802_id` as id,"
	    	
	    		+ "		`modulos`.`Modulos_nombre`,"
	    		+ "		`modulos`.`Modulos_codigo`, "
	    		+ "		CASE `802`.`802_tipodte`"
	    		+ "		WHEN 33 THEN CONCAT_WS(' - ',`802`.`802_tipodte`,'FACTURA ELECTRONICA AFECTA')"
	    		+ "		WHEN 34 THEN CONCAT_WS(' - ',`802`.`802_tipodte`,'FACTURA ELECTRONICA EXENTA')"
	    		+ "		WHEN 30 THEN CONCAT_WS(' - ',`802`.`802_tipodte`,'FACTURA AFECTA') "
	    		+ "		WHEN 32 THEN CONCAT_WS(' - ',`802`.`802_tipodte`,'FACTURA EXENTA') "
	    		+ "		END AS 'tipodte', "
	    		+ "		`802`.`802_total` as total,"
	    		+ "		DATE_FORMAT(`802`.`802_fecha`,'%d-%m-%Y') as fecha,"
	    		+ "		`802`.`802_fecha` as fecreal, "
	    		
	    		+ "		`empresas`.`empresas_razonsocial` as rz "
		    		
		    	
	    		+ "	FROM"
	    		+ "		`802`"
	    		+ "	INNER JOIN `modulos` ON `modulos`.`Modulos_codigo` = 802"
	    		+ " INNER JOIN empresas on empresas.empresas_id=`802`.cliente_id" 
	    		
	    		+ "	WHERE"
	    	
	    		+ "	 `802`.`802_folio` IS NOT NULL) "
	    		+ "	ORDER BY fecreal";*/
		 
		 String SQL_clpr = ""
		 		+ "	SELECT "
		 		+ "		Folio"
		 		+ "		,RSCliente"
		 		+ "		,Codigo_relacionado"
		 		+ "		,Modulo"
		 		+ "		,Total"
		 		+ "		,FechaEmision "
		 		+ "		,DATE_FORMAT(FechaEmision,'%d-%m-%Y') as fecha "
		 		+ "	FROM dte_encabezado"
		 		+ "	WHERE Modulo IN ('801','802','803','804') "
		 		+ "	ORDER BY FechaEmision DESC  ";
	    
	    System.out.println(SQL_clpr);
	    clpr_rs =  state.executeQuery(SQL_clpr);
	    ArrayList<String> estado_clpral = new ArrayList<String>();
	    
	    while(clpr_rs.next()){
	    	
	    	estado_clpral.add(clpr_rs.getString("Folio")+"/"+clpr_rs.getString("RSCliente")+"/"+clpr_rs.getString("Codigo_relacionado")+"/dummy/"+clpr_rs.getString("Modulo")+"/"+clpr_rs.getString("Total")+"/"+clpr_rs.getString("fecha"));
	    }	
	  clpr_rs.close();
	  state.close();
      conexion.close();
            
      String[] ar_clpr = new String[estado_clpral.size()];
      for(int x=0; x < estado_clpral.size(); x++){
    	  ar_clpr[x]=estado_clpral.get(x);
      }
            
      request.setAttribute("ar_clpr", ar_clpr);
      
	}catch(Exception ex){
		ex.printStackTrace();
	    out.println("Error: "+ex);
	}
	RequestDispatcher rd = request.getRequestDispatcher("inc2.jsp");
    rd.forward(request, response);
	
}

}
