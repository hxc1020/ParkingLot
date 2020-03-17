package com.thoughtworks.ddd

import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

infix fun Any.`should be`(any: Any) {
    assertEquals(any, this)
}

infix fun Any.not(any: Any?) {
    assertNotEquals(any, this)
}

infix fun Any.`is`(any: Any?) {
    assertEquals(any, this)
}