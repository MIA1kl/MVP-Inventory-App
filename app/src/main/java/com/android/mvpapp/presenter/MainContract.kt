package com.android.mvpapp.presenter

interface MainContract {
    interface Views{
        fun setupViews()
        fun setupListeners()
    }

    interface  Actions{
        fun initScreen()
    }
}