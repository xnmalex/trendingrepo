package com.gojek.assignment.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gojek.assignment.injection.ViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {

//    @Binds
//    @IntoMap
//    @ViewModelKey(MoviesViewModel::class)
//    abstract fun bindsMoviesViewModel(moviesViewModel: MoviesViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(MovieDetailsViewModel::class)
//    abstract fun bindsMovieDetailsViewModel(movieDetailsViewModel: MovieDetailsViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
    @MapKey
    internal annotation class ViewModelKey(val value: KClass<out ViewModel>)
}