package com.bladerlaiga.catanime.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

private enum class State { PLACING, PLACED }

data class ScaleAndAlphaArgs(
  val fromScale: Float,
  val toScale: Float,
  val fromAlpha: Float,
  val toAlpha: Float
)

@Composable
fun scaleAndAlpha(
  args: ScaleAndAlphaArgs,
  animation: FiniteAnimationSpec<Float>
): Pair<Float, Float> {
  val transitionState = remember { MutableTransitionState(State.PLACING).apply { targetState = State.PLACED } }
  val transition = updateTransition(transitionState, label = "")
  val alpha by transition.animateFloat(transitionSpec = { animation }, label = "") { state ->
    when (state) {
      State.PLACING -> args.fromAlpha
      State.PLACED -> args.toAlpha
    }
  }
  val scale by transition.animateFloat(transitionSpec = { animation }, label = "") { state ->
    when (state) {
      State.PLACING -> args.fromScale
      State.PLACED -> args.toScale
    }
  }
  return alpha to scale
}

@Composable
fun LazyListState.calculateDelayAndEasing(index: Int, columnCount: Int): Easing {
  val row = index / columnCount
  val firstVisibleRow = firstVisibleItemIndex
  val visibleRows = layoutInfo.visibleItemsInfo.count()
  val scrollingToBottom = firstVisibleRow < row
  val isFirstLoad = visibleRows == 0
  return if (scrollingToBottom || isFirstLoad) LinearOutSlowInEasing else FastOutSlowInEasing
}