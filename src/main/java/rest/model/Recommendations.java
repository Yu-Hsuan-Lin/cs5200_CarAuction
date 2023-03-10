package rest.model;

import java.util.Date;

public class Recommendations {
	protected int recommendationId;
	protected Users user;
	protected Restaurants restaurantId;
    protected Date created;
	protected String recommendation ;
	protected Float rating;
	
	public Recommendations(int recommendationId, Users user, Restaurants restaurantId) {
		this.recommendationId = recommendationId;
		this.user = user;
		this.restaurantId = restaurantId;

	}
	
	public Recommendations(int recommendationId) {
		this.recommendationId = recommendationId;
	}
	
	public Recommendations(Users user, Restaurants restaurantId) {
        this.user = user;
        this.restaurantId = restaurantId;
        }

	/** Getters and setters. */

	public int getrecommendationId() {
		return recommendationId;
	}

	public void setrecommendationId(int recommendationId) {
		this.recommendationId = recommendationId;
	}

	public Users getuser() {
		return user;
	}

	public void setuser(Users user) {
		this.user = user;
	}

	public Restaurants getrestaurantId() {
		return restaurantId;
	}

	public void setrestaurantId(Restaurants restaurantId) {
		this.restaurantId = restaurantId;
	}
}
