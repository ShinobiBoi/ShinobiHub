package com.example.composeshinobicima.appcore.data.remote

import com.example.composeshinobicima.appcore.data.model.genre.GenreResponse
import com.example.composeshinobicima.appcore.data.model.movie.MediaResponse
import com.example.composeshinobicima.features.detail.data.model.detailitem.DetailMediaItemDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {


    //TRENDING////////////////////////////////////////////

    @GET("trending/all/week")
    suspend fun getTrendingAll(
        @Query("page") page: Int,
        @Query("api_key") key: String = API_KEY
    ): Response<MediaResponse>

    @GET("trending/movie/week")
    suspend fun getTrendingMovies(
        @Query("page") page: Int,
        @Query("api_key") key: String = API_KEY
    ): Response<MediaResponse>

    @GET("trending/tv/week")
    suspend fun getTrendingTv(
        @Query("page") page: Int,
        @Query("api_key") key: String = API_KEY
    ): Response<MediaResponse>


    @GET("trending/person/week")
    suspend fun getTrendingPeople(
        @Query("page") page: Int,
        @Query("api_key") key: String = API_KEY
    ): Response<MediaResponse>



    //MOVIES//////////////////////////////////////////////////////


    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page")page:Int,
        @Query("api_key") key:String= API_KEY
    ):Response<MediaResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page")page:Int,
        @Query("api_key") key:String= API_KEY
    ):Response<MediaResponse>

    @GET("movie/upcoming")
    suspend fun getUpComingMovies(
        @Query("page") page:Int,
        @Query("api_key") key:String= API_KEY
    ):Response<MediaResponse>


    //TV SERIES///////////////////////////////////////////////


    @GET("tv/on_the_air")
    suspend fun getOnTheAirTv(
        @Query("page")page:Int,
        @Query("api_key") key:String= API_KEY
    ):Response<MediaResponse>


    @GET("tv/popular")
    suspend fun getPopularTv(
        @Query("page")page:Int,
        @Query("api_key") key:String= API_KEY
    ):Response<MediaResponse>


    @GET("tv/top_rated")
    suspend fun getTopRatedTv(
        @Query("page")page:Int,
        @Query("api_key") key:String= API_KEY
    ):Response<MediaResponse>






    //Detail////////////////////////////////////////



    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") movieId:Int,
        @Query("api_key") key:String= API_KEY
    ):Response<DetailMediaItemDto>

    @GET("tv/{series_id}")
    suspend fun getDetailTv(
        @Path("series_id") seriesId:Int,
        @Query("api_key") key:String= API_KEY
    ):Response<DetailMediaItemDto>

    @GET("person/{person_id}")
    suspend fun getDetailPerson(
        @Path("person_id") personId:Int,
        @Query("api_key") key:String= API_KEY
    ):Response<DetailMediaItemDto>





    //SEARCH////////////////////////////////////////

    @GET("search/multi")
    suspend fun searchMulti(
        @Query("query")query:String,
        @Query("page")page:Int,
        @Query("api_key") key:String= API_KEY
    ):Response<MediaResponse>

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query")query:String,
        @Query("page")page:Int,
        @Query("api_key") key:String= API_KEY
    ):Response<MediaResponse>

    @GET("search/tv")
    suspend fun searchTv(
        @Query("query")query:String,
        @Query("page")page:Int,
        @Query("api_key") key:String= API_KEY
    ):Response<MediaResponse>

    @GET("search/person")
    suspend fun searchPeople(
        @Query("query")query:String,
        @Query("page")page:Int,
        @Query("api_key") key:String= API_KEY
    ):Response<MediaResponse>


    @GET("discover/movie")
    suspend fun getGenreWiseMovieList(
        @Query("with_genres") genresId: String,
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = API_KEY,
    ): Response<MediaResponse>

    @GET("genre/movie/list")
    suspend fun getGenreList(
        @Query("api_key") apiKey: String = API_KEY,
    ): Response<GenreResponse>


    companion object{
        const val API_KEY="0e00cb8df334c216bc341e703723c22a"
    }

}