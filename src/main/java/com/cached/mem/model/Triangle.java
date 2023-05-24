package com.cached.mem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("Triangle")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Triangle implements Serializable {
    @Id
    private Integer id;

    private int A;

    private int B;

    private int C;

    private String Type;

    private int Area;
}
