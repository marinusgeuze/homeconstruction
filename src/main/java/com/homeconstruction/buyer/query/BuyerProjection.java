package com.homeconstruction.buyer.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BuyerProjection {

    private String id;
    private String firstname;
    private String surname;
    private String address;
}