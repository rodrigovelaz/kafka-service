package kafka.controller;

import kafka.messge.KafkaMessageString;
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
    public void send(@RequestBody KafkaMessageString kafkaMessageString) {
		this.service.send(kafkaMessageString);
    }
    
}