package com.example.fakestore.ui.categoryDetails

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fakestore.R
import com.example.fakestore.databinding.FragmentDetailsCategoryBinding
import com.example.fakestore.ui.base.BaseFragment
import com.example.fakestore.ui.details.DetailsFragmentArgs
import com.example.fakestore.ui.home.HomeAdapter
import com.example.fakestore.ui.home.HomeFragmentDirections


class DetailsCategoryFragment : BaseFragment<FragmentDetailsCategoryBinding,DetailsCategoryViewModel>() {
    override val layoutIdFragment: Int = R.layout.fragment_details_category
    override val viewModel: DetailsCategoryViewModel by viewModels()
    private val args: DetailsCategoryFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val categoryName = args.category
        viewModel.fetchProductsByCategory(categoryName)
    }

    override fun setUp() {
        binding.recyclerViewProducts.adapter = DetailsCategoryAdapter(emptyList(), viewModel)
        navigateToProductDetails()
    }

    private fun navigateToProductDetails() {
        viewModel.navigationToProductDetails.observe(viewLifecycleOwner) { event ->
            event?.getContentIfNotHandle()?.let {
                val directions = DetailsCategoryFragmentDirections.actionDetailsCategoryFragmentToDetailsFragment(it)
                findNavController().navigate(directions)
            }
        }
    }
}