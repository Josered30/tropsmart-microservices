package com.softper.cargoservice.servicesImp;

import com.softper.cargoservice.resources.outputs.PriceOutput;
import com.softper.cargoservice.services.IPriceService;
import com.softper.cargoservice.models.Price;
import com.softper.cargoservice.repositories.IPriceRepository;
import com.softper.cargoservice.resources.comunications.PriceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PriceService implements IPriceService {

    @Autowired
    IPriceRepository priceRepository;

    @Override
    public Price save(Price price) throws Exception {
        //return priceRepository.save(price);
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        //priceRepository.deleteById(id);
    }

    @Override
    public Optional<Price> findById(Integer id) {
        //return priceRepository.findById(id);
        return null;
    }

    @Override
    public List<Price> findAll() throws Exception {
        //return priceRepository.findAll();
        return null;
    }

    @Override
    public PriceResponse findAllPrices() {
        /*
        try
        {
            List<Price> prices = priceRepository.findAll();
            List<PriceOutput> priceResponsesList = new ArrayList<>();
            for (Price p:prices) {
                PriceOutput newPriceOutput = new PriceOutput();
                newPriceOutput.setTotalPrice(p.getTotalPrice());
                newPriceOutput.setTax(p.getTax());
                if(p.getPriceType()==1)
                    newPriceOutput.setPriceFrom("Subscription");
                if(p.getPriceType()==2)
                    newPriceOutput.setPriceFrom("Cargo");
                priceResponsesList.add(newPriceOutput);
            }
            return new PriceResponse(priceResponsesList);
        }
        catch (Exception e)
        {
            return new PriceResponse("An error ocurred while getting the price list : "+e.getMessage());

        }
        */
        return null;

    }

    @Override
    public PriceResponse findPriceById(int priceId) {
        /*
        try
        {
            Price getPrice = priceRepository.findById(priceId).get();
            PriceOutput newPriceOutput = new PriceOutput();
            newPriceOutput.setTotalPrice(getPrice.getTotalPrice());
            newPriceOutput.setTax(getPrice.getTax());
            if(getPrice.getPriceType()==1)
                newPriceOutput.setPriceFrom("Subscription");
            if(getPrice.getPriceType()==2)
                newPriceOutput.setPriceFrom("Cargo");

            return new PriceResponse(newPriceOutput);
        }
        catch (Exception e)
        {
            return new PriceResponse("An error ocurred while getting the price : "+e.getMessage());
        }
        */
        return null;
    }

    @Override
    public PriceResponse findPricesByPriceType(int priceType) {
        /*try
        {
            List<Price> prices = priceRepository.findPricesByType(priceType);
            List<PriceOutput> priceResponsesList = new ArrayList<>();
            for (Price p:prices) {
                PriceOutput newPriceOutput = new PriceOutput();
                newPriceOutput.setTotalPrice(p.getTotalPrice());
                newPriceOutput.setTax(p.getTax());
                if(p.getPriceType()==1)
                    newPriceOutput.setPriceFrom("Subscription");
                if(p.getPriceType()==2)
                    newPriceOutput.setPriceFrom("Cargo");
                priceResponsesList.add(newPriceOutput);
            }
            return new PriceResponse(priceResponsesList);
        }
        catch (Exception e)
        {
            return new PriceResponse("An error ocurred while getting the price list : "+e.getMessage());
        }
        */
        return null;
    }
}
