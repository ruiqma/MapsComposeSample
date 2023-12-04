package com.example.mapscomposesample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.clustering.Clustering
import com.google.maps.android.compose.clustering.rememberClusterManager
import com.google.maps.android.compose.clustering.rememberClusterRenderer
import com.google.maps.android.compose.rememberCameraPositionState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MapContent()
        }
    }
}

@OptIn(MapsComposeExperimentalApi::class)
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
        val items = remember { mutableListOf<PrefectureClusterItem>() }
        Prefecture.values().forEach {
            items.add(PrefectureClusterItem(it))
        }

        val clusterManager = rememberClusterManager<PrefectureClusterItem>() ?: return@GoogleMap
        clusterManager.setOnClusterClickListener {
            // クラスタータップ時に何かする場合はここに実装
            true
        }
        clusterManager.setOnClusterItemClickListener {
            // マーカータップ時に何かする場合はここに実装
            true
        }

        val renderer = rememberClusterRenderer(
            // クラスタリング時の見た目を指定可能。null指定でデフォルトとなる
            clusterContent = null,
            // MarkerComposableのContentをそのまま実装する
            clusterItemContent = { clusterItem ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CallOut(clusterItem.title)
                    Spacer(modifier = Modifier.height(4.dp))
                    Image(
                        modifier = Modifier.size(45.dp, 30.dp),
                        painter = painterResource(clusterItem.prefecture.flag),
                        contentDescription = null,
                    )
                }
            },
            clusterManager = clusterManager,
        )
        if (clusterManager.renderer != renderer) {
            clusterManager.renderer = renderer ?: return@GoogleMap
        }

        Clustering(
            items = items,
            clusterManager = clusterManager,
        )
    }
}
