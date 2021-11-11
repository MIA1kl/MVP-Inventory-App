package com.android.mvpapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.android.mvpapp.R
import com.android.mvpapp.adapter.MainAdapter
import com.android.mvpapp.model.ItemModel
import com.android.mvpapp.presenter.MainContract
import com.android.mvpapp.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_add_item.*
import kotlin.random.Random

class AddItemActivity : AppCompatActivity() , MainContract.Views{

    lateinit var presenter1: MainPresenter
//    lateinit var mAdapter1: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title="Inventory store"
        presenter1 = MainPresenter(this)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
            R.id.basket -> {
                Toast.makeText(this,"Item deleted", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }


    override fun setupListeners() {
        btn_add.setOnClickListener{
            val task = edt_write_task.text.toString()
            if(task.equals("")){
                Toast.makeText(this, "Write a text",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                presenter1.addItemToRecyclerView()
                edt_write_task.setText("")
                Toast.makeText(this,"Item was added",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItem(): ItemModel {
        return ItemModel(
            Random.nextLong(),
            edt_write_task.text.toString()
        )
    }


}