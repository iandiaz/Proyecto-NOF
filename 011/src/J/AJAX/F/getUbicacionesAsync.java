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

import Constantes.Controlador;
import DAO.UbicacionesDAO;
import VO.UbicacionVO;


/**
 * Servlet implementation class getDireccionesSelect
 */
@WebServlet("/getUbicacionesAsync")
public class getUbicacionesAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getUbicacionesAsync() {
        super();
        // TODO Auto-generated constructor stub
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
		
		String id_cliente=request.getParameter("id_cliente");
		
		System.out.println("BUSCANDO UBICACIONES ");
		
		//==========TRAEMOS UBICACIONES =========///
		
		response.setCharacterEncoding("UTF-8");
		
		
		ArrayList<UbicacionVO> arr = UbicacionesDAO.getUbicaciones(new UbicacionVO(), Controlador.getPerfilIDSession(request));
		Gson gson= new Gson();
		
		String json_=gson.toJson(arr);
		PrintWriter out = response.getWriter();
		
		out.println(json_);
		 
        
        
	}
}
