package carAuction.servlet;

import carAuction.dal.*;
import carAuction.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/creditcardupdate")
public class CreditCardUpdate extends HttpServlet {
	
	protected CreditCardsDao creditCardsDao;
	
	@Override
	public void init() throws ServletException {
		creditCardsDao = CreditCardsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);


        String cardNumber = req.getParameter("cardnumber");
        if (cardNumber == null || cardNumber.trim().isEmpty()) {
            messages.put("success", "Please enter a valid cardNumber.");
        } else {
        	try {
        		CreditCards creditCard = creditCardsDao.getCreditCardByCardNumber(cardNumber);
        		if(creditCard == null) {
        			messages.put("success", "CreditCard does not exist.");
        		}
        		req.setAttribute("creditCard", creditCard);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        } 
        }req.getRequestDispatcher("/CreditCardUpdate.jsp").forward(req, resp);
        
       
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);


        String cardNumber = req.getParameter("cardnumber");      
        if (cardNumber == null || cardNumber.trim().isEmpty()) {
            messages.put("success", "Please enter a valid cardNumber.");
        } else {
        	try {
        		CreditCards creditCard = creditCardsDao.getCreditCardByCardNumber(cardNumber);
        		if(creditCard == null) {
        			messages.put("success", "CreditCard does not exist. No update to perform.");
        		} else {
        			String newNameOnCard = req.getParameter("newnameoncard");	
        			if (newNameOnCard == null || newNameOnCard.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid new NameOnCard.");
        	        } else {
        	        	creditCard = creditCardsDao.updateNameOnCard(creditCard, newNameOnCard);
        	        	messages.put("success", "NameOnCard has been updated successfully.");       
        	        }
        		}
			req.setAttribute("creditCard", creditCard);
		} catch (SQLException e) {
		e.printStackTrace();
		throw new IOException(e);
		}
		}
		
		req.getRequestDispatcher("/CreditCardUpdate.jsp").forward(req, resp);
		}
		}
