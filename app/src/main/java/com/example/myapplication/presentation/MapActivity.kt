package com.example.myapplication.presentation

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.carto.styles.AnimationStyle
import com.carto.styles.AnimationStyleBuilder
import com.carto.styles.AnimationType
import com.carto.styles.MarkerStyleBuilder
import com.example.myapplication.R
import com.example.myapplication.domain.model.Vehicle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.neshan.common.model.LatLng
import org.neshan.mapsdk.MapView
import org.neshan.mapsdk.internal.utils.BitmapUtils
import org.neshan.mapsdk.model.Marker
import java.text.SimpleDateFormat


@AndroidEntryPoint
class MapActivity : AppCompatActivity() {

    private lateinit var map: MapView
    private lateinit var refreshDateTv: TextView
    private lateinit var animSt: AnimationStyle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

    }


    override fun onStart() {
        super.onStart()
        // everything related to ui is initialized here
        map = findViewById(R.id.map)
        refreshDateTv = findViewById(R.id.refreshDate)

        val viewModel: VehicleViewModel by viewModels()
        lifecycleScope.launch {
            viewModel.uiState.collect { vehicleUiState ->
                if (vehicleUiState is VehicleUiState.Success) {
                    if (vehicleUiState.data.isNotEmpty())
                        updateMap(vehicleUiState.data)
                }
            }
        }
    }


    private fun updateMap(data: List<Vehicle>) {
        data.forEach { vehicle ->
            val latLng = LatLng(vehicle.lat, vehicle.lng)
            map.addMarker(createMarker(latLng).apply {
                title = vehicle.type
            })
        }
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")

        refreshDateTv.text = data.last().modifiedAt.let {
            "Last Refresh: ${simpleDateFormat.format(it)}"
        }
        map.moveCamera(LatLng(data.last().lat, data.last().lng), 0f)
        map.setZoom(14f, 0f)

    }


    private fun createMarker(loc: LatLng): Marker {
        val animStBl = AnimationStyleBuilder()
        animStBl.fadeAnimationType = AnimationType.ANIMATION_TYPE_SMOOTHSTEP
        animStBl.sizeAnimationType = AnimationType.ANIMATION_TYPE_SPRING
        animStBl.phaseInDuration = 0.5f
        animStBl.phaseOutDuration = 0.5f
        animSt = animStBl.buildStyle()
        val markStCr = MarkerStyleBuilder()
        markStCr.size = 30f
        markStCr.bitmap = BitmapUtils.createBitmapFromAndroidBitmap(
            BitmapFactory.decodeResource(
                resources, R.drawable.ic_marker_blue
            )
        )
        markStCr.animationStyle = animSt
        val markSt = markStCr.buildStyle()
        return Marker(loc, markSt)
    }


}