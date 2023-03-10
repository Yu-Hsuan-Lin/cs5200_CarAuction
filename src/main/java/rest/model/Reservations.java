package rest.model;

import java.util.Date;

public class Reservations {
	protected int reservationId;
	protected Users user;
	protected SitDownRestaurants restaurantId;
    protected Date startTime;
	protected Date endTime;
	protected int partySize;
	
	public Reservations(int reservationId, Users user, SitDownRestaurants restaurantId,
                    Date startTime,  Date endTime, int partySize) {
		this.reservationId = reservationId;
		this.user = user;
		this.restaurantId = restaurantId;
        this.startTime = startTime;
		this.endTime = endTime;
		this.partySize = partySize;
	}
	
	public Reservations(int reservationId) {
		this.reservationId = reservationId;
	}
	
	public Reservations(Users user, SitDownRestaurants restaurantId,
                    Date startTime,  Date endTime, int partySize) {
        this.user = user;
        this.restaurantId = restaurantId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.partySize = partySize;
        }

	/** Getters and setters. */

	public int getreservationId() {
		return reservationId;
	}

	public void setreservationId(int reservationId) {
		this.reservationId = reservationId;
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

	public void setrestaurantId(SitDownRestaurants restaurantId) {
		this.restaurantId = restaurantId;
	}

    public Date getstartTime() {
		return startTime;
	}

	public void setstartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getendTime() {
		return endTime;
	}

	public void setendTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getpartySize() {
		return partySize;
	}

	public void setpartySize(int partySize) {
		this.partySize = partySize;
	}
}
