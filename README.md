# Kafka 예제 (Spring Kafka)

Docker Compose를 이용해 Kafka 4.0 (KRaft 모드), Kafka UI 그리고 Spring Boot 기반의 프로듀서와 컨슈머 애플리케이션을 한 번에 실행할 수 있는 프로젝트입니다.

## 구성 요소
- **Kafka** 4.0 (KRaft 모드)
- **Kafka UI** – 토픽과 컨슈머 그룹을 확인할 수 있는 웹 UI (8080 포트)
- **Producer 앱** – `/send?message=메시지` 엔드포인트를 제공 (8081 포트)
- **Consumer 앱** – `demo-topic`의 메시지를 로그로 출력

## 파티션 할당 전략 테스트

`consumer-app`은 두 가지 스프링 프로파일을 통해 파티션 할당 전략을 선택할 수 있습니다.

- **classic**: `CooperativeStickyAssignor`를 사용하는 전통적인 방식
- **consumer**: [KIP‑848](https://www.confluent.io/blog/kip-848-consumer-rebalance-protocol/)에서 제안된 새로운 `consumer` 그룹 프로토콜 사용

컨테이너 실행 시 `SPRING_PROFILES_ACTIVE` 값을 지정하여 전략을 변경합니다.

```bash
# classic 모드
docker compose run consumer-app -e SPRING_PROFILES_ACTIVE=classic

# 새로운 consumer 그룹 프로토콜 모드
docker compose run consumer-app -e SPRING_PROFILES_ACTIVE=consumer
```

## 실행 방법

1. 모든 서비스 빌드 및 기동
   ```bash
   docker compose up --build
   ```
2. 메시지 전송
   ```bash
   curl "http://localhost:8081/send?message=hello"
   ```
3. Kafka UI로 결과 확인: <http://localhost:8080>
