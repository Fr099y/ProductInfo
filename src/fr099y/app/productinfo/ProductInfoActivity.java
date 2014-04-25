package fr099y.app.productinfo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ProductInfoActivity extends Activity{
	
	private final String TAG_="TEST";
	private String content;
	private String format;
	private DBAdapter db;
	private ProductObject product=null;
	private TextView name, barcode, country, importer, ingredients, manufactured, validation;
	@Override
	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.product_info);
		getIntentData();
		initMyView();
		db=new DBAdapter(this);
		if(checkProduct())
		{
			name.setText(product.getName());
			barcode.setText(product.getBarcode());
			country.setText(product.getCountry());
			importer.setText(product.getImporter());
			ingredients.setText(product.getIngredients());
			manufactured.setText(product.getManufactured());
			validation.setText(product.getValidation());
		}
		else
		{
			Log.d("TEST", "false");
		}
		
		
		Log.d(TAG_, "content="+content);
		Log.d(TAG_, "format="+format);
	}
	private boolean checkProduct()
	{
		product=db.getProduct(content);
		if(product!=null)
			return true;
		return false;
	}
	public void getIntentData()
	{
		content=getIntent().getStringExtra(getString(R.string.intent_data_content));
		format=getIntent().getStringExtra(getString(R.string.intent_data_format));
	}
	private void initMyView()
	{
		name=(TextView)findViewById(R.id.product_info_name);
		barcode=(TextView)findViewById(R.id.product_info_barcode);
		country=(TextView)findViewById(R.id.product_info_country);
		importer=(TextView)findViewById(R.id.product_info_importer);
		ingredients=(TextView)findViewById(R.id.product_info_ingredient);
		manufactured=(TextView)findViewById(R.id.product_info_manufactured);
		validation=(TextView)findViewById(R.id.product_info_validate);
	}
}