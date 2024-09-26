package com.example.aaos_onelink.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.aaos_onelink.ui.theme.dpx
import com.example.aaos_onelink.ui.theme.dpy

@Composable
fun RoundedCard(modifier: Modifier, content: @Composable () -> Unit) {
    Box(modifier = modifier)
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(32.dpx)
                )
        )
        {
            content()
        }
    }
}