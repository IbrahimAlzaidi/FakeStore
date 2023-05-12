package com.example.fakestore.ui.home

import com.example.fakestore.R
import com.example.fakestore.data.model.ProductsItem
import com.example.fakestore.ui.base.BaseAdapter
import com.example.fakestore.util.OnClickListener

class HomeAdapter(
    items: List<ProductsItem>,
    listener: OnClickListener
) : BaseAdapter<ProductsItem>(items, listener, R.layout.item_products)