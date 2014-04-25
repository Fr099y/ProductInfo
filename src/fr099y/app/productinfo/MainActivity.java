package fr099y.app.productinfo;

import com.google.zxing.Result;
import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ResultParser;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Bitmap;

public class MainActivity extends CaptureActivity {

	@Override
	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.activity_main);
	}
	
	 @Override
	 public void handleDecode(Result rawResult, Bitmap barcode) 
	 {
		 ParsedResult result_parser = ResultParser.parseResult(rawResult);
		 String format = result_parser.getType().toString();
		 Intent productInfo=new Intent(this, ProductInfoActivity.class);
		 productInfo.putExtra(getString(R.string.intent_data_content), rawResult.toString());
		 productInfo.putExtra(getString(R.string.intent_data_format), format);
		 startActivity(productInfo);
	 }
}
