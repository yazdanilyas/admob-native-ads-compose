package com.yi.compose.nativeads

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.yi.compose.nativeads.ui.theme.NativeAdsComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NativeAdsComposeTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    ScreenContent()
                }
            }
        }
    }
}

@Composable
fun ScreenContent() {
    NativeAdWithMedia(
        LocalContext.current,
        "ca-app-pub-3940256099942544/2247696110",
        R.layout.native_ad_with_media
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NativeAdsComposeTheme {
        ScreenContent()
    }
}