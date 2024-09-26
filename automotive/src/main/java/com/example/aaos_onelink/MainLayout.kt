package com.example.aaos_onelink

import android.annotation.SuppressLint
import android.util.Log
import android_serialport_api.SerialPortFinder
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.dp
import com.example.aaos_onelink.connection.SerialCommunication
import com.example.aaos_onelink.database.DataViewModel
import com.example.aaos_onelink.database.UserInformationEntity
import com.example.aaos_onelink.page.BehaviorPage
import com.example.aaos_onelink.page.DashboardPage
import com.example.aaos_onelink.page.DataPage
import com.example.aaos_onelink.page.NotificationPage
import com.example.aaos_onelink.page.SettingPage
import com.example.aaos_onelink.ui.theme.BackgroundColor_def
import com.example.aaos_onelink.ui.theme.PrimaryColor_def
import com.example.aaos_onelink.ui.theme.SecondaryColor_def
import com.example.aaos_onelink.ui.theme.dpx
import com.example.aaos_onelink.ui.theme.dpy
import com.example.aaos_onelink.ui.theme.provider
import com.example.aaos_onelink.ui.theme.spt
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Const for 1280x600 px
val menuBar_max_width = 100.dpx
val button_menuBar_max_height = 100.dpy
val button_menuBar_icon_width = 32.dpx
val button_menuBar_icon_height = 32.dpy
val topBar_max_height = 62.dpy

@Composable
fun MainLayout(
    serialDataViewModel: DataViewModel
) {
    Row(modifier = Modifier.fillMaxSize())
    {
        val page = remember { mutableIntStateOf(1) }

        /* =========================================================================================
        *   Menu bar
        * */
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
                .width(menuBar_max_width)
                .background(SecondaryColor_def)
        )
        {
            MenuButton(
                page.intValue == 1,
                onChangeAction = { page.intValue = 1 },
                iconOn = R.drawable.menu_icon_1_on,
                iconOff = R.drawable.menu_icon_1_off
            )
            MenuButton(
                page.intValue == 2,
                onChangeAction = { page.intValue = 2 },
                iconOn = R.drawable.menu_icon_2_on,
                iconOff = R.drawable.menu_icon_2_off
            )
            MenuButton(
                page.intValue == 3,
                onChangeAction = { page.intValue = 3 },
                iconOn = R.drawable.menu_icon_3_on,
                iconOff = R.drawable.menu_icon_3_off
            )
            MenuButton(
                page.intValue == 4,
                onChangeAction = { page.intValue = 4 },
                iconOn = R.drawable.menu_icon_4_on,
                iconOff = R.drawable.menu_icon_4_off
            )
            MenuButton(
                page.intValue == 5,
                onChangeAction = { page.intValue = 5 },
                iconOn = R.drawable.menu_icon_5_on,
                iconOff = R.drawable.menu_icon_5_off
            )
        }

        /* =========================================================================================
        *   Main page
        * */
        Column(modifier = Modifier.fillMaxSize())
        {
            TopBar()
            Box(modifier = Modifier.fillMaxSize())
            {
                PageNavigationLayout(page.intValue, serialDataViewModel)
            }
        }
    }
}

