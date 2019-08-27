# reddiu

*Backend*: Java Spring Boot, Hibernate, Spring Data JPA, MySql for a database

*Frontend*: Angular 7, Bootstrap

## What's reddiu?

It's more like Reddit's long lost third cousin twice removed than it is a Reddit clone.

The main reason for reddiu's existence is my expanding a project (more specifically, a bulletin board project) to include the most I can from the materials I learnt from. 

So, on the backend side there are one to many and many to many relations, searches that return lists of DTOs and present them in a page DTO (sadly, I did not use a Page or PagedListHolder to handle DTOs in this particular project - look to [Korzo](https://github.com/AlexStibbons/Korzo) for that), CRUD functionalities for every post and every comment, along with a way of up and downvoting. 

On the frontend side, my main concern is that everything works rather than it looks pretty (at least for now).

##N. B. 

There are still *some pretty important* details I need to add to this project. For example:

Access annotations are missing on the backend side. Access needs to be more explicitly limited on the frontend side. Edge cases are rarely included in either side. A search by title and category function needs to be added (similar to the findByGenreAndTitle function in the [Korzo controller](https://github.com/AlexStibbons/Korzo/blob/master/Korzo/server/KorzoRest/src/main/java/korzoApp/web/controller/FilmController.java)).

## What can you do on reddiu?

If you are not logged in, you can:

* take a look at the list of posts (messages)
* register

If you are logged in, you can:

* read all posts/messages
* create post/message
* edit only **your own** post/message
* delete only **your own** post/message
* upvote and downvote posts/messages and comments
* comment on a message or another comment
* edit only **your own** comment
* delete only **your own** comment
* take a look at a user's profile

### This doesn't seems like reddit at all...

That's right! This started off as a bulletin board. That's why posts are called messages, and there are categories instead of subreddits, and also why access is so limited.

It's a thingamajig that's somewhat vaguely reminiscent of reddit and has taught me quite a bit.

### Where are all the subreddits?

Since this project grew our of a bulleting board projects, the subject of subreddits is a bit fraught.

The equivalent of a subreddit in this particular project would be a category. 

