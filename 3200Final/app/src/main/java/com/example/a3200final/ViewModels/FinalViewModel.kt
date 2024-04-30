package com.example.a3200final.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a3200final.Models.DomainObjectList

class FinalViewModel : ViewModel() {

    private var image: String? = null
    private var metadata: String? = null
    private var curImage = MutableLiveData<Int>()

    init { curImage.value = 0 }

    fun setImage(iURL: String) { image = iURL }

    fun getImage() : String? { return image }

    fun setMetaData(dURL: String) { metadata = dURL }

    fun getMetaData() : String? { return metadata }

    fun nextImage() : Int {
        val indexing = (0 until DomainObjectList.size()).random()
        curImage.value = indexing
        return DomainObjectList.getAllMyIds()[indexing].id
    }

}