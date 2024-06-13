package com.example.mob_sae401.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mob_sae401.PreferencesManager
import com.example.mob_sae401.data.models.Review
import com.example.mob_sae401.data.models.Work
import com.example.mob_sae401.data.models.WorkType

@Composable

fun DetailPage(work: Work?) {
    work?.let {
        val context = LocalContext.current
        val preferencesManager = remember { PreferencesManager(context) }
        val data = remember {
            mutableStateOf(preferencesManager.getData("${work.type}-${work.id}", "blank"))
        }

        val booked = remember { mutableStateOf(work.booked || data.value != "blank") }

        WorkInfo(it, booked.value) {
            booked.value = true
            val reservationData = "${work.name}|${work.image}|${work.releaseDate}|5|false" // Replace "5" and "false" with actual days and overdue status if needed
            println("Saving reservation data for key ${work.type}-${work.id}: $reservationData")
            preferencesManager.saveData("${work.type}-${work.id}", reservationData)
        }
    } ?: run {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = "Aucune ressource n'a été trouvée",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}


@Composable
fun WorkInfo(
    work: Work,
    booked: Boolean,
    modifier: Modifier = Modifier,
    bookingClicked: () -> Unit
) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = work.image),
                contentDescription = work.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(20.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxHeight(.4f)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = work.name,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Director",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Red
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = work.director,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Divider(color = Color.Gray, thickness = 1.dp)

            Text(
                text = "${if (work.type == WorkType.MOVIE) "Film" else "Livre"} - ${work.releaseDate}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
            )

            Text(
                text = work.description,
                fontSize = 16.sp,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.RadioButtonChecked,
                    contentDescription = if (!work.booked) "Available" else "Unavailable",
                    modifier = Modifier.size(24.dp),
                    tint = if (!booked) Color.Green else Color.Red
                )

                Text(
                    text = if (booked) "Indisponible" else "Disponible",
                )
            }

            Button(
                onClick = bookingClicked,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA52A2A)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(24.dp)),
                enabled = !booked
            ) {
                Text(
                    text = if (!booked) "Réserver" else "Déjà réservé",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

        }

        ReviewPart(reviews = work.reviews)
    }
}

@Composable
fun ReviewPart(reviews: List<Review>, modifier: Modifier = Modifier) {
    Surface(color = Color(0xFF010423), contentColor = Color(0xFFDDDDDD)) {
        Column(Modifier) {
            Text(text = "Avis (${reviews.size})", modifier = Modifier.padding(24.dp), style = MaterialTheme.typography.headlineSmall, )
            LazyColumn(
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
            ) {
                items(reviews, key = { it.id }) {
                    Column(
                        modifier = Modifier.padding(24.dp)
                        .border(2.dp, color = Color(0xFF960F26), RoundedCornerShape(16.dp))
                        .padding(12.dp)) {
                        Row {
                            Text(text = it.writerName, fontWeight = FontWeight.SemiBold)
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(it.content)
                    }
                }
            }
        }
    }
}