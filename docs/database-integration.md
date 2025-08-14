# Database Integration

## Data Access Technologies

### Repositories

SPRING DATA JPA

- Provides repositories interfaces 

### Object-oriented persistence

JPA

- Jakarta Persistence API
- A specification for mapping Java objects to database tables
- We don't write SQL queries
- We work directly with objects

HIBERNATE

- An Object/Relational Mapper (ORM)
- Similar to Entity Framework (in .NET)
- We don't write SQL queries
- Provide caching
- Hibernate Query Language
- Automatic schema generation

### Low-level access

JDBC

- Java Database Connectivity
- Allows Java code to execute SQL directly
- We have to manually manage everything

## Lombok

Gives us bunch of annotations and instruct Intellij to **automatically generate annotations and utility methods at compile time** for simplifying our code.

- `@Getter`
- `@Setter`
- `@AllArgsConstructor`
- `@NoArgsConstructor`
- `@ToString`

## Generating Database Tables with Hibernate

### Situations

In reality, this approach should only be used for

1. Development
2. Quick prototyping

### Problems

1. With model first, we should include all the database details in entities.
    > Otherwise, our database schema will not be in good shape.
2. As we modify our domain model, Hibernate may update the schema in ways that may not be optimized.
    > So over time, our database schema might end up looking real ugly.
3. Losing versioning make us can't recreate the exact same database on other machines.
    > Like a testing environment, production environment, or on someone else's machine in the same team.

### Suggestions

1. Use Hibernate to create a working prototype which database is in good shape
2. Use JPA buddy to create the initial migration for properly versioning your database
3. Allow to recreate the same database on other machines

## Managing Transactions

A group of operations that succeed or fail together.
> Keep a system in a consistence state.

- Create an order
- Reduce the stock