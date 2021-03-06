package kr.ds.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import java.util.ArrayList;

import kr.com.biligo.R;
import kr.ds.handler.TeacherHandler;
import kr.ds.utils.DsObjectUtils;

/**
 * Created by Administrator on 2016-03-21.
 */
public class BounceAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<TeacherHandler> mData;
    private LayoutInflater mInflater;
    private final ImageLoader imageDownloader = ImageLoader.getInstance();


    public BounceAdapter(Context context, ArrayList<TeacherHandler> data ) {
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
    public int getWidth(){
        Point p = new Point();
        p.x = mContext.getResources().getDisplayMetrics().widthPixels;
        return p.x;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_bounce_fragment_item,null);
            holder.imageView = (ImageView)convertView.findViewById(R.id.imageView);
            holder.textViewName = (TextView) convertView.findViewById(R.id.textView_name);
            holder.textViewTitle = (TextView) convertView.findViewById(R.id.textView_title);
            holder.textViewType = (TextView) convertView.findViewById(R.id.textView_type);
            holder.linearLayoutType = (LinearLayout)convertView.findViewById(R.id.linearLayout_type);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(!DsObjectUtils.getInstance(mContext).isEmpty(mData.get(position).getTd_image())){
            imageDownloader.displayImage(mData.get(position).getTd_image(), holder.imageView, new ImageLoadingListener() {

                @Override
                public void onLoadingStarted(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                    holder.imageView.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
                    // TODO Auto-generated method stub
                    holder.imageView.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
                    // TODO Auto-generated method stub
                    holder.imageView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingCancelled(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                    holder.imageView.setVisibility(View.GONE);
                }
            });
        }else{
            holder.imageView.setVisibility(View.GONE);
        }


        if(!DsObjectUtils.getInstance(mContext).isEmpty(mData.get(position).getTd_name())){
            holder.textViewName.setText(mData.get(position).getTd_name());
        }else{
            holder.textViewName.setText("");
        }
        if(!DsObjectUtils.getInstance(mContext).isEmpty(mData.get(position).getTd_title())){
            holder.textViewTitle.setText(mData.get(position).getTd_title());
        }else{
            holder.textViewTitle.setText("");
        }
        if(!DsObjectUtils.getInstance(mContext).isEmpty(mData.get(position).getTd_type())){
            //holder.textViewType.setText(mData.get(position).getTd_type());
            holder.linearLayoutType.removeAllViews();
            ImageView imageView[] = new ImageView[Integer.parseInt(mData.get(position).getTd_type())];
            for(int i=0; i<Integer.parseInt(mData.get(position).getTd_type()); i++) {
                imageView[i] = new ImageView(mContext);
                imageView[i].setImageResource(R.mipmap.type);
                holder.linearLayoutType.addView(imageView[i]);
            }
        }else{
            //holder.textViewType.setText("");
        }

        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView textViewName;
        TextView textViewType;
        TextView textViewTitle;
        LinearLayout linearLayoutType;


    }
}