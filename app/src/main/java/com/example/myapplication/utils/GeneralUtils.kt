package com.example.myapplication.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build


class GeneralUtils {

    companion object{

        fun isInternetConnection(context: Context?): Boolean {

            context?.let {
                val cmg = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    // Android 10+
                    cmg.getNetworkCapabilities(cmg.activeNetwork)?.let { networkCapabilities ->
                        return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                                || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    }
                } else {
                    return cmg.activeNetworkInfo?.isConnectedOrConnecting == true
                }
            }

            return false
        }
    }

}