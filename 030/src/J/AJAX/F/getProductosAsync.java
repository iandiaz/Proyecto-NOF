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

import DAO.ProductosDAO;
import VO.EstadosVigNoVigVO;
import VO.FuncionalidadVO;
import VO.ProductoVO;


/**
 * Servlet implementation class getDireccionesSelect
 */
@WebServlet("/getProductosAsync")
public class getProductosAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getProductosAsync() {
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
		
		String id_func= request.getParameter("id_func");
		System.out.println("BUSCANDO PRODUCTOS  ");
		
		//==========TRAEMOS PRODUCTOS =========///
		
		response.setCharacterEncoding("UTF-8");
		
		ProductoVO prod_filter = new ProductoVO();
		if(id_func!=null) prod_filter.setFuncionalidad(new FuncionalidadVO(id_func));
		
		prod_filter.setEstadoVigNoVig(new EstadosVigNoVigVO("1"));
		
		ArrayList<ProductoVO> arr = ProductosDAO.getProductos(prod_filter);
		Gson gson= new Gson();
		
		String json_=gson.toJson(arr);
		PrintWriter out = response.getWriter();
		
		out.println(json_);
		 
        
        
	}
}
