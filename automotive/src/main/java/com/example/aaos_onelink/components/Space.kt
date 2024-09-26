package com.example.aaos_onelink.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aaos_onelink.ui.theme.dpx
import com.example.aaos_onelink.ui.theme.dpy

@Composable
fun SpaceX(length: Int, modifier: Modifier? = Modifier) {
    if (modifier != null) {
        Box(modifier = modifier)
        {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(length.dpx)
            )
        }
    } else
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(length.dpx)
        )
}

@Composable
fun SpaceY(length: Int, modifier: Modifier? = Modifier) {
    if (modifier != null) {
        Box(modifier = modifier)
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(length.dpy)
            )
        }
    } else
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(length.dpy)
        )
}