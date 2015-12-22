

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
 * Servlet implementation class c1
 */
@WebServlet("/c1")
public class c1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public c1() {
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
	
	
		String Usuarios_ID=Controlador.getUsuIDSession(request);
		
		request.setAttribute("usuname", Controlador.getUsunameSession(request));
		
		
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		Statement state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
    	    
    		String SQL_Guias = "select date_format(`adm_folios`.`admfolios_feccreacion`,'%d-%m-%Y') as admfolios_feccreacion "
    				+ "		,`adm_folios`.`admfolios_desde`"
    				+ "		,`adm_folios`.`admfolios_hasta`"
    				+ "		,`adm_folios`.`tipodte`"
    				+ "		,`adm_folios`.`admfolios_id`"
    				+ "		,`adm_folios`.`estados_vig_novig_id`"
    				+ "		,`empresas`.`empresas_nombrenof` "
    				+ "		, lastfoliotb.folio AS lastfolio		 "
    				+ "		from `adm_folios`"
    				+ "		LEFT JOIN (SELECT max(`dte_encabezado`.`Folio`) AS Folio  ,`dte_encabezado`.`TipoDTE`, dte_encabezado.empresa_emisora_id " + 
    				"			FROM `dte_encabezado`    " + 
    				"			GROUP BY  dte_encabezado.`TipoDTE`,dte_encabezado.`empresa_emisora_id` 		 	" + 
    				"			) AS lastfoliotb ON (`lastfoliotb`.`TipoDTE`=`adm_folios`.`tipodte`	 AND `lastfoliotb`.`empresa_emisora_id`=`adm_folios`.`empresas_id` ) " 
    				+ "		inner join `empresas` on `empresas`.`empresas_id` = `adm_folios`.`empresas_id`  "; 
    		System.out.println(SQL_Guias);
 		    ResultSet GUIAS_RS = state.executeQuery(SQL_Guias);ArrayList<String> prod_res = new ArrayList<String>();
 		    while(GUIAS_RS.next()){
		    	
 		    	String marcado="0";
 		    	
 		    	if(GUIAS_RS.getInt("admfolios_desde")< GUIAS_RS.getInt("lastfolio") && GUIAS_RS.getInt("admfolios_hasta")> GUIAS_RS.getInt("lastfolio"))marcado="1";
		    	
 		    	String vig=GUIAS_RS.getString("estados_vig_novig_id");
 		    	
 		    	if(GUIAS_RS.getInt("lastfolio") > GUIAS_RS.getInt("admfolios_hasta"))vig="2";
		    	
 		    	
 		    	prod_res.add(GUIAS_RS.getString("admfolios_feccreacion")+"/"+GUIAS_RS.getString("admfolios_desde")+"/"+GUIAS_RS.getString("admfolios_hasta")+"/"+GUIAS_RS.getString("tipodte")+"/"+GUIAS_RS.getString("admfolios_id")+"/"+vig+"/"+GUIAS_RS.getString("empresas_nombrenof")+"/"+marcado);
		   }
 		     
 		    String[] ar_admfolios = new String[prod_res.size()];
 		    for(int x=0; x < prod_res.size(); x++){
 		    	ar_admfolios[x]=prod_res.get(x);
 		    }
 		    
 		    request.setAttribute("ar_admfolios", ar_admfolios);
 		    
 		    
 		 
 		   GUIAS_RS.close();
 		    state.close();
 		  
 		 conexion.close();
           
		}catch(Exception ex){
		    out.println("ERROR: "+ex);
		    ex.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("c1.jsp");
        rd.forward(request, response);
		
	}
}
