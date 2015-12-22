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
import DAO.ConfEquiposDAO;
import VO.ConfEquipoVO;
import VO.EstadosVigNoVigVO;


/**
 * Servlet implementation class getDireccionesSelect
 */
@WebServlet("/eliminarConfAsync")
public class eliminarConfAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public eliminarConfAsync() {
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
		
		String id_conf=request.getParameter("id");
		String confe_nombre=request.getParameter("confe_nombre");
		System.out.println("ELIMINANDO LA CONFIGURACION");
		
		ConfEquipoVO conf = new ConfEquipoVO();
		conf.setNombre(confe_nombre);
		conf.setId(id_conf);
		conf.setEstadoVigNoVig(new EstadosVigNoVigVO("2"));
		
		ConfEquiposDAO.updateConfEquipo(conf, Controlador.getUsuIDSession(request));
		
		//registramos evento 
		
		AlertEventTriggeredDAO.insert(Constantes.MODULOID, Constantes.PELIMINAR, Controlador.getUsuIDSession(request), conf.getNombre(), conf.getId());
				
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
			
		out.println("OK");
		
	}
}
