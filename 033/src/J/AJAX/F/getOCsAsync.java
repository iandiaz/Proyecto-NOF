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

import DAO.OCsDAO;
import VO.EmpresaVO;
import VO.OcVO;


/**
 * Servlet implementation class getDireccionesSelect
 */
@WebServlet("/getOCsAsync")
public class getOCsAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getOCsAsync() {
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
		
		String id_proveedor=request.getParameter("id_proveedor");
		String id_comprador=request.getParameter("id_comprador");
		
		System.out.println("BUSCANDO OCs  ");
		
		//==========TRAEMOS OCs =========///
		
		response.setCharacterEncoding("UTF-8");
		
		OcVO oc_filter=new OcVO();
		if(id_comprador!=null)oc_filter.setComprador(new EmpresaVO(id_comprador));
		if(id_proveedor!=null)oc_filter.setProveedor(new EmpresaVO(id_proveedor));
		
		ArrayList<OcVO> arr = OCsDAO.getOCs(oc_filter);
		Gson gson= new Gson();
		
		String json_=gson.toJson(arr);
		PrintWriter out = response.getWriter();
		
		out.println(json_);
		 
        
        
	}
}
