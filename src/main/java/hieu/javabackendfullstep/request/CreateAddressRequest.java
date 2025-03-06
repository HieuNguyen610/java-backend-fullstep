package hieu.javabackendfullstep.request;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class CreateAddressRequest implements Serializable {

    private String apartmentNumber;
    private int floor;
    private String building;
    private String streetNumber;
    private String street;
    private String city;
    private String country;
    private String addressType;
}
