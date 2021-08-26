package com.example.myapplication.UI.Fragmentos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.UI.Listener.OnProductListener
import com.example.myapplication.UI.Adaptadores.ProductAdapter
import com.example.myapplication.Data.Models.Producto
import com.example.myapplication.R
import com.example.myapplication.UI.viewmodels.HomeViewModels
import com.example.myapplication.UI.viewmodels.ProductViewModel
import com.example.myapplication.databinding.FragmentProductBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 * Use the [ProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductFragment : Fragment() {
    private lateinit var    productAdapter: ProductAdapter
    private  lateinit var productManager:GridLayoutManager

    private var _binding: FragmentProductBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val productviemodel:ProductViewModel by sharedViewModel()
    private val storeInfoVieModel: HomeViewModels by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        productAdapter= ProductAdapter(
            listOf()
        )

        productAdapter.listener=object : OnProductListener {
            override fun onClick(product: Producto) {
                Log.d("Click",product.name!!)
                productviemodel.loadProductSelect(product)
                findNavController().navigate(R.id.action_productFragment_to_detailProductFragment)
            }
        }
        productManager= GridLayoutManager(requireContext(),2)
        binding.ProductRecycler.apply {
            adapter=productAdapter
            layoutManager=productManager
        }

        productviemodel.loadProduct()
        productviemodel.product.observe(viewLifecycleOwner, Observer { product->
            productAdapter.newDataset(product)

        })
        storeInfoVieModel.loadStoreInfo()
        storeInfoVieModel.info.observe(viewLifecycleOwner, Observer { info ->
            Glide.with(binding.root).load(info.image).into(binding.mitiendaProductImage)

        })

    }


}