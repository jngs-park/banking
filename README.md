# banking
✅ Spring Boot → MySQL DB 저장
✅ Kafka → 메시지 발행 성공
✅ Redis → 캐시 저장 성공

즉, 은행·증권사 백엔드 아키텍처의 핵심 구성 요소 3가지 (DB, 메시징, 캐시)
를 모두 한 번에 통합해서 구동시킨 겁니다 🚀

🔍 지금까지 구현된 구조
[Spring Boot]
├─ ArticleController → REST API
├─ ArticleRepository → MySQL 저장
├─ ArticleEventService → Kafka 메시지 발행
└─ RedisCacheService → 최근 글 캐시
↓
+---------------------------+
|  MySQL + Kafka + Redis   |
+---------------------------+