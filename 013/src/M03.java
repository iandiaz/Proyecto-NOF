

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Constantes.Constantes;
import DAO.EstructuraCobroDAO;
import DAO.RangoEstructuraCobroDAO;
import DAO.TipoCambioDAO;
import DAO.TipoEstcobroDAO;
import VO.AnexoContratoVO;
import VO.EstadosVigNoVigVO;
import VO.EstructuraCobroVO;
import VO.RangoEstructuraCobroVO;
import VO.TipoEstcobroVO;

/**
 * Servlet implementation class M03
 */
@WebServlet("/M03")
public class M03 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public M03() {
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
	
	private void mt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		PrintWriter out = response.getWriter();		
		
		if(request.getParameter("logout") !=  null){
				Controlador.eraseCookie(request, response);
				response.sendRedirect("/001/");	
				return;
		}
		
		//////////////////////////////////////////////////
		////////VERIFICAMOS PERMISO ASOCIADO/////////////

		String p=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), Constantes.PMODIFICAR);
		
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
		
		
		if(request.getParameter("deteleRango") != null){
			
			String id_rango = request.getParameter("deteleRango");
			
			HttpSession session =request.getSession(true);

			EstructuraCobroVO estructuraCobro=(EstructuraCobroVO)session.getAttribute("estructuraCobro_");

			ArrayList<RangoEstructuraCobroVO> rangos = estructuraCobro.getRangosEstCobro();
			
			for(int i =0; i<rangos.size();i++){
				RangoEstructuraCobroVO rangoSET = rangos.get(i);
				
				if(rangoSET.getRango_id().equals(id_rango)) rangos.remove(i);
				
				
			}
			
			estructuraCobro.setRangosEstCobro(rangos);
			
			AnexoContratoVO anexoContrato = (AnexoContratoVO)session.getAttribute("anexoContrato_");
			
			request.setAttribute("estructuraCobro", estructuraCobro);
			request.setAttribute("anexoContrato", anexoContrato);
			
			request.setAttribute("tipocambios", TipoCambioDAO.getTipoCambios());
			
			session.setAttribute("estructuraCobro_", estructuraCobro);
    		
			RequestDispatcher rd = request.getRequestDispatcher("M03.jsp");
            rd.forward(request, response);
			return; 
		}
		if(request.getParameter("grabarEstructura") != null){

			String estrcobro_observaciones=request.getParameter("estrcobro_observaciones");
			String tipo_cambio_id=request.getParameter("tipo_cambio_id");
			String estrcobro_cxa=request.getParameter("estrcobro_cxa");
			String tipo_estcobro_id=request.getParameter("tipo_estcobro_id");
			
			
			HttpSession session =request.getSession(true);

			EstructuraCobroVO estructuraCobro=(EstructuraCobroVO)session.getAttribute("estructuraCobro_");
			estructuraCobro.setEstrcobro_observaciones(estrcobro_observaciones.toUpperCase());
			estructuraCobro.setTipo_cambio_id(tipo_cambio_id);
			estructuraCobro.setEstrcobro_cxa(estrcobro_cxa);
			
			estructuraCobro.setEstadoVignoVig(new EstadosVigNoVigVO("1"));
			
			TipoEstcobroVO tipoEst = new TipoEstcobroVO();
			tipoEst.setId(tipo_estcobro_id);
			
			estructuraCobro.setTipo_estructuraC(tipoEst);
			
			
			ArrayList<RangoEstructuraCobroVO> rangos = estructuraCobro.getRangosEstCobro();
			//buscamos y reordenamos rangos
			
			for(int i =0; i<rangos.size();i++){
				RangoEstructuraCobroVO rangoSET = rangos.get(i);
				String rangoFinalSet=request.getParameter(rangoSET.getRango_id()+"_rangoFinal");
				
				String rango_costofijo=request.getParameter(rangoSET.getRango_id()+"_rangoCFijo");
				String rango_costovariable=request.getParameter(rangoSET.getRango_id()+"_rangoCVariable");
				
				rangoSET.setRango_final(rangoFinalSet);
				rangoSET.setRango_costo_fijo(rango_costofijo);
				rangoSET.setRango_costo_variable(rango_costovariable);
				
				
				rangos.set(i, rangoSET);
			}
			
			estructuraCobro.setRangosEstCobro(rangos);
			
			AnexoContratoVO anexoContrato = (AnexoContratoVO)session.getAttribute("anexoContrato_");
			
			ArrayList<EstructuraCobroVO> listEstructuras= anexoContrato.getEstructurasCobro();
			for(int i =0; i<listEstructuras.size();i++){
				
				EstructuraCobroVO est = listEstructuras.get(i);
				
				if(est.getEstrcobro_id().equals(estructuraCobro.getEstrcobro_id())){
					listEstructuras.remove(i);
				}
			}
			
			listEstructuras.add(estructuraCobro);
			
			anexoContrato.setEstructurasCobro(listEstructuras);
			
			session.setAttribute("anexoContrato_", anexoContrato);
		
			response.sendRedirect("M02?anexo="+anexoContrato.getAnc_id());
			return; 
		
		}
		if(request.getParameter("agregarRango") != null){
			
			
			String estrcobro_observaciones=request.getParameter("estrcobro_observaciones");
			String tipo_cambio_id=request.getParameter("tipo_cambio_id");
			String estrcobro_cxa=request.getParameter("estrcobro_cxa");
			String estrcobro_nombre=request.getParameter("estrcobro_nombre");
			
			String tipo_estcobro_id=request.getParameter("tipo_estcobro_id");
			
			HttpSession session =request.getSession(true);

			EstructuraCobroVO estructuraCobro=(EstructuraCobroVO)session.getAttribute("estructuraCobro_");
			
			estructuraCobro.setEstrcobro_observaciones(estrcobro_observaciones.toUpperCase());
			estructuraCobro.setTipo_cambio_id(tipo_cambio_id);
			estructuraCobro.setEstrcobro_cxa(estrcobro_cxa);
			estructuraCobro.setEstrcobro_nombre(estrcobro_nombre.toUpperCase());
			
			TipoEstcobroVO tipoEst = new TipoEstcobroVO();
			tipoEst.setId(tipo_estcobro_id);
			
			estructuraCobro.setTipo_estructuraC(tipoEst);
			
			
			RangoEstructuraCobroVO rango = new RangoEstructuraCobroVO();
			rango.setRango_id(RangoEstructuraCobroDAO.getNewId()+"");
			rango.setRango_final("");
			rango.setRango_costo_fijo("");
			rango.setRango_costo_variable("");
			
			rango.setEstadoVignoVig(new EstadosVigNoVigVO("1"));
			
			ArrayList<RangoEstructuraCobroVO> rangos = estructuraCobro.getRangosEstCobro();
			
			if(rangos.size()==0) rango.setRango_inicial("1");
			else{
				
				//buscamos y reordenamos rangos
				
				for(int i =0; i<rangos.size();i++){
					RangoEstructuraCobroVO rangoSET = rangos.get(i);
					String rangoFinalSet=request.getParameter(rangoSET.getRango_id()+"_rangoFinal");
					
					String rango_costofijo=request.getParameter(rangoSET.getRango_id()+"_rangoCFijo");
					String rango_costovariable=request.getParameter(rangoSET.getRango_id()+"_rangoCVariable");
					
					rangoSET.setRango_final(rangoFinalSet);
					rangoSET.setRango_costo_fijo(rango_costofijo);
					rangoSET.setRango_costo_variable(rango_costovariable);
					
					rangos.set(i, rangoSET);
				}
				
				//sumamos 1 al ultimo rango ingresado
				
				rango.setRango_id((RangoEstructuraCobroDAO.getNewId()+rangos.size())+"");
				rango.setRango_inicial((Integer.parseInt(rangos.get(rangos.size()-1).getRango_final())+1)+"");
			}
			rangos.add(rango);
			
			estructuraCobro.setRangosEstCobro(rangos);
			
			AnexoContratoVO anexoContrato = (AnexoContratoVO)session.getAttribute("anexoContrato_");
			
			request.setAttribute("estructuraCobro", estructuraCobro);
			request.setAttribute("anexoContrato", anexoContrato);
			request.setAttribute("tipoestructura", TipoEstcobroDAO.getTipoEstcobro());
			request.setAttribute("tipocambios", TipoCambioDAO.getTipoCambios());
			
			
			session.setAttribute("estructuraCobro_", estructuraCobro);
    		
			RequestDispatcher rd = request.getRequestDispatcher("M03.jsp");
            rd.forward(request, response);
			return; 
		}
		
		
		//===============NINGUN BOTON PRESIONADO==============///
		
		
		String estr = (String) request.getParameter("estr");
		
		//restacamos contrato
		
		HttpSession session =request.getSession(true);

		AnexoContratoVO anexoContrato = (AnexoContratoVO)session.getAttribute("anexoContrato_");
		
		EstructuraCobroVO estructuraCobro = null; 
		
		if(estr!=null){
			for(EstructuraCobroVO est:anexoContrato.getEstructurasCobro()){
				if(est.getEstrcobro_id().equals(estr)){
					estructuraCobro=est;
					break;
				}
			}
		}else{
			estructuraCobro = new EstructuraCobroVO();
			
			int idnew=EstructuraCobroDAO.getNewId();
			
			while(anexoContrato.getEstructurasCobro().contains(new EstructuraCobroVO(idnew+""))){
				idnew++;
			}
			
			estructuraCobro.setEstrcobro_id(idnew+"");
			
		}
		
		
		request.setAttribute("estructuraCobro", estructuraCobro);
		request.setAttribute("anexoContrato", anexoContrato);
		request.setAttribute("tipoestructura", TipoEstcobroDAO.getTipoEstcobro());
		request.setAttribute("tipocambios", TipoCambioDAO.getTipoCambios());
		
		
		session.setAttribute("estructuraCobro_", estructuraCobro);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("M03.jsp");
        rd.forward(request, response);
	}

}
