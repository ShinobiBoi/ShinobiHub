# 🎬 ShinobiHub  

![Jetpack Compose](https://img.shields.io/badge/Built%20With-Jetpack%20Compose-4285F4?logo=android&logoColor=white)
![Architecture](https://img.shields.io/badge/Architecture-MVI-orange)
![License: MIT](https://img.shields.io/badge/License-MIT-green)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-purple)
![Android Studio](https://img.shields.io/badge/IDE-Android%20Studio%20Giraffe+-brightgreen)

---

## 🎥 Overview  

**ShinobiHub** is a modern Android app built using the **TMDB API**, designed to explore trending and popular movies and TV shows with a focus on **clean architecture**, **state-driven UI**, and **modern Android development practices**.  

It’s developed entirely with **Jetpack Compose** and follows the **MVI (Model–View–Intent)** pattern to ensure predictable state management and unidirectional data flow — combining elegant design with maintainable architecture.

---

## 🖼️ Screenshots  

| Home | Details | Watchlist |
|:----:|:--------:|:----------:|
| ![Home Screen](docs/screenshots/home.jpg) | ![Details Screen](docs/screenshots/detail.jpg) | ![Watchlist](docs/screenshots/watchlist.jpg) |


## ✨ Features  

🎥 **Explore Movies & TV Shows**  
Browse trending, popular, and top-rated titles powered by the TMDB API.  

🔑 **TMDB Authentication**  
Secure login using TMDB request tokens to manage user sessions safely.  

🧠 **MVI Architecture**  
Predictable and testable UI powered by Actions, Results, and ViewStates.  

🎨 **Dynamic Themes**  
Automatic light/dark mode support with Material 3 dynamic color schemes.  

💾 **Persistent Session Management**  
Built with **DataStore** for secure, lightweight data persistence.  

⚡ **Reactive State Management**  
Smooth async flow using **Kotlin Coroutines** and **StateFlow**.  

🧩 **Dependency Injection**  
Modular and scalable design with **Dagger Hilt**.  

🧭 **Navigation Made Simple**  
Compose Navigation ensures type-safe and intuitive navigation.  

---

## 🛠 Tech Stack  

| Layer | Technologies |
|-------|---------------|
| **Language** | Kotlin |
| **UI** | Jetpack Compose + Material 3 |
| **Architecture** | MVI (Model–View–Intent) |
| **DI** | Dagger Hilt |
| **Persistence** | DataStore Preferences |
| **Async** | Coroutines + StateFlow |
| **Networking** | TMDB API |
| **Design** | Material You + Figma |
| **Build** | Gradle KTS (Kotlin DSL) |

---


🧩 Architecture Overview

ShinobiHub follows the MVI (Model–View–Intent) architecture pattern for predictable and maintainable state handling.
Each screen defines:

Actions → User or system-triggered events.

Results → Intermediate processing outcomes.

ViewState → Immutable UI states representing the current screen.

This ensures a unidirectional data flow, making UI logic easier to debug and extend.



## 🚀 Quick Start  

### Prerequisites  
✅ Android Studio **Giraffe (or newer)**  
✅ Android **5.0+ (API 21)**  
✅ A **TMDB API key** from [themoviedb.org](https://www.themoviedb.org/)  

### Installation  

1. Clone the repository:
   ```bash
   git clone https://github.com/ShinobiBoi/ShinobiHub.git


2. Open the project in Android Studio.

3. Add your TMDB API key:
    Go to your local.properties file or create an .env file.
    Add this line:TMDB_API_KEY=your_api_key_here


4. Sync the Gradle files.

5. Run the app on your emulator or device.



🎨 Design

Every screen was designed following Material 3 principles and inspired by modern movie app UX trends.
All visuals were first prototyped in Figma to ensure consistency and aesthetic coherence.

🎨 Figma Design: https://www.figma.com/design/HzNwCGJrYLb0sNfCKzqEtU/MovieMoz---Movie-Recomendation-App----Community-?t=d9JaRPY9lacD6oDw-0

The theme dynamically adapts to system dark/light mode, keeping the viewing experience seamless.
