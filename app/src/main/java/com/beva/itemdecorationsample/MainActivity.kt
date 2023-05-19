package com.beva.itemdecorationsample

import android.os.Bundle
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.beva.itemdecorationsample.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val LIST_VIEW = "List_View"
    private val GRID_VIEW = "Grid_View"
    private var currentView = LIST_VIEW
    private val viewModel = CardViewModel()
    private val listCardAdapter = ListCardAdapter()
    private val girdCardAdapter = GridCardAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.item.observe(this) { listCardAdapter.submitList(it) }
        viewModel.item.observe(this) { girdCardAdapter.submitList(it) }

        listView()
        val cardDecoration = CardDecoration(this, R.drawable.baseline_bookmark_24, R.color.teal_200)
//        val divider = DividerItemDecoration(this, LinearLayoutManager.VERTICAL) //原生裝飾線
        val divider = DividerItemDecoration(this)
        val textDecoration = TextItemDecoration(this, "This is Title Area")
        val imageDecoration = ImageItemDecoration(this, R.drawable.baseline_stars_24)
        binding.recyclerView.addItemDecoration(cardDecoration)
        binding.recyclerView.addItemDecoration(divider)
//        binding.recyclerView.apply {
//            //滑動時繪製
//            setWillNotDraw(false)
//        }

        binding.floating.setOnClickListener {
            binding.recyclerView.removeItemDecoration(cardDecoration)
            binding.recyclerView.removeItemDecoration(divider)
            binding.recyclerView.removeItemDecoration(textDecoration)
            if (currentView == LIST_VIEW) {
                gridView()
                binding.recyclerView.addItemDecoration(textDecoration)
                binding.recyclerView.removeItemDecoration(imageDecoration)
            } else {
                listView()
                binding.recyclerView.addItemDecoration(imageDecoration)
                binding.recyclerView.removeItemDecoration(textDecoration)

            }
        }
    }

    private fun listView() {
        with(binding) {
            floating.setImageResource(R.drawable.baseline_space_dashboard_24)
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.adapter = listCardAdapter
        }
        currentView = LIST_VIEW
    }

    private fun gridView() {
        with(binding) {
            floating.setImageResource(R.drawable.baseline_table_rows_24)
            recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 2)
            recyclerView.adapter = girdCardAdapter
        }
        currentView = GRID_VIEW
    }
}