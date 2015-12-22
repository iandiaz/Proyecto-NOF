

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
 * Servlet implementation class i2
 */
@WebServlet("/i2")
public class i2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public i2() {
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
	

		String Usuarios_ID=Controlador.getUsuIDSession(request);
		
		request.setAttribute("usuname", Controlador.getUsunameSession(request));
		
		
		//grabar

		if(request.getParameter("grabar") != null){
			Statement state = null;	
			try {
				String tipodte=request.getParameter("tipodte");
				String fdesde=request.getParameter("fdesde");
				String fhasta=request.getParameter("fhasta");
				String admfolios_correo_aviso=request.getParameter("admfolios_correo_aviso");
				
				String fecha_ar[]=request.getParameter("fec").split("-");
				String fecha1 = fecha_ar[2]+"-"+fecha_ar[1]+"-"+fecha_ar[0];
				
				String emp_id = request.getParameter("em_id");
	    		
				
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		String SQL_IN = "insert into `adm_folios` (`admfolios_creador`,`admfolios_feccreacion`,`tipodte`,`admfolios_desde`,`admfolios_hasta`,`admfolios_correo_aviso`,admfolios_fecha_asignacion,empresas_id) " + 
	    				"values ('"+Usuarios_ID+"',now(),'"+tipodte+"','"+fdesde+"','"+fhasta+"','"+admfolios_correo_aviso.toUpperCase()+"','"+fecha1+"','"+emp_id+"')"; 
	    		System.out.println(SQL_IN);
	 		    int RS_IN = state.executeUpdate(SQL_IN);
	 		   
	 		  
	        	
	     	    state.close();
	 		    conexion.close();
	        	response.sendRedirect("menu?Exito=OK");
	        	
	        	
			}catch(Exception ex){
				ex.printStackTrace();
			    out.println("ERROR: "+ex);
			}
	  
			
		}else{
		
			try {
				Statement state = null;
				Statement statecli = null;
				ResultSet GUIAS_RS = null;
				ResultSet RS_CLIENTE = null;
				
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		statecli = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		String tipodte = request.getParameter("tipodte");
	    		String emp_id = request.getParameter("em_id");
	    		
	    		request.setAttribute("em_id", emp_id);
	            
	    		
	    		
	    		
	    		   //buscamos el folio en el que vamos 
	 		    
		 		   String SQL_findfolio = "select `dte_encabezado`.`Folio` "
		 		   		+ " from `dte_encabezado` "
		 		   		+ " where `dte_encabezado`.`TipoDTE`='"+tipodte+"' and `dte_encabezado`.`empresa_emisora_id`="+emp_id
		 		   		+ " ORDER BY `dte_encabezado`.`Folio` DESC limit 1 "; 
		    		System.out.println(SQL_findfolio);
		 		    ResultSet RS_FINDFOLIO = state.executeQuery(SQL_findfolio);
		 		    
		 		    String folio="0";
		 		    if(RS_FINDFOLIO.next()){
		 		    	folio=(RS_FINDFOLIO.getInt("Folio"))+"";
		 		    }

		 		   request.setAttribute("folioactual", folio);
		 		    
	    		
	    		
	    		String SQL_Guias = "select date_format(`adm_folios`.`admfolios_feccreacion`,'%d-%m-%Y') as admfolios_feccreacion,`adm_folios`.`admfolios_desde`,`adm_folios`.`admfolios_hasta` "
	    				+ " from `adm_folios` "
	    				+ " where `adm_folios`.`tipodte`='"+tipodte+"' and `adm_folios`.`empresas_id`="+emp_id; 
	    		System.out.println(SQL_Guias);
	 		    GUIAS_RS =  state.executeQuery(SQL_Guias);
	 		    ArrayList<String> prod_res = new ArrayList<String>();
	 		    int count=0;
			    while(GUIAS_RS.next()){
			    	
			    	String estado="";
			    	
			    	if(Integer.parseInt(folio)>=Integer.parseInt(GUIAS_RS.getString("admfolios_desde")) && Integer.parseInt(folio)<=Integer.parseInt(GUIAS_RS.getString("admfolios_hasta"))){
			    		estado="EN USO";
			    	}
			    	if(Integer.parseInt(folio)<Integer.parseInt(GUIAS_RS.getString("admfolios_desde")) ){
			    		estado="NO UTILIZADO";
			    	}
			    	if(Integer.parseInt(folio)>Integer.parseInt(GUIAS_RS.getString("admfolios_hasta")) ){
			    		estado="UTILIZADO";
			    	}
			    	
			    	prod_res.add(GUIAS_RS.getString("admfolios_feccreacion")+"/"+GUIAS_RS.getString("admfolios_desde")+"/"+GUIAS_RS.getString("admfolios_hasta")+"/"+estado);
			    count++;}
	 		     
	 		    String[] ar_admfolios = new String[prod_res.size()];
	 		    for(int x=0; x < prod_res.size(); x++){
	 		    	ar_admfolios[x]=prod_res.get(x);
	 		    }
	 		    
	 		    request.setAttribute("ar_admfolios", ar_admfolios);
	 		    
	 		   request.setAttribute("tipodte", tipodte);
	 		    
	 		   //vemos cual es el ultimo folio ingresado y le sumamos 1 , asi mismo sacamos la fecha actual
	 		   
	 		  String SQL_lastfolio = "select if(max(`adm_folios`.`admfolios_hasta`) is null ,0,max(`adm_folios`.`admfolios_hasta`)) as lastfolio"
	 		  		+ "	, DATE_FORMAT(now(), '%d-%m-%Y') as hoy "
	 		  		+ " from `adm_folios` "
	 		  		+ " where `adm_folios`.`tipodte`='"+tipodte+"' and `adm_folios`.`empresas_id`="+emp_id; 
	 		 System.out.println(SQL_lastfolio);
	 		    ResultSet LASTFOLIO_RS = state.executeQuery(SQL_lastfolio);
	 		    
	 		    if(LASTFOLIO_RS.next()){
	 		    	 request.setAttribute("maxfolio", (LASTFOLIO_RS.getInt("lastfolio")+1)+"" );
	 		    	request.setAttribute("hoy", LASTFOLIO_RS.getString("hoy")); 
	 		    }
	 		    
	 		    
	 		    
	 		    
	 		 
	 		    
	 		  //buscamos el nombre de la empresa seleccionada 
	 		    String emp="";
	 		   String SQL_findem = "select `empresas_nombrenof` from `empresas` where `empresas_id`= "+emp_id; 
	    		System.out.println(SQL_findem);
	 		    ResultSet RS_FINDEMP = state.executeQuery(SQL_findem);
	 		    
	 		 if(RS_FINDEMP.next())emp=RS_FINDEMP.getString("empresas_nombrenof");
	 		   request.setAttribute("emp", emp);
	 		    
	 		    

	 		  //buscamos el ultimo correo alertado
	 		    String lastcorreo="";
	 		   String SQL_lastcorreo = "SELECT `admfolios_correo_aviso` FROM `adm_folios` ORDER BY `admfolios_id` DESC LIMIT 1"; 
	    		System.out.println(SQL_lastcorreo);
	 		    ResultSet RS_LASTCORREO = state.executeQuery(SQL_lastcorreo);
	 		    
	 		 if(RS_LASTCORREO.next())lastcorreo=RS_LASTCORREO.getString("admfolios_correo_aviso");
	 		   request.setAttribute("lastcorreo", lastcorreo);
	 		    
	 		    
	 		    
	 		    
	 		   LASTFOLIO_RS.close();
	 		   GUIAS_RS.close();
	 		    state.close();
	 		  
	 		 conexion.close();
	           
			}catch(Exception ex){
			    out.println("ERROR: "+ex);
			    ex.printStackTrace();
			}
		
			
		RequestDispatcher rd = request.getRequestDispatcher("i2.jsp");
        rd.forward(request, response);
		
		}
		
	}
}
