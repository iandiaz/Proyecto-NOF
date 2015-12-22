


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import Constantes.Controlador;
import DAO.DireccionesDAO;
import DAO.EmpresasDAO;
import DAO.EstadosVigNoVigDAO;
import DAO.GruposDAO;
import DAO.PedidosDAO;
import DAO.PedidosDetallesDAO;
import VO.EstadosVigNoVigVO;
import VO.PedidoDetalleVO;
import VO.PedidoVO;
import VO.ProductoVO;

/**
 * Servlet implementation class R_GEN1
 */
@WebServlet("/R_GEN1")
public class R_GEN1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public R_GEN1() {
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
		
		PedidoVO pedido_ = new PedidoVO();
		
		PedidoDetalleVO detallepedido_ = new PedidoDetalleVO();
		detallepedido_.setEstadoVigNoVig(new EstadosVigNoVigVO("1"));
		
		if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
			pedido_.setGrupoCliente(GruposDAO.getGrupo(request.getParameter("select_grupo")));
		}
		if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
			pedido_.setCliente(EmpresasDAO.getEmpresa(request.getParameter("select_empresa")));
		}
		if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
			pedido_.setDireccion(DireccionesDAO.getDireccion(request.getParameter("select_direccion")));
		}
		if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
			pedido_.setEstadoVigNoVig(EstadosVigNoVigDAO.getEstadoVigNoVig(request.getParameter("select_estado")));
		}
		if(request.getParameter("select_prod") !=  null && !request.getParameter("select_prod").equals("") ){
			detallepedido_.setProducto(new ProductoVO(request.getParameter("select_prod")));
		}
		
		
		if(request.getParameter("f1")!=null && !request.getParameter("f1").equals("") && request.getParameter("f2")!=null && !request.getParameter("f2").equals("")){
			
			String fec_desde_ar[]= request.getParameter("f1").split("-");
			String fec_hasta_ar[]= request.getParameter("f2").split("-");
			
			String fec_desde=fec_desde_ar[2]+"-"+fec_desde_ar[1]+"-"+fec_desde_ar[0];
			String fec_hasta=fec_hasta_ar[2]+"-"+fec_hasta_ar[1]+"-"+fec_hasta_ar[0];
			
			pedido_.setFec_desde(fec_desde);
			pedido_.setFec_hasta(fec_hasta);
			
		}
		
		
		ArrayList<PedidoVO> rpt = PedidosDAO.getPedidos(pedido_);
		
		String[] pedidos_ids = new String[rpt.size()];
		int c=0;
		for(PedidoVO pedido:rpt){
			pedidos_ids[c]=pedido.getId();
			
			c++;
		}
		detallepedido_.setIds_pedidos_filter(pedidos_ids);
		
		
		ArrayList<PedidoDetalleVO> detalle_pedido = PedidosDetallesDAO.getDetallePedidos(detallepedido_);
		
		for(PedidoDetalleVO detallePedido:detalle_pedido){
			//seteamos los detalles en los pedido que correspondan
			
			String id_p=detallePedido.getId_pedido();
			
			PedidoVO pedi= rpt.get(rpt.indexOf(new PedidoVO(id_p)));
			pedi.getDetallePedido().add(detallePedido);
		}
		
		//eliminamos todos los pedidos sin detalle
		
		for (Iterator<PedidoVO> iter=rpt.iterator();iter.hasNext();) {
			  final PedidoVO pedido = iter.next();
			  if (pedido.getDetallePedido().size()==0) { iter.remove(); }
		}
		
		/**
		 * PREGUNTAMOS PORQUE VIA LO GENERO
		 */
		
		if(request.getParameter("grabar_web") !=  null){
			request.setAttribute("usuname", Controlador.getUsunameSession(request));
	 	    request.setAttribute("rep", rpt);
	        request.setAttribute("pedi_filter", pedido_);
	        request.setAttribute("pedidet_filter", detallepedido_);
	        
	        RequestDispatcher a = request.getRequestDispatcher("R_GEN1.jsp");
	      	a.forward(request, response);
	      	return;
			
		}
		if(request.getParameter("grabar_pdf") !=  null){
			try{
				System.out.println("GENERANDO PDF");
				 // step 1
		        Document document = new Document();
		        
		        ByteArrayOutputStream baos = new ByteArrayOutputStream();
		        // step 2
		        PdfWriter.getInstance(document, baos);
		        // step 3
		        document.open();
		        // step 4
		        
		        Font fontNormal = FontFactory.getFont("sans-serif",10);
		        Font fontBolt = FontFactory.getFont("sans-serif",10,Font.BOLD);
			    
		        Paragraph titulo=new Paragraph("INFORME DE CONTROL: PEDIDOS MAYORISTA",fontBolt); 
		        Paragraph usu=new Paragraph("GENERADO POR "+Controlador.getUsunameSession(request),fontNormal); 
		        
		        String imageUrl = "http://186.67.10.115:81/NOF/img/logo2.png";

		        Image imageLogo = Image.getInstance(new URL(imageUrl));
		        
		        document.add(imageLogo);
		                
		        document.add(titulo);
		        
		        document.add(Chunk.NEWLINE);
		        
		        document.add(usu);
		        
		        document.add(Chunk.NEWLINE);
		        
	            List PEDIDO_List = new List(List.UNORDERED);
	            		        
		        for(PedidoVO pedido:rpt){
		        	
		            Chunk data_pedido=new Chunk(pedido.getId()+" - "+pedido.getCliente().getNombre_nof(), fontNormal); //This gonna be normal font
		            Paragraph p_pedido=new Paragraph(); 
		            p_pedido.add(new Chunk(data_pedido)); 
		        	
		        	ListItem pedidoItem =new ListItem(p_pedido);
		        	PEDIDO_List.add(pedidoItem);
		        	List DETALLES_List = new List(List.UNORDERED);
		        	for(PedidoDetalleVO det_pedido : pedido.getDetallePedido()){
		        		Chunk data_detalle=new Chunk("ID PROD: "+det_pedido.getProducto().getId() +" - "+det_pedido.getProducto().getDesc_corto() +" - "+det_pedido.getProducto().getPn() +" - "+"CANT: "+det_pedido.getCantidad()+" - "+det_pedido.getUbicacion().getNom_fisica()+" - "+"TICKET: "+det_pedido.getId_ticket(), fontNormal);
			            Paragraph p_detalle=new Paragraph(); 
			            p_detalle.add(new Chunk(data_detalle)); 
		        		
		        		ListItem detalleItem =new ListItem(p_detalle);
		        		DETALLES_List.add(detalleItem);
			        	
		        	}
		        	pedidoItem.add(DETALLES_List);
		        	
		        }
		        
		        document.add(PEDIDO_List);
		        // step 5
		        document.close();
		        
		        response.setHeader("Expires", "0");
	            response.setHeader("Cache-Control",
	                "must-revalidate, post-check=0, pre-check=0");
	            response.setHeader("Pragma", "public");
	            // setting the content type
	            response.setContentType("application/pdf");
	            // the contentlength
	            response.setContentLength(baos.size());
	            // write ByteArrayOutputStream to the ServletOutputStream
	            ServletOutputStream os = response.getOutputStream();
	            baos.writeTo(os);
	            os.flush();
	            os.close();
		      	return;
	        
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
     
		if(request.getParameter("grabar_excel") !=  null){
			try{
				System.out.println("GENERANDO EXCEL");
				 // step 1
				// this could be file stream too or any other stream where you want to write your output
			
				// create a work book
				HSSFWorkbook workbook = new HSSFWorkbook();

				// create a sheet under the work book above
				HSSFSheet sheet = workbook.createSheet("sheet 1");

				// create a row in above sheet
				HSSFRow row = sheet.createRow(0); // 0,1,2..

				// create a cell or column in the above row
				HSSFCell cellTitulo = row.createCell(0); // 0,1,2...
				// set the actual data or cell contents
				cellTitulo.setCellValue("NEWOFFICE");
				
				HSSFRow row1 = sheet.createRow(1); // 0,1,2..
				(row1.createCell(0)).setCellValue("INFORME PEDIDOS MAYORISTA"); //A3
				/**
				 * DATA ENCABEZADO
				 */
				
				
				HSSFRow row3 = sheet.createRow(3); // 0,1,2..
				//HSSFCell cell_A3 = row3.createCell(0); // 0,1,2...
				
				(row3.createCell(0)).setCellValue("ID PEDIDO"); //A3
				(row3.createCell(1)).setCellValue("CLIENTE"); 
				(row3.createCell(2)).setCellValue("PROVEEDOR");
				(row3.createCell(3)).setCellValue("DIRECCION"); 
				(row3.createCell(4)).setCellValue("ESTADO"); 
				(row3.createCell(5)).setCellValue("FECHA"); 
				(row3.createCell(6)).setCellValue("ESTADO VIGENTE NO VIGENTE");
				
				(row3.createCell(7)).setCellValue("ID PRODUCTO");
				(row3.createCell(8)).setCellValue("DESCRIPCION");
				(row3.createCell(9)).setCellValue("PART NUMBER");
				(row3.createCell(10)).setCellValue("CANTIDAD");
				(row3.createCell(11)).setCellValue("UBI FISICA");
				(row3.createCell(12)).setCellValue("TICKET");
				
				/**
				 * DATA DETALLE
				 */
				int countRow=4;
				 for(PedidoVO pedido:rpt){
					 	
			         	for(PedidoDetalleVO det_pedido : pedido.getDetallePedido()){
			        		
			        		
			        		HSSFRow rowData = sheet.createRow(countRow); 
			        		(rowData.createCell(0)).setCellValue(pedido.getId());
			        		(rowData.createCell(1)).setCellValue(pedido.getCliente().getNombre_nof());
			        		(rowData.createCell(2)).setCellValue(pedido.getProveedor().getNombre_nof());
			        		(rowData.createCell(3)).setCellValue(pedido.getDireccion().getDireccion());
			        		
			        		(rowData.createCell(4)).setCellValue(pedido.getEstado_p());
			        		(rowData.createCell(5)).setCellValue(pedido.getFec_crea());
			        		(rowData.createCell(6)).setCellValue(pedido.getEstadoVigNoVig().getNombre());
			        		
			        		(rowData.createCell(7)).setCellValue(det_pedido.getProducto().getId());
			        		(rowData.createCell(8)).setCellValue(det_pedido.getProducto().getDesc_corto());
			        		(rowData.createCell(9)).setCellValue(det_pedido.getProducto().getPn());
			        		(rowData.createCell(10)).setCellValue(det_pedido.getCantidad());
			        		(rowData.createCell(11)).setCellValue(det_pedido.getUbicacion().getNom_fisica());
			        		(rowData.createCell(12)).setCellValue(det_pedido.getId_ticket());
			        		
			        		countRow++;
			        	}
			        	
			        }
				
				
				// More about cell styles can be found on the Developer Guide page above.
				// create font
				//HSSFFont font = workbook.createFont();
				// create cell style that can be applied to cell or entire row
				//HSSFCellStyle style = workbook.createCellStyle();
				//style.setFont(font);
				//cell.setCellStyle(style);

				// write-out your workbook to the output stream
					
				
				response.setContentType("application/vnd.ms-excel");
			       workbook.write(response.getOutputStream());
			       response.getOutputStream().close();
				
			  	return;
	        
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
	
	
	
}

}
