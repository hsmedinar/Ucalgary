package ucalgary.stbig.com.ucalgary.objects;

import java.io.Serializable;

public class SSC implements Serializable {
	public String Title;
	public String Content;
	public String Url;
	public String type;
	public String Date;
	public String Time_Stamp;
	
	public SSC() {
		super();
		Title = "";
		Content = "";
		Url = "";
		Date="";
		type="";
	}

	public SSC(String title, String content, String url) {
		super();
		Title = title;
		Content = content;
		Url = url;
	}
	
	public SSC(String title, String content, String url,String date,String type) {
		super();
		Title = title;
		Content = content;
		Url = url;
		this.Date=date;
		this.type = type;
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
	
	public String getUrl() {
		return Url;
	}
	
	public void setUrl(String url) {
		Url = url;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}	


	public String getTime_Stamp() {
		return Time_Stamp;
	}

	public void setTime_Stamp(String time_Stamp) {
		Time_Stamp = time_Stamp;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public enum SCC_Type {
		 facebook, twitter, blog
	}

}
