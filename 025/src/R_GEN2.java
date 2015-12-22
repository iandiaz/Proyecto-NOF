


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

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

import Constantes.Constantes;
import Constantes.Controlador;
import DAO.ConfEquiposDAO;
import DAO.FuncionalidadesDAO;
import DAO.ProductosDAO;
import DAO.Reportes025DAO;
import VO.ConfEquipoDetalleVO;
import VO.ConfEquipoVO;
import VO.ProductoVO;

/**
 * Servlet implementation class R_GEN1
 */
@WebServlet("/R_GEN2")
public class R_GEN2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public R_GEN2() {
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
		
		ConfEquipoDetalleVO confdetalle_filter = new ConfEquipoDetalleVO();
		confdetalle_filter.setCantMayorCero(true);
		
		ConfEquipoVO conf_filter = new ConfEquipoVO();
		
		if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
			confdetalle_filter.setFuncionalidad(FuncionalidadesDAO.getFuncionalidad(request.getParameter("select_funcionalidad")));
		}
		if(request.getParameter("select_prod") !=  null && !request.getParameter("select_prod").equals("") ){
			confdetalle_filter.setProducto(ProductosDAO.getProducto(request.getParameter("select_prod")));
		}
		if(request.getParameter("select_conf") !=  null && !request.getParameter("select_conf").equals("") ){
			conf_filter.setId(request.getParameter("select_conf"));
			conf_filter= ConfEquiposDAO.getConfiguracion(conf_filter.getId());
			
			confdetalle_filter.setId_confe(conf_filter.getId());
			
		}
			
		
		ArrayList<ProductoVO> rpt = Reportes025DAO.getRep2(confdetalle_filter);
		
		/**
		 * PREGUNTAMOS PORQUE VIA LO GENERO
		 */
		
		if(request.getParameter("grabar_web") !=  null){
			request.setAttribute("usuname", Controlador.getUsunameSession(request));
	 	    request.setAttribute("rep", rpt);
	 	   request.setAttribute("confdetalle_filter", confdetalle_filter);
	 	  request.setAttribute("conf_filter", conf_filter);
	        RequestDispatcher a = request.getRequestDispatcher("R_GEN2.jsp");
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
			    
		        Paragraph titulo=new Paragraph("INFORME DE CONTROL: CONFIGURACIONES POR PRODUCTO",fontBolt); 
		        Paragraph usu=new Paragraph("GENERADO POR "+Controlador.getUsunameSession(request),fontNormal); 
		        
		        String imageUrl = "http://127.0.0.1:"+request.getServerPort()+"/"+Constantes.MODULO+"/images/logo2.png";

		        Image imageLogo = Image.getInstance(new URL(imageUrl));
		        
		        document.add(imageLogo);
		                
		        document.add(titulo);
		        
		        document.add(Chunk.NEWLINE);
		        
		        document.add(usu);
		        
		        document.add(Chunk.NEWLINE);
		        
	            List List_ = new List(List.UNORDERED);
	            		        
		        for(ProductoVO producto:rpt){
		        	
		            Chunk chunk_p1=new Chunk(producto.getId()+" - "+producto.getPn()+" - "+producto.getDesc_corto(), fontNormal); //This gonna be normal font
		            Paragraph p1=new Paragraph(); 
		            p1.add(new Chunk(chunk_p1)); 
		        	
		        	ListItem p1Item =new ListItem(p1);
		        	List_.add(p1Item);
		        	List DETALLES_List = new List(List.UNORDERED);
		        	for(ConfEquipoVO configuracion : producto.getConfiguracionesEquipo()){
		        		Chunk data_detalle=new Chunk(configuracion.getNombre(), fontNormal);
			            Paragraph p_detalle=new Paragraph(); 
			            p_detalle.add(new Chunk(data_detalle)); 
		        		
		        		ListItem detalleItem =new ListItem(p_detalle);
		        		DETALLES_List.add(detalleItem);
			        	
		        	}
		        	p1Item.add(DETALLES_List);
		        	
		        }
		        
		        document.add(List_);
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
				(row1.createCell(0)).setCellValue("CONFIGURACIONES POR PRODUCTO"); //A3
				
				/**
				 * DATA ENCABEZADO
				 */
				
				HSSFRow row3 = sheet.createRow(3); // 0,1,2..
				//HSSFCell cell_A3 = row3.createCell(0); // 0,1,2...
				
				(row3.createCell(0)).setCellValue("ID PRODUCTO"); //A3
				(row3.createCell(1)).setCellValue("PART NUMBER"); 
				(row3.createCell(2)).setCellValue("DESCRIPCION");
				(row3.createCell(3)).setCellValue("CONFIGURACION"); 
				
				/**
				 * DATA DETALLE
				 */
				int countRow=4;
				
				 for(ProductoVO producto:rpt){
					 	
			         	for(ConfEquipoVO conf : producto.getConfiguracionesEquipo()){
			        		
			        		HSSFRow rowData = sheet.createRow(countRow); 
			        		(rowData.createCell(0)).setCellValue(producto.getId());
			        		(rowData.createCell(1)).setCellValue(producto.getPn());
			        		(rowData.createCell(2)).setCellValue(producto.getDesc_corto());
			        		(rowData.createCell(3)).setCellValue(conf.getNombre());
			        		
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
