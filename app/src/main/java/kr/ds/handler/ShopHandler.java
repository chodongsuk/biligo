package kr.ds.handler;

import android.os.Parcel;
import android.os.Parcelable;

public class ShopHandler implements Parcelable {

	private String sd_uid;
	private String sd_title;
	private String sd_link;
	private String sd_image;
	private String sd_regdate;


	public ShopHandler() {
		// TODO Auto-generated constructor stub

	}
	public ShopHandler(Parcel src) {
		// TODO Auto-generated constructor stub
		this.sd_uid = src.readString();
		this.sd_title = src.readString();
		this.sd_link = src.readString();
		this.sd_image = src.readString();
		this.sd_regdate = src.readString();

	}

	public String getSd_uid() {
		return sd_uid;
	}

	public void setSd_uid(String sd_uid) {
		this.sd_uid = sd_uid;
	}

	public String getSd_title() {
		return sd_title;
	}

	public void setSd_title(String sd_title) {
		this.sd_title = sd_title;
	}

	public String getSd_link() {
		return sd_link;
	}

	public void setSd_link(String sd_link) {
		this.sd_link = sd_link;
	}

	public String getSd_image() {
		return sd_image;
	}

	public void setSd_image(String sd_image) {
		this.sd_image = sd_image;
	}

	public String getSd_regdate() {
		return sd_regdate;
	}

	public void setSd_regdate(String sd_regdate) {
		this.sd_regdate = sd_regdate;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(this.sd_uid);
		dest.writeString(this.sd_title);
		dest.writeString(this.sd_link);
		dest.writeString(this.sd_image);
		dest.writeString(this.sd_regdate);

	}

	public static final Creator CREATOR = new Creator() { //데이터 가져오기

		@Override
		public Object createFromParcel(Parcel in) {
			// TODO Auto-generated method stub
			return new ShopHandler(in);
		}
		@Override
		public Object[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ShopHandler[size];
		}
	};
}
