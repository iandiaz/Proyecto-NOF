package VO;

public class BoletaDetalleVO {
	private String id_boleta;
	private int Precio;
	private String Codigo;
	private int id_producto;
	private int cantidad; 
	private String descripcion; 
	
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public BoletaDetalleVO() {
		super();
		
	}
	public String getId_boleta() {
		return id_boleta;
	}
	public void setId_boleta(String id_boleta) {
		this.id_boleta = id_boleta;
	}
	public int getPrecio() {
		return Precio;
	}
	public void setPrecio(int precio) {
		Precio = precio;
	}
	public String getCodigo() {
		return Codigo;
	}
	public void setCodigo(String codigo) {
		Codigo = codigo;
	}
	public int getId_producto() {
		return id_producto;
	}
	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}
	
}
