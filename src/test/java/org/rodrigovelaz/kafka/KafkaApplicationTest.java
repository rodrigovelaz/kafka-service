package org.rodrigovelaz.kafka;

import kafka.KafkaApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= KafkaApplication.class)
public class KafkaApplicationTest {

	@Test
	public void test() {
	}
	
}
