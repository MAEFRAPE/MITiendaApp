package com.example.myapplication.UI.Fragmentos

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.myapplication.Data.Models.User
import com.example.myapplication.UI.Activities.LoginActivity
import com.example.myapplication.UI.Activities.MainActivity
import com.example.myapplication.UI.viewmodels.LoginViewModel

import com.example.myapplication.databinding.FragmentProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val loginViewmodels: LoginViewModel by viewModel()
    private val binding get() = _binding!!
    private val Solicitar_Permiso_camara = 1
    private val Solicitar_imagen= 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onStart() {
        super.onStart()
        loginViewmodels.loginIn()
        verificarPermiso()
        binding.profileImage.setOnClickListener {
            Log.d("Click","aqui se dio click")
            if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED ){
                Log.d("Click","Ya tiene el permiso")
                Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
                    startActivityForResult(intent,Solicitar_imagen)
                    /*intent.resolveActivity(requireActivity().packageManager)?.also {
                        Log.d("Click","se solicita la imagen")

                    }*/

                }
            }
        }
        binding.profileLogOut.setOnClickListener{

            loginViewmodels.logout()
        }
        loginViewmodels.user.observe(viewLifecycleOwner, Observer { user ->

            if(user== null){
                val intent= Intent(requireContext(),LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }else{

                binding.profileName.text= user.displayName
                if(user.photoUrl != null){
                    Glide.with(binding.root).load(user.photoUrl).into(binding.profileImage)

                }
                loginViewmodels.loadUser(user.uid)
                loginViewmodels.userDB.observe(viewLifecycleOwner, Observer { userDB ->
                    binding.profileTel.text= userDB.telefono
                })
            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK){
            Log.d("Click","el resultado fue OK")
            if (requestCode == Solicitar_imagen){
                Log.d("Click","SE va colocar la imagen")
                val bitmap= data?.extras?.get("data") as Bitmap
                /*binding.profileImage.setImageBitmap(bitmap)*/
                loginViewmodels.uploadImage(bitmap)
            }
        }

    }

    private fun verificarPermiso(){
        if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED ){
            Log.d("Click","se va solcitar el permiso")
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CAMERA),Solicitar_Permiso_camara)
        }

    }

}