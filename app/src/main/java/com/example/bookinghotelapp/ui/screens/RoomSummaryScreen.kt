package com.example.bookinghotelapp.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bookinghotelapp.data.Booking
@Composable
fun SummaryScreen(
    booking: Booking,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    val amenities = booking.room.tiennghi
    val price = booking.room.gia
    val roomType = stringResource(id = booking.room.loai)
    val tongtien = booking.totalPrice

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 50.dp, start = 16.dp, end = 16.dp)
            .border(2.dp, Color.Gray, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text(text = roomType)
        Text(text = "Giá: $price USD")
        Text(text = "Tiện nghi: " + amenities.map { stringResource(it) }.joinToString(", "))
        Text(text = "Số lượng phòng: ${booking.quantity.toInt()}")
        Text(text = "Tổng giá tiền: $tongtien $")
        Text(text = "Phòng đã được đặt. Hãy thanh toán trong vòng 3 giờ")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onCancel) {
            Text("Trở lại")
        }
    }
}