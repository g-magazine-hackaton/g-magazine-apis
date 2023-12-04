# 빌드 스테이지
FROM openjdk:11 as build

# 소스 코드 복사
WORKDIR /app
COPY . .

# Gradle Wrapper를 사용하여 프로젝트 빌드
RUN ./gradlew build

# 실행 스테이지
FROM openjdk:11

# 애플리케이션 디렉토리 생성
WORKDIR /app

# 빌드 스테이지에서 생성된 JAR 파일 복사
COPY --from=build /app/build/libs/*.jar app.jar

# 포트 8080으로 애플리케이션 실행
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
