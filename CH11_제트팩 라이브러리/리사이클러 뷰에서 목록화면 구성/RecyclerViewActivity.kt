package com.example.ch11_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch11_recyclerview.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle("B-JayU's RecyclerView")

        val datas = mutableListOf<String>()
        for (i in 1..10) {
            datas.add("item $i")
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        //  어댑터는 뷰홀더가 가진 뷰에 데이터를 대입,출력하는 역할, 어떤 데이터를 보낼 것인지를 인자로 전달
        binding.recyclerView.adapter = MyAdapter(datas)

        // 아이템 항목별로 구분선을 넣어주는 역할
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this,
        LinearLayoutManager.VERTICAL))

        binding.btnAdd.setOnClickListener {
            datas.add("new item")
            (binding.recyclerView.adapter as MyAdapter).notifyDataSetChanged()
        }

        binding.recyclerView.addItemDecoration(MyDecoration(this))
    }
}