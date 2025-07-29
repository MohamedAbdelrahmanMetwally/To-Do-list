# 📝 To-Do List Android App

A simple Android application for managing daily tasks (Add, Update, Delete, View) using SQLite database and a clean, user-friendly interface.

---

## 📱 Features
- ➕ **Add** a new task with title and description.
- ✏️ **Update** existing tasks.
- 🗑️ **Delete** tasks.
- 📋 **View** all tasks in an elegant list using RecyclerView.
- 💾 Store data locally using SQLite.
- 🎨 Simple and modern UI design with Material Design and CardView.

---

## 🛠️ Tech Stack
- **Programming Language:** Java ☕
- **Database:**
- SQLite
- Shared prefrences
- **UI Design:** XML + Material Design
- **Android Libraries:**
  - RecyclerView
  - CardView
  - ConstraintLayout
---

## 📂 Project Structure

📦 to-do-list-android
┣ 📂 app
┃ ┣ 📂 src
┃ ┃ ┣ 📂 main
┃ ┃ ┃ ┣ 📂 java/com/example/to_dolist
┃ ┃ ┃ ┃ ┣ 📜 MainActivity.java
┃ ┃ ┃ ┃ ┣ 📜 Viewing.java
┃ ┃ ┃ ┃ ┣ 📜 UpdateActivity.java
┃ ┃ ┃ ┃ ┣ 📜 AdditionActivity.java
┃ ┃ ┃ ┃ ┣ 📜 DeletionActivity.java
┃ ┃ ┃ ┃ ┣ 📜 Adapter.java
┃ ┃ ┃ ┃ ┣ 📜 Model.java
┃ ┃ ┃ ┃ ┗ 📜 Database.java
┃ ┃ ┃ ┣ 📂 res/layout
┃ ┃ ┃ ┃ ┣ 📜 activity_main.xml
┃ ┃ ┃ ┃ ┣ 📜 activity_update.xml
┃ ┃ ┃ ┃ ┣ 📜 activity_viewing.xml
┃ ┃ ┃ ┃ ┗ 📜 card.xml
┃ ┃ ┃ ┗ 📜 AndroidManifest.xml
┃ ┗ 📜 build.gradle
┗ 📜 README.md
---

## 🚀 How to Run
1. Open the project in **Android Studio**.
2. Make sure **Android SDK** is installed.
3. Run the app on an **emulator** or a **real device**.

---

## 📌 Notes
- Works completely **offline**.
- Can be extended to support **Firebase** or any cloud-based storage.

---

## 📄 License
This project is open-source under the **MIT License**.  
You are free to use, modify, and distribute it.

---
👨‍💻 **Developer:** [Mohamed](https://github.com/MohamedAbdelrahmanMetwally)
