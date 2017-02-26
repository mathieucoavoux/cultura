package fr.comprehensiveit.cultura.model;

public class GoogleBook {
	public String kind;
	public String id;
	public String selfLink;
	public String genField;
	public String title;
	
	/*
	public GoogleBook() {
		
	}
	
	public GoogleBook(String id, String title) {
		
	}
	*/
	
	public String getKind() {
		return kind;
	}
	
	
	public String getId() {
		return id;
	}
	
	public GoogleBook setId(String id) {
		this.id = id;
		return this;
	}
	
	public String getTitle() {
		return title;
	}
	
	public GoogleBook setTitle(String title) {
		this.title = title;
		return this;
	}
	
	public String getSelfLink() {
		return selfLink;
	}
	
	public String getGenField() {
		return genField;
	}
	
}
