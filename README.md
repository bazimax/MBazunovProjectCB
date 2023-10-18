# Cocktails Bar

## Проект для летней школы Surf

"Cocktail Bar -  это приложение, которое позволяет пользователям создавать и сохранять свои любимые коктейли в одном месте."

ТЗ - ссылка [ссылка docs.google](https://docs.google.com/document/d/16poPTQjJIaynjJpQzxlrNiPPSd_20PaDv9OXHKY9w6o/edit),  
 Дизайн - [figma](https://www.figma.com/file/UCmGNNZj7950sB6sD9BaCG/Android-Test---Cocktail-Bar?type=design&node-id=5-671&mode=design&t=Z0hn3tvPIjFF7NTv-0)
***

## Стек
//так как в тз было указание по минимуму использовать библиотеки, я не стал использовать room, liveData и тд.

- View (XML) + Fragments
- SQLite
- Sharedpreferences
  
***


## Что успел и не успел 
- начал в 11:00
- до 00:00 не успел - (решил доделать чтобы уже было)
- к 00:20 были готовы экраны и БазаДанных, RecyclerView близко, но еще оставалось связать с БД (форму отправил в это время)
- к 05:00 доделал все что не успел (новых фишек уже не добавлял)
- к 06:00 закончил с оформлением (текст и скрины) 
- итого (с перерывами) ~16-17 часов 
  
##### Базовые задачи
- Экраны почти все (кроме ингридиентов, с chipGroup не работал, а изучить не успевал), но масштаб элементов не нравится
- База Данных на SQLite - готово было наверно почти все, но требовалась полировка
- Recycler View был почти готов (разметка, элемент, адаптер), но свзяка с БД заняла больше времени, чем я расчитывал
- проверка введеного текста примитивная (но хоть что-то)
- в дполнительное время добавил генерацию картинок на заглушку
- размеры в dimen.xml, значения в string.xml, цвета в colors.xml, стили уже не успевал
- коментарии старался вести, но много где не хватает

##### Дополнительные задачи
- удаление коктейля (без всплывашки)
- есть заготовка к обновлению, но и так уже потратил слишком много времени

***

## Preview

<img src="myCocktailsEmpty.png" width="250" /> <img src="all.gif" width="250" /> 

<img src="myCocktails.png" width="250" /> <img src="cocktailDetail.png" width="250" /> <img src="createCocktail.png" width="250" />

