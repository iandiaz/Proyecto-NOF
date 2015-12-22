package VO;

public class ProductoVO {
	String id;
	String pn;
	String func_id;
	String desc_corto;
	String tipo;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDesc_corto() {
		return desc_corto;
	}

	public void setDesc_corto(String desc_corto) {
		this.desc_corto = desc_corto;
	}

	public String getFunc_id() {
		return func_id;
	}

	public void setFunc_id(String func_id) {
		this.func_id = func_id;
	}

	public String getId() {
		return id;
	}

	public String getPn() {
		return pn;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPn(String pn) {
		this.pn = pn;
	}

	public ProductoVO() {
		super();
		
	}

}
