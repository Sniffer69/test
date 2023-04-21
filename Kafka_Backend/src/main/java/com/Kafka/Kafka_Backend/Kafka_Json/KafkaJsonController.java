package com.Kafka.Kafka_Backend.Kafka_Json;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.TopicPartitionInfo;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.TopicPartitionOffset;
import org.springframework.kafka.support.converter.KafkaMessageHeaders;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/kafka/json")
public class KafkaJsonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaJsonController.class);

    @Autowired
    private KafkaJsonRepo kafkaJsonRepo;
    @Autowired
    private KafkaJsonProducer kafkaJsonProducer;
    @Autowired
    private KafkaJsonListner kafkaJsonListner;
    private final KafkaAdmin admin;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private KafkaProperties kafkaProperties;
    @Autowired
    private KafkaAdminClient kafkaAdminClient;
//    @Autowired
//    private KafkaConsumer<String, String> kafkaConsumer;

    @Autowired
    public KafkaJsonController(KafkaAdmin admin) {
        this.admin = admin;
    }

    //Get API response
    @GetMapping("/getresponse")
    public HttpStatus getresponse() {
        List<User> mydata = kafkaJsonRepo.findAll();
        if (mydata.size() == 0) {
            return HttpStatus.NOT_FOUND;
        } else {
            return HttpStatus.OK;

        }
    }

    // Get Body of the body
    @GetMapping("/getmessage")
    public List<User> getMyData() {
        List<User> mydata = kafkaJsonRepo.findAll();
        return mydata;
    }

    //Create Topic
    @PostMapping("/topics")
    public ResponseEntity<String> createTopic(@RequestParam("topicName") String topicName) throws ExecutionException, InterruptedException {
        NewTopic newTopic = new NewTopic(topicName, 10, (short) 1);
        Properties props = new Properties();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "myGroup");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        AdminClient adminClient = AdminClient.create(props);
        adminClient.createTopics(Arrays.asList(newTopic)).all().get();
        ;
        LOGGER.info(String.format("created topic is: %s", newTopic.name()));
        return ResponseEntity.ok("Topic created successfully");
    }

    //Send Msg to Kafka Topic
    @PostMapping("/jsonpublish")
    public ResponseEntity<String> publishjson(@RequestBody User user) {
        kafkaJsonProducer.sendjsonmsg(user);
        return ResponseEntity.ok("Message has been successfully send to the topic");
    }

    //Delete Kafka Topic
    @DeleteMapping("/delete")
    public void deleteTopic(@RequestParam String topicName, @RequestParam(required = false, defaultValue = "false") boolean deleteTopic) throws ExecutionException, InterruptedException {
        if (deleteTopic) {
            // Set up the AdminClient properties
            Properties properties = new Properties();
            properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // Change this to your Kafka broker address
            properties.put(AdminClientConfig.CLIENT_ID_CONFIG, "myGroupr");

            // Create the AdminClient
            AdminClient client = AdminClient.create(admin.getConfigurationProperties());

            // Delete the topic
            DeleteTopicsOptions options = new DeleteTopicsOptions();
            client.deleteTopics(Collections.singletonList(topicName), options).all().get();
            LOGGER.info(String.format("Topic has been deleted: %s", topicName));

            // Close the AdminClient
            client.close();
        }
    }

    @GetMapping("/topic-length")
    public int getTopicLength() throws ExecutionException, InterruptedException {
        AdminClient adminClient = AdminClient.create(admin.getConfigurationProperties());
        DescribeTopicsResult topicsResult = adminClient.describeTopics(Collections.singletonList("ApiTest"));
        Map<String, TopicDescription> topicsMap = topicsResult.all().get();
        TopicDescription topicDescription = topicsMap.get("ApiTest");
        return topicDescription.partitions().size();
    }

    String topicName = "my-topic";
    int partitionNumber = 0;

    @GetMapping("/{topic}/count")
    public Long getMessageCount(@PathVariable String topic) {
        AdminClient adminClient = AdminClient.create(admin.getConfigurationProperties());
        Map<org.apache.kafka.common.TopicPartition, OffsetSpec> topicPartitionOffsets = new HashMap<>();
        topicPartitionOffsets.put(new org.apache.kafka.common.TopicPartition(topic, 3), new OffsetSpec());

        ListOffsetsResult listOffsetsResult = adminClient.listOffsets(topicPartitionOffsets);

        Map<org.apache.kafka.common.TopicPartition, ListOffsetsResult.ListOffsetsResultInfo> offsets = new HashMap<>();
        try {
            offsets = listOffsetsResult.all().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            // handle exception
        }

        long messageCount = offsets.values().stream().mapToLong(ListOffsetsResult.ListOffsetsResultInfo::offset).sum();
        adminClient.close();
        LOGGER.info(String.format("number of msg: %d", messageCount));
        return messageCount;
    }

    //GEt Topic Information
    @GetMapping("/topiclist")
    public List<KafkaTopicInfo> getTopics() throws InterruptedException, ExecutionException {
        AdminClient adminClient = AdminClient.create(admin.getConfigurationProperties());

        ListTopicsResult topics = adminClient.listTopics();

        Set<String> topicNames = topics.names().get();

        List<TopicDescription> topicDescriptions = adminClient.describeTopics(topicNames).all().get().values().stream().collect(Collectors.toList());

        return topicDescriptions.stream().map(topicDescription -> {
            KafkaTopicInfo kafkaTopicInfo = new KafkaTopicInfo();
            kafkaTopicInfo.setTopicName(topicDescription.name());
            kafkaTopicInfo.setNumPartitions(topicDescription.partitions().size());
            kafkaTopicInfo.setReplicationFactor(topicDescription.partitions().get(0).replicas().size());
            return kafkaTopicInfo;
        }).collect(Collectors.toList());
    }

    public static class KafkaTopicInfo {
        private String topicName;
        private int numPartitions;
        private int replicationFactor;

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }

        public int getNumPartitions() {
            return numPartitions;
        }

        public void setNumPartitions(int numPartitions) {
            this.numPartitions = numPartitions;
        }

        public int getReplicationFactor() {
            return replicationFactor;
        }

        public void setReplicationFactor(int replicationFactor) {
            this.replicationFactor = replicationFactor;
        }
    }


