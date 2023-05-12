package com.example.fakestore.ui.home

import androidx.fragment.app.viewModels
import com.example.fakestore.R
import com.example.fakestore.databinding.FragmentHomeBinding
import com.example.fakestore.ui.base.BaseFragment
import androidx.navigation.fragment.findNavController


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutIdFragment = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()
    override fun setUp() {
        binding.recyclerViewProducts.adapter = HomeAdapter(emptyList(), viewModel)
        navigateToProductDetails()
    }

    private fun navigateToProductDetails() {
        viewModel.navigationToProductDetails.observe(viewLifecycleOwner) { event ->
            event?.getContentIfNotHandle()?.let {
                val directions = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
                findNavController().navigate(directions)
            }
        }
    }
}