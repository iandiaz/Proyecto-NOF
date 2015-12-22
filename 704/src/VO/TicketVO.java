package VO;

public class TicketVO {
	String id;
	String deti_tipo_ticket;
	String deti_empresa;
	String direccion;
	String ubicacion;
	String fecha_envio;
	
	public String getFecha_envio() {
		return fecha_envio;
	}
	public void setFecha_envio(String fecha_envio) {
		this.fecha_envio = fecha_envio;
	}
	public String getId() {
		return id;
	}
	public String getDireccion() {
		return direccion;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getDeti_tipo_ticket() {
		return deti_tipo_ticket;
	}
	public String getDeti_empresa() {
		return deti_empresa;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setDeti_tipo_ticket(String deti_tipo_ticket) {
		this.deti_tipo_ticket = deti_tipo_ticket;
	}
	public void setDeti_empresa(String deti_empresa) {
		this.deti_empresa = deti_empresa;
	}
	
}
