package VO;

public class PedidoSerieVO {
	String prod_id;
	String id_pedido;
	String id_detalle_pedido;
	String precio;
	String sb_id;
	String serie;
	String pn;
	String desc_corto;
	
	public String getDesc_corto() {
		return desc_corto;
	}
	public void setDesc_corto(String desc_corto) {
		this.desc_corto = desc_corto;
	}
	String ubi_fisica; 
	String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPn() {
		return pn;
	}
	public String getUbi_fisica() {
		return ubi_fisica;
	}
	public void setPn(String pn) {
		this.pn = pn;
	}
	public void setUbi_fisica(String ubi_fisica) {
		this.ubi_fisica = ubi_fisica;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getSb_id() {
		return sb_id;
	}
	public void setSb_id(String sb_id) {
		this.sb_id = sb_id;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	
	public PedidoSerieVO() {
		super();
		
	}
	public String getProd_id() {
		return prod_id;
	}
	public String getId_pedido() {
		return id_pedido;
	}
	public String getId_detalle_pedido() {
		return id_detalle_pedido;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	public void setId_pedido(String id_pedido) {
		this.id_pedido = id_pedido;
	}
	public void setId_detalle_pedido(String id_detalle_pedido) {
		this.id_detalle_pedido = id_detalle_pedido;
	}
	
}
