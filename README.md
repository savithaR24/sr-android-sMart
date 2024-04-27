# sr-android-sMart
Shop Mart App (SMart)

![smart](https://github.com/savithaR24/sr-android-sMart/assets/157411007/03600850-0fea-4b9f-b9bf-ecf81e230dcf)


Shop Mart App displays information about products available in the store. 
Smart is free and open source.
This App does not need authentication and is not hosted on Google Play Store.

## Features
This Android app lets you - 
- View Product Information and an Image of the Product.
- Displays further details like description of the Product, category and price for the product.
- Offline mode can be enabled or disabled.
- Can Favorite the product for later reference.
- No login needed.
- Works on both Portrait and landscape mode.
- Light and Dark mode supported.

-App Screenshots

![Screenshot_20240427_083838](https://github.com/savithaR24/sr-android-sMart/assets/157411007/4a4dc7a5-a9d2-4993-a2ac-04ec926f6e7b)

![Screenshot_20240427_083904](https://github.com/savithaR24/sr-android-sMart/assets/157411007/24f29ac0-1dc9-4096-b387-072b56982872)

![Screenshot_20240427_083924](https://github.com/savithaR24/sr-android-sMart/assets/157411007/0c2919ec-9bd9-4d34-a0c3-95fb81c7c70e)

Rubric Items Implemented -
- The app shows a static splash screen on launch. 
- The app has a custom launcher icon.
- All features in the app are complete.
- The app includes a list screen using LazyColumn. Each item in the list contains a title, category it belongs to, and an image of the item. This list includes lot of products where the user can scroll to find the needed one.
- Tapping an item in this list navigates to a detail view. This shows the same data in the list item with some further details such as a description of the product and the price (price is shown as derived from the API I.e without currency).
- The app includes network calls using a library Retrofit to download/upload data that relate to the core tasks of the app. The app’s repo does not contain API keys or other authentication information.
- The app handles all typical errors related to network calls — including server error response codes and no network connection — and keeps the user informed.  
- The app uses PreferenceDataStore to save user settings.
- The app uses Kotlin Coroutines and scoped launches appropriately to keep slow-running tasks off the main thread and to update the UI on the main thread.
- The app communicates to the user whenever data is missing or empty.
- All included screens work successfully without crashes or UI issues. 
- Views work for both landscape and portrait orientations, for the wide range of common Android devices.
- App works on the latest 5 versions of Android OS. 
- Views work for both light and dark modes.
- There are no obvious UI issues, like UI components overlapping or running off the screen.
- The code is organized and easily readable.Project source files are organized in packages such as ui.components, ui.screens, models, networking etc.
- Composables are be in their own files, each have their own Preview functions.
- The project uses MVVM architecture. The viewmodel preferably includes only one StateFlow object that returns a “stateful” representation of the associated data (loading, error, success) typically in the form of a sealed class. Networking code is in a service interface (following the Retrofit pattern).
- The app builds without Warnings or Errors.

- The app also includes About(short description of the app with the version number of the app) and Settings screens.
- Custom app name - SMart
- Includes compose animation - for Favoriting the product with a star which rotates and shows color on selection, plus tapping the product image in the detail view, shows bigger product image and on retap puts to back to original size.
