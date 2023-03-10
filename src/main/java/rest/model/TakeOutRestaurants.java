package rest.model;


/**
 * TakeOutRestaurants is a simple, plain old java objects (POJO).
 * Well, almost (it extends {@link Restaurants}).
 */
public class TakeOutRestaurants extends Restaurants {
	protected int maxWaitTime;
	
	public TakeOutRestaurants(  int restaurantId, String name, String description,
                                String menu, String hours, boolean isActive, 
                                String street1, String street2, String city,
                                String state, String zip, CuisineType cuisineType, 
                                Companies companyName, int maxWaitTime) {
		super(restaurantId, name,  description, menu,  hours,  isActive, 
              street1, street2,  city, state,  zip,  cuisineType,  companyName);
		this.maxWaitTime = maxWaitTime;
	}
	
	public TakeOutRestaurants(int restaurantId) {
		super(restaurantId);
	}

	/** Getters and setters. */
	
	public int getmaxWaitTime() {
		return maxWaitTime;
	}

	public void setmaxWaitTime(int maxWaitTime) {
		this.maxWaitTime = maxWaitTime;
	}
	
}
