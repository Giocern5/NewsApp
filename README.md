Requirements:
    1. Technical Requirements:
        Use Kotlin and follow the MVVM architecture.
        Implement Jetpack Compose for the UI.
        Use Coroutines to handle asynchronous API calls.
        Implement Compose Navigation to switch between screens.
        Use ViewModel for state management.
    2. App Features:
        Main Screen
            Fetch and display the latest news headlines using the NewsAPI.
            Include a search functionality to filter articles based on keywords.
            Implement a manual refresh option to reload data.
            Display the full article title, source, and publication date when the headline is clicked
        Detail Screen
            Create a link out to the provided article from the API
    3. Data Handling:
            Retrieve and display paginated data from the NewsAPI.
            Handle errors gracefully (e.g., invalid API key, no network).
    4. User Analytics
        Track basic interactions using Firebase Analytics.


Built Using
    Retrofit: Used to easily and safely make HTTP requests to RESTFUL Apis
    Hilt: Used for dependency injection to help modularize the code for testability and readability
    Coroutines: Used for its asynchronous progamming
    Jetpack Compose: Used per project requirements
    Jetpack Navigation: Used to simplify navigation
    Coil: Helps with image caching and loading
    Pager 3: Used for Pagination
    Firebase Analytics: Used to track basic user interactions
    Misc: MVVM, Flows, Firebase Analytics, 

Summary
    Due to personal time constraints, I was able to get an MVP going as required. Given more time 
    I would handle errors better by creating a custom loggin class. I was able to add animations
    on screen navigation. For favorites, i would have used Room to store the favorite accounts. I 
    would of added a favorites tab to for the user to view. With more time, i would of also added modules
    instead of just new folders to fully modularize the code and import the sections as needed.I would 
    have done more analytics for crashes and such.

