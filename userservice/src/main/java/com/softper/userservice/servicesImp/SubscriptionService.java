package com.softper.userservice.servicesImp;

import com.softper.userservice.exception.ResourceNotFoundException;
import com.softper.userservice.models.Plan;
import com.softper.userservice.models.Subscription;
import com.softper.userservice.models.User;
import com.softper.userservice.repositories.IPlanRepository;
import com.softper.userservice.repositories.ISubscriptionRepository;
import com.softper.userservice.repositories.IUserRepository;
import com.softper.userservice.services.ISubscriptionService;
import com.tropsmart.resources.comunications.UserBoundResponse;
import com.tropsmart.resources.outputs.SubscriptionOutput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService implements ISubscriptionService {
    @Autowired
    private ISubscriptionRepository subscriptionRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IPlanRepository planRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserBoundResponse findSubscriptionById(int subscriptionId) {

        try {
            Optional<Subscription> subscription = subscriptionRepository.findById(subscriptionId);

            if (!subscription.isPresent()) {
                return new UserBoundResponse("findSubscriptionById", "not found", 0);
            }

            UserBoundResponse response = new UserBoundResponse("findSubscriptionById", "success", 1);
            response.setSubscriptionOutput(modelMapper.map(subscription.get(), SubscriptionOutput.class));
            return response;
        } catch (Exception e) {
            return new UserBoundResponse("findSubscriptionById", "An error ocurred : " + e.getMessage(), -2);
        }
    }

    @Override
    public UserBoundResponse subscribe(int userId, int planId) {

        try {
            UserBoundResponse response;
            Plan getPlan = planRepository.findById(planId).get();
            User getUser = userRepository.findById(userId).get();

            Subscription newSubscription = new Subscription();
            newSubscription.setPlan(getPlan);
            newSubscription.setUser(getUser);
            newSubscription.setSubscriptionState("Actived");
            newSubscription.setStartTime(Calendar.getInstance().getTime());
            newSubscription.setFinishTime(Calendar.getInstance().getTime());

            List<Subscription> subscriptionList = subscriptionRepository.getSubscriptionsByUserId(userId);

            for (Subscription s : subscriptionList) {
                s.setSubscriptionState("Disabled");
            }

            subscriptionRepository.saveAll(subscriptionList);
            newSubscription = subscriptionRepository.save(newSubscription);

            response = new UserBoundResponse("subscribe", "success", 1);
            response.setSubscriptionOutput(modelMapper.map(newSubscription, SubscriptionOutput.class));
            return response;
        } catch (Exception e) {
            return new UserBoundResponse("subscribe", "An error ocurred : " + e.getMessage(), -2);
        }
    }

    @Override
    public UserBoundResponse findSubscriptionsByUserId(int userId) {

        try {
            UserBoundResponse response;

            Optional<User> optionalUser = userRepository.findById(userId);
            if (!optionalUser.isPresent()) {
                return new UserBoundResponse("findSubscriptionsByUserId", "not found", 0);
            }

            if (optionalUser.get().getPerson().getPersonType() == 1) {
                return new UserBoundResponse("findSubscriptionsByUserId", "Subscriptions are only available to drivers", 0);
            }

            List<Subscription> subscriptionList = subscriptionRepository.getSubscriptionsByUserId(userId);
            List<SubscriptionOutput> subscriptionOutputList = new ArrayList<>();
            for (Subscription s : subscriptionList) {
                subscriptionOutputList.add(modelMapper.map(s, SubscriptionOutput.class));
            }
            response = new UserBoundResponse("findSubscriptionsByUserId", "Success", 1);
            response.setSubscriptionOutputs(subscriptionOutputList);
            return response;
        } catch (Exception e) {
            return new UserBoundResponse("findSubscriptionsByUserId", "An error ocurred : " + e.getMessage(), -2);
        }
    }

    @Override
    public UserBoundResponse findAllSubscriptions() {

        try {
            UserBoundResponse response;
            List<Subscription> subscriptionList = subscriptionRepository.findAll();
            List<SubscriptionOutput> subscriptionOutputList = new ArrayList<>();
            for (Subscription s : subscriptionList) {
                subscriptionOutputList.add(modelMapper.map(s, SubscriptionOutput.class));
            }
            response = new UserBoundResponse("findAllSubscriptions", "Success", 1);
            response.setSubscriptionOutputs(subscriptionOutputList);
            return response;
        } catch (Exception e) {
            return new UserBoundResponse("findAllSubscriptions", "An error ocurred : " + e.getMessage(), -2);
        }
    }

    @Override
    public UserBoundResponse cancelSubscription(int subscriptionId) {

        try {
            UserBoundResponse response;
            Optional<Subscription> optionalSubscription = subscriptionRepository.findById(subscriptionId);

            if (!optionalSubscription.isPresent()) {
                return new UserBoundResponse("cancelSubscription", "not found", 0);
            }

            Subscription suscription = optionalSubscription.get();

            suscription.setSubscriptionState("Canceled");
            suscription = subscriptionRepository.save(suscription);

            response = new UserBoundResponse("cancelSubscription", "Success", 1);
            response.setSubscriptionOutput(modelMapper.map(suscription, SubscriptionOutput.class));
            return response;
        } catch (Exception e) {
            return new UserBoundResponse("cancelSubscription", "An error ocurred : " + e.getMessage(), -2);
        }
    }

    @Override
    public UserBoundResponse enableSubscriptionById(int subscriptionId) {

        try {
            UserBoundResponse response;

            Optional<Subscription> optionalSubscription = subscriptionRepository.findById(subscriptionId);
            if (!optionalSubscription.isPresent()) {
                return new UserBoundResponse("enableSubscriptionById", "not found", 0);
            }

            Subscription subscription = optionalSubscription.get();

            List<Subscription> subscriptionList = subscriptionRepository.getSubscriptionsByUserId(subscription.getUser().getId());
            for (Subscription s : subscriptionList) {
                if (s.getId() != subscriptionId) {
                    s.setSubscriptionState("Disabled");
                } else {
                    s.setSubscriptionState("Actived");
                }
                subscriptionRepository.save(s);
            }

            response = new UserBoundResponse("enableSubscriptionById", "success", 1);
            response.setSubscriptionOutput(modelMapper.map(subscription, SubscriptionOutput.class));
            return response;
        } catch (Exception e) {
            return new UserBoundResponse("enableSubscriptionById", "an error ocurred : " + e.getMessage(), -2);
        }
    }

    @Override
    public UserBoundResponse deleteSubscriptionBySubscriptionId(int subscriptionId) {

        try {
            UserBoundResponse response;

            Optional<Subscription> optionalSubscription = subscriptionRepository.findById(subscriptionId);
            if (!optionalSubscription.isPresent()) {
                return new UserBoundResponse("deleteSubscriptionBySubscriptionId", "not found", 0);
            }
            Subscription subscription = optionalSubscription.get();
            subscriptionRepository.deleteById(subscriptionId);

            response = new UserBoundResponse("deleteSubscriptionBySubscriptionId", "Success", 1);
            response.setSubscriptionOutput(modelMapper.map(subscription, SubscriptionOutput.class));
            return response;
        } catch (Exception e) {
            return new UserBoundResponse("deleteSubscriptionBySubscriptionId", "An error ocurred : " + e.getMessage(), -2);
        }

    }

    @Override
    public Subscription save(Subscription subscription) throws Exception {
        return subscriptionRepository.save(subscription);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        subscriptionRepository.deleteById(id);
    }

    @Override
    public Optional<Subscription> findById(Integer id) throws Exception {
        return subscriptionRepository.findById(id);
    }

    @Override
    public List<Subscription> findAll() throws Exception {
        return subscriptionRepository.findAll();
    }


}
