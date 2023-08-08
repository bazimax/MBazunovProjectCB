package com.example.bgg_stats.objects

object Constants {
    //log
    const val TAG = "TAG_D_1" //разное
    const val TAG_DEBUG = "TAG_DEBUG" //запуск функций, активити и тд
    const val TAG_DATA = "TAG_DATA" //небольшие переменные и данные
    const val TAG_DATA_BIG = "TAG_BIG_DATA" //объемные данные
    const val TAG_DATA_IF = "TAG_DATA_IF" //повторяющиеся переменные и данные в циклах

    //SharedPreferences
    const val SEARCH_ITEM = "search"
    const val SHARED_FIRST_LAUNCH = "firstLaunch"
    const val SHARED_INSTRUCTION = "instruction"

    //Имена файлов
    const val FILE_SEARCH_ITEM = "searchItems.json"
    const val FILE_FIRST_LAUNCH = "firstLaunch.txt"
    const val FILE_TEST_LOAD_SITE = "test_load_site.txt"

    //Worker
    const val WORKER_TAG_PARSER = "parser"
    const val WORKER_UNIQUE_NAME_PARSER = "uniqueParser"
    const val WORKER_PUT_STATUS_UPDATE = "statusUpdate"
}