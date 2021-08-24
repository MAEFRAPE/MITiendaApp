package com.example.myapplication.UI.Fragmentos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.Observer
import com.example.myapplication.UI.Activities.LoginActivity
import com.example.myapplication.UI.viewmodels.LoginViewModel
import com.example.myapplication.Utils.isValidEmail

import com.example.myapplication.databinding.FragmentRegistroBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 * Use the [RegistroFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegistroFragment : Fragment() {
    private var _binding: FragmentRegistroBinding?= null

    private val binding get() = _binding!!

    private val loginViewmodels: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentRegistroBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.buttonResgistrar.setOnClickListener {
            var isValid= true
            if (!binding.EmailCampos.text.isValidEmail()){
                isValid= false
                binding.Email.error="Correo Electronico No Valido"
            }else{
                binding.Email.error= null
            }

            if (binding.nombresCampos.text.toString().length < 3 ){
                isValid= false
                binding.nombres.error="El tamaño del nombre es muy corto"
            }else{
                binding.nombres.error= null
            }

            if (binding.apellidosCampos.text.toString().length < 3 ){
                isValid= false
                binding.apellidos.error="El tamaño del apellido es muy corto"
            }else{
                binding.apellidos.error= null
            }
            if (binding.Password1Campo.text.toString().length < 8 ){
                isValid= false
                binding.Password1.error="La contraseña es muy corta"
            }else{
                binding.Password1.error= null
            }
            if (binding.Password2Campo.text.toString()!= binding.Password1Campo.text.toString() ){
                isValid= false
                binding.Password2.error="Valide su contraseña,debe ser la misma"
            }else{
                binding.Password2.error= null
            }
            if (binding.telefonoCampos.text.toString().length < 7) {
                isValid= false
                binding.telefono.error="Debe ser mayor a 7 digitos"
            }else{
                binding.telefono.error= null
            }

            if(isValid){

                loginViewmodels.registro(binding.nombresCampos.text.toString(),binding.apellidosCampos.text.toString(),
                    binding.EmailCampos.text.toString(),binding.telefonoCampos.text.toString(),binding.Password1Campo.text.toString())

            }

        }

        loginViewmodels.user.observe(viewLifecycleOwner, Observer { user ->
           Toast.makeText(requireContext(),"Usuario Registrado",Toast.LENGTH_LONG).show()
            requireActivity().onBackPressed()

        })

        loginViewmodels.error.observe(viewLifecycleOwner, Observer { error->
            Log.d("Click",error)
            Toast.makeText(requireContext(),error,Toast.LENGTH_LONG).show()
        })


    }

}