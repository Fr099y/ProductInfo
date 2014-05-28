package fr099y.app.productinfo;

import java.io.IOException;

import net.sqlcipher.database.SQLiteDatabase;

import com.google.zxing.Result;
import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ResultParser;

import fr099y.lib.tools.DataLoader;
import fr099y.lib.tools.DataLoaderListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;
import android.graphics.Bitmap;

public class MainActivity extends CaptureActivity {

	@Override
	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.activity_main);
		new CopyDatabase().execute();
		loadTempData();
	}
	private void loadTempData()
	{
		String url=getString(R.string.main_json_url)+"index.php";
		new DataLoader(this, new DataLoaderListener() {
			
			@Override
			public void onPostExecuted(String data) {
				// TODO Auto-generated method stub
				 Intent productInfo=new Intent(MainActivity.this, ProductInfoActivity.class);
				 productInfo.putExtra(getString(R.string.intent_data_content), "8888240000309");
				 productInfo.putExtra(getString(R.string.intent_data_format), "");
				 startActivity(productInfo);
			}
		}, false).execute(url);
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
	 
	 public class CopyDatabase extends AsyncTask<Object, Object, Boolean>
	 {

		@Override
		protected Boolean doInBackground(Object... params) {
			// TODO Auto-generated method stub
			DBAdapter db=new DBAdapter(MainActivity.this);
			SQLiteDatabase.loadLibs(MainActivity.this);
			try {
				db.createDataBase();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
	 }
}
