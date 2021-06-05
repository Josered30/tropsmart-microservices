package com.softper.userservice.services;

//import com.softper.userservice.resources.comunications.CustomerResponse;
//import com.softper.userservice.resources.comunications.DriverResponse;
import com.softper.userservice.models.User;
import com.softper.userservice.resources.comunications.*;

public interface IUserService extends ICrudService<User>{
    UserBoundResponse setFavourited(int userId, int favoriteId);
    UserBoundResponse setBlocked(int userId, int blockedId);
    UserBoundResponse findAllUsers();
    UserBoundResponse findAllUsersByType(int userType);
    UserBoundResponse findFavoritesByUserId(int userId);
    UserBoundResponse findBlockedsByUserId(int userId);
    UserBoundResponse findAllFavourites();
    UserBoundResponse findAllBlockeds();
    UserBoundResponse findUserByEmail(String email);
    UserBoundResponse findUserById(int userId);
    UserBoundResponse findFavoriteByUserIdAndFavoriteId(int userId, int favouriteId);
    UserBoundResponse findBlockByUserIdAndBlockedId(int userId, int blockedId);
    UserBoundResponse deleteFavoriteByUserIdAndFavoriteId(int userId, int favoriteId);
    UserBoundResponse deleteBlockByUserIdAndBlockId(int userId, int blockedId);
    UserBoundResponse findUserByPersonId(int personid);

    //CustomerResponse findCustomerByUserId(int userId);
    //DriverResponse findDriverByUserId(int userId);

}
