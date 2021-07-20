package com.example.myapplication


import com.example.myapplication.domain.model.BookModel
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.junit.Test
import java.io.File
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

        val gson = GsonBuilder().create()
        val type = object: TypeToken<Map<String, BookModel>>(){}.type

        val bookResponse: Map<String, BookModel> = gson.fromJson<Map<String, BookModel>>(getJson("success_resp_list.json"), type)

        for((key, value) in bookResponse){
            assertNotNull(value.id)
            assertNotNull(value.title)
            assertNotNull(value.price)
        }

    }

    fun getJson(path : String) : String {
        val uri = javaClass.classLoader!!.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }
}