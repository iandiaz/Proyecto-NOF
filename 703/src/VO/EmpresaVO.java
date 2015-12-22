package VO;

public class EmpresaVO {
	private String empresas_nombrenof;
	private String empresas_razonsocial;
	private String empresas_rut;
	private String empresas_id;
	private String grupos_nombre;
	private String empresas_escliente;
	private EstadosVigNoVigVO estadoVigNoVig;
	private String empresas_relacionada;


	public String getEmpresas_relacionada() {
		return empresas_relacionada;
	}


	public void setEmpresas_relacionada(String empresas_relacionada) {
		this.empresas_relacionada = empresas_relacionada;
	}


	public EstadosVigNoVigVO getEstadoVigNoVig() {
		return estadoVigNoVig;
	}


	public void setEstadoVigNoVig(EstadosVigNoVigVO estadoVigNoVig) {
		this.estadoVigNoVig = estadoVigNoVig;
	}


	public String getEmpresas_escliente() {
		return empresas_escliente;
	}


	public void setEmpresas_escliente(String empresas_escliente) {
		this.empresas_escliente = empresas_escliente;
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
