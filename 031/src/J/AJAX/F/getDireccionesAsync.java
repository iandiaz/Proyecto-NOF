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

import DAO.DireccionesDAO;
import VO.DireccionVO;
import VO.EmpresaVO;
import VO.EstadosVigNoVigVO;


/**
 * Servlet implementation class getDireccionesSelect
 */
@WebServlet("/getDireccionesAsync")
public class getDireccionesAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getDireccionesAsync() {
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
		String id_emp= request.getParameter("id_emp");
		System.out.println("BUSCANDO Direcciones  ");
		
		//==========TRAEMOS DIRECCIONES =========///
		
		DireccionVO dire_filter = new DireccionVO();
		if(id_emp!=null)dire_filter.setEmpresa(new EmpresaVO(id_emp));
		
		dire_filter.setEstadoVigNoVig(new EstadosVigNoVigVO("1"));
		
		response.setCharacterEncoding("UTF-8");
		
		ArrayList<DireccionVO> arr = DireccionesDAO.getDirecciones(dire_filter);
		Gson gson= new Gson();
		
		String json_=gson.toJson(arr);
		PrintWriter out = response.getWriter();
		
		out.println(json_);
		 
        
        
	}
}
