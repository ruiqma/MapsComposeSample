package com.example.mapscomposesample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MapContent()
        }
    }
}

@Composable
fun MapContent() {
    val defaultPosition = LatLng(35.689501, 139.691722) // 東京都庁
    val defaultZoom = 8f
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultPosition, defaultZoom)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
    ) {
        Prefecture.values().forEach {
            MarkerComposable(
                state = MarkerState(LatLng(it.latitude, it.longitude)),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CallOut(it.name)
                    Spacer(modifier = Modifier.height(4.dp))
                    Image(
                        modifier = Modifier.size(45.dp, 30.dp),
                        painter = painterResource(it.flag),
                        contentDescription = null,
                    )
                }
            }
        }
    }
}

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
