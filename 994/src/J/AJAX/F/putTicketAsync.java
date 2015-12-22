package J.AJAX.F;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Constantes.Constantes;
import DAO.CorreosDAO;
import DAO.ticketsBirtDAO;
import VO.CorreoVO;
import VO.TicketVO;

/**
 * Servlet implementation class getDireccionesSelect
 */
@WebServlet("/putTicketAsync")
public class putTicketAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public putTicketAsync() {
        super();
        // 
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
		
		System.out.println("INSERTANDO TICKET");
		
		//==========TRAEMOS VALORES DE TICKET =========///
		
		
		String CLPR_NOMBRE_FANTASIA=request.getParameter("CLPR_NOMBRE_FANTASIA");
		String TICK_USUARIO=request.getParameter("TICK_USUARIO");
		String TICK_EMAIL=request.getParameter("TICK_EMAIL");
		
		String TICK_DIRECCION=request.getParameter("TICK_DIRECCION");
		String TICK_TELEFONO=request.getParameter("TICK_TELEFONO");
		String TICK_MODELO_SERIE=request.getParameter("TICK_MODELO_SERIE");
		String TICK_SERIE=request.getParameter("TICK_SERIE");
		String TICK_CODIGO_FALLA=request.getParameter("TICK_CODIGO_FALLA");
		String TICK_FALLA_DENUNCIA=request.getParameter("TICK_FALLA_DENUNCIA");
		String TICK_ESTADO="ABIERTO";
		String TICK_FECHA_ENVIO=request.getParameter("TICK_FECHA_ENVIO");
		String TICK_HORA_ENVIO=request.getParameter("TICK_HORA_ENVIO");
		String TICK_USU_ING="CLI";
		String USU_ID_UM="0";
		
		System.out.println(TICK_FECHA_ENVIO+" "+TICK_HORA_ENVIO);
		
				
		TicketVO ticket = new TicketVO();
		
		String[] TICK_FECHA_ENVIO_=TICK_FECHA_ENVIO.split("-");
		TICK_FECHA_ENVIO=TICK_FECHA_ENVIO_[2]+"-"+TICK_FECHA_ENVIO_[1]+"-"+TICK_FECHA_ENVIO_[0];
		
		ticket.setCLPR_NOMBRE_FANTASIA(CLPR_NOMBRE_FANTASIA.toUpperCase());
		ticket.setTICK_USUARIO(TICK_USUARIO.toUpperCase());
		ticket.setTICK_EMAIL(TICK_EMAIL.toUpperCase());
		ticket.setTICK_DIRECCION(TICK_DIRECCION.toUpperCase());
		ticket.setTICK_TELEFONO(TICK_TELEFONO.toUpperCase());
		ticket.setTICK_MODELO_SERIE(TICK_MODELO_SERIE.toUpperCase());
		ticket.setTICK_SERIE(TICK_SERIE.toUpperCase());
		ticket.setTICK_CODIGO_FALLA(TICK_CODIGO_FALLA.toUpperCase());
		ticket.setTICK_FALLA_DENUNCIA(TICK_FALLA_DENUNCIA.toUpperCase());
		ticket.setTICK_ESTADO(TICK_ESTADO.toUpperCase());
		ticket.setTICK_USU_ING(TICK_USU_ING.toUpperCase());
		ticket.setUSU_ID_UM(USU_ID_UM);
		
		ticket.setTICK_FECHA_ENVIO(TICK_FECHA_ENVIO+" "+TICK_HORA_ENVIO);
		
		ticketsBirtDAO ticketdao=new ticketsBirtDAO(false);
		String id=null;
		try{
			id=ticketdao.insertTicket(ticket);
		}catch(Exception e){
			e.printStackTrace();
		}
		ticket.setId(id);
		
		response.setCharacterEncoding("UTF-8");
		String[] result=new String[2];
		
		if(ticket.getId()==null){
			result[0]="ERROR";
			result[1]="ERROR EN EL INGRESO DEL TICKET, FAVOR CONTACTARSE CON NEWOFFICE ";
				
		}
		else{
			result[0]="OK";
			result[1]="ESTIMADO USUARIO: EL TICKET NRO "+ticket.getId()+" SE HA INGRESADO DE MANERA CORRECTA, LO CONTACTAREMOS A LA BREVEDAD. QUE TENGA UN BUEN DIA ";
			
			//insetamos correo 
			
			CorreoVO correo = new CorreoVO();
			correo.setModulos_id(Constantes.MODULOID);
			correo.setSubject("CONFIRMACION ENVIO TICKET NRO "+ticket.getId());
			correo.setTo(ticket.getTICK_EMAIL());
			correo.setBody("ESTIMADO USUARIO: EL TICKET NRO. "+ticket.getId()+" SE HA ENVIADO DE MANERA SATISFACTORIA A NEWOFFICE, LO CONTACTAREMOS A LA BREVEDAD. QUE TENGA UN BUEN DIA");
			
			CorreosDAO.insertCorreo(correo);
			
		}
		
		
		PrintWriter out = response.getWriter();
		
		Gson gson= new Gson();
		
		String json_=gson.toJson(result);
		
		out.println(json_);
		 
	
		 
        
        
	}
}
