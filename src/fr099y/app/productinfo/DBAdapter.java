package fr099y.app.productinfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;


public class DBAdapter extends SQLiteOpenHelper {

	private static final String DATABASE_NAME="productinfos.db";
	final private static String DB_PATH = "/data/data/fr099y.app.productinfo/databases/";
	private static final String TABLE_NAME="products";
	private final String ROW_ID="id";
	private final String ROW_NAME="name";
	private final String ROW_BARCODE="barcode";
	private final String ROW_COUNTRY="country";
	private final String ROW_IMPORTER="importer";
	private final String ROW_INGREDIENTS="ingredients";
	private final String ROW_MANUFACTURED="manufactured";
	private final String ROW_VALIDATION="validation";
	private final String ROW_IMAGE="image";
	private final String PASSWORD;
	
	private final Context context;
	public DBAdapter(Context context) 
	{
		super(context, DATABASE_NAME, null, 1);
		// TODO Auto-generated constructor stub
//		this.PASSWORD=context.getString(R.string.database_password);
		this.context=context;
		this.PASSWORD="";
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
//		String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+ROW_ID+" INTEGER PRIMARY KEY NOT NULL, "+ROW_NAME+" TEXT, "+ROW_BARCODE+" TEXT, "+ROW_COUNTRY+" TEXT, "+
//		ROW_IMPORTER+" TEXT, "+ROW_INGREDIENTS+" TEXT, "+ROW_MANUFACTURED+" TEXT, "+ROW_VALIDATION+" TEXT)";
//		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	
	private boolean checkDataBase()
    {
        File dbFile = new File(DB_PATH + DATABASE_NAME);
        //Log.v("dbFile", dbFile + "   "+ dbFile.exists());
        return dbFile.exists();
    }

    public void createDataBase() throws IOException{
   	 
    	boolean dbExist = checkDataBase();
    	Log.d("TEST", "isHaveDB="+dbExist);
    	if(dbExist){
    	}else{
        	this.getReadableDatabase(PASSWORD);
        	try {
        		copyDataBase();
    		} catch (IOException e) {
 
        		throw new Error("Error copying database");
        	}
        	this.close();
    	}
    }
    private void copyDataBase() throws IOException
    {
        InputStream mInput = context.getAssets().open(DATABASE_NAME);
        String outFileName = DB_PATH + DATABASE_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer))>0)
        {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
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
		values.put(ROW_IMAGE, object.getImage());
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
		Cursor cursor=db.query(TABLE_NAME, new String[] {ROW_NAME, ROW_BARCODE, ROW_COUNTRY, ROW_IMPORTER, ROW_INGREDIENTS, ROW_MANUFACTURED, ROW_VALIDATION, ROW_IMAGE}, null, null, null, null, null);
		if(cursor.moveToFirst())
		{
			do {
				if(barcode.equals(cursor.getString(1)))
				{
					ProductObject product=new ProductObject(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
					return product;
				}
			} while (cursor.moveToNext());
		}
		return null;
	}

}
