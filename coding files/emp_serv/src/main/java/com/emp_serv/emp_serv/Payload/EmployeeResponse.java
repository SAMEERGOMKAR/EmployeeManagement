package com.emp_serv.emp_serv.Payload;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeResponse {
    private List<EmployeeDTO> content;
    private int pageNo;
    private int pageSize;
    private long totalElement;
    private int totalPages;
    private boolean last;
}
