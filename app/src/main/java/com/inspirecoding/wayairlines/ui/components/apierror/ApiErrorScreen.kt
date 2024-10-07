package com.inspirecoding.wayairlines.ui.components.apierror

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.inspirecoding.wayairlines.R
import com.inspirecoding.wayairlines.ui.components.button.ButtonOutline
import com.inspirecoding.wayairlines.ui.theme.BackgroundColor
import com.inspirecoding.wayairlines.ui.theme.LightBlack
import com.inspirecoding.wayairlines.ui.theme.Typography

@Composable
fun ApiErrorScreen(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.wrapContentSize(),
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(R.drawable.way_airlines_ic_error).apply(block = fun ImageRequest.Builder.() {
                    error(R.drawable.way_airlines_ic_error)
                }).build()
            ),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.alpha(0.75f),
            text = stringResource(id = R.string.way_airlines_tv_title_apierror),
            style = Typography.titleMedium,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.alpha(0.5f),
            text = stringResource(id = R.string.way_airlines_tv_body_apierror),
            style = Typography.titleSmall,
            color = LightBlack,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(48.dp))

        ButtonOutline(
            border = BorderStroke(1.dp, Color.Black),
            textStyle = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            ),
            text = stringResource(id = R.string.way_airlines_re_try),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 32.dp),
            onClick = {
                onClick()
            }
        )
    }
}