package com.example.fakestore.ui.category

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fakestore.R
import com.example.fakestore.databinding.FragmentCategoryBinding
import com.example.fakestore.ui.base.BaseFragment


class CategoryFragment : BaseFragment<FragmentCategoryBinding,CategoryViewModel>() {
    override val layoutIdFragment: Int = R.layout.fragment_category
    override val viewModel: CategoryViewModel by viewModels()
    override fun setUp() {
        binding.listener = viewModel
        navigateToDetails()
    }

    private fun navigateToDetails() {
        viewModel.navigationToDetails.observe(viewLifecycleOwner) { event ->
            event?.getContentIfNotHandle()?.let { category ->
                val directions = CategoryFragmentDirections.actionCategoryFragmentToDetailsCategoryFragment(category)
                findNavController().navigate(directions)
            }
        }
    }
}