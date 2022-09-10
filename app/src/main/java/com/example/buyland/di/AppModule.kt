package com.example.buyland.di

import android.app.Application
import androidx.room.Room
import com.example.buyland.data.local.BuylandDatabase
import com.example.buyland.data.remote.BuyLandApi
import com.example.buyland.data.repository.BuylandRepositoryImpl
import com.example.buyland.domain.api_use_cases.GetCommentUseCase
import com.example.buyland.domain.api_use_cases.PostCommentUseCase
import com.example.buyland.domain.model.NetResponse
import com.example.buyland.domain.model.PostComment
import com.example.buyland.domain.repository.BuylandRepository
import com.example.buyland.domain.use_cases.*
import com.example.buyland.domain.use_cases_product.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideBuylandDatabase(app:Application):BuylandDatabase{
        return Room.databaseBuilder(
            app,
            BuylandDatabase::class.java,
            BuylandDatabase.DATABASE_NAME

        ).addMigrations(BuylandDatabase.MIGRATION_1_2)
            .build()
    }
    @Provides
    @Singleton
    fun provideBuylandRepository(db:BuylandDatabase,api:BuyLandApi) : BuylandRepository{
        return BuylandRepositoryImpl(db.buylandDao,db.buylandPDao,api)
    }
    @Provides
    @Singleton
    fun provideBuylandUseCases(repository: BuylandRepository) : UserUseCases{
        return UserUseCases(
        getIdByName= GetIdByName(repository),
        getUserById= GetUserById(repository),
        getPasswordById = GetPasswordById(repository),
        checkUserName= CheckUserName(repository),
        insertUser= InsertUser(repository),
        updateUser=UpdateUser(repository),

        )
    }
    @Provides
    @Singleton
    fun provideBuylandProductUseCase(repository: BuylandRepository) : ProductUseCases{
        return ProductUseCases(
            getProductsUseCase = GetProductsUseCase(repository=repository),
            getProductUseCase = GetProductUseCase(repository=repository),
            deleteProductUseCase = DeleteProductUseCase(repository=repository),
            insertProduct = InsertProduct(repository=repository)
        )
    }
    /////////////////////
    @Provides
    @Singleton
    fun provideBuyLandApi():BuyLandApi{
        return Retrofit.Builder()
            .baseUrl(BuyLandApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BuyLandApi::class.java)
    }
    @Provides
    @Singleton
    fun provideCommentUseCase(db:BuylandDatabase,api:BuyLandApi):GetCommentUseCase{
        return GetCommentUseCase(repository = BuylandRepositoryImpl(db.buylandDao,db.buylandPDao,api))
    }
    /////////////////////
    @Provides
    @Singleton
    fun providePostCommentUseCase(db:BuylandDatabase,api:BuyLandApi):PostCommentUseCase{
        return PostCommentUseCase(repository = BuylandRepositoryImpl(db.buylandDao,db.buylandPDao,api))

    }





}