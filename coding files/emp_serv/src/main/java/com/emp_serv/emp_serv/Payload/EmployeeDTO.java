package com.emp_serv.emp_serv.Payload;

import lombok.Data;

@Data
public class EmployeeDTO {
    private long id;

    private String firstName;

    private String lastName;

    private String email;
}
