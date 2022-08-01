# 🏆 **Catch Prize !**

> ### WebRTC 를 이용한 화상채팅 보드게임 플랫폼

<br/>

## 🔗 **링크 모음**

<hr/>

> [팀 노션 페이지](https://awesome-gardenia-42a.notion.site/3-3ffec97331794949820351b077cb72a2)

> [오늘 한 일(TIL)](./TIL.md)

<br/>

## 🙉 **SSAFY 2반 3팀**

<hr/>

> 👨‍💻 배인수 - **팀장**, BE

> 👨‍💻 권순석 - BE

> 👨‍💻 이상준 - BE

> 👨‍💻 김도연 - FE

> 👩‍💻 염수홍 - FE

> 👨‍💻 황태희 - FE

<br/>

## ⚡ **프로젝트 개요**

<hr/>

- 기간 : 2022.07.05 ~ 2022.08.19

- 목표
  - 브라우저를 통해 음성, 영상, 채팅을 통해 서로 소통하며 보드게임을 온라인으로 플레이
  - 회원가입 없이 SNS 아이디 등을 이용하여 간편하게 로그인
  - 친구가 없는 유저들도 즐길 수 있게 게임 방 목록 등의 기능 지원
  - 여러가지 게임을 통합하여 즐길 수 있는 보드게임 플랫폼

<br/>

## 🔎 **프로젝트 소개**

<hr/>

> 상세 게임 목록 및 소개 (작성 필요)

<br/>

## 💡 **구현 기능**

<hr/>

> 구현 기능 목록 및 서술 (작성 필요)

<br/>

## ⚙️ **기술 스택**

<hr/>

> ### 시스템 아키텍처

<br/>

![image](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/09e70e44-a47e-48cb-977a-e36d124774b8/_%281%29.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220801%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220801T000452Z&X-Amz-Expires=86400&X-Amz-Signature=13c81188817c76586b533e2328e5093c200f261a1545eece3a2abc3f548ed292&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22%25E3%2585%2587%25E3%2585%2587%25E3%2585%2587%2520%281%29.jpg%22&x-id=GetObject)

- Frontend
  - Vue - 3.x
  - Vuex - x*.x.x*
  - Vue Router - x.x.x
  - sockjs-client - x.x.x
  - webstomp-client x.x.x
- Backend
  - MySQL - 8.0.28
  - Redis - x.x.x
  - Spring Boot - 2.7.1
- WebRTC
  - \*\*\* - x.x.x
- Deploy
  - AWS
    - EC2
    - RDS
  - NginX
  - Docker
  - Jenkins

<br/>

## 🌵 **컨벤션**

<hr/>

> ### Commit Message Convention

| emoji |    type    |         desc         |
| :---: | :--------: | :------------------: |
|  ⚡   |   `feat`   |   새로운 기능 추가   |
|  🛠️   |   `fix`    |      버그 수정       |
|  📝   |   `docs`   |      문서 관련       |
|  🎨   |  `style`   |     스타일 관련      |
|  ⚙️   | `refactor` |    코드 리팩토링     |
|  🚗   |   `test`   |   테스트 관련 코드   |
|  🌵   |  `chore`   |      설정 변경       |
|  🐋   |   `cicd`   | ci/cd 관련 파일 수정 |
|  ✒️   |   `comment`   | 필요한 주석 추가 및 변경 |

```yaml
emoji type : subject

body (optional)
...
...
...

footer (optional)
```

<br/>

> ### Git Branch Convention

- master
  - frontend-develop
    - fe/feature/login
  - backend-develop
    - be/feature/login

<br/>

- `feature/login`과 같이 자신이 맡은 기능을 나타내는 브랜치를 로컬에 생성 후 작업
- `feature/login`으로 모든 작업 후 원격 저장소에 `develop branch`에 push하여 PR
- PR에서 서로 코멘트를 남기고 리뷰 후 `develop branch`로 merge
- branch를 merge할 때 항상 -no-ff 옵션을 붙여 branch에 대한 기록을 유지한다.
- 완료되지 않은 PR은 앞에 `Draft:` 를 붙힌다.

<br/>

## 🌐 **배포 방법**

<hr/>

> 배포 방법 (작성 필요)

<br/>
