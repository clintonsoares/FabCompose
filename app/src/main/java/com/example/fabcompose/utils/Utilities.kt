package com.example.fabcompose.utils


import java.text.SimpleDateFormat
import java.util.*

class Utilities {
    companion object {
        fun getCurrentDate(): String {
            val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT+05:30");
            return simpleDateFormat.format(Date())
        }

        val Categories = listOf(
            "Teams",
            "Locations",
            "Devices",
            "People",
            "Laptops",
            "Titles",
            "Flowers",
            "Bugs",
            "Windows",
            "Screens",
            "Colors",
            "Bottles",
            "Cars",
            "Tricks",
        )

        /*

        private val axisLabelTextStyle = TextStyle(
  fontSize = 10.sp,
  color = ChartColors.defaultColors().xlabel
)

internal val LinesSampleData = listOf(
    "Lines" to LineChartData(
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
        xAxisTypeface = axisLabelTextStyle
    ),
    "Gradient fill" to LineChartData(
        series = listOf(
            LineChartData.SeriesData(
                title = "Line A",
                points = listOf(
                    LineChartData.SeriesData.Point(0, 0f),
                    LineChartData.SeriesData.Point(1, 10.0f),
                    LineChartData.SeriesData.Point(2, 20.0f),
                    LineChartData.SeriesData.Point(3, 30.0f),
                    LineChartData.SeriesData.Point(4, 50.0f),
                    LineChartData.SeriesData.Point(5, 35.0f),
                ),
                Color.Red, gradientFill = true
            ),
            LineChartData.SeriesData(
                title = "Line B",
                points = listOf(
                    LineChartData.SeriesData.Point(0, 20f),
                    LineChartData.SeriesData.Point(1, 10.0f),
                    LineChartData.SeriesData.Point(2, 5.0f),
                    LineChartData.SeriesData.Point(3, 15.0f),
                    LineChartData.SeriesData.Point(4, 30.0f),
                    LineChartData.SeriesData.Point(5, 35.0f),
                ),
                Color.Blue, gradientFill = true
            ),
        ),
    ),
    "Y-axis labels" to LineChartData(
        series = listOf(
            LineChartData.SeriesData(
                title = "Line A",
                points = listOf(
                    LineChartData.SeriesData.Point(0, 0f),
                    LineChartData.SeriesData.Point(1, 10.0f),
                    LineChartData.SeriesData.Point(2, 20.0f),
                    LineChartData.SeriesData.Point(3, 30.0f),
                    LineChartData.SeriesData.Point(4, 50.0f),
                    LineChartData.SeriesData.Point(5, 35.0f),
                ),
                Color.Red, gradientFill = true
            ),
            LineChartData.SeriesData(
                title = "Line B",
                points = listOf(
                    LineChartData.SeriesData.Point(0, 20f),
                    LineChartData.SeriesData.Point(1, 10.0f),
                    LineChartData.SeriesData.Point(2, 5.0f),
                    LineChartData.SeriesData.Point(3, 15.0f),
                    LineChartData.SeriesData.Point(4, 30.0f),
                    LineChartData.SeriesData.Point(5, 35.0f),
                ),
                Color.Blue, gradientFill = true
            ),
        ),
        yLabels = listOf(
            AxisLabel(0f, "0K"),
            AxisLabel(20f, "20K"),
            AxisLabel(40f, "40K"),
        ),
        drawAxis = DrawAxis.X,
        xAxisTypeface = axisLabelTextStyle,
        yAxisTypeface = axisLabelTextStyle
    ),
    "Y-axis labels w/lines" to LineChartData(
        series = listOf(
            LineChartData.SeriesData(
                title = "Line A",
                points = listOf(
                    LineChartData.SeriesData.Point(0, 0f),
                    LineChartData.SeriesData.Point(1, 10.0f),
                    LineChartData.SeriesData.Point(2, 20.0f),
                    LineChartData.SeriesData.Point(3, 30.0f),
                    LineChartData.SeriesData.Point(4, 50.0f),
                    LineChartData.SeriesData.Point(5, 35.0f),
                ),
                Color.Red, gradientFill = true
            ),
            LineChartData.SeriesData(
                title = "Line B",
                points = listOf(
                    LineChartData.SeriesData.Point(0, 20f),
                    LineChartData.SeriesData.Point(1, 10.0f),
                    LineChartData.SeriesData.Point(2, 5.0f),
                    LineChartData.SeriesData.Point(3, 15.0f),
                    LineChartData.SeriesData.Point(4, 30.0f),
                    LineChartData.SeriesData.Point(5, 35.0f),
                ),
                Color.Blue, gradientFill = true
            ),
        ),
        yLabels = listOf(
            AxisLabel(0f, "0K"),
            AxisLabel(20f, "20K"),
            AxisLabel(40f, "40K"),
        ),
        drawAxis = DrawAxis.X,
        horizontalLines = true,
        xAxisTypeface = axisLabelTextStyle,
        yAxisTypeface = axisLabelTextStyle
    ),
    "Legend top" to LineChartData(
        series = listOf(
            LineChartData.SeriesData(
                title = "Line A",
                points = listOf(
                    LineChartData.SeriesData.Point(0, 0f),
                    LineChartData.SeriesData.Point(1, 10.0f),
                    LineChartData.SeriesData.Point(2, 20.0f),
                    LineChartData.SeriesData.Point(3, 30.0f),
                    LineChartData.SeriesData.Point(4, 50.0f),
                    LineChartData.SeriesData.Point(5, 35.0f),
                ),
                Color.Red, gradientFill = true
            ),
            LineChartData.SeriesData(
                title = "Line B",
                points = listOf(
                    LineChartData.SeriesData.Point(0, 20f),
                    LineChartData.SeriesData.Point(1, 10.0f),
                    LineChartData.SeriesData.Point(2, 5.0f),
                    LineChartData.SeriesData.Point(3, 15.0f),
                    LineChartData.SeriesData.Point(4, 30.0f),
                    LineChartData.SeriesData.Point(5, 35.0f),
                ),
                Color.Blue, gradientFill = true
            ),
        ),
        yLabels = listOf(
            AxisLabel(0f, "0K"),
            AxisLabel(20f, "20K"),
            AxisLabel(40f, "40K"),
        ),
        drawAxis = DrawAxis.X,
        horizontalLines = true,
        legendPosition = LegendPosition.Top,
        xAxisTypeface = axisLabelTextStyle,
        yAxisTypeface = axisLabelTextStyle
    ),
    "Legend bottom" to LineChartData(
        series = listOf(
            LineChartData.SeriesData(
                title = "Line A",
                points = listOf(
                    LineChartData.SeriesData.Point(0, 0f),
                    LineChartData.SeriesData.Point(1, 10.0f),
                    LineChartData.SeriesData.Point(2, 20.0f),
                    LineChartData.SeriesData.Point(3, 30.0f),
                    LineChartData.SeriesData.Point(4, 50.0f),
                    LineChartData.SeriesData.Point(5, 35.0f),
                ),
                Color.Red, gradientFill = true
            ),
            LineChartData.SeriesData(
                title = "Line B",
                points = listOf(
                    LineChartData.SeriesData.Point(0, 20f),
                    LineChartData.SeriesData.Point(1, 10.0f),
                    LineChartData.SeriesData.Point(2, 5.0f),
                    LineChartData.SeriesData.Point(3, 15.0f),
                    LineChartData.SeriesData.Point(4, 30.0f),
                    LineChartData.SeriesData.Point(5, 35.0f),
                ),
                Color.Blue, gradientFill = true
            ),
        ),
        yLabels = listOf(
            AxisLabel(0f, "0K"),
            AxisLabel(20f, "20K"),
            AxisLabel(40f, "40K"),
        ),
        drawAxis = DrawAxis.X,
        horizontalLines = true,
        legendPosition = LegendPosition.Bottom,
        xAxisTypeface = axisLabelTextStyle,
        yAxisTypeface = axisLabelTextStyle
    ),
    "Legend start" to LineChartData(
        series = listOf(
            LineChartData.SeriesData(
                title = "Line A",
                points = listOf(
                    LineChartData.SeriesData.Point(0, 0f),
                    LineChartData.SeriesData.Point(1, 10.0f),
                    LineChartData.SeriesData.Point(2, 20.0f),
                    LineChartData.SeriesData.Point(3, 30.0f),
                    LineChartData.SeriesData.Point(4, 50.0f),
                    LineChartData.SeriesData.Point(5, 35.0f),
                ),
                Color.Red, gradientFill = true
            ),
            LineChartData.SeriesData(
                title = "Line B",
                points = listOf(
                    LineChartData.SeriesData.Point(0, 20f),
                    LineChartData.SeriesData.Point(1, 10.0f),
                    LineChartData.SeriesData.Point(2, 5.0f),
                    LineChartData.SeriesData.Point(3, 15.0f),
                    LineChartData.SeriesData.Point(4, 30.0f),
                    LineChartData.SeriesData.Point(5, 35.0f),
                ),
                Color.Blue, gradientFill = true
            ),
        ),
        yLabels = listOf(
            AxisLabel(0f, "0K"),
            AxisLabel(20f, "20K"),
            AxisLabel(40f, "40K"),
        ),
        drawAxis = DrawAxis.X,
        horizontalLines = true,
        legendPosition = LegendPosition.Start,
        xAxisTypeface = axisLabelTextStyle,
        yAxisTypeface = axisLabelTextStyle
    ),
    "Legend end" to LineChartData(
        series = listOf(
            LineChartData.SeriesData(
                title = "Line A",
                points = listOf(
                    LineChartData.SeriesData.Point(0, 0f),
                    LineChartData.SeriesData.Point(1, 10.0f),
                    LineChartData.SeriesData.Point(2, 20.0f),
                    LineChartData.SeriesData.Point(3, 30.0f),
                    LineChartData.SeriesData.Point(4, 50.0f),
                    LineChartData.SeriesData.Point(5, 35.0f),
                ),
                Color.Red, gradientFill = true
            ),
            LineChartData.SeriesData(
                title = "Line B",
                points = listOf(
                    LineChartData.SeriesData.Point(0, 20f),
                    LineChartData.SeriesData.Point(1, 10.0f),
                    LineChartData.SeriesData.Point(2, 5.0f),
                    LineChartData.SeriesData.Point(3, 15.0f),
                    LineChartData.SeriesData.Point(4, 30.0f),
                    LineChartData.SeriesData.Point(5, 35.0f),
                ),
                Color.Blue, gradientFill = true
            ),
        ),
        yLabels = listOf(
            AxisLabel(0f, "0K"),
            AxisLabel(20f, "20K"),
            AxisLabel(40f, "40K"),
        ),
        drawAxis = DrawAxis.X,
        horizontalLines = true,
        legendPosition = LegendPosition.End,
        xAxisTypeface = axisLabelTextStyle,
        yAxisTypeface = axisLabelTextStyle
    ),
    "Legend end, vertical alignment" to LineChartData(
        series = listOf(
            LineChartData.SeriesData(
                title = "Line A",
                points = listOf(
                    LineChartData.SeriesData.Point(0, 0f),
                    LineChartData.SeriesData.Point(1, 10.0f),
                    LineChartData.SeriesData.Point(2, 20.0f),
                    LineChartData.SeriesData.Point(3, 30.0f),
                    LineChartData.SeriesData.Point(4, 50.0f),
                    LineChartData.SeriesData.Point(5, 35.0f),
                ),
                Color.Red, gradientFill = true
            ),
            LineChartData.SeriesData(
                title = "Line B",
                points = listOf(
                    LineChartData.SeriesData.Point(0, 20f),
                    LineChartData.SeriesData.Point(1, 10.0f),
                    LineChartData.SeriesData.Point(2, 5.0f),
                    LineChartData.SeriesData.Point(3, 15.0f),
                    LineChartData.SeriesData.Point(4, 30.0f),
                    LineChartData.SeriesData.Point(5, 35.0f),
                ),
                Color.Blue, gradientFill = true
            ),
        ),
        yLabels = listOf(
            AxisLabel(0f, "0K"),
            AxisLabel(20f, "20K"),
            AxisLabel(40f, "40K"),
        ),
        drawAxis = DrawAxis.X,
        horizontalLines = true,
        legendPosition = LegendPosition.End,
        legendAlignment = LegendAlignment.Center,
        xAxisTypeface = axisLabelTextStyle,
        yAxisTypeface = axisLabelTextStyle
    ),
    "Legend bottom, center alignment" to LineChartData(
        series = listOf(
            LineChartData.SeriesData(
                title = "Line A",
                points = listOf(
                    LineChartData.SeriesData.Point(0, 0f),
                    LineChartData.SeriesData.Point(1, 10.0f),
                    LineChartData.SeriesData.Point(2, 20.0f),
                    LineChartData.SeriesData.Point(3, 30.0f),
                    LineChartData.SeriesData.Point(4, 50.0f),
                    LineChartData.SeriesData.Point(5, 35.0f),
                ),
                Color.Red, gradientFill = true
            ),
            LineChartData.SeriesData(
                title = "Line B",
                points = listOf(
                    LineChartData.SeriesData.Point(0, 20f),
                    LineChartData.SeriesData.Point(1, 10.0f),
                    LineChartData.SeriesData.Point(2, 5.0f),
                    LineChartData.SeriesData.Point(3, 15.0f),
                    LineChartData.SeriesData.Point(4, 30.0f),
                    LineChartData.SeriesData.Point(5, 35.0f),
                ),
                Color.Blue, gradientFill = true
            ),
        ),
        xLabels = listOf("A", "B", "C", "D", "E", "F"),
        yLabels = listOf(
            AxisLabel(0f, "0K"),
            AxisLabel(20f, "20K"),
            AxisLabel(40f, "40K"),
        ),
        drawAxis = DrawAxis.X,
        horizontalLines = true,
        legendPosition = LegendPosition.Bottom,
        legendAlignment = LegendAlignment.Center,
        xAxisTypeface = axisLabelTextStyle,
        yAxisTypeface = axisLabelTextStyle
    ),
    "Autogenerated Y-labels" to LineChartData(
        series = listOf(
            LineChartData.SeriesData(
                title = "Line A",
                points = listOf(
                    LineChartData.SeriesData.Point(0, 0f),
                    LineChartData.SeriesData.Point(1, 10.0f),
                    LineChartData.SeriesData.Point(2, 20.0f),
                    LineChartData.SeriesData.Point(3, 30.0f),
                    LineChartData.SeriesData.Point(4, 50.0f),
                    LineChartData.SeriesData.Point(5, 35.0f),
                ),
                Color.Red, gradientFill = true
            ),
            LineChartData.SeriesData(
                title = "Line B",
                points = listOf(
                    LineChartData.SeriesData.Point(0, 20f),
                    LineChartData.SeriesData.Point(1, 10.0f),
                    LineChartData.SeriesData.Point(2, 5.0f),
                    LineChartData.SeriesData.Point(3, 15.0f),
                    LineChartData.SeriesData.Point(4, 30.0f),
                    LineChartData.SeriesData.Point(5, 35.0f),
                ),
                Color.Blue, gradientFill = true
            ),
        ),
        xLabels = listOf("A", "B", "C", "D", "E", "F"),
        autoYLabels = true,
        maxYLabels = 4,
        drawAxis = DrawAxis.X,
        horizontalLines = true,
        legendPosition = LegendPosition.Bottom,
        legendAlignment = LegendAlignment.Center,
        xAxisTypeface = axisLabelTextStyle,
        yAxisTypeface = axisLabelTextStyle
    ),
    "Floating y-value" to LineChartData(
        series = listOf(
            LineChartData.SeriesData(
                title = "Line A",
                points = listOf(
                    LineChartData.SeriesData.Point(0, 15_000f),
                    LineChartData.SeriesData.Point(1, 10_000f),
                    LineChartData.SeriesData.Point(2, 20_000f),
                    LineChartData.SeriesData.Point(3, 30_000f),
                    LineChartData.SeriesData.Point(4, 50_000f),
                    LineChartData.SeriesData.Point(5, 35_000f),
                ),
                Color.Red, gradientFill = true
            ),
            LineChartData.SeriesData(
                title = "Line B",
                points = listOf(
                    LineChartData.SeriesData.Point(0, 20_000f),
                    LineChartData.SeriesData.Point(1, 10_000f),
                    LineChartData.SeriesData.Point(2, 18_000f),
                    LineChartData.SeriesData.Point(3, 15_000f),
                    LineChartData.SeriesData.Point(4, 30_000f),
                    LineChartData.SeriesData.Point(5, 35_000f),
                ),
                Color.Blue, gradientFill = true
            ),
        ),
        xLabels = listOf("A", "B", "C", "D", "E", "F"),
        autoYLabels = true,
        maxYLabels = 4,
        floatingYValue = true,
        drawAxis = DrawAxis.X,
        horizontalLines = true,
        legendPosition = LegendPosition.Bottom,
        legendAlignment = LegendAlignment.Center,
        xAxisTypeface = axisLabelTextStyle,
        yAxisTypeface = axisLabelTextStyle
    ),
)
*/
    }
}