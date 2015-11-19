package ucalgary.stbig.com.ucalgary.objects;

import java.io.Serializable;

public class News implements Serializable {
	public String Title;
	public String Content;
	public String Date;
	public String Field_Browser;
	
	
	
	public News() {
		super();
		Title = "";
		Content = "";
		Date = "";
		Field_Browser="";
	}
	public News(String title, String content, String date,String field_browser) {
		super();
		Title = title;
		Content = content;
		Date = date;
		Field_Browser = field_browser;
	}

	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getField_Browser() {
		return Field_Browser;
	}
	public void setField_Browser(String field_Browser) {
		Field_Browser = field_Browser;
	}
	
	

}
