# Dependency Injection

## What is Dependency Injection

Inject a dependency (particular implementation) into a class (service).

> Dependencies are normal in application, and the issue is **how dependencies are created and managed**.

## The Spring IOC Container

Spring can create objects and inject them into our classes.

> IOC (Inversion of Control) inverts the control of creating objects and managing dependencies.

## Configuring Beans Using Annotations

- `@Component` is often used for utility classes
- `@Service` for classes that contain business logic
    - `@Primary` as single primary service of multiple candidates
    - `@Qualifier` to prefix parameter of implementation
- `@Autowired` is used for **class has multiple constructors**, and not necessary for single constructor.

> Technically, `@Service` is an alias for `@Component`
