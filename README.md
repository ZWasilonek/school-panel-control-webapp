# School-panel-control-with-interface

### See the application on HEROKU
https://school-panel-control-webapp.herokuapp.com/

![alt text](https://user-images.githubusercontent.com/59224048/85195743-5ad66680-b2d5-11ea-90d6-51881252fd66.png)

It is a school web application based on Java EE technologies. 
* Not logged in to the user: <br>
    - has access only to the start page, which will display the last solutions added to the exercise

* Logged in user: <br>
  - has access to the list of groups to which users are assigned
  - has access to the exercises list
  - can add new solutions to prepare exercises, edit them and remove solutions that he previously added

* "Super" admin, after authorization, has access to the admin panel, which allows him to:
  - access to user groups that he can manage, i.e. add (groups have a unique name), edit and delete groups to which there are no users assigned.<br>
  The "ADMIN group" is a special group that cannot be deleted or renamed. Membership in this group gives administrative privileges to users who belong to it, and "super" admin decides who it will be.
  - manage users who can add, edit, delete and assign to groups
  - like any logged in user, he can add solutions to exercise, but additionally has the privileges to edit and delete each of the solutions.
  
  
## Technologies:
* Javax Servlet
* JSTL
* JSP
* MySQL
* HTML
* CSS 


## Running the application locally
To run the application, insert in the context.xml file located in the directory src/main/webapp/META-INF:

database connection details
```
Provide your login to connect to the local database:
username=
```

```
Provide your password to connect to the local database:
password=
```

```
If you use a different port then change it. I use the default 3306 for MySQL:
url="jdbc:mysql://localhost:3306/codeschool"
```

There is a codeschool.sql file prepared for loading in the directory src/main/webapp/META-INF/sql <br>
After loading the template into the database, we will be able to log in as: -> see "Project presentation" section

Add server


## Project presentation
You already have users at your disposal in the database:

* ordinary user without additional privileges:
```
email:  (you have the choice) 
        marcin.cieszkowski@gmail.com
        jan.kowalski@wp.pl
        anna.nowak@o2.pl

password: (the same for everyone) password
```

* "super" admin with all privileges:
```
email: admin@gmail.com
password: adminpass
```


## Contact 
zofiawasilonek@.gmail.com<br>
<a href="https://www.linkedin.com/in/zofia-wasilonek/">Linkedin</a>