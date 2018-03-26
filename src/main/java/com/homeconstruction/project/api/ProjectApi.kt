package com.homeconstruction.project.api

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.homeconstruction.core.api.AggregateId
import org.axonframework.commandhandling.TargetAggregateIdentifier
import java.io.Serializable
import java.time.LocalDate
import javax.persistence.Embeddable

class InitiateProject(@TargetAggregateIdentifier val id: ProjectId, val name: ProjectName)
class ProjectInitiated(val id: ProjectId, val name: ProjectName)
class ReachProjectTarget(@TargetAggregateIdentifier val id: ProjectId, val percentage: MinimumAmountOfBuyersReachedPercentage, val reachedDate: LocalDate)
class ProjectTargetReached(val id: ProjectId, val percentage: MinimumAmountOfBuyersReachedPercentage, val reachedDate: LocalDate)
class StartConstructionOnSite(@TargetAggregateIdentifier val id: ProjectId, val startDate: LocalDate)
class ConstructionOnSiteStarted(val id: ProjectId, val startDate: LocalDate)

data class ProjectId
constructor(override val id: String) : AggregateId(id), Serializable {
}

@Embeddable
data class ProjectName
@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
constructor(@get:JsonValue val name: String) {

    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("Project name may not be blank")
        }
    }

    override fun toString(): String = name
}

@Embeddable
data class MinimumAmountOfBuyersReachedPercentage
@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
constructor(@get:JsonValue val minimumAmountOfBuyersReachedPercentage: Int?) {

    init {
        if (minimumAmountOfBuyersReachedPercentage !in 1..100) {
            throw IllegalArgumentException("Project reached percentage must be between 1 and 100")
        }
    }

    override fun toString(): String = minimumAmountOfBuyersReachedPercentage.toString()
}