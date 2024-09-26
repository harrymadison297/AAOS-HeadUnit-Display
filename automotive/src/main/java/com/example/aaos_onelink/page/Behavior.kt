package com.example.aaos_onelink.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.example.aaos_onelink.R
import com.example.aaos_onelink.components.RoundedCard
import com.example.aaos_onelink.components.SpaceY
import com.example.aaos_onelink.ui.theme.GreenGradient
import com.example.aaos_onelink.ui.theme.SecondaryColor_def
import com.example.aaos_onelink.ui.theme.dpx
import com.example.aaos_onelink.ui.theme.dpy
import com.example.aaos_onelink.ui.theme.provider
import com.example.aaos_onelink.ui.theme.spt
import io.github.koalaplot.core.Symbol
import io.github.koalaplot.core.polar.PolarGraph
import io.github.koalaplot.core.polar.PolarGraphDefaults
import io.github.koalaplot.core.polar.PolarPlotSeries
import io.github.koalaplot.core.polar.PolarPoint
import io.github.koalaplot.core.polar.RadialGridType
import io.github.koalaplot.core.polar.rememberAngularValueAxisModel
import io.github.koalaplot.core.polar.rememberFloatRadialAxisModel
import io.github.koalaplot.core.util.AngularValue
import io.github.koalaplot.core.util.ExperimentalKoalaPlotApi
import io.github.koalaplot.core.util.toDegrees
import kotlin.random.Random

@Composable
fun BehaviorPage() {

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 24.dpy), horizontalArrangement = Arrangement.SpaceBetween
    )
    {
        // Column 1
        RoundedCard(
            modifier = Modifier
                .padding(start = 20.dpx, end = 10.dpx)
                .fillMaxWidth(0.5f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF111926))
            )
            {
                Box(modifier = Modifier.fillMaxWidth())
                {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dpy)
                            .background(SecondaryColor_def),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Image(
                                painter = painterResource(id = R.drawable.car_icon),
                                contentDescription = "",
                                modifier = Modifier
                                    .height(32.dpy)
                                    .width(32.dpx)
                            )
                            Text(
                                text = "Safety Driving",
                                color = Color.White,
                                fontFamily = FontFamily(
                                    androidx.compose.ui.text.googlefonts.Font(
                                        googleFont = GoogleFont("Inter"),
                                        fontProvider = provider,
                                    )
                                ),
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 22.spt,
                                modifier = Modifier.padding(start = 12.dpx)
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .padding(top = 20.dpy)
                            .fillMaxWidth()
                            .height(400.dpy), contentAlignment = Alignment.Center
                    )
                    {
                        val labels = listOf(
                            "Harsh Start",
                            "Exceeding RPM",
                            "Exceeding Speed",
                            "Sharp Turn",
                            "Harsh Brake",
                            "Harsh Acceleration"
                        )
                        ChartPlot(labels)
                    }
                    Box(
                        modifier = Modifier.padding(top = 386.dpy),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                            TaskBehavior(R.drawable.speedometer, "Exceeding Speed", "8/10")
                            TaskBehavior(R.drawable.speedometer_rpm, "Exceeding RPM", "7/10")
                            TaskBehavior(R.drawable.right_turn, "Sharp Turn", "9/10")
                            TaskBehavior(R.drawable.thunder, "Harsh Start", "7/10")
                            TaskBehavior(R.drawable.disc_brake, "Harsh Brake", "9/10")
                            TaskBehavior(R.drawable.on_time, "Harsh Acceleration", "10/10")
                        }
                    }
                }
                SpaceY(length = 4)
            }
        }

        // Column 2
        RoundedCard(
            modifier = Modifier
                .padding(start = 10.dpx, end = 20.dpx)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF111926))
            )
            {
                Box(modifier = Modifier.fillMaxWidth())
                {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dpy)
                            .background(SecondaryColor_def),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Image(
                                painter = painterResource(id = R.drawable.eco_icon),
                                contentDescription = "",
                                modifier = Modifier
                                    .height(32.dpy)
                                    .width(32.dpx)
                            )
                            Text(
                                text = "ECO Driving",
                                color = Color.White,
                                fontFamily = FontFamily(
                                    androidx.compose.ui.text.googlefonts.Font(
                                        googleFont = GoogleFont("Inter"),
                                        fontProvider = provider,
                                    )
                                ),
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 22.spt,
                                modifier = Modifier.padding(start = 12.dpx)
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(450.dpy), contentAlignment = Alignment.Center
                    )
                    {
                        val labels = listOf(
                            "RPM High Speed",
                            "Exhoust Brake Retarder",
                            "Long Idling",
                            "Shift Down & \nExceeding RPM",
                            "Shift Up & Exceeding RPM",
                            "RPM Low Speed"
                        )
                        ChartPlot(labels)
                    }
                    Box(
                        modifier = Modifier.padding(top = 386.dpy),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                            TaskBehavior(R.drawable.truck, "Long Idling", "8/10")
                            TaskBehavior(R.drawable.handbrake, "Shift Down & Exceeding RPM", "9/10")
                            TaskBehavior(
                                R.drawable.handbrake_up,
                                "Shift Up & Exceeding RPM",
                                "9/10"
                            )
                            TaskBehavior(R.drawable.brake_pad, "Exhoust Brake Retarder", "6/10")
                            TaskBehavior(R.drawable.low_speed, "RPM Low Speed", "10/10")
                            TaskBehavior(R.drawable.high_speed, "RPM High Speed", "8/10")
                        }
                    }
                }
                SpaceY(length = 4)
            }
        }

    }
}

