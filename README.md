### Description:

The app has one screen showing user's credit score in a Donut View. The app cashes
the credit score, so if internet is not available it will retrieve the cached value.
The user can swipe to refresh to get updated results.

### Technologies:

Clean Architecture + MVVM, Kotlin, Coroutines, LiveData, ViewModel, Retrofit, Dagger, 
Room, MockK, Espresso

### Tests:

The project contains Unit & Instrumented Tests. Some Files with mock data have been used.

### Notes:

The app stores very few data, so usage of Datastore or SharedPreferences would probably make  
more sense instead of using Room. I chose Room in the sense that an actual project would need 
to store & show more data of the credit reports, keep a history, etc.

&nbsp;

![Alt text](screenshots/img.png?raw=true "app screenshot")