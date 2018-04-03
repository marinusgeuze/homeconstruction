package com.homeconstruction.home.api

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.homeconstruction.buyer.api.BuyerId
import com.homeconstruction.core.api.AggregateId
import org.axonframework.commandhandling.TargetAggregateIdentifier
import java.io.Serializable
import java.time.LocalDate
import javax.persistence.Embeddable

data class DefineHomeType(@TargetAggregateIdentifier val id: String, val projectId: String, val typeKey: HomeTypeKey, val description: HomeTypeDescription)
data class HomeTypeDefined(val id: String, val projectId: String, val typeKey: HomeTypeKey, val description: HomeTypeDescription)
data class DefineHome(@TargetAggregateIdentifier val id: String, val homeTypeId: HomeTypeId, val projectNumber: ProjectNumber,
                      val lotSize: LotSize, val areaOfUse: AreaOfUse, val price: Price)
data class HomeDefined(val id: String, val homeTypeId: HomeTypeId, val projectNumber: ProjectNumber, val lotSize: LotSize,
                       val areaOfUse: AreaOfUse, val price: Price)
data class ReserveHome(@TargetAggregateIdentifier val id: String, val buyerId: BuyerId, val reservationDate: ReservationDate);
data class HomeReserved(val id: String, val buyerId: BuyerId, val reservationDate: ReservationDate);
data class SellHome(@TargetAggregateIdentifier val id: String, val buyerId: BuyerId, val soldDate: SoldDate)
data class HomeSold(val id: String, val buyerId: BuyerId, val soldDate: SoldDate)
data class StartHomeConstruction(@TargetAggregateIdentifier val id: String, val buyerId: BuyerId, val constructionStartDate: HomeConstructionStartDate)
data class HomeConstructionStarted(val id: String, val buyerId: BuyerId, val constructionStartDate: HomeConstructionStartDate)

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

@Embeddable
data class ReservationDate
@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
constructor(@get:JsonValue val reservationDate: LocalDate) {
}

@Embeddable
data class SoldDate
@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
constructor(@get:JsonValue val soldDate: LocalDate) {
}

@Embeddable
data class HomeConstructionStartDate
@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
constructor(@get:JsonValue val constructionStartDate: LocalDate) {
}