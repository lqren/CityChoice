package com.example.administrator.stethodemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.administrator.stethodemo.R;
import com.example.administrator.stethodemo.adapter.CityListAdapter;
import com.example.administrator.stethodemo.adapter.GroupSuspensionDividerItemDecoration;
import com.example.administrator.stethodemo.bean.CityEntity;
import com.example.administrator.stethodemo.view.SideBarView;

import java.util.ArrayList;
import java.util.List;

public class ChoiceCountryActivity extends AppCompatActivity {

    private RecyclerView mRv;
    private List<CityEntity> mList;
    private SideBarView mSideBarView;
    private String[] data = {"★", "A", "B", "C", "D", "E", "F", "G"};
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mRv = (RecyclerView) findViewById(R.id.rv);
        mSideBarView = (SideBarView) findViewById(R.id.sbv);
        initData();
        initEvent();
        mRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRv.addItemDecoration(new GroupSuspensionDividerItemDecoration(this, mList));
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRv.setLayoutManager(mLinearLayoutManager);
        CityListAdapter myAdapter = new CityListAdapter(mList, this, new CityListAdapter.ItemOnClickistener() {
            @Override
            public void onClick(List<CityEntity> mList, int pos) {
                Toast.makeText(ChoiceCountryActivity.this, "当前选择城市:" + mList.get(pos).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        mRv.setAdapter(myAdapter);

    }

    private void initEvent() {
        mSideBarView.setIndexDatas(data);
        mSideBarView.setOnTouchListeber(new SideBarView.OnTouchListeber() {
            @Override
            public void onTouch(String[] mLettersShow, int pos) {
                for (int i = 0; i < mList.size(); i++) {
                    if (pos == 0) {
                        mLinearLayoutManager.scrollToPositionWithOffset(0, 45);
                        return;
                    } else {
                        if (TextUtils.equals(mList.get(i).getFirstWord(), mLettersShow[pos])) {
                            mLinearLayoutManager.scrollToPositionWithOffset(i, 45);
                            return;
                        }
                    }
                }

            }

            @Override
            public void dismiss() {

            }
        });
    }

    private void initData() {
        if (mList == null) {
            mList = new ArrayList<>();
        }

        mList.add(new CityEntity("中国大陆", "常用"));
        mList.add(new CityEntity("中国香港", "常用"));
        mList.add(new CityEntity("马来西亚", "常用"));


        mList.add(new CityEntity("阿联酋", "A"));
        mList.add(new CityEntity("阿根廷", "A"));
        mList.add(new CityEntity("奥地利", "A"));
        mList.add(new CityEntity("澳大利亚", "A"));

        mList.add(new CityEntity("比利时", "B"));
        mList.add(new CityEntity("保加利亚", "B"));
        mList.add(new CityEntity("巴西", "B"));
        mList.add(new CityEntity("巴哈马", "B"));


        mList.add(new CityEntity("丹麦", "D"));
        mList.add(new CityEntity("德国", "D"));


        mList.add(new CityEntity("深圳市3", "E"));
        mList.add(new CityEntity("东莞市3", "E"));
        mList.add(new CityEntity("揭阳市3", "E"));
        mList.add(new CityEntity("佛山市3", "E"));

        mList.add(new CityEntity("深圳市4", "F"));
        mList.add(new CityEntity("东莞市4", "F"));
        mList.add(new CityEntity("揭阳市4", "F"));
        mList.add(new CityEntity("佛山市4", "F"));

        mList.add(new CityEntity("深圳市5", "G"));
        mList.add(new CityEntity("东莞市5", "G"));
        mList.add(new CityEntity("揭阳市5", "G"));
        mList.add(new CityEntity("佛山市5", "G"));

        mList.add(new CityEntity("深圳市6", "H"));
        mList.add(new CityEntity("东莞市6", "H"));
        mList.add(new CityEntity("揭阳市6", "H"));
        mList.add(new CityEntity("佛山市6", "H"));

        mList.add(new CityEntity("深圳市7", "I"));
        mList.add(new CityEntity("东莞市7", "I"));
        mList.add(new CityEntity("揭阳市7", "I"));
        mList.add(new CityEntity("佛山市7", "I"));

        mList.add(new CityEntity("深圳市8", "J"));
        mList.add(new CityEntity("东莞市8", "J"));
        mList.add(new CityEntity("揭阳市8", "J"));
        mList.add(new CityEntity("佛山市8", "J"));

        mList.add(new CityEntity("深圳市9", "K"));
        mList.add(new CityEntity("东莞市9", "K"));
        mList.add(new CityEntity("揭阳市9", "K"));
        mList.add(new CityEntity("佛山市9", "K"));

        mList.add(new CityEntity("深圳市10", "L"));
        mList.add(new CityEntity("东莞市10", "L"));
        mList.add(new CityEntity("揭阳市10", "L"));
        mList.add(new CityEntity("佛山市10", "L"));
    }
}
