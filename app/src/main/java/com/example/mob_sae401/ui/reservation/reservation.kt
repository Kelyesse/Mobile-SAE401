package com.example.mob_sae401.ui.reservation

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mob_sae401.PreferencesManager
import com.example.mob_sae401.R

data class Reservation(
    val imageResId: Int,
    val title: String,
    val days: Int,
    val date: String,
    val isOverdue: Boolean
)

fun getReservations(context: Context): List<Reservation> {
    val preferencesManager = PreferencesManager(context)
    val reservations = mutableListOf<Reservation>()

    for (i in 1..100) { // Vérifiez jusqu'à 100 entrées, ajustez ce nombre si nécessaire
        val movieKey = "movie-$i"
        val bookKey = "book-$i"

        val movieData = preferencesManager.getData(movieKey, "blank")
        if (movieData != "blank") {
            println("Movie data found for key $movieKey: $movieData")
            val dataParts = movieData.split("|")
            if (dataParts.size >= 5) {
                try {
                    reservations.add(
                        Reservation(
                            imageResId = dataParts[1].toInt(), // Assuming the image ID is stored as an int
                            title = dataParts[0],
                            days = dataParts[3].toInt(),
                            date = dataParts[2],
                            isOverdue = dataParts[4].toBoolean()
                        )
                    )
                } catch (e: Exception) {
                    println("Error parsing movie reservation: ${e.message}")
                }
            } else {
                println("Movie data has insufficient parts for key $movieKey: $dataParts")
            }
        }

        val bookData = preferencesManager.getData(bookKey, "blank")
        if (bookData != "blank") {
            println("Book data found for key $bookKey: $bookData")
            val dataParts = bookData.split("|")
            if (dataParts.size >= 5) {
                try {
                    reservations.add(
                        Reservation(
                            imageResId = dataParts[1].toInt(), // Assuming the image ID is stored as an int
                            title = dataParts[0],
                            days = dataParts[3].toInt(),
                            date = dataParts[2],
                            isOverdue = dataParts[4].toBoolean()
                        )
                    )
                } catch (e: Exception) {
                    println("Error parsing book reservation: ${e.message}")
                }
            } else {
                println("Book data has insufficient parts for key $bookKey: $dataParts")
            }
        }
    }

    return reservations
}


@Composable
fun ReservationItem(
    reservation: Reservation,
    sectionTitle: String // pour la section titre on passe ce param
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = sectionTitle,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Image(
                    painter = painterResource(id = reservation.imageResId),
                    contentDescription = reservation.title,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(16.dp))
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = reservation.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = if (reservation.isOverdue) "${reservation.days} jours en retard" else "${reservation.days} jours restants",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (reservation.isOverdue) Color.Red else Color.Green
                )

                Text(
                    text = "(${reservation.date})",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = "Arrow",
                tint = Color.Red,
                modifier = Modifier
                    .size(24.dp)
                    .padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun ReservationPage() {
    val context = LocalContext.current
    val reservations = getReservations(context)
    val overdueReservations = reservations.filter { it.isOverdue }
    val activeReservations = reservations.filter { !it.isOverdue }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Vos réservations",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Divider(color = Color(0xFFA52A2A), thickness = 2.dp)

        Spacer(modifier = Modifier.height(16.dp))

        overdueReservations.forEach { reservation ->
            ReservationItem(reservation = reservation, sectionTitle = "Réservations en retard")
        }

        Spacer(modifier = Modifier.height(16.dp))

        activeReservations.forEach { reservation ->
            ReservationItem(reservation = reservation, sectionTitle = "Réservations actives")
        }
    }
}
