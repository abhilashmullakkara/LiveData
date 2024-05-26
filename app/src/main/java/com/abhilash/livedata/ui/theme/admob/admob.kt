package com.abhilash.livedata.ui.theme.admob

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import com.abhilash.livedata.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@SuppressLint("VisibleForTests")
@Composable
fun BannerAdView(isTest: Boolean = true ,banner:AdSize= AdSize.BANNER) {
    val unitId = if (isTest) stringResource(id = R.string.ad_mob_test_banner_id) else stringResource(
        id =R.string.ad_mob_banner_id  //ca-app-pub-9950046999139130/7284135590
    )
    AndroidView(
        factory = { context ->
            AdView(context).apply {
                setAdSize(banner)
                adUnitId = unitId
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}




