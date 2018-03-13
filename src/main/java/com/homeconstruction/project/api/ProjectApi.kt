package com.homeconstruction.project.api

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import org.axonframework.commandhandling.TargetAggregateIdentifier
import javax.persistence.Embeddable

class InitiateProjectCommand(@TargetAggregateIdentifier val id: String, val name: ProjectName)
class ProjectInitiatedEvent(val id: String, val name: ProjectName)

@Embeddable
data class ProjectName @JsonCreator(mode = JsonCreator.Mode.DELEGATING) constructor(@get:JsonValue val name: String) {

    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("Project name may not be blank")
        }
    }

    override fun toString(): String = name
}