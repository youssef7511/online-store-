# online-store-
online store fullstuck web with springboot for back and front-end and mysql for database /
the project have the  architecture busniss web dao 
1. Presentation Layer (Web Layer)
web/
├── controllers/
│   └── ProductController.java    # Handles HTTP requests/responses
└── models/
    └── requests/
        └── ProductForm.java      # DTO for form validation
   Responsibilities:
Handles user interface and HTTP requests
Form validation using Bean Validation
Thymeleaf template rendering
Request/Response mapping
2. Business Logic Layer (Service Layer)
business/
├── services/
│   └── ProductService.java       # Service interface
└── servicesImpl/
    └── ProductServiceImpl.java   # Service implementation
   Responsibilities:
Business logic implementation
Transaction management
Data validation and business rules
Exception handling for business operations
3. Data Access Layer (DAO Layer)
   dao/
├── entities/
│   └── Product.java             # JPA Entity
└── repositories/
    ├── ProductRespository.java  # Spring Data JPA Repository
    └── ClientRepository.java    # (Unused in current implementation)
