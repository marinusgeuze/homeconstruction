package com.homeconstruction.project.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class ProjectProjection {

    private String id;
    private String name;
    private Boolean minimumAmountOfBuyersTargetReached;
    private Integer minimumAmountOfBuyersReachedPercentage;
    private LocalDate minimumAmountOfBuyersTargetReachedDate;
    private LocalDate constructionOnSiteStartDate;
}
