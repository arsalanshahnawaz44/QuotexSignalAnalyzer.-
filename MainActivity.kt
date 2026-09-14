package com.example.quotexsignalanalyzer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

private val Bg = Color(0xFFF7F7F8)
private val Card = Color.White
private val Green = Color(0xFF168A4A)
private val Red = Color(0xFFC93434)
private val Amber = Color(0xFFB66A00)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { SignalAnalyzerApp() }
    }
}

@Composable
fun SignalAnalyzerApp() {
    var pair by remember { mutableStateOf("EUR/USD") }
    var timeframe by remember { mutableStateOf("1M") }
    var signal by remember { mutableStateOf("WAIT") }
    var confidence by remember { mutableIntStateOf(0) }
    var seconds by remember { mutableIntStateOf(60) }

    LaunchedEffect(timeframe) {
        while (true) {
            delay(1000)
            seconds = if (seconds <= 1) 60 else seconds - 1
        }
    }

    fun analyze() {
        // Demo analysis engine: deterministic UI demo, NOT a live trading feed.
        // Replace this function with a real market-data provider before production use.
        val seed = (System.currentTimeMillis() / 10000).toInt()
        val score = kotlin.math.abs(seed) % 101
        confidence = score
        signal = when {
            score >= 70 -> "CALL"
            score <= 30 -> "PUT"
            else -> "WAIT"
        }
    }

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = Bg) {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text("Signal Analyzer", fontSize = 27.sp, fontWeight = FontWeight.Bold)
                Text("Market analysis dashboard", color = Color.Gray)

                Text("Asset", fontWeight = FontWeight.SemiBold)
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(listOf("EUR/USD", "GBP/USD", "USD/JPY", "AUD/USD", "BTC/USD")) { p ->
                        FilterChip(selected = pair == p, onClick = { pair = p }, label = { Text(p) })
                    }
                }

                Text("Timeframe", fontWeight = FontWeight.SemiBold)
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(listOf("1M", "2M", "5M", "10M", "15M", "30M", "1H")) { tf ->
                        FilterChip(selected = timeframe == tf, onClick = {
                            timeframe = tf
                            seconds = 60
                        }, label = { Text(tf) })
                    }
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("$pair • $timeframe", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                        Spacer(Modifier.height(12.dp))
                        Text(signal, fontSize = 38.sp, fontWeight = FontWeight.ExtraBold,
                            color = when(signal) { "CALL" -> Green; "PUT" -> Red; else -> Amber })
                        Text(
                            if (confidence == 0) "Run analysis" else "Confidence: $confidence%",
                            fontSize = 17.sp
                        )
                        Spacer(Modifier.height(8.dp))
                        Text("Next candle timer: ${seconds}s", color = Color.Gray)
                        Spacer(Modifier.height(16.dp))
                        Button(onClick = { analyze() }, modifier = Modifier.fillMaxWidth()) {
                            Text("ANALYZE")
                        }
                    }
                }

                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)) {
                    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Analysis factors", fontWeight = FontWeight.Bold)
                        Text("• EMA trend")
                        Text("• RSI momentum")
                        Text("• MACD direction")
                        Text("• Bollinger Bands")
                        Text("• Support / Resistance")
                        Text("• Candlestick confirmation")
                    }
                }

                Text(
                    "Demo only: this build does not connect to Quotex or execute trades. " +
                    "Signals are not guaranteed predictions. Connect a licensed/reliable market-data source " +
                    "and validate the strategy with historical data before real use.",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}
