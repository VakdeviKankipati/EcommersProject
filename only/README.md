# Microservices E-Commerce Platform

This project is a complete microservices-based e-commerce platform built with Spring Boot and Spring Cloud. It consists of multiple services that work together to provide a scalable and distributed system.

## Architecture Overview

The system follows a microservices architecture pattern with the following components:

- **Service Discovery (Eureka Server)** - Service registry for microservices
- **API Gateway** - Single entry point for all client requests
- **User Service** - Handles user authentication and management
- **Product Service** - Manages product catalog and inventory
- **Order Service** - Processes and manages orders
- **Payment Service** - Handles payment processing (Stripe & Razorpay)
- **Email Service** - Manages email notifications via Kafka

## Services Details

### 1. Service Discovery (Port: 8761)
- **Technology**: Spring Cloud Netflix Eureka Server
- **Purpose**: Service registry and discovery
- **Location**: `/ServiceDiscovery`
- **Main Class**: `ServiceDiscoveryApplication.java`

### 2. API Gateway (Port: 8080)
- **Technology**: Spring Cloud Gateway
- **Purpose**: Routes requests to appropriate microservices
- **Location**: `/ApiGateway`
- **Main Class**: `ApiGatewayApplication.java`
- **Routes**:
  - `/products/**` → Product Service
  - `/users/**` → User Service
  - `/orders/**` → Order Service
  - `/pay/**` → Payment Service

### 3. User Service (Port: 8082)
- **Technology**: Spring Boot + Spring Security + JWT
- **Purpose**: User authentication, registration, and management
- **Location**: `/userrServices`
- **Database**: MySQL (`user` database)
- **Main Class**: `UserrServicesApplication.java`
- **Features**: JWT-based authentication, user management

### 4. Product Service (Port: 8081)
- **Technology**: Spring Boot + JPA + Redis + Flyway
- **Purpose**: Product catalog management
- **Location**: `/productservicesfeb29`
- **Database**: MySQL (`productservice8mar` database)
- **Main Class**: `Productservicesfeb29Application.java`
- **Features**: Product CRUD, caching with Redis, database migrations

### 5. Order Service (Port: 8083)
- **Technology**: Spring Boot + JPA + Hibernate
- **Purpose**: Order processing and management
- **Location**: `/orderservice`
- **Database**: MySQL (`oms` database)
- **Main Class**: `OrderserviceApplication.java`

### 6. Payment Service (Port: 8084)
- **Technology**: Spring Boot + Stripe + Razorpay
- **Purpose**: Payment processing
- **Location**: `/paymentservice`
- **Main Class**: `PaymentserviceApplication.java`
- **Integrations**: Stripe and Razorpay payment gateways

### 7. Email Service (Port: 8085)
- **Technology**: Spring Boot + Apache Kafka
- **Purpose**: Email notifications
- **Location**: `/EmailService`
- **Main Class**: `EmailServiceApplication.java`
- **Features**: Kafka-based messaging for email notifications

## Prerequisites

- **Java 17** or higher
- **Maven 3.6+**
- **MySQL 8.0+**
- **Redis** (for Product Service caching)
- **Apache Kafka** (for Email Service)

## Database Setup

Create the following MySQL databases:
```sql
CREATE DATABASE user;
CREATE DATABASE productservice8mar;
CREATE DATABASE oms;
```

Create a user for the product service:
```sql
CREATE USER 'productservice8maruser'@'localhost' IDENTIFIED BY 'root';
GRANT ALL PRIVILEGES ON productservice8mar.* TO 'productservice8maruser'@'localhost';
FLUSH PRIVILEGES;
```

## Configuration

All services are configured to connect to:
- **Eureka Server**: `http://localhost:8761/eureka`
- **MySQL**: `localhost:3306` (username: `root`, password: `root`)

### Environment Variables

For Payment Service, set up your payment gateway credentials:
```bash
export STRIPE_SECRET_KEY=your_stripe_secret_key
export RAZORPAY_KEY_ID=your_razorpay_key_id
export RAZORPAY_KEY_SECRET=your_razorpay_key_secret
```

## Startup Sequence

**Important**: Start services in the following order to ensure proper service registration:

1. **Start Service Discovery** (Port 8761)
   ```bash
   cd ServiceDiscovery
   mvn spring-boot:run
   ```

2. **Start API Gateway** (Port 8080)
   ```bash
   cd ApiGateway
   mvn spring-boot:run
   ```

3. **Start Core Services** (in any order):
   ```bash
   # User Service (Port 8082)
   cd userrServices
   mvn spring-boot:run
   
   # Product Service (Port 8081)
   cd productservicesfeb29
   mvn spring-boot:run
   
   # Order Service (Port 8083)
   cd orderservice
   mvn spring-boot:run
   
   # Payment Service (Port 8084)
   cd paymentservice
   mvn spring-boot:run
   
   # Email Service (Port 8085)
   cd EmailService
   mvn spring-boot:run
   ```

## Verification

1. **Check Eureka Dashboard**: http://localhost:8761
2. **API Gateway Health**: http://localhost:8080/actuator/health
3. **Service Registration**: All services should appear in the Eureka dashboard

## API Usage

All API calls should go through the API Gateway at `http://localhost:8080`:

- **User APIs**: `http://localhost:8080/users/**`
- **Product APIs**: `http://localhost:8080/products/**`
- **Order APIs**: `http://localhost:8080/orders/**`
- **Payment APIs**: `http://localhost:8080/pay/**`

## Technology Stack

- **Framework**: Spring Boot 3.3.2
- **Cloud**: Spring Cloud 2023.0.3
- **Database**: MySQL 8.0+
- **Cache**: Redis
- **Messaging**: Apache Kafka
- **Service Discovery**: Netflix Eureka
- **API Gateway**: Spring Cloud Gateway
- **Security**: Spring Security + JWT
- **Payment**: Stripe, Razorpay
- **Database Migration**: Flyway
- **Build Tool**: Maven

## Recent Fixes Applied

This project has been completely fixed and standardized:

✅ **Dependency Management**:
- Standardized Spring Boot version to 3.3.2 across all services
- Added proper Spring Cloud dependency management
- Updated JWT library to secure version 0.12.6
- Removed duplicate and incorrect dependencies

✅ **Service Configuration**:
- Fixed Eureka server port to standard 8761
- Resolved port conflicts between services
- Standardized group IDs to `com.vakya`
- Fixed service registration configurations

✅ **Code Issues**:
- Updated JWT implementation to use new API
- Removed deprecated `@EnableEurekaClient` annotations
- Fixed incorrect Eureka server dependency in Product Service
- Ensured all services compile successfully

✅ **Application Properties**:
- Fixed Eureka client URLs to point to correct server
- Assigned unique ports to each service
- Added missing application names and configurations

## Build and Test

To build all services:
```bash
# Build all services
find . -name "pom.xml" -exec dirname {} \; | xargs -I {} bash -c 'echo "Building {}" && cd "{}" && mvn clean compile'
```

All services now compile successfully without errors.

## Support

For issues or questions, please check the service logs and ensure all prerequisites are properly installed and configured.