package com.example.mapscomposesample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CallOut(name: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy((-6).dp),
    ) {
        Text(
            text = name,
            fontSize = 12.sp,
            modifier = Modifier
                .drawBehind {
                    drawRoundRect(color = Color.White, cornerRadius = CornerRadius(12f))
                }
                .padding(12.dp, 6.dp)
        )
        Box(
            modifier = Modifier
                .size(12.dp)
                .rotate(45f)
                .clip(RectangleShape)
                .background(Color.White)
        )
    }
}
