package webserver;

import java.util.ArrayList;

import VO.BoletaDetalleVO;

public class ArchivoBoleta {
	public static String getEncabezado(String TipoDTE,String Folio,String FechaEmision,String IndServicio,String IndMntNeto,String PeriodoDesde,String PeriodoHasta,String FechaVenc,String RUTCliente,String CodInterno,String RSCliente,String GiroCliente,String DirCliente,String ComCliente,String CiuCliente,String Email){
		String encabezado ="";
		
		encabezado+="->Boleta<-\r\n"+
					TipoDTE+";"+Folio+";"+FechaEmision+";"+IndServicio+";"+IndMntNeto+";"+
					PeriodoDesde+";"+PeriodoHasta+";"+FechaVenc+";"+
					RUTCliente+";"+CodInterno+";"+RSCliente+";"+
					GiroCliente+";"+DirCliente+";"+ComCliente+";"+CiuCliente+";"+Email+";\r\n";
		
		return encabezado; 
	}
	
	public static String getTotales(String Neto,String Exento,String IVA,String Total,String MontoNF,String TotalPeriodo,String SaldoAnterior,String ValorAPagar){
		String totales ="";
		totales+="->BoletaTotales<-\r\n"+
					Neto+";"+Exento+";"+IVA+";"+Total+";"+MontoNF+";"+
					TotalPeriodo+";"+SaldoAnterior+";"+ValorAPagar+";\r\n";
		
		return totales; 
	}
	
	public static String getDetalle(ArrayList<BoletaDetalleVO> boletaDetalle){
		String detalle ="";
		detalle+="->BoletaDetalle<-\r\n";
		int c=1;
		for (BoletaDetalleVO detalleBoleta: boletaDetalle) {
			detalle+=c+";"+detalleBoleta.getId_producto()+";"+detalleBoleta.getDescripcion()+";0;"+detalleBoleta.getCantidad()+";"+detalleBoleta.getPrecio()+";0;"+detalleBoleta.getPrecio()+";INT1;UN;"+detalleBoleta.getDescripcion()+";\r\n";
			c++;
		}
					
		
		return detalle; 
	}
}
