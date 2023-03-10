package rest.model;

import java.util.Date;

public class Reviews {
	protected int reviewId;
	protected Users user;
	protected Restaurants restaurantId;
    protected Date created;
	protected String review ;
	protected Float rating;
	
	public Reviews(int reviewId, Users user, Restaurants restaurantId,
                    Date created,  String review , Float rating) {
		this.reviewId = reviewId;
		this.user = user;
		this.restaurantId = restaurantId;
        this.created = created;
		this.review  = review ;
		this.rating = rating;
	}
	
	public Reviews(int reviewId) {
		this.reviewId = reviewId;
	}
	
	public Reviews(Users user, Restaurants restaurantId,
                    Date created,  String review , Float rating) {
        this.user = user;
        this.restaurantId = restaurantId;
        this.created = created;
        this.review  = review ;
        this.rating = rating;
        }

	/** Getters and setters. */

	public int getreviewId() {
		return reviewId;
	}

	public void setreviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public Users getuser() {
		return user;
	}

	public void setusername(Users user) {
		this.user = user;
	}

	public Restaurants getrestaurantId() {
		return restaurantId;
	}

	public void setrestaurantId(Restaurants restaurantId) {
		this.restaurantId = restaurantId;
	}

    public Date getcreated() {
		return created;
	}

	public void setcreated(Date created) {
		this.created = created;
	}

	public String getreview () {
		return review;
	}

	public void setreview (String review ) {
		this.review  = review ;
	}

	public Float getrating() {
		return rating;
	}

	public void setrating(Float rating) {
		this.rating = rating;
	}
}
