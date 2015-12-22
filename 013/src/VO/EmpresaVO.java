package VO;

public class EmpresaVO {
	private String empresas_nombrenof;
	private String empresas_razonsocial;
	private String empresas_rut;
	private String empresas_id;
	private String grupos_nombre;
	
	private EstadosVigNoVigVO estadoVigNoVig;
	
	public EstadosVigNoVigVO getEstadoVigNoVig() {
		return estadoVigNoVig;
	}


	public void setEstadoVigNoVig(EstadosVigNoVigVO estadoVigNoVig) {
		this.estadoVigNoVig = estadoVigNoVig;
	}


	public String getGrupos_nombre() {
		return grupos_nombre;
	}


	public void setGrupos_nombre(String grupos_nombre) {
		this.grupos_nombre = grupos_nombre;
	}


	public String getEmpresas_id() {
		return empresas_id;
	}


	public void setEmpresas_id(String empresas_id) {
		this.empresas_id = empresas_id;
	}


	public String getEmpresas_nombrenof() {
		return empresas_nombrenof;
	}


	public void setEmpresas_nombrenof(String empresas_nombrenof) {
		this.empresas_nombrenof = empresas_nombrenof;
	}


	public String getEmpresas_razonsocial() {
		return empresas_razonsocial;
	}


	public void setEmpresas_razonsocial(String empresas_razonsocial) {
		this.empresas_razonsocial = empresas_razonsocial;
	}


	public String getEmpresas_rut() {
		return empresas_rut;
	}


	public void setEmpresas_rut(String empresas_rut) {
		this.empresas_rut = empresas_rut;
	}


	public EmpresaVO() {
		super();
		
	}

}
