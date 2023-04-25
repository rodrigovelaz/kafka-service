package kafka.abstr;

import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.constant.PmdConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerString {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @SuppressWarnings(PmdConstant.AVOID_CATCHING_GENERIC_EXCEPTION)
    public void send(String topic, String key, Object data) {

        try {
            String dataString = this.objectMapper.writeValueAsString(data);
            log.info("Sending KafkaMessage - topic = {}, key = {}, data = {}", topic, key, dataString);
            this.kafkaTemplate.send(topic, key, dataString);
        }
        catch(Exception e) {
            log.error("Error sending KafkaMessage - topic = " + topic, e);
        }

    }

}