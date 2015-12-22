

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Constantes.Constantes;
import Constantes.Controlador;
import DAO.DteDetallesDAO;
import DAO.DteTotalesDAO;
import DAO.DtesDAO;
import VO.DteDetalleVO;
import VO.DteTotalVO;
import VO.DteVO;

/**
 * Servlet implementation class C03_001_831
 */
@WebServlet("/C03_001_831")
public class C03_001_831 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public C03_001_831() {
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
		
		String id_dte=request.getParameter("id_dte");
		
		DteVO dte_encabezado = DtesDAO.getDteEncabezado(id_dte);
		DteDetalleVO dte_detalle_filter= new DteDetalleVO();
		dte_detalle_filter.setId_enc(id_dte);
		ArrayList<DteDetalleVO> dte_detalles = DteDetallesDAO.getDteDetalles(dte_detalle_filter);
		
		DteTotalVO dte_totales= DteTotalesDAO.getDteTotales(id_dte);
		
		request.setAttribute("dte_encabezado", dte_encabezado);
		request.setAttribute("dte_detalles", dte_detalles);
		request.setAttribute("dte_totales", dte_totales);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("C03_001_831.jsp");
        rd.forward(request, response);
		
		}

}
