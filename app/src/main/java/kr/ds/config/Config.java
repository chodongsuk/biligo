package kr.ds.config;

import android.content.Context;

public class Config {
	public Context mContext;
	
	public static String URL = "http://biligo1.cafe24.com/";
	public static String URL_XML = "json/";
	public static String MAIN = "main.php";
	public static String EVENT = "event.php";
	public static String EVENTSAVE = "event_save.php";
	public static String GOOD = "good.php";
	public static String GOODSAVE = "good_save.php";

	public static String SHOP = "shop.php";
	public static String BOARD = "board.php";
	public static String CATEGORY = "category.php";

	public static String WEB1 = "html1.php";
	public static String WEB2 = "html2.php";





	public static String FILEFOLDER = "";// 서버파일폴더
	public static String GCM = "gcm.php";

	public Config(Context context) {
		mContext = context;
		FILEFOLDER = mContext.getExternalFilesDir(null).getAbsolutePath() + "/capture/";// 저장폴더
	}



	 

}
