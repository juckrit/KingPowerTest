package com.example.kingpowertest.data.api

import com.example.kingpowertest.data.model.PhotoNetworkModel
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test


class KingPowerServiceTest {

    private lateinit var kingPowerService: KingPowerService

    @Before
    fun setup() {
        kingPowerService = KingPowerService.instance
    }


    @Test
    fun `kingPowerService parse correctly`() {
        runBlocking {
            val actualResponse = kingPowerService.getPhotos(1)
            assertThat(actualResponse[0], instanceOf(PhotoNetworkModel::class.java))
        }

    }
}