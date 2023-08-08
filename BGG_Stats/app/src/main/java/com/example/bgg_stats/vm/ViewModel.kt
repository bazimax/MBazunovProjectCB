package com.example.bgg_stats.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class ViewModel : ViewModel() {

    //Список новостей (NewsItem)
    /*val newsItemArrayAll: MutableLiveData<ArrayList<NewsItem>> by lazy { //Список новостей (NewsItem) - "всех"
        MutableLiveData<ArrayList<NewsItem>>()
    }*/

    //список "сохраненных поисков" (SearchItem) для постоянного отслеживания (подписка на определенные новости)
    /*val searchItemList: MutableLiveData<List<SearchItem>> by lazy {
        MutableLiveData<List<SearchItem>>()
    }*/

    //Отслеживаем поворот экрана
    val statusLandscape: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    var sizeFAButton: Int = -99 //размер кнопки, как точка отсчета для подгонки интерфейса
    var statusSavedSearchesView: Boolean = false //статус кнопки сохранённых поисков (показывает скрыт ли список "сохранённых поисков")


    //отслеживаем элемент поиска (скрыт или нет)
    val statusSearchMutable: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    //список searchItem на удаление
    val searchItemDeleteArrayList = MutableLiveData<ArrayList<String>>()

    var statusInstruction = MutableLiveData("step0") //начальная инструкция
    var statusProgressBar = MutableLiveData(false)
    var serviceMessage = MutableLiveData("")
    var searchItemActive = MutableLiveData("") //активный "поисковой запрос"(или сохраненный поиск) для которого выводятся все найденные новости. Если запрос пустой - значит еще нет "поисковых запросов"
    var searchItemDeleteCount = MutableLiveData<Int>() //счетчик элементов searchItem на удаление (если счетчик = 0, то кнопки для удаления скрываются)


    init {
        //запускать init в activity или fragment не надо?

        //searchItemDeleteArrayList.value = ArrayList() //пустой список searchItem на удаление
        //searchItemDeleteCount.value = 0

    }



}