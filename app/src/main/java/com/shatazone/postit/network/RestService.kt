package com.shatazone.postit.network

import com.shatazone.postit.BuildConfig
import com.shatazone.postit.network.data.*
import com.shatazone.postit.network.response.Response
import com.shatazone.postit.network.response.ResponseList
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

private val okHttpClient = OkHttpClient.Builder()
    .addNetworkInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
    .connectTimeout(30, TimeUnit.SECONDS) //Backend is really slow
    .writeTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .addInterceptor { chain ->
        val request = chain.request().newBuilder().addHeader("Authorization", "Bearer 45d282875795220eb197114c396e2ce0aff7e1729fbd316fb4880f273309ae57").build()
        chain.proceed(request)
    }.build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface UserEndpoint {
    @GET("public/v1/users")
    suspend fun getAll(): ResponseList<User>

    @GET("public/v1/users/{userId}")
    suspend fun get(@Path("userId") userId: Int): Response<User>

    @PUT("public/v1/users/{userId}")
    suspend fun update(@Path("userId") userId: Int, @Body user: User): Response<User>

    @DELETE("public/v1/users/{userId}")
    suspend fun delete(@Path("userId") userId: Int): Response<User>
}

interface PostEndpoint {
    @GET("public/v1/posts")
    suspend fun getAll(): ResponseList<Post>

    @GET("public/v1/users/{userId}/posts")
    suspend fun getAll(@Path("userId") userId: Int): ResponseList<Post>

    @GET("public/v1/posts/{postId}")
    suspend fun get(@Path("postId") postId: Int): Response<Post>

    @PUT("public/v1/posts/{postId}")
    suspend fun update(@Path("postId") postId: Int, @Body post: Post): Response<Post>

    @DELETE("public/v1/posts/{postId}")
    suspend fun delete(@Path("postId") postId: Int): Response<Post>
}

interface CommentEndpoint {
    @GET("public/v1/comments")
    suspend fun getAll(): ResponseList<Comment>

    @GET("public/v1/posts/{postId}/comments")
    suspend fun getAll(@Path("postId") postId: Int): ResponseList<Comment>

    @GET("public/v1/comments/{commentId}")
    suspend fun get(@Path("commentId") commentId: Int): Response<Comment>

    @PUT("public/v1/comments/{commentId}")
    suspend fun update(@Path("commentId") commentId: Int, @Body post: Comment): Response<Comment>

    @DELETE("public/v1/comments/{commentId}")
    suspend fun delete(@Path("commentId") commentId: Int): Response<Comment>
}

interface TodoEndpoint {
    @GET("public/v1/todos")
    suspend fun getAll(): ResponseList<Todo>

    @GET("public/v1/users/{userId}/todos")
    suspend fun getAll(@Path("userId") userId: Int): ResponseList<Todo>

    @GET("public/v1/todos/{todoId}")
    suspend fun get(@Path("todoId") todoId: Int): Response<Todo>

    @PUT("public/v1/todos/{todoId}")
    suspend fun update(@Path("todoId") todoId: Int, @Body post: Todo): Response<Todo>

    @DELETE("public/v1/todos/{todoId}")
    suspend fun delete(@Path("todoId") todoId: Int): Response<Todo>
}

object PostItApi {
    val USERS: UserEndpoint by lazy {
        retrofit.create(UserEndpoint::class.java)
    }

    val COMMENTS: CommentEndpoint by lazy {
        retrofit.create(CommentEndpoint::class.java)
    }

    val POSTS: PostEndpoint by lazy {
        retrofit.create(PostEndpoint::class.java)
    }

    val TODO: TodoEndpoint by lazy {
        retrofit.create(TodoEndpoint::class.java)
    }
}