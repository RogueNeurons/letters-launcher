package com.archbrey.www.letters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;


public class DrawFilterBox {

    private static GlobalHolder global;
    private static LinearLayout filterBox;
    private Resources rDrawBox;
    private static FilterItem[] filterItems;
    Context mainContext;
    int NumOfFilters;

    public DrawFilterBox(LinearLayout mainfilterBox,Context c, Resources r) {

        rDrawBox = r;
        mainContext = c;

        NumOfFilters = 6; //this should be dynamically assigned;

        filterBox = new LinearLayout(mainContext);
        filterBox = mainfilterBox;

        assignFilterCodes();
        drawFilterBox();

    } //public DrawFilterBox()

    public  LinearLayout getLayout(){return filterBox;
    }//public LinearLayout getLayout()

    public  FilterItem[] getFilterItems(){ return filterItems;
    }//public  KeypadButton[] getKeypadButton

    private void drawFilterBox() {

        int filterTextSize = 16;

        for (int inc=0; inc<NumOfFilters; inc++) { //change '3' to the number of actual buttons
            filterItems[inc].button = new Button(mainContext);
            filterItems[inc].button.setText(filterItems[inc].Code);
            filterItems[inc].button.setTextColor(Color.WHITE);
            filterItems[inc].button.setTextSize(TypedValue.COMPLEX_UNIT_SP, filterTextSize);
            filterItems[inc].button.setBackgroundColor(rDrawBox.getColor(R.color.Black_transparent));
        } //for (inc=0; inc<=NumOfFilters; inc++)

        int filter_button_width = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 0,
                rDrawBox.getDisplayMetrics());
        int filter_button_height = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 45,
                rDrawBox.getDisplayMetrics());

        LinearLayout.LayoutParams filterButtonParams = new LinearLayout.LayoutParams (
                filter_button_width,
                filter_button_height);
        filterButtonParams.setMargins(0,0,0,0);
        filterButtonParams.weight=0.1f;


        filterBox.setOrientation(LinearLayout.HORIZONTAL);
        filterBox.setBackgroundColor(rDrawBox.getColor(R.color.Blacker_transparent));
        filterBox.setGravity(Gravity.BOTTOM);


        for (int inc=0; inc<NumOfFilters; inc++) {filterBox.addView(filterItems[inc].button, filterButtonParams);}

    } //private void drawFilerBox()

    private void assignFilterCodes(){

        filterItems = new FilterItem[NumOfFilters];

        for (int inc=0; inc<NumOfFilters; inc++) {
            filterItems[inc] = new FilterItem();
        } //for (int inc=0; inc<NumOfFilters; inc++)


        filterItems[0].Code="All";filterItems[0].Code="All";
        filterItems[1].Code="Rec";
        filterItems[2].Code="Fav";
        filterItems[3].Code="Gam";
        filterItems[4].Code="Sys";
        filterItems[5].Code="Www";


    } //private void getFilterCodes()


    public void refreshFilterItems(AppItem[] masterAppItems){

        int ArraySize = masterAppItems.length;
        global = new GlobalHolder();

        AppItem[] RecentApps;
        GetAppList getAppListHandle;
        getAppListHandle = new GetAppList();

        RecentApps = new AppItem[10];
        RecentApps = getAppListHandle.getRecentApps();

        for (int inc=0; inc<NumOfFilters; inc++) {
            filterItems[inc].filteredPkgs = new AppItem[ArraySize];

            if (filterItems[inc].Code.equals("All")) { //assign all installed launchable apps to this special filter

                filterItems[inc].filteredPkgs = masterAppItems;
                filterItems[inc].CountofPackages = ArraySize;

            } //if (filterItems[inc].Code.equals("All"))
          /*  else if (filterItems[inc].Code.equals("Rec")) { //assign recent launchable apps to this special filter

                filterItems[inc].filteredPkgs = RecentApps;
                filterItems[inc].CountofPackages = RecentApps.length;

            } //else if (filterItems[inc].Code.equals("Rec"))*/
            else {

                filterItems[inc].filteredPkgs[0] = masterAppItems[4];
                filterItems[inc].filteredPkgs[1] = masterAppItems[8];
                filterItems[inc].filteredPkgs[2] = masterAppItems[9];
                filterItems[inc].CountofPackages = 3;

            } //else of else if (filterItems[inc].Code.equals("Rec")

        } //for (int inc=0; inc<NumOfFilters; inc++)


    } //public void refreshFilterItems()



} //public class DrawFilterBox