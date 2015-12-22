

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
 * Servlet implementation class rpt
 */
@WebServlet("/rpt")
public class rpt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public rpt() {
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
		boolean sesion_valida=validateSession(request,response);
		
		if(!sesion_valida) response.sendRedirect("/001/");
		else {
			 mt(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
        //guardams el usuario que esta haciendo el reporte
        String usuario="";
        Cookie [] cookies=request.getCookies();
		
        if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        if(cookie.getName().equals("Usuarios_nombre")) usuario=cookie.getValue();
		    }
		}
		
        
        
        //////////////////////////EXCEL 
        
        
		if(request.getParameter("grabar_excel") !=  null && request.getParameter("informe").equals("1")){
			PrintWriter out = response.getWriter();
			String filtros="a=1";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
			if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
			if(request.getParameter("select_eclpr") !=  null && !request.getParameter("select_eclpr").equals("") ){
				filtros+="&id_eclpr="+request.getParameter("select_eclpr")+"";
				
			}
			/*if(request.getParameter("select_responsable") !=  null && !request.getParameter("select_responsable").equals("") ){
				filtros+="&id_re="+request.getParameter("select_responsable")+"";
				
			}*/
			if(request.getParameter("cli") !=  null && request.getParameter("cli").equals("1") ){
				filtros+="&cli="+("1")+"";
				
			}
			if(request.getParameter("pro") !=  null && request.getParameter("pro").equals("1") ){
				filtros+="&pro="+("1")+"";
				
			}
			if(request.getParameter("pros") !=  null && request.getParameter("pros").equals("1") ){
				filtros+="&pros="+("1")+"";
				
			}
			if(request.getParameter("select_estado_contrato") !=  null && !request.getParameter("select_estado_contrato").equals("") ){
				filtros+="&id_ecvnv="+request.getParameter("select_estado_contrato")+"";
				
			}
			if(request.getParameter("estado_contrato_id") !=  null && !request.getParameter("estado_contrato_id").equals("") ){
				filtros+="&id_estcon="+request.getParameter("estado_contrato_id")+"";
				
			}
			
			
			out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_017_1_xls.php?"+filtros+"';</script>");
		}
		/////////////////////////////PDF 
		
		
		
		if(request.getParameter("grabar_pdf") !=  null && request.getParameter("informe").equals("1")){
			PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
			if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
			if(request.getParameter("select_eclpr") !=  null && !request.getParameter("select_eclpr").equals("") ){
				filtros+="&id_eclpr="+request.getParameter("select_eclpr")+"";
				
			}
			/*if(request.getParameter("select_responsable") !=  null && !request.getParameter("select_responsable").equals("") ){
				filtros+="&id_re="+request.getParameter("select_responsable")+"";
				
			}*/
			if(request.getParameter("cli") !=  null && request.getParameter("cli").equals("1") ){
				filtros+="&cli="+("1")+"";
				
			}
			if(request.getParameter("pro") !=  null && request.getParameter("pro").equals("1") ){
				filtros+="&pro="+("1")+"";
				
			}
			if(request.getParameter("pros") !=  null && request.getParameter("pros").equals("1") ){
				filtros+="&pros="+("1")+"";
				
			}
			if(request.getParameter("select_estado_contrato") !=  null && !request.getParameter("select_estado_contrato").equals("") ){
				filtros+="&id_ecvnv="+request.getParameter("select_estado_contrato")+"";
				
			}
			if(request.getParameter("estado_contrato_id") !=  null && !request.getParameter("estado_contrato_id").equals("") ){
				filtros+="&id_estcon="+request.getParameter("estado_contrato_id")+"";
				
			}
			
			out.write("<script>window.location='"+Constantes.URL_SITIO+"generaPDF.php?inf=017-1&usu="+usuario+filtros+"';</script>");
		}
		
		
		
		
		
		
   /////////////////////GENERAR POR WEB ////////////////////////
        
