package com.example.aaos_onelink.connection

import android.util.Log
import com.example.aaos_onelink.database.AlarmConfig
import com.example.aaos_onelink.database.AlarmEntity
import com.example.aaos_onelink.database.DataViewModel
import com.example.aaos_onelink.database.DynamicInformationEntity
import com.example.aaos_onelink.database.FixedInformationEntity
import com.example.aaos_onelink.database.UserInformationEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import tp.xmaihh.serialport.SerialHelper
import tp.xmaihh.serialport.bean.ComBean

@Serializable
data class FixedInformationType(
    @SerialName("devcode")
    val devcode: String,
    @SerialName("TimeStamp")
    val TimeStamp: String,
    @SerialName("TripMark")
    val TripMark: String,
    @SerialName("AccStatus")
    val AccStatus: Int,
    @SerialName("AlmID")
    val AlmID: String,
    @SerialName("ValidByte")
    val ValidByte: Int,
    @SerialName("Latitude")
    val Latitude: String,
    @SerialName("Longitude")
    val Longitude: String,
    @SerialName("Altitude")
    val Altitude: String,
    @SerialName("Satellites")
    val Satellites: String,
    @SerialName("Speed")
    val Speed: String,
    @SerialName("Direction")
    val Direction: String,
    @SerialName("PDOP")
    val PDOP: String,
    @SerialName("VehicleID")
    val VehicleID: String,
    @SerialName("ObdProType")
    val ObdProType: Int,
    @SerialName("MilesType")
    val MilesType: Int,
    @SerialName("FuelType")
    val FuelType: Int
)

@Serializable
data class DynamicInformationType(
    @SerialName("0001")
    val d_0001: String,
    @SerialName("0002")
    val d_0002: String,
    @SerialName("0003")
    val d_0003: String,
    @SerialName("0004")
    val d_0004: String,
    @SerialName("0005")
    val d_0005: String,
    @SerialName("0010")
    val d_0010: String,
    @SerialName("0012")
    val d_0012: String,
    @SerialName("0013")
    val d_0013: String,
    @SerialName("0014")
    val d_0014: Int,
    @SerialName("0015")
    val d_0015: String,
    @SerialName("0016")
    val d_0016: String,
    @SerialName("3010")
    val d_3010: String,
    @SerialName("3012")
    val d_3012: String,
    @SerialName("3013")
    val d_3013: String,
    @SerialName("3014")
    val d_3014: String,
    @SerialName("3015")
    val d_3015: String,
    @SerialName("3016")
    val d_3016: String,
    @SerialName("3017")
    val d_3017: Int,
    @SerialName("3018")
    val d_3018: String,
    @SerialName("3019")
    val d_3019: String,
    @SerialName("3020")
    val d_3020: Int,
    @SerialName("301A")
    val d_301A: String,
    @SerialName("9010")
    val d_9010: Int,
    @SerialName("60C0")
    val d_60C0: String,
    @SerialName("60D0")
    val d_60D0: Int,
    @SerialName("62F0")
    val d_62F0: String,
    @SerialName("6050")
    val d_6050: Int,
    @SerialName("6490")
    val d_6490: Int,
    @SerialName("6010")
    val d_6010: Int,
    @SerialName("5001")
    val d_5001: Int,
    @SerialName("5002")
    val d_5002: Int,
    @SerialName("5003")
    val d_5003: Int,
    @SerialName("5004")
    val d_5004: Int,
    @SerialName("5005")
    val d_5005: String,
    @SerialName("5006")
    val d_5006: String,
    @SerialName("5007")
    val d_5007: String,
    @SerialName("5008")
    val d_5008: Int,
    @SerialName("5009")
    val d_5009: Int,
    @SerialName("500A")
    val d_500A: Int,
    @SerialName("500B")
    val d_500B: Int,
    @SerialName("500C")
    val d_500C: Int,
    @SerialName("500D")
    val d_500D: Int,
    @SerialName("500E")
    val d_500E: String,
    @SerialName("500F")
    val d_500F: Int,
    @SerialName("5010")
    val d_5010: String,
    @SerialName("5011")
    val d_5011: String,
    @SerialName("5012")
    val d_5012: String,
    @SerialName("5013")
    val d_5013: String,
    @SerialName("5014")
    val d_5014: String,
    @SerialName("5015")
    val d_5015: String,
    @SerialName("5016")
    val d_5016: String,
    @SerialName("5017")
    val d_5017: String,
    @SerialName("5019")
    val d_5019: String,
    @SerialName("501A")
    val d_501A: String,
    @SerialName("501B")
    val d_501B: Int,
    @SerialName("501C")
    val d_501C: String,
    @SerialName("501D")
    val d_501D: String,
    @SerialName("501E")
    val d_501E: String,
    @SerialName("501F")
    val d_501F: String,
    @SerialName("5020")
    val d_5020: String,
    @SerialName("5030")
    val d_5030: String,
    @SerialName("5041")
    val d_5041: String
)

