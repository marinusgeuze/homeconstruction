package com.homeconstruction.home.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class HomeProjection {

    private String id;
    private String homeTypeId;
    private String projectNumber;
    private Integer lotSize;
    private Integer areaOfUse;
    private Integer price;
    private String buyerId;
    private LocalDate reservationDate;
    private LocalDate soldDate;
    private LocalDate constructionStartDate;
}