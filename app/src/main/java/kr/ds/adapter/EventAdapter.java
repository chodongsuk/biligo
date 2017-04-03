package kr.ds.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import java.util.ArrayList;

import kr.com.biligo.R;
import kr.ds.handler.EventHandler;
import kr.ds.handler.GoodHandler;
import kr.ds.utils.DsObjectUtils;
import kr.ds.utils.ScreenUtils;

/**
 * Created by Administrator on 2016-03-21.
 */
public class EventAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<EventHandler> mData;
    private LayoutInflater mInflater;
    private final ImageLoader imageDownloader = ImageLoader.getInstance();


    public EventAdapter(Context context, ArrayList<EventHandler> data ) {
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
            convertView = mInflater.inflate(R.layout.list_fragment_item,null);
            holder.cardView = (CardView)convertView.findViewById(R.id.card_view);
            holder.imageView = (ImageView)convertView.findViewById(R.id.imageView);
            holder.textViewName = (TextView) convertView.findViewById(R.id.textView_name);
            holder.frameLayout = (FrameLayout) convertView.findViewById(R.id.frameLayout);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(!DsObjectUtils.getInstance(mContext).isEmpty(mData.get(position).getEd_image())) {
            Glide.with(mContext)
                    .load(mData.get(position).getEd_image())
                    .thumbnail(0.5f)
                    .override(500, 500)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(new ImageViewTarget<GlideDrawable>(holder.imageView) {
                        @Override
                        protected void setResource(GlideDrawable resource) {
                            holder.imageView.setVisibility(View.VISIBLE);
                            int width = getWidth() - ScreenUtils.getInstacne().getPixelFromDPI(mContext, 12);
                            int height = (int) (resource.getIntrinsicHeight() * (width / (float) resource.getIntrinsicWidth()));
                            holder.imageView.setLayoutParams(new FrameLayout.LayoutParams(width, height));
                            holder.imageView.setImageDrawable(resource);
                        }


                    });
        }else{
            holder.imageView.setVisibility(View.GONE);
        }

//        if(!DsObjectUtils.getInstance(mContext).isEmpty(mData.get(position).getEd_image())){
//            imageDownloader.displayImage(mData.get(position).getEd_image(), holder.imageView, new ImageLoadingListener() {
//
//                @Override
//                public void onLoadingStarted(String arg0, View arg1) {
//                    // TODO Auto-generated method stub
//                    holder.imageView.setVisibility(View.GONE);
//                }
//
//                @Override
//                public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
//                    // TODO Auto-generated method stub
//                    holder.imageView.setVisibility(View.GONE);
//                }
//
//                @Override
//                public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
//                    // TODO Auto-generated method stub
//                    int width = getWidth();
//                    int height = (int) (arg2.getHeight() * (width / (float) arg2.getWidth()));
//                    holder.frameLayout.setLayoutParams(new LinearLayout.LayoutParams(width, height));
//                    holder.imageView.setLayoutParams(new FrameLayout.LayoutParams(width, height));
//                    holder.imageView.setVisibility(View.VISIBLE);
//                }
//
//                @Override
//                public void onLoadingCancelled(String arg0, View arg1) {
//                    // TODO Auto-generated method stub
//                    holder.imageView.setVisibility(View.GONE);
//                }
//            });
//        }else{
//            holder.imageView.setVisibility(View.GONE);
//        }



        if(!DsObjectUtils.getInstance(mContext).isEmpty(mData.get(position).getEd_name())){
            holder.textViewName.setText(mData.get(position).getEd_name());
        }else{
            holder.textViewName.setText("");
        }



        return convertView;
    }

    class ViewHolder {
        CardView cardView;
        ImageView imageView;
        LinearLayout linearLayoutReservation;
        TextView textViewName;
        FrameLayout frameLayout;

    }
}