package com.memoizr.assertk

import com.memoizr.assertk.ObjectStuff.notNull
import org.assertj.core.api.AbstractAssert
import org.assertj.core.api.Assertions

enum class ObjectStuff {
    notNull
}

inline fun <reified R : Any> of() = AbstractAssertBuilder.InstanceMatcher<R>()

abstract class AbstractAssertBuilder<S : AbstractAssertBuilder<S, A>, A : Any> internal constructor(actual: A?, selfType: Class<*>) {
    class InstanceMatcher<R>

    @Suppress("UNCHECKED_CAST", "LeakingThis")
    protected val myself: S = selfType.cast(this) as S

    open protected val assertion: AbstractAssert<*, out A?> = Assertions.assertThat(actual)

    infix fun isEqualTo(other: A): S {
        assertion.isEqualTo(other)
        return myself
    }

    infix fun isNotEqualTo(other: A): S {
        assertion.isNotEqualTo(other)
        return myself
    }

    @Suppress("UNUSED_PARAMETER")
    inline infix fun <reified R : Any> isInstance(bar: InstanceMatcher<R>): S {
        assertion.isInstanceOf(R::class.java)
        return myself
    }

    infix fun _is(objectStuff: ObjectStuff?): S {
        return when (objectStuff) {
            notNull -> {
                assertion.isNotNull()
                myself
            }
            else -> {
                assertion.isNull()
                myself
            }
        }
    }

    infix fun describedAs(description: String): S {
        assertion.`as`(description)
        return myself
    }

    @Suppress("UNCHECKED_CAST")
    infix fun isSuchThat(assertionBlock: S.(S) -> Unit): S {
        (this as S).assertionBlock(this)
        return myself
    }
}