package br.edu.utfpr.pb.pw44s.server.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Column(length = 1024)
    private String description;

    @NotNull
    private BigDecimal price;

    private CategoryDTO category;
}
