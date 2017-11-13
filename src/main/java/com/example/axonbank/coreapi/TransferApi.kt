package com.example.axonbank.coreapi

import org.axonframework.commandhandling.TargetAggregateIdentifier

class RequestMoneyTransferCommand(@TargetAggregateIdentifier val transferId : String, val sourceAccount : String,
                                  val targetAccount : String, val amount : Int)
class MoneyTransferRequestedEvent(val transferId : String, val sourceAccount : String,
                                  val targetAccount : String, val amount : Int)

class CompleteMoneyTransferCommand(@TargetAggregateIdentifier val transactionId : String)
class MoneyTransferCompletedEvent(val transferId : String)