package com.emp_serv.emp_serv.service;

import com.emp_serv.emp_serv.Exception.ResourceNotFoundException;
import com.emp_serv.emp_serv.Payload.EmployeeDTO;
import com.emp_serv.emp_serv.Payload.EmployeeResponse;
import com.emp_serv.emp_serv.model.Employee;
import com.emp_serv.emp_serv.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImp1 implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImp1(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    @Override
    public EmployeeDTO addEmployee(EmployeeDTO employeeDto) {
      Employee employee = new Employee();
      employee.setId(employeeDto.getId());
      employee.setFirstName(employeeDto.getFirstName());
      employee.setLastName(employeeDto.getLastName());
      employee.setEmail(employeeDto.getEmail());

        Employee saveEmployee = employeeRepository.save(employee);
        EmployeeDTO employeeDto1 = mapToDTO(saveEmployee);
        return employeeDto1;
    }

    @Override
    public EmployeeResponse getAllEmployee(int pageNum, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?

                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        PageRequest pageable = PageRequest.of(pageNum, pageSize, sort);

        Page<Employee> employees = employeeRepository.findAll(pageable);
        List<Employee> content = employees.getContent();
        List<EmployeeDTO> collect = content.stream().map(employee -> mapToDTO(employee)).collect(Collectors.toList());

        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setContent(collect);
        employeeResponse.setPageNo(employees.getNumber());
        employeeResponse.setPageSize(employees.getSize());
        employeeResponse.setTotalElement(employees.getTotalElements());
        employeeResponse.setTotalPages(employees.getTotalPages());
        employeeResponse.setLast(employees.isLast());
        return employeeResponse;
    }

    @Override
    public EmployeeDTO getHotelsById(Long id) {
        //get employee from database
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        return mapToDTO(employee);
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, long id) {
      //get employee from database
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        employee.setId(employeeDTO.getId());
        employee.setFirstName(employee.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        Employee saveEmployee = employeeRepository.save(employee);
        EmployeeDTO employeeDTO1 = mapToDTO(saveEmployee);

        return employeeDTO1;
    }

    @Override
    public void deleteHotelDetails(Long id) {
        //get all hotels from database
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        employeeRepository.delete(employee);
    }

    private EmployeeDTO mapToDTO(Employee saveEmployee) {
        EmployeeDTO employeeDto = new EmployeeDTO();
        employeeDto.setId(saveEmployee.getId());
        employeeDto.setFirstName(saveEmployee.getFirstName());
        employeeDto.setLastName(saveEmployee.getLastName());
        employeeDto.setEmail(saveEmployee.getEmail());
        return employeeDto;
    }

}




