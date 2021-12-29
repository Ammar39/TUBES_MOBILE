package com.bladerlaiga.catanime

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.bladerlaiga.catanime.ui.theme.CataNimeTheme
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CataNimeTheme {
        SplashContent() { }
      }
    }
  }
}

//@Composable
//fun SplashContent() {
//  val context = LocalContext.current
//  Surface(color = MaterialTheme.colors.primary) {
//    val scale = remember {
//      Animatable(0f)
//    }
//    LaunchedEffect(key1 = true) {
//      scale.animateTo(
//        targetValue = 0.7f,
//        animationSpec = tween(
//          durationMillis = 800,
//          easing = {
//            OvershootInterpolator(4f).getInterpolation(it)
//          })
//      )
//      delay(2000L)
//      context.startActivity(Intent(context, MainActivity::class.java))
//    }
//    Box(
//      contentAlignment = Alignment.Center,
//      modifier = Modifier.fillMaxSize()
//    ) {
//      Image(
//        painter = painterResource(id = R.drawable.proto_v2),
//        contentDescription = "Logo",
//        modifier = Modifier.scale(scale.value)
//      )
//    }
//  }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun SplashPreview() {
//  CataNimeTheme {
//    SplashContent()
//  }
//}