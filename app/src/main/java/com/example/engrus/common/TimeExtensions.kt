package com.example.engrus.common

val Int.minutes get() = this * 60_000L
val Int.hours get() = this * 60 * 60_000L
val Int.days get() = this * 24 * 60 * 60_000L