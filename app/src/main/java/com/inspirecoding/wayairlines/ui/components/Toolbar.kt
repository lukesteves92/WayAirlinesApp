package com.inspirecoding.wayairlines.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.inspirecoding.wayairlines.R
import com.inspirecoding.wayairlines.ui.theme.BluePrimary
import com.inspirecoding.wayairlines.ui.theme.LightBlack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WayAirlinesToolbar(
    modifier: Modifier = Modifier,
    onBackPressed: (() -> Unit)? = null
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Image(
                modifier = Modifier
                    .size(54.dp)
                    .background(Color.Transparent),
                painter = rememberAsyncImagePainter(R.drawable.logo_way_airlines),
                contentScale = ContentScale.Fit,
                contentDescription = null
            )
        },
        navigationIcon = {
            if (onBackPressed != null) {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.way_airlines_content_accessibility_back)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = BluePrimary,
            titleContentColor = LightBlack,
            navigationIconContentColor = LightBlack
        )
    )
}