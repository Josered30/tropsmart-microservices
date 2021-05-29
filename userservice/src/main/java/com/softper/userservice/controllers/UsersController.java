package com.softper.userservice.controllers;


import com.softper.userservice.resources.comunications.*;
import com.softper.userservice.servicesImp.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/userservice/users")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<UserBoundResponse> findAllUsers()
    {
        UserBoundResponse result = userService.findAllUsers();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("{userId}/favorites/{userfavoritedId}")
    public ResponseEntity<UserBoundResponse> setUserFavorited(@PathVariable(value = "userId")int userId, @PathVariable(value = "userfavoritedId")int userFavouritedId)
    {
        UserBoundResponse result = userService.setFavourited(userId, userFavouritedId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("/{userId}/blockeds/{userBlockedId}")
    public ResponseEntity<UserBoundResponse> setUserBlocked(@PathVariable(value = "userId")int userId, @PathVariable(value = "userBlockedId")int userBlockedId)
    {
        UserBoundResponse result = userService.setBlocked(userId, userBlockedId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserBoundResponse> findUserById(@PathVariable(value = "userId")int userId)
    {
        UserBoundResponse result = userService.findUserById(userId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //========================================================================

    @GetMapping("/{userId}/favorites")
    public ResponseEntity<UserBoundResponse> findFavoritesByUserId(@PathVariable(value = "userId")int userId)
    {
        UserBoundResponse result = userService.findFavoritesByUserId(userId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{userId}/blockeds")
    public ResponseEntity<UserBoundResponse> findBlockedsByUserId(@PathVariable(value = "userId")int userId)
    {
        UserBoundResponse result = userService.findBlockedsByUserId(userId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/favorites")
    public ResponseEntity<UserBoundResponse> findAllFavourites()
    {
        UserBoundResponse result = userService.findAllFavourites();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/blockeds")
    public ResponseEntity<UserBoundResponse> findAllBlockeds()
    {
        UserBoundResponse result = userService.findAllBlockeds();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    /*
    @GetMapping("/{userId}/customers")
    public ResponseEntity<CustomerResponse> findCustomerByUserId(@PathVariable(value = "userId")int userId)
    {
        CustomerResponse result = userService.findCustomerByUserId(userId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    */
    /*
    @GetMapping("/{userId}/drivers")
    public ResponseEntity<DriverResponse> findDriverByUserId(@PathVariable(value = "userId")int userId)
    {
        DriverResponse result = userService.findDriverByUserId(userId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    */

    @GetMapping("/type/{userType}")
    public ResponseEntity<UserBoundResponse> findUsersTypeCustomers(@PathVariable(value="userType")int userType)
    {
        UserBoundResponse result = userService.findAllUsersByType(userType);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //====================================================================

    @DeleteMapping("{userId}/favorites/{userFavoritedId}")
    public ResponseEntity<UserBoundResponse> deleteFavoriteByUserIdAndUserFavoritedId(@PathVariable(value="userId")int userId, @PathVariable(value = "userFavorited")int userFavoritedId)
    {
        UserBoundResponse result = userService.deleteFavoriteByUserIdAndFavoriteId(userId, userFavoritedId);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @DeleteMapping("{userId}/blockeds/{userBlockedId}")
    public ResponseEntity<UserBoundResponse> deleteBlockedByUserIdAndBlockedId(@PathVariable(value = "userId")int userId, @PathVariable(value = "userBlockedId")int userBlockedId)
    {
        UserBoundResponse result = userService.deleteBlockByUserIdAndBlockId(userId, userBlockedId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
