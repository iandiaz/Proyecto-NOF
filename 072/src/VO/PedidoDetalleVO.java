package VO;

public class PedidoDetalleVO {
	String id; 
	String id_pedido;
	String cantidad;
	String id_producto;
	
	String id_ubi;
	ProductoVO producto;
	UbicacionVO ubicacion;
	
	
	
	public ProductoVO getProducto() {
		return producto;
	}

	public void setProducto(ProductoVO producto) {
		this.producto = producto;
	}

	
	public UbicacionVO getUbicacion() {
		return ubicacion;
	}

	
	public void setUbicacion(UbicacionVO ubicacion) {
		this.ubicacion = ubicacion;
	}

	
	
	public String getId_ubi() {
		return id_ubi;
	}

	public void setId_ubi(String id_ubi) {
		this.id_ubi = id_ubi;
	}

	public String getId() {
		return id;
	}

	public String getId_pedido() {
		return id_pedido;
	}

	public String getCantidad() {
		return cantidad;
	}

	public String getId_producto() {
		return id_producto;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setId_pedido(String id_pedido) {
		this.id_pedido = id_pedido;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public void setId_producto(String id_producto) {
		this.id_producto = id_producto;
	}

	public PedidoDetalleVO() {
		super();
		
	}

}
