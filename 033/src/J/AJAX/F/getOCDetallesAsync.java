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

import DAO.OCDetallesDAO;
import VO.EstadosVigNoVigVO;
import VO.OcDetalleVO;
import VO.OcVO;


/**
 * Servlet implementation class getDireccionesSelect
 */
@WebServlet("/getOCDetallesAsync")
public class getOCDetallesAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getOCDetallesAsync() {
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
		
		String id_oc=request.getParameter("id_oc");
		String estados_vig_novig_id=request.getParameter("estados_vig_novig_id");
		
		System.out.println("BUSCANDO OCsDetalles "+id_oc);
		
		//==========TRAEMOS OCs =========///
		
		response.setCharacterEncoding("UTF-8");
		
		OcDetalleVO ocdet_filter=new OcDetalleVO();
		if(id_oc!=null)ocdet_filter.setOc(new OcVO(id_oc));
		if(estados_vig_novig_id!=null)ocdet_filter.setEstadoVigNoVig(new EstadosVigNoVigVO(estados_vig_novig_id));
		
		ArrayList<OcDetalleVO> arr = OCDetallesDAO.getOCDetalles(ocdet_filter);
		Gson gson= new Gson();
		
		String json_=gson.toJson(arr);
		PrintWriter out = response.getWriter();
		
		out.println(json_);
		 
        
        
	}
}
