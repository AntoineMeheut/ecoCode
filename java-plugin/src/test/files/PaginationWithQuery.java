package fr.greencodeinitiative.java.checks;

import java.util.*;

import AvoidRepositoryCallInLoopCheck.Employee;
import AvoidRepositoryCallInLoopCheck.EmployeeRepository;

public class PaginationWithQuery {

    @Repository
    public class EmployeeRepository {

      @PersistenceContext
      private EntityManager em;

      public List<Employee> getEmployeesPaginated(int noOfRecords, int pageIndex) {       
          
          return em.createNamedQuery("getEmployees", Employee.class)
          	      .setMaxResults(noOfRecords)
          	      .setFirstResult(pageIndex * noOfRecords)
          	      .getResultList();
      }
      
      public List<Employee> getEmployeesWithoutPagination() {       
          
          return em.createNamedQuery("getEmployees", Employee.class)
          	      .getResultList(); //Noncompliant {{Use Pagination}}
      }
    }
    
    public class Employee {
    }

}