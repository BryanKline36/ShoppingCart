# HEB Shopping Cart

### Spring Boot Application (Maven, Java 17)
Pull down project from Github, open in Intellij or other IDE, load Maven dependencies, and build and run as with any Spring Boot project.

### Instructions

One the project is up and running, endpoints are served locally on: http://localhost:8080

The four different features can be accessed through the following endpoints, either in the browser or with a tool such as Postman: 

#### Feature One

http://localhost:8080/api/shopping-cart/feature-one

#### Feature Two

http://localhost:8080/api/shopping-cart/feature-two

#### Feature Three

http://localhost:8080/api/shopping-cart/feature-three

#### Feature Four

http://localhost:8080/api/shopping-cart/feature-four

Additionally, Swagger UI is built in to the project and so all endpoints and data models can be accessed via:

http://localhost:8080/swagger-ui/


### Project Structure

The project makes use of an H2 database which is meant to simulate an actual database, upon start up of the project json files which hold data for the cart and coupons are read and loaded into the H2 database.

JPA repositories act as the means of accessing the data after it's loaded into the H2 database, these are leveraged via services (CartService, CouponService, ConstantService) to serve data to the controller (CartController) which exposes the various feautre endpoints.



