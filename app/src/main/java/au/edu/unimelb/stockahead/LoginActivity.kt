package au.edu.unimelb.stockahead

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import au.edu.unimelb.stockahead.databinding.ActivityLoginBinding
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (PreferenceManager(this).getCompanyID() != 0) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.btnSignin.setOnClickListener {
            if (binding.txtPassword.text.toString() != binding.txtRePassword.text.toString()) {
                binding.txtRePassword.error = "Passwords don't Match"
            }
            pushData()
        }
    }

    private fun pushData() {
        val queue = Volley.newRequestQueue(this)
        val params = HashMap<String, String>()


        params["name"] = binding.txtCompanyName.text.toString()
        params["email"] = binding.txtEmail.text.toString()
        params["password"] = binding.txtPassword.text.toString()

        val url = "https://dakshag.pythonanywhere.com/company/add"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, JSONObject((params as Map<*, *>?)!!), { response ->
                PreferenceManager(this@LoginActivity).putCompanyID(response.getInt("id"))
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            },
            { exception ->
                Log.e("Errr", "That didnt work")
                exception.printStackTrace()
            }

        )
        queue.add(jsonObjectRequest)
    }
}