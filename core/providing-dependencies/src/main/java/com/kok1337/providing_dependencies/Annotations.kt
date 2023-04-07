package com.kok1337.providing_dependencies

import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
annotation class DependenciesKey(val value: KClass<out Dependencies>)