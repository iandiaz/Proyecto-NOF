

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
import VO.EstadosVigNoVigVO;
import VO.OcDetalleVO;
import VO.OcVO;

/**
 * Servlet implementation class N030_I_01_001
 */
@WebServlet("/N032_C_02_001")
public class N032_C_02_001 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public N032_C_02_001() {
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
	   	
	   	String p=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), Constantes.PINGRESAR);
	   	
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
	   	
	   	String id_oc= request.getParameter("id");
	   	
	   	OcVO oc=OCsDAO.getOC(id_oc);
	   	
	   	OcDetalleVO dcdet_filter = new OcDetalleVO();
	   	dcdet_filter.setOc(new OcVO(oc.getId()));
	   	dcdet_filter.setEstadoVigNoVig(new EstadosVigNoVigVO("1"));
	   	oc.setDetalle_oc(OCDetallesDAO.getOCDetalles(dcdet_filter));
	   	
	   	request.setAttribute("oc", oc);
		
		RequestDispatcher rd = request.getRequestDispatcher("N"+Constantes.MODULO+"_C_02_001.jsp");
        rd.forward(request, response);
		
	}
	
	
	 
	
	

}
