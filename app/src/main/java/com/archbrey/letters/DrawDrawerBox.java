package com.archbrey.letters;

import android.content.Context;
import android.widget.GridView;

import com.archbrey.letters.Preferences.SettingsActivity;
//import android.widget.RelativeLayout;


public class DrawDrawerBox extends LaunchpadActivity {

    private static Context getViewContext;
    private static AppItem[] showItem;
    private static AppDrawerAdapter AppDrawerAdapterObject;
    private static GridView appGridView;
    private static GlobalHolder global;

    public DrawDrawerBox (Context c, GridView getAppGridView, AppItem[] appItem)  {


        getViewContext = c;
        showItem = appItem;
        appGridView = getAppGridView;

        global = new GlobalHolder();

        AppDrawerAdapterObject = new AppDrawerAdapter(getViewContext, showItem);
        appGridView.setAdapter(AppDrawerAdapterObject);
        appGridView.setBackgroundColor(SettingsActivity.backerColor);
        appGridView.setNumColumns(SettingsActivity.drawerColumns);
        //appGridView.setVerticalSpacing(5);


    } // public DrawDrawerBox (Context c, AppItem[] appItem)


    public void setListener() {

        basicPkgMgr=global.getPackageManager();
        appGridView.setOnItemClickListener(new DrawerClickListener(getViewContext, showItem, basicPkgMgr));
        appGridView.setOnItemLongClickListener(new DrawerLongClickListener(getViewContext, showItem, basicPkgMgr));

    }

} //public class DrawDrawerBox