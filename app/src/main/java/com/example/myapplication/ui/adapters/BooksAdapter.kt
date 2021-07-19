package com.example.myapplication.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.domain.model.BookModel
import kotlinx.android.synthetic.main.book_item.view.*

class BooksAdapter: RecyclerView.Adapter<BooksAdapter.ItemViewHolder>() {

    private val bookList: ArrayList<BookModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.book_item, parent, false
            ))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.setBook(bookList[position])
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    fun setBookList(bookList: ArrayList<BookModel>){
        this.bookList.clear()
        this.bookList.addAll(bookList)
        notifyDataSetChanged()
    }


    inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun setBook(bookModel: BookModel){
            itemView.titleTextView.text = bookModel.title
        }

    }
}