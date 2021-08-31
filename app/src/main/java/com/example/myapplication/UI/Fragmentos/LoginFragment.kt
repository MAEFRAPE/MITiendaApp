package com.example.myapplication.UI.Fragmentos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.UI.Activities.MainActivity
import com.example.myapplication.UI.viewmodels.HomeViewModels
import com.example.myapplication.UI.viewmodels.LoginViewModel
import com.example.myapplication.Utils.isValidEmail
import com.example.myapplication.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel



class LoginFragment : Fragment() {
    private  var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    private val loginViewmodels: LoginViewModel by viewModel()
    private val homeViewModel:HomeViewModels by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding= FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        homeViewModel.loadStoreInfo()
        homeViewModel.info.observe(viewLifecycleOwner, Observer { info ->

            Glide.with(binding.root).load(info.image).into(binding.imgeLogo)
        })

        binding.buttonRegistro.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registroFragment)
        }

        binding.buttonReseteo.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_resetFragment)
        }

        binding.buttonSignIn.setOnClickListener {
            var isValid= true
            if (!binding.UsernameCampo.text.isValidEmail()){
                isValid= false
                binding.Username.error="Correo Electronico No Valido"
            }else{
                binding.Username.error= null
            }
            if (binding.PasswordCampo.text.toString().length < 8 ){
                isValid= false
                binding.Password.error="La contraseña es muy corta"
            }else{
                binding.Password.error= null
            }
            if(isValid){
               loginViewmodels.login(binding.UsernameCampo.text.toString(),binding.PasswordCampo.text.toString())
            }
        }

        loginViewmodels.user.observe(viewLifecycleOwner, Observer { user ->

            if(user != null){

                val intent = Intent(requireActivity(), MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
        }

        })

        loginViewmodels.error.observe(viewLifecycleOwner, Observer { error->
            Log.d("Click",error)
            Toast.makeText(requireContext(),error,Toast.LENGTH_LONG).show()
        })



    }

}