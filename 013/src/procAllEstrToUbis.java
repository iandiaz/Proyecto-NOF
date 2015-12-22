

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import VO.AnexoContratoVO;
import VO.EstructuraCobroVO;
import VO.MaquinaContadorVO;

/**
 * Servlet implementation class procAllEstrToUbis
 */
@WebServlet("/procAllEstrToUbis")
public class procAllEstrToUbis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public procAllEstrToUbis() {
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
		mt(request, response);
	}
	
	public void mt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		System.out.println("ASIGNAMOS TODAS LAS ESTRUCTURAS A TODAS LAS UBI MAQUINAS ");
		
		HttpSession session =request.getSession(true);
		AnexoContratoVO anexoContrato = (AnexoContratoVO)session.getAttribute("anexoContrato");
		
		String func = request.getParameter("func");
		if(func.equals("add"))for(EstructuraCobroVO estructura: anexoContrato.getEstructurasCobro()){
			
			estructura.setMaquinasContador(anexoContrato.getMaquinasContadores());
		}
		if(func.equals("remove"))for(EstructuraCobroVO estructura: anexoContrato.getEstructurasCobro()){
			
			estructura.setMaquinasContador(new ArrayList<MaquinaContadorVO>());
		}
		
	}

}
