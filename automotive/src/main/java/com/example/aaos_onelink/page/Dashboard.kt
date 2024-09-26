package com.example.aaos_onelink.page

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.aaos_onelink.R
import com.example.aaos_onelink.components.MapActivity
import com.example.aaos_onelink.database.DataViewModel
import com.example.aaos_onelink.database.DynamicInformationEntity
import com.example.aaos_onelink.database.FixedInformationEntity
import com.example.aaos_onelink.database.UserInformationEntity
import com.example.aaos_onelink.ui.theme.BackgroundColor_def
import com.example.aaos_onelink.ui.theme.dpx
import com.example.aaos_onelink.ui.theme.dpy
import com.example.aaos_onelink.ui.theme.provider
import com.example.aaos_onelink.ui.theme.spt
import org.mapsforge.core.model.LatLong

@Composable
fun DashboardPage(serialDataViewModel: DataViewModel, userSetting: UserInformationEntity) {
    val fixedInfoData by serialDataViewModel.getLastFixedInformation.collectAsState(initial = FixedInformationEntity(
        devcode = "",
        TimeStamp = "",
        TripMark = "",
        AccStatus = 0,
        AlmID = "",
        ValidByte = 0,
        Latitude = "0",
        Longitude = "0",
        Altitude = "",
        Satellites = "",
        Speed = "",
        Direction = "",
        PDOP = "",
        VehicleID = "",
        ObdProType = 0,
        MilesType = 0,
        FuelType = 0
    )
    )
    val dynamicInfoData by serialDataViewModel.getLastDynamicInformation.collectAsState(initial = DynamicInformationEntity(
        d_0001 = "",
        d_0002 = "",
        d_0003 = "",
        d_0004 = "",
        d_0005 = "",
        d_0010 = "",
        d_0012 = "",
        d_0013 = "",
        d_0014 = 0,
        d_0015 = "",
        d_0016 = "",
        d_3010 = "",
        d_3012 = "",
        d_3013 = "",
        d_3014 = "",
        d_3015 = "",
        d_3016 = "",
        d_3017 = 0,
        d_3018 = "",
        d_3019 = "",
        d_3020 = 0,
        d_301A = "",
        d_9010 = 0,
        d_60C0 = "",
        d_60D0 = 0,
        d_62F0 = "",
        d_6050 = 0,
        d_6490 = 0,
        d_6010 = 0,
        d_5001 = 0,
        d_5002 = 0,
        d_5003 = 0,
        d_5004 = 0,
        d_5005 = "",
        d_5006 = "",
        d_5007 = "",
        d_5008 = 0,
        d_5009 = 0,
        d_500A = 0,
        d_500B = 0,
        d_500C = 0,
        d_500D = 0,
        d_500E = "",
        d_500F = 0,
        d_5010 = "",
        d_5011 = "",
        d_5012 = "",
        d_5013 = "",
        d_5014 = "",
        d_5015 = "",
        d_5016 = "",
        d_5017 = "",
        d_5019 = "",
        d_501A = "",
        d_501B = 0,
        d_501C = "",
        d_501D = "",
        d_501E = "",
        d_501F = "",
        d_5020 = "",
        d_5030 = "",
        d_5041 = ""
    )
    )
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dpx, end = 24.dpx, bottom = 24.dpy)
    )
    {
        MapViewComponent(fixedInfoData)
        Box(
            modifier = Modifier
                .width(20.dpx)
                .height(1.dpy)
        )
        Column {
            var speed = 0f
            try {
                speed = dynamicInfoData?.d_60D0?.toFloat() ?: 0f
            } catch(_: Exception){}
            OdoMeterComponent( speed, fixedInfoData?.TripMark ?: "", userSetting.speedLimit.toString() , dynamicInfoData)
            Row(
                modifier = Modifier
                    .padding(top = 20.dpy)
                    .fillMaxWidth()
            )
            {
                Box(modifier = Modifier.fillMaxWidth(0.5f))
                {
                    var fuelString = dynamicInfoData?.d_62F0 ?: "0"
                    if (fuelString.isEmpty()) fuelString = "100"
                    var fuel = fuelString.toFloat()
                    fuel /= 10f
                    InfoCard(R.drawable.gas_station, fuel.toString() , "%")
                }
                Box(modifier = Modifier
                    .width(20.dpx)
                    .height(1.dpy))
                Box(modifier = Modifier.fillMaxWidth(1f))
                {
                    var oilString = dynamicInfoData?.d_5019 ?: "0"
                    if (oilString.isEmpty()) oilString = "0"
                    var oil = oilString.toFloat()
                    oil /= 1000f
                    InfoCard(R.drawable.oil_tank, oil.toString(), userSetting.fuelConsumption)
                }
            }
        }
    }
}

