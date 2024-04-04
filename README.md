
#TemperApp

## Project Set up
- Android Studio Iguana | 2023.2.1 Patch 
- Programming Language: Kotlin
- Third Party API tool: Postman v9.1.1
- Gradle Plug in Version: 7.3.1 - > Go to File>Project Structure
- Gradle Version: 8.0
- JDK version: 17 -> Go to File>Settings>Build, Execution and Deployment>Build Tools>Gradle

## Software Design Pattern
MVVM (Model-View-ViewModel)+Clean Architecture(Use Case)

MVVM (Model-View-ViewModel) is an architectural pattern used in software development to separate user interface logic from the business logic of the application. In MVVM, the Model represents the data and business logic, the View represents the user interface, and the ViewModel serves as an intermediary that connects the View and the Model, handling user interactions, data binding, and presentation logic. This separation enhances code maintainability, testability, and scalability, making it easier to manage and modify different components of the application independently.

MVVM focuses on the separation of concerns within the application, Use Cases play a crucial role in defining the application's requirements and behaviors, which in turn influence the design and implementation of the ViewModel layer in MVVM

Why MVVM+Clean Architecture? Because it's very good pattern for developing app with bigger and wide functionalities and makes your code easily testable, you can check it on my Test Folder of the project on how i performed unit testing using mockwebservers with local json file. On top of that i also used Dagger Hilt that automatically generates related modules and components which is very at easy to pass values from Data Layer to Domain Layer up to the Presentation Layer.

## APK file 

 - [Google Drive Link for APK]([https://drive.google.com/file/d/13DXDzdXvt9vrBNlXst7iSpXXR6tuTNgX/view?usp=drive_link](https://drive.google.com/drive/folders/1AlP6bqwmnx8MeStT3la5Pkw5IrjODhAY)

