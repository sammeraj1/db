package com.db.service;

import com.db.exception.BadRequestException;
import com.db.model.Trade;
import com.db.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class TradeService {


    private TradeRepository tradeRepository;

    @Autowired
    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository=tradeRepository;
    }

    public Trade processTrade(Trade trade) throws ParseException {

        Trade tradeResponse=null;
        if(isTradeVersionValid(trade) && idMaturityDateValid(trade) ){
            //setting the created date to today's date
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
                LocalDateTime now = LocalDateTime.now();
                String currentDate=dtf.format(now);
                trade.setCreatedDate(currentDate);
            tradeResponse= tradeRepository.save(trade);
            }
       return  tradeResponse;
    }

    private boolean isTradeVersionValid(Trade trade)  {
      // fetch the details based on tradeId
        Optional<Trade> tradeResponse = Optional.empty();
        tradeResponse=tradeRepository.findTradeByTradeId(trade.getTradeId());
        if(tradeResponse.isPresent()){
            // in trade version is greater or equal to saved version
            if(trade.getVersion()>=tradeResponse.get().getVersion()){
                return true;
            }else{
                throw new BadRequestException("trade version should be equal or greater then previous");
            }

        }
        return true;
    }

    private boolean idMaturityDateValid(Trade trade) throws ParseException {

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/YYYY");
        Date d1=simpleDateFormat.parse(trade.getMaturityDate());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        LocalDateTime now = LocalDateTime.now();
        String currentDate=dtf.format(now);
        Date d2=simpleDateFormat.parse(currentDate);
        if(d1.compareTo(d2)>=0){
            return true;
        }
        return  false;
    }

    @Scheduled(fixedDelay = 0)
    public  void updateExpiryFlag(){

        ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
        exec.scheduleAtFixedRate(new Runnable() {
            public void run() {
                tradeRepository.updateExpiryFlag();
            }
        }, 0, 0, TimeUnit.SECONDS); // execute every 0 seconds

    }
}
