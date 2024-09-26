package com.example.aaos_onelink.components

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.AssetManager
import android.util.AttributeSet
import com.example.aaos_onelink.R
import org.mapsforge.core.graphics.Bitmap
import org.mapsforge.core.model.LatLong
import org.mapsforge.map.android.graphics.AndroidGraphicFactory
import org.mapsforge.map.android.util.AndroidUtil
import org.mapsforge.map.android.view.MapView
import org.mapsforge.map.layer.overlay.Marker
import org.mapsforge.map.layer.renderer.TileRendererLayer
import org.mapsforge.map.reader.MapFile
import org.mapsforge.map.rendertheme.InternalRenderTheme
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


class MapActivity(context: Context, attrs: AttributeSet? = null, initLatlongP: LatLong) :
    MapView(context, attrs) {

    private var currentPointMarker: Marker = Marker(initLatlongP, null, 0, 0)

    init {
        // Initialize Mapsforge
        AndroidGraphicFactory.createInstance(context.applicationContext)

        // Set up tile cache
        val tileCache = AndroidUtil.createTileCache(
            context, "mapcache", model.displayModel.tileSize, 1f,
            model.frameBufferModel.overdrawFactor
        )

        // Load map file (replace with your map file)
        val assetManager = context.assets
        val tempFile = File.createTempFile("temp", null, context.cacheDir)
        copyAssetToFile(assetManager, "thailand.map", tempFile)
        val stream = FileInputStream(tempFile)
        val mapFile = MapFile(stream)

        // Create tile renderer layer
        val tileRendererLayer = TileRendererLayer(
            tileCache, mapFile,
            model.mapViewPosition, AndroidGraphicFactory.INSTANCE
        )
        tileRendererLayer.setXmlRenderTheme(InternalRenderTheme.DEFAULT)

        // Add layer to map
        layerManager.layers.add(tileRendererLayer)

        // Set initial map position (adjust as needed)
        setCenter(initLatlongP)
        setZoomLevel((12).toByte())

        // Mark current point
        layerManager.layers.add(createMarker(initLatlongP))
    }

    fun updateCurrentLocation(p: LatLong) {
        layerManager.layers.remove(currentPointMarker)
        layerManager.layers.add(createMarker(p))
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun createMarker(p: LatLong): Marker {
        val icon: Bitmap =
            AndroidGraphicFactory.convertToBitmap(context.getDrawable(R.drawable.current_location))
        currentPointMarker = Marker(p, icon, 0, 0)
        return currentPointMarker
    }
}

fun copyAssetToFile(assetManager: AssetManager, assetName: String, outputFile: File) {
    assetManager.open(assetName).use { inputStream ->
        FileOutputStream(outputFile).use { outputStream ->
            inputStream.copyTo(outputStream)
        }
    }
}