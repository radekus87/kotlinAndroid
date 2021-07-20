package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.domain.model.BookModel
import com.example.myapplication.ui.adapters.BooksAdapter
import com.example.myapplication.ui.vm.BooksFragmentViewModel
import com.example.myapplication.utils.GeneralUtils
import kotlinx.android.synthetic.main.books_fragment.*
import kotlinx.android.synthetic.main.books_fragment.view.*
import org.koin.android.ext.android.inject

class BooksFragment: DialogFragment() {

    private val booksFragmentViewModel: BooksFragmentViewModel by inject()
    private val booksAdapter: BooksAdapter by inject()

    companion object{
        fun newInstance(): BooksFragment{
            val args = Bundle()

            val fragment: BooksFragment = BooksFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fethAllBooks()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.books_fragment, container,  false)

        initBooksRecyclerView(rootView)
        initBooksObserver()
        initSuccessBookFetchObserver()

        return rootView
    }

    fun initBooksObserver(){
        booksFragmentViewModel.getAllbooks().observe(this, { books ->

            if(books.isNotEmpty()){
                booksAdapter.setBookList(books as ArrayList<BookModel>)
                booksRecyclerView.visibility = View.VISIBLE
                infoTextView.visibility = View.GONE
            }else{
                infoTextView.text = getString(R.string.no_books_info)
            }

        })
    }

    fun initSuccessBookFetchObserver(){
        booksFragmentViewModel.isSuccess.observe(this, {isSuccess ->

            if(!isSuccess){
                Toast.makeText(context, R.string.sgw, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun initBooksRecyclerView(view: View){
        view.booksRecyclerView.layoutManager = LinearLayoutManager(activity)
        view.booksRecyclerView.adapter = booksAdapter
    }

    fun fethAllBooks(){
        if(GeneralUtils.isInternetConnection(context)){
            booksFragmentViewModel.fetchAllBooks("library")
        }else{
            Toast.makeText(context, R.string.no_internet_info, Toast.LENGTH_LONG).show()
        }
    }
}