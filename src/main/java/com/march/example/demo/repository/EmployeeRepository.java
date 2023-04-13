package com.march.example.demo.repository;

import com.march.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value="select * from employee order by age desc", nativeQuery = true)
    public List<Employee> getEmployeesByAgeDesc();

    @Query(value="select * from employee where age> :age order by age asc", nativeQuery = true)
    public List<Employee> getEmployeesByAgeGreaterThan(@Param("age") int age);

    @Query(value="select * from employee where first_name = :firstName and last_name = :lastName", nativeQuery = true)
    public Employee getEmployeesByFirstAndLastName(
            @Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query(value="select * from employee where first_name = :firstName and last_name = :lastName and age >18 and age<65", nativeQuery = true)
    public Employee getEmployeesByFirstAndLastNameAndAgeGap(
            @Param("firstName") String firstName, @Param("lastName") String lastName);


    @Query(value="INSERT INTO employee values(:id, :firstName, :lastName, :age, :dateOfBirth)", nativeQuery = true)
    public Employee saveEmployee(@Param("id") long id, @Param("firstName") String firstName,
                                 @Param("lastName") String lastName, @Param("age") int age,
                                 @Param("dateOfBirth") Date dateOfBirth);

    @Query(value ="select count(id) from employee", nativeQuery = true)
    public int getTotalEmployee();

    @Query(value = "select * from employee order by first_name desc", nativeQuery = true)
    public List<Employee> descOrderOfEmployee();


//    @Query(value="select first_name, last_name, age, date_of_birth, start_date, end_date, termination, address.address_name, department.department_name \n" +
//            "from employee inner join address on employee.id = address.id\n" +
//            "inner Join department on employee.department_id = department.dept_id", nativeQuery = true)
//    public List<Employee> getAddressAndDepartment();
}
