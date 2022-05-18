# happy_dog
Demo video here ->
https://youtube.com/shorts/7FGVCLSC14A

## Architecture
- MVVM

## Dependencies
- Dagger-Hilt
- Retrofit
- ViewBinding
- Glide
- Coroutines

## Description
###### First Screen
The breeds list returned from the API are presented in an expandable list. Breeds with sub-breeds allows the user to expand the section (arrow tap) to see all sub-breeds.
When user clicks on a breed name, a second fragment is called in order to show the pictures of this breed.

<img src="https://user-images.githubusercontent.com/18723547/169014585-5574d510-b881-4765-bdd8-0cb00bf4d6a7.png" width=40% height=40%>
<img src="https://user-images.githubusercontent.com/18723547/169014588-e0e14c0e-769e-4c1d-be05-834200eaa3e4.png" width=40% height=40%>

###### Second Screen
A list of 10 random pictures are presented in the screen (some breeds have less of 10 pictures available, in this cases I show only the number available)

<img src="https://user-images.githubusercontent.com/18723547/169014565-68784f04-f56e-453e-b05a-442fd2aa94ca.png" width=40% height=40%>



