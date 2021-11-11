package com.android.mvpapp.presenter

class MainPresenter: MainContract.Actions {
    var _views: MainContract.MainViews? = null
    var _views1: MainContract.Views? = null

    constructor(_views: MainContract.MainViews?){
        this._views = _views
        initScreen()
    }

    constructor(_views1: MainContract.Views?){
        this._views1 = _views1
        initScreen()
    }

    override fun initScreen(){
        _views?.setupViews()
        _views1?.setupListeners()
    }

    override fun addItemToRecyclerView() {
        _views?.addItemToRecyclerView(_views1?.getItem()!!)
    }

    override fun addItemToDatabase() {

    }

    override fun fetchAllItemsFromDatabase() {

    }
}