package ua.cn.stu.simplemvvm

import android.app.Application
import ua.cn.stu.foundation.BaseApplication
import ua.cn.stu.foundation.model.dispatchers.MainThreadDispatcher
import ua.cn.stu.simplemvvm.model.colors.InMemoryColorsRepository
import java.util.concurrent.Executors

/**
 * Here we store instances of model layer classes.
 */
class App : Application(), BaseApplication {

    /**
     * Place your singleton scope dependencies here
     */
    override val singletonScopeDependencies: List<Any> = listOf(
        InMemoryColorsRepository()
    )

}