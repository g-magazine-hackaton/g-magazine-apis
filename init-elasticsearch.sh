#!/bin/bash

# Elasticsearch 서버 주소
ES_HOST="localhost:9200"

# 인덱스 및 매핑 생성
echo "\n인덱스 데이터 매핑 구조에 따라 인덱스를 생성합니다..."

# consumers 인덱스 생성
curl -X PUT "$ES_HOST/consumers" -H 'Content-Type: application/json' -d'
{
  "mappings": {
    "properties": {
      "consumer_id": { "type": "keyword" },
      "consumer_nickname": { "type": "keyword" },
      "consumer_rank": { "type": "integer" },
      "consumer_score": { "type": "integer" },
      "profile_content": { "type": "keyword" },
      "profile_url": { "type": "keyword" },
      "scrapped_magazine_ids": { "type": "keyword" },
      "liked_magazine_ids": { "type": "keyword" },
      "follower_consumer_ids": { "type": "keyword" },
      "following_consumer_ids": { "type": "keyword" },
      "up_dt": { "type": "date", "format": "yyyy-MM-dd" }
    }
  }
}'

# magazines 인덱스 생성
curl -X PUT "$ES_HOST/magazines" -H 'Content-Type: application/json' -d'
{
  "mappings": {
    "properties": {
      "magazine_id": { "type": "keyword" },
      "consumer_id": { "type": "keyword" },
      "magazine_content": { "type": "keyword" },
      "liked_cnt": { "type": "integer" },
      "forder_ids": { "type": "keyword" },
      "goods_ids": { "type": "keyword" },
      "photo_urls": { "type": "keyword" },
      "up_dt": { "type": "date", "format": "yyyy-MM-dd" }
    }
  }
}'

# folders 인덱스 생성
curl -X PUT "$ES_HOST/folders" -H 'Content-Type: application/json' -d'
{
  "mappings": {
    "properties": {
      "folder_id": { "type": "keyword" },
      "consumer_id": { "type": "keyword" },
      "folder_name": { "type": "keyword" },
      "up_dt": { "type": "date", "format": "yyyy-MM-dd" }
    }
  }
}'

# goods 인덱스 생성
curl -X PUT "$ES_HOST/goods" -H 'Content-Type: application/json' -d'
{
  "mappings": {
    "properties": {
      "goods_id": { "type": "keyword" },
      "goods_name": { "type": "keyword" },
      "goods_price": { "type": "integer" },
      "goods_page_url": { "type": "keyword" },
      "goods_photo_url": { "type": "keyword" },
      "folder_id": { "type": "keyword" },
      "up_dt": { "type": "date", "format": "yyyy-MM-dd" }
    }
  }
}'

echo "\n인덱스 데이터 매핑 구조 정의가 완료되었습니다."

# 벌크 데이터 적재
echo "\nES에 초기 데이터를 적재합니다..."

