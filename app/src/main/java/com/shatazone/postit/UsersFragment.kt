package com.shatazone.postit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.shatazone.postit.databinding.FragmentUsersBinding
import com.shatazone.postit.network.PostItApi
import kotlinx.coroutines.launch

class UsersFragment : Fragment() {
    private lateinit var binding: FragmentUsersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userListAdapter = UserListAdapter()

        binding.recyclerView.adapter = userListAdapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))

        lifecycleScope.launch {
            val allUsers = PostItApi.USERS.getAll()
            userListAdapter.submitList(allUsers.data)
        }
    }
}