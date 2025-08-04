package com.example.elienorcandlev2.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.verticalScroll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 0.dp)
    ) {
        SectionTitle("Overview")
        DashboardStatRow(
            stats = listOf(
                "Pending Orders" to "12",
                "Total Customers" to "345",
                "Products" to "67",
                "Monthly Revenue" to "$8,900"
            ),
            modifier = Modifier.padding(top = 8.dp)
        )
        HorizontalDivider(color = Color(0xFFF3EDE7), thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))
        SectionTitle("Recent Orders")
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            RecentOrderRow("Ethan Carter", "Order #12345", "$50")
            RecentOrderRow("Sophia Bennett", "Order #12346", "$75")
            RecentOrderRow("Liam Harper", "Order #12347", "$100")
            RecentOrderRow("Olivia Smith", "Order #12348", "$120")
            RecentOrderRow("Noah Johnson", "Order #12349", "$60")
        }
        HorizontalDivider(color = Color(0xFFF3EDE7), thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))
        SectionTitle("Today's Stats")
        DashboardStatRow(
            stats = listOf(
                "Orders" to "5 (+20%)",
                "Revenue" to "$250 (+15%)"
            ),
            background = Color(0xFFF4ECE7),
            highlightColor = Color(0xFF07880E),
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = Color(0xFF1C140D),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun DashboardStatRow(
    stats: List<Pair<String, String>>,
    background: Color = Color.Transparent,
    highlightColor: Color = Color.Unspecified,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        stats.chunked(2).forEach { rowStats ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                rowStats.forEach { (label, value) ->
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .defaultMinSize(minHeight = 72.dp)
                            .padding(vertical = 2.dp, horizontal = 16.dp),
                        colors = CardDefaults.cardColors(containerColor = background),
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(1.dp, Color(0xFFE9DACE))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(label, fontSize = 14.sp, color = Color(0xFF1C140D), fontWeight = FontWeight.Medium, textAlign = TextAlign.Left)
                            Text(value, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = if (value.contains('+')) highlightColor else Color(0xFF1C140D), textAlign = TextAlign.Left)
                        }
                    }
                }
                if (rowStats.size < 2) Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun RecentOrderRow(name: String, orderId: String, price: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(name, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color(0xFF1C140D))
            Text(orderId, fontSize = 14.sp, color = Color(0xFF9D6F48))
        }
        Text(price, fontSize = 16.sp, color = Color(0xFF1C140D))
    }
}
