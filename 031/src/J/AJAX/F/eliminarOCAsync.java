package J.AJAX.F;




import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Constantes.Constantes;
import Constantes.Controlador;
import DAO.AlertEventTriggeredDAO;
import DAO.OCsDAO;
import VO.EstadosVigNoVigVO;
import VO.OcVO;


/**
 * Servlet implementation class getDireccionesSelect
 */
@WebServlet("/eliminarOCAsync")
public class eliminarOCAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public eliminarOCAsync() {
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
		
		
		
		String id= request.getParameter("id").trim();
		
		//==========TRAEMOS OC =========///
		
		OcVO oc_filter= new OcVO();
		oc_filter.setId(id);
		oc_filter.setEstadoVigNoVig(new EstadosVigNoVigVO("2"));
		
		OCsDAO.updateOc(oc_filter, Controlador.getUsuIDSession(request));
		
		AlertEventTriggeredDAO.insert(Constantes.MODULOID, Constantes.PELIMINAR, Controlador.getUsuIDSession(request), "", oc_filter.getId()+"");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
	
		out.println("OK");
			
			
			
		
		
	}
	
}
