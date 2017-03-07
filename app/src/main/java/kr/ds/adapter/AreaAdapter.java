package kr.ds.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.support.v7.widget.CardView;
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
import kr.ds.handler.AreaHandler;
import kr.ds.utils.DsObjectUtils;

/**
 * Created by Administrator on 2016-03-21.
 */
public class AreaAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<AreaHandler> mData;
    private LayoutInflater mInflater;


    public AreaAdapter(Context context, ArrayList<AreaHandler> data ) {
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
            convertView = mInflater.inflate(R.layout.grid_fragment_item,null);
            holder.textViewName = (TextView) convertView.findViewById(R.id.textView_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(!DsObjectUtils.getInstance(mContext).isEmpty(mData.get(position).getName())){
            holder.textViewName.setText(mData.get(position).getName());
        }else{
            holder.textViewName.setText("");
        }

        return convertView;
    }

    class ViewHolder {
        TextView textViewName;

    }
}