# 벌크 데이터 파일 실행
curl -X POST "$ES_HOST/_bulk" -H 'Content-Type: application/json' -d '
{ "index" : { "_index" : "consumers", "_id" : "consumer1" } }
{ "consumer_id" : "consumer1", "consumer_nickname" : "주부9단", "consumer_rank" : 5, "consumer_score" : 32000, "profile_content" : "지마켓 대표 주부9단", "profile_url" : "/healthcheck.png", "scrapped_magazine_ids" : [ ], "liked_magazine_ids" : [1, 2, 3, 4, 5], "follower_consumer_ids" : ["consumer2", "consumer3", "consumer4", "consumer5", "consumer6", "consumer7", "consumer8", "consumer9", "consumer10"], "following_consumer_ids" : ["consumer2", "consumer3", "consumer6", "consumer8", "consumer10"], "up_dt" : "2023-12-05" }
{ "index" : { "_index" : "consumers", "_id" : "consumer2" } }
{ "consumer_id" : "consumer2", "consumer_nickname" : "G구본", "consumer_rank" : 5, "consumer_score" : 29000, "profile_content" : "G구본의 잡학다식", "profile_url" : "/healthcheck.png", "scrapped_magazine_ids" : [ ], "liked_magazine_ids" : [ ], "follower_consumer_ids" : ["consumer1" ], "following_consumer_ids" : ["consumer1" ], "up_dt" : "2023-12-05" }
{ "index" : { "_index" : "consumers", "_id" : "consumer3" } }
{ "consumer_id" : "consumer3", "consumer_nickname" : "서은맘", "consumer_rank" : 5, "consumer_score" : 21000, "profile_content" : "Dont worry be happy!", "profile_url" : "/healthcheck.png", "scrapped_magazine_ids" : [ ], "liked_magazine_ids" : [ ], "follower_consumer_ids" : ["consumer1" ], "following_consumer_ids" : ["consumer1" ], "up_dt" : "2023-12-05" }
{ "index" : { "_index" : "consumers", "_id" : "consumer4" } }
{ "consumer_id" : "consumer4", "consumer_nickname" : "남편9단", "consumer_rank" : 5, "consumer_score" : 17500, "profile_content" : "평화로운 일상", "profile_url" : "/healthcheck.png", "scrapped_magazine_ids" : [ ], "liked_magazine_ids" : [ ], "follower_consumer_ids" : [ ], "following_consumer_ids" : ["consumer1" ], "up_dt" : "2023-12-05" }
{ "index" : { "_index" : "consumers", "_id" : "consumer5" } }
{ "consumer_id" : "consumer5", "consumer_nickname" : "rlawlgns123", "consumer_rank" : 5, "consumer_score" : 12300, "profile_content" : "장난감 리뷰 전문!", "profile_url" : "/healthcheck.png", "scrapped_magazine_ids" : [ ], "liked_magazine_ids" : [ ], "follower_consumer_ids" : [ ], "following_consumer_ids" : ["consumer1" ], "up_dt" : "2023-12-05" }
{ "index" : { "_index" : "consumers", "_id" : "consumer6" } }
{ "consumer_id" : "consumer6", "consumer_nickname" : "홍학철", "consumer_rank" : 4, "consumer_score" : 9800, "profile_content" : "디지털, 전자기기 리뷰어 입니다!", "profile_url" : "/healthcheck2.png", "scrapped_magazine_ids" : [ ], "liked_magazine_ids" : [ ], "follower_consumer_ids" : ["consumer1" ], "following_consumer_ids" : ["consumer1" ], "up_dt" : "2023-12-05" }
{ "index" : { "_index" : "consumers", "_id" : "consumer7" } }
{ "consumer_id" : "consumer7", "consumer_nickname" : "Ha_ppy", "consumer_rank" : 3, "consumer_score" : 5300, "profile_content" : "좋은 연말 되세요", "profile_url" : "/healthcheck2.png", "scrapped_magazine_ids" : [ ], "liked_magazine_ids" : [ ], "follower_consumer_ids" : [ ], "following_consumer_ids" : ["consumer1" ], "up_dt" : "2023-12-05" }
{ "index" : { "_index" : "consumers", "_id" : "consumer8" } }
{ "consumer_id" : "consumer8", "consumer_nickname" : "뷰티Create", "consumer_rank" : 2, "consumer_score" : 2200, "profile_content" : "11/23 G-Lice 뷰티편 출연!", "profile_url" : "/healthcheck2.png", "scrapped_magazine_ids" : [ ], "liked_magazine_ids" : [ ], "follower_consumer_ids" : ["consumer1" ], "following_consumer_ids" : ["consumer1" ], "up_dt" : "2023-12-05" }
{ "index" : { "_index" : "consumers", "_id" : "consumer9" } }
{ "consumer_id" : "consumer9", "consumer_nickname" : "사나이", "consumer_rank" : 2, "consumer_score" : 2200, "profile_content" : "자동차 리뷰는 저에게!!", "profile_url" : "/healthcheck2.png", "scrapped_magazine_ids" : [ ], "liked_magazine_ids" : [ ], "follower_consumer_ids" : [ ], "following_consumer_ids" : ["consumer1" ], "up_dt" : "2023-12-05" }
{ "index" : { "_index" : "consumers", "_id" : "consumer10" } }
{ "consumer_id" : "consumer10", "consumer_nickname" : "규리니", "consumer_rank" : 5, "consumer_score" : 23480, "profile_content" : "지마켓 영업은 저에게!!", "profile_url" : "/healthcheck2.png", "scrapped_magazine_ids" : [ ], "liked_magazine_ids" : [ ], "follower_consumer_ids" : ["consumer1" ], "following_consumer_ids" : ["consumer1" ], "up_dt" : "2023-12-05" }
{"index":{"_index":"magazines","_id":"1"}}
{"magazine_id":"1","consumer_id":"consumer1","magazine_content":"조거팬트로 하루를 따듯하게!!","liked_cnt":10,"folder_id":"1","goods_ids":["3293205512"],"photo_urls":["/magazine1-1.png"],"up_dt":"2023-12-05"}
{"index":{"_index":"magazines","_id":"2"}}
{"magazine_id":"2","consumer_id":"consumer1","magazine_content":"밴딩 기모바지가 필요한가?","liked_cnt":3,"folder_id":"1","goods_ids":["3317134297"],"photo_urls":["/magazine2-1.png"],"up_dt":"2023-12-05"}
{"index":{"_index":"magazines","_id":"3"}}
{"magazine_id":"3","consumer_id":"consumer1","magazine_content":"올해 레깅스는 이걸로!!","liked_cnt":232,"folder_id":"1","goods_ids":["2835289263"],"photo_urls":["/magazine3-1.png"],"up_dt":"2023-12-05"}
{"index":{"_index":"magazines","_id":"4"}}
{"magazine_id":"4","consumer_id":"consumer1","magazine_content":"레깅스 힙커버 Y존! 이게 힙템이다!?!?","liked_cnt":1073,"folder_id":"1","goods_ids":["3043546992"],"photo_urls":["/magazine4-1.png"],"up_dt":"2023-12-05"}
{"index":{"_index":"magazines","_id":"5"}}
{"magazine_id":"5","consumer_id":"consumer1","magazine_content":"운동할때마다 입는데 너무 편하네요..! 조거팬츠 트레이닝복 다들 입어보셔요~~","liked_cnt":203,"folder_id":"1","goods_ids":["2989516462"],"photo_urls":["/magazine5-1.png"],"up_dt":"2023-12-05"}
{"index":{"_index":"folders","_id":"1"}}
{"folder_id":"1","consumer_id":["consumer1", "consumer2", "consumer3", "consumer8"],"folder_name":"패션","up_dt":"2023-12-05"}
{"index":{"_index":"folders","_id":"2"}}
{"folder_id":"2","consumer_id":["consumer2", "consumer3", "consumer6"],"folder_name":"가전/디지털","up_dt":"2023-12-05"}
{"index":{"_index":"folders","_id":"3"}}
{"folder_id":"3","consumer_id":["consumer1", "consumer2", "consumer3", "consumer8"],"folder_name":"화장품/향수","up_dt":"2023-12-05"}
{"index":{"_index":"folders","_id":"4"}}
{"folder_id":"4","consumer_id":["consumer1", "consumer2", "consumer3", "consumer4", "consumer5", "consumer6", "consumer7", "consumer8", "consumer9", "consumer10"],"folder_name":"식품","up_dt":"2023-12-05"}
{"index":{"_index":"folders","_id":"5"}}
{"folder_id":"5","consumer_id":["consumer1", "consumer2", "consumer3", "consumer4", "consumer5", "consumer6", "consumer7", "consumer8", "consumer9", "consumer10"],"folder_name":"도서","up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"3293205512"}}
{"goods_id":"3293205512","goods_name":"주문폭주 워밍밍크조거팬츠 융기모팬츠/겨울팬츠/한파팬츠/여자겨울팬츠","goods_price":17600,"goods_page_url":"https://item.gmarket.co.kr/Item?goodscode=3293205512&ver=20231201","goods_photo_url":["/goods1-1.png", "/goods1-2.png", "/goods1-3.png"],"folder_id": "1","up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"3317134297"}}
{"goods_id":"3317134297","goods_name":"따시따시 강열바지 3종 세트 밴딩 기모바지 신축성 보온 체형 보정 아이큐샵","goods_price":39900,"goods_page_url":"https://item.gmarket.co.kr/Item?goodscode=3317134297","goods_photo_url":["/goods2-1.png"],"folder_id": "1","up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"2835289263"}}
{"goods_id":"2835289263","goods_name":"(블랙:FREE) SUU8048 밍크퍼3부후레아치마레깅스/기모/하의/여성","goods_price":39950,"goods_page_url":"https://item.gmarket.co.kr/Item?goodscode=2835289263","goods_photo_url":["/goods3-1.png", "/goods3-2.png"],"folder_id": "1","up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"3043546992"}}
{"goods_id":"3043546992","goods_name":"레깅스 힙커버 힙커버업 Y존 엉덩이 가리개 요가복 필라테스복","goods_price":5900,"goods_page_url":"https://item.gmarket.co.kr/Item?goodscode=3043546992","goods_photo_url":["/goods4-1.png", "/goods4-2.png", "/goods4-3.png", "/goods4-4.png"],"folder_id": "1","up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"2989516462"}}
{"goods_id":"2989516462","goods_name":"여름 쿨링 밴딩 조거팬츠 트레이닝 츄리닝 발목밴딩바지","goods_price":3900,"goods_page_url":"https://item.gmarket.co.kr/Item?goodsCode=2989516462","goods_photo_url":["/goods5-1.png", "/goods5-2.png", "/goods5-3.png", "/goods5-4.png"],"folder_id": "1","up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"3298607418"}}
{"goods_id":"3298607418","goods_name":"니트 목도리 플러피 여성 겨울 목도리 부드러운 쁘띠 머플러","goods_price":3900,"goods_page_url":"https://item.gmarket.co.kr/Item?goodscode=3298607418","goods_photo_url":["/goods6-1.png", "/goods6-2.png", "/goods6-3.png", "/goods6-4.png"],"folder_id": "1", "up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"2835294523"}}
{"goods_id":"2835294523","goods_name":"(블랙:FREE) SUU8049 밍크퍼5부LONG치마레깅스/기모/하의/여성","goods_price":39950,"goods_page_url":"https://item.gmarket.co.kr/Item?goodscode=2835294523","goods_photo_url":["/goods7-1.png"],"folder_id": "1", "up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"1178998574"}}
{"goods_id":"1178998574","goods_name":"새로본 기모 밍크 731치마레깅스겨을 가을 여성 여자 미시 빅사이즈","goods_price":29800,"goods_page_url":"https://item.gmarket.co.kr/Item?goodscode=1178998574","goods_photo_url":["/goods8-1.png"],"folder_id": "1", "up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"3347375949"}}
{"goods_id":"3347375949","goods_name":"여성 겨울 융털 레깅스 팬츠 바지 기모 고탄력 쫄 9부 안감 방한","goods_price":8440,"goods_page_url":"https://item.gmarket.co.kr/Item?goodscode=3347375949","goods_photo_url":["/goods9-1.png"],"folder_id": "1", "up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"3344126190"}}
{"goods_id":"3344126190","goods_name":"루즈핏 기모 후드 롱 원피스 캐주얼 루즈핏 기모 후드 롱 원피스 캐주얼","goods_price":18230,"goods_page_url":"https://item.gmarket.co.kr/Item?goodsCode=3344126190","goods_photo_url":["/goods10-1.png"],"folder_id": "1", "up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"3111086381"}}
{"goods_id":"3111086381","goods_name":"뮤패드 K10 PLUS RAM4G /UFS64GB /G99 탑재 / 2K해상도","goods_price":149000,"goods_page_url":"https://item.gmarket.co.kr/item?goodscode=3111086381&gate_id=27809ED4-5DF1-4990-9CDE-216C015A8960","goods_photo_url":["/goods11-1.png"],"folder_id": "2", "up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"2871746469"}}
{"goods_id":"2871746469","goods_name":"와콤 타블렛 인튜어스 CTL-6100WL 그린 WACOM 중형 태블릿 +파우치세트+","goods_price":194900,"goods_page_url":"https://item.gmarket.co.kr/Item?goodscode=2871746469","goods_photo_url":["/goods12-1.png"],"folder_id": "2", "up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"1873547336"}}
{"goods_id":"1873547336","goods_name":"netis MEX01 기가와이파이 유무선 공유기 인터넷 5GHz","goods_price":42300,"goods_page_url":"https://item.gmarket.co.kr/Item?goodsCode=1873547336","goods_photo_url":["/goods13-1.png"],"folder_id": "2", "up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"1873587224"}}
{"goods_id":"1873587224","goods_name":"netis WF2003 와이파이 공유기 유무선 인터넷","goods_price":20300,"goods_page_url":"https://item.gmarket.co.kr/Item?goodscode=1873587224","goods_photo_url":["/goods14-1.png"],"folder_id": "2", "up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"2871397012"}}
{"goods_id":"2871397012","goods_name":"와콤 타블렛 CTL-672 One by Wacom 그림 필기 태블릿 중형 +펜심세트+","goods_price":98900,"goods_page_url":"https://item.gmarket.co.kr/Item?goodscode=2871397012","goods_photo_url":["/goods15-1.png"],"folder_id": "2", "up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"2057297813"}}
{"goods_id":"2057297813","goods_name":"갤럭시S23 S22 S21 S20 노트20 노트10 노트9 플러스 울트라 FE 풀커버 카메라 보호 링 거치대 핸드폰케이스","goods_price":9510,"goods_page_url":"https://item.gmarket.co.kr/item?goodscode=2057297813&pos_shop_cd=GE&pos_class_cd=100000056&pos_class_kind=L","goods_photo_url":["/goods16-1.png"],"folder_id": "2", "up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"2332953460"}}
{"goods_id":"2332953460","goods_name":"가죽 갤럭시Z플립5 Z플립4 Z플립3 제트지 럭셔리 골드링 스탠딩 핸드폰 케이스","goods_price":23340,"goods_page_url":"https://item.gmarket.co.kr/Item?goodsCode=2332953460","goods_photo_url":["/goods17-1.png"],"folder_id": "2", "up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"3326659048"}}
{"goods_id":"3326659048","goods_name":"DB400S6A 6세대i3 8GB SSD128G+HDD 2TB 윈도우10 삼성 22인치 모니터 포함 키보드 마우스 증정","goods_price":189000,"goods_page_url":"https://item.gmarket.co.kr/item?goodscode=3326659048&pos_shop_cd=GE&pos_class_cd=100000002&pos_class_kind=L","goods_photo_url":["/goods18-1.png"],"folder_id": "2", "up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"1910037083"}}
{"goods_id":"1910037083","goods_name":"애슬론 3000G_SSD 120G 사무용컴퓨터 조립PC_687","goods_price":261000,"goods_page_url":"https://item.gmarket.co.kr/Item?goodscode=1910037083","goods_photo_url":["/goods19-1.png"],"folder_id": "2", "up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"2857356743"}}
{"goods_id":"2857356743","goods_name":"사이보그 15 A12VF 12세대 인텔i7/RTX4060/8G/512GB/144Hz/OS미탑재 마우스","goods_price":1349000,"goods_page_url":"https://item.gmarket.co.kr/item?goodscode=2857356743&gate_id=604F5F76-27D3-4C4A-A8A2-DF8789517EB7","goods_photo_url":["/goods20-1.png"],"folder_id": "2", "up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"103732608"}}
{"goods_id":"103732608","goods_name":"라임 리얼 커버 핑크 쿠션20g/V 콜라겐 앰플 쿠션","goods_price":35000,"goods_page_url":"https://item.gmarket.co.kr/item?goodscode=103732608&pos_shop_cd=GE&pos_class_cd=100000005&pos_class_kind=L","goods_photo_url":["/goods21-1.png"],"folder_id": "3", "up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"2496308252"}}
{"goods_id":"2496308252","goods_name":"쿠폰/더블사은품) 참존 비건콜라겐 밀착쿠션 택1 +미스트 00라이트베이지/01내추럴베이지/02샌드베이지","goods_price":19800,"goods_page_url":"https://item.gmarket.co.kr/Item?goodscode=2496308252","goods_photo_url":["/goods22-1.png"],"folder_id": "3", "up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"999217348"}}
{"goods_id":"999217348","goods_name":"스틱 파운데이션 8colors 1+1 컨투어링/쉐딩/하이라이터","goods_price":17900,"goods_page_url":"https://item.gmarket.co.kr/Item?goodscode=999217348","goods_photo_url":["/goods23-1.png"],"folder_id": "3", "up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"674439477"}}
{"goods_id":"674439477","goods_name":"커버 파운데이션 10colors","goods_price":9900,"goods_page_url":"https://item.gmarket.co.kr/Item?goodscode=674439477","goods_photo_url":["/goods24-1.png"],"folder_id": "3", "up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"1836500435"}}
{"goods_id":"1836500435","goods_name":"1+1+1 지워지지 않는 틴트 스틱 BEST 컬러","goods_price":14900,"goods_page_url":"https://item.gmarket.co.kr/Item?goodsCode=1836500435","goods_photo_url":["/goods25-1.png"],"folder_id": "3", "up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"100000020"}}
{"goods_id":"100000020","goods_name":"자연담은 배추김치 10KG (포기김치) / HACCP 인증 / 아삭한 생포기김치 맛보기 찬스특가","goods_price":19800,"goods_page_url":"https://item.gmarket.co.kr/item?goodscode=3324469689&pos_shop_cd=GE&pos_class_cd=100000020&pos_class_kind=L","goods_photo_url":["/goods31-1.png"],"folder_id": "4", "up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"2085735343"}}
{"goods_id":"2085735343","goods_name":"국내산 알찬암꽃게 간장게장 2.5kg 연평도 알배기꽃게 4~5마리내외","goods_price":31800,"goods_page_url":"https://item.gmarket.co.kr/Item?goodsCode=2085735343","goods_photo_url":["/goods32-1.png"],"folder_id": "5", "up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"1642845083"}}
{"goods_id":"1642845083","goods_name":"여보게웃게 간장새우장 400g (10미)","goods_price":14500,"goods_page_url":"https://item.gmarket.co.kr/Item?goodscode=1642845083","goods_photo_url":["/goods33-1.png"],"folder_id": "5", "up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"2250306667"}}
{"goods_id":"2250306667","goods_name":"통영 생굴 2kg (25320원 최종혜택가) 횟감용 햇굴 통영 49번 경매인 산지직송","goods_price":42200,"goods_page_url":"https://item.gmarket.co.kr/item?goodscode=2250306667&gate_id=FAF24599-F1E0-4122-8044-24EA16BBAB1A","goods_photo_url":["/goods34-1.png"],"folder_id": "5", "up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"2365987650"}}
{"goods_id":"2365987650","goods_name":"버터구이 오징어 60gx10팩(600g)","goods_price":23400,"goods_page_url":"https://item.gmarket.co.kr/Item?goodscode=2365987650","goods_photo_url":["/goods35-1.png"],"folder_id": "5", "up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"2257452300"}}
{"goods_id":"2257452300","goods_name":"Clean Code(클린 코드) 애자일 소프트웨어 장인 정신","goods_price":29700,"goods_page_url":"https://item.gmarket.co.kr/Item?goodscode=2257452300","goods_photo_url":["/goods41-1.png"],"folder_id": "5", "up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"1774461839"}}
{"goods_id":"1774461839","goods_name":"리팩터링 2판 : 코드 구조를 체계적으로 개선하여 효율적인 리팩터링 구현하기","goods_price":31500,"goods_page_url":"https://item.gmarket.co.kr/Item?goodscode=1774461839","goods_photo_url":["/goods42-1.png"],"folder_id": "5", "up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"686165866"}}
{"goods_id":"686165866","goods_name":"객체지향의 사실과 오해 : 역할 책임 협력 관점에서 본 객체지향","goods_price":18000,"goods_page_url":"https://item.gmarket.co.kr/Item?goodscode=686165866","goods_photo_url":["/goods43-1.png"],"folder_id": "5", "up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"1617757585"}}
{"goods_id":"1617757585","goods_name":"오브젝트 : 코드로 이해하는 객체지향 설계","goods_price":34200,"goods_page_url":"https://item.gmarket.co.kr/Item?goodscode=1617757585","goods_photo_url":["/goods44-1.png"],"folder_id": "5", "up_dt":"2023-12-05"}
{"index":{"_index":"goods","_id":"729346698"}}
{"goods_id":"729346698","goods_name":"실용주의 사고와 학습","goods_price":19800,"goods_page_url":"https://item.gmarket.co.kr/Item?goodscode=729346698","goods_photo_url":["/goods45-1.png"],"folder_id": "5", "up_dt":"2023-12-05"}
'

echo "\nES에 초기 데이터가 적재가 완료되었습니다."
