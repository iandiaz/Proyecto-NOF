

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		Cookie [] cookies=request.getCookies();
		
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 System.out.println("cookie: "+cookie.getName()+"-"+cookie.getValue());
		    	if(cookie.getName().equals("Usuarios_id") && cookie.getValue()!=null) user_in_session=true;
		    	if(cookie.getName().equals("Usuarios_nombre") && cookie.getValue()!=null) username_in_session=true;
		    }
		}
		
		
		if(user_in_session && username_in_session) user_in_session=true;
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
		
		 //guardams el usuario qeu esta haciendo el reporte
        String usuario="";
        Cookie [] cookies=request.getCookies();
		
        if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        if(cookie.getName().equals("Usuarios_nombre")) usuario=cookie.getValue();
		    }
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
            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
            if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
            
            if(request.getParameter("use_dir_despacho") !=  null && !request.getParameter("use_dir_despacho").equals("") ){
				filtros+="&use1="+request.getParameter("use_dir_despacho")+"";
				
			}
            if(request.getParameter("use_dir_fac") !=  null && !request.getParameter("use_dir_fac").equals("") ){
				filtros+="&use2="+request.getParameter("use_dir_fac")+"";
				
			}
            if(request.getParameter("use_dir_cobranza") !=  null && !request.getParameter("use_dir_cobranza").equals("") ){
				filtros+="&use3="+request.getParameter("use_dir_cobranza")+"";
				
			}
            if(request.getParameter("use_dir_corres") !=  null && !request.getParameter("use_dir_corres").equals("") ){
				filtros+="&use4="+request.getParameter("use_dir_corres")+"";
				
			}
            if(request.getParameter("use_dir_otrofin") !=  null && !request.getParameter("use_dir_otrofin").equals("") ){
				filtros+="&use5="+request.getParameter("use_dir_otrofin")+"";
				
			}
            if(request.getParameter("select_estado_emp") !=  null && !request.getParameter("select_estado_emp").equals("") ){
				filtros+="&see="+request.getParameter("select_estado_emp")+"";
				
			}
            
            
			
           
			out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_010_1_pdf.php?inf=010-1&usu="+usuario+filtros+"';</script>");
		}
        if(request.getParameter("grabar_web") !=  null && request.getParameter("informe").equals("2")){
			PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
            if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
            if(request.getParameter("use_dir_despacho") !=  null && !request.getParameter("use_dir_despacho").equals("") ){
				filtros+="&use1="+request.getParameter("use_dir_despacho")+"";
				
			}
            if(request.getParameter("use_dir_fac") !=  null && !request.getParameter("use_dir_fac").equals("") ){
				filtros+="&use2="+request.getParameter("use_dir_fac")+"";
				
			}
            if(request.getParameter("use_dir_cobranza") !=  null && !request.getParameter("use_dir_cobranza").equals("") ){
				filtros+="&use3="+request.getParameter("use_dir_cobranza")+"";
				
			}
            if(request.getParameter("use_dir_corres") !=  null && !request.getParameter("use_dir_corres").equals("") ){
				filtros+="&use4="+request.getParameter("use_dir_corres")+"";
				
			}
            if(request.getParameter("use_dir_otrofin") !=  null && !request.getParameter("use_dir_otrofin").equals("") ){
				filtros+="&use5="+request.getParameter("use_dir_otrofin")+"";
				
			}
            if(request.getParameter("select_estado_emp") !=  null && !request.getParameter("select_estado_emp").equals("") ){
				filtros+="&see="+request.getParameter("select_estado_emp")+"";
				
			}
			
          
            out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_010_2_pdf.php?inf=010-2&usu="+usuario+filtros+"';</script>");
		}
        if(request.getParameter("grabar_web") !=  null && request.getParameter("informe").equals("3")){
			PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
            if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
            if(request.getParameter("use_dir_despacho") !=  null && !request.getParameter("use_dir_despacho").equals("") ){
				filtros+="&use1="+request.getParameter("use_dir_despacho")+"";
				
			}
            if(request.getParameter("use_dir_fac") !=  null && !request.getParameter("use_dir_fac").equals("") ){
				filtros+="&use2="+request.getParameter("use_dir_fac")+"";
				
			}
            if(request.getParameter("use_dir_cobranza") !=  null && !request.getParameter("use_dir_cobranza").equals("") ){
				filtros+="&use3="+request.getParameter("use_dir_cobranza")+"";
				
			}
            if(request.getParameter("use_dir_corres") !=  null && !request.getParameter("use_dir_corres").equals("") ){
				filtros+="&use4="+request.getParameter("use_dir_corres")+"";
				
			}
            if(request.getParameter("use_dir_otrofin") !=  null && !request.getParameter("use_dir_otrofin").equals("") ){
				filtros+="&use5="+request.getParameter("use_dir_otrofin")+"";
				
			}
            if(request.getParameter("select_estado_emp") !=  null && !request.getParameter("select_estado_emp").equals("") ){
				filtros+="&see="+request.getParameter("select_estado_emp")+"";
				
			}
			
          
            out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_010_3_pdf.php?inf=010-3&usu="+usuario+filtros+"';</script>");
		}
        if(request.getParameter("grabar_web") !=  null && request.getParameter("informe").equals("4")){
			PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
            if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
            if(request.getParameter("use_dir_despacho") !=  null && !request.getParameter("use_dir_despacho").equals("") ){
				filtros+="&use1="+request.getParameter("use_dir_despacho")+"";
				
			}
            if(request.getParameter("use_dir_fac") !=  null && !request.getParameter("use_dir_fac").equals("") ){
				filtros+="&use2="+request.getParameter("use_dir_fac")+"";
				
			}
            if(request.getParameter("use_dir_cobranza") !=  null && !request.getParameter("use_dir_cobranza").equals("") ){
				filtros+="&use3="+request.getParameter("use_dir_cobranza")+"";
				
			}
            if(request.getParameter("use_dir_corres") !=  null && !request.getParameter("use_dir_corres").equals("") ){
				filtros+="&use4="+request.getParameter("use_dir_corres")+"";
				
			}
            if(request.getParameter("use_dir_otrofin") !=  null && !request.getParameter("use_dir_otrofin").equals("") ){
				filtros+="&use5="+request.getParameter("use_dir_otrofin")+"";
				
			}
            if(request.getParameter("select_estado_emp") !=  null && !request.getParameter("select_estado_emp").equals("") ){
				filtros+="&see="+request.getParameter("select_estado_emp")+"";
				
			}
            out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_010_4_pdf.php?inf=010-4&usu="+usuario+filtros+"';</script>");
		}        
        
  /////////////////////GENERAR POR EXCEL ////////////////////////
        
        if(request.getParameter("grabar_excel") !=  null && request.getParameter("informe").equals("1")){
			PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
            if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
			
            
            if(request.getParameter("use_dir_despacho") !=  null && !request.getParameter("use_dir_despacho").equals("") ){
				filtros+="&use1="+request.getParameter("use_dir_despacho")+"";
				
			}
            if(request.getParameter("use_dir_fac") !=  null && !request.getParameter("use_dir_fac").equals("") ){
				filtros+="&use2="+request.getParameter("use_dir_fac")+"";
				
			}
            if(request.getParameter("use_dir_cobranza") !=  null && !request.getParameter("use_dir_cobranza").equals("") ){
				filtros+="&use3="+request.getParameter("use_dir_cobranza")+"";
				
			}
            if(request.getParameter("use_dir_corres") !=  null && !request.getParameter("use_dir_corres").equals("") ){
				filtros+="&use4="+request.getParameter("use_dir_corres")+"";
				
			}
            if(request.getParameter("use_dir_otrofin") !=  null && !request.getParameter("use_dir_otrofin").equals("") ){
				filtros+="&use5="+request.getParameter("use_dir_otrofin")+"";
				
			}
			
            if(request.getParameter("select_estado_emp") !=  null && !request.getParameter("select_estado_emp").equals("") ){
				filtros+="&see="+request.getParameter("select_estado_emp")+"";
				
			}
            
            
			out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_010_1_xls.php?inf=010-1&usu="+usuario+filtros+"';</script>");
		}
        if(request.getParameter("grabar_excel") !=  null && request.getParameter("informe").equals("2")){
			PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
            
            if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
            if(request.getParameter("use_dir_despacho") !=  null && !request.getParameter("use_dir_despacho").equals("") ){
				filtros+="&use1="+request.getParameter("use_dir_despacho")+"";
				
			}
            if(request.getParameter("use_dir_fac") !=  null && !request.getParameter("use_dir_fac").equals("") ){
				filtros+="&use2="+request.getParameter("use_dir_fac")+"";
				
			}
            if(request.getParameter("use_dir_cobranza") !=  null && !request.getParameter("use_dir_cobranza").equals("") ){
				filtros+="&use3="+request.getParameter("use_dir_cobranza")+"";
				
			}
            if(request.getParameter("use_dir_corres") !=  null && !request.getParameter("use_dir_corres").equals("") ){
				filtros+="&use4="+request.getParameter("use_dir_corres")+"";
				
			}
            if(request.getParameter("use_dir_otrofin") !=  null && !request.getParameter("use_dir_otrofin").equals("") ){
				filtros+="&use5="+request.getParameter("use_dir_otrofin")+"";
				
			}
            if(request.getParameter("select_estado_emp") !=  null && !request.getParameter("select_estado_emp").equals("") ){
				filtros+="&see="+request.getParameter("select_estado_emp")+"";
				
			}
            out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_010_2_xls.php?inf=010-2&usu="+usuario+filtros+"';</script>");
		}
        if(request.getParameter("grabar_excel") !=  null && request.getParameter("informe").equals("3")){
			PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
            if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
            if(request.getParameter("use_dir_despacho") !=  null && !request.getParameter("use_dir_despacho").equals("") ){
				filtros+="&use1="+request.getParameter("use_dir_despacho")+"";
				
			}
            if(request.getParameter("use_dir_fac") !=  null && !request.getParameter("use_dir_fac").equals("") ){
				filtros+="&use2="+request.getParameter("use_dir_fac")+"";
				
			}
            if(request.getParameter("use_dir_cobranza") !=  null && !request.getParameter("use_dir_cobranza").equals("") ){
				filtros+="&use3="+request.getParameter("use_dir_cobranza")+"";
				
			}
            if(request.getParameter("use_dir_corres") !=  null && !request.getParameter("use_dir_corres").equals("") ){
				filtros+="&use4="+request.getParameter("use_dir_corres")+"";
				
			}
            if(request.getParameter("use_dir_otrofin") !=  null && !request.getParameter("use_dir_otrofin").equals("") ){
				filtros+="&use5="+request.getParameter("use_dir_otrofin")+"";
				
			}
            if(request.getParameter("select_estado_emp") !=  null && !request.getParameter("select_estado_emp").equals("") ){
				filtros+="&see="+request.getParameter("select_estado_emp")+"";
				
			}
            out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_010_3_xls.php?inf=010-3&usu="+usuario+filtros+"';</script>");
		}
        if(request.getParameter("grabar_excel") !=  null && request.getParameter("informe").equals("4")){
			PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
            if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
            if(request.getParameter("use_dir_despacho") !=  null && !request.getParameter("use_dir_despacho").equals("") ){
				filtros+="&use1="+request.getParameter("use_dir_despacho")+"";
				
			}
            if(request.getParameter("use_dir_fac") !=  null && !request.getParameter("use_dir_fac").equals("") ){
				filtros+="&use2="+request.getParameter("use_dir_fac")+"";
				
			}
            if(request.getParameter("use_dir_cobranza") !=  null && !request.getParameter("use_dir_cobranza").equals("") ){
				filtros+="&use3="+request.getParameter("use_dir_cobranza")+"";
				
			}
            if(request.getParameter("use_dir_corres") !=  null && !request.getParameter("use_dir_corres").equals("") ){
				filtros+="&use4="+request.getParameter("use_dir_corres")+"";
				
			}
            if(request.getParameter("use_dir_otrofin") !=  null && !request.getParameter("use_dir_otrofin").equals("") ){
				filtros+="&use5="+request.getParameter("use_dir_otrofin")+"";
				
			}
            if(request.getParameter("select_estado_emp") !=  null && !request.getParameter("select_estado_emp").equals("") ){
				filtros+="&see="+request.getParameter("select_estado_emp")+"";
				
			}
            out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_010_4_xls.php?inf=010-4&usu="+usuario+filtros+"';</script>");
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
		
		
		
		ResultSet GRUPOS_RS=null;
		ResultSet EMPRESAS_RS=null;
		ResultSet DIRECCION_RS=null;
		
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 Statement state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
		
		
		
		  //::::::::::::::::::::::::::::::::::::::::::sql grupos para select option::::::::::::::::::::::::::::::::::::::
		  String SQL_GRUPOS = "SELECT * FROM grupos";
		    GRUPOS_RS =  state.executeQuery(SQL_GRUPOS);		    
		    //definimos un arreglo para los resultados		    
		    ArrayList<String> grupos = new ArrayList<String>();		   
		    //recorremos los resultados de la consulta
		    while(GRUPOS_RS.next()){        	   
     	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	grupos.add(GRUPOS_RS.getString("grupos_id")+"/"+GRUPOS_RS.getString("grupos_nombre"));
     	    }
		  System.out.println("SIZE LIST: "+grupos.size());  	
		
		  String[] ar_grupos = new String[grupos.size()];
         for(int x=0; x < grupos.size(); x++){
       	  ar_grupos[x]=grupos.get(x);
         }
               
         request.setAttribute("ar_grupos", ar_grupos);
      //:::::::::::::::::::::::::: sql trae empresas para select option:::::::::::::::::::::::::::::::::::::
         
         
         //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         
         
        String SQL_EMPRESAS = "SELECT * FROM empresas WHERE empresas.empresas_esproveedor =0  ORDER BY empresas.empresas_nombrenof   ";
	    EMPRESAS_RS =  state.executeQuery(SQL_EMPRESAS);
	    //definimos un arreglo para los resultados
	    ArrayList<String> empresas = new ArrayList<String>();
	    //recorremos los resultados de la consulta
	    while(EMPRESAS_RS.next()){        	   
    	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
	    	empresas.add(EMPRESAS_RS.getString("empresas_id")+"/"+EMPRESAS_RS.getString("empresas_nombrenof"));
	    }
	  System.out.println("SIZE LIST: "+empresas.size());	
	  
	  String[] ar_empresas = new String[empresas.size()];
      for(int x=0; x < empresas.size(); x++){
    	 ar_empresas[x]=empresas.get(x);
      }
            
      request.setAttribute("ar_empresas", ar_empresas);
      //::::::::::::::::::::::::::::::::::::::::::sql direcciones para select option::::::::::::::::::::::::::::::::::::::
	  state = (Statement) ((java.sql.Connection) conexion).createStatement();
	  String SQL_DIRECCION = "SELECT * FROM direccion  ORDER BY direccion.DIRE_DIRECCION ";
	    DIRECCION_RS =  state.executeQuery(SQL_DIRECCION);		    
	    //definimos un arreglo para los resultados		    
	    ArrayList<String> direcciones = new ArrayList<String>();		   
	    //recorremos los resultados de la consulta
	    while(DIRECCION_RS.next()){        	   
 	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
	    	direcciones.add(DIRECCION_RS.getString("DIRE_ID")+"/"+DIRECCION_RS.getString("DIRE_DIRECCION")+"/"+DIRECCION_RS.getString("clpr_id"));
 	    }
	  System.out.println("SIZE LIST: "+direcciones.size());  	
	 
	 
	  String[] ar_direcciones = new String[direcciones.size()];
     for(int x=0; x < direcciones.size(); x++){
    	 ar_direcciones[x]=direcciones.get(x);
     }
           
     request.setAttribute("ar_direcciones", ar_direcciones);
    
           
            //vemos si tiene permiso para todos los reportes
    
            String query_permisos_reportes="SELECT "
            		+ "		`perfilusu_has_permisos`.`permisos_id` "
            		+ "	FROM"
            		+ "		`perfilusu_has_permisos`"
            		+ "	WHERE"
            		+ "		`perfilusu_has_permisos`.`estados_vig_novig_id` = 1 "
            		+ "	AND `perfilusu_has_permisos`.`perfilusu_id` = 1"
            		+ "	AND `perfilusu_has_permisos`.`permisos_id` IN (36, 60, 61)";
            
//            conquery.Consulta(query_permisos_reportes);
//            String tiene36="";
//            String tiene60="";
//            String tiene61="";
           EMPRESAS_RS.close();
      	  
            DIRECCION_RS.close();	
            GRUPOS_RS.close();	
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
