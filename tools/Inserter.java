package carAuction.tools;

import carAuction.dal.*;
import carAuction.model.*;
import carAuction.model.ChatHistory.ServiceType;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


public class Inserter {

  public static void main(String[] args) throws SQLException {
    // DAO instances.
    ChatHistoryDao chatHistoryDao = ChatHistoryDao.getInstance();
    CollectionDao collectionDao = CollectionDao.getInstance();


    // INSERT objects from our model.
    Date date = new Date();
    ChatHistory chatHistory1 = new ChatHistory("111111","111112","111113",
        new Date(2001, 1, 1), ServiceType.Account);
    chatHistory1 = chatHistoryDao.create(chatHistory1);

    ChatHistory chatHistory2 = new ChatHistory("222221","222222","222223",
        new Date(2002, 2, 2), ServiceType.Legal);
    chatHistory2 = chatHistoryDao.create(chatHistory2);

    ChatHistory chatHistory3= new ChatHistory("333331","333332","333333",
        new Date(2003, 3, 3), ServiceType.Auction);
    chatHistory3 = chatHistoryDao.create(chatHistory3);

    Collection collection1 = new Collection("c11111","u11111","a11111",
        true,true);
    collection1 = collectionDao.create(collection1);

    Collection collection2 = new Collection("c22222","u22222","a22222",
        true,true);
    collection2 = collectionDao.create(collection2);

    Collection collection3 = new Collection("c33333","u33333","a33333",
        false,false);
    collection3 = collectionDao.create(collection3);


    // READ.
    ChatHistory c1 = chatHistoryDao.getChatHistoryByUserID("111111");
    System.out.format("Reading Forum:  ChatID:%s   CustomerServiceID:%s  UserID:%s  "
            + "Time:%s  ServiceType%s  \n",
        c1.getChatID(), c1.getCustomerServiceID(),
        c1.getUserID(), c1.getTimeStamp(), c1.getServiceType());

    ChatHistory c2 = chatHistoryDao.updateServiceType(chatHistory1, ServiceType.Other);
    System.out.format("Reading Forum:  ChatID:%s   CustomerServiceID:%s  UserID:%s  "
            + "Time:%s  ServiceType%s  \n",
        c2.getChatID(), c2.getCustomerServiceID(),
        c2.getUserID(), c2.getTimeStamp(), c2.getServiceType());


    Collection cl1 = collectionDao.getCollectionById("c11111");
    System.out.format("Reading Forum:  CollectionId:%s   UserID:%s  AuctionID:%s Time:%s"
            + "  PriceChangeAlert%s StatusChangeAlert:%s \n",
        cl1.getCollectionId(), cl1.getUserID(),
        cl1.getAuctionID(), cl1.getPriceChangeAlert(), cl1.getStatusChangeAlert());

    Collection cl2 = collectionDao.updatePriceChangeAlert(collection2, false);
    System.out.format("Reading Forum:  CollectionId:%s   UserID:%s  AuctionID:%s Time:%s"
            + "  PriceChangeAlert%s StatusChangeAlert:%s \n",
        cl2.getCollectionId(), cl2.getUserID(),
        cl2.getAuctionID(), cl2.getPriceChangeAlert(), cl2.getStatusChangeAlert());


}
}