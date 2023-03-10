package rest.tools;

import rest.dal.*;
import rest.model.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


/**
 * main() runner, used for the app demo.
 * 
 * Instructions:
 * 1. Create a new MySQL schema and then run the CREATE TABLE statements from lecture:
 * 2. Update ConnectionManager with the correct user, password, and schema.
 */
public class Inserter {

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		UsersDao usersDao = UsersDao.getInstance();
		CreditCardsDao creditCardsDao = CreditCardsDao.getInstance();
		CompaniesDao companiesDao = CompaniesDao.getInstance();
		RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
		SitDownRestaurantsDao sitDownRestaurantsDao = SitDownRestaurantsDao.getInstance();
		TakeOutRestaurantsDao takeOutRestaurantsDao = TakeOutRestaurantsDao.getInstance();
		FoodCartRestaurantsDao foodCartRestaurantsDao = FoodCartRestaurantsDao.getInstance();
		ReviewsDao reviewsDao = ReviewsDao.getInstance();
		RecommendationsDao recommendationsDao = RecommendationsDao.getInstance();
		ReservationsDao reservationsDao = ReservationsDao.getInstance();
		
		// INSERT objects from our model.
		Users user1 = new Users("abc", "abcabc", "AAA", "BC", "abc@northeastern.edu", "000-000-0000");
		user1 = usersDao.create(user1);
		Users user2 = new Users("xyz", "xyzxzy", "XXX", "YZ", "xyz@northeasternedu", "111-111-1111");
		user2 = usersDao.create(user2);
		Users user3 = new Users("ijk", "ijkijk", "III", "JK", "ijk@northeasternedu", "222-222-2222");
		user3 = usersDao.create(user3);

		Date date = new Date();
		
		CreditCards creditCard1 = new CreditCards(403003344455L, new Date(125, 9, 3) , user1);
		creditCard1 = creditCardsDao.create(creditCard1);
		CreditCards creditCard2 = new CreditCards(439394030302L, new Date(125, 1, 2) , user2);
		creditCard2 = creditCardsDao.create(creditCard2);
		CreditCards creditCard3 = new CreditCards(494904233344L, new Date(125, 0, 25) , user3);
		creditCard3 = creditCardsDao.create(creditCard3);

		Companies company1 = new Companies("company_A", "Largest restraunt company worldwide");
		company1 = companiesDao.create(company1);
		Companies company2 = new Companies("company_B", "Largest restraunt company in the U.S.");
		company2 = companiesDao.create(company2);
		Companies company3 = new Companies("company_C", "Largest restraunt company in Seattle");
		company3 = companiesDao.create(company3);

		SitDownRestaurants sitDownRestaurant1 = new SitDownRestaurants(1, "Restaurant_A", "Chinese food", "egg, shrimp", "12:00-21:00 everyday",
		 true, "AA street unit1", "", "Seattle", "WA", "98101", Restaurants.CuisineType.asian, company1, 15);
		sitDownRestaurant1 = sitDownRestaurantsDao.create(sitDownRestaurant1);
		SitDownRestaurants sitDownRestaurant2 = new SitDownRestaurants(2, "Restaurant_B", "African food", "Piri piri chicken", "12:00-21:00 everyday", 
		true, "BB street unit2", "", "Seattle", "WA", "98131", Restaurants.CuisineType.african, company1, 14);
		sitDownRestaurant1 = sitDownRestaurantsDao.create(sitDownRestaurant2);

		TakeOutRestaurants takeOutRestaurant1 = new TakeOutRestaurants(3, "Restaurant_C", "American food", "burger", "12:00-21:00 everyday", 
		false, "CC street unit3", "", "Seattle", "WA", "98031", Restaurants.CuisineType.american, company1, 180);
		takeOutRestaurant1 = takeOutRestaurantsDao.create(takeOutRestaurant1);
		TakeOutRestaurants takeOutRestaurant2 = new TakeOutRestaurants(4, "Restaurant_D", "European food", "pizza, pasta", "12:00-21:00 everyday", 
		true, "DD street unit4", "", "Seattle", "WA", "98141", Restaurants.CuisineType.european, company2, 360);
		takeOutRestaurant2 = takeOutRestaurantsDao.create(takeOutRestaurant2);