@Composable
fun MenuButton(state: Boolean = false, onChangeAction: () -> Unit, iconOn: Int, iconOff: Int) {
    var buttonBg = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFF1A222F),
            Color(0xFF1A222F),
        )
    )
    if (state)
        buttonBg = Brush.horizontalGradient(
            colors = listOf(
                Color(0x002E7FDC),
                Color(0x1A378FF3)
            )
        )
    TextButton(
        onClick = onChangeAction,
        interactionSource = remember { MutableInteractionSource() },
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp
        ),
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .height(button_menuBar_max_height)
            .fillMaxWidth()
            .background(
                brush = buttonBg
            )
    )
    {
        Row(verticalAlignment = Alignment.CenterVertically)
        {
            Row(
                modifier = Modifier.width(96.dpx),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                if (state)
                    Image(
                        painter = painterResource(id = iconOn), "", modifier = Modifier
                            .height(button_menuBar_icon_height)
                            .width(button_menuBar_icon_width)
                    )
                else
                    Image(
                        painter = painterResource(id = iconOff), "", modifier = Modifier
                            .height(button_menuBar_icon_height)
                            .width(button_menuBar_icon_width)
                    )
            }
            if (state)
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(4.dpx)
                        .background(PrimaryColor_def)
                ) {}
            else
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(4.dpx)
                ) {}
        }
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(topBar_max_height),
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .background(BackgroundColor_def),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        )
        {
            Box(modifier = Modifier.padding(start = 30.dpx))
            {
                Image(
                    painter = painterResource(id = R.drawable.mobile_signal),
                    "",
                    modifier = Modifier
                        .height(28.dpy)
                        .width(28.dpx)
                )
            }
            Box(modifier = Modifier.padding(start = 30.dpx))
            {
                Image(
                    painter = painterResource(id = R.drawable.wifi_signal_4),
                    "",
                    modifier = Modifier
                        .height(28.dpy)
                        .width(28.dpx)
                )
            }
            Box(modifier = Modifier.padding(start = 30.dpx))
            {
                var formattedTime by remember { mutableStateOf("") }
                LaunchedEffect(Unit) { // Trigger the coroutine once
                    while (true) {
                        val currentDate = Date()
                        formattedTime =
                            SimpleDateFormat("HH:mm", Locale.getDefault()).format(currentDate)
                        delay(1000) // Update every second
                    }
                }

                Text(
                    text = formattedTime,
                    modifier = Modifier.padding(end = 20.dpx),
                    color = Color.White,
                    fontFamily = FontFamily(
                        androidx.compose.ui.text.googlefonts.Font(
                            googleFont = GoogleFont("Inter"),
                            fontProvider = provider,
                        )
                    ),
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.spt,
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .background(Color(0xFF0D121F)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        )
        {
            Box(modifier = Modifier.padding(end = 30.dpx))
            {
                Image(
                    painter = painterResource(id = R.drawable.rainny_icon),
                    "",
                    modifier = Modifier
                        .height(32.dpy)
                        .width(32.dpx)
                )
            }
            Text(
                text = "16℃",
                modifier = Modifier.padding(end = 20.dpx),
                color = Color.White,
                fontFamily = FontFamily(
                    androidx.compose.ui.text.googlefonts.Font(
                        googleFont = GoogleFont("Inter"),
                        fontProvider = provider,
                    )
                ),
                fontWeight = FontWeight.Bold,
                fontSize = 18.spt,
            )
        }
    }
}

@Composable
fun PageNavigationLayout(
    intValue: Int,
    serialDataViewModel: DataViewModel
) {
    val settingSaved by serialDataViewModel.getLastUserInformation.collectAsState(initial = UserInformationEntity(
        id = 0,
        fuelConsumption = "L/100km",
        speedLimit = 50,
        serialPort = "/dev/ttyS5"
    )
    )
    val userSetting = UserInformationEntity(
        fuelConsumption = settingSaved?.fuelConsumption ?: "L/100km",
        speedLimit = settingSaved?.speedLimit ?: 50,
        serialPort = settingSaved?.serialPort ?: "/dev/ttyS5"
    )
    // Serial communication
    val allPort = SerialPortFinder().allDevicesPath
    for (port in allPort)
    {
        val serialPortComm = SerialCommunication(userSetting.serialPort, 115200, serialDataViewModel)
        try {
            serialPortComm.open()
            Log.d("Serial", "Opened")
        } catch (e: Exception) {
            Log.d("Serial", "Error: ${e.message}")
        }
    }

    val allAlarm = serialDataViewModel.getAllAlarmInfo.collectAsState(initial = emptyList())
    val type1Alarm = serialDataViewModel.getAllAlarmInfoType1.collectAsState(initial = emptyList())
    val type2Alarm = serialDataViewModel.getAllAlarmInfoType2.collectAsState(initial = emptyList())
    val type3Alarm = serialDataViewModel.getAllAlarmInfoType3.collectAsState(initial = emptyList())
    val type4Alarm = serialDataViewModel.getAllAlarmInfoType4.collectAsState(initial = emptyList())

    Box(modifier = Modifier.fillMaxSize())
    {
        DashboardPage(serialDataViewModel, userSetting)

        if (intValue != 1)
            Row(modifier = Modifier.fillMaxSize().background(Color(0xFF0D121F))){}

        when (intValue) {
            2 -> BehaviorPage()
            3 -> DataPage(serialDataViewModel)
            4 -> NotificationPage(serialDataViewModel, allAlarm, type1Alarm, type2Alarm, type3Alarm, type4Alarm)
            5 -> SettingPage(userSetting, serialDataViewModel)
        }
    }
}
