package com.yi.compose.nativeads

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView

@Composable
fun NativeAdWithMedia(context: Context, adId: String, layoutId: Int) {
    AndroidView(
        factory = { ctx ->
            val adLoader = AdLoader.Builder(context, adId)
                .forNativeAd { nativeAd ->
                    val adView = LayoutInflater.from(ctx)
                        .inflate(layoutId, null) as NativeAdView

                    // Populate the NativeAdView with the ad content
                    populateNativeAdView(nativeAd, adView)

                    adView
                }
                .withAdListener(object : AdListener() {
                    override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                        Log.e("NativeAd", "Ad failed to load: ${loadAdError.message}")
                    }
                }).build()

            adLoader.loadAd(AdRequest.Builder().build())
            FrameLayout(ctx)


        },
        modifier = Modifier.padding()
    )
}

fun populateNativeAdView(nativeAd: NativeAd, adView: NativeAdView) {
    val headlineView = adView.findViewById<TextView>(R.id.ad_headline)
    val bodyView = adView.findViewById<TextView>(R.id.ad_body)

    headlineView.text = nativeAd.headline
    adView.headlineView = headlineView

    nativeAd.body?.let {
        bodyView.text = it
        bodyView.visibility = View.VISIBLE
        adView.bodyView = bodyView
    } ?: run {
        bodyView.visibility = View.GONE
    }

    adView.setNativeAd(nativeAd)
}