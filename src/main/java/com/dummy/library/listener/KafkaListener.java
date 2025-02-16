package com.dummy.library.listener;

import static com.dummy.library.service.Impl.KafkaAuthorCreatedEventServiceImpl.AUTHOR_CREATED_TOPIC;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaListener {

  @org.springframework.kafka.annotation.KafkaListener(
      topics = AUTHOR_CREATED_TOPIC,
      groupId = "${spring.kafka.consumer.group-id}")
  public void onTransferInitiated(String json) {
    log.info("!!!! Transfer initiated event received: {}", json);
  }

}
