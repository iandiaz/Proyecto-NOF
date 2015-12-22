

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
 * Servlet implementation class m1
 */
@WebServlet("/m1")
public class m1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public m1() {
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
		    	if(cookie.getName().equals("perfilusu_id") && cookie.getValue()!=null && !cookie.getValue().equals("")) perf_in_session=true;
		    	
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
		
		///////////////////////////////////////////////////
		
		
		Statement state = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
    		 
    		//:::::::::::::::::::::::::: :::::::::::::::::::::::::::::::
  		    String SQL_PRECIOBDNOF = "SELECT pgt_prod_id,pgt_precio_guia_traslado FROM precio_guia_traslado";
  		    ResultSet PRECIOBDNOF_RS = state.executeQuery(SQL_PRECIOBDNOF);
  		    //definimos un arreglo para los resultados
  		    ArrayList<String> preciosbdnof = new ArrayList<String>();
  		    //recorremos los resultados de la consulta
  		    while(PRECIOBDNOF_RS.next()){        	   
          	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
  		    	preciosbdnof.add(PRECIOBDNOF_RS.getString("pgt_prod_id")+"/"+PRECIOBDNOF_RS.getString("pgt_precio_guia_traslado"));    
      	    }
    		 
  		    //:::::::::::::::::::::::::: :::::::::::::::::::::::::::::::
  		  String SQL_Birt="" + 
  		 		" select P.PROD_ID, b.ALT_ID ,"
  		 		+ " CONVERT(varchar, RECEPCION.RECEP_FECHA, 105) RECEP_FECHA ,"
  		 		+ " [DETALLE_RECEPCION].[DETIRECEP_PRECIO], P.PROD_DESC_CORTO, P.PROD_PN_TLI_CODFAB" + 
  		 		" from [PRODUCTO] P" + 
  		 		" left join (" + 
  		 		"	select act.ALT_ID, act.PROD_ID from [ACTIVO] act " + 
  		 		"	inner join (select max([ACTIVO].[ALT_ID]) as last_id_activo from [ACTIVO] WHERE [ACTIVO].[ALT_TIPO_MOV]='COMPRA' group by [ACTIVO].[PROD_ID]) a on a.last_id_activo= act.ALT_ID" + 
  		 		" ) b on b.PROD_ID= P.PROD_ID" + 
  		 		" LEFT JOIN [DETALLE_RECEPCION] on [DETALLE_RECEPCION].[ALT_ID]=b.ALT_ID "+
  		 		" left JOIN [RECEPCION] on [RECEPCION].[RECEP_ID]=DETALLE_RECEPCION.RECEP_ID "
  		 		+ "ORDER BY P.PROD_PN_TLI_CODFAB";

    		 System.out.println(SQL_Birt);
    		 ConBirt birtBD= new ConBirt();
    		 ResultSet QueryBirt= birtBD.Consulta(SQL_Birt);
    		 
    		  ArrayList<String> alertas = new ArrayList<String>();
    		  
		    
    		    while(QueryBirt.next()){     	   
    		    	
    		    	String precio2="NO EXISTE";
    		    	
    		    	for(int i =0; i<preciosbdnof.size();i++){
    		    		String dat = preciosbdnof.get(i);
    		    		String[] dat_ar = dat.split("/");
    		    		
    		    		if(dat_ar[0].equals(QueryBirt.getString("PROD_ID"))){
    		    			precio2=dat_ar[1];
    		    		}
    		    		
    		    	}
    		    	
    		    	alertas.add(QueryBirt.getString("PROD_ID")+"-//"+QueryBirt.getString("PROD_PN_TLI_CODFAB")+" "+QueryBirt.getString("PROD_DESC_CORTO")
	            			+"-//"+QueryBirt.getString("DETIRECEP_PRECIO")+"-//"+precio2+"-//"+QueryBirt.getString("RECEP_FECHA"));
	             	
    				 
        	    }
    		 
    		  QueryBirt.close();		    
  		    birtBD.Desconectar();
    		 
    		
		    if(request.getAttribute("filtro")!=null){
		    	request.setAttribute("filtro",request.getAttribute("filtro"));
		    }
		    /*System.out.println("Consulta: "+SQL_Alertas);*/
		   
                
          String[] ar_alertas = new String[alertas.size()];
          for(int x=0; x < alertas.size(); x++){
        	  ar_alertas[x]=alertas.get(x);
          }
             
          request.setAttribute("ar_alertas", ar_alertas);
          
          state.close();
          conexion.close();
		}catch(Exception ex){
			out.println("ERROR: "+ex);
		    out.println("ERROR"+ex);
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("m1.jsp");
        rd.forward(request, response);
		
	}

}
