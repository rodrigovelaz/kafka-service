package kafka.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.constant.PmdConstant;
import kafka.json.request.KafkaMessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
//    @Qualifier(KafkaConstant.OBJECT_MAPPER)
	private ObjectMapper objectMapper;

	public void send(KafkaMessageRequest kafkaMessageRequest) {

		this.send(kafkaMessageRequest.getTopic(), kafkaMessageRequest.getKey(), kafkaMessageRequest.getData());

		/*
		switch (topic) {

				default:
					throw new RuntimeException("Invalid topic: " + topic);
			}
*/
	}

	@SuppressWarnings(PmdConstant.AVOID_CATCHING_GENERIC_EXCEPTION)
	protected void send(String topic, String key, Object data) {

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