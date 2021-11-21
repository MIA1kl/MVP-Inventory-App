package com.android.mvpapp.view.allitems

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import com.android.android.inventory.R
import com.android.mvpapp.presenter.AllItemsPresenter
import com.android.mvpapp.view.item.ItemActivity
import kotlinx.android.synthetic.main.activity_all_items.*
import kotlinx.android.synthetic.main.content_all_items.*

class AllItemsActivity : AppCompatActivity(), AllItemsContract.View {

    private val adapter = ItemAdapter(mutableListOf())
    private val presenter = AllItemsPresenter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_items)
        setSupportActionBar(toolbar)
        presenter.setView(this)
        itemsRecyclerView.layoutManager = LinearLayoutManager(this)
        itemsRecyclerView.adapter = adapter
        presenter.getAllItems().observe(this, Observer {
            it?.let { itemList ->
                adapter.updateItems(itemList)
            }
        })
        fab.setOnClickListener {
            startActivity(Intent(this, ItemActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_clear_all -> {
                presenter.clearAllItems()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showItemCleared() {
        Toast.makeText(this,getString(R.string.item_empty),Toast.LENGTH_SHORT).show()
    }
}
