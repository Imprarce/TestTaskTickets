package com.imprarce.android.ticket_api

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class ReadFromAssets(val context: Context) {

    inline fun <reified T> readFromAssets(fileName: String, key: String): List<T>? {
        val json: String? = try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }

        println("JSON from assets: $json")

        return json?.let {
            val jsonArray = JSONObject(it).getJSONArray(key).toString()
            val type = object : TypeToken<List<T>>() {}.type
            val gson = GsonBuilder()
                .registerTypeAdapter(Date::class.java, JsonDeserializer<Date> { json, _, _ ->
                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).parse(json.asString)
                })
                .create()
            gson.fromJson(jsonArray, type)
        }
    }
}