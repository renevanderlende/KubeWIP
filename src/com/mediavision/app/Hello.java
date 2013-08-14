package com.mediavision.app;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class Hello extends Activity {

	PopupWindow popUp;
	LinearLayout layout;
	TextView tv;
	LayoutParams params;
	LinearLayout mainLayout;
	Button but;
	boolean click = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
    	 
		popUp 		= new PopupWindow(this);
		layout 		= new LinearLayout(this);
		mainLayout 	= new LinearLayout(this);
		tv 			= new TextView(this);
		but			= new Button(this);
		
/*		
		View myPoppyView = popUp.getContentView();
		Button myBelovedButton = (Button)myPoppyView.findViewById(R.id.my_beloved_button);
		//do something with my beloved button? :p
*/		
		
    	tv.setText("Hi this is a sample text for popup window");
		
		but.setText("Click Me");
    	but.setOnClickListener(new OnClickListener() {
    		public void onClick(View v) {
    			if (click) {
    				popUp.showAtLocation(layout, Gravity.BOTTOM, 10, 10);
    				popUp.update(50, 50, 300, 80);
    				click = false;
        	
    			} else {
    				popUp.dismiss();
    				click = true;
    			}
    		}
    	});
    	 
    	params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    	
    	layout.setOrientation(LinearLayout.VERTICAL);
    	layout.addView(tv, params);

    	popUp.setContentView(layout);
//    	popUp.showAtLocation(layout, Gravity.BOTTOM, 10, 10);
    	mainLayout.addView(but, params);

    	setContentView(mainLayout);
	}
}	
	
	
/*
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hello);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hello, menu);
		return true;
	}
*/



/*
 * LayoutInflater inflater = (LayoutInflater) PartyCentral.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
final View popup = inflater.inflate(R.layout.minimain, (View) findViewById(R.id.minimain));
final PopupWindow pw = new PopupWindow(popup, 0, 0, true);
pw.setAnimationStyle(android.R.anim.fade_in);


popup.measure(View.MeasureSpec.makeMeasureSpec(findViewById(R.id.main).getWidth(), View.MeasureSpec.AT_MOST), View.MeasureSpec.UNSPECIFIED);
pw.setWidth(popup.getMeasuredWidth());
pw.setHeight(popup.getMeasuredHeight());
pw.showAtLocation(findViewById(R.id.main), Gravity.CENTER, 0, 0);
 */

