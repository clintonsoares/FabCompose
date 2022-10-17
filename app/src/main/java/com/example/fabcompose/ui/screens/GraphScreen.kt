package com.example.fabcompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fabcompose.ui.custom.BackIconButton
import com.example.fabcompose.ui.custom.MultiLineGraph
import com.example.fabcompose.ui.custom.SingleLineGraph
import com.example.fabcompose.ui.theme.PrimaryColor
import com.example.fabcompose.ui.theme.SimpleColors
import com.example.fabcompose.utils.StringConstants
import com.example.fabcompose.utils.Utilities.Companion.Categories
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import hu.ma.charts.ChartShape
import hu.ma.charts.legend.data.LegendEntry
import hu.ma.charts.legend.data.LegendPosition
import hu.ma.charts.line.LineChart
import hu.ma.charts.line.data.ChartColors
import hu.ma.charts.line.data.LineChartData
import hu.ma.charts.pie.PieChart
import hu.ma.charts.pie.data.PieChartData
import hu.ma.charts.pie.data.PieChartEntry
import hu.ma.charts.table.TableChart
import hu.ma.charts.table.data.TableEntry
import kotlin.math.roundToInt
import kotlin.random.Random


@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun GraphScreenLayout(
    navigator: DestinationsNavigator
) {
    Scaffold(
        topBar = { TopBarLayout(navigator = navigator) }
    ) { screenPadding ->
        val yStep = 50
        val random = Random
        /* to test with random points */
        val points = (0..9).map {
            var num = random.nextInt(350)
            if (num <= 50)
                num += 100
            num.toFloat()
        }
        val pointsList = (0..3).map {
            (0..9).map {
                var num = random.nextInt(350)
                if (num <= 50)
                    num += 100
                num.toFloat()
            }
        }
        val pieEntries = points.map { value ->
            PieChartEntry(value, AnnotatedString("$value"))
        }
        val pieChartData = PieChartData(
            entries = pieEntries,
            colors = SimpleColors,
            legendPosition = LegendPosition.Bottom,
            legendShape = CircleShape
        )
        val lineChartData = LineChartData(
            series = listOf(
                LineChartData.SeriesData(
                    "Line A",
                    points = listOf(
                        LineChartData.SeriesData.Point(0, 0f),
                        LineChartData.SeriesData.Point(1, 10.0f),
                        LineChartData.SeriesData.Point(2, 20.0f),
                        LineChartData.SeriesData.Point(3, 30.0f),
                        LineChartData.SeriesData.Point(4, 50.0f),
                        LineChartData.SeriesData.Point(5, 35.0f),
                    ),
                    Color.Red
                ),
                LineChartData.SeriesData(
                    "Line B",
                    points = listOf(
                        LineChartData.SeriesData.Point(0, 20f),
                        LineChartData.SeriesData.Point(1, 10.0f),
                        LineChartData.SeriesData.Point(2, 5.0f),
                        LineChartData.SeriesData.Point(3, 15.0f),
                        LineChartData.SeriesData.Point(4, 30.0f),
                        LineChartData.SeriesData.Point(5, 35.0f),
                    ),
                    Color.Blue
                ),
            ),
            xLabels = listOf("Year 1", "2", "3", "4", "5", "6"),
            xAxisTypeface = TextStyle(
                fontSize = 10.sp,
                color = ChartColors.defaultColors().xlabel
            )
        )

        Column(
            modifier = Modifier
                .padding(screenPadding)
                .background(Color.DarkGray)
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .weight(weight = 1f, fill = false)
            ) {
                SingleLineGraph(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                        .height(500.dp),
                    xValues = (0..9).map { it + 1 },
                    yValues = (0..6).map { (it + 1) * yStep },
                    points = points,
                    paddingSpace = 16.dp,
                    verticalStep = yStep
                )
                Spacer(modifier = Modifier.height(8.dp))
                MultiLineGraph(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                        .height(500.dp),
                    xValues = (0..9).map { it + 1 },
                    yValues = (0..6).map { (it + 1) * yStep },
                    pointsList = pointsList,
                    paddingSpace = 16.dp,
                    verticalStep = yStep,
                    fillDotMarkers = false
                )
                Spacer(modifier = Modifier.height(8.dp))
                LineChart(
                    data = lineChartData,
                    chartHeight = 400.dp,
                )
                Spacer(modifier = Modifier.height(8.dp))
                PieChart(
                    data = pieChartData,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    sliceWidth = 80.dp,
                    chartSize = 200.dp,
                    chartShapeSize = 20.dp
                )
                Spacer(modifier = Modifier.height(8.dp))
                PieChart(
                    data = pieChartData,
                    legend = { entries ->
                        CustomVerticalLegend(entries = entries)
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TableChart(data = points.mapIndexed { idx, value ->
                    TableEntry(
                        key = AnnotatedString("$value"),
                        value = AnnotatedString(
                            (value).toString()
                        ),
                        drawShape = ChartShape(
                            size = 8.dp,
                            shape = CircleShape,
                            color = SimpleColors[idx],
                        )
                    )
                })
            }
        }
    }
}

@Composable
fun TopBarLayout(
    navigator: DestinationsNavigator
) {
    TopAppBar(
        title = {
            Text(text = StringConstants.GRAPHS_SCREEN)
        },
        navigationIcon = {
            BackIconButton(navigator = navigator)
        },
        backgroundColor = PrimaryColor
    )
}

@Composable
internal fun RowScope.CustomVerticalLegend(entries: List<LegendEntry>) {
    Column(
        modifier = Modifier.Companion.weight(1f),
    ) {
        entries.forEachIndexed { idx, item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 14.dp)
            ) {
                Box(
                    Modifier
                        .requiredSize(item.shape.size)
                        .background(item.shape.color, item.shape.shape)
                )

                Spacer(modifier = Modifier.requiredSize(8.dp))

                Text(
                    text = item.text,
                    style = MaterialTheme.typography.caption
                )
                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = buildValuePercentString(item),
                    style = MaterialTheme.typography.caption,
                )
            }

            if (idx != entries.lastIndex)
                Divider()
        }
    }
}

@Composable
internal fun buildValuePercentString(item: LegendEntry) = buildAnnotatedString {
    item.value?.let { value ->
        withStyle(
            style = MaterialTheme.typography.body2.toSpanStyle()
                .copy(color = MaterialTheme.colors.primary)
        ) {
            append(value.toInt().toString())
        }
        append(" ")
    }

    withStyle(
        style = MaterialTheme.typography.caption.toSpanStyle()
            .copy(color = MaterialTheme.colors.secondary)
    ) {
        val percentString = item.percent.roundToInt().toString()
        append("($percentString %)")
    }
}

internal val TableSampleData = listOf(
    "Without Shape" to (1..6).map {
        TableEntry(
            key = AnnotatedString(Categories[it]),
            value = AnnotatedString((it * Random(System.currentTimeMillis()).nextInt(123)).toString()),
        )
    },
    "With Shape" to (1..5).map {
        TableEntry(
            key = AnnotatedString(Categories[it]),
            value = AnnotatedString((it * Random(System.currentTimeMillis()).nextInt(123)).toString()),
            drawShape = ChartShape(
                size = 8.dp,
                shape = CircleShape,
                color = SimpleColors[it],
            )
        )
    },
)