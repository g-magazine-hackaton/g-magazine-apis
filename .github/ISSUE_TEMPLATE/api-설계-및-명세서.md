---
name: API 설계 및 명세서
about: API 설계 및 명세서 공유용 이슈 템플릿
title: ''
labels: API 설계
assignees: ''

---

# API

## 00 조회
### Request
* method: `GET`
* path: `/path/{paramVariable}?query1=value1&query2=value2`
* header: 
```json
{
    "Content-Type": "application/json; charset=utf-8"
}

### Response
* http-status: `200 OK`
```json
{
   "success": true,
   "responseKey": "responseValue"
}
```

## 00 생성
### Request
* method: `POST`
* path: `/path/{paramVariable}`
* header: 
```json
{
    "Content-Type": "application/json; charset=utf-8"
}
* body:
```json
{
   "requestKey": "requestValue"
}
```

### Response
* http-status: `201 Created`
```json
{
   "success": true,
   "responseKey": "responseValue"
}
```
