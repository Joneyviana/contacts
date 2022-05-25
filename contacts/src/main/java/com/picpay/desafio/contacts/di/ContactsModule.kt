package com.picpay.desafio.contacts.di

import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.contacts.data.local.AppDatabase
import com.picpay.desafio.contacts.data.network.ContactService
import com.picpay.desafio.contacts.data.repository.ContactRepository
import com.picpay.desafio.contacts.domain.GetListContactsUseCase
import com.picpay.desafio.contacts.ui.ContactViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val url = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"

val contactModule = module {
    single {
        Room.databaseBuilder(
            androidContext(), AppDatabase::class.java,
            "contact_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    factory { get<AppDatabase>().contactDao() }
    factory { OkHttpClient.Builder().build() }
    factory { GsonBuilder().create() }
    factory { provideRetrofit(get(), get()) }
    single { provideContactsApi(get()) }
    factory { ContactRepository(get(), get()) }
    factory { GetListContactsUseCase(get()) }
    viewModel { ContactViewModel(get()) }

}

fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

fun provideContactsApi(retrofit: Retrofit): ContactService {
    return retrofit.create(ContactService::class.java)
}
