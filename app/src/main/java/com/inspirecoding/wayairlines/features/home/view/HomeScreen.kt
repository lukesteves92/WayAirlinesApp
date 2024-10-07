package com.inspirecoding.wayairlines.features.home.view

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.inspirecoding.wayairlines.domain.model.data.FlightsDataDomain
import com.inspirecoding.wayairlines.extensions.features.rememberFlowWithLifecycle
import com.inspirecoding.wayairlines.features.home.action.HomeAction
import com.inspirecoding.wayairlines.features.home.state.HomeState
import com.inspirecoding.wayairlines.features.home.viewmodel.HomeViewModel
import com.inspirecoding.wayairlines.ui.components.apierror.ApiErrorScreen
import com.inspirecoding.wayairlines.ui.components.bottomsheet.main.SheetLayout
import com.inspirecoding.wayairlines.ui.components.card.FlightsCard
import com.inspirecoding.wayairlines.ui.components.loading.Loading
import com.inspirecoding.wayairlines.ui.components.toolbar.WayAirlinesToolbar
import com.inspirecoding.wayairlines.ui.theme.BackgroundColor
import com.inspirecoding.wayairlines.util.Constants.Numbers.KEY_DURATION_ANIMATION
import com.inspirecoding.wayairlines.util.Constants.Text.EMPTY_STRING_DEFAULT
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = getViewModel()
) {
    val state by rememberFlowWithLifecycle(viewModel.state)
        .collectAsState(initial = HomeState.ShowLoading)

    Home(state = state) { action ->
        viewModel.submitAction(action = action)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Home(
    state: HomeState,
    action: (HomeAction) -> Unit
) {
    val bottomSheetTitle = rememberSaveable { mutableStateOf(EMPTY_STRING_DEFAULT) }
    val bottomSheetFlightsList = rememberSaveable { mutableStateOf(listOf(FlightsDataDomain())) }
    val dispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val animationSpec = remember {
        Animatable(0f)
            .run {
                TweenSpec<Float>(durationMillis = KEY_DURATION_ANIMATION)
            }
    }
    val modalState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { false },
        skipHalfExpanded = true,
        animationSpec = animationSpec
    )
    val coroutineScope = rememberCoroutineScope()


    ModalBottomSheetLayout(
        sheetState = modalState,
        sheetContent = {
            SheetLayout(
                title = bottomSheetTitle.value,
                flights = bottomSheetFlightsList.value,
                onCloseBottomSheet = {
                    coroutineScope.launch {
                        modalState.hide()
                    }
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                WayAirlinesToolbar(
                    onBackPressed = {
                        dispatcher?.onBackPressed()
                    })
            },
            containerColor = BackgroundColor,
            bottomBar = {},
            content = { values ->
                Column(modifier = Modifier.padding(values)) {
                    when (state) {
                        is HomeState.ShowLoading -> {
                            Loading()
                        }

                        is HomeState.UpdateErrorView -> ApiErrorScreen {
                            action(HomeAction.GetFlights)
                        }

                        is HomeState.ShowFlightsInfo -> {
                            ShowCompletionStatus(
                                flights = state.flights,
                                onClickFlightsCard = { name, flights ->
                                    bottomSheetTitle.value = name
                                    bottomSheetFlightsList.value = flights
                                    coroutineScope.launch {
                                        modalState.show()
                                    }
                                }
                            )
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun ShowCompletionStatus(
    flights: Map<String, List<FlightsDataDomain>>,
    onClickFlightsCard: (String, List<FlightsDataDomain>) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp)
    ) {
        items(flights.keys.toList()) { item ->
            FlightsCard(
                completionStatus = item,
                onClick = { type ->
                    onClickFlightsCard.invoke(item, flights[type].orEmpty())
                }
            )
        }
    }
}