@Composable
fun MapViewComponent(fixedInfoData: FixedInformationEntity?) {

    var currentLocation = LatLong(13.736717,100.523186)
    try {
        val lat = fixedInfoData?.Latitude?.toDouble()?.div(1000000.0)
        val long = fixedInfoData?.Longitude?.toDouble()?.div(1000000.0)
        if (-90 <= lat!! && lat <= 90 && -180 <= long!! && long <= 180)
            currentLocation = LatLong(lat, long)
    } catch (_:Exception){}

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(706.dpx)
            .clip(
                RoundedCornerShape(32.dpx)
            )
            .background(BackgroundColor_def)
    )
    {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                MapActivity(context, null, currentLocation)
            },
            update = { mapView ->
                mapView.updateCurrentLocation(currentLocation)
                mapView.setCenter(currentLocation)
            }
        )
    }
}

@Composable
fun OdoMeterComponent(speed: Float, tripMark: String, speedLimit: String, dynamicInfoData: DynamicInformationEntity?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(467.dpy)
            .clip(
                RoundedCornerShape(32.dpx)
            )
            .background(Color(0xFF111926))
            .padding(top = 31.dpy),
        contentAlignment = Alignment.TopCenter
    )
    {
        CircularSpeedIndicator(speed)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 270.dpy), horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Row (verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.demo_info_dashboard),
                    "",
                    modifier = Modifier
                        .height(32.dpy)
                        .width(32.dpx)
                )
                Text(
                    text = tripMark,
                    color = Color.White,
                    fontFamily = FontFamily(
                        androidx.compose.ui.text.googlefonts.Font(
                            googleFont = GoogleFont("Inter"),
                            fontProvider = provider,
                        )
                    ),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 32.spt,
                    modifier = Modifier.padding(horizontal = 10.dpx)
                )
                Box (contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.speedlimit),
                        "",
                        modifier = Modifier
                            .height(48.dpy)
                            .width(48.dpx)
                    )
                    Text(
                        text = speedLimit,
                        color = Color.Black,
                        fontFamily = FontFamily(
                            androidx.compose.ui.text.googlefonts.Font(
                                googleFont = GoogleFont("Inter"),
                                fontProvider = provider,
                            )
                        ),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 24.spt,
                        style = TextStyle(
                            letterSpacing = 1.sp
                        )
                    )
                }
            }

            Box(modifier = Modifier.height(35.dpy))
            Box(contentAlignment = Alignment.Center)
            {
//                Image(
//                    painter = painterResource(id = R.drawable.demo_info_dashboard_2),
//                    "",
//                    modifier = Modifier
//                        .height(60.dpy)
//                        .width(358.dpx)
//                )
                var num = 0
                try {
                    num = dynamicInfoData?.d_5014?.toInt() ?: 0
                } catch (_: Exception) {
                }
                OdoTextMeter(num)
            }
        }
    }
}

@Composable
fun InfoCard(icon: Int, value: String, mesure: String) {
    Box(modifier = Modifier.padding(top = 20.dpy))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(467.dpy)
            .clip(
                RoundedCornerShape(32.dpx)
            )
            .background(Color(0xFF111926)),
        contentAlignment = Alignment.Center
    )
    {
        Column(horizontalAlignment = Alignment.CenterHorizontally)
        {
            Image(
                painter = painterResource(id = icon),
                "",
                modifier = Modifier
                    .height(48.dpy)
                    .width(56.dpx)
            )
            Text(
                text = value,
                color = Color.White,
                fontFamily = FontFamily(
                    androidx.compose.ui.text.googlefonts.Font(
                        googleFont = GoogleFont("Inter"),
                        fontProvider = provider,
                    )
                ),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 48.spt,
                modifier = Modifier.padding(top = 20.dpy, bottom = 4.dpy)
            )
            Text(
                text = mesure,
                color = Color(0x40FFFFFF),
                fontFamily = FontFamily(
                    androidx.compose.ui.text.googlefonts.Font(
                        googleFont = GoogleFont("Inter"),
                        fontProvider = provider,
                    )
                ),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.spt,
            )
        }
    }
}

