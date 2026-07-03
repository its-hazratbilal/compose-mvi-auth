# 🔐 Compose MVI Auth Template

<p align="center">
  <b>A modern Android authentication app built with Jetpack Compose, MVI Architecture, Hilt, Retrofit, Coroutines, and Material 3.</b>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white"/>
  <img src="https://img.shields.io/badge/Jetpack%20Compose-Material%203-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white"/>
  <img src="https://img.shields.io/badge/MVI-Architecture-FF9800?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Hilt-DI-0F9D58?style=for-the-badge"/>
</p>

---

## 📱 Overview

Compose MVI Auth Template is a simple but production-style Android authentication project demonstrating how to build a scalable application using modern Android development practices.

The project focuses on clean state management using **MVI (Model–View–Intent)** and serves as a solid starting point for larger Android applications.

It includes complete Login and Registration flows powered by Retrofit, Hilt, Coroutines, Navigation Compose, and Material 3.

---

# ✨ Features

- 🔑 Login screen
- 📝 Registration screen
- 🧭 Navigation Compose
- 🎯 MVI Architecture
- 💉 Dependency Injection with Hilt
- 🌐 Retrofit networking
- ⚡ Kotlin Coroutines
- 📦 Repository Pattern
- 📱 Material 3 UI
- 🔄 One-time UI Effects
- 🔐 API Key Interceptor

---

# 🛠 Tech Stack

| Technology | Purpose |
|------------|---------|
| Kotlin | Programming Language |
| Jetpack Compose | Declarative UI |
| Material 3 | UI Components |
| MVI | State Management |
| ViewModel | UI State Holder |
| Navigation Compose | Screen Navigation |
| Hilt | Dependency Injection |
| Retrofit | REST API |
| OkHttp | HTTP Client |
| Coroutines | Asynchronous Programming |

---

# 🧠 Architecture

```
Presentation
│
├── Screen
├── ViewModel
├── Intent
├── State
└── Effect
        │
        ▼
    Repository
        │
        ▼
   Retrofit API
```

## MVI Flow

```
User Action
      │
      ▼
   Intent
      │
      ▼
  ViewModel
      │
      ▼
  Repository
      │
      ▼
     API
      │
      ▼
NetworkResult
      │
      ▼
State / Effect
      │
      ▼
  Compose UI
```

---

# 📂 Project Structure

```
app
│
├── data
│   ├── remote
│   │     ├── api
│   │     ├── dto
│   │     └── interceptors
│   └── repository
│
├── di
│
├── navigation
│
├── presentation
│   ├── login
│   ├── register
│   └── home
│
├── ui
│   ├── theme
│   └── components
│
├── utils
│
├── BaseApp.kt
└── MainActivity.kt
```

---

# 🚀 Getting Started

## Clone

```bash
git clone https://github.com/its-hazratbilal/compose-mvi-auth.git
```

Open the project in Android Studio.

---

## Configure API

This sample uses BuildConfig fields.

Create a `local.properties` file (or update yours):

```properties
BASE_URL=https://your-api-url.com/
API_KEY=your_api_key
```

Then sync Gradle.

---

## Run

Simply run the `app` module.

Minimum SDK: **26**

Target SDK: **37**

---

# 🎯 Learning Goals

This project demonstrates:

- Modern Android app architecture
- Unidirectional Data Flow (MVI)
- State vs Effect handling
- Dependency Injection with Hilt
- Repository Pattern
- Retrofit networking
- Navigation Compose
- Material 3
- Clean package structure
- Production-style project organization

---

## 👨‍💻 Author

**Hazrat Bilal**  
Senior Android Engineer  
Kotlin • Jetpack Compose • Clean Architecture • Kotlin Multiplatform (KMP) • Flutter

[![GitHub](https://img.shields.io/badge/GitHub-View%20Profile-181717?style=flat&logo=github)](https://github.com/its-hazratbilal)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-0077B5?style=flat&logo=linkedin)](https://linkedin.com/in/its-hazratbilal)

---

## ⭐ Support

If you find this project useful:

- ⭐ **Star** this repository
- 🍴 **Fork** it and build your own version
- 🐛 **Report issues** or suggest features
- 💬 **Share** it with the community

---

## 📄 License  
MIT License — feel free to use, modify, and distribute.
