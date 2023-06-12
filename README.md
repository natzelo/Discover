# Discover
Android application consuming the Google Maps API

# Build Instructions

- Clone the repository to your local machine
- Import the project to Android Studio
- In the `local.properties` file add the Google Maps API key (make sure that the API is enabled ) like below

` MAPS_API_KEY=<YOUR API KEY HERE> `

- Build the project and run it from Android Studio

# Features and Details 

- Project is buit on the Modern MVVM Architecture based on Android Architecture Components
- Clean, reusable and maintainable codebase
- Has the ability to search a place and the map animates to that location 

![picture alt](https://i.imgur.com/kdCvyc6.png "Screenshot from the App")

- Marker is used to show the location
- Uses the Google GeoCoding SDK to fetch the coordinates from text
- Uses the RESTful API of Google Places to fetch the nearby locations of a coordinate
- RESTful calls are made via Retrofit Library
- The nearby locations are being logged when the button is clicked
