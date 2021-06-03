package com.softper.driverservice.servicesImp;

import com.softper.driverservice.resources.comunications.DriverBoundResponse;
import com.softper.driverservice.models.Cargo;
import com.softper.driverservice.models.Qualification;
import com.softper.driverservice.models.Review;
//import com.softper.driverservice.repositories.ICargoRepository;
import com.softper.driverservice.repositories.IQualificationRepository;
import com.softper.driverservice.repositories.IReviewRepository;
import com.softper.driverservice.resources.inputs.ReviewInput;
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
    private IQualificationRepository qualificationRepository;

    @Override
    public DriverBoundResponse findAllReviews() {
        /*
        try
        {
            List<Review> reviews = reviewRepository.findAll();
            List<ReviewOutput> reviewOutputList = new ArrayList<>();
            for (Review r:reviews) {
                ReviewOutput newReviewOutput = new ReviewOutput();
                newReviewOutput.setCustomer(r.getCargo().getCustomer().getPerson().getFirstName()+" "+r.getCargo().getCustomer().getPerson().getLastName());
                newReviewOutput.setDriver(r.getCargo().getService().getServicesRequest().getDriver().getPerson().getFirstName()+" "+r.getCargo().getService().getServicesRequest().getDriver().getPerson().getLastName());
                newReviewOutput.setCargo(r.getCargo().getDescription());
                newReviewOutput.setCommentary(r.getCommentary());
                newReviewOutput.setCalification(r.getCalification());
                reviewOutputList.add(newReviewOutput);
            }
            return new ReviewResponse(reviewOutputList);
        }
        catch (Exception e)
        {
            return new ReviewResponse("An error ocurred while getting the review list : "+e.getMessage());
        }
        */
        return null;
    }


    @Override
    public DriverBoundResponse findReviewsByCustomerId(int customerId) {
        /*try
        {
            List<Review> reviews = reviewRepository.findReviewsByCustomerId(customerId);
            List<ReviewOutput> reviewOutputList = new ArrayList<>();
            for (Review r:reviews) {
                ReviewOutput newReviewOutput = new ReviewOutput();
                newReviewOutput.setCustomer(r.getCargo().getCustomer().getPerson().getFirstName()+" "+r.getCargo().getCustomer().getPerson().getLastName());
                newReviewOutput.setDriver(r.getCargo().getService().getServicesRequest().getDriver().getPerson().getFirstName()+" "+r.getCargo().getService().getServicesRequest().getDriver().getPerson().getLastName());
                newReviewOutput.setCargo(r.getCargo().getDescription());
                newReviewOutput.setCommentary(r.getCommentary());
                newReviewOutput.setCalification(r.getCalification());
                reviewOutputList.add(newReviewOutput);
            }
            return new ReviewResponse(reviewOutputList);
        }
        catch (Exception e)
        {
            return new ReviewResponse("An error ocurred while getting the review list : "+e.getMessage());
        }
        */
        return null;
    }

    @Override
    public DriverBoundResponse findReviewsByDriverId(int driverId) {
        /*
        try
        {
            List<Review> reviews = reviewRepository.findReviewsByDriverId(driverId);
            List<ReviewOutput> reviewOutputList = new ArrayList<>();
            for (Review r:reviews) {
                ReviewOutput newReviewOutput = new ReviewOutput();
                newReviewOutput.setCustomer(r.getCargo().getCustomer().getPerson().getFirstName()+" "+r.getCargo().getCustomer().getPerson().getLastName());
                newReviewOutput.setDriver(r.getCargo().getService().getServicesRequest().getDriver().getPerson().getFirstName()+" "+r.getCargo().getService().getServicesRequest().getDriver().getPerson().getLastName());
                newReviewOutput.setCargo(r.getCargo().getDescription());
                newReviewOutput.setCommentary(r.getCommentary());
                newReviewOutput.setCalification(r.getCalification());
                reviewOutputList.add(newReviewOutput);
            }
            return new ReviewResponse(reviewOutputList);
        }
        catch (Exception e)
        {
            return new ReviewResponse("An error ocurred while getting the review list : "+e.getMessage());
        }
        */
        return null;

    }

    @Override
    public DriverBoundResponse findReviewById(int reviewId) {
        /*
        try
        {
            Review getReview = reviewRepository.findById(reviewId).get();
            ReviewOutput newReviewOutput = new ReviewOutput();
            newReviewOutput.setCustomer(getReview.getCargo().getCustomer().getPerson().getFirstName()+" "+getReview.getCargo().getCustomer().getPerson().getLastName());
            newReviewOutput.setDriver(getReview.getCargo().getService().getServicesRequest().getDriver().getPerson().getFirstName()+" "+getReview.getCargo().getService().getServicesRequest().getDriver().getPerson().getLastName());
            newReviewOutput.setCargo(getReview.getCargo().getDescription());
            newReviewOutput.setCommentary(getReview.getCommentary());
            newReviewOutput.setCalification(getReview.getCalification());
            return new ReviewResponse(newReviewOutput);
        }
        catch (Exception e)
        {
            return new ReviewResponse("An error ocurred while getting the review : "+e.getMessage());

        }
        */
        return null;


    }

    @Override
    public DriverBoundResponse addReviewByCargoId(int cargoId, ReviewInput reviewInput) {
        /*
        try
        {
            Cargo getCargo = cargoRepository.findById(cargoId).get();
            Qualification getQualification = qualificationRepository.findQualificationByDriverId(getCargo.getService().getServicesRequest().getDriver().getId());
            Review newReview = new Review();
            newReview.setCommentary(reviewInput.getCommentary());
            newReview.setCalification(reviewInput.getCalification());
            newReview.setCargo(getCargo);
            newReview.setQualification(getQualification);

            newReview = reviewRepository.save(newReview);

            ReviewOutput newReviewOutput = new ReviewOutput();
            newReviewOutput.setCustomer(newReview.getCargo().getCustomer().getPerson().getFirstName()+" "+newReview.getCargo().getCustomer().getPerson().getLastName());
            newReviewOutput.setDriver(newReview.getCargo().getService().getServicesRequest().getDriver().getPerson().getFirstName()+" "+newReview.getCargo().getService().getServicesRequest().getDriver().getPerson().getLastName());
            newReviewOutput.setCargo(newReview.getCargo().getDescription());
            newReviewOutput.setCommentary(newReview.getCommentary());
            newReviewOutput.setCalification(newReview.getCalification());
            return new ReviewResponse(newReviewOutput);
        }
        catch (Exception e)
        {
            return new ReviewResponse("An error ocurred while registering the review : "+e.getMessage());
        }
        */
        return null;
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
}