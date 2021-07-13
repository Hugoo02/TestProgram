package ipca.appscore.a20126

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    var apps : MutableList<App> = arrayListOf(App("Facebook", 3, "Descrição Facebook"),
        App("Instagram", 4, "Descrição Instagram"))
    val appAdapter = AppAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listViewApps = findViewById<ListView>(R.id.listViewApps)

        listViewApps.adapter = appAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)

        when (item.itemId){

            R.id.itemAverage -> {

                var average: Float = 0F

                for(index in 0 until apps.size){

                    average += apps[index].points!!.toFloat()

                }

                average /= apps.size

                Toast.makeText(this, "A média é de ${average}!", Toast.LENGTH_SHORT).show()

                return true
            }
        }

        return false
    }

    inner class  AppAdapter : BaseAdapter() {

        override fun getCount(): Int {
            return apps.size
        }

        override fun getItem(position: Int): Any {
            return apps[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            var view = layoutInflater.inflate(R.layout.row_app, parent, false)

            val textViewName = view.findViewById<TextView>(R.id.textViewName)
            val textViewPoints = view.findViewById<TextView>(R.id.textViewPoints)
            val buttonPlus = view.findViewById<Button>(R.id.buttonPlus)
            val buttonSubtract = view.findViewById<Button>(R.id.buttonSubtract)

            textViewName.text = apps[position].name
            textViewPoints.text = apps[position].points.toString()

            buttonPlus.setOnClickListener {

                if(apps[position].points!! < 5)
                {
                    apps[position].points = apps[position].points!! + 1
                    textViewPoints.text = apps[position].points.toString()
                }

            }

            buttonSubtract.setOnClickListener {

                if(apps[position].points!! > 0)
                {
                    apps[position].points = apps[position].points!! - 1
                    textViewPoints.text = apps[position].points.toString()
                }

            }

            view.setOnClickListener {

                val intent = Intent(this@MainActivity, AppDetails::class.java)
                intent.putExtra("appObject", apps[position].toJson().toString())
                startActivity(intent)

            }

            return view

        }
    }
}
