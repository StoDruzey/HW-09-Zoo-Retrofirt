package com.example.hw09zooretrofirt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.example.hw09zooretrofirt.databinding.FragmentRecyclerBinding
import com.google.gson.Gson
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecyclerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecyclerFragment : Fragment() {

    private var _binding: FragmentRecyclerBinding? = null
    private val binding get() = requireNotNull(_binding)

    private var randomRequest: Call<List<Animal>>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentRecyclerBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://zoo-animal-api.herokuapp.com/")
            .build()

        val animalsApi = retrofit.create<AnimalsApi>()

        randomRequest = animalsApi
            .fetchAnimals()
            .apply {
                enqueue(object : Callback<List<Animal>> {
                    override fun onResponse(call: Call<List<Animal>>, response: Response<List<Animal>>) {
                        if (response.isSuccessful) {
                            val animal = response.body() ?: return
                            binding.imageView.load(animal[0].url)
                        } else {
                            handleException(HttpException(response))
                        }
                    }

                    override fun onFailure(call: Call<List<Animal>>, t: Throwable) {
                        if (!call.isCanceled) {
                            handleException(t)
                        }
                    }
                })
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        randomRequest?.cancel()
        _binding = null
    }

    private fun handleException(t: Throwable) {

    }

    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_recycler, container, false)
//    }
//
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment RecyclerFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            RecyclerFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}