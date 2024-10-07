package com.inspirecoding.wayairlines.viewmodel

import app.cash.turbine.test
import com.inspirecoding.wayairlines.data.util.exception.WayAirlinesException
import com.inspirecoding.wayairlines.domain.model.data.FlightsDataDomain
import com.inspirecoding.wayairlines.domain.usecase.GetFlightsUseCase
import com.inspirecoding.wayairlines.extensions.string.groupFlightsByCategory
import com.inspirecoding.wayairlines.features.home.action.HomeAction
import com.inspirecoding.wayairlines.features.home.state.HomeState
import com.inspirecoding.wayairlines.features.home.viewmodel.HomeViewModel
import com.inspirecoding.wayairlines.main.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getFlightsUseCase: GetFlightsUseCase = mockk(relaxed = true)
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        homeViewModel = HomeViewModel(getFlightsUseCase)
    }

    @Test
    fun `test initial state is ShowLoading and then ShowFlightsInfo when flights are fetched successfully`() = runTest {
        val flightsData = listOf(FlightsDataDomain())
        coEvery { getFlightsUseCase.getFlights() } returns flowOf(flightsData)

        homeViewModel.state.test {
            assertEquals(HomeState.ShowLoading, awaitItem())
            homeViewModel.submitAction(HomeAction.GetFlights)
            advanceUntilIdle()
            assertEquals(
                HomeState.ShowFlightsInfo(flights = flightsData.groupFlightsByCategory()),
                awaitItem()
            )
        }

        coVerify { getFlightsUseCase.getFlights() }
    }

    @Test
    fun `test ShowErrorView state when flights fetching fails`() = runTest {
        coEvery { getFlightsUseCase.getFlights() } returns flow {
            throw WayAirlinesException.ErrorNetworkException
        }

        homeViewModel.state.test {
            assertEquals(HomeState.ShowLoading, awaitItem())
            homeViewModel.submitAction(HomeAction.GetFlights)
            advanceUntilIdle()
            assertEquals(HomeState.UpdateErrorView, awaitItem())
        }

        coVerify { getFlightsUseCase.getFlights() }
    }

    @Test
    fun `test submitAction triggers correct action`() = runTest {
        val flightsData = listOf(FlightsDataDomain())
        coEvery { getFlightsUseCase.getFlights() } returns flowOf(flightsData)

        homeViewModel.state.test {
            assertEquals(HomeState.ShowLoading, awaitItem())
            homeViewModel.submitAction(HomeAction.GetFlights)
            advanceUntilIdle()
            assertEquals(
                HomeState.ShowFlightsInfo(flights = flightsData.groupFlightsByCategory()),
                awaitItem()
            )
        }
    }
}
