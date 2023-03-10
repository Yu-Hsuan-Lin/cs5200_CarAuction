package rest.model;

/**
 * SitDownRestaurants is a simple, plain old java objects (POJO).
 * Well, almost (it extends {@link Restaurants}).
 */
public class SitDownRestaurants extends Restaurants {
	protected int capacity;
	
	public SitDownRestaurants(  int restaurantId, String name, String description,
                                String menu, String hours, boolean isActive, 
                                String street1, String street2, String city,
                                String state, String zip, CuisineType cuisineType, 
                                Companies companyName, int capacity) {
		super(restaurantId, name,  description, menu,  hours,  isActive, 
              street1, street2,  city, state,  zip,  cuisineType,  companyName);
		this.capacity = capacity;
	}
	
	public SitDownRestaurants(int restaurantId) {
		super(restaurantId);
	}

	/** Getters and setters. */
	
	public int getcapacity() {
		return capacity;
	}

	public void setcapacity(int capacity) {
		this.capacity = capacity;
	}
	
}
