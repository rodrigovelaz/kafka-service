package kafka.controller;

import kafka.json.request.KafkaMessageRequest;
import kafka.service.KafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class KafkaController {
	
	private final KafkaService service;
	
	@RequestMapping(method=RequestMethod.POST, value="/send")
    public void send(@RequestBody KafkaMessageRequest kafkaMessageRequest) {
		this.service.send(kafkaMessageRequest);
    }
    
}