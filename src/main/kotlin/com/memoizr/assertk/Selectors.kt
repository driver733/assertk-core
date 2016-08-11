package com.memoizr.assertk

sealed class SequenceSelector
object nullOrEmpty : SequenceSelector()
object empty : SequenceSelector()
object notEmpty : SequenceSelector()

object onlyDigits

sealed class ObjectSelector
object notNull : ObjectSelector()

sealed class CauseSelector
object noCause : CauseSelector()

object duplicates

object onlyNotNull

sealed class IntegerSelector
object zero : IntegerSelector()
object notZero : IntegerSelector()
object positive : IntegerSelector()
object notPositive : IntegerSelector()
object negative : IntegerSelector()
object notNegative : IntegerSelector()
