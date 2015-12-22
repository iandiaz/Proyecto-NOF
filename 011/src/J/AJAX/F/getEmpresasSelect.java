package J.AJAX.F;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Constantes.Controlador;
import DAO.EmpresasDAO;
import VO.EmpresaVO;

/**
 * Servlet implementation class getDireccionesSelect
 */
@WebServlet("/getEmpresasSelect")
public class getEmpresasSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getEmpresasSelect() {
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
		
		String id_grupo=request.getParameter("id_grupo");
		
		System.out.println("BUSCANDO EMPRESAS ");
		
		//==========TRAEMOS GRUPOS =========///
		
		EmpresaVO empSelec = new EmpresaVO();
		if(id_grupo!=null)empSelec.setId_grupo(id_grupo);
		
		
		ArrayList<EmpresaVO> empresas= EmpresasDAO.getEmpresas(empSelec,Controlador.getUsuIDSession(request));
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<option value='' selected >TODOS</option>");
		for(EmpresaVO empresa:empresas){
			
			out.println("<option value='"+empresa.getId()+"'>"+empresa.getId()+" - "+empresa.getNombre()+"</option>");
			
		}
		
	
		 
        
        
	}
}
