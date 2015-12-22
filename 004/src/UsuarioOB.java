
public class UsuarioOB {
	
	private String Usuarios_id;
	private String Usuarios_nombre;
	private String Usuarios_email;
	private String Usuarios_login;
	private String Usuarios_pass;
	private String tipo_usu_id;
	
	public UsuarioOB(){
		
	}

	public String getTipo_usu_id() {
		return tipo_usu_id;
	}

	public void setTipo_usu_id(String tipo_usu_id) {
		this.tipo_usu_id = tipo_usu_id;
	}

	public String getUsuarios_id() {
		return Usuarios_id;
	}

	public void setUsuarios_id(String usuarios_id) {
		Usuarios_id = usuarios_id;
	}

	public String getUsuarios_nombre() {
		return Usuarios_nombre;
	}

	public void setUsuarios_nombre(String usuarios_nombre) {
		Usuarios_nombre = usuarios_nombre;
	}

	public String getUsuarios_email() {
		return Usuarios_email;
	}

	public void setUsuarios_email(String usuarios_email) {
		Usuarios_email = usuarios_email;
	}

	public String getUsuarios_login() {
		return Usuarios_login;
	}

	public void setUsuarios_login(String usuarios_login) {
		Usuarios_login = usuarios_login;
	}

	public String getUsuarios_pass() {
		return Usuarios_pass;
	}

	public void setUsuarios_pass(String usuarios_pass) {
		Usuarios_pass = usuarios_pass;
	}

}
