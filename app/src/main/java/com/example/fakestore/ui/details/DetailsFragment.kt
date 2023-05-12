package com.example.fakestore.ui.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.fakestore.R
import com.example.fakestore.databinding.FragmentDetailsBinding
import com.example.fakestore.ui.base.BaseFragment

class DetailsFragment : BaseFragment<FragmentDetailsBinding, DetailsViewModel>() {
    override val layoutIdFragment = R.layout.fragment_details
    override val viewModel: DetailsViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val itemId = args.itemId
        viewModel.getProductById(itemId)
        viewModel.setProductId(itemId)
        Log.d("DetailsFragment", "Called getProductById with itemId: $itemId")
    }

    override fun setUp() {}

}