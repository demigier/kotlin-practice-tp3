package com.ort.loginpractica.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.ort.loginpractica.R
import com.ort.loginpractica.adapters.BeerAdapter
import com.ort.loginpractica.entities.Beer
import com.ort.loginpractica.entities.User
import com.ort.loginpractica.viewmodels.ProfileViewModel

class Profile : Fragment() {

    companion object {
        fun newInstance() = Profile()
    }

    private lateinit var viewModel: ProfileViewModel

    lateinit var v: View
    lateinit var txtMail : TextView

    lateinit var recBeers: RecyclerView
    var beersList: MutableList<Beer> = mutableListOf()
    private lateinit var beerAdapter: BeerAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v=  inflater.inflate(R.layout.profile_fragment, container, false)
        recBeers = v.findViewById(R.id.recBeers)

        return v
    }

    override fun onStart() {
        super.onStart()

        //var email  = ProfileArgs.fromBundle(requireArguments()).email

        var list = getBeers()
        recBeers.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        recBeers.layoutManager = linearLayoutManager

        beerAdapter = BeerAdapter(list) { item ->
            onItemLongClick(item)
        }

        recBeers.adapter = beerAdapter
    }

    private fun getBeers(): MutableList<Beer>{
        beersList.add(Beer("Corona Rubia",6))
        beersList.add(Beer("Corona Negra",8))
        beersList.add(Beer("Patagonia Rubia",5))
        beersList.add(Beer("Patagonia Gold",7))
        beersList.add(Beer("Budweiser",5))
        beersList.add(Beer("Quilmes Rubia",4))
        beersList.add(Beer("Quilmes Negra",6))

        return beersList
    }

    fun onItemLongClick ( position : Int ) : Boolean {
        Snackbar.make(v,"Graduation: " + beersList[position].graduation.toString() + "°",Snackbar.LENGTH_SHORT).show()
        return true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

}