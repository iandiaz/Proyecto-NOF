package VO;

public class SpecialBidVO {
	String id;
	String SB_PROVEEDOR;
	String SB_NRO;
	String id_prod;
	String DETISB_PRECIO;
	String GRUPO_NOM;
	
	String saldo; 
	
	
	public String getGRUPO_NOM() {
		return GRUPO_NOM;
	}
	public void setGRUPO_NOM(String gRUPO_NOM) {
		GRUPO_NOM = gRUPO_NOM;
	}
	public String getSaldo() {
		return saldo;
	}
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}
	public String getDETISB_PRECIO() {
		return DETISB_PRECIO;
	}
	public void setDETISB_PRECIO(String dETISB_PRECIO) {
		DETISB_PRECIO = dETISB_PRECIO;
	}
	public String getId_prod() {
		return id_prod;
	}
	public void setId_prod(String id_prod) {
		this.id_prod = id_prod;
	}
	public String getSB_NRO() {
		return SB_NRO;
	}
	public void setSB_NRO(String sB_NRO) {
		SB_NRO = sB_NRO;
	}
	public SpecialBidVO() {
		super();
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getSB_PROVEEDOR() {
		return SB_PROVEEDOR;
	}

	public void setSB_PROVEEDOR(String sB_PROVEEDOR) {
		SB_PROVEEDOR = sB_PROVEEDOR;
	}

	

}
