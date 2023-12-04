package com.example.mapscomposesample

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class PrefectureClusterItem(
    val prefecture: Prefecture,
) : ClusterItem {
    override fun getPosition(): LatLng = LatLng(prefecture.latitude, prefecture.longitude)

    override fun getTitle(): String = prefecture.name

    override fun getSnippet(): String = ""

    override fun getZIndex(): Float = 0f
}