		FoodCartRestaurants foodCartRestaurant1 = new FoodCartRestaurants(5, "Restaurant_E", "Hispanic food", "Tacos", "12:00-21:00 everyday", 
		true, "EE street unit5", "", "Seattle", "WA", "98341", Restaurants.CuisineType.hispanic, company2, true);
		foodCartRestaurant1 = foodCartRestaurantsDao.create(foodCartRestaurant1);
		FoodCartRestaurants foodCartRestaurant2 = new FoodCartRestaurants(6, "Restaurant_F", "Hispanic food", "Paella Valenciana", "12:00-21:00 everyday", 
		true, "FF street unit1", "", "Seattle", "WA", "98141", Restaurants.CuisineType.hispanic, company3, true);
		foodCartRestaurant2 = foodCartRestaurantsDao.create(foodCartRestaurant2);
		
		Restaurants Restaurant7 = new Restaurants( "Restaurant_", "Chinese food", "egg, shrimp", "12:00-21:00 everyday",
				 true, "AA street unit1", "", "Seattle", "WA", "98101", Restaurants.CuisineType.asian, company1);

		Reviews review1= new Reviews(user1, sitDownRestaurant1, new Date(105, 6, 29), "good", 4.5f);
		review1 = reviewsDao.create(review1);
		Reviews review2 = new Reviews(user2, sitDownRestaurant2, new Date(100, 2, 13) , "fair", 3.5f);
		review2 = reviewsDao.create(review2);
		Reviews review3 = new Reviews(user3, foodCartRestaurant2, new Date(111, 9, 17) , "not recommend", 1.5f);
		review3 = reviewsDao.create(review3);

		Recommendations recommendation1= new Recommendations(user2, takeOutRestaurant1);
		recommendation1 = recommendationsDao.create(recommendation1);
		Recommendations recommendation2 = new Recommendations(user2, foodCartRestaurant2);
		recommendation2 = recommendationsDao.create(recommendation2);
		Recommendations recommendation3 = new Recommendations(user3, foodCartRestaurant1);
		recommendation3 = recommendationsDao.create(recommendation3);

		Reservations reservation1= new Reservations(user1, sitDownRestaurant1, date , date, 4);
		reservation1 = reservationsDao.create(reservation1);
		Reservations reservation2 = new Reservations(user2, sitDownRestaurant2, date, date, 3);
		reservation2 = reservationsDao.create(reservation2);
		Reservations reservation3 = new Reservations(user3, sitDownRestaurant2, date, date, 5);
		reservation3 = reservationsDao.create(reservation3);

		// READ.
		Users u1 = usersDao.getUserFromusername("abc");
		System.out.format("Reading user: u:%s p:%s f:%s l:%s e:%s p:%s \n",
			u1.getusername(), u1.getpassword(), u1.getfirstName(),
			u1.getlastName(), u1.getemail(), u1.getphoneNumber());
	
		CreditCards cd1 = creditCardsDao.getCreditCardByCardNumber(403003344455L);
		List<CreditCards> cdList1 = creditCardsDao.getCreditCardsByUserName("abc");
		System.out.format("Reading creditCard: c:%s e:%s u:%s \n",
			cd1.getcardNumber(), cd1.getexpirationDate(), cd1.getuser());
		for(CreditCards cd : cdList1) {
			System.out.format("Looping creditCards: c:%s e:%s u:%s \n",
			cd.getcardNumber(), cd.getexpirationDate(), cd.getuser());
		}

		Companies c1 = companiesDao.getCompanyByCompanyName("company_A");
		System.out.format("Reading person: c:%s d:%s \n",
			c1.getcompanyname(), c1.getdescription());

		Restaurants r1 = restaurantsDao.getRestaurantById(1);
		List<Restaurants> rList1 = restaurantsDao.getRestaurantsByCuisine(Restaurants.CuisineType.asian);
		List<Restaurants> rList2 = restaurantsDao.getRestaurantsByCompanyName(company1);
		System.out.format("Reading restaurant: r:%s n:%s d:%s m:%s h:%s i:%s s:%s s:%s c:%s s:%s z:%s c:%s c:%s \n",
			r1.getrestaurantId(), r1.getname(), r1.getdescription(), r1.getmenu(), r1.gethours(),
			r1.getisActive(), r1.getstreet1(), r1.getstreet2(), r1.getcity(), r1.getstate(), 
			r1.getzip(), r1.getCuisineType().name(), r1.getcompany());
		for(Restaurants r : rList1) {
			System.out.format("Reading restaurants: r:%s n:%s d:%s m:%s h:%s i:%s s:%s s:%s c:%s s:%s z:%s c:%s c:%s \n",
			r.getrestaurantId(), r.getname(), r.getdescription(), r.getmenu(), r.gethours(),
			r.getisActive(), r.getstreet1(), r.getstreet2(), r.getcity(), r.getstate(), 
			r.getzip(), r.getCuisineType().name(), r.getcompany());
		}
		for(Restaurants r : rList2) {
			System.out.format("Reading restaurants: r:%s n:%s d:%s m:%s h:%s i:%s s:%s s:%s c:%s s:%s z:%s c:%s c:%s \n",
			r.getrestaurantId(), r.getname(), r.getdescription(), r.getmenu(), r.gethours(),
			r.getisActive(), r.getstreet1(), r.getstreet2(), r.getcity(), r.getstate(), 
			r.getzip(), r.getCuisineType().name(), r.getcompany());
		}


