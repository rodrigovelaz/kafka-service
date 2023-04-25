package kafka.service;

import com.fasterxml.jackson.databind.JsonNode;
import kafka.abstr.KafkaProducerString;
import kafka.configprop.RestServerProperties;
import kafka.json.request.KafkaMessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaService {

	private final KafkaProducerString kafkaProducerString;
	private final RestTemplate restTemplate;
	private final RestServerProperties servers;

	public void send(KafkaMessageRequest kafkaMessageRequest) {

		if (!this.servers.getTopics().contains(kafkaMessageRequest.getTopic())) {
			throw new RuntimeException("Topic not configured: " + kafkaMessageRequest.getTopic());
		}
		
		this.kafkaProducerString.send(kafkaMessageRequest.getTopic(), kafkaMessageRequest.getKey(), kafkaMessageRequest.getPayload());
	}
	
	public void receive(String topic, String key, JsonNode data) {

		String url = this.servers.getRestServers().get(topic);
		
		if (url == null) {
			throw new RuntimeException("Couldn't find response server for topic: " + topic);
		}

		KafkaMessageRequest requestBody = KafkaMessageRequest.builder()
				.topic(topic)
				.key(key)
				.payload(data)
				.build();
		
		HttpEntity httpEntity = new HttpEntity(requestBody, null);
		ParameterizedTypeReference typeRef = new ParameterizedTypeReference<Void>(){};
		this.restTemplate.exchange(url, HttpMethod.POST, httpEntity, typeRef);
	}

}