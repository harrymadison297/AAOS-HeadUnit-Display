package com.example.aaos_onelink.page

import android.content.Context
import android.util.Log
import android_serialport_api.SerialPortFinder
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.example.aaos_onelink.components.RoundedCard
import com.example.aaos_onelink.database.DataStoreManager
import com.example.aaos_onelink.database.DataViewModel
import com.example.aaos_onelink.database.UserInformationEntity
import com.example.aaos_onelink.ui.theme.dpx
import com.example.aaos_onelink.ui.theme.dpy
import com.example.aaos_onelink.ui.theme.provider
import com.example.aaos_onelink.ui.theme.spt
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun SettingPage(userSetting: UserInformationEntity, serialDataViewModel: DataViewModel) {
    var expandedFuelConsumption by remember { mutableStateOf(false) }
    var expandedSpeedLimit by remember { mutableStateOf(false) }
    var expandedSerialPort by remember { mutableStateOf(false) }
    val allPort = SerialPortFinder().allDevicesPath

    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        // Fuel consumption config
        RoundedCard(
            modifier = Modifier
                .padding(start = 20.dpx, end = 20.dpx, top = 10.dpy)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF111926))
                    .padding(32.dpx),
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                Text(
                    text = "Fuel consumption unit",
                    modifier = Modifier.clickable { expandedFuelConsumption = true },
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

                Box {
                    Text(
                        text = userSetting.fuelConsumption,
                        modifier = Modifier.clickable { expandedFuelConsumption = true },
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
                    DropdownMenu(
                        expanded = expandedFuelConsumption,
                        onDismissRequest = { expandedFuelConsumption = false }
                    ) {
                        for (type in arrayOf("L/100km", "L/Hour")) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = type,
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
                                },
                                onClick = {
                                    val changeSetting = UserInformationEntity(
                                        fuelConsumption = type,
                                        speedLimit = userSetting.speedLimit,
                                        serialPort = userSetting.serialPort
                                    )
                                    serialDataViewModel.insertUserInformation(changeSetting)
                                    expandedFuelConsumption = false
                                }
                            )
                        }
                    }
                }
            }
        }
        // Speed limit config
        RoundedCard(
            modifier = Modifier
                .padding(start = 20.dpx, end = 20.dpx, top = 10.dpy)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF111926))
                    .padding(32.dpx),
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                Text(
                    text = "Speed limit", modifier = Modifier.clickable { expandedSpeedLimit = true },
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
                Box {
                    Text(
                        text = userSetting.speedLimit.toString(),
                        modifier = Modifier.clickable { expandedSpeedLimit = true },
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
                    DropdownMenu(
                        expanded = expandedSpeedLimit,
                        onDismissRequest = { expandedSpeedLimit = false }
                    ) {
                        for (type in IntArray(20) { 10 * (it + 1) }) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = type.toString(),
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
                                },
                                onClick = {
                                    val changeSetting = UserInformationEntity(
                                        fuelConsumption = userSetting.fuelConsumption,
                                        speedLimit = type,
                                        serialPort = userSetting.serialPort
                                    )
                                    serialDataViewModel.insertUserInformation(changeSetting)
                                    expandedSpeedLimit = false
                                }
                            )
                        }
                    }
                }
            }
        }
        // Serial port config
        RoundedCard(
            modifier = Modifier
                .padding(start = 20.dpx, end = 20.dpx, top = 10.dpy)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF111926))
                    .padding(32.dpx),
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                Text(
                    text = "Select serial port", modifier = Modifier.clickable { expandedSerialPort = true },
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

                Box {
                    Text(
                        text = userSetting.serialPort,
                        modifier = Modifier.clickable { expandedSerialPort = true },
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
                    DropdownMenu(
                        expanded = expandedSerialPort,
                        onDismissRequest = { expandedSerialPort = false }
                    ) {
                        for (port in allPort) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = port,
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
                                },
                                onClick = {
                                    Log.d("SettingPage", "Selected port: $port")
                                    val changeSetting = UserInformationEntity(
                                        fuelConsumption = userSetting.fuelConsumption,
                                        speedLimit = userSetting.speedLimit,
                                        serialPort = port
                                    )
                                    serialDataViewModel.insertUserInformation(changeSetting)
                                    expandedSerialPort = false
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}