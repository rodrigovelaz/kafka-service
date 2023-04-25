package kafka.abstr;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.constant.BeansConstant;
import kafka.constant.PmdConstant;
import kafka.service.KafkaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "#{'${kafka.topics}'.split(',')}", containerFactory = BeansConstant.CONTAINER_FACTORY)
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerString {

    private final KafkaService kafkaService;
    private final ObjectMapper objectMapper;

    @KafkaHandler
    @SuppressWarnings(PmdConstant.AVOID_CATCHING_GENERIC_EXCEPTION)
    public void kafkaHandler(
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
            @Payload String payload
    ) {

        try {
            log.info("Received KafkaMessage - topic = {}, key = {}, payload = {}", topic, key, payload);
            JsonNode jsonNode = objectMapper.readTree(payload);
            this.kafkaService.receive(topic, key, jsonNode);
        }
        catch(Exception e) {
            log.error("Error processing KafkaMessage - topic = " + topic, e);
        }
    }

}