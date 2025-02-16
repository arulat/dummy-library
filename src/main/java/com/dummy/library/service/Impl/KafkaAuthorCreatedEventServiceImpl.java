package com.dummy.library.service.Impl;

import com.dummy.library.dto.AuthorCreatedEvent;
import com.dummy.library.exception.KafkaSendException;
import com.dummy.library.service.AuthorCreatedEventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaAuthorCreatedEventServiceImpl implements AuthorCreatedEventService {

  public static final String AUTHOR_CREATED_TOPIC = "private.dummy.library.author.created";

  private final KafkaTemplate<String, Object> kafkaTemplate;

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void fire(Long authorId) throws JsonProcessingException {
    log.info("Fire author created event for authorId: {}", authorId);
    kafkaTemplate
        .send(AUTHOR_CREATED_TOPIC, objectMapper.writeValueAsString(new AuthorCreatedEvent(authorId)))
        .thenAccept(
            result -> {
              log.info(
                  "Message sent successfully to topic {} with offset {} for authorId: {}",
                  result.getRecordMetadata().topic(),
                  result.getRecordMetadata().offset(),
                  authorId);
            })
        .exceptionally(
            ex -> {
              log.error(
                  "Failed to send message to topic {} for authorId:{}. Error: {}",
                  AUTHOR_CREATED_TOPIC,
                  authorId,
                  ex.getMessage(),
                  ex);
              throw new KafkaSendException(ex.getMessage());
            })
        .join();
  }

}
