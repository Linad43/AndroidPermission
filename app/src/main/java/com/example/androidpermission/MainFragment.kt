package com.example.androidpermission

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.androidpermission.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
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
        binding.photoBTN.setOnClickListener {
            getPermissionCamera()
        }
        binding.contactsBTN.setOnClickListener {
            getPermissionContacts()
        }
    }

    private fun getPermissionContacts() {
        val permission = Manifest.permission.READ_CONTACTS
        permissionLaunchContacts.launch(permission)
    }

    private fun getPermissionCamera() {
        val permission = Manifest.permission.CAMERA
        permissionLaunchPhoto.launch(permission)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private val permissionLaunchPhoto = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            fragmentManager
                ?.beginTransaction()
                ?.add(R.id.container, PhotoFragment())
                ?.addToBackStack("")
                ?.commit()
        } else {
            Toast.makeText(
                requireContext(),
                "В разрешении отказано!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    private val permissionLaunchContacts = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            fragmentManager
                ?.beginTransaction()
                ?.add(R.id.container, ContactsFragment())
                ?.addToBackStack("")
                ?.commit()
        } else {
            Toast.makeText(
                requireContext(),
                "В разрешении отказано!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}