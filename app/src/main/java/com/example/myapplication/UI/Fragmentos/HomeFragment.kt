package com.example.myapplication.UI.Fragmentos

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.preference.PreferenceManager
import com.bumptech.glide.Glide
import com.example.myapplication.Data.Models.Storeinfo
import com.example.myapplication.UI.viewmodels.CarruselViewModel
import com.example.myapplication.UI.viewmodels.HomeViewModels
import com.example.myapplication.databinding.FragmentHomeBinding
import com.limerse.slider.model.CarouselItem
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.osmdroid.config.Configuration.*
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.overlay.Marker
import org.osmdroid.util.GeoPoint
import java.security.KeyStore
import kotlin.math.log


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private  var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModels by viewModel()
    private val carruseviewModels:CarruselViewModel by viewModel()

    private var mostrar= true

    private lateinit var store :Storeinfo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding= FragmentHomeBinding.inflate(inflater,container,false)
        getInstance().load(requireContext(), PreferenceManager.getDefaultSharedPreferences(requireContext()))
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        carruseviewModels.loadImagenes()
        homeViewModel.loadStoreInfo()
        homeViewModel.info.observe(viewLifecycleOwner, Observer { info ->
            store = info
            binding.tituloHome.text= info.name
            binding.homeName.text=info.name
            binding.homePhone.text=info.description
            binding.homeAddress.text=info.adress
            Glide.with(binding.root).load(info.image).into(binding.homeImage)

            if(ContextCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED) {
                binding.homeOsmMap.visibility = View.GONE
                binding.homeOsmMap.setTileSource(TileSourceFactory.MAPNIK)
                val mapController = binding.homeOsmMap.controller
                mapController.setZoom(18.0)
                val startPoint = GeoPoint(info.lat!!, info.lng!!)
                mapController.setCenter(startPoint)

                val marker = Marker(binding.homeOsmMap)
                marker.position = startPoint
                marker.setAnchor(
                    Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM
                )
                marker.title = store.name
                binding.homeOsmMap.overlays.add(marker)
            }
        })

        binding.textButton.setOnClickListener{
            if (mostrar){
                binding.homeOsmMap.visibility= View.VISIBLE
                binding.textButton.text="Ocultar Ubicacion"
                mostrar=false
            }else{
                binding.homeOsmMap.visibility= View.GONE
                binding.textButton.text="Ver Ubicacion"
                mostrar=true
            }



        }


        binding.carousel.registerLifecycle(lifecycle)
        carruseviewModels.carrusel.observe(viewLifecycleOwner, Observer { carrusel ->
            Log.d("Click",carrusel.toString())
            binding.carousel.setData(carrusel)

        })



        /*binding.carousel.setData( listOf(

            CarouselItem(
                imageUrl = "https://i.ytimg.com/vi/8kP1uxB6a1o/maxresdefault.jpg"

            ) ,
            CarouselItem(
                imageUrl = "https://c8.alamy.com/compes/2c45cyp/minimercado-bicicleta-al-aire-libre-con-girasoles-y-una-maquina-expendedora-santiago-del-teide-tenerife-islas-canarias-espana-2c45cyp.jpg"

            ),
            CarouselItem(
                imageUrl = "https://cr00.epimg.net/radio/imagenes/2020/03/31/nacional/1585611285_931799_1585611482_noticia_normal_recorte1.jpg"

            ),
            CarouselItem(
                imageUrl = "https://www.vanguardia.com/binrepository/716x477/0c0/0d0/none/12204/MTWJ/distrialgusto_comercializadores__de_productos_de_la_canasta_familiar_VL418932_MG19902093.jpg"

            ),
            CarouselItem(
                imageUrl = "https://www.eltiempo.com/files/article_main/uploads/2017/11/18/5a1099d2a5e3d.jpeg"

            ),
        )
        )*/
        binding.carousel.autoPlayDelay = 2000
        binding.carousel.showCaption=true

    }









}