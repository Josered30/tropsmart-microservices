package com.softper.userservice.servicesImp;

import com.softper.userservice.client.CustomerClient;
import com.softper.userservice.client.DriverClient;
//import com.softper.userservice.resources.comunications.CustomerResponse;
//import com.softper.userservice.resources.outputs.CustomerOutput;
//import com.softper.userservice.resources.comunications.DriverResponse;
//import com.softper.userservice.resources.outputs.DriverOutput;
import com.softper.userservice.exception.ResourceNotFoundException;
import com.softper.userservice.models.*;
import com.softper.userservice.repositories.IBlockRepository;
import com.softper.userservice.repositories.IFavoriteRepository;
import com.softper.userservice.repositories.IUserRepository;
import com.softper.userservice.resources.comunications.*;
import com.softper.userservice.resources.outputs.*;
import com.softper.userservice.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IFavoriteRepository favoriteRepository;

    @Autowired
    private IBlockRepository blockRepository;

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private DriverClient driverClient;

    
    @Override
    public UserBoundResponse setFavourited(int userId, int favouriteId){
        
        try
        {
            UserBoundResponse response;
            User getUser = userRepository.findById(userId)
                    .orElseThrow(()->new ResourceNotFoundException("id","User",userId));

            User getUserFavourite = userRepository.findById(favouriteId)
                    .orElseThrow(()->new ResourceNotFoundException("id","User",favouriteId));

            if(getUser.getPerson().getPersonType()==2 || getUserFavourite.getPerson().getPersonType()==1)
            {
                return new UserBoundResponse("setFavourited","Only customers can add driver as favourite",0);
            }
            Favorite newFavourite = new Favorite();
            newFavourite.setUser(getUser);
            newFavourite.setFavorited(getUserFavourite);
            newFavourite.setCreatedAt(new Date());
            newFavourite = favoriteRepository.save(newFavourite);

            response = new UserBoundResponse("setFavourited","success",1);
            response.setFavoriteOutput(toFavoriteOutput(newFavourite));
            return response;
        }
        catch (Exception e)
        {
            return new UserBoundResponse("setFavourited","An error ocurred : "+e.getMessage(),-2);
        }
    }

    @Override
    public UserBoundResponse setBlocked(int userId, int blockedId) {
        
        try
        {
            UserBoundResponse response;
            User getUser = userRepository.findById(userId)
                    .orElseThrow(()->new ResourceNotFoundException("id","User",userId));

            User getUserBlocked = userRepository.findById(blockedId)
                    .orElseThrow(()->new ResourceNotFoundException("id","User",blockedId));

            Block newBlock =  new Block();
            newBlock.setUser(getUser);
            newBlock.setBlocked(getUserBlocked);
            newBlock.setCreatedAt(new Date());

            newBlock = blockRepository.save(newBlock);

            response = new UserBoundResponse("setBlocked","success",1);
            response.setBlockedOutput(toBlockedOutput(newBlock));
            return response;
        }
        catch (Exception e)
        {
            return new UserBoundResponse("setBlocked","An error ocurred : "+e.getMessage(),-2);
        }        
    }

    @Override
    public UserBoundResponse findAllUsers() {
        
        try
        {
            UserBoundResponse response;
            List<User> userList = userRepository.findAll();
            List<UserOutput> userOutputList = new ArrayList<>();
            for (User u:userList) {
                userOutputList.add(toUserOutput(u));
            }
            response = new UserBoundResponse("findAllUsers","success",1);
            response.setUserOutputs(userOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new UserBoundResponse("findAllUsers","An error ocurred : "+e.getMessage(),-2);
        }        
    }


    @Override
    public UserBoundResponse findFavoritesByUserId(int userId) {
        
        try
        {
            UserBoundResponse response;
            List<Favorite> favoriteList = favoriteRepository.findFavouritesByUserId(userId);
            List<FavoriteOutput> favouriteOutputList = new ArrayList<>();
            for (Favorite f:favoriteList) {
                favouriteOutputList.add(toFavoriteOutput(f));
            }
            response = new UserBoundResponse("findFavoritesByUserId","success",1);
            response.setFavoriteOutputs(favouriteOutputList); 
            return response;
        }
        catch (Exception e)
        {
            return new UserBoundResponse("findFavoritesByUserId","An error ocurred : "+e.getMessage(),-2);
        }
    }

    @Override
    public UserBoundResponse findBlockedsByUserId(int userId) {
        
        try
        {
            List<Block> blockList = blockRepository.findBlockedsByUserId(userId);
            List<BlockedOutput> blockedOutputList = new ArrayList<>();
            for (Block b:blockList) {
                blockedOutputList.add(toBlockedOutput(b));
            }
            UserBoundResponse response = new UserBoundResponse("findBlockedsByUserId","success",1);
            response.setBlockedOutputs(blockedOutputList); 
            return response;
        }
        catch (Exception e)
        {
            return new UserBoundResponse("findBlockedsByUserId","An error ocurred : "+e.getMessage(),-2);
        }
    }

    @Override
    public UserBoundResponse findAllFavourites() {
        
        try
        {
            List<Favorite> favoriteList = favoriteRepository.findAll();
            List<FavoriteOutput> favoriteOutputList = new ArrayList<>();
            for (Favorite f:favoriteList) {
                favoriteOutputList.add(toFavoriteOutput(f));
            }
            UserBoundResponse response = new UserBoundResponse("findAllFavourites","success",1);
            response.setFavoriteOutputs(favoriteOutputList); 
            return response;
        }
        catch (Exception e)
        {
            return new UserBoundResponse("findAllFavourites","An error ocurred : "+e.getMessage(),-2);

        }
    }

    @Override
    public UserBoundResponse findAllBlockeds() {
        
        try
        {
            List<Block> blockList = blockRepository.findAll();
            List<BlockedOutput> blockedOutputList = new ArrayList<>();
            for (Block b:blockList) {
                blockedOutputList.add(toBlockedOutput(b));
            }
            UserBoundResponse response = new UserBoundResponse("findAllBlockeds","success",1);
            response.setBlockedOutputs(blockedOutputList); 
            return response;
        }
        catch (Exception e)
        {
            return new UserBoundResponse("findAllBlockeds","An error ocurred : "+e.getMessage(),-2);
        }
    }

    @Override
    public UserBoundResponse findFavoriteByUserIdAndFavoriteId(int userId, int favouriteId) {
        
        try
        {
            Favorite getFavourite = favoriteRepository.findFavouriteByUserAndFavouriteId(userId, favouriteId);
            UserBoundResponse response = new UserBoundResponse("findFavoriteByUserIdAndFavoriteId","success",1);
            response.setFavoriteOutput(toFavoriteOutput(getFavourite)); 
            return response;
        }
        catch (Exception e)
        {
            return new UserBoundResponse("findFavoriteByUserIdAndFavoriteId","An error ocurred : "+e.getMessage(),-2);
        }
    }

    @Override
    public UserBoundResponse findBlockByUserIdAndBlockedId(int userId, int blockedId) {

        try
        {
            Block getBlock = blockRepository.findBlockByUserAndBlockedId(userId, blockedId);
            UserBoundResponse response = new UserBoundResponse("findBlockByUserIdAndBlockedId","success",1);
            response.setBlockedOutput(toBlockedOutput(getBlock)); 
            return response;
        }
        catch (Exception e)
        {
            return new UserBoundResponse("findBlockByUserIdAndBlockedId","An error ocurred : "+e.getMessage(),-2);
        }
    }

    @Override
    public UserBoundResponse findAllUsersByType(int userType) {
        
        try{
            List<User> userList = userRepository.findAll();
            List<UserOutput> userOutputList = new ArrayList<>();
            for (User u:userList) {
                if(u.getPerson().getPersonType()==userType) {
                    userOutputList.add(toUserOutput(u));
                }

            }
            UserBoundResponse response = new UserBoundResponse("findAllUsersByType","success",1);
            response.setUserOutputs(userOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new UserBoundResponse("findAllUsersByType","An error ocurred : "+e.getMessage(),-2);
        }
    }


    @Override
    public UserBoundResponse deleteFavoriteByUserIdAndFavoriteId(int userId, int favoriteId) {
        
        try
        {
            Favorite getFavorite = favoriteRepository.findFavouriteByUserAndFavouriteId(userId, favoriteId);
            favoriteRepository.deleteById(getFavorite.getId());
            UserBoundResponse response = new UserBoundResponse("deleteFavoriteByUserIdAndFavoriteId","success",1);
            response.setFavoriteOutput(toFavoriteOutput(getFavorite)); 
            return response;
        }
        catch (Exception e)
        {
            return new UserBoundResponse("deleteFavoriteByUserIdAndFavoriteId","An error ocurred : "+e.getMessage(),-2);
        }
    }

    @Override
    public UserBoundResponse deleteBlockByUserIdAndBlockId(int userId, int blockedId) {
        
        try
        {
            Block getBlock = blockRepository.findBlockByUserAndBlockedId(userId, blockedId);
            blockRepository.deleteById(getBlock.getId());
            UserBoundResponse response = new UserBoundResponse("deleteBlockByUserIdAndBlockId","success",1);
            response.setBlockedOutput(toBlockedOutput(getBlock)); 
            return response;
        }
        catch (Exception e)
        {
            return new UserBoundResponse("deleteBlockByUserIdAndBlockId","An error ocurred : "+e.getMessage(),-2);
        }
    }

    @Override
    public UserBoundResponse findUserByEmail(String email) {
        
        try
        {
            User getUser = userRepository.findPersonByEmail(email)
                    .orElseThrow(()->new ResourceNotFoundException("email","user",email));

            UserBoundResponse response = new UserBoundResponse("findUserByEmail","success",1);
            response.setUserOutput(toUserOutput(getUser));
            return response;
        }
        catch (Exception e)
        {
            return new UserBoundResponse("findUserByEmail","An error ocurred : "+e.getMessage(),-2);
        }
    }

    @Override
    public UserBoundResponse findUserById(int userId) {
        
        try
        {
            User getUser = userRepository.findById(userId)
                    .orElseThrow(()->new ResourceNotFoundException("User","id",userId));
            UserBoundResponse response = new UserBoundResponse("findUserById","success",1);
            response.setUserOutput(toUserOutput(getUser));
            return response;
        }
        catch (Exception e)
        {
            return new UserBoundResponse("findUserById","An error ocurred : "+e.getMessage(),-2);
        }
    }

    @Override
    public UserBoundResponse findUserByPersonId(int personId)
    {
        try{
            User getUser = userRepository.findUserByPersonId(personId)
                    .orElseThrow(()->new ResourceNotFoundException("User","personId",personId));
            UserBoundResponse response = new UserBoundResponse("findUserByPersonId", "success", 1);
            response.setUserOutput(toUserOutput(getUser));
            return response;
        } catch(Exception e)
        {
            return new UserBoundResponse("findUserByPersonId","An error ocurred : "+e.getMessage(),-2);
        }
    }


    /*
    @Override
    public UserBoundResponse findCustomerByUserId(int userId) {
        
        try
        {
            User getUser = userRepository.findById(userId)
                    .orElseThrow(()->new ResourceNotFoundException("userId","user",userId));
            Customer getCustomer = getUser.getPerson().getCustomer();
            UserBoundResponse response = new UserBoundResponse("findCustomerByUserId","success",1);
            response.setUserOutput(toUserOutput(getUser));
            return response;
            return new CustomerResponse(new CustomerOutput(getCustomer.getPerson().getUser().getId(),getCustomer.getPerson().getFirstName(),getCustomer.getPerson().getLastName(),getCustomer.getCredits(),getCustomer.getPerson().getUser().getEmail(), getCustomer.getPerson().getPersonType(), getCustomer.getId()));
        }
        catch (Exception e)
        {
            return new CustomerResponse("An error ocurred while getting customer: "+e.getMessage());
        }
    }
    */
    

    /*
    @Override
    public DriverResponse findDriverByUserId(int userId) {
        try
        {
            User getUser = userRepository.findById(userId)
                    .orElseThrow(()-> new ResourceNotFoundException("Id","user",userId));

            Driver getDriver = getUser.getPerson().getDriver();
            return new DriverResponse(new DriverOutput(getDriver.getId(),getDriver.getPerson().getFirstName(),getDriver.getPerson().getLastName(),getDriver.getLicense(),getDriver.getPerson().getUser().getEmail(),getDriver.getPerson().getPersonType(), getDriver.getId()));

        }
        catch (Exception e)
        {
            return new DriverResponse("An error ocurred while getting driver: "+e.getMessage());

        }
        
    }*/


    @Override
    public User save(User user) throws Exception {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        userRepository.deleteById(id);
    }


    @Override
    public Optional<User> findById(Integer id) throws Exception {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() throws Exception {
        return userRepository.findAll();
    }

    public UserOutput toUserOutput(User user){
        
        UserOutput newUserOutput = new UserOutput();
        newUserOutput.setId(user.getId());
        newUserOutput.setEmail(user.getEmail());
        newUserOutput.setFirstName(user.getPerson().getFirstName());
        newUserOutput.setLastName(user.getPerson().getLastName());
        newUserOutput.setPassword(user.getPassword());

        if(user.getPerson().getPersonType()==1)
            newUserOutput.setRole("Customer");
        else if(user.getPerson().getPersonType()==2)
            newUserOutput.setRole("Driver");

        if(user.getPerson().getPersonType()==1){
            //authenticatedOutput.setRoleId(getPerson.getCustomer().getId());
            Customer getCustomer = customerClient.getCustomerById(user.getPerson().getCustomerId()).getBody();
            newUserOutput.setRoleId(getCustomer.getId());
            
        }
        if(user.getPerson().getPersonType()==2){
            Driver getDriver = driverClient.getDriverById(user.getPerson().getDriverId()).getBody();
            newUserOutput.setRoleId(getDriver.getId());
        }

        return newUserOutput;
    }

    public FavoriteOutput toFavoriteOutput(Favorite favorite)
    {
        FavoriteOutput newFavouriteOutput = new FavoriteOutput();
        newFavouriteOutput.setUser(favorite.getUser().getPerson().getFirstName()+" "+favorite.getUser().getPerson().getLastName());
        newFavouriteOutput.setFavourited(favorite.getFavorited().getPerson().getFirstName()+" "+favorite.getFavorited().getPerson().getLastName());
        newFavouriteOutput.setSince(favorite.getCreatedAt());
        return newFavouriteOutput;
    }

    public BlockedOutput toBlockedOutput(Block block)
    {
        BlockedOutput newBlockedOutput = new BlockedOutput();
        newBlockedOutput.setUser(block.getUser().getPerson().getFirstName()+" "+block.getBlocked().getPerson().getLastName());
        newBlockedOutput.setBlocked(block.getBlocked().getPerson().getFirstName()+" "+block.getBlocked().getPerson().getLastName());
        newBlockedOutput.setSince(block.getCreatedAt());
        return newBlockedOutput;
    }
}
