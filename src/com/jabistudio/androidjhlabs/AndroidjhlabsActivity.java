package com.jabistudio.androidjhlabs;

import com.jabistudio.androidjhlabs.filter.util.AndroidUtils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

public class AndroidjhlabsActivity extends Activity implements OnClickListener{
	
	private static final String ACTIVITY = "Activity";
	private static final String DOT = ".";
	
	private static final float FILTER_TEXT_SIZE = 14.6f;
	private static final float TITLE_TEXT_SIZE = 13.3f;
	
	private ScrollView mMainScrollView;
	
	private int mDispalyHeight;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Display display = ((WindowManager)getApplicationContext().getSystemService(Activity.WINDOW_SERVICE)).getDefaultDisplay();
        mDispalyHeight = display.getHeight();
        
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(getListLayout(R.string.color_adjustment_filter, R.string.coloradjustmentfilteractivity, R.array.color_adjustment_filtername));
        linearLayout.addView(getListLayout(R.string.distortion_and_warping_filter, R.string.distortionandwarpingactivity, R.array.distortion_and_warping_filtername));
        linearLayout.addView(getListLayout(R.string.effects_filter, R.string.effectsactivity, R.array.effects_filtername));
        linearLayout.addView(getListLayout(R.string.blurring_and_sharpening_filter, R.string.blurringandsharpeningactivity, R.array.blurring_and_sharpening_filtername));
        linearLayout.addView(getListLayout(R.string.edge_detection_filter, R.string.edgedetectionactivity, R.array.edge_detection_filtername));
        linearLayout.addView(getListLayout(R.string.transitions_filter, R.string.transitionsactivity, R.array.transitions_filtername));
        linearLayout.addView(getListLayout(R.string.alpha_channel_filter, R.string.alphachannelactivity, R.array.alpha_channel_filtername));
        
        mMainScrollView = new ScrollView(this);
        mMainScrollView.addView(linearLayout);
        setContentView(mMainScrollView);
    }
    private LinearLayout getListLayout(int titleStringId, int activitypackageNameId, int stringArrayId){
    	LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        
    	String[] filterNameArray = getResources().getStringArray(stringArrayId); 
        TextView filterTitle = new TextView(this);
        filterTitle.setTextSize(AndroidUtils.dipTopx(TITLE_TEXT_SIZE,this));
        filterTitle.setTextColor(Color.WHITE);
        filterTitle.setBackgroundResource(R.drawable.title_bar);
        filterTitle.setText(titleStringId);
        
        View titleSaparator = new View(this);
        titleSaparator.setBackgroundColor(Color.GRAY);
        
        linearLayout.addView(filterTitle);
        linearLayout.addView(titleSaparator);
        
        LinearLayout.LayoutParams titleSaparatorParams = (LinearLayout.LayoutParams) titleSaparator.getLayoutParams();
        titleSaparatorParams.width = LayoutParams.FILL_PARENT;
        titleSaparatorParams.height = 1;
    	titleSaparator.setLayoutParams(titleSaparatorParams);
    	
        for(int i=0;i<filterNameArray.length;++i){
        	TextView textView = new TextView(this);
        	textView.setText(filterNameArray[i]);
        	textView.setTextColor(Color.WHITE);
        	textView.setTextSize(AndroidUtils.dipTopx(FILTER_TEXT_SIZE,this));
        	textView.setGravity(Gravity.CENTER_VERTICAL);
        	textView.setBackgroundResource(android.R.drawable.list_selector_background);
        	textView.setClickable(true);
        	textView.setOnClickListener(this);
        	final String activitypackageName = getResources().getString(activitypackageNameId);
        	textView.setTag(DOT + activitypackageName + DOT + filterNameArray[i] + ACTIVITY);
        	View saparator = new View(this);
        	saparator.setBackgroundColor(Color.GRAY);
        	
        	linearLayout.addView(textView);
        	linearLayout.addView(saparator);
        	
        	LinearLayout.LayoutParams textViewParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
        	textViewParams.width = LayoutParams.FILL_PARENT;
        	textViewParams.height = (int)(mDispalyHeight/7.5f);
        	textView.setLayoutParams(textViewParams);
        	
        	LinearLayout.LayoutParams saparatorParams = (LinearLayout.LayoutParams) saparator.getLayoutParams();
        	saparatorParams.width = LayoutParams.FILL_PARENT;
        	saparatorParams.height = 1;
        	saparator.setLayoutParams(saparatorParams);
        }
        return linearLayout;
    }
	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		intent.setClassName(getPackageName(), getPackageName() + (String)(v.getTag()));
		startActivity(intent);
	}
}