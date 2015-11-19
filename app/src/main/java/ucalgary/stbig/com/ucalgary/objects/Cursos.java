package ucalgary.stbig.com.ucalgary.objects;

import java.io.Serializable;

public class Cursos implements Serializable {
	
	public String nid;
	public String title;
	public String descripcion;
	public String code;
	public String duracion;
	public String inicio;
	public String fin;
	public String contents;
	public String state_sus;
	public String category_nid;


	
	public Cursos(){
		nid="";
		title="";
		descripcion="";
		code="";
		duracion="";
		inicio="";
		fin="";
		state_sus="";
		contents="";
		category_nid="";
	}
	
	public Cursos(String nid, String title, String descripcion,String code,String duracion, String inicio, String fin, String contents, String state_sus,String category_nid){
		this.nid=nid;
		this.title=title;
		this.descripcion=descripcion;
		this.code=code;
		this.duracion=duracion;
		this.inicio=inicio;
		this.fin=fin;
		this.contents=contents;
		this.state_sus=state_sus;
		this.category_nid=category_nid;
	}	
	
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getFin() {
		return fin;
	}

	public void setFin(String fin) {
		this.fin = fin;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getState_sus() {
		return state_sus;
	}

	public void setState_sus(String state_sus) {
		this.state_sus = state_sus;
	}

	public String getCategory_nid() {
		return category_nid;
	}

	public void setCategory_nid(String category_nid) {
		this.category_nid = category_nid;
	}
}
