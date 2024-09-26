package com.example.aaos_onelink.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.example.aaos_onelink.R
import com.example.aaos_onelink.components.RoundedCard
import com.example.aaos_onelink.components.SpaceY
import com.example.aaos_onelink.database.DataStoreManager
import com.example.aaos_onelink.database.DataViewModel
import com.example.aaos_onelink.database.DynamicInformationEntity
import com.example.aaos_onelink.database.FixedInformationEntity
import com.example.aaos_onelink.ui.theme.SecondaryColor_def
import com.example.aaos_onelink.ui.theme.dpx
import com.example.aaos_onelink.ui.theme.dpy
import com.example.aaos_onelink.ui.theme.provider
import com.example.aaos_onelink.ui.theme.spt

@Composable
fun DataPage(serialDataViewModel: DataViewModel) {

    val fixedInfoData by serialDataViewModel.getLastFixedInformation.collectAsState(initial = FixedInformationEntity(
        devcode = "",
        TimeStamp = "",
        TripMark = "",
        AccStatus = 0,
        AlmID = "",
        ValidByte = 0,
        Latitude = "",
        Longitude = "",
        Altitude = "",
        Satellites = "",
        Speed = "",
        Direction = "",
        PDOP = "",
        VehicleID = "",
        ObdProType = 0,
        MilesType = 0,
        FuelType = 0
    ))

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
    ))

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
                            painter = painterResource(id = R.drawable.repair),
                            contentDescription = "",
                            modifier = Modifier
                                .height(32.dpy)
                                .width(32.dpx)
                        )
                        Text(
                            text = "Fixed Information",
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

                Column(modifier = Modifier.verticalScroll(rememberScrollState()).padding(bottom = 20.dpy)) {
                    SingleInfoCard(R.drawable.cpu, "Device ID", fixedInfoData?.devcode ?: "")
                    SingleInfoCard(R.drawable.time, "TimeStamp", fixedInfoData?.TimeStamp ?: "")
                    SingleInfoCard(R.drawable.on_time, "TripMark", fixedInfoData?.TripMark ?: "")
                    SingleInfoCard(R.drawable.info_icon, "AccStatus", fixedInfoData?.AccStatus.toString())
                    SingleInfoCard(R.drawable.alert, "AlmID", fixedInfoData?.AlmID ?: "")
                    SingleInfoCard(R.drawable.thunder, "ValidByte", fixedInfoData?.ValidByte.toString())
                    InfoCard(icon = R.drawable.gps) {
                        InfoLine("Latitude", fixedInfoData?.Latitude ?: "")
                        InfoLine("Longitude", fixedInfoData?.Longitude ?: "")
                        InfoLine("Altitude", fixedInfoData?.Altitude ?: "")
                    }
                    SingleInfoCard(R.drawable.location, "Satellites", fixedInfoData?.Satellites ?: "")
                    SingleInfoCard(R.drawable.speedometer, "Speed", fixedInfoData?.Speed ?: "")
                    SingleInfoCard(R.drawable.right_turn, "Direction", fixedInfoData?.Direction ?: "")
                    SingleInfoCard(R.drawable.box, "PDOP", fixedInfoData?.PDOP ?: "")
                    SingleInfoCard(R.drawable.truck, "VehicleID", fixedInfoData?.VehicleID ?: "")
                    SingleInfoCard(R.drawable.repair, "ObdProType", fixedInfoData?.ObdProType.toString())
                    SingleInfoCard(R.drawable.info_icon, "MilesType", fixedInfoData?.MilesType.toString())
                    SingleInfoCard(R.drawable.oil_tank, "FuelType", fixedInfoData?.FuelType.toString())
                }
                SpaceY(length = 20)
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
                            painter = painterResource(id = R.drawable.box),
                            contentDescription = "",
                            modifier = Modifier
                                .height(32.dpy)
                                .width(32.dpx)
                        )
                        Text(
                            text = "Dynamic Information",
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

                Column(modifier = Modifier.verticalScroll(rememberScrollState()).padding(bottom = 20.dpy)) {
                    SingleInfoCardLongTitle(R.drawable.info_icon,"Total mileage data", dynamicInfoData?.d_0001 ?: "")
                    SingleInfoCardLongTitle(R.drawable.info_icon,"Total fuel consumption data", dynamicInfoData?.d_0002 ?: "")
                    SingleInfoCardLongTitle(R.drawable.info_icon,"Run time length", dynamicInfoData?.d_0003 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"When the stall is long", dynamicInfoData?.d_0004 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"Idle time length", dynamicInfoData?.d_0005 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"OBD voltage", dynamicInfoData?.d_0010 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"VERSION number", dynamicInfoData?.d_0012 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"SIM card Iccid", dynamicInfoData?.d_0013 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"GSM  CSQ", dynamicInfoData?.d_0014.toString())
                    SingleInfoCard(R.drawable.info_icon,"Device status", dynamicInfoData?.d_0015 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"millisecond", dynamicInfoData?.d_0016 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"AI  and MDVR status", dynamicInfoData?.d_3010 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"Geo_fence ID", dynamicInfoData?.d_3012 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"G-sensor  value", dynamicInfoData?.d_3013 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"Input and output status", dynamicInfoData?.d_3014 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"Analog input1", dynamicInfoData?.d_3015 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"Analog input2", dynamicInfoData?.d_3016 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"Speed change per second", dynamicInfoData?.d_3017.toString())
                    SingleInfoCard(R.drawable.info_icon,"Angel change per second", dynamicInfoData?.d_3018 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"Rpm  change  per second", dynamicInfoData?.d_3019 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"Camera connection status", dynamicInfoData?.d_3020.toString())
                    SingleInfoCard(R.drawable.info_icon,"G_senser data", dynamicInfoData?.d_301A ?: "")
                    SingleInfoCard(R.drawable.info_icon,"DLT login status", dynamicInfoData?.d_9010.toString())
                    SingleInfoCard(R.drawable.info_icon,"OBD Engine speed", dynamicInfoData?.d_60C0 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"OBD speed", dynamicInfoData?.d_60D0.toString())
                    SingleInfoCard(R.drawable.info_icon,"OBD remaining oil content", dynamicInfoData?.d_62F0 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"OBD Coolant temperature", dynamicInfoData?.d_6050.toString())
                    SingleInfoCard(R.drawable.info_icon,"OBD Accelerator pedal Position", dynamicInfoData?.d_6490.toString())
                    SingleInfoCard(R.drawable.info_icon,"Number of OBD fault codes", dynamicInfoData?.d_6010.toString())
                    SingleInfoCard(R.drawable.info_icon,"OBD Clutch Switch", dynamicInfoData?.d_5001.toString())
                    SingleInfoCard(R.drawable.info_icon,"OBD Brake Brake Switch", dynamicInfoData?.d_5002.toString())
                    SingleInfoCard(R.drawable.info_icon,"OBD Parking Brake Switch", dynamicInfoData?.d_5003.toString())
                    SingleInfoCard(R.drawable.info_icon,"OBD Throttle Valve Position", dynamicInfoData?.d_5004.toString())
                    SingleInfoCard(R.drawable.info_icon,"Utilization rate of OBD oil", dynamicInfoData?.d_5005 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"OBD Fuel temperature", dynamicInfoData?.d_5006 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"OBD Oil Temperature", dynamicInfoData?.d_5007 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"OBD engine lubricating Oil pressure", dynamicInfoData?.d_5008.toString())
                    SingleInfoCard(R.drawable.info_icon,"OBD Brake pedal Position", dynamicInfoData?.d_5009.toString())
                    SingleInfoCard(R.drawable.info_icon,"OBD Exhause", dynamicInfoData?.d_500A.toString())
                    SingleInfoCard(R.drawable.info_icon,"OBD Cruise  control", dynamicInfoData?.d_500B.toString())
                    SingleInfoCard(R.drawable.info_icon,"DTC Status", dynamicInfoData?.d_500C.toString())
                    SingleInfoCard(R.drawable.info_icon,"OBD Gear", dynamicInfoData?.d_500D.toString())
                    SingleInfoCard(R.drawable.info_icon,"OBD engine time run", dynamicInfoData?.d_500E ?: "")
                    SingleInfoCard(R.drawable.info_icon,"OBD PTO Status", dynamicInfoData?.d_500F.toString())
                    SingleInfoCard(R.drawable.info_icon,"OBD total idle fuel used", dynamicInfoData?.d_5010 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"OBD total  idle  hours", dynamicInfoData?.d_5011 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"OBD Total PTO fuel used", dynamicInfoData?.d_5012 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"OBD Total power hours", dynamicInfoData?.d_5013 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"OBD high resolution trip distance", dynamicInfoData?.d_5014 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"HYBRIBUS  battery temperature", dynamicInfoData?.d_5015 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"OBD trip fuel consumption real time", dynamicInfoData?.d_5016 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"Inter battery voltage", dynamicInfoData?.d_5017 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"Fuel used  real time", dynamicInfoData?.d_5019 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"Turning data", dynamicInfoData?.d_501A ?: "")
                    SingleInfoCard(R.drawable.info_icon,"Sleep enable", dynamicInfoData?.d_501B.toString())
                    SingleInfoCard(R.drawable.info_icon,"GPS   HDOP", dynamicInfoData?.d_501C ?: "")
                    SingleInfoCard(R.drawable.info_icon,"Temperature sensor", dynamicInfoData?.d_501D ?: "")
                    SingleInfoCard(R.drawable.info_icon,"Total OBD mileage data", dynamicInfoData?.d_501E ?: "")
                    SingleInfoCard(R.drawable.info_icon,"Total GPS mileage data", dynamicInfoData?.d_501F ?: "")
                    SingleInfoCard(R.drawable.info_icon,"CANBUS state", dynamicInfoData?.d_5020 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"Alarm ID", dynamicInfoData?.d_5030 ?: "")
                    SingleInfoCard(R.drawable.info_icon,"N/A", dynamicInfoData?.d_5041 ?: "")
                }
                SpaceY(length = 20)
            }
        }
    }
}

