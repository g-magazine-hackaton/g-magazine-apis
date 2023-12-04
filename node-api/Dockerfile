# Node 이미지 사용
FROM node:16

# 애플리케이션 디렉토리 생성
WORKDIR /usr/src/app

# 애플리케이션 의존성 설치
COPY package*.json ./
RUN npm install

# 애플리케이션 소스 추가
COPY . .

# 포트 3000으로 애플리케이션 실행
EXPOSE 3000
CMD ["node", "app.js"]
