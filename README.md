# Music player App in kotlin

## Description
The MusicPlayer app is a versatile and intuitive music player designed to elevate your listening experience. Developed entirely in Kotlin and integrated seamlessly with Firebase database technology, this app offers a modern approach to music playback and management. Whether you're an avid music enthusiast or someone who enjoys casual listening, MusicPlayer provides a feature-rich platform that caters to all your musical needs.

## App Features

### 1. Sign In and Sign Up with Firebase Authentication:
Firebase Authentication enables users to sign in or sign up for your app using their email address and password or other authentication methods (e.g., Google Sign-In, Facebook Login). Here's how you can integrate Firebase Authentication:
* Implement the Firebase Authentication SDK in your app.
* Create sign-in and sign-up screens where users can enter their credentials.
* Utilize Firebase Authentication APIs to handle user authentication securely.
* Upon successful authentication, users are granted access to the app's features, such as music playback and personalized content.

### 2. Splash Screen with Animation:
The splash screen provides a visually appealing introduction to the app and sets the tone for the user experience. You can implement animation effects such as fading in/out, scaling, or sliding to create an engaging splash screen. Here's how it works:
* When the app is launched, the splash screen is displayed for a brief period.
* During this time, the animation plays, captivating the user's attention.
* After the animation completes, the splash screen transitions smoothly to the home screen of the app.
  
### 3. Home Page:
The home page serves as the central hub of the MusicPlayer app, providing users with easy access to various sections and features. It typically includes elements such as:
* Recently played songs
* Recommended playlists or albums
* Quick search functionality
* Featured artists or albums

### 4. Categories of Music: 
This feature organizes the music library into different categories or genres, allowing users to explore and discover music based on their preferences. Categories may include genres like pop, rock, hip-hop, classical, etc., or curated playlists such as "Top Hits", "Chill Vibes", or "Workout Mix".

### 5. Promoted Google Ads: 
Integrating Google Ads into the app allows you to monetize your platform by displaying targeted advertisements to users. These ads can be strategically placed within the app interface, such as banner ads at the bottom of the screen or interstitial ads between song transitions. Promoted ads help generate revenue while providing relevant content to users.

### 6. Animation:
Animation enhances the visual appeal and interactivity of the app, creating a more engaging user experience. Examples of animations in a MusicPlayer app may include:
* Smooth transitions between screens or menu items
* Animated effects when playing or pausing music
* Visual feedback for user interactions (e.g., tapping on buttons, scrolling through playlists)

### 7. Favorite Music:
This feature allows users to mark songs, albums, or playlists as favorites, making it easier to access and revisit their preferred content. Users can create a personalized collection of favorite music that reflects their tastes and preferences, enhancing user engagement and satisfaction.

### 8. User Profile:
The user profile section provides users with a personalized space where they can access all the screens from the user profile, view their listening history, and customize their preferences. Key elements of the user profile may include:
* Profile picture with the first letter of the user email ID and username
* Account settings (e.g., email, password)

### 9. Artist Playlist: 
This feature showcases playlists curated by specific artists or bands, allowing users to explore their favorite artist's music selections or discover new tracks recommended by artists themselves. Artist playlists offer a unique insight into an artist's musical influences and preferences, fostering a deeper connection between fans and artists.

### 10. Mostly Played Music:  
MusicPlayer tracks and analyzes users' listening habits to identify the most frequently played songs. This feature provides users with insights into their listening preferences and helps them rediscover their favorite tracks or explore similar content. I set the count variable in the Firebase database. so if any user plays the song count will be updated for that song and songs will be displayed according to which song has the highest count.

### 11. UI Design: 
The user interface (UI) design plays a crucial role in shaping the overall look and feel of the MusicPlayer app. A well-designed UI enhances usability, aesthetics, and user satisfaction. Key aspects of UI design in a MusicPlayer app may include:
* Intuitive navigation and layout
* Consistent visual style and branding
* Clear hierarchy of information
* Support for different screen sizes and resolutions
* Accessibility features for users with disabilities

### 12. Logout:
The logout process starts when the user initiates the logout action through a designated UI element, such as a "Logout" button or option in the app's settings menu. This interaction signals to the app that the user wants to sign out of their account.

## Technologies Used:

* Kotlin
* Firebase database
* Exoplayer
* Glide
* UI/UX Design
* Google AdMob
* Dependency Injection Framework

## Firebase Configuration Guide:

### Setting up Firebase
To set up Firebase for the project, follow these steps:

* Create a Firebase project in the Firebase Console.
* Configure Firebase Authentication, Realtime Database, and Storage in the Firebase Console.
* Download the google-services.json file for Android or GoogleService-Info.plist file for iOS from the Firebase Console.
* Add the configuration file to your project's directory as instructed by Firebase.

