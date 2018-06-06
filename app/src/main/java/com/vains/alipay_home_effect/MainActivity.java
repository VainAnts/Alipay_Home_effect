package com.vains.alipay_home_effect;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vains.alipay_home_effect.widgets.APBarView;
import com.vains.alipay_home_effect.widgets.APHeaderView;
import com.vains.alipay_home_effect.widgets.APSnapView;
import com.vains.alipay_home_effect.widgets.CommonListDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener,View.OnClickListener{

    @Bind(R.id.home_top_tv_saoma)
    TextView homeTopTvSaoma;
    @Bind(R.id.home_top_tv_register)
    TextView homeTopTvRegister;
    @Bind(R.id.home_top_tv_zhuangxiang)
    TextView homeTopTvZhuangxiang;
    @Bind(R.id.home_top_tv_dengji)
    TextView homeTopTvDengji;
    @Bind(R.id.top_ly)
    LinearLayout topLy;
    @Bind(R.id.my_snap)
    APSnapView mySnap;
    @Bind(R.id.home_top_tv_saoma1)
    ImageButton homeTopTvSaoma1;
    @Bind(R.id.home_top_tv_register1)
    ImageButton homeTopTvRegister1;
    @Bind(R.id.home_top_tv_zhuangxiang1)
    ImageButton homeTopTvZhuangxiang1;
    @Bind(R.id.home_top_tv_dengji1)
    ImageButton homeTopTvDengji1;
    @Bind(R.id.top_bar_ly)
    LinearLayout topBarLy;
    @Bind(R.id.toolbar_menu)
    ImageButton toolbarMenu;
    @Bind(R.id.toobar_title)
    TextView toobarTitle;
    @Bind(R.id.toobar_right_btn)
    ImageButton toobarRightBtn;
    @Bind(R.id.toobar_right_tv)
    TextView toobarRightTv;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.my_bar)
    APBarView myBar;
    @Bind(R.id.home_header)
    APHeaderView homeHeader;
    @Bind(R.id.home_bottom_rcy)
    RecyclerView homeBottomRcy;
    @Bind(R.id.fragment_home_ly)
    CoordinatorLayout fragmentHomeLy;
    AlipayAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    @SuppressLint("RestrictedApi")
    private void initView() {
        ((MenuBuilder) mToolbar.getMenu()).setOptionalIconsVisible(true);
        //设置导航按钮的点击事件
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
        //生成选项菜单
        mToolbar.inflateMenu(R.menu.menu_main);
        setOnClickListener(topLy);
        setOnClickListener(topBarLy);
        toolbarMenu.setOnClickListener(this);
        //设置溢出菜单的icon，显示、隐藏溢出菜单弹出的窗口
        mToolbar.setOverflowIcon(getResources().getDrawable(R.drawable.more));
        mToolbar.setOnMenuItemClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homeBottomRcy.setLayoutManager(layoutManager);
        ArrayList<String> titles = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            titles.add("item " + i);
        }
        homeBottomRcy.setLayoutManager(layoutManager);
        mAdapter = new AlipayAdapter(titles);
        homeBottomRcy.setAdapter(mAdapter);
        homeBottomRcy.addItemDecoration(new CommonListDecoration());

    }
    public void setOnClickListener(ViewGroup container) {
        for (int i = 0; i < container.getChildCount(); i++) {
            container.getChildAt(i).setOnClickListener(this);
        }
    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_pop_1:
                Toast.makeText(this,"菜单1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.toolbar_pop_2:
                Toast.makeText(this,"菜单2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.toolbar_pop_3:
                Toast.makeText(this,"菜单3",Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_top_tv_saoma:
            case R.id.home_top_tv_saoma1:
                Toast.makeText(this,"扫码",Toast.LENGTH_SHORT).show();
                break;
            case R.id.home_top_tv_register:
            case R.id.home_top_tv_register1:
                Toast.makeText(this,"注册",Toast.LENGTH_SHORT).show();
                break;
            case R.id.home_top_tv_zhuangxiang:
            case R.id.home_top_tv_zhuangxiang1:
                Toast.makeText(this,"收钱",Toast.LENGTH_SHORT).show();
                break;
            case R.id.home_top_tv_dengji:
            case R.id.home_top_tv_dengji1:
                Toast.makeText(this,"卡包",Toast.LENGTH_SHORT).show();
                break;
            case R.id.toolbar_menu:
                mToolbar.showOverflowMenu();
                break;
        }
    }
    static class AlipayItemViewHolder extends RecyclerView.ViewHolder {

        TextView titleTv;

        public AlipayItemViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView;
        }
    }

    private static class AlipayAdapter extends RecyclerView.Adapter<AlipayItemViewHolder> {

        private List<String> mTitles;

        public AlipayAdapter(List<String> titles) {
            mTitles = titles;
        }

        @Override
        public AlipayItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new AlipayItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content, parent, false));
        }

        @Override
        public void onBindViewHolder(AlipayItemViewHolder holder, int position) {
            holder.titleTv.setText(mTitles.get(position));
        }

        @Override
        public int getItemCount() {
            return mTitles == null ? 0 : mTitles.size();
        }
    }
}
