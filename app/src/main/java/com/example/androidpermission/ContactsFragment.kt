package com.example.androidpermission

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidpermission.databinding.FragmentContactsBinding
import com.example.androidpermission.databinding.FragmentMainBinding

class ContactsFragment : Fragment() {

    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.inflateMenu(R.menu.main_menu)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.exit -> activity?.finish()
            }
            true
        }
        val recyclerList = arrayListOf(
            Contact("one", "123456"),
            Contact("two", "234567"),
            Contact("three", "345678"),
            Contact("four", "456789"),
            Contact("five", "567890"),
        )
        val adapter = RecyclerAdapter(recyclerList)
        binding.recyclerRV.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerRV.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}