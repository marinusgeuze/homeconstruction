package com.homeconstruction.core.api

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import javax.persistence.Embeddable

@Embeddable
open class AggregateId
@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
constructor(@get:JsonValue open val id: String) {

    override fun toString(): String = id
}