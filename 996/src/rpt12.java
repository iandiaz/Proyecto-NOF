
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Constantes.Constantes;
import Constantes.Controlador;

/**
 * Servlet implementation class rpt12
 */
@WebServlet("/rpt12")
public class rpt12 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public rpt12() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
boolean sesion_valida=Controlador.validateSession(request,response);
		
		if(!sesion_valida) response.sendRedirect("/001/");
		else {
			 mt(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean sesion_valida=Controlador.validateSession(request,response);
		
		if(!sesion_valida) response.sendRedirect("/001/");
		else {
			 mt(request,response);
		}
	}
	
	public void mt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();		
   	
	   	if(request.getParameter("logout") !=  null){
	   			Controlador.eraseCookie(request, response);
	   			response.sendRedirect("/001/");	
	   			return;
	   	}

	   	//////////////////////////////////////////////////
	   	////////VERIFICAMOS PERMISO ASOCIADO/////////////
	   	
	   	String p=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), Constantes.PREP9);
	   	
	   	if(p.equals("0")){
	   		response.sendRedirect("/001/");	
	   		return;
	   	}
	
	   	
	   	//////////////////////////////////////////////////
	   	////////DEFINE PARAMETROS DE USUARIO//////////////
	   	
	   	request.setAttribute("modname", Controlador.getNameModulo());
	   	
	   	String Perfil_id=Controlador.getPerfilIDSession(request);
	   	
	   	request.setAttribute("usuname", Controlador.getUsunameSession(request));
	   	
	   	////////////////////////////////////////////////////////////
	   	//////////////////////////////////////////////////////////
	   	
	   	
	   	
	  //----------------------PERIODOS Y SUS FECHAS -----------------------// 
		/*
		rep10DAO tcdao=new rep10DAO();
		
		tcdao.generatePeriodos_para_tc(select_year, select_month);
		tcdao.generate12Periodos_para_tc(select_year, select_month);
		
		tcdao.generateNMaquinas_para_tc(idemp,idgrupo,select_vendedor);
		tcdao.generateTotalNCImp_para_tc(idemp, idgrupo,select_vendedor);
		tcdao.generateNImp_para_tc(idemp,idgrupo,select_vendedor);
		
		tcdao.generateTotalVentasImp_para_tc(idemp,idgrupo,select_vendedor);
		
		tcdao.generateTotalVentasNOImpNC_para_tc(idemp,idgrupo,select_vendedor);
		tcdao.generateTotalVentasNOImp_para_tc(idemp,idgrupo,select_vendedor);
		
		
		
		
		
		
		tcdao.generateNSuministrosMaquinasEnviatos_para_tc(idemp,idgrupo,select_vendedor);
		
		tcdao.generateNKitEnviatos_para_tc(idemp,idgrupo,select_vendedor);
		
		tcdao.generateNEnviatos_para_tc_fotocond(idemp,idgrupo,select_vendedor);
		tcdao.generateNEnviatos_para_tc_consumibles(idemp,idgrupo,select_vendedor);
		tcdao.generateNEnviatos_para_tc_repuestos(idemp,idgrupo,select_vendedor);
		
	
		tcdao.generateNTickets_para_tc(idemp,idgrupo,select_vendedor);
	
		
		tcdao.generateNTicketsLogistica_para_tc(idemp,idgrupo,select_vendedor);
		
		tcdao.generateNTicketsServTecnico_para_tc(idemp,idgrupo,select_vendedor);
		
		tcdao.generateNDirecciones_para_tc(idemp,idgrupo,select_vendedor);
		
		tcdao.generateTotalIngresos_para_tc();
		
		tcdao.generateTotales(idemp,idgrupo,select_vendedor);
		
		
		request.setAttribute("periodos_para_tc", tcdao.getPeriodos_para_tc());
		request.setAttribute("periodosfechas_para_tc", tcdao.getPeriodosfechas_para_tc());
		request.setAttribute("periodos_para_tc_nimps", tcdao.getPeriodos_para_tc_nimps());
		request.setAttribute("periodos_para_tc_nmaquinas", tcdao.getPeriodos_para_tc_nmaquinas());
		request.setAttribute("periodos_para_tc_totalventasimps", tcdao.getPeriodos_para_tc_totalventasimps());
		 
		request.setAttribute("periodos_para_tc_totalventasnoimps", tcdao.getPeriodos_para_tc_totalventasnoimps());
		
		request.setAttribute("periodos_para_tc_totaldolaresimps", tcdao.getPeriodos_para_tc_totaldolaresimps());
		request.setAttribute("periodos_para_tc_valorUnitarioimps", tcdao.getPeriodos_para_tc_valorUnitarioimps());
		request.setAttribute("periodos_para_tc_valorUnitarioimpsCL", tcdao.getPeriodos_para_tc_valorUnitarioimpsCL());
		
		request.setAttribute("periodos_para_tc_mediaTC", tcdao.getPeriodos_para_tc_mediaTC());
		request.setAttribute("periodos_para_tc_nsme", tcdao.getPeriodos_para_tc_nsme());
		request.setAttribute("periodos_para_tc_ntickets", tcdao.getPeriodos_para_tc_ntickets());
		request.setAttribute("periodos_para_tc_rendimiento", tcdao.getPeriodos_para_tc_rendimiento());
		
		request.setAttribute("periodos_para_tc_cuc", tcdao.getPeriodos_para_tc_cuc());
		
		request.setAttribute("periodos_para_tc_nkite", tcdao.getPeriodos_para_tc_nkite());
		request.setAttribute("periodos_para_tc_kiterendimiento", tcdao.getPeriodos_para_tc_kiterendimiento());
		request.setAttribute("periodos_para_tc_kitecuc", tcdao.getPeriodos_para_tc_kitecuc());
		
		
		
		request.setAttribute("periodos_para_tc_n_fotocond", tcdao.getPeriodos_para_tc_n_fotocond());
		request.setAttribute("periodos_para_tc_n_consumibles", tcdao.getPeriodos_para_tc_n_consumibles());
		request.setAttribute("periodos_para_tc_n_repuestos", tcdao.getPeriodos_para_tc_n_repuestos());
		
		request.setAttribute("periodos_para_tc_rendimiento_fotocond", tcdao.getPeriodos_para_tc_rendimiento_fotocond());
		request.setAttribute("periodos_para_tc_rendimiento_consumibles", tcdao.getPeriodos_para_tc_rendimiento_consumibles());
		request.setAttribute("periodos_para_tc_rendimiento_repuestos", tcdao.getPeriodos_para_tc_rendimiento_repuestos());
		
		request.setAttribute("periodos_para_tc_cuc_fotocond", tcdao.getPeriodos_para_tc_cuc_fotocond());
		request.setAttribute("periodos_para_tc_cuc_consumibles", tcdao.getPeriodos_para_tc_cuc_consumibles());
		request.setAttribute("periodos_para_tc_cuc_repuestos", tcdao.getPeriodos_para_tc_cuc_repuestos());
		
		
		request.setAttribute("periodos_para_tc_nticketsLog", tcdao.getPeriodos_para_tc_nticketsLog());
		request.setAttribute("periodos_para_tc_nticketsServTec", tcdao.getPeriodos_para_tc_nticketsServTec());
		
		request.setAttribute("periodos_para_tc_ndires", tcdao.getPeriodos_para_tc_ndires());
		
		request.setAttribute("periodos_para_tc_totalIngresos", tcdao.getPeriodos_para_tc_totalIngresos());
		
		request.setAttribute("periodos_para_tc_totales", tcdao.getPeriodos_para_tc_totales());
		tcdao.disconect();
		*/
		
		/*
		
	   	//:::::::::::::::::::::::::: sql trae empresas para select option:::::::::::::::::::::::::::::::::::::
        
		request.setAttribute("ar_grupos", GruposDAO.getGruposVigentesParaSelect());
	     
		request.setAttribute("ar_empresas", EmpresasDAO.getClientesVigentesParaSelect());
		request.setAttribute("ar_select_periodo", PeriodosDAO.getPeriodosVigentesParaSelect());

		 request.setAttribute("ar_vendedores", UsuariosDAO.getVendedoresParaSelect());*/
     
        RequestDispatcher a = request.getRequestDispatcher("rpt12.jsp");
    	a.forward(request, response);
		
		
		
	}

}
