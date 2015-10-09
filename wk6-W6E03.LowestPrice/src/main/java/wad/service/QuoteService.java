package wad.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Service;
import wad.service.ext.BaseService;

@Service
public class QuoteService {

    // i guess you didn't know that you can do this as well :)
    @Autowired
    private List<BaseService> services;
    
    @Autowired
    private AsyncTaskExecutor taskExecutor;

    public BaseService getLowestPriceService(final String item) {

        BaseService lowestPriceService = null;
        Double lowest = null;
        
        List<Future<BaseService>> results = new ArrayList<>();
        
        for (BaseService service : services) {
            results.add(taskExecutor.submit(new Callable<BaseService>(){
                @Override
                public BaseService call(){
                    Double price = service.getLowestPrice(item);
                    
                    return price;
                }
            }));
        }
       
        for (Future<BaseService> result: results) {
            BaseService service = result.get();
            if (lowest == null || lowest > price) {
                lowest = price;
                lowestPriceService = service;
            }
        }       
        }

        
        return lowestPriceService;

    }
}
