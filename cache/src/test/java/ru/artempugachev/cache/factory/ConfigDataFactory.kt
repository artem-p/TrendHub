package ru.artempugachev.cache.factory

import ru.artempugachev.cache.model.Config


object ConfigDataFactory {

    fun makeCachedConfig(): Config {
        return Config(DataFactory.randomInt(), DataFactory.randomLong())
    }

}