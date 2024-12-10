<h1>Summary</h1>
<p>
Despite personal time constraints, I successfully completed all required sections. Given more time, I would have improved error handling by creating a custom logging class. I also incorporated animations for screen navigation. For the favorites feature, I would have used Room to store favorite accounts and added a dedicated favorites tab for user access. Additionally, I would have modularized the code further by creating separate modules instead of just organizing it into folders, allowing for more efficient code management. Lastly, I would have enhanced analytics to track crashes and other key metrics.
</p>
<h1>Technical Requirements:</h1>

<li>Use Kotlin and follow the MVVM architecture.</li>
<li>Implement Jetpack Compose for the UI.</li>
<li>Use Coroutines to handle asynchronous API calls.</li>
<li>Implement Compose Navigation to switch between screens.</li>
<li>Use ViewModel for state management.</li>
<li>App Features:</li>
<ul>
    <h3>Main Screen</h3>
    <ul>
        <li>Fetch and display the latest news headlines using the NewsAPI.</li>
        <li>Include a search functionality to filter articles based on keywords.</li
        <li>Implement a manual refresh option to reload data.</li>
        <li>Display the full article title, source, and publication date when the headline is clicked.</li>
    </ul>
       
<h3>Detail Screen</h3>
    <ul>  
        <li>Create a link out to the provided article from the API.</li>
    </ul>
<ul>
    <li>Data Handling:</li>
    <ul>
        <li>Retrieve and display paginated data from the NewsAPI.</li>
        <li>Handle errors gracefully (e.g., invalid API key, no network).</li>
    </ul>
</ul>
    <h3>User Analytics:</h3>
    <ul>
        <li>Track basic interactions using Firebase Analytics.</li>
    </ul>
    </ul>
</ul>


<h1>Built Using</h1>
<li>Retrofit: Used to easily and safely make HTTP requests to RESTFUL Apis </li>
<li>Hilt: Used for dependency injection to help modularize the code for testability and readability</li>
<li>Coroutines: Useed for its ansynchronous porgamming</li>
<li>Jetpack Compose: Used per project requirements</li>
<li>Jetpack Navigation: Used to simplify navigation</li>
<li>Pager 3: Used for Pagination</li>
<li>Coil: Helps with image caching and loading</li>
<li>Firebase Analytics: Used to track basic user interactions</li>
<li>Misc: MVVM, Flows, Firebase Analytics, </li>    

<h1>Usage: </h1>
Users can search for news or it will load the latest headlines on refresh / intial load

<h1>Sample Gifs:</h1>
<ol>
  <li> First gif is on initial app load and article selected </li>
  <li> Middle gif is searching for an article using a key word </li>
  <li> Last gif is loading url for the article </li>
</ol>
<p float="left">
     <img src="https://github.com/user-attachments/assets/baaa46b1-14fa-4d72-b21f-c29c513ce578" width="300" height="600" />
    <img src="https://github.com/user-attachments/assets/b96b18b1-cbc4-4182-aa72-d23a3357fabc" width="300" height="600" />
    <img src="https://github.com/user-attachments/assets/85db49fb-04b7-4412-8c01-b2f9aac42054" width="300" height="600" /> 
</p>


<h1>Firebase Console</h1>
<img src="https://github.com/user-attachments/assets/9d464e5a-6409-48fb-82d0-5f9faa87bba2" width="600" height="600"/>


