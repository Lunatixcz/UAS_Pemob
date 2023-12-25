package com.example.uas.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uas.R
import com.example.uas.UnivAdapter
import com.example.uas.api.ApiConfig
import com.example.uas.api.UnivResponseItem
import com.example.uas.databinding.FragmentSearchBinding
import com.mobile.mp3_final.repository.UnivRespos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: UnivAdapter
    private lateinit var rv : RecyclerView
    private lateinit var univRespos: UnivRespos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root

        rv = view.findViewById(R.id.rv_chara)
        val application = requireNotNull(this.activity).application
        univRespos = UnivRespos(application)
        adapter = UnivAdapter(ArrayList(), univRespos)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(requireContext())

        getChara()

        return view
    }

    private fun getChara() {
        val client = ApiConfig.getApiService().getChara()

        client.enqueue(object:Callback<List<UnivResponseItem>>{
            override fun onResponse(
                call: Call<List<UnivResponseItem>>,
                response: Response<List<UnivResponseItem>>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    val adapter = responseBody?.let { UnivAdapter(it, univRespos) }
                    binding.rvChara.adapter = adapter
                }
                else {
                    Log.e("Home Fragment" , "network call failure on response: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<UnivResponseItem>>, t: Throwable) {
                Log.e("Home Fragment","network call failure: ${t.message}")
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}