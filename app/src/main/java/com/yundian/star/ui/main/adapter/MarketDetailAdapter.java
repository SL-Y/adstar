package com.yundian.star.ui.main.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.yundian.star.R;
import com.yundian.star.base.ListBaseAdapter;
import com.yundian.star.base.SuperViewHolder;
import com.yundian.star.been.StarListbeen;
import com.yundian.star.utils.ImageLoaderUtils;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2017/5/15.
 */

public class MarketDetailAdapter extends ListBaseAdapter<StarListbeen.SymbolInfoBean> {
    public MarketDetailAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_market_detail;
    }


    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        StarListbeen.SymbolInfoBean item = mDataList.get(position);
        ImageView imageView = holder.getView(R.id.image_star);
        TextView tv_name = holder.getView(R.id.tv_name);
        TextView tv_code = holder.getView(R.id.tv_code);
        TextView tv_price = holder.getView(R.id.tv_price);
        TextView tv_updown = holder.getView(R.id.tv_updown);
        ImageLoaderUtils.displaySmallPhoto(mContext,imageView,item.getPic());
        tv_name.setText(item.getName());
        tv_code.setText(item.getSymbol());
        tv_price.setText(String.format("%.2f",item.getCurrentPrice()));
        if (item.getPchg()>0){
            tv_updown.setBackgroundResource(R.drawable.bg_red_radius);
            tv_price.setTextColor(mContext.getResources().getColor(R.color.color_CB4232));
        }else if(item.getPchg()<0) {
            tv_updown.setBackgroundResource(R.drawable.bg_green_radius);
            tv_price.setTextColor(mContext.getResources().getColor(R.color.color_18B03F));
        }else if (item.getPchg()==0){
            tv_updown.setBackgroundResource(R.drawable.bg_green_black);
            tv_price.setTextColor(mContext.getResources().getColor(R.color.color_black_333333));
        }
        DecimalFormat format = new DecimalFormat("0.00%");
        String updown = format.format(item.getPchg());
        tv_updown.setText(updown);
    }
}
