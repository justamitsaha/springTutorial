# Spring Basic: Core Framework Concepts

This module is a step-by-step guide to understanding the foundational principles of the **Spring Framework**, specifically Dependency Injection (DI) and Inversion of Control (IoC).

## Learning Roadmap

### 1. Coupling (Tight vs. Loose)
- **`a_tightCoupeling`**: Shows a `Traveler` class directly instantiating a `Car`. If we want to switch to a `Bike`, we must modify the `Traveler` code.
- **`b_looseCoupeling`**: Introduces the `Vehicle` interface. `Traveler` now depends on the interface, and the specific implementation is passed in, making the code flexible.

### 2. Configuration Styles
- **`c_annotationBasedConfiguration`**: Uses `@Component` on classes and `@ComponentScan` in `AppConfiguration`. Spring automatically discovers and manages the beans.
- **`d_javaBasedConfiguration`**: Uses `@Configuration` and `@Bean` methods to explicitly define beans. This is useful for third-party classes or complex setup logic.
- **`e_stereoTypeAnnotations`**: Explains the semantic difference between `@Component`, `@Service`, `@Repository`, and `@Controller`.

### 3. Dependency Injection (DI) Types
- **`f_dependencyInjectionTypes`**: Compares **Constructor Injection** (recommended for required dependencies) vs. **Setter Injection** (for optional dependencies).

### 4. Bean Lifecycle & Scopes
- **`g_beanLifeCycle`**: Demonstrates `@PostConstruct` and `@PreDestroy` hooks.
- **`h_beanScope`**:
    - **Singleton (Default):** One instance per Spring container.
    - **Prototype:** A new instance every time the bean is requested.
- **`i_lazyBeanLoad`**: Shows how to use `@Lazy` to delay bean initialization until it's actually needed, speeding up application startup.

---

## How to Run
Since this is a core Spring module without Spring Boot, each concept has its own `main` method. 

1. Navigate to the specific package: `src/main/java/com/saha/amit/`
2. Run the `Client.java` or `Demo.java` file within the sub-package.
   Example: Run `AnnotationBasedSpringClient.java` to see DI in action.

## Key Learnings
- **IoC Container:** The Spring container (ApplicationContext) is responsible for instantiating, configuring, and assembling beans.
- **Dependency Injection:** The process where the container "injects" dependencies into a bean at runtime.
- **Component Scanning:** Automating bean registration by scanning packages.
