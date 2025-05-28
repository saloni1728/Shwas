package com.example.Shwas.domain.model

data class TimedInstruction(
    val text: String,
    val durationMs: Long,
    val type: BreathType
)

enum class BreathType { INHALE, EXHALE, HOLD, SOUND, NEUTRAL }