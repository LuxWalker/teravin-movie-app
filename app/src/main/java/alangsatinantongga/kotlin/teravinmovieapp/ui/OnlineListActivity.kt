package alangsatinantongga.kotlin.teravinmovieapp.ui

import alangsatinantongga.kotlin.teravinmovieapp.adapter.MovieListAdapter
import alangsatinantongga.kotlin.teravinmovieapp.databinding.ActivityOnlineListBinding
import alangsatinantongga.kotlin.teravinmovieapp.model.MovieListResponse
import alangsatinantongga.kotlin.teravinmovieapp.repository.MoviesRepository
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class OnlineListActivity : AppCompatActivity() {
    lateinit var binding: ActivityOnlineListBinding

    @Inject
    lateinit var Repo: MoviesRepository

    @Inject
    lateinit var moviesListAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnlineListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            Repo.moviesListCall(1).enqueue(object : Callback<MovieListResponse> {
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {
                    when (response.code()) {
                        in 200..299 -> {
                            Log.d("Response Code", " success messages : ${response.code()}")
                            response.body()?.let { itBody ->
                                itBody.results.let { itData ->
                                    if (itData.isNotEmpty()) {
                                        val moviesRecyclerView = binding.rvMovies.apply {
                                                adapter = moviesListAdapter
                                                layoutManager =
                                                    LinearLayoutManager(this@OnlineListActivity)
                                                setHasFixedSize(true)
                                            }
                                        (moviesRecyclerView.adapter as MovieListAdapter).differ.submitList(
                                           itData
                                        )
                                    }
                                }
                            }
                        }

                        in 300..399 -> {
                            Log.d("Response Code", " Redirection messages : ${response.code()}")
                        }

                        in 400..499 -> {
                            Log.d("Response Code", " Client error responses : ${response.code()}")
                        }

                        in 500..599 -> {
                            Log.d("Response Code", " Server error responses : ${response.code()}")
                        }
                    }
                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                    Log.d(ContentValues.TAG, t.message.toString())

                }

            })
        }
    }
}