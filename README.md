# Nextale

## Presentation

You have been hired as the new and only backend developer in charge of building a Java web application that let user browse and discover new books. The CTO has build for you the skeleton of this application and have includes the based models you will have to work with, and have assigned you some feature requests for your first days in the company.

## Project setup

This project require Java 18 or newer and NodeJS 6.x or newer (`npm`) to be installed on your machine.

The data will be stored using `h2` in memory so you will not have to install any external depencies

We have provided a bunch of fake data to fill the database, it will be pushed in it at each restart. Cover are dynamilly generated, so they will change each time they appear.

After cloning the project, please install NPM packages (`npm install`)  then you can use the `./mvnw clean exec:exec spring-boot:run` to install libraries, build the project and assets and run it. The `exec:exec` will build CSS data using TailwindCSS.

The project offer a UI available on `http://localhost:8080` as well as an API `http://localhost:8080/api`. It's not required that you do the exercice in both "side" and are just here for convienience if you prefer to separate frontend from backend (using `VueJS` for exemple).

## Work expected

Each of the following tasks could be done independently from the others, so you can choose to do them in the order you prefer. We just ask you to create a Git commit for each feature, and once completed to open a merge request in the Gitlab before merging anything into the `main` branch.

We expect that you include in your work automated tests that cover at least every model public methods. If you feel it you can also include some system tests to validate the integration of the different part of your feature.

We ask you to not add any additional lib to the project to achieve the feature requested, at the exceptions of lib that help you during you developments and tests (like adding a debugger, or test helpers). If you want to, we will appreciate that you indicate in your merge request description some libraries suggestion you may have used before to accomplish the requested job.

What will be evaluated with this test are the following points
- Readiness of the code
- Application architecture
- Unit testing redaction
- Git history
- Merge request description


## Feature requests

### 1 - Display book authors’ name

Under each book’s card, we would like to display it’s author’s name. The name should follow the specifications
- it should start with the author’s first name initial uppercased, followed by a dot `.`
- it should then include the author full last name uppercased too

For example if the author’s first name is `stephen` and its last name is `King` the name displayed should be `S. KING`

*Bonus:* Some authors could shared the same last name, and first name initial so to help users to differentiate them, we would like you to find a way to display the full name capitalized when hovering the author name with your mouse

### 2 - Adding some paginations

When browsing the list of books we are currently displaying all of them at once, which is pretty inefficient both for users and for application performance

We would like you to implement a pagination system that will limit the number of books displayed at once to 25. Users will have to click on the `Previous` and `Next` button on the page to display a different set of books.

Once done we would like you to re-use that pagination system on the authors index page, and in the book show page to paginate the list of comments displayed for a book.

*Bonus:* Some users have differents needs and so we would like to propose them to choose the numbers of books/authors/comments displayed at will between the following values: 25, 50, 100, all

### 3 - Posting comments on a book

For the moment we only display comments on a book we have fetched from an external system, but we would like to let our users post comments on the book they like or dislike. So we want you to implement a page to add a new comment for a book

To be valid a comment should include at least
- a `pseudonyme`: the name of the user
- a `content`: what they want to tell about this book

If one of these field is missing we should display an error to inform the user of that fact and let them fix it.

*Bonus:* Letting users posting comment is not enough for us to know if they like a book or not, so we want to implement a rating system (from 1 to 5) that they could fill when posting a comment. And then we want to sort the comments for a book using to display the best rating first.

### 4 - Suggesting book to read

We want to add suggestion to the book show page to let users discover more books that could interest them to read.

So we want to display a list of 5 books written by the same author, excepted the one currently displayed on screen. If that author has less than 5 other books written complete the list with the books that have the highest `boost_score`.

*Bonus:* As we care a lot of web application performance, we ask you to build that list of suggested books using only database request.
