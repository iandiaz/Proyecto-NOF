package J.AJAX.F;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UbicacionesDAO;
import VO.DireccionVO;
import VO.EstadosVigNoVigVO;
import VO.UbicacionVO;

/**
 * Servlet implementation class getUbicacionesSelect
 */
@WebServlet("/getUbicacionesSelect")
public class getUbicacionesSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getUbicacionesSelect() {
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
		
		String id_direccion=request.getParameter("id_direccion");
		
		System.out.println("BUSCANDO UBICACIONES DE DIRECCION "+id_direccion);
		
		//==========TRAEMOS UBICACIONES DE DIRECCION=========///
		
		DireccionVO direSeleccionada = new DireccionVO();
		direSeleccionada.setId(id_direccion);
		
		EstadosVigNoVigVO estado = new EstadosVigNoVigVO();
		estado.setId("1");
		
		UbicacionVO ubi = new UbicacionVO();
		
		
		ubi.setDireccion(direSeleccionada);
		
		ubi.setEstadoVigNoVig(estado);
		
		ArrayList<UbicacionVO> ubicaciones= UbicacionesDAO.getUbicaciones(ubi);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<option value=''>SELECCIONAR</option>");
		for(UbicacionVO ubicacion:ubicaciones){
			
			out.println("<option value='"+ubicacion.getId()+"'>"+ubicacion.getId()+" - "+ubicacion.getNom_fisica()+"</option>");
			
		}
		
	
		 
        
        
	}
}
