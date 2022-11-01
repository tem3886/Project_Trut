# 시내버스를 이용한 춘천시 관광 동선 안내 서비스

## 개요
* 춘천시 관광지 공공데이터와 Odsay의 버스 길찾기 Api를 이용한 관광지 동선 안내 웹 프로젝트
* 개발 기간: 19일
* 참여도: 100%(개인 프로젝트)

## 사용 기술 및 개발 환경
* Framework: Spring Boot(2.7.3,Gradle,jar), Spring MVC
* Language: JAVA(17), HTML, Thymeleaf
* DB: H2 Database(2.1.214)
* Tool: Intellij, GitHub
* Library: LomBok, MyBatis, Json-Simple, H2 Database, Validation

## 내용
#### DB 구조
* MEMBER
  * id bigint, generated by default as identity, Primary key
  * login_id varchar(20)
  * password varchar(20)
  * name varchar(20)

* TOUR
  * id bigint, primary key, foreign key references MEMBER(id)
  * date_time DATE, primary key
  * departure varchar(20)
  * destination varchar(20)
  * title_a varchar(20)
  * title_b varchar(20)
  * title_c varchar(20)

* COORDINATE
  * title varchar(20), primary key
  * map_x varchar(20)
  * map_y varchar(20)

* MEMBER: 회원을 저장하는 TABLE, id를 primary key로 db에서 자동으로 값을 지정
* TOUR: 출발지, 관광지, 목적지를 저장하는 TABLE
* COORDINATE: 출발지, 관광지, 목적지의 이름과 좌표를 저장하는 TABLE


#### 구현기능
* 회원가입/로그인/로그아웃
  * HttpSession으로 세션 생성, Interceptor로 차단, 로그아웃 시 세션 삭제
* 개인정보 수정
* 관광지 목록 보기
  * TOUR 테이블의 date_time으로 내림차순 정렬, 날짜 클릭시 길찾기 페이지로 이동
  * Tour Api사용, Api에 1페이지 마다 10개의 관광지 호출, 페이징 기능으로 Api에 n페이지 호출
* 출발지/목적지 선택, 관광지 추가 삭제
  * 임시로 저장할 로컬 저장소 사용, Scope를 session으로 사용해 세션마다 저장소 할당
* 버스 길찾기
  * 순열 사용, 좌표상 거리로 정렬, 버스 길찾기를 시작할 때 DB에 저장, Odsay Api사용
* 버스 경로 지도에 표시
  * 네이버 지도 Api 사용, Odsay Api로 버스 경로 좌표 호출 지도에 경로 표시


## 추가 사항
* 2022-11-01: JPA 
