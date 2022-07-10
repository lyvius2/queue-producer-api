Queue Producer API
-

Kafka와 SQS에 메시지를 보내는 API 샘플입니다.  
메시지와 Kafka의 Topic 또는 SQS의 대기열 이름을 Parameter로 받아 메시지를 Queue에 송신합니다.

#### JVM & Framework & Library

* OpenJDK 17
* SpringBoot 2.7.1
* SpringCloud AWS 2.4.1
* Spring-Kafka 2.8.5
* Spring Doc 1.6.9
* Lombok

#### Usage

application.yml에 아래의 내용을 기입합니다.
* `spring.kafka.producer.bootstrap-servers` : Kafka 서버 정보 (보통 3대이고 9092포트 씁니다)
* `cloud.aws.credentials.accessKey` : AWS 인증에 access key를 쓴다면 기입
* `cloud.aws.credentials.secretKey` : AWS 인증에 secret key를 쓴다면 기입
* `cloud.aws.region.static` : AWS 리전을 기입 (서울이면 ap-northeast-2 입니다)
* `sqs.queue.url` : AWS Queue의 URL 
  * ex) _https://sqs.ap-northeast2.amazonaws.com/{aws account 번호}/_
* `sqs.queue.message.group.id` : FIFO Queue 에 송신하려면 Message Group ID의 설정이 필요합니다. 쓰고 싶은 이름을 설정합니다.

Service의 Test 클래스에서 테스트용 Queue, Topic 이름이 임의 지정되어 있는데 실제 이름으로 수정합니다.
* `com.walter.queue.producer.service.KafkaTransferServiceTest.java`의 17 line
* `com.walter.queue.producer.service.SqsTransferServiceTest.java`의 17 line

gradle로 build하고 deploy합니다.

API 문서는 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) 에서 볼 수 있고, 직접 API를 호출하는 것도 가능합니다.