//    @GetMapping("/kafka/topic/{topicName}/stuck-messages")
//    public long getStuckMessagesCount(@PathVariable String topicName) throws ExecutionException, InterruptedException {
//        Map<TopicPartition, Long> endOffsets = admin.describeTopics(topicName)
//                .flatMap(topicDescription -> topicDescription.partitions().stream())
//                .collect(Collectors.toMap(Function.identity(), tp -> admin.describeTopics(topicName).get(tp).partitionSize()));
//        long totalCount = 0;
//        for (Map.Entry<TopicPartition, Long> entry : endOffsets.entrySet()) {
//            long endOffset = entry.getValue();
//            OffsetAndMetadata offsetAndMetadata = admin.listConsumerGroupOffsets("my-consumer-group").get().get(entry.getKey());
//            if (offsetAndMetadata == null) {
//                // No offset information available for this partition
//                continue;
//            }
//            long committedOffset = offsetAndMetadata.offset();
//            totalCount += endOffset - committedOffset;
//        }
//        return totalCount;


//    @GetMapping("/kafka/topic/{topicName}/stuck-messages")
//    public long getStuckMessagesCount(@PathVariable String topicName) throws ExecutionException, InterruptedException {
//        Map<TopicPartition, Long> endOffsets = admin.describeTopics(topicName)
////                .filter(name -> name.equals(topicName))
//                .flatMap(name -> admin.describeTopics(topicName).partitionsToOffsetAndMetadata().get().entrySet().stream())
//                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().offset()));
//        long totalCount = 0;
//        for (Map.Entry<TopicPartition, Long> entry : endOffsets.entrySet()) {
//            long endOffset = entry.getValue();
//            long committedOffset = admin.describeTopics(topicName).get(entry.getKey()).offset();
//            totalCount += endOffset - committedOffset;
//        }
//        return totalCount;
//    }


    @GetMapping("/topic/partitions/messages")
    public Map<Integer, Long> getPartitionInformationAndNumberOfMessages(@RequestParam String topicName) {
        KafkaConsumer<byte[], byte[]> kafkaConsumer = new KafkaConsumer<>(kafkaProperties.buildConsumerProperties());
        List<PartitionInfo> partitionInfos = kafkaConsumer.partitionsFor(topicName);

        Map<Integer, Long> partitionInformationAndNumberOfMessages = new HashMap<>();

        for (PartitionInfo partitionInfo : partitionInfos) {
            TopicPartition topicPartition = new TopicPartition(partitionInfo.topic(), partitionInfo.partition());
            kafkaConsumer.assign(Collections.singletonList(topicPartition));
            kafkaConsumer.seekToBeginning(Collections.singletonList(topicPartition));
            long numberOfMessages = kafkaConsumer.endOffsets(Collections.singletonList(topicPartition)).get(topicPartition) - kafkaConsumer.position(topicPartition);

            partitionInformationAndNumberOfMessages.put(partitionInfo.partition(), numberOfMessages);
        }

        kafkaConsumer.close();

        return partitionInformationAndNumberOfMessages;

    }
}

//
//    @GetMapping("/messages-in-topic")
//    public long getMessagesInTopic(@RequestParam String topic) throws ExecutionException, InterruptedException {
//        ListTopicsResult topics = kafkaAdminClient.listTopics();
//        Map<String, TopicListing> topicListings = topics.namesToListings().get();
//        TopicListing topicListing = topicListings.get(topic);
//        TopicDescription topicDescription = kafkaAdminClient.describeTopics(Arrays.asList(topic)).all().get().get(topic);
//        return topicDescription.partitions().stream()
//                .mapToLong(partitionInfo -> {
//                    try {
//                        return partitionInfo.replicas().isEmpty() ?
//                                0L : kafkaAdminClient.listConsumerGroupOffsets("myGroup", new ListConsumerGroupOffsetsOptions()
//                                        .topicPartitions(Collections.singletonList(new TopicPartition(topic, partitionInfo.partition()))))
//                                .partitionsToOffsetAndMetadata().get(new TopicPartition(topic, partitionInfo.partition(), kafkaConsumer.position(topic, partitionInfo.partition())));
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    } catch (ExecutionException e) {
//                        throw new RuntimeException(e);
//                    }
//                })
//                .sum();
//    }
//
//
//    @GetMapping("/messages")
//    public ResponseEntity<List<String>> getMessages(@RequestParam String topic) {
//        List<String> messages = readMessagesFromTopic(topic);
//        return ResponseEntity.ok(messages);
//    }
//
//    private List<String> readMessagesFromTopic(String topic) {
//        // Same code as provided in the previous answer
//        List<String> messages = new ArrayList<>();
//
//        // Set up a consumer to read all messages in the topic<
//        Consumer<String, String> consumer = kafkaTemplate.setConsumerFactory().createConsumer();
//        consumer.subscribe(Collections.singleton(topic));
//        consumer.seekToBeginning(consumer.assignment());
//
//        // Read all messages in the topic
//        while (true) {
//            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
//            if (records.isEmpty()) {
//                break;
//            }
//            for (ConsumerRecord<String, String> record : records) {
//                messages.add(record.value());
//            }
//        }
//
//        consumer.close();
//        return messages;
//    }






