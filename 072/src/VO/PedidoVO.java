package VO;

import java.util.ArrayList;

public class PedidoVO {
	String id;
	String id_ticket;
	EmpresaVO cliente;
	EmpresaVO proveedor; 
	String fec_crea;
	String enviado;
	DireccionVO direccion;
	String estado_p;
	
	public DireccionVO getDireccion() {
		return direccion;
	}

	public String getEstado_p() {
		return estado_p;
	}

	public void setDireccion(DireccionVO direccion) {
		this.direccion = direccion;
	}

	public void setEstado_p(String estado_p) {
		this.estado_p = estado_p;
	}

	public String getEnviado() {
		return enviado;
	}

	public void setEnviado(String enviado) {
		this.enviado = enviado;
	}

	public EmpresaVO getProveedor() {
		return proveedor;
	}

	public void setProveedor(EmpresaVO proveedor) {
		this.proveedor = proveedor;
	}

	public String getFec_crea() {
		return fec_crea;
	}

	public void setFec_crea(String fec_crea) {
		this.fec_crea = fec_crea;
	}

	public EmpresaVO getCliente() {
		return cliente;
	}

	public void setCliente(EmpresaVO cliente) {
		this.cliente = cliente;
	}

	ArrayList<PedidoDetalleVO> detallePedido; 

	public ArrayList<PedidoDetalleVO> getDetallePedido() {
		return detallePedido;
	}

	public void setDetallePedido(ArrayList<PedidoDetalleVO> detallePedido) {
		this.detallePedido = detallePedido;
	}

	

	public String getId_ticket() {
		return id_ticket;
	}

	
	public void setId_ticket(String id_ticket) {
		this.id_ticket = id_ticket;
	}

	public PedidoVO() {
		super();
		detallePedido= new ArrayList<PedidoDetalleVO>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	} 

}
