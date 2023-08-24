package com.emp_serv.emp_serv.service;

import com.emp_serv.emp_serv.Payload.EmployeeDTO;
import com.emp_serv.emp_serv.Payload.EmployeeResponse;

public interface EmployeeService {

    EmployeeDTO addEmployee(EmployeeDTO employeeDto);

    public EmployeeResponse getAllEmployee(int pageNum, int pageSize, String sortBy, String sortDir);

    EmployeeDTO getHotelsById(Long employeeId);

    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, long id);

    void deleteHotelDetails(Long id);
}

