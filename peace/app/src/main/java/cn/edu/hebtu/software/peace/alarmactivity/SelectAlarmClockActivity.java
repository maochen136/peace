package cn.edu.hebtu.software.peace.alarmactivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;




import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import cn.edu.hebtu.software.peace.R;
import cn.edu.hebtu.software.peace.entity.Brand;

public class SelectAlarmClockActivity extends Activity {


    private ListView mListView; //首页的ListView
    private List<Brand> namesList; //用于装载数据的集合
    private int selectPosition = 0;//用于记录用户选择的变量,默认选中第一项；
    private Brand selectBrand; //用户选择的闹铃
    private String alarmMusicName;
    private ImageView imageViewCancel;

    private Map<Integer,Integer> musicId;
    private SoundPool sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_alarm_clock);

        imageViewCancel = findViewById( R.id.iv_alarm_cancel);//获取返回按钮
        initView();
        initDatas();

        initPalyMusic();
        initMusicId();


        imageViewCancel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("闹铃取消",String.valueOf(  selectPosition));
                cancelMusic( selectPosition );
                Intent intent = new Intent(  );
                intent.putExtra( "yinyue",alarmMusicName );
                Log.e( "获取到没啦",alarmMusicName );
                setResult( 24 ,intent);
                finish();
            }
        } );



    }

    private void initView(){
        mListView = (ListView)findViewById(R.id.musiclist);
    }

    private void initDatas(){
        //初始化ListView适配器的数据
        namesList = new ArrayList<>();
        Brand brand0 = new Brand("晨诗");
        Brand brand1 = new Brand("星光");
        Brand brand2 = new Brand("晓音");
        Brand brand3 = new Brand("浮光");
        Brand brand4 = new Brand("宿雨");
        Brand brand5 = new Brand("秘境");
        Brand brand6 = new Brand("荷风");
        Brand brand7 = new Brand("落霞");
        Brand brand8 = new Brand("初晴");
        Brand brand9 = new Brand("疏影");
        namesList.add(brand0);
        namesList.add(brand1);
        namesList.add(brand2);
        namesList.add(brand3);
        namesList.add(brand4);
        namesList.add(brand5);
        namesList.add(brand6);
        namesList.add(brand7);
        namesList.add(brand8);
        namesList.add(brand9);

        final MyAdapter myAdapter = new MyAdapter(this,namesList);
        mListView.setAdapter(myAdapter);
        mListView.setItemChecked( 0,true );
        alarmMusicName="晨诗";
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取选中的参数
                selectPosition = position;
                myAdapter.notifyDataSetChanged();
                selectBrand = namesList.get(position);
                alarmMusicName = selectBrand.getBandname();
                //播放音乐
                palyMusic(position);
                //Toast.makeText(SelectAlarmClockActivity.this,"您选中的闹钟铃声是："+selectBrand.getBandname(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class MyAdapter extends BaseAdapter {
        Context context;
        List<Brand> brandsList;
        LayoutInflater mInflater;
        public MyAdapter(Context context,List<Brand> mList){
            this.context = context;
            this.brandsList = mList;
            mInflater = (LayoutInflater)context.getSystemService( Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return brandsList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = null;
            if(convertView == null){
                convertView = mInflater.inflate(R.layout.alarm_clock_item,parent,false);
                viewHolder = new ViewHolder();
                viewHolder.name = (TextView)convertView.findViewById(R.id.tv_alarm_select_name);
                viewHolder.select = (RadioButton)convertView.findViewById(R.id.alarm_select_icon);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder)convertView.getTag();
            }
            viewHolder.name.setText(brandsList.get(position).getBandname());

            if(selectPosition == position){
                viewHolder.select.setChecked(true);
            }
            else{
                viewHolder.select.setChecked(false);
            }
            return convertView;
        }
    }
    public class ViewHolder{
        TextView name;
        RadioButton select;
    }

    //播放音频的方法
    public void initPalyMusic(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            SoundPool.Builder builder = new SoundPool.Builder();
            builder.setMaxStreams( 1 );
            sp = builder.build();
        }else{
            sp = new SoundPool( 1,0,5 );
        }
    }
    private  void initMusicId(){
        musicId = new HashMap<>(  );
        musicId.put(0,sp.load( getApplicationContext(),R.raw.chuchen,0 ));
        musicId.put(1,sp.load( getApplicationContext(),R.raw.xingguang,1 ));
        musicId.put(2,sp.load( getApplicationContext(),R.raw.xiaoyin,2 ));
        musicId.put(3,sp.load( getApplicationContext(),R.raw.chuchen,3 ));
        musicId.put(4,sp.load( getApplicationContext(),R.raw.suyu,4 ));
        musicId.put(5,sp.load( getApplicationContext(),R.raw.mijing,5 ));
        musicId.put(6,sp.load( getApplicationContext(),R.raw.chuchen,6 ));
        musicId.put(7,sp.load( getApplicationContext(),R.raw.chuchen,7 ));
        musicId.put(8,sp.load( getApplicationContext(),R.raw.chuchen,8 ));
        musicId.put(9,sp.load( getApplicationContext(),R.raw.chuchen,9 ));
    }
    private void palyMusic(int id){
        sp.play( musicId.get( id ),1,1,0,0,1 );
    }
    //取消播放
    private  void cancelMusic(int id){
        sp.stop( musicId.get( id ) );
    }

}