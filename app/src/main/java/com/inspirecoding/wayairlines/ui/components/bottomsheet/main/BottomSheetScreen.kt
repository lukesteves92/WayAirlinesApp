package com.inspirecoding.wayairlines.ui.components.bottomsheet.main

import androidx.compose.runtime.Composable
import com.inspirecoding.wayairlines.domain.model.data.FlightsDataDomain
import com.inspirecoding.wayairlines.ui.components.bottomsheet.details.DetailsScreen

@Composable
fun SheetLayout(title: String, flights: List<FlightsDataDomain>, onCloseBottomSheet: () -> Unit) {
    BottomSheetBody {
        DetailsScreen(title = title, flights = flights, onClickDeny = {
            onCloseBottomSheet.invoke()
        })
    }
}

@Composable
fun BottomSheetBody(content: @Composable () -> Unit) = content()