package com.example.a3200final.Models

object DomainObjectList {
    val allIds = mutableListOf<DomainObjectId>()

    fun getAllMyIds() : List<DomainObjectId> {
        return allIds
    }

    fun size() : Int {
        return allIdInts.size
    }

    val allIdInts = (0..400).toList()

    init{
        allIdInts.forEach{ myInt ->
            allIds.add(DomainObjectId(myInt))
        }
    }

}