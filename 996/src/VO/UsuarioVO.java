package VO;

public class UsuarioVO {
  private String id; 
  private String nombre; 
  private String ape_p; 
  private String ape_m; 
  
	public String getApe_p() {
		return ape_p;
	}
	
	public String getApe_m() {
		return ape_m;
	}
	
	public void setApe_p(String ape_p) {
		this.ape_p = ape_p;
	}
	
	public void setApe_m(String ape_m) {
		this.ape_m = ape_m;
	}

	public UsuarioVO() {
		super();
	}

	public UsuarioVO(String id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
