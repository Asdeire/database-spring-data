# Database Processing With Spring Data

## Topic

Working with Databases Using Spring Data

## Objective

To practice working with the Spring Data framework to interact with different types of databases.

## Short Theoretical Background

This practical work focuses on using Spring Data JPA for handling CRUD operations and other advanced functionalities like sorting, pagination, filtering, and searching within a web application. Spring Data abstracts the database interaction, allowing the developer to focus on business logic rather than SQL queries.

## Steps of the Task

### 1. Managing Products and Categories in a Web Application

#### Entity Creation
- **Product**: Create a `Product` class with fields such as `id`, `name`, `price`, `image`, and a reference to a `Category`. These fields represent the minimal attributes.
- **Category**: Create a `Category` class with fields `id` and `name`.

#### Repository Creation
- Create `ProductRepository` and `CategoryRepository` interfaces, extending `JpaRepository` or other suitable interfaces to work with the database.

#### Service Creation
- Create `ProductService` and `CategoryService` classes to handle business logic for products and categories, respectively.

#### Controller Creation
- Create controllers for managing products and categories. For example, `ProductController` and `CategoryController`.

#### Setting Relationships Between Products and Categories
- Use JPA annotations to configure the relationship between products and categories. For example, use the `@ManyToOne` annotation to establish this relationship.

#### Sorting by Categories
- Add a method in `ProductController` to allow sorting products by category, leveraging repository and service methods.

#### Pagination
- Implement pagination for the product list using the capabilities of Spring Data JPA.

#### Filtering and Searching
- Implement filtering products by various parameters, such as category, price range, etc.

#### Frontend Creation
- Create a simple frontend using **Thymeleaf** to display and interact with the products and categories.

## Technologies Used

- **Java**: Programming language
- **Spring Boot**: Framework for building the application
- **Spring Data JPA**: For database interaction
- **Thymeleaf**: For rendering HTML templates
- **Bootstrap**: For responsive styling
- **Maven**: For project management and dependency handling

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/practical-work-4.git
   cd practical-work-4
   ```

2. Build the project using Maven:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. Access the application at `http://localhost:8081`.

## Usage

- Navigate to `/products` to view the list of products.
- Use `/products/new` to create a new product.
- Products can be filtered, sorted by category, and searched by parameters like price range.
- Pagination is available for the product list.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request for any enhancements or bug fixes.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
