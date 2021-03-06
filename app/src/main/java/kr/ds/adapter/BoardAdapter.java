package kr.ds.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import kr.com.biligo.R;
import kr.ds.handler.BoardHandler;
import kr.ds.utils.DsObjectUtils;


public class BoardAdapter extends BaseAdapter{
	private Context mContext;
	private ArrayList<BoardHandler> mData;
	private LayoutInflater mInflater;

	public BoardAdapter(Context context, ArrayList<BoardHandler> data){
		mContext = context;
		mData = data;
		mInflater = LayoutInflater.from(mContext);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.board_frament_item, null);
			holder = new ViewHolder();
			holder.mLinearLayoutContent = (LinearLayout) convertView.findViewById(R.id.linearLayout_content);
			holder.mImageViewOnOff = (ImageView) convertView.findViewById(R.id.imageView_on_off);
			holder.mTextViewTitle = (TextView) convertView.findViewById(R.id.textView_title);
			holder.mTextViewRegdate = (TextView) convertView.findViewById(R.id.textView_regdate);
			holder.mTextViewContent = (TextView) convertView.findViewById(R.id.textView_content);
			holder.mTextViewRegdate2 = (TextView) convertView.findViewById(R.id.textView_regdate2);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if(!DsObjectUtils.getInstance(mContext).isEmpty(mData.get(position).getSubject())){
			holder.mTextViewTitle.setText(mData.get(position).getSubject());
		}else{
			holder.mTextViewTitle.setText("");
		}
		if(!DsObjectUtils.getInstance(mContext).isEmpty(mData.get(position).getRegdate())){
			holder.mTextViewRegdate.setText(mData.get(position).getRegdate().substring(0,10));
		}else{
			holder.mTextViewRegdate.setText("");
		}
		if(!DsObjectUtils.getInstance(mContext).isEmpty(mData.get(position).getContent())){
			holder.mTextViewContent.setText(Html.fromHtml(mData.get(position).getContent()));
		}else{
			holder.mTextViewContent.setText("");
		}
		if(!DsObjectUtils.getInstance(mContext).isEmpty(mData.get(position).getRegdate())){
			holder.mTextViewRegdate2.setText(mData.get(position).getRegdate());
		}else{
			holder.mTextViewRegdate2.setText("");
		}
		if(mData.get(position).getLayout() == true){
			holder.mLinearLayoutContent.setVisibility(View.VISIBLE);
			holder.mImageViewOnOff.setBackgroundResource(R.mipmap.gongi_on);
		}else{
			holder.mLinearLayoutContent.setVisibility(View.GONE);
			holder.mImageViewOnOff.setBackgroundResource(R.mipmap.gongi_off);
		}
		return convertView;

	}
	class ViewHolder {
		ImageView mImageViewOnOff;
		TextView mTextViewTitle,mTextViewRegdate,mTextViewContent,mTextViewRegdate2;
		LinearLayout mLinearLayoutContent;
	}
}
