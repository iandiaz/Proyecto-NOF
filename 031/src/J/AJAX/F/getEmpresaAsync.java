package J.AJAX.F;




import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAO.EmpresasDAO;
import VO.EmpresaVO;


/**
 * Servlet implementation class getDireccionesSelect
 */
@WebServlet("/getEmpresaAsync")
public class getEmpresaAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getEmpresaAsync() {
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
		
		String id_emp=request.getParameter("id_emp");
		
		System.out.println("BUSCANDO EMPRESA ASYNC");
		
		//==========TRAEMOS EMPRESAS =========///
		
		response.setCharacterEncoding("UTF-8");
		
		EmpresaVO empSelec = EmpresasDAO.getEmpresa(id_emp);
		
		Gson gson= new Gson();
			
		String json_=gson.toJson(empSelec);
		PrintWriter out = response.getWriter();
			
		out.println(json_);
		
	}
}
