package com.example.exploringcoroutines.domain

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import kotlin.properties.Delegates
import kotlin.properties.ObservableProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by josephmagara on 25/5/20.
 */

interface FlowableProtocol<T : Any> : ReadWriteProperty<Any?, T> {
    fun getFlow(): Flow<T>
}

abstract class FlowableProperty<T : Any>(private var currentValue: T) : FlowableProtocol<T> {

    private val propertyFlow: Flow<T> = flow {}

    @ExperimentalCoroutinesApi
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        propertyFlow.transform { emit(value) }
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return currentValue
    }

    override fun getFlow(): Flow<T> = propertyFlow
}

fun <T : Any> Delegates.flowable(initialValue: T): FlowableProperty<T> = object : FlowableProperty<T>(initialValue) {
    fun toFlow() = getFlow()
}

