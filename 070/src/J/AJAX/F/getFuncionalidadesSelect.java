package J.AJAX.F;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.FuncionalidadesDAO;
import VO.FuncionalidadVO;

/**
 * Servlet implementation class getFuncionalidades
 */
@WebServlet("/getFuncionalidadesSelect")
public class getFuncionalidadesSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getFuncionalidadesSelect() {
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
		
	
		System.out.println("BUSCANDO FUNCIONALIDADES ");
		
		//==========TRAEMOS PRODUCTOS =========///
		
		
		FuncionalidadVO func = new FuncionalidadVO();
		
		ArrayList<FuncionalidadVO> funcionalidades= FuncionalidadesDAO.getFuncionalidades(func);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<option value=''>SELECCIONAR</option>");
		for(FuncionalidadVO funcionalidad:funcionalidades){
			
			out.println("<option value='"+funcionalidad.getId()+"'>"+funcionalidad.getId()+" - "+funcionalidad.getNombre()+"</option>");
			
		}
		
	
		 
        
        
	}

}
