package com.example.groceryprices.models.data;

import com.example.groceryprices.models.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface DepartmentDAO extends CrudRepository<Department, Integer>{
    Department findAllBydepartmentId(Integer departmentId);
}
