package com.android.mvpapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.mvpapp.view.AddItemActivity
import com.android.mvpapp.R
import com.android.mvpapp.adapter.MainAdapter
import com.android.mvpapp.model.ItemModel
import com.android.mvpapp.presenter.MainContract
import com.android.mvpapp.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_add_item.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(), MainContract.MainViews {

    lateinit var presenter: MainPresenter
    lateinit var mAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Inventory store"

        presenter = MainPresenter(this)

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            val intent = Intent(this, AddItemActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
            R.id.basket -> {
                Toast.makeText(this,"All Items deleted",Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    override fun setupViews() {
        mAdapter = MainAdapter(this)
        task_recyclerView.apply{
            layoutManager = LinearLayoutManager(this.context)
            adapter = mAdapter
        }
    }

    override fun addItemToRecyclerView(item: ItemModel) {
        mAdapter.addItem(item)
    }

    override fun addItemToRecyclerViewList(list: ArrayList<ItemModel>) {
        mAdapter.setItem(list)

    }

}