package fr099y.app.productinfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;


public class DBAdapter extends SQLiteOpenHelper {

	private static final String DATABASE_NAME="productinfos.db";
	private static final String TABLE_NAME="products";
	private final String ROW_ID="id";
	private final String ROW_NAME="name";
	private final String ROW_BARCODE="barcode";
	private final String ROW_COUNTRY="country";
	private final String ROW_IMPORTER="importer";
	private final String ROW_INGREDIENTS="ingredients";
	private final String ROW_MANUFACTURED="manufactured";
	private final String ROW_VALIDATION="validation";
	private final String PASSWORD;
	public DBAdapter(Context context) 
	{
		super(context, DATABASE_NAME, null, 1);
		// TODO Auto-generated constructor stub
		this.PASSWORD=context.getString(R.string.database_password);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+ROW_ID+" INTEGER PRIMARY KEY NOT NULL, "+ROW_NAME+" TEXT, "+ROW_BARCODE+" TEXT, "+ROW_COUNTRY+" TEXT, "+
		ROW_IMPORTER+" TEXT, "+ROW_INGREDIENTS+" TEXT, "+ROW_MANUFACTURED+" TEXT, "+ROW_VALIDATION+" TEXT)";
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	
	public boolean addProduct(ProductObject object)
	{
		boolean isInserted=false;
		SQLiteDatabase db=this.getWritableDatabase(PASSWORD);
		ContentValues values=new ContentValues();
		values.put(ROW_NAME, object.getName());
		values.put(ROW_BARCODE, object.getBarcode());
		values.put(ROW_COUNTRY, object.getCountry());
		values.put(ROW_IMPORTER, object.getImporter());
		values.put(ROW_INGREDIENTS, object.getIngredients());
		values.put(ROW_MANUFACTURED, object.getManufactured());
		values.put(ROW_VALIDATION, object.getValidation());
		try {
			db.insert(TABLE_NAME, null, values);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			db.close();
		}
		if(db.isOpen())
			db.close();
		return isInserted;
	}
	
	public ProductObject getProduct(String barcode)
	{
		SQLiteDatabase db=this.getWritableDatabase(PASSWORD);
		Cursor cursor=db.query(TABLE_NAME, new String[] {ROW_NAME, ROW_BARCODE, ROW_COUNTRY, ROW_IMPORTER, ROW_INGREDIENTS, ROW_MANUFACTURED, ROW_VALIDATION}, null, null, null, null, null);
		if(cursor.moveToFirst())
		{
			do {
				if(barcode.equals(cursor.getString(1)))
				{
					ProductObject product=new ProductObject(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
					return product;
				}
			} while (cursor.moveToNext());
		}
		return null;
	}

}