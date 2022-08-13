
# 🎯 ToDo Application

An Android mobile application that enables users to create todos. Users can also update and delete todos
#### By **[Caleb Langat](https://github.com/Mzazi25)**
## Description

The Mobile Application is still in development. Currently, Users can create, update and delete todo items

<br />

## 🎨 UI Design

***Click to View `ToDo` app Design from below 👇***

<br />

## 🌞 Day Mode



## 🌚 We Support Dark Mode Too



## 🛠 Built With

- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android
  development.
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Jetpack Compose is Android’s
  modern toolkit for building native UI.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - A coroutine is a
  concurrency design pattern that you can use on Android to simplify code that executes
  asynchronously.
- [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) - A flow is an asynchronous
  version of a Sequence, a type of collection whose values are lazily produced.
- [Jetpack DataStore](https://developer.android.com/topic/libraries/architecture/datastore) -
  Jetpack DataStore is a data storage solution that allows you to store key-value pairs or typed
  objects with protocol buffers. DataStore uses Kotlin coroutines and Flow to store data
  asynchronously, consistently, and transactionally.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) -
  Collection of libraries that help you design robust, testable, and maintainable apps.
    - [Stateflow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) - StateFlow is
      a state-holder observable flow that emits the current and new state updates to its collectors.
    - [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) - A flow is an asynchronous
      version of a Sequence, a type of collection whose values are lazily produced.
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores
      UI-related data that isn"t destroyed on UI changes.
    - [Jetpack Compose Navigation](https://developer.android.com/jetpack/compose/navigation) - The
      Navigation component provides support for Jetpack Compose applications.
    - [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) - Jetpack
      DataStore is a data storage solution that allows you to store key-value pairs or typed objects
      with protocol buffers. DataStore uses Kotlin coroutines and Flow to store data asynchronously,
      consistently, and transactionally.
- [Material Components for Android](https://github.com/material-components/material-components-android)
    - Modular and customizable Material Design UI components for Android.

<br />

## 📦 Package Structure

 ```
dev.spikeysanju.einsen
├── app                   # Application class
├── components            # All resuable components for this app
├── data                  # For data handling
│   ├── local               # Local Persistence Database. Room (SQLite) database
│   │   ├── Dao               # Data Access Object for Room
│   │   └── Database          # Database Instance
│   └── datastore
│       └── ThemePref         # Datastore Theme Preference 
├── di                        # Hilt DI Modules
├── model                     # Model class for [Task] & [Emoji]
├── navigation                # For navigation handling
│   ├── Routes                # All unique navigation routes of this app
│   └── NavGraph              # Single source for Navigation Routes of this app
├── repository                # Used to handle all data operations
├── ui.theme                  # Theme setup for this app
├── utils                     # Extension functions
├── view                      # All composables screens root folder
│   ├── add                   # Add Task Screen
│   ├── edit_task             # Edit Task Screen
│   ├── dashboard             # Dashboard Screen
│   ├── all_task              # All Task Screen
│   ├── task_details          # Task Details Screen
│   ├── emoji                 # Choose Emoji Screen
│   ├── webview               # WebView Screen
│   ├── animation             # Animation Placeholders for State handling(Loading, Error, Empty etc.)
│   ├── about                 # About Screen
│   └── viewmodel             # ViewModel 
└── MainActivity.kt           # MainActivity 

```

<br />

## 🗼 Architecture

This app uses [***MVVM (Model View
View-Model)***](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture.


## 🧰 Build-tool

Android Studio Chipmunk


## 🤝 Contribute

If you want to contribute to this app, you're always welcome!

<br>

## 📩 Contact

I know that first and foremost you are looking for a tool to solve your problems, but if you enjoy
it that much, why not tell us? We would love to hear from you 😉

DM me at 👇

* Twitter: <a href="https://twitter.com/_CalebLangat" target="_blank">@_CalebLangat</a>
* Email: langat.caleb95@gmail.com

<br>

## 🔖 License

```
MIT License
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.<br>
Copyright (c) 2022 **Caleb Langat**

```
