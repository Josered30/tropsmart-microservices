package com.softper.userservice.servicesImp;

import com.softper.userservice.models.Plan;
import com.softper.userservice.models.Price;
import com.softper.userservice.repositories.IPlanRepository;
//import com.softper.userservice.repositories.IPriceRepository;
import com.softper.userservice.resources.comunications.UserBoundResponse;
import com.softper.userservice.resources.inputs.PlanInput;
import com.softper.userservice.resources.outputs.PlanOutput;
import com.softper.userservice.services.IPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlanService implements IPlanService {

    @Autowired
    private IPlanRepository planRepository;

    //@Autowired
    //private IPriceRepository priceRepository;


    @Override
    public UserBoundResponse findPlansByPrice(double priceValue) {
        
        try
        {
            UserBoundResponse response;
            List<Plan> plans = planRepository.findPlansByPriceValue(priceValue);
            List<PlanOutput> planOutputList = new ArrayList<>();
            for (Plan p: plans) {
                PlanOutput newPlanOutput = new PlanOutput();
                newPlanOutput.setId(p.getId());
                newPlanOutput.setDurationDays(p.getDuration());
                newPlanOutput.setPrice(p.getTotalPrice());
                newPlanOutput.setTax(p.getTax());
                newPlanOutput.setPlanName(p.getName());
                planOutputList.add(newPlanOutput);
            }
            response = new UserBoundResponse("findPlansByPrice","success",1);
            response.setPlanOutputs(planOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new UserBoundResponse("findPlansByPrice","An error ocurred : "+e.getMessage(),-2);
        }
    }

    @Override
    public UserBoundResponse findPlansHigherThan(double priceValue) {
        try
        {
            UserBoundResponse response;
            List<Plan> plans = planRepository.findPlansHigherThanPriceValue(priceValue);
            List<PlanOutput> planOutputList = new ArrayList<>();
            for (Plan p: plans) {
                PlanOutput newPlanOutput = new PlanOutput();
                newPlanOutput.setId(p.getId());
                newPlanOutput.setDurationDays(p.getDuration());
                newPlanOutput.setPrice(p.getTotalPrice());
                newPlanOutput.setTax(p.getTax());
                newPlanOutput.setPlanName(p.getName());
                planOutputList.add(newPlanOutput);
            }
            response = new UserBoundResponse("findPlansHigherThan","success",1);
            response.setPlanOutputs(planOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new UserBoundResponse("findPlansHigherThan","An error ocurred : "+e.getMessage(),-2);
        }
    }

    @Override
    public UserBoundResponse findPlansLessThan(double priceValue) {
        
        try
        {
            UserBoundResponse response;
            List<Plan> plans = planRepository.findPlansLessThanPriceValue(priceValue);
            List<PlanOutput> planOutputList = new ArrayList<>();
            for (Plan p: plans) {
                PlanOutput newPlanOutput = new PlanOutput();
                newPlanOutput.setId(p.getId());
                newPlanOutput.setDurationDays(p.getDuration());
                newPlanOutput.setPrice(p.getTotalPrice());
                newPlanOutput.setTax(p.getTax());
                newPlanOutput.setPlanName(p.getName());
                planOutputList.add(newPlanOutput);
            }
            response = new UserBoundResponse("findPlansLessThan","success",1);
            response.setPlanOutputs(planOutputList);
            return response;        
        }
        catch (Exception e)
        {
            return new UserBoundResponse("findPlansLessThan","An error ocurred : "+e.getMessage(),-2);
        }
    }

    @Override
    public UserBoundResponse findAllPlans() {
        
        try
        {
            UserBoundResponse response;
            List<Plan> plans = planRepository.findAll();
            List<PlanOutput> planOutputList = new ArrayList<>();
            for (Plan p: plans) {
                PlanOutput newPlanOutput = new PlanOutput();
                newPlanOutput.setId(p.getId());
                newPlanOutput.setDurationDays(p.getDuration());
                newPlanOutput.setPrice(p.getTotalPrice());
                newPlanOutput.setTax(p.getTax());
                newPlanOutput.setPlanName(p.getName());
                planOutputList.add(newPlanOutput);
            }
            response = new UserBoundResponse("findAllPlans","success",1);
            response.setPlanOutputs(planOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new UserBoundResponse("findAllPlans","An error ocurred : "+e.getMessage(),-2);
        }
    }

    @Override
    public UserBoundResponse registerPlan(PlanInput planInput) {
        
        try
        {
            UserBoundResponse response;
            /*
            Price newPrice = new Price();
            newPrice.setTotalPrice(planInput.getPrice());
            newPrice.setTax((planInput.getPrice())*0.19);
            newPrice.setPriceType(1);
            newPrice = priceRepository.save(newPrice);
            */

            Plan newPlan = new Plan();
            newPlan.setName(planInput.getPlanName());
            newPlan.setDuration(planInput.getDurationDays());
            newPlan.setTotalPrice(planInput.getPrice());
            newPlan.setTax((planInput.getPrice())*0.19);
            newPlan = planRepository.save(newPlan);

            PlanOutput newPlanOutput = new PlanOutput();
            newPlanOutput.setId(newPlan.getId());
            newPlanOutput.setPlanName(newPlan.getName());
            newPlanOutput.setDurationDays(newPlan.getDuration());
            newPlanOutput.setPrice(newPlan.getTotalPrice());
            newPlanOutput.setTax(newPlan.getTax());

            response = new UserBoundResponse("registerPlan","success",1);
            response.setPlanOutput(newPlanOutput);
            return response;
        }
        catch (Exception e)
        {
            return new UserBoundResponse("registerPlan","An error ocurred : "+e.getMessage(),-2);
        }
    }

    @Override
    public UserBoundResponse findPlanById(int planId) {
        
        try
        {
            UserBoundResponse response;
            Plan getPlan = planRepository.findById(planId).get();
            PlanOutput newPlanOutput = new PlanOutput();
            newPlanOutput.setId(getPlan.getId());
            newPlanOutput.setDurationDays(getPlan.getDuration());
            newPlanOutput.setPrice(getPlan.getTotalPrice());
            newPlanOutput.setTax(getPlan.getTax());
            newPlanOutput.setPlanName(getPlan.getName());

            response = new UserBoundResponse("findPlanById","success",1);
            response.setPlanOutput(newPlanOutput);
            return response;
        }
        catch (Exception e)
        {
            return new UserBoundResponse("findPlanById","An error ocurred : "+e.getMessage(),-2);
        }
    }

    @Override
    public UserBoundResponse getPlanModelById(int planId)
    {
        try{
            Optional<Plan> getPlan = planRepository.findById(planId);
            if(getPlan.isPresent()){
                UserBoundResponse response = new UserBoundResponse("getPlanModelById", "success",1);
                response.setPlanOutput(toPlanModelOutput(getPlan.get()));
                return response;
            } else {
                return new UserBoundResponse("getPlanModelById","Not found",0);
            }   
        } catch(Exception e)
        {
            return new UserBoundResponse("getPlanModelById","An error ocurred : "+e.getMessage(),-2);
        }
    }

    @Override
    public UserBoundResponse deletePlanById(int planId) {
        return null;
    }

    @Override
    public Plan save(Plan plan) {
        return planRepository.save(plan);
    }

    @Override
    public void deleteById(Integer id) {
        planRepository.deleteById(id);
    }

    @Override
    public Optional<Plan> findById(Integer id) {
        return planRepository.findById(id);
    }

    @Override
    public List<Plan> findAll() throws Exception {
        return planRepository.findAll();
    }

    public PlanOutput toPlanModelOutput(Plan getPlan){
        PlanOutput newPlanOutput = new PlanOutput();
        newPlanOutput.setId(getPlan.getId());
        newPlanOutput.setPlanName(getPlan.getName());
        newPlanOutput.setDurationDays(getPlan.getDuration());
        newPlanOutput.setPrice(getPlan.getTotalPrice());
        newPlanOutput.setTax(getPlan.getTax());
        return newPlanOutput;
    }
}