@Composable
fun InfoCard(icon: Int, content: @Composable () -> Unit) {
    Row(
        modifier = Modifier
            .padding(start = 20.dpx, end = 20.dpx, top = 20.dpy)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    )
    {
        Column(
            horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxHeight()
        )
        {
            Box(
                modifier = Modifier
                    .width(50.dpx)
                    .height(50.dpy)
                    .clip(
                        RoundedCornerShape(12.dpx)
                    )
                    .background(SecondaryColor_def),
                contentAlignment = Alignment.Center
            )
            {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "",
                    modifier = Modifier
                        .height(32.dpy)
                        .width(32.dpx)
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(start = 20.dpx)
                .fillMaxSize()
                .defaultMinSize(minHeight = 50.dpy)
        ) {
            content()
        }
    }
}

@Composable
fun InfoLine(title: String, detail: String, unit: String? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dpy),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    )
    {
        Text(
            text = title,
            color = Color.White,
            fontFamily = FontFamily(
                androidx.compose.ui.text.googlefonts.Font(
                    googleFont = GoogleFont("Inter"),
                    fontProvider = provider,
                )
            ),
            fontWeight = FontWeight.Medium,
            fontSize = 18.spt,
            modifier = Modifier
                .width(150.dpx)
                .padding(end = 20.dpx)
        )
        Row()
        {


            Text(
                text = detail,
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
            if (unit != null)
                Text(
                    text = "(${unit})",
                    color = Color.White,
                    fontFamily = FontFamily(
                        androidx.compose.ui.text.googlefonts.Font(
                            googleFont = GoogleFont("Inter"),
                            fontProvider = provider,
                        )
                    ),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.spt,
                    modifier = Modifier.padding(start = 18.dpx)
                )
        }
    }

}

