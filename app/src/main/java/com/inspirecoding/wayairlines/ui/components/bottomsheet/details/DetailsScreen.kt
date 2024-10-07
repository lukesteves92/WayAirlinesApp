package com.inspirecoding.wayairlines.ui.components.bottomsheet.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.inspirecoding.wayairlines.domain.model.data.FlightsDataDomain
import com.inspirecoding.wayairlines.ui.components.bottomsheet.title.BottomSheetTitle
import com.inspirecoding.wayairlines.ui.components.flight.FlightItem
import com.inspirecoding.wayairlines.ui.theme.BackgroundColor

@Composable
fun DetailsScreen(title: String, flights: List<FlightsDataDomain>, onClickDeny: () -> Unit)
{

    BottomSheetTitle(title = title) {
        onClickDeny.invoke()
    }

    Scaffold(
        topBar = {},
        bottomBar = {},
        containerColor = BackgroundColor,
        content = { values ->
            Column(modifier = Modifier.padding(values)) {
                LazyColumn(
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(flights) { item ->
                        FlightItem(flightsDataDomain = item)
                    }
                }
            }
        }
    )
}