# Android Checkout App

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for
development and testing purposes. See deployment for notes on how to deploy the project on a live
system.

### Installing

* Clone the repo / or unzip the provided file.

```
cd Checkout
```

### Running the application

* The application runs on [Android Studio](https://developer.android.com/studio/)
* Follow [this link](https://developer.android.com/studio/run/) to build and run the app.

### Android Architecture

* This is app is build based on MVVM architecture enhanced with states and some modification toward
  the clean architecture which was inspired by uncle BOB while using DI and Coroutines.
* Layered multi-module app (data - domain - presentation)

### Project Structure

#### The project has following modules:

1. **data**: It contains all the data accessing and manipulating components(Database"Local in case
   needed" , Network - model - mappers - repository implementation).
2. **domain**: It contains all the business logic components(UseCases - repository interfaces -
   domain models).
3. **presentation**: View classes(Activity/Compose Screens - ViewModel).\
   -> Each module has it's own DI package to describe how this module dependencies been provided

#### The app used the following Third parties:

1. **DI**: Dagger Hilt .
2. **Asynchronous**: Coroutine.
3. **Network**: Retrofit.
4. **UI**: Jetpack Compose.
5. **UnitTest**: mockk - junit4 - kotlinFixture.

### Future Plans:

1. Enhance the compose navigation code.
2. Support multiple card types with showing icon to represent each type when user type the card
   number.
3. E2E/ui test using [Kaspresso](https://github.com/KasperskyLab/Kaspresso).
4. Add snapshot testing using [paparazzi](https://github.com/cashapp/paparazzi) which doesn't
   require any physical device or emulator to run.
5. Add ktlint gradle support.
6. Github actions to build/run tests and generate release. 
