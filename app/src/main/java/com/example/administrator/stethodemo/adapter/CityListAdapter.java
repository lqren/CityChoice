package com.example.administrator.stethodemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.stethodemo.bean.CityEntity;
import com.example.administrator.stethodemo.R;

import java.util.List;

import static android.support.v7.widget.RecyclerView.Adapter;
import static android.support.v7.widget.RecyclerView.ViewHolder;
import static android.view.View.inflate;

/**
 * 作  者: roqy
 * 包  名: com.example.administrator.stethodemo
 * 日  期: 2018/7/18
 * 描  述:
 */

public class CityListAdapter extends Adapter {
    private List<CityEntity> mList;
    private Context mContext;
    public CityListAdapter(List<CityEntity> list, Context context, ItemOnClickistener itemOnClickistener) {
        mList = list;
        mContext = context;
        mItemOnClickistener = itemOnClickistener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =inflate(mContext, R.layout.item_city, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ((MyViewHolder)holder).setData(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemOnClickistener.onClick(mList,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mList.size() > 0) {
            return mList.size();
        }
        return 0;
    }

    class MyViewHolder extends ViewHolder{
        TextView tvCityName;
        TextView tvCityNum;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvCityName = (TextView) itemView.findViewById(R.id.city_name_tv);
            tvCityNum = (TextView) itemView.findViewById(R.id.city_num_tv);

        }

        public void setData(int position){

            tvCityName.setText(mList.get(position).getName());
            tvCityNum.setText("+86");
        }
    }

  public  interface ItemOnClickistener{
        void onClick(List<CityEntity> mList, int pos);
    }
    public ItemOnClickistener mItemOnClickistener;

}
