package cn.edu.hebtu.software.peace.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hebtu.software.peace.R;
import cn.edu.hebtu.software.peace.views.CalendarView;

public class RespiteFragment extends Fragment {

    private CalendarView calendarViewRespiteinute;
    private String respiteMintue;
    private LinearLayout ll15;
    private LinearLayout ll30;
    private LinearLayout ll45;
    ArrayList<String> gradeRespiteMintue;
    private List<String> respiteCallBack;
    private RespiteCallBackValue respiteCallBackValue;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.respite_layout, null );
        return view;


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach( activity );
        respiteCallBackValue = (RespiteCallBackValue) getActivity();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );

        gradeRespiteMintue = new ArrayList<>();
        respiteCallBack = new ArrayList<>( 1 );
        respiteCallBack.add( 0,String.valueOf( 30 ) );

        //为数据项赋值
        for (int i = 5; i <= 120; i = i + 5)           // 60分钟
            gradeRespiteMintue.add( i + "" );

        ll15 = getView().findViewById( R.id.ll_btn_15min );
        ll30 = getView().findViewById( R.id.ll_btn_30min );
        ll45 = getView().findViewById( R.id.ll_btn_45min );
        respiteCalendar();
        ll15.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarViewRespiteinute.setData( gradeRespiteMintue );
                calendarViewRespiteinute.setSelected( 2 );
                respiteCallBack.set( 0,String.valueOf( 15 ) );
            }

        } );
        ll30.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarViewRespiteinute.setData( gradeRespiteMintue );
                calendarViewRespiteinute.setSelected( 5 );
                respiteCallBack.set( 0,String.valueOf( 30 ) );
            }
        } );
        ll45.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarViewRespiteinute.setData( gradeRespiteMintue );
                calendarViewRespiteinute.setSelected( 8 );
                respiteCallBack.set( 0,String.valueOf( 45 ) );
            }
        } );


        respiteCallBackValue.SendRespiteValue( respiteCallBack );
    }

    private void respiteCalendar() {
        //加载时间的三个 CalendarView 的 id
        calendarViewRespiteinute = (CalendarView) getView().findViewById( R.id.sleep_respite_mintue );

        //为滚动选择器设置数据
        calendarViewRespiteinute.setData( gradeRespiteMintue );

        //滚动选择事件
        calendarViewRespiteinute.setOnSelectListener( new CalendarView.onSelectListener() {
            @Override
            public void onSelect(String data) {
                respiteMintue = data;
                respiteCallBack.set( 0,data );
            }
        } );

        respiteCallBackValue.SendRespiteValue(respiteCallBack);
    }

    //定义一个回调接口
    public interface RespiteCallBackValue {
        public void SendRespiteValue(List<String> data);
    }

}
