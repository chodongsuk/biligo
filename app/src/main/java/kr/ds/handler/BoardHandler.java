package kr.ds.handler;

import android.os.Parcel;
import android.os.Parcelable;

public class BoardHandler {

	private String uid;
	private String subject;
	private String content;
	private String image;
	private String regdate;
	private Boolean layout;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public Boolean getLayout() {

		return layout;
	}

	public void setLayout(Boolean layout) {
		this.layout = layout;
	}
}
