package com.example.Shwas.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.dp

@Immutable
class Shapes(
    val circle: CornerBasedShape,
    val roundedCornerX4Small: CornerBasedShape,
    val roundedCornerX3Small: CornerBasedShape,
    val roundedCornerX2Small: CornerBasedShape,
    val roundedCornerXXSmall: CornerBasedShape,
    val roundedCornerXSmall: CornerBasedShape,
    val roundedCornerSmall: CornerBasedShape,
    val roundedCornerNormal: CornerBasedShape,
    val roundedCorner2Normal: CornerBasedShape,
    val roundedCornerMedium: CornerBasedShape,
    val rounderCornerMedium16: CornerBasedShape,
    val roundedCornerMedium18: CornerBasedShape,
    val roundedCornerLarge: CornerBasedShape,
    val roundedCornerLarge24: CornerBasedShape,
    val roundedCornerLarge26: CornerBasedShape,
    val roundedCornerLarge28: CornerBasedShape,
    val roundedCornerLarge2: CornerBasedShape,
    val roundedCornerLarge32: CornerBasedShape,
    val roundedCornerXLarge: CornerBasedShape
) {
    companion object {
        val default = Shapes(
            circle = CircleShape,
            roundedCornerX4Small = RoundedCornerShape(2.dp),
            roundedCornerXXSmall = RoundedCornerShape(4.dp),
            roundedCornerX3Small = RoundedCornerShape(6.dp),
            roundedCornerX2Small = RoundedCornerShape(7.dp),
            roundedCornerXSmall = RoundedCornerShape(8.dp),
            roundedCornerSmall = RoundedCornerShape(10.dp),
            roundedCornerNormal = RoundedCornerShape(12.dp),
            roundedCorner2Normal = RoundedCornerShape(13.dp),
            rounderCornerMedium16 = RoundedCornerShape(16.dp),
            roundedCornerMedium = RoundedCornerShape(15.dp),
            roundedCornerMedium18 = RoundedCornerShape(18.dp),
            roundedCornerLarge = RoundedCornerShape(20.dp),
            roundedCornerLarge24 = RoundedCornerShape(24.dp),
            roundedCornerLarge26 = RoundedCornerShape(26.dp),
            roundedCornerLarge28 = RoundedCornerShape(28.dp),
            roundedCornerLarge2 = RoundedCornerShape(30.dp),
            roundedCornerLarge32 = RoundedCornerShape(32.dp),
            roundedCornerXLarge = RoundedCornerShape(40.dp)
        )
    }
}