        if(request.getParameter("grabar_web") !=  null && request.getParameter("informe").equals("1")){
        	PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
			if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
			if(request.getParameter("select_eclpr") !=  null && !request.getParameter("select_eclpr").equals("") ){
				filtros+="&id_eclpr="+request.getParameter("select_eclpr")+"";
				
			}
			/*if(request.getParameter("select_responsable") !=  null && !request.getParameter("select_responsable").equals("") ){
				filtros+="&id_re="+request.getParameter("select_responsable")+"";
				
			}*/
			if(request.getParameter("cli") !=  null && request.getParameter("cli").equals("1") ){
				filtros+="&cli="+("1")+"";
				
			}
			if(request.getParameter("pro") !=  null && request.getParameter("pro").equals("1") ){
				filtros+="&pro="+("1")+"";
				
			}
			if(request.getParameter("pros") !=  null && request.getParameter("pros").equals("1") ){
				filtros+="&pros="+("1")+"";
				
			}
			if(request.getParameter("select_estado_contrato") !=  null && !request.getParameter("select_estado_contrato").equals("") ){
				filtros+="&id_ecvnv="+request.getParameter("select_estado_contrato")+"";
				
			}
			
			if(request.getParameter("estado_contrato_id") !=  null && !request.getParameter("estado_contrato_id").equals("") ){
				filtros+="&id_estcon="+request.getParameter("estado_contrato_id")+"";
				
			}
			
			
		
			out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_017_1_pdf.php?inf=017-1&usu="+usuario+filtros+"';</script>");
		}
        
		
	}
