package cn.edu.hebtu.software.peace;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
//import android.graphics.Color;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
//import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import cn.edu.hebtu.software.peace.fragments.CalendarFragment1;
import cn.edu.hebtu.software.peace.fragments.CalendarFragment2;
import cn.edu.hebtu.software.peace.fragments.CalendarFragment3;
import cn.edu.hebtu.software.peace.fragments.CalendarFragment4;
import cn.edu.hebtu.software.peace.fragments.CalendarFragment5;
import cn.edu.hebtu.software.peace.fragments.CalendarFragment6;
import cn.edu.hebtu.software.peace.fragments.CalendarFragment7;
import cn.edu.hebtu.software.peace.utils.SharePopDialog;

import static cn.edu.hebtu.software.peace.CalenderActivity.SHARE_TYPE.Type_WXSceneSession;
import static cn.edu.hebtu.software.peace.CalenderActivity.SHARE_TYPE.Type_WXSceneTimeline;
import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneSession;
import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneTimeline;


public class CalenderActivity extends AppCompatActivity implements IWXAPIEventHandler{

    private ViewPager viewPager;
    private List<Fragment> list;
    private FragmentPagerAdapter adapter;

    private ImageView share;
//    private Handler handler = new Handler(Looper.getMainLooper());
    private ImageView close;

    private String APP_ID = "wxab50f50cb60abe19";
    private IWXAPI iwxapi;
    enum SHARE_TYPE {Type_WXSceneSession, Type_WXSceneTimeline}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏状态栏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.calendar);
        InitView();

        iwxapi = WXAPIFactory.createWXAPI(this, APP_ID, false);
        iwxapi.handleIntent(getIntent(), this);
        iwxapi.registerApp(APP_ID);

        //把Fragment添加到List集合里面
        list = new ArrayList<>();
        list.add(new CalendarFragment7());
        list.add(new CalendarFragment6());
        list.add(new CalendarFragment5());
        list.add(new CalendarFragment4());
        list.add(new CalendarFragment3());
        list.add(new CalendarFragment2());
        list.add(new CalendarFragment1());

        adapter = new CalendarFragmentAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(6);  //初始化显示第一个页面
    }

    //初始化控件
    private void InitView() {
        viewPager= findViewById(R.id.viewpager);
        share=findViewById(R.id.share);
        close=findViewById(R.id.close);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharePopDialog popDialog = new SharePopDialog();
                popDialog.showDialog(CalenderActivity.this);

            }

        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent closeintent=new Intent();
                closeintent.setClass(CalenderActivity.this,IndexActivity.class);
                startActivity(closeintent);
            }
        });

    }

    public void shareWXSceneSession(View view) {
        share(Type_WXSceneSession);
    }

    public void shareWXSceneTimeline(View view) {
        share(Type_WXSceneTimeline);
    }

    private void share(SHARE_TYPE type) {
        View view=getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        Bitmap bmp=Bitmap.createBitmap(view.getDrawingCache());
        WXImageObject imageObject=new WXImageObject(bmp);
        WXMediaMessage msg=new WXMediaMessage();
        msg.mediaObject=imageObject;
        Bitmap thumbBmp=Bitmap.createScaledBitmap(bmp,200,200,true);
        bmp.recycle();
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        thumbBmp.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
        msg.thumbData=byteArrayOutputStream.toByteArray();//设置缩略图
        SendMessageToWX.Req req=new SendMessageToWX.Req();
        req.transaction=buildTransaction("img");
        req.message=msg;
        req.scene=SendMessageToWX.Req.WXSceneTimeline;
        switch (type) {
            case Type_WXSceneSession:
                req.scene = WXSceneSession;
                break;
            case Type_WXSceneTimeline:
                req.scene = WXSceneTimeline;
                break;
        }
        iwxapi.sendReq(req);
        finish();
//        WXWebpageObject webpageObject = new WXWebpageObject();
//        webpageObject.webpageUrl = "http://www.initobject.com/";
//        WXMediaMessage msg = new WXMediaMessage(webpageObject);
//        msg.title = "Hi,Tips";
//        msg.description = "这是一个校园应用";
//        Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.drawable.ninja);
//        msg.thumbData = bmpToByteArray(thumb, true);
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = buildTransaction("Req");
//        req.message = msg;
//        switch (type) {
//            case Type_WXSceneSession:
//                req.scene = WXSceneSession;
//                break;
//            case Type_WXSceneTimeline:
//                req.scene = WXSceneTimeline;
//                break;
//        }
//        iwxapi.sendReq(req);
//        finish();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        iwxapi.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
    }

    @Override
    public void onResp(BaseResp resp) {
        String result;
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = "分享成功";
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "取消分享";
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "分享被拒绝";
                break;
            default:
                result = "发送返回";
                break;
        }
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        finish();
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

//    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
//        if (needRecycle) {
//            bmp.recycle();
//        }
//        byte[] result = output.toByteArray();
//        try {
//            output.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

}
