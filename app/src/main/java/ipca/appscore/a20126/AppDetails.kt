package ipca.appscore.a20126

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import org.json.JSONObject

class AppDetails : AppCompatActivity() {

    var app : App? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_details)

        val textViewPoints : TextView = findViewById(R.id.textViewPoints)
        val textViewDescription : TextView = findViewById(R.id.textViewDescription)

        val bundle = intent.extras

        val actionBar = supportActionBar

        bundle?.let {
            app = App.fromJson(JSONObject(it.getString("appObject")))
        }

        actionBar!!.title = app!!.name

        textViewPoints.text = app!!.points.toString()
        textViewDescription.text = app!!.description.toString()


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_details, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)

        when (item.itemId){

            R.id.itemShare -> {

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)

                return true
            }
        }

        return false
    }
}