public void mt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
		
		///////////////////////////////////////////
		//////DEFINE DESLOGEO//////////////////////
		
		Cookie [] cookies=request.getCookies();
		if(request.getParameter("logout") !=  null){
			if (cookies != null) {
			    for (Cookie cookie : cookies) {
			    	//System.out.println("cookie logout: "+cookie.getName());
			    	if(cookie.getName().equals("Usuarios_id")) {cookie.setMaxAge(0);cookie.setPath("/");response.addCookie(cookie);}
			    }
			}
				response.sendRedirect("/001/");
				return;
			
		}
		
		
		//////////////////////////////////////////////////
		////////DEFINE PARAMETROS DE USUARIO//////////////
		
		String Usuarios_nombre="";
		
		
		
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}
		request.setAttribute("usuname", Usuarios_nombre);
		
		//////////////////////////////////////////////////
		
		
		Statement state = null;
		
		ResultSet GRUPOS_RS=null;
		ResultSet EMPRESAS_RS=null;
		ResultSet ESTCLPR_RS=null;
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conexion=(Connection) DriverManager.getConnection
	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
		
		//::::::::::::::::::::::::::::::::::::::::::sql estado clpr para select option::::::::::::::::::::::::::::::::::::::  
		  state = (Statement) ((java.sql.Connection) conexion).createStatement();
		  String SQL_estclpr = "SELECT * FROM estado_clpr WHERE estados_vig_novig_id=1 ORDER BY Estado_Clpr_nombre";
		    ESTCLPR_RS =  state.executeQuery(SQL_estclpr);		    
		    //definimos un arreglo para los resultados		    
		    ArrayList<String> estclpr = new ArrayList<String>();		   
		    //recorremos los resultados de la consulta
		    while(ESTCLPR_RS.next()){        	   
  	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	estclpr.add(ESTCLPR_RS.getString("Estado_Clpr_id")+"/"+ESTCLPR_RS.getString("Estado_Clpr_nombre"));
  	    }
		 
		  String[] ar_estclpr = new String[estclpr.size()];
        for(int x=0; x < estclpr.size(); x++){
      	  ar_estclpr[x]=estclpr.get(x);
        }
              
        request.setAttribute("ar_estclpr", ar_estclpr);
		
		
		
		  //::::::::::::::::::::::::::::::::::::::::::sql grupos para select option::::::::::::::::::::::::::::::::::::::
		  state = (Statement) ((java.sql.Connection) conexion).createStatement();
		  String SQL_GRUPOS = "SELECT * FROM grupos WHERE estados_vig_novig_id=1 ORDER BY grupos_nombre";
		  System.out.println(SQL_GRUPOS);  	
		    GRUPOS_RS =  state.executeQuery(SQL_GRUPOS);		    
		    //definimos un arreglo para los resultados		    
		    ArrayList<String> grupos = new ArrayList<String>();		   
		    //recorremos los resultados de la consulta
		    while(GRUPOS_RS.next()){        	   
     	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	grupos.add(GRUPOS_RS.getString("grupos_id")+"/"+GRUPOS_RS.getString("grupos_nombre"));
     	    }
		   String[] ar_grupos = new String[grupos.size()];
         for(int x=0; x < grupos.size(); x++){
       	  ar_grupos[x]=grupos.get(x);
         }
               
         request.setAttribute("ar_grupos", ar_grupos);
      //:::::::::::::::::::::::::: sql trae empresas para select option:::::::::::::::::::::::::::::::::::::
         state = (Statement) ((java.sql.Connection) conexion).createStatement();
	    String SQL_EMPRESAS = "SELECT * FROM empresas WHERE estados_vig_novig_id=1 ORDER BY empresas_nombrenof";
	    EMPRESAS_RS =  state.executeQuery(SQL_EMPRESAS);
	    //definimos un arreglo para los resultados
	    ArrayList<String> empresas = new ArrayList<String>();
	    //recorremos los resultados de la consulta
	    while(EMPRESAS_RS.next()){        	   
    	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
	    	empresas.add(EMPRESAS_RS.getString("empresas_id")+"/"+EMPRESAS_RS.getString("empresas_nombrenof")+"/"+EMPRESAS_RS.getString("grupos_id"));    
	    }
	  String[] ar_empresas = new String[empresas.size()];
      for(int x=0; x < empresas.size(); x++){
    	 ar_empresas[x]=empresas.get(x);
      }
            
      request.setAttribute("ar_empresas", ar_empresas);
      
    //::::::::::::::::::::::::::::::::::::::::::sql estado contrato select option::::::::::::::::::::::::::::::::::::::
      
      
      String SQL_est_contrato = "SELECT estadocontrato_id,estadocontrato_nombre FROM estadocontrato";
	    ResultSet EST_CON_RS = state.executeQuery(SQL_est_contrato);	    
	    //definimos un arreglo para los resultados	
	    
	    ArrayList<String> estadocontrato= new ArrayList<String>();
	    
	    //recorremos los resultados de la consulta
	    
	    while(EST_CON_RS.next())
	    {        	   
	    
	    	//SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
	    	estadocontrato.add(EST_CON_RS.getString("estadocontrato_id")+"/"+EST_CON_RS.getString("estadocontrato_nombre"));
	    	
	    }
	    
	  
	  
	  
	  String[] ar_estadocontrato = new String[estadocontrato.size()];
	  
    for(int x=0; x < estadocontrato.size(); x++)
    {
    	ar_estadocontrato[x]=estadocontrato.get(x);
    }
          
    request.setAttribute("ar_estadocontrato", ar_estadocontrato);
      
      //::::::::::::::::::::::::::::::::::::::::::sql usuarios internos para responsable select option::::::::::::::::::::::::::::::::::::::  
	/*  state = (Statement) ((java.sql.Connection) conexion).createStatement();
	  String SQL_resp = "SELECT "
	  		+ "		`usuarios`.`Usuarios_id`,"
	  		+ "		CONCAT_WS(' ',`usuarios`.`Usuarios_nombre`,`usuarios`.`Usuarios_ape_p`,`usuarios`.`Usuarios_ape_m`) AS Usuarios_nombre "
	  		+ "	FROM"
	  		+ "		`usuarios`"
	  		+ "	WHERE "
	  		+ "		`usuarios`.`tipo_usu_id` = 1 "
	  		+ "	ORDER BY `usuarios`.`Usuarios_nombre` ";
	  
	    ResultSet resp_RS = state.executeQuery(SQL_resp);		    
	    //definimos un arreglo para los resultados		    
	    ArrayList<String> responsables = new ArrayList<String>();		   
	    //recorremos los resultados de la consulta
	    while(resp_RS.next()){        	   
	    	responsables.add(resp_RS.getString("Usuarios_id")+"/"+resp_RS.getString("Usuarios_nombre"));
	    }
	  String[] ar_responsables = new String[responsables.size()];
      for(int x=0; x < responsables.size(); x++){
    	  ar_responsables[x]=responsables.get(x);
      }
            
      request.setAttribute("ar_responsables", ar_responsables);*/
    EMPRESAS_RS.close();
	  
    GRUPOS_RS.close();	
    ESTCLPR_RS.close();
	        state.close();
		conexion.close();
        RequestDispatcher a = request.getRequestDispatcher("rpt.jsp");
    	a.forward(request, response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

}
