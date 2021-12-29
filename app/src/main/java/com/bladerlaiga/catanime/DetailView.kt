package com.bladerlaiga.catanime

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bladerlaiga.catanime.models.DetailViewModel
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DetailContent(id: Long) {
  val viewModel = viewModel<DetailViewModel>()
  val scaffoldState = rememberScaffoldState()
  val coroutineScope = rememberCoroutineScope()
  var loaded by remember { mutableStateOf(false) }
  Surface(
    modifier = Modifier.fillMaxSize(),
    color = MaterialTheme.colors.background
  ) {
    Scaffold(
      scaffoldState = scaffoldState,
      modifier = Modifier,
      topBar = {
        TopAppBar(
          title = {
            Text(text = viewModel.title)
          },
          navigationIcon = {
            IconButton(onClick = { Route.navigator.back() }) {
              Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
          }
        )
      }
    ) {
      val scrollState = rememberScrollState()
      if (!loaded) {
        Box(
          contentAlignment = Alignment.Center,
          modifier = Modifier.fillMaxSize()
        ) {
          CircularProgressIndicator(
            modifier = Modifier
          )
        }
        coroutineScope.launch {
          try {
            viewModel.loadFromNetwork(id)
            loaded = true;
          } catch (e: Exception) {
            loaded = false;
            scaffoldState.snackbarHostState.showSnackbar("Data Cannot Load")
          }
        }
      } else {
        Column(
          modifier = Modifier
            .verticalScroll(state = scrollState)
            .padding(16.dp),
          verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
          Card() {
            Column(
              modifier = Modifier
                .padding(16.dp),
              verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
              GlideImage(
                imageModel = viewModel.image,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop,
                circularReveal = CircularReveal(duration = 60),
                placeHolder = painterResource(id = R.drawable.ic_outline_image_24),
                error = painterResource(id = R.drawable.ic_outline_broken_image_24)
              )
              Text(
                text = viewModel.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
                maxLines = 2,
                softWrap = true,
                modifier = Modifier
                  .fillMaxWidth()
              )
            }
          }
          Card() {
            Column(
              modifier = Modifier
                .padding(16.dp),
              verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Text(
                text = "Detail Information",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
                maxLines = 2,
                softWrap = true,
                modifier = Modifier
                  .fillMaxWidth()
              )
              Divider()
              Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
              ) {
                Text(
                  text = "Alternative Title :",
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Bold,
                  textAlign = TextAlign.Left,
                )
                Text(
                  text = viewModel.title_alt,
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Normal,
                  textAlign = TextAlign.Left,
                  maxLines = 2,
                  softWrap = true,
                  modifier = Modifier
                    .fillMaxWidth()
                )
              }
              Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
              ) {
                Text(
                  text = "Episode :",
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Bold,
                  textAlign = TextAlign.Left,
                )
                Text(
                  text = viewModel.episode,
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Normal,
                  textAlign = TextAlign.Left,
                  maxLines = 2,
                  softWrap = true,
                  modifier = Modifier
                    .fillMaxWidth()
                )
              }
              Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
              ) {
                Text(
                  text = "Status :",
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Bold,
                  textAlign = TextAlign.Left,
                )
                Text(
                  text = viewModel.status,
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Normal,
                  textAlign = TextAlign.Left,
                  maxLines = 2,
                  softWrap = true,
                  modifier = Modifier
                    .fillMaxWidth()
                )
              }
              Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
              ) {
                Text(
                  text = "Aired :",
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Bold,
                  textAlign = TextAlign.Left,
                )
                Text(
                  text = viewModel.aired,
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Normal,
                  textAlign = TextAlign.Left,
                  maxLines = 2,
                  softWrap = true,
                  modifier = Modifier
                    .fillMaxWidth()
                )
              }
              Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
              ) {
                Text(
                  text = "Premiered :",
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Bold,
                  textAlign = TextAlign.Left,
                )
                Text(
                  text = viewModel.premiered,
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Normal,
                  textAlign = TextAlign.Left,
                  maxLines = 2,
                  softWrap = true,
                  modifier = Modifier
                    .fillMaxWidth()
                )
              }
              Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
              ) {
                Text(
                  text = "Source :",
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Bold,
                  textAlign = TextAlign.Left,
                )
                Text(
                  text = viewModel.source,
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Normal,
                  textAlign = TextAlign.Left,
                  maxLines = 2,
                  softWrap = true,
                  modifier = Modifier
                    .fillMaxWidth()
                )
              }
              Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
              ) {
                Text(
                  text = "Producers :",
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Bold,
                  textAlign = TextAlign.Left,
                )
                Text(
                  text = viewModel.producer,
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Normal,
                  textAlign = TextAlign.Left,
                  maxLines = 2,
                  softWrap = true,
                  modifier = Modifier
                    .fillMaxWidth()
                )
              }
              Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
              ) {
                Text(
                  text = "Studios :",
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Bold,
                  textAlign = TextAlign.Left,
                )
                Text(
                  text = viewModel.premiered,
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Normal,
                  textAlign = TextAlign.Left,
                  maxLines = 2,
                  softWrap = true,
                  modifier = Modifier
                    .fillMaxWidth()
                )
              }
              Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
              ) {
                Text(
                  text = "Genres :",
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Bold,
                  textAlign = TextAlign.Left,
                )
                Text(
                  text = viewModel.genre,
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Normal,
                  textAlign = TextAlign.Left,
                  maxLines = 2,
                  softWrap = true,
                  modifier = Modifier
                    .fillMaxWidth()
                )
              }
              Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
              ) {
                Text(
                  text = "Themes :",
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Bold,
                  textAlign = TextAlign.Left,
                )
                Text(
                  text = viewModel.theme,
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Normal,
                  textAlign = TextAlign.Left,
                  maxLines = 2,
                  softWrap = true,
                  modifier = Modifier
                    .fillMaxWidth()
                )
              }
              Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
              ) {
                Text(
                  text = "Duration :",
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Bold,
                  textAlign = TextAlign.Left,
                )
                Text(
                  text = viewModel.duration,
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Normal,
                  textAlign = TextAlign.Left,
                  maxLines = 2,
                  softWrap = true,
                  modifier = Modifier
                    .fillMaxWidth()
                )
              }
              Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
              ) {
                Text(
                  text = "Rating :",
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Bold,
                  textAlign = TextAlign.Left,
                )
                Text(
                  text = viewModel.rating,
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Normal,
                  textAlign = TextAlign.Left,
                  maxLines = 2,
                  softWrap = true,
                  modifier = Modifier
                    .fillMaxWidth()
                )
              }
            }
          }
        }
      }
    }
  }
}

/*
@Preview(
  uiMode = Configuration.UI_MODE_NIGHT_YES,
  showBackground = true,
  name = "Dark Mode"
)
@Preview(showBackground = true)
@Composable
fun DetailPreview() {
  CataNimeTheme {
    DetailContent()
  }
}*/
