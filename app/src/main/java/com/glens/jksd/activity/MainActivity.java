package com.glens.jksd.activity;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.glens.jksd.R;
import com.glens.jksd.base.BaseFragment;
import com.glens.jksd.fragment.ContactsFragment;
import com.glens.jksd.fragment.MainFragment;
import com.glens.jksd.fragment.MessageFragment;
import com.glens.jksd.fragment.MineFragment;
import com.glens.jksd.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private RadioGroup mRg_main;
    private RadioButton mRbMain;
    private RadioButton mRbMessage;
    private RadioButton mRbContacts;
    private RadioButton mRbMine;
    private List<BaseFragment> mBaseFragment;

    /**
     * 选中的Fragment的对应的位置
     */
    private int position;

    /**
     * 上次切换的Fragment
     */
    private Fragment mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置没有标题
        //初始化View
        initView();
        //初始化Fragment
        initFragment();
        //设置RadioGroup的监听
        setListener();
    }

    private void setListener() {
        mRg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //设置默认选中常用框架
        mRg_main.check(R.id.rb_main);
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_main:
                    position = 0;
                    break;
                case R.id.rb_message:
                    position = 1;
                    break;
                case R.id.rb_contacts:
                    position = 2;
                    break;
                case R.id.rb_mine:
                    position = 3;
                    break;
                default:
                    position = 0;
                    break;
            }

            //根据位置得到对应的Fragment
            BaseFragment to = getFragment();
            //替换
            switchFrament(mContent, to);

        }
    }


    /**
     * @param from 刚显示的Fragment,马上就要被隐藏了
     * @param to   马上要切换到的Fragment，一会要显示
     */
    private void switchFrament(Fragment from, Fragment to) {
        if (from != to) {
            mContent = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //才切换
            //判断有没有被添加
            if (!to.isAdded()) {
                //to没有被添加
                //from隐藏
                if (from != null) {
                    ft.hide(from);
                }
                //添加to
                if (to != null) {
                    ft.add(R.id.fl_content, to).commit();
                }
            } else {
                //to已经被添加
                // from隐藏
                if (from != null) {
                    ft.hide(from);
                }
                //显示to
                if (to != null) {
                    ft.show(to).commit();
                }
            }
        }

    }


    /**
     * 根据位置得到对应的Fragment
     *
     * @return
     */
    private BaseFragment getFragment() {
        BaseFragment fragment = mBaseFragment.get(position);
        return fragment;
    }

    private void initFragment() {
        mBaseFragment = new ArrayList<>();
        mBaseFragment.add(new MainFragment());//首页
        mBaseFragment.add(new MessageFragment());//消息
        mBaseFragment.add(new ContactsFragment());//联系人
        mBaseFragment.add(new MineFragment());//我的
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        mRg_main = findViewById(R.id.rg_main);
        mRbMain = findViewById(R.id.rb_main);
        mRbMessage = findViewById(R.id.rb_message);
        mRbContacts = findViewById(R.id.rb_contacts);
        mRbMine = findViewById(R.id.rb_mine);
//        setMainIcon();
    }

    /**
     * 设置主页面icon大小
     */
    private void setMainIcon() {

        RadioButton[] rbs = new RadioButton[4];
        rbs[0] = mRbMain;
        rbs[1] = mRbMessage;
        rbs[2] = mRbContacts;
        rbs[3] = mRbMine;
        for (RadioButton rb : rbs) {
            //挨着给每个RadioButton加入drawable限制边距以控制显示大小
            Drawable[] drawables = rb.getCompoundDrawables();
            //获取drawables
            Rect r = new Rect(0, 0, DensityUtil.px2dip(MainActivity.this, 150), DensityUtil.px2dip(MainActivity.this, 150));
            //定义一个Rect边界
            drawables[1].setBounds(r);
            //添加限制给控件
            rb.setCompoundDrawables(null, drawables[1], null, null);
        }

    }


}
