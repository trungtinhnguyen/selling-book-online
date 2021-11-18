package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class BillDto extends BaseDto{
    private String username;
    private int status;
    private Date modifiedDate;
    private List<BillDetailDto> details;
    public double getTotalPrice() {
        if (details == null || details.isEmpty()) {
            return 0;
        }
        return details.stream().mapToDouble(BillDetailDto::getPrices).sum();
    }
}
