package com.kodeco.smart.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kodeco.smart.models.Product

@Composable
fun ProductListRow(
    product: Product,
    isFavoritesFeatureEnabled: Boolean,
    onClick: () -> Unit,
    onFavorite: () -> Unit,
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Card(
                shape = RoundedCornerShape(8.dp)
            ) {
                AsyncImage(
                    model = product.productImageUrl,
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.size(100.dp)
                )
            }
            Spacer(modifier = Modifier.size(4.dp))

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,

                ) {
                Text(
                    text = "Name: ${product.productTitle}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp,
                )

                Text(
                    text = "Category: ${product.productCategory}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp,
                )

                if (isFavoritesFeatureEnabled) {
                    ProductFavoriteStar(product = product, onTap = onFavorite)
                }
            }
        }
    }
}

val sampleProduct = Product(
    id = 1,
    productTitle = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
    productCategory = "men's clothing",
    productDescription = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
    productImageUrl = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
    productPrice = 109.95,
    isFavorite = false,
)

@Preview
@Composable
fun ProductListRowPreview() {
    ProductListRow(
        sampleProduct,
        isFavoritesFeatureEnabled = true,
        onClick = {},
        onFavorite = {},
    )
}