@Composable
fun TaskBehavior(icon: Int, text: String, mark: String) {
    Box(
        modifier = Modifier
            .padding(horizontal = 24.dpx)
            .fillMaxWidth()
            .height(2.dpy)
            .background(Color(0x0AFFFFFF))
    )
    Box(modifier = Modifier.padding(horizontal = 24.dpx, vertical = 12.dpy))
    {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "",
                    modifier = Modifier
                        .height(40.dpy)
                        .width(40.dpx)
                )
                Text(
                    text = text,
                    color = Color.White,
                    fontFamily = FontFamily(
                        androidx.compose.ui.text.googlefonts.Font(
                            googleFont = GoogleFont("Inter"),
                            fontProvider = provider,
                        )
                    ),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.spt,
                    modifier = Modifier.padding(start = 12.dpx)
                )
            }
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
                    text = mark,
                    color = Color.White,
                    fontFamily = FontFamily(
                        androidx.compose.ui.text.googlefonts.Font(
                            googleFont = GoogleFont("Inter"),
                            fontProvider = provider,
                        )
                    ),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.spt,
                    modifier = Modifier.padding(start = 12.dpx)
                )
            }
        }
    }
}

@OptIn(ExperimentalKoalaPlotApi::class)
@Composable
fun ChartPlot(labels: List<String>) {
    val data1: List<PolarPoint<Float, AngularValue>> = buildList {
        for (i in 1..6) {
            add(PolarPoint(Random.nextFloat() * 2f + 3f, (0f + i * 60f).toDegrees()))
        }
    }

    PolarGraph(
        rememberFloatRadialAxisModel(List(6) { it.toFloat() }),
        rememberAngularValueAxisModel(
            listOf(
                0f.toDegrees(),
                60f.toDegrees(),
                120f.toDegrees(),
                180f.toDegrees(),
                240f.toDegrees(),
                300f.toDegrees()
            )
        ),
        angularAxisLabelText = { plotLabel(it, labels) },
        polarGraphProperties = PolarGraphDefaults.PolarGraphPropertyDefaults()
            .copy(RadialGridType.LINES),
        modifier = Modifier.fillMaxWidth()
    ) {
        PolarPlotSeries(data1, symbols = { Symbol(shape = CircleShape, fillBrush = GreenGradient) })
    }
}

fun plotLabel(angle: AngularValue, label: List<String>): String {
    val num = angle.toDegrees().value
    return when (num) {
        in 0.0..59.9 -> label[0]
        in 60.0..119.9 -> label[1]
        in 120.0..179.9 -> label[2]
        in 180.0..239.9 -> label[3]
        in 240.0..299.9 -> label[4]
        else -> label[5]
    }
}

















