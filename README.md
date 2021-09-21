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

## Data Source XML template
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
## JDBC Template
- makes your life much easier, removes boilerplate codes
- uses DataSource internally ie JdbcTemplate is dependent on DataSource

## JDBC XML Template
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
		<property name="driverClassName" value="com.mysql.jdbc.Driver" />
		<property name="url" value= "jdbc:mysql://localhost:3307/spring" />
		<property name="username" value="root" />
		<property name="password" value="root"/>
	</bean>
	
	<bean id="jdbcTemplate"
		class="org.springframework.jdbc.core.JdbcTemplate">
		<property name="dataSource" ref="dataSource"></property>
	</bean>
	
</beans>
```
## Jdbc Template key sytax
```java
@Autowired 
DataSource dataSource;
@Autowired
JdbcTemplate jdbcTemplate;
```
1. *Query which return integer values*
 	```java
	 jdbcTemplate.queryForInt(sql_query);
	 ```
	 - example
	 ```java
	 public int getNumberOfRows()
	 {
	 	return jdbcTemplate.queryForInt("select count(*) from employee");
	 }
	 ```
2. *Query which return generic type*
 	```java
	 jdbcTemplate.queryForObject(sql_query, wrapper_class_of_return_type);
	 ```
	 - example
	 ```java
	 public int getNumberOfRows()
	 {
	 	return jdbcTemplate.queryForObject("select count(*) from employee", Integer.class);
	 }
	 ```
3. *Query which takes input and gives output*
	```java
	 jdbcTemplate.queryForObject(sql_query, new Object[]{parameters});
	 ```
	 - example
	 ```java
	 @Override
	 public String employeeName(String empId) {
		sql = "select empName from employee where empId = ?";
		return jdbcTemplate.queryForObject(sql,new Object[] {empId}, String.class);
	}

	 ```
5. *Inserting data into table*
	```java
	 jdbcTemplate.update(sql_query, columm1, column2 ......., column n);
	 ```
	 - example
	 ```java
	 public int insert(Employee emp) {
		sql = "Insert into employee values(?,?,?);";
		return jdbcTemplate.update(sql,emp.getEmpId(),emp.getEmpName(), emp.getEmpSalary());	
	}
	 ```
5. *Deleting data from table*
	```java
	 jdbcTemplate.update(sql_query, columm);
	 ```
	 - example
	 ```java
	 public int delete(String empId) {
		sql = "Delete from employee where empId = ?";
		return jdbcTemplate.update(sql,empId);	
	}
	 ```
6. *Fetching entire row*
	- This is tricky, you need to have a row mapper object
	```java
	public static final class EmployeeRow implements RowMapper<Employee>{
		@Override
		public Employee mapRow(ResultSet rs, int arg1) throws SQLException {
			Employee e = new Employee();
			e.setEmpId(rs.getString(1));
			e.setEmpName(rs.getString(2));
			e.setEmpSalary(rs.getDouble(3));
			return e;
		}
	}
	
	@Override
	public Employee search(String empId) {
		sql = "select * from employee where empId = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{empId}, new EmployeeRow());
	}

	```
7. *Query which returns entire table*
	```java
	jdbcTemplate.query(sql, rowMapper);
	```
	- example
	```java
	public List<Employee> getAllEmployees() {
		sql = "select * from employee";
		return jdbcTemplate.query(sql, new EmployeeRow());
	}
	```
