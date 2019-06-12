package cn.edu.hebtu.software.peace;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


/**
 * 主界面底部菜单适配器
 */
public class CalendarFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> clist;

    public CalendarFragmentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.clist = list;
    }

    @Override
    public Fragment getItem(int arg0) {
        return clist.get(arg0);//显示第几个页面
    }

    @Override
    public int getCount() {
        return clist.size();//有几个页面
    }
}

