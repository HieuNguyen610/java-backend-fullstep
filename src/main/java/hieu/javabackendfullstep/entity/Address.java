package hieu.javabackendfullstep.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@Entity(name = "addresses")
public class Address extends AbstractEntity{

    @Column(name = "apartment_number")
    private String apartmentNumber;

    @Column(name = "floor")
    private int floor;

    @Column(name = "building")
    private String building;

    @Column(name = "street_number")
    private String streetNumber;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "address_type")
    private String addressType;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
