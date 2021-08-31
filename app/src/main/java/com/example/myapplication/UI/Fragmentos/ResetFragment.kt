package com.example.myapplication.UI.Fragmentos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.UI.viewmodels.LoginViewModel
import com.example.myapplication.Utils.isValidEmail
import com.example.myapplication.databinding.FragmentLoginBinding
import com.example.myapplication.databinding.FragmentResetBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer


class ResetFragment : Fragment() {

    private  var _binding: FragmentResetBinding? = null

    private val binding get() = _binding!!

    private val loginViewmodels: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentResetBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.buttonReset.setOnClickListener {

            var isValid= true
            if (!binding.EmailResetCampo.text.isValidEmail()){
                isValid= false
                binding.EmailReset.error="Correo Electronico No Valido"
            }else{
                binding.EmailReset.error= null
            }

            if(isValid){

                loginViewmodels.ValidarCorreo(binding.EmailResetCampo.text.toString())


            }
        }
        loginViewmodels.user.observe(viewLifecycleOwner, Observer { user ->
            user
            Toast.makeText(requireContext(),"Correo Enviado", Toast.LENGTH_LONG).show()
            requireActivity().onBackPressed()

        })

        loginViewmodels.error.observe(viewLifecycleOwner, Observer { error->
            Log.d("Click",error)
            Toast.makeText(requireContext(),error, Toast.LENGTH_LONG).show()
        })
    }


}