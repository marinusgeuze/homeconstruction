package com.homeconstruction.home.api

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.homeconstruction.core.api.AggregateId
import org.axonframework.commandhandling.TargetAggregateIdentifier
import java.io.Serializable
import javax.persistence.Embeddable

class DefineHomeType(@TargetAggregateIdentifier val id: String, val type: HomeType, val description: HomeDescription)
class HomeTypeDefined(val id: String, val type: HomeType, val description: HomeDescription)
class DefineHome(@TargetAggregateIdentifier val id: HomeId, val projectNumber: ProjectNumber,
                 val lotSize: LotSize, val areaOfUse: AreaOfUse, val price: Price)
class HomeDefined(val id: HomeId, val projectNumber: ProjectNumber, val lotSize: LotSize,
                  val areaOfUse: AreaOfUse, val price: Price)


data class HomeId
constructor(override val id: String) : AggregateId(id), Serializable {
}

@Embeddable
data class HomeType
@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
constructor(@get:JsonValue val homeType: String) {

    override fun toString(): String = homeType
}

@Embeddable
data class HomeDescription
@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
constructor(@get:JsonValue val homeDescription: String) {

    override fun toString(): String = homeDescription
}

@Embeddable
data class ProjectNumber
@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
constructor(@get:JsonValue val projectNumber: Int) {

    override fun toString(): String = projectNumber.toString()
}

@Embeddable
data class LotSize
@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
constructor(@get:JsonValue val lotSize: Int) {

    override fun toString(): String = lotSize.toString()
}

@Embeddable
data class AreaOfUse
@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
constructor(@get:JsonValue val areaOfUse: Int) {

    override fun toString(): String = areaOfUse.toString()
}

@Embeddable
data class Price
@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
constructor(@get:JsonValue val price: Int) {

    override fun toString(): String = price.toString()
}