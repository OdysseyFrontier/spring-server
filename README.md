# 📜 Contents
- [1️⃣ 프로젝트 개요](#1️⃣-프로젝트-개요)
- [2️⃣ 주요 기능](#2️⃣-주요-기능)
- [3️⃣ 개발 환경](#3️⃣-개발-환경)
- [4️⃣ 기술 특이점](#4️⃣-기술-특이점)
- [5️⃣ 산출물](#5️⃣-산출물)
- [6️⃣ 팀원 소개](#6️⃣-팀원-소개)
- [7️⃣ 실제 화면](#7️⃣-실제-화면)

---

# 1️⃣ 프로젝트 개요

### **프로젝트명**: **OdysseyFrontiers**
**서비스 특징**:  
효율적인 여행 계획을 돕기 위한 **지도 기반 여행 일정 관리 및 팔로우 기반 공유 커뮤니티**  

**진행 기간**: 2024. 05. 06 ~ 05. 23  
**팀원**: 조시현, 김소연

### **📌 프로젝트 목표**
- **사용자 맞춤형 여행 계획 서비스 제공**  
- **지도 기반 관광지 탐색 기능 제공**  
- **회원 간 여행 계획 공유 및 소통 지원**

### **📌 핵심 가치**
- 📍 **지도 기반 여행 계획**
- 📊 **사용자 맞춤형 추천**
- 🔗 **회원 간 여행 공유**

---

# 2️⃣ 주요 기능

### 🚀 **기능적 요구사항**
- **회원 관리** (회원 가입, 조회, 수정, 검색, JWT 인증)
- **회원 팔로우 기능** (팔로우, 언팔로우, 팔로잉 목록 관리)
- **여행 계획 관리** (여행 일정 생성, 수정, 공유)
- **관광지 검색** (Google Map API 활용, 지역/키워드 검색)
- **사용자가 직접 등록하는 핫플레이스 기능** (개인 추천 장소 등록)
- **여행지 및 핫플레이스 좋아요 및 조회수 기능** (인기 장소 트래킹)
- **게시판 기능** (공지사항, 커뮤니티 글쓰기, 수정, 삭제, 좋아요)
- **통계 제공** (인기 관광지, 사용자 활동 통계)
- **OpenAI ChatGPT 기반 여행 계획 지원** (사용자가 GPT와 채팅하여 맞춤형 여행 일정 생성)

### 🛠 **비기능적 요구사항**
- **공공데이터 정확성 보장** (공공 API 활용)
- **직관적인 UI/UX** (반응형 웹 적용)
- **서비스 가용성** (모든 디바이스에서 이용 가능)
- **빠른 응답 속도** (최적화된 데이터 처리)


---

# 3️⃣ 개발 환경

### **🔹 주요 기술**
- **백엔드**: Java, Spring Boot, MyBatis, RESTful API, JWT 인증
- **프론트엔드**: Vue.js, JavaScript, Bootstrap, Pinia, Chart.js
- **데이터베이스**: MySQL (회원, 여행지, 핫플레이스, 계획 관리)
- **지도 API**: Google Map API
- **AI 연동**: OpenAI ChatGPT API 활용

### **🔹 배포 환경**
- **백엔드**: 로컬 환경에서 JAR 파일 실행
- **프론트엔드**: Vite 서버 기반 실행

---

# 4️⃣ 기술 특이점
### ✅ **보안 강화**
- JWT 기반 인증 시스템 적용  

### ✅ **지도 기반 여행 계획 기능**
- Google Map API를 활용한 **관광지 검색 및 여행 계획 생성**  
- **사용자가 직접 핫플레이스를 등록하여 공유 가능**  
- **Drag & Drop 기능을 활용한 여행 계획 수정**  

### ✅ **데이터 최적화**
- **MyBatis 기반 SQL 쿼리 직접 작성 및 최적화**  
- **RESTful API 설계** (백엔드-프론트 간 데이터 효율적 연동)

### ✅ **여행지 및 핫플레이스 좋아요 및 조회수 기능**
- **사용자가 좋아요한 핫플레이스 및 관광지 데이터 저장**
- **인기 핫플레이스 및 여행지 조회수를 통한 트렌드 분석**

### ✅ **반응형 UI/UX**
- **Vue.js 기반 SPA 개발**
- **Bootstrap을 활용한 반응형 웹 디자인**
- **모바일 & 데스크탑 UI 최적화**
- **Pinia를 활용한 상태 관리 및 데이터 흐름 최적화**
- **Chart.js를 활용한 통계 시각화**

---

# 5️⃣ 산출물

### **📌 시스템 아키텍처**
![image](https://github.com/user-attachments/assets/c4648501-f978-4a3f-ada7-34be068c2354)
![아키텍처](#)

### **📌 데이터베이스 설계 (ERD)**
<img width="464" alt="image" src="https://github.com/user-attachments/assets/a1b625bc-ccaa-4134-8fdb-2d1743f9150f" />
![ERD](#)

### **📌 UI & MockUp**
📌 **[Figma 디자인 보기](#)**

### **📌 기능 명세서**
📌 **[기능 명세서 보기](#)**

---

# 6️⃣ 팀원 소개
| 이름 | 역할 |
|------|------|
| **김소연** | 백엔드 개발 (Spring Boot, API 설계, DB 관리) |
| **조시현** | 프론트엔드 개발 (Vue.js, UI/UX, 지도 연동) |

---

# 7️⃣ 실제 화면

## 🔹 메인 페이지
<img width="400" alt="image" src="https://github.com/user-attachments/assets/266bdf11-e928-4801-aaf4-706803e2167d" />

## 🔹 관광지 검색 및 여행 계획 생성
<img width="400" alt="image" src="https://github.com/user-attachments/assets/fa9f0f78-76f2-404b-b007-d54ef68225b4" />

## 🔹 회원 화면 및 팔로우 화면
<div style="display: flex; justify-content: center;">
  <img width="350" alt="image" src="https://github.com/user-attachments/assets/17b803ab-ec8d-4cea-9a9e-1d48d9f4e7e7" />
  <img width="350" alt="image" src="https://github.com/user-attachments/assets/e24781b0-e42f-48d1-b6e8-87ed5c40e656" />
</div>

## 🔹 통계 화면
<img width="400" alt="image" src="https://github.com/user-attachments/assets/5ce4b59d-5f3d-47a6-a467-ace5c596e0d4" />

## 🔹 챗봇 화면
<img width="400" alt="image" src="https://github.com/user-attachments/assets/14caae2d-9a4e-4888-9a12-2bb77699e7bd" />

---
