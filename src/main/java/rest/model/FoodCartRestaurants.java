package rest.model;


/**
 * FoodCartRestaurants is a simple, plain old java objects (POJO).
 * Well, almost (it extends {@link Restaurants}).
 */
public class FoodCartRestaurants extends Restaurants {
	protected boolean isLicensed;
	
	public FoodCartRestaurants(  int restaurantId, String name, String description,
                                String menu, String hours, boolean isActive, 
                                String street1, String street2, String city,
                                String state, String zip, CuisineType cuisineType, 
                                Companies company, boolean isLicensed) {
		super(restaurantId, name,  description, menu,  hours,  isActive, 
              street1, street2,  city, state,  zip,  cuisineType,  company);
		this.isLicensed = isLicensed;
	}
	
	public FoodCartRestaurants(int restaurantId) {
		super(restaurantId);
	}

	/** Getters and setters. */
	
	public boolean getisLicensed() {
		return isLicensed;
	}

	public void setisLicensed(boolean isLicensed) {
		this.isLicensed = isLicensed;
	}
	
}
