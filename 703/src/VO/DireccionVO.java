package VO;

public class DireccionVO {
	private EstadosVigNoVigVO estadoVigNoVig;
	private EmpresaVO empresa;
	private String id;
	private String direccion;
	private String ciudad;
	private String comu_nombre;
	private String regi_nombre;
	
	public String getRegi_nombre() {
		return regi_nombre;
	}
	public void setRegi_nombre(String regi_nombre) {
		this.regi_nombre = regi_nombre;
	}
	public String getComu_nombre() {
		return comu_nombre;
	}
	public void setComu_nombre(String comu_nombre) {
		this.comu_nombre = comu_nombre;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public DireccionVO() {
		super();
		
	}
	public EstadosVigNoVigVO getEstadoVigNoVig() {
		return estadoVigNoVig;
	}
	public EmpresaVO getEmpresa() {
		return empresa;
	}
	public void setEstadoVigNoVig(EstadosVigNoVigVO estadoVigNoVig) {
		this.estadoVigNoVig = estadoVigNoVig;
	}
	public void setEmpresa(EmpresaVO empresa) {
		this.empresa = empresa;
	}
	
}
