package com.android.mvpapp.view.item
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.android.inventory.R
import com.android.mvpapp.model.Avatar
import com.android.mvpapp.presenter.ItemPresenter
import com.android.mvpapp.presenter.MainContract
import com.android.mvpapp.view.avatars.AvatarAdapter
import com.android.mvpapp.view.avatars.AvatarBottomDialogFragment


import kotlinx.android.synthetic.main.activity_item.*


class ItemActivity : AppCompatActivity(), AvatarAdapter.AvatarListener, MainContract.View {

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
            val bottomDialogFragment = AvatarBottomDialogFragment.newInstance()
            bottomDialogFragment.show(supportFragmentManager, "AvatarBottomDialogFragment")
        }

        saveButton.setOnClickListener {
            presenter.saveItem()
        }
    }

    override fun avatarClicked(avatar: Avatar) {
        presenter.drawableSelected(avatar.drawable)
        hideTapLabel()
    }

    private fun hideTapLabel() {
        tapLabel.visibility = View.INVISIBLE
    }


    override fun showAvatarDrawable(resourceId: Int) {
        avatarImageView.setImageResource(resourceId)
    }

    override fun showItemSaved() {
        Toast.makeText(this,getString(R.string.item_saved),Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun showItemSavedError() {
        Toast.makeText(this,getString(R.string.error_saving_item),Toast.LENGTH_SHORT).show()
    }
}