## Firebase Integration
Firebase is integrated into the app for authentication, real-time database, and storage functionalities. Here's an overview of how Firebase is used:

### Authentication:
Firebase Authentication is used to manage user authentication securely. Users can sign up, log in, and securely access their personalized music libraries.

### Realtime Database:
Firebase Realtime Database stores music metadata, user preferences, and playlist information. Real-time synchronization ensures data consistency across users' devices.

### Storage:
Firebase Storage securely stores audio files, album artwork, and multimedia assets associated with music tracks. It provides reliable and high-quality audio streaming and playback.
Third-Party Libraries/Frameworks

### The MusicPlayer app utilizes the following third-party libraries and frameworks:
ExoPlayer: ExoPlayer is used for audio playback, providing advanced features and customization options to enhance the user experience.

## App Visuals
<img width="150" alt="SplashScreen" src="https://github.com/kathan5550/MusicPlayer_App/assets/105222761/deb564f5-ea01-4136-b8b8-c66693303f3f">
<img width="150" alt="Firstpage" src="https://github.com/kathan5550/MusicPlayer_App/assets/105222761/49828079-f09c-4c04-ba54-1eebba53d024">
<img width="150" alt="Signin" src="https://github.com/kathan5550/MusicPlayer_App/assets/105222761/cac54e79-fc30-4390-bebf-744452242b5b">
<img width="150" alt="Signup" src="https://github.com/kathan5550/MusicPlayer_App/assets/105222761/aa3d6c40-8e67-4ab8-be0f-dfb7efa6ce25">
<img width="150" alt="UserProfile" src="https://github.com/kathan5550/MusicPlayer_App/assets/105222761/a2a29aab-4d9f-4cdb-bf15-cb95df10392b">
<img width="150" alt="HomeScreen1" src="https://github.com/kathan5550/MusicPlayer_App/assets/105222761/b7d25b6b-6eb7-45da-9101-d3a992a8f354">
<img width="150" alt="HomeScreen2" src="https://github.com/kathan5550/MusicPlayer_App/assets/105222761/a5862852-b745-44b9-a001-972b1ec5bf96">
<img width="150" alt="PlyerScreen" src="https://github.com/kathan5550/MusicPlayer_App/assets/105222761/e2f68620-f87c-4abf-a288-30dbf3271fc1">
<img width="150" alt="Artistscreen" src="https://github.com/kathan5550/MusicPlayer_App/assets/105222761/065b5f0a-2356-483c-a4b5-2904e37f6fab">
<img width="150" alt="ArtistInfo" src="https://github.com/kathan5550/MusicPlayer_App/assets/105222761/1c15093f-9ed1-4bbf-8117-66b706207f1c">
<img width="150" alt="Screenshot 2024-04-28 at 11 52 09 AM" src="https://github.com/kathan5550/MusicPlayer_App/assets/105222761/b874f17f-ebfe-4750-92f5-8cd772ff9cbc">
<img width="150" alt="MadeForYou" src="https://github.com/kathan5550/MusicPlayer_App/assets/105222761/b6dea350-5704-450c-bb5a-db0f0e5ff174">
<img width="150" alt="MusicCategories" src="https://github.com/kathan5550/MusicPlayer_App/assets/105222761/1ea95264-b279-4916-96ee-39c1e3487440">
<img width="150" alt="Mostlypaid" src="https://github.com/kathan5550/MusicPlayer_App/assets/105222761/b000d611-f9a2-4d90-8bd6-968831cdd93e">
<img width="150" alt="AllTimeHit" src="https://github.com/kathan5550/MusicPlayer_App/assets/105222761/b9af09ab-49be-4aff-bae1-26863bdc595d">
<img width="150" alt="HotTracks" src="https://github.com/kathan5550/MusicPlayer_App/assets/105222761/e912768f-daf1-4b41-80ba-5b6c8ee7acda">
<img width="150" alt="FavScreen" src="https://github.com/kathan5550/MusicPlayer_App/assets/105222761/c65ff8c0-3677-4f36-8044-28ec1eccb906">
<img width="150" alt="Screenshot 2024-04-28 at 12 00 32 PM" src="https://github.com/kathan5550/MusicPlayer_App/assets/105222761/5f2b8f7c-1ce1-4511-bb65-0d206232738f">

## License
This project is licensed under the MIT License.

## Contact
Name: Kathan Patel
Email: kathanpatel63548@gmail.com
LinkedIn Profile: https://www.linkedin.com/in/kathan-patel-b07446237/

