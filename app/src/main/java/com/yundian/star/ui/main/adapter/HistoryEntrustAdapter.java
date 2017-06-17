package com.yundian.star.ui.main.adapter;

import android.content.Context;
import android.widget.TextView;

import com.yundian.star.R;
import com.yundian.star.base.ListBaseAdapter;
import com.yundian.star.base.SuperViewHolder;
import com.yundian.star.been.TodayEntrustReturnBean;
import com.yundian.star.greendao.GreenDaoManager;
import com.yundian.star.greendao.StarInfo;
import com.yundian.star.utils.BuyHandleStatuUtils;
import com.yundian.star.utils.TimeUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/5/25.
 */

public class HistoryEntrustAdapter extends ListBaseAdapter<TodayEntrustReturnBean> {
    public HistoryEntrustAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_history_entrust;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        TodayEntrustReturnBean bean = mDataList.get(position);
        TextView name = holder.getView(R.id.tv_name);
        TextView price = holder.getView(R.id.tv_price);
        TextView tv_entrust_num = holder.getView(R.id.tv_entrust_num);
        TextView tv_content_ing = holder.getView(R.id.tv_content_ing);
        TextView tv_code = holder.getView(R.id.tv_code);
        TextView tv_time = holder.getView(R.id.tv_time);
        TextView tv_bargain_num = holder.getView(R.id.tv_bargain_num);
        TextView tv_content_ed = holder.getView(R.id.tv_content_ed);

        tv_time.setText(TimeUtil.getHourMinuteSecond(bean.getPositionTime() * 1000));
        price.setText(bean.getOpenPrice() + "");
        List<StarInfo> starInfos = GreenDaoManager.getInstance().queryLove(bean.getSymbol());
        if (starInfos != null && starInfos.size() > 0) {
            name.setText(starInfos.get(0).getName());
        }
        price.setText(bean.getOpenPrice() + "");
        tv_entrust_num.setText(bean.getRtAmount() + "");
        tv_bargain_num.setText(bean.getAmount() + "");
        tv_code.setText(bean.getSymbol());
        if (bean.getHandle() == 3) {
            tv_content_ing.setText("购买");
        } else {
            tv_content_ing.setText("委托");
        }

        tv_content_ed.setText(BuyHandleStatuUtils.getHandleStatu(bean.getHandle()));
    }

}
