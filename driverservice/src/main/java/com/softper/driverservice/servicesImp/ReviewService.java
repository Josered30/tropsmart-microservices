package com.softper.driverservice.servicesImp;

import com.softper.driverservice.resources.comunications.CargoBoundResponse;
import com.softper.driverservice.resources.comunications.DriverBoundResponse;
import com.softper.driverservice.client.CargoClient;
import com.softper.driverservice.client.CustomerClient;
import com.softper.driverservice.models.Cargo;
import com.softper.driverservice.models.Qualification;
import com.softper.driverservice.models.Review;
import com.softper.driverservice.models.ServiceRequest;
//import com.softper.driverservice.repositories.ICargoRepository;
import com.softper.driverservice.repositories.IQualificationRepository;
import com.softper.driverservice.repositories.IReviewRepository;
import com.softper.driverservice.repositories.IServiceRequestRepository;
import com.softper.driverservice.resources.inputs.ReviewInput;
import com.softper.driverservice.resources.outputs.CargoOutput;
import com.softper.driverservice.resources.outputs.CustomerOutput;
import com.softper.driverservice.resources.outputs.ReviewOutput;
import com.softper.driverservice.services.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService implements IReviewService {
    @Autowired
    private IReviewRepository reviewRepository;
    //@Autowired
    //private ICargoRepository cargoRepository;

    @Autowired
    private CargoClient cargoClient;

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private IQualificationRepository qualificationRepository;

    @Autowired
    private IServiceRequestRepository serviceRequestRepository;

    @Override
    public DriverBoundResponse findAllReviews() {
        
        try
        {
            List<Review> reviews = reviewRepository.findAll();
            List<ReviewOutput> reviewOutputList = new ArrayList<>();
            for (Review getReview:reviews) {
                /*
                ReviewOutput newReviewOutput = new ReviewOutput();
                newReviewOutput.setCustomer(r.getCargo().getCustomer().getPerson().getFirstName()+" "+r.getCargo().getCustomer().getPerson().getLastName());
                newReviewOutput.setDriver(r.getCargo().getService().getServicesRequest().getDriver().getPerson().getFirstName()+" "+r.getCargo().getService().getServicesRequest().getDriver().getPerson().getLastName());
                newReviewOutput.setCargo(r.getCargo().getDescription());
                newReviewOutput.setCommentary(r.getCommentary());
                newReviewOutput.setCalification(r.getCalification());
                reviewOutputList.add(newReviewOutput);
                */
                reviewOutputList.add(toReviewOutput(getReview));
            }
            DriverBoundResponse response = new DriverBoundResponse("findAllReviews","success",1);
            response.setReviewOutputs(reviewOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new DriverBoundResponse("findAllReviews","An error ocurred : "+e.getMessage(),-2);
        }
    }


    @Override
    public DriverBoundResponse findReviewsByCustomerId(int customerId) {
        try
        {
            List<Review> reviews = reviewRepository.findReviewsByCustomerId(customerId);
            List<ReviewOutput> reviewOutputList = new ArrayList<>();
            for (Review getReview:reviews) {
                CargoOutput getCargo = cargoClient.findCargoById(getReview.getCargoId()).getBody().getCargoOutput();
                
                //ReviewOutput newReviewOutput = new ReviewOutput();
                //newReviewOutput.setCustomer(r.getCargo().getCustomer().getPerson().getFirstName()+" "+r.getCargo().getCustomer().getPerson().getLastName());
                //newReviewOutput.setDriver(r.getCargo().getService().getServicesRequest().getDriver().getPerson().getFirstName()+" "+r.getCargo().getService().getServicesRequest().getDriver().getPerson().getLastName());
                //newReviewOutput.setCargo(r.getCargo().getDescription());
                //newReviewOutput.setCommentary(r.getCommentary());
                //newReviewOutput.setCalification(r.getCalification());
                //reviewOutputList.add(newReviewOutput);
                reviewOutputList.add(toReviewOutput(getReview));
            }
            DriverBoundResponse response = new DriverBoundResponse("findReviewsByCustomerId","success",1);
            response.setReviewOutputs(reviewOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new DriverBoundResponse("findReviewsByCustomerId","An error ocurred : "+e.getMessage(),-2);
        }
    }

    @Override
    public DriverBoundResponse findReviewsByDriverId(int driverId) {
        try
        {
            List<Review> reviews = reviewRepository.findReviewsByDriverId(driverId);
            List<ReviewOutput> reviewOutputList = new ArrayList<>();
            for (Review getReview:reviews) {
                //CargoBoundResponse cargoResponse = cargoClient.findCargoAndServiceByCargoId(getReview.getCargoId()).getBody();
                //UserBoundResponse userResponse = userClient.getPersonById()
                //newReviewOutput.setCustomer(r.getCargo().getCustomer().getPerson().getFirstName()+" "+r.getCargo().getCustomer().getPerson().getLastName());
                //newReviewOutput.setDriver(r.getCargo().getService().getServicesRequest().getDriver().getPerson().getFirstName()+" "+r.getCargo().getService().getServicesRequest().getDriver().getPerson().getLastName());
                //newReviewOutput.setCargo(r.getCargo().getDescription());
                //newReviewOutput.setCommentary(r.getCommentary());
                //newReviewOutput.setCalification(r.getCalification());
                reviewOutputList.add(toReviewOutput(getReview));
            }
            DriverBoundResponse response = new DriverBoundResponse("findReviewsByDriverId","success",1);
            response.setReviewOutputs(reviewOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new DriverBoundResponse("findReviewsByDriverId","An error ocurred : "+e.getMessage(),-2);
        }
    }

    @Override
    public DriverBoundResponse findReviewById(int reviewId) {
        
        try
        {
            Review getReview = reviewRepository.findById(reviewId).get();
            //newReviewOutput.setCustomer(getReview.getCargo().getCustomer().getPerson().getFirstName()+" "+getReview.getCargo().getCustomer().getPerson().getLastName());
            //newReviewOutput.setDriver(getReview.getCargo().getService().getServicesRequest().getDriver().getPerson().getFirstName()+" "+getReview.getCargo().getService().getServicesRequest().getDriver().getPerson().getLastName());
            //newReviewOutput.setCargo(getReview.getCargo().getDescription());
            //newReviewOutput.setCommentary(getReview.getCommentary());
            //newReviewOutput.setCalification(getReview.getCalification());
            DriverBoundResponse response = new DriverBoundResponse("findReviewById","success",1);
            response.setReviewOutput(toReviewOutput(getReview));
            return response;
        }
        catch (Exception e)
        {
            return new DriverBoundResponse("findReviewById","An error ocurred : "+e.getMessage(),-2);
        }
    }


    @Override
    public Review save(Review review) {
        //return reviewRepository.save(review);
        return null;
    }

    @Override
    public void deleteById(Integer id){
        //reviewRepository.deleteById(id);
    }

    @Override
    public Optional<Review> findById(Integer id){
        //return reviewRepository.findById(id);
        return null;
    }

    @Override
    public List<Review> findAll(){
        //return reviewRepository.findAll();
        return null;
    }

    public ReviewOutput toReviewOutput(Review getReview) 
    {
        ReviewOutput newReviewOutput = new ReviewOutput();
        //newReviewOutput.setCustomer(r.getCargo().getCustomer().getPerson().getFirstName()+" "+r.getCargo().getCustomer().getPerson().getLastName());
        //newReviewOutput.setDriver(r.getCargo().getService().getServicesRequest().getDriver().getPerson().getFirstName()+" "+r.getCargo().getService().getServicesRequest().getDriver().getPerson().getLastName());
        //newReviewOutput.setCargo(r.getCargo().getDescription());
        newReviewOutput.setCommentary(getReview.getCommentary());
        newReviewOutput.setCalification(getReview.getCalification());
        newReviewOutput.setId(getReview.getId());
        return newReviewOutput;
    }
}