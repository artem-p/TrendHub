package ru.artempugachev.mobileui

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.artempugachev.domain.executor.PostExecutionThread
import javax.inject.Inject


/**
 * Domain module knows nothing about UI implementation.
 * It only has a reference to the post execution thread.
 * This approach allows us to achieve the abstraction by keeping
 * any android framework references outside of the domain layer
 *
 * The UI module allows us to implement the user interface of our application
 * for a specific form factor or framework.
 *
 * In this lesson, we're going to be creating an implementation of the
 * PostExecutionThread interface that has allowed us to provide an abstraction for
 * other layers for when it comes to the threading method used for work
 * to be executed on.
 * */
class UiThread @Inject constructor() : PostExecutionThread {

    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}