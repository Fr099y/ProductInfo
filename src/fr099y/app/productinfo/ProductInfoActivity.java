package fr099y.app.productinfo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class ProductInfoActivity extends Activity{
	
	private final String TAG_="TEST";
	private String content;
	private String format;
	@Override
	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.product_info);
		getIntentData();
		Log.d(TAG_, "content="+content);
		Log.d(TAG_, "format="+format);
	}
	
	public void getIntentData()
	{
		content=getIntent().getStringExtra(getString(R.string.intent_data_content));
		format=getIntent().getStringExtra(getString(R.string.intent_data_format));
	}
}