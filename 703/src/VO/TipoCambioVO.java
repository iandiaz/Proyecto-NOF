package VO;

public class TipoCambioVO {
	private String id,nombre;

	public TipoCambioVO() {
		super();
		
	}
	
	public TipoCambioVO(String id) {
		super();
		this.id=id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	} 
	
}
