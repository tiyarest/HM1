package com.example.admin.tiktok;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button login;
    private ViewPager mViewPager;
    private List<Integer> pictureList = new ArrayList<>();
    private BannerPagerAdapter mBannerPagerAdapter;
    private int yourChoice = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        initDatas();
        initView();
        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("登陆的用户名：",username.getText().toString());
                Log.d("用户名的密码：",password.getText().toString());
                Toast toast = Toast.makeText(getApplicationContext(),
                        "你已经登陆成功了！", Toast.LENGTH_LONG);
                login.setEnabled(false);
                login.setText("已经登陆");
                toast.setGravity(Gravity.CENTER, 0, 0);
                LinearLayout toastView = (LinearLayout) toast.getView();
                ImageView imageCodeProject = new ImageView(getApplicationContext());
                imageCodeProject.setImageResource(R.mipmap.tiktok_logo);
                toastView.addView(imageCodeProject, 0);
                toast.show();
            }
        });
        setSupportActionBar(toolbar);

    }
    private void initDatas() {
        pictureList.add(R.drawable.advertisement);
        pictureList.add(R.mipmap.tiktok_logo);
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp_banner);
        mBannerPagerAdapter = new BannerPagerAdapter(this, pictureList);
        mViewPager.setAdapter(mBannerPagerAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.question) {

            final String[] items = { "非常满意","满意","一般","差评" };
            AlertDialog.Builder singleChoiceDialog = new AlertDialog.Builder(MainActivity.this);
            singleChoiceDialog.setTitle("你对抖音满意吗？");
            // 第二个参数是默认选项，此处设置为0
            singleChoiceDialog.setSingleChoiceItems(items, 0,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            yourChoice = which;
                        }
                    });
            singleChoiceDialog.setPositiveButton("确定",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (yourChoice != -1) {
                                Toast.makeText(MainActivity.this,
                                        "你选择了" + items[yourChoice],
                                        Toast.LENGTH_SHORT).show();
                                Log.d("用户满意度为：",String.valueOf(items[yourChoice]));
                            }
                        }
                    });
            singleChoiceDialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
