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
		
		System.out.println("BUSCANDO PRODUCTOS ");
		
		//==========TRAEMOS UBICACIONES =========///
		
		response.setCharacterEncoding("UTF-8");
		
		ArrayList<ProductoVO> arr = ProductosDAO.getProductos(new ProductoVO());
		Gson gson= new Gson();
		
		String json_=gson.toJson(arr);
		PrintWriter out = response.getWriter();
		
		out.println(json_);
		 
        
        
	}
}
