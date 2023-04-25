package kafka.messge;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class KafkaMessageString {

    private String topic;
    private String key;
    private JsonNode payload;
    
}
