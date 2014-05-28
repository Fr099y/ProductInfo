package fr099y.app.productinfo;

import fr099y.lib.tools.ImageLoader;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductInfoActivity extends Activity{
	
	private final String TAG_="TEST";
	private String content;
	private String format;
	private DBAdapter db;
	private ProductObject product=null;
	private TextView name, barcode, country, importer, ingredients, manufactured, validation;
	private ImageLoader imgLoader;
	private ImageView image;
	private InitHeader header=new InitHeader();
	@Override
	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.product_info);
		getIntentData();
		db=new DBAdapter(this);
		imgLoader=new ImageLoader(this, R.drawable.na);
		initMyView();
		if(checkProduct())
		{
			name.setText(product.getName());
			barcode.setText(product.getBarcode());
			country.setText(product.getCountry());
			importer.setText(product.getImporter());
			ingredients.setText(product.getIngredients());
			manufactured.setText(product.getManufactured());
			validation.setText(product.getValidation());
			imgLoader.DisplayImage(product.getImage(), image);			
			header.initHeader(this, product.getName(), product.getBarcode(), true);
		}
		else
		{
			finish();
			Intent addProductIntent=new Intent(this, AddProductActivity.class);
			addProductIntent.putExtra(getString(R.string.intent_data_barcode), content);
			startActivity(addProductIntent);
		}
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
		image=(ImageView)findViewById(R.id.product_info_image);
		name=(TextView)findViewById(R.id.product_info_name);
		barcode=(TextView)findViewById(R.id.product_info_barcode);
		country=(TextView)findViewById(R.id.product_info_country);
		importer=(TextView)findViewById(R.id.product_info_importer);
		ingredients=(TextView)findViewById(R.id.product_info_ingredient);
		manufactured=(TextView)findViewById(R.id.product_info_manufactured);
		validation=(TextView)findViewById(R.id.product_info_validate);
	}
}