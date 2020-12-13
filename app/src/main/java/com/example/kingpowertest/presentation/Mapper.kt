package com.example.kingpowertest.presentation

import com.example.kingpowertest.data.model.PhotoNetworkModel

interface Mapper<in FROM, out TO> {

    fun map(from: FROM): TO
}