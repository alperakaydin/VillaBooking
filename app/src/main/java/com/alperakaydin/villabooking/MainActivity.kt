package com.alperakaydin.villabooking

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alperakaydin.villabooking.ui.theme.VillaBookingTheme
import com.alperakaydin.villabooking.ui.theme.pacifico

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VillaBookingTheme {
                MainScreen(
                )
            }
        }
    }
}
// Data
data class Villa(
    val imageRes: Int,
    val name: String,
    val location: String,
    val rating: Double
)
data class City(
    val name: String,
    val imageResId: Int
)

// Moc Data
val cities = listOf(
    City("Cancun", R.drawable.cancun),
    City("Venice", R.drawable.cancun),
    City("Macao", R.drawable.cancun),
    City("London", R.drawable.cancun),
    City("Bern", R.drawable.cancun)
)

val villas = listOf(
    Villa(R.drawable.villa1, "Schleifer Villa", "Venice, Italy", 4.9),
    Villa(R.drawable.villa1, "Dorwart Villa", "Venice, Italy", 4.9),
    Villa(R.drawable.villa1, "Sunset Villa", "Paris, France", 4.7),
    Villa(R.drawable.villa1, "Palm Beach Villa", "Malibu, USA", 4.8),
    Villa(R.drawable.villa1, "Wonderful Villa", "Paris, France", 4.7),
    Villa(R.drawable.villa1, "Extra Beach Villa", "Florida, USA", 4.8)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(darkTheme: Boolean = isSystemInDarkTheme()) {
    Scaffold(
        modifier = Modifier
        .background(MaterialTheme.colorScheme.background),
        topBar = {
            CustomTopBarWithSearch()
        },
        content = { paddingValues ->
            LazyColumn (modifier = Modifier.padding(paddingValues).padding(start = 15.dp, end = 15.dp)) {
                item { Image(
                    painter = painterResource(id = R.drawable.villa1),
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .clip(
                            RoundedCornerShape(16.dp)
                        )
                ) }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(id = R.string.popular_city),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(stringResource(id = R.string.see_all),)
                    }
                    CityRowComponent(cities)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(id = R.string.favorites_place),
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        )
                        Text(stringResource(id = R.string.see_all),)
                    }
                    VillaCardGrid(villas)
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityRowComponent(cities:List<City>) {

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        LazyRow(
            modifier = Modifier

                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items(cities) { city ->
                CityCard(city)
            }
        }
    }
}

@Composable
fun CityCard(city: City) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = city.imageResId),
            contentDescription = city.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Text(
            text = city.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f) ,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}


@Composable
fun VillaCardGrid(villas:List<Villa>) {

    LazyVerticalGrid  (
        columns = GridCells.Fixed(2),
        modifier = Modifier.height(400.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(villas) { villa ->
            VillaCard(villa)
        }
    }
}

@Composable
fun VillaCard(villa: Villa) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                Image(
                    painter = painterResource(id = villa.imageRes),
                    contentDescription = villa.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color.White.copy(alpha = 0.7f))
                        .padding(horizontal = 6.dp, vertical = 2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating Star",
                        tint = Color(0xFFFFD700),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = villa.rating.toString(),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 12.sp
                    )
                }
            }

            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = villa.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.villa1),
                        contentDescription = "Location Icon",
                        tint = Color.Gray,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = villa.location,
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBarWithSearch() {
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth().padding(start = 15.dp, end = 15.dp)
    ) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            title = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = stringResource(id = R.string.happy_holiday),
                        fontFamily = pacifico,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                    Text(
                        text = stringResource(id = R.string.user_name),
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                        fontSize = 22.sp
                    )
                }
            },
            actions = {
                ProfileImage()
            },
        )
        // Search Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(50)) // Yuvarlak köşeler
                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)) // Hafif gri arka plan
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (searchText.isEmpty()) {
                    Text(
                        text = stringResource(id = R.string.search_placeholder),

                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        fontSize = 16.sp
                    )
                }

                BasicTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    singleLine = true,
                    modifier = Modifier
                        .weight(1f)
                )

                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Composable
fun ProfileImage() {
    val image: Painter = painterResource(id = R.drawable.profile_picture)
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(40.dp)
    ) {

        Image(
            painter = image,
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
                .clip(CircleShape)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VillaBookingTheme {
        MainScreen()
    }
}
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES, // Dark Theme
    showBackground = true,
    locale = "tr"
)
@Composable
fun DarkModePreview() {
    VillaBookingTheme {
        MainScreen()
    }
}