package com.emp_serv.emp_serv.controller;

import com.emp_serv.emp_serv.Payload.EmployeeDTO;
import com.emp_serv.emp_serv.Payload.EmployeeResponse;
import com.emp_serv.emp_serv.service.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping("/addEmployee")
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employeeDto) {
        EmployeeDTO employeeDto1 = employeeService.addEmployee(employeeDto);
        return new ResponseEntity<>(employeeDto1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<EmployeeResponse> getAllEmployee(
            @RequestParam(value = "pageNum", defaultValue = "0", required = false) int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        EmployeeResponse allEmployee = employeeService.getAllEmployee(pageNum, pageSize, sortBy, sortDir);
       return  new ResponseEntity<>(allEmployee,HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO>getEmployeeById(@PathVariable(name = "id")Long employeeId){
        EmployeeDTO hotelsById = employeeService.getHotelsById(employeeId);
        return new ResponseEntity<>(hotelsById,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee( @RequestBody EmployeeDTO employeeDTO,@PathVariable(name = "id")long id) {
        EmployeeDTO employeeDTO1 = employeeService.updateEmployee(employeeDTO, id);
        return new ResponseEntity<>(employeeDTO1,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(name = "id")Long id){
        employeeService.deleteHotelDetails(id);
        return new ResponseEntity<>("Hotel Details Delete Successfully",HttpStatus.OK);
    }
    }
