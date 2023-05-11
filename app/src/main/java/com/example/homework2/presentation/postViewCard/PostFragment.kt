package com.example.homework2.presentation.postViewCard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.example.homework2.R
import com.example.homework2.data.DataProfile
import com.example.homework2.databinding.ActivityPostBinding
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment : Fragment(R.layout.activity_post) {
    private val binding by viewBinding(ActivityPostBinding:: bind)

    companion object {
        fun createIntent(context: Context, data: DataProfile) =
            Intent(context, PostFragment::class.java).apply {
                putExtra("ARG_TEXT_KEY", data)
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*
        val data = intent.extras?.getParcelable<DataProfile>("ARG_TEXT_KEY")
        with(binding) {
            textViewName.text = data?.name
            textViewDate.text = data?.date
            imageViewPost.load(data?.link)
            textViewPost.text = data?.title
        }

         */

    }

}