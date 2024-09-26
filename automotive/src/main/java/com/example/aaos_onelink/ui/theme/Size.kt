package com.example.aaos_onelink.ui.theme

import android.util.DisplayMetrics
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

const val baseX = 1280f
const val baseY = 800f
const val currentX = 1024f
const val currentY = 600f
const val sptTransform = 0.7f

@Stable
inline val Int.dpx: Dp get() = Dp(this.toFloat()* currentX/baseX)

@Stable
inline val Double.dpx: Dp get() = Dp(this.toFloat()* currentX/baseX)

@Stable
inline val Float.dpx: Dp get() = Dp(this* currentX/baseX)

@Stable
inline val Int.dpy: Dp get() = Dp(this.toFloat() * currentY / baseY)

@Stable
inline val Double.dpy: Dp get() = Dp(this.toFloat() * currentY / baseY)

@Stable
inline val Float.dpy: Dp get() = Dp(this * currentY / baseY)

@Stable
val Int.spt: TextUnit get() = this.toFloat().sp * sptTransform

@Stable
val Double.spt: TextUnit get() = this.toFloat().sp * sptTransform

@Stable
val Float.spt: TextUnit get() = this.toFloat().sp * sptTransform