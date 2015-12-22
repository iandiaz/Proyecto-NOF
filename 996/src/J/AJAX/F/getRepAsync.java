package J.AJAX.F;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAO.ActivosDAO;
import DAO.BirtTicketsDAO;
import DAO.BirtTrasladosDAO;
import DAO.DireccionesDAO;
import DAO.ImpresionesDAO;
import DAO.PeriodosDAO;
import VO.ActivoVO;
import VO.DireccionVO;
import VO.ImpresionVO;
import VO.PeriodoVO;
import VO.SuministroVO;
import VO.TicketVO;

/**
 * Servlet implementation class getRepAsync
 */
@WebServlet("/getRepAsync")
public class getRepAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getRepAsync() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 mt(request,response);
	}
	
public void mt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		String func=request.getParameter("func");
	
		System.out.println("BUSCANDO REP "+func+" ASYNC");
		
		//==========TRAEMOS REP =========///
		
		response.setCharacterEncoding("UTF-8");
		
		
		if(func.equals("get6Periodos")){
			String select_year=request.getParameter("select_year");
		   	String select_month=request.getParameter("select_month");
		  	
			ArrayList<PeriodoVO> arr =PeriodosDAO.get6Periodos_Hasta(select_year, select_month);
			Gson gson= new Gson();
			
			String json_=gson.toJson(arr);
			PrintWriter out = response.getWriter();
			
			out.println(json_);
			 
		}
		
		if(func.equals("getVentasImp")){
			String select_year=request.getParameter("select_year");
		   	String select_month=request.getParameter("select_month");
		 	String idemp=request.getParameter("select_empresa");
		   	String idgrupo=request.getParameter("select_grupo");
		   	String[] select_vendedor = request.getParameterValues("select_vendedor[]");
			
			ArrayList<PeriodoVO> arr_periodos =PeriodosDAO.get6Periodos_Hasta(select_year, select_month);
			
			ArrayList<ImpresionVO> impresiones = new ArrayList<ImpresionVO>();
			for(PeriodoVO per:arr_periodos){
				ImpresionVO imp = ImpresionesDAO.getTotalVentasImp(per.getYear(), per.getMonth(), idemp, idgrupo, select_vendedor);
				impresiones.add(imp);
			}
			
			
			Gson gson= new Gson();
			
			String json_=gson.toJson(impresiones);
			PrintWriter out = response.getWriter();
			
			out.println(json_);
			 
		}
		if(func.equals("getNImp")){
			String select_year=request.getParameter("select_year");
		   	String select_month=request.getParameter("select_month");
		 	String idemp=request.getParameter("select_empresa");
		   	String idgrupo=request.getParameter("select_grupo");
		   	String[] select_vendedor = request.getParameterValues("select_vendedor[]");
			
			ArrayList<PeriodoVO> arr_periodos =PeriodosDAO.get6Periodos_Hasta(select_year, select_month);
			
			ArrayList<ImpresionVO> impresiones = new ArrayList<ImpresionVO>();
			for(PeriodoVO per:arr_periodos){
				ImpresionVO imp = ImpresionesDAO.getTotalNImp(per.getYear(), per.getMonth(), idemp, idgrupo, select_vendedor);
				impresiones.add(imp);
			}
			
			
			Gson gson= new Gson();
			
			String json_=gson.toJson(impresiones);
			PrintWriter out = response.getWriter();
			
			out.println(json_);
			 
		}
		if(func.equals("getEnvSumMaq")){
			String select_year=request.getParameter("select_year");
		   	String select_month=request.getParameter("select_month");
		 	String idemp=request.getParameter("select_empresa");
		   	String idgrupo=request.getParameter("select_grupo");
		   	String[] select_vendedor = request.getParameterValues("select_vendedor[]");
			
			ArrayList<PeriodoVO> arr_periodos =PeriodosDAO.get6Periodos_Hasta(select_year, select_month);
			
			ArrayList<SuministroVO> suministros = new ArrayList<SuministroVO>();
			for(PeriodoVO per:arr_periodos){
				SuministroVO suministro = BirtTrasladosDAO.getNumeroEnvSuministrosMaqConYearMonth(per.getYear(), per.getMonth(), idemp, idgrupo, select_vendedor);
				if(suministro.getRendimiento()==null)suministro.setRendimiento("0");
				suministros.add(suministro);
			}
			
			
			Gson gson= new Gson();
			
			String json_=gson.toJson(suministros);
			PrintWriter out = response.getWriter();
			
			out.println(json_);
			 
		}
		
		if(func.equals("getEnvKit")){
			String select_year=request.getParameter("select_year");
		   	String select_month=request.getParameter("select_month");
		 	String idemp=request.getParameter("select_empresa");
		   	String idgrupo=request.getParameter("select_grupo");
		   	String[] select_vendedor = request.getParameterValues("select_vendedor[]");
			
			ArrayList<PeriodoVO> arr_periodos =PeriodosDAO.get6Periodos_Hasta(select_year, select_month);
			
			ArrayList<SuministroVO> suministros = new ArrayList<SuministroVO>();
			for(PeriodoVO per:arr_periodos){
				SuministroVO suministro = BirtTrasladosDAO.getNumeroEnvKitConYearMonth(per.getYear(), per.getMonth(), idemp, idgrupo, select_vendedor);
				if(suministro.getRendimiento()==null)suministro.setRendimiento("0");
				suministros.add(suministro);
			}
			
			Gson gson= new Gson();
			
			String json_=gson.toJson(suministros);
			PrintWriter out = response.getWriter();
			
			out.println(json_);
			 
		}
		if(func.equals("getEnvFotoCond")){
			String select_year=request.getParameter("select_year");
		   	String select_month=request.getParameter("select_month");
		 	String idemp=request.getParameter("select_empresa");
		   	String idgrupo=request.getParameter("select_grupo");
		   	String[] select_vendedor = request.getParameterValues("select_vendedor[]");
			
			ArrayList<PeriodoVO> arr_periodos =PeriodosDAO.get6Periodos_Hasta(select_year, select_month);
			
			ArrayList<SuministroVO> suministros = new ArrayList<SuministroVO>();
			for(PeriodoVO per:arr_periodos){
				SuministroVO suministro = BirtTrasladosDAO.getNumeroEnvFotoCondConYearMonth(per.getYear(), per.getMonth(), idemp, idgrupo, select_vendedor);
				if(suministro.getRendimiento()==null)suministro.setRendimiento("0");
				suministros.add(suministro);
			}
			
			Gson gson= new Gson();
			
			String json_=gson.toJson(suministros);
			PrintWriter out = response.getWriter();
			
			out.println(json_);
			 
		}
		if(func.equals("getEnvConsumibles")){
			String select_year=request.getParameter("select_year");
		   	String select_month=request.getParameter("select_month");
		 	String idemp=request.getParameter("select_empresa");
		   	String idgrupo=request.getParameter("select_grupo");
		   	String[] select_vendedor = request.getParameterValues("select_vendedor[]");
			
			ArrayList<PeriodoVO> arr_periodos =PeriodosDAO.get6Periodos_Hasta(select_year, select_month);
			
			ArrayList<SuministroVO> suministros = new ArrayList<SuministroVO>();
			for(PeriodoVO per:arr_periodos){
				SuministroVO suministro = BirtTrasladosDAO.getNumeroEnvConsumiblesConYearMonth(per.getYear(), per.getMonth(), idemp, idgrupo, select_vendedor);
				if(suministro.getRendimiento()==null)suministro.setRendimiento("0");
				suministros.add(suministro);
			}
			
			Gson gson= new Gson();
			
			String json_=gson.toJson(suministros);
			PrintWriter out = response.getWriter();
			
			out.println(json_);
			 
		}
		if(func.equals("getEnvRepuestos")){
			String select_year=request.getParameter("select_year");
		   	String select_month=request.getParameter("select_month");
		 	String idemp=request.getParameter("select_empresa");
		   	String idgrupo=request.getParameter("select_grupo");
		   	String[] select_vendedor = request.getParameterValues("select_vendedor[]");
			
			ArrayList<PeriodoVO> arr_periodos =PeriodosDAO.get6Periodos_Hasta(select_year, select_month);
			
			ArrayList<SuministroVO> suministros = new ArrayList<SuministroVO>();
			for(PeriodoVO per:arr_periodos){
				SuministroVO suministro = BirtTrasladosDAO.getNumeroEnvRepuestosConYearMonth(per.getYear(), per.getMonth(), idemp, idgrupo, select_vendedor);
				if(suministro.getRendimiento()==null)suministro.setRendimiento("0");
				suministros.add(suministro);
			}
			
			Gson gson= new Gson();
			
			String json_=gson.toJson(suministros);
			PrintWriter out = response.getWriter();
			
			out.println(json_);
			 
		}
		
		if(func.equals("getTickLog")){
			
			String select_year=request.getParameter("select_year");
		   	String select_month=request.getParameter("select_month");
		 	String idemp=request.getParameter("select_empresa");
		   	String idgrupo=request.getParameter("select_grupo");
		   	String[] select_vendedor = request.getParameterValues("select_vendedor[]");
			
			ArrayList<PeriodoVO> arr_periodos =PeriodosDAO.get6Periodos_Hasta(select_year, select_month);
			
			ArrayList<TicketVO> tickets = new ArrayList<TicketVO>();
			for(PeriodoVO per:arr_periodos){
				TicketVO tick = BirtTicketsDAO.getNumeroTicketsLogConYearMonth(per.getYear(), per.getMonth(), idemp, idgrupo, select_vendedor);
				tickets.add(tick);
			}
			
			Gson gson= new Gson();
			
			String json_=gson.toJson(tickets);
			PrintWriter out = response.getWriter();
			
			out.println(json_);
			 
		}
		
		
		if(func.equals("getTickServTec")){
			
			String select_year=request.getParameter("select_year");
		   	String select_month=request.getParameter("select_month");
		 	String idemp=request.getParameter("select_empresa");
		   	String idgrupo=request.getParameter("select_grupo");
		   	String[] select_vendedor = request.getParameterValues("select_vendedor[]");
			
			ArrayList<PeriodoVO> arr_periodos =PeriodosDAO.get6Periodos_Hasta(select_year, select_month);
			
			ArrayList<TicketVO> tickets = new ArrayList<TicketVO>();
			for(PeriodoVO per:arr_periodos){
				TicketVO tick = BirtTicketsDAO.getNumeroTicketsServTecConYearMonth(per.getYear(), per.getMonth(), idemp, idgrupo, select_vendedor);
				tickets.add(tick);
			}
			
			Gson gson= new Gson();
			
			String json_=gson.toJson(tickets);
			PrintWriter out = response.getWriter();
			
			out.println(json_);
			 
		}
		
		if(func.equals("getNmaquinas")){
			
			String select_year=request.getParameter("select_year");
		   	String select_month=request.getParameter("select_month");
		 	String idemp=request.getParameter("select_empresa");
		   	String idgrupo=request.getParameter("select_grupo");
		   	String[] select_vendedor = request.getParameterValues("select_vendedor[]");
			
			ArrayList<PeriodoVO> arr_periodos =PeriodosDAO.get6Periodos_Hasta(select_year, select_month);
			
			ArrayList<ActivoVO> activos = new ArrayList<ActivoVO>();
			for(PeriodoVO per:arr_periodos){
				ActivoVO activo = ActivosDAO.getNMaquinasConYearMonth(per.getYear(), per.getMonth(), idemp, idgrupo, select_vendedor);
				activos.add(activo);
			}
			
			Gson gson= new Gson();
			
			String json_=gson.toJson(activos);
			PrintWriter out = response.getWriter();
			
			out.println(json_);
			 
		}
		
		if(func.equals("getDirs")){
			
			String select_year=request.getParameter("select_year");
		   	String select_month=request.getParameter("select_month");
		 	String idemp=request.getParameter("select_empresa");
		   	String idgrupo=request.getParameter("select_grupo");
		   	String[] select_vendedor = request.getParameterValues("select_vendedor[]");
			
			ArrayList<PeriodoVO> arr_periodos =PeriodosDAO.get6Periodos_Hasta(select_year, select_month);
			
			ArrayList<DireccionVO> direcciones = new ArrayList<DireccionVO>();
			for(PeriodoVO per:arr_periodos){
				DireccionVO direccion = DireccionesDAO.getNDireccionesYearMonth(per.getYear(), per.getMonth(), idemp, idgrupo, select_vendedor);
				direcciones.add(direccion);
			}
			
			Gson gson= new Gson();
			
			String json_=gson.toJson(direcciones);
			PrintWriter out = response.getWriter();
			
			out.println(json_);
			 
		}
        
	}

}
