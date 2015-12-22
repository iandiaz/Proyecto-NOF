package J.AJAX.F;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.PedidosDetallesDAO;
import DAO.ticketsBirtDAO;
import VO.EstadosVigNoVigVO;
import VO.PedidoDetalleVO;
import VO.TicketVO;

/**
 * Servlet implementation class getTickets
 */
@WebServlet("/getTickets")
public class getTickets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getTickets() {
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
		
		String id_cliente=request.getParameter("id_cliente");
		
		System.out.println("BUSCANDO TICKETS DE CLIENTE "+id_cliente);
		
		//==========TRAEMOS TICKETS DE CLIENTES=========///
		
		
		TicketVO tickFindVO = new TicketVO();
		tickFindVO.setDeti_empresa(id_cliente);
		tickFindVO.setDeti_tipo_ticket("ENVIO");
		
		ticketsBirtDAO ticketsBirt = new ticketsBirtDAO();
		
		ArrayList<TicketVO> tickets= ticketsBirt.getTickets(tickFindVO);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		for(TicketVO tick:tickets){
			
			//verificamos si el ticket esta pedido, en caso verdadero concatenamos una S
			PedidoDetalleVO detallefilter= new PedidoDetalleVO();
			detallefilter.setId_ticket(tick.getId());
			detallefilter.setEstadoVigNoVig(new EstadosVigNoVigVO("1"));
			
			if(PedidosDetallesDAO.getDetallePedidos(detallefilter).size()>0){tick.setPedido(true);}
			
			String color="";
			if(tick.isPedido())color="color:red";
			
			
			out.println("<tr class='hov' >");
			out.println("<td class='detailID' style='font-size:20px;'><strong><a style='"+color+"' onclick='seleccionaTicket("+tick.getId()+")' >"+tick.getId()+" "+(tick.isPedido() ? "S":"")+"</a></strong></td>");
			out.println("<td class='detailDir' style='font-size:20px'><strong>"+tick.getDireccion()+"</strong></td>");
			out.println("<td class='detailUbi' style='font-size:20px'><strong>"+tick.getUbicacion()+"</strong></td>");
			out.println("<td class='detailFec' style='font-size:20px'><strong>"+tick.getFecha_envio()+"</strong></td>");
			out.println("</tr>");	
		}
		
	
		 
        
        
	}

}
