package VO;

public class PeriodoTcVO {
	private EstadosVigNoVigVO estadoVigNoVig;
	private EmpresaVO empresa;
	private String id;
	private String fdesde;
	private String fhasta;
	private String correlativo;
	private String estado_periodo_id;
	
	public String getEstado_periodo_id() {
		return estado_periodo_id;
	}

	public void setEstado_periodo_id(String estado_periodo_id) {
		this.estado_periodo_id = estado_periodo_id;
	}

	public PeriodoTcVO() {
		super();
	}

	public EstadosVigNoVigVO getEstadoVigNoVig() {
		return estadoVigNoVig;
	}

	public EmpresaVO getEmpresa() {
		return empresa;
	}

	public String getId() {
		return id;
	}

	public String getFdesde() {
		return fdesde;
	}

	public String getFhasta() {
		return fhasta;
	}

	public String getCorrelativo() {
		return correlativo;
	}

	public void setEstadoVigNoVig(EstadosVigNoVigVO estadoVigNoVig) {
		this.estadoVigNoVig = estadoVigNoVig;
	}

	public void setEmpresa(EmpresaVO empresa) {
		this.empresa = empresa;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFdesde(String fdesde) {
		this.fdesde = fdesde;
	}

	public void setFhasta(String fhasta) {
		this.fhasta = fhasta;
	}

	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}
}
