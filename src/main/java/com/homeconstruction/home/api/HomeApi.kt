package com.homeconstruction.home.api

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.homeconstruction.core.api.AggregateId
import org.axonframework.commandhandling.TargetAggregateIdentifier
import java.io.Serializable
import javax.persistence.Embeddable

data class DefineHomeType(@TargetAggregateIdentifier val id: String, val projectId: String, val typeKey: HomeTypeKey, val description: HomeTypeDescription)
data class HomeTypeDefined(val id: String, val projectId: String, val typeKey: HomeTypeKey, val description: HomeTypeDescription)
data class DefineHome(@TargetAggregateIdentifier val id: String, val homeTypeId: HomeTypeId, val projectNumber: ProjectNumber,
                      val lotSize: LotSize, val areaOfUse: AreaOfUse, val price: Price)
data class HomeDefined(val id: String, val homeTypeId: HomeTypeId, val projectNumber: ProjectNumber, val lotSize: LotSize,
                       val areaOfUse: AreaOfUse, val price: Price)

data class HomeTypeId
constructor(override val id: String) : AggregateId(id), Serializable {
}

@Embeddable
data class HomeTypeKey
@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
constructor(@get:JsonValue val typeKey: String) {
}

@Embeddable
data class HomeTypeDescription
@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
constructor(@get:JsonValue val description: String) {
}

data class HomeId
constructor(override val id: String) : AggregateId(id), Serializable {
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