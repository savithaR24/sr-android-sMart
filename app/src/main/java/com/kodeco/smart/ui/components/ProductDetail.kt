package com.kodeco.smart.ui.components

//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kodeco.smart.models.Product

@Composable
fun ProductDetail(
    modifier: Modifier,
    product: Product,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            var expanded by remember { mutableStateOf(false) }
            val imageTransition = updateTransition(
                targetState = expanded,
                label = "${product.productTitle}_details_transition",
            )
            val imageSize by imageTransition.animateDp(
                label = "${product.productTitle}_details_size",
            ) { state ->
                if (state) {
                    300.dp
                } else {
                    150.dp
                }
            }
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.productImageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(imageSize)
                    .clickable { expanded = !expanded },
            )
        }
        item { Text(text = "\nName - \n${product.productTitle}") }
        item { Text(text = "\nDescription - \n${product.productDescription}") }
        item { Text(text = "\nCategory - \n${product.productCategory}") }
        item { Text(text = "\nPrice - \n${product.productPrice}") }
    }
}

@Preview
@Composable
fun ProductDetailPreview() {
    ProductDetail(
        modifier = Modifier.padding(10.dp),
        sampleProduct,
    )
}