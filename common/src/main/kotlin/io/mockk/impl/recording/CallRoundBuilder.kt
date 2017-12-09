package io.mockk.impl.recording

import io.mockk.Invocation
import io.mockk.Matcher
import kotlin.reflect.KClass

class CallRoundBuilder {
    val signedCalls = mutableListOf<SignedCall>()

    val matchers = mutableListOf<Matcher<*>>()
    val signatures = mutableListOf<Any>()

    fun addMatcher(matcher: Matcher<*>, sigValue: Any) {
        matchers.add(matcher)
        signatures.add(sigValue)
    }

    fun addSignedCall(retType: KClass<*>, invocation: Invocation) {
        val signedCall = SignedCall(retType,
                invocation,
                matchers.toList(),
                signatures.toList())

        signedCalls.add(signedCall)

        matchers.clear()
        signatures.clear()
    }

    fun build() = CallRound(signedCalls.toList())
}