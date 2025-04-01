package com.example.khabar.ui.base

typealias ClickHandler = () -> Unit
typealias RetryHandler = () -> Unit
typealias DismissHandler = () -> Unit
typealias SearchHandler = (String) -> Unit
typealias UrlHandler = (String) -> Unit
typealias HeadlineHandler<T> = (headline: T) -> Unit
//typealias SourceHandler = (Source) -> Unit
//typealias LanguageHandler = (Language) -> Unit
//typealias CountryHandler = (Country) -> Unit