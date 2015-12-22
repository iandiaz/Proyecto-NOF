

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
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
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
import DAO.EmpresasDAO;
import DAO.EstadosVigNoVigDAO;
import DAO.GruposDAO;
import DAO.ReportesDAO;
import VO.EmpresaVO;
import VO.EstadoClPrVO;
import VO.UsuarioVO;

/**
 * Servlet implementation class R_GEN2
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
		response.sendRedirect("/001/");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		EmpresaVO empresa_ = new EmpresaVO();
		
		if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
			empresa_.setGrupo(GruposDAO.getGrupo(request.getParameter("select_grupo")));
			
		}
		if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
			empresa_.setId(request.getParameter("select_empresa"));
			empresa_.setNombre_nof(EmpresasDAO.getNombreNof(request.getParameter("select_empresa")));
			
			
		}
		if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
			empresa_.setEstadoVigNoVig(EstadosVigNoVigDAO.getEstadoVigNoVig(request.getParameter("select_estado")));
			
		}
		if(request.getParameter("select_eclpr") !=  null && !request.getParameter("select_eclpr").equals("") ){
			empresa_.setEstadoClPr(new EstadoClPrVO(request.getParameter("select_eclpr")));
			
		}
		if(request.getParameter("select_responsable") !=  null && !request.getParameter("select_responsable").equals("") ){
			empresa_.setResponsable_id(request.getParameter("select_responsable"));
			
		}
		if(request.getParameter("cli") !=  null && request.getParameter("cli").equals("1") ){
			empresa_.setEscliente(request.getParameter("cli"));
			
		}
		if(request.getParameter("pro") !=  null && request.getParameter("pro").equals("1") ){
			empresa_.setEsproveedor(request.getParameter("pro"));
			
		}
		if(request.getParameter("pros") !=  null && request.getParameter("pros").equals("1") ){
			empresa_.setEsprospeccion(request.getParameter("pros"));
			
		}
		
		
		ArrayList<UsuarioVO> rpt = ReportesDAO.getEmpresasxResponsable(empresa_, Controlador.getUsuIDSession(request));
		
		/**
		 * PREGUNTAMOS PORQUE VIA LO GENERO
		 */
		
	
		
		if(request.getParameter("grabar_web") !=  null){
			request.setAttribute("usuname", Controlador.getUsunameSession(request));
	 	    request.setAttribute("rep", rpt);
	        request.setAttribute("empresa_filter", empresa_);
	        
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
			    
		        Paragraph titulo=new Paragraph("INFORME DE CONTROL: REPORTE CLIENTE-PROVEEDOR POR RESPONSABLE",fontBolt); 
		        Paragraph usu=new Paragraph("GENERADO POR "+Controlador.getUsunameSession(request),fontNormal); 
		        
		        
		        String imageUrl = "http://186.67.10.115:81/NOF/img/logo2.png";

		        Image imageLogo = Image.getInstance(new URL(imageUrl));
		        
		        document.add(imageLogo);
		                
		        document.add(titulo);
		        
		        document.add(Chunk.NEWLINE);
		        
		        document.add(usu);
		        
		        document.add(Chunk.NEWLINE);
		        
	            List GRUPOS_List = new List(List.UNORDERED);
	            		        
		        for(UsuarioVO usuario:rpt){
		        	
		        	Chunk titulo_responsable=new Chunk("RESPONSABLE CUENTA: ",fontBolt);//This gonna be bold font
		            Chunk data_responsable=new Chunk(usuario.getId()+" - "+usuario.getNombre()+" "+usuario.getApe_p()+" "+usuario.getApe_m(), fontNormal); //This gonna be normal font
		            Paragraph p_grupo=new Paragraph(); 
		            p_grupo.add(new Chunk(titulo_responsable));
		            p_grupo.add(new Chunk(data_responsable)); 
		        	
		        	ListItem grupoItem =new ListItem(p_grupo);
		        	GRUPOS_List.add(grupoItem);
		        
		        	List EMPRESAS_List = new List(List.UNORDERED);
		        	for(EmpresaVO empresa :usuario.getEmpresas()){
		        		
		        		Chunk titulo_empresa=new Chunk("",fontBolt);
		        		Chunk data_empresa=new Chunk(empresa.getId()+" - "+empresa.getNombre_nof()+" ES CLI:"+(empresa.getEscliente().equals("1")?"SI":"NO")
			            		+" ES PRO:"+(empresa.getEsproveedor().equals("1")?"SI":"NO")
			            		+" ES PROS:"+ (empresa.getEsprospeccion().equals("1")?"SI":"NO")
			            		+" - "+empresa.getEstadoVigNoVig().getNombre(), fontNormal);
			            Paragraph p_empresa=new Paragraph(); 
			            p_empresa.add(new Chunk(titulo_empresa));
			            p_empresa.add(new Chunk(data_empresa)); 
		        		
		        		ListItem empresaItem =new ListItem(p_empresa);
		        		EMPRESAS_List.add(empresaItem);
			        		
		        	}
		        	
		        	grupoItem.add(EMPRESAS_List);
		        	
		        }
		        
		        document.add(GRUPOS_List);
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
				(row1.createCell(0)).setCellValue("REPORTE CLIENTE-PROVEEDOR POR RESPONSABLE"); //A3
				
				
				HSSFCellStyle style = workbook.createCellStyle();
			       // style.setBorderTop((short) 6); // double lines border
			        //style.setBorderBottom((short) 1); // single line border
			        HSSFFont font = workbook.createFont();
			        //font.setFontHeightInPoints((short) 15);
			        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			        style.setFont(font);        
				/**
				 * DATA ENCABEZADO
				 */
				
				
				HSSFRow row3 = sheet.createRow(3); // 0,1,2..
				//HSSFCell cell_A3 = row3.createCell(0); // 0,1,2...
				
				(row3.createCell(0)).setCellValue("RESPONSABLE CUENTA");
				(row3.createCell(1)).setCellValue("EMPRESA NOMBRE FANTASIA"); //A3
				(row3.createCell(2)).setCellValue("EMPRESA NOMBRE NOF"); 
				(row3.createCell(3)).setCellValue("EMPRESA RAZON SOCIAL");
				(row3.createCell(4)).setCellValue("EMPRESA RUT"); 
				(row3.createCell(5)).setCellValue("EMPRESA WEB"); 
				(row3.createCell(6)).setCellValue("ID EMPRESA"); 
				(row3.createCell(7)).setCellValue("ESTADO CLIENTE-PROVEEDOR");
				(row3.createCell(8)).setCellValue("ESTADO");
				(row3.createCell(9)).setCellValue("GRUPO");
				(row3.createCell(10)).setCellValue("ES CLIENTE");
				(row3.createCell(11)).setCellValue("ES PROVEEDOR");
				(row3.createCell(12)).setCellValue("ES PROSPECCION");
				
				row3.getCell(0).setCellStyle(style);
				row3.getCell(1).setCellStyle(style);
				row3.getCell(2).setCellStyle(style);
				row3.getCell(3).setCellStyle(style);
				row3.getCell(4).setCellStyle(style);
				row3.getCell(5).setCellStyle(style);
				row3.getCell(6).setCellStyle(style);
				row3.getCell(7).setCellStyle(style);
				row3.getCell(8).setCellStyle(style);
				row3.getCell(9).setCellStyle(style);
				row3.getCell(10).setCellStyle(style);
				row3.getCell(11).setCellStyle(style);
				row3.getCell(12).setCellStyle(style);
				
				
				/**
				 * DATA DETALLE
				 */
				int countRow=4;
				 for(UsuarioVO responsable:rpt){
					 	
			         	for(EmpresaVO empresa : responsable.getEmpresas()){
			         		HSSFRow rowData = sheet.createRow(countRow); 
			         		(rowData.createCell(0)).setCellValue(responsable.getNombre()+" "+responsable.getApe_p()+" "+responsable.getApe_m());
			         		(rowData.createCell(1)).setCellValue(empresa.getNombre());
			        		(rowData.createCell(2)).setCellValue(empresa.getNombre_nof());
			        		(rowData.createCell(3)).setCellValue(empresa.getRazonsocial());
			        		(rowData.createCell(4)).setCellValue(empresa.getRut());
			        		(rowData.createCell(5)).setCellValue(empresa.getWeb());
			        		(rowData.createCell(6)).setCellValue(empresa.getId());
			        		(rowData.createCell(7)).setCellValue(empresa.getEstadoClPr().getNombre());
			        		(rowData.createCell(8)).setCellValue(empresa.getEstadoVigNoVig().getNombre());
			        		(rowData.createCell(9)).setCellValue(empresa.getGrupo().getNombre());
			        		(rowData.createCell(10)).setCellValue(empresa.getEscliente().equals("1")?"SI":"NO");
			        		(rowData.createCell(11)).setCellValue(empresa.getEsproveedor().equals("1")?"SI":"NO");
			        		(rowData.createCell(12)).setCellValue(empresa.getEsprospeccion().equals("1")?"SI":"NO");
			        		
			        		countRow++;
			        		
			         		
			        	}
			        
			        }
				
				
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
