# Cinema-project
# Table of Contents
* [Project purpose](#purpose)
* [Project structure](#structure)
* [For developer](#developer-start)
* [Author](#author)

# <a name="purpose"></a>Project purpose
Creation of cinema booking system with basic operations required for it.
<hr>
Without being authenticated you can register and login. There are specific urls for admins and users.
Only users can complete order and add tickets to shopping cart.
As admin you can adding new movies, movie sessions and cinema halls.
<hr>

# <a name="structure"></a>Project Structure
* Java 11
* Maven 3.6.0
* maven-checkstyle-plugin 3.1.1
* maven-war-plugin 3.2.3
* javax.servlet-api 3.1.0
* mysql-connector-java 8.0.17
* hibernate-validator 6.1.5.Final
* hibernate-core, hibernate-java8 5.4.5.Final
* spring-context, spring-orm, spring-webmvc, 
spring-security-core, spring-security-config, spring-security-web 5.2.0.RELEASE
* log4j 1.2.17
<hr>

# <a name="developer-start"></a>For developer

1. Open the project in your IDE.

2. Add Java SDK 11 or above in Project Structure.

3. Configure Tomcat:
add the artifact cinema-project:war exploded;
add as URL http://localhost:8080/

4. Install MySQL if you don't have it and start MySQLWorkbench.

5. At src.main.resources.db.properties use your username 
and password for your MySQLWorkbench to create a Connection.

6. Run this line to create a schema: CREATE SCHEMA `cinema` DEFAULT CHARACTER SET utf8.

7. Change a path to log file in src.main.resources.log4j.properties.

8. Run the project.

9. For testing this API you can download Postman or another such an analogue.

10. For authorization, you must add a new header, where Authorization is key and Basic email:password is value, where email:password must be encoded to Base64 format.

There are test data that you can use.
There’s one user already registered with ADMIN role (email = "admin@ukr.net"", password = "1234") and
one user with USER role (email = "user@ukr.net"", password = "1234"). You can change these test data in InjectDataController if you want.

# <a name="author"></a>Author

Mykyta Arkhanhelskyi: https://github.com/Nick97-git


