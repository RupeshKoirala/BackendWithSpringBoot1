package com.march.example.demo.controller;

import com.march.example.demo.entity.Employee;
import com.march.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/employee")

public class EmployeeController{

        @Autowired
        EmployeeRepository em;
        @GetMapping("/all")
        public List<Employee> getAll(){

            //1.Using size and get to iterate the list
            var allFields = em.findAll();
            for(int i=0; i<allFields.size(); i++){
                String name = allFields.get(i).getFirst_name();
                String reversedString = "";
                for(int j=name.length()-1; j>=0; j--){
                    reversedString+=name.charAt(j);
                }
                System.out.println(reversedString);
                allFields.get(i).setFirst_name(reversedString);
            }
            return allFields;


            //2.Using forEach to iterate the List
            /*List<Employee> allFields = em.findAll();
            for(Employee e: allFields){
                   String firstName =e.getFirst_name();
                   String reversedString = "";
                   for(int i=firstName.length()-1; i>=0; i--){
                        reversedString+=firstName.charAt(i);
                   }
                   e.setFirst_name(reversedString);
                System.out.println(reversedString);
            }
            return allFields;*/
        }

        @GetMapping("/names")
        public String name(){

            List<Employee> all = em.findAll();
            String[] employeeName = new String[all.size()];
            for(int i=0; i<all.size(); i++){
                String name = all.get(i).getFirst_name();
                employeeName[i] = name;
            }
            return Arrays.toString(employeeName);

        }

        @PostMapping("/save")
        public Employee save(@RequestBody Employee emp){
            return em.saveEmployee(emp.getId(), emp.getFirst_name(), emp.getLast_name(), emp.getAge(), emp.getDate_of_birth());
        }

        @GetMapping("/hashmap")
        public HashMap<String, Integer> returnHashMap(){
            List<Employee> allEmployee = em.findAll();
            HashMap<String, Integer> hashName = new HashMap<>();
            for(int i=0; i<allEmployee.size(); i++){
                String value = allEmployee.get(i).getFirst_name();
                hashName.put(value, value.length());
            }

            /*for(Employee e: allEmployee){
                String name = e.getFirst_name();
                hashName.put(name,name.length());
            }
            return hashName;*/
            return hashName;
        }

        @GetMapping("/{id}")
        public Employee getId(@PathVariable("id") Long id){
            Optional<Employee> employee = em.findById(id);
            if(!employee.isEmpty()){
                return employee.get();
            }
           return null;
        }

        @GetMapping("/orderByAge")
        public List<Employee> employeeOrderByAge(){
            List<Employee> allEmployees = em.findAll();

            for (int i = 0; i < allEmployees.size(); i++) {
                for (int j = 0; j < allEmployees.size() - i - 1; j++) {
                    if (allEmployees.get(j).getAge() < allEmployees.get(j + 1).getAge()) {
                        // Swap the elements
                        Employee temp = allEmployees.get(j);
                        allEmployees.set(j, allEmployees.get(j + 1));
                        allEmployees.set(j + 1, temp);
                    }
                }
            }
            return allEmployees;
        }

        @GetMapping("/orderByAge2")
        public List<Employee> employeeOrderByAge2(){
            List<Employee> allEmployees = em.getEmployeesByAgeDesc();
            return allEmployees;
        }

        @GetMapping("/orderByGivenAge/{age}")
        public List<Employee> employeeOrderByGivenAge(@PathVariable("age") int age){
            List<Employee> allEmployees = em.getEmployeesByAgeGreaterThan(age);
            return allEmployees;
        }

   /*     @PostMapping("/getEmpByNames/{firstName}/{lastName}")
        public Employee saveEmployeeByFirstAndLastName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName){
            Employee queryEmployeeValue = em.getEmployeesByFirstAndLastName(firstName, lastName);
            if(queryEmployeeValue.getAge()>18&&queryEmployeeValue.getAge()<65){
                em.save()
            }


            return null;
        }*/

        @PostMapping("/saveEmployeeByName")
        public Employee saveEmployeeByname(@RequestBody Employee emp){
            System.out.println(emp);
            String firstName = emp.getFirst_name();
            String lastName = emp.getLast_name();
            int age = emp.getAge();
            Date dateOfBirth = emp.getDate_of_birth();
//            System.out.println(emp);
            if(firstName instanceof String && lastName instanceof String){
                if(age<65&&age>18){
                   return em.save(emp);
                }else{
                    throw new AgeNotValid("Please enter the age from 18 to 65 only");
                }
            }else{
                throw new NameNotValid("Please enter the valid name with only characters");
            }
        }


        @GetMapping("/date-of-birth/{id}")
        public Date getDateOfBirthById(@PathVariable("id") long id){
            Employee emp = em.getById(id);
            //convert age into dateofbirth
            return emp.getDate_of_birth();
        }

        //create an api that will return list of employee based on matching keyword on first name or last name
        @GetMapping("/all-names/{name}")
        public List<Employee> getEmployeeListByName(@PathVariable("name") String name){
            List<Employee> allEmployees = em.findAll();
            List<Employee> newList = new ArrayList<>();
            System.out.println(allEmployees);
            for(Employee e: allEmployees){
                System.out.println(e.getFirst_name()+e.getLast_name());
                if(e.getFirst_name().equalsIgnoreCase(name) || e.getLast_name().equalsIgnoreCase(name)){
                    newList.add(e);
                }
            }
            System.out.println(newList);
            return newList;
            //if there is initial sequence of characters, get the list...

            //do the same thing using stream api
        }


        //—create an api that will return the total number of employee
        @GetMapping("/total")
        public int getTotalEmployees(){
          /*  List<Employee> total = em.findAll();
            int count = 0;
            for(Employee e: total){
                count++;
            }
            return count;
            */

            //using sql query
            return em.getTotalEmployee();
        }

        @GetMapping("/retire-employees")
        public List<Employee> retireEmployee(){
            List<Employee> employees = em.findAll();
            List<Employee> retiredEmployeeList = new ArrayList<>();

            if(employees.size()>0){
                for(Employee e: employees){
                    if (e.getAge()> 65){
                        e.setTermination(true);
                        e.setEnd_date(new Date());
                        retiredEmployeeList.add(e);
                        em.save(e);
                    }
                }
            }
            return retiredEmployeeList;
        }

        @GetMapping("/calculate-salary")
        public List<Employee> calculateSalary(){
            return null;
        }

        @GetMapping("/desc-order-of-employee")
        public List<Employee> descOrderOfEmployee(){

            //return em.descOrderOfEmployee();


            List<Employee> allEmployees= em.findAll();
            //Collections.sort(allEmployees, new MyComparator());

            Collections.sort(allEmployees);

            return allEmployees;

        }

        @GetMapping("/addressAndDepartment")
        public List<Employee> getAddressAndDepartment(){
            return em.findAll();
        }

        //classwork: create a employee api that will give me a hashmap
    // containing employee based on age range (eg) {"25-35": List<Employee>}
        @GetMapping("/getHashValue")
        public HashMap<Integer, Employee> getHashEmployee(){
            List<Employee> employees = em.findAll();
            HashMap<Integer, Employee> hashMap = new HashMap<>();
            for(Employee e: employees){
                if(e.getAge()>=15&&e.getAge()<=35){
                    hashMap.put(e.getAge(), e);
                }
            }
            return hashMap;
        }

}

//add a address entity and department entity and map it to employee entity