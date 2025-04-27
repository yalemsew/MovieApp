MovieMail - Online DVD Rental System

Project Overview

MovieMail is an online DVD rental system built with Spring Boot, MySQL, and Docker.
It allows users to register, log in, subscribe to DVD rental plans, browse available DVDs, and manage personal watchlists.
The project demonstrates authentication, role-based access control, relational database design, and containerization.
It can be deployed easily to cloud services like AWS EC2 and AWS RDS.

Features:
User Registration and Authentication (JWT-based)

Subscription Management (rental plans)

DVD Browsing (list and detail view)

Watchlist Management (prioritized DVDs)

Admin and User Roles (role-based authorization)

RESTful APIs built with Spring Boot

Database interaction using Spring Data JPA

Containerization with Docker and Docker Compose

Technologies Used
Java 17

Spring Boot 3.2.5

Spring Security (JWT Authentication)

Spring Data JPA

MySQL / AWS RDS

Docker and Docker Compose

JUnit 5, Mockito

AWS EC2 and RDS

Entity Relationships
Customer

One-to-Many with Subscription

Many-to-Many with DVD through WatchListEntry

Subscription

Many-to-One with Customer

DVD

Many-to-Many with Customer via WatchListEntry

WatchListEntry

Many-to-One with Customer

Many-to-One with DVD

Simple Relationship Diagram:
Customer (1) -> (Many) Subscription
Customer (Many) <-> (Many) DVD (via WatchListEntry)

Deployment Instructions

1. Build the application
   mvn clean install
2. Create Docker Image
   docker build -t moviemail-app .
3. Run with Docker Compose
   docker-compose up -d

API Endpoints

Authentication

POST /api/v1/auth/register - Register a new user

POST /api/v1/auth/login - Authenticate user and receive JWT token

Customer

POST /api/v1/customer - Create customer profile

GET /api/v1/customer/profile - Retrieve customer profile

Subscription

POST /api/v1/subscriptions - Subscribe to a rental plan

DVD

GET /api/v1/dvds - List all DVDs

GET /api/v1/dvds/{id} - Get DVD details

Watchlist

POST /api/v1/watchlist - Add a DVD to watchlist

DELETE /api/v1/watchlist/{dvdId} - Remove a DVD from watchlist

Author
Yalemsew Mekuriaw
yalemone@gmail.com
