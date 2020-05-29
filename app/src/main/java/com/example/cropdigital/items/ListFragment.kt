package com.example.cropdigital.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cropdigital.R
import com.example.cropdigital.network.ItemsResponse
import com.example.cropdigital.showDialog
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: CustomAdapter
    private var recyclerView: RecyclerView? = null
    private var lastIndex: Int = 0

    private lateinit var listViewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listViewModel = ViewModelProvider(this).get(ListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loading.visibility = View.VISIBLE

        onCreateAdapter()

        initView()

        listViewModel.getListItems()

        registerObservers()
        registerListeners()
    }

    private fun registerListeners() {
        registerBtn.setOnClickListener {
            findNavController().navigate(
                ListFragmentDirections.navigateToRegistrationFragment(
                    lastIndex
                )
            )
        }
    }

    private fun registerObservers() {
        listViewModel.listItemsResponse.observe(viewLifecycleOwner, Observer { list ->
            loading.visibility = View.INVISIBLE
            adapter.addItems(list)
            list.forEach { item ->
                lastIndex = item.index
            }
        })

        listViewModel.onError.observe(viewLifecycleOwner, Observer {
            showDialog(
                requireContext(),
                "Error",
                "Ha ocurrido un error",
                "Ok"
            )
            listViewModel.getListItems()
        })

        listViewModel.onSuccess.observe(viewLifecycleOwner, Observer { it ->
            loading.visibility = View.INVISIBLE
            Bundle().apply {
                putParcelable("Item", it)
            }.also {
                setFragmentResult("ItemSelected", it)
            }
        })
    }

    private fun onCreateAdapter() {
        adapter = CustomAdapter(object : CustomAdapter.OnItemClickListener {
            override fun onCloselicked(item: ItemsResponse) {
                loading.visibility = View.VISIBLE
                listViewModel.getItemSelected(item.index)
                ItemDialog().show(parentFragmentManager, "ItemDialog")
            }
        })
    }

    private fun initView() {
        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
        recyclerView = view?.findViewById(R.id.recyclerView)
        linearLayoutManager = LinearLayoutManager(activity)
        recyclerView?.adapter = adapter
    }

}
