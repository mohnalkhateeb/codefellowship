# Lab 16 : codefellowship

### How to Run
* Open terminal and cd into the codefellowship directory
* Enter: `./gradlew bootRun`
* Open web browser to: `localhost:8080`

### Routs 
* `/` : home page
* `/sinup` : signUp page 
* `/signIn` : singing page 
* `/users` : show all users
* `/users/{id}` : show spsific user with id 
* `/posts` : allow user to add posts 
* `/profile` : show info to login user 

### Controller 
* ##### ApplicationUserController
    * `@PostMapping("/users") public RedirectView createUser(String username, String password, String firstName, String lastName,String dateOfBirth, String bio)`
    * `@GetMapping("login") public String getLoginPage()`
    * `@GetMapping("/profile") public String getMyProfile(Principal p, Model m)`
    * `@GetMapping("/users") public String getAllUsers(Principal p, Model m)`
    * `@GetMapping("/users/{id}") public String getOneUser(@PathVariable long id, Principal p, Model m)`

* ##### MainController`
    * `@GetMapping("/") public String getIndex(Model m)`
    * `@GetMapping("/loginpage") public String getLogin()`
    * `@GetMapping("/signup") public String getSignUp()`
  
* ##### PostController
    * `@GetMapping("/post") public String getPost(Principal p, Model m)`
    * `@PostMapping("/post") public RedirectView createPost(String body, Principal p)`
### configration 
  * ##### UserDetailsServiceImpl
  * [UserDetailsServiceImpl](src/main/java/com/lab16/codefellowship/config/UserDetailsServiceImpl.java)
  * ##### WebSecurityConfig()
  * [WebSecurityConfig()](src/main/java/com/lab16/codefellowship/config/WebSecurityConfig.java)
### Models
  * ##### AppllicationUser:
    contains  String username, String password ( hashed using BCrypt), String firstName, String lastName, Date dateOfBirth,String bio.
    and List<Post> posts;
  * ##### Post
    contains String body, String createdAt;
### RelationShip 
  * ###### One Application has many posts
### Repositories 
  * ##### AppllicationUserRepository 
  to find user by username 
  * ##### postRepository
    to find pots by applicationUser 

  