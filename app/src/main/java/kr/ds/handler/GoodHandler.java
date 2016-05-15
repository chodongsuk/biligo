package kr.ds.handler;

import android.os.Parcel;
import android.os.Parcelable;

public class GoodHandler implements Parcelable {

	private String gd_uid;
	private String cd_code;
	private String gd_name;
	private String gd_total;
	private String gd_image;
	private String gd_content;
	private String gd_regdate;
	private String sub_images;

	public GoodHandler() {
		// TODO Auto-generated constructor stub

	}
	public GoodHandler(Parcel src) {
		// TODO Auto-generated constructor stub
		this.gd_uid = src.readString();
		this.cd_code = src.readString();
		this.gd_name = src.readString();
		this.gd_total = src.readString();
		this.gd_image = src.readString();
		this.gd_content = src.readString();
		this.gd_regdate = src.readString();
		this.sub_images = src.readString();

	}

	public String getGd_uid() {
		return gd_uid;
	}

	public void setGd_uid(String gd_uid) {
		this.gd_uid = gd_uid;
	}

	public String getCd_code() {
		return cd_code;
	}

	public void setCd_code(String cd_code) {
		this.cd_code = cd_code;
	}

	public String getGd_name() {
		return gd_name;
	}

	public void setGd_name(String gd_name) {
		this.gd_name = gd_name;
	}

	public String getGd_total() {
		return gd_total;
	}

	public void setGd_total(String gd_total) {
		this.gd_total = gd_total;
	}

	public String getGd_image() {
		return gd_image;
	}

	public void setGd_image(String gd_image) {
		this.gd_image = gd_image;
	}

	public String getGd_content() {
		return gd_content;
	}

	public void setGd_content(String gd_content) {
		this.gd_content = gd_content;
	}

	public String getGd_regdate() {
		return gd_regdate;
	}

	public void setGd_regdate(String gd_regdate) {
		this.gd_regdate = gd_regdate;
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
		dest.writeString(this.gd_uid);
		dest.writeString(this.cd_code);
		dest.writeString(this.gd_name);
		dest.writeString(this.gd_total);
		dest.writeString(this.gd_image);
		dest.writeString(this.gd_content);
		dest.writeString(this.gd_regdate);
		dest.writeString(this.sub_images);

	}

	public static final Creator CREATOR = new Creator() { //데이터 가져오기

		@Override
		public Object createFromParcel(Parcel in) {
			// TODO Auto-generated method stub
			return new GoodHandler(in);
		}
		@Override
		public Object[] newArray(int size) {
			// TODO Auto-generated method stub
			return new GoodHandler[size];
		}
	};
}
