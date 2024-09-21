package net.javaproject.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // we use to this get get constructors, getter, setter methods automatically
@AllArgsConstructor // to dont provide constructor
public class AccountDto {

    private Long id;
    private String accountHolderName;
    private double balance;
}
