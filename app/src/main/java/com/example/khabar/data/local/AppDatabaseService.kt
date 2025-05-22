package com.example.khabar.data.local

import com.example.khabar.data.local.entity.Article
import com.example.khabar.data.local.entity.articleToSavedArticleEntity
import com.example.khabar.data.local.entity.savedArticleEntityToArticle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDatabaseService @Inject constructor(private val appDatabase: AppDatabase) :
    DatabaseService {
    override suspend fun upsert(article: Article) {
        return appDatabase.getSavedArticleDao().add(article.articleToSavedArticleEntity())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getSavedArticles(): Flow<List<Article>> {
        return appDatabase.getSavedArticleDao().getSavedArticles().flatMapConcat {
            flow {
                val list = mutableListOf<Article>()
                it.forEach { SavedArticleEntity ->
                    list.add(SavedArticleEntity.savedArticleEntityToArticle())
                }
                emit(list)
            }
        }
    }

    override suspend fun deleteArticle(article: Article) {
        return appDatabase.getSavedArticleDao().remove(article.articleToSavedArticleEntity())
    }

    override fun getAllArticles(): Flow<List<Article>> {
        return appDatabase.getArticleDao().getAll()
    }

    override fun deleteAllAndInsertAll(articles: List<Article>) {
        appDatabase.getArticleDao().deleteAllAndInsertAll(articles)
    }
}