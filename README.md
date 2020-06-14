# School-panel-control-with-interface

It is a school web application based on Java EE technologies. 
* Not logged in to the user: <br>
    - has access only to the start page, which will display the last solutions added to the exercise

* Logged in user: <br>
  - has access to the list of groups to which users are assigned
  - has access to the exercises list
  - can add new solutions to prepare exercises, edit them and remove solutions that he previously added

* "Super admin", after authorization, has access to the admin panel, which allows him to:
  - access to user groups that he can manage, i.e. add (groups have a unique name), edit and delete groups to which there are no users assigned.<br>
  The "ADMIN group" is a special group that cannot be deleted or renamed. Membership in this group gives administrative privileges to users who belong to it, and "super" admin decides who it will be.
  - manage users who can add, edit, delete and assign to groups
  - like any logged in user, he can add solutions to exercise, but additionally has the privileges to edit and delete each of the solutions.
  
  
##Technologies:
* JSTL
* JSP
* MySQL

## Running the app
To run the application, insert in the context.xml file located in src/main/webapp/META-INF:

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

## Run the application
Tomcat 

## Project presentation
There is a codeschool.sql file prepared for loading in the directory src/main/webapp/META-INF/sql.
After loading the template into the database, we will be able to log in as:

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