@Serializable
data class InformationType(
    @SerialName("FixedInformation")
    val FixedInformation: FixedInformationType,
    @SerialName("DynamicData")
    val DynamicInformation: DynamicInformationType
)

var data: String = ""
class SerialCommunication(sPort: String?, iBaudRate: Int, serialDataViewModel: DataViewModel) :
    SerialHelper(sPort, iBaudRate) {
    private val serialDataView = serialDataViewModel

    @OptIn(ExperimentalSerializationApi::class)
    override fun onDataReceived(p0: ComBean?) {
//        Log.d("Serial", "${p0?.sComPort} : ${p0?.bRec?.toString(Charsets.UTF_8)}")
        var bufferSPort = p0?.bRec?.toString(Charsets.UTF_8) ?: ""
        bufferSPort = bufferSPort.replace("?", "")
        data += bufferSPort
        Log.d("Serial", "handleSerialData: $data")

        CoroutineScope(Dispatchers.IO).launch {
            delay(600)
            data = ""
        }

        val serialInfoData: InformationType
        val fixedInfoData: FixedInformationType
        val dynamicInfoData: DynamicInformationType
        val jsonParse = Json { prettyPrint = true; allowTrailingComma = true; ignoreUnknownKeys = true }
        try {
            serialInfoData = jsonParse.decodeFromString(InformationType.serializer(), data)
            fixedInfoData = serialInfoData.FixedInformation
            dynamicInfoData = serialInfoData.DynamicInformation

            serialDataView.insertFixedInformation(
                FixedInformationEntity(
                    devcode = fixedInfoData.devcode,
                    TimeStamp = fixedInfoData.TimeStamp,
                    TripMark = fixedInfoData.TripMark,
                    AccStatus = fixedInfoData.AccStatus,
                    AlmID = fixedInfoData.AlmID,
                    ValidByte = fixedInfoData.ValidByte,
                    Latitude = fixedInfoData.Latitude,
                    Longitude = fixedInfoData.Longitude,
                    Altitude = fixedInfoData.Altitude,
                    Satellites = fixedInfoData.Satellites,
                    Speed = fixedInfoData.Speed,
                    Direction = fixedInfoData.Direction,
                    PDOP = fixedInfoData.PDOP,
                    VehicleID = fixedInfoData.VehicleID,
                    ObdProType = fixedInfoData.ObdProType,
                    MilesType = fixedInfoData.MilesType,
                    FuelType = fixedInfoData.FuelType
                )
            )

            serialDataView.insertDynamicInformation(
                DynamicInformationEntity(
                    d_0001 = dynamicInfoData.d_0001,
                    d_0002 = dynamicInfoData.d_0002,
                    d_0003 = dynamicInfoData.d_0003,
                    d_0004 = dynamicInfoData.d_0004,
                    d_0005 = dynamicInfoData.d_0005,
                    d_0010 = dynamicInfoData.d_0010,
                    d_0012 = dynamicInfoData.d_0012,
                    d_0013 = dynamicInfoData.d_0013,
                    d_0014 = dynamicInfoData.d_0014,
                    d_0015 = dynamicInfoData.d_0015,
                    d_0016 = dynamicInfoData.d_0016,
                    d_3010 = dynamicInfoData.d_3010,
                    d_3012 = dynamicInfoData.d_3012,
                    d_3013 = dynamicInfoData.d_3013,
                    d_3014 = dynamicInfoData.d_3014,
                    d_3015 = dynamicInfoData.d_3015,
                    d_3016 = dynamicInfoData.d_3016,
                    d_3017 = dynamicInfoData.d_3017,
                    d_3018 = dynamicInfoData.d_3018,
                    d_3019 = dynamicInfoData.d_3019,
                    d_3020 = dynamicInfoData.d_3020,
                    d_301A = dynamicInfoData.d_301A,
                    d_9010 = dynamicInfoData.d_9010,
                    d_60C0 = dynamicInfoData.d_60C0,
                    d_60D0 = dynamicInfoData.d_60D0,
                    d_62F0 = dynamicInfoData.d_62F0,
                    d_6050 = dynamicInfoData.d_6050,
                    d_6490 = dynamicInfoData.d_6490,
                    d_6010 = dynamicInfoData.d_6010,
                    d_5001 = dynamicInfoData.d_5001,
                    d_5002 = dynamicInfoData.d_5002,
                    d_5003 = dynamicInfoData.d_5003,
                    d_5004 = dynamicInfoData.d_5004,
                    d_5005 = dynamicInfoData.d_5005,
                    d_5006 = dynamicInfoData.d_5006,
                    d_5007 = dynamicInfoData.d_5007,
                    d_5008 = dynamicInfoData.d_5008,
                    d_5009 = dynamicInfoData.d_5009,
                    d_500A = dynamicInfoData.d_500A,
                    d_500B = dynamicInfoData.d_500B,
                    d_500C = dynamicInfoData.d_500C,
                    d_500D = dynamicInfoData.d_500D,
                    d_500E = dynamicInfoData.d_500E,
                    d_500F = dynamicInfoData.d_500F,
                    d_5010 = dynamicInfoData.d_5010,
                    d_5011 = dynamicInfoData.d_5011,
                    d_5012 = dynamicInfoData.d_5012,
                    d_5013 = dynamicInfoData.d_5013,
                    d_5014 = dynamicInfoData.d_5014,
                    d_5015 = dynamicInfoData.d_5015,
                    d_5016 = dynamicInfoData.d_5016,
                    d_5017 = dynamicInfoData.d_5017,
                    d_5019 = dynamicInfoData.d_5019,
                    d_501A = dynamicInfoData.d_501A,
                    d_501B = dynamicInfoData.d_501B,
                    d_501C = dynamicInfoData.d_501C,
                    d_501D = dynamicInfoData.d_501D,
                    d_501E = dynamicInfoData.d_501E,
                    d_501F = dynamicInfoData.d_501F,
                    d_5020 = dynamicInfoData.d_5020,
                    d_5030 = dynamicInfoData.d_5030,
                    d_5041 = dynamicInfoData.d_5041
                )
            )

            if (AlarmConfig.containsKey(dynamicInfoData.d_5030)) {
                val alarm = dynamicInfoData.d_5030.toInt()
                if (alarm == 0) return
                val message = AlarmConfig[dynamicInfoData.d_5030] ?: ""
                if (message == "") return
                val type = when (alarm)
                {
                    in 0..255 -> 1
                    in 256..511 -> 2
                    in 512..767 -> 3
                    in 768..1023 -> 4
                    else -> 0
                }
                if (type == 0) return
                serialDataView.insertAlarmInfo(
                    AlarmEntity(
                        type = type,
                        alarm = alarm,
                        message = message,
                        time = fixedInfoData.TimeStamp
                    )
                )
            }

            if (p0?.sComPort != null) {
                serialDataView.setPortInUserInformation(p0.sComPort)
            }
        } catch (e: Exception) {
            Log.d("Serial", "Error: ${e.message}")
            Log.d("Serial", "Data: $data")
        }

    }
}