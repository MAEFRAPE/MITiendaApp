package com.example.myapplication.UI.Fragmentos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.UI.Adaptadores.Adaptador
import com.example.myapplication.Data.Models.Comment
import com.example.myapplication.UI.Listener.OnCommentListener
import com.example.myapplication.UI.viewmodels.CommentViewmodel
import com.example.myapplication.databinding.FragmentCommentBinding
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

    private val binding get() = _binding!!

    private val commentVieModel:CommentViewmodel by viewModel()

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