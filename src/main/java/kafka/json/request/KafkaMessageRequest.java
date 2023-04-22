package kafka.json.request;

import lombok.Data;

@Data
public class KafkaMessageRequest {

    private String topic;
    private String key;
    private Object data;
}
