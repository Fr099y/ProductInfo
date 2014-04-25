package fr099y.app.productinfo;

public class ProductObject {
	
	String name; 
	String barcode;
	String country;
	String importer;
	String ingredients;
	String manufactured;
	String validation;
	public ProductObject(String name, String barcode, String country, String importer, String ingredients, String manufactured, String validation)
	{
		this.name=name;
		this.barcode=barcode;
		this.country=country;
		this.importer=importer;
		this.ingredients=ingredients;
		this.manufactured=manufactured;
		this.validation=validation;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	public void setBarcode(String barcode)
	{
		this.barcode=barcode;
	}
	public void setCountry(String country)
	{
		this.country=country;
	}
	public void setImporter(String importer)
	{
		this.importer=importer;
	}
	public void setIngredients(String ingredients)
	{
		this.ingredients=ingredients;
	}
	public void setManufactured(String manufactured)
	{
		this.manufactured=manufactured;
	}
	public void setValidation(String validation)
	{
		this.validation=validation;
	}
	
	public String getName()
	{
		return this.name;
	}
	public String getBarcode()
	{
		return this.barcode;
	}
	public String getCountry()
	{
		return this.country;
	}
	public String getImporter()
	{
		return this.importer;
	}
	public String getIngredients()
	{
		return this.ingredients;
	}
	public String getManufactured()
	{
		return this.manufactured;
	}
	public String getValidation()
	{
		return this.validation;
	}
	

}
