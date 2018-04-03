package com.homeconstruction.buyer.api

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.homeconstruction.core.api.AggregateId
import org.axonframework.commandhandling.TargetAggregateIdentifier
import java.io.Serializable
import javax.persistence.Embeddable

data class AddBuyer(@TargetAggregateIdentifier val id: String, val firstname: Firstname, val surname: Surname, val address: Address)
data class BuyerAdded(val id: String, val firstname: Firstname, val surname: Surname, val address: Address)

data class BuyerId
constructor(override val id: String) : AggregateId(id), Serializable {
}

@Embeddable
data class Firstname
@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
constructor(@get:JsonValue val firstname: String) {
}

@Embeddable
data class Surname
@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
constructor(@get:JsonValue val surname: String) {
}

@Embeddable
data class Address
@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
constructor(@get:JsonValue val address: String) {
}