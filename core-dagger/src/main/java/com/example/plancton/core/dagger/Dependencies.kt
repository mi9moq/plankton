package com.example.plancton.core.dagger

import androidx.fragment.app.Fragment
import dagger.MapKey
import kotlin.reflect.KClass

interface Dependencies

@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class DependenciesKey(val value: KClass<out Dependencies>)

interface HasDependencies {

    val dependenciesMap: Map<Class<out Dependencies>, Dependencies>

}

inline fun <reified D : Dependencies> Fragment.findDependencies(): D {
    val hasDependencies = parentFragment as? HasDependencies
        ?: activity as? HasDependencies
        ?: activity?.application as? HasDependencies

    return hasDependencies
        ?.dependenciesMap
        ?.get(D::class.java) as? D
        ?: error("No dependencies ${D::class.java} for ${this::class.java}")
}
