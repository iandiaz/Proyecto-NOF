

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
import DAO.OCDetallesDAO;
import DAO.OCsDAO;
import DAO.TipoCambioDAO;
import VO.EstadosVigNoVigVO;
import VO.OcDetalleVO;
import VO.OcVO;

/**
 * Servlet implementation class E02
 */
@WebServlet("/E02")
public class E02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public E02() {
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
		
		//////////////////////////////////////////////////
		////////VERIFICAMOS PERMISO ASOCIADO/////////////

		String p=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), Constantes.PELIMINAR);
		
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
		
		String id_oc=request.getParameter("id");
		
		if(request.getParameter("grabar") != null){
			
			OcVO oc = new OcVO(id_oc);
			oc.setEstadoVigNoVig(new EstadosVigNoVigVO("2"));
			
			OCsDAO.updateOc(oc, Controlador.getUsuIDSession(request), Constantes.PMODIFICAR);
			
			response.sendRedirect("/071?Exito=OK");
			return;
			
			
		}
		
		//==========OC=========///
		
		OcVO OC = OCsDAO.getOC(id_oc);
		
		OcDetalleVO detalle_oc_filter = new OcDetalleVO();
		detalle_oc_filter.setOc(new OcVO(OC.getId()));
		detalle_oc_filter.setEstadoVigNoVig(new EstadosVigNoVigVO("1"));
		
		OC.setDetalle_oc(OCDetallesDAO.getOCDetalles(detalle_oc_filter));
		
		request.setAttribute("OC", OC);
		
		request.setAttribute("tipocambios", TipoCambioDAO.getTipoCambios());
		
		RequestDispatcher rd = request.getRequestDispatcher("E02.jsp");
        rd.forward(request, response);
	}

}
