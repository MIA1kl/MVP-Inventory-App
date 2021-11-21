package com.android.mvpapp.view.item
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.android.inventory.R
import com.android.mvpapp.presenter.ItemPresenter


import kotlinx.android.synthetic.main.activity_item.*


class ItemActivity : AppCompatActivity(), MainContract.View {

    private val presenter = ItemPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)
        presenter.setView(this)
        configureUI()
        configureEditText()
        configureClickListeners()
    }

    private fun configureUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.add_item)
        if (presenter.isDrawableSelected()) {
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
//            val bottomDialogFragment = AvatarBottomDialogFragment.newInstance()
//            bottomDialogFragment.show(supportFragmentManager, "AvatarBottomDialogFragment")
        }

        saveButton.setOnClickListener {
            presenter.saveItem()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==123){
            var bmp = data?.extras?.get("data") as Bitmap
            avatarImageView.setImageBitmap(bmp)
            presenter.imageSelected(bmp)
            hideTapLabel()
        }
    }

//    override fun avatarClicked(avatar: Avatar) {
//        presenter.drawableSelected(avatar.drawable)
//        hideTapLabel()
//    }

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
