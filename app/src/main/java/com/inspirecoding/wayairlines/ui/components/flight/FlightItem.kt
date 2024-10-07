package com.inspirecoding.wayairlines.ui.components.flight

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.inspirecoding.wayairlines.R
import com.inspirecoding.wayairlines.domain.model.data.FlightsDataDomain
import com.inspirecoding.wayairlines.extensions.string.formatFlightDetails
import com.inspirecoding.wayairlines.ui.shape.Shapes
import com.inspirecoding.wayairlines.ui.theme.LightBlack
import com.inspirecoding.wayairlines.util.Constants.Status.KEY_DONE_STATUS

@Composable
fun FlightItem(
    modifier: Modifier = Modifier,
    flightsDataDomain: FlightsDataDomain
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White, shape = Shapes.large)
            .padding(8.dp)
    ) {

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = flightsDataDomain.flightId.orEmpty(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = LightBlack
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = flightsDataDomain.departureAirport.orEmpty(),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = LightBlack
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = flightsDataDomain.arrivalAirport.orEmpty(),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = LightBlack
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = flightsDataDomain.formatFlightDetails(),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = LightBlack
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AirplaneItem(item = flightsDataDomain.airplaneName.orEmpty())
                StatusItem(status = flightsDataDomain.status.orEmpty())
            }

        }
    }
}

@Composable
fun StatusItem(status: String) {
    Surface(
        shape = RoundedCornerShape(100.dp),
        color = when (status) { KEY_DONE_STATUS -> Color.Green.copy(alpha = 0.1f) else -> Color.Red.copy(alpha = 0.1f) }
    ) {
        Text(
            modifier = Modifier.padding(start = 14.dp, end = 14.dp, top = 8.dp, bottom = 10.dp),
            text = status,
            color = LightBlack,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
        )
    }
}

@Composable
fun AirplaneItem(item: String) {
    Surface(
        shape = RoundedCornerShape(100.dp),
        color = LightBlack.copy(alpha = 0.1f)
    ) {
        Row(
            modifier = Modifier.padding(start = 14.dp, end = 14.dp, top = 8.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_local_airport_24),
                contentDescription = stringResource(id = R.string.way_airlines_content_accessibility_back)
            )

            Text(
                modifier = Modifier.padding(start = 14.dp),
                text = item,
                color = LightBlack,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
            )
        }
    }
}