package com.codewithmosh.store.projections;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductSummaryDto {
    private Long id;
    private String name;
}
