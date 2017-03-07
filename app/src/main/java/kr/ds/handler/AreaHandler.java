package kr.ds.handler;

import android.os.Parcel;
import android.os.Parcelable;

public class AreaHandler implements Parcelable {

	private String name;
	private String code;


	public AreaHandler() {
		// TODO Auto-generated constructor stub

	}
	public AreaHandler(Parcel src) {
		// TODO Auto-generated constructor stub
		this.name = src.readString();
		this.code = src.readString();


	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(this.name);
		dest.writeString(this.code);

	}

	public static final Creator CREATOR = new Creator() { //데이터 가져오기

		@Override
		public Object createFromParcel(Parcel in) {
			// TODO Auto-generated method stub
			return new AreaHandler(in);
		}
		@Override
		public Object[] newArray(int size) {
			// TODO Auto-generated method stub
			return new AreaHandler[size];
		}
	};
}
