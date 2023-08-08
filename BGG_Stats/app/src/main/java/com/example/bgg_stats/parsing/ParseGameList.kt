package com.example.bgg_stats.parsing

import android.content.Context
import android.util.Log
import com.example.bgg_stats.objects.Constants
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class ParseGameList() {

    private val logNameClass = "ParserSites" //for logs //для логов

    //CONSTANTS //КОНСТАНТЫ
    companion object {
        //log
        const val TAG = Constants.TAG //other
        const val TAG_DEBUG = Constants.TAG_DEBUG //start fun, activity etc.
        const val TAG_DATA = Constants.TAG_DATA
        const val TAG_DATA_BIG = Constants.TAG_DATA_BIG
        const val TAG_DATA_IF = Constants.TAG_DATA_IF
    }

    private val newsItemList = ArrayList<String>()
    private var statusEthernet = ""
    data class ResultParse(val list: ArrayList<String>, val statusEthernet: String)

    //start parsing
    fun parse(search: String, context: Context): ResultParse{
        Log.d(TAG_DEBUG, "$logNameClass >f parse === START")
        parseGameList(search, context)
        Log.d(TAG_DEBUG, "$logNameClass >f parse ----- END")
        Log.d(TAG_DATA_BIG, "$logNameClass >f parse > statusEthernet: $statusEthernet\n- newsItemList: $newsItemList")
        return ResultParse(newsItemList, statusEthernet)
    }

    //open website
    private fun initFromParseHTML(url: String): String {
        Log.d(TAG_DEBUG, "$logNameClass >f initFromParseHTML === START")
        Log.d(TAG_DEBUG, "$logNameClass >f initFromParseHTML // открываем интернет страницу")
        Log.d(TAG_DATA, "$logNameClass >f initFromParseHTML > url: $url")
        var siteTemp = ""
        //coroutines
        GlobalScope.launch { // start new coroutine in background
            //delay(1000L) // non-blocking delay for 1 second

            val urlConnection = URL(url).openConnection() as HttpsURLConnection

            try {
                urlConnection.connect()

                if(urlConnection.responseCode == HttpURLConnection.HTTP_OK) {
                    //val isr = InputStreamReader(conn.getInputStream(), "windows-1251")
                    //val br = BufferedReader(isr)
                    Log.d(TAG, "$logNameClass >f initFromParseHTML > Connection - Good")
                    val br = BufferedReader(
                        InputStreamReader(
                            urlConnection.inputStream,
                            "windows-1251" //site encoding
                            //StandardCharsets.UTF_8
                        )
                    )
                    br.lineSequence().forEach {
                        //Log.d("TAG1", "1+ $siteTemp")
                        siteTemp += it
                    }
                    br.close()
                }
                else Log.d(TAG, "$logNameClass >f initFromParseHTML > Connection - Failed")
            }catch (e: IOException) {
                e.printStackTrace()
            }finally {
                urlConnection.disconnect()
            }
        }

        Thread.sleep(2000L)
        if (siteTemp == "") {
            Log.d(TAG, "$logNameClass >f initFromParseHTML > Connection - Wait More")
            Thread.sleep(6000L)
        }
        //Log.d("TAG1", "!!Return:: : $siteTemp")
        return siteTemp
    }

    private fun parseGameList(search: String, context: Context):String {
        Log.d(TAG_DEBUG, "$logNameClass >f parseP === START")
        Log.d(TAG_DATA, "$logNameClass >f parseP > search: $search")
        val correctSearch = search.replace(" ", "%20", true)
        Log.d(TAG_DATA, "$logNameClass >f parseP > correctSearch: $correctSearch")

        val url = "https://pikabu.ru/search?q=$correctSearch&r=4" //рейтинг от 100
        //https://pikabu.ru/search?q=witcher&d=5347&D=5378 - по дате

        //Log.d("TAG1", "parseP url: $url")
        val siteTemp = initFromParseHTML(url)

        //записываем полученный сайт в файл (для тестов)
        //FilesWorker().writeToFile(siteTemp, Constants.FILE_TEST_LOAD_SITE, context)
        Log.d(TAG_DATA, "$logNameClass >f parseP > siteTemp: $siteTemp")

        statusEthernet = true.toString() //"good"
        if (siteTemp == "") {
            statusEthernet = false.toString() //"bad"
        }
        Log.d(TAG_DATA, "$logNameClass >f parseP > statusEthernet: $statusEthernet")

        val doc = Jsoup.parse(siteTemp)
        val item = doc.select("article") //начали парсить
        newsItemList.clear()

        Log.d(TAG_DATA_BIG, "$logNameClass >f parseP > item: $item")
        Log.d(TAG_DATA, "$logNameClass >f parseP > item size: ${item.size}")
        item.forEach {
            //для каждого новостного элемента
            val id = 0
            val img = ""//it.select("href").first().toString() ?: "Img Error"
            //Log.d("TAG1", "forEach val 2: $img")
            val date = it.select("time").attr("datetime") ?: "Date Error" //time (full)
            //Log.d("TAG1", "!! it1: ${it.select("time").text()}") //time text
            //Log.d("TAG1", "forEach val 3")
            val title = it.select("a.story__title-link").text() ?: "Title Error" //title
            //Log.d("TAG1", "forEach val 4")
            var content = ""
            if (it.select("div.story-block_type_text").size > 0) {
                content = it.select("div.story-block_type_text").text() ?: "Content Error" // content
            }
            //Log.d("TAG1", "forEach val 5")
            val link = it.select("header.story__header").select("a").attr("href") ?: "Link Error" //link

            val statusSaved = false.toString()

            /*val newsItem = NewsItem(id, search ,img, date, title, content, link, statusSaved)
            //Log.d("TAG1", "forEach newsItem: $newsItem")
            //Log.d("TAG1", "forEach newsItemList: $newsItemList")

            //если есть ссылка
            if (newsItem.link.contains("https://")) {
                //если есть дата
                if (date != "") {
                    newsItemList.add(newsItem)

                    Log.d(TAG_DATA_IF, "$logNameClass >f ParseP > item.forEach:\n" +
                            "- id: $id\n" +
                            "- search: $search\n" +
                            "- img: $img\n" +
                            "- date: $date\n" +
                            "- title: $title\n" +
                            "- content: $content\n" +
                            "- link: $link\n" +
                            "- statusSaved: $statusSaved")
                }
            }*/
            //Log.d("TAG1", "forEach newsItemList: $newsItemList")
            //Log.d("TAG1", "forEach Count: $testCount")
            //testCount++
        }
        Log.d(TAG, "$logNameClass >f parseP END ==================================================================")
        return statusEthernet
    }
}