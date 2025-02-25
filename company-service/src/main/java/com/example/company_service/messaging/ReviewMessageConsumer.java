package com.example.company_service.messaging;

import com.example.company_service.dto.ReviewMessage;
import com.example.company_service.service.CompanyService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.amqp.rabbit.support.Acknowledgment;


@Service
public class ReviewMessageConsumer {

    @Autowired
    private CompanyService companyService;

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage){
        try{
            //System.out.println("Received message "+ reviewMessage);
            companyService.updateCompanyRating(reviewMessage);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

}
