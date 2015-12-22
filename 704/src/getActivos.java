

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ActivosDAO;
import VO.ActivoVO;

/**
 * Servlet implementation class getActivos
 */
@WebServlet("/getActivos")
public class getActivos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getActivos() {
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
		
		String prod_id=request.getParameter("prod_id");
		
		System.out.println("BUSCANDO ACTIVOS DE PRODUCTO "+prod_id);
		
		//==========TRAEMOS ACTIVOS DE PRODUCTO=========///
		
		ActivoVO acti = new ActivoVO();
		acti.setProd_id(prod_id);
		
		ArrayList<ActivoVO> activos= ActivosDAO.getActivos(acti);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		for(ActivoVO activo:activos){
			out.println("<tr class='hov' >");
			out.println("<td class='detailID' style='font-size:20px'><strong><a href='I02?id_activo="+activo.getId()+"' >"+activo.getId()+"</a></strong></td>");
			out.println("<td class='detailSerie' style='font-size:20px'><strong>"+activo.getSerie()+"</strong></td>");
			out.println("<td class='detailUbi' style='font-size:20px'><strong>"+activo.getEstado_prod()+"</strong></td>");
	
			out.println("</tr>");	
		}
		
	
		 
        
        
	}

}
