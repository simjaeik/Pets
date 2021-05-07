package com.lacuc.pets.ui.manage.group.gallery.detail

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView
import io.reactivex.rxjava3.subjects.PublishSubject
import timber.log.Timber

class ZoomImageView(
    val container: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(container, attrs, defStyleAttr),
    GestureDetector.OnGestureListener {

    constructor(container: Context) : this(container, null, 0)

    constructor(container: Context, attrs: AttributeSet?) : this(container, attrs, 0)

    private val detector = GestureDetector(container, this)

    val tabEvent = PublishSubject.create<Unit>()

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return detector.onTouchEvent(event)
    }

    override fun onDown(e: MotionEvent?): Boolean {
        return true
    }

    override fun onShowPress(e: MotionEvent?) {

    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        Timber.d("SingleTapUp")
        tabEvent.onNext(Unit)
        return true
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        return false
    }

    override fun onLongPress(e: MotionEvent?) {
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return false
    }
}