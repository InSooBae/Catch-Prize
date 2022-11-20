# Catch Prize!

> WebRTC 를 이용한 화상채팅 보드게임 플랫폼
> 

---

## 🙉 SSAFY 2반 3팀

- 👨‍💻 배인수 - 팀장, BE 개발 총괄 (친구, 방, 로그인 개발)
- 👨‍💻 권순석 - BE 개발, OpenVidu 적용
- 👨‍💻 이상진 - AWS, Docker, 배포 Infra
- 👨‍💻 황태희 - FE 개발 총괄, 디자인
- 👨‍💻 김도연 - FE 개발, Socket, 게임 시스템 구현
- 👨‍💻 염수홍 - FE 개발, OpenVidu 적용

---

## ⚡ 프로젝트 개요

- 기간 : 2022.07.05 ~ 2022.08.19
- 브라우저를 통해 음성, 영상, 채팅을 통해 서로 소통하며 보드게임을 온라인으로 플레이
- 회원가입 없이 SNS 아이디 등을 이용하여 간편하게 로그인
- 친구가 없는 유저들도 즐길 수 있게 게임 방 목록 등의 기능 지원
- 여러가지 게임을 통합하여 즐길 수 있는 보드게임 플랫폼

---

## 🔎 프로젝트 소개

- WebRTC를 이용하여 화상채팅 보드게임을 진행하는 플랫폼
    - 상대의 얼굴을 보며 게임을 하는 심리전 게임이 주류
    - 다양한 아이템 사용으로 더욱 흥미진진하게 진행 가능

- 호불호 포커
    - 심리전을 통해 상대 카드 종류를 맞추는 게임



https://user-images.githubusercontent.com/48831854/202899793-06a61130-2300-4a20-906e-ec73848ab893.mp4



---

## 💡 구현 기능

![image29.gif](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/6e94efe9-68e7-42c7-95ee-cb2b618b716f/image29.gif?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20221120%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20221120T111537Z&X-Amz-Expires=86400&X-Amz-Signature=f54aeaec79e22b88e61e2fc3d389d0c23b9c0c60c37703b27f27ddaee9cf128b&X-Amz-SignedHeaders=host&x-id=GetObject)

- OAuth2.0 로그인
- 친구 기능
    - 친구 온라인 상태 확인 가능
    - 친구 추가 및 제거 실시간 적용
    - 친구 게임 방 참가 기능

