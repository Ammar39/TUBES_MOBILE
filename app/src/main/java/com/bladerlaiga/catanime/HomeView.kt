package com.bladerlaiga.catanime

import android.content.res.Configuration
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bladerlaiga.catanime.models.GridType
import com.bladerlaiga.catanime.models.HomeViewModel
import com.bladerlaiga.catanime.ui.components.ScaleAndAlphaArgs
import com.bladerlaiga.catanime.ui.components.calculateDelayAndEasing
import com.bladerlaiga.catanime.ui.components.scaleAndAlpha
import com.bladerlaiga.catanime.ui.theme.CataNimeTheme
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch

const val title = "Home"

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun HomeContent() {
  val app_name = stringResource(id = R.string.app_name)
  val viewModel = viewModel<HomeViewModel>()
  val coroutineScope = rememberCoroutineScope()
  Surface(
    modifier = Modifier.fillMaxSize(),
    color = MaterialTheme.colors.background
  ) {
    val scaffoldState = rememberScaffoldState()
    val drawerState = scaffoldState.drawerState
    Scaffold(
      scaffoldState = scaffoldState,
      topBar = {
        TopAppBar(
          title = { Text(title) },
          navigationIcon = {
            IconButton(onClick = {
              coroutineScope.launch {
                if (drawerState.isClosed) {
                  drawerState.open()
                } else {
                  drawerState.close()
                }
              }
            }) {
              Icon(Icons.Filled.Menu, contentDescription = null)
            }
          },
          actions = {
            IconButton(onClick = { }) {
              Icon(Icons.Filled.Search, contentDescription = "")
            }
            IconButton(
              onClick = {
                viewModel.rotate()
              }
            ) {
              Icon(
                painter = painterResource(id = viewModel.grid_id),
                contentDescription = null
              )
            }
          }
        )
      },
      drawerContent = {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
          horizontalArrangement = Arrangement.Center
        ) {
          Image(
            painter = painterResource(id = R.drawable.proto_v2),
            modifier = Modifier.size(32.dp),
            contentDescription = null
          )
          Text(
            app_name,
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
          )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column() {
          ListItem(
            text = { Text(title) },
            icon = {
              Icon(
                Icons.Filled.Home,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
              )
            },
            modifier = Modifier.clickable { }
          )
          ListItem(
            text = { Text("Favorite") },
            icon = {
              Icon(
                Icons.Filled.Favorite,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
              )
            },
            modifier = Modifier.clickable { }
          )
          ListItem(
            text = { Text("Coming Soon") },
            icon = {
              Icon(
                Icons.Filled.Star,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
              )
            },
            modifier = Modifier.clickable { }
          )
        }
      },
      floatingActionButton = {
        FloatingActionButton(
          modifier = Modifier.clickable { },
          onClick = {
            Route.Edit.navigate()
          }
        ) {
          Icon(
            Icons.Filled.Add,
            contentDescription = null,
            modifier = Modifier
              .size(24.dp)
          )
        }
      }
    ) {
      if (viewModel.data.isEmpty()) {
        Box(
          contentAlignment = Alignment.Center,
          modifier = Modifier.fillMaxSize()
        ) {
          CircularProgressIndicator(
            modifier = Modifier
          )
        }
        LaunchedEffect(key1 = "load_data") {
          launch {
            viewModel.loadFromNetwork()
          }
        }
      }
      Column() {
        val lazyListState = rememberLazyListState()
        LazyVerticalGrid(
          cells = GridCells.Fixed(viewModel.grid_count),
          modifier = Modifier,
          contentPadding = PaddingValues(16.dp),
          verticalArrangement = Arrangement.spacedBy(16.dp),
          horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
          items(viewModel.data.size) { index ->
            val easing = lazyListState.calculateDelayAndEasing(index, viewModel.grid_count)
            val animation = tween<Float>(durationMillis = 240, delayMillis = 120, easing = easing)
            val args = ScaleAndAlphaArgs(fromScale = 2f, toScale = 1f, fromAlpha = 0f, toAlpha = 1f)
            val (scale, alpha) = scaleAndAlpha(args = args, animation = animation)
            val data = viewModel.data[index]
            Card(
              elevation = 4.dp,
              modifier = Modifier
                .graphicsLayer(alpha = alpha, scaleX = scale, scaleY = scale)
                .clickable {
                  Route.Detail.navigate(data.id)
                },
            ) {
              if (viewModel.grid_type == GridType.List) {
                Row() {
                  GlideImage(
                    imageModel = data.image_url,
                    modifier = Modifier.fillParentMaxWidth(0.3F),
                    contentScale = ContentScale.Crop,
                    circularReveal = CircularReveal(duration = 280),
                    placeHolder = painterResource(id = R.drawable.ic_outline_image_24),
                    error = painterResource(id = R.drawable.ic_outline_broken_image_24)
                  )
                  Column(
                    modifier = Modifier
                      .fillParentMaxWidth(0.7F)
                      .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                  ) {
                    Text(
                      text = "Title : ${data.title}",
                      fontSize = 16.sp,
                      textAlign = TextAlign.Left,
                      maxLines = 2,
                      softWrap = true,
                      modifier = Modifier
                        .fillMaxWidth()
                    )
                    Text(
                      text = "Genre : ${data.genres.joinToString { it.name }}",
                      fontSize = 16.sp,
                      textAlign = TextAlign.Left,
                      maxLines = 2,
                      softWrap = true,
                      modifier = Modifier
                        .fillMaxWidth()
                    )
                    Text(
                      text = "Episode : ${data.episodes}",
                      fontSize = 16.sp,
                      textAlign = TextAlign.Left,
                      maxLines = 1,
                      softWrap = true,
                      modifier = Modifier
                        .fillMaxWidth()
                    )
                    Text(
                      text = "Source : ${data.source}",
                      fontSize = 16.sp,
                      textAlign = TextAlign.Left,
                      maxLines = 1,
                      softWrap = true,
                      modifier = Modifier
                        .fillMaxWidth()
                    )
                  }
                }
              } else {
                Column(
                  verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                  GlideImage(
                    imageModel = data.image_url,
                    modifier = Modifier.fillParentMaxWidth(),
                    contentScale = ContentScale.Crop,
                    circularReveal = CircularReveal(duration = 280),
                    placeHolder = painterResource(id = R.drawable.ic_outline_image_24),
                    error = painterResource(id = R.drawable.ic_outline_broken_image_24)
                  )
                  Text(
                    text = data.title,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    softWrap = true,
                    modifier = Modifier
                      .fillMaxWidth()
                      .padding(bottom = 16.dp)
                  )
                }
              }
            }
          }
        }
        Box(
          contentAlignment = Alignment.Center,
          modifier = Modifier.fillMaxWidth()
        ) {
          IconButton(onClick = {
            coroutineScope.launch {
              viewModel.loadMoreFromNetwork()
            }
          }) {
            Icon(
              Icons.Filled.MoreVert,
              contentDescription = null,
              modifier = Modifier
                .size(24.dp)
            )
          }
        }
      }
    }
  }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Preview(
  uiMode = Configuration.UI_MODE_NIGHT_YES,
  showBackground = true,
  name = "Dark Mode"
)
@Preview(showBackground = true)
@Composable
fun HomePreview() {
  CataNimeTheme {
    HomeContent()
  }
}