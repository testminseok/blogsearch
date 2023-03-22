# blogsearch
블로그 검색 서비스

## 빌드 결과물

아래 경로의 jar 파일 을 다운로드
https://github.com/testminseok/blogsearch/blob/dev/src/main/resources/BlogSearchApp-0.0.1-SNAPSHOT.jar

## 빌드 결과물 실행 환경변수
openapi.kakao.api.key={카카오 api Rest key}
openapi.naver.api.client-id={네이버 클라이언트 id}
openapi.naver.api.client-secret={네이버 클라이언트 secret}

## 빌드 실행 예시
java -Dfile.encoding=UTF-8 -jar BlogSearchApp-0.0.1-SNAPSHOT.jar --openapi.kakao.api.key={카카오 API REST KEY} --openapi.naver.api.client-id={NAVER 클라이언트 ID} --openapi.naver.api.client-secret={NAVER 클라이언트 secret}