@Composable
fun CircularSpeedIndicator(value: Float) {
    Box(
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.speed_bg), "", modifier = Modifier
                .height(260.dpy)
                .width(344.dpx)
        )
        Canvas(
            modifier = Modifier
                .height(344.dpy)
                .width(344.dpx),
        ) {
            drawArcs(size / 1.15f, 45f, value)
        }
        Image(
            painter = painterResource(id = R.drawable.speed_fg), "", modifier = Modifier
                .height(260.dpy)
                .width(344.dpx)
        )
        Column(
            modifier = Modifier
                .height(260.dpy)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(
                text = value.toInt().toString(),
                modifier = Modifier.padding(top = 80.dpy),
                color = Color.White,
                fontFamily = FontFamily(
                    androidx.compose.ui.text.googlefonts.Font(
                        googleFont = GoogleFont("Inter"),
                        fontProvider = provider,
                    )
                ),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 60.spt,
            )
            Text(
                text = "km/h",
                color = Color.White,
                fontFamily = FontFamily(
                    androidx.compose.ui.text.googlefonts.Font(
                        googleFont = GoogleFont("Inter"),
                        fontProvider = provider,
                    )
                ),
                fontWeight = FontWeight.Medium,
                fontSize = 16.spt,
            )
        }
    }
}

fun DrawScope.drawArcs(componentSize: Size, indicatorStrokeWidth: Float, value: Float) {

    fun drawStroke() {
        drawArc(
            brush = Brush.sweepGradient(
                listOf(
                    Color(0x00132C49),
                    Color(0xFF2E7FDC)
                )
            ),
            startAngle = 150f,
            sweepAngle = value / 200f * 240f,
            useCenter = false,
            size = componentSize,
            style = Stroke(
                width = indicatorStrokeWidth,
                cap = StrokeCap.Butt
            ),
            topLeft = Offset(
                x = 22.5f,
                y = 22.5f
            )
        )
    }

    drawStroke()
}

@Composable
fun OdoTextMeter(num: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        val value = 10000000 + num / 100 % 1000000
        val tex = value.toString()
        TextInBoxOdoStart(tex[1])
        TextInBoxOdo(tex[2])
        TextInBoxOdo(tex[3])
        TextInBoxOdo(tex[4])
        TextInBoxOdo(tex[5])
        TextInBoxOdo(tex[6])
        TextInBoxOdoDot()
        TextInBoxOdoEnd(tex[7])
    }
}

@Composable
fun TextInBoxOdo(value: Char) {
    Box(modifier = Modifier.background(Color(0xFF202532)), contentAlignment = Alignment.CenterStart)
    {
        Box(modifier = Modifier
            .width(2.dpx)
            .height(24.dpy)
            .background(Color(0xFF414D66)))
        Row(
            modifier = Modifier
                .width(48.dpx)
                .height(54.dpy),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(
                text = value.toString(),
                color = Color.White,
                fontFamily = FontFamily(
                    androidx.compose.ui.text.googlefonts.Font(
                        googleFont = GoogleFont("Inter"),
                        fontProvider = provider,
                    )
                ),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.spt,
            )
        }
    }
}

@Composable
fun TextInBoxOdoStart(value: Char) {
    Box(modifier = Modifier
        .clip(RoundedCornerShape(topStart = 8.dpx, bottomStart = 8.dpx))
        .background(Color(0xFF202532)), contentAlignment = Alignment.Center)
    {
        Row(
            modifier = Modifier
                .width(48.dpx)
                .height(54.dpy),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(
                text = value.toString(),
                color = Color.White,
                fontFamily = FontFamily(
                    androidx.compose.ui.text.googlefonts.Font(
                        googleFont = GoogleFont("Inter"),
                        fontProvider = provider,
                    )
                ),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.spt,
            )
        }
    }
}

@Composable
fun TextInBoxOdoEnd(value: Char) {
    Box(modifier = Modifier
        .clip(RoundedCornerShape(topEnd = 8.dpx, bottomEnd = 8.dpx))
        .background(Color(0xFF202532)), contentAlignment = Alignment.Center)
    {
        Row(
            modifier = Modifier
                .width(48.dpx)
                .height(54.dpy),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(
                text = value.toString(),
                color = Color.White,
                fontFamily = FontFamily(
                    androidx.compose.ui.text.googlefonts.Font(
                        googleFont = GoogleFont("Inter"),
                        fontProvider = provider,
                    )
                ),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.spt,
            )
        }
    }
}

@Composable
fun TextInBoxOdoDot() {
    Box(modifier = Modifier
        .background(Color(0xFF202532)), contentAlignment = Alignment.Center)
    {
        Row(
            modifier = Modifier
                .width(24.dpx)
                .height(54.dpy),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(
                text = ".",
                color = Color.White,
                fontFamily = FontFamily(
                    androidx.compose.ui.text.googlefonts.Font(
                        googleFont = GoogleFont("Inter"),
                        fontProvider = provider,
                    )
                ),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.spt,
            )
        }
    }
}