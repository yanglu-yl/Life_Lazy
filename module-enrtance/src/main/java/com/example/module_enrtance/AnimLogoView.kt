package com.example.module_enrtance

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.os.Build
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.util.SparseArray
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.Q)
class AnimLogoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {

    var mLogoTexts = SparseArray<String>()
    var mQuietPoints = SparseArray<PointF>()
    var mRadonPoints = SparseArray<PointF>()
    var isAutoPlay: Boolean ?= null
    var isShowGradient: Boolean ?= null
    var isOffsetAnimEnd: Boolean = false
    var mPaint: Paint ?= null
    var mTextSize: Int ?= null
    var mTextColor: Int ?= null
    var mGradientColor: Int ?= null
    var mTextPadding: Int ?= null
    var mOffsetDuration: Int ?= null
    var mOffsetAnimator: ValueAnimator ?= null
    var mGradientAnimator: ValueAnimator ? = null
    var mOffsetAnimProgress: Float = 0.0f
    var mLinearGradient: LinearGradient ?= null
    var mWidth: Int ?= null
    var mHeight: Int ?= null
    var mLogoOffset: Int ?= null
    var mGradientListener: Animator.AnimatorListener ?= null
    var mMatrixTranslate: Int ?= null
    var mGradientMatrix: Matrix ?= null
    var mGradientDuration: Int ?= null

    init {
        val ta: TypedArray =context.obtainStyledAttributes(attrs, R.styleable.AnimLogoView)
        var logoName: String? = ta.getString(R.styleable.AnimLogoView_logoName)
        mTextColor = ta.getColor(R.styleable.AnimLogoView_textColor, ANIM_LOGO_TEXT_COLOR)
        mGradientColor = ta.getColor(R.styleable.AnimLogoView_gradientColor, ANIM_LOGO_GRADIENT_COLOR)
        mTextSize = ta.getDimensionPixelSize(R.styleable.AnimLogoView_textSize, ANIM_LOGO_TEXT_SIZE)
        mTextPadding = ta.getDimensionPixelSize(R.styleable.AnimLogoView_textPadding, DEFAULT_TEXT_PADDING)
        mOffsetDuration = ta.getInt(R.styleable.AnimLogoView_offsetAnimDuration, ANIM_LOGO_DURATION)
        mGradientDuration = ta.getInt(R.styleable.AnimLogoView_gradientAnimDuration, ANIM_LOGO_GRADIENT_DURATION)
        isAutoPlay = ta.getBoolean(R.styleable.AnimLogoView_autoPlay, true)
        isShowGradient = ta.getBoolean(R.styleable.AnimLogoView_showGradient, false)
        mLogoOffset = ta.getDimensionPixelOffset(R.styleable.AnimLogoView_verticalOffset, 0)
        ta.recycle()
        if (TextUtils.isEmpty(logoName)){
            logoName = DEFAULT_LOGO
        }
        fillLogoTextArray(logoName!!)
        initPaint()
        initOffsetAnimation()
        Log.d("haha","init")
    }

