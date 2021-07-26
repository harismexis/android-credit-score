### Description:

The app has one screen showing user's credit score in a Donut View. The app cashes
the credit score, so if internet is not available it will retrieve the cached values.

### Technologies:

Kotlin, Clean Architecture / MVVM, JetPack, LiveData, ViewModel, Coroutines, Retrofit, Dagger,
Room, Mockito, MockK, Espresso

### Tests:

The project contains Unit & Instrumented Tests. Some Files with mock data have been used.

### Notes :

Using Room just for storing 2 values is a bit of overkill, I could have used SharedPreferences or 
DataStore. 

&nbsp;

![Alt text](screenshots/app_screenshot.png?raw=true "app screenshot")