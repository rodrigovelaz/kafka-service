package kafka.constant;

public class KafkaConstant {

    // Beans
    public static final String CONTAINER_FACTORY = "kafkaListenerContainerFactory";
    public static final String OBJECT_MAPPER = "objectMapper";

    public static final String KAFKA = "kafka";

    public static final String KAFKA_TOPICS = KAFKA + ".topics";
    public static final String KAFKA_TOPICS_VALUE = "${ " + KAFKA_TOPICS + "}";
    
}