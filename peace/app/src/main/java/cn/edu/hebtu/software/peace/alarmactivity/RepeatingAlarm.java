package cn.edu.hebtu.software.peace.alarmactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class RepeatingAlarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction()!=null&&intent.getAction().equals("com.ll.alarm")) {//自定义的action
            intent = new Intent(context,AlarmActivity.class);
            intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
