package tcc.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import tcc.service.DemoService;
import tcc.vo.Member;

@Component
public class KafkaImpl {
	private static final Logger log = LoggerFactory.getLogger(KafkaImpl.class);

//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    @Autowired
	private DemoService service;
    
    public void send(String topic, String payload) {
        kafkaTemplate.send(topic, payload);
        log.info("Message: " + payload + " sent to topic: " + topic);
    }


//    @Scheduled(fixedRate = 1000000)
//    public void reportCurrentTime() {
//        send("test", "helloworld " + dateFormat.format(new Date()));
//    }

    @KafkaListener(topics = "save1")
    public void receiveTopic1(String message) {
        log.info(String.format("Message Received : %s", message));

        try {
        	Member member = Member.deserializeJSON(message);

//            // 이미 처리된 주문인지 확인
//            if(paymentService.isAlreadyProcessedOrderId(paymentRequest.getOrderId())) {
//                log.info(String.format("AlreadyProcessedOrderId : [%s]", paymentRequest.getOrderId()));
//            } else {
//                paymentService.payOrder(paymentRequest.getOrderId(), paymentRequest.getPaymentAmt());
//            }
            

            // Kafka Offset Manual Commit
            service.insert(member);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
