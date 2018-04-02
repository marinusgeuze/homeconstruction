package com.homeconstruction.home.api

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.homeconstruction.core.api.AggregateId
import org.axonframework.commandhandling.TargetAggregateIdentifier
import java.io.Serializable
import javax.persistence.Embeddable

data class DefineHomeType(@TargetAggregateIdentifier val id: String, val type: HomeType, val description: HomeDescription)
data class HomeTypeDefined(val id: String, val type: HomeType, val description: HomeDescription)
data class DefineHome(@TargetAggregateIdentifier val id: String, val projectNumber: ProjectNumber,
                 val lotSize: LotSize, val areaOfUse: AreaOfUse, val price: Price)
data class HomeDefined(val id: String, val projectNumber: ProjectNumber, val lotSize: LotSize,
                  val areaOfUse: AreaOfUse, val price: Price)


data class HomeId
constructor(override val id: String) : AggregateId(id), Serializable {
}

@Embeddable
data class HomeType
@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
constructor(@get:JsonValue val homeType: String) {
}

@Embeddable
data class HomeDescription
@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
constructor(@get:JsonValue val homeDescription: String) {
}

@Embeddable
data class ProjectNumber
@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
constructor(@get:JsonValue val projectNumber: Int) {
}

@Embeddable
data class LotSize
@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
constructor(@get:JsonValue val lotSize: Int) {
}

@Embeddable
data class AreaOfUse
@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
constructor(@get:JsonValue val areaOfUse: Int) {
}

@Embeddable
data class Price
@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
constructor(@get:JsonValue val price: Int) {
}