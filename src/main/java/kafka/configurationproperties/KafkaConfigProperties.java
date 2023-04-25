package kafka.configurationproperties;

import kafka.constant.PropertiesConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = PropertiesConstant.KAFKA)
@Data
public class KafkaConfigProperties {

    private List<String> topics;
    private Map<String, String> restServers;
    
}