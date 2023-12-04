# g-magazine-apis

> 저장소는 다음과 같이 구성되어있습니다.

1. kotlin API: 어플리케이션 CRUD를 위한 API
2. node API: 파일 업로드용 API
3. docker-compose.yml: kotlin/node를 한 번에 띄워주며, nginx 7001 포트를 통해 각 서버로 트래픽을 분산해준다.
4. docker-compose-db.yml: elasticsearch & kibana 로컬
   테스트용 (WAS에서는 클라우드 DB와 통신하므로 실제로는 사용할 필요 없다.)

# API

## 이미지 업로드 API

> http://localhost:7001/uploads/

[여기](https://github.com/g-magazine-hackaton/g-magazine-apis/issues/5)를 참고해주세요.

## 어플리케이션 CRUD API

> http://localhost:7001/api/

[프로젝트 보드](https://github.com/orgs/g-magazine-hackaton/projects/3)의 각 페이지 별 이슈를 참고해주세요.

## 시작하기

### 1. 저장소 복제

저장소를 로컬 시스템으로 복제합니다.

```bash
git clone https://github.com/g-magazine-hackaton/g-magazine-apis.git
```

### 2. 필수 도구 설치

- **Docker & Docker Compose:** 애플리케이션 컨테이너화를 위한 도구입니다. 맥과 윈도우에서는 Docker Desktop을 설치하면 Docker Compose도 함께 설치됩니다.
  - Docker 설치: [여기](https://docs.docker.com/get-docker/)에서 설치할 수 있습니다.
  - Docker Compose 설치: [여기](https://docs.docker.com/compose/install/)에서 설치할 수 있습니다.

### 서버 실행

#### Run

> Docker Compose를 이용하여 `node-api`와 `kotlin-api`를 한 번에 로드합니다.  
> 서버 URL은 `http://localhost:7001` 입니다.

`docker-compose.yml` 파일이 존재하는 루트 경로에서 아래 명령어를 실행해야 합니다.

```bash
# -d 옵션은 데몬으로 로드합니다. 서버 로깅을 보고 싶다면 -d 옵션을 제거하면 됩니다.
# --build 옵션을 추가하면 매 실행 시 변경 사항이 발생했다면 이미지부터 재생성합니다.
$ docker-compose up --build -d
```

#### Terminate

`docker-compose.yml` 파일이 존재하는 루트 경로에서 아래 명령어를 실행해야 합니다.

```bash
$ docker-compose down
```
