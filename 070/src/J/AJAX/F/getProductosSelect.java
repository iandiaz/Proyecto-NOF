package J.AJAX.F;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ProductosDAO;
import VO.ProductoVO;

/**
 * Servlet implementation class getProductos
 */
@WebServlet("/getProductosSelect")
public class getProductosSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getProductosSelect() {
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
		
		String func_id=request.getParameter("func_id");
		
	
		System.out.println("BUSCANDO PRODUCTOS DE CLIENTE CON ID PRODS ");
		
		//==========TRAEMOS PRODUCTOS =========///
		
		
		ProductoVO prod = new ProductoVO();
		prod.setFunc_id(func_id);
		ArrayList<ProductoVO> productos= ProductosDAO.getProductos(prod);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<option value=''>SELECCIONAR</option>");
		for(ProductoVO producto:productos){
			
			out.println("<option value='"+producto.getId()+"'>"+producto.getId()+" - "+producto.getPn()+" - "+producto.getDesc_corto()+"</option>");
			
		}
		
		
	
		 
        
        
	}

}
