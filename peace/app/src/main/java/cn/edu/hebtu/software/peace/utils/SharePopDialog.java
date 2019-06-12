package cn.edu.hebtu.software.peace.utils;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import cn.edu.hebtu.software.peace.R;


public class SharePopDialog {

    public SharePopDialog() {
    }

    private Dialog dialog;// 利用android自带的dialog类
    private View dialogView;// 弹窗的内容view
    private RelativeLayout ll_up;// 弹窗内容的某部分
    private LinearLayout popListView;// 弹窗内容的某部分

    public void showDialog(Activity activity) {
//       dialog = new Dialog(activity, R.style.dialog_loading_bar_no_frame);//创建一个dialog实例，
//        dialog = new Dialog(activity, R.style.popwindow_anim_style);//创建一个dialog实例，
        dialog=new Dialog(activity);
        dialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_share_pop_v, null);//实例化一个view作为弹窗的内容view
        popListView = dialogView.findViewById(R.id.popListView);
        ll_up = dialogView.findViewById(R.id.ll_up);

        dialog.setContentView(dialogView);//设置弹窗的内容view
        dialog.setCanceledOnTouchOutside(true);//设置是否可以在窗口之外点击屏幕让弹窗消失
        dialog.setCancelable(true);//是否可以被按下back而让弹窗消失
        Window window = dialog.getWindow();//获得弹窗的 window对象
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.gravity = Gravity.BOTTOM;//初始化弹窗的位置，在底部
            params.width = getScreenPixelsWidth(activity);//弹窗的宽度是整个屏幕的宽度
            window.setWindowAnimations(R.style.popwindow_anim_style);// 弹窗的显示和消失，加入动画
        }

        dialog.show();//显示弹窗

        startAnimLayout();//上部分外层动画
        startAnim();// 上部分内层动画，每一个元素分开做动画
    }

    /**
     * 获取屏幕的宽度，单位是像素px
     * @param activity
     * @return
     */
    private int getScreenPixelsWidth(Context activity) {
        return activity.getResources().getDisplayMetrics().widthPixels;
    }

    private int animDuration = 500;// 动画执行的时长

    /**
     * 动画效果1
     */
    private void startAnimLayout() {
        //整体layout的动画，Y轴移动
        final int height = 20;
        final ValueAnimator anim = ValueAnimator.ofFloat(1, 0, 1);
        anim.setDuration(animDuration);
        anim.setInterpolator(new OvershootInterpolator());//先快后慢的插值器
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                ll_up.setTranslationY((1 - value) * height);
            }
        });
        anim.start();
    }

    /**
     * 动画效果2
     */
    private void startAnim() {
        int count = popListView.getChildCount();
        long delay = 0;

        for (int i = 0; i < count; i++) {
            final View child = popListView.getChildAt(i);
            child.setTranslationY(1 * 600);
            child.setAlpha(1 - 1);
        }

        for (int i = 0; i < count; i++) {
            final View child = popListView.getChildAt(i);

            ValueAnimator anim = ValueAnimator.ofFloat(1, 0);
            anim.setDuration(animDuration);
            anim.setInterpolator(new OvershootInterpolator());
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float value = (float) animation.getAnimatedValue();
                    child.setTranslationY(value * 600);
                    child.setAlpha(1 - value);
                }
            });
            anim.setStartDelay(delay);
            anim.start();
            delay += 100;
        }
    }
}