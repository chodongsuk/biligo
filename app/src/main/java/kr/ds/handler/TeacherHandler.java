package kr.ds.handler;

import android.os.Parcel;
import android.os.Parcelable;

public class TeacherHandler implements Parcelable {

	private String td_uid;
	private String cd_code;
	private String td_name;
	private String td_title;
	private String td_type;
	private String td_image;
	private String td_content;
	private String td_regdate;
	private String sub_images;

	public TeacherHandler() {
		// TODO Auto-generated constructor stub

	}
	public TeacherHandler(Parcel src) {
		// TODO Auto-generated constructor stub
		this.td_uid = src.readString();
		this.cd_code = src.readString();
		this.td_name = src.readString();
		this.td_title = src.readString();
		this.td_type = src.readString();
		this.td_image = src.readString();
		this.td_content = src.readString();
		this.td_regdate = src.readString();
		this.sub_images = src.readString();

	}

	public String getTd_uid() {
		return td_uid;
	}

	public void setTd_uid(String td_uid) {
		this.td_uid = td_uid;
	}

	public String getCd_code() {
		return cd_code;
	}

	public void setCd_code(String cd_code) {
		this.cd_code = cd_code;
	}

	public String getTd_name() {
		return td_name;
	}

	public void setTd_name(String td_name) {
		this.td_name = td_name;
	}

	public String getTd_title() {
		return td_title;
	}

	public void setTd_title(String td_title) {
		this.td_title = td_title;
	}

	public String getTd_type() {
		return td_type;
	}

	public void setTd_type(String td_type) {
		this.td_type = td_type;
	}

	public String getTd_image() {
		return td_image;
	}

	public void setTd_image(String td_image) {
		this.td_image = td_image;
	}

	public String getTd_content() {
		return td_content;
	}

	public void setTd_content(String td_content) {
		this.td_content = td_content;
	}

	public String getTd_regdate() {
		return td_regdate;
	}

	public void setTd_regdate(String td_regdate) {
		this.td_regdate = td_regdate;
	}

	public String getSub_images() {
		return sub_images;
	}

	public void setSub_images(String sub_images) {
		this.sub_images = sub_images;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(this.td_uid);
		dest.writeString(this.cd_code);
		dest.writeString(this.td_name);
		dest.writeString(this.td_title);
		dest.writeString(this.td_type);
		dest.writeString(this.td_image);
		dest.writeString(this.td_content);
		dest.writeString(this.td_regdate);
		dest.writeString(this.sub_images);

	}

	public static final Creator CREATOR = new Creator() { //데이터 가져오기

		@Override
		public Object createFromParcel(Parcel in) {
			// TODO Auto-generated method stub
			return new TeacherHandler(in);
		}
		@Override
		public Object[] newArray(int size) {
			// TODO Auto-generated method stub
			return new TeacherHandler[size];
		}
	};
}