		SitDownRestaurants s1 = sitDownRestaurantsDao.getSitDownRestaurantById(1);
		List<SitDownRestaurants> sList1 = sitDownRestaurantsDao.getSitDownRestaurantsByCompanyName(company1);
		System.out.format("Reading sitDownRestaurant: r:%s n:%s d:%s m:%s h:%s i:%s s:%s s:%s c:%s s:%s z:%s c:%s c:%s c:%s \n",
		s1.getrestaurantId(), s1.getname(), s1.getdescription(), s1.getmenu(), s1.gethours(),
		s1.getisActive(), s1.getstreet1(), s1.getstreet2(), s1.getcity(), s1.getstate(), 
		s1.getzip(), s1.getCuisineType().name(), s1.getcompany(), s1.getcapacity());
		for(SitDownRestaurants s : sList1) {
			System.out.format("Looping sitDownRestaurants: r:%s n:%s d:%s m:%s h:%s i:%s s:%s s:%s c:%s s:%s z:%s c:%s c:%s c:%s \n",
			s.getrestaurantId(), s.getname(), s.getdescription(), s.getmenu(), s.gethours(),
			s.getisActive(), s.getstreet1(), s.getstreet2(), s.getcity(), s.getstate(), 
			s.getzip(), s.getCuisineType().name(), s.getcompany(), s.getcapacity());
		}

		TakeOutRestaurants t1 = takeOutRestaurantsDao.getTakeOutRestaurantById(3);
		List<TakeOutRestaurants> tList1 = takeOutRestaurantsDao.getTakeOutRestaurantsByCompanyName(company1);
		System.out.format("Reading takeOutRestaurant: r:%s n:%s d:%s m:%s h:%s i:%s s:%s s:%s c:%s s:%s z:%s c:%s c:%s m:%s \n",
		t1.getrestaurantId(), t1.getname(), t1.getdescription(), t1.getmenu(), t1.gethours(),
		t1.getisActive(), t1.getstreet1(), t1.getstreet2(), t1.getcity(), t1.getstate(), 
		t1.getzip(), t1.getCuisineType().name(), t1.getcompany(), t1.getmaxWaitTime());
		for(TakeOutRestaurants t : tList1) {
			System.out.format("Looping takeOutRestaurants: r:%s n:%s d:%s m:%s h:%s i:%s s:%s s:%s c:%s s:%s z:%s c:%s c:%s m:%s \n",
			t.getrestaurantId(), t.getname(), t.getdescription(), t.getmenu(), t.gethours(),
			t.getisActive(), t.getstreet1(), t.getstreet2(), t.getcity(), t.getstate(), 
			t.getzip(), t.getCuisineType().name(), t.getcompany(), t.getmaxWaitTime());
		}

		FoodCartRestaurants f1 = foodCartRestaurantsDao.getFoodCartRestaurantById(5);
		List<FoodCartRestaurants> fList1 = foodCartRestaurantsDao.getFoodCartRestaurantsByCompanyName(company1);
		System.out.format("Reading foodCartRestaurant: r:%s n:%s d:%s m:%s h:%s i:%s s:%s s:%s c:%s s:%s z:%s c:%s c:%s l:%s \n",
		f1.getrestaurantId(), f1.getname(), f1.getdescription(), f1.getmenu(), f1.gethours(),
		f1.getisActive(), f1.getstreet1(), f1.getstreet2(), f1.getcity(), f1.getstate(), 
		f1.getzip(), f1.getCuisineType().name(),f1.getcompany(), f1.getisLicensed());
		for(FoodCartRestaurants f : fList1) {
			System.out.format("Looping foodCartRestaurants: r:%s n:%s d:%s m:%s h:%s i:%s s:%s s:%s c:%s s:%s z:%s c:%s c:%s l:%s \n",
			f.getrestaurantId(), f.getname(), f.getdescription(), f.getmenu(), f.gethours(),
			f.getisActive(), f.getstreet1(), f.getstreet2(), f.getcity(), f.getstate(), 
			f.getzip(), f.getCuisineType().name(), f.getcompany(), f.getisLicensed());
		}
		
