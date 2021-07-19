package com.example.myapplication.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.myapplication.R
import com.example.myapplication.ui.fragments.BooksFragment
import com.example.myapplication.ui.vm.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: MainActivityViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initShowBooksButtonListener()
    }


    private fun initShowBooksButtonListener(){
        showBooksFragmentButton.setOnClickListener {
            showBooksFragmentFragment()
        }
    }

    private fun showBooksFragmentFragment(){
        val booksFragment: BooksFragment = BooksFragment.newInstance()
        booksFragment.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.fullScreenFragmentDialog)
        booksFragment.show(supportFragmentManager, BooksFragment::class.simpleName)
    }
}