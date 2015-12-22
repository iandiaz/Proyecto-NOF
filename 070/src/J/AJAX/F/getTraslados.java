package J.AJAX.F;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class getTraslados
 */
@WebServlet("/getTraslados")
public class getTraslados extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getTraslados() {
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
		
		
		System.out.println("BUSCANDO HISTORIA DE LA UBICACION ");
		
		//==========TRAEMOS TRASLADOS =========///
		String ubi_id=request.getParameter("ubi_id");
		
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		
		 URL oracle = new URL("http://186.67.10.115:81/NOF/rpt_996_2_pdf_VIEW.php?id_ub="+ubi_id+"");
	        BufferedReader in = new BufferedReader(
	        new InputStreamReader(oracle.openStream()));

	        String page="";
	        String inputLine;
	        while ((inputLine = in.readLine()) != null)
	           
	        page+=inputLine;
	        in.close();
	        System.out.println(page);
	       out.println(page);	
        
        
	}

}