@Composable
fun InfoLineLongTitle(title: String, detail: String, unit: String? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dpy),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    )
    {
        Text(
            text = title,
            color = Color.White,
            fontFamily = FontFamily(
                androidx.compose.ui.text.googlefonts.Font(
                    googleFont = GoogleFont("Inter"),
                    fontProvider = provider,
                )
            ),
            fontWeight = FontWeight.Medium,
            fontSize = 18.spt,
            modifier = Modifier
                .width(250.dpx)
                .padding(end = 20.dpx)
        )
        Row()
        {
            Text(
                text = detail,
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
            if (unit != null)
                Text(
                    text = "(${unit})",
                    color = Color.White,
                    fontFamily = FontFamily(
                        androidx.compose.ui.text.googlefonts.Font(
                            googleFont = GoogleFont("Inter"),
                            fontProvider = provider,
                        )
                    ),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.spt,
                    modifier = Modifier.padding(start = 18.dpx)
                )
        }
    }

}

@Composable
fun SingleInfoCard(icon: Int, title: String, detail: String, unit: String? = null)
{
    InfoCard(icon = icon)
    {
        InfoLine(title, detail, unit)
    }
}

@Composable
fun SingleInfoCardLongTitle(icon: Int, title: String, detail: String, unit: String? = null)
{
    InfoCard(icon = icon)
    {
        InfoLineLongTitle(title, detail, unit)
    }
}