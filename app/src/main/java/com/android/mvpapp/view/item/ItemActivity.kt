package com.android.mvpapp.view.item
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
//import androidx.core.graphics.drawable.toBitmap
import com.android.android.inventory.R
import com.android.mvpapp.model.Item
import com.android.mvpapp.presenter.ItemPresenter
import com.android.mvpapp.view.allitems.AllItemsActivity
import kotlinx.android.synthetic.main.activity_all_items.*


import kotlinx.android.synthetic.main.activity_item.*
import kotlinx.android.synthetic.main.list_item_item.*


class ItemActivity : AppCompatActivity(), MainContract.View {

    private val presenter = ItemPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title="Inventory store"
        presenter.setView(this)
        checkIntent()
        configureUI()
        configureEditText()
        configureClickListeners()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var alertDialog: AlertDialog? = null
        return when (item.itemId) {
            R.id.action_clear_all -> {
                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setTitle("Delete this item")
                alertDialogBuilder.setMessage("Are you sure you want to delete this item?")
                alertDialogBuilder.setPositiveButton("Yes") { _: DialogInterface, _: Int ->
                    presenter.deleteItem(nameEditText.text.toString())
                    val intent = Intent(this, AllItemsActivity::class.java)
                    startActivity(intent)
                }
                alertDialogBuilder.setNegativeButton("Cancel", { dialogInterface: DialogInterface, i: Int -> })

                alertDialog = alertDialogBuilder.create()
                alertDialog?.show()
                true

            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun checkIntent() {
        if (!intent.getStringExtra("name").isNullOrEmpty()){
            hideTapLabel()
            val n  = intent.getStringExtra("name")
            val price1 = intent.getStringExtra("price")
            val bitmap = intent.getByteArrayExtra("image")
            val quantity1 = intent.getStringExtra("quantity")
            val supplier1 = intent.getStringExtra("supplier")

            avatarImageView.setImageBitmap(BitmapFactory.decodeByteArray(bitmap, 0, bitmap!!.size))
            nameEditText.setText(n.toString())
            priceEditText.setText(""+price1)
            quantityEditText.setText(""+quantity1)
            supplierEditText.setText(supplier1.toString())
//            toolbar.subtitle = "Editor Mode"
        }
    }

    private fun configureUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.add_item)
        if (presenter.isImageSelected()) {
            hideTapLabel()
        }
    }


    private fun configureEditText() {
        nameEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.updateName(s.toString())
            }
        })
        priceEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.updatePrice(s.toString().toInt())
            }
        })

        quantityEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.updateQuantity(s.toString().toInt())
            }
        })

        supplierEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.updateSupplier(s.toString())
            }
        })
    }

    private fun configureClickListeners() {
        avatarImageView.setOnClickListener {
            var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i, 123)
        }
        saveButton.setOnClickListener {
            if (!intent.getStringExtra("name").isNullOrEmpty()){
                configureEditText()
                configureUI()
                avatarImageView.setDrawingCacheEnabled(true)
                val item = Item(nameEditText.text.toString(),
                    priceEditText.text.toString().toInt(), quantityEditText.text.toString().toInt(),supplierEditText.text.toString(), avatarImageView.getDrawingCache())

                presenter.updateThisItem(item)
            }
            else{
                presenter.saveItem()
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode != RESULT_CANCELED){
            if(requestCode==123){
                var bmp = data?.extras?.get("data") as Bitmap
                avatarImageView.setImageBitmap(bmp)
                presenter.imageSelected(bmp)
                hideTapLabel()
            }
        }
    }


    private fun hideTapLabel() {
        tapLabel.visibility = View.INVISIBLE
    }


    override fun showAvatarBitmap(resourceId: Bitmap) {
        avatarImageView.setImageBitmap(resourceId)
    }

    override fun showItemSaved() {
        Toast.makeText(this,getString(R.string.item_saved),Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun showItemSavedError() {
        Toast.makeText(this,getString(R.string.error_saving_item),Toast.LENGTH_SHORT).show()
    }


}
