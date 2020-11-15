package com.ealmazan.marvelcodechallenge.framework.di

import com.ealmazan.marvelcodechallenge.data.CharacterDataSource
import com.ealmazan.marvelcodechallenge.data.CharacterRemoteDataSource
import com.ealmazan.marvelcodechallenge.data.repository.CharacterRepository
import com.ealmazan.marvelcodechallenge.data.repository.CharacterRepositoryImp
import com.ealmazan.marvelcodechallenge.framework.persistence.CharacterDataSourceImp
import com.ealmazan.marvelcodechallenge.framework.rest.CharacterRemoteDataSourceImp
import com.ealmazan.marvelcodechallenge.framework.rest.MarvelAPI
import com.ealmazan.marvelcodechallenge.presentation.viewmodel.CharacterViewModel
import com.ealmazan.marvelcodechallenge.usecases.GetCharacterUseCase
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

/**
 * Usaremos esta variable para añadir las dependencias de la aplicación
 */
val appModules = module {
    viewModel { CharacterViewModel(get()) }
    single { GetCharacterUseCase(get()) }
    factory<CharacterRepository> { CharacterRepositoryImp(get(), get()) }
    factory<CharacterRemoteDataSource> { CharacterRemoteDataSourceImp(get()) }
    factory<CharacterDataSource> { CharacterDataSourceImp() }
    single { MarvelAPI() }
}