package com.example.myapplication


import com.example.myapplication.domain.entities.Book
import com.example.myapplication.ui.vm.BooksFragmentViewModel
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.junit.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun get_books_parse_correct() {

        System.out.println("Test " + getJson("success_resp_list.json"))

        val booksFragmentViewModel: BooksFragmentViewModel = BooksFragmentViewModel()

        val gson = GsonBuilder().create()
        val type = object: TypeToken<Map<String, Book>>(){}.type
        val bookResponse: Map<String, Book> = gson.fromJson<Map<String, Book>>(getJson("success_resp_list.json"), type)

        val books: ArrayList<Book> = booksFragmentViewModel.convertMapToArray(bookResponse)

        assertEquals(3, books.size)

        for(book in books){
            assertNotNull(book.id)
            assertNotNull(book.title)
            assertNotNull(book.price)
        }

    }

    fun getJson(path : String) : String {
        val uri = javaClass.classLoader!!.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }
}