package com.inspirecoding.wayairlines.ui.components.bottomsheet.title

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.inspirecoding.wayairlines.R
import com.inspirecoding.wayairlines.ui.theme.BackgroundColor
import com.inspirecoding.wayairlines.ui.theme.BluePrimary
import com.inspirecoding.wayairlines.ui.theme.Typography

@Composable
fun BottomSheetTitle(
    title: String?,
    headerBackground: Color = BluePrimary,
    showCloseButton: Boolean = true,
    emptyBottomSheet: Boolean = false,
    onClose: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = if (emptyBottomSheet) Color.Transparent else headerBackground,
                shape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp)
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        Card(
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth(.2f)
                .alpha(.1f),
        ) {
            Spacer(
                modifier = Modifier
                    .height(5.dp)
                    .fillMaxWidth(.1f)
                    .background(
                        if (emptyBottomSheet) Color.Transparent else Color.Black,
                        shape = RoundedCornerShape(10.dp)
                    )
            )
        }

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ){
            title?.let {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 16.dp)
                        .align(Alignment.Center),
                    text = it,
                    textAlign = TextAlign.Center,
                    color = BackgroundColor,
                    fontSize = 18.sp,
                    style = Typography.headlineMedium
                )
            }

            if(!emptyBottomSheet && showCloseButton) {
                Image(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clickable { onClose() },
                    painter = painterResource(id = R.drawable.way_airlines_ic_close),
                    contentDescription = null
                )
            }
        }
    }
}