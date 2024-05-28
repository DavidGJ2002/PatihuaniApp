package com.appsbig.patihuaniapp.alvaro
import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

object GeoCode {
    fun reverseGeocode(context: Context, latitude: Double, longitude: Double, completion: (address: String?) -> Unit) {
        val url = "https://nominatim.openstreetmap.org/reverse?format=json&lat=$latitude&lon=$longitude"

        val requestQueue = Volley.newRequestQueue(context)

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                try {
                    val data = JSONObject(response)
                    val displayString = data.getString("display_name")
                    completion(displayString)
                } catch (e: Exception) {
                    completion(null) // Handle parsing error
                }
            },
            { error ->
                completion(null) // Handle Volley error
            }
        )

        requestQueue.add(stringRequest)
    }
}