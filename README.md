# Spring-DAO
Used to add presentation layer to the application
1. Using DataSource
2. JDBC Template
## Jars Required
- spring-core
- spring-context
- spring-jdbc
- mysql-connector

## Annotations
- Service (subset of component used in service layer)
- Repository (Subset of component used in persistance layer)

## XML template
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans 
           http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
           http://www.springframework.org/schema/context
           http://www.springframework.org/schema/context/spring-context-3.0.xsd">
               
     <context:annotation-config/>
	 <context:component-scan base-package="com"></context:component-scan>
	 
	 <bean id="dataSource"
		class="org.springframework.jdbc.datasource.DriverManagerDataSource">
		<property name="driverClassName" value="com.mysql.cj.jdbc.Driver" />
		<property name="url" value= "jdbc:mysql://localhost:3307/spring" />
		<property name="username" value="root" />
		<property name="password" value="root"/>
	</bean>
	
</beans>
```
