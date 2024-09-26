package com.example.aaos_onelink.database

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aaos_onelink.MainApplication
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Entity(tableName = "serial_information_table")
data class SerialInformation(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val fixedInf: String,
    val dynamicInf: String
)

@Entity(tableName = "fixed_information_table")
data class FixedInformationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val devcode: String,
    val TimeStamp: String,
    val TripMark: String,
    val AccStatus: Int,
    val AlmID: String,
    val ValidByte: Int,
    val Latitude: String,
    val Longitude: String,
    val Altitude: String,
    val Satellites: String,
    val Speed: String,
    val Direction: String,
    val PDOP: String,
    val VehicleID: String,
    val ObdProType: Int,
    val MilesType: Int,
    val FuelType: Int
)

@Entity(tableName = "dynamic_information_table")
data class DynamicInformationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val d_0001: String,
    val d_0002: String,
    val d_0003: String,
    val d_0004: String,
    val d_0005: String,
    val d_0010: String,
    val d_0012: String,
    val d_0013: String,
    val d_0014: Int,
    val d_0015: String,
    val d_0016: String,
    val d_3010: String,
    val d_3012: String,
    val d_3013: String,
    val d_3014: String,
    val d_3015: String,
    val d_3016: String,
    val d_3017: Int,
    val d_3018: String,
    val d_3019: String,
    val d_3020: Int,
    val d_301A: String,
    val d_9010: Int,
    val d_60C0: String,
    val d_60D0: Int,
    val d_62F0: String,
    val d_6050: Int,
    val d_6490: Int,
    val d_6010: Int,
    val d_5001: Int,
    val d_5002: Int,
    val d_5003: Int,
    val d_5004: Int,
    val d_5005: String,
    val d_5006: String,
    val d_5007: String,
    val d_5008: Int,
    val d_5009: Int,
    val d_500A: Int,
    val d_500B: Int,
    val d_500C: Int,
    val d_500D: Int,
    val d_500E: String,
    val d_500F: Int,
    val d_5010: String,
    val d_5011: String,
    val d_5012: String,
    val d_5013: String,
    val d_5014: String,
    val d_5015: String,
    val d_5016: String,
    val d_5017: String,
    val d_5019: String,
    val d_501A: String,
    val d_501B: Int,
    val d_501C: String,
    val d_501D: String,
    val d_501E: String,
    val d_501F: String,
    val d_5020: String,
    val d_5030: String,
    val d_5041: String
)

@Entity(tableName = "user_information_table")
data class UserInformationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val fuelConsumption: String,
    val speedLimit: Int,
    val serialPort: String
)

@Entity(tableName = "alarm_table")
data class AlarmEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val type: Int,
    val alarm: Int,
    val message: String,
    val time: String
)