		Reviews rw1 = reviewsDao.getReviewById(1);
		List<Reviews> rwList1 = reviewsDao.getReviewsByUserName(user1);
		List<Reviews> rwList2 = reviewsDao.getReviewsByRestaurantId(sitDownRestaurant1);
		System.out.format("Reading review: r:%s u:%s r:%s c:%s r:%s r:%s \n",
			rw1.getreviewId(), rw1.getuser(), rw1.getrestaurantId(), 
			rw1.getcreated(), rw1.getreview(), rw1.getrating());
		for(Reviews rw : rwList1) {
			System.out.format("Looping reviews: r:%s u:%s r:%s c:%s r:%s r:%s \n",
			rw.getreviewId(), rw.getuser(), rw.getrestaurantId(), 
			rw.getcreated(), rw.getreview(), rw.getrating());
		}
		for(Reviews rw2 : rwList2) {
			System.out.format("Looping reviews: r:%s u:%s r:%s c:%s r:%s r:%s \n",
			rw2.getreviewId(), rw2.getuser(), rw2.getrestaurantId(), 
			rw2.getcreated(), rw2.getreview(), rw2.getrating());
		}

		Recommendations rc1 = recommendationsDao.getRecommendationById(1);
		List<Recommendations> rcList1 = recommendationsDao.getRecommendationsByUserName(user2);
		List<Recommendations> rcList2 = recommendationsDao.getRecommendationsByRestaurantId(takeOutRestaurant1);
		System.out.format("Reading recommendation: r:%s u:%s \n",
				rc1.getrecommendationId(), rc1.getuser());
		for(Recommendations rc : rcList1) {
			System.out.format("Looping recommendations: r:%s u:%s \n",
			rc.getrecommendationId(), rc.getuser());
		}
		for(Recommendations rc2 : rcList2) {
			System.out.format("Looping recommendations: r:%s u:%s \n",
			rc2.getrecommendationId(), rc2.getuser());
		}
		
		Reservations rv1 = reservationsDao.getReservationById(1);
		List<Reservations> rvList1 = reservationsDao.getReservationsByUserName(user1);
		List<Reservations> rvList2 = reservationsDao.getReservationsByRestaurantId(sitDownRestaurant1);
		System.out.format("Reading reservation: r:%s u:%s r:%s s:%s e:%s p:%s \n",
			rv1.getreservationId(), rv1.getuser(), rv1.getrestaurantId(), 
			rv1.getstartTime(), rv1.getendTime(), rv1.getpartySize());
		for(Reservations rv : rvList1) {
			System.out.format("Looping reservations: r:%s u:%s r:%s s:%s e:%s p:%s \n",
			rv.getreservationId(), rv.getuser(), rv.getrestaurantId(), 
			rv.getstartTime(), rv.getendTime(), rv.getpartySize());
		}
		for(Reservations rv2 : rvList2) {
			System.out.format("Looping reservations: r:%s u:%s r:%s s:%s e:%s p:%s \n",
			rv2.getreservationId(), rv2.getuser(), rv2.getrestaurantId(), 
			rv2.getstartTime(), rv2.getendTime(), rv2.getpartySize());
		}
		
		// UPDATE
		
		creditCardsDao.updateExpiration(creditCard1, new Date(125, 4, 23));
		companiesDao.updateDescription(company1, "Largest restraunt company worldwide in the restaurant industry");
		

		//DELETE
		
		reservationsDao.delete(reservation1);
		recommendationsDao.delete(recommendation1);
		reviewsDao.delete(review1);
		foodCartRestaurantsDao.delete(foodCartRestaurant1);
		takeOutRestaurantsDao.delete(takeOutRestaurant1);
		sitDownRestaurantsDao.delete(sitDownRestaurant1);
 		restaurantsDao.delete(Restaurant7);
		companiesDao.delete(company1);
		creditCardsDao.delete(creditCard1);
		usersDao.delete(user1);
		
		
		
		
		
		
		
		
		
	}



}