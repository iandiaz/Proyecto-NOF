package VO;

public class PedidoSerieVO {
	String prod_id;
	String id_pedido;
	String id_detalle_pedido;
	String precio;
	String sb_id;
	
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
