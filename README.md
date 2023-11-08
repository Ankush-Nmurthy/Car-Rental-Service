# CAR RENTAL SERVICES
<img src="https://i.ibb.co/1sJSkb7/Car-Rental-Services-2.jpg" alt="Car-Rental-Services-2" width="320px" height="300px">

## Introduction
The CAR-RENTAL-SERVICES is a console-based project that simplifies car rental operations for administrators and customers. Administrators can manage cars and generate reports, while customers can register, browse cars, and book rentals. Overall, it enhances efficiency and convenience in the car rental process.

## Key Features:

- ### Customers operations:
   - **Register customer account:** Customers can create their accounts to access the car rental service and enjoy personalized features.
   - **View all available cars:** Customers can browse through a list of cars that are available for rent.
   - **Search cars:** Customers can search for specific cars based on their preferences, such as model, brand, or price range.
   - **Book cars:** Customers can reserve a car for a specific date and time, ensuring its availability for their desired rental period.
   - **Delete booking:** Customers have the option to cancel a booking if their plans change or if they no longer require the rental.
   - **View all bookings and transactions:** Customers can view a comprehensive list of their past and current bookings, as well as their transaction history.
   - **Delete account:** Customers can choose to delete their account if they no longer wish to use the car rental service.

- ### Admin operations:

   - **Add cars:** Administrators have the ability to add new cars to the system, including their details such as model, brand, and availability.
   - **View cars:** Administrators can access a list of all cars in the system, allowing them to review and update the car information as needed.
   - **Delete cars:** Administrators can remove cars from the system if they are no longer available for rent or if they need to be removed for other reasons.
   - **Generate reports:** Administrators can generate detailed reports that provide insights into car-related activities, such as bookings, rentals, and revenue.
   - **View customer details:** Administrators can access customer information, including registration details and transaction history.

## FOLDER STRUCTURE
```
project-root
│
├── src
│   └── main
│       ├── java
│       │   └── com
│       │       └── masai
│       │           ├── dao
│       │           ├── entity
│       │           ├── exception
│       │           ├── service
│       │           ├── test (This class is simple to test the code)
│       │           └── ui
│       └── resources
│           └── META-INF
│               └── persistence
└── pom.xml
```


## Tech-Stacks:
    - Backend: Java, Hibernate etc 
    - Database: MYSQL.
    - Build tool: Maven.

## Database
<img src="https://i.ibb.co/FBVPhpz/Untitled-2.png" alt="Untitled-2" border="0">
<br>

## Project Configuration

The project uses the following configuration:

```properties
<property name="jakarta.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver" />
<property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/**[your database name]**" /> 
<property name="jakarta.persistence.jdbc.user" value="**[Inside here enter your SQL username]**" /> 
<property name="jakarta.persistence.jdbc.password" value="**[Inside here enter your SQL password]**" />

<!-- this will show the queries on console -->
<property name="hibernate.show_sql" value="true" />

<!-- <property name="hibernate.show_sql" value="true"/> -->
<property name="hibernate.hbm2ddl.auto" value="update" />
```
## Setup

To run the application, follow these steps:

1. Clone the remote `Repository` to the local system.
2. Ensure you have Java and MySQL installed on your system.
3. Import the project to the editor.
4. Run the project and use the application on editor console.

## Conclusion:
CAR RENTAL SERVICES provides convenience for both administrators and customers, allowing administrators to manage cars, generate reports, and view customer details, while customers can register, browse, book, and manage their rentals. This project aims to enhance the overall car rental experience and optimize the management of car-related activities.
    
