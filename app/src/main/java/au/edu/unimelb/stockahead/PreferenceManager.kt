package au.edu.unimelb.stockahead

import android.content.Context
import android.content.Context.MODE_PRIVATE

import android.content.SharedPreferences


class PreferenceManager(context: Context) {
    val PREFERENCES_FILENAME = "preferences"
    val sharedPref: SharedPreferences =
        context.getSharedPreferences(PREFERENCES_FILENAME, MODE_PRIVATE)

    fun putCompanyID(companyId: Int) {
        sharedPref.edit()
            .putInt("company_id", companyId)
            .apply();
    }

    fun getCompanyID(): Int {
        return sharedPref.getInt("company_id", 1)
    }

}