package com.android.mvpapp.view.avatars

import com.android.android.inventory.R
import com.android.mvpapp.model.Avatar

object AvatarStore {
    val AVATARS: List<Avatar> by lazy {
        val avatars = mutableListOf<Avatar>()

        avatars.add(Avatar(R.drawable.ic_launcher_background))


        avatars
    }
}