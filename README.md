# Random Weather

Small app to present the weather of a random location. The app is consuming the [OpenWeather API]() to get the weather of a random location by passing valid coordinates. Valid latitudes are between -90 and 90 degrees and valid longitudes are between -180 and 180. The app assumes 4 decimals as the API suggests. The app has been built using Clean Architecture divided by modules, Repository pattern to access the data and MVVM as the design pattern in the presentation. Some other Architecture Components has been presented.

The app is compatible since API 21, that means over 94.1 devices.

## Architecture
Clean Architecture divided into three layers, each one in a diferent module

- Presentation (app module)
- Data (Android module)
- Domain (Pure Kotlin module)

Dependency Injection is managed with Koin library.
 
## Libraries

Libraries used in the whole application are:

- [Jetpack](https://developer.android.com/jetpack)🚀
  - [Viewmodel](https://developer.android.com/topic/libraries/architecture/viewmodel)
  - [View Binding](https://developer.android.com/topic/libraries/view-binding)
  - [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started)
- [Retrofit](https://square.github.io/retrofit/)
- [Moshi](https://github.com/square/moshi)
- [okhttp-logging-interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md)
- [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- [Koin](https://github.com/InsertKoinIO/koin)
- [Coil](https://github.com/coil-kt/coil)
- [Truth](https://truth.dev/)
- [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver)
