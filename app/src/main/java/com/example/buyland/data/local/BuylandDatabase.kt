package com.example.buyland.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

import com.example.buyland.domain.model.Product
import com.example.buyland.domain.model.User
import com.example.buyland.domain.util.Converter



@Database(entities = [(User::class),(Product::class)],version= 2 ,exportSchema = true  )
@TypeConverters(Converter::class)
abstract class BuylandDatabase:RoomDatabase() {
    abstract val buylandPDao:BuyLandPDao
    abstract val buylandDao:BuylandDao
    companion object{
        const val DATABASE_NAME = "buyland_db"
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

                database.execSQL("CREATE TABLE IF NOT EXISTS `product` (`pName` TEXT NOT NULL, `pPrice` TEXT NOT NULL, `pDescription` TEXT NOT NULL, `userId` INTEGER NOT NULL, `pId` INTEGER, PRIMARY KEY(`pId`))")
            }
        }
    }
}
