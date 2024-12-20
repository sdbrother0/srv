package srv.domains.customer;

import lombok.Data;

@Data
public class CustomerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String name;
}