![친구 오프라인](https://user-images.githubusercontent.com/97645988/195661806-e9642ad8-bd4a-4898-95e0-32e2930fb794.gif)
![친구추가](https://user-images.githubusercontent.com/97645988/195661818-2305a949-fbe8-4f25-8e8a-6f8b224ba17b.gif)

- 게임 로비 기능
    - 게임 방 추가 및 제거 실시간 확인



- 게임 기능
    - 카드를 정하여 상대 공격 및 수비 가능
    - 실시간으로 상대 화상을 체크하며 게임 진행 가능

![image30-min.gif](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/f392dde0-928b-4016-a182-ca00dc9f7a85/image30-min.gif?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20221120%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20221120T111537Z&X-Amz-Expires=86400&X-Amz-Signature=76a95989c477d23b8c60917ef6db2802c8b4507e46739ce8762b41d9dadf8491&X-Amz-SignedHeaders=host&x-id=GetObject)

- 아이템 기능
    - 비디오 화면 상하 뒤집기
    - 비디오 화면 흑백 적용

![image31.gif](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/08a0e157-99f5-4ae6-bcf8-f699733acdbb/image31.gif?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20221120%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20221120T111734Z&X-Amz-Expires=86400&X-Amz-Signature=a9afbe02867c071c0f6b19ab90dec9efde034379217dfa46d2d1eb3fdaca69d3&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22image31.gif%22&x-id=GetObject)
---

## ⚙️ 기술 스택

### 시스템 아키텍처

![sysarch.png](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/a99a92a0-6586-4190-9391-d80dc0ccd0e2/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20221120%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20221120T111905Z&X-Amz-Expires=86400&X-Amz-Signature=9c15c01281f3deeb2030cc838f5edea9a5b93511c660397b5a4d6966d8d1dcc7&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Untitled.png%22&x-id=GetObject)

### ✨ **Frontend**

- vue - 3.2.13
- vuex - 4.0.2
- vue-axios - 3.4.1
- vue router - 4.1.2
- socket.io-client - 4.5.1
- element-plus - 2.2.9
- openvidu-browser - 2.22.0

### ✨ **Backend**

- MySQL - 8.0.28
- Redis
- Spring Boot - 2.7.1
- WebRTC
- Deploy
- AWS
- EC2
- RDS
- NginX
- Docker
- Jenkins

---

## 🌵 Git 컨벤션

### 커밋 메세지 컨벤션

| emoji | type | desc |
| --- | --- | --- |
| ⚡ | feat | 새로운 기능 추가 |
| 🛠️ | fix | 버그 수정 |
| 📝 | docs | 문서 관련 |
| 🎨 | style | 스타일 관련 |
| ⚙️ | refactor | 코드 리팩토링 |
| 🚗 | test | 테스트 관련 코드 수정 |
| 🌵 | chore | 설정 변경 |
| 🐋 | cicd | 배포 관련 수정 |
| ✒️ | comment | 주석 추가 및 변경 |

```markup
emoji type : subject

body (optional)
...
...

footer (optional)
```

### Git 브랜치 컨벤션

- master
    - frontend-develop
        - fe/feature/login
    - backend-develop
        - be/feature/login

- `feature/login`과 같이 자신이 맡은 기능을 나타내는 브랜치를 로컬에 생성 후 작업
- `feature/login`으로 모든 작업 후 원격 저장소에 `develop branch`에 push하여 PR
- PR에서 서로 코멘트를 남기고 리뷰 후 `develop branch`로 merge
- branch를 merge할 때 항상 -no-ff 옵션을 붙여 branch에 대한 기록을 유지한다.
- 완료되지 않은 PR은 앞에 `Draft:` 를 붙힌다.

---

## 🌐 배포 방법

### 배포 서버 1 (유저 서버)

- `.env` 파일을 다음과 같이 작성한다

```markup
USER_SV_PORT=<port for user server>
USER_SV_DB_URL=jdbc:mysql://mysql:<mysql port>/<mysql database>
USER_SV_DB_USERNAME=<mysql user name>
USER_SV_DB_PASSWORD=<mysql password>
USER_SV_BASE_URL=https://user.catch-prize.com
USER_SV_BASE_CLIENT_URL=https://catch-prize.com
USER_SV_JWT_SECRET_KEY=<jwt secret key>
USER_SV_KAKAO_CLIENT_ID=<kakao client id>
USER_SV_KAKAO_CLIENT_SECRET=<kakao client secret>
USER_SV_KAKAO_REDIRECT_URI=/oauth2/callback/kakao
USER_SV_GOOGLE_CLIENT_ID=<google cliend id>
USER_SV_GOOGLE_CLIENT_SECRET=<google client secret>
USER_SV_GOOGLE_REDIRECT_URI=/oauth2/callback/google
USER_SV_NAVER_CLIENT_ID=<naver client id>
USER_SV_NAVER_CLIENT_SECRET=<naver client secret>
USER_SV_NAVER_REDIRECT_URI=/oauth2/callback/naver
USER_SV_OPENVIDU_URL=https://ov.catch-prize.com/
USER_SV_OPENVIDU_SECRET=<openvidu secret>

MYSQL_USER=<mysql user name>
MYSQL_PASSWORD=<mysql password>
MYSQL_DATABASE=catchprize
MYSQL_ROOT_PASSWORD=<mysql root password>
MYSQL_TCP_PORT=<port for mysql>

INGAME_SV_PORT=<port for ingame server>
```

- `./init-letsencrypt.sh` 최초 배포 시 위 명령어를 실행하여 지정된 도메인들에 대해서 https를 적용한다.
- `docker-compose up` 위 명령어를 실행하여 필요한 컨테이너를 생성하고 실행한다.

### 배포 서버 2 (OpenVidu 서버)

- Openvidu Server는 배포의 편의를 위해 AWS CloudFormation을 이용한다.
- `https://docs.openvidu.io/en/stable/deployment/ce/aws/`의 절차를 그대로 따른다.
- `/opt/openvidu/.env`에서 필요한 환경변수를 변경할 수 있다.

---

## 🔗 링크 모음

- [팀 노션 페이지](https://www.notion.so/3ffec97331794949820351b077cb72a2)
- [OpenVidu 홈페이지](https://openvidu.io/)

---
