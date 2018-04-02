package com.homeconstruction.home.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class HomeTypeProjection {

    private String id;
    private String projectId;
    private String typeKey;
    private String description;
}