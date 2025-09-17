package com.urbanride.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class DBConstant extends BaseModel{

    @Column(unique = true, nullable = false)
    private String name;

    private String value;

}
