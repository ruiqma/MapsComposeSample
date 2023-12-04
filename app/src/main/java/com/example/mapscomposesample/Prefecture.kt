package com.example.mapscomposesample

import androidx.annotation.DrawableRes

enum class Prefecture(
    val latitude: Double,
    val longitude: Double,
    @DrawableRes val flag: Int
) {
    Tokyo(35.689501, 139.691722, R.drawable.flag_of_tokyo_metropolis),
    Kanagawa(35.447734, 139.642537, R.drawable.flag_of_kanagawa_prefecture),
    Saitama(35.857033, 139.649012, R.drawable.flag_of_saitama_prefecture),
    Chiba(35.604560, 140.123154, R.drawable.flag_of_chiba_prefecture),
    Ibaraki(36.341737, 140.446824, R.drawable.flag_of_ibaraki_prefecture),
    Tochigi(36.565912, 139.883592, R.drawable.flag_of_tochigi_prefecture),
    Gunma(36.390688, 139.060453, R.drawable.flag_of_gunma_prefecture),
}
