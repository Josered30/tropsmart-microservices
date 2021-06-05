package com.softper.driverservice.services;

import com.softper.driverservice.resources.comunications.DriverBoundResponse;
import com.softper.driverservice.models.Review;
import com.softper.driverservice.resources.inputs.ReviewInput;

public interface IReviewService extends ICrudService<Review> {
    DriverBoundResponse findAllReviews();
    DriverBoundResponse findReviewsByCustomerId(int customerId);
    DriverBoundResponse findReviewsByDriverId(int driverId);
    DriverBoundResponse findReviewById(int reviewId);
}