    //fill the text to array
    private fun fillLogoTextArray(logoName: String){

        if (logoName.isEmpty()) return
        if (mLogoTexts.size() > 0) mLogoTexts.clear()

        for (i in 0 until  logoName.length){
            var c = logoName[i]
            var s = c.toString()
            mLogoTexts.put(i , s)
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun initPaint(){
        mPaint = Paint()
        mPaint!!.setAntiAlias(true)
        mPaint!!.setStyle(Paint.Style.FILL)
        mPaint!!.setStrokeCap(Paint.Cap.ROUND)

        mPaint!!.textSize = mTextSize!!.toFloat()
        mPaint!!.color = mTextColor!!
        Log.d("haha","initPaint")
    }

    //init the translation animation
    private fun initOffsetAnimation() {
        mOffsetAnimator = ValueAnimator.ofFloat(0F, 1F)
        mOffsetAnimator!!.setInterpolator(AccelerateDecelerateInterpolator())
        mOffsetAnimator!!.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener{

            override fun onAnimationUpdate(p0: ValueAnimator?) {
                if (mQuietPoints.size() <=0 || mRadonPoints.size() <= 0) return
                mOffsetAnimProgress = p0!!.animatedValue as Float
                invalidate()
            }

        })

        mOffsetAnimator!!.addListener(object : AnimatorListenerAdapter(){

            override fun onAnimationEnd(animation: Animator?) {
                if (mGradientAnimator != null && isShowGradient!!){
                    isOffsetAnimEnd = true
                    mPaint!!.setShader(mLinearGradient)
                    mGradientAnimator!!.start()
                }
            }
        })
        mOffsetAnimator!!.setDuration(mOffsetDuration!!.toLong())
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (visibility == VISIBLE && isAutoPlay!!) mOffsetAnimator!!.start()
        Log.d("haha","onattach")
    }

    override fun onDetachedFromWindow() {
        Log.d("haha","ondetach")
        // release animation
        if (mOffsetAnimator != null && mOffsetAnimator!!.isRunning){
            mOffsetAnimator!!.cancel()
        }
        if (mGradientAnimator != null && mGradientAnimator!!.isRunning) mGradientAnimator!!.cancel()
        super.onDetachedFromWindow()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
        initLogoCoordinate()
        initGradientAnimation(w)
        Log.d("haha","onSize")
    }

    private fun initLogoCoordinate(){
        if (mWidth == 0 || mHeight == 0){
            Log.w(this.javaClass.getSimpleName(), "The view has not measure, it will auto init later.")
            return
        }
        var centerY = mHeight!!/2f + mPaint!!.textSize / 2 + mLogoOffset!!
        // calculate the final xy of the text
        var totalLength: Float = 0f

        for (i in 0 until mLogoTexts.size()){
            var str: String = mLogoTexts.get(i)
            var currentLength: Float = mPaint!!.measureText(str)

            if (i != mLogoTexts.size() - 1){
                totalLength += currentLength + mTextPadding!!
            }else totalLength += currentLength

            // the draw width of the logo must small than the width of this AnimLogoView
            if (totalLength > mWidth!!){
                throw IllegalStateException("The text of logoName is too large that this view can not display all text")
            }

            var startX = (mWidth!! - totalLength) / 2
            if (mQuietPoints.size() > 0) mQuietPoints.clear()
            for (i in 0 until mLogoTexts.size()){
                var str = mLogoTexts.get(i)
                var currentLength = mPaint!!.measureText(str)
                mQuietPoints.put(i, PointF(startX, centerY))
                startX += currentLength + mTextPadding!!
            }

            // generate random start xy of the text
            if (mRadonPoints.size() > 0) mRadonPoints.clear()
            for (i in 0 until mLogoTexts.size()){
                mRadonPoints.put(i, PointF(
                    (Math.random()*mWidth!!).toFloat(),
                    (Math.random()* mHeight!!).toFloat()
                ))
            }
        }
    }

    /**
     * 监听offset动画状态
     */
    public fun addOffestAnimListener(listener: Animator.AnimatorListener){
        mOffsetAnimator!!.addListener(listener)
    }

    /**
     * 监听gradient动画状态
     */
    public fun addGradientAnimListener(listener: Animator.AnimatorListener) {
        mGradientListener = listener
    }

    /**
     * 开启动画
     */
    public fun startAnimation(){
        if (visibility == VISIBLE){
            if (mOffsetAnimator!!.isRunning){
                mOffsetAnimator!!.cancel()
            }
            isOffsetAnimEnd = false
            mOffsetAnimator!!.start()
        }else Log.w("AnimLogoView", "The view is not visible, not to play the animation .")
    }

    //init the gradient animation
    private fun initGradientAnimation(width: Int){
        if (width == 0){
            Log.w(this.javaClass.getSimpleName(), "The view has not measure, it will auto init later.")
            return
        }
        mGradientAnimator = ValueAnimator.ofInt(0, 2 * width)
        if (mGradientListener != null) mGradientAnimator!!.addListener(mGradientListener)
        mGradientAnimator!!.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener{
            override fun onAnimationUpdate(p0: ValueAnimator?) {
                mMatrixTranslate = p0!!.getAnimatedValue() as Int
                invalidate()
            }
        })
        mLinearGradient = LinearGradient(-(width.toFloat()), 0f, 0f, 0f, intArrayOf(mTextColor!!,
            mGradientColor!!, mTextColor!!
        ), floatArrayOf(0f, 0.5f, 1f), Shader.TileMode.CLAMP)

        mGradientMatrix = Matrix()
        
        mGradientAnimator!!.setDuration(mGradientDuration!!.toLong())
    }

    override fun onDraw(canvas: Canvas?) {
        Log.d("haha","onDraw")
        if (!isOffsetAnimEnd){// offset animation
            mPaint!!.setAlpha(Math.min(255, 255 * mOffsetAnimProgress.toInt() + 100))
            for (i in 0 until mQuietPoints.size()){
                var quietP = mQuietPoints.get(i)
                var radon = mRadonPoints.get(i)
                var x = (radon.x + (quietP.x - radon.x) * mOffsetAnimProgress) as Float
                var y = (radon.y + (quietP.y - radon.y) * mOffsetAnimProgress) as Float
                canvas!!.drawText(mLogoTexts.get(i), x, y, mPaint!!)
            }
        } else {// gradient animation
            for (i in 0 until mQuietPoints.size()){
                var quietP = mQuietPoints.get(i)
                canvas!!.drawText(mLogoTexts.get(i), quietP.x, quietP.y, mPaint!!)
            }
            mGradientMatrix!!.setTranslate(mMatrixTranslate!!.toFloat(), 0f )
            mLinearGradient!!.setLocalMatrix(mGradientMatrix)
        }
    }

    companion object{
        val DEFAULT_LOGO = "SEAGAZER"
        val ANIM_LOGO_TEXT_COLOR = Color.BLACK
        val ANIM_LOGO_TEXT_SIZE = 30
        val DEFAULT_TEXT_PADDING = 10
        val ANIM_LOGO_DURATION = 1500
        val ANIM_LOGO_GRADIENT_COLOR = Color.YELLOW
        val ANIM_LOGO_GRADIENT_DURATION = 1500
    }

    /**
     * 设置logo名
     */
    public fun setLogoText(logoName: String){
        fillLogoTextArray(logoName)
        initLogoCoordinate()
    }

    /**
     * 设置logo文字动效时长
     */
    public fun setOffsetAnimDuration(duration: Int){
        mOffsetDuration = duration
        initOffsetAnimation()
    }

    /**
     * 设置logo文字渐变动效时长
     */
    public fun setGradientAnimDuration(duration: Int){
        mGradientDuration = duration
        initGradientAnimation(mWidth!!)
    }

    /**
     * 设置logo文字渐变颜色
     */
    public fun setGradientColor(gradientColor: Int){
        this.mGradientColor = gradientColor
    }

    /**
     * 设置
     */
    public fun setShowGradient(isShowGradient: Boolean){
        this.isShowGradient = isShowGradient
    }

    public fun setTextPadding(padding: Int){
        mTextPadding = padding
        initLogoCoordinate()
    }

    public fun setTextColor(color: Int){
        mTextColor = color
        initPaint()
    }

    public fun setTextSize(size: Int){
        mTextSize = size
        initPaint()
    }

}