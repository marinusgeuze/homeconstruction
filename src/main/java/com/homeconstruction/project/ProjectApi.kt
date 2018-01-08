package com.homeconstruction.project

import org.axonframework.commandhandling.TargetAggregateIdentifier

class CreateProjectCommand(@TargetAggregateIdentifier val id : String, val name : String)
class ProjectCreatedEvent(val id : String, val name : String)