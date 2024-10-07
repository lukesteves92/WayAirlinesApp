package com.inspirecoding.wayairlines.ui.components.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.inspirecoding.wayairlines.R
import com.inspirecoding.wayairlines.ui.theme.BackgroundColor
import com.inspirecoding.wayairlines.ui.theme.ImageHover
import com.inspirecoding.wayairlines.util.Constants.Numbers.KEY_NUMBER_TWO

@Composable
fun FlightsCard(
    completionStatus: String,
    onClick: (String) -> Unit = {}
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 20.dp)
        .height(220.dp)
        .clickable {
            onClick.invoke(completionStatus)
        }) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(R.drawable.img_way_airlines)
                    .apply(block = fun ImageRequest.Builder.() {
                        error(R.drawable.img_way_airlines)
                        placeholder(R.drawable.img_way_airlines)
                    }).build()
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop,
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp))
                .background(ImageHover)
        )
        Text(
            text = completionStatus,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(all = 16.dp),
            maxLines = KEY_NUMBER_TWO,
            overflow = TextOverflow.Ellipsis,
            color = BackgroundColor,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
        )
    }
}