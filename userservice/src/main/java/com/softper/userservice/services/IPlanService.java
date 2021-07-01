package com.softper.userservice.services;

import com.softper.userservice.models.Plan;
import com.softper.userservice.resources.inputs.PlanInput;
import com.tropsmart.resources.comunications.UserBoundResponse;

public interface IPlanService extends ICrudService<Plan>{
    UserBoundResponse findPlansByPrice(double priceValue);
    UserBoundResponse findPlansHigherThan(double priceValue);
    UserBoundResponse findPlansLessThan(double priceValue);
    UserBoundResponse findAllPlans();
    UserBoundResponse registerPlan(PlanInput planInput);
    UserBoundResponse findPlanById(int planId);
    UserBoundResponse deletePlanById(int planId);
}
