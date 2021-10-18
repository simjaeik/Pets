## web - server
> 다양한 API를 제공하고 데이터베이스를 관리합니다

### 사용된 기술
![image](https://user-images.githubusercontent.com/43948654/137658202-2fedff57-4e57-4116-809e-2e1ec1781604.png)


### 서버 아키텍처
![image](https://user-images.githubusercontent.com/43948654/137657686-c7b65b1d-ab96-443d-a554-6a8e493d9ac8.png)


### 디렉토리 구조

```
 +-- src
    +-- config - DB관련 세팅
    +-- controller - router에서 동작할 함수들 표현
    +-- lib - 기타 함수
        +-- utill - 자주 쓰이는 함수 모음
    +-- model - DB 관련 함수
    +-- passport - passport 관련 함수
    +-- routes - 라우팅
        +-- middleware - 미들웨어
    +-- service - 비즈니스 로직
    app.js - endpoint
 +-- .env - AWS 관련 민감정보 은닉
 +-- .gitignore
 +-- pakage-lock.json
 +-- pakage.json
 +-- readme.md
```



### [API 명세서](https://www.notion.so/API-e6f9824c71344c49aaef5658f3be2a4a)







