package com.kodeco.smart.ui.components

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kodeco.smart.R
import com.kodeco.smart.models.Product
import com.kodeco.smart.ui.theme.DarkOrange
import com.kodeco.smart.ui.theme.LightOrange

@Composable
fun ProductFavoriteStar(
    product: Product,
    onTap: () -> Unit,
) {
    val favoriteTransition = updateTransition(
        targetState = product.isFavorite,
        label = "${product.productTitle}_favorite_transition",
    )
    val scaleAnimation by favoriteTransition.animateFloat(
        transitionSpec = {
            if (!product.isFavorite) tween(0) else {
                keyframes {
                    durationMillis = 1000
                    1.0f at 0 using FastOutSlowInEasing
                    0.75f at 400 using FastOutSlowInEasing
                    1.5f at 700 using FastOutSlowInEasing
                    1.0f at 1000 using FastOutSlowInEasing
                }
            }
        },
        label = "${product.productTitle}_favorite_size",
    ) { state ->
        if (state) {
            1.5f
        } else {
            1.0f
        }
    }
    val rotationAnimation by favoriteTransition.animateFloat(
        transitionSpec = { tween(if (!product.isFavorite) 0 else 750) },
        label = "${product.productTitle}_favorite_rotation",
    ) { state ->
        if (state) {
            360.0f
        } else {
            0.0f
        }
    }
    val colorAnimation by favoriteTransition.animateColor(
        transitionSpec = {
            if (!product.isFavorite) tween(0) else {
                keyframes {
                    durationMillis = 1000
                    DarkOrange at 700 using FastOutSlowInEasing
                    LightOrange at 1000 using FastOutSlowInEasing
                }
            }
        },
        label = "${product.productTitle}_favorite_color",
    ) { state ->
        if (state) {
            LightOrange
        } else {
            LocalContentColor.current
        }
    }
    Crossfade(
        targetState = product.isFavorite,
        animationSpec = tween(500),
        modifier = Modifier.padding(all = 8.dp),
        label = "${product.productTitle}_favorite_crossfade",
    ) { state ->
        IconButton(onClick = onTap) {
            Icon(
                painter = painterResource(
                    id = if (state) {
                        R.drawable.star_filled
                    } else {
                        R.drawable.star_outline
                    },
                ),
                contentDescription = "favorite",
                modifier = Modifier
                    .padding(all = 8.dp)
                    .size(32.dp)
                    .graphicsLayer(
                        scaleX = scaleAnimation,
                        scaleY = scaleAnimation,
                        rotationZ = rotationAnimation,
                    ),
                tint = colorAnimation,
            )
        }
    }
}