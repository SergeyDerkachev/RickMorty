package ru.softwarefree.rickandmorty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.softwarefree.rickandmorty.adapter.Adapter
import ru.softwarefree.rickandmorty.databinding.FragmentListBinding
import ru.softwarefree.rickandmorty.retrofit.CharacterApi

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: Adapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(layoutInflater)
        adapter = Adapter()
        val layoutManager = LinearLayoutManager(context)
        binding.listCharacter.layoutManager = layoutManager
        binding.listCharacter.adapter = adapter
        fetchCharacter()
        return binding.root
    }
    private fun fetchCharacter() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val characterApi = retrofit.create(CharacterApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val list = characterApi.getCharacterApi()
            requireActivity().runOnUiThread {
                binding.apply {
                    adapter.submitList(list.results)
                }
            }
        }
    }
}