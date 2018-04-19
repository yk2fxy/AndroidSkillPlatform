package com.yk.skill.androidskillplatform.index;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yk.skill.androidskillplatform.R;
import com.yk.skill.androidskillplatform.base.BaseActivity;
import com.yk.skill.androidskillplatform.base.BaseFragment;


public class IndexActivity extends BaseActivity implements IndexViewDetail {

    TextView mHeaderText;
    IndexPresent mIndexPresent = new IndexPresent(this);
    RadioGroup mIndexActRg;

 /*   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_index);
        //绑定butterknife
        ButterKnife.bind(this);
        //让present层去初始化页面

    }*/

    @Override
    protected int getLayoutId() {
        return R.layout.activity_index;
    }

    @Override
    public void initView() {
        mHeaderText = findViewById(R.id.header_text);
        mIndexActRg = findViewById(R.id.index_act_rg);
        mIndexPresent.init();
        //设置radiogroup 监听
        mIndexActRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup,int i) {
                //让present 去实现radiogroup切换的业务
                mIndexPresent.onCheckecChanged(i);
            }
        });
    }


    @Override
    public void setTitle(String title) {
        //设置页面header
        mHeaderText.setText(title);
    }

    @Override
    public void setViewPage(int position) {
    }

    /**
     * 显示指定的fragment
     * @param baseFragment
     */
    @Override
    public void showFragment(BaseFragment baseFragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.index_fl_container,baseFragment);
        ft.commit();
    }
}
