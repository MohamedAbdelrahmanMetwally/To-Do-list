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

- **app/**
  - **src/main/**
    - **java/com/example/to_dolist/**
      - `MainActivity.java` – Main screen with buttons for all actions.
      - `Viewing.java` – Displays all tasks in a RecyclerView.
      - `UpdateActivity.java` – Updates tasks by ID.
      - `AdditionActivity.java` – Adds new tasks.
      - `DeletionActivity.java` – Deletes tasks by ID.
      - `Adapter.java` – RecyclerView adapter for displaying tasks.
      - `Model.java` – Data model class for a task.
      - `Database.java` – SQLite database helper class.
    - **res/layout/**
      - `activity_main.xml` – Layout for the main screen.
      - `activity_update.xml` – Layout for the update screen.
      - `activity_viewing.xml` – Layout for the viewing screen.
      - `card.xml` – Layout for individual RecyclerView items.
    - `AndroidManifest.xml` – App manifest file.
  - `build.gradle` – App build configuration.
- `README.md` – Project documentation.
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
