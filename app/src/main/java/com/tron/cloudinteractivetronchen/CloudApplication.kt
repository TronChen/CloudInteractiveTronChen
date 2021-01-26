package com.tron.cloudinteractivetronchen

import android.app.Application
import com.tron.cloudinteractivetronchen.data.CloudRepository
import com.tron.cloudinteractivetronchen.util.ServiceLocator
import kotlin.properties.Delegates

/**
 * An application that lazily provides a repository. Note that this Service Locator pattern is
 * used to simplify the sample. Consider a Dependency Injection framework.
 */
class CloudApplication : Application() {

    // Depends on the flavor,
    val cloudRepository: CloudRepository
        get() = ServiceLocator.provideTasksRepository(this)

    companion object {
        var instance: CloudApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
