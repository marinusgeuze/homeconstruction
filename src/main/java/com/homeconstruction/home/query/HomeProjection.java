package com.homeconstruction.home.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class HomeProjection {

    private String id;
    private String projectNumber;
    private Integer lotSize;
    private Integer areaOfUse;
    private Integer price;
}