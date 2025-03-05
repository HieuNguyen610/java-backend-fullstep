package hieu.javabackendfullstep.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String apartmentNumber;
    private int floor;
    private String building;
    private String streetNumber;
    private String street;
    private String city;
    private String country;
    private String addressType;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
