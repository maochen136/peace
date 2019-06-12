package cn.edu.hebtu.software.peace.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hebtu.software.peace.IndexActivity;
import cn.edu.hebtu.software.peace.R;
import cn.edu.hebtu.software.peace.alarmactivity.SelectAlarmClockActivity;
import cn.edu.hebtu.software.peace.views.CalendarView;

public class AlarmFragment extends Fragment {

    private CallBackValue callBackValue;
    private CalendarView calendarViewHour, calendarViewminute;
    private String sleepAlarmTime;
    private String sleepAlarmhour, sleepAlarmMinute;
    private Button buttonSaveSleepTime;
    List<String> alarmHourList;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach( activity );
        callBackValue= (CallBackValue) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.alarm_layout, null );
        return view;


    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );

        alarmHourList = new ArrayList<>( 2 );
        alarmHourList.add( 0,String.valueOf( 8  ));
        alarmHourList.add( 1,String.valueOf( 0 ) );

        myCalendar();

        //闹钟选择的监听事件
        LinearLayout linearLayout = getView().findViewById( R.id.alarm_clock_select );
        linearLayout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slectAlarm();
            }
        } );

    }

    private void myCalendar() {
        //加载时间的三个 CalendarView 的 id
        calendarViewHour = (CalendarView) getView().findViewById( R.id.sleep_alarm_hour );
        calendarViewminute = (CalendarView) getView().findViewById( R.id.sleep_alarm_minute );


        //定义滚动选择器的数据项（年月日的）
        final ArrayList<String> gradeHour = new ArrayList<>();
        ArrayList<String> grademinute = new ArrayList<>();

        //为数据项赋值

        for (int i = 0; i <= 23; i++)            // 24小时
            gradeHour.add( i + "" );
        for (int i = 0; i <= 59; i++)           // 60分钟
            grademinute.add( i + "" );

        //为滚动选择器设置数据
        calendarViewHour.setData( gradeHour );
        calendarViewminute.setData( grademinute );

        //滚动选择事件
        calendarViewHour.setOnSelectListener( new CalendarView.onSelectListener() {
            @Override
            public void onSelect(String data) {
                sleepAlarmhour = data;
                alarmHourList.set( 0 ,sleepAlarmhour);
                Log.e( "打印闹钟小时值里面",  alarmHourList.toString());
            }
        } );
        calendarViewminute.setOnSelectListener( new CalendarView.onSelectListener() {
            @Override
            public void onSelect(String data) {
                sleepAlarmMinute = data;
                alarmHourList.set( 1,sleepAlarmMinute );
                Log.e( "打印闹钟分钟值里面",  alarmHourList.toString());
            }
        } );
        Log.e( "方法执行完之后的值",alarmHourList.toString()  );
        callBackValue.SendMessageValue( alarmHourList);
    }

    //定义一个回调接口
    public interface CallBackValue {
        public void SendMessageValue(List<String> data);
    }

    //之后闹钟选择1card
    public  void  slectAlarm(){
        Intent intent = new Intent(  );
        intent.setClass( getActivity().getApplicationContext(),SelectAlarmClockActivity.class );
        getActivity().startActivityForResult( intent,12 );
    }



}
