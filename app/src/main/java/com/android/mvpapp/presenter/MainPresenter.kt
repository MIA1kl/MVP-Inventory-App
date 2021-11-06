package com.android.mvpapp.presenter

class MainPresenter: MainContract.Actions {
    var _views: MainContract.Views? = null

    constructor(_views: MainContract.Views?){
        this._views = _views
        initScreen()
    }

    override fun initScreen(){
        _views?.setupViews()
        _views?.setupListeners()
    }
}