package kafka.json.request;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class KafkaMessageRequest {

    private String topic;
    private String key;
    private JsonNode payload;
    
}
