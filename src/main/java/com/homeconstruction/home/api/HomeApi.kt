package com.homeconstruction.home.api

import org.axonframework.commandhandling.TargetAggregateIdentifier

class CreateHomeDefinition(@TargetAggregateIdentifier val id: String)
class HomeDefinitionCreated(val id: String)