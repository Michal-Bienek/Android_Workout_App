# Android Training App

## Table of Contents
- [Installation](#installation)
- [Description](#description)
  - [Purpose](#purpose)
  - [Structure](#structure)
  - [Requirements](#requirements)
- [Tools](#tools)

## Installation
1. Download and install [Android Studio](https://developer.android.com/studio)
2. Download the project and open it in Android Studio
3. Install the virtual device
4. Run the project
    - In case of "Tag mismatch" error, clean and rebuild the project

## Description
### Purpose
The purpose of this project is to design and implement a mobile training  app as a university project.

### Structure
The project is built using the MVVM pattern:
- Database - SQLite
- Model - RoomDatabase, consisting of:
  - DAO - data access objects, implementation of methods defined in the "DAO" interface is automatically generated during compilation.
  - Entity - representing tables in the database.
- Repository - separates the data access layer from the presentation layer. Performs most operations on the database asynchronously.
- ViewModel - intermediate layer between the view and the model. Prepares observable data for the view layer and interacts with the model by reading and updating data.
- View - registers user-initiated events in the GUI and updates the GUI by observing the ViewModel layer.

### Requirements
The app allows for:
- Creating, editing, and deleting training plans
- Creating, editing, and deleting training routines
- Displaying routines from active training plans on the main page
- Creating, editing, and deleting exercise sets in a routine
- Saving parameters of individual exercises in a routine during training
- Displaying a summary of the completed training - date, volume, with a display of an indicator showing whether the volume increased from previous trainings

## Tools
- Java
- SQLite
- Android Jetpack Libraries
