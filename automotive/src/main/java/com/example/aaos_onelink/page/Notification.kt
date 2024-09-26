package com.example.aaos_onelink.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.example.aaos_onelink.R
import com.example.aaos_onelink.components.RoundedCard
import com.example.aaos_onelink.database.AlarmEntity
import com.example.aaos_onelink.database.DataViewModel
import com.example.aaos_onelink.ui.theme.dpx
import com.example.aaos_onelink.ui.theme.dpy
import com.example.aaos_onelink.ui.theme.provider
import com.example.aaos_onelink.ui.theme.spt

@Composable
fun NotificationPage(
    serialDataViewModel: DataViewModel,
    allAlarm: State<List<AlarmEntity>?>,
    type1Alarm: State<List<AlarmEntity>?>,
    type2Alarm: State<List<AlarmEntity>?>,
    type3Alarm: State<List<AlarmEntity>?>,
    type4Alarm: State<List<AlarmEntity>?>
)
{
    val notiType = remember { mutableIntStateOf(1) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dpx), horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        RoundedCard(modifier = Modifier.fillMaxWidth())
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF111926))
            )
            {
                Row(
                    modifier = Modifier.padding(10.dpx),
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                {
                    NotificationTypeButton(
                        notiType.intValue == 1,
                        onChangeAction = { notiType.intValue = 1 },
                        icon = R.drawable.alert,
                        title = "All",
                        modifier = Modifier.fillMaxWidth(0.2f)
                    )
                    NotificationTypeButton(
                        notiType.intValue == 2,
                        onChangeAction = { notiType.intValue = 2 },
                        icon = R.drawable.warning_alert,
                        title = "Type 1",
                        modifier = Modifier.fillMaxWidth(0.25f)
                    )
                    NotificationTypeButton(
                        notiType.intValue == 3,
                        onChangeAction = { notiType.intValue = 3 },
                        icon = R.drawable.alertt2,
                        title = "Type 2",
                        modifier = Modifier.fillMaxWidth(0.33f)
                    )
                    NotificationTypeButton(
                        notiType.intValue == 4,
                        onChangeAction = { notiType.intValue = 4 },
                        icon = R.drawable.alarmt3,
                        title = "Type 3",
                        modifier = Modifier.fillMaxWidth(0.5f)
                    )
                    NotificationTypeButton(
                        notiType.intValue == 5,
                        onChangeAction = { notiType.intValue = 5 },
                        icon = R.drawable.alarmt4,
                        title = "Type 4",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        var alarmList = listOf<AlarmEntity>()
        when (notiType.intValue) {
            1 -> alarmList = allAlarm.value!!
            2 -> alarmList = type1Alarm.value!!
            3 -> alarmList = type2Alarm.value!!
            4 -> alarmList = type3Alarm.value!!
            5 -> alarmList = type4Alarm.value!!
        }

        Column (modifier = Modifier.verticalScroll(rememberScrollState()))
        {
            for (alarm in alarmList.reversed()) {
                AlarmCard(alarm.type, alarm.message, alarm.time)
            }
        }
    }
}

@Composable
fun NotificationTypeButton(
    state: Boolean = false,
    onChangeAction: () -> Unit,
    icon: Int? = null,
    title: String,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    var buttonBg = Color(0xFF111926)
    if (state)
        buttonBg = Color(0xFF1A2434)

    Box(modifier = modifier)
    {
        TextButton(
            onClick = onChangeAction,
            interactionSource = remember { MutableInteractionSource() },
            shape = RoundedCornerShape(
                topStart = 32.dpx,
                topEnd = 32.dpx,
                bottomStart = 32.dpx,
                bottomEnd = 32.dpx
            ),
            contentPadding = PaddingValues(0.dpx),
            modifier = Modifier
                .fillMaxWidth()
        )
        {
            RoundedCard(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dpy)
                        .background(buttonBg),
                    contentAlignment = Alignment.Center
                )
                {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        if (icon != null)
                            Image(
                                painter = painterResource(id = icon),
                                contentDescription = "",
                                modifier = Modifier
                                    .height(32.dpy)
                                    .width(32.dpx)
                            )
                        Text(
                            text = title,
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
            }
        }
    }
}

@Composable
fun AlarmCard(type: Int, message: String, time: String) {
    var backgroundColor = Color(0x0E26ECA0)
    var icon = R.drawable.alert
    if (type == 1) {
        backgroundColor = Color(0x0EFFE460)
        icon = R.drawable.warning_alert
    }
    if (type == 2) {
        backgroundColor = Color(0x0E26ECA0)
        icon = R.drawable.alertt2
    }
    if (type == 3) {
        backgroundColor = Color(0x0EFC5151)
        icon = R.drawable.alarmt3
    }
    if (type == 4) {
        backgroundColor = Color(0x0E227ADF)
        icon = R.drawable.alarmt4
    }
    RoundedCard(
        modifier = Modifier
            .padding(start = 20.dpx, end = 20.dpx, top = 20.dpy)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .padding(20.dpx),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Row(
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "",
                    modifier = Modifier
                        .height(32.dpy)
                        .width(32.dpx)
                )
                Text(
                    text = message,
                    color = Color.White,
                    fontFamily = FontFamily(
                        androidx.compose.ui.text.googlefonts.Font(
                            googleFont = GoogleFont("Inter"),
                            fontProvider = provider,
                        )
                    ),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.spt,
                    modifier = Modifier.padding(start = 18.dpx)
                )
            }

            Text(
                text = time,
                color = Color.White,
                fontFamily = FontFamily(
                    androidx.compose.ui.text.googlefonts.Font(
                        googleFont = GoogleFont("Inter"),
                        fontProvider = provider,
                    )
                ),
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.spt
            )
        }
    }
}