package com.example.drinkcountapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.drinkcountapp.data.AppDatabase
import com.example.drinkcountapp.data.Drink
import com.example.drinkcountapp.data.DrinkHistoryRepository
import com.example.drinkcountapp.dummy.DummyContent
import com.example.drinkcountapp.viewmodels.MyDrinkHistoryRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class DrinkHistoryFragment : Fragment() {

    private var columnCount = 1
    private lateinit var recyclerView: RecyclerView
    private lateinit var protocolAdapter: RecyclerView.Adapter<MyDrinkHistoryRecyclerViewAdapter.ViewHolder>
    private lateinit var protocolManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_drink_list, container, false)

        val database = AppDatabase.getInstance(this.requireContext()).drinkDao()
        var repo = DrinkHistoryRepository(database)

        //var items = repo.getAll()

        var items: List<Drink>
        runBlocking {
            items = repo.getAll()
        }

        // Set the adapter
//        if (view is RecyclerView) {
//            with(view) {
//                layoutManager = when {
//                    columnCount <= 1 -> LinearLayoutManager(context)
//                    else -> GridLayoutManager(context, columnCount)
//                }
//                adapter = MyDrinkHistoryRecyclerViewAdapter(items)
//            }
//        }
        protocolManager = GridLayoutManager(view.context,1)

        protocolAdapter = MyDrinkHistoryRecyclerViewAdapter(items)

        recyclerView = view.findViewById<RecyclerView>(R.id.drinksList).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            //setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = protocolManager

            // specify an viewAdapter (see also next example)
            adapter = protocolAdapter

        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            DrinkHistoryFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}