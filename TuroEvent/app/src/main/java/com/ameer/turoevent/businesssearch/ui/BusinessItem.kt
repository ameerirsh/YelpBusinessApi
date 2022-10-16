package com.ameer.turoevent.businesssearch

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.example.Businesses


@Composable
fun BusinessItem(businesses: Businesses, index: Int, selectedIndex: Int, onClick:(Int) -> Unit) {
    val backgroundColor =
        if (index == selectedIndex) MaterialTheme.colors.primary else MaterialTheme.colors.background
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
    ) {
        Surface(color = backgroundColor) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.padding(2.dp)
                .fillMaxSize()
            ) {
                Image(painter = rememberImagePainter(data = businesses.imageUrl,
                    builder = {
                        scale(Scale.FIT)
                        transformations(CircleCropTransformation())
                    }), contentDescription = null, contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(84.dp)
                        .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
                )
            }
            Column(
                modifier = Modifier
                    .padding(120.dp, 2.dp)
                    .fillMaxWidth(),
            ) {
                Text(text = businesses.name!!, style = MaterialTheme.typography.h6, maxLines = 3, overflow = TextOverflow.Visible)
                Text(text = "Rating: "+businesses.rating.toString(), style = MaterialTheme.typography.h6)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessItem() {
    val business = Businesses("PTFxtXS47ZVRCdZIrEWvGw",
        "golden-boy-pizza-san-francisco",
        "Golden Boy Pizza",
        "https://s3-media1.fl.yelpcdn.com/bphoto/SfosPh2iWkVTMC4LLVIL8g/o.jpg",
        false,
        "https://www.yelp.com/biz/golden-boy-pizza-san-francisco?adjust_creative=51SsU9NI_tuXrEuO3-2lqg&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=51SsU9NI_tuXrEuO3-2lqg",
        4047,
        null,
        4.5,
        null,
        null,
        "$",
        null,
        "+14159829738",
        "(415) 982-9738",
        1205.687
    )
    BusinessItem(businesses = business, 0,  0) {
            i ->
    }
}
