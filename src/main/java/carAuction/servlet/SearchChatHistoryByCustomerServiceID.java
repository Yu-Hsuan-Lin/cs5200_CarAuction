package carAuction.servlet;

import carAuction.dal.*;
import carAuction.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/searchchathistorybycustomerserviceID ")

public class SearchChatHistoryByCustomerServiceID extends HttpServlet  {
	
	protected ChatHistoriesDao chathistoriesDao;
	protected CustomerServicesDao customerServicesDao;
	
	public void init() throws ServletException {
		chathistoriesDao = ChatHistoriesDao.getInstance();
		customerServicesDao =  CustomerServicesDao.getInstance();
	}
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        List<ChatHistories > chatHistories = new ArrayList<ChatHistories >();
        
        int customerServiceID = -1;
       
        try {
        	customerServiceID = Integer.parseInt(req.getParameter("customerServiceID"));
        } catch (NumberFormatException e) {
        	e.printStackTrace();
			throw new IOException(e);
        }
        
        if (customerServiceID < 0) {
            messages.put("success", "Please enter valid CustomerServiceID.");
        } else {
        
        	try {
        		
        		
        		chatHistories = chathistoriesDao.getChatHistoriesByCustomerServiceID(customerServiceID);
        				
//        		Users user = usersDao.getUserFromUserID(userid);
//        		forums = forumsDao.getForumForUser(user);
        		messages.put("success", "Displaying Forums posted by User " + customerServiceID);
        		req.setAttribute("chatHistories", chatHistories);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        }
       
        req.getRequestDispatcher("/searchchathistorybycustomerserviceID.jsp").forward(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //List<Forums> forums = new ArrayList<Forums>();
        List<ChatHistories > chatHistories = new ArrayList<ChatHistories >();
        

        //int userid = -1;
        int customerServiceID = -1;
        try {
        	customerServiceID = Integer.parseInt(req.getParameter("customerServiceID"));
        } catch (NumberFormatException e) {
        	e.printStackTrace();
			throw new IOException(e);
        }
        
        if (customerServiceID < 0) {
            messages.put("success", "Please enter valid UcustomerServiceID.");
        } else {
        
        	try {
        		
        		chatHistories = 
        				chathistoriesDao.getChatHistoriesByCustomerServiceID(customerServiceID);
        			
//        		Users user = usersDao.getUserFromUserID(userid);
//        		forums = forumsDao.getForumForUser(user);
        		messages.put("success", "Displaying chatHistories posted by CustomerServiceID" + customerServiceID);
        		req.setAttribute("chatHistories", chatHistories);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        }
       
        req.getRequestDispatcher("/searchchathistorybycustomerserviceID.jsp").forward(req, resp);
	}


}