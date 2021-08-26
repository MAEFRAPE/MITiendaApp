package com.example.myapplication.UI.Fragmentos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.UI.Adaptadores.Adaptador
import com.example.myapplication.Data.Models.Comment
import com.example.myapplication.UI.Listener.OnCommentListener
import com.example.myapplication.UI.viewmodels.CommentViewmodel
import com.example.myapplication.UI.viewmodels.LoginViewModel
import com.example.myapplication.databinding.FragmentCommentBinding
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 * Use the [CommentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CommentFragment : Fragment() {


    private var _binding: FragmentCommentBinding? = null
    private lateinit var    commentAdapter: Adaptador
    private  lateinit var layaoutManager: LinearLayoutManager
    private lateinit var  userDB:FirebaseUser

    private val binding get() = _binding!!

    private val commentVieModel:CommentViewmodel by viewModel()
    private val loginVieModel:LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCommentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.containedButton.setOnClickListener {
            Log.d("Click","Escucha")
            if (!binding.textComment.text.toString().equals("")){
                Log.d("Click","Empezo")
                loginVieModel.loginIn()
                loginVieModel.user.observe(viewLifecycleOwner, Observer { user ->

                    commentVieModel.registroComment(user!!,binding.textComment.text.toString())
                   binding.textComment.text.clear()
                })
           }
        }
        commentAdapter= Adaptador(
            listOf()
            )

        commentAdapter.listener=object : OnCommentListener {
            override fun onClick(comment: Comment) {
                Log.d("Click",comment.name!!)
            }
        }
        layaoutManager= LinearLayoutManager(requireContext())
        binding.Recycler.apply {
            adapter=commentAdapter
            layoutManager=layaoutManager
        }


        commentVieModel.comment.observe(viewLifecycleOwner, Observer { comments ->
            commentAdapter.newDataset(comments)
        })

        commentVieModel.loadCommentInfo()



    }


}