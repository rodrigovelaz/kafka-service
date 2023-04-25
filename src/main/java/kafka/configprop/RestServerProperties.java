package kafka.configprop;

import kafka.constant.KafkaConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = KafkaConstant.KAFKA)
@Data
public class RestServerProperties {

    private List<String> topics;
    private Map<String, String> restServers;
    
}