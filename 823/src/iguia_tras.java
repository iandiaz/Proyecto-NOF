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
 * Servlet implementation class iguia_tras
 */
@WebServlet("/iguia_tras")
public class iguia_tras extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public iguia_tras() {
        super();
       
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(Controlador.validateSession(request, response)){
			
			 mt(request,response);
		}
		else response.sendRedirect("/001/");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		
		request.setAttribute("usuname", Controlador.getUsunameSession(request));
		
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
    		String DID=request.getParameter("did");
    		String condiciones=request.getParameter("condiciones");
    		String obs=request.getParameter("obs");
    		String ref=request.getParameter("ref");

    		request.setAttribute("clientes_id",EID);
    		request.setAttribute("condiciones",condiciones);
    		request.setAttribute("ref",ref);
    		request.setAttribute("obs",obs);
    		request.setAttribute("did",DID);
    		//::::::::::::::::::::::::::SACAMOS LOS YA EXISTENTES :::::::::::::::::::::::::::::::
  		    String SQL_TRASNOFDET = "SELECT tras_id FROM detalle_823 WHERE estados_vig_novig_id=1";
  		    ResultSet TRASNOFDET_RS = state.executeQuery(SQL_TRASNOFDET);
  		    
  		    
  		    ArrayList<String> id_tras_existentes= new ArrayList<String>();
  		    while(TRASNOFDET_RS.next()){   
  		    	id_tras_existentes.add(TRASNOFDET_RS.getString("tras_id"));
  		    	
  		    	
  			}
    		
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
    		
    		String ids="0";
    		
    		String[] seleccionado_detguias = request.getParameterValues("detguias[]");
    		if(seleccionado_detguias!=null)
    			for (String id_gi: seleccionado_detguias) {
	    			if(id_gi!=null && !id_gi.equals("-1")){
	    				ids+=","+id_gi;
	    			}
    			}
    		ConBirt birtBD2= new ConBirt();
    		String queru="SELECT "
    				+ "		t.ALT_ID,"
    				+ "		t.tras_id,"
    				+ "		p.PROD_ID,"
    				+ "		p.PROD_PN_TLI_CODFAB,"
    				+ "		p.PROD_DESC_CORTO,"
    				+ "		p.PROD_COD_BAR_FAB,"
    				+ "		a.ALT_SERIE,"
    				+ "		CONVERT (VARCHAR(10), t.TRAS_FECHA, 105) AS fecha,"
    				+ "		u_destino.UBI_DESCRIPCION,"
    				+ "		u_destino.UBI_ID, "
    				+ "		t.TRAS_FECHA, t.TICK_ID "
    				+ "	FROM"
    				+ "		traslado t"
    				+ "	INNER JOIN activo a ON t.ALT_ID = a.ALT_ID"
    				+ "	INNER JOIN producto p ON a.PROD_ID = p.PROD_ID"
    				+ "	INNER JOIN UBICACION u_destino ON t.TRAS_UBI_ID_DESTINO = u_destino.UBI_ID"
    				+ "	INNER JOIN UBICACION u_origen ON t.TRAS_UBI_ID_ORIGEN = u_origen.UBI_ID"
    				+ "	WHERE"
    				+ "		u_destino.DIRE_ID = "+request.getParameter("d_id")
    				+ "	AND u_origen.DIRE_ID = "+request.getParameter("o_id")
    				+ "	AND t.TRAS_FECHA>'2015-03-15 00:00:00' "
    				+ ""
    				+ " ORDER BY"
    				+ "		p.PROD_DESC_CORTO,a.ALT_SERIE";
    		System.out.println(queru);
    		ResultSet QueryBirt2= birtBD2.Consulta(queru);
		    
		    if(request.getAttribute("filtro")!=null){
		    	request.setAttribute("filtro",request.getAttribute("filtro"));
		    }		    
		    ArrayList<String> alertas = new ArrayList<String>();
		    while(QueryBirt2.next()){
		    	String marcado="0";		    	
		    	if(ids.contains(QueryBirt2.getString("tras_id"))) marcado=QueryBirt2.getString("tras_id");
		    	
		    	String precio2="NO EXISTE";
		    	
		    	for(int i =0; i<preciosbdnof.size();i++){
		    		String dat = preciosbdnof.get(i);
		    		String[] dat_ar = dat.split("/");
		    		
		    		if(dat_ar[0].equals(QueryBirt2.getString("PROD_ID"))){
		    			precio2=dat_ar[1];
		    		}
		    		
		    	}
		    	
		    	boolean lomuestra=true; 
		    	for(String id_tras_existente:id_tras_existentes){
		    		if(QueryBirt2.getString("tras_id").equals(id_tras_existente)){
		    			System.out.println("ya existe "+id_tras_existente);
		    			lomuestra=false;
		    			break;
		    		}
		    	}
		    	if(lomuestra){
		    		alertas.add(
    						QueryBirt2.getString("ALT_ID")+";;"+
    						QueryBirt2.getString("PROD_DESC_CORTO").replaceAll(";", "")+";;"+
    						QueryBirt2.getString("PROD_PN_TLI_CODFAB").replaceAll(";", "")+";;"+
    						QueryBirt2.getString("PROD_COD_BAR_FAB").replaceAll(";", "")+";;"+
    						QueryBirt2.getString("ALT_SERIE").replaceAll(";", "")+";;"+
    						marcado+";;"+
    						QueryBirt2.getString("tras_id")+";;"+
    						QueryBirt2.getString("fecha")+";;"+
    						QueryBirt2.getString("UBI_DESCRIPCION").replaceAll(";", "")+";;"+
    						QueryBirt2.getString("TRAS_FECHA")+";;"+
    						QueryBirt2.getString("tras_id")+";;"+
    						QueryBirt2.getString("UBI_ID")+";;"+
    						QueryBirt2.getString("TICK_ID")+";;"+
    						precio2
    						);	
		    	}
	    		
    	    }
		    QueryBirt2.close();
		    state.close();
            conexion.close();
                
            String[] ar_alertas = new String[alertas.size()];
            for(int x=0; x < alertas.size(); x++){
            	ar_alertas[x]=alertas.get(x);
            }
             
            request.setAttribute("ar_alertas", ar_alertas);
            
            request.setAttribute("d_id", request.getParameter("d_id"));
		    request.setAttribute("o_id", request.getParameter("o_id"));
		    
		    request.setAttribute("empresas_id", request.getParameter("empresas_id"));
		    request.setAttribute("gv_fecha", request.getParameter("gv_fecha"));
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("ERROR "+ex);
		}
			
		RequestDispatcher rd = request.getRequestDispatcher("iguia_tras.jsp");
        rd.forward(request, response);
		
		}

}
