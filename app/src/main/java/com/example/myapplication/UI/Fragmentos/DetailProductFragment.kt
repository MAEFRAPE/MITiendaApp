package com.example.myapplication.UI.Fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.myapplication.UI.viewmodels.ProductViewModel

import com.example.myapplication.databinding.FragmentDetailProductBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [DetailProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailProductFragment : Fragment() {

    private var _binding:FragmentDetailProductBinding?=null

    private val binding get() = _binding!!

    private val productSelectViewModel:ProductViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentDetailProductBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        productSelectViewModel.selected.observe(viewLifecycleOwner, Observer { producto ->
            binding.tituloDetalle.text= producto.name
            binding.detalleDescripcion.text= producto.description
            Glide.with(binding.root).load(producto.image).into(binding.detalleImage)
        })
    }
}