@Dao
interface SerialInformationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSerialInformation(serialInformation: SerialInformation)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFixedInformation(fixedInformation: FixedInformationEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDynamicInformation(dynamicInformation: DynamicInformationEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUserInformation(userInformation: UserInformationEntity)

    // SerialInformation Queries
    @Query("SELECT * FROM serial_information_table")
    suspend fun getAllSerialInformation(): List<SerialInformation>

    @Query("SELECT * FROM serial_information_table ORDER BY id DESC LIMIT 1")
    suspend fun getLastSerialInformation(): SerialInformation?

    @Query("DELETE FROM serial_information_table")
    suspend fun clearSerialInformation()

    // FixedInformation Queries
    @Query("SELECT * FROM fixed_information_table")
    fun getAllFixedInformation(): Flow<List<FixedInformationEntity>>

    @Query("SELECT * FROM fixed_information_table ORDER BY id DESC LIMIT 1")
    fun getLastFixedInformation(): Flow<FixedInformationEntity?>

    @Query("DELETE FROM fixed_information_table")
    fun clearFixedInformation()

    // DynamicInformation Queries
    @Query("SELECT * FROM dynamic_information_table")
    fun getAllDynamicInformation(): Flow<List<DynamicInformationEntity>>

    @Query("SELECT * FROM dynamic_information_table ORDER BY id DESC LIMIT 1")
    fun getLastDynamicInformation(): Flow<DynamicInformationEntity?>

    @Query("DELETE FROM dynamic_information_table")
    fun clearDynamicInformation()

    @Query("SELECT TimeStamp FROM fixed_information_table")
    fun getAllTime() : Flow<List<String>?>

    // UserInformation Queries
    @Query("SELECT * FROM user_information_table ORDER BY id DESC LIMIT 1")
    fun getLastUserInformation(): Flow<UserInformationEntity?>

    // Alarm Queries
    @Query("SELECT * FROM alarm_table")
    fun getAllAlarmInfo(): Flow<List<AlarmEntity>?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAlarmInfo(alarmEntity: AlarmEntity)

    @Query("DELETE FROM alarm_table")
    fun clearAlarmInfo()

    @Query("SELECT * FROM alarm_table WHERE type = 1")
    fun getAllAlarmInfoType1(): Flow<List<AlarmEntity>?>

    @Query("SELECT * FROM alarm_table WHERE type = 2")
    fun getAllAlarmInfoType2(): Flow<List<AlarmEntity>?>

    @Query("SELECT * FROM alarm_table WHERE type = 3")
    fun getAllAlarmInfoType3(): Flow<List<AlarmEntity>?>

    @Query("SELECT * FROM alarm_table WHERE type = 4")
    fun getAllAlarmInfoType4(): Flow<List<AlarmEntity>?>
}

@Database(
    entities = [SerialInformation::class, FixedInformationEntity::class, DynamicInformationEntity::class, UserInformationEntity::class, AlarmEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SerialInformationDatabase : RoomDatabase() {
    abstract fun serialInformationDao(): SerialInformationDao

    companion object {
        @Volatile
        private var Instance: SerialInformationDatabase? = null

        fun getDatabase(context: Context): SerialInformationDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    SerialInformationDatabase::class.java,
                    "serial_information_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}


class DataViewModel : ViewModel() {
    private val serialInformationDao = MainApplication.SerialInformationDB.serialInformationDao()

    @OptIn(DelicateCoroutinesApi::class)
    fun getLastSerialInformation() {
        GlobalScope.launch {
            serialInformationDao.getLastSerialInformation()
        }
    }

    // FixedInformation Functions
    val getAllFixedInformation = serialInformationDao.getAllFixedInformation()
    val getLastFixedInformation = serialInformationDao.getLastFixedInformation()

    @OptIn(DelicateCoroutinesApi::class)
    fun insertFixedInformation(fixedInformation: FixedInformationEntity) {
        GlobalScope.launch {
            serialInformationDao.insertFixedInformation(fixedInformation)
        }

    }

    @OptIn(DelicateCoroutinesApi::class)
    fun clearFixedInformation() {
        GlobalScope.launch {
            serialInformationDao.clearFixedInformation()
        }
    }

    // DynamicInformation Functions
    val getAllDynamicInformation = serialInformationDao.getAllDynamicInformation()
    val getLastDynamicInformation = serialInformationDao.getLastDynamicInformation()

    @OptIn(DelicateCoroutinesApi::class)
    fun insertDynamicInformation(dynamicInformation: DynamicInformationEntity) {
        GlobalScope.launch {
            serialInformationDao.insertDynamicInformation(dynamicInformation)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun clearDynamicInformation() {
        GlobalScope.launch {
            serialInformationDao.clearDynamicInformation()
        }
    }

    // UserInformation Functions
    val getLastUserInformation = serialInformationDao.getLastUserInformation()

    @OptIn(DelicateCoroutinesApi::class)
    fun insertUserInformation(userInformation: UserInformationEntity) {
        GlobalScope.launch {
            serialInformationDao.insertUserInformation(userInformation)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun setPortInUserInformation(port: String) {
        GlobalScope.launch {
//            getLastUserInformation.collect {
//                if (it != null) {
//                    val speed = it.speedLimit
//                    val consumption = it.fuelConsumption
//                    serialInformationDao.insertUserInformation(UserInformationEntity(
//                        fuelConsumption = consumption,
//                        speedLimit = speed,
//                        serialPort = port
//                    ))
//                }
//                else
//                {
//                    serialInformationDao.insertUserInformation(UserInformationEntity(
//                        fuelConsumption = "L/100km",
//                        speedLimit = 50,
//                        serialPort = port
//                    ))
//                }
//            }
        }
    }

    // Alarm Functions
    val getAllAlarmInfo = serialInformationDao.getAllAlarmInfo()
    val getAllAlarmInfoType1 = serialInformationDao.getAllAlarmInfoType1()
    val getAllAlarmInfoType2 = serialInformationDao.getAllAlarmInfoType2()
    val getAllAlarmInfoType3 = serialInformationDao.getAllAlarmInfoType3()
    val getAllAlarmInfoType4 = serialInformationDao.getAllAlarmInfoType4()

    @OptIn(DelicateCoroutinesApi::class)
    fun insertAlarmInfo(alarmEntity: AlarmEntity) {
        GlobalScope.launch {
            serialInformationDao.insertAlarmInfo(alarmEntity)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun clearAlarmInfo() {
        GlobalScope.launch {
            serialInformationDao.clearAlarmInfo()
        }
    }
}